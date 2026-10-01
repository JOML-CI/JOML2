// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float3x3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float3x3Impl implements Float3x3 {

    public float m00;
    public float m10;
    public float m20;
    public float m01;
    public float m11;
    public float m21;
    public float m02;
    public float m12;
    public float m22;
    public int properties;

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

    public Float3x3Impl() {
        m00 = 1;
        m11 = 1;
        m22 = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float3x3Impl(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m02 = m02;
        this.m12 = m12;
        this.m22 = m22;
        this.properties = determineProperties();
    }

    public Float3x3Impl(Float3x3R src) {
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m20 = src.m20();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.m21 = src.m21();
        this.m02 = src.m02();
        this.m12 = src.m12();
        this.m22 = src.m22();
        this.properties = ((Float3x3Impl) src).properties;
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
        if (this.m20 != 0 || this.m21 != 0 || this.m22 != 1) return 0;
        if (this.m00 != 1 || this.m01 != 0 || this.m10 != 0 || this.m11 != 1) return 1;
        if (this.m02 != 0 || this.m12 != 0) return 7;
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
        Float3Impl d = (Float3Impl) dest;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; _idxSw2 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; _idxSw2 = 0.0f; break;
            case 2: _idxSw0 = 0.0f; _idxSw1 = 0.0f; _idxSw2 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
        return d;
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float3 getColumn_translation(int col, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw3 = 1.0f; _idxSw4 = 0.0f; _idxSw5 = 0.0f; break;
            case 1: _idxSw3 = 0.0f; _idxSw4 = 1.0f; _idxSw5 = 0.0f; break;
            case 2: _idxSw3 = this.m02; _idxSw4 = this.m12; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw3;
        d.y = _idxSw4;
        d.z = _idxSw5;
        return d;
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float3 getColumn_general(int col, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw6;
        float _idxSw7;
        float _idxSw8;
        switch (col) {
            case 0: _idxSw6 = this.m00; _idxSw7 = this.m10; _idxSw8 = this.m20; break;
            case 1: _idxSw6 = this.m01; _idxSw7 = this.m11; _idxSw8 = this.m21; break;
            case 2: _idxSw6 = this.m02; _idxSw7 = this.m12; _idxSw8 = this.m22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw6;
        d.y = _idxSw7;
        d.z = _idxSw8;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _idxSw9;
        float _idxSw10;
        float _idxSw11;
        switch (col) {
            case 0: _idxSw9 = this.m00; _idxSw10 = this.m10; _idxSw11 = this.m20; break;
            case 1: _idxSw9 = this.m01; _idxSw10 = this.m11; _idxSw11 = this.m21; break;
            case 2: _idxSw9 = this.m02; _idxSw10 = this.m12; _idxSw11 = this.m22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw9;
        d.y = _idxSw10;
        d.z = _idxSw11;
        return d;
    }



    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_translation(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = Math.fma(this.m12, this.m12, 1.0f);
        d.x = _t0 < Math.fma(this.m12, this.m12, Math.fma(this.m02, this.m02, 1.0f)) * 1.0E-7f ? 0.0f : (float) Math.atan2(-this.m12, 1.0f);
        d.y = (float) Math.atan2(this.m02, (float) Math.sqrt(_t0));
        d.z = 0.0f;
        return d;
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m12, this.m12, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m02, this.m02, _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(this.m21, this.m11);
            d.z = 0.0f;
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m12, this.m22);
            d.z = (float) Math.atan2(-this.m01, this.m00);
            d.x = _buf0;
        }
        d.y = (float) Math.atan2(this.m02, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m12, this.m12, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m02, this.m02, _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(this.m21, this.m11);
            d.z = 0.0f;
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m12, this.m22);
            d.z = (float) Math.atan2(-this.m01, this.m00);
            d.x = _buf0;
        }
        d.y = (float) Math.atan2(this.m02, (float) Math.sqrt(_t1));
        return d;
    }




    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Float3 getEulerAnglesXZY_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m11, this.m11, this.m21 * this.m21);
        if (_t1 < Math.fma(this.m01, this.m01, _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(-this.m12, this.m22);
            float _buf1 = 0.0f;
            d.x = _buf0;
            d.y = _buf1;
        } else {
            float _buf0 = (float) Math.atan2(this.m21, this.m11);
            float _buf1 = (float) Math.atan2(this.m02, this.m00);
            d.x = _buf0;
            d.y = _buf1;
        }
        d.z = (float) Math.atan2(-this.m01, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = (float) Math.atan2(this.m02, 1.0f);
            d.z = 0.0f;
            return d;
        }
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m11, this.m11, this.m21 * this.m21);
        if (_t1 < Math.fma(this.m01, this.m01, _t1) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(-this.m12, this.m22);
            float _buf1 = 0.0f;
            d.x = _buf0;
            d.y = _buf1;
        } else {
            float _buf0 = (float) Math.atan2(this.m21, this.m11);
            float _buf1 = (float) Math.atan2(this.m02, this.m00);
            d.x = _buf0;
            d.y = _buf1;
        }
        d.z = (float) Math.atan2(-this.m01, (float) Math.sqrt(_t1));
        return d;
    }



    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_translation(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = Math.fma(this.m02, this.m02, 1.0f);
        d.x = (float) Math.atan2(-this.m12, (float) Math.sqrt(_t0));
        d.y = _t0 < Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f)) * 1.0E-7f ? 0.0f : (float) Math.atan2(this.m02, 1.0f);
        d.z = 0.0f;
        return d;
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m02, this.m02, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m12, this.m12, _t1) * 1.0E-7f) {
            d.y = (float) Math.atan2(-this.m20, this.m00);
            d.z = 0.0f;
        } else {
            d.y = (float) Math.atan2(this.m02, this.m22);
            d.z = (float) Math.atan2(this.m10, this.m11);
        }
        d.x = (float) Math.atan2(-this.m12, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m02, this.m02, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m12, this.m12, _t1) * 1.0E-7f) {
            d.y = (float) Math.atan2(-this.m20, this.m00);
            d.z = 0.0f;
        } else {
            d.y = (float) Math.atan2(this.m02, this.m22);
            d.z = (float) Math.atan2(this.m10, this.m11);
        }
        d.x = (float) Math.atan2(-this.m12, (float) Math.sqrt(_t1));
        return d;
    }



    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_translation(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = Math.fma(this.m12, this.m12, 1.0f);
        if (_t0 < _t0 * 1.0E-7f) {
            d.x = 0.0f;
            d.y = (float) Math.atan2(this.m02, 1.0f);
        } else {
            d.x = (float) Math.atan2(-this.m12, 1.0f);
            d.y = 0.0f;
        }
        d.z = (float) Math.atan2(0.0f, (float) Math.sqrt(_t0));
        return d;
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m11, this.m11, this.m12 * this.m12);
        if (_t1 < Math.fma(this.m10, this.m10, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.y = (float) Math.atan2(this.m02, this.m22);
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m12, this.m11);
            d.y = (float) Math.atan2(-this.m20, this.m00);
            d.x = _buf0;
        }
        d.z = (float) Math.atan2(this.m10, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m11, this.m11, this.m12 * this.m12);
        if (_t1 < Math.fma(this.m10, this.m10, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.y = (float) Math.atan2(this.m02, this.m22);
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m12, this.m11);
            d.y = (float) Math.atan2(-this.m20, this.m00);
            d.x = _buf0;
        }
        d.z = (float) Math.atan2(this.m10, (float) Math.sqrt(_t1));
        return d;
    }



    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_orthogonal(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m01, this.m01, this.m11 * this.m11);
        float _buf0 = 0.0f;
        float _buf1 = 0.0f;
        d.z = _t1 < _t1 * 1.0E-7f ? (float) Math.atan2(this.m10, this.m00) : (float) Math.atan2(-this.m01, this.m11);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m01, this.m01, this.m11 * this.m11);
        if (_t1 < Math.fma(this.m21, this.m21, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.z = (float) Math.atan2(this.m10, this.m00);
            d.y = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m20, this.m22);
            d.z = (float) Math.atan2(-this.m01, this.m11);
            d.y = _buf0;
        }
        d.x = (float) Math.atan2(this.m21, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m01, this.m01, this.m11 * this.m11);
        if (_t1 < Math.fma(this.m21, this.m21, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.z = (float) Math.atan2(this.m10, this.m00);
            d.y = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(-this.m20, this.m22);
            d.z = (float) Math.atan2(-this.m01, this.m11);
            d.y = _buf0;
        }
        d.x = (float) Math.atan2(this.m21, (float) Math.sqrt(_t1));
        return d;
    }




    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Float3 getEulerAnglesZYX_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = Math.fma(this.m21, this.m21, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m20, this.m20, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.z = (float) Math.atan2(-this.m01, this.m11);
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(this.m21, this.m22);
            d.z = (float) Math.atan2(this.m10, this.m00);
            d.x = _buf0;
        }
        d.y = (float) Math.atan2(-this.m20, (float) Math.sqrt(_t1));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            Float3Impl d = (Float3Impl) dest;
            float _buf0 = 0.0f;
            d.y = 0.0f;
            d.z = (float) Math.atan2(this.m10, this.m00);
            d.x = _buf0;
            return d;
        }
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = Math.fma(this.m21, this.m21, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m20, this.m20, _t1) * 1.0E-7f) {
            float _buf0 = 0.0f;
            d.z = (float) Math.atan2(-this.m01, this.m11);
            d.x = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(this.m21, this.m22);
            d.z = (float) Math.atan2(this.m10, this.m00);
            d.x = _buf0;
        }
        d.y = (float) Math.atan2(-this.m20, (float) Math.sqrt(_t1));
        return d;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code getNormalizedRotation},
     * {@code decomposeRotation} and {@code getUnnormalizedRotation}; reached only through them.
     */
    private FloatQuat getNormalizedRotation_identity(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = 0.0f;
        d.y = 0.0f;
        d.z = 0.0f;
        d.w = 1.0f;
        return d;
    }

    /** Private store group 0 of {@code getNormalizedRotation_translation}: computes and stores it; reached only through it. */
    private void getNormalizedRotation_translation_s6f858fd1_c0(FloatQuatImpl _dst, float _t13, float _sp0, float _t21, float _t10, float _t6, float _t18, float _t5, float _sp1, float _t22, float _t20, float _t23, float _t24, float _t19, float _t17) {
        _dst.x = _t13 > 0.0f ? -(_sp0 * _t21) : _t10 > _t6 ? 0.5f * (float) Math.sqrt(_t18) : 1.0f > _t5 ? 0.0f : _sp1 * _t22;
        _dst.y = _t13 > 0.0f ? _sp1 * _t21 : _t10 > _t6 ? 0.0f : 1.0f > _t5 ? 0.5f * (float) Math.sqrt(_t20) : _sp0 * _t22;
        _dst.z = _t13 > 0.0f ? 0.0f : _t10 > _t6 ? _sp1 * _t23 : 1.0f > _t5 ? _sp0 * _t24 : 0.5f * (float) Math.sqrt(_t19);
        _dst.w = _t13 > 0.0f ? 0.5f * (float) Math.sqrt(_t17) : _t10 > _t6 ? -(_sp0 * _t23) : 1.0f > _t5 ? _sp1 * _t24 : 0.0f;
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_translation(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _t1 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, 1.0f));
        float _t2 = (1.0f / (float) Math.sqrt(_t1));
        float _t5, _sp0, _sp1;
        if (_t1 != 0.0f) {
            _t5 = _t2;
            _sp0 = 0.5f * _r1 * _t2;
            _sp1 = 0.5f * _r0 * _t2;
        } else {
            _t5 = 0.0f;
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
        getNormalizedRotation_translation_s6f858fd1_c0(d, _t13, _sp0, (1.0f / (float) Math.sqrt(_t17)), _t10, Math.max(1.0f, _t5), _t18, _t5, _sp1, (1.0f / (float) Math.sqrt(_t19)), _t20, (1.0f / (float) Math.sqrt(_t18)), (1.0f / (float) Math.sqrt(_t20)), _t19, _t17);
        return d;
    }

    /**
     * Private store group 0 of {@code getNormalizedRotation_general}: computes and stores it.
     * Shared by the identical private paths of {@code getNormalizedRotation},
     * {@code getUnnormalizedRotation} and {@code decomposeRotation}; reached only through them.
     */
    private void getNormalizedRotation_general_s6f858fd1_c0(FloatQuatImpl _dst, float _t58, float _sp0, float _t36, float _t49, float _t37, float _t63, float _t23, float _t26, float _sp1, float _t53, float _sp2, float _t55, float _t56, float _sp3, float _t64, float _t39, float _t57, float _t65, float _t62) {
        _dst.x = _t58 > 0.0f ? _sp0 * _t36 : _t49 > _t37 ? 0.5f * (float) Math.sqrt(_t63) : _t23 > _t26 ? _sp1 * _t53 : _sp2 * _t55;
        _dst.y = _t58 > 0.0f ? _sp0 * _t56 : _t49 > _t37 ? _sp3 * _t53 : _t23 > _t26 ? 0.5f * (float) Math.sqrt(_t64) : _sp2 * _t39;
        _dst.z = _t58 > 0.0f ? _sp0 * _t57 : _t49 > _t37 ? _sp3 * _t55 : _t23 > _t26 ? _sp1 * _t39 : 0.5f * (float) Math.sqrt(_t65);
        _dst.w = _t58 > 0.0f ? 0.5f * (float) Math.sqrt(_t62) : _t49 > _t37 ? _sp3 * _t36 : _t23 > _t26 ? _sp1 * _t56 : _sp2 * _t57;
    }

    /** Private tail of {@code getNormalizedRotation_general}; reached only through it. */
    private void getNormalizedRotation_general_s6f858fd1_tail(FloatQuatImpl _dst, float _t7, float _r4, float _t10, float _t8, float _r6, float _t11, float _r3, float _t6, float _r0, float _t9, float _r7, float _r8, float _t22, float _t23, float _t21) {
        float _t24, _t26;
        if (_t7 != 0.0f) {
            _t24 = _r4 * _t10;
            _t26 = _r3 * _t10;
        } else {
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _r6 * _t11;
            _t28 = _r7 * _t11;
            _t29 = _r8 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t27 = _t6 != 0.0f ? _r0 * _t9 : 0.0f;
        getNormalizedRotation_general_s6f858fd1_tail2(_dst, Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)), _t28, _t29, _t25, _t23, _t21, _t24, _t26, _t27 - _t22, Math.max(_t23, _t26), _t27 + _t22);
    }

    /** Private tail of {@code getNormalizedRotation_general}; reached only through it. */
    private void getNormalizedRotation_general_s6f858fd1_tail2(FloatQuatImpl _dst, float _t48, float _t28, float _t29, float _t25, float _t23, float _t21, float _t24, float _t26, float _t36, float _t37, float _t39) {
        float _t49, _t50, _t51;
        if (_t48 < 0.0f) {
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
        getNormalizedRotation_general_s6f858fd1_c0(_dst, _t58, 0.5f * (1.0f / (float) Math.sqrt(_t62)), _t36, _t49, _t37, _t63, _t23, _t26, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t50 + _t21, 0.5f * (1.0f / (float) Math.sqrt(_t65)), _t51 + _t24, _t24 - _t51, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t64, _t39, _t50 - _t21, _t65, _t62);
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_general(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.m21;
        float _r1 = this.m01;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t6 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t7 = Math.fma(_r3, _r3, Math.fma(_r4, _r4, _r5 * _r5));
        float _t8 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        float _t9 = (1.0f / (float) Math.sqrt(_t6));
        float _t10 = (1.0f / (float) Math.sqrt(_t7));
        float _t21, _t23;
        if (_t6 != 0.0f) {
            _t21 = _r1 * _t9;
            _t23 = _r2 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
        }
        getNormalizedRotation_general_s6f858fd1_tail(d, _t7, _r4, _t10, _t8, _r6, (1.0f / (float) Math.sqrt(_t8)), _r3, _t6, _r0, _t9, _r7, _r8, _t7 != 0.0f ? _r5 * _t10 : 0.0f, _t23, _t21);
        return d;
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
     * Private store group 0 of {@code getNormalizedRotation}: computes and stores it. Shared by the
     * identical private paths of {@code getNormalizedRotation}, {@code getUnnormalizedRotation} and
     * {@code decomposeRotation}; reached only through them.
     */
    private void getNormalizedRotation_s62e3ac38_c0(DoubleQuatImpl _dst, float _t58, float _sp0, float _t36, float _t49, float _t37, float _t63, float _t23, float _t26, float _sp1, float _t53, float _sp2, float _t55, float _t56, float _sp3, float _t64, float _t39, float _t57, float _t65, float _t62) {
        _dst.x = _t58 > 0.0f ? _sp0 * _t36 : _t49 > _t37 ? 0.5f * (float) Math.sqrt(_t63) : _t23 > _t26 ? _sp1 * _t53 : _sp2 * _t55;
        _dst.y = _t58 > 0.0f ? _sp0 * _t56 : _t49 > _t37 ? _sp3 * _t53 : _t23 > _t26 ? 0.5f * (float) Math.sqrt(_t64) : _sp2 * _t39;
        _dst.z = _t58 > 0.0f ? _sp0 * _t57 : _t49 > _t37 ? _sp3 * _t55 : _t23 > _t26 ? _sp1 * _t39 : 0.5f * (float) Math.sqrt(_t65);
        _dst.w = _t58 > 0.0f ? 0.5f * (float) Math.sqrt(_t62) : _t49 > _t37 ? _sp3 * _t36 : _t23 > _t26 ? _sp1 * _t56 : _sp2 * _t57;
    }

    /** Private tail of {@code getNormalizedRotation}; reached only through it. */
    private void getNormalizedRotation_s62e3ac38_tail(DoubleQuatImpl _dst, float _t7, float _r4, float _t10, float _t8, float _r6, float _t11, float _r3, float _t6, float _r0, float _t9, float _r7, float _r8, float _t22, float _t23, float _t21) {
        float _t24, _t26;
        if (_t7 != 0.0f) {
            _t24 = _r4 * _t10;
            _t26 = _r3 * _t10;
        } else {
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _r6 * _t11;
            _t28 = _r7 * _t11;
            _t29 = _r8 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t27 = _t6 != 0.0f ? _r0 * _t9 : 0.0f;
        getNormalizedRotation_s62e3ac38_tail2(_dst, Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)), _t28, _t29, _t25, _t23, _t21, _t24, _t26, _t27 - _t22, Math.max(_t23, _t26), _t27 + _t22);
    }

    /** Private tail of {@code getNormalizedRotation}; reached only through it. */
    private void getNormalizedRotation_s62e3ac38_tail2(DoubleQuatImpl _dst, float _t48, float _t28, float _t29, float _t25, float _t23, float _t21, float _t24, float _t26, float _t36, float _t37, float _t39) {
        float _t49, _t50, _t51;
        if (_t48 < 0.0f) {
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
        getNormalizedRotation_s62e3ac38_c0(_dst, _t58, 0.5f * (1.0f / (float) Math.sqrt(_t62)), _t36, _t49, _t37, _t63, _t23, _t26, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t50 + _t21, 0.5f * (1.0f / (float) Math.sqrt(_t65)), _t51 + _t24, _t24 - _t51, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t64, _t39, _t50 - _t21, _t65, _t62);
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.m21;
        float _r1 = this.m01;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t6 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t7 = Math.fma(_r3, _r3, Math.fma(_r4, _r4, _r5 * _r5));
        float _t8 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        float _t9 = (1.0f / (float) Math.sqrt(_t6));
        float _t10 = (1.0f / (float) Math.sqrt(_t7));
        float _t21, _t23;
        if (_t6 != 0.0f) {
            _t21 = _r1 * _t9;
            _t23 = _r2 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
        }
        getNormalizedRotation_s62e3ac38_tail(d, _t7, _r4, _t10, _t8, _r6, (1.0f / (float) Math.sqrt(_t8)), _r3, _t6, _r0, _t9, _r7, _r8, _t7 != 0.0f ? _r5 * _t10 : 0.0f, _t23, _t21);
        return d;
    }



    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_translation(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw3 = 1.0f; _idxSw4 = 0.0f; _idxSw5 = this.m02; break;
            case 1: _idxSw3 = 0.0f; _idxSw4 = 1.0f; _idxSw5 = this.m12; break;
            case 2: _idxSw3 = 0.0f; _idxSw4 = 0.0f; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw3;
        d.y = _idxSw4;
        d.z = _idxSw5;
        return d;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_general(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw6;
        float _idxSw7;
        float _idxSw8;
        switch (row) {
            case 0: _idxSw6 = this.m00; _idxSw7 = this.m01; _idxSw8 = this.m02; break;
            case 1: _idxSw6 = this.m10; _idxSw7 = this.m11; _idxSw8 = this.m12; break;
            case 2: _idxSw6 = this.m20; _idxSw7 = this.m21; _idxSw8 = this.m22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw6;
        d.y = _idxSw7;
        d.z = _idxSw8;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _idxSw9;
        float _idxSw10;
        float _idxSw11;
        switch (row) {
            case 0: _idxSw9 = this.m00; _idxSw10 = this.m01; _idxSw11 = this.m02; break;
            case 1: _idxSw9 = this.m10; _idxSw10 = this.m11; _idxSw11 = this.m12; break;
            case 2: _idxSw9 = this.m20; _idxSw10 = this.m21; _idxSw11 = this.m22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw9;
        d.y = _idxSw10;
        d.z = _idxSw11;
        return d;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getScale} and {@code decomposeScale}; reached only through
     * them.
     */
    private Float3 getScale_identity(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = 1.0f;
        d.y = 1.0f;
        d.z = 1.0f;
        return d;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_translation(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = 1.0f;
        d.y = 1.0f;
        d.z = (float) Math.sqrt(Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f)));
        return d;
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = (float) Math.sqrt(Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10)));
        d.y = (float) Math.sqrt(Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11)));
        d.z = (float) Math.sqrt(Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = (float) Math.sqrt(Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10)));
        d.y = (float) Math.sqrt(Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11)));
        d.z = (float) Math.sqrt(Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12)));
        return d;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_identity(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = 0.0f;
        d.y = 0.0f;
        return d;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_general(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.m02;
        d.y = this.m12;
        return d;
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
        Double2Impl d = (Double2Impl) dest;
        d.x = this.m02;
        d.y = this.m12;
        return d;
    }



    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_translation(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = -(0.25f * this.m12);
        d.y = 0.25f * this.m02;
        d.z = 0.0f;
        d.w = 1.0f;
        return d;
    }

    /** Private store group 0 of {@code getUnnormalizedRotation_orthogonal}: computes and stores it; reached only through it. */
    private void getUnnormalizedRotation_orthogonal_s6f858fd1_c0(FloatQuatImpl _dst, float _t6, float _sp0, float _t14, float _r3, float _t0, float _t11, float _r2, float _sp2, float _t15, float _sp1, float _t16, float _t17, float _t12, float _sp3, float _t13, float _t10) {
        _dst.x = _t6 > 0.0f ? -(_sp0 * _t14) : _r3 > _t0 ? 0.5f * (float) Math.sqrt(_t11) : _r2 > 1.0f ? _sp2 * _t15 : _sp1 * _t16;
        _dst.y = _t6 > 0.0f ? _sp1 * _t14 : _r3 > _t0 ? _sp2 * _t17 : _r2 > 1.0f ? 0.5f * (float) Math.sqrt(_t12) : _sp0 * _t16;
        _dst.z = _t6 > 0.0f ? _sp3 * _t14 : _r3 > _t0 ? _sp1 * _t17 : _r2 > 1.0f ? _sp0 * _t15 : 0.5f * (float) Math.sqrt(_t13);
        _dst.w = _t6 > 0.0f ? 0.5f * (float) Math.sqrt(_t10) : _r3 > _t0 ? -(_sp0 * _t17) : _r2 > 1.0f ? _sp1 * _t15 : _sp3 * _t16;
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_orthogonal(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _r2 = this.m11;
        float _r3 = this.m00;
        float _r4 = this.m01;
        float _r5 = this.m10;
        float _t3 = _r3 + _r2;
        float _t6 = 1.0f + _t3;
        float _t10 = 1.0f + _t6;
        float _t11 = 1.0f + (_r3 - (1.0f + _r2));
        float _t12 = 1.0f + (_r2 - (1.0f + _r3));
        float _t13 = 1.0f + (1.0f - _t3);
        getUnnormalizedRotation_orthogonal_s6f858fd1_c0(d, _t6, 0.5f * _r1, (1.0f / (float) Math.sqrt(_t10)), _r3, Math.max(_r2, 1.0f), _t11, _r2, 0.5f * (_r4 + _r5), (1.0f / (float) Math.sqrt(_t12)), 0.5f * _r0, (1.0f / (float) Math.sqrt(_t13)), (1.0f / (float) Math.sqrt(_t11)), _t12, 0.5f * (_r5 - _r4), _t13, _t10);
        return d;
    }

    /** Private tail of {@code getUnnormalizedRotation_general}; reached only through it. */
    private void getUnnormalizedRotation_general_s6f858fd1_tail(FloatQuatImpl _dst, float _t15, float _t10, float _sp0, float _t1, float _r0, float _t2, float _r1, float _r4, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        getNormalizedRotation_general_s6f858fd1_c0(_dst, _t10, _sp0, _t1, _r0, _t2, _t15, _r1, _r4, _sp1, _t4, _sp2, _t6, _t7, 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_general(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.m00;
        float _r1 = this.m11;
        float _r2 = this.m21;
        float _r3 = this.m12;
        float _r4 = this.m22;
        float _r5 = this.m01;
        float _r6 = this.m10;
        float _r7 = this.m02;
        float _r8 = this.m20;
        float _t0 = _r0 + _r1;
        float _t10 = _r4 + _t0;
        float _t14 = 1.0f + _t10;
        float _t16 = 1.0f + (_r1 - (_r0 + _r4));
        float _t17 = 1.0f + (_r4 - _t0);
        getUnnormalizedRotation_general_s6f858fd1_tail(d, 1.0f + (_r0 - (_r1 + _r4)), _t10, 0.5f * (1.0f / (float) Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5f * (1.0f / (float) Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
        return d;
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

    /** Private tail of {@code getUnnormalizedRotation}; reached only through it. */
    private void getUnnormalizedRotation_s62e3ac38_tail(DoubleQuatImpl _dst, float _t15, float _t10, float _sp0, float _t1, float _r0, float _t2, float _r1, float _r4, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        getNormalizedRotation_s62e3ac38_c0(_dst, _t10, _sp0, _t1, _r0, _t2, _t15, _r1, _r4, _sp1, _t4, _sp2, _t6, _t7, 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.m00;
        float _r1 = this.m11;
        float _r2 = this.m21;
        float _r3 = this.m12;
        float _r4 = this.m22;
        float _r5 = this.m01;
        float _r6 = this.m10;
        float _r7 = this.m02;
        float _r8 = this.m20;
        float _t0 = _r0 + _r1;
        float _t10 = _r4 + _t0;
        float _t14 = 1.0f + _t10;
        float _t16 = 1.0f + (_r1 - (_r0 + _r4));
        float _t17 = 1.0f + (_r4 - _t0);
        getUnnormalizedRotation_s62e3ac38_tail(d, 1.0f + (_r0 - (_r1 + _r4)), _t10, 0.5f * (1.0f / (float) Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5f * (1.0f / (float) Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
        return d;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor}, {@code invert}, {@code normal} and
     * {@code transpose}; reached only through them.
     */
    private Float3x3 cofactor_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }



    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float3x3 cofactor_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = -this.m02;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = -this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Float3x3 cofactor_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m20 = -this.m02;
        d.m21 = -this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float3x3 cofactor_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m20 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m01 = -this.m10;
        float _buf1 = this.m11;
        d.m21 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code cofactor}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code cofactor} and {@code normal};
     * reached only through them.
     */
    private Float3x3 cofactor_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m20 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m01 = -this.m10;
        float _buf1 = this.m11;
        d.m21 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m11;
        float _buf1 = -this.m01;
        d.m20 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11));
        float _buf2 = -this.m10;
        float _buf3 = this.m00;
        d.m21 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12));
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        float _buf1 = Math.fma(this.m02, this.m21, -(this.m01 * this.m22));
        float _buf2 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11));
        float _buf3 = Math.fma(this.m12, this.m20, -(this.m10 * this.m22));
        float _buf4 = Math.fma(this.m00, this.m22, -(this.m02 * this.m20));
        float _buf5 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12));
        d.m02 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        d.m12 = Math.fma(this.m01, this.m20, -(this.m00 * this.m21));
        d.m22 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        float _buf1 = Math.fma(this.m02, this.m21, -(this.m01 * this.m22));
        float _buf2 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11));
        float _buf3 = Math.fma(this.m12, this.m20, -(this.m10 * this.m22));
        float _buf4 = Math.fma(this.m00, this.m22, -(this.m02 * this.m20));
        float _buf5 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12));
        d.m02 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        d.m12 = Math.fma(this.m01, this.m20, -(this.m00 * this.m21));
        d.m22 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        return (float) Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m02, this.m02, this.m10 * this.m10) + (Math.fma(this.m11, this.m11, this.m12 * this.m12) + Math.fma(this.m20, this.m20, Math.fma(this.m21, this.m21, this.m22 * this.m22))));
    }




    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }



    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = -this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m10;
        float _buf1 = this.m11;
        d.m21 = 0.0f;
        float _buf2 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m12 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = -this.m10;
        d.m01 = this.m10;
        float _buf1 = this.m11;
        float _buf2 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m12 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = this.m11 * _t3_inv;
        float _buf1 = -(this.m10 * _t3_inv);
        d.m20 = 0.0f;
        float _buf2 = -(this.m01 * _t3_inv);
        float _buf3 = this.m00 * _t3_inv;
        d.m21 = 0.0f;
        float _buf4 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv;
        d.m12 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = this.m11 * _t3_inv;
        float _buf1 = -(this.m10 * _t3_inv);
        float _buf2 = -(this.m01 * _t3_inv);
        float _buf3 = this.m00 * _t3_inv;
        float _buf4 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv;
        d.m12 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private column 1 of {@code invert_general}: computes and stores it. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private void invert_general_s715c4c6e_c1(Float3x3Impl _dst, float _r6, float _r3, float _r8, float _r1, float _t13_inv, float _r7, float _r5) {
        _dst.m01 = Math.fma(_r6, _r3, -(_r8 * _r1)) * _t13_inv;
        _dst.m11 = Math.fma(_r7, _r1, -(_r6 * _r5)) * _t13_inv;
        _dst.m21 = Math.fma(_r8, _r5, -(_r7 * _r3)) * _t13_inv;
    }

    /**
     * Private column 2 of {@code invert_general}: computes and stores it. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private void invert_general_s715c4c6e_c2(Float3x3Impl _dst, float _r8, float _r2, float _r6, float _r0, float _t13_inv, float _r4, float _r7) {
        _dst.m02 = Math.fma(_r8, _r2, -(_r6 * _r0)) * _t13_inv;
        _dst.m12 = Math.fma(_r6, _r4, -(_r7 * _r2)) * _t13_inv;
        _dst.m22 = Math.fma(_r7, _r0, -(_r8 * _r4)) * _t13_inv;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m12;
        float _r3 = this.m21;
        float _r4 = this.m10;
        float _r5 = this.m20;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invert_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        d.m00 = _t6 * _t13_inv;
        d.m10 = Math.fma(_r2, _r5, -(_r4 * _r1)) * _t13_inv;
        d.m20 = _t7 * _t13_inv;
        invert_general_s715c4c6e_c1(d, _r6, _r3, _r8, _r1, _t13_inv, _r7, _r5);
        invert_general_s715c4c6e_c2(d, _r8, _r2, _r6, _r0, _t13_inv, _r4, _r7);
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m02 = -this.m02;
            d.m12 = -this.m12;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine_self(this);
        return invert_general(this);
    }

    /**
     * Private column 1 of {@code invert}: computes and stores it. Shared by 2 identical private
     * paths of {@code invert}; reached only through it.
     */
    private void invert_s37258727_c1(Double3x3Impl _dst, float _r6, float _r3, float _r8, float _r1, float _t13_inv, float _r7, float _r5) {
        _dst.m01 = Math.fma(_r6, _r3, -(_r8 * _r1)) * _t13_inv;
        _dst.m11 = Math.fma(_r7, _r1, -(_r6 * _r5)) * _t13_inv;
        _dst.m21 = Math.fma(_r8, _r5, -(_r7 * _r3)) * _t13_inv;
    }

    /**
     * Private column 2 of {@code invert}: computes and stores it. Shared by 2 identical private
     * paths of {@code invert}; reached only through it.
     */
    private void invert_s37258727_c2(Double3x3Impl _dst, float _r8, float _r2, float _r6, float _r0, float _t13_inv, float _r4, float _r7) {
        _dst.m02 = Math.fma(_r8, _r2, -(_r6 * _r0)) * _t13_inv;
        _dst.m12 = Math.fma(_r6, _r4, -(_r7 * _r2)) * _t13_inv;
        _dst.m22 = Math.fma(_r7, _r0, -(_r8 * _r4)) * _t13_inv;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m12;
        float _r3 = this.m21;
        float _r4 = this.m10;
        float _r5 = this.m20;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invert_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        d.m00 = _t6 * _t13_inv;
        d.m10 = Math.fma(_r2, _r5, -(_r4 * _r1)) * _t13_inv;
        d.m20 = _t7 * _t13_inv;
        invert_s37258727_c1(d, _r6, _r3, _r8, _r1, _t13_inv, _r7, _r5);
        invert_s37258727_c2(d, _r8, _r2, _r6, _r0, _t13_inv, _r4, _r7);
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_orthogonal_affine(@Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m20 = 0.0f;
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m21 = 0.0f;
        d.m02 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m12 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_orthogonal_affine_self(@Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m12 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.properties = _props;
        return d;
    }




    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1 * _t3_inv;
        d.m21 = 0.0f;
        d.m02 = -(this.m02 * _t0 * _t2_inv);
        d.m12 = -(this.m12 * _t1 * _t3_inv);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m11 = _t1 * _t3_inv;
        d.m02 = -(this.m02 * _t0 * _t2_inv);
        d.m12 = -(this.m12 * _t1 * _t3_inv);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private tail of {@code invert_degenerate_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private void invert_degenerate_general_s715c4c6e_tail(Float3x3Impl _dst, float _t28, float _t18, float _t27, float _t19, float _t16, float _t13, float _t14, float _t17, float _t20, float _t1, float _t0, float _t2, float _t15, float _t12) {
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        {
            float _t13_inv = _t2 * _t33_inv;
            _dst.m00 = _t27 * _t13_inv;
            _dst.m10 = Math.fma(_t14, _t17, -(_t16 * _t13)) * _t13_inv;
            _dst.m20 = _t28 * _t13_inv;
        }
        invert_general_s715c4c6e_c1(_dst, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t19, _t17);
        invert_general_s715c4c6e_c2(_dst, _t20, _t14, _t18, _t12, _t1 * _t33_inv, _t16, _t19);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m12;
        float _r3 = this.m20;
        float _r4 = this.m21;
        float _r5 = this.m22;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m02;
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        invert_degenerate_general_s715c4c6e_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = 0;
        return d;
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
    @Mutated private Float3x3 invert_degenerate() {
        if (Joml.RETURN_NEW) return invert_degenerate(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_orthogonal_affine_self(this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        return invert_degenerate_general(this);
    }

    /** Private tail of {@code invert_degenerate}; reached only through it. */
    private void invert_degenerate_s37258727_tail(Double3x3Impl _dst, float _t28, float _t18, float _t27, float _t19, float _t16, float _t13, float _t14, float _t17, float _t20, float _t1, float _t0, float _t2, float _t15, float _t12) {
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        {
            float _t13_inv = _t2 * _t33_inv;
            _dst.m00 = _t27 * _t13_inv;
            _dst.m10 = Math.fma(_t14, _t17, -(_t16 * _t13)) * _t13_inv;
            _dst.m20 = _t28 * _t13_inv;
        }
        invert_s37258727_c1(_dst, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t19, _t17);
        invert_s37258727_c2(_dst, _t20, _t14, _t18, _t12, _t1 * _t33_inv, _t16, _t19);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m12;
        float _r3 = this.m20;
        float _r4 = this.m21;
        float _r5 = this.m22;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m02;
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        invert_degenerate_s37258727_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code invertProduct_general}: computes and stores it. Shared by the
     * identical private paths of {@code invertProduct} and {@code normal}; reached only through
     * them.
     */
    private void invertProduct_general_s36a279f2_c1(Float3x3Impl _dst, float _t20, float _t22, float _t26, float _t19, float _t40_inv, float _t25, float _t24) {
        _dst.m01 = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        _dst.m11 = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        _dst.m21 = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
    }

    /**
     * Private column 2 of {@code invertProduct_general}: computes and stores it. Shared by 6
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_s36a279f2_c2(Float3x3Impl _dst, float _t26, float _t21, float _t18, float _t22, float _t40_inv, float _t23, float _t25) {
        _dst.m02 = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        _dst.m12 = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        _dst.m22 = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
    }

    /**
     * Private tail of {@code invertProduct_general}. Shared by 2 identical private paths of
     * {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_s36a279f2_tail(Float3x3Impl _dst, float _t40, float _t33, float _t20, float _t22, float _t26, float _t19, float _t21, float _t18, float _t24, float _t23, float _t25, float _t34) {
        float _t40_inv = 1.0f / _t40;
        _dst.m00 = _t33 * _t40_inv;
        _dst.m10 = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        _dst.m20 = _t34 * _t40_inv;
        invertProduct_general_s36a279f2_c1(_dst, _t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        invertProduct_general_s36a279f2_c2(_dst, _t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general(Float3x3R other, @Mutated Float3x3 dest) {
        float _r0 = other.m21();
        float _r1 = this.m12;
        float _r2 = other.m01();
        float _r3 = this.m10;
        float _r4 = other.m11();
        float _r5 = this.m11;
        float _r6 = other.m22();
        float _r7 = this.m22;
        float _r8 = other.m02();
        float _r9 = this.m20;
        float _r10 = other.m12();
        float _r11 = this.m21;
        float _r12 = this.m02;
        float _r13 = this.m00;
        float _r14 = this.m01;
        float _r15 = other.m20();
        float _r16 = other.m00();
        float _r17 = other.m10();
        return invertProduct_general_se2d7dc11_1(other, dest, (Float3x3Impl) dest, Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5)), Math.fma(_r6, _r7, Math.fma(_r8, _r9, _r10 * _r11)), Math.fma(_r0, _r7, Math.fma(_r2, _r9, _r4 * _r11)), Math.fma(_r6, _r1, Math.fma(_r8, _r3, _r10 * _r5)), Math.fma(_r6, _r12, Math.fma(_r8, _r13, _r10 * _r14)), Math.fma(_r15, _r1, Math.fma(_r16, _r3, _r17 * _r5)), Math.fma(_r15, _r7, Math.fma(_r16, _r9, _r17 * _r11)), Math.fma(_r15, _r12, Math.fma(_r16, _r13, _r17 * _r14)), Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14)));
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_se2d7dc11_1(Float3x3R other, Float3x3 dest, Float3x3Impl d, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        float _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        invertProduct_general_s36a279f2_tail(d, _t40, _t33, _t20, _t22, _t26, _t19, _t21, _t18, _t24, _t23, _t25, _t34);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m11();
        float _r1 = other.m22();
        float _r2 = other.m12();
        float _r3 = other.m21();
        float _r4 = other.m10();
        float _r5 = other.m20();
        float _r6 = other.m02();
        float _r7 = other.m00();
        float _r8 = other.m01();
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t13_inv = 1.0f / _t13;
        d.m00 = _t6 * _t13_inv;
        d.m10 = Math.fma(_r2, _r5, -(_r4 * _r1)) * _t13_inv;
        d.m20 = _t7 * _t13_inv;
        invert_general_s715c4c6e_c1(d, _r6, _r3, _r8, _r1, _t13_inv, _r7, _r5);
        invert_general_s715c4c6e_c2(d, _r8, _r2, _r6, _r0, _t13_inv, _r4, _r7);
        d.properties = ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private column 1 of {@code invertProduct_translation}: computes and stores it. Shared by 4
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_translation_s36a279f2_c1(Float3x3Impl _dst, float _r0, float _t2, float _r3, float _t5, float _t19_inv, float _t4, float _r7) {
        _dst.m01 = Math.fma(_r0, _t2, -(_r3 * _t5)) * _t19_inv;
        _dst.m11 = Math.fma(_r3, _t4, -(_r7 * _t2)) * _t19_inv;
        _dst.m21 = Math.fma(_r7, _t5, -(_r0 * _t4)) * _t19_inv;
    }

    /** Private tail of {@code invertProduct_translation}; reached only through it. */
    private void invertProduct_translation_s36a279f2_tail(Float3x3Impl _dst, float _t19, float _t12, float _r0, float _t2, float _r3, float _t5, float _t1, float _t0, float _r7, float _t3, float _t4, float _t13) {
        float _t19_inv = 1.0f / _t19;
        _dst.m00 = _t12 * _t19_inv;
        _dst.m10 = Math.fma(_r7, _t1, -(_r3 * _t3)) * _t19_inv;
        _dst.m20 = _t13 * _t19_inv;
        invertProduct_translation_s36a279f2_c1(_dst, _r0, _t2, _r3, _t5, _t19_inv, _t4, _r7);
        invert_general_s715c4c6e_c2(_dst, _t5, _t1, _t2, _t0, _t19_inv, _t3, _t4);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m21();
        float _r1 = this.m12;
        float _r2 = other.m11();
        float _r3 = other.m22();
        float _r4 = other.m12();
        float _r5 = this.m02;
        float _r6 = other.m02();
        float _r7 = other.m20();
        float _r8 = other.m10();
        float _r9 = other.m00();
        float _r10 = other.m01();
        float _t0 = Math.fma(_r0, _r1, _r2);
        float _t1 = Math.fma(_r3, _r1, _r4);
        float _t2 = Math.fma(_r3, _r5, _r6);
        float _t3 = Math.fma(_r7, _r1, _r8);
        float _t4 = Math.fma(_r7, _r5, _r9);
        float _t5 = Math.fma(_r0, _r5, _r10);
        float _t12 = Math.fma(_r3, _t0, -(_r0 * _t1));
        float _t13 = Math.fma(_r0, _t3, -(_r7 * _t0));
        float _t19 = Math.fma(_t2, _t13, Math.fma(_t4, _t12, -(_t5 * Math.fma(_r3, _t3, -(_r7 * _t1)))));
        if (!(Math.abs(_t19) > 1.1754944E-38f && Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        invertProduct_translation_s36a279f2_tail(d, _t19, _t12, _r0, _t2, _r3, _t5, _t1, _t0, _r7, _t3, _t4, _t13);
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }

    /** Private tail of {@code invertProduct_orthogonal}; reached only through it. */
    private void invertProduct_orthogonal_s6782e528_tail(Float3x3Impl _dst, float _t31, float _t24, float _r0, float _t14, float _r6, float _t17, float _t13, float _t12, float _r12, float _t15, float _t16, float _t25) {
        float _t31_inv = 1.0f / _t31;
        _dst.m00 = _t24 * _t31_inv;
        _dst.m10 = Math.fma(_r12, _t13, -(_r6 * _t15)) * _t31_inv;
        _dst.m20 = _t25 * _t31_inv;
        invertProduct_translation_s36a279f2_c1(_dst, _r0, _t14, _r6, _t17, _t31_inv, _t16, _r12);
        invertProduct_general_s36a279f2_c2(_dst, _t17, _t13, _t12, _t14, _t31_inv, _t15, _t16);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float _r0 = other.m21();
        float _r1 = this.m12;
        float _r2 = other.m01();
        float _r3 = this.m10;
        float _r4 = other.m11();
        float _r5 = this.m11;
        float _r6 = other.m22();
        float _r7 = other.m02();
        float _r8 = other.m12();
        float _r9 = this.m02;
        float _r10 = this.m00;
        float _r11 = this.m01;
        float _r12 = other.m20();
        float _r13 = other.m00();
        float _r14 = other.m10();
        float _t12 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        float _t13 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        float _t15 = Math.fma(_r12, _r1, Math.fma(_r13, _r3, _r14 * _r5));
        return invertProduct_orthogonal_s7cd20356_1(other, dest, _props, (Float3x3Impl) dest, _r0, _r6, _r12, _t12, _t13, Math.fma(_r6, _r9, Math.fma(_r7, _r10, _r8 * _r11)), _t15, Math.fma(_r12, _r9, Math.fma(_r13, _r10, _r14 * _r11)), Math.fma(_r0, _r9, Math.fma(_r2, _r10, _r4 * _r11)), Math.fma(_r6, _t12, -(_r0 * _t13)), Math.fma(_r0, _t15, -(_r12 * _t12)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_orthogonal_s7cd20356_1(Float3x3R other, Float3x3 dest, int _props, Float3x3Impl d, float _r0, float _r6, float _r12, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t24, float _t25) {
        float _t31 = Math.fma(_t14, _t25, Math.fma(_t16, _t24, -(_t17 * Math.fma(_r6, _t15, -(_r12 * _t13)))));
        if (!(Math.abs(_t31) > 1.1754944E-38f && Math.abs(_t31) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        invertProduct_orthogonal_s6782e528_tail(d, _t31, _t24, _r0, _t14, _r6, _t17, _t13, _t12, _r12, _t15, _t16, _t25);
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float3x3 invertProduct_identity_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -other.m02();
        d.m12 = -other.m12();
        d.m22 = 1.0f;
        d.properties = ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = other.m11() * _t3_inv;
        float _buf1 = -(other.m10() * _t3_inv);
        d.m20 = 0.0f;
        float _buf2 = -(other.m01() * _t3_inv);
        float _buf3 = other.m00() * _t3_inv;
        d.m21 = 0.0f;
        float _buf4 = Math.fma(other.m01(), other.m12(), -(other.m02() * other.m11())) * _t3_inv;
        d.m12 = Math.fma(other.m02(), other.m10(), -(other.m00() * other.m12())) * _t3_inv;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -(other.m02() + this.m02);
        d.m12 = -(other.m12() + this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t1 = other.m12() + this.m12;
        float _t2 = other.m02() + this.m02;
        float _t5 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t5) > 1.1754944E-38f && Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = other.m11() * _t5_inv;
        float _buf1 = -(other.m10() * _t5_inv);
        d.m20 = 0.0f;
        float _buf2 = -(other.m01() * _t5_inv);
        float _buf3 = other.m00() * _t5_inv;
        d.m21 = 0.0f;
        d.m02 = Math.fma(other.m01(), _t1, -(other.m11() * _t2)) * _t5_inv;
        d.m12 = Math.fma(other.m10(), _t2, -(other.m00() * _t1)) * _t5_inv;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = -this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m10;
        float _buf1 = this.m11;
        d.m21 = 0.0f;
        float _buf2 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m12 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = -this.m10;
        d.m00 = this.m11;
        float _buf0 = _t0;
        d.m20 = 0.0f;
        d.m01 = this.m10;
        float _buf1 = this.m11;
        d.m21 = 0.0f;
        float _buf2 = Math.fma(_t0, this.m12, Math.fma(-this.m02, this.m11, -other.m02()));
        d.m12 = Math.fma(this.m02, this.m10, Math.fma(-this.m11, this.m12, -other.m12()));
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private column 2 of {@code invertProduct_orthogonal_affine}: computes and stores it. Shared
     * by 2 identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_orthogonal_affine_s6782e528_c2(Float3x3Impl _dst, float _t10, float _t9, float _t11, float _t6, float _t15_inv, float _t8, float _t7) {
        _dst.m02 = Math.fma(_t10, _t9, -(_t11 * _t6)) * _t15_inv;
        _dst.m12 = Math.fma(_t11, _t8, -(_t10 * _t7)) * _t15_inv;
        _dst.m22 = 1.0f;
    }

    /** Private tail of {@code invertProduct_orthogonal_affine}; reached only through it. */
    private void invertProduct_orthogonal_affine_s6782e528_tail(Float3x3Impl _dst, float _r8, float _r1, float _r9, float _r3, float _r10, float _r5, float _r7, float _r11, float _t15, float _t6, float _t9, float _t8, float _t7) {
        float _t15_inv = 1.0f / _t15;
        _dst.m00 = _t6 * _t15_inv;
        _dst.m10 = -(_t8 * _t15_inv);
        _dst.m20 = 0.0f;
        _dst.m01 = -(_t9 * _t15_inv);
        _dst.m11 = _t7 * _t15_inv;
        _dst.m21 = 0.0f;
        invertProduct_orthogonal_affine_s6782e528_c2(_dst, Math.fma(_r8, _r1, Math.fma(_r9, _r3, _r10)), _t9, Math.fma(_r8, _r5, Math.fma(_r9, _r7, _r11)), _t6, _t15_inv, _t8, _t7);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m01();
        float _r1 = this.m10;
        float _r2 = other.m11();
        float _r3 = this.m11;
        float _r4 = other.m00();
        float _r5 = this.m00;
        float _r6 = other.m10();
        float _r7 = this.m01;
        float _t6 = Math.fma(_r0, _r1, _r2 * _r3);
        float _t7 = Math.fma(_r4, _r5, _r6 * _r7);
        float _t8 = Math.fma(_r4, _r1, _r6 * _r3);
        float _t9 = Math.fma(_r0, _r5, _r2 * _r7);
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(Math.abs(_t15) > 1.1754944E-38f && Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _r8 = other.m02();
        float _r9 = other.m12();
        float _r10 = this.m12;
        float _r11 = this.m02;
        invertProduct_orthogonal_affine_s6782e528_tail(d, _r8, _r1, _r9, _r3, _r10, _r5, _r7, _r11, _t15, _t6, _t9, _t8, _t7);
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = this.m11 * _t3_inv;
        float _buf1 = -(this.m10 * _t3_inv);
        d.m20 = 0.0f;
        float _buf2 = -(this.m01 * _t3_inv);
        float _buf3 = this.m00 * _t3_inv;
        d.m21 = 0.0f;
        float _buf4 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv;
        d.m12 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t5 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t6 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t7 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t7) > 1.1754944E-38f && Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _buf0 = this.m11 * _t7_inv;
        float _buf1 = -(this.m10 * _t7_inv);
        d.m20 = 0.0f;
        float _buf2 = -(this.m01 * _t7_inv);
        float _buf3 = this.m00 * _t7_inv;
        d.m21 = 0.0f;
        d.m02 = Math.fma(this.m01, _t5, -(this.m11 * _t6)) * _t7_inv;
        d.m12 = Math.fma(this.m10, _t6, -(this.m00 * _t5)) * _t7_inv;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m12;
        float _r3 = this.m21;
        float _r4 = this.m10;
        float _r5 = this.m20;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t13_inv = 1.0f / _t13;
        d.m00 = _t6 * _t13_inv;
        d.m10 = Math.fma(_r2, _r5, -(_r4 * _r1)) * _t13_inv;
        d.m20 = _t7 * _t13_inv;
        invert_general_s715c4c6e_c1(d, _r6, _r3, _r8, _r1, _t13_inv, _r7, _r5);
        invert_general_s715c4c6e_c2(d, _r8, _r2, _r6, _r0, _t13_inv, _r4, _r7);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code invertProduct_general_translation}: computes and stores it. Shared
     * by 2 identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_translation_s36a279f2_c1(Float3x3Impl _dst, float _r1, float _t8, float _r9, float _t6, float _t19_inv, float _r8, float _r3) {
        _dst.m01 = Math.fma(_r1, _t8, -(_r9 * _t6)) * _t19_inv;
        _dst.m11 = Math.fma(_r8, _t6, -(_r3 * _t8)) * _t19_inv;
        _dst.m21 = Math.fma(_r9, _r3, -(_r8 * _r1)) * _t19_inv;
    }

    /**
     * Private column 2 of {@code invertProduct_general_translation}: computes and stores it. Shared
     * by 2 identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_translation_s36a279f2_c2(Float3x3Impl _dst, float _r9, float _t7, float _r2, float _t8, float _t19_inv, float _r0, float _r8) {
        _dst.m02 = Math.fma(_r9, _t7, -(_r2 * _t8)) * _t19_inv;
        _dst.m12 = Math.fma(_r0, _t8, -(_r8 * _t7)) * _t19_inv;
        _dst.m22 = Math.fma(_r8, _r2, -(_r9 * _r0)) * _t19_inv;
    }

    /** Private tail of {@code invertProduct_general_translation}; reached only through it. */
    private void invertProduct_general_translation_s36a279f2_tail(Float3x3Impl _dst, float _t19, float _t13, float _r1, float _t8, float _r9, float _t6, float _t7, float _r2, float _r3, float _r0, float _r8, float _t5) {
        float _t19_inv = 1.0f / _t19;
        _dst.m00 = _t13 * _t19_inv;
        _dst.m10 = Math.fma(_r3, _t7, -(_r0 * _t6)) * _t19_inv;
        _dst.m20 = _t5 * _t19_inv;
        invertProduct_general_translation_s36a279f2_c1(_dst, _r1, _t8, _r9, _t6, _t19_inv, _r8, _r3);
        invertProduct_general_translation_s36a279f2_c2(_dst, _r9, _t7, _r2, _t8, _t19_inv, _r0, _r8);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m21;
        float _r2 = this.m11;
        float _r3 = this.m20;
        float _r4 = other.m02();
        float _r5 = other.m12();
        float _r6 = this.m22;
        float _r7 = this.m12;
        float _r8 = this.m00;
        float _r9 = this.m01;
        float _r10 = this.m02;
        float _t5 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t6 = Math.fma(_r4, _r3, Math.fma(_r5, _r1, _r6));
        float _t7 = Math.fma(_r4, _r0, Math.fma(_r5, _r2, _r7));
        float _t8 = Math.fma(_r4, _r8, Math.fma(_r5, _r9, _r10));
        float _t13 = Math.fma(_r2, _t6, -(_r1 * _t7));
        float _t19 = Math.fma(_t8, _t5, Math.fma(_r8, _t13, -(_r9 * Math.fma(_r0, _t6, -(_r3 * _t7)))));
        if (!(Math.abs(_t19) > 1.1754944E-38f && Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        invertProduct_general_translation_s36a279f2_tail(d, _t19, _t13, _r1, _t8, _r9, _t6, _t7, _r2, _r3, _r0, _r8, _t5);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code invertProduct_general_affine}: computes and stores it. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_affine_s36a279f2_c1(Float3x3Impl _dst, float _t17, float _t10, float _t15, float _t14, float _t31_inv, float _t13, float _t12) {
        _dst.m01 = Math.fma(_t17, _t10, -(_t15 * _t14)) * _t31_inv;
        _dst.m11 = Math.fma(_t15, _t13, -(_t17 * _t12)) * _t31_inv;
        _dst.m21 = Math.fma(_t12, _t14, -(_t13 * _t10)) * _t31_inv;
    }

    /**
     * Private column 2 of {@code invertProduct_general_affine}: computes and stores it. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_affine_s36a279f2_c2(Float3x3Impl _dst, float _t16, float _t14, float _t17, float _t9, float _t31_inv, float _t11, float _t13) {
        _dst.m02 = Math.fma(_t16, _t14, -(_t17 * _t9)) * _t31_inv;
        _dst.m12 = Math.fma(_t17, _t11, -(_t16 * _t13)) * _t31_inv;
        _dst.m22 = Math.fma(_t13, _t9, -(_t11 * _t14)) * _t31_inv;
    }

    /** Private tail of {@code invertProduct_general_affine}; reached only through it. */
    private void invertProduct_general_affine_s36a279f2_tail(Float3x3Impl _dst, float _t31, float _t25, float _t17, float _t10, float _t15, float _t14, float _t16, float _t9, float _t12, float _t11, float _t13, float _t24) {
        float _t31_inv = 1.0f / _t31;
        _dst.m00 = _t25 * _t31_inv;
        _dst.m10 = Math.fma(_t16, _t12, -(_t15 * _t11)) * _t31_inv;
        _dst.m20 = _t24 * _t31_inv;
        invertProduct_general_affine_s36a279f2_c1(_dst, _t17, _t10, _t15, _t14, _t31_inv, _t13, _t12);
        invertProduct_general_affine_s36a279f2_c2(_dst, _t16, _t14, _t17, _t9, _t31_inv, _t11, _t13);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        float _r0 = other.m01();
        float _r1 = this.m10;
        float _r2 = other.m11();
        float _r3 = this.m11;
        float _r4 = this.m20;
        float _r5 = this.m21;
        float _r6 = other.m00();
        float _r7 = other.m10();
        float _r8 = this.m00;
        float _r9 = this.m01;
        float _r10 = other.m02();
        float _r11 = other.m12();
        float _r12 = this.m22;
        float _r13 = this.m12;
        float _r14 = this.m02;
        float _t9 = Math.fma(_r0, _r1, _r2 * _r3);
        float _t10 = Math.fma(_r0, _r4, _r2 * _r5);
        float _t11 = Math.fma(_r6, _r1, _r7 * _r3);
        float _t12 = Math.fma(_r6, _r4, _r7 * _r5);
        float _t15 = Math.fma(_r10, _r4, Math.fma(_r11, _r5, _r12));
        float _t16 = Math.fma(_r10, _r1, Math.fma(_r11, _r3, _r13));
        return invertProduct_general_affine_s34a8b39_1(other, dest, (Float3x3Impl) dest, _t9, _t10, _t11, _t12, Math.fma(_r6, _r8, _r7 * _r9), Math.fma(_r0, _r8, _r2 * _r9), _t15, _t16, Math.fma(_r10, _r8, Math.fma(_r11, _r9, _r14)), Math.fma(_t11, _t10, -(_t12 * _t9)), Math.fma(_t15, _t9, -(_t16 * _t10)));
    }

    /** Piece 2 of {@code invertProduct_general_affine}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_general_affine_s34a8b39_1(Float3x3R other, Float3x3 dest, Float3x3Impl d, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t24, float _t25) {
        float _t31 = Math.fma(_t17, _t24, Math.fma(_t13, _t25, -(_t14 * Math.fma(_t15, _t11, -(_t16 * _t12)))));
        if (!(Math.abs(_t31) > 1.1754944E-38f && Math.abs(_t31) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        invertProduct_general_affine_s36a279f2_tail(d, _t31, _t25, _t17, _t10, _t15, _t14, _t16, _t9, _t12, _t11, _t13, _t24);
        d.properties = 0;
        return d;
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
        float _r0 = this.m12;
        float _r1 = this.m10;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m20;
        float _r5 = this.m21;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t18 = Math.fma(m21, _r0, Math.fma(m01, _r1, m11 * _r2));
        float _t19 = Math.fma(m22, _r3, Math.fma(m02, _r4, m12 * _r5));
        float _t20 = Math.fma(m21, _r3, Math.fma(m01, _r4, m11 * _r5));
        float _t21 = Math.fma(m22, _r0, Math.fma(m02, _r1, m12 * _r2));
        float _t23 = Math.fma(m20, _r0, Math.fma(m00, _r1, m10 * _r2));
        float _t24 = Math.fma(m20, _r3, Math.fma(m00, _r4, m10 * _r5));
        return invertProduct_s60657eeb_1(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest, (Float3x3Impl) dest, _t18, _t19, _t20, _t21, Math.fma(m22, _r6, Math.fma(m02, _r7, m12 * _r8)), _t23, _t24, Math.fma(m20, _r6, Math.fma(m00, _r7, m10 * _r8)), Math.fma(m21, _r6, Math.fma(m01, _r7, m11 * _r8)), Math.fma(_t18, _t19, -(_t20 * _t21)), Math.fma(_t23, _t20, -(_t24 * _t18)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_s60657eeb_1(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, Float3x3 dest, Float3x3Impl d, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t33, float _t34) {
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest);
        invertProduct_general_s36a279f2_tail(d, _t40, _t33, _t20, _t22, _t26, _t19, _t21, _t18, _t24, _t23, _t25, _t34);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code invertProduct}: computes and stores it. Shared by the identical
     * private paths of {@code invertProduct} and {@code normal}; reached only through them.
     */
    private void invertProduct_s3f6830ca_c1(Double3x3Impl _dst, float _t20, float _t22, float _t26, float _t19, float _t40_inv, float _t25, float _t24) {
        _dst.m01 = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        _dst.m11 = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        _dst.m21 = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
    }

    /**
     * Private column 2 of {@code invertProduct}: computes and stores it. Shared by 2 identical
     * private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_s3f6830ca_c2(Double3x3Impl _dst, float _t26, float _t21, float _t18, float _t22, float _t40_inv, float _t23, float _t25) {
        _dst.m02 = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        _dst.m12 = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        _dst.m22 = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s3f6830ca_tail(Double3x3Impl _dst, float _t40, float _t33, float _t20, float _t22, float _t26, float _t19, float _t21, float _t18, float _t24, float _t23, float _t25, float _t34) {
        float _t40_inv = 1.0f / _t40;
        _dst.m00 = _t33 * _t40_inv;
        _dst.m10 = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        _dst.m20 = _t34 * _t40_inv;
        invertProduct_s3f6830ca_c1(_dst, _t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        invertProduct_s3f6830ca_c2(_dst, _t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
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
        float _r0 = this.m12;
        float _r1 = this.m10;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m20;
        float _r5 = this.m21;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t18 = Math.fma(m21, _r0, Math.fma(m01, _r1, m11 * _r2));
        float _t19 = Math.fma(m22, _r3, Math.fma(m02, _r4, m12 * _r5));
        float _t20 = Math.fma(m21, _r3, Math.fma(m01, _r4, m11 * _r5));
        float _t21 = Math.fma(m22, _r0, Math.fma(m02, _r1, m12 * _r2));
        float _t23 = Math.fma(m20, _r0, Math.fma(m00, _r1, m10 * _r2));
        float _t24 = Math.fma(m20, _r3, Math.fma(m00, _r4, m10 * _r5));
        return invertProduct_sa09afb2e_1(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest, (Double3x3Impl) dest, _t18, _t19, _t20, _t21, Math.fma(m22, _r6, Math.fma(m02, _r7, m12 * _r8)), _t23, _t24, Math.fma(m20, _r6, Math.fma(m00, _r7, m10 * _r8)), Math.fma(m21, _r6, Math.fma(m01, _r7, m11 * _r8)), Math.fma(_t18, _t19, -(_t20 * _t21)), Math.fma(_t23, _t20, -(_t24 * _t18)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double3x3 invertProduct_sa09afb2e_1(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, Double3x3 dest, Double3x3Impl d, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t33, float _t34) {
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, dest);
        invertProduct_s3f6830ca_tail(d, _t40, _t33, _t20, _t22, _t26, _t19, _t21, _t18, _t24, _t23, _t25, _t34);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private void invertProduct_degenerate_general_s36a279f2_tail(Float3x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r0, float _r2, float _r4, float _r15, float _r16, float _r17, float _t18) {
        float _t19 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        float _t20 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5));
        float _t21 = Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14));
        float _t22 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14));
        float _t24 = Math.fma(_r6, _r15, Math.fma(_r7, _r16, _r8 * _r17));
        float _t25 = Math.fma(_r0, _r15, Math.fma(_r2, _r16, _r4 * _r17));
        float _t26 = Math.fma(_r9, _r15, Math.fma(_r10, _r16, _r11 * _r17));
        float _t27 = unitScale(_t19, _t18, _t20);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t24, _t25, _t26);
        invertProduct_degenerate_general_s36a279f2_tail2(_dst, _t24, _t29, _t25, _t18 * _t27, _t21 * _t28, _t23 * _t28, _t20 * _t27, _t19 * _t27, _t22 * _t28, _t26 * _t29, _t28, _t27);
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private void invertProduct_degenerate_general_s36a279f2_tail2(Float3x3Impl _dst, float _t24, float _t29, float _t25, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t28, float _t27) {
        float _t46 = _t24 * _t29;
        float _t47 = _t25 * _t29;
        float _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        float _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        float _t60_inv = 1.0f / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        {
            float _t13_inv = _t29 * _t60_inv;
            _dst.m00 = _t54 * _t13_inv;
            _dst.m10 = Math.fma(_t44, _t42, -(_t43 * _t40)) * _t13_inv;
            _dst.m20 = _t55 * _t13_inv;
        }
        invertProduct_general_s36a279f2_c1(_dst, _t41, _t45, _t47, _t40, _t27 * _t60_inv, _t46, _t44);
        invertProduct_general_s36a279f2_c2(_dst, _t47, _t42, _t39, _t45, _t28 * _t60_inv, _t43, _t46);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m21();
        float _r1 = this.m12;
        float _r2 = other.m01();
        float _r3 = this.m10;
        float _r4 = other.m11();
        float _r5 = this.m11;
        float _r6 = other.m20();
        float _r7 = other.m00();
        float _r8 = other.m10();
        float _r9 = other.m22();
        float _r10 = other.m02();
        float _r11 = other.m12();
        float _r12 = this.m22;
        float _r13 = this.m20;
        float _r14 = this.m21;
        float _r15 = this.m02;
        float _r16 = this.m00;
        float _r17 = this.m01;
        invertProduct_degenerate_general_s36a279f2_tail(d, _r6, _r1, _r7, _r3, _r8, _r5, _r9, _r10, _r11, _r12, _r13, _r14, _r0, _r2, _r4, _r15, _r16, _r17, Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5)));
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m10();
        float _r1 = other.m11();
        float _r2 = other.m12();
        float _r3 = other.m20();
        float _r4 = other.m21();
        float _r5 = other.m22();
        float _r6 = other.m00();
        float _r7 = other.m01();
        float _r8 = other.m02();
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        invert_degenerate_general_s715c4c6e_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_translation}; reached only through it. */
    private void invertProduct_degenerate_translation_s36a279f2_tail(Float3x3Impl _dst, float _t2, float _t13, float _t6, float _t14, float _t4, float _t5, float _t10, float _t21, float _t11, float _t22, float _t12, float _t0) {
        float _t23 = _t2 * _t13;
        float _t24 = _t6 * _t14;
        float _t25 = _t4 * _t14;
        float _t26 = _t5 * _t14;
        float _t33 = Math.fma(_t10, _t21, -(_t11 * _t22));
        float _t34 = Math.fma(_t11, _t23, -(_t12 * _t21));
        float _t39_inv = 1.0f / Math.fma(_t34, _t24, Math.fma(_t33, _t25, -(Math.fma(_t10, _t23, -(_t12 * _t22)) * _t26)));
        {
            float _t13_inv = _t14 * _t39_inv;
            _dst.m00 = _t33 * _t13_inv;
            _dst.m10 = Math.fma(_t12, _t22, -(_t10 * _t23)) * _t13_inv;
            _dst.m20 = _t34 * _t13_inv;
        }
        invertProduct_translation_s36a279f2_c1(_dst, _t11, _t24, _t10, _t26, _t13 * _t39_inv, _t25, _t12);
        invert_general_s715c4c6e_c2(_dst, _t26, _t22, _t24, _t21, _t0 * _t39_inv, _t23, _t25);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m20();
        float _r1 = other.m21();
        float _r2 = other.m22();
        float _r3 = this.m12;
        float _r4 = other.m11();
        float _r5 = other.m10();
        float _r6 = other.m12();
        float _r7 = this.m02;
        float _r8 = other.m00();
        float _r9 = other.m01();
        float _r10 = other.m02();
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = Math.fma(_r1, _r3, _r4);
        float _t2 = Math.fma(_r0, _r3, _r5);
        float _t3 = Math.fma(_r2, _r3, _r6);
        float _t4 = Math.fma(_r0, _r7, _r8);
        float _t5 = Math.fma(_r1, _r7, _r9);
        float _t6 = Math.fma(_r2, _r7, _r10);
        float _t13 = unitScale(_t2, _t1, _t3);
        invertProduct_degenerate_translation_s36a279f2_tail(d, _t2, _t13, _t6, unitScale(_t4, _t5, _t6), _t4, _t5, _r2 * _t0, _t1 * _t13, _r1 * _t0, _t3 * _t13, _r0 * _t0, _t0);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal}; reached only through it. */
    private void invertProduct_degenerate_orthogonal_s6782e528_tail(Float3x3Impl _dst, float _r0, float _r3, float _r8, float _r5, float _r9, float _r7, float _r2, float _r10, float _r11, float _r12, float _r13, float _r14, float _r1, float _r4, float _r6, float _t19, float _t16, float _t17, float _t18, float _t6) {
        float _t20 = Math.fma(_r0, _r3, Math.fma(_r8, _r5, _r9 * _r7));
        float _t21 = Math.fma(_r2, _r3, Math.fma(_r10, _r5, _r11 * _r7));
        float _t22 = Math.fma(_r0, _r12, Math.fma(_r8, _r13, _r9 * _r14));
        float _t23 = Math.fma(_r1, _r12, Math.fma(_r4, _r13, _r6 * _r14));
        float _t24 = Math.fma(_r2, _r12, Math.fma(_r10, _r13, _r11 * _r14));
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
        invertProduct_degenerate_orthogonal_s6782e528_tail2(_dst, _t6, 1.0f / Math.fma(_t46, _t36, Math.fma(_t45, _t37, -(Math.fma(_t16, _t35, -(_t18 * _t34)) * _t38))), _t25, _t26, _t45, _t17, _t36, _t16, _t38, _t34, _t33, _t18, _t35, _t37, _t46);
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal}; reached only through it. */
    private void invertProduct_degenerate_orthogonal_s6782e528_tail2(Float3x3Impl _dst, float _t6, float _t51_inv, float _t25, float _t26, float _t45, float _t17, float _t36, float _t16, float _t38, float _t34, float _t33, float _t18, float _t35, float _t37, float _t46) {
        {
            float _t13_inv = _t26 * _t51_inv;
            _dst.m00 = _t45 * _t13_inv;
            _dst.m10 = Math.fma(_t18, _t34, -(_t16 * _t35)) * _t13_inv;
            _dst.m20 = _t46 * _t13_inv;
        }
        invertProduct_translation_s36a279f2_c1(_dst, _t17, _t36, _t16, _t38, _t25 * _t51_inv, _t37, _t18);
        invertProduct_general_s36a279f2_c2(_dst, _t38, _t34, _t33, _t36, _t6 * _t51_inv, _t35, _t37);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m20();
        float _r1 = other.m21();
        float _r2 = other.m22();
        float _r3 = this.m12;
        float _r4 = other.m01();
        float _r5 = this.m10;
        float _r6 = other.m11();
        float _r7 = this.m11;
        float _r8 = other.m00();
        float _r9 = other.m10();
        float _r10 = other.m02();
        float _r11 = other.m12();
        float _r12 = this.m02;
        float _r13 = this.m00;
        float _r14 = this.m01;
        float _t6 = unitScale(_r0, _r1, _r2);
        invertProduct_degenerate_orthogonal_s6782e528_tail(d, _r0, _r3, _r8, _r5, _r9, _r7, _r2, _r10, _r11, _r12, _r13, _r14, _r1, _r4, _r6, Math.fma(_r1, _r3, Math.fma(_r4, _r5, _r6 * _r7)), _r2 * _t6, _r1 * _t6, _r0 * _t6, _t6);
        d.properties = _props;
        return d;
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, other.m02());
        float _t1 = unitScale(0.0f, 1.0f, other.m12());
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1 * _t3_inv;
        d.m21 = 0.0f;
        d.m02 = -(other.m02() * _t0 * _t2_inv);
        d.m12 = -(other.m12() * _t1 * _t3_inv);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m20 = 0.0f;
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m21 = 0.0f;
        d.m02 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m12 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1 * _t3_inv;
        d.m21 = 0.0f;
        d.m02 = -(this.m02 * _t0 * _t2_inv);
        d.m12 = -(this.m12 * _t1 * _t3_inv);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = other.m02() + this.m02;
        float _t1 = other.m12() + this.m12;
        float _t2 = unitScale(1.0f, 0.0f, _t0);
        float _t3 = unitScale(0.0f, 1.0f, _t1);
        float _t4_inv = 1.0f / _t2;
        float _t5_inv = 1.0f / _t3;
        d.m00 = _t2 * _t4_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t3 * _t5_inv;
        d.m21 = 0.0f;
        d.m02 = -(_t0 * _t2 * _t4_inv);
        d.m12 = -(_t1 * _t3 * _t5_inv);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m20 = 0.0f;
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m21 = 0.0f;
        d.m02 = Math.fma(_t10, _t14, -(_t8 * _t15)) * _t18_inv;
        d.m12 = Math.fma(_t11, _t15, -(_t9 * _t14)) * _t18_inv;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_identity(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m20 = 0.0f;
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m21 = 0.0f;
        d.m02 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m12 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t10 * _sp0;
        d.m10 = -(_t13 * _sp0);
        d.m20 = 0.0f;
        d.m01 = -(_t12 * _sp1);
        d.m11 = _t11 * _sp1;
        d.m21 = 0.0f;
        d.m02 = Math.fma(_t12, _t16, -(_t10 * _t17)) * _t20_inv;
        d.m12 = Math.fma(_t13, _t17, -(_t11 * _t16)) * _t20_inv;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal_affine}; reached only through it. */
    private void invertProduct_degenerate_orthogonal_affine_s6782e528_tail(Float3x3Impl _dst, float _t7, float _t12, float _t9, float _t13, float _t10, float _t11, float _t19, float _t18) {
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        {
            float _t15_inv = _t13 * _t28_inv;
            _dst.m00 = _t18 * _t15_inv;
            _dst.m10 = -(_t20 * _t15_inv);
            _dst.m20 = 0.0f;
        }
        {
            float _t15_inv = _t12 * _t28_inv;
            _dst.m01 = -(_t21 * _t15_inv);
            _dst.m11 = _t19 * _t15_inv;
            _dst.m21 = 0.0f;
        }
        invertProduct_orthogonal_affine_s6782e528_c2(_dst, _t10 * _t12, _t21, _t11 * _t13, _t18, _t28_inv, _t20, _t19);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m01();
        float _r1 = this.m10;
        float _r2 = other.m11();
        float _r3 = this.m11;
        float _r4 = other.m00();
        float _r5 = other.m10();
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = other.m02();
        float _r9 = other.m12();
        float _r10 = this.m12;
        float _r11 = this.m02;
        float _t6 = Math.fma(_r0, _r1, _r2 * _r3);
        float _t7 = Math.fma(_r4, _r1, _r5 * _r3);
        float _t8 = Math.fma(_r4, _r6, _r5 * _r7);
        float _t9 = Math.fma(_r0, _r6, _r2 * _r7);
        float _t10 = Math.fma(_r8, _r1, Math.fma(_r9, _r3, _r10));
        float _t11 = Math.fma(_r8, _r6, Math.fma(_r9, _r7, _r11));
        float _t12 = unitScale(_t7, _t6, _t10);
        float _t13 = unitScale(_t8, _t9, _t11);
        invertProduct_degenerate_orthogonal_affine_s6782e528_tail(d, _t7, _t12, _t9, _t13, _t10, _t11, _t8 * _t13, _t6 * _t12);
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_identity(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m12;
        float _r3 = this.m20;
        float _r4 = this.m21;
        float _r5 = this.m22;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m02;
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        invert_degenerate_general_s715c4c6e_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_general_translation}; reached only through it. */
    private void invertProduct_degenerate_general_translation_s36a279f2_tail(Float3x3Impl _dst, float _t4, float _t7, float _t3, float _t6, float _t5, float _t8, float _t17, float _t16, float _t15, float _t18, float _t19, float _t20) {
        float _t24 = _t4 * _t7;
        float _t25 = _t3 * _t6;
        float _t26 = _t5 * _t8;
        float _t33 = Math.fma(_t17, _t16, -(_t15 * _t18));
        float _t34 = Math.fma(_t15, _t24, -(_t16 * _t25));
        float _t39_inv = 1.0f / Math.fma(_t33, _t26, Math.fma(_t34, _t19, -(Math.fma(_t17, _t24, -(_t18 * _t25)) * _t20)));
        {
            float _t13_inv = _t8 * _t39_inv;
            _dst.m00 = _t34 * _t13_inv;
            _dst.m10 = Math.fma(_t18, _t25, -(_t17 * _t24)) * _t13_inv;
            _dst.m20 = _t33 * _t13_inv;
        }
        invertProduct_general_translation_s36a279f2_c1(_dst, _t16, _t26, _t20, _t24, _t6 * _t39_inv, _t19, _t18);
        invertProduct_general_translation_s36a279f2_c2(_dst, _t20, _t25, _t15, _t26, _t7 * _t39_inv, _t17, _t19);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m02();
        float _r1 = this.m10;
        float _r2 = other.m12();
        float _r3 = this.m11;
        float _r4 = this.m12;
        float _r5 = this.m20;
        float _r6 = this.m21;
        float _r7 = this.m22;
        float _r8 = this.m00;
        float _r9 = this.m01;
        float _r10 = this.m02;
        float _t3 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4));
        float _t4 = Math.fma(_r0, _r5, Math.fma(_r2, _r6, _r7));
        float _t5 = Math.fma(_r0, _r8, Math.fma(_r2, _r9, _r10));
        float _t6 = unitScale(_r1, _r3, _t3);
        float _t7 = unitScale(_r5, _r6, _t4);
        float _t8 = unitScale(_r8, _r9, _t5);
        invertProduct_degenerate_general_translation_s36a279f2_tail(d, _t4, _t7, _t3, _t6, _t5, _t8, _r1 * _t6, _r6 * _t7, _r3 * _t6, _r5 * _t7, _r8 * _t8, _r9 * _t8);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_general_affine}; reached only through it. */
    private void invertProduct_degenerate_general_affine_s36a279f2_tail(Float3x3Impl _dst, float _r4, float _r8, float _r5, float _r9, float _r10, float _r1, float _r11, float _r3, float _r12, float _r6, float _r7, float _r13, float _r14, float _t9, float _t10, float _t12, float _t11, float _t13) {
        float _t14 = Math.fma(_r4, _r8, _r5 * _r9);
        float _t15 = Math.fma(_r10, _r1, Math.fma(_r11, _r3, _r12));
        float _t16 = Math.fma(_r10, _r6, Math.fma(_r11, _r7, _r13));
        float _t17 = Math.fma(_r10, _r8, Math.fma(_r11, _r9, _r14));
        float _t18 = unitScale(_t9, _t10, _t15);
        float _t19 = unitScale(_t12, _t11, _t16);
        float _t20 = unitScale(_t13, _t14, _t17);
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
        invertProduct_degenerate_general_affine_s36a279f2_tail2(_dst, _t19, _t51_inv, _t20, _t46, _t38, _t28, _t36, _t32, _t37, _t27, _t18 * _t51_inv, _t30, _t29, _t31, _t45);
    }

    /** Private tail of {@code invertProduct_degenerate_general_affine}; reached only through it. */
    private void invertProduct_degenerate_general_affine_s36a279f2_tail2(Float3x3Impl _dst, float _t19, float _t51_inv, float _t20, float _t46, float _t38, float _t28, float _t36, float _t32, float _t37, float _t27, float _sp2, float _t30, float _t29, float _t31, float _t45) {
        {
            float _t13_inv = _t20 * _t51_inv;
            _dst.m00 = _t46 * _t13_inv;
            _dst.m10 = Math.fma(_t37, _t30, -(_t36 * _t29)) * _t13_inv;
            _dst.m20 = _t45 * _t13_inv;
        }
        invertProduct_general_affine_s36a279f2_c1(_dst, _t38, _t28, _t36, _t32, _t19 * _t51_inv, _t31, _t30);
        invertProduct_general_affine_s36a279f2_c2(_dst, _t37, _t32, _t38, _t27, _sp2, _t29, _t31);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m00();
        float _r1 = this.m20;
        float _r2 = other.m10();
        float _r3 = this.m21;
        float _r4 = other.m01();
        float _r5 = other.m11();
        float _r6 = this.m10;
        float _r7 = this.m11;
        float _r8 = this.m00;
        float _r9 = this.m01;
        float _r10 = other.m02();
        float _r11 = other.m12();
        float _r12 = this.m22;
        float _r13 = this.m12;
        float _r14 = this.m02;
        invertProduct_degenerate_general_affine_s36a279f2_tail(d, _r4, _r8, _r5, _r9, _r10, _r1, _r11, _r3, _r12, _r6, _r7, _r13, _r14, Math.fma(_r0, _r1, _r2 * _r3), Math.fma(_r4, _r1, _r5 * _r3), Math.fma(_r0, _r6, _r2 * _r7), Math.fma(_r4, _r6, _r5 * _r7), Math.fma(_r0, _r8, _r2 * _r9));
        d.properties = 0;
        return d;
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
    @Mutated private Float3x3 invertProduct_degenerate(Float3x3R other) {
        if (Joml.RETURN_NEW) return invertProduct_degenerate(other, Joml.float3x3());
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_identity_affine(other, this);
            return invertProduct_degenerate_identity(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_translation_affine(other, this);
            return invertProduct_degenerate_translation(other, this);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, this, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, this, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, this, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            return invertProduct_degenerate_orthogonal(other, this, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
        }
        return invertProduct_degenerate_s5560acbf_1(other, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3x3 invertProduct_degenerate_s5560acbf_1(Float3x3R other, int q) {
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation(other, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_general_affine(other, this);
        return invertProduct_degenerate_general(other, this);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(Float3x3R other, @Mutated Double3x3 dest) {
        return invertProduct_degenerate(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), dest);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s611c626b_tail(Float3x3Impl _dst, float m21, float _r6, float m01, float _r7, float m11, float _r8, float m22, float m02, float m12, float _t19, float _t18, float _t20, float _t22, float _t23, float _t21, float _t24) {
        float _t25 = Math.fma(m21, _r6, Math.fma(m01, _r7, m11 * _r8));
        float _t26 = Math.fma(m22, _r6, Math.fma(m02, _r7, m12 * _r8));
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
        invertProduct_degenerate_s611c626b_tail2(_dst, _t29, _t60_inv, _t54, _t41, _t45, _t47, _t40, _t27 * _t60_inv, _t42, _t39, _t28 * _t60_inv, _t44, _t43, _t46, _t55);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s611c626b_tail2(Float3x3Impl _dst, float _t29, float _t60_inv, float _t54, float _t41, float _t45, float _t47, float _t40, float _sp1, float _t42, float _t39, float _sp2, float _t44, float _t43, float _t46, float _t55) {
        {
            float _t13_inv = _t29 * _t60_inv;
            _dst.m00 = _t54 * _t13_inv;
            _dst.m10 = Math.fma(_t44, _t42, -(_t43 * _t40)) * _t13_inv;
            _dst.m20 = _t55 * _t13_inv;
        }
        invertProduct_general_s36a279f2_c1(_dst, _t41, _t45, _t47, _t40, _sp1, _t46, _t44);
        invertProduct_general_s36a279f2_c2(_dst, _t47, _t42, _t39, _t45, _sp2, _t43, _t46);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m12;
        float _r1 = this.m10;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m20;
        float _r5 = this.m21;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        invertProduct_degenerate_s611c626b_tail(d, m21, _r6, m01, _r7, m11, _r8, m22, m02, m12, Math.fma(m20, _r0, Math.fma(m00, _r1, m10 * _r2)), Math.fma(m21, _r0, Math.fma(m01, _r1, m11 * _r2)), Math.fma(m22, _r0, Math.fma(m02, _r1, m12 * _r2)), Math.fma(m20, _r3, Math.fma(m00, _r4, m10 * _r5)), Math.fma(m21, _r3, Math.fma(m01, _r4, m11 * _r5)), Math.fma(m22, _r3, Math.fma(m02, _r4, m12 * _r5)), Math.fma(m20, _r6, Math.fma(m00, _r7, m10 * _r8)));
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    @Mutated private Float3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22, Joml.RETURN_NEW ? Joml.float3x3() : this);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s3f6830ca_tail(Double3x3Impl _dst, float m21, float _r6, float m01, float _r7, float m11, float _r8, float m22, float m02, float m12, float _t19, float _t18, float _t20, float _t22, float _t23, float _t21, float _t24) {
        float _t25 = Math.fma(m21, _r6, Math.fma(m01, _r7, m11 * _r8));
        float _t26 = Math.fma(m22, _r6, Math.fma(m02, _r7, m12 * _r8));
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
        invertProduct_degenerate_s3f6830ca_tail2(_dst, _t29, _t60_inv, _t54, _t41, _t45, _t47, _t40, _t27 * _t60_inv, _t42, _t39, _t28 * _t60_inv, _t44, _t43, _t46, _t55);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s3f6830ca_tail2(Double3x3Impl _dst, float _t29, float _t60_inv, float _t54, float _t41, float _t45, float _t47, float _t40, float _sp1, float _t42, float _t39, float _sp2, float _t44, float _t43, float _t46, float _t55) {
        {
            float _t13_inv = _t29 * _t60_inv;
            _dst.m00 = _t54 * _t13_inv;
            _dst.m10 = Math.fma(_t44, _t42, -(_t43 * _t40)) * _t13_inv;
            _dst.m20 = _t55 * _t13_inv;
        }
        invertProduct_s3f6830ca_c1(_dst, _t41, _t45, _t47, _t40, _sp1, _t46, _t44);
        invertProduct_s3f6830ca_c2(_dst, _t47, _t42, _t39, _t45, _sp2, _t43, _t46);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m12;
        float _r1 = this.m10;
        float _r2 = this.m11;
        float _r3 = this.m22;
        float _r4 = this.m20;
        float _r5 = this.m21;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        invertProduct_degenerate_s3f6830ca_tail(d, m21, _r6, m01, _r7, m11, _r8, m22, m02, m12, Math.fma(m20, _r0, Math.fma(m00, _r1, m10 * _r2)), Math.fma(m21, _r0, Math.fma(m01, _r1, m11 * _r2)), Math.fma(m22, _r0, Math.fma(m02, _r1, m12 * _r2)), Math.fma(m20, _r3, Math.fma(m00, _r4, m10 * _r5)), Math.fma(m21, _r3, Math.fma(m01, _r4, m11 * _r5)), Math.fma(m22, _r3, Math.fma(m02, _r4, m12 * _r5)), Math.fma(m20, _r6, Math.fma(m00, _r7, m10 * _r8)));
        d.properties = 0;
        return d;
    }








    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return normal_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = this.m11 * _t3_inv;
        float _buf1 = -(this.m01 * _t3_inv);
        d.m20 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv;
        d.m01 = -(this.m10 * _t3_inv);
        d.m11 = this.m00 * _t3_inv;
        d.m21 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return normal_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = this.m11 * _t3_inv;
        float _buf1 = -(this.m01 * _t3_inv);
        d.m20 = Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv;
        d.m01 = -(this.m10 * _t3_inv);
        d.m11 = this.m00 * _t3_inv;
        d.m21 = Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }

    /** Private column 0 of {@code normal_general}: computes and stores it; reached only through it. */
    private void normal_general_s715c4c6e_c0(Float3x3Impl _dst, float _t6, float _t13_inv, float _r6, float _r3, float _r8, float _r1, float _r2, float _r0) {
        _dst.m00 = _t6 * _t13_inv;
        _dst.m10 = Math.fma(_r6, _r3, -(_r8 * _r1)) * _t13_inv;
        _dst.m20 = Math.fma(_r8, _r2, -(_r6 * _r0)) * _t13_inv;
    }

    /** Private column 2 of {@code normal_general}: computes and stores it; reached only through it. */
    private void normal_general_s715c4c6e_c2(Float3x3Impl _dst, float _t7, float _t13_inv, float _r8, float _r5, float _r7, float _r3, float _r0, float _r4) {
        _dst.m02 = _t7 * _t13_inv;
        _dst.m12 = Math.fma(_r8, _r5, -(_r7 * _r3)) * _t13_inv;
        _dst.m22 = Math.fma(_r7, _r0, -(_r8 * _r4)) * _t13_inv;
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m12;
        float _r3 = this.m21;
        float _r4 = this.m10;
        float _r5 = this.m20;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return normal_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        normal_general_s715c4c6e_c0(d, _t6, _t13_inv, _r6, _r3, _r8, _r1, _r2, _r0);
        invertProduct_general_s36a279f2_c1(d, _r2, _r5, _r4, _r1, _t13_inv, _r7, _r6);
        normal_general_s715c4c6e_c2(d, _t7, _t13_inv, _r8, _r5, _r7, _r3, _r0, _r4);
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return cofactor_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine_self(this);
        return normal_general(this);
    }

    /** Private column 0 of {@code normal}: computes and stores it; reached only through it. */
    private void normal_s37258727_c0(Double3x3Impl _dst, float _t6, float _t13_inv, float _r6, float _r3, float _r8, float _r1, float _r2, float _r0) {
        _dst.m00 = _t6 * _t13_inv;
        _dst.m10 = Math.fma(_r6, _r3, -(_r8 * _r1)) * _t13_inv;
        _dst.m20 = Math.fma(_r8, _r2, -(_r6 * _r0)) * _t13_inv;
    }

    /** Private column 2 of {@code normal}: computes and stores it; reached only through it. */
    private void normal_s37258727_c2(Double3x3Impl _dst, float _t7, float _t13_inv, float _r8, float _r5, float _r7, float _r3, float _r0, float _r4) {
        _dst.m02 = _t7 * _t13_inv;
        _dst.m12 = Math.fma(_r8, _r5, -(_r7 * _r3)) * _t13_inv;
        _dst.m22 = Math.fma(_r7, _r0, -(_r8 * _r4)) * _t13_inv;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m12;
        float _r3 = this.m21;
        float _r4 = this.m10;
        float _r5 = this.m20;
        float _r6 = this.m02;
        float _r7 = this.m00;
        float _r8 = this.m01;
        float _t6 = Math.fma(_r0, _r1, -(_r2 * _r3));
        float _t7 = Math.fma(_r4, _r3, -(_r0 * _r5));
        float _t13 = Math.fma(_r6, _t7, Math.fma(_r7, _t6, -(_r8 * Math.fma(_r4, _r1, -(_r2 * _r5)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return normal_degenerate(dest);
        float _t13_inv = 1.0f / _t13;
        normal_s37258727_c0(d, _t6, _t13_inv, _r6, _r3, _r8, _r1, _r2, _r0);
        invertProduct_s3f6830ca_c1(d, _r2, _r5, _r4, _r1, _t13_inv, _r7, _r6);
        normal_s37258727_c2(d, _t7, _t13_inv, _r8, _r5, _r7, _r3, _r0, _r4);
        d.properties = 0;
        return d;
    }




    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m20 = -(this.m02 * _t0 * _t2_inv);
        d.m01 = 0.0f;
        d.m11 = _t1 * _t3_inv;
        d.m21 = -(this.m12 * _t1 * _t3_inv);
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        d.m00 = _t0 * _t2_inv;
        d.m20 = -(this.m02 * _t0 * _t2_inv);
        d.m11 = _t1 * _t3_inv;
        d.m21 = -(this.m12 * _t1 * _t3_inv);
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t10 * _sp1);
        d.m20 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m01 = -(_t11 * _sp0);
        d.m11 = _t9 * _sp1;
        d.m21 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t10 * _sp1);
        d.m20 = Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv;
        d.m01 = -(_t11 * _sp0);
        d.m11 = _t9 * _sp1;
        d.m21 = Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = 0;
        return d;
    }

    /** Private column 0 of {@code normal_degenerate_general}: computes and stores it; reached only through it. */
    private void normal_degenerate_general_s715c4c6e_c0(Float3x3Impl _dst, float _t27, float _sp0, float _t18, float _t15, float _t20, float _t13, float _sp1, float _t14, float _t12, float _sp2) {
        _dst.m00 = _t27 * _sp0;
        _dst.m10 = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        _dst.m20 = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
    }

    /** Private column 1 of {@code normal_degenerate_general}: computes and stores it; reached only through it. */
    private void normal_degenerate_general_s715c4c6e_c1(Float3x3Impl _dst, float _t14, float _t17, float _t16, float _t13, float _sp0, float _t19, float _t18, float _sp1, float _sp2) {
        _dst.m01 = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        _dst.m11 = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        _dst.m21 = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
    }

    /** Private column 2 of {@code normal_degenerate_general}: computes and stores it; reached only through it. */
    private void normal_degenerate_general_s715c4c6e_c2(Float3x3Impl _dst, float _t28, float _sp0, float _t20, float _t17, float _t19, float _t15, float _sp1, float _t12, float _t16, float _sp2) {
        _dst.m02 = _t28 * _sp0;
        _dst.m12 = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        _dst.m22 = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
    }

    /** Private tail of {@code normal_degenerate_general}; reached only through it. */
    private void normal_degenerate_general_s715c4c6e_tail(Float3x3Impl _dst, float _t28, float _t18, float _t27, float _t19, float _t16, float _t13, float _t14, float _t17, float _t20, float _t1, float _t0, float _t2, float _t15, float _t12) {
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        normal_degenerate_general_s715c4c6e_c0(_dst, _t27, _sp0, _t18, _t15, _t20, _t13, _sp1, _t14, _t12, _sp2);
        normal_degenerate_general_s715c4c6e_c1(_dst, _t14, _t17, _t16, _t13, _sp0, _t19, _t18, _sp1, _sp2);
        normal_degenerate_general_s715c4c6e_c2(_dst, _t28, _sp0, _t20, _t17, _t19, _t15, _sp1, _t12, _t16, _sp2);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m12;
        float _r3 = this.m20;
        float _r4 = this.m21;
        float _r5 = this.m22;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m02;
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        normal_degenerate_general_s715c4c6e_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = 0;
        return d;
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
    @Mutated private Float3x3 normal_degenerate() {
        if (Joml.RETURN_NEW) return normal_degenerate(Joml.float3x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation_self(this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_orthogonal_self(this);
        return normal_degenerate_general(this);
    }

    /** Private column 0 of {@code normal_degenerate}: computes and stores it; reached only through it. */
    private void normal_degenerate_s37258727_c0(Double3x3Impl _dst, float _t27, float _sp0, float _t18, float _t15, float _t20, float _t13, float _sp1, float _t14, float _t12, float _sp2) {
        _dst.m00 = _t27 * _sp0;
        _dst.m10 = Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1;
        _dst.m20 = Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2;
    }

    /** Private column 1 of {@code normal_degenerate}: computes and stores it; reached only through it. */
    private void normal_degenerate_s37258727_c1(Double3x3Impl _dst, float _t14, float _t17, float _t16, float _t13, float _sp0, float _t19, float _t18, float _sp1, float _sp2) {
        _dst.m01 = Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0;
        _dst.m11 = Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1;
        _dst.m21 = Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2;
    }

    /** Private column 2 of {@code normal_degenerate}: computes and stores it; reached only through it. */
    private void normal_degenerate_s37258727_c2(Double3x3Impl _dst, float _t28, float _sp0, float _t20, float _t17, float _t19, float _t15, float _sp1, float _t12, float _t16, float _sp2) {
        _dst.m02 = _t28 * _sp0;
        _dst.m12 = Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1;
        _dst.m22 = Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2;
    }

    /** Private tail of {@code normal_degenerate}; reached only through it. */
    private void normal_degenerate_s37258727_tail(Double3x3Impl _dst, float _t28, float _t18, float _t27, float _t19, float _t16, float _t13, float _t14, float _t17, float _t20, float _t1, float _t0, float _t2, float _t15, float _t12) {
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        normal_degenerate_s37258727_c0(_dst, _t27, _sp0, _t18, _t15, _t20, _t13, _sp1, _t14, _t12, _sp2);
        normal_degenerate_s37258727_c1(_dst, _t14, _t17, _t16, _t13, _sp0, _t19, _t18, _sp1, _sp2);
        normal_degenerate_s37258727_c2(_dst, _t28, _sp0, _t20, _t17, _t19, _t15, _sp1, _t12, _t16, _sp2);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m12;
        float _r3 = this.m20;
        float _r4 = this.m21;
        float _r5 = this.m22;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m02;
        float _t0 = unitScale(_r0, _r1, _r2);
        float _t1 = unitScale(_r3, _r4, _r5);
        float _t2 = unitScale(_r6, _r7, _r8);
        float _t12 = _r1 * _t0;
        float _t13 = _r5 * _t1;
        float _t14 = _r2 * _t0;
        float _t15 = _r4 * _t1;
        float _t16 = _r0 * _t0;
        float _t17 = _r3 * _t1;
        normal_degenerate_s37258727_tail(d, Math.fma(_t16, _t15, -(_t12 * _t17)), _r8 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), _r6 * _t2, _t16, _t13, _t14, _t17, _r7 * _t2, _t1, _t0, _t2, _t15, _t12);
        d.properties = 0;
        return d;
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
    private Float3x3 transpose_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = this.m02;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m20 = this.m02;
        d.m21 = this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        d.m20 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        d.m20 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m10 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        float _buf1 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        float _buf2 = this.m12;
        d.m02 = this.m20;
        d.m12 = this.m21;
        d.m22 = this.m22;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        float _buf1 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        float _buf2 = this.m12;
        d.m02 = this.m20;
        d.m12 = this.m21;
        d.m22 = this.m22;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m20 = other.m20() + this.m20;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
        d.m21 = other.m21() + this.m21;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = other.m22() + this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.m22 = m22 + this.m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.m22 = m22 + this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = scalar;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = scalar;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = scalar;
        d.m21 = 0.0f;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_self(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar;
        d.m11 = scalar;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = 0.0f;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = 0.0f;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_self(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(float scalar, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar * this.m22;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = scalar;
            d.m11 = scalar;
            d.m22 = scalar;
            d.properties = 0;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar * this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m20 = this.m20 - other.m20();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
        d.m21 = this.m21 - other.m21();
        d.m02 = this.m02 - other.m02();
        d.m12 = this.m12 - other.m12();
        d.m22 = this.m22 - other.m22();
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.m22 = this.m22 - m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.m22 = this.m22 - m22;
        d.properties = 0;
        return d;
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
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m20 = v.m20();
        this.m01 = v.m01();
        this.m11 = v.m11();
        this.m21 = v.m21();
        this.m02 = v.m02();
        this.m12 = v.m12();
        this.m22 = v.m22();
        this.properties = ((Float3x3Impl) v).properties;
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
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m02 = m02;
        this.m12 = m12;
        this.m22 = m22;
        this.properties = determineProperties();
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m20 = 0.0f;
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = determineProperties();
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m20 = 0.0f;
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m21 = 0.0f;
        this.m02 = m.m02();
        this.m12 = m.m12();
        this.m22 = 1.0f;
        this.properties = determineProperties();
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m20 = m.m20();
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m21 = m.m21();
        this.m02 = m.m02();
        this.m12 = m.m12();
        this.m22 = m.m22();
        this.properties = determineProperties();
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m20 = m.m20();
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m21 = m.m21();
        this.m02 = m.m02();
        this.m12 = m.m12();
        this.m22 = m.m22();
        this.properties = determineProperties();
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = tX;
        d.m12 = tY;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code withTranslation}, specialized by runtime matrix
     * properties; reached only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_orthogonal_affine_self(float tX, float tY, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code withTranslation}, {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float3x3 withTranslation_identity(float tX, float tY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = tX;
        d.m12 = tY;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }



    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_general(float tX, float tY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = tX;
        d.m12 = tY;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = tX;
        d.m12 = tY;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = this.properties;
        return d;
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
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        this.m00 = Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f);
        this.m10 = 2.0f * Math.fma(rRX, rRY, _t1);
        this.m20 = 2.0f * Math.fma(rRX, rRZ, -_t2);
        this.m01 = 2.0f * Math.fma(rRX, rRY, -_t1);
        this.m11 = Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f);
        this.m21 = 2.0f * Math.fma(rRX, rRW, rRY * rRZ);
        this.m02 = 2.0f * Math.fma(rRX, rRZ, _t2);
        this.m12 = 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW));
        this.m22 = Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f);
        this.properties = 0;
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
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        this.m00 = Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX);
        this.m10 = Math.fma(tRX, tRY, _t4) * _t0;
        this.m20 = Math.fma(tRX, tRZ, -_t5) * _t0;
        this.m01 = Math.fma(tRX, tRY, -_t4) * _t1;
        this.m11 = Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY);
        this.m21 = Math.fma(tRX, tRW, tRY * tRZ) * _t1;
        this.m02 = Math.fma(tRX, tRZ, _t5) * _t2;
        this.m12 = Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2;
        this.m22 = Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ);
        this.properties = 0;
        return this;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_identity(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_general(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.properties = 0;
        return d;
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
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_orthogonal_general(@Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_identity(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_translation(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_identity(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        d.m00 = 1.0f;
        d.m01 = 0.0f;
        d.m02 = 0.0f;
        d.m03 = 0.0f;
        d.m10 = 0.0f;
        d.m11 = 1.0f;
        d.m12 = 0.0f;
        d.m13 = 0.0f;
        d.m20 = 0.0f;
        d.m21 = 0.0f;
        d.m22 = 1.0f;
        d.m23 = 0.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_translation(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        d.m00 = 1.0f;
        d.m01 = 0.0f;
        d.m02 = this.m02;
        d.m03 = 0.0f;
        d.m10 = 0.0f;
        float _buf0 = 1.0f;
        d.m12 = this.m12;
        d.m13 = 0.0f;
        d.m20 = 0.0f;
        d.m21 = 0.0f;
        d.m22 = 1.0f;
        d.m23 = 0.0f;
        d.m11 = _buf0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_orthogonal(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m01;
        d.m02 = this.m02;
        float _buf0 = 0.0f;
        float _buf1 = this.m10;
        float _buf2 = this.m11;
        d.m12 = this.m12;
        d.m13 = 0.0f;
        d.m20 = 0.0f;
        d.m21 = 0.0f;
        d.m22 = 1.0f;
        d.m23 = 0.0f;
        d.m03 = _buf0;
        d.m10 = _buf1;
        d.m11 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_general(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m01;
        d.m02 = this.m02;
        float _buf0 = 0.0f;
        float _buf1 = this.m10;
        float _buf2 = this.m11;
        float _buf3 = this.m12;
        float _buf4 = 0.0f;
        float _buf5 = this.m20;
        d.m21 = this.m21;
        d.m22 = this.m22;
        d.m23 = 0.0f;
        d.m03 = _buf0;
        d.m10 = _buf1;
        d.m11 = _buf2;
        d.m12 = _buf3;
        d.m13 = _buf4;
        d.m20 = _buf5;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        Double3x4Impl d = (Double3x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m01;
        d.m02 = this.m02;
        float _buf0 = 0.0f;
        float _buf1 = this.m10;
        float _buf2 = this.m11;
        float _buf3 = this.m12;
        float _buf4 = 0.0f;
        float _buf5 = this.m20;
        d.m21 = this.m21;
        d.m22 = this.m22;
        d.m23 = 0.0f;
        d.m03 = _buf0;
        d.m10 = _buf1;
        d.m11 = _buf2;
        d.m12 = _buf3;
        d.m13 = _buf4;
        d.m20 = _buf5;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_identity(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m30 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m31 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_translation(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m30 = 0.0f;
        d.m01 = 0.0f;
        float _buf0 = 1.0f;
        d.m21 = 0.0f;
        d.m31 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.m11 = _buf0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_orthogonal(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m10;
        d.m20 = 0.0f;
        d.m30 = 0.0f;
        d.m01 = this.m01;
        float _buf1 = this.m11;
        d.m21 = 0.0f;
        d.m31 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_general(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m10;
        float _buf1 = this.m20;
        d.m30 = 0.0f;
        d.m01 = this.m01;
        float _buf2 = this.m11;
        d.m21 = this.m21;
        d.m31 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m11 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        Double4x4Impl d = (Double4x4Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m10;
        float _buf1 = this.m20;
        d.m30 = 0.0f;
        d.m01 = this.m01;
        float _buf2 = this.m11;
        d.m21 = this.m21;
        d.m31 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m11 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_identity(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        d.rX = 0.0f;
        d.rY = 0.0f;
        d.rZ = 0.0f;
        d.rW = 1.0f;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
        return d;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_translation(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        d.rX = -(0.25f * this.m12);
        d.rY = 0.25f * this.m02;
        d.rZ = 0.0f;
        d.rW = 1.0f;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
        return d;
    }

    /** Private store group 0 of {@code toDualQuat_orthogonal}: computes and stores it; reached only through it. */
    private void toDualQuat_orthogonal_s416ccfb5_c0(FloatDualQuatImpl _dst, float _t9, float _sp0, float _t13, float _r3, float _t0, float _t3, float _r2, float _sp2, float _t7, float _sp1, float _t14, float _t8, float _t5, float _sp3, float _t12, float _t11) {
        _dst.rX = _t9 > 0.0f ? -(_sp0 * _t13) : _r3 > _t0 ? 0.5f * (float) Math.sqrt(_t3) : _r2 > 1.0f ? _sp2 * _t7 : _sp1 * _t14;
        _dst.rY = _t9 > 0.0f ? _sp1 * _t13 : _r3 > _t0 ? _sp2 * _t8 : _r2 > 1.0f ? 0.5f * (float) Math.sqrt(_t5) : _sp0 * _t14;
        _dst.rZ = _t9 > 0.0f ? _sp3 * _t13 : _r3 > _t0 ? _sp1 * _t8 : _r2 > 1.0f ? _sp0 * _t7 : 0.5f * (float) Math.sqrt(_t12);
        _dst.rW = _t9 > 0.0f ? 0.5f * (float) Math.sqrt(_t11) : _r3 > _t0 ? -(_sp0 * _t8) : _r2 > 1.0f ? _sp1 * _t7 : _sp3 * _t14;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_orthogonal(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _r2 = this.m11;
        float _r3 = this.m00;
        float _r4 = this.m01;
        float _r5 = this.m10;
        float _t3 = _r3 - _r2;
        float _t5 = _r2 - _r3;
        float _t9 = 1.0f + (_r3 + _r2);
        float _t11 = 1.0f + _t9;
        float _t12 = 1.0f + (1.0f - _r3 - _r2);
        toDualQuat_orthogonal_s416ccfb5_c0(d, _t9, 0.5f * _r1, (1.0f / (float) Math.sqrt(_t11)), _r3, Math.max(_r2, 1.0f), _t3, _r2, 0.5f * (_r4 + _r5), (1.0f / (float) Math.sqrt(_t5)), 0.5f * _r0, (1.0f / (float) Math.sqrt(_t12)), (1.0f / (float) Math.sqrt(_t3)), _t5, 0.5f * (_r5 - _r4), _t12, _t11);
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
        return d;
    }

    /** Private store group 0 of {@code toDualQuat_general}: computes and stores it; reached only through it. */
    private void toDualQuat_general_s416ccfb5_c0(FloatDualQuatImpl _dst, float _t13, float _sp0, float _t3, float _r0, float _t4, float _t15, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        _dst.rX = _t13 > 0.0f ? _sp0 * _t3 : _r0 > _t4 ? 0.5f * (float) Math.sqrt(_t15) : _r3 > _r4 ? _sp1 * _t5 : _sp2 * _t6;
        _dst.rY = _t13 > 0.0f ? _sp0 * _t7 : _r0 > _t4 ? _sp3 * _t5 : _r3 > _r4 ? 0.5f * (float) Math.sqrt(_t16) : _sp2 * _t8;
        _dst.rZ = _t13 > 0.0f ? _sp0 * _t9 : _r0 > _t4 ? _sp3 * _t6 : _r3 > _r4 ? _sp1 * _t8 : 0.5f * (float) Math.sqrt(_t17);
        _dst.rW = _t13 > 0.0f ? 0.5f * (float) Math.sqrt(_t14) : _r0 > _t4 ? _sp3 * _t3 : _r3 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /** Private tail of {@code toDualQuat_general}; reached only through it. */
    private void toDualQuat_general_s416ccfb5_tail(FloatDualQuatImpl _dst, float _t15, float _t13, float _sp0, float _t3, float _r0, float _t4, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        toDualQuat_general_s416ccfb5_c0(_dst, _t13, _sp0, _t3, _r0, _t4, _t15, _r3, _r4, _sp1, _t5, _sp2, _t6, _t7, 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
        _dst.dX = 0.0f;
        _dst.dY = 0.0f;
        _dst.dZ = 0.0f;
        _dst.dW = 0.0f;
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_general(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.m00;
        float _r1 = this.m21;
        float _r2 = this.m12;
        float _r3 = this.m11;
        float _r4 = this.m22;
        float _r5 = this.m01;
        float _r6 = this.m10;
        float _r7 = this.m02;
        float _r8 = this.m20;
        float _t1 = 1.0f - _r0;
        float _t13 = _r4 + (_r0 + _r3);
        float _t14 = 1.0f + _t13;
        float _t16 = _r3 + (_t1 - _r4);
        float _t17 = _r4 + (_t1 - _r3);
        toDualQuat_general_s416ccfb5_tail(d, _r0 + (1.0f - _r3 - _r4), _t13, 0.5f * (1.0f / (float) Math.sqrt(_t14)), _r1 - _r2, _r0, Math.max(_r3, _r4), _r3, _r4, 0.5f * (1.0f / (float) Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r2 + _r1, _r6 - _r5, _t17, _t14);
        return d;
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

    /** Private store group 0 of {@code toDualQuat}: computes and stores it; reached only through it. */
    private void toDualQuat_s6608609c_c0(DoubleDualQuatImpl _dst, float _t13, float _sp0, float _t3, float _r0, float _t4, float _t15, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        _dst.rX = _t13 > 0.0f ? _sp0 * _t3 : _r0 > _t4 ? 0.5f * (float) Math.sqrt(_t15) : _r3 > _r4 ? _sp1 * _t5 : _sp2 * _t6;
        _dst.rY = _t13 > 0.0f ? _sp0 * _t7 : _r0 > _t4 ? _sp3 * _t5 : _r3 > _r4 ? 0.5f * (float) Math.sqrt(_t16) : _sp2 * _t8;
        _dst.rZ = _t13 > 0.0f ? _sp0 * _t9 : _r0 > _t4 ? _sp3 * _t6 : _r3 > _r4 ? _sp1 * _t8 : 0.5f * (float) Math.sqrt(_t17);
        _dst.rW = _t13 > 0.0f ? 0.5f * (float) Math.sqrt(_t14) : _r0 > _t4 ? _sp3 * _t3 : _r3 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /** Private tail of {@code toDualQuat}; reached only through it. */
    private void toDualQuat_s6608609c_tail(DoubleDualQuatImpl _dst, float _t15, float _t13, float _sp0, float _t3, float _r0, float _t4, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        toDualQuat_s6608609c_c0(_dst, _t13, _sp0, _t3, _r0, _t4, _t15, _r3, _r4, _sp1, _t5, _sp2, _t6, _t7, 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
        _dst.dX = 0.0f;
        _dst.dY = 0.0f;
        _dst.dZ = 0.0f;
        _dst.dW = 0.0f;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.m00;
        float _r1 = this.m21;
        float _r2 = this.m12;
        float _r3 = this.m11;
        float _r4 = this.m22;
        float _r5 = this.m01;
        float _r6 = this.m10;
        float _r7 = this.m02;
        float _r8 = this.m20;
        float _t1 = 1.0f - _r0;
        float _t13 = _r4 + (_r0 + _r3);
        float _t14 = 1.0f + _t13;
        float _t16 = _r3 + (_t1 - _r4);
        float _t17 = _r4 + (_t1 - _r3);
        toDualQuat_s6608609c_tail(d, _r0 + (1.0f - _r3 - _r4), _t13, 0.5f * (1.0f / (float) Math.sqrt(_t14)), _r1 - _r2, _r0, Math.max(_r3, _r4), _r3, _r4, 0.5f * (1.0f / (float) Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r2 + _r1, _r6 - _r5, _t17, _t14);
        return d;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private FloatRigid toRigid_identity(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = 0.0f;
        d.rY = 0.0f;
        d.rZ = 0.0f;
        d.rW = 1.0f;
        return d;
    }

    /**
     * Private store group 0 of {@code toRigid_translation}: computes and stores it. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private void toRigid_translation_s1bedcc31_c0(FloatRigidImpl _dst, float _t13, float _sp0, float _t19, float _t8, float _t4, float _t11, float _t3, float _sp1, float _t20) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t13 > 0.0f ? -(_sp0 * _t19) : _t8 > _t4 ? 0.5f * (float) Math.sqrt(_t11) : 1.0f > _t3 ? 0.0f : _sp1 * _t20;
    }

    /**
     * Private store group 1 of {@code toRigid_translation}: computes and stores it. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private void toRigid_translation_s1bedcc31_c1(FloatRigidImpl _dst, float _t13, float _sp1, float _t19, float _t8, float _t4, float _t3, float _t15, float _sp0, float _t20, float _t12, float _t16, float _t18, float _t17) {
        _dst.rY = _t13 > 0.0f ? _sp1 * _t19 : _t8 > _t4 ? 0.0f : 1.0f > _t3 ? 0.5f * (float) Math.sqrt(_t15) : _sp0 * _t20;
        _dst.rZ = _t13 > 0.0f ? 0.0f : _t8 > _t4 ? _sp1 * _t12 : 1.0f > _t3 ? _sp0 * _t16 : 0.5f * (float) Math.sqrt(_t18);
        _dst.rW = _t13 > 0.0f ? 0.5f * (float) Math.sqrt(_t17) : _t8 > _t4 ? -(_sp0 * _t12) : 1.0f > _t3 ? _sp1 * _t16 : 0.0f;
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_translation(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _ct0 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, 1.0f));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t3 = (1.0f / (float) Math.sqrt(_ct0));
        float _t4 = Math.max(1.0f, _t3);
        float _sp0 = 0.5f * _r1 * _t3;
        float _sp1 = 0.5f * _r0 * _t3;
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        float _t18 = 1.0f + _t3 - _t8 - 1.0f;
        float _t19 = (1.0f / (float) Math.sqrt(_t17));
        float _t20 = (1.0f / (float) Math.sqrt(_t18));
        toRigid_translation_s1bedcc31_c0(d, _t13, _sp0, _t19, _t8, _t4, _t11, _t3, _sp1, _t20);
        toRigid_translation_s1bedcc31_c1(d, _t13, _sp1, _t19, _t8, _t4, _t3, _t15, _sp0, _t20, (1.0f / (float) Math.sqrt(_t11)), (1.0f / (float) Math.sqrt(_t15)), _t18, _t17);
        return d;
    }

    /** Private store group 0 of {@code toRigid_general}: computes and stores it; reached only through it. */
    private void toRigid_general_s1bedcc31_c0(FloatRigidImpl _dst, float _t62, float _sp0, float _t35, float _t47, float _t36, float _t67, float _t24, float _t19, float _sp1, float _t54, float _sp2, float _t55) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t62 > 0.0f ? _sp0 * _t35 : _t47 > _t36 ? 0.5f * (float) Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
    }

    /** Private store group 1 of {@code toRigid_general}: computes and stores it; reached only through it. */
    private void toRigid_general_s1bedcc31_c1(FloatRigidImpl _dst, float _t62, float _sp0, float _t56, float _t47, float _t36, float _sp3, float _t54, float _t24, float _t19, float _t65, float _sp2, float _t31, float _t57, float _t55, float _sp1, float _t66, float _t63, float _t35) {
        _dst.rY = _t62 > 0.0f ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5f * (float) Math.sqrt(_t65) : _sp2 * _t31;
        _dst.rZ = _t62 > 0.0f ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5f * (float) Math.sqrt(_t66);
        _dst.rW = _t62 > 0.0f ? 0.5f * (float) Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
    }

    /** Private tail of {@code toRigid_general}; reached only through it. */
    private void toRigid_general_s1bedcc31_tail(FloatRigidImpl _dst, float _r0, float _r1, float _r8, float _t17, float _t16, float _r5, float _r6, float _r2, float _t15, float _r7, float _r3, float _r4) {
        float _t18 = _r8 * _t17;
        float _t19 = _r1 * _t16;
        float _t20 = _r5 * _t16;
        float _t21 = _r6 * _t17;
        float _t23 = _r2 * _t15;
        float _t24 = _r0 * _t15;
        float _t26 = _r7 * _t17;
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        toRigid_general_s1bedcc31_tail2(_dst, _r4, _t16, _t49, _r3, _t15, _t48, _r0, _r1, _t47, 1.0f + _t47, -_r1, 1.0f - _t47, -_r0, Math.fma(_r2, _t15, -_t20), Math.max(_t24, _t19), _t24, _t19, Math.fma(_r3, _t15, _t48), Math.fma(_r5, _t16, _t23));
    }

    /** Private tail of {@code toRigid_general}; reached only through it. */
    private void toRigid_general_s1bedcc31_tail2(FloatRigidImpl _dst, float _r4, float _t16, float _t49, float _r3, float _t15, float _t48, float _r0, float _r1, float _t47, float _t51, float _t1, float _t52, float _t0, float _t35, float _t36, float _t24, float _t19, float _t54, float _t31) {
        float _t55 = Math.fma(_r4, _t16, _t49);
        float _t62 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t47));
        float _t63 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t51));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t63));
        float _t65 = Math.fma(_r0, _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(_r1, _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        toRigid_general_s1bedcc31_c0(_dst, _t62, _sp0, _t35, _t47, _t36, _t67, _t24, _t19, _sp1, _t54, _sp2, _t55);
        toRigid_general_s1bedcc31_c1(_dst, _t62, _sp0, Math.fma(_r4, _t16, -_t49), _t47, _t36, 0.5f * (1.0f / (float) Math.sqrt(_t67)), _t54, _t24, _t19, _t65, _sp2, _t31, Math.fma(-_r3, _t15, _t48), _t55, _sp1, _t66, _t63, _t35);
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_general(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m21;
        float _r3 = this.m01;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t15 = Math.fma(_r2, _r2, Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t15 > 1.1754944E-38f && _t15 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t16 = Math.fma(_r1, _r1, Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t16 > 1.1754944E-38f && _t16 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t17 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t17 > 1.1754944E-38f && _t17 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        toRigid_general_s1bedcc31_tail(d, _r0, _r1, _r8, (1.0f / (float) Math.sqrt(_t17)), (1.0f / (float) Math.sqrt(_t16)), _r5, _r6, _r2, (1.0f / (float) Math.sqrt(_t15)), _r7, _r3, _r4);
        return d;
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

    /** Private store group 0 of {@code toRigid}: computes and stores it; reached only through it. */
    private void toRigid_s14533caa_c0(DoubleRigidImpl _dst, float _t62, float _sp0, float _t35, float _t47, float _t36, float _t67, float _t24, float _t19, float _sp1, float _t54, float _sp2, float _t55) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t62 > 0.0f ? _sp0 * _t35 : _t47 > _t36 ? 0.5f * (float) Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
    }

    /** Private store group 1 of {@code toRigid}: computes and stores it; reached only through it. */
    private void toRigid_s14533caa_c1(DoubleRigidImpl _dst, float _t62, float _sp0, float _t56, float _t47, float _t36, float _sp3, float _t54, float _t24, float _t19, float _t65, float _sp2, float _t31, float _t57, float _t55, float _sp1, float _t66, float _t63, float _t35) {
        _dst.rY = _t62 > 0.0f ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5f * (float) Math.sqrt(_t65) : _sp2 * _t31;
        _dst.rZ = _t62 > 0.0f ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5f * (float) Math.sqrt(_t66);
        _dst.rW = _t62 > 0.0f ? 0.5f * (float) Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
    }

    /** Private tail of {@code toRigid}; reached only through it. */
    private void toRigid_s14533caa_tail(DoubleRigidImpl _dst, float _r0, float _r1, float _r8, float _t17, float _t16, float _r5, float _r6, float _r2, float _t15, float _r7, float _r3, float _r4) {
        float _t18 = _r8 * _t17;
        float _t19 = _r1 * _t16;
        float _t20 = _r5 * _t16;
        float _t21 = _r6 * _t17;
        float _t23 = _r2 * _t15;
        float _t24 = _r0 * _t15;
        float _t26 = _r7 * _t17;
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        toRigid_s14533caa_tail2(_dst, _r4, _t16, _t49, _r3, _t15, _t48, _r0, _r1, _t47, 1.0f + _t47, -_r1, 1.0f - _t47, -_r0, Math.fma(_r2, _t15, -_t20), Math.max(_t24, _t19), _t24, _t19, Math.fma(_r3, _t15, _t48), Math.fma(_r5, _t16, _t23));
    }

    /** Private tail of {@code toRigid}; reached only through it. */
    private void toRigid_s14533caa_tail2(DoubleRigidImpl _dst, float _r4, float _t16, float _t49, float _r3, float _t15, float _t48, float _r0, float _r1, float _t47, float _t51, float _t1, float _t52, float _t0, float _t35, float _t36, float _t24, float _t19, float _t54, float _t31) {
        float _t55 = Math.fma(_r4, _t16, _t49);
        float _t62 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t47));
        float _t63 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t51));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t63));
        float _t65 = Math.fma(_r0, _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(_r1, _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        toRigid_s14533caa_c0(_dst, _t62, _sp0, _t35, _t47, _t36, _t67, _t24, _t19, _sp1, _t54, _sp2, _t55);
        toRigid_s14533caa_c1(_dst, _t62, _sp0, Math.fma(_r4, _t16, -_t49), _t47, _t36, 0.5f * (1.0f / (float) Math.sqrt(_t67)), _t54, _t24, _t19, _t65, _sp2, _t31, Math.fma(-_r3, _t15, _t48), _t55, _sp1, _t66, _t63, _t35);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m21;
        float _r3 = this.m01;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t15 = Math.fma(_r2, _r2, Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t15 > 1.1754944E-38f && _t15 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t16 = Math.fma(_r1, _r1, Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t16 > 1.1754944E-38f && _t16 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        float _t17 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t17 > 1.1754944E-38f && _t17 < Float.POSITIVE_INFINITY)) return toRigid_degenerate(dest);
        toRigid_s14533caa_tail(d, _r0, _r1, _r8, (1.0f / (float) Math.sqrt(_t17)), (1.0f / (float) Math.sqrt(_t16)), _r5, _r6, _r2, (1.0f / (float) Math.sqrt(_t15)), _r7, _r3, _r4);
        return d;
    }


    /** Private tail of {@code toRigid_degenerate_translation}; reached only through it. */
    private void toRigid_degenerate_translation_s1bedcc31_tail(FloatRigidImpl _dst, float _t28, float _t23, float _sp0, float _t29, float _t18, float _t14, float _t21, float _t13, float _sp1, float _t25, float _t22, float _t26, float _t27) {
        float _t30 = (1.0f / (float) Math.sqrt(_t28));
        toRigid_translation_s1bedcc31_c0(_dst, _t23, _sp0, _t29, _t18, _t14, _t21, _t13, _sp1, _t30);
        toRigid_translation_s1bedcc31_c1(_dst, _t23, _sp1, _t29, _t18, _t14, _t13, _t25, _sp0, _t30, _t22, _t26, _t28, _t27);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_translation(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _t0 = unitScale(_r0, _r1, 1.0f);
        float _t1 = _t0;
        float _t4 = _r0 * _t0;
        float _t5 = _r1 * _t0;
        float _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) Math.sqrt(_t8));
        float _t13, _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _t13 = 1.0f;
            _sp0 = 0.5f * 0.0f;
            _sp1 = 0.5f * 0.0f;
        } else {
            _t13 = _t9 * _t1;
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        toRigid_degenerate_translation_s1bedcc31_tail(d, 1.0f + _t13 - _t18 - 1.0f, _t23, _sp0, (1.0f / (float) Math.sqrt(_t27)), _t18, Math.max(1.0f, _t13), _t21, _t13, _sp1, _t25, (1.0f / (float) Math.sqrt(_t21)), (1.0f / (float) Math.sqrt(_t25)), _t27);
        return d;
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_general(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
            d.rX = _sp0 * _t182;
            d.rY = _sp0 * _t201;
            d.rZ = _sp0 * _t202;
            d.rW = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                d.rX = 0.5f * (float) Math.sqrt(_t208);
                d.rY = _sp3 * _t199;
                d.rZ = _sp3 * _t200;
                d.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    d.rX = _sp1 * _t199;
                    d.rY = 0.5f * (float) Math.sqrt(_t209);
                    d.rZ = _sp1 * _t184;
                    d.rW = _sp1 * _t201;
                } else {
                    d.rX = _sp2 * _t200;
                    d.rY = _sp2 * _t184;
                    d.rZ = 0.5f * (float) Math.sqrt(_t210);
                    d.rW = _sp2 * _t202;
                }
            }
        }
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        return d;
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
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
            d.rX = _sp0 * _t182;
            d.rY = _sp0 * _t201;
            d.rZ = _sp0 * _t202;
            d.rW = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                d.rX = 0.5f * (float) Math.sqrt(_t208);
                d.rY = _sp3 * _t199;
                d.rZ = _sp3 * _t200;
                d.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    d.rX = _sp1 * _t199;
                    d.rY = 0.5f * (float) Math.sqrt(_t209);
                    d.rZ = _sp1 * _t184;
                    d.rW = _sp1 * _t201;
                } else {
                    d.rX = _sp2 * _t200;
                    d.rY = _sp2 * _t184;
                    d.rZ = 0.5f * (float) Math.sqrt(_t210);
                    d.rW = _sp2 * _t202;
                }
            }
        }
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        return d;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private FloatTransform toTransform_identity(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = 0.0f;
        d.rY = 0.0f;
        d.rZ = 0.0f;
        d.rW = 1.0f;
        d.sX = 1.0f;
        d.sY = 1.0f;
        d.sZ = 1.0f;
        return d;
    }

    /**
     * Private store group 0 of {@code toTransform_translation}: computes and stores it. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private void toTransform_translation_s6e32d690_c0(FloatTransformImpl _dst, float _t13, float _sp0, float _t19, float _t8, float _t4, float _t11, float _t3, float _sp1, float _t20) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t13 > 0.0f ? -(_sp0 * _t19) : _t8 > _t4 ? 0.5f * (float) Math.sqrt(_t11) : 1.0f > _t3 ? 0.0f : _sp1 * _t20;
    }

    /**
     * Private store group 1 of {@code toTransform_translation}: computes and stores it. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private void toTransform_translation_s6e32d690_c1(FloatTransformImpl _dst, float _t13, float _sp1, float _t19, float _t8, float _t4, float _t3, float _t15, float _sp0, float _t20, float _t12, float _t16, float _t18, float _t17) {
        _dst.rY = _t13 > 0.0f ? _sp1 * _t19 : _t8 > _t4 ? 0.0f : 1.0f > _t3 ? 0.5f * (float) Math.sqrt(_t15) : _sp0 * _t20;
        _dst.rZ = _t13 > 0.0f ? 0.0f : _t8 > _t4 ? _sp1 * _t12 : 1.0f > _t3 ? _sp0 * _t16 : 0.5f * (float) Math.sqrt(_t18);
        _dst.rW = _t13 > 0.0f ? 0.5f * (float) Math.sqrt(_t17) : _t8 > _t4 ? -(_sp0 * _t12) : 1.0f > _t3 ? _sp1 * _t16 : 0.0f;
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_translation(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, 1.0f));
        if (!(_t2 > 1.1754944E-38f && _t2 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t4 = Math.max(1.0f, _t3);
        float _sp0 = 0.5f * _r1 * _t3;
        float _sp1 = 0.5f * _r0 * _t3;
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        float _t18 = 1.0f + _t3 - _t8 - 1.0f;
        float _t19 = (1.0f / (float) Math.sqrt(_t17));
        float _t20 = (1.0f / (float) Math.sqrt(_t18));
        toTransform_translation_s6e32d690_c0(d, _t13, _sp0, _t19, _t8, _t4, _t11, _t3, _sp1, _t20);
        toTransform_translation_s6e32d690_c1(d, _t13, _sp1, _t19, _t8, _t4, _t3, _t15, _sp0, _t20, (1.0f / (float) Math.sqrt(_t11)), (1.0f / (float) Math.sqrt(_t15)), _t18, _t17);
        d.sX = _t8;
        d.sY = 1.0f;
        d.sZ = (float) Math.sqrt(_t2);
        return d;
    }

    /** Private store group 0 of {@code toTransform_general}: computes and stores it; reached only through it. */
    private void toTransform_general_s6e32d690_c0(FloatTransformImpl _dst, float _t63, float _sp0, float _t36, float _t48, float _t37, float _t68, float _t25, float _t20, float _sp1, float _t55, float _sp2, float _t56) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
    }

    /** Private store group 1 of {@code toTransform_general}: computes and stores it; reached only through it. */
    private void toTransform_general_s6e32d690_c1(FloatTransformImpl _dst, float _t63, float _sp0, float _t57, float _t48, float _t37, float _sp3, float _t55, float _t25, float _t20, float _t66, float _sp2, float _t32, float _t58, float _t56, float _sp1, float _t67, float _t64, float _t36) {
        _dst.rY = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        _dst.rZ = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        _dst.rW = _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
    }

    /** Private store group 2 of {@code toTransform_general}: computes and stores it; reached only through it. */
    private void toTransform_general_s6e32d690_c2(FloatTransformImpl _dst, float _t47, float _t18, float _t12, float _t13) {
        _dst.sX = _t47 < 0.0f ? -_t18 : _t18;
        _dst.sY = (float) Math.sqrt(_t12);
        _dst.sZ = (float) Math.sqrt(_t13);
    }

    /** Private tail of {@code toTransform_general}; reached only through it. */
    private void toTransform_general_s6e32d690_tail(FloatTransformImpl _dst, float _r0, float _r1, float _t12, float _t13, float _t14, float _r8, float _r5, float _r6, float _r2, float _r7, float _r3, float _r4) {
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t17 = (1.0f / (float) Math.sqrt(_t14));
        float _t19 = _r8 * _t17;
        float _t20 = _r1 * _t16;
        float _t21 = _r5 * _t16;
        float _t22 = _r6 * _t17;
        float _t24 = _r2 * _t15;
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _r3 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _r4 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        toTransform_general_s6e32d690_tail2(_dst, _t48, _r3, _t15, _t49, _r4, _t16, _t50, _r0, _r1, -_r1, -_r0, Math.fma(_r2, _t15, -_t21), Math.max(_t25, _t20), _t25, _t20, Math.fma(_r5, _t16, _t24), _t47, (float) Math.sqrt(_t14), _t12, _t13);
    }

    /** Private tail of {@code toTransform_general}; reached only through it. */
    private void toTransform_general_s6e32d690_tail2(FloatTransformImpl _dst, float _t48, float _r3, float _t15, float _t49, float _r4, float _t16, float _t50, float _r0, float _r1, float _t1, float _t0, float _t36, float _t37, float _t25, float _t20, float _t32, float _t47, float _t18, float _t12, float _t13) {
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t55 = Math.fma(_r3, _t15, _t49);
        float _t56 = Math.fma(_r4, _t16, _t50);
        float _t63 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t48));
        float _t64 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t52));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t64));
        float _t66 = Math.fma(_r0, _t15, Math.fma(_t1, _t16, _t53));
        float _t67 = Math.fma(_r1, _t16, Math.fma(_t0, _t15, _t53));
        float _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        toTransform_general_s6e32d690_c0(_dst, _t63, _sp0, _t36, _t48, _t37, _t68, _t25, _t20, _sp1, _t55, _sp2, _t56);
        toTransform_general_s6e32d690_c1(_dst, _t63, _sp0, Math.fma(_r4, _t16, -_t50), _t48, _t37, 0.5f * (1.0f / (float) Math.sqrt(_t68)), _t55, _t25, _t20, _t66, _sp2, _t32, Math.fma(-_r3, _t15, _t49), _t56, _sp1, _t67, _t64, _t36);
        toTransform_general_s6e32d690_c2(_dst, _t47, _t18, _t12, _t13);
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_general(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m21;
        float _r3 = this.m01;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t12 = Math.fma(_r2, _r2, Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t13 = Math.fma(_r1, _r1, Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t14 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        toTransform_general_s6e32d690_tail(d, _r0, _r1, _t12, _t13, _t14, _r8, _r5, _r6, _r2, _r7, _r3, _r4);
        return d;
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

    /** Private store group 0 of {@code toTransform}: computes and stores it; reached only through it. */
    private void toTransform_s5d096289_c0(DoubleTransformImpl _dst, float _t63, float _sp0, float _t36, float _t48, float _t37, float _t68, float _t25, float _t20, float _sp1, float _t55, float _sp2, float _t56) {
        _dst.tX = 0.0f;
        _dst.tY = 0.0f;
        _dst.tZ = 0.0f;
        _dst.rX = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
    }

    /** Private store group 1 of {@code toTransform}: computes and stores it; reached only through it. */
    private void toTransform_s5d096289_c1(DoubleTransformImpl _dst, float _t63, float _sp0, float _t57, float _t48, float _t37, float _sp3, float _t55, float _t25, float _t20, float _t66, float _sp2, float _t32, float _t58, float _t56, float _sp1, float _t67, float _t64, float _t36) {
        _dst.rY = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        _dst.rZ = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        _dst.rW = _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
    }

    /** Private store group 2 of {@code toTransform}: computes and stores it; reached only through it. */
    private void toTransform_s5d096289_c2(DoubleTransformImpl _dst, float _t47, float _t18, float _t12, float _t13) {
        _dst.sX = _t47 < 0.0f ? -_t18 : _t18;
        _dst.sY = (float) Math.sqrt(_t12);
        _dst.sZ = (float) Math.sqrt(_t13);
    }

    /** Private tail of {@code toTransform}; reached only through it. */
    private void toTransform_s5d096289_tail(DoubleTransformImpl _dst, float _r0, float _r1, float _t12, float _t13, float _t14, float _r8, float _r5, float _r6, float _r2, float _r7, float _r3, float _r4) {
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t17 = (1.0f / (float) Math.sqrt(_t14));
        float _t19 = _r8 * _t17;
        float _t20 = _r1 * _t16;
        float _t21 = _r5 * _t16;
        float _t22 = _r6 * _t17;
        float _t24 = _r2 * _t15;
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _r3 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _r4 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        toTransform_s5d096289_tail2(_dst, _t48, _r3, _t15, _t49, _r4, _t16, _t50, _r0, _r1, -_r1, -_r0, Math.fma(_r2, _t15, -_t21), Math.max(_t25, _t20), _t25, _t20, Math.fma(_r5, _t16, _t24), _t47, (float) Math.sqrt(_t14), _t12, _t13);
    }

    /** Private tail of {@code toTransform}; reached only through it. */
    private void toTransform_s5d096289_tail2(DoubleTransformImpl _dst, float _t48, float _r3, float _t15, float _t49, float _r4, float _t16, float _t50, float _r0, float _r1, float _t1, float _t0, float _t36, float _t37, float _t25, float _t20, float _t32, float _t47, float _t18, float _t12, float _t13) {
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t55 = Math.fma(_r3, _t15, _t49);
        float _t56 = Math.fma(_r4, _t16, _t50);
        float _t63 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t48));
        float _t64 = Math.fma(_r0, _t15, Math.fma(_r1, _t16, _t52));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t64));
        float _t66 = Math.fma(_r0, _t15, Math.fma(_t1, _t16, _t53));
        float _t67 = Math.fma(_r1, _t16, Math.fma(_t0, _t15, _t53));
        float _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        toTransform_s5d096289_c0(_dst, _t63, _sp0, _t36, _t48, _t37, _t68, _t25, _t20, _sp1, _t55, _sp2, _t56);
        toTransform_s5d096289_c1(_dst, _t63, _sp0, Math.fma(_r4, _t16, -_t50), _t48, _t37, 0.5f * (1.0f / (float) Math.sqrt(_t68)), _t55, _t25, _t20, _t66, _sp2, _t32, Math.fma(-_r3, _t15, _t49), _t56, _sp1, _t67, _t64, _t36);
        toTransform_s5d096289_c2(_dst, _t47, _t18, _t12, _t13);
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.m11;
        float _r1 = this.m22;
        float _r2 = this.m21;
        float _r3 = this.m01;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m00;
        float _r8 = this.m10;
        float _t12 = Math.fma(_r2, _r2, Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t13 = Math.fma(_r1, _r1, Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        float _t14 = Math.fma(_r6, _r6, Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return toTransform_degenerate(dest);
        toTransform_s5d096289_tail(d, _r0, _r1, _t12, _t13, _t14, _r8, _r5, _r6, _r2, _r7, _r3, _r4);
        return d;
    }


    /** Private tail of {@code toTransform_degenerate_translation}; reached only through it. */
    private void toTransform_degenerate_translation_s6e32d690_tail(FloatTransformImpl _dst, float _t28, float _t23, float _sp0, float _t29, float _t18, float _t14, float _t21, float _t13, float _sp1, float _t25, float _t22, float _t26, float _t27, float _t8, float _t0) {
        float _t30 = (1.0f / (float) Math.sqrt(_t28));
        toTransform_translation_s6e32d690_c0(_dst, _t23, _sp0, _t29, _t18, _t14, _t21, _t13, _sp1, _t30);
        toTransform_translation_s6e32d690_c1(_dst, _t23, _sp1, _t29, _t18, _t14, _t13, _t25, _sp0, _t30, _t22, _t26, _t28, _t27);
        _dst.sX = _t18;
        _dst.sY = 1.0f;
        _dst.sZ = _t8 <= 0.0f ? 0.0f : (float) Math.sqrt(_t8) / _t0;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_translation(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _t0 = unitScale(_r0, _r1, 1.0f);
        float _t1 = _t0;
        float _t4 = _r0 * _t0;
        float _t5 = _r1 * _t0;
        float _t8 = Math.fma(_t1, _t1, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) Math.sqrt(_t8));
        float _t13, _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _t13 = 1.0f;
            _sp0 = 0.5f * 0.0f;
            _sp1 = 0.5f * 0.0f;
        } else {
            _t13 = _t9 * _t1;
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        toTransform_degenerate_translation_s6e32d690_tail(d, 1.0f + _t13 - _t18 - 1.0f, _t23, _sp0, (1.0f / (float) Math.sqrt(_t27)), _t18, Math.max(1.0f, _t13), _t21, _t13, _sp1, _t25, (1.0f / (float) Math.sqrt(_t21)), (1.0f / (float) Math.sqrt(_t25)), _t27, _t8, _t0);
        return d;
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_general(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
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
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        d.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        d.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        d.rW = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        d.sX = _t196 < 0.0f ? -_t56 : _t56;
        d.sY = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        d.sZ = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
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
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        d.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        d.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        d.rW = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        d.sX = _t196 < 0.0f ? -_t56 : _t56;
        d.sY = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        d.sZ = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
        return d;
    }


    /** Private tail of {@code decomposeRotation_general}; reached only through it. */
    private void decomposeRotation_general_s6f858fd1_tail(FloatQuatImpl _dst, float _t19, float _t9, float _r5, float _t21, float _t22, float _t20, float _t7, float _r6, float _t8, float _r7, float _r8) {
        float _t23 = Math.fma(_t19, _t9, _r5);
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
        float _t40 = -Math.fma(Math.fma(_t20, _t7, _r6), _t35, Math.fma(Math.fma(_t20, _t8, _r7), _t34, Math.fma(_t20, _t9, _r8) * _t36));
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, _r6));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, _r7));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, _r8));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        decomposeRotation_general_s6f858fd1_tail2(_dst, _t49, _t46, (1.0f / (float) Math.sqrt(_t49)), _t45, _t44, _t35, _t36, _t34, _t7, _t8, _t9);
    }

    /** Private tail of {@code decomposeRotation_general}; reached only through it. */
    private void decomposeRotation_general_s6f858fd1_tail2(FloatQuatImpl _dst, float _t49, float _t46, float _t50, float _t45, float _t44, float _t35, float _t36, float _t34, float _t7, float _t8, float _t9) {
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
        decomposeRotation_general_s6f858fd1_tail3(_dst, _t76 + _t56, _t73, _t36, _t56, _t76, _t35 - _t54, Math.max(_t36, _t56), _t74 + _t34, _t75 + _t55, _t55 - _t75, _t35 + _t54, _t74 - _t34);
    }

    /** Private tail of {@code decomposeRotation_general}; reached only through it. */
    private void decomposeRotation_general_s6f858fd1_tail3(FloatQuatImpl _dst, float _t82, float _t73, float _t36, float _t56, float _t76, float _t60, float _t61, float _t77, float _t80, float _t81, float _t63, float _t78) {
        float _t86 = 1.0f + _t82;
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        getNormalizedRotation_general_s6f858fd1_c0(_dst, _t82, 0.5f * (1.0f / (float) Math.sqrt(_t86)), _t60, _t73, _t61, _t87, _t36, _t56, 0.5f * (1.0f / (float) Math.sqrt(_t88)), _t77, 0.5f * (1.0f / (float) Math.sqrt(_t89)), _t80, _t81, 0.5f * (1.0f / (float) Math.sqrt(_t87)), _t88, _t63, _t78, _t89, _t86);
    }


    /**
     * Private body of {@code decomposeRotation}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeRotation} dispatcher.
     */
    private FloatQuat decomposeRotation_general(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _r0 * _t3;
            _t8 = _r1 * _t3;
            _t9 = _r2 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -Math.fma(_r3, _t7, Math.fma(_r4, _t8, _r5 * _t9));
        decomposeRotation_general_s6f858fd1_tail(d, _t19, _t9, _r5, Math.fma(_t19, _t7, _r3), Math.fma(_t19, _t8, _r4), -Math.fma(_r6, _t7, Math.fma(_r7, _t8, _r8 * _t9)), _t7, _r6, _t8, _r7, _r8);
        return d;
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

    /** Private tail of {@code decomposeRotation}; reached only through it. */
    private void decomposeRotation_s62e3ac38_tail(DoubleQuatImpl _dst, float _t19, float _t9, float _r5, float _t21, float _t22, float _t20, float _t7, float _r6, float _t8, float _r7, float _r8) {
        float _t23 = Math.fma(_t19, _t9, _r5);
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
        float _t40 = -Math.fma(Math.fma(_t20, _t7, _r6), _t35, Math.fma(Math.fma(_t20, _t8, _r7), _t34, Math.fma(_t20, _t9, _r8) * _t36));
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, _r6));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, _r7));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, _r8));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        decomposeRotation_s62e3ac38_tail2(_dst, _t49, _t46, (1.0f / (float) Math.sqrt(_t49)), _t45, _t44, _t35, _t36, _t34, _t7, _t8, _t9);
    }

    /** Private tail of {@code decomposeRotation}; reached only through it. */
    private void decomposeRotation_s62e3ac38_tail2(DoubleQuatImpl _dst, float _t49, float _t46, float _t50, float _t45, float _t44, float _t35, float _t36, float _t34, float _t7, float _t8, float _t9) {
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
        decomposeRotation_s62e3ac38_tail3(_dst, _t76 + _t56, _t73, _t36, _t56, _t76, _t35 - _t54, Math.max(_t36, _t56), _t74 + _t34, _t75 + _t55, _t55 - _t75, _t35 + _t54, _t74 - _t34);
    }

    /** Private tail of {@code decomposeRotation}; reached only through it. */
    private void decomposeRotation_s62e3ac38_tail3(DoubleQuatImpl _dst, float _t82, float _t73, float _t36, float _t56, float _t76, float _t60, float _t61, float _t77, float _t80, float _t81, float _t63, float _t78) {
        float _t86 = 1.0f + _t82;
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        getNormalizedRotation_s62e3ac38_c0(_dst, _t82, 0.5f * (1.0f / (float) Math.sqrt(_t86)), _t60, _t73, _t61, _t87, _t36, _t56, 0.5f * (1.0f / (float) Math.sqrt(_t88)), _t77, 0.5f * (1.0f / (float) Math.sqrt(_t89)), _t80, _t81, 0.5f * (1.0f / (float) Math.sqrt(_t87)), _t88, _t63, _t78, _t89, _t86);
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _r0 * _t3;
            _t8 = _r1 * _t3;
            _t9 = _r2 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -Math.fma(_r3, _t7, Math.fma(_r4, _t8, _r5 * _t9));
        decomposeRotation_s62e3ac38_tail(d, _t19, _t9, _r5, Math.fma(_t19, _t7, _r3), Math.fma(_t19, _t8, _r4), -Math.fma(_r6, _t7, Math.fma(_r7, _t8, _r8 * _t9)), _t7, _r6, _t8, _r7, _r8);
        return d;
    }


    /** Private store group 0 of {@code decomposeScale_general}: computes and stores it; reached only through it. */
    private void decomposeScale_general_s52bd9409_c0(Float3Impl _dst, float _t32, float _t52, float _t34, float _t53, float _t8, float _t54, float _t33, float _t9, float _t10, float _t4, float _t27, float _t47) {
        _dst.x = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4;
        _dst.y = (float) Math.sqrt(_t27);
        _dst.z = (float) Math.sqrt(_t47);
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private void decomposeScale_general_s52bd9409_tail(Float3Impl _dst, float _t17, float _t9, float _r4, float _t10, float _r5, float _t19, float _t18, float _t8, float _r6, float _r7, float _r8, float _t4) {
        float _t20 = Math.fma(_t17, _t9, _r4);
        float _t21 = Math.fma(_t17, _t10, _r5);
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
        float _t38 = -Math.fma(Math.fma(_t18, _t8, _r6), _t33, Math.fma(Math.fma(_t18, _t9, _r7), _t32, Math.fma(_t18, _t10, _r8) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, _r6));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, _r7));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, _r8));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        decomposeScale_general_s52bd9409_tail2(_dst, _t47, _t44, (1.0f / (float) Math.sqrt(_t47)), _t43, _t42, _t32, _t34, _t8, _t33, _t9, _t10, _t4, _t27);
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private void decomposeScale_general_s52bd9409_tail2(Float3Impl _dst, float _t47, float _t44, float _t48, float _t43, float _t42, float _t32, float _t34, float _t8, float _t33, float _t9, float _t10, float _t4, float _t27) {
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
        decomposeScale_general_s52bd9409_c0(_dst, _t32, _t52, _t34, _t53, _t8, _t54, _t33, _t9, _t10, _t4, _t27, _t47);
    }


    /**
     * Private body of {@code decomposeScale}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeScale} dispatcher.
     */
    private Float3 decomposeScale_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _r0 * _t3;
            _t9 = _r1 * _t3;
            _t10 = _r2 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t17 = -Math.fma(_r3, _t8, Math.fma(_r4, _t9, _r5 * _t10));
        decomposeScale_general_s52bd9409_tail(d, _t17, _t9, _r4, _t10, _r5, Math.fma(_t17, _t8, _r3), -Math.fma(_r6, _t8, Math.fma(_r7, _t9, _r8 * _t10)), _t8, _r6, _r7, _r8, (float) Math.sqrt(_t2));
        return d;
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

    /** Private store group 0 of {@code decomposeScale}: computes and stores it; reached only through it. */
    private void decomposeScale_s34d82902_c0(Double3Impl _dst, float _t32, float _t52, float _t34, float _t53, float _t8, float _t54, float _t33, float _t9, float _t10, float _t4, float _t27, float _t47) {
        _dst.x = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4;
        _dst.y = (float) Math.sqrt(_t27);
        _dst.z = (float) Math.sqrt(_t47);
    }

    /** Private tail of {@code decomposeScale}; reached only through it. */
    private void decomposeScale_s34d82902_tail(Double3Impl _dst, float _t17, float _t9, float _r4, float _t10, float _r5, float _t19, float _t18, float _t8, float _r6, float _r7, float _r8, float _t4) {
        float _t20 = Math.fma(_t17, _t9, _r4);
        float _t21 = Math.fma(_t17, _t10, _r5);
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
        float _t38 = -Math.fma(Math.fma(_t18, _t8, _r6), _t33, Math.fma(Math.fma(_t18, _t9, _r7), _t32, Math.fma(_t18, _t10, _r8) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, _r6));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, _r7));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, _r8));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        decomposeScale_s34d82902_tail2(_dst, _t47, _t44, (1.0f / (float) Math.sqrt(_t47)), _t43, _t42, _t32, _t34, _t8, _t33, _t9, _t10, _t4, _t27);
    }

    /** Private tail of {@code decomposeScale}; reached only through it. */
    private void decomposeScale_s34d82902_tail2(Double3Impl _dst, float _t47, float _t44, float _t48, float _t43, float _t42, float _t32, float _t34, float _t8, float _t33, float _t9, float _t10, float _t4, float _t27) {
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
        decomposeScale_s34d82902_c0(_dst, _t32, _t52, _t34, _t53, _t8, _t54, _t33, _t9, _t10, _t4, _t27, _t47);
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
        Double3Impl d = (Double3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _r0 * _t3;
            _t9 = _r1 * _t3;
            _t10 = _r2 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t17 = -Math.fma(_r3, _t8, Math.fma(_r4, _t9, _r5 * _t10));
        decomposeScale_s34d82902_tail(d, _t17, _t9, _r4, _t10, _r5, Math.fma(_t17, _t8, _r3), -Math.fma(_r6, _t8, Math.fma(_r7, _t9, _r8 * _t10)), _t8, _r6, _r7, _r8, (float) Math.sqrt(_t2));
        return d;
    }



    /** Private store group 0 of {@code decomposeSkew_general}: computes and stores it; reached only through it. */
    private void decomposeSkew_general_s52bd9409_c0(Float3Impl _dst, float _t37, float _t48, float _t67, float _t49, float _t28) {
        _dst.x = _t37 * _t48;
        _dst.y = _t67 < 0.0f ? -_t49 : _t49;
        _dst.z = _t67 < 0.0f ? -_t28 : _t28;
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private void decomposeSkew_general_s52bd9409_tail(Float3Impl _dst, float _t17, float _t8, float _r7, float _t9, float _r8, float _t19, float _t15, float _t16, float _t7, float _r3, float _r4, float _r5, float _t14) {
        float _t20 = Math.fma(_t17, _t8, _r7);
        float _t21 = Math.fma(_t17, _t9, _r8);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) Math.sqrt(_t26));
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
        float _t37 = Math.fma(Math.fma(_t16, _t7, _r3), _t32, Math.fma(Math.fma(_t16, _t8, _r4), _t33, Math.fma(_t16, _t9, _r5) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, _r3));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, _r4));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, _r5));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
        decomposeSkew_general_s52bd9409_tail2(_dst, _t47, _t44, _t48, _t43, _t42, _t33, _t34, _t7, _t32, _t8, _t9, _t37, _t14 * _t48, _t15 * _t27);
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private void decomposeSkew_general_s52bd9409_tail2(Float3Impl _dst, float _t47, float _t44, float _t48, float _t43, float _t42, float _t33, float _t34, float _t7, float _t32, float _t8, float _t9, float _t37, float _t49, float _t28) {
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
        decomposeSkew_general_s52bd9409_c0(_dst, _t37, _t48, Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)), _t49, _t28);
    }


    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Float3 decomposeSkew_general(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m22;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m21;
        float _r7 = this.m01;
        float _r8 = this.m11;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _r0 * _t3;
            _t8 = _r1 * _t3;
            _t9 = _r2 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = Math.fma(_r3, _t7, Math.fma(_r4, _t8, _r5 * _t9));
        float _t15 = Math.fma(_r6, _t7, Math.fma(_r7, _t8, _r8 * _t9));
        float _t17 = -_t15;
        decomposeSkew_general_s52bd9409_tail(d, _t17, _t8, _r7, _t9, _r8, Math.fma(_t17, _t7, _r6), _t15, -_t14, _t7, _r3, _r4, _r5, _t14);
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float3Impl d = (Float3Impl) dest;
            d.x = this.m12;
            d.y = this.m02;
            d.z = 0.0f;
            return d;
        }
        return decomposeSkew_general(dest);
    }

    /** Private store group 0 of {@code decomposeSkew}: computes and stores it; reached only through it. */
    private void decomposeSkew_s34d82902_c0(Double3Impl _dst, float _t37, float _t48, float _t67, float _t49, float _t28) {
        _dst.x = _t37 * _t48;
        _dst.y = _t67 < 0.0f ? -_t49 : _t49;
        _dst.z = _t67 < 0.0f ? -_t28 : _t28;
    }

    /** Private tail of {@code decomposeSkew}; reached only through it. */
    private void decomposeSkew_s34d82902_tail(Double3Impl _dst, float _t17, float _t8, float _r7, float _t9, float _r8, float _t19, float _t15, float _t16, float _t7, float _r3, float _r4, float _r5, float _t14) {
        float _t20 = Math.fma(_t17, _t8, _r7);
        float _t21 = Math.fma(_t17, _t9, _r8);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) Math.sqrt(_t26));
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
        float _t37 = Math.fma(Math.fma(_t16, _t7, _r3), _t32, Math.fma(Math.fma(_t16, _t8, _r4), _t33, Math.fma(_t16, _t9, _r5) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, _r3));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, _r4));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, _r5));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
        decomposeSkew_s34d82902_tail2(_dst, _t47, _t44, _t48, _t43, _t42, _t33, _t34, _t7, _t32, _t8, _t9, _t37, _t14 * _t48, _t15 * _t27);
    }

    /** Private tail of {@code decomposeSkew}; reached only through it. */
    private void decomposeSkew_s34d82902_tail2(Double3Impl _dst, float _t47, float _t44, float _t48, float _t43, float _t42, float _t33, float _t34, float _t7, float _t32, float _t8, float _t9, float _t37, float _t49, float _t28) {
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
        decomposeSkew_s34d82902_c0(_dst, _t37, _t48, Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)), _t49, _t28);
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
        Double3Impl d = (Double3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m22;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = this.m21;
        float _r7 = this.m01;
        float _r8 = this.m11;
        float _t2 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _r0 * _t3;
            _t8 = _r1 * _t3;
            _t9 = _r2 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = Math.fma(_r3, _t7, Math.fma(_r4, _t8, _r5 * _t9));
        float _t15 = Math.fma(_r6, _t7, Math.fma(_r7, _t8, _r8 * _t9));
        float _t17 = -_t15;
        decomposeSkew_s34d82902_tail(d, _t17, _t8, _r7, _t9, _r8, Math.fma(_t17, _t7, _r6), _t15, -_t14, _t7, _r3, _r4, _r5, _t14);
        return d;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float3x3 makeIdentity() {
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_IDENTITY;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m20 = Math.fma(t, other.m20() - this.m20, this.m20);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.m21 = Math.fma(t, other.m21() - this.m21, this.m21);
        d.m02 = Math.fma(t, other.m02() - this.m02, this.m02);
        d.m12 = Math.fma(t, other.m12() - this.m12, this.m12);
        d.m22 = Math.fma(t, other.m22() - this.m22, this.m22);
        d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float3x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float3x3Impl) other).properties) >> 1);
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = Math.fma(t, m20 - this.m20, this.m20);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = Math.fma(t, m21 - this.m21, this.m21);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.m22 = Math.fma(t, m22 - this.m22, this.m22);
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = Math.fma(t, m20 - this.m20, this.m20);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = Math.fma(t, m21 - this.m21, this.m21);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.m22 = Math.fma(t, m22 - this.m22, this.m22);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code mul_general}: computes and stores it. Shared by the identical
     * private paths of {@code mul} and {@code preRotateAxis}; reached only through them.
     */
    private void mul_general_s2e3258fe_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        _dst.m10 = Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14));
        _dst.m20 = Math.fma(_r0, _r15, Math.fma(_r2, _r16, _r4 * _r17));
    }

    /**
     * Private column 1 of {@code mul_general}: computes and stores it. Shared by the identical
     * private paths of {@code mul} and {@code preRotateAxis}; reached only through them.
     */
    private void mul_general_s2e3258fe_c1(Float3x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m01 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        _dst.m11 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14));
        _dst.m21 = Math.fma(_r6, _r15, Math.fma(_r7, _r16, _r8 * _r17));
    }

    /**
     * Private column 2 of {@code mul_general}: computes and stores it. Shared by the identical
     * private paths of {@code mul} and {@code preRotateAxis}; reached only through them.
     */
    private void mul_general_s2e3258fe_c2(Float3x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m02 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5));
        _dst.m12 = Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14));
        _dst.m22 = Math.fma(_r9, _r15, Math.fma(_r10, _r16, _r11 * _r17));
    }

    /** Private tail of {@code mul_general}; reached only through it. */
    private void mul_general_s2e3258fe_tail(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16) {
        float _r17 = this.m21;
        mul_general_s2e3258fe_c0(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17);
        mul_general_s2e3258fe_c1(_dst, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14, _r15, _r16, _r17);
        mul_general_s2e3258fe_c2(_dst, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14, _r15, _r16, _r17);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = right.m20();
        float _r1 = this.m02;
        float _r2 = right.m00();
        float _r3 = this.m00;
        float _r4 = right.m10();
        float _r5 = this.m01;
        float _r6 = right.m21();
        float _r7 = right.m01();
        float _r8 = right.m11();
        float _r9 = right.m22();
        float _r10 = right.m02();
        float _r11 = right.m12();
        float _r12 = this.m12;
        float _r13 = this.m10;
        float _r14 = this.m11;
        float _r15 = this.m22;
        float _r16 = this.m20;
        mul_general_s2e3258fe_tail(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(right.m20(), this.m02, right.m00());
        d.m10 = Math.fma(right.m20(), this.m12, right.m10());
        d.m20 = right.m20();
        d.m01 = Math.fma(right.m21(), this.m02, right.m01());
        d.m11 = Math.fma(right.m21(), this.m12, right.m11());
        d.m21 = right.m21();
        d.m02 = Math.fma(right.m22(), this.m02, right.m02());
        d.m12 = Math.fma(right.m22(), this.m12, right.m12());
        d.m22 = right.m22();
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }

    /** Private column 0 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_s2e3258fe_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, -(_r4 * _r5)));
        _dst.m10 = Math.fma(_r0, _r12, Math.fma(_r2, _r5, _r4 * _r3));
        _dst.m20 = _r0;
    }

    /** Private column 1 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_s2e3258fe_c1(Float3x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12) {
        _dst.m01 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, -(_r8 * _r5)));
        _dst.m11 = Math.fma(_r6, _r12, Math.fma(_r7, _r5, _r8 * _r3));
        _dst.m21 = _r6;
    }

    /** Private column 2 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_s2e3258fe_c2(Float3x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12) {
        _dst.m02 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, -(_r11 * _r5)));
        _dst.m12 = Math.fma(_r9, _r12, Math.fma(_r10, _r5, _r11 * _r3));
        _dst.m22 = _r9;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = right.m20();
        float _r1 = this.m02;
        float _r2 = right.m00();
        float _r3 = this.m11;
        float _r4 = right.m10();
        float _r5 = this.m10;
        float _r6 = right.m21();
        float _r7 = right.m01();
        float _r8 = right.m11();
        float _r9 = right.m22();
        float _r10 = right.m02();
        float _r11 = right.m12();
        float _r12 = this.m12;
        mul_orthogonal_s2e3258fe_c0(d, _r0, _r1, _r2, _r3, _r4, _r5, _r12);
        mul_orthogonal_s2e3258fe_c1(d, _r6, _r1, _r7, _r3, _r8, _r5, _r12);
        mul_orthogonal_s2e3258fe_c2(d, _r9, _r1, _r10, _r3, _r11, _r5, _r12);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return d;
    }

    /** Private column 0 of {@code mul_affine}: computes and stores it; reached only through it. */
    private void mul_affine_s2e3258fe_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        _dst.m10 = Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14));
        _dst.m20 = _r0;
    }

    /** Private column 1 of {@code mul_affine}: computes and stores it; reached only through it. */
    private void mul_affine_s2e3258fe_c1(Float3x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14) {
        _dst.m01 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        _dst.m11 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14));
        _dst.m21 = _r6;
    }

    /** Private column 2 of {@code mul_affine}: computes and stores it; reached only through it. */
    private void mul_affine_s2e3258fe_c2(Float3x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14) {
        _dst.m02 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5));
        _dst.m12 = Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14));
        _dst.m22 = _r9;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = right.m20();
        float _r1 = this.m02;
        float _r2 = right.m00();
        float _r3 = this.m00;
        float _r4 = right.m10();
        float _r5 = this.m01;
        float _r6 = right.m21();
        float _r7 = right.m01();
        float _r8 = right.m11();
        float _r9 = right.m22();
        float _r10 = right.m02();
        float _r11 = right.m12();
        float _r12 = this.m12;
        float _r13 = this.m10;
        float _r14 = this.m11;
        mul_affine_s2e3258fe_c0(d, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14);
        mul_affine_s2e3258fe_c1(d, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14);
        mul_affine_s2e3258fe_c2(d, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14);
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_affine(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m20 = 0.0f;
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m21 = 0.0f;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        d.m20 = 0.0f;
        float _buf2 = this.m01;
        float _buf3 = this.m11;
        d.m21 = 0.0f;
        float _buf4 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_affine(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        float _buf3 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        float _buf4 = Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine_affine(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        float _buf3 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        float _buf4 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        float _buf2 = this.m20;
        float _buf3 = this.m01;
        float _buf4 = this.m11;
        float _buf5 = this.m21;
        float _buf6 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        float _buf7 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.m02 = _buf6;
        d.m12 = _buf7;
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code mul_general_affine}: computes and stores it. Shared by the
     * identical private paths of {@code mul} and {@code preRotateAxis}; reached only through them.
     */
    private void mul_general_affine_s2e3258fe_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r9, float _r10, float _r12, float _r13) {
        _dst.m00 = Math.fma(_r0, _r1, _r2 * _r3);
        _dst.m10 = Math.fma(_r0, _r9, _r2 * _r10);
        _dst.m20 = Math.fma(_r0, _r12, _r2 * _r13);
    }

    /**
     * Private column 1 of {@code mul_general_affine}: computes and stores it. Shared by the
     * identical private paths of {@code mul} and {@code preRotateAxis}; reached only through them.
     */
    private void mul_general_affine_s2e3258fe_c1(Float3x3Impl _dst, float _r4, float _r1, float _r5, float _r3, float _r9, float _r10, float _r12, float _r13) {
        _dst.m01 = Math.fma(_r4, _r1, _r5 * _r3);
        _dst.m11 = Math.fma(_r4, _r9, _r5 * _r10);
        _dst.m21 = Math.fma(_r4, _r12, _r5 * _r13);
    }

    /** Private column 2 of {@code mul_general_affine}: computes and stores it; reached only through it. */
    private void mul_general_affine_s2e3258fe_c2(Float3x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.m02 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8));
        _dst.m12 = Math.fma(_r6, _r9, Math.fma(_r7, _r10, _r11));
        _dst.m22 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r14));
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_affine(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = right.m00();
        float _r1 = this.m00;
        float _r2 = right.m10();
        float _r3 = this.m01;
        float _r4 = right.m01();
        float _r5 = right.m11();
        float _r6 = right.m02();
        float _r7 = right.m12();
        float _r8 = this.m02;
        float _r9 = this.m10;
        float _r10 = this.m11;
        float _r11 = this.m12;
        float _r12 = this.m20;
        float _r13 = this.m21;
        float _r14 = this.m22;
        mul_general_affine_s2e3258fe_c0(d, _r0, _r1, _r2, _r3, _r9, _r10, _r12, _r13);
        mul_general_affine_s2e3258fe_c1(d, _r4, _r1, _r5, _r3, _r9, _r10, _r12, _r13);
        mul_general_affine_s2e3258fe_c2(d, _r6, _r1, _r7, _r3, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01));
        float _buf1 = Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11));
        float _buf2 = Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21));
        float _buf3 = Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01));
        float _buf4 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        float _buf5 = Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21));
        d.m02 = Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01));
        d.m12 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        d.m22 = Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01));
        float _buf1 = Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11));
        float _buf2 = Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21));
        float _buf3 = Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01));
        float _buf4 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        float _buf5 = Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21));
        d.m02 = Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01));
        d.m12 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        d.m22 = Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m20 = 0.0f;
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code mul} and {@code preMul}; reached only through
     * them.
     */
    private Float3x3 mul_identity_self(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m20 = 0.0f;
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_self(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        float _buf0 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        d.m01 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_self(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        float _buf0 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m01 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m10 = _buf0;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine_self(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x2R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        float _buf2 = Math.fma(right.m00(), this.m20, right.m10() * this.m21);
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = Math.fma(right.m01(), this.m20, right.m11() * this.m21);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        float _buf2 = Math.fma(right.m00(), this.m20, right.m10() * this.m21);
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = Math.fma(right.m01(), this.m20, right.m11() * this.m21);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        float _buf2 = Math.fma(right.m00(), this.m20, right.m10() * this.m21);
        float _buf3 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        float _buf4 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        float _buf5 = Math.fma(right.m01(), this.m20, right.m11() * this.m21);
        d.m02 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m20 = 0.0f;
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m21 = 0.0f;
        d.m02 = right.m02();
        d.m12 = right.m12();
        d.m22 = 1.0f;
        d.properties = ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m20 = 0.0f;
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m21 = 0.0f;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        float _buf0 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        d.m01 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        float _buf1 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        d.m02 = Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        float _buf3 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = 0.0f;
        d.m02 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Float3x3 mul_identity_translation(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = right.m02();
        d.m12 = right.m12();
        d.m22 = 1.0f;
        d.properties = ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float2x3R right, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        d.m20 = 0.0f;
        float _buf2 = this.m01;
        float _buf3 = this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float2x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        float _buf2 = this.m20;
        float _buf3 = this.m01;
        float _buf4 = this.m11;
        float _buf5 = this.m21;
        d.m02 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        float _buf2 = Math.fma(right.m00(), this.m20, right.m10() * this.m21);
        float _buf3 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        float _buf4 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        float _buf5 = Math.fma(right.m01(), this.m20, right.m11() * this.m21);
        d.m02 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code preMul_general}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ},
     * {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void preMul_general_s36a279f2_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        _dst.m10 = Math.fma(_r12, _r1, Math.fma(_r13, _r3, _r14 * _r5));
        _dst.m20 = Math.fma(_r15, _r1, Math.fma(_r16, _r3, _r17 * _r5));
    }

    /**
     * Private column 1 of {@code preMul_general}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code lookAlong}, {@code rotateAxis}, {@code rotateYXZ},
     * {@code rotateYZX} and {@code rotateZYX}; reached only through them.
     */
    private void preMul_general_s36a279f2_c1(Float3x3Impl _dst, float _r0, float _r6, float _r2, float _r7, float _r4, float _r8, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m01 = Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8));
        _dst.m11 = Math.fma(_r12, _r6, Math.fma(_r13, _r7, _r14 * _r8));
        _dst.m21 = Math.fma(_r15, _r6, Math.fma(_r16, _r7, _r17 * _r8));
    }

    /**
     * Private column 2 of {@code preMul_general}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code lookAlong}, {@code rotateAxis}, {@code rotateXZY},
     * {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void preMul_general_s36a279f2_c2(Float3x3Impl _dst, float _r0, float _r9, float _r2, float _r10, float _r4, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17) {
        _dst.m02 = Math.fma(_r0, _r9, Math.fma(_r2, _r10, _r4 * _r11));
        _dst.m12 = Math.fma(_r12, _r9, Math.fma(_r13, _r10, _r14 * _r11));
        _dst.m22 = Math.fma(_r15, _r9, Math.fma(_r16, _r10, _r17 * _r11));
    }

    /** Private tail of {@code preMul_general}; reached only through it. */
    private void preMul_general_s36a279f2_tail(Float3x3Impl _dst, Float3x3R other, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16) {
        float _r17 = other.m21();
        preMul_general_s36a279f2_c0(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17);
        preMul_general_s36a279f2_c1(_dst, _r0, _r6, _r2, _r7, _r4, _r8, _r12, _r13, _r14, _r15, _r16, _r17);
        preMul_general_s36a279f2_c2(_dst, _r0, _r9, _r2, _r10, _r4, _r11, _r12, _r13, _r14, _r15, _r16, _r17);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m02();
        float _r1 = this.m20;
        float _r2 = other.m00();
        float _r3 = this.m00;
        float _r4 = other.m01();
        float _r5 = this.m10;
        float _r6 = this.m21;
        float _r7 = this.m01;
        float _r8 = this.m11;
        float _r9 = this.m22;
        float _r10 = this.m02;
        float _r11 = this.m12;
        float _r12 = other.m12();
        float _r13 = other.m10();
        float _r14 = other.m11();
        float _r15 = other.m22();
        float _r16 = other.m20();
        preMul_general_s36a279f2_tail(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = other.m00();
        float _buf1 = other.m10();
        float _buf2 = other.m20();
        float _buf3 = other.m01();
        float _buf4 = other.m11();
        float _buf5 = other.m21();
        float _buf6 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        float _buf7 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22()));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.m02 = _buf6;
        d.m12 = _buf7;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private column 0 of {@code preMul_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code preMul} and {@code rotateAround}; reached only through
     * them.
     */
    private void preMul_orthogonal_s36a279f2_c0(Float3x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r7, float _r8, float _r10, float _r11) {
        _dst.m00 = Math.fma(_r0, _r1, _r2 * _r3);
        _dst.m10 = Math.fma(_r7, _r1, _r8 * _r3);
        _dst.m20 = Math.fma(_r10, _r1, _r11 * _r3);
    }

    /**
     * Private column 1 of {@code preMul_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code preMul} and {@code rotateAround}; reached only through
     * them.
     */
    private void preMul_orthogonal_s36a279f2_c1(Float3x3Impl _dst, float _r2, float _r1, float _r0, float _r3, float _r8, float _r7, float _r11, float _r10) {
        _dst.m01 = Math.fma(_r2, _r1, -(_r0 * _r3));
        _dst.m11 = Math.fma(_r8, _r1, -(_r7 * _r3));
        _dst.m21 = Math.fma(_r11, _r1, -(_r10 * _r3));
    }

    /**
     * Private column 2 of {@code preMul_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code preMul} and {@code rotateAround}; reached only through
     * them.
     */
    private void preMul_orthogonal_s36a279f2_c2(Float3x3Impl _dst, float _r0, float _r4, float _r2, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12) {
        _dst.m02 = Math.fma(_r0, _r4, Math.fma(_r2, _r5, _r6));
        _dst.m12 = Math.fma(_r7, _r4, Math.fma(_r8, _r5, _r9));
        _dst.m22 = Math.fma(_r10, _r4, Math.fma(_r11, _r5, _r12));
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m00();
        float _r1 = this.m11;
        float _r2 = other.m01();
        float _r3 = this.m10;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = other.m02();
        float _r7 = other.m10();
        float _r8 = other.m11();
        float _r9 = other.m12();
        float _r10 = other.m20();
        float _r11 = other.m21();
        float _r12 = other.m22();
        preMul_orthogonal_s36a279f2_c0(d, _r0, _r1, _r2, _r3, _r7, _r8, _r10, _r11);
        preMul_orthogonal_s36a279f2_c1(d, _r2, _r1, _r0, _r3, _r8, _r7, _r11, _r10);
        preMul_orthogonal_s36a279f2_c2(d, _r0, _r4, _r2, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /** Private column 1 of {@code preMul_affine}: computes and stores it; reached only through it. */
    private void preMul_affine_s36a279f2_c1(Float3x3Impl _dst, float _r0, float _r4, float _r2, float _r5, float _r9, float _r10, float _r12, float _r13) {
        _dst.m01 = Math.fma(_r0, _r4, _r2 * _r5);
        _dst.m11 = Math.fma(_r9, _r4, _r10 * _r5);
        _dst.m21 = Math.fma(_r12, _r4, _r13 * _r5);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = other.m00();
        float _r1 = this.m00;
        float _r2 = other.m01();
        float _r3 = this.m10;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m02;
        float _r7 = this.m12;
        float _r8 = other.m02();
        float _r9 = other.m10();
        float _r10 = other.m11();
        float _r11 = other.m12();
        float _r12 = other.m20();
        float _r13 = other.m21();
        float _r14 = other.m22();
        preMul_orthogonal_s36a279f2_c0(d, _r0, _r1, _r2, _r3, _r9, _r10, _r12, _r13);
        preMul_affine_s36a279f2_c1(d, _r0, _r4, _r2, _r5, _r9, _r10, _r12, _r13);
        preMul_orthogonal_s36a279f2_c2(d, _r0, _r6, _r2, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = other.m00();
        float _buf1 = other.m10();
        d.m20 = 0.0f;
        float _buf2 = other.m01();
        float _buf3 = other.m11();
        d.m21 = 0.0f;
        float _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        float _buf1 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        float _buf3 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        d.m21 = 0.0f;
        float _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        float _buf1 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        float _buf3 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m21 = 0.0f;
        float _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Float3x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(other.m02(), this.m20, this.m00);
        d.m10 = Math.fma(other.m12(), this.m20, this.m10);
        d.m20 = this.m20;
        d.m01 = Math.fma(other.m02(), this.m21, this.m01);
        d.m11 = Math.fma(other.m12(), this.m21, this.m11);
        d.m21 = this.m21;
        d.m02 = Math.fma(other.m02(), this.m22, this.m02);
        d.m12 = Math.fma(other.m12(), this.m22, this.m12);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_affine(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10));
        float _buf1 = Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10));
        d.m20 = this.m20;
        float _buf2 = Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11));
        float _buf3 = Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11));
        d.m21 = this.m21;
        float _buf4 = Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12));
        d.m12 = Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12));
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(m02, this.m20, Math.fma(m00, this.m00, m01 * this.m10));
        float _buf1 = Math.fma(m12, this.m20, Math.fma(m10, this.m00, m11 * this.m10));
        d.m20 = Math.fma(m22, this.m20, Math.fma(m20, this.m00, m21 * this.m10));
        float _buf2 = Math.fma(m02, this.m21, Math.fma(m00, this.m01, m01 * this.m11));
        float _buf3 = Math.fma(m12, this.m21, Math.fma(m10, this.m01, m11 * this.m11));
        d.m21 = Math.fma(m22, this.m21, Math.fma(m20, this.m01, m21 * this.m11));
        float _buf4 = Math.fma(m02, this.m22, Math.fma(m00, this.m02, m01 * this.m12));
        float _buf5 = Math.fma(m12, this.m22, Math.fma(m10, this.m02, m11 * this.m12));
        d.m22 = Math.fma(m22, this.m22, Math.fma(m20, this.m02, m21 * this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.m12 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(m02, this.m20, Math.fma(m00, this.m00, m01 * this.m10));
        float _buf1 = Math.fma(m12, this.m20, Math.fma(m10, this.m00, m11 * this.m10));
        d.m20 = Math.fma(m22, this.m20, Math.fma(m20, this.m00, m21 * this.m10));
        float _buf2 = Math.fma(m02, this.m21, Math.fma(m00, this.m01, m01 * this.m11));
        float _buf3 = Math.fma(m12, this.m21, Math.fma(m10, this.m01, m11 * this.m11));
        d.m21 = Math.fma(m22, this.m21, Math.fma(m20, this.m01, m21 * this.m11));
        float _buf4 = Math.fma(m02, this.m22, Math.fma(m00, this.m02, m01 * this.m12));
        float _buf5 = Math.fma(m12, this.m22, Math.fma(m10, this.m02, m11 * this.m12));
        d.m22 = Math.fma(m22, this.m22, Math.fma(m20, this.m02, m21 * this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.m12 = _buf5;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m20 = 0.0f;
        d.m01 = other.m01();
        d.m11 = other.m11();
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m20 = 0.0f;
        d.m01 = other.m01();
        d.m11 = other.m11();
        d.m21 = 0.0f;
        float _buf0 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m22 = 1.0f;
        d.m02 = _buf0;
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_self(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m01 = other.m01();
        d.m11 = other.m11();
        float _buf0 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m02 = _buf0;
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        float _buf0 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        d.m20 = 0.0f;
        d.m01 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        d.m11 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        d.m21 = 0.0f;
        float _buf1 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m02 = _buf1;
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_self(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        float _buf0 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        d.m01 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        d.m11 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        float _buf1 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m10 = _buf0;
        d.m02 = _buf1;
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        d.m20 = 0.0f;
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m21 = 0.0f;
        float _buf2 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine_self(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        float _buf2 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float2x2R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        d.m20 = this.m20;
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m21 = this.m21;
        float _buf2 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(other, this);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        d.m20 = this.m20;
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m21 = this.m21;
        float _buf2 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10));
        d.m10 = Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10));
        d.m20 = this.m20;
        float _buf1 = Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11));
        d.m11 = Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11));
        d.m21 = this.m21;
        float _buf2 = Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12));
        d.m12 = Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12));
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m20 = 0.0f;
        d.m01 = other.m01();
        d.m11 = other.m11();
        d.m21 = 0.0f;
        d.m02 = other.m02();
        d.m12 = other.m12();
        d.m22 = 1.0f;
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m20 = 0.0f;
        d.m01 = other.m01();
        d.m11 = other.m11();
        d.m21 = 0.0f;
        float _buf0 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m02 = _buf0;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        float _buf0 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        d.m20 = 0.0f;
        d.m01 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        d.m11 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        d.m21 = 0.0f;
        float _buf1 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.m02 = _buf1;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        d.m20 = 0.0f;
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m21 = 0.0f;
        float _buf2 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float2x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float2x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(other.m02(), this.m20, this.m00);
        d.m10 = Math.fma(other.m12(), this.m20, this.m10);
        d.m20 = this.m20;
        d.m01 = Math.fma(other.m02(), this.m21, this.m01);
        d.m11 = Math.fma(other.m12(), this.m21, this.m11);
        d.m21 = this.m21;
        d.m02 = Math.fma(other.m02(), this.m22, this.m02);
        d.m12 = Math.fma(other.m12(), this.m22, this.m12);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_identity_translation(other, dest);
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_identity_translation(other, this);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10));
        d.m10 = Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10));
        d.m20 = this.m20;
        float _buf1 = Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11));
        d.m11 = Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11));
        d.m21 = this.m21;
        float _buf2 = Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12));
        d.m12 = Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12));
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m20 = Math.fma(weight, other.m20(), this.m20);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.m21 = Math.fma(weight, other.m21(), this.m21);
        d.m02 = Math.fma(weight, other.m02(), this.m02);
        d.m12 = Math.fma(weight, other.m12(), this.m12);
        d.m22 = Math.fma(weight, other.m22(), this.m22);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m22 = Math.fma(weight, m22, this.m22);
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m22 = Math.fma(weight, m22, this.m22);
        d.properties = 0;
        return d;
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
        this.m00 = colX * rowX;
        this.m10 = colY * rowX;
        this.m20 = colZ * rowX;
        this.m01 = colX * rowY;
        this.m11 = colY * rowY;
        this.m21 = colZ * rowY;
        this.m02 = colX * rowZ;
        this.m12 = colY * rowZ;
        this.m22 = colZ * rowZ;
        this.properties = 0;
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
     * Private column 1 of {@code lookAlong_identity}: computes and stores it. Shared by the
     * identical private paths of {@code lookAlong} and {@code makeRotationLookAlong}; reached only
     * through them.
     */
    private void lookAlong_identity_s7f5de0d0_c1(Float3x3Impl _dst, float _t15, float _t40, float _t16, float _t41, float _t39, float _t17) {
        _dst.m01 = Math.fma(_t15, _t40, -(_t16 * _t41));
        _dst.m11 = Math.fma(_t16, _t39, -(_t17 * _t40));
        _dst.m21 = Math.fma(_t17, _t41, -(_t15 * _t39));
    }

    /**
     * Private tail of {@code lookAlong_identity}. Shared by the identical private paths of
     * {@code lookAlong} and {@code makeRotationLookAlong}; reached only through them.
     */
    private void lookAlong_identity_s7f5de0d0_tail(Float3x3Impl _dst, float dirZ, float _t14, float dirX, float _t31, float _t38, float _t32, float _t33, float _t15) {
        float _t16 = dirZ * _t14;
        float _t17 = dirX * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t32 * _t38;
        float _t41 = _t33 * _t38;
        _dst.m00 = _t39;
        _dst.m10 = _t41;
        _dst.m20 = _t40;
        lookAlong_identity_s7f5de0d0_c1(_dst, _t15, _t40, _t16, _t41, _t39, _t17);
        _dst.m02 = _t17;
        _dst.m12 = _t15;
        _dst.m22 = _t16;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_identity(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        float _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        lookAlong_identity_s7f5de0d0_tail(d, dirZ, _t14, dirX, _t31, (1.0f / (float) Math.sqrt(_t38)), _t32, _t33, dirY * _t14);
        d.properties = 0;
        return d;
    }

    /** Private column 1 of {@code lookAlong_translation}: computes and stores it; reached only through it. */
    private void lookAlong_translation_s7f5de0d0_c1(Float3x3Impl _dst, float _r0, float _t44, float _t16, float _t39, float _t17, float _t41, float _r1, float _t40, float _t15) {
        _dst.m01 = Math.fma(_r0, _t44, Math.fma(_t16, _t39, -(_t17 * _t41)));
        _dst.m11 = Math.fma(_r1, _t44, Math.fma(_t17, _t40, -(_t15 * _t39)));
        _dst.m21 = _t44;
    }

    /** Private tail of {@code lookAlong_translation}; reached only through it. */
    private void lookAlong_translation_s7f5de0d0_tail(Float3x3Impl _dst, float dirX, float _t14, float dirY, float dirZ, float _t31, float _t38, float _t33, float _t32) {
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _t15 = dirX * _t14;
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        _dst.m00 = Math.fma(_r0, _t39, _t40);
        _dst.m10 = Math.fma(_r1, _t39, _t41);
        _dst.m20 = _t39;
        lookAlong_translation_s7f5de0d0_c1(_dst, _r0, Math.fma(_t15, _t41, -(_t16 * _t40)), _t16, _t39, _t17, _t41, _r1, _t40, _t15);
        _dst.m02 = Math.fma(dirX, _t14, _r0 * _t17);
        _dst.m12 = Math.fma(dirY, _t14, _r1 * _t17);
        _dst.m22 = _t17;
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_translation(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        float _t14 = _t7;
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        lookAlong_translation_s7f5de0d0_tail(d, dirX, (1.0f / (float) Math.sqrt(_t14)), dirY, dirZ, _t31, (1.0f / (float) Math.sqrt(_t38)), _t33, _t32);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code lookAlong_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ},
     * {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void lookAlong_orthogonal_s7f5de0d0_c0(Float3x3Impl _dst, float _r0, float _t39, float _r1, float _t40, float _r2, float _t41, float _r3, float _r4, float _r5) {
        _dst.m00 = Math.fma(_r0, _t39, Math.fma(_r1, _t40, _r2 * _t41));
        _dst.m10 = Math.fma(_r3, _t39, Math.fma(_r4, _t40, _r5 * _t41));
        _dst.m20 = _t39;
    }

    /**
     * Private column 1 of {@code lookAlong_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateYXZ},
     * {@code rotateYZX} and {@code rotateZYX}; reached only through them.
     */
    private void lookAlong_orthogonal_s7f5de0d0_c1(Float3x3Impl _dst, float _r0, float _t48, float _r1, float _t49, float _r2, float _t50, float _r3, float _r4, float _r5) {
        _dst.m01 = Math.fma(_r0, _t48, Math.fma(_r1, _t49, _r2 * _t50));
        _dst.m11 = Math.fma(_r3, _t48, Math.fma(_r4, _t49, _r5 * _t50));
        _dst.m21 = _t48;
    }

    /**
     * Private column 2 of {@code lookAlong_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXZY},
     * {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void lookAlong_orthogonal_s7f5de0d0_c2(Float3x3Impl _dst, float _r0, float _t17, float _r1, float _t15, float _r2, float _t16, float _r3, float _r4, float _r5) {
        _dst.m02 = Math.fma(_r0, _t17, Math.fma(_r1, _t15, _r2 * _t16));
        _dst.m12 = Math.fma(_r3, _t17, Math.fma(_r4, _t15, _r5 * _t16));
        _dst.m22 = _t17;
    }

    /** Private tail of {@code lookAlong_orthogonal}; reached only through it. */
    private void lookAlong_orthogonal_s7f5de0d0_tail(Float3x3Impl _dst, float dirX, float _t14, float dirY, float dirZ, float _t31, float _t38, float _t33, float _t32) {
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t15 = dirX * _t14;
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        lookAlong_orthogonal_s7f5de0d0_c0(_dst, _r0, _t39, _r1, _t40, _r2, _t41, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c1(_dst, _r0, Math.fma(_t15, _t41, -(_t16 * _t40)), _r1, Math.fma(_t16, _t39, -(_t17 * _t41)), _r2, Math.fma(_t17, _t40, -(_t15 * _t39)), _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c2(_dst, _r0, _t17, _r1, _t15, _r2, _t16, _r3, _r4, _r5);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_orthogonal(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        float _t14 = _t7;
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        lookAlong_orthogonal_s7f5de0d0_tail(d, dirX, (1.0f / (float) Math.sqrt(_t14)), dirY, dirZ, _t31, (1.0f / (float) Math.sqrt(_t38)), _t33, _t32);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code lookAlong_general}; reached only through it. */
    private void lookAlong_general_s7f5de0d0_tail(Float3x3Impl _dst, float dirX, float _t14, float dirY, float dirZ, float _t31, float _t38, float _t33, float _t32) {
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t15 = dirX * _t14;
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        lookAlong_general_s7f5de0d0_tail2(_dst, _t17, _t40, _t15, _t39, _r0, _r1, _r2, _t41, Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), _t16, _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong_general}; reached only through it. */
    private void lookAlong_general_s7f5de0d0_tail2(Float3x3Impl _dst, float _t17, float _t40, float _t15, float _t39, float _r0, float _r1, float _r2, float _t41, float _t48, float _t49, float _t16, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, _t39, _r1, _t40, _r2, _t41, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, _t48, _r1, _t49, _r2, Math.fma(_t17, _t40, -(_t15 * _t39)), _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c2(_dst, _r0, _t17, _r1, _t15, _r2, _t16, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_general(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        float _t14 = _t7;
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        lookAlong_general_s7f5de0d0_tail(d, dirX, (1.0f / (float) Math.sqrt(_t14)), dirY, dirZ, _t31, (1.0f / (float) Math.sqrt(_t38)), _t33, _t32);
        d.properties = 0;
        return d;
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
     * Private column 0 of {@code lookAlong}: computes and stores it. Shared by the identical
     * private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}
     * and {@code rotateYXZ}; reached only through them.
     */
    private void lookAlong_s69567f05_c0(Double3x3Impl _dst, float _r0, float _t39, float _r1, float _t40, float _r2, float _t41, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m00 = Math.fma(_r0, _t39, Math.fma(_r1, _t40, _r2 * _t41));
        _dst.m10 = Math.fma(_r3, _t39, Math.fma(_r4, _t40, _r5 * _t41));
        _dst.m20 = Math.fma(_r6, _t39, Math.fma(_r7, _t40, _r8 * _t41));
    }

    /**
     * Private column 1 of {@code lookAlong}: computes and stores it. Shared by the identical
     * private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateYXZ}, {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void lookAlong_s69567f05_c1(Double3x3Impl _dst, float _r0, float _t48, float _r1, float _t49, float _r2, float _t50, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m01 = Math.fma(_r0, _t48, Math.fma(_r1, _t49, _r2 * _t50));
        _dst.m11 = Math.fma(_r3, _t48, Math.fma(_r4, _t49, _r5 * _t50));
        _dst.m21 = Math.fma(_r6, _t48, Math.fma(_r7, _t49, _r8 * _t50));
    }

    /**
     * Private column 2 of {@code lookAlong}: computes and stores it. Shared by the identical
     * private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXZY}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private void lookAlong_s69567f05_c2(Double3x3Impl _dst, float _r0, float _t17, float _r1, float _t15, float _r2, float _t16, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m02 = Math.fma(_r0, _t17, Math.fma(_r1, _t15, _r2 * _t16));
        _dst.m12 = Math.fma(_r3, _t17, Math.fma(_r4, _t15, _r5 * _t16));
        _dst.m22 = Math.fma(_r6, _t17, Math.fma(_r7, _t15, _r8 * _t16));
    }

    /** Private tail of {@code lookAlong}; reached only through it. */
    private void lookAlong_s69567f05_tail(Double3x3Impl _dst, float dirX, float _t14, float dirY, float dirZ, float _t31, float _t38, float _t33, float _t32) {
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t15 = dirX * _t14;
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        lookAlong_s69567f05_tail2(_dst, _t17, _t40, _t15, _t39, _r0, _r1, _r2, _t41, Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), _t16, _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong}; reached only through it. */
    private void lookAlong_s69567f05_tail2(Double3x3Impl _dst, float _t17, float _t40, float _t15, float _t39, float _r0, float _r1, float _r2, float _t41, float _t48, float _t49, float _t16, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, _t39, _r1, _t40, _r2, _t41, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, _t48, _r1, _t49, _r2, Math.fma(_t17, _t40, -(_t15 * _t39)), _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c2(_dst, _r0, _t17, _r1, _t15, _r2, _t16, _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        float _t14 = _t7;
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        lookAlong_s69567f05_tail(d, dirX, (1.0f / (float) Math.sqrt(_t14)), dirY, dirZ, _t31, (1.0f / (float) Math.sqrt(_t38)), _t33, _t32);
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Float3x3 lookAlong_degenerate(Float3R dir, Float3R up, @Mutated Float3x3 dest) {
        return lookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Double3x3 lookAlong_degenerate(Float3R dir, Float3R up, @Mutated Double3x3 dest) {
        return lookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private @Mutated Float3x3 lookAlong_degenerate(Float3R dir, Float3R up) {
        return lookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s7f5de0d0_tail(Float3x3Impl _dst, float _t16, float upZ, float _t1, float upX, float upY, float _t17, float _t8, float _t9, float _t10, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
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
        lookAlong_degenerate_s7f5de0d0_tail2(_dst, _t21, _t24, _t25, _t22, _t26, _t23, Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f, _t34, _t35, _t39, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s7f5de0d0_tail2(Float3x3Impl _dst, float _t21, float _t24, float _t25, float _t22, float _t26, float _t23, float _t38, float _t34, float _t35, float _t39, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t42 = Math.fma(_t41, _t25, _t22);
        float _t43 = Math.fma(_t41, _t26, _t23);
        float _t44 = Math.fma(_t41, _t24, _t21);
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= _t38) {
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
        lookAlong_degenerate_s7f5de0d0_tail3(_dst, _t65, _t26, _t67, _t24, _t25, _t66, _r0, _r1, _r2, Math.fma(_t66, _t24, -(_t65 * _t25)), _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s7f5de0d0_tail3(Float3x3Impl _dst, float _t65, float _t26, float _t67, float _t24, float _t25, float _t66, float _r0, float _r1, float _r2, float _t74, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, _t65, _r1, _t66, _r2, _t67, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, Math.fma(_t67, _t25, -(_t66 * _t26)), _r1, Math.fma(_t65, _t26, -(_t67 * _t24)), _r2, _t74, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c2(_dst, _r0, _t24, _r1, _t25, _r2, _t26, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Float3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        lookAlong_degenerate_s7f5de0d0_tail(d, _t16, upZ, unitScale(upX, upY, upZ), upX, upY, (1.0f / (float) Math.sqrt(_t16)), _t8, _t9, _t10, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    @Mutated private Float3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.float3x3() : this);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s69567f05_tail(Double3x3Impl _dst, float _t16, float upZ, float _t1, float upX, float upY, float _t17, float _t8, float _t9, float _t10, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
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
        lookAlong_degenerate_s69567f05_tail2(_dst, _t21, _t24, _t25, _t22, _t26, _t23, Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f, _t34, _t35, _t39, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s69567f05_tail2(Double3x3Impl _dst, float _t21, float _t24, float _t25, float _t22, float _t26, float _t23, float _t38, float _t34, float _t35, float _t39, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t42 = Math.fma(_t41, _t25, _t22);
        float _t43 = Math.fma(_t41, _t26, _t23);
        float _t44 = Math.fma(_t41, _t24, _t21);
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= _t38) {
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
        lookAlong_degenerate_s69567f05_tail3(_dst, _t65, _t26, _t67, _t24, _t25, _t66, _r0, _r1, _r2, Math.fma(_t66, _t24, -(_t65 * _t25)), _r3, _r4, _r5, _r6, _r7, _r8);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private void lookAlong_degenerate_s69567f05_tail3(Double3x3Impl _dst, float _t65, float _t26, float _t67, float _t24, float _t25, float _t66, float _r0, float _r1, float _r2, float _t74, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, _t65, _r1, _t66, _r2, _t67, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, Math.fma(_t67, _t25, -(_t66 * _t26)), _r1, Math.fma(_t65, _t26, -(_t67 * _t24)), _r2, _t74, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c2(_dst, _r0, _t24, _r1, _t25, _r2, _t26, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Double3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        lookAlong_degenerate_s69567f05_tail(d, _t16, upZ, unitScale(upX, upY, upZ), upX, upY, (1.0f / (float) Math.sqrt(_t16)), _t8, _t9, _t10, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        this.m00 = Math.fma(-2.0f, _t0, _t6);
        this.m10 = 2.0f * Math.fma(dqRX, dqRY, _t2);
        this.m20 = Math.fma(-2.0f, _t3, _sp0 * dqRZ);
        this.m01 = Math.fma(-2.0f, _t2, _sp0 * dqRY);
        this.m11 = Math.fma(-2.0f, _t4, _t6);
        this.m21 = 2.0f * Math.fma(dqRX, dqRW, _t5);
        this.m02 = 2.0f * Math.fma(dqRX, dqRZ, _t3);
        this.m12 = Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5);
        this.m22 = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        this.m00 = _t1;
        this.m10 = _t0;
        this.m20 = 0.0f;
        this.m01 = -_t0;
        this.m11 = _t1;
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_ORTHOGONAL;
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
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        this.m00 = Math.fma(_t5, axisX * axisX, _t1);
        this.m10 = Math.fma(axisZ, _t0, _t5 * _t2);
        this.m20 = Math.fma(_t5, _t3, -(axisY * _t0));
        this.m01 = Math.fma(_t5, _t2, -(axisZ * _t0));
        this.m11 = Math.fma(_t5, axisY * axisY, _t1);
        this.m21 = Math.fma(axisX, _t0, _t5 * _t4);
        this.m02 = Math.fma(axisY, _t0, _t5 * _t3);
        this.m12 = Math.fma(_t5, _t4, -(axisX * _t0));
        this.m22 = Math.fma(_t5, axisZ * axisZ, _t1);
        this.properties = 0;
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
        Float3x3Impl d = this;
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
        float _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        lookAlong_identity_s7f5de0d0_tail(d, dirZ, _t14, dirX, _t31, (1.0f / (float) Math.sqrt(_t38)), _t32, _t33, dirY * _t14);
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private @Mutated Float3x3 makeRotationLookAlong_degenerate(Float3R dir, Float3R up) {
        return makeRotationLookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private column 1 of {@code makeRotationLookAlong_degenerate}: computes and stores it; reached only through it. */
    private void makeRotationLookAlong_degenerate_s524747ee_c1(Float3x3Impl _dst, float _t65, float _t26, float _t67, float _t24, float _t66, float _t25) {
        _dst.m01 = Math.fma(_t65, _t26, -(_t67 * _t24));
        _dst.m11 = Math.fma(_t66, _t24, -(_t65 * _t25));
        _dst.m21 = Math.fma(_t67, _t25, -(_t66 * _t26));
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private void makeRotationLookAlong_degenerate_s524747ee_tail(Float3x3Impl _dst, float _t21, float _t22, float _t23, float _t27, float _t28, float _t25, float _t24, float _t26, float _t34, float _t35) {
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t42 = Math.fma(_t41, _t25, _t22);
        float _t43 = Math.fma(_t41, _t26, _t23);
        float _t44 = Math.fma(_t41, _t24, _t21);
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        makeRotationLookAlong_degenerate_s524747ee_tail2(_dst, Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55)), Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f, _t34, _t35, _t27 > _t28 ? _t25 : -_t24, _t53, _t55, _t54, _t26, _t24, _t25);
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private void makeRotationLookAlong_degenerate_s524747ee_tail2(Float3x3Impl _dst, float _t59, float _t38, float _t34, float _t35, float _t39, float _t53, float _t55, float _t54, float _t26, float _t24, float _t25) {
        float _t64, _t65, _t66, _t67;
        if (_t59 <= _t38) {
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
        _dst.m00 = _t66;
        _dst.m10 = _t67;
        _dst.m20 = _t65;
        makeRotationLookAlong_degenerate_s524747ee_c1(_dst, _t65, _t26, _t67, _t24, _t66, _t25);
        _dst.m02 = _t25;
        _dst.m12 = _t26;
        _dst.m22 = _t24;
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private Float3x3 makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        Float3x3Impl d = this;
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
        float _t27 = Math.abs(_t25);
        float _t28 = Math.abs(_t24);
        float _t34, _t35;
        if (_t27 > _t28) {
            _t34 = 0.0f;
            _t35 = -_t26;
        } else {
            _t34 = _t26;
            _t35 = 0.0f;
        }
        makeRotationLookAlong_degenerate_s524747ee_tail(d, _t21, _t22, _t23, _t27, _t28, _t25, _t24, _t26, _t34, _t35);
        d.properties = 0;
        return d;
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
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        this.m00 = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        this.m10 = 2.0f * Math.fma(qX, qY, _t1);
        this.m20 = 2.0f * Math.fma(qX, qZ, -_t2);
        this.m01 = 2.0f * Math.fma(qX, qY, -_t1);
        this.m11 = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        this.m21 = 2.0f * Math.fma(qX, qW, qY * qZ);
        this.m02 = 2.0f * Math.fma(qX, qZ, _t2);
        this.m12 = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        this.m22 = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = _t1;
        this.m21 = _t0;
        this.m02 = 0.0f;
        this.m12 = -_t0;
        this.m22 = _t1;
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        this.m00 = _t3 * _t4;
        this.m10 = Math.fma(_t6, _t4, _t1 * _t5);
        this.m20 = Math.fma(_t2, _t1, -(_t7 * _t4));
        this.m01 = -(_t1 * _t3);
        this.m11 = Math.fma(_t5, _t4, -(_t6 * _t1));
        this.m21 = Math.fma(_t7, _t1, _t2 * _t4);
        this.m02 = _t0;
        this.m12 = -(_t2 * _t3);
        this.m22 = _t5 * _t3;
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        this.m00 = _t3 * _t4;
        this.m10 = Math.fma(_t7, _t3, _t2 * _t0);
        this.m20 = Math.fma(_t6, _t3, -(_t0 * _t5));
        this.m01 = -_t1;
        this.m11 = _t5 * _t4;
        this.m21 = _t2 * _t4;
        this.m02 = _t0 * _t4;
        this.m12 = Math.fma(_t7, _t0, -(_t2 * _t3));
        this.m22 = Math.fma(_t6, _t0, _t5 * _t3);
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        this.m00 = _t1;
        this.m10 = 0.0f;
        this.m20 = -_t0;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m21 = 0.0f;
        this.m02 = _t0;
        this.m12 = 0.0f;
        this.m22 = _t1;
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        this.m00 = Math.fma(_t6, _t2, _t3 * _t4);
        this.m10 = _t2 * _t5;
        this.m20 = Math.fma(_t7, _t2, -(_t1 * _t4));
        this.m01 = Math.fma(_t6, _t4, -(_t2 * _t3));
        this.m11 = _t5 * _t4;
        this.m21 = Math.fma(_t7, _t4, _t1 * _t2);
        this.m02 = _t1 * _t5;
        this.m12 = -_t0;
        this.m22 = _t5 * _t3;
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        this.m00 = _t3 * _t4;
        this.m10 = _t1;
        this.m20 = -(_t0 * _t4);
        this.m01 = Math.fma(_t2, _t0, -(_t7 * _t5));
        this.m11 = _t5 * _t4;
        this.m21 = Math.fma(_t6, _t5, _t2 * _t3);
        this.m02 = Math.fma(_t7, _t2, _t0 * _t5);
        this.m12 = -(_t2 * _t4);
        this.m22 = Math.fma(_t5, _t3, -(_t6 * _t2));
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        this.m00 = _t1;
        this.m10 = _t0;
        this.m20 = 0.0f;
        this.m01 = -_t0;
        this.m11 = _t1;
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_ORTHOGONAL;
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
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        this.m00 = Math.fma(_t3, _t4, -(_t6 * _t0));
        this.m10 = Math.fma(_t7, _t0, _t1 * _t3);
        this.m20 = -(_t0 * _t5);
        this.m01 = -(_t1 * _t5);
        this.m11 = _t5 * _t4;
        this.m21 = _t2;
        this.m02 = Math.fma(_t6, _t3, _t0 * _t4);
        this.m12 = Math.fma(_t0, _t1, -(_t7 * _t3));
        this.m22 = _t5 * _t3;
        this.properties = 0;
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
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        this.m00 = _t3 * _t4;
        this.m10 = _t1 * _t3;
        this.m20 = -_t0;
        this.m01 = Math.fma(_t7, _t2, -(_t1 * _t5));
        this.m11 = Math.fma(_t6, _t2, _t5 * _t4);
        this.m21 = _t2 * _t3;
        this.m02 = Math.fma(_t7, _t5, _t2 * _t1);
        this.m12 = Math.fma(_t6, _t5, -(_t2 * _t4));
        this.m22 = _t5 * _t3;
        this.properties = 0;
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
        this.m00 = vX;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = vY;
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_AFFINE;
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
        this.m00 = s;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = s;
        this.m21 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_AFFINE;
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
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m21 = 0.0f;
        this.m02 = vX;
        this.m12 = vY;
        this.m22 = 1.0f;
        this.properties = Joml.BIT_TRANSLATION;
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
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        this.m00 = _t0_inv + _t0_inv;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = _t1_inv + _t1_inv;
        this.m21 = 0.0f;
        this.m02 = -((left + right) * _t0_inv);
        this.m12 = -((bottom + top) * _t1_inv);
        this.m22 = 1.0f;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_orthogonal_affine(float angle, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        d.m20 = 0.0f;
        float _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        d.m21 = 0.0f;
        float _buf2 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_orthogonal_affine_self(float angle, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        float _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        float _buf2 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Float3x3 preRotate_identity(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Float3x3 preRotate_identity_self(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_translation(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.m21 = 0.0f;
        float _buf0 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m22 = 1.0f;
        d.m02 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_translation_self(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        float _buf0 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m02 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_general(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        d.m20 = this.m20;
        float _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        d.m21 = this.m21;
        float _buf2 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        d.m20 = this.m20;
        float _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        d.m21 = this.m21;
        float _buf2 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(this.m00, _t2, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t2);
        d.m20 = 0.0f;
        float _buf1 = Math.fma(this.m01, _t2, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t2);
        d.m21 = 0.0f;
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_orthogonal_affine_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(this.m00, _t2, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t2);
        float _buf1 = Math.fma(this.m01, _t2, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t2);
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float3x3 preRotateAround_identity(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, _t5, pivotY * _t0);
        d.m12 = Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotateAround} and
     * {@code rotateAround}; reached only through them.
     */
    private Float3x3 preRotateAround_identity_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m02 = Math.fma(pivotX, _t5, pivotY * _t0);
        d.m12 = Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m21 = 0.0f;
        float _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m22 = 1.0f;
        d.m02 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_translation_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        float _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m02 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(this.m20, _t9, Math.fma(this.m00, _t3, -(this.m10 * _t0)));
        d.m10 = Math.fma(this.m20, _t10, Math.fma(this.m00, _t0, this.m10 * _t3));
        d.m20 = this.m20;
        float _buf1 = Math.fma(this.m21, _t9, Math.fma(this.m01, _t3, -(this.m11 * _t0)));
        d.m11 = Math.fma(this.m21, _t10, Math.fma(this.m01, _t0, this.m11 * _t3));
        d.m21 = this.m21;
        float _buf2 = Math.fma(this.m22, _t9, Math.fma(this.m02, _t3, -(this.m12 * _t0)));
        d.m12 = Math.fma(this.m22, _t10, Math.fma(this.m02, _t0, this.m12 * _t3));
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(this.m20, _t9, Math.fma(this.m00, _t3, -(this.m10 * _t0)));
        d.m10 = Math.fma(this.m20, _t10, Math.fma(this.m00, _t0, this.m10 * _t3));
        d.m20 = this.m20;
        float _buf1 = Math.fma(this.m21, _t9, Math.fma(this.m01, _t3, -(this.m11 * _t0)));
        d.m11 = Math.fma(this.m21, _t10, Math.fma(this.m01, _t0, this.m11 * _t3));
        d.m21 = this.m21;
        float _buf2 = Math.fma(this.m22, _t9, Math.fma(this.m02, _t3, -(this.m12 * _t0)));
        d.m12 = Math.fma(this.m22, _t10, Math.fma(this.m02, _t0, this.m12 * _t3));
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        d.m00 = Math.fma(_t5, axisX * axisX, _t1);
        d.m10 = Math.fma(axisZ, _t0, _t5 * _t2);
        d.m20 = Math.fma(_t5, _t3, -(axisY * _t0));
        d.m01 = Math.fma(_t5, _t2, -(axisZ * _t0));
        d.m11 = Math.fma(_t5, axisY * axisY, _t1);
        d.m21 = Math.fma(axisX, _t0, _t5 * _t4);
        d.m02 = Math.fma(axisY, _t0, _t5 * _t3);
        d.m12 = Math.fma(_t5, _t4, -(axisX * _t0));
        d.m22 = Math.fma(_t5, axisZ * axisZ, _t1);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_translation(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = _t14;
        d.m10 = _t16;
        d.m20 = _t19;
        d.m01 = _t18;
        d.m11 = _t15;
        d.m21 = _t17;
        float _buf0 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(this.m02, _t14, this.m12 * _t18);
        float _buf1 = Math.fma(this.m02, _t16, this.m12 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        d.m22 = Math.fma(this.m02, _t19, Math.fma(this.m12, _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
        d.m02 = _buf0;
        d.m12 = _buf1;
        d.properties = 0;
        return d;
    }

    /** Private column 2 of {@code preRotateAxis_orthogonal}: computes and stores it; reached only through it. */
    private void preRotateAxis_orthogonal_s31397653_c2(Float3x3Impl _dst, float axisY, float _t0, float _t9, float _t5, float _r4, float _t14, float _r5, float _t18, float _t16, float _t15, float _t7, float axisX, float _t19, float _t17, float axisZ, float _t1) {
        _dst.m02 = Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(_r4, _t14, _r5 * _t18);
        _dst.m12 = Math.fma(_r4, _t16, _r5 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0));
        _dst.m22 = Math.fma(_r4, _t19, Math.fma(_r5, _t17, Math.fma(_t9, axisZ * axisZ, _t1)));
    }

    /** Private tail of {@code preRotateAxis_orthogonal}; reached only through it. */
    private void preRotateAxis_orthogonal_s31397653_tail(Float3x3Impl _dst, float _t9, float _t5, float axisY, float _t0, float _r0, float _t14, float _r1, float _t18, float _r2, float _r3, float _r4, float _r5, float _t16, float _t15, float _t7, float axisX, float _t17, float axisZ, float _t1) {
        float _t19 = Math.fma(_t9, _t5, -(axisY * _t0));
        mul_general_affine_s2e3258fe_c0(_dst, _r0, _t14, _r1, _t18, _t16, _t15, _t19, _t17);
        mul_general_affine_s2e3258fe_c1(_dst, _r2, _t14, _r3, _t18, _t16, _t15, _t19, _t17);
        preRotateAxis_orthogonal_s31397653_c2(_dst, axisY, _t0, _t9, _t5, _r4, _t14, _r5, _t18, _t16, _t15, _t7, axisX, _t19, _t17, axisZ, _t1);
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m00;
        float _r1 = this.m10;
        float _r2 = this.m01;
        float _r3 = this.m11;
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t9 = 1.0f - _t1;
        preRotateAxis_orthogonal_s31397653_tail(d, _t9, axisX * axisZ, axisY, _t0, _r0, Math.fma(_t9, axisX * axisX, _t1), _r1, Math.fma(_t9, _t3, -(axisZ * _t0)), _r2, _r3, _r4, _r5, Math.fma(axisZ, _t0, _t9 * _t3), Math.fma(_t9, axisY * axisY, _t1), _t7, axisX, Math.fma(axisX, _t0, _t9 * _t7), axisZ, _t1);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code preRotateAxis_general}; reached only through it. */
    private void preRotateAxis_general_s31397653_tail(Float3x3Impl _dst, float _t11, float _t4, float axisZ, float _t0, float _t6, float axisX, float _t2, float axisY, float _r0, float _t21, float _r1, float _t18, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _t22, float _t19, float _t20, float _t23) {
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        float _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        float _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        mul_general_s2e3258fe_c0(_dst, _r0, _t21, _r1, _t18, _r2, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        mul_general_s2e3258fe_c1(_dst, _r3, _t21, _r4, _t18, _r5, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        mul_general_s2e3258fe_c2(_dst, _r6, _t21, _r7, _t18, _r8, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_general(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        preRotateAxis_general_s31397653_tail(d, _t11, _t4, axisZ, _t0, _t6, axisX, _t2, axisY, _r0, Math.fma(axisY, _t0, _t11 * _t2), _r1, Math.fma(_t11, axisX * axisX, _t1), _r2, _r3, _r4, _r5, _r6, _r7, _r8, Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisX, _t0, _t11 * _t6));
        d.properties = 0;
        return d;
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

    /** Private column 0 of {@code preRotateAxis}: computes and stores it; reached only through it. */
    private void preRotateAxis_s72ed99e2_c0(Double3x3Impl _dst, float _r0, float _t21, float _r1, float _t18, float _r2, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        _dst.m00 = Math.fma(_r0, _t21, Math.fma(_r1, _t18, _r2 * _t24));
        _dst.m10 = Math.fma(_r0, _t25, Math.fma(_r1, _t22, _r2 * _t19));
        _dst.m20 = Math.fma(_r0, _t20, Math.fma(_r1, _t26, _r2 * _t23));
    }

    /** Private column 1 of {@code preRotateAxis}: computes and stores it; reached only through it. */
    private void preRotateAxis_s72ed99e2_c1(Double3x3Impl _dst, float _r3, float _t21, float _r4, float _t18, float _r5, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        _dst.m01 = Math.fma(_r3, _t21, Math.fma(_r4, _t18, _r5 * _t24));
        _dst.m11 = Math.fma(_r3, _t25, Math.fma(_r4, _t22, _r5 * _t19));
        _dst.m21 = Math.fma(_r3, _t20, Math.fma(_r4, _t26, _r5 * _t23));
    }

    /** Private column 2 of {@code preRotateAxis}: computes and stores it; reached only through it. */
    private void preRotateAxis_s72ed99e2_c2(Double3x3Impl _dst, float _r6, float _t21, float _r7, float _t18, float _r8, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        _dst.m02 = Math.fma(_r6, _t21, Math.fma(_r7, _t18, _r8 * _t24));
        _dst.m12 = Math.fma(_r6, _t25, Math.fma(_r7, _t22, _r8 * _t19));
        _dst.m22 = Math.fma(_r6, _t20, Math.fma(_r7, _t26, _r8 * _t23));
    }

    /** Private tail of {@code preRotateAxis}; reached only through it. */
    private void preRotateAxis_s72ed99e2_tail(Double3x3Impl _dst, float _t11, float _t4, float axisZ, float _t0, float _t6, float axisX, float _t2, float axisY, float _r0, float _t21, float _r1, float _t18, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _t22, float _t19, float _t20, float _t23) {
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        float _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        float _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        preRotateAxis_s72ed99e2_c0(_dst, _r0, _t21, _r1, _t18, _r2, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        preRotateAxis_s72ed99e2_c1(_dst, _r3, _t21, _r4, _t18, _r5, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        preRotateAxis_s72ed99e2_c2(_dst, _r6, _t21, _r7, _t18, _r8, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m20;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m21;
        float _r4 = this.m01;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m02;
        float _r8 = this.m12;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        preRotateAxis_s72ed99e2_tail(d, _t11, _t4, axisZ, _t0, _t6, axisX, _t2, axisY, _r0, Math.fma(axisY, _t0, _t11 * _t2), _r1, Math.fma(_t11, axisX * axisX, _t1), _r2, _r3, _r4, _r5, _r6, _r7, _r8, Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisX, _t0, _t11 * _t6));
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = this.m00;
        float _buf0 = Math.fma(this.m10, _t1, -(this.m20 * _t0));
        d.m20 = Math.fma(this.m10, _t0, this.m20 * _t1);
        d.m01 = this.m01;
        float _buf1 = Math.fma(this.m11, _t1, -(this.m21 * _t0));
        d.m21 = Math.fma(this.m11, _t0, this.m21 * _t1);
        d.m02 = this.m02;
        float _buf2 = Math.fma(this.m12, _t1, -(this.m22 * _t0));
        d.m22 = Math.fma(this.m12, _t0, this.m22 * _t1);
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m12 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = this.m00;
        float _buf0 = Math.fma(this.m10, _t1, -(this.m20 * _t0));
        d.m20 = Math.fma(this.m10, _t0, this.m20 * _t1);
        d.m01 = this.m01;
        float _buf1 = Math.fma(this.m11, _t1, -(this.m21 * _t0));
        d.m21 = Math.fma(this.m11, _t0, this.m21 * _t1);
        d.m02 = this.m02;
        float _buf2 = Math.fma(this.m12, _t1, -(this.m22 * _t0));
        d.m22 = Math.fma(this.m12, _t0, this.m22 * _t1);
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m12 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m20 * _t0);
        d.m10 = this.m10;
        d.m20 = Math.fma(this.m20, _t1, -(this.m00 * _t0));
        float _buf1 = Math.fma(this.m01, _t1, this.m21 * _t0);
        d.m11 = this.m11;
        d.m21 = Math.fma(this.m21, _t1, -(this.m01 * _t0));
        float _buf2 = Math.fma(this.m02, _t1, this.m22 * _t0);
        d.m12 = this.m12;
        d.m22 = Math.fma(this.m22, _t1, -(this.m02 * _t0));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m20 * _t0);
        d.m10 = this.m10;
        d.m20 = Math.fma(this.m20, _t1, -(this.m00 * _t0));
        float _buf1 = Math.fma(this.m01, _t1, this.m21 * _t0);
        d.m11 = this.m11;
        d.m21 = Math.fma(this.m21, _t1, -(this.m01 * _t0));
        float _buf2 = Math.fma(this.m02, _t1, this.m22 * _t0);
        d.m12 = this.m12;
        d.m22 = Math.fma(this.m22, _t1, -(this.m02 * _t0));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m21 = 0.0f;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = vX;
        d.m11 = vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m20 = 0.0f;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m21 = 0.0f;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal_self(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m20 = this.m20;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m21 = this.m21;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m20 = this.m20;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m21 = this.m21;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_identity(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_translation_self(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s;
        d.m11 = s;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = 0.0f;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = 0.0f;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal_self(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = this.m20;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = this.m21;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = s;
            d.m11 = s;
            d.properties = Joml.BIT_AFFINE;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = this.m20;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = this.m21;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = pivotX * _t0;
        d.m12 = pivotY * _t0;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = pivotX * _t0;
        d.m12 = pivotY * _t0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = 0.0f;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        d.m00 = Math.fma(s, this.m00, this.m20 * _t1);
        d.m10 = Math.fma(s, this.m10, this.m20 * _t2);
        d.m20 = this.m20;
        d.m01 = Math.fma(s, this.m01, this.m21 * _t1);
        d.m11 = Math.fma(s, this.m11, this.m21 * _t2);
        d.m21 = this.m21;
        d.m02 = Math.fma(s, this.m02, this.m22 * _t1);
        d.m12 = Math.fma(s, this.m12, this.m22 * _t2);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        d.m00 = Math.fma(s, this.m00, this.m20 * _t1);
        d.m10 = Math.fma(s, this.m10, this.m20 * _t2);
        d.m20 = this.m20;
        d.m01 = Math.fma(s, this.m01, this.m21 * _t1);
        d.m11 = Math.fma(s, this.m11, this.m21 * _t2);
        d.m21 = this.m21;
        d.m02 = Math.fma(s, this.m02, this.m22 * _t1);
        d.m12 = Math.fma(s, this.m12, this.m22 * _t2);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = sY;
        d.m21 = 0.0f;
        d.m02 = pivotX * (1.0f - sX);
        d.m12 = pivotY * (1.0f - sY);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_identity_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = pivotX * (1.0f - sX);
        d.m12 = pivotY * (1.0f - sY);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = sY;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, 1.0f - sX, sX * this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, sY * this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = Math.fma(pivotX, 1.0f - sX, sX * this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, sY * this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX * this.m00;
        d.m10 = sY * this.m10;
        d.m20 = 0.0f;
        d.m01 = sX * this.m01;
        d.m11 = sY * this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, 1.0f - sX, sX * this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, sY * this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX * this.m00;
        d.m10 = sY * this.m10;
        d.m01 = sX * this.m01;
        d.m11 = sY * this.m11;
        d.m02 = Math.fma(pivotX, 1.0f - sX, sX * this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, sY * this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        d.m00 = Math.fma(sX, this.m00, this.m20 * _t2);
        d.m10 = Math.fma(sY, this.m10, this.m20 * _t3);
        d.m20 = this.m20;
        d.m01 = Math.fma(sX, this.m01, this.m21 * _t2);
        d.m11 = Math.fma(sY, this.m11, this.m21 * _t3);
        d.m21 = this.m21;
        d.m02 = Math.fma(sX, this.m02, this.m22 * _t2);
        d.m12 = Math.fma(sY, this.m12, this.m22 * _t3);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        d.m00 = Math.fma(sX, this.m00, this.m20 * _t2);
        d.m10 = Math.fma(sY, this.m10, this.m20 * _t3);
        d.m20 = this.m20;
        d.m01 = Math.fma(sX, this.m01, this.m21 * _t2);
        d.m11 = Math.fma(sY, this.m11, this.m21 * _t3);
        d.m21 = this.m21;
        d.m02 = Math.fma(sX, this.m02, this.m22 * _t2);
        d.m12 = Math.fma(sY, this.m12, this.m22 * _t3);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_orthogonal_affine_self(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = _props;
        return d;
    }




    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preTranslate} and {@code translate}; reached only through
     * them.
     */
    private Float3x3 preTranslate_translation(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float3x3 preTranslate_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_general(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = Math.fma(this.m20, vX, this.m00);
        d.m10 = Math.fma(this.m20, vY, this.m10);
        d.m20 = this.m20;
        d.m01 = Math.fma(this.m21, vX, this.m01);
        d.m11 = Math.fma(this.m21, vY, this.m11);
        d.m21 = this.m21;
        d.m02 = Math.fma(this.m22, vX, this.m02);
        d.m12 = Math.fma(this.m22, vY, this.m12);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = Math.fma(this.m20, vX, this.m00);
        d.m10 = Math.fma(this.m20, vY, this.m10);
        d.m20 = this.m20;
        d.m01 = Math.fma(this.m21, vX, this.m01);
        d.m11 = Math.fma(this.m21, vY, this.m11);
        d.m21 = this.m21;
        d.m02 = Math.fma(this.m22, vX, this.m02);
        d.m12 = Math.fma(this.m22, vY, this.m12);
        d.m22 = this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_orthogonal_affine(float angle, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        d.m20 = 0.0f;
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_orthogonal_affine_self(float angle, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = _props;
        return d;
    }




    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_translation(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_translation_self(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_general(float angle, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        float _buf2 = Math.fma(this.m20, _t1, this.m21 * _t0);
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m21 = Math.fma(this.m21, _t1, -(this.m20 * _t0));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        float _buf2 = Math.fma(this.m20, _t1, this.m21 * _t0);
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m21 = Math.fma(this.m21, _t1, -(this.m20 * _t0));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(this.m00, _t2, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t2, this.m11 * _t0);
        d.m20 = 0.0f;
        float _buf2 = Math.fma(this.m01, _t2, -(this.m00 * _t0));
        float _buf3 = Math.fma(this.m11, _t2, -(this.m10 * _t0));
        d.m21 = 0.0f;
        d.m02 = Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02));
        d.m12 = Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_orthogonal_affine_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(this.m00, _t2, this.m01 * _t0);
        float _buf1 = Math.fma(this.m10, _t2, this.m11 * _t0);
        float _buf2 = Math.fma(this.m01, _t2, -(this.m00 * _t0));
        float _buf3 = Math.fma(this.m11, _t2, -(this.m10 * _t0));
        d.m02 = Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02));
        d.m12 = Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = _props;
        return d;
    }




    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m20 = 0.0f;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, this.m02));
        d.m12 = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, this.m12));
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateAround}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_translation_self(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m02 = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, this.m02));
        d.m12 = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m00;
        float _r1 = this.m01;
        float _r2 = this.m02;
        float _r3 = this.m10;
        float _r4 = this.m11;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m21;
        float _r8 = this.m22;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        preMul_orthogonal_s36a279f2_c0(d, _r0, _t2, _r1, _t0, _r3, _r4, _r6, _r7);
        preMul_orthogonal_s36a279f2_c1(d, _r1, _t2, _r0, _t0, _r4, _r3, _r7, _r6);
        preMul_orthogonal_s36a279f2_c2(d, _r0, Math.fma(pivotX, _t8, pivotY * _t0), _r1, Math.fma(pivotY, _t8, -(pivotX * _t0)), _r2, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private column 0 of {@code rotateAround}: computes and stores it; reached only through it. */
    private void rotateAround_s10022787_c0(Double3x3Impl _dst, float _r0, float _t2, float _r1, float _t0, float _r3, float _r4, float _r6, float _r7) {
        _dst.m00 = Math.fma(_r0, _t2, _r1 * _t0);
        _dst.m10 = Math.fma(_r3, _t2, _r4 * _t0);
        _dst.m20 = Math.fma(_r6, _t2, _r7 * _t0);
    }

    /** Private column 1 of {@code rotateAround}: computes and stores it; reached only through it. */
    private void rotateAround_s10022787_c1(Double3x3Impl _dst, float _r1, float _t2, float _r0, float _t0, float _r4, float _r3, float _r7, float _r6) {
        _dst.m01 = Math.fma(_r1, _t2, -(_r0 * _t0));
        _dst.m11 = Math.fma(_r4, _t2, -(_r3 * _t0));
        _dst.m21 = Math.fma(_r7, _t2, -(_r6 * _t0));
    }

    /** Private column 2 of {@code rotateAround}: computes and stores it; reached only through it. */
    private void rotateAround_s10022787_c2(Double3x3Impl _dst, float _r0, float _t9, float _r1, float _t10, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m02 = Math.fma(_r0, _t9, Math.fma(_r1, _t10, _r2));
        _dst.m12 = Math.fma(_r3, _t9, Math.fma(_r4, _t10, _r5));
        _dst.m22 = Math.fma(_r6, _t9, Math.fma(_r7, _t10, _r8));
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m00;
        float _r1 = this.m01;
        float _r2 = this.m02;
        float _r3 = this.m10;
        float _r4 = this.m11;
        float _r5 = this.m12;
        float _r6 = this.m20;
        float _r7 = this.m21;
        float _r8 = this.m22;
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        rotateAround_s10022787_c0(d, _r0, _t2, _r1, _t0, _r3, _r4, _r6, _r7);
        rotateAround_s10022787_c1(d, _r1, _t2, _r0, _t0, _r4, _r3, _r7, _r6);
        rotateAround_s10022787_c2(d, _r0, Math.fma(pivotX, _t8, pivotY * _t0), _r1, Math.fma(pivotY, _t8, -(pivotX * _t0)), _r2, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = 1.0f - _t1;
        float _t10 = Math.fma(_t7, axisZ * axisZ, _t1);
        float _t11 = Math.fma(axisX, _t0, _t7 * _t4);
        float _t12 = Math.fma(_t7, _t2, -(axisY * _t0));
        d.m00 = Math.fma(_t7, axisX * axisX, Math.fma(this.m02, _t12, _t1));
        d.m10 = Math.fma(this.m12, _t12, Math.fma(axisZ, _t0, _t7 * _t5));
        d.m20 = _t12;
        d.m01 = Math.fma(this.m02, _t11, Math.fma(_t7, _t5, -(axisZ * _t0)));
        d.m11 = Math.fma(_t7, axisY * axisY, Math.fma(this.m12, _t11, _t1));
        d.m21 = _t11;
        d.m02 = Math.fma(this.m02, _t10, Math.fma(axisY, _t0, _t7 * _t2));
        d.m12 = Math.fma(this.m12, _t10, Math.fma(_t7, _t4, -(axisX * _t0)));
        d.m22 = _t10;
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateAxis_orthogonal}; reached only through it. */
    private void rotateAxis_orthogonal_s31397653_tail(Float3x3Impl _dst, float _t11, float _t6, float axisX, float _t0, float _r0, float _t24, float _r1, float _t18, float _r2, float _t21, float _t22, float _t25, float _t19, float _t20, float _t23, float _r3, float _r4, float _r5) {
        lookAlong_orthogonal_s7f5de0d0_c0(_dst, _r0, _t24, _r1, _t18, _r2, _t21, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c1(_dst, _r0, _t22, _r1, _t25, _r2, _t19, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c2(_dst, _r0, _t20, _r1, _t23, _r2, Math.fma(_t11, _t6, -(axisX * _t0)), _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_orthogonal_s31397653_tail(d, _t11, _t6, axisX, _t0, _r0, Math.fma(_t11, _t2, -(axisY * _t0)), _r1, Math.fma(_t11, axisX * axisX, _t1), _r2, Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateAxis_general}; reached only through it. */
    private void rotateAxis_general_s31397653_tail(Float3x3Impl _dst, float _t11, float _t2, float axisY, float _t0, float _t5, float axisZ, float _t6, float axisX, float _r0, float _r1, float _t18, float _r2, float _t21, float _t22, float _t19, float _t20, float _t23, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, Math.fma(_t11, _t2, -(axisY * _t0)), _r1, _t18, _r2, _t21, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, _t22, _r1, Math.fma(_t11, _t5, -(axisZ * _t0)), _r2, _t19, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c2(_dst, _r0, _t20, _r1, _t23, _r2, Math.fma(_t11, _t6, -(axisX * _t0)), _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_general(float angle, float axisX, float axisY, float axisZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_general_s31397653_tail(d, _t11, _t2, axisY, _t0, _t5, axisZ, _t6, axisX, _r0, _r1, Math.fma(_t11, axisX * axisX, _t1), _r2, Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private void rotateAxis_s72ed99e2_tail(Double3x3Impl _dst, float _t11, float _t2, float axisY, float _t0, float _t5, float axisZ, float _t6, float axisX, float _r0, float _r1, float _t18, float _r2, float _t21, float _t22, float _t19, float _t20, float _t23, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, Math.fma(_t11, _t2, -(axisY * _t0)), _r1, _t18, _r2, _t21, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, _t22, _r1, Math.fma(_t11, _t5, -(axisZ * _t0)), _r2, _t19, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c2(_dst, _r0, _t20, _r1, _t23, _r2, Math.fma(_t11, _t6, -(axisX * _t0)), _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_s72ed99e2_tail(d, _t11, _t2, axisY, _t0, _t5, axisZ, _t6, axisX, _r0, _r1, Math.fma(_t11, axisX * axisX, _t1), _r2, Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = Math.fma(this.m01, _t1, this.m02 * _t0);
        float _buf1 = Math.fma(this.m11, _t1, this.m12 * _t0);
        float _buf2 = Math.fma(this.m21, _t1, this.m22 * _t0);
        d.m02 = Math.fma(this.m02, _t1, -(this.m01 * _t0));
        d.m12 = Math.fma(this.m12, _t1, -(this.m11 * _t0));
        d.m22 = Math.fma(this.m22, _t1, -(this.m21 * _t0));
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = Math.fma(this.m01, _t1, this.m02 * _t0);
        float _buf1 = Math.fma(this.m11, _t1, this.m12 * _t0);
        float _buf2 = Math.fma(this.m21, _t1, this.m22 * _t0);
        d.m02 = Math.fma(this.m02, _t1, -(this.m01 * _t0));
        d.m12 = Math.fma(this.m12, _t1, -(this.m11 * _t0));
        d.m22 = Math.fma(this.m22, _t1, -(this.m21 * _t0));
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = -1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = -1.0f;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m11 = -1.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m10;
        d.m11 = -this.m11;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.m10 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m01 = this.m10;
        d.m11 = -this.m11;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.m10 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m11 = -1.0f;
            d.m22 = -1.0f;
            d.properties = 0;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 0.0f;
        d.m21 = -1.0f;
        d.m02 = 0.0f;
        d.m12 = 1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = -this.m02;
        d.m11 = -this.m12;
        d.m21 = -1.0f;
        d.m02 = 0.0f;
        d.m12 = 1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m01 = -this.m02;
        d.m11 = -this.m12;
        d.m21 = -1.0f;
        d.m02 = 0.0f;
        d.m12 = 1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        d.m21 = -1.0f;
        d.m02 = this.m01;
        d.m12 = this.m11;
        d.m22 = 0.0f;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        d.m21 = -1.0f;
        d.m02 = this.m01;
        d.m12 = this.m11;
        d.m22 = 0.0f;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        float _buf2 = -this.m22;
        d.m02 = this.m01;
        d.m12 = this.m11;
        d.m22 = this.m21;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m11 = 0.0f;
            d.m21 = -1.0f;
            d.m12 = 1.0f;
            d.m22 = 0.0f;
            d.properties = 0;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        float _buf2 = -this.m22;
        d.m02 = this.m01;
        d.m12 = this.m11;
        d.m22 = this.m21;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 0.0f;
        d.m21 = 1.0f;
        d.m02 = 0.0f;
        d.m12 = -1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = this.m02;
        d.m11 = this.m12;
        d.m21 = 1.0f;
        d.m02 = 0.0f;
        d.m12 = -1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m01 = this.m02;
        d.m11 = this.m12;
        d.m21 = 1.0f;
        d.m02 = 0.0f;
        d.m12 = -1.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m02;
        float _buf1 = this.m12;
        d.m21 = 1.0f;
        d.m02 = this.m10;
        d.m12 = -this.m11;
        d.m22 = 0.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m11;
        float _buf0 = this.m10;
        d.m01 = this.m02;
        float _buf1 = this.m12;
        d.m21 = 1.0f;
        d.m02 = this.m10;
        d.m12 = -this.m11;
        d.m22 = 0.0f;
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        d.m21 = 1.0f;
        d.m02 = -this.m01;
        d.m12 = -this.m11;
        d.m22 = 0.0f;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateX90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        d.m21 = 1.0f;
        d.m02 = -this.m01;
        d.m12 = -this.m11;
        d.m22 = 0.0f;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        float _buf2 = this.m22;
        d.m02 = -this.m01;
        d.m12 = -this.m11;
        d.m22 = -this.m21;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m11 = 0.0f;
            d.m21 = 1.0f;
            d.m12 = -1.0f;
            d.m22 = 0.0f;
            d.properties = 0;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        float _buf2 = this.m22;
        d.m02 = -this.m01;
        d.m12 = -this.m11;
        d.m22 = -this.m21;
        d.m01 = _buf0;
        d.m11 = _buf1;
        d.m21 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_identity(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        d.m00 = _t3 * _t4;
        d.m10 = Math.fma(_t6, _t4, _t1 * _t5);
        d.m20 = Math.fma(_t2, _t1, -(_t7 * _t4));
        d.m01 = -(_t1 * _t3);
        d.m11 = Math.fma(_t5, _t4, -(_t6 * _t1));
        d.m21 = Math.fma(_t7, _t1, _t2 * _t4);
        d.m02 = _t0;
        d.m12 = -(_t2 * _t3);
        d.m22 = _t5 * _t3;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_translation(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(this.m02, _t13, _t5 * _t4);
        d.m10 = Math.fma(this.m12, _t13, Math.fma(_t6, _t4, _t1 * _t3));
        d.m20 = _t13;
        d.m01 = Math.fma(this.m02, _t12, -(_t1 * _t5));
        d.m11 = Math.fma(this.m12, _t12, Math.fma(_t3, _t4, -(_t6 * _t1)));
        d.m21 = _t12;
        d.m02 = Math.fma(this.m02, _t9, _t2);
        d.m12 = Math.fma(this.m12, _t9, -(_t0 * _t5));
        d.m22 = _t9;
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code rotateXYZ_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY};
     * reached only through them.
     */
    private void rotateXYZ_orthogonal_s25e2e448_c1(Float3x3Impl _dst, float _r0, float _t19, float _r2, float _t21, float _r1, float _t10, float _r3, float _r5, float _r4) {
        _dst.m01 = Math.fma(_r0, _t19, Math.fma(_r2, _t21, -(_r1 * _t10)));
        _dst.m11 = Math.fma(_r3, _t19, Math.fma(_r5, _t21, -(_r4 * _t10)));
        _dst.m21 = _t19;
    }

    /**
     * Private column 2 of {@code rotateXYZ_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX};
     * reached only through them.
     */
    private void rotateXYZ_orthogonal_s25e2e448_c2(Float3x3Impl _dst, float _r0, float _t15, float _r1, float _t2, float _r2, float _t11, float _r3, float _r4, float _r5) {
        _dst.m02 = Math.fma(_r0, _t15, Math.fma(_r1, _t2, -(_r2 * _t11)));
        _dst.m12 = Math.fma(_r3, _t15, Math.fma(_r4, _t2, -(_r5 * _t11)));
        _dst.m22 = _t15;
    }

    /** Private tail of {@code rotateXYZ_orthogonal}; reached only through it. */
    private void rotateXYZ_orthogonal_s25e2e448_tail(Float3x3Impl _dst, float _t3, float _t4, float _t6, float _t1, float _r0, float _t20, float _r1, float _t13, float _r2, float _t18, float _t19, float _t10, float _t15, float _t2, float _t11, float _r3, float _r4, float _r5) {
        lookAlong_orthogonal_s7f5de0d0_c0(_dst, _r0, _t20, _r1, _t13, _r2, _t18, _r3, _r4, _r5);
        rotateXYZ_orthogonal_s25e2e448_c1(_dst, _r0, _t19, _r2, Math.fma(_t3, _t4, -(_t6 * _t1)), _r1, _t10, _r3, _r5, _r4);
        rotateXYZ_orthogonal_s25e2e448_c2(_dst, _r0, _t15, _r1, _t2, _r2, _t11, _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_orthogonal(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        rotateXYZ_orthogonal_s25e2e448_tail(d, _t3, _t4, _t6, _t1, _r0, Math.fma(_t0, _t1, -(_t7 * _t4)), _r1, _t5 * _t4, _r2, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), _t1 * _t5, _t3 * _t5, _t2, _t0 * _t5, _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 1 of {@code rotateXYZ_general}: computes and stores it. Shared by the
     * identical private paths of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY};
     * reached only through them.
     */
    private void rotateXYZ_general_s25e2e448_c1(Float3x3Impl _dst, float _r0, float _t19, float _r2, float _t21, float _r1, float _t10, float _r3, float _r5, float _r4, float _r6, float _r8, float _r7) {
        _dst.m01 = Math.fma(_r0, _t19, Math.fma(_r2, _t21, -(_r1 * _t10)));
        _dst.m11 = Math.fma(_r3, _t19, Math.fma(_r5, _t21, -(_r4 * _t10)));
        _dst.m21 = Math.fma(_r6, _t19, Math.fma(_r8, _t21, -(_r7 * _t10)));
    }

    /**
     * Private column 2 of {@code rotateXYZ_general}: computes and stores it. Shared by the
     * identical private paths of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX};
     * reached only through them.
     */
    private void rotateXYZ_general_s25e2e448_c2(Float3x3Impl _dst, float _r0, float _t15, float _r1, float _t2, float _r2, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m02 = Math.fma(_r0, _t15, Math.fma(_r1, _t2, -(_r2 * _t11)));
        _dst.m12 = Math.fma(_r3, _t15, Math.fma(_r4, _t2, -(_r5 * _t11)));
        _dst.m22 = Math.fma(_r6, _t15, Math.fma(_r7, _t2, -(_r8 * _t11)));
    }

    /** Private tail of {@code rotateXYZ_general}; reached only through it. */
    private void rotateXYZ_general_s25e2e448_tail(Float3x3Impl _dst, float _t3, float _t4, float _t6, float _t1, float _r0, float _t20, float _r1, float _t13, float _r2, float _t18, float _t19, float _t10, float _t15, float _t2, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, _t20, _r1, _t13, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_general_s25e2e448_c1(_dst, _r0, _t19, _r2, Math.fma(_t3, _t4, -(_t6 * _t1)), _r1, _t10, _r3, _r5, _r4, _r6, _r8, _r7);
        rotateXYZ_general_s25e2e448_c2(_dst, _r0, _t15, _r1, _t2, _r2, _t11, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_general(float angleX, float angleY, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        rotateXYZ_general_s25e2e448_tail(d, _t3, _t4, _t6, _t1, _r0, Math.fma(_t0, _t1, -(_t7 * _t4)), _r1, _t5 * _t4, _r2, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), _t1 * _t5, _t3 * _t5, _t2, _t0 * _t5, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
     * Private column 1 of {@code rotateXYZ}: computes and stores it. Shared by the identical
     * private paths of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY}; reached only
     * through them.
     */
    private void rotateXYZ_s1371ea8d_c1(Double3x3Impl _dst, float _r0, float _t19, float _r2, float _t21, float _r1, float _t10, float _r3, float _r5, float _r4, float _r6, float _r8, float _r7) {
        _dst.m01 = Math.fma(_r0, _t19, Math.fma(_r2, _t21, -(_r1 * _t10)));
        _dst.m11 = Math.fma(_r3, _t19, Math.fma(_r5, _t21, -(_r4 * _t10)));
        _dst.m21 = Math.fma(_r6, _t19, Math.fma(_r8, _t21, -(_r7 * _t10)));
    }

    /**
     * Private column 2 of {@code rotateXYZ}: computes and stores it. Shared by the identical
     * private paths of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX}; reached only
     * through them.
     */
    private void rotateXYZ_s1371ea8d_c2(Double3x3Impl _dst, float _r0, float _t15, float _r1, float _t2, float _r2, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m02 = Math.fma(_r0, _t15, Math.fma(_r1, _t2, -(_r2 * _t11)));
        _dst.m12 = Math.fma(_r3, _t15, Math.fma(_r4, _t2, -(_r5 * _t11)));
        _dst.m22 = Math.fma(_r6, _t15, Math.fma(_r7, _t2, -(_r8 * _t11)));
    }

    /** Private tail of {@code rotateXYZ}; reached only through it. */
    private void rotateXYZ_s1371ea8d_tail(Double3x3Impl _dst, float _t3, float _t4, float _t6, float _t1, float _r0, float _t20, float _r1, float _t13, float _r2, float _t18, float _t19, float _t10, float _t15, float _t2, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, _t20, _r1, _t13, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_s1371ea8d_c1(_dst, _r0, _t19, _r2, Math.fma(_t3, _t4, -(_t6 * _t1)), _r1, _t10, _r3, _r5, _r4, _r6, _r8, _r7);
        rotateXYZ_s1371ea8d_c2(_dst, _r0, _t15, _r1, _t2, _r2, _t11, _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        rotateXYZ_s1371ea8d_tail(d, _t3, _t4, _t6, _t1, _r0, Math.fma(_t0, _t1, -(_t7 * _t4)), _r1, _t5 * _t4, _r2, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), _t1 * _t5, _t3 * _t5, _t2, _t0 * _t5, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_identity(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        d.m00 = _t3 * _t4;
        d.m10 = Math.fma(_t7, _t3, _t2 * _t0);
        d.m20 = Math.fma(_t6, _t3, -(_t0 * _t5));
        d.m01 = -_t1;
        d.m11 = _t5 * _t4;
        d.m21 = _t2 * _t4;
        d.m02 = _t0 * _t4;
        d.m12 = Math.fma(_t7, _t0, -(_t2 * _t3));
        d.m22 = Math.fma(_t6, _t0, _t5 * _t3);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_translation(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(this.m02, _t13, _t3 * _t5);
        d.m10 = Math.fma(this.m12, _t13, Math.fma(_t9, _t3, _t0 * _t2));
        d.m20 = _t13;
        d.m01 = Math.fma(this.m02, _t8, -_t1);
        d.m11 = Math.fma(this.m12, _t8, _t4 * _t5);
        d.m21 = _t8;
        d.m02 = Math.fma(this.m02, _t12, _t2 * _t5);
        d.m12 = Math.fma(this.m12, _t12, Math.fma(_t9, _t2, -(_t0 * _t3)));
        d.m22 = _t12;
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateXZY_orthogonal}; reached only through it. */
    private void rotateXZY_orthogonal_s6ab38e88_tail(Float3x3Impl _dst, float _t9, float _t2, float _t0, float _t3, float _r0, float _t20, float _r1, float _t15, float _r2, float _t18, float _t10, float _t16, float _t1, float _t19, float _t11, float _r3, float _r4, float _r5) {
        lookAlong_orthogonal_s7f5de0d0_c0(_dst, _r0, _t20, _r1, _t15, _r2, _t18, _r3, _r4, _r5);
        rotateXYZ_orthogonal_s25e2e448_c1(_dst, _r0, _t10, _r2, _t16, _r1, _t1, _r3, _r5, _r4);
        lookAlong_orthogonal_s7f5de0d0_c2(_dst, _r0, _t19, _r1, _t11, _r2, Math.fma(_t9, _t2, -(_t0 * _t3)), _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_orthogonal(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateXZY_orthogonal_s6ab38e88_tail(d, _t9, _t2, _t0, _t3, _r0, Math.fma(_t6, _t3, -(_t2 * _t4)), _r1, _t3 * _t5, _r2, Math.fma(_t9, _t3, _t0 * _t2), _t0 * _t5, _t4 * _t5, _t1, Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateXZY_general}; reached only through it. */
    private void rotateXZY_general_s6ab38e88_tail(Float3x3Impl _dst, float _t9, float _t2, float _t0, float _t3, float _r0, float _t20, float _r1, float _t15, float _r2, float _t18, float _t10, float _t16, float _t1, float _t19, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, _t20, _r1, _t15, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_general_s25e2e448_c1(_dst, _r0, _t10, _r2, _t16, _r1, _t1, _r3, _r5, _r4, _r6, _r8, _r7);
        preMul_general_s36a279f2_c2(_dst, _r0, _t19, _r1, _t11, _r2, Math.fma(_t9, _t2, -(_t0 * _t3)), _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_general(float angleX, float angleZ, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateXZY_general_s6ab38e88_tail(d, _t9, _t2, _t0, _t3, _r0, Math.fma(_t6, _t3, -(_t2 * _t4)), _r1, _t3 * _t5, _r2, Math.fma(_t9, _t3, _t0 * _t2), _t0 * _t5, _t4 * _t5, _t1, Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private tail of {@code rotateXZY}; reached only through it. */
    private void rotateXZY_s68b6884d_tail(Double3x3Impl _dst, float _t9, float _t2, float _t0, float _t3, float _r0, float _t20, float _r1, float _t15, float _r2, float _t18, float _t10, float _t16, float _t1, float _t19, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, _t20, _r1, _t15, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_s1371ea8d_c1(_dst, _r0, _t10, _r2, _t16, _r1, _t1, _r3, _r5, _r4, _r6, _r8, _r7);
        lookAlong_s69567f05_c2(_dst, _r0, _t19, _r1, _t11, _r2, Math.fma(_t9, _t2, -(_t0 * _t3)), _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateXZY_s68b6884d_tail(d, _t9, _t2, _t0, _t3, _r0, Math.fma(_t6, _t3, -(_t2 * _t4)), _r1, _t3 * _t5, _r2, Math.fma(_t9, _t3, _t0 * _t2), _t0 * _t5, _t4 * _t5, _t1, Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m02 * _t0));
        float _buf1 = Math.fma(this.m10, _t1, -(this.m12 * _t0));
        float _buf2 = Math.fma(this.m20, _t1, -(this.m22 * _t0));
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = Math.fma(this.m00, _t0, this.m02 * _t1);
        d.m12 = Math.fma(this.m10, _t0, this.m12 * _t1);
        d.m22 = Math.fma(this.m20, _t0, this.m22 * _t1);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(this.m00, _t1, -(this.m02 * _t0));
        float _buf1 = Math.fma(this.m10, _t1, -(this.m12 * _t0));
        float _buf2 = Math.fma(this.m20, _t1, -(this.m22 * _t0));
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = Math.fma(this.m00, _t0, this.m02 * _t1);
        d.m12 = Math.fma(this.m10, _t0, this.m12 * _t1);
        d.m22 = Math.fma(this.m20, _t0, this.m22 * _t1);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -1.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = -1.0f;
            d.m22 = -1.0f;
            d.properties = 0;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = 0.0f;
        d.m20 = 1.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m02;
        d.m10 = this.m12;
        d.m20 = 1.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = -1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m02;
        d.m10 = this.m12;
        d.m20 = 1.0f;
        d.m02 = -1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        d.m20 = 1.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = -this.m00;
        d.m12 = -this.m10;
        d.m22 = 0.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        d.m20 = 1.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = -this.m00;
        d.m12 = -this.m10;
        d.m22 = 0.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        float _buf2 = this.m22;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = -this.m00;
        d.m12 = -this.m10;
        d.m22 = -this.m20;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = 0.0f;
            d.m20 = 1.0f;
            d.m02 = -1.0f;
            d.m22 = 0.0f;
            d.properties = 0;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = this.m02;
        float _buf1 = this.m12;
        float _buf2 = this.m22;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = -this.m00;
        d.m12 = -this.m10;
        d.m22 = -this.m20;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = 0.0f;
        d.m20 = -1.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m02;
        d.m10 = -this.m12;
        d.m20 = -1.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m02;
        d.m10 = -this.m12;
        d.m20 = -1.0f;
        d.m02 = 1.0f;
        d.m12 = 0.0f;
        d.m22 = 0.0f;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        d.m20 = -1.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m00;
        d.m12 = this.m10;
        d.m22 = 0.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateY90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        d.m20 = -1.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m00;
        d.m12 = this.m10;
        d.m22 = 0.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        float _buf2 = -this.m22;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = this.m00;
        d.m12 = this.m10;
        d.m22 = this.m20;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = 0.0f;
            d.m20 = -1.0f;
            d.m02 = 1.0f;
            d.m22 = 0.0f;
            d.properties = 0;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = -this.m02;
        float _buf1 = -this.m12;
        float _buf2 = -this.m22;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m02 = this.m00;
        d.m12 = this.m10;
        d.m22 = this.m20;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_identity(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        d.m00 = Math.fma(_t6, _t2, _t3 * _t4);
        d.m10 = _t2 * _t5;
        d.m20 = Math.fma(_t7, _t2, -(_t1 * _t4));
        d.m01 = Math.fma(_t6, _t4, -(_t2 * _t3));
        d.m11 = _t5 * _t4;
        d.m21 = Math.fma(_t7, _t4, _t1 * _t2);
        d.m02 = _t1 * _t5;
        d.m12 = -_t0;
        d.m22 = _t5 * _t3;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_translation(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(this.m02, _t13, Math.fma(_t6, _t2, _t3 * _t4));
        d.m10 = Math.fma(this.m12, _t13, _t2 * _t5);
        d.m20 = _t13;
        d.m01 = Math.fma(this.m02, _t12, Math.fma(_t6, _t4, -(_t2 * _t3)));
        d.m11 = Math.fma(this.m12, _t12, _t5 * _t4);
        d.m21 = _t12;
        d.m02 = Math.fma(this.m02, _t11, _t1 * _t5);
        d.m12 = Math.fma(this.m12, _t11, -_t0);
        d.m22 = _t11;
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateYXZ_orthogonal}; reached only through it. */
    private void rotateYXZ_orthogonal_s6a1c9e88_tail(Float3x3Impl _dst, float _t6, float _t4, float _t2, float _t3, float _r0, float _t20, float _r1, float _t18, float _r2, float _t10, float _t19, float _t16, float _t17, float _t12, float _t0, float _r3, float _r4, float _r5) {
        lookAlong_orthogonal_s7f5de0d0_c0(_dst, _r0, _t20, _r1, _t18, _r2, _t10, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c1(_dst, _r0, _t19, _r1, Math.fma(_t6, _t4, -(_t2 * _t3)), _r2, _t16, _r3, _r4, _r5);
        rotateXYZ_orthogonal_s25e2e448_c2(_dst, _r0, _t17, _r1, _t12, _r2, _t0, _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_orthogonal(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        rotateYXZ_orthogonal_s6a1c9e88_tail(d, _t6, _t4, _t2, _t3, _r0, Math.fma(_t8, _t2, -(_t1 * _t4)), _r1, Math.fma(_t6, _t2, _t3 * _t4), _r2, _t2 * _t5, Math.fma(_t8, _t4, _t1 * _t2), _t5 * _t4, _t5 * _t3, _t1 * _t5, _t0, _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateYXZ_general}; reached only through it. */
    private void rotateYXZ_general_s6a1c9e88_tail(Float3x3Impl _dst, float _t6, float _t4, float _t2, float _t3, float _r0, float _t20, float _r1, float _t18, float _r2, float _t10, float _t19, float _t16, float _t17, float _t12, float _t0, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        preMul_general_s36a279f2_c0(_dst, _r0, _t20, _r1, _t18, _r2, _t10, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, _t19, _r1, Math.fma(_t6, _t4, -(_t2 * _t3)), _r2, _t16, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_general_s25e2e448_c2(_dst, _r0, _t17, _r1, _t12, _r2, _t0, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_general(float angleY, float angleX, float angleZ, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        rotateYXZ_general_s6a1c9e88_tail(d, _t6, _t4, _t2, _t3, _r0, Math.fma(_t8, _t2, -(_t1 * _t4)), _r1, Math.fma(_t6, _t2, _t3 * _t4), _r2, _t2 * _t5, Math.fma(_t8, _t4, _t1 * _t2), _t5 * _t4, _t5 * _t3, _t1 * _t5, _t0, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private tail of {@code rotateYXZ}; reached only through it. */
    private void rotateYXZ_s566f784d_tail(Double3x3Impl _dst, float _t6, float _t4, float _t2, float _t3, float _r0, float _t20, float _r1, float _t18, float _r2, float _t10, float _t19, float _t16, float _t17, float _t12, float _t0, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        lookAlong_s69567f05_c0(_dst, _r0, _t20, _r1, _t18, _r2, _t10, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, _t19, _r1, Math.fma(_t6, _t4, -(_t2 * _t3)), _r2, _t16, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_s1371ea8d_c2(_dst, _r0, _t17, _r1, _t12, _r2, _t0, _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        rotateYXZ_s566f784d_tail(d, _t6, _t4, _t2, _t3, _r0, Math.fma(_t8, _t2, -(_t1 * _t4)), _r1, Math.fma(_t6, _t2, _t3 * _t4), _r2, _t2 * _t5, Math.fma(_t8, _t4, _t1 * _t2), _t5 * _t4, _t5 * _t3, _t1 * _t5, _t0, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_identity(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        d.m00 = _t3 * _t4;
        d.m10 = _t1;
        d.m20 = -(_t0 * _t4);
        d.m01 = Math.fma(_t2, _t0, -(_t7 * _t5));
        d.m11 = _t5 * _t4;
        d.m21 = Math.fma(_t6, _t5, _t2 * _t3);
        d.m02 = Math.fma(_t7, _t2, _t0 * _t5);
        d.m12 = -(_t2 * _t4);
        d.m22 = Math.fma(_t5, _t3, -(_t6 * _t2));
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_translation(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(_t3, _t4, -(this.m02 * _t7));
        d.m10 = Math.fma(-this.m12, _t7, _t1);
        d.m20 = -_t7;
        d.m01 = Math.fma(this.m02, _t12, Math.fma(_t2, _t0, -(_t9 * _t5)));
        d.m11 = Math.fma(this.m12, _t12, _t5 * _t4);
        d.m21 = _t12;
        d.m02 = Math.fma(this.m02, _t13, Math.fma(_t9, _t2, _t0 * _t5));
        d.m12 = Math.fma(this.m12, _t13, -(_t2 * _t4));
        d.m22 = _t13;
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code rotateYZX_orthogonal}: computes and stores it. Shared by the
     * identical private paths of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX};
     * reached only through them.
     */
    private void rotateYZX_orthogonal_s73bdf308_c0(Float3x3Impl _dst, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _r3, float _r4, float _r5) {
        _dst.m00 = Math.fma(-_r0, _t7, Math.fma(_r1, _t13, _r2 * _t1));
        _dst.m10 = Math.fma(-_r3, _t7, Math.fma(_r4, _t13, _r5 * _t1));
        _dst.m20 = -_t7;
    }

    /** Private tail of {@code rotateYZX_orthogonal}; reached only through it. */
    private void rotateYZX_orthogonal_s73bdf308_tail(Float3x3Impl _dst, float _t5, float _t4, float _t6, float _t2, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _t18, float _t20, float _t14, float _t19, float _t11, float _r3, float _r4, float _r5) {
        rotateYZX_orthogonal_s73bdf308_c0(_dst, _r0, _t7, _r1, _t13, _r2, _t1, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c1(_dst, _r0, _t18, _r1, _t20, _r2, _t14, _r3, _r4, _r5);
        rotateXYZ_orthogonal_s25e2e448_c2(_dst, _r0, Math.fma(_t5, _t4, -(_t6 * _t2)), _r1, _t19, _r2, _t11, _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_orthogonal(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateYZX_orthogonal_s73bdf308_tail(d, _t5, _t4, _t6, _t2, _r0, _t0 * _t3, _r1, _t4 * _t3, _r2, _t1, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3, Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3, _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code rotateYZX_general}: computes and stores it. Shared by the
     * identical private paths of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX};
     * reached only through them.
     */
    private void rotateYZX_general_s73bdf308_c0(Float3x3Impl _dst, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m00 = Math.fma(-_r0, _t7, Math.fma(_r1, _t13, _r2 * _t1));
        _dst.m10 = Math.fma(-_r3, _t7, Math.fma(_r4, _t13, _r5 * _t1));
        _dst.m20 = Math.fma(-_r6, _t7, Math.fma(_r7, _t13, _r8 * _t1));
    }

    /** Private tail of {@code rotateYZX_general}; reached only through it. */
    private void rotateYZX_general_s73bdf308_tail(Float3x3Impl _dst, float _t5, float _t4, float _t6, float _t2, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _t18, float _t20, float _t14, float _t19, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_general_s73bdf308_c0(_dst, _r0, _t7, _r1, _t13, _r2, _t1, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, _t18, _r1, _t20, _r2, _t14, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_general_s25e2e448_c2(_dst, _r0, Math.fma(_t5, _t4, -(_t6 * _t2)), _r1, _t19, _r2, _t11, _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_general(float angleY, float angleZ, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateYZX_general_s73bdf308_tail(d, _t5, _t4, _t6, _t2, _r0, _t0 * _t3, _r1, _t4 * _t3, _r2, _t1, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3, Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
     * Private column 0 of {@code rotateYZX}: computes and stores it. Shared by the identical
     * private paths of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void rotateYZX_sf8b3cd_c0(Double3x3Impl _dst, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        _dst.m00 = Math.fma(-_r0, _t7, Math.fma(_r1, _t13, _r2 * _t1));
        _dst.m10 = Math.fma(-_r3, _t7, Math.fma(_r4, _t13, _r5 * _t1));
        _dst.m20 = Math.fma(-_r6, _t7, Math.fma(_r7, _t13, _r8 * _t1));
    }

    /** Private tail of {@code rotateYZX}; reached only through it. */
    private void rotateYZX_sf8b3cd_tail(Double3x3Impl _dst, float _t5, float _t4, float _t6, float _t2, float _r0, float _t7, float _r1, float _t13, float _r2, float _t1, float _t18, float _t20, float _t14, float _t19, float _t11, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_sf8b3cd_c0(_dst, _r0, _t7, _r1, _t13, _r2, _t1, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, _t18, _r1, _t20, _r2, _t14, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_s1371ea8d_c2(_dst, _r0, Math.fma(_t5, _t4, -(_t6 * _t2)), _r1, _t19, _r2, _t11, _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateYZX_sf8b3cd_tail(d, _t5, _t4, _t6, _t2, _r0, _t0 * _t3, _r1, _t4 * _t3, _r2, _t1, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3, Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3, _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = -1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }



    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = -1.0f;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -1.0f;
        d.m11 = -1.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = -this.m11;
        d.m00 = _t0;
        float _buf0 = -this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m10;
        d.m11 = _t0;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = -this.m11;
        d.m00 = _t0;
        float _buf0 = -this.m10;
        d.m01 = this.m10;
        d.m11 = _t0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m10 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = 0.0f;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ180}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = -1.0f;
            d.m11 = -1.0f;
            d.properties = Joml.BIT_ORTHOGONAL;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = -1.0f;
        d.m20 = 0.0f;
        d.m01 = 1.0f;
        d.m11 = 0.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }



    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = -1.0f;
        d.m20 = 0.0f;
        d.m01 = 1.0f;
        d.m11 = 0.0f;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = -1.0f;
        d.m01 = 1.0f;
        d.m11 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_orthogonal(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m10;
        float _buf0 = -this.m11;
        d.m20 = 0.0f;
        d.m01 = this.m11;
        d.m11 = this.m10;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m10 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_orthogonal_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m10;
        float _buf0 = -this.m11;
        d.m01 = this.m11;
        d.m11 = this.m10;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m10 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_affine(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m01;
        float _buf1 = -this.m11;
        d.m20 = 0.0f;
        d.m01 = this.m00;
        d.m11 = this.m10;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ270}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_affine_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m01;
        float _buf1 = -this.m11;
        d.m01 = this.m00;
        d.m11 = this.m10;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = -this.m01;
        float _buf1 = -this.m11;
        float _buf2 = -this.m21;
        d.m01 = this.m00;
        d.m11 = this.m10;
        d.m21 = this.m20;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = 0.0f;
            d.m10 = -1.0f;
            d.m01 = 1.0f;
            d.m11 = 0.0f;
            d.properties = Joml.BIT_ORTHOGONAL;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = -this.m01;
        float _buf1 = -this.m11;
        float _buf2 = -this.m21;
        d.m01 = this.m00;
        d.m11 = this.m10;
        d.m21 = this.m20;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_orthogonal_affine(@Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m01;
        float _buf1 = this.m11;
        d.m20 = 0.0f;
        d.m01 = -this.m00;
        d.m11 = -this.m10;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_orthogonal_affine_self(@Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m01;
        float _buf1 = this.m11;
        d.m01 = -this.m00;
        d.m11 = -this.m10;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_identity(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = 1.0f;
        d.m20 = 0.0f;
        d.m01 = -1.0f;
        d.m11 = 0.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }



    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_translation(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = 1.0f;
        d.m20 = 0.0f;
        d.m01 = -1.0f;
        d.m11 = 0.0f;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code rotateZ90}, specialized by runtime matrix
     * properties; reached only through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_translation_self(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 0.0f;
        d.m10 = 1.0f;
        d.m01 = -1.0f;
        d.m11 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_general(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m01;
        float _buf1 = this.m11;
        float _buf2 = this.m21;
        d.m01 = -this.m00;
        d.m11 = -this.m10;
        d.m21 = -this.m20;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = 0.0f;
            d.m10 = 1.0f;
            d.m01 = -1.0f;
            d.m11 = 0.0f;
            d.properties = Joml.BIT_ORTHOGONAL;
            return d;
        }
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = this.m01;
        float _buf1 = this.m11;
        float _buf2 = this.m21;
        d.m01 = -this.m00;
        d.m11 = -this.m10;
        d.m21 = -this.m20;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_identity(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        d.m00 = Math.fma(_t3, _t4, -(_t6 * _t0));
        d.m10 = Math.fma(_t7, _t0, _t1 * _t3);
        d.m20 = -(_t0 * _t5);
        d.m01 = -(_t1 * _t5);
        d.m11 = _t5 * _t4;
        d.m21 = _t2;
        d.m02 = Math.fma(_t6, _t3, _t0 * _t4);
        d.m12 = Math.fma(_t0, _t1, -(_t7 * _t3));
        d.m22 = _t5 * _t3;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_translation(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(-this.m02, _t7, Math.fma(_t4, _t5, -(_t6 * _t0)));
        d.m10 = Math.fma(-this.m12, _t7, Math.fma(_t8, _t0, _t2 * _t4));
        d.m20 = -_t7;
        d.m01 = Math.fma(this.m02, _t1, -(_t2 * _t3));
        d.m11 = Math.fma(this.m12, _t1, _t3 * _t5);
        d.m21 = _t1;
        d.m02 = Math.fma(this.m02, _t9, Math.fma(_t6, _t4, _t0 * _t5));
        d.m12 = Math.fma(this.m12, _t9, Math.fma(_t0, _t2, -(_t8 * _t4)));
        d.m22 = _t9;
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateZXY_orthogonal}; reached only through it. */
    private void rotateZXY_orthogonal_s73270308_tail(Float3x3Impl _dst, float _t0, float _t2, float _t8, float _t4, float _r0, float _t7, float _r1, float _t20, float _r2, float _t18, float _t1, float _t14, float _t10, float _t15, float _t19, float _r3, float _r4, float _r5) {
        rotateYZX_orthogonal_s73bdf308_c0(_dst, _r0, _t7, _r1, _t20, _r2, _t18, _r3, _r4, _r5);
        rotateXYZ_orthogonal_s25e2e448_c1(_dst, _r0, _t1, _r2, _t14, _r1, _t10, _r3, _r5, _r4);
        lookAlong_orthogonal_s7f5de0d0_c2(_dst, _r0, _t15, _r1, _t19, _r2, Math.fma(_t0, _t2, -(_t8 * _t4)), _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_orthogonal(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        rotateZXY_orthogonal_s73270308_tail(d, _t0, _t2, _t8, _t4, _r0, _t0 * _t3, _r1, Math.fma(_t4, _t5, -(_t6 * _t0)), _r2, Math.fma(_t8, _t0, _t2 * _t4), _t1, _t3 * _t5, _t2 * _t3, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateZXY_general}; reached only through it. */
    private void rotateZXY_general_s73270308_tail(Float3x3Impl _dst, float _t0, float _t2, float _t8, float _t4, float _r0, float _t7, float _r1, float _t20, float _r2, float _t18, float _t1, float _t14, float _t10, float _t15, float _t19, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_general_s73bdf308_c0(_dst, _r0, _t7, _r1, _t20, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_general_s25e2e448_c1(_dst, _r0, _t1, _r2, _t14, _r1, _t10, _r3, _r5, _r4, _r6, _r8, _r7);
        preMul_general_s36a279f2_c2(_dst, _r0, _t15, _r1, _t19, _r2, Math.fma(_t0, _t2, -(_t8 * _t4)), _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_general(float angleZ, float angleX, float angleY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        rotateZXY_general_s73270308_tail(d, _t0, _t2, _t8, _t4, _r0, _t0 * _t3, _r1, Math.fma(_t4, _t5, -(_t6 * _t0)), _r2, Math.fma(_t8, _t0, _t2 * _t4), _t1, _t3 * _t5, _t2 * _t3, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s6eb1a3cd_tail(Double3x3Impl _dst, float _t0, float _t2, float _t8, float _t4, float _r0, float _t7, float _r1, float _t20, float _r2, float _t18, float _t1, float _t14, float _t10, float _t15, float _t19, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_sf8b3cd_c0(_dst, _r0, _t7, _r1, _t20, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        rotateXYZ_s1371ea8d_c1(_dst, _r0, _t1, _r2, _t14, _r1, _t10, _r3, _r5, _r4, _r6, _r8, _r7);
        lookAlong_s69567f05_c2(_dst, _r0, _t15, _r1, _t19, _r2, Math.fma(_t0, _t2, -(_t8 * _t4)), _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        rotateZXY_s6eb1a3cd_tail(d, _t0, _t2, _t8, _t4, _r0, _t0 * _t3, _r1, Math.fma(_t4, _t5, -(_t6 * _t0)), _r2, Math.fma(_t8, _t0, _t2 * _t4), _t1, _t3 * _t5, _t2 * _t3, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_identity(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        d.m00 = _t3 * _t4;
        d.m10 = _t1 * _t3;
        d.m20 = -_t0;
        d.m01 = Math.fma(_t7, _t2, -(_t1 * _t5));
        d.m11 = Math.fma(_t6, _t2, _t5 * _t4);
        d.m21 = _t2 * _t3;
        d.m02 = Math.fma(_t7, _t5, _t2 * _t1);
        d.m12 = Math.fma(_t6, _t5, -(_t2 * _t4));
        d.m22 = _t5 * _t3;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_translation(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
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
        d.m00 = Math.fma(_t3, _t4, -(this.m02 * _t0));
        d.m10 = Math.fma(_t1, _t3, -(this.m12 * _t0));
        d.m20 = -_t0;
        d.m01 = Math.fma(this.m02, _t7, Math.fma(_t8, _t2, -(_t1 * _t5)));
        d.m11 = Math.fma(this.m12, _t7, Math.fma(_t6, _t2, _t5 * _t4));
        d.m21 = _t7;
        d.m02 = Math.fma(this.m02, _t9, Math.fma(_t8, _t5, _t2 * _t1));
        d.m12 = Math.fma(this.m12, _t9, Math.fma(_t6, _t5, -(_t2 * _t4)));
        d.m22 = _t9;
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateZYX_orthogonal}; reached only through it. */
    private void rotateZYX_orthogonal_s37f7ad48_tail(Float3x3Impl _dst, float _t6, float _t5, float _t2, float _t4, float _r0, float _t0, float _r1, float _t15, float _r2, float _t8, float _t9, float _t20, float _t18, float _t17, float _t19, float _r3, float _r4, float _r5) {
        rotateYZX_orthogonal_s73bdf308_c0(_dst, _r0, _t0, _r1, _t15, _r2, _t8, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c1(_dst, _r0, _t9, _r1, _t20, _r2, _t18, _r3, _r4, _r5);
        lookAlong_orthogonal_s7f5de0d0_c2(_dst, _r0, _t17, _r1, _t19, _r2, Math.fma(_t6, _t5, -(_t2 * _t4)), _r3, _r4, _r5);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_orthogonal(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        rotateZYX_orthogonal_s37f7ad48_tail(d, _t6, _t5, _t2, _t4, _r0, _t0, _r1, _t3 * _t4, _r2, _t1 * _t3, _t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4), _t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), _r3, _r4, _r5);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code rotateZYX_general}; reached only through it. */
    private void rotateZYX_general_s37f7ad48_tail(Float3x3Impl _dst, float _t6, float _t5, float _t2, float _t4, float _r0, float _t0, float _r1, float _t15, float _r2, float _t8, float _t9, float _t20, float _t18, float _t17, float _t19, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_general_s73bdf308_c0(_dst, _r0, _t0, _r1, _t15, _r2, _t8, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c1(_dst, _r0, _t9, _r1, _t20, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        preMul_general_s36a279f2_c2(_dst, _r0, _t17, _r1, _t19, _r2, Math.fma(_t6, _t5, -(_t2 * _t4)), _r3, _r4, _r5, _r6, _r7, _r8);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_general(float angleZ, float angleY, float angleX, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        rotateZYX_general_s37f7ad48_tail(d, _t6, _t5, _t2, _t4, _r0, _t0, _r1, _t3 * _t4, _r2, _t1 * _t3, _t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4), _t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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

    /** Private tail of {@code rotateZYX}; reached only through it. */
    private void rotateZYX_s43f6418d_tail(Double3x3Impl _dst, float _t6, float _t5, float _t2, float _t4, float _r0, float _t0, float _r1, float _t15, float _r2, float _t8, float _t9, float _t20, float _t18, float _t17, float _t19, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8) {
        rotateYZX_sf8b3cd_c0(_dst, _r0, _t0, _r1, _t15, _r2, _t8, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c1(_dst, _r0, _t9, _r1, _t20, _r2, _t18, _r3, _r4, _r5, _r6, _r7, _r8);
        lookAlong_s69567f05_c2(_dst, _r0, _t17, _r1, _t19, _r2, Math.fma(_t6, _t5, -(_t2 * _t4)), _r3, _r4, _r5, _r6, _r7, _r8);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _r0 = this.m02;
        float _r1 = this.m00;
        float _r2 = this.m01;
        float _r3 = this.m12;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m22;
        float _r7 = this.m20;
        float _r8 = this.m21;
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        rotateZYX_s43f6418d_tail(d, _t6, _t5, _t2, _t4, _r0, _t0, _r1, _t3 * _t4, _r2, _t1 * _t3, _t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4), _t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), _r3, _r4, _r5, _r6, _r7, _r8);
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation_self(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = vX;
        d.m11 = vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m20 = 0.0f;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal_self(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m20 = this.m20 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m21 = this.m21 * vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m20 = this.m20 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m21 = this.m21 * vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_translation_self(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s;
        d.m11 = s;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = 0.0f;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal_self(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float s, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = s * this.m20;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = s * this.m21;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m00 = s;
            d.m11 = s;
            d.properties = Joml.BIT_AFFINE;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m20 = s * this.m20;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m21 = s * this.m21;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, _t0, this.m02);
        d.m12 = Math.fma(pivotY, _t0, this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = Math.fma(pivotX, _t0, this.m02);
        d.m12 = Math.fma(pivotY, _t0, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * this.m00;
        float _buf1 = s * this.m10;
        d.m20 = 0.0f;
        float _buf2 = s * this.m01;
        float _buf3 = s * this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02));
        d.m12 = Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal_self(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * this.m00;
        float _buf1 = s * this.m10;
        float _buf2 = s * this.m01;
        float _buf3 = s * this.m11;
        d.m02 = Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02));
        d.m12 = Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float s, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * this.m00;
        float _buf1 = s * this.m10;
        float _buf2 = s * this.m20;
        float _buf3 = s * this.m01;
        float _buf4 = s * this.m11;
        float _buf5 = s * this.m21;
        d.m02 = Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02));
        d.m12 = Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12));
        d.m22 = Math.fma(this.m20, _t1, Math.fma(this.m21, _t2, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _buf0 = s * this.m00;
        float _buf1 = s * this.m10;
        float _buf2 = s * this.m20;
        float _buf3 = s * this.m01;
        float _buf4 = s * this.m11;
        float _buf5 = s * this.m21;
        d.m02 = Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02));
        d.m12 = Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12));
        d.m22 = Math.fma(this.m20, _t1, Math.fma(this.m21, _t2, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = sY;
        d.m21 = 0.0f;
        d.m02 = Math.fma(pivotX, 1.0f - sX, this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = Math.fma(pivotX, 1.0f - sX, this.m02);
        d.m12 = Math.fma(pivotY, 1.0f - sY, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * this.m00;
        float _buf1 = sX * this.m10;
        d.m20 = 0.0f;
        float _buf2 = sY * this.m01;
        float _buf3 = sY * this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02));
        d.m12 = Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * this.m00;
        float _buf1 = sX * this.m10;
        float _buf2 = sY * this.m01;
        float _buf3 = sY * this.m11;
        d.m02 = Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02));
        d.m12 = Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float sX, float sY, float pivotX, float pivotY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * this.m00;
        float _buf1 = sX * this.m10;
        float _buf2 = sX * this.m20;
        float _buf3 = sY * this.m01;
        float _buf4 = sY * this.m11;
        float _buf5 = sY * this.m21;
        d.m02 = Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02));
        d.m12 = Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12));
        d.m22 = Math.fma(this.m20, _t2, Math.fma(this.m21, _t3, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _buf0 = sX * this.m00;
        float _buf1 = sX * this.m10;
        float _buf2 = sX * this.m20;
        float _buf3 = sY * this.m01;
        float _buf4 = sY * this.m11;
        float _buf5 = sY * this.m21;
        d.m02 = Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02));
        d.m12 = Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12));
        d.m22 = Math.fma(this.m20, _t2, Math.fma(this.m21, _t3, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        d.m20 = 0.0f;
        float _buf2 = this.m01;
        float _buf3 = this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = _props;
        return d;
    }


    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_orthogonal_affine_self(float vX, float vY, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        float _buf2 = this.m01;
        float _buf3 = this.m11;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = _props;
        return d;
    }






    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_general(float vX, float vY, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        float _buf2 = this.m20;
        float _buf3 = this.m01;
        float _buf4 = this.m11;
        float _buf5 = this.m21;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m22 = Math.fma(this.m20, vX, Math.fma(this.m21, vY, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
            Float3x3Impl d = (Float3x3Impl) this;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _buf0 = this.m00;
        float _buf1 = this.m10;
        float _buf2 = this.m20;
        float _buf3 = this.m01;
        float _buf4 = this.m11;
        float _buf5 = this.m21;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m22 = Math.fma(this.m20, vX, Math.fma(this.m21, vY, this.m22));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_identity(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1_inv + _t1_inv;
        d.m21 = 0.0f;
        d.m02 = -((left + right) * _t0_inv);
        d.m12 = -((bottom + top) * _t1_inv);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_identity_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = -((left + right) * _t0_inv);
        d.m12 = -((bottom + top) * _t1_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_translation(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1_inv + _t1_inv;
        d.m21 = 0.0f;
        d.m02 = Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_translation_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_orthogonal(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        d.m20 = 0.0f;
        float _buf2 = _sp1 * this.m01;
        float _buf3 = _sp1 * this.m11;
        d.m21 = 0.0f;
        d.m02 = Math.fma(-this.m01, _sp3, Math.fma(-this.m00, _sp2, this.m02));
        d.m12 = Math.fma(-this.m11, _sp3, Math.fma(-this.m10, _sp2, this.m12));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_orthogonal_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        float _buf2 = _sp1 * this.m01;
        float _buf3 = _sp1 * this.m11;
        d.m02 = Math.fma(-this.m01, _sp3, Math.fma(-this.m00, _sp2, this.m02));
        d.m12 = Math.fma(-this.m11, _sp3, Math.fma(-this.m10, _sp2, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_affine(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        d.m20 = 0.0f;
        float _buf2 = _sp1 * this.m01;
        float _buf3 = _sp1 * this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2));
        d.m22 = 1.0f;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float3x3 view_affine_self(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        float _buf2 = _sp1 * this.m01;
        float _buf3 = _sp1 * this.m11;
        d.m02 = this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_general(float left, float right, float bottom, float top, @Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        float _buf2 = _sp0 * this.m20;
        float _buf3 = _sp1 * this.m01;
        float _buf4 = _sp1 * this.m11;
        float _buf5 = _sp1 * this.m21;
        d.m02 = this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2));
        d.m22 = this.m22 + Math.fma(-this.m21, _sp3, -(this.m20 * _sp2));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _buf0 = _sp0 * this.m00;
        float _buf1 = _sp0 * this.m10;
        float _buf2 = _sp0 * this.m20;
        float _buf3 = _sp1 * this.m01;
        float _buf4 = _sp1 * this.m11;
        float _buf5 = _sp1 * this.m21;
        d.m02 = this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2));
        d.m22 = this.m22 + Math.fma(-this.m21, _sp3, -(this.m20 * _sp2));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.properties = 0;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = Math.fma(this.m02, vZ, vX);
        d.y = Math.fma(this.m12, vZ, vY);
        d.z = vZ;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3 mul_general(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY));
        d.y = Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY));
        d.z = Math.fma(this.m22, vZ, Math.fma(this.m20, vX, this.m21 * vY));
        return d;
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
            Float3Impl d = (Float3Impl) dest;
            d.x = vX;
            d.y = vY;
            d.z = vZ;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY));
        d.y = Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY));
        d.z = Math.fma(this.m22, vZ, Math.fma(this.m20, vX, this.m21 * vY));
        return d;
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
        Float2Impl d = (Float2Impl) dest;
        d.x = vX;
        d.y = vY;
        return d;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = Math.fma(this.m00, vX, this.m01 * vY);
        d.y = Math.fma(this.m10, vX, this.m11 * vY);
        return d;
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
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m00, vX, this.m01 * vY);
        d.y = Math.fma(this.m10, vX, this.m11 * vY);
        return d;
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
        Float2Impl d = (Float2Impl) dest;
        d.x = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.y = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        return d;
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
            Float2Impl d = (Float2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
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
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.y = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        return d;
    }

    public float m00() { return this.m00; }
    public float m01() { return this.m01; }
    public float m02() { return this.m02; }
    public float m10() { return this.m10; }
    public float m11() { return this.m11; }
    public float m12() { return this.m12; }
    public float m20() { return this.m20; }
    public float m21() { return this.m21; }
    public float m22() { return this.m22; }

    @Override public String toString() {
        return "Float3x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float3x3Impl)) return false;
        Float3x3Impl o = (Float3x3Impl) obj;
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

    @Override public boolean isFinite() {
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

    @Override public boolean isNaN() {
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

    @Override public boolean equalsEpsilon(Float3x3R other, float epsilon) {
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m02 - other.m02()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon
            && Math.abs(m12 - other.m12()) <= epsilon
            && Math.abs(m20 - other.m20()) <= epsilon
            && Math.abs(m21 - other.m21()) <= epsilon
            && Math.abs(m22 - other.m22()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public @Mutated Float3x3 loadCM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m21 = src[offset + 5];
        this.m02 = src[offset + 6];
        this.m12 = src[offset + 7];
        this.m22 = src[offset + 8];
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
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    public Float3x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public @Mutated Float3x3 loadCM(double[] src, int offset) {
        this.m00 = (float) src[offset + 0];
        this.m10 = (float) src[offset + 1];
        this.m20 = (float) src[offset + 2];
        this.m01 = (float) src[offset + 3];
        this.m11 = (float) src[offset + 4];
        this.m21 = (float) src[offset + 5];
        this.m02 = (float) src[offset + 6];
        this.m12 = (float) src[offset + 7];
        this.m22 = (float) src[offset + 8];
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }
    public Float3x3 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public @Mutated Float3x3 loadRM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.m20 = src[offset + 6];
        this.m21 = src[offset + 7];
        this.m22 = src[offset + 8];
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
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Float3x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public @Mutated Float3x3 loadRM(double[] src, int offset) {
        this.m00 = (float) src[offset + 0];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[offset + 3];
        this.m11 = (float) src[offset + 4];
        this.m12 = (float) src[offset + 5];
        this.m20 = (float) src[offset + 6];
        this.m21 = (float) src[offset + 7];
        this.m22 = (float) src[offset + 8];
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }
    public Float3x3 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
        this.m02 = src[_p2];
        this.m12 = src[_p2 + 1];
        this.m22 = src[_p2 + 2];
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
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    public Float3x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m20 = (float) src[offset + 2];
        this.m01 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m21 = (float) src[_p1 + 2];
        this.m02 = (float) src[_p2];
        this.m12 = (float) src[_p2 + 1];
        this.m22 = (float) src[_p2 + 2];
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }
    public Float3x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m12 = src[_p1 + 2];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        this.m22 = src[_p2 + 2];
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
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    public Float3x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float3x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m12 = (float) src[_p1 + 2];
        this.m20 = (float) src[_p2];
        this.m21 = (float) src[_p2 + 1];
        this.m22 = (float) src[_p2 + 2];
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }
    public Float3x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public FloatBuffer storeCM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public Float3x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeCM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeCM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4DoubleUnsafe(this, address);
    }
    public MemorySegment storeCM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Double(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public FloatBuffer storeRM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public Float3x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, index, buf);
    }
    public Float3x3 storeRM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4DoubleUnsafe(this, address);
    }
    public MemorySegment storeRM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Double(this, offset, dest);
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
