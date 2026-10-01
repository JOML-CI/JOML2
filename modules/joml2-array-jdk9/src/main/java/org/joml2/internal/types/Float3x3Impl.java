// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float3x3} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float3x3Impl implements Float3x3 {

    public float[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float3x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3x3BbOpsUnsafe()
                        : new Float3x3BbOpsApi();
        static final Float3x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3x3RawOpsUnsafe()
                        : new Float3x3RawOpsApi();
    }

    public Float3x3Impl() {
        data = new float[9];
        data[0] = 1;
        data[4] = 1;
        data[8] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float3x3Impl(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        float[] dd = this.data = new float[9];
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

    public Float3x3Impl(Float3x3R src) {
        Float3x3Impl s = (Float3x3Impl) src;
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
    private Float3 getColumn_identity(int col, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; _idxSw2 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; _idxSw2 = 0.0f; break;
            case 2: _idxSw0 = 0.0f; _idxSw1 = 0.0f; _idxSw2 = 1.0f; break;
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
    private Float3 getColumn_translation(int col, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw3 = 1.0f; _idxSw4 = 0.0f; _idxSw5 = 0.0f; break;
            case 1: _idxSw3 = 0.0f; _idxSw4 = 1.0f; _idxSw5 = 0.0f; break;
            case 2: _idxSw3 = sd[6]; _idxSw4 = sd[7]; _idxSw5 = 1.0f; break;
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
    private Float3 getColumn_general(int col, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw6;
        float _idxSw7;
        float _idxSw8;
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
    public Float3 getColumn(int col, @Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getColumn_translation(col, dest);
        return getColumn_general(col, dest);
    }


    /**
     * Get the column at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Double3 getColumn(int col, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _idxSw9;
        float _idxSw10;
        float _idxSw11;
        switch (col) {
            case 0: _idxSw9 = sd[0]; _idxSw10 = sd[1]; _idxSw11 = sd[2]; break;
            case 1: _idxSw9 = sd[3]; _idxSw10 = sd[4]; _idxSw11 = sd[5]; break;
            case 2: _idxSw9 = sd[6]; _idxSw10 = sd[7]; _idxSw11 = sd[8]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw9;
        dd[1] = _idxSw10;
        dd[2] = _idxSw11;
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_translation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = Math.fma(sd[7], sd[7], 1.0f);
        dd[0] = _t0 < Math.fma(sd[7], sd[7], Math.fma(sd[6], sd[6], 1.0f)) * 1.0E-7f ? 0.0f : (float) Math.atan2(-sd[7], 1.0f);
        dd[1] = (float) Math.atan2(sd[6], (float) Math.sqrt(_t0));
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[7], sd[7], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[6], sd[6], _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(sd[5], sd[4]);
            dd[2] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[7], sd[8]);
            dd[2] = (float) Math.atan2(-sd[3], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(sd[6], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_translation(dest);
        return getEulerAnglesXYZ_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[7], sd[7], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[6], sd[6], _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(sd[5], sd[4]);
            dd[2] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[7], sd[8]);
            dd[2] = (float) Math.atan2(-sd[3], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(sd[6], (float) Math.sqrt(_t1));
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Float3 getEulerAnglesXZY_translation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = (float) Math.atan2(sd[6], 1.0f);
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Float3 getEulerAnglesXZY_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[4], sd[4], sd[5] * sd[5]);
        if (_t1 < Math.fma(sd[3], sd[3], _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(-sd[7], sd[8]);
            float _buf1 = 0.0f;
            dd[0] = _buf0;
            dd[1] = _buf1;
        } else {
            float _buf0 = (float) Math.atan2(sd[5], sd[4]);
            float _buf1 = (float) Math.atan2(sd[6], sd[0]);
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        dd[2] = (float) Math.atan2(-sd[3], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXZY_translation(dest);
        return getEulerAnglesXZY_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[4], sd[4], sd[5] * sd[5]);
        if (_t1 < Math.fma(sd[3], sd[3], _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(-sd[7], sd[8]);
            float _buf1 = 0.0f;
            dd[0] = _buf0;
            dd[1] = _buf1;
        } else {
            float _buf0 = (float) Math.atan2(sd[5], sd[4]);
            float _buf1 = (float) Math.atan2(sd[6], sd[0]);
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        dd[2] = (float) Math.atan2(-sd[3], (float) Math.sqrt(_t1));
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_translation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = Math.fma(sd[6], sd[6], 1.0f);
        dd[0] = (float) Math.atan2(-sd[7], (float) Math.sqrt(_t0));
        dd[1] = _t0 < Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0f)) * 1.0E-7f ? 0.0f : (float) Math.atan2(sd[6], 1.0f);
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[6], sd[6], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[7], sd[7], _t1) * 1.0E-7f) {
            dd[1] = (float) Math.atan2(-sd[2], sd[0]);
            dd[2] = 0.0f;
        } else {
            dd[1] = (float) Math.atan2(sd[6], sd[8]);
            dd[2] = (float) Math.atan2(sd[1], sd[4]);
        }
        dd[0] = (float) Math.atan2(-sd[7], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYXZ_translation(dest);
        return getEulerAnglesYXZ_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[6], sd[6], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[7], sd[7], _t1) * 1.0E-7f) {
            dd[1] = (float) Math.atan2(-sd[2], sd[0]);
            dd[2] = 0.0f;
        } else {
            dd[1] = (float) Math.atan2(sd[6], sd[8]);
            dd[2] = (float) Math.atan2(sd[1], sd[4]);
        }
        dd[0] = (float) Math.atan2(-sd[7], (float) Math.sqrt(_t1));
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_translation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = Math.fma(sd[7], sd[7], 1.0f);
        if (_t0 < _t0 * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[1] = (float) Math.atan2(sd[6], 1.0f);
        } else {
            dd[0] = (float) Math.atan2(-sd[7], 1.0f);
            dd[1] = 0.0f;
        }
        dd[2] = (float) Math.atan2(0.0f, (float) Math.sqrt(_t0));
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[4], sd[4], sd[7] * sd[7]);
        if (_t1 < Math.fma(sd[1], sd[1], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[1] = (float) Math.atan2(sd[6], sd[8]);
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[7], sd[4]);
            dd[1] = (float) Math.atan2(-sd[2], sd[0]);
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(sd[1], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYZX_translation(dest);
        return getEulerAnglesYZX_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[4], sd[4], sd[7] * sd[7]);
        if (_t1 < Math.fma(sd[1], sd[1], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[1] = (float) Math.atan2(sd[6], sd[8]);
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[7], sd[4]);
            dd[1] = (float) Math.atan2(-sd[2], sd[0]);
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(sd[1], (float) Math.sqrt(_t1));
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_orthogonal(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[3], sd[3], sd[4] * sd[4]);
        float _buf0 = 0.0f;
        float _buf1 = 0.0f;
        dd[2] = _t1 < _t1 * 1.0E-7f ? (float) Math.atan2(sd[1], sd[0]) : (float) Math.atan2(-sd[3], sd[4]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[3], sd[3], sd[4] * sd[4]);
        if (_t1 < Math.fma(sd[5], sd[5], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(sd[1], sd[0]);
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[2], sd[8]);
            dd[2] = (float) Math.atan2(-sd[3], sd[4]);
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(sd[5], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getEulerAnglesZXY_orthogonal(dest);
        return getEulerAnglesZXY_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[3], sd[3], sd[4] * sd[4]);
        if (_t1 < Math.fma(sd[5], sd[5], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(sd[1], sd[0]);
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-sd[2], sd[8]);
            dd[2] = (float) Math.atan2(-sd[3], sd[4]);
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(sd[5], (float) Math.sqrt(_t1));
        return dest;
    }



    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Float3 getEulerAnglesZYX_orthogonal(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = 0.0f;
        dd[1] = 0.0f;
        dd[2] = (float) Math.atan2(sd[1], sd[0]);
        dd[0] = _buf0;
        return dest;
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Float3 getEulerAnglesZYX_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = Math.fma(sd[5], sd[5], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[2], sd[2], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(-sd[3], sd[4]);
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(sd[5], sd[8]);
            dd[2] = (float) Math.atan2(sd[1], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(-sd[2], (float) Math.sqrt(_t1));
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
     * {@code float} resolution over its whole range, down to 0.
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
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getEulerAnglesZYX_orthogonal(dest);
        return getEulerAnglesZYX_general(dest);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = Math.fma(sd[5], sd[5], sd[8] * sd[8]);
        if (_t1 < Math.fma(sd[2], sd[2], _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(-sd[3], sd[4]);
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(sd[5], sd[8]);
            dd[2] = (float) Math.atan2(sd[1], sd[0]);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(-sd[2], (float) Math.sqrt(_t1));
        return dest;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code getNormalizedRotation},
     * {@code decomposeRotation} and {@code getUnnormalizedRotation}; reached only through them.
     */
    private FloatQuat getNormalizedRotation_identity(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        return dest;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_translation(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t1 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0f));
        float _t2 = (1.0f / (float) Math.sqrt(_t1));
        float _t5 = _t1 != 0.0f ? _t2 : 0.0f;
        float _sp0, _sp1;
        if (_t1 != 0.0f) {
            _sp0 = 0.5f * sd[7] * _t2;
            _sp1 = 0.5f * sd[6] * _t2;
        } else {
            _sp0 = 0.5f * 0.0f;
            _sp1 = 0.5f * 0.0f;
        }
        float _t10 = _t5 < 0.0f ? -1.0f : 1.0f;
        float _t11 = 1.0f + _t10;
        float _t13 = _t11 + _t5;
        float _t17 = 1.0f + _t13;
        float _t18 = 1.0f + (_t10 - (1.0f + _t5));
        float _t19 = 1.0f + (_t5 - _t11);
        float _t20 = 1.0f + (1.0f - (_t10 + _t5));
        return getNormalizedRotation_translation_se5b73cd5_1(dest, dd, _t5, _sp0, _sp1, _t10, _t13, _t17, _t18, _t19, _t20, (1.0f / (float) Math.sqrt(_t17)), (1.0f / (float) Math.sqrt(_t19)), (1.0f / (float) Math.sqrt(_t18)), (1.0f / (float) Math.sqrt(_t20)));
    }

    /** Piece 2 of {@code getNormalizedRotation_translation}, split to fit the inline budget; reached only through it. */
    private FloatQuat getNormalizedRotation_translation_se5b73cd5_1(FloatQuat dest, float[] dd, float _t5, float _sp0, float _sp1, float _t10, float _t13, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        if (_t13 > 0.0f) {
            dd[0] = -(_sp0 * _t21);
            dd[1] = _sp1 * _t21;
            dd[2] = 0.0f;
            dd[3] = 0.5f * (float) Math.sqrt(_t17);
        } else {
            if (_t10 > Math.max(1.0f, _t5)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t18);
                dd[1] = 0.0f;
                dd[2] = _sp1 * _t23;
                dd[3] = -(_sp0 * _t23);
            } else {
                if (1.0f > _t5) {
                    dd[0] = 0.0f;
                    dd[1] = 0.5f * (float) Math.sqrt(_t20);
                    dd[2] = _sp0 * _t24;
                    dd[3] = _sp1 * _t24;
                } else {
                    dd[0] = _sp1 * _t22;
                    dd[1] = _sp0 * _t22;
                    dd[2] = 0.5f * (float) Math.sqrt(_t19);
                    dd[3] = 0.0f;
                }
            }
        }
        return dest;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_general(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t6 = Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4]));
        float _t7 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        float _t8 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t9 = (1.0f / (float) Math.sqrt(_t6));
        float _t10 = (1.0f / (float) Math.sqrt(_t7));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = sd[3] * _t9;
            _t23 = sd[4] * _t9;
            _t27 = sd[5] * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = sd[7] * _t10;
            _t24 = sd[6] * _t10;
            _t26 = sd[8] * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        return getNormalizedRotation_general_sf5bcfc92_1(dest, sd, dd, _t8, (1.0f / (float) Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat getNormalizedRotation_general_sf5bcfc92_1(FloatQuat dest, float[] sd, float[] dd, float _t8, float _t11, float _t21, float _t23, float _t27, float _t22, float _t24, float _t26) {
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = sd[2] * _t11;
            _t28 = sd[0] * _t11;
            _t29 = sd[1] * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
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
        return getNormalizedRotation_general_sf5bcfc92_2(dest, dd, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) Math.sqrt(_t62)), 0.5f * (1.0f / (float) Math.sqrt(_t64)), 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat getNormalizedRotation_general_sf5bcfc92_2(FloatQuat dest, float[] dd, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            dd[0] = _sp0 * _t36;
            dd[1] = _sp0 * _t56;
            dd[2] = _sp0 * _t57;
            dd[3] = 0.5f * (float) Math.sqrt(_t62);
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t63);
                dd[1] = _sp3 * _t53;
                dd[2] = _sp3 * _t55;
                dd[3] = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    dd[0] = _sp1 * _t53;
                    dd[1] = 0.5f * (float) Math.sqrt(_t64);
                    dd[2] = _sp1 * _t39;
                    dd[3] = _sp1 * _t56;
                } else {
                    dd[0] = _sp2 * _t55;
                    dd[1] = _sp2 * _t39;
                    dd[2] = 0.5f * (float) Math.sqrt(_t65);
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat getNormalizedRotation(@Mutated FloatQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_translation(dest);
        return getNormalizedRotation_general(dest);
    }


    /**
     * Extract the rotation of this matrix as a quaternion, column-normalizing the linear block
     * first to strip scale (skew is not removed: a sheared block yields a quaternion that is not
     * unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getNormalizedRotation(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t6 = Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4]));
        float _t7 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        float _t8 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t9 = (1.0f / (float) Math.sqrt(_t6));
        float _t10 = (1.0f / (float) Math.sqrt(_t7));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = sd[3] * _t9;
            _t23 = sd[4] * _t9;
            _t27 = sd[5] * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = sd[7] * _t10;
            _t24 = sd[6] * _t10;
            _t26 = sd[8] * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        return getNormalizedRotation_s11850cc8_1(dest, sd, dd, _t8, (1.0f / (float) Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_s11850cc8_1(DoubleQuat dest, float[] sd, double[] dd, float _t8, float _t11, float _t21, float _t23, float _t27, float _t22, float _t24, float _t26) {
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = sd[2] * _t11;
            _t28 = sd[0] * _t11;
            _t29 = sd[1] * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
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
        return getNormalizedRotation_s11850cc8_2(dest, dd, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) Math.sqrt(_t62)), 0.5f * (1.0f / (float) Math.sqrt(_t64)), 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_s11850cc8_2(DoubleQuat dest, double[] dd, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            dd[0] = _sp0 * _t36;
            dd[1] = _sp0 * _t56;
            dd[2] = _sp0 * _t57;
            dd[3] = 0.5f * (float) Math.sqrt(_t62);
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t63);
                dd[1] = _sp3 * _t53;
                dd[2] = _sp3 * _t55;
                dd[3] = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    dd[0] = _sp1 * _t53;
                    dd[1] = 0.5f * (float) Math.sqrt(_t64);
                    dd[2] = _sp1 * _t39;
                    dd[3] = _sp1 * _t56;
                } else {
                    dd[0] = _sp2 * _t55;
                    dd[1] = _sp2 * _t39;
                    dd[2] = 0.5f * (float) Math.sqrt(_t65);
                    dd[3] = _sp2 * _t57;
                }
            }
        }
        return dest;
    }



    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_translation(int row, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw3 = 1.0f; _idxSw4 = 0.0f; _idxSw5 = sd[6]; break;
            case 1: _idxSw3 = 0.0f; _idxSw4 = 1.0f; _idxSw5 = sd[7]; break;
            case 2: _idxSw3 = 0.0f; _idxSw4 = 0.0f; _idxSw5 = 1.0f; break;
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
    private Float3 getRow_general(int row, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw6;
        float _idxSw7;
        float _idxSw8;
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
    public Float3 getRow(int row, @Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getRow_translation(row, dest);
        return getRow_general(row, dest);
    }


    /**
     * Get the row at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    public Double3 getRow(int row, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _idxSw9;
        float _idxSw10;
        float _idxSw11;
        switch (row) {
            case 0: _idxSw9 = sd[0]; _idxSw10 = sd[3]; _idxSw11 = sd[6]; break;
            case 1: _idxSw9 = sd[1]; _idxSw10 = sd[4]; _idxSw11 = sd[7]; break;
            case 2: _idxSw9 = sd[2]; _idxSw10 = sd[5]; _idxSw11 = sd[8]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw9;
        dd[1] = _idxSw10;
        dd[2] = _idxSw11;
        return dest;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getScale} and {@code decomposeScale}; reached only through
     * them.
     */
    private Float3 getScale_identity(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 1.0f;
        dd[2] = 1.0f;
        return dest;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_translation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 1.0f;
        dd[2] = (float) Math.sqrt(Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0f)));
        return dest;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = (float) Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        dd[1] = (float) Math.sqrt(Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        dd[2] = (float) Math.sqrt(Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7])));
        return dest;
    }


    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored) and store the result in {@code dest}.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getScale(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getScale_identity(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getScale_translation(dest);
        return getScale_general(dest);
    }


    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored) and store the result in {@code dest}.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getScale(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = (float) Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        dd[1] = (float) Math.sqrt(Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        dd[2] = (float) Math.sqrt(Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7])));
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_identity(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_general(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
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
    public Float2 getTranslation(@Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getTranslation_identity(dest);
        return getTranslation_general(dest);
    }


    /**
     * Get the translation of this matrix, read from its last column as {@code (m02, m12)} (the 2D
     * homogeneous convention) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 getTranslation(@Mutated Double2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        return dest;
    }



    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_translation(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = -(0.25f * sd[7]);
        dd[1] = 0.25f * sd[6];
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        return dest;
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_orthogonal(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t3 = sd[0] + sd[4];
        float _t6 = 1.0f + _t3;
        float _t10 = 1.0f + _t6;
        float _t11 = 1.0f + (sd[0] - (1.0f + sd[4]));
        float _t12 = 1.0f + (sd[4] - (1.0f + sd[0]));
        float _t13 = 1.0f + (1.0f - _t3);
        return getUnnormalizedRotation_orthogonal_s9ec0f65e_1(dest, sd, dd, 0.5f * sd[6], 0.5f * sd[7], 0.5f * (sd[3] + sd[1]), 0.5f * (sd[1] - sd[3]), _t6, _t10, _t11, _t12, _t13, (1.0f / (float) Math.sqrt(_t10)), (1.0f / (float) Math.sqrt(_t12)), (1.0f / (float) Math.sqrt(_t13)), (1.0f / (float) Math.sqrt(_t11)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_orthogonal}, split to fit the inline budget; reached only through it. */
    private FloatQuat getUnnormalizedRotation_orthogonal_s9ec0f65e_1(FloatQuat dest, float[] sd, float[] dd, float _sp1, float _sp0, float _sp2, float _sp3, float _t6, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17) {
        if (_t6 > 0.0f) {
            float _buf0 = -(_sp0 * _t14);
            dd[1] = _sp1 * _t14;
            dd[2] = _sp3 * _t14;
            dd[3] = 0.5f * (float) Math.sqrt(_t10);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], 1.0f)) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t11);
                dd[1] = _sp2 * _t17;
                dd[2] = _sp1 * _t17;
                dd[3] = -(_sp0 * _t17);
                dd[0] = _buf0;
            } else {
                if (sd[4] > 1.0f) {
                    float _buf0 = _sp2 * _t15;
                    dd[1] = 0.5f * (float) Math.sqrt(_t12);
                    dd[2] = _sp0 * _t15;
                    dd[3] = _sp1 * _t15;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp1 * _t16;
                    dd[1] = _sp0 * _t16;
                    dd[2] = 0.5f * (float) Math.sqrt(_t13);
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
    private FloatQuat getUnnormalizedRotation_general(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = sd[0] + sd[4];
        float _t10 = sd[8] + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (sd[0] - (sd[4] + sd[8]));
        float _t16 = 1.0f + (sd[4] - (sd[0] + sd[8]));
        float _t17 = 1.0f + (sd[8] - _t0);
        return getUnnormalizedRotation_general_s9f8be771_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat getUnnormalizedRotation_general_s9f8be771_1(FloatQuat dest, float[] sd, float[] dd, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            float _buf0 = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    float _buf0 = _sp1 * _t4;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
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
    public FloatQuat getUnnormalizedRotation(@Mutated FloatQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getUnnormalizedRotation_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getUnnormalizedRotation_orthogonal(dest);
        return getUnnormalizedRotation_general(dest);
    }


    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getUnnormalizedRotation(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = sd[0] + sd[4];
        float _t10 = sd[8] + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (sd[0] - (sd[4] + sd[8]));
        float _t16 = 1.0f + (sd[4] - (sd[0] + sd[8]));
        float _t17 = 1.0f + (sd[8] - _t0);
        return getUnnormalizedRotation_sfab0d139_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_sfab0d139_1(DoubleQuat dest, float[] sd, double[] dd, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            float _buf0 = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    float _buf0 = _sp1 * _t4;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                    dd[0] = _buf0;
                }
            }
        }
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor}, {@code invert}, {@code normal} and
     * {@code transpose}; reached only through them.
     */
    private Float3x3 cofactor_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }



    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float3x3 cofactor_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = -sd[6];
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = -sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Float3x3 cofactor_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[2] = -sd[6];
        dd[5] = -sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float3x3 cofactor_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[2] = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[3] = -sd[1];
        float _buf1 = sd[4];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Float3x3 cofactor_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[2] = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[3] = -sd[1];
        float _buf1 = sd[4];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[4];
        float _buf1 = -sd[3];
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4]));
        float _buf2 = -sd[1];
        float _buf3 = sd[0];
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7]));
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _buf1 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8]));
        float _buf2 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4]));
        float _buf3 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8]));
        float _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2]));
        float _buf5 = Math.fma(sd[6], sd[1], -(sd[0] * sd[7]));
        dd[6] = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        dd[7] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5]));
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 cofactor(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 cofactor() {
        if (Joml.RETURN_NEW) return cofactor(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            ((Float3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine(this);
        return cofactor_general(this);
    }


    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 cofactor(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _buf1 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8]));
        float _buf2 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4]));
        float _buf3 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8]));
        float _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2]));
        float _buf5 = Math.fma(sd[6], sd[1], -(sd[0] * sd[7]));
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
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    public float determinant() {
        float[] sd = this.data;
        return Math.fma(sd[6], Math.fma(sd[1], sd[5], -(sd[4] * sd[2])), Math.fma(sd[0], Math.fma(sd[4], sd[8], -(sd[7] * sd[5])), -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public float frobeniusNorm() {
        float[] sd = this.data;
        return (float) Math.sqrt(Math.fma(sd[0], sd[0], sd[3] * sd[3]) + Math.fma(sd[6], sd[6], sd[1] * sd[1]) + (Math.fma(sd[4], sd[4], sd[7] * sd[7]) + Math.fma(sd[2], sd[2], Math.fma(sd[5], sd[5], sd[8] * sd[8]))));
    }




    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = -sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[1];
        float _buf1 = sd[4];
        dd[5] = 0.0f;
        float _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = -sd[1];
        dd[3] = sd[1];
        float _buf1 = sd[4];
        float _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[4] * _t3_inv;
        float _buf1 = -(sd[1] * _t3_inv);
        dd[2] = 0.0f;
        float _buf2 = -(sd[3] * _t3_inv);
        float _buf3 = sd[0] * _t3_inv;
        dd[5] = 0.0f;
        float _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[4] * _t3_inv;
        float _buf1 = -(sd[1] * _t3_inv);
        float _buf2 = -(sd[3] * _t3_inv);
        float _buf3 = sd[0] * _t3_inv;
        float _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invert_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        float _buf1 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv;
        float _buf3 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv;
        float _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        return invert_general_s6ea7f8ca_1(dest, sd, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4, Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv);
    }

    /** Piece 2 of {@code invert_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 invert_general_s6ea7f8ca_1(Float3x3 dest, float[] sd, float[] dd, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[6] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 invert(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 invert() {
        if (Joml.RETURN_NEW) return invert(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            ((Float3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine_self(this);
        return invert_general(this);
    }


    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 invert(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invert_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        float _buf1 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv;
        float _buf3 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv;
        float _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        return invert_s26a25e_1(dest, sd, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4, Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv);
    }

    /** Piece 2 of {@code invert}, split to fit the inline budget; reached only through it. */
    private Double3x3 invert_s26a25e_1(Double3x3 dest, float[] sd, double[] dd, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
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
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_orthogonal_affine(@Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[0], sd[3], sd[6]);
        float _t8 = sd[4] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[3] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[7] * _t0;
        float _t13 = sd[6] * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0f;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }





    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(1.0f, 0.0f, sd[6]);
        float _t1 = unitScale(0.0f, 1.0f, sd[7]);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0f;
        dd[6] = -(sd[6] * _t0 * _t2_inv);
        dd[7] = -(sd[7] * _t1 * _t3_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[2], sd[5], sd[8]);
        float _t2 = unitScale(sd[0], sd[3], sd[6]);
        float _t12 = sd[4] * _t0;
        float _t13 = sd[8] * _t1;
        float _t14 = sd[7] * _t0;
        float _t15 = sd[5] * _t1;
        float _t16 = sd[1] * _t0;
        float _t17 = sd[2] * _t1;
        float _t18 = sd[6] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[2] = _t28 * _sp0;
        return invert_degenerate_general_sdcbb0e0f_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t1 * _t33_inv, _t0 * _t33_inv);
    }

    /**
     * Piece 2 of {@code invert_degenerate_general}, split to fit the inline budget. Shared by the
     * identical private paths of {@code invert} and {@code invertProduct}; reached only through
     * them.
     */
    private Float3x3 invert_degenerate_general_sdcbb0e0f_1(Float3x3 dest, float[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _sp2, float _sp1) {
        dd[3] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[6] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[7] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate(@Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_orthogonal_affine(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return invert_degenerate_general(dest);
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[2], sd[5], sd[8]);
        float _t2 = unitScale(sd[0], sd[3], sd[6]);
        float _t12 = sd[4] * _t0;
        float _t13 = sd[8] * _t1;
        float _t14 = sd[7] * _t0;
        float _t15 = sd[5] * _t1;
        float _t16 = sd[1] * _t0;
        float _t17 = sd[2] * _t1;
        float _t18 = sd[6] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[2] = _t28 * _sp0;
        return invert_degenerate_s8723a0a5_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t1 * _t33_inv, _t0 * _t33_inv);
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invert_degenerate_s8723a0a5_1(Double3x3 dest, double[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _sp2, float _sp1) {
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
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        return invertProduct_general_se2d7dc11_1(other, dest, sd, otherData, dd, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[8], sd[8], Math.fma(otherData[6], sd[2], otherData[7] * sd[5])), Math.fma(otherData[5], sd[8], Math.fma(otherData[3], sd[2], otherData[4] * sd[5])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[2], sd[8], Math.fma(otherData[0], sd[2], otherData[1] * sd[5])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_se2d7dc11_1(Float3x3R other, Float3x3 dest, float[] sd, float[] otherData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3]));
        float _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t40_inv = 1.0f / _t40;
        dd[0] = _t33 * _t40_inv;
        dd[1] = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        dd[2] = _t34 * _t40_inv;
        dd[3] = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        dd[4] = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        dd[5] = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
        dd[6] = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        dd[7] = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        dd[8] = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(otherData[4], otherData[8], -(otherData[7] * otherData[5]));
        float _t7 = Math.fma(otherData[1], otherData[5], -(otherData[4] * otherData[2]));
        float _t13 = Math.fma(otherData[6], _t7, Math.fma(otherData[0], _t6, -(otherData[3] * Math.fma(otherData[1], otherData[8], -(otherData[7] * otherData[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t13_inv = 1.0f / _t13;
        float _buf1 = Math.fma(otherData[7], otherData[2], -(otherData[1] * otherData[8])) * _t13_inv;
        float _buf3 = Math.fma(otherData[6], otherData[5], -(otherData[3] * otherData[8])) * _t13_inv;
        float _buf4 = Math.fma(otherData[0], otherData[8], -(otherData[6] * otherData[2])) * _t13_inv;
        dd[5] = Math.fma(otherData[3], otherData[2], -(otherData[0] * otherData[5])) * _t13_inv;
        return invertProduct_identity_s4f22b811_1(other, dest, otherData, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4);
    }

    /** Piece 2 of {@code invertProduct_identity}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_identity_s4f22b811_1(Float3x3R other, Float3x3 dest, float[] otherData, float[] dd, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(otherData[3], otherData[7], -(otherData[6] * otherData[4])) * _t13_inv;
        dd[7] = Math.fma(otherData[6], otherData[1], -(otherData[0] * otherData[7])) * _t13_inv;
        dd[8] = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[6] = _buf5;
        ((Float3x3Impl) dest).properties = ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = Math.fma(otherData[5], sd[7], otherData[4]);
        float _t1 = Math.fma(otherData[8], sd[7], otherData[7]);
        float _t2 = Math.fma(otherData[8], sd[6], otherData[6]);
        float _t3 = Math.fma(otherData[2], sd[7], otherData[1]);
        float _t4 = Math.fma(otherData[2], sd[6], otherData[0]);
        float _t5 = Math.fma(otherData[5], sd[6], otherData[3]);
        float _t12 = Math.fma(otherData[8], _t0, -(otherData[5] * _t1));
        float _t13 = Math.fma(otherData[5], _t3, -(otherData[2] * _t0));
        float _t19 = Math.fma(_t2, _t13, Math.fma(_t4, _t12, -(_t5 * Math.fma(otherData[8], _t3, -(otherData[2] * _t1)))));
        if (!(Math.abs(_t19) > 1.1754944E-38f && Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t19_inv = 1.0f / _t19;
        dd[0] = _t12 * _t19_inv;
        dd[1] = Math.fma(otherData[2], _t1, -(otherData[8] * _t3)) * _t19_inv;
        return invertProduct_translation_sabdb163a_1(other, dest, otherData, dd, _t0, _t1, _t2, _t3, _t4, _t5, _t13, _t19_inv);
    }

    /** Piece 2 of {@code invertProduct_translation}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_translation_sabdb163a_1(Float3x3R other, Float3x3 dest, float[] otherData, float[] dd, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t13, float _t19_inv) {
        float _buf0 = _t13 * _t19_inv;
        dd[3] = Math.fma(otherData[5], _t2, -(otherData[8] * _t5)) * _t19_inv;
        dd[4] = Math.fma(otherData[8], _t4, -(otherData[2] * _t2)) * _t19_inv;
        dd[5] = Math.fma(otherData[2], _t5, -(otherData[5] * _t4)) * _t19_inv;
        dd[6] = Math.fma(_t5, _t1, -(_t2 * _t0)) * _t19_inv;
        dd[7] = Math.fma(_t2, _t3, -(_t4 * _t1)) * _t19_inv;
        dd[8] = Math.fma(_t4, _t0, -(_t5 * _t3)) * _t19_inv;
        dd[2] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t12 = Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4]));
        float _t13 = Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4]));
        float _t15 = Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4]));
        return invertProduct_orthogonal_s7cd20356_1(other, dest, _props, otherData, dd, _t12, _t13, Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])), _t15, Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])), Math.fma(otherData[8], _t12, -(otherData[5] * _t13)), Math.fma(otherData[5], _t15, -(otherData[2] * _t12)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_orthogonal_s7cd20356_1(Float3x3R other, Float3x3 dest, int _props, float[] otherData, float[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t24, float _t25) {
        float _t31 = Math.fma(_t14, _t25, Math.fma(_t16, _t24, -(_t17 * Math.fma(otherData[8], _t15, -(otherData[2] * _t13)))));
        if (!(Math.abs(_t31) > 1.1754944E-38f && Math.abs(_t31) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t31_inv = 1.0f / _t31;
        dd[0] = _t24 * _t31_inv;
        dd[1] = Math.fma(otherData[2], _t13, -(otherData[8] * _t15)) * _t31_inv;
        float _buf0 = _t25 * _t31_inv;
        dd[3] = Math.fma(otherData[5], _t14, -(otherData[8] * _t17)) * _t31_inv;
        dd[4] = Math.fma(otherData[8], _t16, -(otherData[2] * _t14)) * _t31_inv;
        dd[5] = Math.fma(otherData[2], _t17, -(otherData[5] * _t16)) * _t31_inv;
        dd[6] = Math.fma(_t17, _t13, -(_t12 * _t14)) * _t31_inv;
        dd[7] = Math.fma(_t15, _t14, -(_t16 * _t13)) * _t31_inv;
        dd[8] = Math.fma(_t16, _t12, -(_t15 * _t17)) * _t31_inv;
        dd[2] = _buf0;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float3x3 invertProduct_identity_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -otherData[6];
        dd[7] = -otherData[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = otherData[4] * _t3_inv;
        float _buf1 = -(otherData[1] * _t3_inv);
        dd[2] = 0.0f;
        float _buf2 = -(otherData[3] * _t3_inv);
        float _buf3 = otherData[0] * _t3_inv;
        dd[5] = 0.0f;
        float _buf4 = Math.fma(otherData[3], otherData[7], -(otherData[6] * otherData[4])) * _t3_inv;
        dd[7] = Math.fma(otherData[6], otherData[1], -(otherData[0] * otherData[7])) * _t3_inv;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -(otherData[6] + sd[6]);
        dd[7] = -(otherData[7] + sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t1 = otherData[7] + sd[7];
        float _t2 = otherData[6] + sd[6];
        float _t5 = Math.fma(otherData[0], otherData[4], -(otherData[3] * otherData[1]));
        if (!(Math.abs(_t5) > 1.1754944E-38f && Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = otherData[4] * _t5_inv;
        float _buf1 = -(otherData[1] * _t5_inv);
        dd[2] = 0.0f;
        float _buf2 = -(otherData[3] * _t5_inv);
        float _buf3 = otherData[0] * _t5_inv;
        dd[5] = 0.0f;
        dd[6] = Math.fma(otherData[3], _t1, -(otherData[4] * _t2)) * _t5_inv;
        dd[7] = Math.fma(otherData[1], _t2, -(otherData[0] * _t1)) * _t5_inv;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = -sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[1];
        float _buf1 = sd[4];
        dd[5] = 0.0f;
        float _buf2 = Math.fma(-sd[6], sd[4], -(sd[1] * sd[7]));
        dd[7] = Math.fma(sd[6], sd[1], -(sd[4] * sd[7]));
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[4];
        float _buf0 = _t0;
        dd[2] = 0.0f;
        dd[3] = sd[1];
        float _buf1 = sd[4];
        dd[5] = 0.0f;
        float _buf2 = Math.fma(_t0, sd[7], Math.fma(-sd[6], sd[4], -otherData[6]));
        dd[7] = Math.fma(sd[6], sd[1], Math.fma(-sd[4], sd[7], -otherData[7]));
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        float _t7 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        float _t8 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        float _t9 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        return invertProduct_orthogonal_affine_s4d998354_1(other, dest, _props, dd, _t6, _t7, _t8, _t9, Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7])), Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6])), Math.fma(_t7, _t6, -(_t8 * _t9)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal_affine}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_orthogonal_affine_s4d998354_1(Float3x3R other, Float3x3 dest, int _props, float[] dd, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t15) {
        if (!(Math.abs(_t15) > 1.1754944E-38f && Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t15_inv = 1.0f / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = 0.0f;
        dd[3] = -(_t9 * _t15_inv);
        dd[4] = _t7 * _t15_inv;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t10, _t9, -(_t11 * _t6)) * _t15_inv;
        dd[7] = Math.fma(_t11, _t8, -(_t10 * _t7)) * _t15_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[4] * _t3_inv;
        float _buf1 = -(sd[1] * _t3_inv);
        dd[2] = 0.0f;
        float _buf2 = -(sd[3] * _t3_inv);
        float _buf3 = sd[0] * _t3_inv;
        dd[5] = 0.0f;
        float _buf4 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t5 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t6 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        float _t7 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t7) > 1.1754944E-38f && Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _buf0 = sd[4] * _t7_inv;
        float _buf1 = -(sd[1] * _t7_inv);
        dd[2] = 0.0f;
        float _buf2 = -(sd[3] * _t7_inv);
        float _buf3 = sd[0] * _t7_inv;
        dd[5] = 0.0f;
        dd[6] = Math.fma(sd[3], _t5, -(sd[4] * _t6)) * _t7_inv;
        dd[7] = Math.fma(sd[1], _t6, -(sd[0] * _t5)) * _t7_inv;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t13_inv = 1.0f / _t13;
        float _buf1 = Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv;
        float _buf3 = Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv;
        float _buf4 = Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        return invertProduct_general_identity_s1b4eaa0c_1(dest, sd, dd, _t13_inv, _t6 * _t13_inv, _buf1, _t7 * _t13_inv, _buf3, _buf4);
    }

    /** Piece 2 of {@code invertProduct_general_identity}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_identity_s1b4eaa0c_1(Float3x3 dest, float[] sd, float[] dd, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv;
        dd[7] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[6] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t5 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t6 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        float _t7 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t8 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        float _t13 = Math.fma(sd[4], _t6, -(sd[5] * _t7));
        float _t19 = Math.fma(_t8, _t5, Math.fma(sd[0], _t13, -(sd[3] * Math.fma(sd[1], _t6, -(sd[2] * _t7)))));
        if (!(Math.abs(_t19) > 1.1754944E-38f && Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t19_inv = 1.0f / _t19;
        return invertProduct_general_translation_s2daf0e35_1(dest, sd, dd, _t6, _t7, _t8, _t19_inv, _t13 * _t19_inv, Math.fma(sd[2], _t7, -(sd[1] * _t6)) * _t19_inv, _t5 * _t19_inv, Math.fma(sd[5], _t8, -(sd[3] * _t6)) * _t19_inv);
    }

    /** Piece 2 of {@code invertProduct_general_translation}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_translation_s2daf0e35_1(Float3x3 dest, float[] sd, float[] dd, float _t6, float _t7, float _t8, float _t19_inv, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(sd[0], _t6, -(sd[2] * _t8)) * _t19_inv;
        dd[5] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t19_inv;
        dd[6] = Math.fma(sd[3], _t7, -(sd[4] * _t8)) * _t19_inv;
        dd[7] = Math.fma(sd[1], _t8, -(sd[0] * _t7)) * _t19_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t19_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t9 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        float _t10 = Math.fma(otherData[3], sd[2], otherData[4] * sd[5]);
        float _t11 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        float _t12 = Math.fma(otherData[0], sd[2], otherData[1] * sd[5]);
        float _t15 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        float _t16 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        return invertProduct_general_affine_s34a8b39_1(other, dest, dd, _t9, _t10, _t11, _t12, Math.fma(otherData[0], sd[0], otherData[1] * sd[3]), Math.fma(otherData[3], sd[0], otherData[4] * sd[3]), _t15, _t16, Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6])), Math.fma(_t11, _t10, -(_t12 * _t9)), Math.fma(_t15, _t9, -(_t16 * _t10)));
    }

    /** Piece 2 of {@code invertProduct_general_affine}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_affine_s34a8b39_1(Float3x3R other, Float3x3 dest, float[] dd, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t24, float _t25) {
        float _t31 = Math.fma(_t17, _t24, Math.fma(_t13, _t25, -(_t14 * Math.fma(_t15, _t11, -(_t16 * _t12)))));
        if (!(Math.abs(_t31) > 1.1754944E-38f && Math.abs(_t31) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t31_inv = 1.0f / _t31;
        dd[0] = _t25 * _t31_inv;
        dd[1] = Math.fma(_t16, _t12, -(_t15 * _t11)) * _t31_inv;
        dd[2] = _t24 * _t31_inv;
        dd[3] = Math.fma(_t17, _t10, -(_t15 * _t14)) * _t31_inv;
        dd[4] = Math.fma(_t15, _t13, -(_t17 * _t12)) * _t31_inv;
        dd[5] = Math.fma(_t12, _t14, -(_t13 * _t10)) * _t31_inv;
        dd[6] = Math.fma(_t16, _t14, -(_t17 * _t9)) * _t31_inv;
        dd[7] = Math.fma(_t17, _t11, -(_t16 * _t13)) * _t31_inv;
        dd[8] = Math.fma(_t13, _t9, -(_t11 * _t14)) * _t31_inv;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 invertProduct(Float3x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
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
        return invertProduct_s631f56de_1(other, dest, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_s631f56de_1(Float3x3R other, Float3x3 dest, int p, int q) {
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
    @Mutated public Float3x3 invertProduct(Float3x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.float3x3());
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
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
        return invertProduct_s6b2ee216_1(other, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_s6b2ee216_1(Float3x3R other, int p, int q) {
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
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 invertProduct(Float3x3R other, @Mutated Double3x3 dest) {
        return invertProduct(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), dest);
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
    public Float3x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t18 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        float _t19 = Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5]));
        float _t20 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
        float _t21 = Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4]));
        return invertProduct_s60657eeb_1(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest, dd, _t18, _t19, _t20, _t21, Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(_t18, _t19, -(_t20 * _t21)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_s60657eeb_1(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, Float3x3 dest, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t33) {
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest);
        float _t40_inv = 1.0f / _t40;
        dd[0] = _t33 * _t40_inv;
        dd[1] = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        dd[2] = _t34 * _t40_inv;
        dd[3] = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        dd[4] = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        dd[5] = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
        dd[6] = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        dd[7] = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        dd[8] = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t18 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        float _t19 = Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5]));
        float _t20 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
        float _t21 = Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4]));
        return invertProduct_sa09afb2e_1(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest, dd, _t18, _t19, _t20, _t21, Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(_t18, _t19, -(_t20 * _t21)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_sa09afb2e_1(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, Double3x3 dest, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t33) {
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest);
        float _t40_inv = 1.0f / _t40;
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
    private Float3x3 invertProduct_degenerate_general(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        return invertProduct_degenerate_general_sc695afcc_1(dest, sd, otherData, dd, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[8], sd[8], Math.fma(otherData[6], sd[2], otherData[7] * sd[5])), Math.fma(otherData[2], sd[8], Math.fma(otherData[0], sd[2], otherData[1] * sd[5])), Math.fma(otherData[5], sd[8], Math.fma(otherData[3], sd[2], otherData[4] * sd[5])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_sc695afcc_1(Float3x3 dest, float[] sd, float[] otherData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3]));
        float _t27 = unitScale(_t19, _t18, _t20);
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
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        dd[0] = _t54 * _sp0;
        dd[1] = Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0;
        dd[2] = _t55 * _sp0;
        dd[3] = Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1;
        return invertProduct_degenerate_general_sc695afcc_2(dest, dd, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t28 * _t60_inv, _sp1);
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_sc695afcc_2(Float3x3 dest, float[] dd, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _sp2, float _sp1) {
        dd[4] = Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1;
        dd[5] = Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1;
        dd[6] = Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2;
        dd[7] = Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2;
        dd[8] = Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[4], otherData[7]);
        float _t1 = unitScale(otherData[2], otherData[5], otherData[8]);
        float _t2 = unitScale(otherData[0], otherData[3], otherData[6]);
        float _t12 = otherData[4] * _t0;
        float _t13 = otherData[8] * _t1;
        float _t14 = otherData[7] * _t0;
        float _t15 = otherData[5] * _t1;
        float _t16 = otherData[1] * _t0;
        float _t17 = otherData[2] * _t1;
        float _t18 = otherData[6] * _t2;
        float _t19 = otherData[0] * _t2;
        float _t20 = otherData[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        return invertProduct_degenerate_identity_s3140178a_1(other, dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _t0 * _t33_inv, _sp0);
    }

    /** Piece 2 of {@code invertProduct_degenerate_identity}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_identity_s3140178a_1(Float3x3R other, Float3x3 dest, float[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t28, float _sp2, float _sp1, float _sp0) {
        dd[2] = _t28 * _sp0;
        dd[3] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[6] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[7] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(otherData[2], otherData[5], otherData[8]);
        float _t1 = Math.fma(otherData[5], sd[7], otherData[4]);
        float _t2 = Math.fma(otherData[2], sd[7], otherData[1]);
        float _t3 = Math.fma(otherData[8], sd[7], otherData[7]);
        float _t4 = Math.fma(otherData[2], sd[6], otherData[0]);
        float _t5 = Math.fma(otherData[5], sd[6], otherData[3]);
        float _t6 = Math.fma(otherData[8], sd[6], otherData[6]);
        float _t10 = otherData[8] * _t0;
        float _t11 = otherData[5] * _t0;
        float _t12 = otherData[2] * _t0;
        float _t13 = unitScale(_t2, _t1, _t3);
        float _t14 = unitScale(_t4, _t5, _t6);
        float _t21 = _t1 * _t13;
        float _t22 = _t3 * _t13;
        float _t23 = _t2 * _t13;
        return invertProduct_degenerate_translation_s38e152bf_1(other, dest, dd, _t0, _t10, _t11, _t12, _t13, _t14, _t21, _t22, _t23, _t6 * _t14, _t4 * _t14, _t5 * _t14, Math.fma(_t10, _t21, -(_t11 * _t22)), Math.fma(_t11, _t23, -(_t12 * _t21)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_translation_s38e152bf_1(Float3x3R other, Float3x3 dest, float[] dd, float _t0, float _t10, float _t11, float _t12, float _t13, float _t14, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t33, float _t34) {
        float _t39_inv = 1.0f / Math.fma(_t34, _t24, Math.fma(_t33, _t25, -(Math.fma(_t10, _t23, -(_t12 * _t22)) * _t26)));
        float _sp2 = _t0 * _t39_inv;
        float _sp1 = _t13 * _t39_inv;
        float _sp0 = _t14 * _t39_inv;
        dd[0] = _t33 * _sp0;
        dd[1] = Math.fma(_t12, _t22, -(_t10 * _t23)) * _sp0;
        dd[2] = _t34 * _sp0;
        dd[3] = Math.fma(_t11, _t24, -(_t10 * _t26)) * _sp1;
        dd[4] = Math.fma(_t10, _t25, -(_t12 * _t24)) * _sp1;
        dd[5] = Math.fma(_t12, _t26, -(_t11 * _t25)) * _sp1;
        dd[6] = Math.fma(_t26, _t22, -(_t24 * _t21)) * _sp2;
        dd[7] = Math.fma(_t24, _t23, -(_t25 * _t22)) * _sp2;
        dd[8] = Math.fma(_t25, _t21, -(_t26 * _t23)) * _sp2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = unitScale(otherData[2], otherData[5], otherData[8]);
        return invertProduct_degenerate_orthogonal_s78eef339_1(dest, _props, dd, _t6, otherData[8] * _t6, otherData[5] * _t6, otherData[2] * _t6, Math.fma(otherData[5], sd[7], Math.fma(otherData[3], sd[1], otherData[4] * sd[4])), Math.fma(otherData[2], sd[7], Math.fma(otherData[0], sd[1], otherData[1] * sd[4])), Math.fma(otherData[8], sd[7], Math.fma(otherData[6], sd[1], otherData[7] * sd[4])), Math.fma(otherData[2], sd[6], Math.fma(otherData[0], sd[0], otherData[1] * sd[3])), Math.fma(otherData[5], sd[6], Math.fma(otherData[3], sd[0], otherData[4] * sd[3])), Math.fma(otherData[8], sd[6], Math.fma(otherData[6], sd[0], otherData[7] * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_orthogonal_s78eef339_1(Float3x3 dest, int _props, float[] dd, float _t6, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
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
        float _sp2 = _t6 * _t51_inv;
        float _sp1 = _t25 * _t51_inv;
        float _sp0 = _t26 * _t51_inv;
        dd[0] = _t45 * _sp0;
        dd[1] = Math.fma(_t18, _t34, -(_t16 * _t35)) * _sp0;
        dd[2] = _t46 * _sp0;
        dd[3] = Math.fma(_t17, _t36, -(_t16 * _t38)) * _sp1;
        dd[4] = Math.fma(_t16, _t37, -(_t18 * _t36)) * _sp1;
        dd[5] = Math.fma(_t18, _t38, -(_t17 * _t37)) * _sp1;
        dd[6] = Math.fma(_t38, _t34, -(_t33 * _t36)) * _sp2;
        return invertProduct_degenerate_orthogonal_s78eef339_2(dest, _props, dd, _t33, _t34, _t35, _t36, _t37, _t38, _sp2);
    }

    /** Piece 3 of {@code invertProduct_degenerate_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_orthogonal_s78eef339_2(Float3x3 dest, int _props, float[] dd, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _sp2) {
        dd[7] = Math.fma(_t35, _t36, -(_t37 * _t34)) * _sp2;
        dd[8] = Math.fma(_t37, _t33, -(_t35 * _t38)) * _sp2;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(1.0f, 0.0f, otherData[6]);
        float _t1 = unitScale(0.0f, 1.0f, otherData[7]);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0f;
        dd[6] = -(otherData[6] * _t0 * _t2_inv);
        dd[7] = -(otherData[7] * _t1 * _t3_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[4], otherData[7]);
        float _t1 = unitScale(otherData[0], otherData[3], otherData[6]);
        float _t8 = otherData[4] * _t0;
        float _t9 = otherData[0] * _t1;
        float _t10 = otherData[3] * _t1;
        float _t11 = otherData[1] * _t0;
        float _t12 = otherData[7] * _t0;
        float _t13 = otherData[6] * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0f;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(1.0f, 0.0f, sd[6]);
        float _t1 = unitScale(0.0f, 1.0f, sd[7]);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1 * _t3_inv;
        dd[5] = 0.0f;
        dd[6] = -(sd[6] * _t0 * _t2_inv);
        dd[7] = -(sd[7] * _t1 * _t3_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = otherData[6] + sd[6];
        float _t1 = otherData[7] + sd[7];
        float _t2 = unitScale(1.0f, 0.0f, _t0);
        float _t3 = unitScale(0.0f, 1.0f, _t1);
        float _t4_inv = 1.0f / _t2;
        float _t5_inv = 1.0f / _t3;
        dd[0] = _t2 * _t4_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t3 * _t5_inv;
        dd[5] = 0.0f;
        dd[6] = -(_t0 * _t2 * _t4_inv);
        dd[7] = -(_t1 * _t3 * _t5_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = otherData[7] + sd[7];
        float _t1 = otherData[6] + sd[6];
        float _t2 = unitScale(otherData[1], otherData[4], _t0);
        float _t3 = unitScale(otherData[0], otherData[3], _t1);
        float _t8 = otherData[4] * _t2;
        float _t9 = otherData[0] * _t3;
        float _t10 = otherData[3] * _t3;
        float _t11 = otherData[1] * _t2;
        float _t14 = _t0 * _t2;
        float _t15 = _t1 * _t3;
        float _t18_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t2 * _t18_inv;
        float _sp0 = _t3 * _t18_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0f;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t10, _t14, -(_t8 * _t15)) * _t18_inv;
        dd[7] = Math.fma(_t11, _t15, -(_t9 * _t14)) * _t18_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_identity(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[0], sd[3], sd[6]);
        float _t8 = sd[4] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[3] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[7] * _t0;
        float _t13 = sd[6] * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = 0.0f;
        dd[3] = -(_t10 * _sp1);
        dd[4] = _t9 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[7] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t2 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t3 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        float _t4 = unitScale(sd[1], sd[4], _t2);
        float _t5 = unitScale(sd[0], sd[3], _t3);
        float _t10 = sd[4] * _t4;
        float _t11 = sd[0] * _t5;
        float _t12 = sd[3] * _t5;
        float _t13 = sd[1] * _t4;
        float _t16 = _t2 * _t4;
        float _t17 = _t3 * _t5;
        float _t20_inv = 1.0f / Math.fma(_t11, _t10, -(_t12 * _t13));
        float _sp1 = _t4 * _t20_inv;
        float _sp0 = _t5 * _t20_inv;
        dd[0] = _t10 * _sp0;
        dd[1] = -(_t13 * _sp0);
        dd[2] = 0.0f;
        dd[3] = -(_t12 * _sp1);
        dd[4] = _t11 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t12, _t16, -(_t10 * _t17)) * _t20_inv;
        dd[7] = Math.fma(_t13, _t17, -(_t11 * _t16)) * _t20_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        float _t7 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        float _t8 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        float _t9 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        float _t10 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t11 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        float _t12 = unitScale(_t7, _t6, _t10);
        float _t13 = unitScale(_t8, _t9, _t11);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = 0.0f;
        return invertProduct_degenerate_orthogonal_affine_s8e0fded5_1(dest, _props, dd, _t18, _t19, _t20, _t21, _t10 * _t12, _t11 * _t13, _t28_inv, _t12 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate_orthogonal_affine}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_orthogonal_affine_s8e0fded5_1(Float3x3 dest, int _props, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t24, float _t25, float _t28_inv, float _sp1) {
        dd[3] = -(_t21 * _sp1);
        dd[4] = _t19 * _sp1;
        dd[5] = 0.0f;
        dd[6] = Math.fma(_t24, _t21, -(_t25 * _t18)) * _t28_inv;
        dd[7] = Math.fma(_t25, _t20, -(_t24 * _t19)) * _t28_inv;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_identity(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[2], sd[5], sd[8]);
        float _t2 = unitScale(sd[0], sd[3], sd[6]);
        float _t12 = sd[4] * _t0;
        float _t13 = sd[8] * _t1;
        float _t14 = sd[7] * _t0;
        float _t15 = sd[5] * _t1;
        float _t16 = sd[1] * _t0;
        float _t17 = sd[2] * _t1;
        float _t18 = sd[6] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[2] = _t28 * _sp0;
        return invert_degenerate_general_sdcbb0e0f_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t1 * _t33_inv, _t0 * _t33_inv);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t4 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        float _t5 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        float _t6 = unitScale(sd[1], sd[4], _t3);
        float _t7 = unitScale(sd[2], sd[5], _t4);
        float _t8 = unitScale(sd[0], sd[3], _t5);
        float _t15 = sd[4] * _t6;
        float _t16 = sd[5] * _t7;
        float _t17 = sd[1] * _t6;
        float _t18 = sd[2] * _t7;
        float _t24 = _t4 * _t7;
        float _t25 = _t3 * _t6;
        return invertProduct_degenerate_general_translation_s950c5698_1(dest, dd, _t6, _t7, _t8, _t15, _t16, _t17, _t18, sd[0] * _t8, sd[3] * _t8, _t24, _t25, _t5 * _t8, Math.fma(_t17, _t16, -(_t15 * _t18)), Math.fma(_t15, _t24, -(_t16 * _t25)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general_translation}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_translation_s950c5698_1(Float3x3 dest, float[] dd, float _t6, float _t7, float _t8, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t33, float _t34) {
        float _t39_inv = 1.0f / Math.fma(_t33, _t26, Math.fma(_t34, _t19, -(Math.fma(_t17, _t24, -(_t18 * _t25)) * _t20)));
        float _sp2 = _t7 * _t39_inv;
        float _sp1 = _t6 * _t39_inv;
        float _sp0 = _t8 * _t39_inv;
        dd[0] = _t34 * _sp0;
        dd[1] = Math.fma(_t18, _t25, -(_t17 * _t24)) * _sp0;
        dd[2] = _t33 * _sp0;
        dd[3] = Math.fma(_t16, _t26, -(_t20 * _t24)) * _sp1;
        dd[4] = Math.fma(_t19, _t24, -(_t18 * _t26)) * _sp1;
        dd[5] = Math.fma(_t20, _t18, -(_t19 * _t16)) * _sp1;
        dd[6] = Math.fma(_t20, _t25, -(_t15 * _t26)) * _sp2;
        dd[7] = Math.fma(_t17, _t26, -(_t19 * _t25)) * _sp2;
        dd[8] = Math.fma(_t19, _t15, -(_t20 * _t17)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t9 = Math.fma(otherData[0], sd[2], otherData[1] * sd[5]);
        float _t10 = Math.fma(otherData[3], sd[2], otherData[4] * sd[5]);
        float _t11 = Math.fma(otherData[3], sd[1], otherData[4] * sd[4]);
        float _t12 = Math.fma(otherData[0], sd[1], otherData[1] * sd[4]);
        float _t13 = Math.fma(otherData[0], sd[0], otherData[1] * sd[3]);
        float _t14 = Math.fma(otherData[3], sd[0], otherData[4] * sd[3]);
        float _t15 = Math.fma(otherData[6], sd[2], Math.fma(otherData[7], sd[5], sd[8]));
        float _t16 = Math.fma(otherData[6], sd[1], Math.fma(otherData[7], sd[4], sd[7]));
        float _t17 = Math.fma(otherData[6], sd[0], Math.fma(otherData[7], sd[3], sd[6]));
        return invertProduct_degenerate_general_affine_sa46fae96_1(dest, dd, _t9, _t10, _t11, _t12, _t13, _t14, _t15, _t16, _t17, unitScale(_t9, _t10, _t15), unitScale(_t12, _t11, _t16), unitScale(_t13, _t14, _t17));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general_affine}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_affine_sa46fae96_1(Float3x3 dest, float[] dd, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        float _t27 = _t11 * _t19;
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
        float _sp2 = _t18 * _t51_inv;
        float _sp1 = _t19 * _t51_inv;
        float _sp0 = _t20 * _t51_inv;
        dd[0] = _t46 * _sp0;
        dd[1] = Math.fma(_t37, _t30, -(_t36 * _t29)) * _sp0;
        dd[2] = _t45 * _sp0;
        dd[3] = Math.fma(_t38, _t28, -(_t36 * _t32)) * _sp1;
        dd[4] = Math.fma(_t36, _t31, -(_t38 * _t30)) * _sp1;
        dd[5] = Math.fma(_t30, _t32, -(_t31 * _t28)) * _sp1;
        dd[6] = Math.fma(_t37, _t32, -(_t38 * _t27)) * _sp2;
        dd[7] = Math.fma(_t38, _t29, -(_t37 * _t31)) * _sp2;
        dd[8] = Math.fma(_t31, _t27, -(_t29 * _t32)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate(Float3x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
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
        return invertProduct_degenerate_sfbf888bb_1(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_sfbf888bb_1(Float3x3R other, Float3x3 dest, int q) {
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
    private Float3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        return invertProduct_degenerate_s70b971d2_1(dest, dd, Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4])), Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_s70b971d2_1(Float3x3 dest, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        float _t27 = unitScale(_t19, _t18, _t20);
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
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        dd[0] = _t54 * _sp0;
        dd[1] = Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0;
        dd[2] = _t55 * _sp0;
        dd[3] = Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1;
        dd[4] = Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1;
        dd[5] = Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1;
        return invertProduct_degenerate_s70b971d2_2(dest, dd, _t39, _t42, _t43, _t45, _t46, _t47, _t28 * _t60_inv);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_s70b971d2_2(Float3x3 dest, float[] dd, float _t39, float _t42, float _t43, float _t45, float _t46, float _t47, float _sp2) {
        dd[6] = Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2;
        dd[7] = Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2;
        dd[8] = Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        return invertProduct_degenerate_scd592a75_1(dest, dd, Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4])), Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4])), Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4])), Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5])), Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5])), Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5])), Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3])), Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3])), Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3])));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_scd592a75_1(Double3x3 dest, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        float _t27 = unitScale(_t19, _t18, _t20);
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
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        dd[0] = _t54 * _sp0;
        dd[1] = Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0;
        dd[2] = _t55 * _sp0;
        dd[3] = Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1;
        dd[4] = Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1;
        dd[5] = Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1;
        return invertProduct_degenerate_scd592a75_2(dest, dd, _t39, _t42, _t43, _t45, _t46, _t47, _t28 * _t60_inv);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_degenerate_scd592a75_2(Double3x3 dest, double[] dd, float _t39, float _t42, float _t43, float _t45, float _t46, float _t47, float _sp2) {
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
    private Float3x3 normal_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return normal_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[4] * _t3_inv;
        float _buf1 = -(sd[3] * _t3_inv);
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[3] = -(sd[1] * _t3_inv);
        dd[4] = sd[0] * _t3_inv;
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[4], -(sd[3] * sd[1]));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return normal_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[4] * _t3_inv;
        float _buf1 = -(sd[3] * _t3_inv);
        dd[2] = Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t3_inv;
        dd[3] = -(sd[1] * _t3_inv);
        dd[4] = sd[0] * _t3_inv;
        dd[5] = Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t3_inv;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return normal_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        return normal_general_s32f64427_1(dest, sd, dd, _t7, _t13_inv, _t6 * _t13_inv, Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv, Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv, Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv, Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv, Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv);
    }

    /** Piece 2 of {@code normal_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 normal_general_s32f64427_1(Float3x3 dest, float[] sd, float[] dd, float _t7, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
        dd[6] = _t7 * _t13_inv;
        dd[7] = Math.fma(sd[3], sd[2], -(sd[0] * sd[5])) * _t13_inv;
        dd[8] = Math.fma(sd[0], sd[4], -(sd[3] * sd[1])) * _t13_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 normal(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 normal() {
        if (Joml.RETURN_NEW) return normal(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            ((Float3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine_self(this);
        return normal_general(this);
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 normal(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t6 = Math.fma(sd[4], sd[8], -(sd[7] * sd[5]));
        float _t7 = Math.fma(sd[1], sd[5], -(sd[4] * sd[2]));
        float _t13 = Math.fma(sd[6], _t7, Math.fma(sd[0], _t6, -(sd[3] * Math.fma(sd[1], sd[8], -(sd[7] * sd[2])))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return normal_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        return normal_sbfbdaf7d_1(dest, sd, dd, _t7, _t13_inv, _t6 * _t13_inv, Math.fma(sd[6], sd[5], -(sd[3] * sd[8])) * _t13_inv, Math.fma(sd[3], sd[7], -(sd[6] * sd[4])) * _t13_inv, Math.fma(sd[7], sd[2], -(sd[1] * sd[8])) * _t13_inv, Math.fma(sd[0], sd[8], -(sd[6] * sd[2])) * _t13_inv, Math.fma(sd[6], sd[1], -(sd[0] * sd[7])) * _t13_inv);
    }

    /** Piece 2 of {@code normal}, split to fit the inline budget; reached only through it. */
    private Double3x3 normal_sbfbdaf7d_1(Double3x3 dest, float[] sd, double[] dd, float _t7, float _t13_inv, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
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
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(1.0f, 0.0f, sd[6]);
        float _t1 = unitScale(0.0f, 1.0f, sd[7]);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[6] * _t0 * _t2_inv);
        dd[3] = 0.0f;
        dd[4] = _t1 * _t3_inv;
        dd[5] = -(sd[7] * _t1 * _t3_inv);
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[0], sd[3], sd[6]);
        float _t8 = sd[4] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[3] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[7] * _t0;
        float _t13 = sd[6] * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t10 * _sp1);
        dd[2] = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        dd[3] = -(_t11 * _sp0);
        dd[4] = _t9 * _sp1;
        dd[5] = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[2], sd[5], sd[8]);
        float _t2 = unitScale(sd[0], sd[3], sd[6]);
        float _t12 = sd[4] * _t0;
        float _t13 = sd[8] * _t1;
        float _t14 = sd[7] * _t0;
        float _t15 = sd[5] * _t1;
        float _t16 = sd[1] * _t0;
        float _t17 = sd[2] * _t1;
        float _t18 = sd[6] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        return normal_degenerate_general_sb024e46c_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code normal_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 normal_degenerate_general_sb024e46c_1(Float3x3 dest, float[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t28, float _sp2, float _sp1, float _sp0) {
        dd[2] = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
        dd[3] = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        dd[4] = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        dd[5] = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
        dd[6] = _t28 * _sp0;
        dd[7] = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        dd[8] = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate(@Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return cofactor_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_orthogonal(dest);
        return normal_degenerate_general(dest);
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[4], sd[7]);
        float _t1 = unitScale(sd[2], sd[5], sd[8]);
        float _t2 = unitScale(sd[0], sd[3], sd[6]);
        float _t12 = sd[4] * _t0;
        float _t13 = sd[8] * _t1;
        float _t14 = sd[7] * _t0;
        float _t15 = sd[5] * _t1;
        float _t16 = sd[1] * _t0;
        float _t17 = sd[2] * _t1;
        float _t18 = sd[6] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[3] * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        dd[0] = _t27 * _sp0;
        dd[1] = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        return normal_degenerate_s86949124_1(dest, dd, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 normal_degenerate_s86949124_1(Double3x3 dest, double[] dd, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t28, float _sp2, float _sp1, float _sp0) {
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
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public float trace() {
        float[] sd = this.data;
        return sd[8] + (sd[0] + sd[4]);
    }




    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = sd[6];
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[2] = sd[6];
        dd[5] = sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[3];
        dd[2] = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        dd[5] = sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[3];
        dd[2] = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        dd[5] = sd[7];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[3];
        float _buf1 = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        float _buf2 = sd[7];
        dd[6] = sd[2];
        dd[7] = sd[5];
        dd[8] = sd[8];
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[5] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 transpose(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 transpose() {
        if (Joml.RETURN_NEW) return transpose(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            ((Float3x3Impl) this).properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transpose_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return transpose_orthogonal_self(this);
        return transpose_general(this);
    }


    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 transpose(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[3];
        float _buf1 = sd[6];
        dd[3] = sd[1];
        dd[4] = sd[4];
        float _buf2 = sd[7];
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
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 add(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = otherData[8] + sd[8];
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 add(Float3x3R other, @Mutated Double3x3 dest) {
        return add(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), dest);
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
    public Float3x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m01 + sd[3];
        dd[4] = m11 + sd[4];
        dd[5] = m21 + sd[5];
        dd[6] = m02 + sd[6];
        dd[7] = m12 + sd[7];
        dd[8] = m22 + sd[8];
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    private Float3x3 mul_identity(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = scalar;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity_self(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar;
        dd[4] = scalar;
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = scalar;
        dd[5] = 0.0f;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_self(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar;
        dd[4] = scalar;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = 0.0f;
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = 0.0f;
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_self(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(float scalar, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar * sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 mul(float scalar, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 mul(float scalar) {
        if (Joml.RETURN_NEW) return mul(scalar, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(scalar, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal_self(scalar, this);
        return mul_general(scalar, this);
    }


    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
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
    public Double3x3 mul(float scalar, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 negate(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 negate(@Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    public Float3x3 sub(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        dd[4] = sd[4] - otherData[4];
        dd[5] = sd[5] - otherData[5];
        dd[6] = sd[6] - otherData[6];
        dd[7] = sd[7] - otherData[7];
        dd[8] = sd[8] - otherData[8];
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 sub(Float3x3R other, @Mutated Double3x3 dest) {
        return sub(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), dest);
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
    public Float3x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m01;
        dd[4] = sd[4] - m11;
        dd[5] = sd[5] - m21;
        dd[6] = sd[6] - m02;
        dd[7] = sd[7] - m12;
        dd[8] = sd[8] - m22;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) from this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    @Mutated public Float3x3 set(Float3x3R v) {
        float[] dd = this.data;
        float[] vData = ((Float3x3Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        dd[4] = vData[4];
        dd[5] = vData[5];
        dd[6] = vData[6];
        dd[7] = vData[7];
        dd[8] = vData[8];
        ((Float3x3Impl) this).properties = ((Float3x3Impl) v).properties;
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
    @Mutated public Float3x3 set(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        float[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m01;
        dd[4] = m11;
        dd[5] = m21;
        dd[6] = m02;
        dd[7] = m12;
        dd[8] = m22;
        ((Float3x3Impl) this).properties = determineProperties();
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
    @Mutated public Float3x3 set(Float2x2R m) {
        float[] dd = this.data;
        float[] mData = ((Float2x2Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = 0.0f;
        dd[3] = mData[2];
        dd[4] = mData[3];
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = determineProperties();
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
    @Mutated public Float3x3 set(Float2x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float2x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = 0.0f;
        dd[3] = mData[2];
        dd[4] = mData[3];
        dd[5] = 0.0f;
        dd[6] = mData[4];
        dd[7] = mData[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = determineProperties();
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
    @Mutated public Float3x3 set(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[4];
        dd[2] = mData[8];
        dd[3] = mData[1];
        dd[4] = mData[5];
        dd[5] = mData[9];
        dd[6] = mData[2];
        dd[7] = mData[6];
        dd[8] = mData[10];
        ((Float3x3Impl) this).properties = determineProperties();
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
    @Mutated public Float3x3 set(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[2];
        dd[3] = mData[4];
        dd[4] = mData[5];
        dd[5] = mData[6];
        dd[6] = mData[8];
        dd[7] = mData[9];
        dd[8] = mData[10];
        ((Float3x3Impl) this).properties = determineProperties();
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
    public Float3x3 withTranslation(Float2R t, @Mutated Float3x3 dest) {
        return withTranslation(t.x(), t.y(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 withTranslation(Float2R t, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 withTranslation(Float2R t) {
        return withTranslation(t.x(), t.y());
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_orthogonal_affine(float tX, float tY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code withTranslation}, specialized by runtime matrix
     * properties; reached only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_orthogonal_affine_self(float tX, float tY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = tX;
        dd[7] = tY;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code withTranslation}, {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float3x3 withTranslation_identity(float tX, float tY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }



    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_general(float tX, float tY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = tX;
        dd[7] = tY;
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 withTranslation(float tX, float tY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 withTranslation(float tX, float tY) {
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[6] = tX;
            dd[7] = tY;
            ((Float3x3Impl) this).properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return withTranslation_orthogonal_affine_self(tX, tY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 withTranslation(float tX, float tY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Convert this matrix to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toDouble(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Double3x3Impl) dest).properties = this.properties;
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
    public @Mutated Float3x3 makeFromRigid(FloatRigidR r) {
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
    @Mutated public Float3x3 makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float[] dd = this.data;
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dd[0] = Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f);
        dd[1] = 2.0f * Math.fma(rRX, rRY, _t1);
        dd[2] = 2.0f * Math.fma(rRX, rRZ, -_t2);
        dd[3] = 2.0f * Math.fma(rRX, rRY, -_t1);
        dd[4] = Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(rRX, rRW, rRY * rRZ);
        dd[6] = 2.0f * Math.fma(rRX, rRZ, _t2);
        dd[7] = 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW));
        dd[8] = Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f);
        ((Float3x3Impl) this).properties = 0;
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
    public @Mutated Float3x3 makeFromTransform(FloatTransformR t) {
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
    @Mutated public Float3x3 makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float[] dd = this.data;
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        dd[0] = Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX);
        dd[1] = Math.fma(tRX, tRY, _t4) * _t0;
        dd[2] = Math.fma(tRX, tRZ, -_t5) * _t0;
        dd[3] = Math.fma(tRX, tRY, -_t4) * _t1;
        dd[4] = Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY);
        dd[5] = Math.fma(tRX, tRW, tRY * tRZ) * _t1;
        dd[6] = Math.fma(tRX, tRZ, _t5) * _t2;
        dd[7] = Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2;
        dd[8] = Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ);
        ((Float3x3Impl) this).properties = 0;
        return this;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_identity(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_general(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        ((Float2x2Impl) dest).properties = 0;
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
    public Float2x2 to2x2(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x2_identity(dest);
        return to2x2_general(dest);
    }


    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column and
     * the last row) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 to2x2(@Mutated Double2x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_orthogonal_general(@Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        dd[4] = sd[6];
        dd[5] = sd[7];
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_identity(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_translation(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = sd[6];
        dd[5] = sd[7];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
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
    public Float2x3 to2x3(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to2x3_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x3_translation(dest);
        return to2x3_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Truncate this matrix to a 2x3 matrix, dropping the last row (assumed {@code 0, 0, 1}) and
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
    public Double2x3 to2x3(@Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[3];
        dd[3] = sd[4];
        dd[4] = sd[6];
        dd[5] = sd[7];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_identity(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        ((Float3x4Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_translation(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = sd[6];
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        float _buf0 = 1.0f;
        dd[6] = sd[7];
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        dd[5] = _buf0;
        ((Float3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_orthogonal(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[3];
        dd[2] = sd[6];
        float _buf0 = 0.0f;
        float _buf1 = sd[1];
        float _buf2 = sd[4];
        dd[6] = sd[7];
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Float3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_general(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[3];
        dd[2] = sd[6];
        float _buf0 = 0.0f;
        float _buf1 = sd[1];
        float _buf2 = sd[4];
        float _buf3 = sd[7];
        float _buf4 = 0.0f;
        float _buf5 = sd[2];
        dd[9] = sd[5];
        dd[10] = sd[8];
        dd[11] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        dd[6] = _buf3;
        dd[7] = _buf4;
        dd[8] = _buf5;
        ((Float3x4Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float3x4 to3x4(@Mutated Float3x4 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x4_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to3x4_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to3x4_orthogonal(dest);
        return to3x4_general(dest);
    }


    /**
     * Extend this matrix to a 3x4 matrix with a zero translation column and store the result in
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
    public Double3x4 to3x4(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[3];
        dd[2] = sd[6];
        float _buf0 = 0.0f;
        float _buf1 = sd[1];
        float _buf2 = sd[4];
        float _buf3 = sd[7];
        float _buf4 = 0.0f;
        float _buf5 = sd[2];
        dd[9] = sd[5];
        dd[10] = sd[8];
        dd[11] = 0.0f;
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
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_identity(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        ((Float4x4Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_translation(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        float _buf0 = 1.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[5] = _buf0;
        ((Float4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_orthogonal(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[1];
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = sd[3];
        float _buf1 = sd[4];
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = 1.0f;
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[1] = _buf0;
        dd[5] = _buf1;
        ((Float4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_general(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[1];
        float _buf1 = sd[2];
        dd[3] = 0.0f;
        dd[4] = sd[3];
        float _buf2 = sd[4];
        dd[6] = sd[5];
        dd[7] = 0.0f;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = sd[8];
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[5] = _buf2;
        ((Float4x4Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float4x4 to4x4(@Mutated Float4x4 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to4x4_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to4x4_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to4x4_orthogonal(dest);
        return to4x4_general(dest);
    }


    /**
     * Extend this matrix to a 4x4 matrix, filling the missing cells with identity and store the
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
    public Double4x4 to4x4(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[1];
        float _buf1 = sd[2];
        dd[3] = 0.0f;
        dd[4] = sd[3];
        float _buf2 = sd[4];
        dd[6] = sd[5];
        dd[7] = 0.0f;
        dd[8] = sd[6];
        dd[9] = sd[7];
        dd[10] = sd[8];
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[5] = _buf2;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_identity(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_translation(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = -(0.25f * sd[7]);
        dd[1] = 0.25f * sd[6];
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_orthogonal(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t3 = sd[0] - sd[4];
        float _t5 = sd[4] - sd[0];
        float _t9 = 1.0f + (sd[0] + sd[4]);
        float _t11 = 1.0f + _t9;
        float _t12 = 1.0f + (1.0f - sd[0] - sd[4]);
        return toDualQuat_orthogonal_s5322187e_1(dest, sd, dd, 0.5f * sd[6], 0.5f * sd[7], _t3, 0.5f * (sd[3] + sd[1]), _t5, 0.5f * (sd[1] - sd[3]), (1.0f / (float) Math.sqrt(_t5)), (1.0f / (float) Math.sqrt(_t3)), _t9, _t11, _t12, (1.0f / (float) Math.sqrt(_t11)), (1.0f / (float) Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code toDualQuat_orthogonal}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat toDualQuat_orthogonal_s5322187e_1(FloatDualQuat dest, float[] sd, float[] dd, float _sp1, float _sp0, float _t3, float _sp2, float _t5, float _sp3, float _t7, float _t8, float _t9, float _t11, float _t12, float _t13, float _t14) {
        if (_t9 > 0.0f) {
            float _buf0 = -(_sp0 * _t13);
            dd[1] = _sp1 * _t13;
            dd[2] = _sp3 * _t13;
            dd[3] = 0.5f * (float) Math.sqrt(_t11);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], 1.0f)) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t3);
                dd[1] = _sp2 * _t8;
                dd[2] = _sp1 * _t8;
                dd[3] = -(_sp0 * _t8);
                dd[0] = _buf0;
            } else {
                if (sd[4] > 1.0f) {
                    float _buf0 = _sp2 * _t7;
                    dd[1] = 0.5f * (float) Math.sqrt(_t5);
                    dd[2] = _sp0 * _t7;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp1 * _t14;
                    dd[1] = _sp0 * _t14;
                    dd[2] = 0.5f * (float) Math.sqrt(_t12);
                    dd[3] = _sp3 * _t14;
                    dd[0] = _buf0;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_general(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t1 = 1.0f - sd[0];
        float _t13 = sd[8] + (sd[0] + sd[4]);
        float _t14 = 1.0f + _t13;
        float _t15 = sd[0] + (1.0f - sd[4] - sd[8]);
        float _t16 = sd[4] + (_t1 - sd[8]);
        float _t17 = sd[8] + (_t1 - sd[4]);
        return toDualQuat_general_s9b9fb5dd_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t13, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_general}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat toDualQuat_general_s9b9fb5dd_1(FloatDualQuat dest, float[] sd, float[] dd, float _t3, float _t5, float _t6, float _t7, float _t8, float _t9, float _t13, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t13 > 0.0f) {
            float _buf0 = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    float _buf0 = _sp1 * _t5;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                    dd[0] = _buf0;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
    public FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toDualQuat_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toDualQuat_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return toDualQuat_orthogonal(dest);
        return toDualQuat_general(dest);
    }


    /**
     * Convert this matrix (assumed orthonormal) to a pure-rotation dual quaternion and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t1 = 1.0f - sd[0];
        float _t13 = sd[8] + (sd[0] + sd[4]);
        float _t14 = 1.0f + _t13;
        float _t15 = sd[0] + (1.0f - sd[4] - sd[8]);
        float _t16 = sd[4] + (_t1 - sd[8]);
        float _t17 = sd[8] + (_t1 - sd[4]);
        return toDualQuat_s14946221_1(dest, sd, dd, sd[5] - sd[7], sd[3] + sd[1], sd[6] + sd[2], sd[6] - sd[2], sd[7] + sd[5], sd[1] - sd[3], _t13, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat toDualQuat_s14946221_1(DoubleDualQuat dest, float[] sd, double[] dd, float _t3, float _t5, float _t6, float _t7, float _t8, float _t9, float _t13, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t13 > 0.0f) {
            float _buf0 = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
            dd[0] = _buf0;
        } else {
            if (sd[0] > Math.max(sd[4], sd[8])) {
                float _buf0 = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
                dd[0] = _buf0;
            } else {
                if (sd[4] > sd[8]) {
                    float _buf0 = _sp1 * _t5;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                    dd[0] = _buf0;
                } else {
                    float _buf0 = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                    dd[0] = _buf0;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private FloatRigid toRigid_identity(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
        return dest;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_translation(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _ct0 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0f));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t3 = (1.0f / (float) Math.sqrt(_ct0));
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        float _t18 = 1.0f + _t3 - _t8 - 1.0f;
        return toRigid_translation_s3348e38_1(dest, dd, _t3, 0.5f * sd[7] * _t3, 0.5f * sd[6] * _t3, _t8, _t11, (1.0f / (float) Math.sqrt(_t11)), _t13, _t15, (1.0f / (float) Math.sqrt(_t15)), _t17, _t18, (1.0f / (float) Math.sqrt(_t17)), (1.0f / (float) Math.sqrt(_t18)));
    }

    /**
     * Piece 2 of {@code toRigid_translation}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code toRigid}; reached only through it.
     */
    private FloatRigid toRigid_translation_s3348e38_1(FloatRigid dest, float[] dd, float _t3, float _sp0, float _sp1, float _t8, float _t11, float _t12, float _t13, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        if (_t13 > 0.0f) {
            dd[3] = -(_sp0 * _t19);
            dd[4] = _sp1 * _t19;
            dd[5] = 0.0f;
            dd[6] = 0.5f * (float) Math.sqrt(_t17);
        } else {
            if (_t8 > Math.max(1.0f, _t3)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t11);
                dd[4] = 0.0f;
                dd[5] = _sp1 * _t12;
                dd[6] = -(_sp0 * _t12);
            } else {
                if (1.0f > _t3) {
                    dd[3] = 0.0f;
                    dd[4] = 0.5f * (float) Math.sqrt(_t15);
                    dd[5] = _sp0 * _t16;
                    dd[6] = _sp1 * _t16;
                } else {
                    dd[3] = _sp1 * _t20;
                    dd[4] = _sp0 * _t20;
                    dd[5] = 0.5f * (float) Math.sqrt(_t18);
                    dd[6] = 0.0f;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_general(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        return toRigid_general_seafa78b5_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private FloatRigid toRigid_general_seafa78b5_1(FloatRigid dest, float[] sd, float[] dd, float _t0, float _t1, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
        float _t20 = sd[7] * _t16;
        float _t23 = sd[5] * _t15;
        return toRigid_general_seafa78b5_2(dest, sd, dd, _t0, _t1, _t15, _t16, sd[1] * _t17, sd[8] * _t16, _t20, sd[2] * _t17, _t23, sd[4] * _t15, sd[0] * _t17, Math.fma(sd[7], _t16, _t23), Math.fma(sd[5], _t15, -_t20));
    }

    /** Piece 3 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private FloatRigid toRigid_general_seafa78b5_2(FloatRigid dest, float[] sd, float[] dd, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), sd[3] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), sd[6] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t51));
        float _t65 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t52));
        return toRigid_general_seafa78b5_3(dest, sd, dd, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(sd[3], _t15, _t48), Math.fma(sd[6], _t16, _t49), Math.fma(sd[6], _t16, -_t49), Math.fma(-sd[3], _t15, _t48), _t63, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t65, _t66, Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private FloatRigid toRigid_general_seafa78b5_3(FloatRigid dest, float[] sd, float[] dd, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        if (Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            float _buf0 = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) Math.sqrt(_t63);
            dd[4] = _buf0;
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t67);
                float _buf0 = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
                dd[4] = _buf0;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    float _buf0 = 0.5f * (float) Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                    dd[4] = _buf0;
                } else {
                    dd[3] = _sp2 * _t55;
                    float _buf0 = _sp2 * _t31;
                    dd[5] = 0.5f * (float) Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                    dd[4] = _buf0;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    public FloatRigid toRigid(@Mutated FloatRigid dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_translation(dest);
        return toRigid_general(dest);
    }


    /**
     * Extract this matrix's rotation into a rigid transform with zero translation (scale is removed
     * by normalizing the columns, but shear is not removed: a sheared block yields a rotation
     * quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        return toRigid_s30c47c8d_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toRigid}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_s30c47c8d_1(DoubleRigid dest, float[] sd, double[] dd, float _t0, float _t1, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
        float _t20 = sd[7] * _t16;
        float _t23 = sd[5] * _t15;
        return toRigid_s30c47c8d_2(dest, sd, dd, _t0, _t1, _t15, _t16, sd[1] * _t17, sd[8] * _t16, _t20, sd[2] * _t17, _t23, sd[4] * _t15, sd[0] * _t17, Math.fma(sd[7], _t16, _t23), Math.fma(sd[5], _t15, -_t20));
    }

    /** Piece 3 of {@code toRigid}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_s30c47c8d_2(DoubleRigid dest, float[] sd, double[] dd, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), sd[3] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), sd[6] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t51));
        float _t65 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t52));
        return toRigid_s30c47c8d_3(dest, sd, dd, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(sd[3], _t15, _t48), Math.fma(sd[6], _t16, _t49), Math.fma(sd[6], _t16, -_t49), Math.fma(-sd[3], _t15, _t48), _t63, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t65, _t66, Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toRigid}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_s30c47c8d_3(DoubleRigid dest, float[] sd, double[] dd, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        if (Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            float _buf0 = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) Math.sqrt(_t63);
            dd[4] = _buf0;
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t67);
                float _buf0 = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
                dd[4] = _buf0;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    float _buf0 = 0.5f * (float) Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                    dd[4] = _buf0;
                } else {
                    dd[3] = _sp2 * _t55;
                    float _buf0 = _sp2 * _t31;
                    dd[5] = 0.5f * (float) Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                    dd[4] = _buf0;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return dest;
    }



    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_translation(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = unitScale(sd[6], sd[7], 1.0f);
        float _t1 = _t0;
        float _t4 = sd[6] * _t0;
        float _t5 = sd[7] * _t0;
        float _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) Math.sqrt(_t8));
        float _t13 = _t8 <= 0.0f ? 1.0f : _t9 * _t1;
        float _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _sp0 = 0.5f * 0.0f;
            _sp1 = 0.5f * 0.0f;
        } else {
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        float _t28 = 1.0f + _t13 - _t18 - 1.0f;
        return toRigid_translation_s3348e38_1(dest, dd, _t13, _sp0, _sp1, _t18, _t21, (1.0f / (float) Math.sqrt(_t21)), _t23, _t25, (1.0f / (float) Math.sqrt(_t25)), _t27, _t28, (1.0f / (float) Math.sqrt(_t27)), (1.0f / (float) Math.sqrt(_t28)));
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_general(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = unitScale(sd[3], sd[4], sd[5]);
        float _t1 = unitScale(sd[6], sd[7], sd[8]);
        float _t2 = unitScale(sd[0], sd[1], sd[2]);
        float _t12 = sd[5] * _t0;
        float _t13 = sd[3] * _t0;
        float _t14 = sd[4] * _t0;
        float _t15 = sd[8] * _t1;
        float _t16 = sd[6] * _t1;
        float _t17 = sd[7] * _t1;
        float _t18 = sd[2] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
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
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate(@Mutated FloatRigid dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_degenerate_translation(dest);
        return toRigid_degenerate_general(dest);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = unitScale(sd[3], sd[4], sd[5]);
        float _t1 = unitScale(sd[6], sd[7], sd[8]);
        float _t2 = unitScale(sd[0], sd[1], sd[2]);
        float _t12 = sd[5] * _t0;
        float _t13 = sd[3] * _t0;
        float _t14 = sd[4] * _t0;
        float _t15 = sd[8] * _t1;
        float _t16 = sd[6] * _t1;
        float _t17 = sd[7] * _t1;
        float _t18 = sd[2] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
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
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private FloatTransform toTransform_identity(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_translation(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t2 = Math.fma(sd[6], sd[6], Math.fma(sd[7], sd[7], 1.0f));
        if (!(_t2 > 1.1754944E-38f && _t2 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        float _t18 = 1.0f + _t3 - _t8 - 1.0f;
        return toTransform_translation_sf33a1f20_1(dest, dd, _t2, _t3, 0.5f * sd[7] * _t3, 0.5f * sd[6] * _t3, _t8, _t11, (1.0f / (float) Math.sqrt(_t11)), _t13, _t15, (1.0f / (float) Math.sqrt(_t15)), _t17, _t18, (1.0f / (float) Math.sqrt(_t17)), (1.0f / (float) Math.sqrt(_t18)));
    }

    /** Piece 2 of {@code toTransform_translation}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_translation_sf33a1f20_1(FloatTransform dest, float[] dd, float _t2, float _t3, float _sp0, float _sp1, float _t8, float _t11, float _t12, float _t13, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        if (_t13 > 0.0f) {
            dd[3] = -(_sp0 * _t19);
            dd[4] = _sp1 * _t19;
            dd[5] = 0.0f;
            dd[6] = 0.5f * (float) Math.sqrt(_t17);
        } else {
            if (_t8 > Math.max(1.0f, _t3)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t11);
                dd[4] = 0.0f;
                dd[5] = _sp1 * _t12;
                dd[6] = -(_sp0 * _t12);
            } else {
                if (1.0f > _t3) {
                    dd[3] = 0.0f;
                    dd[4] = 0.5f * (float) Math.sqrt(_t15);
                    dd[5] = _sp0 * _t16;
                    dd[6] = _sp1 * _t16;
                } else {
                    dd[3] = _sp1 * _t20;
                    dd[4] = _sp0 * _t20;
                    dd[5] = 0.5f * (float) Math.sqrt(_t18);
                    dd[6] = 0.0f;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[7] = _t8;
        dd[8] = 1.0f;
        dd[9] = (float) Math.sqrt(_t2);
        return dest;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_general(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        return toTransform_general_sd9aea1f1_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_sd9aea1f1_1(FloatTransform dest, float[] sd, float[] dd, float _t0, float _t1, float _t12) {
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t13 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t14 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t20 = sd[8] * _t16;
        float _t21 = sd[7] * _t16;
        float _t24 = sd[5] * _t15;
        float _t25 = sd[4] * _t15;
        return toTransform_general_sd9aea1f1_2(dest, sd, dd, _t0, _t1, _t12, _t13, _t15, _t16, _t18, sd[1] * _t17, _t20, _t21, sd[2] * _t17, _t24, _t25, sd[0] * _t17, Math.fma(sd[7], _t16, _t24), Math.fma(sd[5], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_sd9aea1f1_2(FloatTransform dest, float[] sd, float[] dd, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32, float _t36, float _t37) {
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), sd[3] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), sd[6] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t52));
        float _t66 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t53));
        return toTransform_general_sd9aea1f1_3(dest, dd, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(sd[3], _t15, _t49), Math.fma(sd[6], _t16, _t50), Math.fma(sd[6], _t16, -_t50), Math.fma(-sd[3], _t15, _t49), Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t48)), _t64, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t66, Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_sd9aea1f1_3(FloatTransform dest, float[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        return toTransform_general_sd9aea1f1_4(dest, dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /** Piece 5 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_sd9aea1f1_4(FloatTransform dest, float[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t36, float _t37, float _t47, float _t48, float _t57, float _t58, float _t63, float _t64, float _sp1, float _sp2, float _sp3) {
        dd[6] = _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
        dd[7] = _t47 < 0.0f ? -_t18 : _t18;
        dd[8] = (float) Math.sqrt(_t12);
        dd[9] = (float) Math.sqrt(_t13);
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
    public FloatTransform toTransform(@Mutated FloatTransform dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_translation(dest);
        return toTransform_general(dest);
    }


    /**
     * Decompose this matrix's linear {@code R * S} block into a TRS transform with zero translation
     * (scale is removed by normalizing the columns, but shear is not removed: a sheared block
     * yields a rotation quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        return toTransform_s9f9fbcab_1(dest, sd, dd, -sd[4], -sd[8], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
    }

    /** Piece 2 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_s9f9fbcab_1(DoubleTransform dest, float[] sd, double[] dd, float _t0, float _t1, float _t12) {
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t13 = Math.fma(sd[8], sd[8], Math.fma(sd[6], sd[6], sd[7] * sd[7]));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t14 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t20 = sd[8] * _t16;
        float _t21 = sd[7] * _t16;
        float _t24 = sd[5] * _t15;
        float _t25 = sd[4] * _t15;
        return toTransform_s9f9fbcab_2(dest, sd, dd, _t0, _t1, _t12, _t13, _t15, _t16, _t18, sd[1] * _t17, _t20, _t21, sd[2] * _t17, _t24, _t25, sd[0] * _t17, Math.fma(sd[7], _t16, _t24), Math.fma(sd[5], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_s9f9fbcab_2(DoubleTransform dest, float[] sd, double[] dd, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32, float _t36, float _t37) {
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), sd[3] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), sd[6] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t52));
        float _t66 = Math.fma(sd[4], _t15, Math.fma(_t1, _t16, _t53));
        return toTransform_s9f9fbcab_3(dest, dd, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(sd[3], _t15, _t49), Math.fma(sd[6], _t16, _t50), Math.fma(sd[6], _t16, -_t50), Math.fma(-sd[3], _t15, _t49), Math.fma(sd[4], _t15, Math.fma(sd[8], _t16, _t48)), _t64, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t66, Math.fma(sd[8], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_s9f9fbcab_3(DoubleTransform dest, double[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        return toTransform_s9f9fbcab_4(dest, dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /** Piece 5 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_s9f9fbcab_4(DoubleTransform dest, double[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t36, float _t37, float _t47, float _t48, float _t57, float _t58, float _t63, float _t64, float _sp1, float _sp2, float _sp3) {
        dd[6] = _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
        dd[7] = _t47 < 0.0f ? -_t18 : _t18;
        dd[8] = (float) Math.sqrt(_t12);
        dd[9] = (float) Math.sqrt(_t13);
        return dest;
    }



    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_translation(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = unitScale(sd[6], sd[7], 1.0f);
        float _t1 = _t0;
        float _t4 = sd[6] * _t0;
        float _t5 = sd[7] * _t0;
        float _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) Math.sqrt(_t8));
        float _t13 = _t8 <= 0.0f ? 1.0f : _t9 * _t1;
        float _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _sp0 = 0.5f * 0.0f;
            _sp1 = 0.5f * 0.0f;
        } else {
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        float _t28 = 1.0f + _t13 - _t18 - 1.0f;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return toTransform_degenerate_translation_s36d7844f_1(dest, dd, _t0, _t8, _t13, Math.max(1.0f, _t13), _sp0, _sp1, _t18, _t21, (1.0f / (float) Math.sqrt(_t21)), _t23, _t25, (1.0f / (float) Math.sqrt(_t25)), _t27, _t28, (1.0f / (float) Math.sqrt(_t27)), (1.0f / (float) Math.sqrt(_t28)));
    }

    /** Piece 2 of {@code toTransform_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_degenerate_translation_s36d7844f_1(FloatTransform dest, float[] dd, float _t0, float _t8, float _t13, float _t14, float _sp0, float _sp1, float _t18, float _t21, float _t22, float _t23, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30) {
        dd[3] = _t23 > 0.0f ? -(_sp0 * _t29) : _t18 > _t14 ? 0.5f * (float) Math.sqrt(_t21) : 1.0f > _t13 ? 0.0f : _sp1 * _t30;
        dd[4] = _t23 > 0.0f ? _sp1 * _t29 : _t18 > _t14 ? 0.0f : 1.0f > _t13 ? 0.5f * (float) Math.sqrt(_t25) : _sp0 * _t30;
        dd[5] = _t23 > 0.0f ? 0.0f : _t18 > _t14 ? _sp1 * _t22 : 1.0f > _t13 ? _sp0 * _t26 : 0.5f * (float) Math.sqrt(_t28);
        dd[6] = _t23 > 0.0f ? 0.5f * (float) Math.sqrt(_t27) : _t18 > _t14 ? -(_sp0 * _t22) : 1.0f > _t13 ? _sp1 * _t26 : 0.0f;
        dd[7] = _t18;
        dd[8] = 1.0f;
        dd[9] = _t8 <= 0.0f ? 0.0f : (float) Math.sqrt(_t8) / _t0;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_general(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = unitScale(sd[3], sd[4], sd[5]);
        float _t1 = unitScale(sd[6], sd[7], sd[8]);
        float _t2 = unitScale(sd[0], sd[1], sd[2]);
        float _t12 = sd[5] * _t0;
        float _t13 = sd[3] * _t0;
        float _t14 = sd[4] * _t0;
        float _t15 = sd[8] * _t1;
        float _t16 = sd[6] * _t1;
        float _t17 = sd[7] * _t1;
        float _t18 = sd[2] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
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
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dd[6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0f ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
        return dest;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate(@Mutated FloatTransform dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_degenerate_translation(dest);
        return toTransform_degenerate_general(dest);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _t0 = unitScale(sd[3], sd[4], sd[5]);
        float _t1 = unitScale(sd[6], sd[7], sd[8]);
        float _t2 = unitScale(sd[0], sd[1], sd[2]);
        float _t12 = sd[5] * _t0;
        float _t13 = sd[3] * _t0;
        float _t14 = sd[4] * _t0;
        float _t15 = sd[8] * _t1;
        float _t16 = sd[6] * _t1;
        float _t17 = sd[7] * _t1;
        float _t18 = sd[2] * _t2;
        float _t19 = sd[0] * _t2;
        float _t20 = sd[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
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
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dd[6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0f ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
        return dest;
    }



    /**
     * Private body of {@code decomposeRotation}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeRotation} dispatcher.
     */
    private FloatQuat decomposeRotation_general(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        float _t21 = Math.fma(_t19, _t7, sd[5]);
        float _t22 = Math.fma(_t19, _t8, sd[3]);
        float _t23 = Math.fma(_t19, _t9, sd[4]);
        float _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
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
        return decomposeRotation_general_s2c4240a_1(dest, sd, dd, _t7, _t8, _t9, -Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9)), _t34, _t35, _t36);
    }

    /** Piece 2 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeRotation_general_s2c4240a_1(FloatQuat dest, float[] sd, float[] dd, float _t7, float _t8, float _t9, float _t20, float _t34, float _t35, float _t36) {
        float _t40 = -Math.fma(Math.fma(_t20, _t7, sd[8]), _t35, Math.fma(Math.fma(_t20, _t8, sd[6]), _t34, Math.fma(_t20, _t9, sd[7]) * _t36));
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, sd[8]));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, sd[6]));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, sd[7]));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
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
        return decomposeRotation_general_s2c4240a_2(dest, dd, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeRotation_general_s2c4240a_2(FloatQuat dest, float[] dd, float _t7, float _t8, float _t9, float _t34, float _t35, float _t36, float _t54, float _t55, float _t56, float _t60, float _t63) {
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
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        return decomposeRotation_general_s2c4240a_3(dest, dd, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5f * (1.0f / (float) Math.sqrt(_t86)), 0.5f * (1.0f / (float) Math.sqrt(_t88)), 0.5f * (1.0f / (float) Math.sqrt(_t89)), 0.5f * (1.0f / (float) Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeRotation_general_s2c4240a_3(FloatQuat dest, float[] dd, float _t36, float _t56, float _t60, float _t63, float _t73, float _t77, float _t78, float _t80, float _t81, float _t82, float _t86, float _t87, float _t88, float _t89, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t82 > 0.0f) {
            dd[0] = _sp0 * _t60;
            dd[1] = _sp0 * _t81;
            dd[2] = _sp0 * _t78;
            dd[3] = 0.5f * (float) Math.sqrt(_t86);
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t87);
                dd[1] = _sp3 * _t77;
                dd[2] = _sp3 * _t80;
                dd[3] = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    dd[0] = _sp1 * _t77;
                    dd[1] = 0.5f * (float) Math.sqrt(_t88);
                    dd[2] = _sp1 * _t63;
                    dd[3] = _sp1 * _t81;
                } else {
                    dd[0] = _sp2 * _t80;
                    dd[1] = _sp2 * _t63;
                    dd[2] = 0.5f * (float) Math.sqrt(_t89);
                    dd[3] = _sp2 * _t78;
                }
            }
        }
        return dest;
    }


    /**
     * Extract the rotation part of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat decomposeRotation(@Mutated FloatQuat dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_identity(dest);
        return decomposeRotation_general(dest);
    }


    /**
     * Extract the rotation part of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat decomposeRotation(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        float _t21 = Math.fma(_t19, _t7, sd[5]);
        float _t22 = Math.fma(_t19, _t8, sd[3]);
        float _t23 = Math.fma(_t19, _t9, sd[4]);
        float _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
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
        return decomposeRotation_s4e30200_1(dest, sd, dd, _t7, _t8, _t9, -Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9)), _t34, _t35, _t36);
    }

    /** Piece 2 of {@code decomposeRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_s4e30200_1(DoubleQuat dest, float[] sd, double[] dd, float _t7, float _t8, float _t9, float _t20, float _t34, float _t35, float _t36) {
        float _t40 = -Math.fma(Math.fma(_t20, _t7, sd[8]), _t35, Math.fma(Math.fma(_t20, _t8, sd[6]), _t34, Math.fma(_t20, _t9, sd[7]) * _t36));
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, sd[8]));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, sd[6]));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, sd[7]));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
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
        return decomposeRotation_s4e30200_2(dest, dd, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_s4e30200_2(DoubleQuat dest, double[] dd, float _t7, float _t8, float _t9, float _t34, float _t35, float _t36, float _t54, float _t55, float _t56, float _t60, float _t63) {
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
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        return decomposeRotation_s4e30200_3(dest, dd, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5f * (1.0f / (float) Math.sqrt(_t86)), 0.5f * (1.0f / (float) Math.sqrt(_t88)), 0.5f * (1.0f / (float) Math.sqrt(_t89)), 0.5f * (1.0f / (float) Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_s4e30200_3(DoubleQuat dest, double[] dd, float _t36, float _t56, float _t60, float _t63, float _t73, float _t77, float _t78, float _t80, float _t81, float _t82, float _t86, float _t87, float _t88, float _t89, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t82 > 0.0f) {
            dd[0] = _sp0 * _t60;
            dd[1] = _sp0 * _t81;
            dd[2] = _sp0 * _t78;
            dd[3] = 0.5f * (float) Math.sqrt(_t86);
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t87);
                dd[1] = _sp3 * _t77;
                dd[2] = _sp3 * _t80;
                dd[3] = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    dd[0] = _sp1 * _t77;
                    dd[1] = 0.5f * (float) Math.sqrt(_t88);
                    dd[2] = _sp1 * _t63;
                    dd[3] = _sp1 * _t81;
                } else {
                    dd[0] = _sp2 * _t80;
                    dd[1] = _sp2 * _t63;
                    dd[2] = 0.5f * (float) Math.sqrt(_t89);
                    dd[3] = _sp2 * _t78;
                }
            }
        }
        return dest;
    }



    /**
     * Private body of {@code decomposeScale}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeScale} dispatcher.
     */
    private Float3 decomposeScale_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t4 = (float) Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = sd[2] * _t3;
            _t9 = sd[0] * _t3;
            _t10 = sd[1] * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t17 = -Math.fma(sd[5], _t8, Math.fma(sd[3], _t9, sd[4] * _t10));
        float _t19 = Math.fma(_t17, _t8, sd[5]);
        float _t20 = Math.fma(_t17, _t9, sd[3]);
        float _t21 = Math.fma(_t17, _t10, sd[4]);
        float _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t28 = (1.0f / (float) Math.sqrt(_t27));
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
        return decomposeScale_general_s1e9d9a46_1(dest, sd, dd, _t4, _t8, _t9, _t10, -Math.fma(sd[8], _t8, Math.fma(sd[6], _t9, sd[7] * _t10)), _t27, _t32, _t33, _t34);
    }

    /** Piece 2 of {@code decomposeScale_general}, split to fit the inline budget; reached only through it. */
    private Float3 decomposeScale_general_s1e9d9a46_1(Float3 dest, float[] sd, float[] dd, float _t4, float _t8, float _t9, float _t10, float _t18, float _t27, float _t32, float _t33, float _t34) {
        float _t38 = -Math.fma(Math.fma(_t18, _t8, sd[8]), _t33, Math.fma(Math.fma(_t18, _t9, sd[6]), _t32, Math.fma(_t18, _t10, sd[7]) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, sd[8]));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, sd[6]));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, sd[7]));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
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
        dd[0] = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4;
        dd[1] = (float) Math.sqrt(_t27);
        dd[2] = (float) Math.sqrt(_t47);
        return dest;
    }


    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 decomposeScale(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getScale_identity(dest);
        return decomposeScale_general(dest);
    }


    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative) and store the
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
    public Double3 decomposeScale(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t4 = (float) Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = sd[2] * _t3;
            _t9 = sd[0] * _t3;
            _t10 = sd[1] * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t17 = -Math.fma(sd[5], _t8, Math.fma(sd[3], _t9, sd[4] * _t10));
        float _t19 = Math.fma(_t17, _t8, sd[5]);
        float _t20 = Math.fma(_t17, _t9, sd[3]);
        float _t21 = Math.fma(_t17, _t10, sd[4]);
        float _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t28 = (1.0f / (float) Math.sqrt(_t27));
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
        return decomposeScale_sc15c2034_1(dest, sd, dd, _t4, _t8, _t9, _t10, -Math.fma(sd[8], _t8, Math.fma(sd[6], _t9, sd[7] * _t10)), _t27, _t32, _t33, _t34);
    }

    /** Piece 2 of {@code decomposeScale}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeScale_sc15c2034_1(Double3 dest, float[] sd, double[] dd, float _t4, float _t8, float _t9, float _t10, float _t18, float _t27, float _t32, float _t33, float _t34) {
        float _t38 = -Math.fma(Math.fma(_t18, _t8, sd[8]), _t33, Math.fma(Math.fma(_t18, _t9, sd[6]), _t32, Math.fma(_t18, _t10, sd[7]) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, sd[8]));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, sd[6]));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, sd[7]));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
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
        dd[0] = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4;
        dd[1] = (float) Math.sqrt(_t27);
        dd[2] = (float) Math.sqrt(_t47);
        return dest;
    }




    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Float3 decomposeSkew_general(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9));
        float _t15 = Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        float _t17 = -_t15;
        float _t19 = Math.fma(_t17, _t7, sd[5]);
        float _t20 = Math.fma(_t17, _t8, sd[3]);
        float _t21 = Math.fma(_t17, _t9, sd[4]);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) Math.sqrt(_t26));
        return decomposeSkew_general_s6c3c6c8e_1(dest, sd, dd, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew_general}, split to fit the inline budget; reached only through it. */
    private Float3 decomposeSkew_general_s6c3c6c8e_1(Float3 dest, float[] sd, float[] dd, float _t7, float _t8, float _t9, float _t14, float _t16, float _t19, float _t20, float _t21, float _t26, float _t27, float _t28) {
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
        float _t37 = Math.fma(Math.fma(_t16, _t7, sd[8]), _t32, Math.fma(Math.fma(_t16, _t8, sd[6]), _t33, Math.fma(_t16, _t9, sd[7]) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, sd[8]));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, sd[6]));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, sd[7]));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
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
        return decomposeSkew_general_s6c3c6c8e_2(dest, dd, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_general}, split to fit the inline budget; reached only through it. */
    private Float3 decomposeSkew_general_s6c3c6c8e_2(Float3 dest, float[] dd, float _t7, float _t8, float _t9, float _t28, float _t32, float _t33, float _t34, float _t37, float _t48, float _t49, float _t53, float _t54, float _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0f) {
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; this matrix
     * must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 decomposeSkew(@Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = sd[7];
            dd[1] = sd[6];
            dd[2] = 0.0f;
            return dest;
        }
        return decomposeSkew_general(dest);
    }


    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; this matrix
     * must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 decomposeSkew(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = sd[2] * _t3;
            _t8 = sd[0] * _t3;
            _t9 = sd[1] * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = Math.fma(sd[8], _t7, Math.fma(sd[6], _t8, sd[7] * _t9));
        float _t15 = Math.fma(sd[5], _t7, Math.fma(sd[3], _t8, sd[4] * _t9));
        float _t17 = -_t15;
        float _t19 = Math.fma(_t17, _t7, sd[5]);
        float _t20 = Math.fma(_t17, _t8, sd[3]);
        float _t21 = Math.fma(_t17, _t9, sd[4]);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) Math.sqrt(_t26));
        return decomposeSkew_s680140bc_1(dest, sd, dd, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeSkew_s680140bc_1(Double3 dest, float[] sd, double[] dd, float _t7, float _t8, float _t9, float _t14, float _t16, float _t19, float _t20, float _t21, float _t26, float _t27, float _t28) {
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
        float _t37 = Math.fma(Math.fma(_t16, _t7, sd[8]), _t32, Math.fma(Math.fma(_t16, _t8, sd[6]), _t33, Math.fma(_t16, _t9, sd[7]) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, sd[8]));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, sd[6]));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, sd[7]));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
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
        return decomposeSkew_s680140bc_2(dest, dd, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew}, split to fit the inline budget; reached only through it. */
    private Double3 decomposeSkew_s680140bc_2(Double3 dest, double[] dd, float _t7, float _t8, float _t9, float _t28, float _t32, float _t33, float _t34, float _t37, float _t48, float _t49, float _t53, float _t54, float _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0f) {
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
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float3x3 makeIdentity() {
        float[] dd = this.data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_IDENTITY;
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
    public Float3x3 lerp(Float3x3R other, float t, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherData[6] - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherData[7] - sd[7], sd[7]);
        dd[8] = Math.fma(t, otherData[8] - sd[8], sd[8]);
        ((Float3x3Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float3x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float3x3Impl) other).properties) >> 1);
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 lerp(Float3x3R other, float t, @Mutated Double3x3 dest) {
        return lerp(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), t, dest);
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
    public Float3x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float t, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m01 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m11 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m21 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m02 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m12 - sd[7], sd[7]);
        dd[8] = Math.fma(t, m22 - sd[8], sd[8]);
        ((Float3x3Impl) dest).properties = 0;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float t, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    private Float3x3 mul_general(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        return mul_general_s19976ee0_1(dest, sd, rightData, dd, Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[0], rightData[1] * sd[3])), Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4])), Math.fma(rightData[2], sd[8], Math.fma(rightData[0], sd[2], rightData[1] * sd[5])), Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[0], rightData[4] * sd[3])), Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4])), Math.fma(rightData[5], sd[8], Math.fma(rightData[3], sd[2], rightData[4] * sd[5])), Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[0], rightData[7] * sd[3])), Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4])));
    }

    /** Piece 2 of {@code mul_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 mul_general_s19976ee0_1(Float3x3 dest, float[] sd, float[] rightData, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6, float _buf7) {
        dd[8] = Math.fma(rightData[8], sd[8], Math.fma(rightData[6], sd[2], rightData[7] * sd[5]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[2], sd[6], rightData[0]);
        dd[1] = Math.fma(rightData[2], sd[7], rightData[1]);
        dd[2] = rightData[2];
        dd[3] = Math.fma(rightData[5], sd[6], rightData[3]);
        dd[4] = Math.fma(rightData[5], sd[7], rightData[4]);
        dd[5] = rightData[5];
        dd[6] = Math.fma(rightData[8], sd[6], rightData[6]);
        dd[7] = Math.fma(rightData[8], sd[7], rightData[7]);
        dd[8] = rightData[8];
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1])));
        float _buf1 = Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4]));
        dd[2] = rightData[2];
        float _buf2 = Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[4], -(rightData[4] * sd[1])));
        float _buf3 = Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4]));
        dd[5] = rightData[5];
        float _buf4 = Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[4], -(rightData[7] * sd[1])));
        dd[7] = Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4]));
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[2], sd[6], Math.fma(rightData[0], sd[0], rightData[1] * sd[3]));
        float _buf1 = Math.fma(rightData[2], sd[7], Math.fma(rightData[0], sd[1], rightData[1] * sd[4]));
        dd[2] = rightData[2];
        float _buf2 = Math.fma(rightData[5], sd[6], Math.fma(rightData[3], sd[0], rightData[4] * sd[3]));
        float _buf3 = Math.fma(rightData[5], sd[7], Math.fma(rightData[3], sd[1], rightData[4] * sd[4]));
        dd[5] = rightData[5];
        float _buf4 = Math.fma(rightData[8], sd[6], Math.fma(rightData[6], sd[0], rightData[7] * sd[3]));
        dd[7] = Math.fma(rightData[8], sd[7], Math.fma(rightData[6], sd[1], rightData[7] * sd[4]));
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = rightData[6] + sd[6];
        dd[7] = rightData[7] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_affine(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0f;
        dd[3] = rightData[3];
        dd[4] = rightData[4];
        dd[5] = 0.0f;
        dd[6] = rightData[6] + sd[6];
        dd[7] = rightData[7] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        dd[2] = 0.0f;
        float _buf2 = sd[3];
        float _buf3 = sd[4];
        dd[5] = 0.0f;
        float _buf4 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_affine(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(rightData[3], sd[4], -(rightData[4] * sd[1]));
        float _buf3 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        dd[5] = 0.0f;
        float _buf4 = Math.fma(-rightData[7], sd[1], Math.fma(rightData[6], sd[4], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine_affine(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(rightData[3], sd[0], rightData[4] * sd[3]);
        float _buf3 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        dd[5] = 0.0f;
        float _buf4 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        float _buf3 = sd[3];
        float _buf4 = sd[4];
        float _buf5 = sd[5];
        float _buf6 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        float _buf7 = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[6], sd[2], Math.fma(rightData[7], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_affine(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        float _buf3 = Math.fma(rightData[3], sd[0], rightData[4] * sd[3]);
        float _buf4 = Math.fma(rightData[3], sd[1], rightData[4] * sd[4]);
        float _buf5 = Math.fma(rightData[3], sd[2], rightData[4] * sd[5]);
        float _buf6 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[3], sd[6]));
        float _buf7 = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[6], sd[2], Math.fma(rightData[7], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 mul(Float3x3R right, @Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Float3x3Impl) right).properties;
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
    @Mutated public Float3x3 mul(Float3x3R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        int q = ((Float3x3Impl) right).properties;
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
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 mul(Float3x3R right, @Mutated Double3x3 dest) {
        return mul(right.m00(), right.m01(), right.m02(), right.m10(), right.m11(), right.m12(), right.m20(), right.m21(), right.m22(), dest);
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
    public Float3x3 mul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3]));
        float _buf1 = Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4]));
        float _buf2 = Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5]));
        float _buf3 = Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3]));
        float _buf4 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        float _buf5 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
        dd[6] = Math.fma(m22, sd[6], Math.fma(m02, sd[0], m12 * sd[3]));
        dd[7] = Math.fma(m22, sd[7], Math.fma(m02, sd[1], m12 * sd[4]));
        dd[8] = Math.fma(m22, sd[8], Math.fma(m02, sd[2], m12 * sd[5]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}, {@code m20}, {@code m21}, {@code m22}) and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 mul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(m20, sd[6], Math.fma(m00, sd[0], m10 * sd[3]));
        float _buf1 = Math.fma(m20, sd[7], Math.fma(m00, sd[1], m10 * sd[4]));
        float _buf2 = Math.fma(m20, sd[8], Math.fma(m00, sd[2], m10 * sd[5]));
        float _buf3 = Math.fma(m21, sd[6], Math.fma(m01, sd[0], m11 * sd[3]));
        float _buf4 = Math.fma(m21, sd[7], Math.fma(m01, sd[1], m11 * sd[4]));
        float _buf5 = Math.fma(m21, sd[8], Math.fma(m01, sd[2], m11 * sd[5]));
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
    private Float3x3 mul_identity(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0f;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity_self(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0f;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_self(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_self(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine_self(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x2R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        dd[3] = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        dd[4] = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = Math.fma(rightData[2], sd[2], rightData[3] * sd[5]);
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 mul(Float2x2R right, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 mul(Float2x2R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(right, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal_self(right, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_self(right, this);
        return mul_general(right, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 mul(Float2x2R right, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        float _buf3 = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        float _buf4 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        float _buf5 = Math.fma(rightData[2], sd[2], rightData[3] * sd[5]);
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[4], sd[2], Math.fma(rightData[5], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0f;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0f;
        dd[6] = rightData[4];
        dd[7] = rightData[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = 0.0f;
        dd[3] = rightData[2];
        dd[4] = rightData[3];
        dd[5] = 0.0f;
        dd[6] = rightData[4] + sd[6];
        dd[7] = rightData[5] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[4], -(rightData[1] * sd[1]));
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        dd[3] = Math.fma(rightData[2], sd[4], -(rightData[3] * sd[1]));
        float _buf1 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0f;
        dd[6] = Math.fma(-rightData[5], sd[1], Math.fma(rightData[4], sd[4], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        float _buf3 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        dd[5] = 0.0f;
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity_translation(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = rightData[4];
        dd[7] = rightData[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = rightData[4] + sd[6];
        dd[7] = rightData[5] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float2x3R right, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        dd[2] = 0.0f;
        float _buf2 = sd[3];
        float _buf3 = sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float2x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        float _buf3 = sd[3];
        float _buf4 = sd[4];
        float _buf5 = sd[5];
        dd[6] = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[3], sd[6]));
        dd[7] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[4], sd[7]));
        dd[8] = Math.fma(rightData[4], sd[2], Math.fma(rightData[5], sd[5], sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 mul(Float2x3R right, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) right).properties;
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
    @Mutated public Float3x3 mul(Float2x3R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.float3x3());
        int p = this.properties;
        int q = ((Float2x3Impl) right).properties;
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
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 mul(Float2x3R right, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[4]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[5]);
        float _buf3 = Math.fma(rightData[2], sd[0], rightData[3] * sd[3]);
        float _buf4 = Math.fma(rightData[2], sd[1], rightData[3] * sd[4]);
        float _buf5 = Math.fma(rightData[2], sd[2], rightData[3] * sd[5]);
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
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        return preMul_general_se4508421_1(dest, sd, otherData, dd, Math.fma(otherData[6], sd[2], Math.fma(otherData[0], sd[0], otherData[3] * sd[1])), Math.fma(otherData[7], sd[2], Math.fma(otherData[1], sd[0], otherData[4] * sd[1])), Math.fma(otherData[8], sd[2], Math.fma(otherData[2], sd[0], otherData[5] * sd[1])), Math.fma(otherData[6], sd[5], Math.fma(otherData[0], sd[3], otherData[3] * sd[4])), Math.fma(otherData[7], sd[5], Math.fma(otherData[1], sd[3], otherData[4] * sd[4])), Math.fma(otherData[8], sd[5], Math.fma(otherData[2], sd[3], otherData[5] * sd[4])), Math.fma(otherData[6], sd[8], Math.fma(otherData[0], sd[6], otherData[3] * sd[7])), Math.fma(otherData[7], sd[8], Math.fma(otherData[1], sd[6], otherData[4] * sd[7])));
    }

    /** Piece 2 of {@code preMul_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 preMul_general_se4508421_1(Float3x3 dest, float[] sd, float[] otherData, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6, float _buf7) {
        dd[8] = Math.fma(otherData[8], sd[8], Math.fma(otherData[2], sd[6], otherData[5] * sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = otherData[0];
        float _buf1 = otherData[1];
        float _buf2 = otherData[2];
        float _buf3 = otherData[3];
        float _buf4 = otherData[4];
        float _buf5 = otherData[5];
        float _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        float _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[4], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[4], otherData[4] * sd[1]);
        float _buf2 = Math.fma(otherData[2], sd[4], otherData[5] * sd[1]);
        float _buf3 = Math.fma(otherData[3], sd[4], -(otherData[0] * sd[1]));
        float _buf4 = Math.fma(otherData[4], sd[4], -(otherData[1] * sd[1]));
        float _buf5 = Math.fma(otherData[5], sd[4], -(otherData[2] * sd[1]));
        float _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        float _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        float _buf2 = Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        float _buf3 = Math.fma(otherData[0], sd[3], otherData[3] * sd[4]);
        float _buf4 = Math.fma(otherData[1], sd[3], otherData[4] * sd[4]);
        float _buf5 = Math.fma(otherData[2], sd[3], otherData[5] * sd[4]);
        float _buf6 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        float _buf7 = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[6], Math.fma(otherData[5], sd[7], otherData[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[7] = _buf7;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = otherData[0];
        float _buf1 = otherData[1];
        dd[2] = 0.0f;
        float _buf2 = otherData[3];
        float _buf3 = otherData[4];
        dd[5] = 0.0f;
        float _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[4], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[4], otherData[4] * sd[1]);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(otherData[3], sd[4], -(otherData[0] * sd[1]));
        float _buf3 = Math.fma(otherData[4], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0f;
        float _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(otherData[0], sd[3], otherData[3] * sd[4]);
        float _buf3 = Math.fma(otherData[1], sd[3], otherData[4] * sd[4]);
        dd[5] = 0.0f;
        float _buf4 = Math.fma(otherData[0], sd[6], Math.fma(otherData[3], sd[7], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[4], sd[7], otherData[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[6], sd[2], sd[0]);
        dd[1] = Math.fma(otherData[7], sd[2], sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(otherData[6], sd[5], sd[3]);
        dd[4] = Math.fma(otherData[7], sd[5], sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(otherData[6], sd[8], sd[6]);
        dd[7] = Math.fma(otherData[7], sd[8], sd[7]);
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[6], sd[2], Math.fma(otherData[0], sd[0], otherData[3] * sd[1]));
        float _buf1 = Math.fma(otherData[7], sd[2], Math.fma(otherData[1], sd[0], otherData[4] * sd[1]));
        dd[2] = sd[2];
        float _buf2 = Math.fma(otherData[6], sd[5], Math.fma(otherData[0], sd[3], otherData[3] * sd[4]));
        float _buf3 = Math.fma(otherData[7], sd[5], Math.fma(otherData[1], sd[3], otherData[4] * sd[4]));
        dd[5] = sd[5];
        float _buf4 = Math.fma(otherData[6], sd[8], Math.fma(otherData[0], sd[6], otherData[3] * sd[7]));
        dd[7] = Math.fma(otherData[7], sd[8], Math.fma(otherData[1], sd[6], otherData[4] * sd[7]));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preMul(Float3x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Float3x3Impl) other).properties;
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
    @Mutated public Float3x3 preMul(Float3x3R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        int q = ((Float3x3Impl) other).properties;
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
     * Pre-multiply the transformation {@code other} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preMul(Float3x3R other, @Mutated Double3x3 dest) {
        return preMul(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), dest);
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
    public Float3x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(m02, sd[2], Math.fma(m00, sd[0], m01 * sd[1]));
        float _buf1 = Math.fma(m12, sd[2], Math.fma(m10, sd[0], m11 * sd[1]));
        dd[2] = Math.fma(m22, sd[2], Math.fma(m20, sd[0], m21 * sd[1]));
        float _buf2 = Math.fma(m02, sd[5], Math.fma(m00, sd[3], m01 * sd[4]));
        float _buf3 = Math.fma(m12, sd[5], Math.fma(m10, sd[3], m11 * sd[4]));
        dd[5] = Math.fma(m22, sd[5], Math.fma(m20, sd[3], m21 * sd[4]));
        float _buf4 = Math.fma(m02, sd[8], Math.fma(m00, sd[6], m01 * sd[7]));
        float _buf5 = Math.fma(m12, sd[8], Math.fma(m10, sd[6], m11 * sd[7]));
        dd[8] = Math.fma(m22, sd[8], Math.fma(m20, sd[6], m21 * sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(m02, sd[2], Math.fma(m00, sd[0], m01 * sd[1]));
        float _buf1 = Math.fma(m12, sd[2], Math.fma(m10, sd[0], m11 * sd[1]));
        dd[2] = Math.fma(m22, sd[2], Math.fma(m20, sd[0], m21 * sd[1]));
        float _buf2 = Math.fma(m02, sd[5], Math.fma(m00, sd[3], m01 * sd[4]));
        float _buf3 = Math.fma(m12, sd[5], Math.fma(m10, sd[3], m11 * sd[4]));
        dd[5] = Math.fma(m22, sd[5], Math.fma(m20, sd[3], m21 * sd[4]));
        float _buf4 = Math.fma(m02, sd[8], Math.fma(m00, sd[6], m01 * sd[7]));
        float _buf5 = Math.fma(m12, sd[8], Math.fma(m10, sd[6], m11 * sd[7]));
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
    private Float3x3 preMul_identity(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0f;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity_self(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0f;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0f;
        float _buf0 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_self(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        float _buf0 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        float _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[2] = 0.0f;
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0f;
        float _buf1 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_self(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        float _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        float _buf1 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Float3x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = 0.0f;
        float _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = 0.0f;
        float _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine_self(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        float _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float2x2R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = sd[2];
        float _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = sd[5];
        float _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preMul(Float2x2R other, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preMul(Float2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity_self(other, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_self(other, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal_self(other, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_self(other, this);
        return preMul_general(other, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preMul(Float2x2R other, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = sd[2];
        float _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = sd[5];
        float _buf2 = Math.fma(otherData[0], sd[6], otherData[2] * sd[7]);
        dd[7] = Math.fma(otherData[1], sd[6], otherData[3] * sd[7]);
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
    private Float3x3 preMul_general(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[4], sd[2], Math.fma(otherData[0], sd[0], otherData[2] * sd[1]));
        dd[1] = Math.fma(otherData[5], sd[2], Math.fma(otherData[1], sd[0], otherData[3] * sd[1]));
        dd[2] = sd[2];
        float _buf1 = Math.fma(otherData[4], sd[5], Math.fma(otherData[0], sd[3], otherData[2] * sd[4]));
        dd[4] = Math.fma(otherData[5], sd[5], Math.fma(otherData[1], sd[3], otherData[3] * sd[4]));
        dd[5] = sd[5];
        float _buf2 = Math.fma(otherData[4], sd[8], Math.fma(otherData[0], sd[6], otherData[2] * sd[7]));
        dd[7] = Math.fma(otherData[5], sd[8], Math.fma(otherData[1], sd[6], otherData[3] * sd[7]));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0f;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0f;
        dd[6] = otherData[4];
        dd[7] = otherData[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = 0.0f;
        dd[3] = otherData[2];
        dd[4] = otherData[3];
        dd[5] = 0.0f;
        float _buf0 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[4], otherData[2] * sd[1]);
        float _buf0 = Math.fma(otherData[1], sd[4], otherData[3] * sd[1]);
        dd[2] = 0.0f;
        dd[3] = Math.fma(otherData[2], sd[4], -(otherData[0] * sd[1]));
        dd[4] = Math.fma(otherData[3], sd[4], -(otherData[1] * sd[1]));
        dd[5] = 0.0f;
        float _buf1 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0f;
        dd[1] = _buf0;
        dd[6] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = 0.0f;
        float _buf1 = Math.fma(otherData[0], sd[3], otherData[2] * sd[4]);
        dd[4] = Math.fma(otherData[1], sd[3], otherData[3] * sd[4]);
        dd[5] = 0.0f;
        float _buf2 = Math.fma(otherData[0], sd[6], Math.fma(otherData[2], sd[7], otherData[4]));
        dd[7] = Math.fma(otherData[1], sd[6], Math.fma(otherData[3], sd[7], otherData[5]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity_translation(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = otherData[4];
        dd[7] = otherData[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = otherData[4] + sd[6];
        dd[7] = otherData[5] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float2x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = otherData[4] + sd[6];
        dd[7] = otherData[5] + sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float2x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[4], sd[2], sd[0]);
        dd[1] = Math.fma(otherData[5], sd[2], sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(otherData[4], sd[5], sd[3]);
        dd[4] = Math.fma(otherData[5], sd[5], sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(otherData[4], sd[8], sd[6]);
        dd[7] = Math.fma(otherData[5], sd[8], sd[7]);
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preMul(Float2x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
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
    @Mutated public Float3x3 preMul(Float2x3R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.float3x3());
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
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
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preMul(Float2x3R other, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[4], sd[2], Math.fma(otherData[0], sd[0], otherData[2] * sd[1]));
        dd[1] = Math.fma(otherData[5], sd[2], Math.fma(otherData[1], sd[0], otherData[3] * sd[1]));
        dd[2] = sd[2];
        float _buf1 = Math.fma(otherData[4], sd[5], Math.fma(otherData[0], sd[3], otherData[2] * sd[4]));
        dd[4] = Math.fma(otherData[5], sd[5], Math.fma(otherData[1], sd[3], otherData[3] * sd[4]));
        dd[5] = sd[5];
        float _buf2 = Math.fma(otherData[4], sd[8], Math.fma(otherData[0], sd[6], otherData[2] * sd[7]));
        dd[7] = Math.fma(otherData[5], sd[8], Math.fma(otherData[1], sd[6], otherData[3] * sd[7]));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
    public Float3x3 addScaled(Float3x3R other, float weight, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(weight, otherData[0], sd[0]);
        dd[1] = Math.fma(weight, otherData[1], sd[1]);
        dd[2] = Math.fma(weight, otherData[2], sd[2]);
        dd[3] = Math.fma(weight, otherData[3], sd[3]);
        dd[4] = Math.fma(weight, otherData[4], sd[4]);
        dd[5] = Math.fma(weight, otherData[5], sd[5]);
        dd[6] = Math.fma(weight, otherData[6], sd[6]);
        dd[7] = Math.fma(weight, otherData[7], sd[7]);
        dd[8] = Math.fma(weight, otherData[8], sd[8]);
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 addScaled(Float3x3R other, float weight, @Mutated Double3x3 dest) {
        return addScaled(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), weight, dest);
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
    public Float3x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float weight, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m20, sd[2]);
        dd[3] = Math.fma(weight, m01, sd[3]);
        dd[4] = Math.fma(weight, m11, sd[4]);
        dd[5] = Math.fma(weight, m21, sd[5]);
        dd[6] = Math.fma(weight, m02, sd[6]);
        dd[7] = Math.fma(weight, m12, sd[7]);
        dd[8] = Math.fma(weight, m22, sd[8]);
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) scaled by {@code weight} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float weight, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    public @Mutated Float3x3 makeOuterProduct(Float3R col, Float3R row) {
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
    @Mutated public Float3x3 makeOuterProduct(float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        float[] dd = this.data;
        dd[0] = colX * rowX;
        dd[1] = colY * rowX;
        dd[2] = colZ * rowX;
        dd[3] = colX * rowY;
        dd[4] = colY * rowY;
        dd[5] = colZ * rowY;
        dd[6] = colX * rowZ;
        dd[7] = colY * rowZ;
        dd[8] = colZ * rowZ;
        ((Float3x3Impl) this).properties = 0;
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
    public Float3x3 lookAlong(Float3R dir, Float3R up, @Mutated Float3x3 dest) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 lookAlong(Float3R dir, Float3R up, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 lookAlong(Float3R dir, Float3R up) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_identity(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirY, _sp0, upY);
        float _t23 = Math.fma(-dirZ, _sp0, upZ);
        float _t24 = Math.fma(-dirX, _sp0, upX);
        float _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        float _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        float _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        float _ct0 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        float _t39 = _t31 * _t38;
        dd[0] = _t39;
        return lookAlong_identity_s5e42c5a4_1(dest, dd, dirY * _t14, dirZ * _t14, dirX * _t14, _t39, _t32 * _t38, _t33 * _t38);
    }

    /** Piece 2 of {@code lookAlong_identity}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_identity_s5e42c5a4_1(Float3x3 dest, float[] dd, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        dd[1] = _t41;
        dd[2] = _t40;
        dd[3] = Math.fma(_t15, _t40, -(_t16 * _t41));
        dd[4] = Math.fma(_t16, _t39, -(_t17 * _t40));
        dd[5] = Math.fma(_t17, _t41, -(_t15 * _t39));
        dd[6] = _t17;
        dd[7] = _t15;
        dd[8] = _t16;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_translation(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        return lookAlong_translation_sb58f8269_1(dirX, dirY, dest, sd, dd, _t14, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_translation}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_translation_sb58f8269_1(float dirX, float dirY, Float3x3 dest, float[] sd, float[] dd, float _t14, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        float _t44 = Math.fma(_t15, _t41, -(_t16 * _t40));
        dd[0] = Math.fma(sd[6], _t39, _t40);
        dd[1] = Math.fma(sd[7], _t39, _t41);
        dd[2] = _t39;
        dd[3] = Math.fma(sd[6], _t44, Math.fma(_t16, _t39, -(_t17 * _t41)));
        dd[4] = Math.fma(sd[7], _t44, Math.fma(_t17, _t40, -(_t15 * _t39)));
        dd[5] = _t44;
        dd[6] = Math.fma(dirX, _t14, sd[6] * _t17);
        dd[7] = Math.fma(dirY, _t14, sd[7] * _t17);
        dd[8] = _t17;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_orthogonal(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        return lookAlong_orthogonal_sa74b2695_1(dest, sd, dd, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_orthogonal_sa74b2695_1(Float3x3 dest, float[] sd, float[] dd, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        float _t48 = Math.fma(_t15, _t41, -(_t16 * _t40));
        float _t49 = Math.fma(_t16, _t39, -(_t17 * _t41));
        float _t50 = Math.fma(_t17, _t40, -(_t15 * _t39));
        float _buf0 = Math.fma(sd[6], _t39, Math.fma(sd[0], _t40, sd[3] * _t41));
        float _buf1 = Math.fma(sd[7], _t39, Math.fma(sd[1], _t40, sd[4] * _t41));
        dd[2] = _t39;
        float _buf2 = Math.fma(sd[6], _t48, Math.fma(sd[0], _t49, sd[3] * _t50));
        float _buf3 = Math.fma(sd[7], _t48, Math.fma(sd[1], _t49, sd[4] * _t50));
        dd[5] = _t48;
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t15, sd[3] * _t16));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t15, sd[4] * _t16));
        dd[8] = _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_general(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        return lookAlong_general_s171035aa_1(dest, sd, dd, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_general_s171035aa_1(Float3x3 dest, float[] sd, float[] dd, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        float _t48 = Math.fma(_t15, _t41, -(_t16 * _t40));
        float _t49 = Math.fma(_t16, _t39, -(_t17 * _t41));
        float _t50 = Math.fma(_t17, _t40, -(_t15 * _t39));
        float _buf0 = Math.fma(sd[6], _t39, Math.fma(sd[0], _t40, sd[3] * _t41));
        float _buf1 = Math.fma(sd[7], _t39, Math.fma(sd[1], _t40, sd[4] * _t41));
        float _buf2 = Math.fma(sd[8], _t39, Math.fma(sd[2], _t40, sd[5] * _t41));
        float _buf3 = Math.fma(sd[6], _t48, Math.fma(sd[0], _t49, sd[3] * _t50));
        float _buf4 = Math.fma(sd[7], _t48, Math.fma(sd[1], _t49, sd[4] * _t50));
        float _buf5 = Math.fma(sd[8], _t48, Math.fma(sd[2], _t49, sd[5] * _t50));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t15, sd[3] * _t16));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t15, sd[4] * _t16));
        return lookAlong_general_s171035aa_2(dest, sd, dd, _t15, _t16, _t17, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5);
    }

    /** Piece 3 of {@code lookAlong_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_general_s171035aa_2(Float3x3 dest, float[] sd, float[] dd, float _t15, float _t16, float _t17, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t15, sd[5] * _t16));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.RETURN_NEW) return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ, this);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _ct0 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        return lookAlong_s57c82d8c_1(dest, sd, dd, dirX * _t14, dirY * _t14, dirZ * _t14, _t31 * _t38, _t33 * _t38, _t32 * _t38);
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_s57c82d8c_1(Double3x3 dest, float[] sd, double[] dd, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        float _t48 = Math.fma(_t15, _t41, -(_t16 * _t40));
        float _t49 = Math.fma(_t16, _t39, -(_t17 * _t41));
        float _t50 = Math.fma(_t17, _t40, -(_t15 * _t39));
        float _buf0 = Math.fma(sd[6], _t39, Math.fma(sd[0], _t40, sd[3] * _t41));
        float _buf1 = Math.fma(sd[7], _t39, Math.fma(sd[1], _t40, sd[4] * _t41));
        float _buf2 = Math.fma(sd[8], _t39, Math.fma(sd[2], _t40, sd[5] * _t41));
        float _buf3 = Math.fma(sd[6], _t48, Math.fma(sd[0], _t49, sd[3] * _t50));
        float _buf4 = Math.fma(sd[7], _t48, Math.fma(sd[1], _t49, sd[4] * _t50));
        float _buf5 = Math.fma(sd[8], _t48, Math.fma(sd[2], _t49, sd[5] * _t50));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t15, sd[3] * _t16));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t15, sd[4] * _t16));
        return lookAlong_s57c82d8c_2(dest, sd, dd, _t15, _t16, _t17, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_s57c82d8c_2(Double3x3 dest, float[] sd, double[] dd, float _t15, float _t16, float _t17, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
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
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Float3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) Math.sqrt(_t16));
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
        float _t34, _t35, _t39;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t34 = 0.0f;
            _t35 = -_t26;
            _t39 = _t25;
        } else {
            _t34 = _t26;
            _t35 = 0.0f;
            _t39 = -_t24;
        }
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_degenerate_s5267b78e_1(dest, sd, dd, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t35, _t39, Math.fma(_t41, _t25, _t22), Math.fma(_t41, _t26, _t23), Math.fma(_t41, _t24, _t21));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_degenerate_s5267b78e_1(Float3x3 dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t34, float _t35, float _t39, float _t42, float _t43, float _t44) {
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t64 = (1.0f / (float) Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0f / (float) Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        return lookAlong_degenerate_s5267b78e_2(dest, sd, dd, _t24, _t25, _t26, Math.fma(_t66, _t24, -(_t65 * _t25)), Math.fma(_t65, _t26, -(_t67 * _t24)), Math.fma(_t67, _t25, -(_t66 * _t26)), Math.fma(sd[6], _t65, Math.fma(sd[0], _t66, sd[3] * _t67)), Math.fma(sd[7], _t65, Math.fma(sd[1], _t66, sd[4] * _t67)), Math.fma(sd[8], _t65, Math.fma(sd[2], _t66, sd[5] * _t67)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 lookAlong_degenerate_s5267b78e_2(Float3x3 dest, float[] sd, float[] dd, float _t24, float _t25, float _t26, float _t74, float _t75, float _t76, float _buf0, float _buf1, float _buf2) {
        float _buf3 = Math.fma(sd[6], _t76, Math.fma(sd[0], _t75, sd[3] * _t74));
        float _buf4 = Math.fma(sd[7], _t76, Math.fma(sd[1], _t75, sd[4] * _t74));
        float _buf5 = Math.fma(sd[8], _t76, Math.fma(sd[2], _t75, sd[5] * _t74));
        dd[6] = Math.fma(sd[6], _t24, Math.fma(sd[0], _t25, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t24, Math.fma(sd[1], _t25, sd[4] * _t26));
        dd[8] = Math.fma(sd[8], _t24, Math.fma(sd[2], _t25, sd[5] * _t26));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Double3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) Math.sqrt(_t16));
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
        float _t34, _t35, _t39;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t34 = 0.0f;
            _t35 = -_t26;
            _t39 = _t25;
        } else {
            _t34 = _t26;
            _t35 = 0.0f;
            _t39 = -_t24;
        }
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_degenerate_sa90dc201_1(dest, sd, dd, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t35, _t39, Math.fma(_t41, _t25, _t22), Math.fma(_t41, _t26, _t23), Math.fma(_t41, _t24, _t21));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_degenerate_sa90dc201_1(Double3x3 dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t34, float _t35, float _t39, float _t42, float _t43, float _t44) {
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t64 = (1.0f / (float) Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0f / (float) Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        return lookAlong_degenerate_sa90dc201_2(dest, sd, dd, _t24, _t25, _t26, Math.fma(_t66, _t24, -(_t65 * _t25)), Math.fma(_t65, _t26, -(_t67 * _t24)), Math.fma(_t67, _t25, -(_t66 * _t26)), Math.fma(sd[6], _t65, Math.fma(sd[0], _t66, sd[3] * _t67)), Math.fma(sd[7], _t65, Math.fma(sd[1], _t66, sd[4] * _t67)), Math.fma(sd[8], _t65, Math.fma(sd[2], _t66, sd[5] * _t67)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3x3 lookAlong_degenerate_sa90dc201_2(Double3x3 dest, float[] sd, double[] dd, float _t24, float _t25, float _t26, float _t74, float _t75, float _t76, float _buf0, float _buf1, float _buf2) {
        float _buf3 = Math.fma(sd[6], _t76, Math.fma(sd[0], _t75, sd[3] * _t74));
        float _buf4 = Math.fma(sd[7], _t76, Math.fma(sd[1], _t75, sd[4] * _t74));
        float _buf5 = Math.fma(sd[8], _t76, Math.fma(sd[2], _t75, sd[5] * _t74));
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
    public @Mutated Float3x3 makeFromDualQuat(FloatDualQuatR dq) {
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
    @Mutated public Float3x3 makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float[] dd = this.data;
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        dd[0] = Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * Math.fma(dqRX, dqRY, _t2);
        dd[2] = Math.fma(-2.0f, _t3, _sp0 * dqRZ);
        dd[3] = Math.fma(-2.0f, _t2, _sp0 * dqRY);
        dd[4] = Math.fma(-2.0f, _t4, _t6);
        dd[5] = 2.0f * Math.fma(dqRX, dqRW, _t5);
        dd[6] = 2.0f * Math.fma(dqRX, dqRZ, _t3);
        dd[7] = Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5);
        dd[8] = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotation(float angle) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
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
    public @Mutated Float3x3 makeRotationAxis(float angle, Float3R axis) {
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
    @Mutated public Float3x3 makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        dd[0] = Math.fma(_t5, axisX * axisX, _t1);
        dd[1] = Math.fma(axisZ, _t0, _t5 * _t2);
        dd[2] = Math.fma(_t5, _t3, -(axisY * _t0));
        dd[3] = Math.fma(_t5, _t2, -(axisZ * _t0));
        dd[4] = Math.fma(_t5, axisY * axisY, _t1);
        dd[5] = Math.fma(axisX, _t0, _t5 * _t4);
        dd[6] = Math.fma(axisY, _t0, _t5 * _t3);
        dd[7] = Math.fma(_t5, _t4, -(axisX * _t0));
        dd[8] = Math.fma(_t5, axisZ * axisZ, _t1);
        ((Float3x3Impl) this).properties = 0;
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
    public @Mutated Float3x3 makeRotationLookAlong(Float3R dir, Float3R up) {
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
    @Mutated public Float3x3 makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) Math.sqrt(_t7));
        float _t22 = Math.fma(-dirY, _sp0, upY);
        float _t23 = Math.fma(-dirZ, _sp0, upZ);
        float _t24 = Math.fma(-dirX, _sp0, upX);
        float _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        float _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        float _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        float _ct0 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_ct0 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t38 = (1.0f / (float) Math.sqrt(_ct0));
        float _t39 = _t31 * _t38;
        float _t40 = _t32 * _t38;
        float _t41 = _t33 * _t38;
        dd[0] = _t39;
        dd[1] = _t41;
        dd[2] = _t40;
        return makeRotationLookAlong_s25627a91_1(dd, dirY * _t14, dirZ * _t14, dirX * _t14, _t39, _t40, _t41);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private Float3x3 makeRotationLookAlong_s25627a91_1(float[] dd, float _t15, float _t16, float _t17, float _t39, float _t40, float _t41) {
        dd[3] = Math.fma(_t15, _t40, -(_t16 * _t41));
        dd[4] = Math.fma(_t16, _t39, -(_t17 * _t40));
        dd[5] = Math.fma(_t17, _t41, -(_t15 * _t39));
        dd[6] = _t17;
        dd[7] = _t15;
        dd[8] = _t16;
        ((Float3x3Impl) this).properties = 0;
        return this;
    }



    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private Float3x3 makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) Math.sqrt(_t16));
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
        float _t34, _t35, _t39;
        if (Math.abs(_t25) > Math.abs(_t24)) {
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
        return makeRotationLookAlong_degenerate_s1ae3d4da_1(dd, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t35, _t39, _t42, _t43, Math.fma(_t41, _t24, _t21), Math.fma(_t42, _t26, -(_t43 * _t25)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 makeRotationLookAlong_degenerate_s1ae3d4da_1(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t34, float _t35, float _t39, float _t42, float _t43, float _t44, float _t53) {
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t64 = (1.0f / (float) Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0f / (float) Math.sqrt(_t59));
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
        ((Float3x3Impl) this).properties = 0;
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
    public @Mutated Float3x3 makeRotationQuat(FloatQuatR q) {
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
    @Mutated public Float3x3 makeRotationQuat(float qX, float qY, float qZ, float qW) {
        float[] dd = this.data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        dd[1] = 2.0f * Math.fma(qX, qY, _t1);
        dd[2] = 2.0f * Math.fma(qX, qZ, -_t2);
        dd[3] = 2.0f * Math.fma(qX, qY, -_t1);
        dd[4] = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(qX, qW, qY * qZ);
        dd[6] = 2.0f * Math.fma(qX, qZ, _t2);
        dd[7] = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        dd[8] = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationX(float angle) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1;
        dd[5] = _t0;
        dd[6] = 0.0f;
        dd[7] = -_t0;
        dd[8] = _t1;
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t6, _t4, _t1 * _t5);
        dd[2] = Math.fma(_t2, _t1, -(_t7 * _t4));
        dd[3] = -(_t1 * _t3);
        dd[4] = Math.fma(_t5, _t4, -(_t6 * _t1));
        dd[5] = Math.fma(_t7, _t1, _t2 * _t4);
        dd[6] = _t0;
        dd[7] = -(_t2 * _t3);
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationXZY(float angleX, float angleZ, float angleY) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t7, _t3, _t2 * _t0);
        dd[2] = Math.fma(_t6, _t3, -(_t0 * _t5));
        dd[3] = -_t1;
        dd[4] = _t5 * _t4;
        dd[5] = _t2 * _t4;
        dd[6] = _t0 * _t4;
        dd[7] = Math.fma(_t7, _t0, -(_t2 * _t3));
        dd[8] = Math.fma(_t6, _t0, _t5 * _t3);
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationY(float angle) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = 0.0f;
        dd[2] = -_t0;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = _t0;
        dd[7] = 0.0f;
        dd[8] = _t1;
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dd[0] = Math.fma(_t6, _t2, _t3 * _t4);
        dd[1] = _t2 * _t5;
        dd[2] = Math.fma(_t7, _t2, -(_t1 * _t4));
        dd[3] = Math.fma(_t6, _t4, -(_t2 * _t3));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t7, _t4, _t1 * _t2);
        dd[6] = _t1 * _t5;
        dd[7] = -_t0;
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationYZX(float angleY, float angleZ, float angleX) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        dd[0] = _t3 * _t4;
        dd[1] = _t1;
        dd[2] = -(_t0 * _t4);
        dd[3] = Math.fma(_t2, _t0, -(_t7 * _t5));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t6, _t5, _t2 * _t3);
        dd[6] = Math.fma(_t7, _t2, _t0 * _t5);
        dd[7] = -(_t2 * _t4);
        dd[8] = Math.fma(_t5, _t3, -(_t6 * _t2));
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationZ(float angle) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
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
    @Mutated public Float3x3 makeRotationZXY(float angleZ, float angleX, float angleY) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dd[0] = Math.fma(_t3, _t4, -(_t6 * _t0));
        dd[1] = Math.fma(_t7, _t0, _t1 * _t3);
        dd[2] = -(_t0 * _t5);
        dd[3] = -(_t1 * _t5);
        dd[4] = _t5 * _t4;
        dd[5] = _t2;
        dd[6] = Math.fma(_t6, _t3, _t0 * _t4);
        dd[7] = Math.fma(_t0, _t1, -(_t7 * _t3));
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) this).properties = 0;
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
    @Mutated public Float3x3 makeRotationZYX(float angleZ, float angleY, float angleX) {
        float[] dd = this.data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        dd[0] = _t3 * _t4;
        dd[1] = _t1 * _t3;
        dd[2] = -_t0;
        dd[3] = Math.fma(_t7, _t2, -(_t1 * _t5));
        dd[4] = Math.fma(_t6, _t2, _t5 * _t4);
        dd[5] = _t2 * _t3;
        dd[6] = Math.fma(_t7, _t5, _t2 * _t1);
        dd[7] = Math.fma(_t6, _t5, -(_t2 * _t4));
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) this).properties = 0;
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
    public @Mutated Float3x3 makeScaling(Float2R v) {
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
    @Mutated public Float3x3 makeScaling(float vX, float vY) {
        float[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = vY;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
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
    @Mutated public Float3x3 makeScaling(float s) {
        float[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
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
    public @Mutated Float3x3 makeTranslation(Float2R v) {
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
    @Mutated public Float3x3 makeTranslation(float vX, float vY) {
        float[] dd = this.data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = vX;
        dd[7] = vY;
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_TRANSLATION;
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
    @Mutated public Float3x3 makeView(float left, float right, float bottom, float top) {
        float[] dd = this.data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0f;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_orthogonal_affine(float angle, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = 0.0f;
        float _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        dd[5] = 0.0f;
        float _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_orthogonal_affine_self(float angle, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        float _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        float _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Float3x3 preRotate_identity(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Float3x3 preRotate_identity_self(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_translation(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0f;
        float _buf0 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_translation_self(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        float _buf0 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_general(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = sd[2];
        float _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        dd[5] = sd[5];
        float _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preRotate(float angle, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preRotate(float angle) {
        if (Joml.RETURN_NEW) return preRotate(angle, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_self(angle, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotate_orthogonal_affine_self(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotate_general(angle, this);
    }


    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
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
    public Double3x3 preRotate(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = sd[2];
        float _buf1 = Math.fma(sd[3], _t1, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t1);
        dd[5] = sd[5];
        float _buf2 = Math.fma(sd[6], _t1, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t1);
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
    public Float3x3 preRotateAround(float angle, Float2R pivot, @Mutated Float3x3 dest) {
        return preRotateAround(angle, pivot.x(), pivot.y(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateAround(float angle, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preRotateAround(float angle, Float2R pivot) {
        return preRotateAround(angle, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_orthogonal_affine(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        dd[2] = 0.0f;
        float _buf1 = Math.fma(sd[3], _t2, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t2);
        dd[5] = 0.0f;
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_orthogonal_affine_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        float _buf1 = Math.fma(sd[3], _t2, -(sd[4] * _t0));
        dd[4] = Math.fma(sd[3], _t0, sd[4] * _t2);
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float3x3 preRotateAround_identity(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, _t5, pivotY * _t0);
        dd[7] = Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotateAround} and
     * {@code rotateAround}; reached only through them.
     */
    private Float3x3 preRotateAround_identity_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[6] = Math.fma(pivotX, _t5, pivotY * _t0);
        dd[7] = Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0f;
        float _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_translation_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        float _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[6], _t2, -(sd[7] * _t0));
        dd[7] = Math.fma(sd[6], _t0, sd[7] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[2], _t9, Math.fma(sd[0], _t3, -(sd[1] * _t0)));
        dd[1] = Math.fma(sd[2], _t10, Math.fma(sd[0], _t0, sd[1] * _t3));
        dd[2] = sd[2];
        float _buf1 = Math.fma(sd[5], _t9, Math.fma(sd[3], _t3, -(sd[4] * _t0)));
        dd[4] = Math.fma(sd[5], _t10, Math.fma(sd[3], _t0, sd[4] * _t3));
        dd[5] = sd[5];
        float _buf2 = Math.fma(sd[8], _t9, Math.fma(sd[6], _t3, -(sd[7] * _t0)));
        dd[7] = Math.fma(sd[8], _t10, Math.fma(sd[6], _t0, sd[7] * _t3));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preRotateAround(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preRotateAround(float angle, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAround_orthogonal_affine_self(angle, pivotX, pivotY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preRotateAround_general(angle, pivotX, pivotY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateAround(float angle, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[2], _t9, Math.fma(sd[0], _t3, -(sd[1] * _t0)));
        dd[1] = Math.fma(sd[2], _t10, Math.fma(sd[0], _t0, sd[1] * _t3));
        dd[2] = sd[2];
        float _buf1 = Math.fma(sd[5], _t9, Math.fma(sd[3], _t3, -(sd[4] * _t0)));
        dd[4] = Math.fma(sd[5], _t10, Math.fma(sd[3], _t0, sd[4] * _t3));
        dd[5] = sd[5];
        float _buf2 = Math.fma(sd[8], _t9, Math.fma(sd[6], _t3, -(sd[7] * _t0)));
        dd[7] = Math.fma(sd[8], _t10, Math.fma(sd[6], _t0, sd[7] * _t3));
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
    public Float3x3 preRotateAxis(float angle, Float3R axis, @Mutated Float3x3 dest) {
        return preRotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis {@code axis} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
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
    public Double3x3 preRotateAxis(float angle, Float3R axis, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preRotateAxis(float angle, Float3R axis) {
        return preRotateAxis(angle, axis.x(), axis.y(), axis.z());
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAxis} and {@code rotateAxis}; reached only
     * through them.
     */
    private Float3x3 preRotateAxis_identity(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        dd[0] = Math.fma(_t5, axisX * axisX, _t1);
        dd[1] = Math.fma(axisZ, _t0, _t5 * _t2);
        dd[2] = Math.fma(_t5, _t3, -(axisY * _t0));
        dd[3] = Math.fma(_t5, _t2, -(axisZ * _t0));
        dd[4] = Math.fma(_t5, axisY * axisY, _t1);
        dd[5] = Math.fma(axisX, _t0, _t5 * _t4);
        dd[6] = Math.fma(axisY, _t0, _t5 * _t3);
        dd[7] = Math.fma(_t5, _t4, -(axisX * _t0));
        dd[8] = Math.fma(_t5, axisZ * axisZ, _t1);
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_translation(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
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
        dd[0] = _t14;
        dd[1] = _t16;
        dd[2] = _t19;
        dd[3] = _t18;
        dd[4] = _t15;
        dd[5] = _t17;
        float _buf0 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(sd[6], _t14, sd[7] * _t18);
        float _buf1 = Math.fma(sd[6], _t16, sd[7] * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        dd[8] = Math.fma(sd[6], _t19, Math.fma(sd[7], _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
        dd[6] = _buf0;
        dd[7] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
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
        float _buf0 = Math.fma(sd[0], _t14, sd[1] * _t18);
        float _buf1 = Math.fma(sd[0], _t16, sd[1] * _t15);
        dd[2] = Math.fma(sd[0], _t19, sd[1] * _t17);
        float _buf2 = Math.fma(sd[3], _t14, sd[4] * _t18);
        float _buf3 = Math.fma(sd[3], _t16, sd[4] * _t15);
        dd[5] = Math.fma(sd[3], _t19, sd[4] * _t17);
        return preRotateAxis_orthogonal_sc467d175_1(axisX, axisY, axisZ, dest, sd, dd, _t0, _t1, _t5, _t7, _t9, _t14, _t15, _t16, _t17, _t18, _t19, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code preRotateAxis_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 preRotateAxis_orthogonal_sc467d175_1(float axisX, float axisY, float axisZ, Float3x3 dest, float[] sd, float[] dd, float _t0, float _t1, float _t5, float _t7, float _t9, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(sd[6], _t14, sd[7] * _t18);
        float _buf5 = Math.fma(sd[6], _t16, sd[7] * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        dd[8] = Math.fma(sd[6], _t19, Math.fma(sd[7], _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_general(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
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
        float _buf0 = Math.fma(sd[2], _t21, Math.fma(sd[0], _t18, sd[1] * _t24));
        float _buf1 = Math.fma(sd[2], _t25, Math.fma(sd[0], _t22, sd[1] * _t19));
        dd[2] = Math.fma(sd[2], _t20, Math.fma(sd[0], _t26, sd[1] * _t23));
        return preRotateAxis_general_s8b16a1b6_1(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _buf0, _buf1);
    }

    /** Piece 2 of {@code preRotateAxis_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 preRotateAxis_general_s8b16a1b6_1(Float3x3 dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, Math.fma(sd[3], _t18, sd[4] * _t24));
        float _buf3 = Math.fma(sd[5], _t25, Math.fma(sd[3], _t22, sd[4] * _t19));
        dd[5] = Math.fma(sd[5], _t20, Math.fma(sd[3], _t26, sd[4] * _t23));
        float _buf4 = Math.fma(sd[8], _t21, Math.fma(sd[6], _t18, sd[7] * _t24));
        float _buf5 = Math.fma(sd[8], _t25, Math.fma(sd[6], _t22, sd[7] * _t19));
        dd[8] = Math.fma(sd[8], _t20, Math.fma(sd[6], _t26, sd[7] * _t23));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preRotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preRotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (Joml.RETURN_NEW) return preRotateAxis(angle, axisX, axisY, axisZ, Joml.float3x3());
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
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 preRotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Double3x3 dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return preRotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return preRotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return preRotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        return preRotateAxis_sdf63cffe_1(dest, sd, dd, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), _t24, Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(sd[2], _t21, Math.fma(sd[0], _t18, sd[1] * _t24)));
    }

    /** Piece 2 of {@code preRotateAxis}, split to fit the inline budget; reached only through it. */
    private Double3x3 preRotateAxis_sdf63cffe_1(Double3x3 dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _buf0) {
        float _buf1 = Math.fma(sd[2], _t25, Math.fma(sd[0], _t22, sd[1] * _t19));
        dd[2] = Math.fma(sd[2], _t20, Math.fma(sd[0], _t26, sd[1] * _t23));
        float _buf2 = Math.fma(sd[5], _t21, Math.fma(sd[3], _t18, sd[4] * _t24));
        float _buf3 = Math.fma(sd[5], _t25, Math.fma(sd[3], _t22, sd[4] * _t19));
        dd[5] = Math.fma(sd[5], _t20, Math.fma(sd[3], _t26, sd[4] * _t23));
        float _buf4 = Math.fma(sd[8], _t21, Math.fma(sd[6], _t18, sd[7] * _t24));
        float _buf5 = Math.fma(sd[8], _t25, Math.fma(sd[6], _t22, sd[7] * _t19));
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
    public Float3x3 preRotateX(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        float _buf0 = Math.fma(sd[1], _t1, -(sd[2] * _t0));
        dd[2] = Math.fma(sd[1], _t0, sd[2] * _t1);
        dd[3] = sd[3];
        float _buf1 = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        dd[6] = sd[6];
        float _buf2 = Math.fma(sd[7], _t1, -(sd[8] * _t0));
        dd[8] = Math.fma(sd[7], _t0, sd[8] * _t1);
        dd[1] = _buf0;
        dd[4] = _buf1;
        dd[7] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
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
    public Double3x3 preRotateX(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        float _buf0 = Math.fma(sd[1], _t1, -(sd[2] * _t0));
        dd[2] = Math.fma(sd[1], _t0, sd[2] * _t1);
        dd[3] = sd[3];
        float _buf1 = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        dd[6] = sd[6];
        float _buf2 = Math.fma(sd[7], _t1, -(sd[8] * _t0));
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
    public Float3x3 preRotateY(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        dd[1] = sd[1];
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        float _buf1 = Math.fma(sd[3], _t1, sd[5] * _t0);
        dd[4] = sd[4];
        dd[5] = Math.fma(sd[5], _t1, -(sd[3] * _t0));
        float _buf2 = Math.fma(sd[6], _t1, sd[8] * _t0);
        dd[7] = sd[7];
        dd[8] = Math.fma(sd[8], _t1, -(sd[6] * _t0));
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preRotateY(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        dd[1] = sd[1];
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        float _buf1 = Math.fma(sd[3], _t1, sd[5] * _t0);
        dd[4] = sd[4];
        dd[5] = Math.fma(sd[5], _t1, -(sd[3] * _t0));
        float _buf2 = Math.fma(sd[6], _t1, sd[8] * _t0);
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
    public Float3x3 preRotateZ(float angle, @Mutated Float3x3 dest) {
        return preRotate(angle, dest);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
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
    public Double3x3 preRotateZ(float angle, @Mutated Double3x3 dest) {
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
    @Mutated public Float3x3 preRotateZ(float angle) {
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
    public Float3x3 preScale(Float2R v, @Mutated Float3x3 dest) {
        return preScale(v.x(), v.y(), dest);
    }


    /**
     * Pre-multiply a scaling by {@code v} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScale(Float2R v, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preScale(Float2R v) {
        return preScale(v.x(), v.y());
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_identity(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = vY;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = vY;
        dd[5] = 0.0f;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = vX;
        dd[4] = vY;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = 0.0f;
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[5] = 0.0f;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal_self(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2];
        dd[3] = sd[3] * vX;
        dd[4] = sd[4] * vY;
        dd[5] = sd[5];
        dd[6] = sd[6] * vX;
        dd[7] = sd[7] * vY;
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preScale(float vX, float vY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preScale(float vX, float vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = vX;
            dd[4] = vY;
            ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal_self(vX, vY, this);
        return preScale_general(vX, vY, this);
    }


    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScale(float vX, float vY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_identity(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation_self(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s;
        dd[4] = s;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0f;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0f;
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal_self(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = sd[5];
        dd[6] = s * sd[6];
        dd[7] = s * sd[7];
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preScale(float s, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preScale(float s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = s;
            dd[4] = s;
            ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(s, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal_self(s, this);
        return preScale_general(s, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScale(float s, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    public Float3x3 preScaleAround(float s, Float2R pivot, @Mutated Float3x3 dest) {
        return preScaleAround(s, pivot.x(), pivot.y(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScaleAround(float s, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preScaleAround(float s, Float2R pivot) {
        return preScaleAround(s, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = pivotX * _t0;
        dd[7] = pivotY * _t0;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = pivotX * _t0;
        dd[7] = pivotY * _t0;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0f;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = Math.fma(s, sd[6], pivotX * _t0);
        dd[7] = Math.fma(s, sd[7], pivotY * _t0);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dd[0] = Math.fma(s, sd[0], sd[2] * _t1);
        dd[1] = Math.fma(s, sd[1], sd[2] * _t2);
        dd[2] = sd[2];
        dd[3] = Math.fma(s, sd[3], sd[5] * _t1);
        dd[4] = Math.fma(s, sd[4], sd[5] * _t2);
        dd[5] = sd[5];
        dd[6] = Math.fma(s, sd[6], sd[8] * _t1);
        dd[7] = Math.fma(s, sd[7], sd[8] * _t2);
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preScaleAround(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preScaleAround(float s, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal_self(s, pivotX, pivotY, this);
        return preScaleAround_general(s, pivotX, pivotY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScaleAround(float s, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
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
    public Float3x3 preScaleAround(Float2R s, Float2R pivot, @Mutated Float3x3 dest) {
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
    }


    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preScaleAround(Float2R s, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preScaleAround(Float2R s, Float2R pivot) {
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = sY;
        dd[5] = 0.0f;
        dd[6] = pivotX * (1.0f - sX);
        dd[7] = pivotY * (1.0f - sY);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = pivotX * (1.0f - sX);
        dd[7] = pivotY * (1.0f - sY);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = sY;
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, 1.0f - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sY * sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = Math.fma(pivotX, 1.0f - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sY * sd[7]);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = 0.0f;
        dd[3] = sX * sd[3];
        dd[4] = sY * sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, 1.0f - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sY * sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[3] = sX * sd[3];
        dd[4] = sY * sd[4];
        dd[6] = Math.fma(pivotX, 1.0f - sX, sX * sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sY * sd[7]);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dd[0] = Math.fma(sX, sd[0], sd[2] * _t2);
        dd[1] = Math.fma(sY, sd[1], sd[2] * _t3);
        dd[2] = sd[2];
        dd[3] = Math.fma(sX, sd[3], sd[5] * _t2);
        dd[4] = Math.fma(sY, sd[4], sd[5] * _t3);
        dd[5] = sd[5];
        dd[6] = Math.fma(sX, sd[6], sd[8] * _t2);
        dd[7] = Math.fma(sY, sd[7], sd[8] * _t3);
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preScaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preScaleAround(float sX, float sY, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal_self(sX, sY, pivotX, pivotY, this);
        return preScaleAround_general(sX, sY, pivotX, pivotY, this);
    }


    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 preScaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
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
    public Float3x3 preTranslate(Float2R v, @Mutated Float3x3 dest) {
        return preTranslate(v.x(), v.y(), dest);
    }


    /**
     * Pre-multiply a translation by {@code v} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preTranslate(Float2R v, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 preTranslate(Float2R v) {
        return preTranslate(v.x(), v.y());
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_orthogonal_affine(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_orthogonal_affine_self(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preTranslate} and {@code translate}; reached only through
     * them.
     */
    private Float3x3 preTranslate_translation(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float3x3 preTranslate_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[6] = sd[6] + vX;
        dd[7] = sd[7] + vY;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_general(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(sd[2], vX, sd[0]);
        dd[1] = Math.fma(sd[2], vY, sd[1]);
        dd[2] = sd[2];
        dd[3] = Math.fma(sd[5], vX, sd[3]);
        dd[4] = Math.fma(sd[5], vY, sd[4]);
        dd[5] = sd[5];
        dd[6] = Math.fma(sd[8], vX, sd[6]);
        dd[7] = Math.fma(sd[8], vY, sd[7]);
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 preTranslate(float vX, float vY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 preTranslate(float vX, float vY) {
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[6] = vX;
            dd[7] = vY;
            ((Float3x3Impl) this).properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_orthogonal_affine_self(vX, vY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return preTranslate_general(vX, vY, this);
    }


    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix and store the result
     * in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 preTranslate(float vX, float vY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_orthogonal_affine(float angle, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        dd[2] = 0.0f;
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_orthogonal_affine_self(float angle, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_translation(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_translation_self(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t1;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_general(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        float _buf2 = Math.fma(sd[2], _t1, sd[5] * _t0);
        dd[3] = Math.fma(sd[3], _t1, -(sd[0] * _t0));
        dd[4] = Math.fma(sd[4], _t1, -(sd[1] * _t0));
        dd[5] = Math.fma(sd[5], _t1, -(sd[2] * _t0));
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotate(float angle, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotate(float angle) {
        if (Joml.RETURN_NEW) return rotate(angle, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation_self(angle, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotate_orthogonal_affine_self(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotate_general(angle, this);
    }


    /**
     * Apply a rotation by {@code angle} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
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
    public Double3x3 rotate(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[4] * _t0);
        float _buf2 = Math.fma(sd[2], _t1, sd[5] * _t0);
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
    public Float3x3 rotateAround(float angle, Float2R pivot, @Mutated Float3x3 dest) {
        return rotateAround(angle, pivot.x(), pivot.y(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateAround(float angle, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 rotateAround(float angle, Float2R pivot) {
        return rotateAround(angle, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_orthogonal_affine(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        dd[2] = 0.0f;
        float _buf2 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        float _buf3 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        dd[5] = 0.0f;
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_orthogonal_affine_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        float _buf2 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        float _buf3 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }




    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = 0.0f;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, sd[6]));
        dd[7] = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, sd[7]));
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_translation_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[3] = -_t0;
        dd[4] = _t2;
        dd[6] = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, sd[6]));
        dd[7] = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, sd[7]));
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        float _buf2 = Math.fma(sd[2], _t2, sd[5] * _t0);
        float _buf3 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        float _buf4 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        float _buf5 = Math.fma(sd[5], _t2, -(sd[2] * _t0));
        dd[6] = Math.fma(sd[0], _t9, Math.fma(sd[3], _t10, sd[6]));
        dd[7] = Math.fma(sd[1], _t9, Math.fma(sd[4], _t10, sd[7]));
        dd[8] = Math.fma(sd[2], _t9, Math.fma(sd[5], _t10, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateAround(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateAround(float angle, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_self(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAround_orthogonal_affine_self(angle, pivotX, pivotY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateAround_general(angle, pivotX, pivotY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateAround(float angle, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[4] * _t0);
        float _buf2 = Math.fma(sd[2], _t2, sd[5] * _t0);
        float _buf3 = Math.fma(sd[3], _t2, -(sd[0] * _t0));
        float _buf4 = Math.fma(sd[4], _t2, -(sd[1] * _t0));
        float _buf5 = Math.fma(sd[5], _t2, -(sd[2] * _t0));
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
    public Float3x3 rotateAxis(float angle, Float3R axis, @Mutated Float3x3 dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
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
    public Double3x3 rotateAxis(float angle, Float3R axis, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 rotateAxis(float angle, Float3R axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
    }



    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_translation(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = 1.0f - _t1;
        float _t10 = Math.fma(_t7, axisZ * axisZ, _t1);
        float _t11 = Math.fma(axisX, _t0, _t7 * _t4);
        float _t12 = Math.fma(_t7, _t2, -(axisY * _t0));
        dd[0] = Math.fma(_t7, axisX * axisX, Math.fma(sd[6], _t12, _t1));
        dd[1] = Math.fma(sd[7], _t12, Math.fma(axisZ, _t0, _t7 * _t5));
        dd[2] = _t12;
        dd[3] = Math.fma(sd[6], _t11, Math.fma(_t7, _t5, -(axisZ * _t0)));
        dd[4] = Math.fma(_t7, axisY * axisY, Math.fma(sd[7], _t11, _t1));
        dd[5] = _t11;
        dd[6] = Math.fma(sd[6], _t10, Math.fma(axisY, _t0, _t7 * _t2));
        dd[7] = Math.fma(sd[7], _t10, Math.fma(_t7, _t4, -(axisX * _t0)));
        dd[8] = _t10;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t19 = Math.fma(_t11, axisY * axisY, _t1);
        float _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        float _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        float _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        float _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        float _buf0 = Math.fma(sd[6], _t24, Math.fma(sd[0], _t18, sd[3] * _t21));
        float _buf1 = Math.fma(sd[7], _t24, Math.fma(sd[1], _t18, sd[4] * _t21));
        dd[2] = _t24;
        return rotateAxis_orthogonal_sb82f93e6_1(dest, sd, dd, Math.fma(_t11, axisZ * axisZ, _t1), _t22, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)), _buf0, _buf1, Math.fma(sd[6], _t22, Math.fma(sd[0], _t25, sd[3] * _t19)), Math.fma(sd[7], _t22, Math.fma(sd[1], _t25, sd[4] * _t19)));
    }

    /** Piece 2 of {@code rotateAxis_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateAxis_orthogonal_sb82f93e6_1(Float3x3 dest, float[] sd, float[] dd, float _t20, float _t22, float _t23, float _t26, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[5] = _t22;
        dd[6] = Math.fma(sd[6], _t20, Math.fma(sd[0], _t23, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t20, Math.fma(sd[1], _t23, sd[4] * _t26));
        dd[8] = _t20;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_general(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t19 = Math.fma(_t11, axisY * axisY, _t1);
        float _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        float _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        float _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        float _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        return rotateAxis_general_s42cda10f_1(dest, sd, dd, _t19, Math.fma(_t11, axisZ * axisZ, _t1), _t22, Math.fma(axisY, _t0, _t11 * _t2), _t25, Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(sd[6], _t24, Math.fma(sd[0], _t18, sd[3] * _t21)), Math.fma(sd[7], _t24, Math.fma(sd[1], _t18, sd[4] * _t21)), Math.fma(sd[8], _t24, Math.fma(sd[2], _t18, sd[5] * _t21)), Math.fma(sd[6], _t22, Math.fma(sd[0], _t25, sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateAxis_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateAxis_general_s42cda10f_1(Float3x3 dest, float[] sd, float[] dd, float _t19, float _t20, float _t22, float _t23, float _t25, float _t26, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(sd[7], _t22, Math.fma(sd[1], _t25, sd[4] * _t19));
        float _buf5 = Math.fma(sd[8], _t22, Math.fma(sd[2], _t25, sd[5] * _t19));
        dd[6] = Math.fma(sd[6], _t20, Math.fma(sd[0], _t23, sd[3] * _t26));
        dd[7] = Math.fma(sd[7], _t20, Math.fma(sd[1], _t23, sd[4] * _t26));
        dd[8] = Math.fma(sd[8], _t20, Math.fma(sd[2], _t23, sd[5] * _t26));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (Joml.RETURN_NEW) return rotateAxis(angle, axisX, axisY, axisZ, Joml.float3x3());
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
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Double3x3 dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        float _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        return rotateAxis_s93a4f9b1_1(dest, sd, dd, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), _t24, Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(sd[6], _t24, Math.fma(sd[0], _t18, sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateAxis_s93a4f9b1_1(Double3x3 dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _buf0) {
        float _buf1 = Math.fma(sd[7], _t24, Math.fma(sd[1], _t18, sd[4] * _t21));
        float _buf2 = Math.fma(sd[8], _t24, Math.fma(sd[2], _t18, sd[5] * _t21));
        float _buf3 = Math.fma(sd[6], _t22, Math.fma(sd[0], _t25, sd[3] * _t19));
        float _buf4 = Math.fma(sd[7], _t22, Math.fma(sd[1], _t25, sd[4] * _t19));
        float _buf5 = Math.fma(sd[8], _t22, Math.fma(sd[2], _t25, sd[5] * _t19));
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
    public Float3x3 rotateX(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t1, sd[6] * _t0);
        float _buf1 = Math.fma(sd[4], _t1, sd[7] * _t0);
        float _buf2 = Math.fma(sd[5], _t1, sd[8] * _t0);
        dd[6] = Math.fma(sd[6], _t1, -(sd[3] * _t0));
        dd[7] = Math.fma(sd[7], _t1, -(sd[4] * _t0));
        dd[8] = Math.fma(sd[8], _t1, -(sd[5] * _t0));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
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
    public Double3x3 rotateX(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t1, sd[6] * _t0);
        float _buf1 = Math.fma(sd[4], _t1, sd[7] * _t0);
        float _buf2 = Math.fma(sd[5], _t1, sd[8] * _t0);
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
    private Float3x3 rotateX180_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = -1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = -1.0f;
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[4] = -1.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[1];
        dd[4] = -sd[4];
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[3] = sd[1];
        dd[4] = -sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateX180(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateX180() {
        if (Joml.RETURN_NEW) return rotateX180(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[4] = -1.0f;
            dd[8] = -1.0f;
            ((Float3x3Impl) this).properties = 0;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX180_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine_self(this);
        return rotateX180_general(this);
    }


    /**
     * Apply a rotation of 180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateX180(@Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = -1.0f;
        dd[6] = 0.0f;
        dd[7] = 1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[4] = 0.0f;
        dd[5] = -1.0f;
        dd[7] = 1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = -sd[6];
        dd[4] = -sd[7];
        dd[5] = -1.0f;
        dd[6] = 0.0f;
        dd[7] = 1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[3] = -sd[6];
        dd[4] = -sd[7];
        dd[5] = -1.0f;
        dd[6] = 0.0f;
        dd[7] = 1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        dd[5] = -1.0f;
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        dd[5] = -1.0f;
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        float _buf2 = -sd[8];
        dd[6] = sd[3];
        dd[7] = sd[4];
        dd[8] = sd[5];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateX270(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateX270() {
        if (Joml.RETURN_NEW) return rotateX270(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX270_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal_self(this);
        return rotateX270_general(this);
    }


    /**
     * Apply a rotation of 270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateX270(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        float _buf2 = -sd[8];
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
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = -1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[7] = -1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sd[6];
        dd[4] = sd[7];
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = -1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[3] = sd[6];
        dd[4] = sd[7];
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = -1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[6];
        float _buf1 = sd[7];
        dd[5] = 1.0f;
        dd[6] = sd[1];
        dd[7] = -sd[4];
        dd[8] = 0.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[4];
        float _buf0 = sd[1];
        dd[3] = sd[6];
        float _buf1 = sd[7];
        dd[5] = 1.0f;
        dd[6] = sd[1];
        dd[7] = -sd[4];
        dd[8] = 0.0f;
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        dd[5] = 1.0f;
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        dd[5] = 1.0f;
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = 0.0f;
        dd[3] = _buf0;
        dd[4] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        float _buf2 = sd[8];
        dd[6] = -sd[3];
        dd[7] = -sd[4];
        dd[8] = -sd[5];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateX90(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateX90() {
        if (Joml.RETURN_NEW) return rotateX90(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateX90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateX90_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine_self(this);
        return rotateX90_general(this);
    }


    /**
     * Apply a rotation of 90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateX90(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        float _buf2 = sd[8];
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
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_identity(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t6, _t4, _t1 * _t5);
        dd[2] = Math.fma(_t2, _t1, -(_t7 * _t4));
        dd[3] = -(_t1 * _t3);
        dd[4] = Math.fma(_t5, _t4, -(_t6 * _t1));
        dd[5] = Math.fma(_t7, _t1, _t2 * _t4);
        dd[6] = _t0;
        dd[7] = -(_t2 * _t3);
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_translation(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t9 = _t3 * _t5;
        float _t12 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t13 = Math.fma(_t0, _t1, -(_t7 * _t4));
        dd[0] = Math.fma(sd[6], _t13, _t5 * _t4);
        dd[1] = Math.fma(sd[7], _t13, Math.fma(_t6, _t4, _t1 * _t3));
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t12, -(_t1 * _t5));
        dd[4] = Math.fma(sd[7], _t12, Math.fma(_t3, _t4, -(_t6 * _t1)));
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t9, _t2);
        dd[7] = Math.fma(sd[7], _t9, -(_t0 * _t5));
        dd[8] = _t9;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_orthogonal(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t10 = _t1 * _t5;
        float _t13 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        float _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        float _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        float _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t13, sd[3] * _t18));
        float _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t13, sd[4] * _t18));
        dd[2] = _t20;
        float _buf2 = Math.fma(sd[6], _t19, Math.fma(sd[3], _t21, -(sd[0] * _t10)));
        float _buf3 = Math.fma(sd[7], _t19, Math.fma(sd[4], _t21, -(sd[1] * _t10)));
        dd[5] = _t19;
        return rotateXYZ_orthogonal_sfc747d55_1(dest, sd, dd, _t2, _t0 * _t5, _t3 * _t5, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code rotateXYZ_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateXYZ_orthogonal_sfc747d55_1(Float3x3 dest, float[] sd, float[] dd, float _t2, float _t11, float _t15, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t2, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t2, -(sd[4] * _t11)));
        dd[8] = _t15;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_general(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t10 = _t1 * _t5;
        float _t13 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        float _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        float _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        return rotateXYZ_general_s3bafaa8c_1(dest, sd, dd, _t2, _t10, _t0 * _t5, _t3 * _t5, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t13, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t13, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t13, sd[5] * _t18)), Math.fma(sd[6], _t19, Math.fma(sd[3], _t21, -(sd[0] * _t10))));
    }

    /** Piece 2 of {@code rotateXYZ_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateXYZ_general_s3bafaa8c_1(Float3x3 dest, float[] sd, float[] dd, float _t2, float _t10, float _t11, float _t15, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(sd[7], _t19, Math.fma(sd[4], _t21, -(sd[1] * _t10)));
        float _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[5], _t21, -(sd[2] * _t10)));
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t2, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t2, -(sd[4] * _t11)));
        dd[8] = Math.fma(sd[8], _t15, Math.fma(sd[2], _t2, -(sd[5] * _t11)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateXYZ(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateXYZ(float angleX, float angleY, float angleZ) {
        if (Joml.RETURN_NEW) return rotateXYZ(angleX, angleY, angleZ, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXYZ_identity(angleX, angleY, angleZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXYZ_translation(angleX, angleY, angleZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXYZ_orthogonal(angleX, angleY, angleZ, this);
        return rotateXYZ_general(angleX, angleY, angleZ, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateXYZ(float angleX, float angleY, float angleZ, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t10 = _t1 * _t5;
        float _t13 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        float _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        float _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        return rotateXYZ_s7f1545dc_1(dest, sd, dd, _t2, _t10, _t0 * _t5, _t3 * _t5, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t13, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t13, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t13, sd[5] * _t18)), Math.fma(sd[6], _t19, Math.fma(sd[3], _t21, -(sd[0] * _t10))));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXYZ_s7f1545dc_1(Double3x3 dest, float[] sd, double[] dd, float _t2, float _t10, float _t11, float _t15, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(sd[7], _t19, Math.fma(sd[4], _t21, -(sd[1] * _t10)));
        float _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[5], _t21, -(sd[2] * _t10)));
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
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_identity(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        dd[0] = _t3 * _t4;
        dd[1] = Math.fma(_t7, _t3, _t2 * _t0);
        dd[2] = Math.fma(_t6, _t3, -(_t0 * _t5));
        dd[3] = -_t1;
        dd[4] = _t5 * _t4;
        dd[5] = _t2 * _t4;
        dd[6] = _t0 * _t4;
        dd[7] = Math.fma(_t7, _t0, -(_t2 * _t3));
        dd[8] = Math.fma(_t6, _t0, _t5 * _t3);
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_translation(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t5;
        float _t9 = _t1 * _t4;
        float _t12 = Math.fma(_t6, _t2, _t4 * _t3);
        float _t13 = Math.fma(_t6, _t3, -(_t2 * _t4));
        dd[0] = Math.fma(sd[6], _t13, _t3 * _t5);
        dd[1] = Math.fma(sd[7], _t13, Math.fma(_t9, _t3, _t0 * _t2));
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t8, -_t1);
        dd[4] = Math.fma(sd[7], _t8, _t4 * _t5);
        dd[5] = _t8;
        dd[6] = Math.fma(sd[6], _t12, _t2 * _t5);
        dd[7] = Math.fma(sd[7], _t12, Math.fma(_t9, _t2, -(_t0 * _t3)));
        dd[8] = _t12;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_orthogonal(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float _t10 = _t0 * _t5;
        float _t15 = _t3 * _t5;
        float _t16 = _t4 * _t5;
        float _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        float _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t15, sd[3] * _t18));
        float _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t15, sd[4] * _t18));
        dd[2] = _t20;
        float _buf2 = Math.fma(sd[6], _t10, Math.fma(sd[3], _t16, -(sd[0] * _t1)));
        float _buf3 = Math.fma(sd[7], _t10, Math.fma(sd[4], _t16, -(sd[1] * _t1)));
        dd[5] = _t10;
        return rotateXZY_orthogonal_s30265597_1(dest, sd, dd, _t2 * _t5, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)), _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code rotateXZY_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateXZY_orthogonal_s30265597_1(Float3x3 dest, float[] sd, float[] dd, float _t11, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[6] = Math.fma(sd[6], _t19, Math.fma(sd[0], _t11, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t19, Math.fma(sd[1], _t11, sd[4] * _t21));
        dd[8] = _t19;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_general(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float _t10 = _t0 * _t5;
        float _t15 = _t3 * _t5;
        float _t16 = _t4 * _t5;
        float _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        return rotateXZY_general_sff2dc756_1(dest, sd, dd, _t1, _t10, _t2 * _t5, _t16, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)), Math.fma(sd[6], _t20, Math.fma(sd[0], _t15, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t15, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t15, sd[5] * _t18)), Math.fma(sd[6], _t10, Math.fma(sd[3], _t16, -(sd[0] * _t1))), Math.fma(sd[7], _t10, Math.fma(sd[4], _t16, -(sd[1] * _t1))));
    }

    /** Piece 2 of {@code rotateXZY_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateXZY_general_sff2dc756_1(Float3x3 dest, float[] sd, float[] dd, float _t1, float _t10, float _t11, float _t16, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t10, Math.fma(sd[5], _t16, -(sd[2] * _t1)));
        dd[6] = Math.fma(sd[6], _t19, Math.fma(sd[0], _t11, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t19, Math.fma(sd[1], _t11, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t19, Math.fma(sd[2], _t11, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateXZY(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateXZY(float angleX, float angleZ, float angleY) {
        if (Joml.RETURN_NEW) return rotateXZY(angleX, angleZ, angleY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXZY_identity(angleX, angleZ, angleY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXZY_translation(angleX, angleZ, angleY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXZY_orthogonal(angleX, angleZ, angleY, this);
        return rotateXZY_general(angleX, angleZ, angleY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateXZY(float angleX, float angleZ, float angleY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float _t10 = _t0 * _t5;
        float _t15 = _t3 * _t5;
        float _t16 = _t4 * _t5;
        float _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        return rotateXZY_s58ec96a2_1(dest, sd, dd, _t1, _t10, _t2 * _t5, _t16, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)), Math.fma(sd[6], _t20, Math.fma(sd[0], _t15, sd[3] * _t18)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t15, sd[4] * _t18)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t15, sd[5] * _t18)), Math.fma(sd[6], _t10, Math.fma(sd[3], _t16, -(sd[0] * _t1))), Math.fma(sd[7], _t10, Math.fma(sd[4], _t16, -(sd[1] * _t1))));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateXZY_s58ec96a2_1(Double3x3 dest, float[] sd, double[] dd, float _t1, float _t10, float _t11, float _t16, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t10, Math.fma(sd[5], _t16, -(sd[2] * _t1)));
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
    public Float3x3 rotateXn180(@Mutated Float3x3 dest) {
        return rotateX180(dest);
    }


    /**
     * Apply a rotation of -180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateXn180() {
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
    public Float3x3 rotateXn270(@Mutated Float3x3 dest) {
        return rotateX90(dest);
    }


    /**
     * Apply a rotation of -270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateXn270() {
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
    public Float3x3 rotateXn90(@Mutated Float3x3 dest) {
        return rotateX270(dest);
    }


    /**
     * Apply a rotation of -90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateXn90() {
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
    public Float3x3 rotateY(float angle, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[6] * _t0));
        float _buf1 = Math.fma(sd[1], _t1, -(sd[7] * _t0));
        float _buf2 = Math.fma(sd[2], _t1, -(sd[8] * _t0));
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = Math.fma(sd[0], _t0, sd[6] * _t1);
        dd[7] = Math.fma(sd[1], _t0, sd[7] * _t1);
        dd[8] = Math.fma(sd[2], _t0, sd[8] * _t1);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
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
    public Double3x3 rotateY(float angle, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[6] * _t0));
        float _buf1 = Math.fma(sd[1], _t1, -(sd[7] * _t0));
        float _buf2 = Math.fma(sd[2], _t1, -(sd[8] * _t0));
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
    private Float3x3 rotateY180_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }



    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -1.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateY180(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateY180() {
        if (Joml.RETURN_NEW) return rotateY180(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = -1.0f;
            dd[8] = -1.0f;
            ((Float3x3Impl) this).properties = 0;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY180_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal_self(this);
        return rotateY180_general(this);
    }


    /**
     * Apply a rotation of 180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateY180(@Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 1.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[2] = 1.0f;
        dd[6] = -1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = 1.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = -1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = 1.0f;
        dd[6] = -1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        dd[2] = 1.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        dd[2] = 1.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        float _buf2 = sd[8];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = -sd[0];
        dd[7] = -sd[1];
        dd[8] = -sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateY270(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateY270() {
        if (Joml.RETURN_NEW) return rotateY270(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY270_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal_self(this);
        return rotateY270_general(this);
    }


    /**
     * Apply a rotation of 270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateY270(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = sd[6];
        float _buf1 = sd[7];
        float _buf2 = sd[8];
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
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = -1.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[2] = -1.0f;
        dd[6] = 1.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[6];
        dd[1] = -sd[7];
        dd[2] = -1.0f;
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[6];
        dd[1] = -sd[7];
        dd[2] = -1.0f;
        dd[6] = 1.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        dd[2] = -1.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = 0.0f;
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        dd[2] = -1.0f;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        float _buf2 = -sd[8];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[0];
        dd[7] = sd[1];
        dd[8] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateY90(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateY90() {
        if (Joml.RETURN_NEW) return rotateY90(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateY90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateY90_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal_self(this);
        return rotateY90_general(this);
    }


    /**
     * Apply a rotation of 90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateY90(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = -sd[6];
        float _buf1 = -sd[7];
        float _buf2 = -sd[8];
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
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_identity(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dd[0] = Math.fma(_t6, _t2, _t3 * _t4);
        dd[1] = _t2 * _t5;
        dd[2] = Math.fma(_t7, _t2, -(_t1 * _t4));
        dd[3] = Math.fma(_t6, _t4, -(_t2 * _t3));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t7, _t4, _t1 * _t2);
        dd[6] = _t1 * _t5;
        dd[7] = -_t0;
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_translation(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t11 = _t5 * _t3;
        float _t12 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t13 = Math.fma(_t8, _t2, -(_t1 * _t4));
        dd[0] = Math.fma(sd[6], _t13, Math.fma(_t6, _t2, _t3 * _t4));
        dd[1] = Math.fma(sd[7], _t13, _t2 * _t5);
        dd[2] = _t13;
        dd[3] = Math.fma(sd[6], _t12, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dd[4] = Math.fma(sd[7], _t12, _t5 * _t4);
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t11, _t1 * _t5);
        dd[7] = Math.fma(sd[7], _t11, -_t0);
        dd[8] = _t11;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_orthogonal(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t16 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        float _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        float _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        float _buf0 = Math.fma(sd[6], _t20, Math.fma(sd[0], _t18, sd[3] * _t10));
        float _buf1 = Math.fma(sd[7], _t20, Math.fma(sd[1], _t18, sd[4] * _t10));
        dd[2] = _t20;
        float _buf2 = Math.fma(sd[6], _t19, Math.fma(sd[0], _t21, sd[3] * _t16));
        float _buf3 = Math.fma(sd[7], _t19, Math.fma(sd[1], _t21, sd[4] * _t16));
        dd[5] = _t19;
        return rotateYXZ_orthogonal_s2ff31e4b_1(dest, sd, dd, _t0, _t1 * _t5, _t5 * _t3, _buf0, _buf1, _buf2, _buf3);
    }

    /**
     * Piece 2 of {@code rotateYXZ_orthogonal}, split to fit the inline budget. Shared by the
     * identical private paths of {@code rotateYXZ} and {@code rotateYZX}; reached only through
     * them.
     */
    private Float3x3 rotateYXZ_orthogonal_s2ff31e4b_1(Float3x3 dest, float[] sd, float[] dd, float _t0, float _t12, float _t17, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t12, -(sd[3] * _t0)));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t12, -(sd[4] * _t0)));
        dd[8] = _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_general(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t16 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        float _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        float _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        return rotateYXZ_general_s584e8dda_1(dest, sd, dd, _t0, _t1 * _t5, _t16, _t5 * _t3, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t18, sd[3] * _t10)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t18, sd[4] * _t10)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t18, sd[5] * _t10)), Math.fma(sd[6], _t19, Math.fma(sd[0], _t21, sd[3] * _t16)), Math.fma(sd[7], _t19, Math.fma(sd[1], _t21, sd[4] * _t16)));
    }

    /** Piece 2 of {@code rotateYXZ_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateYXZ_general_s584e8dda_1(Float3x3 dest, float[] sd, float[] dd, float _t0, float _t12, float _t16, float _t17, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[2], _t21, sd[5] * _t16));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t12, -(sd[3] * _t0)));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t12, -(sd[4] * _t0)));
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t12, -(sd[5] * _t0)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateYXZ(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateYXZ(float angleY, float angleX, float angleZ) {
        if (Joml.RETURN_NEW) return rotateYXZ(angleY, angleX, angleZ, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYXZ_identity(angleY, angleX, angleZ, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYXZ_translation(angleY, angleX, angleZ, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYXZ_orthogonal(angleY, angleX, angleZ, this);
        return rotateYXZ_general(angleY, angleX, angleZ, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateYXZ(float angleY, float angleX, float angleZ, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t16 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        float _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        float _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        return rotateYXZ_sd79fdc7e_1(dest, sd, dd, _t0, _t1 * _t5, _t16, _t5 * _t3, _t19, _t21, Math.fma(sd[6], _t20, Math.fma(sd[0], _t18, sd[3] * _t10)), Math.fma(sd[7], _t20, Math.fma(sd[1], _t18, sd[4] * _t10)), Math.fma(sd[8], _t20, Math.fma(sd[2], _t18, sd[5] * _t10)), Math.fma(sd[6], _t19, Math.fma(sd[0], _t21, sd[3] * _t16)), Math.fma(sd[7], _t19, Math.fma(sd[1], _t21, sd[4] * _t16)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateYXZ_sd79fdc7e_1(Double3x3 dest, float[] sd, double[] dd, float _t0, float _t12, float _t16, float _t17, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t19, Math.fma(sd[2], _t21, sd[5] * _t16));
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
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_identity(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        dd[0] = _t3 * _t4;
        dd[1] = _t1;
        dd[2] = -(_t0 * _t4);
        dd[3] = Math.fma(_t2, _t0, -(_t7 * _t5));
        dd[4] = _t5 * _t4;
        dd[5] = Math.fma(_t6, _t5, _t2 * _t3);
        dd[6] = Math.fma(_t7, _t2, _t0 * _t5);
        dd[7] = -(_t2 * _t4);
        dd[8] = Math.fma(_t5, _t3, -(_t6 * _t2));
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_translation(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        float _t9 = _t1 * _t3;
        float _t12 = Math.fma(_t6, _t5, _t2 * _t3);
        float _t13 = Math.fma(_t5, _t3, -(_t6 * _t2));
        dd[0] = Math.fma(_t3, _t4, -(sd[6] * _t7));
        dd[1] = Math.fma(-sd[7], _t7, _t1);
        dd[2] = -_t7;
        dd[3] = Math.fma(sd[6], _t12, Math.fma(_t2, _t0, -(_t9 * _t5)));
        dd[4] = Math.fma(sd[7], _t12, _t5 * _t4);
        dd[5] = _t12;
        dd[6] = Math.fma(sd[6], _t13, Math.fma(_t9, _t2, _t0 * _t5));
        dd[7] = Math.fma(sd[7], _t13, -(_t2 * _t4));
        dd[8] = _t13;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_orthogonal(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        float _t9 = _t1 * _t4;
        float _t13 = _t4 * _t3;
        float _t14 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        float _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        float _buf0 = Math.fma(-sd[6], _t7, Math.fma(sd[0], _t13, sd[3] * _t1));
        float _buf1 = Math.fma(-sd[7], _t7, Math.fma(sd[1], _t13, sd[4] * _t1));
        dd[2] = -_t7;
        float _buf2 = Math.fma(sd[6], _t18, Math.fma(sd[0], _t20, sd[3] * _t14));
        float _buf3 = Math.fma(sd[7], _t18, Math.fma(sd[1], _t20, sd[4] * _t14));
        dd[5] = _t18;
        return rotateYXZ_orthogonal_s2ff31e4b_1(dest, sd, dd, _t2 * _t3, Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t5, _t4, -(_t6 * _t2)), _buf0, _buf1, _buf2, _buf3);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_general(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        float _t9 = _t1 * _t4;
        float _t13 = _t4 * _t3;
        float _t14 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        float _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        return rotateYZX_general_sd80c989a_1(dest, sd, dd, _t2 * _t3, _t14, _t18, Math.fma(_t9, _t2, _t0 * _t5), _t20, Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t13, sd[3] * _t1)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t13, sd[4] * _t1)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t13, sd[5] * _t1)), Math.fma(sd[6], _t18, Math.fma(sd[0], _t20, sd[3] * _t14)), Math.fma(sd[7], _t18, Math.fma(sd[1], _t20, sd[4] * _t14)));
    }

    /** Piece 2 of {@code rotateYZX_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateYZX_general_sd80c989a_1(Float3x3 dest, float[] sd, float[] dd, float _t11, float _t14, float _t18, float _t19, float _t20, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t18, Math.fma(sd[2], _t20, sd[5] * _t14));
        dd[6] = Math.fma(sd[6], _t21, Math.fma(sd[0], _t19, -(sd[3] * _t11)));
        dd[7] = Math.fma(sd[7], _t21, Math.fma(sd[1], _t19, -(sd[4] * _t11)));
        dd[8] = Math.fma(sd[8], _t21, Math.fma(sd[2], _t19, -(sd[5] * _t11)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateYZX(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateYZX(float angleY, float angleZ, float angleX) {
        if (Joml.RETURN_NEW) return rotateYZX(angleY, angleZ, angleX, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYZX_identity(angleY, angleZ, angleX, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYZX_translation(angleY, angleZ, angleX, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYZX_orthogonal(angleY, angleZ, angleX, this);
        return rotateYZX_general(angleY, angleZ, angleX, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateYZX(float angleY, float angleZ, float angleX, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        float _t9 = _t1 * _t4;
        float _t13 = _t4 * _t3;
        float _t14 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        float _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        return rotateYZX_s50aa056_1(dest, sd, dd, _t2 * _t3, _t14, _t18, Math.fma(_t9, _t2, _t0 * _t5), _t20, Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t13, sd[3] * _t1)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t13, sd[4] * _t1)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t13, sd[5] * _t1)), Math.fma(sd[6], _t18, Math.fma(sd[0], _t20, sd[3] * _t14)), Math.fma(sd[7], _t18, Math.fma(sd[1], _t20, sd[4] * _t14)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateYZX_s50aa056_1(Double3x3 dest, float[] sd, double[] dd, float _t11, float _t14, float _t18, float _t19, float _t20, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t18, Math.fma(sd[2], _t20, sd[5] * _t14));
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
    public Float3x3 rotateYn180(@Mutated Float3x3 dest) {
        return rotateY180(dest);
    }


    /**
     * Apply a rotation of -180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateYn180() {
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
    public Float3x3 rotateYn270(@Mutated Float3x3 dest) {
        return rotateY90(dest);
    }


    /**
     * Apply a rotation of -270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateYn270() {
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
    public Float3x3 rotateYn90(@Mutated Float3x3 dest) {
        return rotateY270(dest);
    }


    /**
     * Apply a rotation of -90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateYn90() {
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
    public Float3x3 rotateZ(float angle, @Mutated Float3x3 dest) {
        return rotate(angle, dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
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
    public Double3x3 rotateZ(float angle, @Mutated Double3x3 dest) {
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
    @Mutated public Float3x3 rotateZ(float angle) {
        return rotate(angle);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = -1.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }



    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = -1.0f;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -1.0f;
        dd[4] = -1.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = -sd[4];
        dd[0] = _t0;
        float _buf0 = -sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[1];
        dd[4] = _t0;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = -sd[4];
        dd[0] = _t0;
        float _buf0 = -sd[1];
        dd[3] = sd[1];
        dd[4] = _t0;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = 0.0f;
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateZ180(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateZ180() {
        if (Joml.RETURN_NEW) return rotateZ180(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = -1.0f;
            dd[4] = -1.0f;
            ((Float3x3Impl) this).properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ180_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine_self(this);
        return rotateZ180_general(this);
    }


    /**
     * Apply a rotation of 180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateZ180(@Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = -1.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = -1.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = -1.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = -1.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_orthogonal(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[1];
        float _buf0 = -sd[4];
        dd[2] = 0.0f;
        dd[3] = sd[4];
        dd[4] = sd[1];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_orthogonal_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[1];
        float _buf0 = -sd[4];
        dd[3] = sd[4];
        dd[4] = sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[3];
        float _buf1 = -sd[4];
        dd[2] = 0.0f;
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_affine_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[3];
        float _buf1 = -sd[4];
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = -sd[3];
        float _buf1 = -sd[4];
        float _buf2 = -sd[5];
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateZ270(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateZ270() {
        if (Joml.RETURN_NEW) return rotateZ270(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ270_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ270_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine_self(this);
        return rotateZ270_general(this);
    }


    /**
     * Apply a rotation of 270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateZ270(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = -sd[3];
        float _buf1 = -sd[4];
        float _buf2 = -sd[5];
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
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_orthogonal_affine(@Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[3];
        float _buf1 = sd[4];
        dd[2] = 0.0f;
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_orthogonal_affine_self(@Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[3];
        float _buf1 = sd[4];
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_identity(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 1.0f;
        dd[2] = 0.0f;
        dd[3] = -1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_identity_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 1.0f;
        dd[3] = -1.0f;
        dd[4] = 0.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 1.0f;
        dd[2] = 0.0f;
        dd[3] = -1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_translation_self(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 1.0f;
        dd[3] = -1.0f;
        dd[4] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[3];
        float _buf1 = sd[4];
        float _buf2 = sd[5];
        dd[3] = -sd[0];
        dd[4] = -sd[1];
        dd[5] = -sd[2];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateZ90(@Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateZ90() {
        if (Joml.RETURN_NEW) return rotateZ90(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZ90_identity_self(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZ90_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_orthogonal_affine_self(this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return rotateZ90_general(this);
    }


    /**
     * Apply a rotation of 90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateZ90(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = sd[3];
        float _buf1 = sd[4];
        float _buf2 = sd[5];
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
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_identity(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dd[0] = Math.fma(_t3, _t4, -(_t6 * _t0));
        dd[1] = Math.fma(_t7, _t0, _t1 * _t3);
        dd[2] = -(_t0 * _t5);
        dd[3] = -(_t1 * _t5);
        dd[4] = _t5 * _t4;
        dd[5] = _t2;
        dd[6] = Math.fma(_t6, _t3, _t0 * _t4);
        dd[7] = Math.fma(_t0, _t1, -(_t7 * _t3));
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_translation(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t9 = _t3 * _t4;
        dd[0] = Math.fma(-sd[6], _t7, Math.fma(_t4, _t5, -(_t6 * _t0)));
        dd[1] = Math.fma(-sd[7], _t7, Math.fma(_t8, _t0, _t2 * _t4));
        dd[2] = -_t7;
        dd[3] = Math.fma(sd[6], _t1, -(_t2 * _t3));
        dd[4] = Math.fma(sd[7], _t1, _t3 * _t5);
        dd[5] = _t1;
        dd[6] = Math.fma(sd[6], _t9, Math.fma(_t6, _t4, _t0 * _t5));
        dd[7] = Math.fma(sd[7], _t9, Math.fma(_t0, _t2, -(_t8 * _t4)));
        dd[8] = _t9;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_orthogonal(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t10 = _t2 * _t3;
        float _t14 = _t3 * _t5;
        float _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        float _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        float _buf0 = Math.fma(-sd[6], _t7, Math.fma(sd[0], _t20, sd[3] * _t18));
        float _buf1 = Math.fma(-sd[7], _t7, Math.fma(sd[1], _t20, sd[4] * _t18));
        dd[2] = -_t7;
        float _buf2 = Math.fma(sd[6], _t1, Math.fma(sd[3], _t14, -(sd[0] * _t10)));
        float _buf3 = Math.fma(sd[7], _t1, Math.fma(sd[4], _t14, -(sd[1] * _t10)));
        dd[5] = _t1;
        return rotateZXY_orthogonal_s310484c3_1(dest, sd, dd, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)), _buf0, _buf1, _buf2, _buf3);
    }

    /**
     * Piece 2 of {@code rotateZXY_orthogonal}, split to fit the inline budget. Shared by the
     * identical private paths of {@code rotateZXY} and {@code rotateZYX}; reached only through
     * them.
     */
    private Float3x3 rotateZXY_orthogonal_s310484c3_1(Float3x3 dest, float[] sd, float[] dd, float _t15, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = _t15;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_general(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t10 = _t2 * _t3;
        float _t14 = _t3 * _t5;
        float _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        float _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        return rotateZXY_general_s9136cce2_1(dest, sd, dd, _t1, _t10, _t14, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t20, sd[4] * _t18)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t20, sd[5] * _t18)), Math.fma(sd[6], _t1, Math.fma(sd[3], _t14, -(sd[0] * _t10))), Math.fma(sd[7], _t1, Math.fma(sd[4], _t14, -(sd[1] * _t10))));
    }

    /** Piece 2 of {@code rotateZXY_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateZXY_general_s9136cce2_1(Float3x3 dest, float[] sd, float[] dd, float _t1, float _t10, float _t14, float _t15, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t1, Math.fma(sd[5], _t14, -(sd[2] * _t10)));
        dd[6] = Math.fma(sd[6], _t15, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t15, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t15, Math.fma(sd[2], _t19, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateZXY(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateZXY(float angleZ, float angleX, float angleY) {
        if (Joml.RETURN_NEW) return rotateZXY(angleZ, angleX, angleY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZXY_identity(angleZ, angleX, angleY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZXY_translation(angleZ, angleX, angleY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZXY_orthogonal(angleZ, angleX, angleY, this);
        return rotateZXY_general(angleZ, angleX, angleY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateZXY(float angleZ, float angleX, float angleY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t10 = _t2 * _t3;
        float _t14 = _t3 * _t5;
        float _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        float _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        return rotateZXY_s4d7cb9a6_1(dest, sd, dd, _t1, _t10, _t14, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)), Math.fma(-sd[6], _t7, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(-sd[7], _t7, Math.fma(sd[1], _t20, sd[4] * _t18)), Math.fma(-sd[8], _t7, Math.fma(sd[2], _t20, sd[5] * _t18)), Math.fma(sd[6], _t1, Math.fma(sd[3], _t14, -(sd[0] * _t10))), Math.fma(sd[7], _t1, Math.fma(sd[4], _t14, -(sd[1] * _t10))));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateZXY_s4d7cb9a6_1(Double3x3 dest, float[] sd, double[] dd, float _t1, float _t10, float _t14, float _t15, float _t19, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t1, Math.fma(sd[5], _t14, -(sd[2] * _t10)));
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
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_identity(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        dd[0] = _t3 * _t4;
        dd[1] = _t1 * _t3;
        dd[2] = -_t0;
        dd[3] = Math.fma(_t7, _t2, -(_t1 * _t5));
        dd[4] = Math.fma(_t6, _t2, _t5 * _t4);
        dd[5] = _t2 * _t3;
        dd[6] = Math.fma(_t7, _t5, _t2 * _t1);
        dd[7] = Math.fma(_t6, _t5, -(_t2 * _t4));
        dd[8] = _t5 * _t3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_translation(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t3;
        float _t8 = _t0 * _t4;
        float _t9 = _t5 * _t3;
        dd[0] = Math.fma(_t3, _t4, -(sd[6] * _t0));
        dd[1] = Math.fma(_t1, _t3, -(sd[7] * _t0));
        dd[2] = -_t0;
        dd[3] = Math.fma(sd[6], _t7, Math.fma(_t8, _t2, -(_t1 * _t5)));
        dd[4] = Math.fma(sd[7], _t7, Math.fma(_t6, _t2, _t5 * _t4));
        dd[5] = _t7;
        dd[6] = Math.fma(sd[6], _t9, Math.fma(_t8, _t5, _t2 * _t1));
        dd[7] = Math.fma(sd[7], _t9, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dd[8] = _t9;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_orthogonal(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t1 * _t3;
        float _t9 = _t2 * _t3;
        float _t10 = _t0 * _t4;
        float _t15 = _t3 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        float _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        float _buf0 = Math.fma(-sd[6], _t0, Math.fma(sd[0], _t15, sd[3] * _t8));
        float _buf1 = Math.fma(-sd[7], _t0, Math.fma(sd[1], _t15, sd[4] * _t8));
        dd[2] = -_t0;
        float _buf2 = Math.fma(sd[6], _t9, Math.fma(sd[0], _t20, sd[3] * _t18));
        float _buf3 = Math.fma(sd[7], _t9, Math.fma(sd[1], _t20, sd[4] * _t18));
        dd[5] = _t9;
        return rotateZXY_orthogonal_s310484c3_1(dest, sd, dd, _t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)), _buf0, _buf1, _buf2, _buf3);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_general(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t1 * _t3;
        float _t9 = _t2 * _t3;
        float _t10 = _t0 * _t4;
        float _t15 = _t3 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        float _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        return rotateZYX_general_s392f1418_1(dest, sd, dd, _t9, _t5 * _t3, _t18, Math.fma(_t10, _t5, _t2 * _t1), _t20, Math.fma(_t6, _t5, -(_t2 * _t4)), Math.fma(-sd[6], _t0, Math.fma(sd[0], _t15, sd[3] * _t8)), Math.fma(-sd[7], _t0, Math.fma(sd[1], _t15, sd[4] * _t8)), Math.fma(-sd[8], _t0, Math.fma(sd[2], _t15, sd[5] * _t8)), Math.fma(sd[6], _t9, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(sd[7], _t9, Math.fma(sd[1], _t20, sd[4] * _t18)));
    }

    /** Piece 2 of {@code rotateZYX_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 rotateZYX_general_s392f1418_1(Float3x3 dest, float[] sd, float[] dd, float _t9, float _t17, float _t18, float _t19, float _t20, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t9, Math.fma(sd[2], _t20, sd[5] * _t18));
        dd[6] = Math.fma(sd[6], _t17, Math.fma(sd[0], _t19, sd[3] * _t21));
        dd[7] = Math.fma(sd[7], _t17, Math.fma(sd[1], _t19, sd[4] * _t21));
        dd[8] = Math.fma(sd[8], _t17, Math.fma(sd[2], _t19, sd[5] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 rotateZYX(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 rotateZYX(float angleZ, float angleY, float angleX) {
        if (Joml.RETURN_NEW) return rotateZYX(angleZ, angleY, angleX, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZYX_identity(angleZ, angleY, angleX, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZYX_translation(angleZ, angleY, angleX, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZYX_orthogonal(angleZ, angleY, angleX, this);
        return rotateZYX_general(angleZ, angleY, angleX, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 rotateZYX(float angleZ, float angleY, float angleX, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t1 * _t3;
        float _t9 = _t2 * _t3;
        float _t10 = _t0 * _t4;
        float _t15 = _t3 * _t4;
        float _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        float _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        return rotateZYX_s6bd93fe8_1(dest, sd, dd, _t9, _t5 * _t3, _t18, Math.fma(_t10, _t5, _t2 * _t1), _t20, Math.fma(_t6, _t5, -(_t2 * _t4)), Math.fma(-sd[6], _t0, Math.fma(sd[0], _t15, sd[3] * _t8)), Math.fma(-sd[7], _t0, Math.fma(sd[1], _t15, sd[4] * _t8)), Math.fma(-sd[8], _t0, Math.fma(sd[2], _t15, sd[5] * _t8)), Math.fma(sd[6], _t9, Math.fma(sd[0], _t20, sd[3] * _t18)), Math.fma(sd[7], _t9, Math.fma(sd[1], _t20, sd[4] * _t18)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private Double3x3 rotateZYX_s6bd93fe8_1(Double3x3 dest, float[] sd, double[] dd, float _t9, float _t17, float _t18, float _t19, float _t20, float _t21, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = Math.fma(sd[8], _t9, Math.fma(sd[2], _t20, sd[5] * _t18));
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
    public Float3x3 rotateZn180(@Mutated Float3x3 dest) {
        return rotateZ180(dest);
    }


    /**
     * Apply a rotation of -180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateZn180() {
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
    public Float3x3 rotateZn270(@Mutated Float3x3 dest) {
        return rotateZ90(dest);
    }


    /**
     * Apply a rotation of -270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateZn270() {
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
    public Float3x3 rotateZn90(@Mutated Float3x3 dest) {
        return rotateZ270(dest);
    }


    /**
     * Apply a rotation of -90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    @Mutated public Float3x3 rotateZn90() {
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
    public Float3x3 scale(Float2R v, @Mutated Float3x3 dest) {
        return scale(v.x(), v.y(), dest);
    }


    /**
     * Apply a scaling by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scale(Float2R v, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 scale(Float2R v) {
        return scale(v.x(), v.y());
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = vY;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = vX;
        dd[4] = vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = 0.0f;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal_self(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vY;
        dd[5] = sd[5] * vY;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 scale(float vX, float vY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 scale(float vX, float vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = vX;
            dd[4] = vY;
            ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal_self(vX, vY, this);
        return scale_general(vX, vY, this);
    }


    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scale(float vX, float vY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation_self(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s;
        dd[4] = s;
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = 0.0f;
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = 0.0f;
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal_self(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[6] = sd[6];
        dd[7] = sd[7];
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float s, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 scale(float s, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 scale(float s) {
        if (Joml.RETURN_NEW) return scale(s, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[0] = s;
            dd[4] = s;
            ((Float3x3Impl) this).properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(s, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal_self(s, this);
        return scale_general(s, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scale(float s, @Mutated Double3x3 dest) {
        float[] sd = this.data;
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
    public Float3x3 scaleAround(float s, Float2R pivot, @Mutated Float3x3 dest) {
        return scaleAround(s, pivot.x(), pivot.y(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scaleAround(float s, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 scaleAround(float s, Float2R pivot) {
        return scaleAround(s, pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = s;
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, _t0, sd[6]);
        dd[7] = Math.fma(pivotY, _t0, sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[4] = s;
        dd[6] = Math.fma(pivotX, _t0, sd[6]);
        dd[7] = Math.fma(pivotY, _t0, sd[7]);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * sd[0];
        float _buf1 = s * sd[1];
        dd[2] = 0.0f;
        float _buf2 = s * sd[3];
        float _buf3 = s * sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * sd[0];
        float _buf1 = s * sd[1];
        float _buf2 = s * sd[3];
        float _buf3 = s * sd[4];
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * sd[0];
        float _buf1 = s * sd[1];
        float _buf2 = s * sd[2];
        float _buf3 = s * sd[3];
        float _buf4 = s * sd[4];
        float _buf5 = s * sd[5];
        dd[6] = Math.fma(sd[0], _t1, Math.fma(sd[3], _t2, sd[6]));
        dd[7] = Math.fma(sd[1], _t1, Math.fma(sd[4], _t2, sd[7]));
        dd[8] = Math.fma(sd[2], _t1, Math.fma(sd[5], _t2, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 scaleAround(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 scaleAround(float s, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal_self(s, pivotX, pivotY, this);
        return scaleAround_general(s, pivotX, pivotY, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scaleAround(float s, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * sd[0];
        float _buf1 = s * sd[1];
        float _buf2 = s * sd[2];
        float _buf3 = s * sd[3];
        float _buf4 = s * sd[4];
        float _buf5 = s * sd[5];
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
    public Float3x3 scaleAround(Float2R s, Float2R pivot, @Mutated Float3x3 dest) {
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
    }


    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 scaleAround(Float2R s, Float2R pivot, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 scaleAround(Float2R s, Float2R pivot) {
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = sY;
        dd[5] = 0.0f;
        dd[6] = Math.fma(pivotX, 1.0f - sX, sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sX;
        dd[4] = sY;
        dd[6] = Math.fma(pivotX, 1.0f - sX, sd[6]);
        dd[7] = Math.fma(pivotY, 1.0f - sY, sd[7]);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * sd[0];
        float _buf1 = sX * sd[1];
        dd[2] = 0.0f;
        float _buf2 = sY * sd[3];
        float _buf3 = sY * sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * sd[0];
        float _buf1 = sX * sd[1];
        float _buf2 = sY * sd[3];
        float _buf3 = sY * sd[4];
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * sd[0];
        float _buf1 = sX * sd[1];
        float _buf2 = sX * sd[2];
        float _buf3 = sY * sd[3];
        float _buf4 = sY * sd[4];
        float _buf5 = sY * sd[5];
        dd[6] = Math.fma(sd[0], _t2, Math.fma(sd[3], _t3, sd[6]));
        dd[7] = Math.fma(sd[1], _t2, Math.fma(sd[4], _t3, sd[7]));
        dd[8] = Math.fma(sd[2], _t2, Math.fma(sd[5], _t3, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 scaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 scaleAround(float sX, float sY, float pivotX, float pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal_self(sX, sY, pivotX, pivotY, this);
        return scaleAround_general(sX, sY, pivotX, pivotY, this);
    }


    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 scaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * sd[0];
        float _buf1 = sX * sd[1];
        float _buf2 = sX * sd[2];
        float _buf3 = sY * sd[3];
        float _buf4 = sY * sd[4];
        float _buf5 = sY * sd[5];
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
    public Float3x3 translate(Float2R v, @Mutated Float3x3 dest) {
        return translate(v.x(), v.y(), dest);
    }


    /**
     * Apply a translation by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 translate(Float2R v, @Mutated Double3x3 dest) {
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
    public @Mutated Float3x3 translate(Float2R v) {
        return translate(v.x(), v.y());
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_orthogonal_affine(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        dd[2] = 0.0f;
        float _buf2 = sd[3];
        float _buf3 = sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_orthogonal_affine_self(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[3];
        float _buf3 = sd[4];
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }






    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_general(float vX, float vY, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        float _buf3 = sd[3];
        float _buf4 = sd[4];
        float _buf5 = sd[5];
        dd[6] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[7] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        dd[8] = Math.fma(sd[2], vX, Math.fma(sd[5], vY, sd[8]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 translate(float vX, float vY, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 translate(float vX, float vY) {
        if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3x3Impl) this).data;
            dd[6] = vX;
            dd[7] = vY;
            ((Float3x3Impl) this).properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_orthogonal_affine_self(vX, vY, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return translate_general(vX, vY, this);
    }


    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 translate(float vX, float vY, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        float _buf3 = sd[3];
        float _buf4 = sd[4];
        float _buf5 = sd[5];
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
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_identity(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0f;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_identity_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[4] = _t1_inv + _t1_inv;
        dd[6] = -((left + right) * _t0_inv);
        dd[7] = -((bottom + top) * _t1_inv);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_translation(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = _t1_inv + _t1_inv;
        dd[5] = 0.0f;
        dd[6] = Math.fma(-(left + right), _t0_inv, sd[6]);
        dd[7] = Math.fma(-(bottom + top), _t1_inv, sd[7]);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_translation_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[4] = _t1_inv + _t1_inv;
        dd[6] = Math.fma(-(left + right), _t0_inv, sd[6]);
        dd[7] = Math.fma(-(bottom + top), _t1_inv, sd[7]);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_orthogonal(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        dd[2] = 0.0f;
        float _buf2 = _sp1 * sd[3];
        float _buf3 = _sp1 * sd[4];
        dd[5] = 0.0f;
        dd[6] = Math.fma(-sd[3], _sp3, Math.fma(-sd[0], _sp2, sd[6]));
        dd[7] = Math.fma(-sd[4], _sp3, Math.fma(-sd[1], _sp2, sd[7]));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_orthogonal_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        float _buf2 = _sp1 * sd[3];
        float _buf3 = _sp1 * sd[4];
        dd[6] = Math.fma(-sd[3], _sp3, Math.fma(-sd[0], _sp2, sd[6]));
        dd[7] = Math.fma(-sd[4], _sp3, Math.fma(-sd[1], _sp2, sd[7]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_affine(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        dd[2] = 0.0f;
        float _buf2 = _sp1 * sd[3];
        float _buf3 = _sp1 * sd[4];
        dd[5] = 0.0f;
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[8] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_affine_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        float _buf2 = _sp1 * sd[3];
        float _buf3 = _sp1 * sd[4];
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_general(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        float _buf2 = _sp0 * sd[2];
        float _buf3 = _sp1 * sd[3];
        float _buf4 = _sp1 * sd[4];
        float _buf5 = _sp1 * sd[5];
        dd[6] = sd[6] + Math.fma(-sd[3], _sp3, -(sd[0] * _sp2));
        dd[7] = sd[7] + Math.fma(-sd[4], _sp3, -(sd[1] * _sp2));
        dd[8] = sd[8] + Math.fma(-sd[5], _sp3, -(sd[2] * _sp2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        ((Float3x3Impl) dest).properties = 0;
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
    public Float3x3 view(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
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
    @Mutated public Float3x3 view(float left, float right, float bottom, float top) {
        if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return view_affine_self(left, right, bottom, top, this);
        return view_general(left, right, bottom, top, this);
    }


    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3x3 view(float left, float right, float bottom, float top, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * sd[0];
        float _buf1 = _sp0 * sd[1];
        float _buf2 = _sp0 * sd[2];
        float _buf3 = _sp1 * sd[3];
        float _buf4 = _sp1 * sd[4];
        float _buf5 = _sp1 * sd[5];
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
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Float3 mul(Float3R v, @Mutated Float3 dest) {
        return mul(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(Float3R v, @Mutated Double3 dest) {
        return mul(v.x(), v.y(), v.z(), dest);
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3 mul_translation(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = Math.fma(sd[6], vZ, vX);
        dd[1] = Math.fma(sd[7], vZ, vY);
        dd[2] = vZ;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3 mul_general(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
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
    public Float3 mul(float vX, float vY, float vZ, @Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            dd[2] = vZ;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(vX, vY, vZ, dest);
        return mul_general(vX, vY, vZ, dest);
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.fma(sd[6], vZ, Math.fma(sd[0], vX, sd[3] * vY));
        dd[1] = Math.fma(sd[7], vZ, Math.fma(sd[1], vX, sd[4] * vY));
        dd[2] = Math.fma(sd[8], vZ, Math.fma(sd[2], vX, sd[5] * vY));
        return dest;
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
    public Float2 transformDirection(Float2R v, @Mutated Float2 dest) {
        return transformDirection(v.x(), v.y(), dest);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformDirection(Float2R v, @Mutated Double2 dest) {
        return transformDirection(v.x(), v.y(), dest);
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code transformDirection} and {@code transformPosition};
     * reached only through them.
     */
    private Float2 transformDirection_identity(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = vX;
        dd[1] = vY;
        return dest;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
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
    public Float2 transformDirection(float vX, float vY, @Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return transformDirection_general(vX, vY, dest);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformDirection(float vX, float vY, @Mutated Double2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[3] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[4] * vY);
        return dest;
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
    public Float2 transformPosition(Float2R v, @Mutated Float2 dest) {
        return transformPosition(v.x(), v.y(), dest);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformPosition(Float2R v, @Mutated Double2 dest) {
        return transformPosition(v.x(), v.y(), dest);
    }




    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
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
    public Float2 transformPosition(float vX, float vY, @Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = sd[6] + vX;
            dd[1] = sd[7] + vY;
            return dest;
        }
        return transformPosition_general(vX, vY, dest);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformPosition(float vX, float vY, @Mutated Double2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, Math.fma(sd[3], vY, sd[6]));
        dd[1] = Math.fma(sd[1], vX, Math.fma(sd[4], vY, sd[7]));
        return dest;
    }

    public float m00() { return data[0]; }
    public float m01() { return data[3]; }
    public float m02() { return data[6]; }
    public float m10() { return data[1]; }
    public float m11() { return data[4]; }
    public float m12() { return data[7]; }
    public float m20() { return data[2]; }
    public float m21() { return data[5]; }
    public float m22() { return data[8]; }

    @Override public String toString() {
        return "Float3x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float3x3Impl)) return false;
        Float3x3Impl o = (Float3x3Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3])
            && Float.isFinite(data[4])
            && Float.isFinite(data[5])
            && Float.isFinite(data[6])
            && Float.isFinite(data[7])
            && Float.isFinite(data[8]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7])
            || Float.isNaN(data[8]);
    }

    @Override public boolean equalsEpsilon(Float3x3R other, float epsilon) {
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

    public float[] storeCM(@Mutated float[] dest, int offset) {
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
    public @Mutated Float3x3 loadCM(float[] src, int offset) {
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
    @Mutated public Float3x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Float3x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Float3x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float3x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
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
    public @Mutated Float3x3 loadCM(double[] src, int offset) {
        this.data[0] = (float) src[offset + 0];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Float3x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float3x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private float[] storeRM_distinct(float[] dest, int offset) {
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
    private float[] storeRM_aliased(float[] dest, int offset) {
        float[] d = this.data;
        float t0 = d[0];
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
        float t8 = d[8];
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
    @Mutated public Float3x3 loadRM(float[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Float3x3 loadRM_distinct(float[] src, int offset) {
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
    private Float3x3 loadRM_aliased(float[] src, int offset) {
        float t0 = src[offset + 0];
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float t4 = src[offset + 4];
        float t5 = src[offset + 5];
        float t6 = src[offset + 6];
        float t7 = src[offset + 7];
        float t8 = src[offset + 8];
        float[] d = this.data;
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float3x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Float3x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float3x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float3x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
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
    public @Mutated Float3x3 loadRM(double[] src, int offset) {
        this.data[0] = (float) src[offset + 0];
        this.data[3] = (float) src[offset + 1];
        this.data[6] = (float) src[offset + 2];
        this.data[1] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[7] = (float) src[offset + 5];
        this.data[2] = (float) src[offset + 6];
        this.data[5] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float3x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float3x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadCM(float[] src, int offset, int stride) {
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
    @Mutated public Float3x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Float3x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float3x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float3x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
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
    public @Mutated Float3x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[_p1];
        this.data[4] = (float) src[_p1 + 1];
        this.data[5] = (float) src[_p1 + 2];
        this.data[6] = (float) src[_p2];
        this.data[7] = (float) src[_p2 + 1];
        this.data[8] = (float) src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float3x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float3x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float3x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }
    private float[] storeRM_distinct(float[] dest, int offset, int stride) {
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
    private float[] storeRM_aliased(float[] dest, int offset, int stride) {
        float[] d = this.data;
        float t0 = d[0];
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
        float t8 = d[8];
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
    @Mutated public Float3x3 loadRM(float[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Float3x3 loadRM_distinct(float[] src, int offset, int stride) {
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
    private Float3x3 loadRM_aliased(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float t0 = src[offset];
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[_p1];
        float t4 = src[_p1 + 1];
        float t5 = src[_p1 + 2];
        float t6 = src[_p2];
        float t7 = src[_p2 + 1];
        float t8 = src[_p2 + 2];
        float[] d = this.data;
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float3x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Float3x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float3x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float3x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = (float) src[offset];
        this.data[3] = (float) src[offset + 1];
        this.data[6] = (float) src[offset + 2];
        this.data[1] = (float) src[_p1];
        this.data[4] = (float) src[_p1 + 1];
        this.data[7] = (float) src[_p1 + 2];
        this.data[2] = (float) src[_p2];
        this.data[5] = (float) src[_p2 + 1];
        this.data[8] = (float) src[_p2 + 2];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float3x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float3x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float3x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[5];
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.data[6];
        dest[offset + 9] = this.data[7];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }
    public FloatBuffer storeCM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public Float3x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[5];
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.data[6];
        dest[offset + 9] = this.data[7];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }
    public DoubleBuffer storeCM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeCM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4DoubleUnsafe(this, address);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[3];
        dest[offset + 2] = this.data[6];
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[7];
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.data[2];
        dest[offset + 9] = this.data[5];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }
    public FloatBuffer storeRM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public Float3x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[3];
        dest[offset + 2] = this.data[6];
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[4];
        dest[offset + 6] = this.data[7];
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.data[2];
        dest[offset + 9] = this.data[5];
        dest[offset + 10] = this.data[8];
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }
    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeRM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4DoubleUnsafe(this, address);
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
