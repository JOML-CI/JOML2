// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double3x3} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double3x3Impl implements Double3x3 {

    public double[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double3x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x3SegOpsUnsafe()
                        : new Double3x3SegOpsMS();
        static final Double3x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x3BbOpsUnsafe()
                        : new Double3x3BbOpsApi();
        static final Double3x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x3RawOpsUnsafe()
                        : new Double3x3RawOpsApi();
    }

    public Double3x3Impl() {
        data = new double[9];
        data[0] = 1;
        data[4] = 1;
        data[8] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Double3x3Impl(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double[] dd = this.data = new double[9];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m01;
        dd[4] = m11;
        dd[5] = m21;
        dd[6] = m02;
        dd[7] = m12;
        dd[8] = m22;
        this.properties = determineProperties();
    }

    public Double3x3Impl(Double3x3R src) {
        Double3x3Impl s = (Double3x3Impl) src;
        this.data = s.data.clone();
        this.properties = s.properties;
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
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.data[2] != 0 || this.data[5] != 0 || this.data[8] != 1) return 0;
        if (this.data[0] != 1 || this.data[3] != 0 || this.data[1] != 0 || this.data[4] != 1) return 1;
        if (this.data[6] != 0 || this.data[7] != 0) return 7;
        return 15;
    }

    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. */
    @Override public boolean isIdentity() { return (this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY; }
    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. */
    @Override public boolean isTranslation() { return (this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION; }
    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. */
    @Override public boolean isOrthogonal() { return (this.properties & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL; }
    /** {@return whether this matrix is known to be affine} O(1) read of the cached property bits; conservative. */
    @Override public boolean isAffine() { return (this.properties & Joml.BIT_AFFINE) == Joml.BIT_AFFINE; }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getColumn} and {@code getRow}; reached only through them.
     */
    private Double3 getColumn_identity(int col, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; _idxSw2 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; _idxSw2 = 0.0; break;
            case 2: _idxSw0 = 0.0; _idxSw1 = 0.0; _idxSw2 = 1.0; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = _idxSw2;
        return dest;
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double3 getColumn_translation(int col, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw3;
        double _idxSw4;
        double _idxSw5;
        switch (col) {
            case 0: _idxSw3 = 1.0; _idxSw4 = 0.0; _idxSw5 = 0.0; break;
            case 1: _idxSw3 = 0.0; _idxSw4 = 1.0; _idxSw5 = 0.0; break;
            case 2: _idxSw3 = sd[6]; _idxSw4 = sd[7]; _idxSw5 = 1.0; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw3;
        dd[1] = _idxSw4;
        dd[2] = _idxSw5;
        return dest;
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double3 getColumn_general(int col, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw6;
        double _idxSw7;
        double _idxSw8;
        switch (col) {
            case 0: _idxSw6 = sd[0]; _idxSw7 = sd[1]; _idxSw8 = sd[2]; break;
            case 1: _idxSw6 = sd[3]; _idxSw7 = sd[4]; _idxSw8 = sd[5]; break;
            case 2: _idxSw6 = sd[6]; _idxSw7 = sd[7]; _idxSw8 = sd[8]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw6;
        dd[1] = _idxSw7;
        dd[2] = _idxSw8;
        return dest;
    }


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
    public Double3 getColumn(int col, @Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getColumn_translation(col, dest);
        return getColumn_general(col, dest);
    }



    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Double3 getEulerAnglesXYZ_translation(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.fma(sd[7], sd[7], 1.0);
        dd[0] = _t0 < Math.fma(sd[7], sd[7], Math.fma(sd[6], sd[6], 1.0)) * 1.0E-15 ? 0.0 : Math.atan2(-sd[7], 1.0);
        dd[1] = Math.atan2(sd[6], Math.sqrt(_t0));
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Double3 getEulerAnglesXYZ_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[7], sd[7], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[6], sd[6], _t1) * 1.0E-15) {
            double _buf0 = Math.atan2(sd[5], sd[4]);
            dd[2] = 0.0;
            dd[0] = _buf0;
        } else {
            double _buf0 = Math.atan2(-sd[7], sd[8]);
            dd[2] = Math.atan2(-sd[3], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = Math.atan2(sd[6], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_translation(dest);
        return getEulerAnglesXYZ_general(dest);
    }




    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Double3 getEulerAnglesXZY_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[4], sd[4], sd[5] * sd[5]);
        if (_t1 < Math.fma(sd[3], sd[3], _t1) * 1.0E-15) {
            double _buf0 = Math.atan2(-sd[7], sd[8]);
            double _buf1 = 0.0;
            dd[0] = _buf0;
            dd[1] = _buf1;
        } else {
            double _buf0 = Math.atan2(sd[5], sd[4]);
            double _buf1 = Math.atan2(sd[6], sd[0]);
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        dd[2] = Math.atan2(-sd[3], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = Math.atan2(sd[6], 1.0);
            dd[2] = 0.0;
            return dest;
        }
        return getEulerAnglesXZY_general(dest);
    }



    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Double3 getEulerAnglesYXZ_translation(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.fma(sd[6], sd[6], 1.0);
        dd[0] = Math.atan2(-sd[7], Math.sqrt(_t0));
        dd[1] = _t0 < Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0)) * 1.0E-15 ? 0.0 : Math.atan2(sd[6], 1.0);
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Double3 getEulerAnglesYXZ_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[6], sd[6], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[7], sd[7], _t1) * 1.0E-15) {
            dd[1] = Math.atan2(-sd[2], sd[0]);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(sd[6], sd[8]);
            dd[2] = Math.atan2(sd[1], sd[4]);
        }
        dd[0] = Math.atan2(-sd[7], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYXZ_translation(dest);
        return getEulerAnglesYXZ_general(dest);
    }



    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Double3 getEulerAnglesYZX_translation(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.fma(sd[7], sd[7], 1.0);
        if (_t0 < _t0 * 1.0E-15) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(sd[6], 1.0);
        } else {
            dd[0] = Math.atan2(-sd[7], 1.0);
            dd[1] = 0.0;
        }
        dd[2] = Math.atan2(0.0, Math.sqrt(_t0));
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Double3 getEulerAnglesYZX_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[4], sd[4], sd[7] * sd[7]);
        if (_t1 < Math.fma(sd[1], sd[1], _t1) * 1.0E-15) {
            double _buf0 = 0.0;
            dd[1] = Math.atan2(sd[6], sd[8]);
            dd[0] = _buf0;
        } else {
            double _buf0 = Math.atan2(-sd[7], sd[4]);
            dd[1] = Math.atan2(-sd[2], sd[0]);
            dd[0] = _buf0;
        }
        dd[2] = Math.atan2(sd[1], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYZX_translation(dest);
        return getEulerAnglesYZX_general(dest);
    }



    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Double3 getEulerAnglesZXY_orthogonal(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[3], sd[3], sd[4] * sd[4]);
        double _buf0 = 0.0;
        double _buf1 = 0.0;
        dd[2] = _t1 < _t1 * 1.0E-15 ? Math.atan2(sd[1], sd[0]) : Math.atan2(-sd[3], sd[4]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Double3 getEulerAnglesZXY_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[3], sd[3], sd[4] * sd[4]);
        if (_t1 < Math.fma(sd[5], sd[5], _t1) * 1.0E-15) {
            double _buf0 = 0.0;
            dd[2] = Math.atan2(sd[1], sd[0]);
            dd[1] = _buf0;
        } else {
            double _buf0 = Math.atan2(-sd[2], sd[8]);
            dd[2] = Math.atan2(-sd[3], sd[4]);
            dd[1] = _buf0;
        }
        dd[0] = Math.atan2(sd[5], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getEulerAnglesZXY_orthogonal(dest);
        return getEulerAnglesZXY_general(dest);
    }



    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Double3 getEulerAnglesZYX_orthogonal(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _buf0 = 0.0;
        dd[1] = 0.0;
        dd[2] = Math.atan2(sd[1], sd[0]);
        dd[0] = _buf0;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Double3 getEulerAnglesZYX_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = Math.fma(sd[5], sd[5], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[2], sd[2], _t1) * 1.0E-15) {
            double _buf0 = 0.0;
            dd[2] = Math.atan2(-sd[3], sd[4]);
            dd[0] = _buf0;
        } else {
            double _buf0 = Math.atan2(sd[5], sd[8]);
            dd[2] = Math.atan2(sd[1], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = Math.atan2(-sd[2], Math.sqrt(_t1));
        return dest;
    }


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
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getEulerAnglesZYX_orthogonal(dest);
        return getEulerAnglesZYX_general(dest);
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code getNormalizedRotation},
     * {@code decomposeRotation} and {@code getUnnormalizedRotation}; reached only through them.
     */
    private DoubleQuat getNormalizedRotation_identity(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        return dest;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private DoubleQuat getNormalizedRotation_translation(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0));
        double _t2 = (1.0 / Math.sqrt(_t1));
        double _t5 = _t1 != 0.0 ? _t2 : 0.0;
        double _sp0, _sp1;
        if (_t1 != 0.0) {
            _sp0 = 0.5 * sd[7] * _t2;
            _sp1 = 0.5 * sd[6] * _t2;
        } else {
            _sp0 = 0.5 * 0.0;
            _sp1 = 0.5 * 0.0;
        }
        double _t10 = _t5 < 0.0 ? -1.0 : 1.0;
        double _t11 = 1.0 + _t10;
        double _t13 = _t11 + _t5;
        double _t17 = 1.0 + _t13;
        double _t18 = 1.0 + (_t10 - (1.0 + _t5));
        double _t19 = 1.0 + (_t5 - _t11);
        double _t20 = 1.0 + (1.0 - (_t10 + _t5));
        return getNormalizedRotation_translation_sc9ab89c4_1(dest, dd, _t5, _sp0, _sp1, _t10, _t13, _t17, _t18, _t19, _t20, (1.0 / Math.sqrt(_t17)), (1.0 / Math.sqrt(_t19)), (1.0 / Math.sqrt(_t18)), (1.0 / Math.sqrt(_t20)));
    }

    /** Piece 2 of {@code getNormalizedRotation_translation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_translation_sc9ab89c4_1(DoubleQuat dest, double[] dd, double _t5, double _sp0, double _sp1, double _t10, double _t13, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        if (_t13 > 0.0) {
            dd[0] = -(_sp0 * _t21);
            dd[1] = _sp1 * _t21;
            dd[2] = 0.0;
            dd[3] = 0.5 * Math.sqrt(_t17);
        } else {
            if (_t10 > Math.max(1.0, _t5)) {
                dd[0] = 0.5 * Math.sqrt(_t18);
                dd[1] = 0.0;
                dd[2] = _sp1 * _t23;
                dd[3] = -(_sp0 * _t23);
            } else {
                if (1.0 > _t5) {
                    dd[0] = 0.0;
                    dd[1] = 0.5 * Math.sqrt(_t20);
                    dd[2] = _sp0 * _t24;
                    dd[3] = _sp1 * _t24;
                } else {
                    dd[0] = _sp1 * _t22;
                    dd[1] = _sp0 * _t22;
                    dd[2] = 0.5 * Math.sqrt(_t19);
                    dd[3] = 0.0;
                }
            }
        }
        return dest;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private DoubleQuat getNormalizedRotation_general(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t6 = Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4]));
        double _t7 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        double _t8 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t9 = (1.0 / Math.sqrt(_t6));
        double _t10 = (1.0 / Math.sqrt(_t7));
        double _t21, _t23, _t27;
        if (_t6 != 0.0) {
            _t21 = sd[3] * _t9;
            _t23 = sd[4] * _t9;
            _t27 = sd[5] * _t9;
        } else {
            _t21 = 0.0;
            _t23 = 0.0;
            _t27 = 0.0;
        }
        double _t22, _t24, _t26;
        if (_t7 != 0.0) {
            _t22 = sd[7] * _t10;
            _t24 = sd[6] * _t10;
            _t26 = sd[8] * _t10;
        } else {
            _t22 = 0.0;
            _t24 = 0.0;
            _t26 = 0.0;
        }
        return getNormalizedRotation_general_se5362bf5_1(dest, sd, dd, _t8, (1.0 / Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_general_se5362bf5_1(DoubleQuat dest, double[] sd, double[] dd, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
        double _t25, _t28, _t29;
        if (_t8 != 0.0) {
            _t25 = sd[2] * _t11;
            _t28 = sd[0] * _t11;
            _t29 = sd[1] * _t11;
        } else {
            _t25 = 0.0;
            _t28 = 0.0;
            _t29 = 0.0;
        }
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
        return getNormalizedRotation_general_se5362bf5_2(dest, dd, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / Math.sqrt(_t62)), 0.5 * (1.0 / Math.sqrt(_t64)), 0.5 * (1.0 / Math.sqrt(_t65)), 0.5 * (1.0 / Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_general_se5362bf5_2(DoubleQuat dest, double[] dd, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dd[0] = _sp0 * _t36;
            dd[1] = _sp0 * _t56;
            dd[2] = _sp0 * _t57;
            dd[3] = 0.5 * Math.sqrt(_t62);
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dd[0] = 0.5 * Math.sqrt(_t63);
                dd[1] = _sp3 * _t53;
                dd[2] = _sp3 * _t55;
                dd[3] = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    dd[0] = _sp1 * _t53;
                    dd[1] = 0.5 * Math.sqrt(_t64);
                    dd[2] = _sp1 * _t39;
                    dd[3] = _sp1 * _t56;
                } else {
                    dd[0] = _sp2 * _t55;
                    dd[1] = _sp2 * _t39;
                    dd[2] = 0.5 * Math.sqrt(_t65);
                    dd[3] = _sp2 * _t57;
                }
            }
        }
        return dest;
    }


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
    public DoubleQuat getNormalizedRotation(@Mutated DoubleQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_translation(dest);
        return getNormalizedRotation_general(dest);
    }



    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_translation(int row, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw3;
        double _idxSw4;
        double _idxSw5;
        switch (row) {
            case 0: _idxSw3 = 1.0; _idxSw4 = 0.0; _idxSw5 = sd[6]; break;
            case 1: _idxSw3 = 0.0; _idxSw4 = 1.0; _idxSw5 = sd[7]; break;
            case 2: _idxSw3 = 0.0; _idxSw4 = 0.0; _idxSw5 = 1.0; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw3;
        dd[1] = _idxSw4;
        dd[2] = _idxSw5;
        return dest;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_general(int row, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw6;
        double _idxSw7;
        double _idxSw8;
        switch (row) {
            case 0: _idxSw6 = sd[0]; _idxSw7 = sd[3]; _idxSw8 = sd[6]; break;
            case 1: _idxSw6 = sd[1]; _idxSw7 = sd[4]; _idxSw8 = sd[7]; break;
            case 2: _idxSw6 = sd[2]; _idxSw7 = sd[5]; _idxSw8 = sd[8]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw6;
        dd[1] = _idxSw7;
        dd[2] = _idxSw8;
        return dest;
    }


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
    public Double3 getRow(int row, @Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getRow_translation(row, dest);
        return getRow_general(row, dest);
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getScale} and {@code decomposeScale}; reached only through
     * them.
     */
    private Double3 getScale_identity(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 1.0;
        dd[2] = 1.0;
        return dest;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Double3 getScale_translation(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 1.0;
        dd[2] = Math.sqrt(Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0)));
        return dest;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Double3 getScale_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        dd[1] = Math.sqrt(Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        dd[2] = Math.sqrt(Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7])));
        return dest;
    }


    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored) and store the result in {@code dest}.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getScale(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getScale_identity(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getScale_translation(dest);
        return getScale_general(dest);
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Double2 getTranslation_identity(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Double2 getTranslation_general(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        return dest;
    }


    /**
     * Get the translation of this matrix, read from its last column as {@code (m02, m12)} (the 2D
     * homogeneous convention) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 getTranslation(@Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getTranslation_identity(dest);
        return getTranslation_general(dest);
    }



    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private DoubleQuat getUnnormalizedRotation_translation(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = -(0.25 * sd[7]);
        dd[1] = 0.25 * sd[6];
        dd[2] = 0.0;
        dd[3] = 1.0;
        return dest;
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private DoubleQuat getUnnormalizedRotation_orthogonal(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t3 = sd[0] + sd[4];
        double _t6 = 1.0 + _t3;
        double _t10 = 1.0 + _t6;
        double _t11 = 1.0 + (sd[0] - (1.0 + sd[4]));
        double _t12 = 1.0 + (sd[4] - (1.0 + sd[0]));
        double _t13 = 1.0 + (1.0 - _t3);
        return getUnnormalizedRotation_orthogonal_s604ecc81_1(dest, sd, dd, 0.5 * sd[6], 0.5 * sd[7], 0.5 * (sd[3] + sd[1]), 0.5 * (sd[1] - sd[3]), _t6, _t10, _t11, _t12, _t13, (1.0 / Math.sqrt(_t10)), (1.0 / Math.sqrt(_t12)), (1.0 / Math.sqrt(_t13)), (1.0 / Math.sqrt(_t11)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_orthogonal}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_orthogonal_s604ecc81_1(DoubleQuat dest, double[] sd, double[] dd, double _sp1, double _sp0, double _sp2, double _sp3, double _t6, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        if (_t6 > 0.0) {
            double _buf0 = -(_sp0 * _t14);
            dd[1] = _sp1 * _t14;
            dd[2] = _sp3 * _t14;
            dd[3] = 0.5 * Math.sqrt(_t10);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], 1.0)) {
                double _buf0 = 0.5 * Math.sqrt(_t11);
                dd[1] = _sp2 * _t17;
                dd[2] = _sp1 * _t17;
                dd[3] = -(_sp0 * _t17);
                dd[0] = _buf0;
            } else {
                if (sd[4] > 1.0) {
                    double _buf0 = _sp2 * _t15;
                    dd[1] = 0.5 * Math.sqrt(_t12);
                    dd[2] = _sp0 * _t15;
                    dd[3] = _sp1 * _t15;
                    dd[0] = _buf0;
                } else {
                    double _buf0 = _sp1 * _t16;
                    dd[1] = _sp0 * _t16;
                    dd[2] = 0.5 * Math.sqrt(_t13);
                    dd[3] = _sp3 * _t16;
                    dd[0] = _buf0;
                }
            }
        }
        return dest;
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private DoubleQuat getUnnormalizedRotation_general(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = sd[0] + sd[4];
        double _t10 = sd[8] + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (sd[0] - (sd[4] + sd[8]));
        double _t16 = 1.0 + (sd[4] - (sd[0] + sd[8]));
        double _t17 = 1.0 + (sd[8] - _t0);
        return getUnnormalizedRotation_general_se6574420_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / Math.sqrt(_t14)), 0.5 * (1.0 / Math.sqrt(_t16)), 0.5 * (1.0 / Math.sqrt(_t17)), 0.5 * (1.0 / Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_general_se6574420_1(DoubleQuat dest, double[] sd, double[] dd, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            double _buf0 = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                double _buf0 = 0.5 * Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    double _buf0 = _sp1 * _t4;
                    dd[1] = 0.5 * Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    double _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5 * Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                    dd[0] = _buf0;
                }
            }
        }
        return dest;
    }


    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getUnnormalizedRotation(@Mutated DoubleQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getUnnormalizedRotation_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getUnnormalizedRotation_orthogonal(dest);
        return getUnnormalizedRotation_general(dest);
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor}, {@code invert}, {@code normal} and
     * {@code transpose}; reached only through them.
     */
    private Double3x3 cofactor_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }



    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Double3x3 cofactor_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = -sd[6];
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = -sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Double3x3 cofactor_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[2] = -sd[6];
        dd[5] = -sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Double3x3 cofactor_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[2] = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[3] = -sd[1];
        double _buf1 = sd[4];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Double3x3 cofactor_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[2] = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[3] = -sd[1];
        double _buf1 = sd[4];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double3x3 cofactor_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[4];
        double _buf1 = -sd[3];
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4]));
        double _buf2 = -sd[1];
        double _buf3 = sd[0];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7]));
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double3x3 cofactor_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        double _buf1 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8]));
        double _buf2 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4]));
        double _buf3 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8]));
        double _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2]));
        double _buf5 = Math.fma(sd[6], sd[1], -(sd[0] * sd[7]));
        dd[6] = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        dd[7] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5]));
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 cofactor(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine(dest);
        return cofactor_general(dest);
    }


    /**
     * Compute the cofactor matrix of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 cofactor() {
        if (Joml.RETURN_NEW) return cofactor(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            ((Double3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine(this);
        return cofactor_general(this);
    }


    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    public double determinant() {
        double[] sd = this.data;
        return Math.fma(sd[6], Math.fma(sd[1], sd[5], -(sd[4] * sd[2])), Math.fma(sd[0], Math.fma(sd[4], sd[8], -(sd[7] * sd[5])), -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        double[] sd = this.data;
        return Math.sqrt(Math.fma(sd[0], sd[0], sd[3] * sd[3]) + Math.fma(sd[6], sd[6], sd[1] * sd[1]) + (Math.fma(sd[4], sd[4], sd[7] * sd[7]) + Math.fma(sd[2], sd[2], Math.fma(sd[5], sd[5], sd[8] * sd[8]))));
    }




    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = -sd[1];
        dd[2] = 0.0;
        dd[3] = sd[1];
        double _buf1 = sd[4];
        dd[5] = 0.0;
        double _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = -sd[1];
        dd[3] = sd[1];
        double _buf1 = sd[4];
        double _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = sd[4] * _t3_inv;
        double _buf1 = -(sd[1] * _t3_inv);
        dd[2] = 0.0;
        double _buf2 = -(sd[3] * _t3_inv);
        double _buf3 = sd[0] * _t3_inv;
        dd[5] = 0.0;
        double _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = sd[4] * _t3_inv;
        double _buf1 = -(sd[1] * _t3_inv);
        double _buf2 = -(sd[3] * _t3_inv);
        double _buf3 = sd[0] * _t3_inv;
        double _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        double _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        double _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t13_inv = 1.0 / _t13;
        double _buf1 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv;
        double _buf3 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv;
        double _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        return invert_general_s937977dd_1(dest, sd, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4);
    }

    /**
     * Piece 2 of {@code invert_general}, split to fit the inline budget. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private Double3x3 invert_general_s937977dd_1(Double3x3 dest, double[] sd, double[] dd, double _t13_inv, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[6] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 invert(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine(dest);
        return invert_general(dest);
    }


    /**
     * Invert this matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 invert() {
        if (Joml.RETURN_NEW) return invert(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            ((Double3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine_self(this);
        return invert_general(this);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_orthogonal_affine(@Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[0], sd[3], sd[6]);
        double _t8 = sd[4] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[3] * _t1;
        double _t11 = sd[1] * _t0;
        double _t12 = sd[7] * _t0;
        double _t13 = sd[6] * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }





    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(1.0, 0.0, sd[6]);
        double _t1 = unitScale(0.0, 1.0, sd[7]);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0;
        dd[6] = -(sd[6] * _t0 * _t2_inv);
        dd[7] = -(sd[7] * _t1 * _t3_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[2], sd[5], sd[8]);
        double _t2 = unitScale(sd[0], sd[3], sd[6]);
        double _t12 = sd[4] * _t0;
        double _t13 = sd[8] * _t1;
        double _t14 = sd[7] * _t0;
        double _t15 = sd[5] * _t1;
        double _t16 = sd[1] * _t0;
        double _t17 = sd[2] * _t1;
        double _t18 = sd[6] * _t2;
        double _t19 = sd[0] * _t2;
        double _t20 = sd[3] * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[2] = _t28 * _sp0;
        return invert_degenerate_general_sc8c46d2_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t1 * _t33_inv, _t0 * _t33_inv);
    }

    /**
     * Piece 2 of {@code invert_degenerate_general}, split to fit the inline budget. Shared by the
     * identical private paths of {@code invert} and {@code invertProduct}; reached only through
     * them.
     */
    private Double3x3 invert_degenerate_general_sc8c46d2_1(Double3x3 dest, double[] dd, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _sp2, double _sp1) {
        dd[3] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[6] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[7] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_orthogonal_affine(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return invert_degenerate_general(dest);
    }



    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        return invertProduct_general_s124c80d7_1(other, dest, sd, otherData, dd, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[8], sd[8], Math.fma(otherData[6], sd[2], otherData[7] * sd[5])), Math.fma(otherData[5], sd[8], Math.fma(otherData[3], sd[2], otherData[4] * sd[5])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[2], sd[8], Math.fma(otherData[0], sd[2], otherData[1] * sd[5])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_general_s124c80d7_1(Double3x3R other, Double3x3 dest, double[] sd, double[] otherData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3]));
        double _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 2.2250738585072014E-308 && Math.abs(_t40) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t40_inv = 1.0 / _t40;
        dd[0] = _t33 * _t40_inv;
        dd[1] = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        dd[2] = _t34 * _t40_inv;
        dd[3] = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        dd[4] = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        dd[5] = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
        dd[6] = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        dd[7] = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        dd[8] = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(otherData[4], otherData[8], -(otherData[7] * otherData[5]));
        double _t7 = Math.fma(otherData[1], otherData[5], -(otherData[4] * otherData[2]));
        double _t13 = Math.fma(otherData[6], _t7, Math.fma(otherData[0], _t6, -(otherData[3] * Math.fma(otherData[1], otherData[8], -(otherData[7] * otherData[2])))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t13_inv = 1.0 / _t13;
        double _buf1 = Math.fma(otherData[7], otherData[2], -(otherData[1] * otherData[8])) * _t13_inv;
        double _buf3 = Math.fma(otherData[6], otherData[5], -(otherData[3] * otherData[8])) * _t13_inv;
        double _buf4 = Math.fma(otherData[0], otherData[8], -(otherData[6] * otherData[2])) * _t13_inv;
        dd[5] = Math.fma(otherData[3], otherData[2], -(otherData[0] * otherData[5])) * _t13_inv;
        return invertProduct_identity_se1d8bcd7_1(other, dest, otherData, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4);
    }

    /** Piece 2 of {@code invertProduct_identity}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_identity_se1d8bcd7_1(Double3x3R other, Double3x3 dest, double[] otherData, double[] dd, double _t13_inv, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(otherData[3], otherData[7], -(otherData[6] * otherData[4])) * _t13_inv;
        dd[7] = Math.fma(otherData[6], otherData[1], -(otherData[0] * otherData[7])) * _t13_inv;
        dd[8] = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[6] = _buf5;
        ((Double3x3Impl) dest).properties = ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.fma(otherData[5], sd[7], otherData[4]);
        double _t1 = Math.fma(otherData[8], sd[7], otherData[7]);
        double _t2 = Math.fma(otherData[8], sd[6], otherData[6]);
        double _t3 = Math.fma(otherData[2], sd[7], otherData[1]);
        double _t4 = Math.fma(otherData[2], sd[6], otherData[0]);
        double _t5 = Math.fma(otherData[5], sd[6], otherData[3]);
        double _t12 = Math.fma(otherData[8], _t0, -(otherData[5] * _t1));
        double _t13 = Math.fma(otherData[5], _t3, -(otherData[2] * _t0));
        double _t19 = Math.fma(_t2, _t13, Math.fma(_t4, _t12, -(_t5 * Math.fma(otherData[8], _t3, -(otherData[2] * _t1)))));
        if (!(Math.abs(_t19) > 2.2250738585072014E-308 && Math.abs(_t19) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t19_inv = 1.0 / _t19;
        dd[0] = _t12 * _t19_inv;
        dd[1] = Math.fma(otherData[2], _t1, -(otherData[8] * _t3)) * _t19_inv;
        return invertProduct_translation_sd8fc34b8_1(other, dest, otherData, dd, _t0, _t1, _t2, _t3, _t4, _t5, _t13, _t19_inv);
    }

    /** Piece 2 of {@code invertProduct_translation}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_translation_sd8fc34b8_1(Double3x3R other, Double3x3 dest, double[] otherData, double[] dd, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t13, double _t19_inv) {
        double _buf0 = _t13 * _t19_inv;
        dd[3] = Math.fma(otherData[5], _t2, -(otherData[8] * _t5)) * _t19_inv;
        dd[4] = Math.fma(otherData[8], _t4, -(otherData[2] * _t2)) * _t19_inv;
        dd[5] = Math.fma(otherData[2], _t5, -(otherData[5] * _t4)) * _t19_inv;
        dd[6] = Math.fma(_t5, _t1, -(_t2 * _t0)) * _t19_inv;
        dd[7] = Math.fma(_t2, _t3, -(_t4 * _t1)) * _t19_inv;
        dd[8] = Math.fma(_t4, _t0, -(_t5 * _t3)) * _t19_inv;
        dd[2] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t12 = Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4]));
        double _t13 = Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4]));
        double _t15 = Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4]));
        return invertProduct_orthogonal_s25ddd0f0_1(other, dest, _props, otherData, dd, _t12, _t13, Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])), _t15, Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])), Math.fma(otherData[8], _t12, -(otherData[5] * _t13)), Math.fma(otherData[5], _t15, -(otherData[2] * _t12)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_orthogonal_s25ddd0f0_1(Double3x3R other, Double3x3 dest, int _props, double[] otherData, double[] dd, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t24, double _t25) {
        double _t31 = Math.fma(_t14, _t25, Math.fma(_t16, _t24, -(_t17 * Math.fma(otherData[8], _t15, -(otherData[2] * _t13)))));
        if (!(Math.abs(_t31) > 2.2250738585072014E-308 && Math.abs(_t31) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t31_inv = 1.0 / _t31;
        dd[0] = _t24 * _t31_inv;
        dd[1] = Math.fma(otherData[2], _t13, -(otherData[8] * _t15)) * _t31_inv;
        double _buf0 = _t25 * _t31_inv;
        dd[3] = Math.fma(otherData[5], _t14, -(otherData[8] * _t17)) * _t31_inv;
        dd[4] = Math.fma(otherData[8], _t16, -(otherData[2] * _t14)) * _t31_inv;
        dd[5] = Math.fma(otherData[2], _t17, -(otherData[5] * _t16)) * _t31_inv;
        dd[6] = Math.fma(_t17, _t13, -(_t12 * _t14)) * _t31_inv;
        dd[7] = Math.fma(_t15, _t14, -(_t16 * _t13)) * _t31_inv;
        dd[8] = Math.fma(_t16, _t12, -(_t15 * _t17)) * _t31_inv;
        dd[2] = _buf0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double3x3 invertProduct_identity_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -otherData[6];
        dd[7] = -otherData[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = otherData[4] * _t3_inv;
        double _buf1 = -(otherData[1] * _t3_inv);
        dd[2] = 0.0;
        double _buf2 = -(otherData[3] * _t3_inv);
        double _buf3 = otherData[0] * _t3_inv;
        dd[5] = 0.0;
        double _buf4 = Math.fma(otherData[3], otherData[7], -(otherData[6] * otherData[4])) * _t3_inv;
        dd[7] = Math.fma(otherData[6], otherData[1], -(otherData[0] * otherData[7])) * _t3_inv;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -(otherData[6] + sd[6]);
        dd[7] = -(otherData[7] + sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t1 = otherData[7] + sd[7];
        double _t2 = otherData[6] + sd[6];
        double _t5 = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1]));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = otherData[4] * _t5_inv;
        double _buf1 = -(otherData[1] * _t5_inv);
        dd[2] = 0.0;
        double _buf2 = -(otherData[3] * _t5_inv);
        double _buf3 = otherData[0] * _t5_inv;
        dd[5] = 0.0;
        dd[6] = Math.fma(otherData[3], _t1, -(otherData[4] * _t2)) * _t5_inv;
        dd[7] = Math.fma(otherData[1], _t2, -(otherData[0] * _t1)) * _t5_inv;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = -sd[1];
        dd[2] = 0.0;
        dd[3] = sd[1];
        double _buf1 = sd[4];
        dd[5] = 0.0;
        double _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = -sd[1];
        dd[0] = sd[4];
        double _buf0 = _t0;
        dd[2] = 0.0;
        dd[3] = sd[1];
        double _buf1 = sd[4];
        dd[5] = 0.0;
        double _buf2 = Math.fma(_t0, sd[7], Math.fma(-sd[6], sd[4], -otherData[6]));
        dd[7] = Math.fma(sd[6], sd[1], Math.fma(-sd[4], sd[7], -otherData[7]));
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_affine(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        double _t7 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        double _t8 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        double _t9 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        return invertProduct_orthogonal_affine_sd2c1fabe_1(other, dest, _props, dd, _t6, _t7, _t8, _t9, Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7])), Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6])), Math.fma(_t7, _t6, -(_t8 * _t9)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal_affine}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_orthogonal_affine_sd2c1fabe_1(Double3x3R other, Double3x3 dest, int _props, double[] dd, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t15) {
        if (!(Math.abs(_t15) > 2.2250738585072014E-308 && Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t15_inv = 1.0 / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = 0.0;
        dd[3] = -(_t9 * _t15_inv);
        dd[4] = _t7 * _t15_inv;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t10, _t9, -(_t11 * _t6)) * _t15_inv;
        dd[7] = Math.fma(_t11, _t8, -(_t10 * _t7)) * _t15_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_affine_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = sd[4] * _t3_inv;
        double _buf1 = -(sd[1] * _t3_inv);
        dd[2] = 0.0;
        double _buf2 = -(sd[3] * _t3_inv);
        double _buf3 = sd[0] * _t3_inv;
        dd[5] = 0.0;
        double _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_affine_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t5 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t6 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        double _t7 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _buf0 = sd[4] * _t7_inv;
        double _buf1 = -(sd[1] * _t7_inv);
        dd[2] = 0.0;
        double _buf2 = -(sd[3] * _t7_inv);
        double _buf3 = sd[0] * _t7_inv;
        dd[5] = 0.0;
        dd[6] = Math.fma(sd[3], _t5, -(sd[4] * _t6)) * _t7_inv;
        dd[7] = Math.fma(sd[1], _t6, -(sd[0] * _t5)) * _t7_inv;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        double _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        double _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t13_inv = 1.0 / _t13;
        double _buf1 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv;
        double _buf3 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv;
        double _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        return invert_general_s937977dd_1(dest, sd, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t5 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        double _t6 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        double _t7 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t8 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        double _t13 = Math.fma(sd[4], _t6, -(sd[5] * _t7));
        double _t19 = Math.fma(_t8, _t5, Math.fma(sd[0], _t13, -(sd[3] * Math.fma(sd[1], _t6, -(sd[2] * _t7)))));
        if (!(Math.abs(_t19) > 2.2250738585072014E-308 && Math.abs(_t19) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t19_inv = 1.0 / _t19;
        return invertProduct_general_translation_s534b8a3b_1(dest, sd, dd, _t6, _t7, _t8, _t19_inv, _t13 * _t19_inv, Math.fma(sd[2], _t7, -(sd[1] * _t6)) * _t19_inv, _t5 * _t19_inv, Math.fma(sd[5], _t8, -(sd[3] * _t6)) * _t19_inv);
    }

    /** Piece 2 of {@code invertProduct_general_translation}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_general_translation_s534b8a3b_1(Double3x3 dest, double[] sd, double[] dd, double _t6, double _t7, double _t8, double _t19_inv, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(sd[0], _t6, -(sd[2] * _t8)) * _t19_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t19_inv;
        dd[6] = Math.fma(sd[3], _t7, -(sd[4] * _t8)) * _t19_inv;
        dd[7] = Math.fma(sd[1], _t8, -(sd[0] * _t7)) * _t19_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t19_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t9 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        double _t10 = Math.fma(otherData[3], sd[2], otherData[4] * sd[5]);
        double _t11 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        double _t12 = Math.fma(otherData[0], sd[2], otherData[1] * sd[5]);
        double _t15 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        double _t16 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        return invertProduct_general_affine_sa517c11f_1(other, dest, dd, _t9, _t10, _t11, _t12, Math.fma(otherData[0], sd[0], otherData[1] * sd[3]), Math.fma(otherData[3], sd[0], otherData[4] * sd[3]), _t15, _t16, Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6])), Math.fma(_t11, _t10, -(_t12 * _t9)), Math.fma(_t15, _t9, -(_t16 * _t10)));
    }

    /** Piece 2 of {@code invertProduct_general_affine}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_general_affine_sa517c11f_1(Double3x3R other, Double3x3 dest, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t24, double _t25) {
        double _t31 = Math.fma(_t17, _t24, Math.fma(_t13, _t25, -(_t14 * Math.fma(_t15, _t11, -(_t16 * _t12)))));
        if (!(Math.abs(_t31) > 2.2250738585072014E-308 && Math.abs(_t31) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t31_inv = 1.0 / _t31;
        dd[0] = _t25 * _t31_inv;
        dd[1] = Math.fma(_t16, _t12, -(_t15 * _t11)) * _t31_inv;
        dd[2] = _t24 * _t31_inv;
        dd[3] = Math.fma(_t17, _t10, -(_t15 * _t14)) * _t31_inv;
        dd[4] = Math.fma(_t15, _t13, -(_t17 * _t12)) * _t31_inv;
        dd[5] = Math.fma(_t12, _t14, -(_t13 * _t10)) * _t31_inv;
        dd[6] = Math.fma(_t16, _t14, -(_t17 * _t9)) * _t31_inv;
        dd[7] = Math.fma(_t17, _t11, -(_t16 * _t13)) * _t31_inv;
        dd[8] = Math.fma(_t13, _t9, -(_t11 * _t14)) * _t31_inv;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 invertProduct(Double3x3R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        int q = ((Double3x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other, dest);
            return invertProduct_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other, dest);
            return invertProduct_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_sa3d1cec_1(other, dest, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_sa3d1cec_1(Double3x3R other, Double3x3 dest, int p, int q) {
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, dest, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other, dest);
        return invertProduct_general(other, dest);
    }


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
    @Mutated public Double3x3 invertProduct(Double3x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double3x3());
        int p = this.properties;
        int q = ((Double3x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other, this);
            return invertProduct_identity(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other, this);
            return invertProduct_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, this, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s159815ad_1(other, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_s159815ad_1(Double3x3R other, int p, int q) {
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, this, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, this, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other, this);
        return invertProduct_general(other, this);
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 invertProduct(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t18 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        double _t19 = Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5]));
        double _t20 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
        double _t21 = Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4]));
        return invertProduct_s1401083f_1(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest, dd, _t18, _t19, _t20, _t21, Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(_t18, _t19, -(_t20 * _t21)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_s1401083f_1(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, Double3x3 dest, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t33) {
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 2.2250738585072014E-308 && Math.abs(_t40) < 4.49423283715579E307)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest);
        double _t40_inv = 1.0 / _t40;
        dd[0] = _t33 * _t40_inv;
        dd[1] = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        dd[2] = _t34 * _t40_inv;
        dd[3] = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        dd[4] = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        dd[5] = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
        dd[6] = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        dd[7] = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        dd[8] = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        return invertProduct_degenerate_general_se92a1ffa_1(dest, sd, otherData, dd, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[8], sd[8], Math.fma(otherData[6], sd[2], otherData[7] * sd[5])), Math.fma(otherData[2], sd[8], Math.fma(otherData[0], sd[2], otherData[1] * sd[5])), Math.fma(otherData[5], sd[8], Math.fma(otherData[3], sd[2], otherData[4] * sd[5])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_se92a1ffa_1(Double3x3 dest, double[] sd, double[] otherData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3]));
        double _t27 = unitScale(_t19, _t18, _t20);
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
        double _sp1 = _t27 * _t60_inv;
        double _sp0 = _t29 * _t60_inv;
        dd[0] = _t54 * _sp0;
        dd[1] = Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0;
        dd[2] = _t55 * _sp0;
        dd[3] = Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1;
        return invertProduct_degenerate_general_se92a1ffa_2(dest, dd, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t28 * _t60_inv, _sp1);
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_se92a1ffa_2(Double3x3 dest, double[] dd, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _sp2, double _sp1) {
        dd[4] = Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1;
        dd[5] = Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1;
        dd[6] = Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2;
        dd[7] = Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2;
        dd[8] = Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[4], otherData[7]);
        double _t1 = unitScale(otherData[2], otherData[5], otherData[8]);
        double _t2 = unitScale(otherData[0], otherData[3], otherData[6]);
        double _t12 = otherData[4] * _t0;
        double _t13 = otherData[8] * _t1;
        double _t14 = otherData[7] * _t0;
        double _t15 = otherData[5] * _t1;
        double _t16 = otherData[1] * _t0;
        double _t17 = otherData[2] * _t1;
        double _t18 = otherData[6] * _t2;
        double _t19 = otherData[0] * _t2;
        double _t20 = otherData[3] * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        return invertProduct_degenerate_identity_s851e5a08_1(other, dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _t0 * _t33_inv, _sp0);
    }

    /** Piece 2 of {@code invertProduct_degenerate_identity}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_identity_s851e5a08_1(Double3x3R other, Double3x3 dest, double[] dd, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t28, double _sp2, double _sp1, double _sp0) {
        dd[2] = _t28 * _sp0;
        dd[3] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[6] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[7] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(otherData[2], otherData[5], otherData[8]);
        double _t1 = Math.fma(otherData[5], sd[7], otherData[4]);
        double _t2 = Math.fma(otherData[2], sd[7], otherData[1]);
        double _t3 = Math.fma(otherData[8], sd[7], otherData[7]);
        double _t4 = Math.fma(otherData[2], sd[6], otherData[0]);
        double _t5 = Math.fma(otherData[5], sd[6], otherData[3]);
        double _t6 = Math.fma(otherData[8], sd[6], otherData[6]);
        double _t10 = otherData[8] * _t0;
        double _t11 = otherData[5] * _t0;
        double _t12 = otherData[2] * _t0;
        double _t13 = unitScale(_t2, _t1, _t3);
        double _t14 = unitScale(_t4, _t5, _t6);
        double _t21 = _t1 * _t13;
        double _t22 = _t3 * _t13;
        double _t23 = _t2 * _t13;
        return invertProduct_degenerate_translation_s2b1098f5_1(other, dest, dd, _t0, _t10, _t11, _t12, _t13, _t14, _t21, _t22, _t23, _t6 * _t14, _t4 * _t14, _t5 * _t14, Math.fma(_t10, _t21, -(_t11 * _t22)), Math.fma(_t11, _t23, -(_t12 * _t21)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_translation_s2b1098f5_1(Double3x3R other, Double3x3 dest, double[] dd, double _t0, double _t10, double _t11, double _t12, double _t13, double _t14, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t33, double _t34) {
        double _t39_inv = 1.0 / Math.fma(_t34, _t24, Math.fma(_t33, _t25, -(Math.fma(_t10, _t23, -(_t12 * _t22)) * _t26)));
        double _sp2 = _t0 * _t39_inv;
        double _sp1 = _t13 * _t39_inv;
        double _sp0 = _t14 * _t39_inv;
        dd[0] = _t33 * _sp0;
        dd[1] = Math.fma(_t12, _t22, -(_t10 * _t23)) * _sp0;
        dd[2] = _t34 * _sp0;
        dd[3] = Math.fma(_t11, _t24, -(_t10 * _t26)) * _sp1;
        dd[4] = Math.fma(_t10, _t25, -(_t12 * _t24)) * _sp1;
        dd[5] = Math.fma(_t12, _t26, -(_t11 * _t25)) * _sp1;
        dd[6] = Math.fma(_t26, _t22, -(_t24 * _t21)) * _sp2;
        dd[7] = Math.fma(_t24, _t23, -(_t25 * _t22)) * _sp2;
        dd[8] = Math.fma(_t25, _t21, -(_t26 * _t23)) * _sp2;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = unitScale(otherData[2], otherData[5], otherData[8]);
        return invertProduct_degenerate_orthogonal_s3ecad5f3_1(dest, _props, dd, _t6, otherData[8] * _t6, otherData[5] * _t6, otherData[2] * _t6, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])), Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_orthogonal_s3ecad5f3_1(Double3x3 dest, int _props, double[] dd, double _t6, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
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
        double _sp2 = _t6 * _t51_inv;
        double _sp1 = _t25 * _t51_inv;
        double _sp0 = _t26 * _t51_inv;
        dd[0] = _t45 * _sp0;
        dd[1] = Math.fma(_t18, _t34, -(_t16 * _t35)) * _sp0;
        dd[2] = _t46 * _sp0;
        dd[3] = Math.fma(_t17, _t36, -(_t16 * _t38)) * _sp1;
        dd[4] = Math.fma(_t16, _t37, -(_t18 * _t36)) * _sp1;
        dd[5] = Math.fma(_t18, _t38, -(_t17 * _t37)) * _sp1;
        dd[6] = Math.fma(_t38, _t34, -(_t33 * _t36)) * _sp2;
        return invertProduct_degenerate_orthogonal_s3ecad5f3_2(dest, _props, dd, _t33, _t34, _t35, _t36, _t37, _t38, _sp2);
    }

    /** Piece 3 of {@code invertProduct_degenerate_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_orthogonal_s3ecad5f3_2(Double3x3 dest, int _props, double[] dd, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _sp2) {
        dd[7] = Math.fma(_t35, _t36, -(_t37 * _t34)) * _sp2;
        dd[8] = Math.fma(_t37, _t33, -(_t35 * _t38)) * _sp2;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(1.0, 0.0, otherData[6]);
        double _t1 = unitScale(0.0, 1.0, otherData[7]);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0;
        dd[6] = -(otherData[6] * _t0 * _t2_inv);
        dd[7] = -(otherData[7] * _t1 * _t3_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[4], otherData[7]);
        double _t1 = unitScale(otherData[0], otherData[3], otherData[6]);
        double _t8 = otherData[4] * _t0;
        double _t9 = otherData[0] * _t1;
        double _t10 = otherData[3] * _t1;
        double _t11 = otherData[1] * _t0;
        double _t12 = otherData[7] * _t0;
        double _t13 = otherData[6] * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(1.0, 0.0, sd[6]);
        double _t1 = unitScale(0.0, 1.0, sd[7]);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0;
        dd[6] = -(sd[6] * _t0 * _t2_inv);
        dd[7] = -(sd[7] * _t1 * _t3_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = otherData[6] + sd[6];
        double _t1 = otherData[7] + sd[7];
        double _t2 = unitScale(1.0, 0.0, _t0);
        double _t3 = unitScale(0.0, 1.0, _t1);
        double _t4_inv = 1.0 / _t2;
        double _t5_inv = 1.0 / _t3;
        dd[0] = _t2 * _t4_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t3 * _t5_inv;
        dd[5] = 0.0;
        dd[6] = -(_t0 * _t2 * _t4_inv);
        dd[7] = -(_t1 * _t3 * _t5_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = otherData[7] + sd[7];
        double _t1 = otherData[6] + sd[6];
        double _t2 = unitScale(otherData[1], otherData[4], _t0);
        double _t3 = unitScale(otherData[0], otherData[3], _t1);
        double _t8 = otherData[4] * _t2;
        double _t9 = otherData[0] * _t3;
        double _t10 = otherData[3] * _t3;
        double _t11 = otherData[1] * _t2;
        double _t14 = _t0 * _t2;
        double _t15 = _t1 * _t3;
        double _t18_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t2 * _t18_inv;
        double _sp0 = _t3 * _t18_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t10, _t14, -(_t8 * _t15)) * _t18_inv;
        dd[7] = Math.fma(_t11, _t15, -(_t9 * _t14)) * _t18_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_identity(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[0], sd[3], sd[6]);
        double _t8 = sd[4] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[3] * _t1;
        double _t11 = sd[1] * _t0;
        double _t12 = sd[7] * _t0;
        double _t13 = sd[6] * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_translation(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t2 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t3 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        double _t4 = unitScale(sd[1], sd[4], _t2);
        double _t5 = unitScale(sd[0], sd[3], _t3);
        double _t10 = sd[4] * _t4;
        double _t11 = sd[0] * _t5;
        double _t12 = sd[3] * _t5;
        double _t13 = sd[1] * _t4;
        double _t16 = _t2 * _t4;
        double _t17 = _t3 * _t5;
        double _t20_inv = 1.0 / Math.fma(_t11, _t10, -(_t12 * _t13));
        double _sp1 = _t4 * _t20_inv;
        double _sp0 = _t5 * _t20_inv;
        dd[0] = _t10 * _sp0;
        dd[1] = -(_t13 * _sp0);
        dd[2] = 0.0;
        dd[3] = -(_t12 * _sp1);
        dd[4] = _t11 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t12, _t16, -(_t10 * _t17)) * _t20_inv;
        dd[7] = Math.fma(_t13, _t17, -(_t11 * _t16)) * _t20_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_affine(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        double _t7 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        double _t8 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        double _t9 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        double _t10 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t11 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        double _t12 = unitScale(_t7, _t6, _t10);
        double _t13 = unitScale(_t8, _t9, _t11);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        double _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = 0.0;
        return invertProduct_degenerate_orthogonal_affine_sef32a38f_1(dest, _props, dd, _t18, _t19, _t20, _t21, _t10 * _t12, _t11 * _t13, _t28_inv, _t12 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate_orthogonal_affine}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_orthogonal_affine_sef32a38f_1(Double3x3 dest, int _props, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t24, double _t25, double _t28_inv, double _sp1) {
        dd[3] = -(_t21 * _sp1);
        dd[4] = _t19 * _sp1;
        dd[5] = 0.0;
        dd[6] = Math.fma(_t24, _t21, -(_t25 * _t18)) * _t28_inv;
        dd[7] = Math.fma(_t25, _t20, -(_t24 * _t19)) * _t28_inv;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general_identity(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[2], sd[5], sd[8]);
        double _t2 = unitScale(sd[0], sd[3], sd[6]);
        double _t12 = sd[4] * _t0;
        double _t13 = sd[8] * _t1;
        double _t14 = sd[7] * _t0;
        double _t15 = sd[5] * _t1;
        double _t16 = sd[1] * _t0;
        double _t17 = sd[2] * _t1;
        double _t18 = sd[6] * _t2;
        double _t19 = sd[0] * _t2;
        double _t20 = sd[3] * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[2] = _t28 * _sp0;
        return invert_degenerate_general_sc8c46d2_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t1 * _t33_inv, _t0 * _t33_inv);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t4 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        double _t5 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        double _t6 = unitScale(sd[1], sd[4], _t3);
        double _t7 = unitScale(sd[2], sd[5], _t4);
        double _t8 = unitScale(sd[0], sd[3], _t5);
        double _t15 = sd[4] * _t6;
        double _t16 = sd[5] * _t7;
        double _t17 = sd[1] * _t6;
        double _t18 = sd[2] * _t7;
        double _t24 = _t4 * _t7;
        double _t25 = _t3 * _t6;
        return invertProduct_degenerate_general_translation_sfe147276_1(dest, dd, _t6, _t7, _t8, _t15, _t16, _t17, _t18, sd[0] * _t8, sd[3] * _t8, _t24, _t25, _t5 * _t8, Math.fma(_t17, _t16, -(_t15 * _t18)), Math.fma(_t15, _t24, -(_t16 * _t25)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general_translation}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_translation_sfe147276_1(Double3x3 dest, double[] dd, double _t6, double _t7, double _t8, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t33, double _t34) {
        double _t39_inv = 1.0 / Math.fma(_t33, _t26, Math.fma(_t34, _t19, -(Math.fma(_t17, _t24, -(_t18 * _t25)) * _t20)));
        double _sp2 = _t7 * _t39_inv;
        double _sp1 = _t6 * _t39_inv;
        double _sp0 = _t8 * _t39_inv;
        dd[0] = _t34 * _sp0;
        dd[1] = Math.fma(_t18, _t25, -(_t17 * _t24)) * _sp0;
        dd[2] = _t33 * _sp0;
        dd[3] = Math.fma(_t16, _t26, -(_t20 * _t24)) * _sp1;
        dd[4] = Math.fma(_t19, _t24, -(_t18 * _t26)) * _sp1;
        dd[5] = Math.fma(_t20, _t18, -(_t19 * _t16)) * _sp1;
        dd[6] = Math.fma(_t20, _t25, -(_t15 * _t26)) * _sp2;
        dd[7] = Math.fma(_t17, _t26, -(_t19 * _t25)) * _sp2;
        dd[8] = Math.fma(_t19, _t15, -(_t20 * _t17)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t9 = Math.fma(otherData[0], sd[2], otherData[1] * sd[5]);
        double _t10 = Math.fma(otherData[3], sd[2], otherData[4] * sd[5]);
        double _t11 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        double _t12 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        double _t13 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        double _t14 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        double _t15 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        double _t16 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        double _t17 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        return invertProduct_degenerate_general_affine_se7a3a764_1(dest, dd, _t9, _t10, _t11, _t12, _t13, _t14, _t15, _t16, _t17, unitScale(_t9, _t10, _t15), unitScale(_t12, _t11, _t16), unitScale(_t13, _t14, _t17));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general_affine}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_affine_se7a3a764_1(Double3x3 dest, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        double _t27 = _t11 * _t19;
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
        double _sp2 = _t18 * _t51_inv;
        double _sp1 = _t19 * _t51_inv;
        double _sp0 = _t20 * _t51_inv;
        dd[0] = _t46 * _sp0;
        dd[1] = Math.fma(_t37, _t30, -(_t36 * _t29)) * _sp0;
        dd[2] = _t45 * _sp0;
        dd[3] = Math.fma(_t38, _t28, -(_t36 * _t32)) * _sp1;
        dd[4] = Math.fma(_t36, _t31, -(_t38 * _t30)) * _sp1;
        dd[5] = Math.fma(_t30, _t32, -(_t31 * _t28)) * _sp1;
        dd[6] = Math.fma(_t37, _t32, -(_t38 * _t27)) * _sp2;
        dd[7] = Math.fma(_t38, _t29, -(_t37 * _t31)) * _sp2;
        dd[8] = Math.fma(_t31, _t27, -(_t29 * _t32)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(Double3x3R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        int q = ((Double3x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_identity_affine(other, dest);
            return invertProduct_degenerate_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_translation_affine(other, dest);
            return invertProduct_degenerate_translation(other, dest);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, dest, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            return invertProduct_degenerate_orthogonal(other, dest, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
        }
        return invertProduct_degenerate_sa6a06c51_1(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_sa6a06c51_1(Double3x3R other, Double3x3 dest, int q) {
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_general_affine(other, dest);
        return invertProduct_degenerate_general(other, dest);
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        return invertProduct_degenerate_sc46c720e_1(dest, dd, Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4])), Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_sc46c720e_1(Double3x3 dest, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        double _t27 = unitScale(_t19, _t18, _t20);
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
        double _sp1 = _t27 * _t60_inv;
        double _sp0 = _t29 * _t60_inv;
        dd[0] = _t54 * _sp0;
        dd[1] = Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0;
        dd[2] = _t55 * _sp0;
        dd[3] = Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1;
        dd[4] = Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1;
        dd[5] = Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1;
        return invertProduct_degenerate_sc46c720e_2(dest, dd, _t39, _t42, _t43, _t45, _t46, _t47, _t28 * _t60_inv);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_sc46c720e_2(Double3x3 dest, double[] dd, double _t39, double _t42, double _t43, double _t45, double _t46, double _t47, double _sp2) {
        dd[6] = Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2;
        dd[7] = Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2;
        dd[8] = Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }









    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double3x3 normal_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = sd[4] * _t3_inv;
        double _buf1 = -(sd[3] * _t3_inv);
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[3] = -(sd[1] * _t3_inv);
        dd[4] = sd[0] * _t3_inv;
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Double3x3 normal_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = sd[4] * _t3_inv;
        double _buf1 = -(sd[3] * _t3_inv);
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[3] = -(sd[1] * _t3_inv);
        dd[4] = sd[0] * _t3_inv;
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double3x3 normal_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        double _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        double _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t13_inv = 1.0 / _t13;
        return normal_general_sfef92d7a_1(dest, sd, dd, _t7, _t13_inv, _t6 * _t13_inv, Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv, Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv, Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv, Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv, Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv);
    }

    /** Piece 2 of {@code normal_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 normal_general_sfef92d7a_1(Double3x3 dest, double[] sd, double[] dd, double _t7, double _t13_inv, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4, double _buf5) {
        dd[6] = _t7 * _t13_inv;
        dd[7] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 normal(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine(dest);
        return normal_general(dest);
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 normal() {
        if (Joml.RETURN_NEW) return normal(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            ((Double3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine_self(this);
        return normal_general(this);
    }




    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(1.0, 0.0, sd[6]);
        double _t1 = unitScale(0.0, 1.0, sd[7]);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[6] * _t0 * _t2_inv);
        dd[3] = 0.0;
        dd[4] = _t1 * _t3_inv;
        dd[5] = -(sd[7] * _t1 * _t3_inv);
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[0], sd[3], sd[6]);
        double _t8 = sd[4] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[3] * _t1;
        double _t11 = sd[1] * _t0;
        double _t12 = sd[7] * _t0;
        double _t13 = sd[6] * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t10 * _sp1);
        dd[2] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[3] = -(_t11 * _sp0);
        dd[4] = _t9 * _sp1;
        dd[5] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[4], sd[7]);
        double _t1 = unitScale(sd[2], sd[5], sd[8]);
        double _t2 = unitScale(sd[0], sd[3], sd[6]);
        double _t12 = sd[4] * _t0;
        double _t13 = sd[8] * _t1;
        double _t14 = sd[7] * _t0;
        double _t15 = sd[5] * _t1;
        double _t16 = sd[1] * _t0;
        double _t17 = sd[2] * _t1;
        double _t18 = sd[6] * _t2;
        double _t19 = sd[0] * _t2;
        double _t20 = sd[3] * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp1 = _t0 * _t33_inv;
        double _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        return normal_degenerate_general_s4ae0b303_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code normal_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 normal_degenerate_general_s4ae0b303_1(Double3x3 dest, double[] dd, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t28, double _sp2, double _sp1, double _sp0) {
        dd[2] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[3] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[6] = _t28 * _sp0;
        dd[7] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_orthogonal(dest);
        return normal_degenerate_general(dest);
    }



    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public double trace() {
        double[] sd = this.data;
        return sd[8] + (sd[0] + sd[4]);
    }




    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = sd[6];
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[2] = sd[6];
        dd[5] = sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[3];
        dd[2] = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        dd[5] = sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[3];
        dd[2] = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        dd[5] = sd[7];
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[3];
        double _buf1 = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        double _buf2 = sd[7];
        dd[6] = sd[2];
        dd[7] = sd[5];
        dd[8] = sd[8];
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[5] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 transpose(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transpose_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return transpose_orthogonal(dest);
        return transpose_general(dest);
    }


    /**
     * Transpose this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 transpose() {
        if (Joml.RETURN_NEW) return transpose(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            ((Double3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transpose_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return transpose_orthogonal_self(this);
        return transpose_general(this);
    }


    /**
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 add(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = otherData[8] + sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 add(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m01 + sd[3];
        dd[4] = m11 + sd[4];
        dd[5] = m21 + sd[5];
        dd[6] = m02 + sd[6];
        dd[7] = m12 + sd[7];
        dd[8] = m22 + sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = scalar;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity_self(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar;
        dd[4] = scalar;
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = scalar;
        dd[5] = 0.0;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_self(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar;
        dd[4] = scalar;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = 0.0;
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = 0.0;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_self(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(double scalar, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar * sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 mul(double scalar, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(scalar, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(scalar, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal(scalar, dest);
        return mul_general(scalar, dest);
    }


    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 mul(double scalar) {
        if (Joml.RETURN_NEW) return mul(scalar, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(scalar, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal_self(scalar, this);
        return mul_general(scalar, this);
    }


    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 negate(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 sub(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        dd[4] = sd[4] - otherData[4];
        dd[5] = sd[5] - otherData[5];
        dd[6] = sd[6] - otherData[6];
        dd[7] = sd[7] - otherData[7];
        dd[8] = sd[8] - otherData[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) from this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 sub(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m01;
        dd[4] = sd[4] - m11;
        dd[5] = sd[5] - m21;
        dd[6] = sd[6] - m02;
        dd[7] = sd[7] - m12;
        dd[8] = sd[8] - m22;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated public Double3x3 set(Double3x3R v) {
        double[] dd = this.data;
        double[] vData = ((Double3x3Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        dd[4] = vData[4];
        dd[5] = vData[5];
        dd[6] = vData[6];
        dd[7] = vData[7];
        dd[8] = vData[8];
        ((Double3x3Impl) this).properties = ((Double3x3Impl) v).properties;
        return this;
    }


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
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return this
     */
    @Mutated public Double3x3 set(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m01;
        dd[4] = m11;
        dd[5] = m21;
        dd[6] = m02;
        dd[7] = m12;
        dd[8] = m22;
        ((Double3x3Impl) this).properties = determineProperties();
        return this;
    }


    /**
     * Set this matrix to the given 2x2 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Double3x3 set(Double2x2R m) {
        double[] dd = this.data;
        double[] mData = ((Double2x2Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = 0.0;
        dd[3] = mData[2];
        dd[4] = mData[3];
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = determineProperties();
        return this;
    }


    /**
     * Set this matrix to the given 2x3 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Double3x3 set(Double2x3R m) {
        double[] dd = this.data;
        double[] mData = ((Double2x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = 0.0;
        dd[3] = mData[2];
        dd[4] = mData[3];
        dd[5] = 0.0;
        dd[6] = mData[4];
        dd[7] = mData[5];
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = determineProperties();
        return this;
    }


    /**
     * Set this matrix to the given 3x4 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Double3x3 set(Double3x4R m) {
        double[] dd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[4];
        dd[2] = mData[8];
        dd[3] = mData[1];
        dd[4] = mData[5];
        dd[5] = mData[9];
        dd[6] = mData[2];
        dd[7] = mData[6];
        dd[8] = mData[10];
        ((Double3x3Impl) this).properties = determineProperties();
        return this;
    }


    /**
     * Set this matrix to the given 4x4 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Double3x3 set(Double4x4R m) {
        double[] dd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[2];
        dd[3] = mData[4];
        dd[4] = mData[5];
        dd[5] = mData[6];
        dd[6] = mData[8];
        dd[7] = mData[9];
        dd[8] = mData[10];
        ((Double3x3Impl) this).properties = determineProperties();
        return this;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
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
    public Double3x3 withTranslation(Double2R t, @Mutated Double3x3 dest) {
        return withTranslation(t.x(), t.y(), dest);
    }


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
    public @Mutated Double3x3 withTranslation(Double2R t) {
        return withTranslation(t.x(), t.y());
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double3x3 withTranslation_orthogonal_affine(double tX, double tY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code withTranslation}, specialized by runtime matrix
     * properties; reached only through the public {@code withTranslation} dispatcher.
     */
    private Double3x3 withTranslation_orthogonal_affine_self(double tX, double tY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = tX;
        dd[7] = tY;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code withTranslation}, {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Double3x3 withTranslation_identity(double tX, double tY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code withTranslation}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code withTranslation},
     * {@code preTranslate} and {@code translate}; reached only through them.
     */
    private Double3x3 withTranslation_identity_self(double tX, double tY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[6] = tX;
        dd[7] = tY;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double3x3 withTranslation_general(double tX, double tY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 withTranslation(double tX, double tY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return withTranslation_identity(tX, tY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return withTranslation_orthogonal_affine(tX, tY, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY, dest);
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 withTranslation(double tX, double tY) {
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return withTranslation_identity_self(tX, tY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return withTranslation_orthogonal_affine_self(tX, tY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY, this);
    }


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
    public Float3x3 toFloat(@Mutated Float3x3 dest) {
        double[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        dd[8] = (float) (sd[8]);
        ((Float3x3Impl) dest).properties = this.properties;
        return dest;
    }


    /**
     * Set this matrix to the given rigid transform's rotation block (the translation is dropped).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    public @Mutated Double3x3 makeFromRigid(DoubleRigidR r) {
        return makeFromRigid(r.tX(), r.tY(), r.tZ(), r.rX(), r.rY(), r.rZ(), r.rW());
    }


    /**
     * Set this matrix to the given rigid transform's rotation block (the translation is dropped).
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
     * @return this
     */
    @Mutated public Double3x3 makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double[] dd = this.data;
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dd[0] = Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0);
        dd[1] = 2.0 * Math.fma(rRX, rRY, _t1);
        dd[2] = 2.0 * Math.fma(rRX, rRZ, -_t2);
        dd[3] = 2.0 * Math.fma(rRX, rRY, -_t1);
        dd[4] = Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0);
        dd[5] = 2.0 * Math.fma(rRX, rRW, rRY * rRZ);
        dd[6] = 2.0 * Math.fma(rRX, rRZ, _t2);
        dd[7] = 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW));
        dd[8] = Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0);
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to the given transform's linear block {@code R * S} (the translation is
     * dropped).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return this
     */
    public @Mutated Double3x3 makeFromTransform(DoubleTransformR t) {
        return makeFromTransform(t.tX(), t.tY(), t.tZ(), t.rX(), t.rY(), t.rZ(), t.rW(), t.sX(), t.sY(), t.sZ());
    }


    /**
     * Set this matrix to the given transform's linear block {@code R * S} (the translation is
     * dropped).
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
     * @return this
     */
    @Mutated public Double3x3 makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double[] dd = this.data;
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dd[0] = Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX);
        dd[1] = Math.fma(tRX, tRY, _t4) * _t0;
        dd[2] = Math.fma(tRX, tRZ, -_t5) * _t0;
        dd[3] = Math.fma(tRX, tRY, -_t4) * _t1;
        dd[4] = Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY);
        dd[5] = Math.fma(tRX, tRW, tRY * tRZ) * _t1;
        dd[6] = Math.fma(tRX, tRZ, _t5) * _t2;
        dd[7] = Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2;
        dd[8] = Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ);
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_identity(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_general(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column and
     * the last row) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 to2x2(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x2_identity(dest);
        return to2x2_general(dest);
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_orthogonal_general(@Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        dd[4] = sd[6];
        dd[5] = sd[7];
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_identity(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_translation(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = sd[6];
        dd[5] = sd[7];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Truncate this matrix to a 2x3 matrix, dropping the last row (assumed {@code 0, 0, 1}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 to2x3(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to2x3_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x3_translation(dest);
        return to2x3_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_identity(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 1.0;
        dd[11] = 0.0;
        ((Double3x4Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_translation(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = sd[6];
        dd[3] = 0.0;
        dd[4] = 0.0;
        double _buf0 = 1.0;
        dd[6] = sd[7];
        dd[7] = 0.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 1.0;
        dd[11] = 0.0;
        dd[5] = _buf0;
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_orthogonal(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[3];
        dd[2] = sd[6];
        double _buf0 = 0.0;
        double _buf1 = sd[1];
        double _buf2 = sd[4];
        dd[6] = sd[7];
        dd[7] = 0.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 1.0;
        dd[11] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_general(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[3];
        dd[2] = sd[6];
        double _buf0 = 0.0;
        double _buf1 = sd[1];
        double _buf2 = sd[4];
        double _buf3 = sd[7];
        double _buf4 = 0.0;
        double _buf5 = sd[2];
        dd[9] = sd[5];
        dd[10] = sd[8];
        dd[11] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        dd[6] = _buf3;
        dd[7] = _buf4;
        dd[8] = _buf5;
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Extend this matrix to a 3x4 matrix with a zero translation column and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 to3x4(@Mutated Double3x4 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x4_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to3x4_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to3x4_orthogonal(dest);
        return to3x4_general(dest);
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_identity(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 1.0;
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_translation(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        double _buf0 = 1.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = 1.0;
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        dd[5] = _buf0;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_orthogonal(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[1];
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = sd[3];
        double _buf1 = sd[4];
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = 1.0;
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        dd[1] = _buf0;
        dd[5] = _buf1;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_general(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[1];
        double _buf1 = sd[2];
        dd[3] = 0.0;
        dd[4] = sd[3];
        double _buf2 = sd[4];
        dd[6] = sd[5];
        dd[7] = 0.0;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = sd[8];
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[5] = _buf2;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Extend this matrix to a 4x4 matrix, filling the missing cells with identity and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 to4x4(@Mutated Double4x4 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to4x4_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to4x4_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to4x4_orthogonal(dest);
        return to4x4_general(dest);
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_identity(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_translation(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = -(0.25 * sd[7]);
        dd[1] = 0.25 * sd[6];
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_orthogonal(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t3 = sd[0] - sd[4];
        double _t5 = sd[4] - sd[0];
        double _t9 = 1.0 + (sd[0] + sd[4]);
        double _t11 = 1.0 + _t9;
        double _t12 = 1.0 + (1.0 - sd[0] - sd[4]);
        return toDualQuat_orthogonal_sfca2a9f9_1(dest, sd, dd, 0.5 * sd[6], 0.5 * sd[7], _t3, 0.5 * (sd[3] + sd[1]), _t5, 0.5 * (sd[1] - sd[3]), (1.0 / Math.sqrt(_t5)), (1.0 / Math.sqrt(_t3)), _t9, _t11, _t12, (1.0 / Math.sqrt(_t11)), (1.0 / Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code toDualQuat_orthogonal}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat toDualQuat_orthogonal_sfca2a9f9_1(DoubleDualQuat dest, double[] sd, double[] dd, double _sp1, double _sp0, double _t3, double _sp2, double _t5, double _sp3, double _t7, double _t8, double _t9, double _t11, double _t12, double _t13, double _t14) {
        if (_t9 > 0.0) {
            double _buf0 = -(_sp0 * _t13);
            dd[1] = _sp1 * _t13;
            dd[2] = _sp3 * _t13;
            dd[3] = 0.5 * Math.sqrt(_t11);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], 1.0)) {
                double _buf0 = 0.5 * Math.sqrt(_t3);
                dd[1] = _sp2 * _t8;
                dd[2] = _sp1 * _t8;
                dd[3] = -(_sp0 * _t8);
                dd[0] = _buf0;
            } else {
                if (sd[4] > 1.0) {
                    double _buf0 = _sp2 * _t7;
                    dd[1] = 0.5 * Math.sqrt(_t5);
                    dd[2] = _sp0 * _t7;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    double _buf0 = _sp1 * _t14;
                    dd[1] = _sp0 * _t14;
                    dd[2] = 0.5 * Math.sqrt(_t12);
                    dd[3] = _sp3 * _t14;
                    dd[0] = _buf0;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_general(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t1 = 1.0 - sd[0];
        double _t13 = sd[8] + (sd[0] + sd[4]);
        double _t14 = 1.0 + _t13;
        double _t15 = sd[0] + (1.0 - sd[4] - sd[8]);
        double _t16 = sd[4] + (_t1 - sd[8]);
        double _t17 = sd[8] + (_t1 - sd[4]);
        return toDualQuat_general_sc2a2c12c_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / Math.sqrt(_t14)), 0.5 * (1.0 / Math.sqrt(_t16)), 0.5 * (1.0 / Math.sqrt(_t17)), 0.5 * (1.0 / Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_general}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat toDualQuat_general_sc2a2c12c_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            double _buf0 = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                double _buf0 = 0.5 * Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    double _buf0 = _sp1 * _t5;
                    dd[1] = 0.5 * Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    double _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5 * Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                    dd[0] = _buf0;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return dest;
    }


    /**
     * Convert this matrix (assumed orthonormal) to a pure-rotation dual quaternion and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toDualQuat_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toDualQuat_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return toDualQuat_orthogonal(dest);
        return toDualQuat_general(dest);
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private DoubleRigid toRigid_identity(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        return dest;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private DoubleRigid toRigid_translation(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _ct0 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        double _t3 = (1.0 / Math.sqrt(_ct0));
        double _t8 = _t3 < 0.0 ? -1.0 : 1.0;
        double _t11 = _t8 - _t3;
        double _t13 = 1.0 + _t8 + _t3;
        double _t15 = 2.0 - _t8 - _t3;
        double _t17 = 1.0 + _t13;
        double _t18 = 1.0 + _t3 - _t8 - 1.0;
        return toRigid_translation_s6f5dee1_1(dest, dd, _t3, 0.5 * sd[7] * _t3, 0.5 * sd[6] * _t3, _t8, _t11, (1.0 / Math.sqrt(_t11)), _t13, _t15, (1.0 / Math.sqrt(_t15)), _t17, _t18, (1.0 / Math.sqrt(_t17)), (1.0 / Math.sqrt(_t18)));
    }

    /**
     * Piece 2 of {@code toRigid_translation}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code toRigid}; reached only through it.
     */
    private DoubleRigid toRigid_translation_s6f5dee1_1(DoubleRigid dest, double[] dd, double _t3, double _sp0, double _sp1, double _t8, double _t11, double _t12, double _t13, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        if (_t13 > 0.0) {
            dd[3] = -(_sp0 * _t19);
            dd[4] = _sp1 * _t19;
            dd[5] = 0.0;
            dd[6] = 0.5 * Math.sqrt(_t17);
        } else {
            if (_t8 > Math.max(1.0, _t3)) {
                dd[3] = 0.5 * Math.sqrt(_t11);
                dd[4] = 0.0;
                dd[5] = _sp1 * _t12;
                dd[6] = -(_sp0 * _t12);
            } else {
                if (1.0 > _t3) {
                    dd[3] = 0.0;
                    dd[4] = 0.5 * Math.sqrt(_t15);
                    dd[5] = _sp0 * _t16;
                    dd[6] = _sp1 * _t16;
                } else {
                    dd[3] = _sp1 * _t20;
                    dd[4] = _sp0 * _t20;
                    dd[5] = 0.5 * Math.sqrt(_t18);
                    dd[6] = 0.0;
                }
            }
        }
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private DoubleRigid toRigid_general(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        return toRigid_general_s296b94ba_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_general_s296b94ba_1(DoubleRigid dest, double[] sd, double[] dd, double _t0, double _t1, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        double _t15 = (1.0 / Math.sqrt(_ct0));
        double _ct1 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        double _t16 = (1.0 / Math.sqrt(_ct1));
        double _ct2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        double _t17 = (1.0 / Math.sqrt(_ct2));
        double _t20 = sd[7] * _t16;
        double _t23 = sd[5] * _t15;
        return toRigid_general_s296b94ba_2(dest, sd, dd, _t0, _t1, _t15, _t16, sd[1] * _t17, sd[8] * _t16, _t20, sd[2] * _t17, _t23, sd[4] * _t15, sd[0] * _t17, Math.fma(sd[7], _t16, _t23), Math.fma(sd[5], _t15, -_t20));
    }

    /** Piece 3 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_general_s296b94ba_2(DoubleRigid dest, double[] sd, double[] dd, double _t0, double _t1, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t26, double _t31, double _t35) {
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), sd[3] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), sd[6] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
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
        double _t63 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t51));
        double _t65 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t52));
        return toRigid_general_s296b94ba_3(dest, sd, dd, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(sd[3], _t15, _t48), Math.fma(sd[6], _t16, _t49), Math.fma(sd[6], _t16, -_t49), Math.fma(-sd[3], _t15, _t48), _t63, 0.5 * (1.0 / Math.sqrt(_t63)), _t65, _t66, Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5 * (1.0 / Math.sqrt(_t65)), 0.5 * (1.0 / Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_general_s296b94ba_3(DoubleRigid dest, double[] sd, double[] dd, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t67));
        if (Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t47)) > 0.0) {
            dd[3] = _sp0 * _t35;
            double _buf0 = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5 * Math.sqrt(_t63);
            dd[4] = _buf0;
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5 * Math.sqrt(_t67);
                double _buf0 = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
                dd[4] = _buf0;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    double _buf0 = 0.5 * Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                    dd[4] = _buf0;
                } else {
                    dd[3] = _sp2 * _t55;
                    double _buf0 = _sp2 * _t31;
                    dd[5] = 0.5 * Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                    dd[4] = _buf0;
                }
            }
        }
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Extract this matrix's rotation into a rigid transform with zero translation (scale is removed
     * by normalizing the columns, but shear is not removed: a sheared block yields a rotation
     * quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_translation(dest);
        return toRigid_general(dest);
    }



    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate_translation(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = unitScale(sd[6], sd[7], 1.0);
        double _t1 = _t0;
        double _t4 = sd[6] * _t0;
        double _t5 = sd[7] * _t0;
        double _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        double _t9 = (1.0 / Math.sqrt(_t8));
        double _t13 = _t8 <= 0.0 ? 1.0 : _t9 * _t1;
        double _sp0, _sp1;
        if (_t8 <= 0.0) {
            _sp0 = 0.5 * 0.0;
            _sp1 = 0.5 * 0.0;
        } else {
            _sp0 = 0.5 * _t9 * _t5;
            _sp1 = 0.5 * _t9 * _t4;
        }
        double _t18 = _t13 < 0.0 ? -1.0 : 1.0;
        double _t21 = _t18 - _t13;
        double _t23 = 1.0 + _t18 + _t13;
        double _t25 = 2.0 - _t18 - _t13;
        double _t27 = 1.0 + _t23;
        double _t28 = 1.0 + _t13 - _t18 - 1.0;
        return toRigid_translation_s6f5dee1_1(dest, dd, _t13, _sp0, _sp1, _t18, _t21, (1.0 / Math.sqrt(_t21)), _t23, _t25, (1.0 / Math.sqrt(_t25)), _t27, _t28, (1.0 / Math.sqrt(_t27)), (1.0 / Math.sqrt(_t28)));
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate_general(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = unitScale(sd[3], sd[4], sd[5]);
        double _t1 = unitScale(sd[6], sd[7], sd[8]);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t12 = sd[5] * _t0;
        double _t13 = sd[3] * _t0;
        double _t14 = sd[4] * _t0;
        double _t15 = sd[8] * _t1;
        double _t16 = sd[6] * _t1;
        double _t17 = sd[7] * _t1;
        double _t18 = sd[2] * _t2;
        double _t19 = sd[0] * _t2;
        double _t20 = sd[1] * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
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
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t208));
        if (_t206 > 0.0) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5 * Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5 * Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5 * Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5 * Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate(@Mutated DoubleRigid dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_degenerate_translation(dest);
        return toRigid_degenerate_general(dest);
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private DoubleTransform toTransform_identity(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return dest;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private DoubleTransform toTransform_translation(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t2 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0));
        if (!(_t2 > 2.2250738585072014E-308 && _t2 < Double.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        double _t3 = (1.0 / Math.sqrt(_t2));
        double _t8 = _t3 < 0.0 ? -1.0 : 1.0;
        double _t11 = _t8 - _t3;
        double _t13 = 1.0 + _t8 + _t3;
        double _t15 = 2.0 - _t8 - _t3;
        double _t17 = 1.0 + _t13;
        double _t18 = 1.0 + _t3 - _t8 - 1.0;
        return toTransform_translation_sd95c1997_1(dest, dd, _t2, _t3, 0.5 * sd[7] * _t3, 0.5 * sd[6] * _t3, _t8, _t11, (1.0 / Math.sqrt(_t11)), _t13, _t15, (1.0 / Math.sqrt(_t15)), _t17, _t18, (1.0 / Math.sqrt(_t17)), (1.0 / Math.sqrt(_t18)));
    }

    /** Piece 2 of {@code toTransform_translation}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_translation_sd95c1997_1(DoubleTransform dest, double[] dd, double _t2, double _t3, double _sp0, double _sp1, double _t8, double _t11, double _t12, double _t13, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        if (_t13 > 0.0) {
            dd[3] = -(_sp0 * _t19);
            dd[4] = _sp1 * _t19;
            dd[5] = 0.0;
            dd[6] = 0.5 * Math.sqrt(_t17);
        } else {
            if (_t8 > Math.max(1.0, _t3)) {
                dd[3] = 0.5 * Math.sqrt(_t11);
                dd[4] = 0.0;
                dd[5] = _sp1 * _t12;
                dd[6] = -(_sp0 * _t12);
            } else {
                if (1.0 > _t3) {
                    dd[3] = 0.0;
                    dd[4] = 0.5 * Math.sqrt(_t15);
                    dd[5] = _sp0 * _t16;
                    dd[6] = _sp1 * _t16;
                } else {
                    dd[3] = _sp1 * _t20;
                    dd[4] = _sp0 * _t20;
                    dd[5] = 0.5 * Math.sqrt(_t18);
                    dd[6] = 0.0;
                }
            }
        }
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[7] = _t8;
        dd[8] = 1.0;
        dd[9] = Math.sqrt(_t2);
        return dest;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private DoubleTransform toTransform_general(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        return toTransform_general_s84fef4c0_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s84fef4c0_1(DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t1, double _t12) {
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        double _t13 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        double _t14 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        double _t15 = (1.0 / Math.sqrt(_t12));
        double _t16 = (1.0 / Math.sqrt(_t13));
        double _t18 = Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _t20 = sd[8] * _t16;
        double _t21 = sd[7] * _t16;
        double _t24 = sd[5] * _t15;
        double _t25 = sd[4] * _t15;
        return toTransform_general_s84fef4c0_2(dest, sd, dd, _t0, _t1, _t12, _t13, _t15, _t16, _t18, sd[1] * _t17, _t20, _t21, sd[2] * _t17, _t24, _t25, sd[0] * _t17, Math.fma(sd[7], _t16, _t24), Math.fma(sd[5], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s84fef4c0_2(DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21, double _t22, double _t24, double _t25, double _t27, double _t32, double _t36, double _t37) {
        double _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), sd[3] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), sd[6] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t64 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t52));
        double _t66 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t53));
        return toTransform_general_s84fef4c0_3(dest, dd, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(sd[3], _t15, _t49), Math.fma(sd[6], _t16, _t50), Math.fma(sd[6], _t16, -_t50), Math.fma(-sd[3], _t15, _t49), Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t48)), _t64, 0.5 * (1.0 / Math.sqrt(_t64)), _t66, Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5 * (1.0 / Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s84fef4c0_3(DoubleTransform dest, double[] dd, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1) {
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t68));
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * Math.sqrt(_t67);
        return toTransform_general_s84fef4c0_4(dest, dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /** Piece 5 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s84fef4c0_4(DoubleTransform dest, double[] dd, double _t12, double _t13, double _t18, double _t20, double _t25, double _t36, double _t37, double _t47, double _t48, double _t57, double _t58, double _t63, double _t64, double _sp1, double _sp2, double _sp3) {
        dd[6] = _t63 > 0.0 ? 0.5 * Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
        dd[7] = _t47 < 0.0 ? -_t18 : _t18;
        dd[8] = Math.sqrt(_t12);
        dd[9] = Math.sqrt(_t13);
        return dest;
    }


    /**
     * Decompose this matrix's linear {@code R * S} block into a TRS transform with zero translation
     * (scale is removed by normalizing the columns, but shear is not removed: a sheared block
     * yields a rotation quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_translation(dest);
        return toTransform_general(dest);
    }



    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate_translation(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = unitScale(sd[6], sd[7], 1.0);
        double _t1 = _t0;
        double _t4 = sd[6] * _t0;
        double _t5 = sd[7] * _t0;
        double _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        double _t9 = (1.0 / Math.sqrt(_t8));
        double _t13 = _t8 <= 0.0 ? 1.0 : _t9 * _t1;
        double _sp0, _sp1;
        if (_t8 <= 0.0) {
            _sp0 = 0.5 * 0.0;
            _sp1 = 0.5 * 0.0;
        } else {
            _sp0 = 0.5 * _t9 * _t5;
            _sp1 = 0.5 * _t9 * _t4;
        }
        double _t18 = _t13 < 0.0 ? -1.0 : 1.0;
        double _t21 = _t18 - _t13;
        double _t23 = 1.0 + _t18 + _t13;
        double _t25 = 2.0 - _t18 - _t13;
        double _t27 = 1.0 + _t23;
        double _t28 = 1.0 + _t13 - _t18 - 1.0;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        return toTransform_degenerate_translation_s38e0c1e2_1(dest, dd, _t0, _t8, _t13, Math.max(1.0, _t13), _sp0, _sp1, _t18, _t21, (1.0 / Math.sqrt(_t21)), _t23, _t25, (1.0 / Math.sqrt(_t25)), _t27, _t28, (1.0 / Math.sqrt(_t27)), (1.0 / Math.sqrt(_t28)));
    }

    /** Piece 2 of {@code toTransform_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_degenerate_translation_s38e0c1e2_1(DoubleTransform dest, double[] dd, double _t0, double _t8, double _t13, double _t14, double _sp0, double _sp1, double _t18, double _t21, double _t22, double _t23, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30) {
        dd[3] = _t23 > 0.0 ? -(_sp0 * _t29) : _t18 > _t14 ? 0.5 * Math.sqrt(_t21) : 1.0 > _t13 ? 0.0 : _sp1 * _t30;
        dd[4] = _t23 > 0.0 ? _sp1 * _t29 : _t18 > _t14 ? 0.0 : 1.0 > _t13 ? 0.5 * Math.sqrt(_t25) : _sp0 * _t30;
        dd[5] = _t23 > 0.0 ? 0.0 : _t18 > _t14 ? _sp1 * _t22 : 1.0 > _t13 ? _sp0 * _t26 : 0.5 * Math.sqrt(_t28);
        dd[6] = _t23 > 0.0 ? 0.5 * Math.sqrt(_t27) : _t18 > _t14 ? -(_sp0 * _t22) : 1.0 > _t13 ? _sp1 * _t26 : 0.0;
        dd[7] = _t18;
        dd[8] = 1.0;
        dd[9] = _t8 <= 0.0 ? 0.0 : Math.sqrt(_t8) / _t0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate_general(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = unitScale(sd[3], sd[4], sd[5]);
        double _t1 = unitScale(sd[6], sd[7], sd[8]);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t12 = sd[5] * _t0;
        double _t13 = sd[3] * _t0;
        double _t14 = sd[4] * _t0;
        double _t15 = sd[8] * _t1;
        double _t16 = sd[6] * _t1;
        double _t17 = sd[7] * _t1;
        double _t18 = sd[2] * _t2;
        double _t19 = sd[0] * _t2;
        double _t20 = sd[1] * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
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
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t211));
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : Math.sqrt(_t28) / _t1;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate(@Mutated DoubleTransform dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_degenerate_translation(dest);
        return toTransform_degenerate_general(dest);
    }



    /**
     * Private body of {@code decomposeRotation}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeRotation} dispatcher.
     */
    private DoubleQuat decomposeRotation_general(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t3 = (1.0 / Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t19 = -Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        double _t21 = Math.fma(_t19, _t7, sd[5]);
        double _t22 = Math.fma(_t19, _t8, sd[3]);
        double _t23 = Math.fma(_t19, _t9, sd[4]);
        double _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        double _t30 = (1.0 / Math.sqrt(_t29));
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
        return decomposeRotation_general_s59ef819d_1(dest, sd, dd, _t7, _t8, _t9, -Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9)), _t34, _t35, _t36);
    }

    /** Piece 2 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_general_s59ef819d_1(DoubleQuat dest, double[] sd, double[] dd, double _t7, double _t8, double _t9, double _t20, double _t34, double _t35, double _t36) {
        double _t40 = -Math.fma(Math.fma(_t20, _t7, sd[8]), _t35, Math.fma(Math.fma(_t20, _t8, sd[6]), _t34, Math.fma(_t20, _t9, sd[7]) * _t36));
        double _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, sd[8]));
        double _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, sd[6]));
        double _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, sd[7]));
        double _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        double _t50 = (1.0 / Math.sqrt(_t49));
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
        return decomposeRotation_general_s59ef819d_2(dest, dd, _t34, _t36, _t55, _t56, _t35 - _t54, _t35 + _t54, _t73, _t74, _t75);
    }

    /** Piece 3 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_general_s59ef819d_2(DoubleQuat dest, double[] dd, double _t34, double _t36, double _t55, double _t56, double _t60, double _t63, double _t73, double _t74, double _t75) {
        double _t76 = _t73 + _t36;
        double _t82 = _t76 + _t56;
        double _t86 = 1.0 + _t82;
        double _t87 = 1.0 + (_t73 - (_t36 + _t56));
        double _t88 = 1.0 + (_t36 - (_t73 + _t56));
        double _t89 = 1.0 + (_t56 - _t76);
        return decomposeRotation_general_s59ef819d_3(dest, dd, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5 * (1.0 / Math.sqrt(_t86)), 0.5 * (1.0 / Math.sqrt(_t88)), 0.5 * (1.0 / Math.sqrt(_t89)), 0.5 * (1.0 / Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_general_s59ef819d_3(DoubleQuat dest, double[] dd, double _t36, double _t56, double _t60, double _t63, double _t73, double _t77, double _t78, double _t80, double _t81, double _t82, double _t86, double _t87, double _t88, double _t89, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t82 > 0.0) {
            dd[0] = _sp0 * _t60;
            dd[1] = _sp0 * _t81;
            dd[2] = _sp0 * _t78;
            dd[3] = 0.5 * Math.sqrt(_t86);
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dd[0] = 0.5 * Math.sqrt(_t87);
                dd[1] = _sp3 * _t77;
                dd[2] = _sp3 * _t80;
                dd[3] = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    dd[0] = _sp1 * _t77;
                    dd[1] = 0.5 * Math.sqrt(_t88);
                    dd[2] = _sp1 * _t63;
                    dd[3] = _sp1 * _t81;
                } else {
                    dd[0] = _sp2 * _t80;
                    dd[1] = _sp2 * _t63;
                    dd[2] = 0.5 * Math.sqrt(_t89);
                    dd[3] = _sp2 * _t78;
                }
            }
        }
        return dest;
    }


    /**
     * Extract the rotation part of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat decomposeRotation(@Mutated DoubleQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_identity(dest);
        return decomposeRotation_general(dest);
    }



    /**
     * Private body of {@code decomposeScale}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeScale} dispatcher.
     */
    private Double3 decomposeScale_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t4 = Math.sqrt(_t2);
        double _t3 = 1.0 / _t4;
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = sd[2] * _t3;
            _t9 = sd[0] * _t3;
            _t10 = sd[1] * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        double _t17 = -Math.fma(sd[5], _t8, Math.fma(sd[3], _t9, sd[4] * _t10));
        double _t19 = Math.fma(_t17, _t8, sd[5]);
        double _t20 = Math.fma(_t17, _t9, sd[3]);
        double _t21 = Math.fma(_t17, _t10, sd[4]);
        double _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        return decomposeScale_general_s3601e88f_1(dest, sd, dd, _t4, _t8, _t9, _t10, -Math.fma(sd[8], _t8, Math.fma(sd[6], _t9, sd[7] * _t10)), _t19, _t20, _t21, _t27, (1.0 / Math.sqrt(_t27)));
    }

    /** Piece 2 of {@code decomposeScale_general}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeScale_general_s3601e88f_1(Double3 dest, double[] sd, double[] dd, double _t4, double _t8, double _t9, double _t10, double _t18, double _t19, double _t20, double _t21, double _t27, double _t28) {
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
        double _t38 = -Math.fma(Math.fma(_t18, _t8, sd[8]), _t33, Math.fma(Math.fma(_t18, _t9, sd[6]), _t32, Math.fma(_t18, _t10, sd[7]) * _t34));
        double _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, sd[8]));
        double _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, sd[6]));
        double _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, sd[7]));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        double _t48 = (1.0 / Math.sqrt(_t47));
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
        return decomposeScale_general_s3601e88f_2(dest, dd, _t4, _t8, _t9, _t10, _t27, _t32, _t33, _t34, _t47, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_general}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeScale_general_s3601e88f_2(Double3 dest, double[] dd, double _t4, double _t8, double _t9, double _t10, double _t27, double _t32, double _t33, double _t34, double _t47, double _t52, double _t53, double _t54) {
        dd[0] = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4;
        dd[1] = Math.sqrt(_t27);
        dd[2] = Math.sqrt(_t47);
        return dest;
    }


    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 decomposeScale(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getScale_identity(dest);
        return decomposeScale_general(dest);
    }




    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Double3 decomposeSkew_general(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t3 = (1.0 / Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t14 = Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9));
        double _t15 = Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        double _t17 = -_t15;
        double _t19 = Math.fma(_t17, _t7, sd[5]);
        double _t20 = Math.fma(_t17, _t8, sd[3]);
        double _t21 = Math.fma(_t17, _t9, sd[4]);
        double _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t27 = (1.0 / Math.sqrt(_t26));
        return decomposeSkew_general_sb2f6f427_1(dest, sd, dd, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew_general}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeSkew_general_sb2f6f427_1(Double3 dest, double[] sd, double[] dd, double _t7, double _t8, double _t9, double _t14, double _t16, double _t19, double _t20, double _t21, double _t26, double _t27, double _t28) {
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
        double _t37 = Math.fma(Math.fma(_t16, _t7, sd[8]), _t32, Math.fma(Math.fma(_t16, _t8, sd[6]), _t33, Math.fma(_t16, _t9, sd[7]) * _t34));
        double _t38 = -_t37;
        double _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, sd[8]));
        double _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, sd[6]));
        double _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, sd[7]));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        double _t48 = (1.0 / Math.sqrt(_t47));
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
        return decomposeSkew_general_sb2f6f427_2(dest, dd, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_general}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeSkew_general_sb2f6f427_2(Double3 dest, double[] dd, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            dd[1] = -_t49;
            dd[2] = -_t28;
        } else {
            dd[1] = _t49;
            dd[2] = _t28;
        }
        dd[0] = _t37 * _t48;
        return dest;
    }


    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 decomposeSkew(@Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = sd[7];
            dd[1] = sd[6];
            dd[2] = 0.0;
            return dest;
        }
        return decomposeSkew_general(dest);
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double3x3 makeIdentity() {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_IDENTITY;
        return this;
    }


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
    public Double3x3 lerp(Double3x3R other, double t, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherData[6] - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherData[7] - sd[7], sd[7]);
        dd[8] = Math.fma(t, otherData[8] - sd[8], sd[8]);
        ((Double3x3Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double3x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double3x3Impl) other).properties) >> 1);
        return dest;
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) using the
     * interpolation factor {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 lerp(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double t, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m01 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m11 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m21 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m02 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m12 - sd[7], sd[7]);
        dd[8] = Math.fma(t, m22 - sd[8], sd[8]);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        return mul_general_s6cc6085e_1(dest, sd, rightData, dd, Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[0], rightData[1] * sd[3])), Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4])), Math.fma(rightData[2], sd[8], Math.fma(rightData[0], sd[2], rightData[1] * sd[5])), Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[0], rightData[4] * sd[3])), Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4])), Math.fma(rightData[5], sd[8], Math.fma(rightData[3], sd[2], rightData[4] * sd[5])), Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[0], rightData[7] * sd[3])), Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4])));
    }

    /** Piece 2 of {@code mul_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 mul_general_s6cc6085e_1(Double3x3 dest, double[] sd, double[] rightData, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4, double _buf5, double _buf6, double _buf7) {
        dd[8] = Math.fma(rightData[8], sd[8], Math.fma(rightData[6], sd[2], rightData[7] * sd[5]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[2], sd[6], rightData[0]);
        dd[1] = Math.fma(rightData[2], sd[7], rightData[1]);
        dd[2] = rightData[2];
        dd[3] = Math.fma(rightData[5], sd[6], rightData[3]);
        dd[4] = Math.fma(rightData[5], sd[7], rightData[4]);
        dd[5] = rightData[5];
        dd[6] = Math.fma(rightData[8], sd[6], rightData[6]);
        dd[7] = Math.fma(rightData[8], sd[7], rightData[7]);
        dd[8] = rightData[8];
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1])));
        double _buf1 = Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4]));
        dd[2] = rightData[2];
        double _buf2 = Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[4], -(rightData[4] * sd[1])));
        double _buf3 = Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4]));
        dd[5] = rightData[5];
        double _buf4 = Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[4], -(rightData[7] * sd[1])));
        dd[7] = Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4]));
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[0], rightData[1] * sd[3]));
        double _buf1 = Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4]));
        dd[2] = rightData[2];
        double _buf2 = Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[0], rightData[4] * sd[3]));
        double _buf3 = Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4]));
        dd[5] = rightData[5];
        double _buf4 = Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[0], rightData[7] * sd[3]));
        dd[7] = Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4]));
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = rightData[6] + sd[6];
        dd[7] = rightData[7] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_affine(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0;
        dd[3] = rightData[3];
        dd[4] = rightData[4];
        dd[5] = 0.0;
        dd[6] = rightData[6] + sd[6];
        dd[7] = rightData[7] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation(Double3x3R right, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        dd[2] = 0.0;
        double _buf2 = sd[3];
        double _buf3 = sd[4];
        dd[5] = 0.0;
        double _buf4 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_affine(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        double _buf2 = Math.fma(rightData[3], sd[4], -(rightData[4] * sd[1]));
        double _buf3 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        dd[5] = 0.0;
        double _buf4 = Math.fma(-rightData[7], sd[1], Math.fma(rightData[6], sd[4], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine_affine(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        double _buf2 = Math.fma(rightData[3], sd[0], rightData[4] * sd[3]);
        double _buf3 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        dd[5] = 0.0;
        double _buf4 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_translation(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        double _buf2 = sd[2];
        double _buf3 = sd[3];
        double _buf4 = sd[4];
        double _buf5 = sd[5];
        double _buf6 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        double _buf7 = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[6], sd[2], Math.fma(rightData[7], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_affine(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        double _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        double _buf3 = Math.fma(rightData[3], sd[0], rightData[4] * sd[3]);
        double _buf4 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        double _buf5 = Math.fma(rightData[3], sd[2], rightData[4] * sd[5]);
        double _buf6 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        double _buf7 = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[6], sd[2], Math.fma(rightData[7], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 mul(Double3x3R right, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Double3x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, dest);
            return mul_translation(right, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal_affine(right, dest);
            return mul_orthogonal(right, dest);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right, dest);
            return mul_affine(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right, dest);
        return mul_general(right, dest);
    }


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
    @Mutated public Double3x3 mul(Double3x3R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        int q = ((Double3x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, this);
            return mul_translation(right, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal_affine(right, this);
            return mul_orthogonal(right, this);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right, this);
            return mul_affine(right, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right, this);
        return mul_general(right, this);
    }


    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}, {@code m20}, {@code m21}, {@code m22}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 mul(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3]));
        double _buf1 = Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4]));
        double _buf2 = Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5]));
        double _buf3 = Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3]));
        double _buf4 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        double _buf5 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
        dd[6] = Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3]));
        dd[7] = Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4]));
        dd[8] = Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity_self(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_self(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        double _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_self(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        double _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine_self(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double2x2R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        double _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = Math.fma(rightData[2], sd[2], rightData[3] * sd[5]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 mul(Double2x2R right, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(right, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(right, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal(right, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine(right, dest);
        return mul_general(right, dest);
    }


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
    @Mutated public Double3x3 mul(Double2x2R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(right, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal_self(right, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_self(right, this);
        return mul_general(right, this);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        double _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        double _buf3 = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        double _buf4 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        double _buf5 = Math.fma(rightData[2], sd[2], rightData[3] * sd[5]);
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[4], sd[2], Math.fma(rightData[5], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0;
        dd[6] = rightData[4];
        dd[7] = rightData[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0;
        dd[6] = rightData[4] + sd[6];
        dd[7] = rightData[5] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        double _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        double _buf1 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0;
        dd[6] = Math.fma(-rightData[5], sd[1], Math.fma(rightData[4], sd[4], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        double _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0;
        double _buf2 = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        double _buf3 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0;
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity_translation(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = rightData[4];
        dd[7] = rightData[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = rightData[4] + sd[6];
        dd[7] = rightData[5] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation(Double2x3R right, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        dd[2] = 0.0;
        double _buf2 = sd[3];
        double _buf3 = sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_translation(Double2x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        double _buf2 = sd[2];
        double _buf3 = sd[3];
        double _buf4 = sd[4];
        double _buf5 = sd[5];
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[4], sd[2], Math.fma(rightData[5], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 mul(Double2x3R right, @Mutated Double3x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_identity_translation(right, dest);
            return mul_identity(right, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation(right, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, dest);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_AFFINE & q);
            return mul_affine(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, dest);
        return mul_general(right, dest);
    }


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
    @Mutated public Double3x3 mul(Double2x3R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double3x3());
        int p = this.properties;
        int q = ((Double2x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_identity_translation(right, this);
            return mul_identity(right, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation(right, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, this);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_AFFINE & q);
            return mul_affine(right, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, this);
        return mul_general(right, this);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        return preMul_general_s9f5f4a67_1(dest, sd, otherData, dd, Math.fma(otherData[6], sd[2], Math.fma(otherData[0], sd[0], otherData[3] * sd[1])), Math.fma(otherData[7], sd[2], Math.fma(otherData[1], sd[0], otherData[4] * sd[1])), Math.fma(otherData[8], sd[2], Math.fma(otherData[2], sd[0], otherData[5] * sd[1])), Math.fma(otherData[6], sd[5], Math.fma(otherData[0], sd[3], otherData[3] * sd[4])), Math.fma(otherData[7], sd[5], Math.fma(otherData[1], sd[3], otherData[4] * sd[4])), Math.fma(otherData[8], sd[5], Math.fma(otherData[2], sd[3], otherData[5] * sd[4])), Math.fma(otherData[6], sd[8], Math.fma(otherData[0], sd[6], otherData[3] * sd[7])), Math.fma(otherData[7], sd[8], Math.fma(otherData[1], sd[6], otherData[4] * sd[7])));
    }

    /** Piece 2 of {@code preMul_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 preMul_general_s9f5f4a67_1(Double3x3 dest, double[] sd, double[] otherData, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4, double _buf5, double _buf6, double _buf7) {
        dd[8] = Math.fma(otherData[8], sd[8], Math.fma(otherData[2], sd[6], otherData[5] * sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = otherData[0];
        double _buf1 = otherData[1];
        double _buf2 = otherData[2];
        double _buf3 = otherData[3];
        double _buf4 = otherData[4];
        double _buf5 = otherData[5];
        double _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        double _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[4], otherData[3] * sd[1]);
        double _buf1 = Math.fma(otherData[1], sd[4], otherData[4] * sd[1]);
        double _buf2 = Math.fma(otherData[2], sd[4], otherData[5] * sd[1]);
        double _buf3 = Math.fma(otherData[3], sd[4], -(otherData[0] * sd[1]));
        double _buf4 = Math.fma(otherData[4], sd[4], -(otherData[1] * sd[1]));
        double _buf5 = Math.fma(otherData[5], sd[4], -(otherData[2] * sd[1]));
        double _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        double _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        double _buf1 = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        double _buf2 = Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        double _buf3 = Math.fma(otherData[0], sd[3], otherData[3] * sd[4]);
        double _buf4 = Math.fma(otherData[1], sd[3], otherData[4] * sd[4]);
        double _buf5 = Math.fma(otherData[2], sd[3], otherData[5] * sd[4]);
        double _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        double _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = otherData[0];
        double _buf1 = otherData[1];
        dd[2] = 0.0;
        double _buf2 = otherData[3];
        double _buf3 = otherData[4];
        dd[5] = 0.0;
        double _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[4], otherData[3] * sd[1]);
        double _buf1 = Math.fma(otherData[1], sd[4], otherData[4] * sd[1]);
        dd[2] = 0.0;
        double _buf2 = Math.fma(otherData[3], sd[4], -(otherData[0] * sd[1]));
        double _buf3 = Math.fma(otherData[4], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0;
        double _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        double _buf1 = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        dd[2] = 0.0;
        double _buf2 = Math.fma(otherData[0], sd[3], otherData[3] * sd[4]);
        double _buf3 = Math.fma(otherData[1], sd[3], otherData[4] * sd[4]);
        dd[5] = 0.0;
        double _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[6], sd[2], sd[0]);
        dd[1] = Math.fma(otherData[7], sd[2], sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(otherData[6], sd[5], sd[3]);
        dd[4] = Math.fma(otherData[7], sd[5], sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(otherData[6], sd[8], sd[6]);
        dd[7] = Math.fma(otherData[7], sd[8], sd[7]);
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_affine(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[6], sd[2], Math.fma(otherData[0], sd[0], otherData[3] * sd[1]));
        double _buf1 = Math.fma(otherData[7], sd[2], Math.fma(otherData[1], sd[0], otherData[4] * sd[1]));
        dd[2] = sd[2];
        double _buf2 = Math.fma(otherData[6], sd[5], Math.fma(otherData[0], sd[3], otherData[3] * sd[4]));
        double _buf3 = Math.fma(otherData[7], sd[5], Math.fma(otherData[1], sd[3], otherData[4] * sd[4]));
        dd[5] = sd[5];
        double _buf4 = Math.fma(otherData[6], sd[8], Math.fma(otherData[0], sd[6], otherData[3] * sd[7]));
        dd[7] = Math.fma(otherData[7], sd[8], Math.fma(otherData[1], sd[6], otherData[4] * sd[7]));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 preMul(Double3x3R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Double3x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, dest);
            return preMul_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_orthogonal_affine(other, dest);
            return preMul_orthogonal(other, dest);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other, dest);
            return preMul_affine(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other, dest);
        return preMul_general(other, dest);
    }


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
    @Mutated public Double3x3 preMul(Double3x3R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        int q = ((Double3x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, this);
            return preMul_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_orthogonal_affine(other, this);
            return preMul_orthogonal(other, this);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other, this);
            return preMul_affine(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other, this);
        return preMul_general(other, this);
    }


    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     * {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) onto this matrix and store
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preMul(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(m02, sd[2], Math.fma(m00, sd[0], m01 * sd[1]));
        double _buf1 = Math.fma(m12, sd[2], Math.fma(m10, sd[0], m11 * sd[1]));
        dd[2] = Math.fma(m22, sd[2], Math.fma(m20, sd[0], m21 * sd[1]));
        double _buf2 = Math.fma(m02, sd[5], Math.fma(m00, sd[3], m01 * sd[4]));
        double _buf3 = Math.fma(m12, sd[5], Math.fma(m10, sd[3], m11 * sd[4]));
        dd[5] = Math.fma(m22, sd[5], Math.fma(m20, sd[3], m21 * sd[4]));
        double _buf4 = Math.fma(m02, sd[8], Math.fma(m00, sd[6], m01 * sd[7]));
        double _buf5 = Math.fma(m12, sd[8], Math.fma(m10, sd[6], m11 * sd[7]));
        dd[8] = Math.fma(m22, sd[8], Math.fma(m20, sd[6], m21 * sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity_self(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0;
        double _buf0 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0;
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_self(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        double _buf0 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        double _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[2] = 0.0;
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0;
        double _buf1 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_self(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        double _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        double _buf1 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Double3x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = 0.0;
        double _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = 0.0;
        double _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine_self(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        double _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        double _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general(Double2x2R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = sd[2];
        double _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = sd[5];
        double _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 preMul(Double2x2R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity(other, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation(other, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal(other, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine(other, dest);
        return preMul_general(other, dest);
    }


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
    @Mutated public Double3x3 preMul(Double2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity_self(other, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_self(other, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal_self(other, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_self(other, this);
        return preMul_general(other, this);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[4], sd[2], Math.fma(otherData[0], sd[0], otherData[2] * sd[1]));
        dd[1] = Math.fma(otherData[5], sd[2], Math.fma(otherData[1], sd[0], otherData[3] * sd[1]));
        dd[2] = sd[2];
        double _buf1 = Math.fma(otherData[4], sd[5], Math.fma(otherData[0], sd[3], otherData[2] * sd[4]));
        dd[4] = Math.fma(otherData[5], sd[5], Math.fma(otherData[1], sd[3], otherData[3] * sd[4]));
        dd[5] = sd[5];
        double _buf2 = Math.fma(otherData[4], sd[8], Math.fma(otherData[0], sd[6], otherData[2] * sd[7]));
        dd[7] = Math.fma(otherData[5], sd[8], Math.fma(otherData[1], sd[6], otherData[3] * sd[7]));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0;
        dd[6] = otherData[4];
        dd[7] = otherData[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0;
        double _buf0 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0;
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        double _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[2] = 0.0;
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0;
        double _buf1 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0;
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = 0.0;
        double _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = 0.0;
        double _buf2 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity_translation(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = otherData[4];
        dd[7] = otherData[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = otherData[4] + sd[6];
        dd[7] = otherData[5] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double2x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = otherData[4] + sd[6];
        dd[7] = otherData[5] + sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_translation(Double2x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[4], sd[2], sd[0]);
        dd[1] = Math.fma(otherData[5], sd[2], sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(otherData[4], sd[5], sd[3]);
        dd[4] = Math.fma(otherData[5], sd[5], sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(otherData[4], sd[8], sd[6]);
        dd[7] = Math.fma(otherData[5], sd[8], sd[7]);
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 preMul(Double2x3R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_identity_translation(other, dest);
            return preMul_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, dest);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
            return preMul_affine(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, dest);
        return preMul_general(other, dest);
    }


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
    @Mutated public Double3x3 preMul(Double2x3R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double3x3());
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_identity_translation(other, this);
            return preMul_identity(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, this);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
            return preMul_affine(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, this);
        return preMul_general(other, this);
    }


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
    public Double3x3 addScaled(Double3x3R other, double weight, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(weight, otherData[0], sd[0]);
        dd[1] = Math.fma(weight, otherData[1], sd[1]);
        dd[2] = Math.fma(weight, otherData[2], sd[2]);
        dd[3] = Math.fma(weight, otherData[3], sd[3]);
        dd[4] = Math.fma(weight, otherData[4], sd[4]);
        dd[5] = Math.fma(weight, otherData[5], sd[5]);
        dd[6] = Math.fma(weight, otherData[6], sd[6]);
        dd[7] = Math.fma(weight, otherData[7], sd[7]);
        dd[8] = Math.fma(weight, otherData[8], sd[8]);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) scaled by {@code weight} to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 addScaled(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double weight, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m20, sd[2]);
        dd[3] = Math.fma(weight, m01, sd[3]);
        dd[4] = Math.fma(weight, m11, sd[4]);
        dd[5] = Math.fma(weight, m21, sd[5]);
        dd[6] = Math.fma(weight, m02, sd[6]);
        dd[7] = Math.fma(weight, m12, sd[7]);
        dd[8] = Math.fma(weight, m22, sd[8]);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Set this matrix to the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column vector (left operand)
     * @param row the row vector (right operand)
     * @return this
     */
    public @Mutated Double3x3 makeOuterProduct(Double3R col, Double3R row) {
        return makeOuterProduct(col.x(), col.y(), col.z(), row.x(), row.y(), row.z());
    }


    /**
     * Set this matrix to the outer product of ({@code colX}, {@code colY}, {@code colZ}) and
     * ({@code rowX}, {@code rowY}, {@code rowZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param colX the {@code x} component of the vector {@code (colX, colY, colZ)}
     * @param colY the {@code y} component of the vector {@code (colX, colY, colZ)}
     * @param colZ the {@code z} component of the vector {@code (colX, colY, colZ)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @return this
     */
    @Mutated public Double3x3 makeOuterProduct(double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        double[] dd = this.data;
        dd[0] = colX * rowX;
        dd[1] = colY * rowX;
        dd[2] = colZ * rowX;
        dd[3] = colX * rowY;
        dd[4] = colY * rowY;
        dd[5] = colZ * rowY;
        dd[6] = colX * rowZ;
        dd[7] = colY * rowZ;
        dd[8] = colZ * rowZ;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    public Double3x3 lookAlong(Double3R dir, Double3R up, @Mutated Double3x3 dest) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


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
    public @Mutated Double3x3 lookAlong(Double3R dir, Double3R up) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_identity(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t14 = (1.0 / Math.sqrt(_t7));
        double _t22 = Math.fma(-dirY, _sp0, upY);
        double _t23 = Math.fma(-dirZ, _sp0, upZ);
        double _t24 = Math.fma(-dirX, _sp0, upX);
        double _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        double _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        double _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        double _ct0 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t38 = (1.0 / Math.sqrt(_ct0));
        return lookAlong_identity_seb28963b_1(dest, dd, dirY * _t14, dirZ * _t14, dirX * _t14, _t31 * _t38, _t32 * _t38, _t33 * _t38);
    }

    /** Piece 2 of {@code lookAlong_identity}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_identity_seb28963b_1(Double3x3 dest, double[] dd, double _t15, double _t16, double _t17, double _t39, double _t40, double _t41) {
        dd[0] = _t39;
        dd[1] = _t41;
        dd[2] = _t40;
        dd[3] = Math.fma(_t15, _t40, -(_t16 * _t41));
        dd[4] = Math.fma(_t16, _t39, -(_t17 * _t40));
        dd[5] = Math.fma(_t17, _t41, -(_t15 * _t39));
        dd[6] = _t17;
        dd[7] = _t15;
        dd[8] = _t16;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_translation(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t14 = (1.0 / Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t38 = (1.0 / Math.sqrt(_ct0));
        return lookAlong_translation_se8453004_1(dirX, dirY, dest, sd, dd, _t14, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_translation}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_translation_se8453004_1(double dirX, double dirY, Double3x3 dest, double[] sd, double[] dd, double _t14, double _t15, double _t16, double _t17, double _t39, double _t40, double _t41) {
        double _t44 = Math.fma(_t15, _t41, -(_t16 * _t40));
        dd[0] = Math.fma(sd[6], _t39, _t40);
        dd[1] = Math.fma(sd[7], _t39, _t41);
        dd[2] = _t39;
        dd[3] = Math.fma(sd[6], _t44, Math.fma(_t16, _t39, -(_t17 * _t41)));
        dd[4] = Math.fma(sd[7], _t44, Math.fma(_t17, _t40, -(_t15 * _t39)));
        dd[5] = _t44;
        dd[6] = Math.fma(dirX, _t14, sd[6] * _t17);
        dd[7] = Math.fma(dirY, _t14, sd[7] * _t17);
        dd[8] = _t17;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_orthogonal(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t14 = (1.0 / Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t38 = (1.0 / Math.sqrt(_ct0));
        return lookAlong_orthogonal_s7a22d6c0_1(dest, sd, dd, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_orthogonal_s7a22d6c0_1(Double3x3 dest, double[] sd, double[] dd, double _t15, double _t16, double _t17, double _t39, double _t40, double _t41) {
        double _t48 = Math.fma(_t15, _t41, -(_t16 * _t40));
        double _t49 = Math.fma(_t16, _t39, -(_t17 * _t41));
        double _t50 = Math.fma(_t17, _t40, -(_t15 * _t39));
        double _buf0 = Math.fma(sd[6], _t39, Math.fma(sd[0], _t40, sd[3] * _t41));
        double _buf1 = Math.fma(sd[7], _t39, Math.fma(sd[1], _t40, sd[4] * _t41));
        dd[2] = _t39;
        double _buf2 = Math.fma(sd[6], _t48, Math.fma(sd[0], _t49, sd[3] * _t50));
        double _buf3 = Math.fma(sd[7], _t48, Math.fma(sd[1], _t49, sd[4] * _t50));
        dd[5] = _t48;
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t15, sd[3] * _t16));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t15, sd[4] * _t16));
        dd[8] = _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_general(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t14 = (1.0 / Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t38 = (1.0 / Math.sqrt(_ct0));
        return lookAlong_general_s8ab4872d_1(dest, sd, dd, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_general_s8ab4872d_1(Double3x3 dest, double[] sd, double[] dd, double _t15, double _t16, double _t17, double _t39, double _t40, double _t41) {
        double _t48 = Math.fma(_t15, _t41, -(_t16 * _t40));
        double _t49 = Math.fma(_t16, _t39, -(_t17 * _t41));
        double _t50 = Math.fma(_t17, _t40, -(_t15 * _t39));
        double _buf0 = Math.fma(sd[6], _t39, Math.fma(sd[0], _t40, sd[3] * _t41));
        double _buf1 = Math.fma(sd[7], _t39, Math.fma(sd[1], _t40, sd[4] * _t41));
        double _buf2 = Math.fma(sd[8], _t39, Math.fma(sd[2], _t40, sd[5] * _t41));
        double _buf3 = Math.fma(sd[6], _t48, Math.fma(sd[0], _t49, sd[3] * _t50));
        double _buf4 = Math.fma(sd[7], _t48, Math.fma(sd[1], _t49, sd[4] * _t50));
        double _buf5 = Math.fma(sd[8], _t48, Math.fma(sd[2], _t49, sd[5] * _t50));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t15, sd[3] * _t16));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t15, sd[4] * _t16));
        return lookAlong_general_s8ab4872d_2(dest, sd, dd, _t15, _t16, _t17, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5);
    }

    /** Piece 3 of {@code lookAlong_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_general_s8ab4872d_2(Double3x3 dest, double[] sd, double[] dd, double _t15, double _t16, double _t17, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4, double _buf5) {
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t15, sd[5] * _t16));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ, dest);
    }


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
    @Mutated public Double3x3 lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.RETURN_NEW) return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ, this);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ, this);
    }




    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Double3x3 lookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / Math.sqrt(_t16));
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
        double _t34, _t35, _t39;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t34 = 0.0;
            _t35 = -_t26;
            _t39 = _t25;
        } else {
            _t34 = _t26;
            _t35 = 0.0;
            _t39 = -_t24;
        }
        double _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_degenerate_s38650531_1(dest, sd, dd, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t35, _t39, Math.fma(_t41, _t25, _t22), Math.fma(_t41, _t26, _t23), Math.fma(_t41, _t24, _t21));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_degenerate_s38650531_1(Double3x3 dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t34, double _t35, double _t39, double _t42, double _t43, double _t44) {
        double _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        double _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        double _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        double _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        double _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t64 = (1.0 / Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0 / Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        return lookAlong_degenerate_s38650531_2(dest, sd, dd, _t24, _t25, _t26, Math.fma(_t66, _t24, -(_t65 * _t25)), Math.fma(_t65, _t26, -(_t67 * _t24)), Math.fma(_t67, _t25, -(_t66 * _t26)), Math.fma(sd[6], _t65, Math.fma(sd[0], _t66, sd[3] * _t67)), Math.fma(sd[7], _t65, Math.fma(sd[1], _t66, sd[4] * _t67)), Math.fma(sd[8], _t65, Math.fma(sd[2], _t66, sd[5] * _t67)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_degenerate_s38650531_2(Double3x3 dest, double[] sd, double[] dd, double _t24, double _t25, double _t26, double _t74, double _t75, double _t76, double _buf0, double _buf1, double _buf2) {
        double _buf3 = Math.fma(sd[6], _t76, Math.fma(sd[0], _t75, sd[3] * _t74));
        double _buf4 = Math.fma(sd[7], _t76, Math.fma(sd[1], _t75, sd[4] * _t74));
        double _buf5 = Math.fma(sd[8], _t76, Math.fma(sd[2], _t75, sd[5] * _t74));
        dd[6] = Math.fma(sd[6], _t24, Math.fma(sd[0], _t25, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t24, Math.fma(sd[1], _t25, sd[4] * _t26));
        dd[8] = Math.fma(sd[8], _t24, Math.fma(sd[2], _t25, sd[5] * _t26));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Set this matrix to the rotation part of the unit dual quaternion {@code dq} (the encoded
     * translation is dropped).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    public @Mutated Double3x3 makeFromDualQuat(DoubleDualQuatR dq) {
        return makeFromDualQuat(dq.rX(), dq.rY(), dq.rZ(), dq.rW(), dq.dX(), dq.dY(), dq.dZ(), dq.dW());
    }


    /**
     * Set this matrix to the rotation part of the unit dual quaternion ({@code dqRX}, {@code dqRY},
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
     * @return this
     */
    @Mutated public Double3x3 makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double[] dd = this.data;
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dd[0] = Math.fma(-2.0, _t0, _t6);
        dd[1] = 2.0 * Math.fma(dqRX, dqRY, _t2);
        dd[2] = Math.fma(-2.0, _t3, _sp0 * dqRZ);
        dd[3] = Math.fma(-2.0, _t2, _sp0 * dqRY);
        dd[4] = Math.fma(-2.0, _t4, _t6);
        dd[5] = 2.0 * Math.fma(dqRX, dqRW, _t5);
        dd[6] = 2.0 * Math.fma(dqRX, dqRZ, _t3);
        dd[7] = Math.fma(-2.0, dqRX * dqRW, _t5 + _t5);
        dd[8] = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Double3x3 makeRotation(double angle) {
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
        return this;
    }


    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated Double3x3 makeRotationAxis(double angle, Double3R axis) {
        return makeRotationAxis(angle, axis.x(), axis.y(), axis.z());
    }


    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return this
     */
    @Mutated public Double3x3 makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dd[0] = Math.fma(_t5, axisX * axisX, _t1);
        dd[1] = Math.fma(axisZ, _t0, _t5 * _t2);
        dd[2] = Math.fma(_t5, _t3, -(axisY * _t0));
        dd[3] = Math.fma(_t5, _t2, -(axisZ * _t0));
        dd[4] = Math.fma(_t5, axisY * axisY, _t1);
        dd[5] = Math.fma(axisX, _t0, _t5 * _t4);
        dd[6] = Math.fma(axisY, _t0, _t5 * _t3);
        dd[7] = Math.fma(_t5, _t4, -(axisX * _t0));
        dd[8] = Math.fma(_t5, axisZ * axisZ, _t1);
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    public @Mutated Double3x3 makeRotationLookAlong(Double3R dir, Double3R up) {
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


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
    @Mutated public Double3x3 makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / Math.sqrt(_t7));
        double _t22 = Math.fma(-dirY, _sp0, upY);
        double _t23 = Math.fma(-dirZ, _sp0, upZ);
        double _t24 = Math.fma(-dirX, _sp0, upX);
        double _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        double _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        double _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        double _ct0 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t38 = (1.0 / Math.sqrt(_ct0));
        double _t39 = _t31 * _t38;
        double _t41 = _t33 * _t38;
        dd[0] = _t39;
        dd[1] = _t41;
        return makeRotationLookAlong_s309f29f5_1(dd, dirY * _t14, dirZ * _t14, dirX * _t14, _t39, _t32 * _t38, _t41);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private Double3x3 makeRotationLookAlong_s309f29f5_1(double[] dd, double _t15, double _t16, double _t17, double _t39, double _t40, double _t41) {
        dd[2] = _t40;
        dd[3] = Math.fma(_t15, _t40, -(_t16 * _t41));
        dd[4] = Math.fma(_t16, _t39, -(_t17 * _t40));
        dd[5] = Math.fma(_t17, _t41, -(_t15 * _t39));
        dd[6] = _t17;
        dd[7] = _t15;
        dd[8] = _t16;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }



    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private Double3x3 makeRotationLookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / Math.sqrt(_t16));
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
        double _t34, _t35, _t39;
        if (Math.abs(_t25) > Math.abs(_t24)) {
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
        return makeRotationLookAlong_degenerate_se3802ea_1(dd, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t35, _t39, _t42, _t43, Math.fma(_t41, _t24, _t21), Math.fma(_t42, _t26, -(_t43 * _t25)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 makeRotationLookAlong_degenerate_se3802ea_1(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t34, double _t35, double _t39, double _t42, double _t43, double _t44, double _t53) {
        double _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        double _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        double _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        double _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t64 = (1.0 / Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0 / Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        dd[0] = _t66;
        dd[1] = _t67;
        dd[2] = _t65;
        dd[3] = Math.fma(_t65, _t26, -(_t67 * _t24));
        dd[4] = Math.fma(_t66, _t24, -(_t65 * _t25));
        dd[5] = Math.fma(_t67, _t25, -(_t66 * _t26));
        dd[6] = _t25;
        dd[7] = _t26;
        dd[8] = _t24;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation quaternion
     * @return this
     */
    public @Mutated Double3x3 makeRotationQuat(DoubleQuatR q) {
        return makeRotationQuat(q.x(), q.y(), q.z(), q.w());
    }


    /**
     * Set this matrix to the rotation represented by the quaternion ({@code qX}, {@code qY},
     * {@code qZ}, {@code qW}).
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return this
     */
    @Mutated public Double3x3 makeRotationQuat(double qX, double qY, double qZ, double qW) {
        double[] dd = this.data;
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dd[0] = Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0);
        dd[1] = 2.0 * Math.fma(qX, qY, _t1);
        dd[2] = 2.0 * Math.fma(qX, qZ, -_t2);
        dd[3] = 2.0 * Math.fma(qX, qY, -_t1);
        dd[4] = Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0);
        dd[5] = 2.0 * Math.fma(qX, qW, qY * qZ);
        dd[6] = 2.0 * Math.fma(qX, qZ, _t2);
        dd[7] = 2.0 * Math.fma(qY, qZ, -(qX * qW));
        dd[8] = Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0);
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Double3x3 makeRotationX(double angle) {
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1;
        dd[5] = _t0;
        dd[6] = 0.0;
        dd[7] = -_t0;
        dd[8] = _t1;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationXYZ(double angleX, double angleY, double angleZ) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t6, _t4, _t1 * _t5);
        dd[2] = Math.fma(_t2, _t1, -(_t7 * _t4));
        dd[3] = -(_t1 * _t3);
        dd[4] = Math.fma(_t5, _t4, -(_t6 * _t1));
        dd[5] = Math.fma(_t7, _t1, _t2 * _t4);
        dd[6] = _t0;
        dd[7] = -(_t2 * _t3);
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationXZY(double angleX, double angleZ, double angleY) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t7, _t3, _t2 * _t0);
        dd[2] = Math.fma(_t6, _t3, -(_t0 * _t5));
        dd[3] = -_t1;
        dd[4] = _t5 * _t4;
        dd[5] = _t2 * _t4;
        dd[6] = _t0 * _t4;
        dd[7] = Math.fma(_t7, _t0, -(_t2 * _t3));
        dd[8] = Math.fma(_t6, _t0, _t5 * _t3);
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Double3x3 makeRotationY(double angle) {
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = 0.0;
        dd[2] = -_t0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = _t0;
        dd[7] = 0.0;
        dd[8] = _t1;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationYXZ(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dd[0] = Math.fma(_t6, _t2, _t3 * _t4);
        dd[1] = _t2 * _t5;
        dd[2] = Math.fma(_t7, _t2, -(_t1 * _t4));
        dd[3] = Math.fma(_t6, _t4, -(_t2 * _t3));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t7, _t4, _t1 * _t2);
        dd[6] = _t1 * _t5;
        dd[7] = -_t0;
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationYZX(double angleY, double angleZ, double angleX) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        dd[0] = _t3 * _t4;
        dd[1] = _t1;
        dd[2] = -(_t0 * _t4);
        dd[3] = Math.fma(_t2, _t0, -(_t7 * _t5));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t6, _t5, _t2 * _t3);
        dd[6] = Math.fma(_t7, _t2, _t0 * _t5);
        dd[7] = -(_t2 * _t4);
        dd[8] = Math.fma(_t5, _t3, -(_t6 * _t2));
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Double3x3 makeRotationZ(double angle) {
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationZXY(double angleZ, double angleX, double angleY) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dd[0] = Math.fma(_t3, _t4, -(_t6 * _t0));
        dd[1] = Math.fma(_t7, _t0, _t1 * _t3);
        dd[2] = -(_t0 * _t5);
        dd[3] = -(_t1 * _t5);
        dd[4] = _t5 * _t4;
        dd[5] = _t2;
        dd[6] = Math.fma(_t6, _t3, _t0 * _t4);
        dd[7] = Math.fma(_t0, _t1, -(_t7 * _t3));
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


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
    @Mutated public Double3x3 makeRotationZYX(double angleZ, double angleY, double angleX) {
        double[] dd = this.data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        dd[0] = _t3 * _t4;
        dd[1] = _t1 * _t3;
        dd[2] = -_t0;
        dd[3] = Math.fma(_t7, _t2, -(_t1 * _t5));
        dd[4] = Math.fma(_t6, _t2, _t5 * _t4);
        dd[5] = _t2 * _t3;
        dd[6] = Math.fma(_t7, _t5, _t2 * _t1);
        dd[7] = Math.fma(_t6, _t5, -(_t2 * _t4));
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this
     */
    public @Mutated Double3x3 makeScaling(Double2R v) {
        return makeScaling(v.x(), v.y());
    }


    /**
     * Set this matrix to a scaling transformation that scales by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this
     */
    @Mutated public Double3x3 makeScaling(double vX, double vY) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = vY;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Set this matrix to a scaling transformation that scales by {@code s} of the x and y axes only
     * (the 2D homogeneous {@code diag(s, s, 1)}: the third row and column are left unscaled).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this
     */
    @Mutated public Double3x3 makeScaling(double s) {
        double[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this
     */
    public @Mutated Double3x3 makeTranslation(Double2R v) {
        return makeTranslation(v.x(), v.y());
    }


    /**
     * Set this matrix to a translation transformation that translates by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return this
     */
    @Mutated public Double3x3 makeTranslation(double vX, double vY) {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = vX;
        dd[7] = vY;
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_TRANSLATION;
        return this;
    }


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
    @Mutated public Double3x3 makeView(double left, double right, double bottom, double top) {
        double[] dd = this.data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) this).properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_orthogonal_affine(double angle, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = 0.0;
        double _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        dd[5] = 0.0;
        double _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_orthogonal_affine_self(double angle, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        double _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        double _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Double3x3 preRotate_identity(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Double3x3 preRotate_identity_self(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_translation(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0;
        double _buf0 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = 1.0;
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_translation_self(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        double _buf0 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_general(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = sd[2];
        double _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        dd[5] = sd[5];
        double _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix and store the result in
     * {@code dest}.
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
    public Double3x3 preRotate(double angle, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotate_orthogonal_affine(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotate_general(angle, dest);
    }


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
    @Mutated public Double3x3 preRotate(double angle) {
        if (Joml.RETURN_NEW) return preRotate(angle, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_self(angle, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotate_orthogonal_affine_self(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotate_general(angle, this);
    }


    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix
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
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateAround(double angle, Double2R pivot, @Mutated Double3x3 dest) {
        return preRotateAround(angle, pivot.x(), pivot.y(), dest);
    }


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
    public @Mutated Double3x3 preRotateAround(double angle, Double2R pivot) {
        return preRotateAround(angle, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_orthogonal_affine(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        dd[2] = 0.0;
        double _buf1 = Math.fma(sd[3], _t2, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t2);
        dd[5] = 0.0;
        double _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_orthogonal_affine_self(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        double _buf1 = Math.fma(sd[3], _t2, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t2);
        double _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Double3x3 preRotateAround_identity(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, _t5, pivotY * _t0);
        dd[7] = Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotateAround} and
     * {@code rotateAround}; reached only through them.
     */
    private Double3x3 preRotateAround_identity_self(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[6] = Math.fma(pivotX, _t5, pivotY * _t0);
        dd[7] = Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_translation(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0;
        double _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0;
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_translation_self(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        double _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[6] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_general(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(sd[2], _t9, Math.fma(sd[0], _t3, -(sd[1] * _t0)));
        dd[1] = Math.fma(sd[2], _t10, Math.fma(sd[0], _t0, sd[1] * _t3));
        dd[2] = sd[2];
        double _buf1 = Math.fma(sd[5], _t9, Math.fma(sd[3], _t3, -(sd[4] * _t0)));
        dd[4] = Math.fma(sd[5], _t10, Math.fma(sd[3], _t0, sd[4] * _t3));
        dd[5] = sd[5];
        double _buf2 = Math.fma(sd[8], _t9, Math.fma(sd[6], _t3, -(sd[7] * _t0)));
        dd[7] = Math.fma(sd[8], _t10, Math.fma(sd[6], _t0, sd[7] * _t3));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateAround(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAround_orthogonal_affine(angle, pivotX, pivotY, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotateAround_general(angle, pivotX, pivotY, dest);
    }


    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 preRotateAround(double angle, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAround_orthogonal_affine_self(angle, pivotX, pivotY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotateAround_general(angle, pivotX, pivotY, this);
    }


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
    public Double3x3 preRotateAxis(double angle, Double3R axis, @Mutated Double3x3 dest) {
        return preRotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


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
    public @Mutated Double3x3 preRotateAxis(double angle, Double3R axis) {
        return preRotateAxis(angle, axis.x(), axis.y(), axis.z());
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAxis} and {@code rotateAxis}; reached only
     * through them.
     */
    private Double3x3 preRotateAxis_identity(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dd[0] = Math.fma(_t5, axisX * axisX, _t1);
        dd[1] = Math.fma(axisZ, _t0, _t5 * _t2);
        dd[2] = Math.fma(_t5, _t3, -(axisY * _t0));
        dd[3] = Math.fma(_t5, _t2, -(axisZ * _t0));
        dd[4] = Math.fma(_t5, axisY * axisY, _t1);
        dd[5] = Math.fma(axisX, _t0, _t5 * _t4);
        dd[6] = Math.fma(axisY, _t0, _t5 * _t3);
        dd[7] = Math.fma(_t5, _t4, -(axisX * _t0));
        dd[8] = Math.fma(_t5, axisZ * axisZ, _t1);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_translation(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = _t14;
        dd[1] = _t16;
        dd[2] = _t19;
        dd[3] = _t18;
        dd[4] = _t15;
        dd[5] = _t17;
        double _buf0 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(sd[6], _t14, sd[7] * _t18);
        double _buf1 = Math.fma(sd[6], _t16, sd[7] * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        dd[8] = Math.fma(sd[6], _t19, Math.fma(sd[7], _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
        dd[6] = _buf0;
        dd[7] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_orthogonal(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        double _buf0 = Math.fma(sd[0], _t14, sd[1] * _t18);
        double _buf1 = Math.fma(sd[0], _t16, sd[1] * _t15);
        dd[2] = Math.fma(sd[0], _t19, sd[1] * _t17);
        double _buf2 = Math.fma(sd[3], _t14, sd[4] * _t18);
        double _buf3 = Math.fma(sd[3], _t16, sd[4] * _t15);
        dd[5] = Math.fma(sd[3], _t19, sd[4] * _t17);
        return preRotateAxis_orthogonal_sb9046c92_1(axisX, axisY, axisZ, dest, sd, dd, _t0, _t1, _t5, _t7, _t9, _t14, _t15, _t16, _t17, _t18, _t19, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code preRotateAxis_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 preRotateAxis_orthogonal_sb9046c92_1(double axisX, double axisY, double axisZ, Double3x3 dest, double[] sd, double[] dd, double _t0, double _t1, double _t5, double _t7, double _t9, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(sd[6], _t14, sd[7] * _t18);
        double _buf5 = Math.fma(sd[6], _t16, sd[7] * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        dd[8] = Math.fma(sd[6], _t19, Math.fma(sd[7], _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_general(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        double _buf0 = Math.fma(sd[2], _t21, Math.fma(sd[0], _t18, sd[1] * _t24));
        double _buf1 = Math.fma(sd[2], _t25, Math.fma(sd[0], _t22, sd[1] * _t19));
        dd[2] = Math.fma(sd[2], _t20, Math.fma(sd[0], _t26, sd[1] * _t23));
        return preRotateAxis_general_sa9e6d03f_1(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _buf0, _buf1);
    }

    /** Piece 2 of {@code preRotateAxis_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 preRotateAxis_general_sa9e6d03f_1(Double3x3 dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[5], _t21, Math.fma(sd[3], _t18, sd[4] * _t24));
        double _buf3 = Math.fma(sd[5], _t25, Math.fma(sd[3], _t22, sd[4] * _t19));
        dd[5] = Math.fma(sd[5], _t20, Math.fma(sd[3], _t26, sd[4] * _t23));
        double _buf4 = Math.fma(sd[8], _t21, Math.fma(sd[6], _t18, sd[7] * _t24));
        double _buf5 = Math.fma(sd[8], _t25, Math.fma(sd[6], _t22, sd[7] * _t19));
        dd[8] = Math.fma(sd[8], _t20, Math.fma(sd[6], _t26, sd[7] * _t23));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return preRotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return preRotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return preRotateZ(axisZ * angle, dest);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAxis_translation(angle, axisX, axisY, axisZ, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAxis_orthogonal(angle, axisX, axisY, axisZ, dest);
        return preRotateAxis_general(angle, axisX, axisY, axisZ, dest);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}) onto this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 preRotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (Joml.RETURN_NEW) return preRotateAxis(angle, axisX, axisY, axisZ, Joml.double3x3());
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return preRotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return preRotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return preRotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAxis_translation(angle, axisX, axisY, axisZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAxis_orthogonal(angle, axisX, axisY, axisZ, this);
        return preRotateAxis_general(angle, axisX, axisY, axisZ, this);
    }


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
    public Double3x3 preRotateX(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        double _buf0 = Math.fma(sd[1], _t1, -(sd[2] * _t0));
        dd[2] = Math.fma(sd[1], _t0, sd[2] * _t1);
        dd[3] = sd[3];
        double _buf1 = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        dd[6] = sd[6];
        double _buf2 = Math.fma(sd[7], _t1, -(sd[8] * _t0));
        dd[8] = Math.fma(sd[7], _t0, sd[8] * _t1);
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[7] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 preRotateY(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        dd[1] = sd[1];
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        double _buf1 = Math.fma(sd[3], _t1, sd[5] * _t0);
        dd[4] = sd[4];
        dd[5] = Math.fma(sd[5], _t1, -(sd[3] * _t0));
        double _buf2 = Math.fma(sd[6], _t1, sd[8] * _t0);
        dd[7] = sd[7];
        dd[8] = Math.fma(sd[8], _t1, -(sd[6] * _t0));
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 preRotateZ(double angle, @Mutated Double3x3 dest) {
        return preRotate(angle, dest);
    }


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
    @Mutated public Double3x3 preRotateZ(double angle) {
        return preRotate(angle);
    }


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
    public Double3x3 preScale(Double2R v, @Mutated Double3x3 dest) {
        return preScale(v.x(), v.y(), dest);
    }


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
    public @Mutated Double3x3 preScale(Double2R v) {
        return preScale(v.x(), v.y());
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_identity(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = vY;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_identity_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[4] = vY;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_translation(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = vY;
        dd[5] = 0.0;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_translation_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[4] = vY;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = 0.0;
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[5] = 0.0;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_general(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2];
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[5] = sd[5];
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScale(double vX, double vY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(vX, vY, dest);
        return preScale_general(vX, vY, dest);
    }


    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 preScale(double vX, double vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity_self(vX, vY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal_self(vX, vY, this);
        return preScale_general(vX, vY, this);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_identity(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_translation(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_translation_self(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s;
        dd[4] = s;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal_self(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_general(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = sd[5];
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) onto this matrix and store
     * the result in {@code dest}.
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
    public Double3x3 preScale(double s, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(s, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(s, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(s, dest);
        return preScale_general(s, dest);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) onto this matrix.
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
    @Mutated public Double3x3 preScale(double s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            dd[0] = s;
            dd[4] = s;
            ((Double3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(s, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal_self(s, this);
        return preScale_general(s, this);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} onto this matrix and store the result in {@code dest}.
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
    public Double3x3 preScaleAround(double s, Double2R pivot, @Mutated Double3x3 dest) {
        return preScaleAround(s, pivot.x(), pivot.y(), dest);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} onto this matrix.
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
    public @Mutated Double3x3 preScaleAround(double s, Double2R pivot) {
        return preScaleAround(s, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_identity(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = pivotX * _t0;
        dd[7] = pivotY * _t0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_identity_self(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = pivotX * _t0;
        dd[7] = pivotY * _t0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation_self(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal_self(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_general(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dd[0] = Math.fma(s, sd[0], sd[2] * _t1);
        dd[1] = Math.fma(s, sd[1], sd[2] * _t2);
        dd[2] = sd[2];
        dd[3] = Math.fma(s, sd[3], sd[5] * _t1);
        dd[4] = Math.fma(s, sd[4], sd[5] * _t2);
        dd[5] = sd[5];
        dd[6] = Math.fma(s, sd[6], sd[8] * _t1);
        dd[7] = Math.fma(s, sd[7], sd[8] * _t2);
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScaleAround(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(s, pivotX, pivotY, dest);
        return preScaleAround_general(s, pivotX, pivotY, dest);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) onto this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 preScaleAround(double s, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal_self(s, pivotX, pivotY, this);
        return preScaleAround_general(s, pivotX, pivotY, this);
    }


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
    public Double3x3 preScaleAround(Double2R s, Double2R pivot, @Mutated Double3x3 dest) {
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
    }


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
    public @Mutated Double3x3 preScaleAround(Double2R s, Double2R pivot) {
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_identity(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = sY;
        dd[5] = 0.0;
        dd[6] = pivotX * (1.0 - sX);
        dd[7] = pivotY * (1.0 - sY);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_identity_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = pivotX * (1.0 - sX);
        dd[7] = pivotY * (1.0 - sY);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = sY;
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, 1.0 - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sY * sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = Math.fma(pivotX, 1.0 - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sY * sd[7]);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = 0.0;
        dd[3] = sX * sd[3];
        dd[4] = sY * sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, 1.0 - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sY * sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[3] = sX * sd[3];
        dd[4] = sY * sd[4];
        dd[6] = Math.fma(pivotX, 1.0 - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sY * sd[7]);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_general(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dd[0] = Math.fma(sX, sd[0], sd[2] * _t2);
        dd[1] = Math.fma(sY, sd[1], sd[2] * _t3);
        dd[2] = sd[2];
        dd[3] = Math.fma(sX, sd[3], sd[5] * _t2);
        dd[4] = Math.fma(sY, sd[4], sd[5] * _t3);
        dd[5] = sd[5];
        dd[6] = Math.fma(sX, sd[6], sd[8] * _t2);
        dd[7] = Math.fma(sY, sd[7], sd[8] * _t3);
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(sX, sY, pivotX, pivotY, dest);
        return preScaleAround_general(sX, sY, pivotX, pivotY, dest);
    }


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
    @Mutated public Double3x3 preScaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal_self(sX, sY, pivotX, pivotY, this);
        return preScaleAround_general(sX, sY, pivotX, pivotY, this);
    }


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
    public Double3x3 preTranslate(Double2R v, @Mutated Double3x3 dest) {
        return preTranslate(v.x(), v.y(), dest);
    }


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
    public @Mutated Double3x3 preTranslate(Double2R v) {
        return preTranslate(v.x(), v.y());
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_orthogonal_affine(double vX, double vY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_orthogonal_affine_self(double vX, double vY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preTranslate} and {@code translate}; reached only through
     * them.
     */
    private Double3x3 preTranslate_translation(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Double3x3 preTranslate_translation_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_general(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(sd[2], vX, sd[0]);
        dd[1] = Math.fma(sd[2], vY, sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(sd[5], vX, sd[3]);
        dd[4] = Math.fma(sd[5], vY, sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(sd[8], vX, sd[6]);
        dd[7] = Math.fma(sd[8], vY, sd[7]);
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix and store the result
     * in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preTranslate(double vX, double vY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_orthogonal_affine(vX, vY, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preTranslate_general(vX, vY, dest);
    }


    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 preTranslate(double vX, double vY) {
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity_self(vX, vY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_orthogonal_affine_self(vX, vY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preTranslate_general(vX, vY, this);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_orthogonal_affine(double angle, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        dd[2] = 0.0;
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_orthogonal_affine_self(double angle, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_translation(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_translation_self(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_general(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        double _buf2 = Math.fma(sd[2], _t1, sd[5] * _t0);
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[5] = Math.fma(sd[5], _t1, -(sd[2] * _t0));
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a rotation by {@code angle} to this matrix and store the result in {@code dest}.
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
    public Double3x3 rotate(double angle, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotate_orthogonal_affine(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotate_general(angle, dest);
    }


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
    @Mutated public Double3x3 rotate(double angle) {
        if (Joml.RETURN_NEW) return rotate(angle, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation_self(angle, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotate_orthogonal_affine_self(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotate_general(angle, this);
    }


    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix and store
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
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateAround(double angle, Double2R pivot, @Mutated Double3x3 dest) {
        return rotateAround(angle, pivot.x(), pivot.y(), dest);
    }


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
    public @Mutated Double3x3 rotateAround(double angle, Double2R pivot) {
        return rotateAround(angle, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_orthogonal_affine(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        dd[2] = 0.0;
        double _buf2 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        double _buf3 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        dd[5] = 0.0;
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_orthogonal_affine_self(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        double _buf2 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        double _buf3 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_translation(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, sd[6]));
        dd[7] = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, sd[7]));
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_translation_self(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[6] = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, sd[6]));
        dd[7] = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, sd[7]));
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_general(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        double _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        double _buf2 = Math.fma(sd[2], _t2, sd[5] * _t0);
        double _buf3 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        double _buf4 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        double _buf5 = Math.fma(sd[5], _t2, -(sd[2] * _t0));
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[8] = Math.fma(sd[2], _t9, Math.fma(sd[5], _t10, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateAround(double angle, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAround_orthogonal_affine(angle, pivotX, pivotY, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateAround_general(angle, pivotX, pivotY, dest);
    }


    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 rotateAround(double angle, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAround_orthogonal_affine_self(angle, pivotX, pivotY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateAround_general(angle, pivotX, pivotY, this);
    }


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
    public Double3x3 rotateAxis(double angle, Double3R axis, @Mutated Double3x3 dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


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
    public @Mutated Double3x3 rotateAxis(double angle, Double3R axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
    }



    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Double3x3 rotateAxis_translation(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = 1.0 - _t1;
        double _t10 = Math.fma(_t7, axisZ * axisZ, _t1);
        double _t11 = Math.fma(axisX, _t0, _t7 * _t4);
        double _t12 = Math.fma(_t7, _t2, -(axisY * _t0));
        dd[0] = Math.fma(_t7, axisX * axisX, Math.fma(sd[6], _t12, _t1));
        dd[1] = Math.fma(sd[7], _t12, Math.fma(axisZ, _t0, _t7 * _t5));
        dd[2] = _t12;
        dd[3] = Math.fma(sd[6], _t11, Math.fma(_t7, _t5, -(axisZ * _t0)));
        dd[4] = Math.fma(_t7, axisY * axisY, Math.fma(sd[7], _t11, _t1));
        dd[5] = _t11;
        dd[6] = Math.fma(sd[6], _t10, Math.fma(axisY, _t0, _t7 * _t2));
        dd[7] = Math.fma(sd[7], _t10, Math.fma(_t7, _t4, -(axisX * _t0)));
        dd[8] = _t10;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Double3x3 rotateAxis_orthogonal(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t19 = Math.fma(_t11, axisY * axisY, _t1);
        double _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        double _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        double _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        double _buf0 = Math.fma(sd[6], _t24, Math.fma(sd[0], _t18, sd[3] * _t21));
        double _buf1 = Math.fma(sd[7], _t24, Math.fma(sd[1], _t18, sd[4] * _t21));
        dd[2] = _t24;
        return rotateAxis_orthogonal_s2359990f_1(dest, sd, dd, Math.fma(_t11, axisZ * axisZ, _t1), _t22, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)), _buf0, _buf1, Math.fma(sd[6], _t22, Math.fma(sd[0], _t25, sd[3] * _t19)), Math.fma(sd[7], _t22, Math.fma(sd[1], _t25, sd[4] * _t19)));
    }

    /** Piece 2 of {@code rotateAxis_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateAxis_orthogonal_s2359990f_1(Double3x3 dest, double[] sd, double[] dd, double _t20, double _t22, double _t23, double _t26, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[5] = _t22;
        dd[6] = Math.fma(sd[6], _t20, Math.fma(sd[0], _t23, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t20, Math.fma(sd[1], _t23, sd[4] * _t26));
        dd[8] = _t20;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Double3x3 rotateAxis_general(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t19 = Math.fma(_t11, axisY * axisY, _t1);
        double _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        double _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        double _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        return rotateAxis_general_s23249c68_1(dest, sd, dd, _t19, Math.fma(_t11, axisZ * axisZ, _t1), _t22, Math.fma(axisY, _t0, _t11 * _t2), _t25, Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(sd[6], _t24, Math.fma(sd[0], _t18, sd[3] * _t21)), Math.fma(sd[7], _t24, Math.fma(sd[1], _t18, sd[4] * _t21)), Math.fma(sd[8], _t24, Math.fma(sd[2], _t18, sd[5] * _t21)), Math.fma(sd[6], _t22, Math.fma(sd[0], _t25, sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateAxis_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateAxis_general_s23249c68_1(Double3x3 dest, double[] sd, double[] dd, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(sd[7], _t22, Math.fma(sd[1], _t25, sd[4] * _t19));
        double _buf5 = Math.fma(sd[8], _t22, Math.fma(sd[2], _t25, sd[5] * _t19));
        dd[6] = Math.fma(sd[6], _t20, Math.fma(sd[0], _t23, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t20, Math.fma(sd[1], _t23, sd[4] * _t26));
        dd[8] = Math.fma(sd[8], _t20, Math.fma(sd[2], _t23, sd[5] * _t26));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated Double3x3 dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAxis_translation(angle, axisX, axisY, axisZ, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAxis_orthogonal(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_general(angle, axisX, axisY, axisZ, dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (Joml.RETURN_NEW) return rotateAxis(angle, axisX, axisY, axisZ, Joml.double3x3());
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAxis_translation(angle, axisX, axisY, axisZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAxis_orthogonal(angle, axisX, axisY, axisZ, this);
        return rotateAxis_general(angle, axisX, axisY, axisZ, this);
    }


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
    public Double3x3 rotateX(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(sd[3], _t1, sd[6] * _t0);
        double _buf1 = Math.fma(sd[4], _t1, sd[7] * _t0);
        double _buf2 = Math.fma(sd[5], _t1, sd[8] * _t0);
        dd[6] = Math.fma(sd[6], _t1, -(sd[3] * _t0));
        dd[7] = Math.fma(sd[7], _t1, -(sd[4] * _t0));
        dd[8] = Math.fma(sd[8], _t1, -(sd[5] * _t0));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = -1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = -1.0;
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[4] = -1.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[1];
        dd[4] = -sd[4];
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[3] = sd[1];
        dd[4] = -sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateX180(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX180_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX180_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine(dest);
        return rotateX180_general(dest);
    }


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
    @Mutated public Double3x3 rotateX180() {
        if (Joml.RETURN_NEW) return rotateX180(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            dd[4] = -1.0;
            dd[8] = -1.0;
            ((Double3x3Impl) this).properties = 0;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX180_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine_self(this);
        return rotateX180_general(this);
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = -1.0;
        dd[6] = 0.0;
        dd[7] = 1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[4] = 0.0;
        dd[5] = -1.0;
        dd[7] = 1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = -sd[6];
        dd[4] = -sd[7];
        dd[5] = -1.0;
        dd[6] = 0.0;
        dd[7] = 1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[3] = -sd[6];
        dd[4] = -sd[7];
        dd[5] = -1.0;
        dd[6] = 0.0;
        dd[7] = 1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        dd[5] = -1.0;
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        dd[5] = -1.0;
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        double _buf2 = -sd[8];
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = sd[5];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateX270(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX270_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX270_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal(dest);
        return rotateX270_general(dest);
    }


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
    @Mutated public Double3x3 rotateX270() {
        if (Joml.RETURN_NEW) return rotateX270(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX270_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal_self(this);
        return rotateX270_general(this);
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = -1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[7] = -1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sd[6];
        dd[4] = sd[7];
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = -1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[3] = sd[6];
        dd[4] = sd[7];
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = -1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[6];
        double _buf1 = sd[7];
        dd[5] = 1.0;
        dd[6] = sd[1];
        dd[7] = -sd[4];
        dd[8] = 0.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[4];
        double _buf0 = sd[1];
        dd[3] = sd[6];
        double _buf1 = sd[7];
        dd[5] = 1.0;
        dd[6] = sd[1];
        dd[7] = -sd[4];
        dd[8] = 0.0;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        dd[5] = 1.0;
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        dd[5] = 1.0;
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = 0.0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        double _buf2 = sd[8];
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = -sd[5];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateX90(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX90_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX90_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine(dest);
        return rotateX90_general(dest);
    }


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
    @Mutated public Double3x3 rotateX90() {
        if (Joml.RETURN_NEW) return rotateX90(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX90_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine_self(this);
        return rotateX90_general(this);
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_identity(double angleX, double angleY, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t6, _t4, _t1 * _t5);
        dd[2] = Math.fma(_t2, _t1, -(_t7 * _t4));
        dd[3] = -(_t1 * _t3);
        dd[4] = Math.fma(_t5, _t4, -(_t6 * _t1));
        dd[5] = Math.fma(_t7, _t1, _t2 * _t4);
        dd[6] = _t0;
        dd[7] = -(_t2 * _t3);
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_translation(double angleX, double angleY, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(sd[6], _t13, _t5 * _t4);
        dd[1] = Math.fma(sd[7], _t13, Math.fma(_t6, _t4, _t1 * _t3));
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t12, -(_t1 * _t5));
        dd[4] = Math.fma(sd[7], _t12, Math.fma(_t3, _t4, -(_t6 * _t1)));
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t9, _t2);
        dd[7] = Math.fma(sd[7], _t9, -(_t0 * _t5));
        dd[8] = _t9;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_orthogonal(double angleX, double angleY, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t10 = _t1 * _t5;
        double _t13 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        double _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        double _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        double _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t13, sd[3] * _t18));
        double _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t13, sd[4] * _t18));
        dd[2] = _t20;
        double _buf2 = Math.fma(sd[6], _t19, Math.fma(sd[3], _t21, -(sd[0] * _t10)));
        double _buf3 = Math.fma(sd[7], _t19, Math.fma(sd[4], _t21, -(sd[1] * _t10)));
        dd[5] = _t19;
        return rotateXYZ_orthogonal_se4e7c41b_1(dest, sd, dd, _t2, _t0 * _t5, _t3 * _t5, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code rotateXYZ_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXYZ_orthogonal_se4e7c41b_1(Double3x3 dest, double[] sd, double[] dd, double _t2, double _t11, double _t15, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t2, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t2, -(sd[4] * _t11)));
        dd[8] = _t15;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_general(double angleX, double angleY, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t10 = _t1 * _t5;
        double _t13 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        double _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        double _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        return rotateXYZ_general_sf3d3b462_1(dest, sd, dd, _t2, _t10, _t0 * _t5, _t3 * _t5, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t13, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t13, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t13, sd[5] * _t18)), Math.fma(sd[6], _t19, Math.fma(sd[3], _t21, -(sd[0] * _t10))), Math.fma(sd[7], _t19, Math.fma(sd[4], _t21, -(sd[1] * _t10))));
    }

    /** Piece 2 of {@code rotateXYZ_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXYZ_general_sf3d3b462_1(Double3x3 dest, double[] sd, double[] dd, double _t2, double _t10, double _t11, double _t15, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[5], _t21, -(sd[2] * _t10)));
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t2, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t2, -(sd[4] * _t11)));
        dd[8] = Math.fma(sd[8], _t15, Math.fma(sd[2], _t2, -(sd[5] * _t11)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateXYZ(double angleX, double angleY, double angleZ, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXYZ_identity(angleX, angleY, angleZ, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXYZ_translation(angleX, angleY, angleZ, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXYZ_orthogonal(angleX, angleY, angleZ, dest);
        return rotateXYZ_general(angleX, angleY, angleZ, dest);
    }


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
    @Mutated public Double3x3 rotateXYZ(double angleX, double angleY, double angleZ) {
        if (Joml.RETURN_NEW) return rotateXYZ(angleX, angleY, angleZ, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXYZ_identity(angleX, angleY, angleZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXYZ_translation(angleX, angleY, angleZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXYZ_orthogonal(angleX, angleY, angleZ, this);
        return rotateXYZ_general(angleX, angleY, angleZ, this);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_identity(double angleX, double angleZ, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t7, _t3, _t2 * _t0);
        dd[2] = Math.fma(_t6, _t3, -(_t0 * _t5));
        dd[3] = -_t1;
        dd[4] = _t5 * _t4;
        dd[5] = _t2 * _t4;
        dd[6] = _t0 * _t4;
        dd[7] = Math.fma(_t7, _t0, -(_t2 * _t3));
        dd[8] = Math.fma(_t6, _t0, _t5 * _t3);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_translation(double angleX, double angleZ, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(sd[6], _t13, _t3 * _t5);
        dd[1] = Math.fma(sd[7], _t13, Math.fma(_t9, _t3, _t0 * _t2));
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t8, -_t1);
        dd[4] = Math.fma(sd[7], _t8, _t4 * _t5);
        dd[5] = _t8;
        dd[6] = Math.fma(sd[6], _t12, _t2 * _t5);
        dd[7] = Math.fma(sd[7], _t12, Math.fma(_t9, _t2, -(_t0 * _t3)));
        dd[8] = _t12;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_orthogonal(double angleX, double angleZ, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double _t10 = _t0 * _t5;
        double _t15 = _t3 * _t5;
        double _t16 = _t4 * _t5;
        double _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        double _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t15, sd[3] * _t18));
        double _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t15, sd[4] * _t18));
        dd[2] = _t20;
        double _buf2 = Math.fma(sd[6], _t10, Math.fma(sd[3], _t16, -(sd[0] * _t1)));
        double _buf3 = Math.fma(sd[7], _t10, Math.fma(sd[4], _t16, -(sd[1] * _t1)));
        dd[5] = _t10;
        return rotateXZY_orthogonal_sdf9d46ef_1(dest, sd, dd, _t2 * _t5, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)), _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code rotateXZY_orthogonal}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXZY_orthogonal_sdf9d46ef_1(Double3x3 dest, double[] sd, double[] dd, double _t11, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[6] = Math.fma(sd[6], _t19, Math.fma(sd[0], _t11, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t19, Math.fma(sd[1], _t11, sd[4] * _t21));
        dd[8] = _t19;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_general(double angleX, double angleZ, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double _t10 = _t0 * _t5;
        double _t15 = _t3 * _t5;
        double _t16 = _t4 * _t5;
        double _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        return rotateXZY_general_s892ec0b6_1(dest, sd, dd, _t1, _t10, _t2 * _t5, _t16, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)), Math.fma(sd[6], _t20, Math.fma(sd[0], _t15, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t15, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t15, sd[5] * _t18)), Math.fma(sd[6], _t10, Math.fma(sd[3], _t16, -(sd[0] * _t1))), Math.fma(sd[7], _t10, Math.fma(sd[4], _t16, -(sd[1] * _t1))));
    }

    /** Piece 2 of {@code rotateXZY_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXZY_general_s892ec0b6_1(Double3x3 dest, double[] sd, double[] dd, double _t1, double _t10, double _t11, double _t16, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t10, Math.fma(sd[5], _t16, -(sd[2] * _t1)));
        dd[6] = Math.fma(sd[6], _t19, Math.fma(sd[0], _t11, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t19, Math.fma(sd[1], _t11, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t19, Math.fma(sd[2], _t11, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateXZY(double angleX, double angleZ, double angleY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXZY_identity(angleX, angleZ, angleY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXZY_translation(angleX, angleZ, angleY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXZY_orthogonal(angleX, angleZ, angleY, dest);
        return rotateXZY_general(angleX, angleZ, angleY, dest);
    }


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
    @Mutated public Double3x3 rotateXZY(double angleX, double angleZ, double angleY) {
        if (Joml.RETURN_NEW) return rotateXZY(angleX, angleZ, angleY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXZY_identity(angleX, angleZ, angleY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXZY_translation(angleX, angleZ, angleY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXZY_orthogonal(angleX, angleZ, angleY, this);
        return rotateXZY_general(angleX, angleZ, angleY, this);
    }


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
    public Double3x3 rotateXn180(@Mutated Double3x3 dest) {
        return rotateX180(dest);
    }


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
    @Mutated public Double3x3 rotateXn180() {
        return rotateX180();
    }


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
    public Double3x3 rotateXn270(@Mutated Double3x3 dest) {
        return rotateX90(dest);
    }


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
    @Mutated public Double3x3 rotateXn270() {
        return rotateX90();
    }


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
    public Double3x3 rotateXn90(@Mutated Double3x3 dest) {
        return rotateX270(dest);
    }


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
    @Mutated public Double3x3 rotateXn90() {
        return rotateX270();
    }


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
    public Double3x3 rotateY(double angle, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[6] * _t0));
        double _buf1 = Math.fma(sd[1], _t1, -(sd[7] * _t0));
        double _buf2 = Math.fma(sd[2], _t1, -(sd[8] * _t0));
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = Math.fma(sd[0], _t0, sd[6] * _t1);
        dd[7] = Math.fma(sd[1], _t0, sd[7] * _t1);
        dd[8] = Math.fma(sd[2], _t0, sd[8] * _t1);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = 0.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateY180(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY180_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY180_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal(dest);
        return rotateY180_general(dest);
    }


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
    @Mutated public Double3x3 rotateY180() {
        if (Joml.RETURN_NEW) return rotateY180(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            dd[0] = -1.0;
            dd[8] = -1.0;
            ((Double3x3Impl) this).properties = 0;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY180_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal_self(this);
        return rotateY180_general(this);
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 1.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[2] = 1.0;
        dd[6] = -1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = 1.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = -1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = 1.0;
        dd[6] = -1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        dd[2] = 1.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = 0.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        dd[2] = 1.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = 0.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[6];
        double _buf1 = sd[7];
        double _buf2 = sd[8];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = -sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateY270(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY270_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY270_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal(dest);
        return rotateY270_general(dest);
    }


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
    @Mutated public Double3x3 rotateY270() {
        if (Joml.RETURN_NEW) return rotateY270(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY270_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal_self(this);
        return rotateY270_general(this);
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = -1.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[2] = -1.0;
        dd[6] = 1.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[6];
        dd[1] = -sd[7];
        dd[2] = -1.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[6];
        dd[1] = -sd[7];
        dd[2] = -1.0;
        dd[6] = 1.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        dd[2] = -1.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0;
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = 0.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        dd[2] = -1.0;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = 0.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[6];
        double _buf1 = -sd[7];
        double _buf2 = -sd[8];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateY90(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY90_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY90_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal(dest);
        return rotateY90_general(dest);
    }


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
    @Mutated public Double3x3 rotateY90() {
        if (Joml.RETURN_NEW) return rotateY90(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY90_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal_self(this);
        return rotateY90_general(this);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_identity(double angleY, double angleX, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dd[0] = Math.fma(_t6, _t2, _t3 * _t4);
        dd[1] = _t2 * _t5;
        dd[2] = Math.fma(_t7, _t2, -(_t1 * _t4));
        dd[3] = Math.fma(_t6, _t4, -(_t2 * _t3));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t7, _t4, _t1 * _t2);
        dd[6] = _t1 * _t5;
        dd[7] = -_t0;
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_translation(double angleY, double angleX, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(sd[6], _t13, Math.fma(_t6, _t2, _t3 * _t4));
        dd[1] = Math.fma(sd[7], _t13, _t2 * _t5);
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t12, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dd[4] = Math.fma(sd[7], _t12, _t5 * _t4);
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t11, _t1 * _t5);
        dd[7] = Math.fma(sd[7], _t11, -_t0);
        dd[8] = _t11;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_orthogonal(double angleY, double angleX, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t16 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        double _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        double _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t18, sd[3] * _t10));
        double _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t18, sd[4] * _t10));
        dd[2] = _t20;
        double _buf2 = Math.fma(sd[6], _t19, Math.fma(sd[0], _t21, sd[3] * _t16));
        double _buf3 = Math.fma(sd[7], _t19, Math.fma(sd[1], _t21, sd[4] * _t16));
        dd[5] = _t19;
        return rotateYXZ_orthogonal_sf409a62f_1(dest, sd, dd, _t0, _t1 * _t5, _t5 * _t3, _buf0, _buf1, _buf2, _buf3);
    }

    /**
     * Piece 2 of {@code rotateYXZ_orthogonal}, split to fit the inline budget. Shared by the
     * identical private paths of {@code rotateYXZ} and {@code rotateYZX}; reached only through
     * them.
     */
    private Double3x3 rotateYXZ_orthogonal_sf409a62f_1(Double3x3 dest, double[] sd, double[] dd, double _t0, double _t12, double _t17, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t12, -(sd[3] * _t0)));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t12, -(sd[4] * _t0)));
        dd[8] = _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_general(double angleY, double angleX, double angleZ, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t16 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        double _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        return rotateYXZ_general_s3dbff562_1(dest, sd, dd, _t0, _t1 * _t5, _t16, _t5 * _t3, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t18, sd[3] * _t10)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t18, sd[4] * _t10)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t18, sd[5] * _t10)), Math.fma(sd[6], _t19, Math.fma(sd[0], _t21, sd[3] * _t16)), Math.fma(sd[7], _t19, Math.fma(sd[1], _t21, sd[4] * _t16)));
    }

    /** Piece 2 of {@code rotateYXZ_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateYXZ_general_s3dbff562_1(Double3x3 dest, double[] sd, double[] dd, double _t0, double _t12, double _t16, double _t17, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[2], _t21, sd[5] * _t16));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t12, -(sd[3] * _t0)));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t12, -(sd[4] * _t0)));
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t12, -(sd[5] * _t0)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateYXZ(double angleY, double angleX, double angleZ, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYXZ_identity(angleY, angleX, angleZ, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYXZ_translation(angleY, angleX, angleZ, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYXZ_orthogonal(angleY, angleX, angleZ, dest);
        return rotateYXZ_general(angleY, angleX, angleZ, dest);
    }


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
    @Mutated public Double3x3 rotateYXZ(double angleY, double angleX, double angleZ) {
        if (Joml.RETURN_NEW) return rotateYXZ(angleY, angleX, angleZ, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYXZ_identity(angleY, angleX, angleZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYXZ_translation(angleY, angleX, angleZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYXZ_orthogonal(angleY, angleX, angleZ, this);
        return rotateYXZ_general(angleY, angleX, angleZ, this);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_identity(double angleY, double angleZ, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        dd[0] = _t3 * _t4;
        dd[1] = _t1;
        dd[2] = -(_t0 * _t4);
        dd[3] = Math.fma(_t2, _t0, -(_t7 * _t5));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t6, _t5, _t2 * _t3);
        dd[6] = Math.fma(_t7, _t2, _t0 * _t5);
        dd[7] = -(_t2 * _t4);
        dd[8] = Math.fma(_t5, _t3, -(_t6 * _t2));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_translation(double angleY, double angleZ, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(_t3, _t4, -(sd[6] * _t7));
        dd[1] = Math.fma(-sd[7], _t7, _t1);
        dd[2] = -_t7;
        dd[3] = Math.fma(sd[6], _t12, Math.fma(_t2, _t0, -(_t9 * _t5)));
        dd[4] = Math.fma(sd[7], _t12, _t5 * _t4);
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t13, Math.fma(_t9, _t2, _t0 * _t5));
        dd[7] = Math.fma(sd[7], _t13, -(_t2 * _t4));
        dd[8] = _t13;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_orthogonal(double angleY, double angleZ, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        double _t9 = _t1 * _t4;
        double _t13 = _t4 * _t3;
        double _t14 = _t5 * _t3;
        double _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        double _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        double _buf0 = Math.fma(-sd[6], _t7, Math.fma(sd[0], _t13, sd[3] * _t1));
        double _buf1 = Math.fma(-sd[7], _t7, Math.fma(sd[1], _t13, sd[4] * _t1));
        dd[2] = -_t7;
        double _buf2 = Math.fma(sd[6], _t18, Math.fma(sd[0], _t20, sd[3] * _t14));
        double _buf3 = Math.fma(sd[7], _t18, Math.fma(sd[1], _t20, sd[4] * _t14));
        dd[5] = _t18;
        return rotateYXZ_orthogonal_sf409a62f_1(dest, sd, dd, _t2 * _t3, Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t5, _t4, -(_t6 * _t2)), _buf0, _buf1, _buf2, _buf3);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_general(double angleY, double angleZ, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        double _t9 = _t1 * _t4;
        double _t13 = _t4 * _t3;
        double _t14 = _t5 * _t3;
        double _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        double _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        return rotateYZX_general_s170a2176_1(dest, sd, dd, _t2 * _t3, _t14, _t18, Math.fma(_t9, _t2, _t0 * _t5), _t20, Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t13, sd[3] * _t1)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t13, sd[4] * _t1)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t13, sd[5] * _t1)), Math.fma(sd[6], _t18, Math.fma(sd[0], _t20, sd[3] * _t14)), Math.fma(sd[7], _t18, Math.fma(sd[1], _t20, sd[4] * _t14)));
    }

    /** Piece 2 of {@code rotateYZX_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateYZX_general_s170a2176_1(Double3x3 dest, double[] sd, double[] dd, double _t11, double _t14, double _t18, double _t19, double _t20, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t18, Math.fma(sd[2], _t20, sd[5] * _t14));
        dd[6] = Math.fma(sd[6], _t21, Math.fma(sd[0], _t19, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t21, Math.fma(sd[1], _t19, -(sd[4] * _t11)));
        dd[8] = Math.fma(sd[8], _t21, Math.fma(sd[2], _t19, -(sd[5] * _t11)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateYZX(double angleY, double angleZ, double angleX, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYZX_identity(angleY, angleZ, angleX, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYZX_translation(angleY, angleZ, angleX, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYZX_orthogonal(angleY, angleZ, angleX, dest);
        return rotateYZX_general(angleY, angleZ, angleX, dest);
    }


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
    @Mutated public Double3x3 rotateYZX(double angleY, double angleZ, double angleX) {
        if (Joml.RETURN_NEW) return rotateYZX(angleY, angleZ, angleX, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYZX_identity(angleY, angleZ, angleX, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYZX_translation(angleY, angleZ, angleX, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYZX_orthogonal(angleY, angleZ, angleX, this);
        return rotateYZX_general(angleY, angleZ, angleX, this);
    }


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
    public Double3x3 rotateYn180(@Mutated Double3x3 dest) {
        return rotateY180(dest);
    }


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
    @Mutated public Double3x3 rotateYn180() {
        return rotateY180();
    }


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
    public Double3x3 rotateYn270(@Mutated Double3x3 dest) {
        return rotateY90(dest);
    }


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
    @Mutated public Double3x3 rotateYn270() {
        return rotateY90();
    }


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
    public Double3x3 rotateYn90(@Mutated Double3x3 dest) {
        return rotateY270(dest);
    }


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
    @Mutated public Double3x3 rotateYn90() {
        return rotateY270();
    }


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
    public Double3x3 rotateZ(double angle, @Mutated Double3x3 dest) {
        return rotate(angle, dest);
    }


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
    @Mutated public Double3x3 rotateZ(double angle) {
        return rotate(angle);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = -1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }



    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = -1.0;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -1.0;
        dd[4] = -1.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = -sd[4];
        dd[0] = _t0;
        double _buf0 = -sd[1];
        dd[2] = 0.0;
        dd[3] = sd[1];
        dd[4] = _t0;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = -sd[4];
        dd[0] = _t0;
        double _buf0 = -sd[1];
        dd[3] = sd[1];
        dd[4] = _t0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = 0.0;
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateZ180(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ180_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ180_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine(dest);
        return rotateZ180_general(dest);
    }


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
    @Mutated public Double3x3 rotateZ180() {
        if (Joml.RETURN_NEW) return rotateZ180(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            dd[0] = -1.0;
            dd[4] = -1.0;
            ((Double3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ180_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine_self(this);
        return rotateZ180_general(this);
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = -1.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = -1.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = -1.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = -1.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_orthogonal(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[1];
        double _buf0 = -sd[4];
        dd[2] = 0.0;
        dd[3] = sd[4];
        dd[4] = sd[1];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_orthogonal_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[1];
        double _buf0 = -sd[4];
        dd[3] = sd[4];
        dd[4] = sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[3];
        double _buf1 = -sd[4];
        dd[2] = 0.0;
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_affine_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[3];
        double _buf1 = -sd[4];
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = -sd[3];
        double _buf1 = -sd[4];
        double _buf2 = -sd[5];
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateZ270(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ270_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ270_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine(dest);
        return rotateZ270_general(dest);
    }


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
    @Mutated public Double3x3 rotateZ270() {
        if (Joml.RETURN_NEW) return rotateZ270(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ270_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine_self(this);
        return rotateZ270_general(this);
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_orthogonal_affine(@Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[3];
        double _buf1 = sd[4];
        dd[2] = 0.0;
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_orthogonal_affine_self(@Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[3];
        double _buf1 = sd[4];
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_identity(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 1.0;
        dd[2] = 0.0;
        dd[3] = -1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_identity_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 1.0;
        dd[3] = -1.0;
        dd[4] = 0.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 1.0;
        dd[2] = 0.0;
        dd[3] = -1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_translation_self(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 1.0;
        dd[3] = -1.0;
        dd[4] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[3];
        double _buf1 = sd[4];
        double _buf2 = sd[5];
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[5] = -sd[2];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateZ90(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ90_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ90_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_orthogonal_affine(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateZ90_general(dest);
    }


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
    @Mutated public Double3x3 rotateZ90() {
        if (Joml.RETURN_NEW) return rotateZ90(Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ90_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_orthogonal_affine_self(this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateZ90_general(this);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_identity(double angleZ, double angleX, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dd[0] = Math.fma(_t3, _t4, -(_t6 * _t0));
        dd[1] = Math.fma(_t7, _t0, _t1 * _t3);
        dd[2] = -(_t0 * _t5);
        dd[3] = -(_t1 * _t5);
        dd[4] = _t5 * _t4;
        dd[5] = _t2;
        dd[6] = Math.fma(_t6, _t3, _t0 * _t4);
        dd[7] = Math.fma(_t0, _t1, -(_t7 * _t3));
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_translation(double angleZ, double angleX, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(-sd[6], _t7, Math.fma(_t4, _t5, -(_t6 * _t0)));
        dd[1] = Math.fma(-sd[7], _t7, Math.fma(_t8, _t0, _t2 * _t4));
        dd[2] = -_t7;
        dd[3] = Math.fma(sd[6], _t1, -(_t2 * _t3));
        dd[4] = Math.fma(sd[7], _t1, _t3 * _t5);
        dd[5] = _t1;
        dd[6] = Math.fma(sd[6], _t9, Math.fma(_t6, _t4, _t0 * _t5));
        dd[7] = Math.fma(sd[7], _t9, Math.fma(_t0, _t2, -(_t8 * _t4)));
        dd[8] = _t9;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_orthogonal(double angleZ, double angleX, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t10 = _t2 * _t3;
        double _t14 = _t3 * _t5;
        double _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        double _buf0 = Math.fma(-sd[6], _t7, Math.fma(sd[0], _t20, sd[3] * _t18));
        double _buf1 = Math.fma(-sd[7], _t7, Math.fma(sd[1], _t20, sd[4] * _t18));
        dd[2] = -_t7;
        double _buf2 = Math.fma(sd[6], _t1, Math.fma(sd[3], _t14, -(sd[0] * _t10)));
        double _buf3 = Math.fma(sd[7], _t1, Math.fma(sd[4], _t14, -(sd[1] * _t10)));
        dd[5] = _t1;
        return rotateZXY_orthogonal_sec5ee9bf_1(dest, sd, dd, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)), _buf0, _buf1, _buf2, _buf3);
    }

    /**
     * Piece 2 of {@code rotateZXY_orthogonal}, split to fit the inline budget. Shared by the
     * identical private paths of {@code rotateZXY} and {@code rotateZYX}; reached only through
     * them.
     */
    private Double3x3 rotateZXY_orthogonal_sec5ee9bf_1(Double3x3 dest, double[] sd, double[] dd, double _t15, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = _t15;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_general(double angleZ, double angleX, double angleY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t10 = _t2 * _t3;
        double _t14 = _t3 * _t5;
        double _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        return rotateZXY_general_se98be732_1(dest, sd, dd, _t1, _t10, _t14, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t20, sd[4] * _t18)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t20, sd[5] * _t18)), Math.fma(sd[6], _t1, Math.fma(sd[3], _t14, -(sd[0] * _t10))), Math.fma(sd[7], _t1, Math.fma(sd[4], _t14, -(sd[1] * _t10))));
    }

    /** Piece 2 of {@code rotateZXY_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateZXY_general_se98be732_1(Double3x3 dest, double[] sd, double[] dd, double _t1, double _t10, double _t14, double _t15, double _t19, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t1, Math.fma(sd[5], _t14, -(sd[2] * _t10)));
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t15, Math.fma(sd[2], _t19, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateZXY(double angleZ, double angleX, double angleY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZXY_identity(angleZ, angleX, angleY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZXY_translation(angleZ, angleX, angleY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZXY_orthogonal(angleZ, angleX, angleY, dest);
        return rotateZXY_general(angleZ, angleX, angleY, dest);
    }


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
    @Mutated public Double3x3 rotateZXY(double angleZ, double angleX, double angleY) {
        if (Joml.RETURN_NEW) return rotateZXY(angleZ, angleX, angleY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZXY_identity(angleZ, angleX, angleY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZXY_translation(angleZ, angleX, angleY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZXY_orthogonal(angleZ, angleX, angleY, this);
        return rotateZXY_general(angleZ, angleX, angleY, this);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_identity(double angleZ, double angleY, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        dd[0] = _t3 * _t4;
        dd[1] = _t1 * _t3;
        dd[2] = -_t0;
        dd[3] = Math.fma(_t7, _t2, -(_t1 * _t5));
        dd[4] = Math.fma(_t6, _t2, _t5 * _t4);
        dd[5] = _t2 * _t3;
        dd[6] = Math.fma(_t7, _t5, _t2 * _t1);
        dd[7] = Math.fma(_t6, _t5, -(_t2 * _t4));
        dd[8] = _t5 * _t3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_translation(double angleZ, double angleY, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
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
        dd[0] = Math.fma(_t3, _t4, -(sd[6] * _t0));
        dd[1] = Math.fma(_t1, _t3, -(sd[7] * _t0));
        dd[2] = -_t0;
        dd[3] = Math.fma(sd[6], _t7, Math.fma(_t8, _t2, -(_t1 * _t5)));
        dd[4] = Math.fma(sd[7], _t7, Math.fma(_t6, _t2, _t5 * _t4));
        dd[5] = _t7;
        dd[6] = Math.fma(sd[6], _t9, Math.fma(_t8, _t5, _t2 * _t1));
        dd[7] = Math.fma(sd[7], _t9, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dd[8] = _t9;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_orthogonal(double angleZ, double angleY, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t1 * _t3;
        double _t9 = _t2 * _t3;
        double _t10 = _t0 * _t4;
        double _t15 = _t3 * _t4;
        double _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        double _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        double _buf0 = Math.fma(-sd[6], _t0, Math.fma(sd[0], _t15, sd[3] * _t8));
        double _buf1 = Math.fma(-sd[7], _t0, Math.fma(sd[1], _t15, sd[4] * _t8));
        dd[2] = -_t0;
        double _buf2 = Math.fma(sd[6], _t9, Math.fma(sd[0], _t20, sd[3] * _t18));
        double _buf3 = Math.fma(sd[7], _t9, Math.fma(sd[1], _t20, sd[4] * _t18));
        dd[5] = _t9;
        return rotateZXY_orthogonal_sec5ee9bf_1(dest, sd, dd, _t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)), _buf0, _buf1, _buf2, _buf3);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_general(double angleZ, double angleY, double angleX, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t1 * _t3;
        double _t9 = _t2 * _t3;
        double _t10 = _t0 * _t4;
        double _t15 = _t3 * _t4;
        double _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        double _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        return rotateZYX_general_sff8489e2_1(dest, sd, dd, _t9, _t5 * _t3, _t18, Math.fma(_t10, _t5, _t2 * _t1), _t20, Math.fma(_t6, _t5, -(_t2 * _t4)), Math.fma(-sd[6], _t0, Math.fma(sd[0], _t15, sd[3] * _t8)), Math.fma(-sd[7], _t0, Math.fma(sd[1], _t15, sd[4] * _t8)), Math.fma(-sd[8], _t0, Math.fma(sd[2], _t15, sd[5] * _t8)), Math.fma(sd[6], _t9, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(sd[7], _t9, Math.fma(sd[1], _t20, sd[4] * _t18)));
    }

    /** Piece 2 of {@code rotateZYX_general}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateZYX_general_sff8489e2_1(Double3x3 dest, double[] sd, double[] dd, double _t9, double _t17, double _t18, double _t19, double _t20, double _t21, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(sd[8], _t9, Math.fma(sd[2], _t20, sd[5] * _t18));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t19, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


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
    public Double3x3 rotateZYX(double angleZ, double angleY, double angleX, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZYX_identity(angleZ, angleY, angleX, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZYX_translation(angleZ, angleY, angleX, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZYX_orthogonal(angleZ, angleY, angleX, dest);
        return rotateZYX_general(angleZ, angleY, angleX, dest);
    }


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
    @Mutated public Double3x3 rotateZYX(double angleZ, double angleY, double angleX) {
        if (Joml.RETURN_NEW) return rotateZYX(angleZ, angleY, angleX, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZYX_identity(angleZ, angleY, angleX, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZYX_translation(angleZ, angleY, angleX, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZYX_orthogonal(angleZ, angleY, angleX, this);
        return rotateZYX_general(angleZ, angleY, angleX, this);
    }


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
    public Double3x3 rotateZn180(@Mutated Double3x3 dest) {
        return rotateZ180(dest);
    }


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
    @Mutated public Double3x3 rotateZn180() {
        return rotateZ180();
    }


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
    public Double3x3 rotateZn270(@Mutated Double3x3 dest) {
        return rotateZ90(dest);
    }


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
    @Mutated public Double3x3 rotateZn270() {
        return rotateZ90();
    }


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
    public Double3x3 rotateZn90(@Mutated Double3x3 dest) {
        return rotateZ270(dest);
    }


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
    @Mutated public Double3x3 rotateZn90() {
        return rotateZ270();
    }


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
    public Double3x3 scale(Double2R v, @Mutated Double3x3 dest) {
        return scale(v.x(), v.y(), dest);
    }


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
    public @Mutated Double3x3 scale(Double2R v) {
        return scale(v.x(), v.y());
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_translation(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = vY;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double3x3 scale_translation_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = vX;
        dd[4] = vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = 0.0;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal_self(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_general(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[5] = sd[5] * vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scale(double vX, double vY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(vX, vY, dest);
        return scale_general(vX, vY, dest);
    }


    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 scale(double vX, double vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity_self(vX, vY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal_self(vX, vY, this);
        return scale_general(vX, vY, this);
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_translation(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double3x3 scale_translation_self(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s;
        dd[4] = s;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal_self(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_general(double s, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) to this matrix and store
     * the result in {@code dest}.
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
    public Double3x3 scale(double s, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(s, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(s, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(s, dest);
        return scale_general(s, dest);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) to this matrix.
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
    @Mutated public Double3x3 scale(double s) {
        if (Joml.RETURN_NEW) return scale(s, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) this).data;
            dd[0] = s;
            dd[4] = s;
            ((Double3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(s, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal_self(s, this);
        return scale_general(s, this);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} to this matrix and store the result in {@code dest}.
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
    public Double3x3 scaleAround(double s, Double2R pivot, @Mutated Double3x3 dest) {
        return scaleAround(s, pivot.x(), pivot.y(), dest);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} to this matrix.
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
    public @Mutated Double3x3 scaleAround(double s, Double2R pivot) {
        return scaleAround(s, pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = s;
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, _t0, sd[6]);
        dd[7] = Math.fma(pivotY, _t0, sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation_self(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = Math.fma(pivotX, _t0, sd[6]);
        dd[7] = Math.fma(pivotY, _t0, sd[7]);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _buf0 = s * sd[0];
        double _buf1 = s * sd[1];
        dd[2] = 0.0;
        double _buf2 = s * sd[3];
        double _buf3 = s * sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal_self(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _buf0 = s * sd[0];
        double _buf1 = s * sd[1];
        double _buf2 = s * sd[3];
        double _buf3 = s * sd[4];
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_general(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _buf0 = s * sd[0];
        double _buf1 = s * sd[1];
        double _buf2 = s * sd[2];
        double _buf3 = s * sd[3];
        double _buf4 = s * sd[4];
        double _buf5 = s * sd[5];
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[8] = Math.fma(sd[2], _t1, Math.fma(sd[5], _t2, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scaleAround(double s, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(s, pivotX, pivotY, dest);
        return scaleAround_general(s, pivotX, pivotY, dest);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) to this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 scaleAround(double s, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal_self(s, pivotX, pivotY, this);
        return scaleAround_general(s, pivotX, pivotY, this);
    }


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
    public Double3x3 scaleAround(Double2R s, Double2R pivot, @Mutated Double3x3 dest) {
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
    }


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
    public @Mutated Double3x3 scaleAround(Double2R s, Double2R pivot) {
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = sY;
        dd[5] = 0.0;
        dd[6] = Math.fma(pivotX, 1.0 - sX, sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = Math.fma(pivotX, 1.0 - sX, sd[6]);
        dd[7] = Math.fma(pivotY, 1.0 - sY, sd[7]);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _buf0 = sX * sd[0];
        double _buf1 = sX * sd[1];
        dd[2] = 0.0;
        double _buf2 = sY * sd[3];
        double _buf3 = sY * sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _buf0 = sX * sd[0];
        double _buf1 = sX * sd[1];
        double _buf2 = sY * sd[3];
        double _buf3 = sY * sd[4];
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_general(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _buf0 = sX * sd[0];
        double _buf1 = sX * sd[1];
        double _buf2 = sX * sd[2];
        double _buf3 = sY * sd[3];
        double _buf4 = sY * sd[4];
        double _buf5 = sY * sd[5];
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[8] = Math.fma(sd[2], _t2, Math.fma(sd[5], _t3, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(sX, sY, pivotX, pivotY, dest);
        return scaleAround_general(sX, sY, pivotX, pivotY, dest);
    }


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
    @Mutated public Double3x3 scaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal_self(sX, sY, pivotX, pivotY, this);
        return scaleAround_general(sX, sY, pivotX, pivotY, this);
    }


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
    public Double3x3 translate(Double2R v, @Mutated Double3x3 dest) {
        return translate(v.x(), v.y(), dest);
    }


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
    public @Mutated Double3x3 translate(Double2R v) {
        return translate(v.x(), v.y());
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_orthogonal_affine(double vX, double vY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        dd[2] = 0.0;
        double _buf2 = sd[3];
        double _buf3 = sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_orthogonal_affine_self(double vX, double vY, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        double _buf2 = sd[3];
        double _buf3 = sd[4];
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }






    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_general(double vX, double vY, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _buf0 = sd[0];
        double _buf1 = sd[1];
        double _buf2 = sd[2];
        double _buf3 = sd[3];
        double _buf4 = sd[4];
        double _buf5 = sd[5];
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[8] = Math.fma(sd[2], vX, Math.fma(sd[5], vY, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 translate(double vX, double vY, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_orthogonal_affine(vX, vY, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return translate_general(vX, vY, dest);
    }


    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double3x3 translate(double vX, double vY) {
        if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity_self(vX, vY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_orthogonal_affine_self(vX, vY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return translate_general(vX, vY, this);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_identity(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double3x3 view_identity_self(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[4] = _t1_inv + _t1_inv;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_translation(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0;
        dd[6] = Math.fma(-(left + right), _t0_inv, sd[6]);
        dd[7] = Math.fma(-(bottom + top), _t1_inv, sd[7]);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double3x3 view_translation_self(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[4] = _t1_inv + _t1_inv;
        dd[6] = Math.fma(-(left + right), _t0_inv, sd[6]);
        dd[7] = Math.fma(-(bottom + top), _t1_inv, sd[7]);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_orthogonal(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * sd[0];
        double _buf1 = _sp0 * sd[1];
        dd[2] = 0.0;
        double _buf2 = _sp1 * sd[3];
        double _buf3 = _sp1 * sd[4];
        dd[5] = 0.0;
        dd[6] = Math.fma(-sd[3], _sp3, Math.fma(-sd[0], _sp2, sd[6]));
        dd[7] = Math.fma(-sd[4], _sp3, Math.fma(-sd[1], _sp2, sd[7]));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double3x3 view_orthogonal_self(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * sd[0];
        double _buf1 = _sp0 * sd[1];
        double _buf2 = _sp1 * sd[3];
        double _buf3 = _sp1 * sd[4];
        dd[6] = Math.fma(-sd[3], _sp3, Math.fma(-sd[0], _sp2, sd[6]));
        dd[7] = Math.fma(-sd[4], _sp3, Math.fma(-sd[1], _sp2, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_affine(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * sd[0];
        double _buf1 = _sp0 * sd[1];
        dd[2] = 0.0;
        double _buf2 = _sp1 * sd[3];
        double _buf3 = _sp1 * sd[4];
        dd[5] = 0.0;
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[8] = 1.0;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double3x3 view_affine_self(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * sd[0];
        double _buf1 = _sp0 * sd[1];
        double _buf2 = _sp1 * sd[3];
        double _buf3 = _sp1 * sd[4];
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_general(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * sd[0];
        double _buf1 = _sp0 * sd[1];
        double _buf2 = _sp0 * sd[2];
        double _buf3 = _sp1 * sd[3];
        double _buf4 = _sp1 * sd[4];
        double _buf5 = _sp1 * sd[5];
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[8] = sd[8] + Math.fma(-sd[5], _sp3, -(sd[2] * _sp2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 view(double left, double right, double bottom, double top, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity(left, right, bottom, top, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation(left, right, bottom, top, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal(left, right, bottom, top, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return view_affine(left, right, bottom, top, dest);
        return view_general(left, right, bottom, top, dest);
    }


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
    @Mutated public Double3x3 view(double left, double right, double bottom, double top) {
        if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.double3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return view_affine_self(left, right, bottom, top, this);
        return view_general(left, right, bottom, top, this);
    }


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
    public Double3 mul(Double3R v, @Mutated Double3 dest) {
        return mul(v.x(), v.y(), v.z(), dest);
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3 mul_translation(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.fma(sd[6], vZ, vX);
        dd[1] = Math.fma(sd[7], vZ, vY);
        dd[2] = vZ;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3 mul_general(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.fma(sd[6], vZ, Math.fma(sd[0], vX, sd[3] * vY));
        dd[1] = Math.fma(sd[7], vZ, Math.fma(sd[1], vX, sd[4] * vY));
        dd[2] = Math.fma(sd[8], vZ, Math.fma(sd[2], vX, sd[5] * vY));
        return dest;
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(double vX, double vY, double vZ, @Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            dd[2] = vZ;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(vX, vY, vZ, dest);
        return mul_general(vX, vY, vZ, dest);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformDirection(Double2R v, @Mutated Double2 dest) {
        return transformDirection(v.x(), v.y(), dest);
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code transformDirection} and {@code transformPosition};
     * reached only through them.
     */
    private Double2 transformDirection_identity(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = vX;
        dd[1] = vY;
        return dest;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Double2 transformDirection_general(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[3] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[4] * vY);
        return dest;
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformDirection(double vX, double vY, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return transformDirection_general(vX, vY, dest);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformPosition(Double2R v, @Mutated Double2 dest) {
        return transformPosition(v.x(), v.y(), dest);
    }



    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_translation(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[6] + vX;
        dd[1] = sd[7] + vY;
        return dest;
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_general(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[1] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        return dest;
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformPosition(double vX, double vY, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformPosition_translation(vX, vY, dest);
        return transformPosition_general(vX, vY, dest);
    }

    public double m00() { return data[0]; }
    public double m01() { return data[3]; }
    public double m02() { return data[6]; }
    public double m10() { return data[1]; }
    public double m11() { return data[4]; }
    public double m12() { return data[7]; }
    public double m20() { return data[2]; }
    public double m21() { return data[5]; }
    public double m22() { return data[8]; }

    @Override public String toString() {
        return "Double3x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3x3Impl)) return false;
        Double3x3Impl o = (Double3x3Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5])
            && Double.isFinite(data[6])
            && Double.isFinite(data[7])
            && Double.isFinite(data[8]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7])
            || Double.isNaN(data[8]);
    }

    @Override public boolean equalsEpsilon(Double3x3R other, double epsilon) {
        return Math.abs(data[0] - other.m00()) <= epsilon
            && Math.abs(data[3] - other.m01()) <= epsilon
            && Math.abs(data[6] - other.m02()) <= epsilon
            && Math.abs(data[1] - other.m10()) <= epsilon
            && Math.abs(data[4] - other.m11()) <= epsilon
            && Math.abs(data[7] - other.m12()) <= epsilon
            && Math.abs(data[2] - other.m20()) <= epsilon
            && Math.abs(data[5] - other.m21()) <= epsilon
            && Math.abs(data[8] - other.m22()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadCM(double[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double3x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Double3x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double3x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double3x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    public Double3x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        dest[offset + 8] = (float) this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadCM(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double3x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public Double3x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double3x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double3x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    public Double3x3 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private double[] storeRM_distinct(double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[3];
        dest[offset + 2] = this.data[6];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[7];
        dest[offset + 6] = this.data[2];
        dest[offset + 7] = this.data[5];
        dest[offset + 8] = this.data[8];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset) {
        double[] d = this.data;
        double t0 = d[0];
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        double t8 = d[8];
        dest[offset + 0] = t0;
        dest[offset + 1] = t3;
        dest[offset + 2] = t6;
        dest[offset + 3] = t1;
        dest[offset + 4] = t4;
        dest[offset + 5] = t7;
        dest[offset + 6] = t2;
        dest[offset + 7] = t5;
        dest[offset + 8] = t8;
        return dest;
    }
    @Mutated public Double3x3 loadRM(double[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Double3x3 loadRM_distinct(double[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[3] = src[offset + 1];
        this.data[6] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[7] = src[offset + 5];
        this.data[2] = src[offset + 6];
        this.data[5] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    private Double3x3 loadRM_aliased(double[] src, int offset) {
        double t0 = src[offset + 0];
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double t4 = src[offset + 4];
        double t5 = src[offset + 5];
        double t6 = src[offset + 6];
        double t7 = src[offset + 7];
        double t8 = src[offset + 8];
        double[] d = this.data;
        d[0] = t0;
        d[3] = t1;
        d[6] = t2;
        d[1] = t3;
        d[4] = t4;
        d[7] = t5;
        d[2] = t6;
        d[5] = t7;
        d[8] = t8;
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double3x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Double3x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double3x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double3x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Double3x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[3];
        dest[offset + 2] = (float) this.data[6];
        dest[offset + 3] = (float) this.data[1];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[7];
        dest[offset + 6] = (float) this.data[2];
        dest[offset + 7] = (float) this.data[5];
        dest[offset + 8] = (float) this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadRM(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[3] = src[offset + 1];
        this.data[6] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[7] = src[offset + 5];
        this.data[2] = src[offset + 6];
        this.data[5] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double3x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public Double3x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double3x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double3x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    public Double3x3 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[_p1] = this.data[3];
        dest[_p1 + 1] = this.data[4];
        dest[_p1 + 2] = this.data[5];
        dest[_p2] = this.data[6];
        dest[_p2 + 1] = this.data[7];
        dest[_p2 + 2] = this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[_p1];
        this.data[4] = src[_p1 + 1];
        this.data[5] = src[_p1 + 2];
        this.data[6] = src[_p2];
        this.data[7] = src[_p2 + 1];
        this.data[8] = src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Double3x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double3x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double3x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    public Double3x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[_p1] = (float) this.data[3];
        dest[_p1 + 1] = (float) this.data[4];
        dest[_p1 + 2] = (float) this.data[5];
        dest[_p2] = (float) this.data[6];
        dest[_p2 + 1] = (float) this.data[7];
        dest[_p2 + 2] = (float) this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[_p1];
        this.data[4] = src[_p1 + 1];
        this.data[5] = src[_p1 + 2];
        this.data[6] = src[_p2];
        this.data[7] = src[_p2 + 1];
        this.data[8] = src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double3x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    public Double3x3 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }
    private double[] storeRM_distinct(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[3];
        dest[offset + 2] = this.data[6];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[4];
        dest[_p1 + 2] = this.data[7];
        dest[_p2] = this.data[2];
        dest[_p2 + 1] = this.data[5];
        dest[_p2 + 2] = this.data[8];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset, int stride) {
        double[] d = this.data;
        double t0 = d[0];
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        double t8 = d[8];
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = t0;
        dest[offset + 1] = t3;
        dest[offset + 2] = t6;
        dest[_p1] = t1;
        dest[_p1 + 1] = t4;
        dest[_p1 + 2] = t7;
        dest[_p2] = t2;
        dest[_p2 + 1] = t5;
        dest[_p2 + 2] = t8;
        return dest;
    }
    @Mutated public Double3x3 loadRM(double[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Double3x3 loadRM_distinct(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[3] = src[offset + 1];
        this.data[6] = src[offset + 2];
        this.data[1] = src[_p1];
        this.data[4] = src[_p1 + 1];
        this.data[7] = src[_p1 + 2];
        this.data[2] = src[_p2];
        this.data[5] = src[_p2 + 1];
        this.data[8] = src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    private Double3x3 loadRM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        double t0 = src[offset];
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[_p1];
        double t4 = src[_p1 + 1];
        double t5 = src[_p1 + 2];
        double t6 = src[_p2];
        double t7 = src[_p2 + 1];
        double t8 = src[_p2 + 2];
        double[] d = this.data;
        d[0] = t0;
        d[3] = t1;
        d[6] = t2;
        d[1] = t3;
        d[4] = t4;
        d[7] = t5;
        d[2] = t6;
        d[5] = t7;
        d[8] = t8;
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Double3x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double3x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double3x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    public Double3x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[3];
        dest[offset + 2] = (float) this.data[6];
        dest[_p1] = (float) this.data[1];
        dest[_p1 + 1] = (float) this.data[4];
        dest[_p1 + 2] = (float) this.data[7];
        dest[_p2] = (float) this.data[2];
        dest[_p2 + 1] = (float) this.data[5];
        dest[_p2 + 2] = (float) this.data[8];
        return dest;
    }
    public @Mutated Double3x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[3] = src[offset + 1];
        this.data[6] = src[offset + 2];
        this.data[1] = src[_p1];
        this.data[4] = src[_p1 + 1];
        this.data[7] = src[_p1 + 2];
        this.data[2] = src[_p2];
        this.data[5] = src[_p2 + 1];
        this.data[8] = src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double3x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    public Double3x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[5];
        dest[offset + 7] = 0.0;
        dest[offset + 8] = this.data[6];
        dest[offset + 9] = this.data[7];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0;
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }
    public DoubleBuffer storeCM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public Double3x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) 0.0;
        dest[offset + 4] = (float) this.data[3];
        dest[offset + 5] = (float) this.data[4];
        dest[offset + 6] = (float) this.data[5];
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) this.data[6];
        dest[offset + 9] = (float) this.data[7];
        dest[offset + 10] = (float) this.data[8];
        dest[offset + 11] = (float) 0.0;
        dest[offset + 12] = (float) 0.0;
        dest[offset + 13] = (float) 0.0;
        dest[offset + 14] = (float) 0.0;
        dest[offset + 15] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeCM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }
    public Double3x3 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }
    public MemorySegment storeCM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Float(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[3];
        dest[offset + 2] = this.data[6];
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[7];
        dest[offset + 7] = 0.0;
        dest[offset + 8] = this.data[2];
        dest[offset + 9] = this.data[5];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0;
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }
    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public Double3x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[3];
        dest[offset + 2] = (float) this.data[6];
        dest[offset + 3] = (float) 0.0;
        dest[offset + 4] = (float) this.data[1];
        dest[offset + 5] = (float) this.data[4];
        dest[offset + 6] = (float) this.data[7];
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) this.data[2];
        dest[offset + 9] = (float) this.data[5];
        dest[offset + 10] = (float) this.data[8];
        dest[offset + 11] = (float) 0.0;
        dest[offset + 12] = (float) 0.0;
        dest[offset + 13] = (float) 0.0;
        dest[offset + 14] = (float) 0.0;
        dest[offset + 15] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeRM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }
    public Double3x3 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }
    public MemorySegment storeRM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Float(this, offset, dest);
    }

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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
