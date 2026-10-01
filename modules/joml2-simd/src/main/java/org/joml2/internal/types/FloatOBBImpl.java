// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatOBB} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatOBBImpl implements FloatOBB {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatOBBSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBSegOpsUnsafe()
                        : new FloatOBBSegOpsMS();
        static final FloatOBBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBBbOpsUnsafe()
                        : new FloatOBBBbOpsApi();
        static final FloatOBBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBRawOpsUnsafe()
                        : new FloatOBBRawOpsApi();
    }

    public FloatOBBImpl() {
        data = new float[15];
        data[3] = 1;
        data[7] = 1;
        data[11] = 1;
    }

    public FloatOBBImpl(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) {
        float[] dd = this.data = new float[15];
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = uXx;
        dd[4] = uXy;
        dd[5] = uXz;
        dd[6] = uYx;
        dd[7] = uYy;
        dd[8] = uYz;
        dd[9] = uZx;
        dd[10] = uZy;
        dd[11] = uZz;
        dd[12] = hsX;
        dd[13] = hsY;
        dd[14] = hsZ;
    }

    public FloatOBBImpl(FloatOBBR src) {
        float[] dd = this.data = new float[15];
        dd[0] = src.cX();
        dd[1] = src.cY();
        dd[2] = src.cZ();
        dd[3] = src.uXx();
        dd[4] = src.uXy();
        dd[5] = src.uXz();
        dd[6] = src.uYx();
        dd[7] = src.uYy();
        dd[8] = src.uYz();
        dd[9] = src.uZx();
        dd[10] = src.uZy();
        dd[11] = src.uZz();
        dd[12] = src.hsX();
        dd[13] = src.hsY();
        dd[14] = src.hsZ();
    }


    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return this
     */
    public @Mutated FloatOBB set(FloatOBBR v) {
        float vCY = v.cY();
        float vCZ = v.cZ();
        float vUXx = v.uXx();
        float vUXy = v.uXy();
        float vUXz = v.uXz();
        float vUYx = v.uYx();
        float vUYy = v.uYy();
        float vUYz = v.uYz();
        float vUZx = v.uZx();
        float vUZy = v.uZy();
        float vUZz = v.uZz();
        float vHsX = v.hsX();
        float vHsY = v.hsY();
        float vHsZ = v.hsZ();
        float[] dd = this.data;
        dd[0] = v.cX();
        dd[1] = vCY;
        dd[2] = vCZ;
        dd[3] = vUXx;
        dd[4] = vUXy;
        dd[5] = vUXz;
        dd[6] = vUYx;
        dd[7] = vUYy;
        dd[8] = vUYz;
        dd[9] = vUZx;
        dd[10] = vUZy;
        dd[11] = vUZz;
        dd[12] = vHsX;
        dd[13] = vHsY;
        dd[14] = vHsZ;
        return this;
    }


    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vCX the {@code cX} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vCY the {@code cY} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vCZ the {@code cZ} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXx the {@code uXx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXy the {@code uXy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXz the {@code uXz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYx the {@code uYx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYy the {@code uYy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYz the {@code uYz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZx the {@code uZx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZy the {@code uZy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZz the {@code uZz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsX the {@code hsX} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsY the {@code hsY} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @return this
     */
    @Mutated public FloatOBB set(float vCX, float vCY, float vCZ, float vUXx, float vUXy, float vUXz, float vUYx, float vUYy, float vUYz, float vUZx, float vUZy, float vUZz, float vHsX, float vHsY, float vHsZ) {
        float[] dd = this.data;
        dd[0] = vCX;
        dd[1] = vCY;
        dd[2] = vCZ;
        dd[3] = vUXx;
        dd[4] = vUXy;
        dd[5] = vUXz;
        dd[6] = vUYx;
        dd[7] = vUYy;
        dd[8] = vUYz;
        dd[9] = vUZx;
        dd[10] = vUZy;
        dd[11] = vUZz;
        dd[12] = vHsX;
        dd[13] = vHsY;
        dd[14] = vHsZ;
        return this;
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axisX}, {@code axisY} and {@code axisZ} must be orthonormal.
     *
     * @param axisX the new local X axis
     * @param axisY the new local Y axis
     * @param axisZ the new local Z axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setAxes(Float3R axisX, Float3R axisY, Float3R axisZ, @Mutated FloatOBB dest) {
        float axisXX = axisX.x();
        float axisXY = axisX.y();
        float axisXZ = axisX.z();
        float axisYX = axisY.x();
        float axisYY = axisY.y();
        float axisYZ = axisY.z();
        float axisZX = axisZ.x();
        float axisZY = axisZ.y();
        float axisZZ = axisZ.z();
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axisX}, {@code axisY} and {@code axisZ} must be orthonormal.
     *
     * @param axisX the new local X axis
     * @param axisY the new local Y axis
     * @param axisZ the new local Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setAxes(Float3R axisX, Float3R axisY, Float3R axisZ, @Mutated DoubleOBB dest) {
        float axisXX = axisX.x();
        float axisXY = axisX.y();
        float axisXZ = axisX.z();
        float axisYX = axisY.x();
        float axisYY = axisY.y();
        float axisYZ = axisY.z();
        float axisZX = axisZ.x();
        float axisZY = axisZ.y();
        float axisZZ = axisZ.z();
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (axisXX, axisXY, axisXZ)}, {@code (axisYX, axisYY, axisYZ)} and
     * {@code (axisZX, axisZY, axisZZ)} must be orthonormal.
     *
     * @param axisXX the {@code x} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXY the {@code y} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXZ the {@code z} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisYX the {@code x} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYY the {@code y} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYZ the {@code z} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisZX the {@code x} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZY the {@code y} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZZ the {@code z} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setAxes(float axisXX, float axisXY, float axisXZ, float axisYX, float axisYY, float axisYZ, float axisZX, float axisZY, float axisZZ, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (axisXX, axisXY, axisXZ)}, {@code (axisYX, axisYY, axisYZ)} and
     * {@code (axisZX, axisZY, axisZZ)} must be orthonormal.
     *
     * @param axisXX the {@code x} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXY the {@code y} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXZ the {@code z} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisYX the {@code x} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYY the {@code y} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYZ the {@code z} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisZX the {@code x} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZY the {@code y} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZZ the {@code z} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setAxes(float axisXX, float axisXY, float axisXZ, float axisYX, float axisYY, float axisYZ, float axisZX, float axisZY, float axisZZ, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the center of this oriented bounding box to {@code c} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setCenter(Float3R c, @Mutated FloatOBB dest) {
        float cY = c.y();
        float cZ = c.z();
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = c.x();
        dd[1] = cY;
        dd[2] = cZ;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        FloatVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        FloatVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }


    /**
     * Set the center of this oriented bounding box to {@code c} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setCenter(Float3R c, @Mutated DoubleOBB dest) {
        float cY = c.y();
        float cZ = c.z();
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = c.x();
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the center of this oriented bounding box to ({@code cX}, {@code cY}, {@code cZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setCenter(float cX, float cY, float cZ, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        FloatVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        FloatVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }


    /**
     * Set the center of this oriented bounding box to ({@code cX}, {@code cY}, {@code cZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setCenter(float cX, float cY, float cZ, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the half extents of this oriented bounding box to {@code h} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code h} must not be negative.
     *
     * @param h the new half extents
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setHalfSize(Float3R h, @Mutated FloatOBB dest) {
        float hX = h.x();
        float hY = h.y();
        float hZ = h.z();
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        FloatVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        FloatVector.fromArray(COL_SPECIES, sd, 8).intoArray(dd, 8);
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }


    /**
     * Set the half extents of this oriented bounding box to {@code h} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code h} must not be negative.
     *
     * @param h the new half extents
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setHalfSize(Float3R h, @Mutated DoubleOBB dest) {
        float hX = h.x();
        float hY = h.y();
        float hZ = h.z();
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }


    /**
     * Set the half extents of this oriented bounding box to ({@code hX}, {@code hY}, {@code hZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (hX, hY, hZ)} must not be negative.
     *
     * @param hX the {@code x} component of the vector {@code (hX, hY, hZ)}
     * @param hY the {@code y} component of the vector {@code (hX, hY, hZ)}
     * @param hZ the {@code z} component of the vector {@code (hX, hY, hZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setHalfSize(float hX, float hY, float hZ, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        FloatVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        FloatVector.fromArray(COL_SPECIES, sd, 8).intoArray(dd, 8);
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }


    /**
     * Set the half extents of this oriented bounding box to ({@code hX}, {@code hY}, {@code hZ})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (hX, hY, hZ)} must not be negative.
     *
     * @param hX the {@code x} component of the vector {@code (hX, hY, hZ)}
     * @param hY the {@code y} component of the vector {@code (hX, hY, hZ)}
     * @param hZ the {@code z} component of the vector {@code (hX, hY, hZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setHalfSize(float hX, float hY, float hZ, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }


    /**
     * Reset the orientation of this oriented bounding box to identity and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setIdentityOrientation(@Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        VEC_0.intoArray(dd, 3);
        VEC_0.intoArray(dd, 7);
        dd[11] = 1.0f;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Reset the orientation of this oriented bounding box to identity and store the result in
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
    public DoubleOBB setIdentityOrientation(@Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 1.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 0.0f;
        dd[11] = 1.0f;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the orientation of this oriented bounding box to {@code q} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the new orientation
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setOrientation(FloatQuatR q, @Mutated FloatOBB dest) {
        float qX = q.x();
        float qY = q.y();
        float qZ = q.z();
        float qW = q.w();
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * Math.fma(qX, qY, -_t1);
        dd[7] = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        dd[11] = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the orientation of this oriented bounding box to {@code q} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the new orientation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setOrientation(FloatQuatR q, @Mutated DoubleOBB dest) {
        float qX = q.x();
        float qY = q.y();
        float qZ = q.z();
        float qW = q.w();
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * Math.fma(qX, qY, -_t1);
        dd[7] = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        dd[11] = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the orientation of this oriented bounding box to ({@code qX}, {@code qY}, {@code qZ},
     * {@code qW}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB setOrientation(float qX, float qY, float qZ, float qW, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * Math.fma(qX, qY, -_t1);
        dd[7] = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        dd[11] = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set the orientation of this oriented bounding box to ({@code qX}, {@code qY}, {@code qZ},
     * {@code qW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setOrientation(float qX, float qY, float qZ, float qW, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * Math.fma(qX, qY, -_t1);
        dd[7] = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        dd[11] = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Convert this oriented bounding box to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB toDouble(@Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Set this oriented bounding box to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatOBB makeIdentity() {
        float[] dd = this.data;
        System.arraycopy(DATA_1, 0, dd, 0, 15);
        return this;
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB transform(Float3x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _ld0 = mData[2];
        float _ld1 = sd[5];
        float _ld2 = mData[0];
        float _ld3 = sd[3];
        float _ld4 = mData[1];
        float _ld5 = sd[4];
        float _ld6 = mData[10];
        float _ld7 = mData[8];
        float _ld8 = mData[9];
        float _ld9 = mData[6];
        float _ld10 = mData[4];
        float _ld11 = mData[5];
        float _ld12 = sd[8];
        float _ld13 = sd[6];
        float _ld14 = sd[7];
        return transform_s84fb42d0_5(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, Math.fma(_ld0, _ld1, Math.fma(_ld2, _ld3, _ld4 * _ld5)), Math.fma(_ld6, _ld1, Math.fma(_ld7, _ld3, _ld8 * _ld5)), Math.fma(_ld9, _ld1, Math.fma(_ld10, _ld3, _ld11 * _ld5)), Math.fma(_ld0, _ld12, Math.fma(_ld2, _ld13, _ld4 * _ld14)), Math.fma(_ld6, _ld12, Math.fma(_ld7, _ld13, _ld8 * _ld14)), Math.fma(_ld9, _ld12, Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float transform_s84fb42d0_1(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t37 = Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = Math.fma(_t20, _t22, -(_t23 * _t19));
        return Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39));
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private void transform_s84fb42d0_2(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = Math.fma(_ld7, _ld18, Math.fma(_ld8, _ld19, Math.fma(_ld6, _ld20, mData[11])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_3(float _ld0, float _ld2, float _ld4, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float[] dd) {
        float _buf0 = Math.fma(_ld2, _ld18, Math.fma(_ld4, _ld19, Math.fma(_ld0, _ld20, _ld21)));
        float _buf1 = Math.fma(_ld10, _ld18, Math.fma(_ld11, _ld19, Math.fma(_ld9, _ld20, _ld22)));
        dd[0] = _buf0;
        dd[1] = _buf1;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_4(float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        float _t62 = Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        float _buf2 = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t46, Math.fma(_t25, _t44, _t26 * _t45))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t46, Math.fma(_t18, _t44, _t20 * _t45))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t46, Math.fma(_t21, _t44, _t23 * _t45)))));
        float _buf3 = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t64, Math.fma(_t25, _t62, _t26 * _t63))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t64, Math.fma(_t18, _t62, _t20 * _t63))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t64, Math.fma(_t21, _t62, _t23 * _t63)))));
        dd[12] = _buf2;
        dd[13] = _buf3;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s84fb42d0_5(Float3x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_ld6, _ld15, Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = Math.fma(_ld0, _ld15, Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = Math.fma(_ld9, _ld15, Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_s84fb42d0_2(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Float3x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _ld0 = mData[2];
        float _ld1 = sd[5];
        float _ld2 = mData[0];
        float _ld3 = sd[3];
        float _ld4 = mData[1];
        float _ld5 = sd[4];
        float _ld6 = mData[10];
        float _ld7 = mData[8];
        float _ld8 = mData[9];
        float _ld9 = mData[6];
        float _ld10 = mData[4];
        float _ld11 = mData[5];
        float _ld12 = sd[8];
        float _ld13 = sd[6];
        float _ld14 = sd[7];
        return transform_s9ad3639_5(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[7], mData[11], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, Math.fma(_ld0, _ld1, Math.fma(_ld2, _ld3, _ld4 * _ld5)), Math.fma(_ld6, _ld1, Math.fma(_ld7, _ld3, _ld8 * _ld5)), Math.fma(_ld9, _ld1, Math.fma(_ld10, _ld3, _ld11 * _ld5)), Math.fma(_ld0, _ld12, Math.fma(_ld2, _ld13, _ld4 * _ld14)), Math.fma(_ld6, _ld12, Math.fma(_ld7, _ld13, _ld8 * _ld14)), Math.fma(_ld9, _ld12, Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private void transform_s9ad3639_2(float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, double[] dd) {
        dd[0] = Math.fma(_ld2, _ld18, Math.fma(_ld4, _ld19, Math.fma(_ld0, _ld20, mData[3])));
        dd[1] = Math.fma(_ld10, _ld18, Math.fma(_ld11, _ld19, Math.fma(_ld9, _ld20, _ld22)));
        dd[2] = Math.fma(_ld7, _ld18, Math.fma(_ld8, _ld19, Math.fma(_ld6, _ld20, _ld23)));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s9ad3639_3(float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[12] = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t46, Math.fma(_t25, _t44, _t26 * _t45))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t46, Math.fma(_t18, _t44, _t20 * _t45))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t46, Math.fma(_t21, _t44, _t23 * _t45)))));
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_s9ad3639_4(DoubleOBB dest, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        float _t62 = Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        dd[13] = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t64, Math.fma(_t25, _t62, _t26 * _t63))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t64, Math.fma(_t18, _t62, _t20 * _t63))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t64, Math.fma(_t21, _t62, _t23 * _t63)))));
        dd[14] = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9ad3639_5(Float3x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_ld6, _ld15, Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = Math.fma(_ld0, _ld15, Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = Math.fma(_ld9, _ld15, Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_s9ad3639_2(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate(Float3x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s8ab82e23_1(dest, sd, mData, ((FloatOBBImpl) dest).data, Math.fma(mData[10], sd[5], Math.fma(mData[8], sd[3], mData[9] * sd[4])), Math.fma(mData[2], sd[5], Math.fma(mData[0], sd[3], mData[1] * sd[4])), Math.fma(mData[6], sd[5], Math.fma(mData[4], sd[3], mData[5] * sd[4])), Math.fma(mData[10], sd[8], Math.fma(mData[8], sd[6], mData[9] * sd[7])), Math.fma(mData[2], sd[8], Math.fma(mData[0], sd[6], mData[1] * sd[7])), Math.fma(mData[6], sd[8], Math.fma(mData[4], sd[6], mData[5] * sd[7])), Math.fma(mData[10], sd[11], Math.fma(mData[8], sd[9], mData[9] * sd[10])), Math.fma(mData[2], sd[11], Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_1(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(mData[6], sd[11], Math.fma(mData[4], sd[9], mData[5] * sd[10]));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t39 = _t18 * _t27;
        float _t40 = _t19 * _t27;
        float _t41 = _t20 * _t27;
        float _t42 = _t21 * _t28;
        float _t43 = _t22 * _t28;
        float _t44 = _t23 * _t28;
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t63, _t64, _t65;
        if (Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0f;
                    _t64 = 1.0f;
                    _t65 = 0.0f;
                }
            }
        }
        return transform_degenerate_s8ab82e23_2(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t63, _t64, _t65);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_2(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t63, float _t64, float _t65) {
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        float _t70 = _t69 * _t64;
        float _t71 = _t69 * _t65;
        float _t72 = _t69 * _t63;
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_s8ab82e23_3(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_3(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110) {
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t129 = _t122 * _t93;
                    _t130 = _t122 * _t92;
                    _t131 = _t122 * _t94;
                } else {
                    _t129 = _t122 * _t96;
                    _t130 = _t122 * _t95;
                    _t131 = _t122 * _t97;
                }
            } else {
                _t129 = _t122 * _t99;
                _t130 = _t122 * _t98;
                _t131 = _t122 * _t100;
            }
        } else {
            _t129 = _t110 * _t83;
            _t130 = _t110 * _t84;
            _t131 = _t110 * _t91;
        }
        return transform_degenerate_s8ab82e23_4(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /** Piece 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_4(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        float _buf0 = Math.fma(mData[0], sd[0], Math.fma(mData[1], sd[1], Math.fma(mData[2], sd[2], mData[3])));
        float _buf1 = Math.fma(mData[4], sd[0], Math.fma(mData[5], sd[1], Math.fma(mData[6], sd[2], mData[7])));
        dd[2] = Math.fma(mData[8], sd[0], Math.fma(mData[9], sd[1], Math.fma(mData[10], sd[2], mData[11])));
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t129;
        dd[7] = _t131;
        dd[8] = _t130;
        dd[9] = _t140;
        dd[10] = _t138;
        dd[11] = _t139;
        return transform_degenerate_s8ab82e23_5(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t129, _t130, _t131, _t138, _t139, _t140, _buf0, _buf1, Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t72, Math.fma(_t25, _t70, _t26 * _t71))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t72, Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t72, Math.fma(_t22, _t70, _t23 * _t71))))));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_5(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140, float _buf0, float _buf1, float _buf2) {
        float _buf3 = Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t130, Math.fma(_t25, _t129, _t26 * _t131))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t130, Math.fma(_t19, _t129, _t20 * _t131))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t130, Math.fma(_t22, _t129, _t23 * _t131)))));
        dd[14] = Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t139, Math.fma(_t25, _t140, _t26 * _t138))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t139, Math.fma(_t19, _t140, _t20 * _t138))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t139, Math.fma(_t22, _t140, _t23 * _t138)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
        dd[13] = _buf3;
        return dest;
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Float3x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s374590_1(dest, sd, mData, ((DoubleOBBImpl) dest).data, Math.fma(mData[10], sd[5], Math.fma(mData[8], sd[3], mData[9] * sd[4])), Math.fma(mData[2], sd[5], Math.fma(mData[0], sd[3], mData[1] * sd[4])), Math.fma(mData[6], sd[5], Math.fma(mData[4], sd[3], mData[5] * sd[4])), Math.fma(mData[10], sd[8], Math.fma(mData[8], sd[6], mData[9] * sd[7])), Math.fma(mData[2], sd[8], Math.fma(mData[0], sd[6], mData[1] * sd[7])), Math.fma(mData[6], sd[8], Math.fma(mData[4], sd[6], mData[5] * sd[7])), Math.fma(mData[10], sd[11], Math.fma(mData[8], sd[9], mData[9] * sd[10])), Math.fma(mData[2], sd[11], Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_1(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(mData[6], sd[11], Math.fma(mData[4], sd[9], mData[5] * sd[10]));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t39 = _t18 * _t27;
        float _t40 = _t19 * _t27;
        float _t41 = _t20 * _t27;
        float _t42 = _t21 * _t28;
        float _t43 = _t22 * _t28;
        float _t44 = _t23 * _t28;
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t63, _t64, _t65;
        if (Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0f;
                    _t64 = 1.0f;
                    _t65 = 0.0f;
                }
            }
        }
        return transform_degenerate_s374590_2(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t63, _t64, _t65);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_2(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t63, float _t64, float _t65) {
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        float _t70 = _t69 * _t64;
        float _t71 = _t69 * _t65;
        float _t72 = _t69 * _t63;
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_s374590_3(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_3(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110) {
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t129 = _t122 * _t93;
                    _t130 = _t122 * _t92;
                    _t131 = _t122 * _t94;
                } else {
                    _t129 = _t122 * _t96;
                    _t130 = _t122 * _t95;
                    _t131 = _t122 * _t97;
                }
            } else {
                _t129 = _t122 * _t99;
                _t130 = _t122 * _t98;
                _t131 = _t122 * _t100;
            }
        } else {
            _t129 = _t110 * _t83;
            _t130 = _t110 * _t84;
            _t131 = _t110 * _t91;
        }
        return transform_degenerate_s374590_4(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /** Piece 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_4(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        dd[0] = Math.fma(mData[0], sd[0], Math.fma(mData[1], sd[1], Math.fma(mData[2], sd[2], mData[3])));
        dd[1] = Math.fma(mData[4], sd[0], Math.fma(mData[5], sd[1], Math.fma(mData[6], sd[2], mData[7])));
        dd[2] = Math.fma(mData[8], sd[0], Math.fma(mData[9], sd[1], Math.fma(mData[10], sd[2], mData[11])));
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t129;
        dd[7] = _t131;
        dd[8] = _t130;
        dd[9] = _t140;
        dd[10] = _t138;
        dd[11] = _t139;
        return transform_degenerate_s374590_5(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, _t138, _t139, _t140);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_5(DoubleOBB dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        dd[12] = Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t72, Math.fma(_t25, _t70, _t26 * _t71))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t72, Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t72, Math.fma(_t22, _t70, _t23 * _t71)))));
        dd[13] = Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t130, Math.fma(_t25, _t129, _t26 * _t131))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t130, Math.fma(_t19, _t129, _t20 * _t131))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t130, Math.fma(_t22, _t129, _t23 * _t131)))));
        dd[14] = Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t139, Math.fma(_t25, _t140, _t26 * _t138))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t139, Math.fma(_t19, _t140, _t20 * _t138))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t139, Math.fma(_t22, _t140, _t23 * _t138)))));
        return dest;
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB transform(Float4x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _ld0 = mData[8];
        float _ld1 = sd[5];
        float _ld2 = mData[0];
        float _ld3 = sd[3];
        float _ld4 = mData[4];
        float _ld5 = sd[4];
        float _ld6 = mData[10];
        float _ld7 = mData[2];
        float _ld8 = mData[6];
        float _ld9 = mData[9];
        float _ld10 = mData[1];
        float _ld11 = mData[5];
        float _ld12 = sd[8];
        float _ld13 = sd[6];
        float _ld14 = sd[7];
        return transform_s4e1499f_5(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, Math.fma(_ld0, _ld1, Math.fma(_ld2, _ld3, _ld4 * _ld5)), Math.fma(_ld6, _ld1, Math.fma(_ld7, _ld3, _ld8 * _ld5)), Math.fma(_ld9, _ld1, Math.fma(_ld10, _ld3, _ld11 * _ld5)), Math.fma(_ld0, _ld12, Math.fma(_ld2, _ld13, _ld4 * _ld14)), Math.fma(_ld6, _ld12, Math.fma(_ld7, _ld13, _ld8 * _ld14)), Math.fma(_ld9, _ld12, Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private void transform_s4e1499f_2(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = Math.fma(_ld7, _ld18, Math.fma(_ld8, _ld19, Math.fma(_ld6, _ld20, mData[14])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = Math.fma(_ld24, java.lang.Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(_ld25, java.lang.Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4e1499f_5(Float4x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_ld6, _ld15, Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = Math.fma(_ld0, _ld15, Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = Math.fma(_ld9, _ld15, Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_s4e1499f_2(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Float4x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _ld0 = mData[8];
        float _ld1 = sd[5];
        float _ld2 = mData[0];
        float _ld3 = sd[3];
        float _ld4 = mData[4];
        float _ld5 = sd[4];
        float _ld6 = mData[10];
        float _ld7 = mData[2];
        float _ld8 = mData[6];
        float _ld9 = mData[9];
        float _ld10 = mData[1];
        float _ld11 = mData[5];
        float _ld12 = sd[8];
        float _ld13 = sd[6];
        float _ld14 = sd[7];
        return transform_s49881fc_5(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[13], mData[14], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, Math.fma(_ld0, _ld1, Math.fma(_ld2, _ld3, _ld4 * _ld5)), Math.fma(_ld6, _ld1, Math.fma(_ld7, _ld3, _ld8 * _ld5)), Math.fma(_ld9, _ld1, Math.fma(_ld10, _ld3, _ld11 * _ld5)), Math.fma(_ld0, _ld12, Math.fma(_ld2, _ld13, _ld4 * _ld14)), Math.fma(_ld6, _ld12, Math.fma(_ld7, _ld13, _ld8 * _ld14)), Math.fma(_ld9, _ld12, Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private void transform_s49881fc_2(float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, double[] dd) {
        dd[0] = Math.fma(_ld2, _ld18, Math.fma(_ld4, _ld19, Math.fma(_ld0, _ld20, mData[12])));
        dd[1] = Math.fma(_ld10, _ld18, Math.fma(_ld11, _ld19, Math.fma(_ld9, _ld20, _ld22)));
        dd[2] = Math.fma(_ld7, _ld18, Math.fma(_ld8, _ld19, Math.fma(_ld6, _ld20, _ld23)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s49881fc_5(Float4x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_ld6, _ld15, Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = Math.fma(_ld0, _ld15, Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = Math.fma(_ld9, _ld15, Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_s49881fc_2(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate(Float4x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_s5c5eb0bc_1(dest, sd, mData, ((FloatOBBImpl) dest).data, Math.fma(mData[10], sd[5], Math.fma(mData[2], sd[3], mData[6] * sd[4])), Math.fma(mData[8], sd[5], Math.fma(mData[0], sd[3], mData[4] * sd[4])), Math.fma(mData[9], sd[5], Math.fma(mData[1], sd[3], mData[5] * sd[4])), Math.fma(mData[10], sd[8], Math.fma(mData[2], sd[6], mData[6] * sd[7])), Math.fma(mData[8], sd[8], Math.fma(mData[0], sd[6], mData[4] * sd[7])), Math.fma(mData[9], sd[8], Math.fma(mData[1], sd[6], mData[5] * sd[7])), Math.fma(mData[10], sd[11], Math.fma(mData[2], sd[9], mData[6] * sd[10])), Math.fma(mData[8], sd[11], Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_1(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(mData[9], sd[11], Math.fma(mData[1], sd[9], mData[5] * sd[10]));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t39 = _t18 * _t27;
        float _t40 = _t19 * _t27;
        float _t41 = _t20 * _t27;
        float _t42 = _t21 * _t28;
        float _t43 = _t22 * _t28;
        float _t44 = _t23 * _t28;
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t63, _t64, _t65;
        if (Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0f;
                    _t64 = 1.0f;
                    _t65 = 0.0f;
                }
            }
        }
        return transform_degenerate_s5c5eb0bc_2(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t63, _t64, _t65);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_2(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t63, float _t64, float _t65) {
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        float _t70 = _t69 * _t64;
        float _t71 = _t69 * _t65;
        float _t72 = _t69 * _t63;
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_s5c5eb0bc_3(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_3(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110) {
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t129 = _t122 * _t93;
                    _t130 = _t122 * _t92;
                    _t131 = _t122 * _t94;
                } else {
                    _t129 = _t122 * _t96;
                    _t130 = _t122 * _t95;
                    _t131 = _t122 * _t97;
                }
            } else {
                _t129 = _t122 * _t99;
                _t130 = _t122 * _t98;
                _t131 = _t122 * _t100;
            }
        } else {
            _t129 = _t110 * _t83;
            _t130 = _t110 * _t84;
            _t131 = _t110 * _t91;
        }
        return transform_degenerate_s5c5eb0bc_4(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /** Piece 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_4(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        float _buf0 = Math.fma(mData[0], sd[0], Math.fma(mData[4], sd[1], Math.fma(mData[8], sd[2], mData[12])));
        float _buf1 = Math.fma(mData[1], sd[0], Math.fma(mData[5], sd[1], Math.fma(mData[9], sd[2], mData[13])));
        dd[2] = Math.fma(mData[2], sd[0], Math.fma(mData[6], sd[1], Math.fma(mData[10], sd[2], mData[14])));
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t129;
        dd[7] = _t131;
        dd[8] = _t130;
        dd[9] = _t140;
        dd[10] = _t138;
        dd[11] = _t139;
        return transform_degenerate_s8ab82e23_5(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t129, _t130, _t131, _t138, _t139, _t140, _buf0, _buf1, Math.fma(sd[14], java.lang.Math.abs(Math.fma(_t24, _t72, Math.fma(_t25, _t70, _t26 * _t71))), Math.fma(sd[12], java.lang.Math.abs(Math.fma(_t18, _t72, Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(Math.fma(_t21, _t72, Math.fma(_t22, _t70, _t23 * _t71))))));
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Float4x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_sba294bd5_1(dest, sd, mData, ((DoubleOBBImpl) dest).data, Math.fma(mData[10], sd[5], Math.fma(mData[2], sd[3], mData[6] * sd[4])), Math.fma(mData[8], sd[5], Math.fma(mData[0], sd[3], mData[4] * sd[4])), Math.fma(mData[9], sd[5], Math.fma(mData[1], sd[3], mData[5] * sd[4])), Math.fma(mData[10], sd[8], Math.fma(mData[2], sd[6], mData[6] * sd[7])), Math.fma(mData[8], sd[8], Math.fma(mData[0], sd[6], mData[4] * sd[7])), Math.fma(mData[9], sd[8], Math.fma(mData[1], sd[6], mData[5] * sd[7])), Math.fma(mData[10], sd[11], Math.fma(mData[2], sd[9], mData[6] * sd[10])), Math.fma(mData[8], sd[11], Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_1(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(mData[9], sd[11], Math.fma(mData[1], sd[9], mData[5] * sd[10]));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t39 = _t18 * _t27;
        float _t40 = _t19 * _t27;
        float _t41 = _t20 * _t27;
        float _t42 = _t21 * _t28;
        float _t43 = _t22 * _t28;
        float _t44 = _t23 * _t28;
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t63, _t64, _t65;
        if (Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0f;
                    _t64 = 1.0f;
                    _t65 = 0.0f;
                }
            }
        }
        return transform_degenerate_sba294bd5_2(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t63, _t64, _t65);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_2(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t63, float _t64, float _t65) {
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        float _t70 = _t69 * _t64;
        float _t71 = _t69 * _t65;
        float _t72 = _t69 * _t63;
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_sba294bd5_3(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_3(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110) {
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t129 = _t122 * _t93;
                    _t130 = _t122 * _t92;
                    _t131 = _t122 * _t94;
                } else {
                    _t129 = _t122 * _t96;
                    _t130 = _t122 * _t95;
                    _t131 = _t122 * _t97;
                }
            } else {
                _t129 = _t122 * _t99;
                _t130 = _t122 * _t98;
                _t131 = _t122 * _t100;
            }
        } else {
            _t129 = _t110 * _t83;
            _t130 = _t110 * _t84;
            _t131 = _t110 * _t91;
        }
        return transform_degenerate_sba294bd5_4(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /** Piece 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_4(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        dd[0] = Math.fma(mData[0], sd[0], Math.fma(mData[4], sd[1], Math.fma(mData[8], sd[2], mData[12])));
        dd[1] = Math.fma(mData[1], sd[0], Math.fma(mData[5], sd[1], Math.fma(mData[9], sd[2], mData[13])));
        dd[2] = Math.fma(mData[2], sd[0], Math.fma(mData[6], sd[1], Math.fma(mData[10], sd[2], mData[14])));
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t129;
        dd[7] = _t131;
        dd[8] = _t130;
        dd[9] = _t140;
        dd[10] = _t138;
        dd[11] = _t139;
        return transform_degenerate_s374590_5(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, _t138, _t139, _t140);
    }


    /**
     * Translate this oriented bounding box by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB translate(Float3R delta, @Mutated FloatOBB dest) {
        float deltaY = delta.y();
        float deltaZ = delta.z();
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = delta.x() + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        FloatVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        FloatVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }


    /**
     * Translate this oriented bounding box by {@code delta} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB translate(Float3R delta, @Mutated DoubleOBB dest) {
        float deltaY = delta.y();
        float deltaZ = delta.z();
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = delta.x() + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Translate this oriented bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB translate(float deltaX, float deltaY, float deltaZ, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        FloatVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        FloatVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }


    /**
     * Translate this oriented bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB translate(float deltaX, float deltaY, float deltaZ, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = java.lang.Math.max(-sd[12], java.lang.Math.min(Math.fma(sd[5], _t3, Math.fma(sd[3], _t4, sd[4] * _t5)), sd[12]));
        float _t19 = java.lang.Math.max(-sd[13], java.lang.Math.min(Math.fma(sd[8], _t3, Math.fma(sd[6], _t4, sd[7] * _t5)), sd[13]));
        float _t20 = java.lang.Math.max(-sd[14], java.lang.Math.min(Math.fma(sd[11], _t3, Math.fma(sd[9], _t4, sd[10] * _t5)), sd[14]));
        dd[0] = Math.fma(sd[3], _t18, Math.fma(sd[6], _t19, Math.fma(sd[9], _t20, sd[0])));
        dd[1] = Math.fma(sd[4], _t18, Math.fma(sd[7], _t19, Math.fma(sd[10], _t20, sd[1])));
        dd[2] = Math.fma(sd[5], _t18, Math.fma(sd[8], _t19, Math.fma(sd[11], _t20, sd[2])));
        return dest;
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = java.lang.Math.max(-sd[12], java.lang.Math.min(Math.fma(sd[5], _t3, Math.fma(sd[3], _t4, sd[4] * _t5)), sd[12]));
        float _t19 = java.lang.Math.max(-sd[13], java.lang.Math.min(Math.fma(sd[8], _t3, Math.fma(sd[6], _t4, sd[7] * _t5)), sd[13]));
        float _t20 = java.lang.Math.max(-sd[14], java.lang.Math.min(Math.fma(sd[11], _t3, Math.fma(sd[9], _t4, sd[10] * _t5)), sd[14]));
        dd[0] = Math.fma(sd[3], _t18, Math.fma(sd[6], _t19, Math.fma(sd[9], _t20, sd[0])));
        dd[1] = Math.fma(sd[4], _t18, Math.fma(sd[7], _t19, Math.fma(sd[10], _t20, sd[1])));
        dd[2] = Math.fma(sd[5], _t18, Math.fma(sd[8], _t19, Math.fma(sd[11], _t20, sd[2])));
        return dest;
    }


    /**
     * Determine whether this oriented bounding box contains the given point (boundary inclusive).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative.
     *
     * @param p the point to test
     * @return {@code true} if this oriented bounding box contains the given point (boundary
     *        inclusive), {@code false} otherwise
     */
    public boolean containsPoint(Float3R p) {
        float[] sd = this.data;
        float _t0 = p.z() - sd[2];
        float _t1 = p.x() - sd[0];
        float _t2 = p.y() - sd[1];
        if (!(java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
    }


    /**
     * Determine whether this oriented bounding box contains the given point (boundary inclusive).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return {@code true} if this oriented bounding box contains the given point (boundary
     *        inclusive), {@code false} otherwise
     */
    public boolean containsPoint(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
    }


    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this oriented bounding box and the given point,
     *        evaluated in the box's local frame; zero for a point inside or on the box. Assumes the
     *        box's axes are orthonormal
     */
    public float distanceSquaredToPoint(Float3R p) {
        float[] sd = this.data;
        float _t0 = p.z() - sd[2];
        float _t1 = p.x() - sd[0];
        float _t2 = p.y() - sd[1];
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
    }


    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this oriented bounding box and the given point,
     *        evaluated in the box's local frame; zero for a point inside or on the box. Assumes the
     *        box's axes are orthonormal
     */
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
    }


    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the distance between this oriented bounding box and the given point, evaluated in the
     *        box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     *        orthonormal
     */
    public float distanceToPoint(Float3R p) {
        float[] sd = this.data;
        float _t0 = p.z() - sd[2];
        float _t1 = p.x() - sd[0];
        float _t2 = p.y() - sd[1];
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return (float) java.lang.Math.sqrt(Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
    }


    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this oriented bounding box and the given point, evaluated in the
     *        box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     *        orthonormal
     */
    public float distanceToPoint(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[11], _t0, Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[5], _t0, Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(sd[8], _t0, Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return (float) java.lang.Math.sqrt(Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
    }


    /**
     * Get the local {@code X} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getAxisX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }


    /**
     * Get the local {@code X} axis of this oriented bounding box and store the result in
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
    public Double3 getAxisX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }


    /**
     * Get the local {@code Y} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getAxisY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }


    /**
     * Get the local {@code Y} axis of this oriented bounding box and store the result in
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
    public Double3 getAxisY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }


    /**
     * Get the local {@code Z} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getAxisZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[9];
        dd[1] = sd[10];
        dd[2] = sd[11];
        return dest;
    }


    /**
     * Get the local {@code Z} axis of this oriented bounding box and store the result in
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
    public Double3 getAxisZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[9];
        dd[1] = sd[10];
        dd[2] = sd[11];
        return dest;
    }


    /**
     * Get the center of this oriented bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCenter(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the center of this oriented bounding box and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the half extents of this oriented bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getHalfSize(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[12];
        dd[1] = sd[13];
        dd[2] = sd[14];
        return dest;
    }


    /**
     * Get the half extents of this oriented bounding box and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getHalfSize(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[12];
        dd[1] = sd[13];
        dd[2] = sd[14];
        return dest;
    }


    /**
     * Determine whether this oriented bounding box intersects {@code o}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of {@code o} must
     * be orthonormal; each component of the half sizes of {@code o} must not be negative.
     *
     * @param o the oriented bounding box to test for intersection
     * @return {@code true} if this oriented bounding box intersects {@code o}, {@code false}
     *        otherwise
     */
    public boolean intersectsOBB(FloatOBBR o) {
        return intersectsOBB(o.cX(), o.cY(), o.cZ(), o.uXx(), o.uXy(), o.uXz(), o.uYx(), o.uYy(), o.uYz(), o.uZx(), o.uZy(), o.uZz(), o.hsX(), o.hsY(), o.hsZ());
    }


    /**
     * Determine whether this oriented bounding box intersects ({@code oCX}, {@code oCY},
     * {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     * {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     * {@code oHsZ}).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of
     * {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * must be orthonormal; each component of {@code (oHsX, oHsY, oHsZ)} must not be negative.
     *
     * @param oCX the {@code cX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCY the {@code cY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCZ the {@code cZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXx the {@code uXx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXy the {@code uXy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXz the {@code uXz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYx the {@code uYx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYy the {@code uYy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYz the {@code uYz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZx the {@code uZx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZy the {@code uZy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZz the {@code uZz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsX the {@code hsX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsY the {@code hsY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @return {@code true} if this oriented bounding box intersects ({@code oCX}, {@code oCY},
     *        {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     *        {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     *        {@code oHsZ}), {@code false} otherwise
     */
    public boolean intersectsOBB(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float[] sd = this.data;
        float _ld3 = sd[5];
        float _ld4 = sd[3];
        float _ld5 = sd[4];
        float _ld6 = sd[12];
        float _t0 = oCZ - sd[2];
        float _t1 = oCX - sd[0];
        float _t2 = oCY - sd[1];
        float _t54 = Math.fma(_ld3, _t0, Math.fma(_ld4, _t1, _ld5 * _t2));
        float _t24 = Math.fma(oUXz, _ld3, Math.fma(oUXx, _ld4, oUXy * _ld5));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = Math.fma(oUYz, _ld3, Math.fma(oUYx, _ld4, oUYy * _ld5));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = Math.fma(oUZz, _ld3, Math.fma(oUZx, _ld4, oUZy * _ld5));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= Math.fma(oHsX, _t45, Math.fma(oHsY, _t46, Math.fma(oHsZ, _t47, _ld6))))) return false;
        return intersectsOBB_s743cf6d4_2(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _ld6, sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_1(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t25, float _t26, float _t55, float _t27, float _t28, float _t29, float _t56, float _t30, float _t31, float _t32) {
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        if (!(java.lang.Math.abs(Math.fma(_t32, _t54, -(_t26 * _t56))) <= Math.fma(oHsX, _t49, oHsY * _t48) + Math.fma(_ld6, _t53, _ld14 * _t47))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t24, _t55, -(_t27 * _t54))) <= Math.fma(oHsY, _t53, oHsZ * _t52) + Math.fma(_ld6, _t48, _ld10 * (java.lang.Math.abs(_t24) + 1.0E-5f)))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t25, _t55, -(_t28 * _t54))) <= Math.fma(oHsX, _t53, oHsZ * _t51) + Math.fma(_ld6, _t49, _ld10 * (java.lang.Math.abs(_t25) + 1.0E-5f)))) return false;
        return java.lang.Math.abs(Math.fma(_t26, _t55, -(_t29 * _t54))) <= Math.fma(oHsX, _t52, oHsY * _t51) + Math.fma(_ld6, (java.lang.Math.abs(_t29) + 1.0E-5f), _ld10 * _t47);
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_2(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47) {
        float _t55 = Math.fma(_ld7, _t0, Math.fma(_ld8, _t1, _ld9 * _t2));
        float _t27 = Math.fma(oUXz, _ld7, Math.fma(oUXx, _ld8, oUXy * _ld9));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = Math.fma(oUYz, _ld7, Math.fma(oUYx, _ld8, oUYy * _ld9));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = Math.fma(oUZz, _ld7, Math.fma(oUZx, _ld8, oUZy * _ld9));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= Math.fma(oHsX, _t48, Math.fma(oHsY, _t49, Math.fma(oHsZ, _t50, _ld10))))) return false;
        float _t30 = Math.fma(oUXz, _ld11, Math.fma(oUXx, _ld12, oUXy * _ld13));
        float _t31 = Math.fma(oUYz, _ld11, Math.fma(oUYx, _ld12, oUYy * _ld13));
        float _t32 = Math.fma(oUZz, _ld11, Math.fma(oUZx, _ld12, oUZy * _ld13));
        return intersectsOBB_s743cf6d4_3(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, Math.fma(_ld11, _t0, Math.fma(_ld12, _t1, _ld13 * _t2)), _t30, java.lang.Math.abs(_t30) + 1.0E-5f, _t31, java.lang.Math.abs(_t31) + 1.0E-5f, _t32, java.lang.Math.abs(_t32) + 1.0E-5f);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_3(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(_t56) <= Math.fma(oHsX, _t51, Math.fma(oHsY, _t52, Math.fma(oHsZ, _t53, _ld14))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t30, _t56, Math.fma(_t24, _t54, _t27 * _t55))) <= Math.fma(_ld6, _t45, Math.fma(_ld10, _t48, Math.fma(_ld14, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t31, _t56, Math.fma(_t25, _t54, _t28 * _t55))) <= Math.fma(_ld6, _t46, Math.fma(_ld10, _t49, Math.fma(_ld14, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t32, _t56, Math.fma(_t26, _t54, _t29 * _t55))) <= Math.fma(_ld6, _t47, Math.fma(_ld10, _t50, Math.fma(_ld14, _t53, oHsZ))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t27, _t56, -(_t30 * _t55))) <= Math.fma(oHsY, _t47, oHsZ * _t46) + Math.fma(_ld10, _t51, _ld14 * _t48))) return false;
        return intersectsOBB_s743cf6d4_4(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_4(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(Math.fma(_t28, _t56, -(_t31 * _t55))) <= Math.fma(oHsX, _t47, oHsZ * _t45) + Math.fma(_ld10, _t52, _ld14 * _t49))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t29, _t56, -(_t32 * _t55))) <= Math.fma(oHsX, _t46, oHsY * _t45) + Math.fma(_ld10, _t53, _ld14 * _t50))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t30, _t54, -(_t24 * _t56))) <= Math.fma(oHsY, _t50, oHsZ * _t49) + Math.fma(_ld6, _t51, _ld14 * _t45))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t31, _t54, -(_t25 * _t56))) <= Math.fma(oHsX, _t50, oHsZ * _t48) + Math.fma(_ld6, _t52, _ld14 * _t46))) return false;
        return intersectsOBB_s743cf6d4_1(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }


    /**
     * Determine whether this oriented bounding box is valid, i.e. none of its half extents is
     * negative.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this oriented bounding box is valid, i.e. none of its half extents is
     *        negative, {@code false} otherwise
     */
    public boolean isValid() {
        float[] sd = this.data;
        if (!(sd[12] >= 0.0f)) return false;
        if (!(sd[13] >= 0.0f)) return false;
        return sd[14] >= 0.0f;
    }

    public float cX() { return data[0]; }
    public float cY() { return data[1]; }
    public float cZ() { return data[2]; }
    public float uXx() { return data[3]; }
    public float uXy() { return data[4]; }
    public float uXz() { return data[5]; }
    public float uYx() { return data[6]; }
    public float uYy() { return data[7]; }
    public float uYz() { return data[8]; }
    public float uZx() { return data[9]; }
    public float uZy() { return data[10]; }
    public float uZz() { return data[11]; }
    public float hsX() { return data[12]; }
    public float hsY() { return data[13]; }
    public float hsZ() { return data[14]; }

    @Override public String toString() {
        return "FloatOBB(" + cX() + ", " + cY() + ", " + cZ() + ", " + uXx() + ", " + uXy() + ", " + uXz() + ", " + uYx() + ", " + uYy() + ", " + uYz() + ", " + uZx() + ", " + uZy() + ", " + uZz() + ", " + hsX() + ", " + hsY() + ", " + hsZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatOBBImpl)) return false;
        FloatOBBImpl o = (FloatOBBImpl) obj;
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
            && Float.isFinite(data[8])
            && Float.isFinite(data[9])
            && Float.isFinite(data[10])
            && Float.isFinite(data[11])
            && Float.isFinite(data[12])
            && Float.isFinite(data[13])
            && Float.isFinite(data[14]);
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
            || Float.isNaN(data[8])
            || Float.isNaN(data[9])
            || Float.isNaN(data[10])
            || Float.isNaN(data[11])
            || Float.isNaN(data[12])
            || Float.isNaN(data[13])
            || Float.isNaN(data[14]);
    }

    @Override public boolean equalsEpsilon(FloatOBBR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.cX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.cY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.cZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.uXx()) <= epsilon
            && java.lang.Math.abs(data[4] - other.uXy()) <= epsilon
            && java.lang.Math.abs(data[5] - other.uXz()) <= epsilon
            && java.lang.Math.abs(data[6] - other.uYx()) <= epsilon
            && java.lang.Math.abs(data[7] - other.uYy()) <= epsilon
            && java.lang.Math.abs(data[8] - other.uYz()) <= epsilon
            && java.lang.Math.abs(data[9] - other.uZx()) <= epsilon
            && java.lang.Math.abs(data[10] - other.uZy()) <= epsilon
            && java.lang.Math.abs(data[11] - other.uZz()) <= epsilon
            && java.lang.Math.abs(data[12] - other.hsX()) <= epsilon
            && java.lang.Math.abs(data[13] - other.hsY()) <= epsilon
            && java.lang.Math.abs(data[14] - other.hsZ()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        dest[offset + 10] = this.data[10];
        dest[offset + 11] = this.data[11];
        dest[offset + 12] = this.data[12];
        dest[offset + 13] = this.data[13];
        dest[offset + 14] = this.data[14];
        return dest;
    }
    public @Mutated FloatOBB load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        this.data[10] = src[offset + 10];
        this.data[11] = src[offset + 11];
        this.data[12] = src[offset + 12];
        this.data[13] = src[offset + 13];
        this.data[14] = src[offset + 14];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return buf;
    }
    @Mutated public FloatOBB load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatOBB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatOBB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return buf;
    }
    public FloatOBB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatOBB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatOBB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return r;
    }
    public FloatOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatOBB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatOBB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatOBB load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        dest[offset + 10] = this.data[10];
        dest[offset + 11] = this.data[11];
        dest[offset + 12] = this.data[12];
        dest[offset + 13] = this.data[13];
        dest[offset + 14] = this.data[14];
        return dest;
    }
    public @Mutated FloatOBB load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        this.data[9] = (float) src[offset + 9];
        this.data[10] = (float) src[offset + 10];
        this.data[11] = (float) src[offset + 11];
        this.data[12] = (float) src[offset + 12];
        this.data[13] = (float) src[offset + 13];
        this.data[14] = (float) src[offset + 14];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return buf;
    }
    @Mutated public FloatOBB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatOBB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatOBB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return buf;
    }
    public FloatOBB loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatOBB loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return r;
    }
    public FloatOBB storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatOBB loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatOBB loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatOBB loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_0 = FloatVector.fromArray(COL_SPECIES, new float[]{1.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final float[] DATA_1 = new float[] {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f};

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
}
