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
import org.joml2.internal.unsafe.HeapSegments;
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
        static final FloatOBBSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatOBBSegOpsUnsafe()
            : new FloatOBBSegOpsMS();
        static final FloatOBBBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatOBBBbOpsUnsafe()
            : new FloatOBBBbOpsApi();
        static final FloatOBBRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        float[] srcData = ((FloatOBBImpl) src).data;
        float[] dd = this.data = new float[15];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
        dd[6] = srcData[6];
        dd[7] = srcData[7];
        dd[8] = srcData[8];
        dd[9] = srcData[9];
        dd[10] = srcData[10];
        dd[11] = srcData[11];
        dd[12] = srcData[12];
        dd[13] = srcData[13];
        dd[14] = srcData[14];
    }

    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return this
     */
    @Mutated public FloatOBB set(FloatOBBR v) {
        float[] vData = ((FloatOBBImpl) v).data;
        float vCY = vData[1];
        float vCZ = vData[2];
        float vUXx = vData[3];
        float vUXy = vData[4];
        float vUXz = vData[5];
        float vUYx = vData[6];
        float vUYy = vData[7];
        float vUYz = vData[8];
        float vUZx = vData[9];
        float vUZy = vData[10];
        float vUZz = vData[11];
        float vHsX = vData[12];
        float vHsY = vData[13];
        float vHsZ = vData[14];
        float[] dd = this.data;
        dd[0] = vData[0];
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
     * and {@code axisZ}, and store the result in {@code dest}.
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
        float[] axisXData = ((Float3Impl) axisX).data;
        float[] axisYData = ((Float3Impl) axisY).data;
        float[] axisZData = ((Float3Impl) axisZ).data;
        float axisXX = axisXData[0];
        float axisXY = axisXData[1];
        float axisXZ = axisXData[2];
        float axisYX = axisYData[0];
        float axisYY = axisYData[1];
        float axisYZ = axisYData[2];
        float axisZX = axisZData[0];
        float axisZY = axisZData[1];
        float axisZZ = axisZData[2];
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
     * and {@code axisZ}, and store the result in {@code dest}.
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
        float[] axisXData = ((Float3Impl) axisX).data;
        float[] axisYData = ((Float3Impl) axisY).data;
        float[] axisZData = ((Float3Impl) axisZ).data;
        float axisXX = axisXData[0];
        float axisXY = axisXData[1];
        float axisXZ = axisXData[2];
        float axisYX = axisYData[0];
        float axisYY = axisYData[1];
        float axisYZ = axisYData[2];
        float axisZX = axisZData[0];
        float axisZY = axisZData[1];
        float axisZZ = axisZData[2];
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
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}), and store the result in {@code dest}.
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
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}), and store the result in {@code dest}.
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
        float[] cData = ((Float3Impl) c).data;
        float cY = cData[1];
        float cZ = cData[2];
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = cData[0];
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
        float[] cData = ((Float3Impl) c).data;
        float cY = cData[1];
        float cZ = cData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cData[0];
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
        float[] hData = ((Float3Impl) h).data;
        float hX = hData[0];
        float hY = hData[1];
        float hZ = hData[2];
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
        float[] hData = ((Float3Impl) h).data;
        float hX = hData[0];
        float hY = hData[1];
        float hZ = hData[2];
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
        float[] qData = ((FloatQuatImpl) q).data;
        float qX = qData[0];
        float qY = qData[1];
        float qZ = qData[2];
        float qW = qData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return setOrientation_mulAdd(qX, qY, qZ, qW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * java.lang.Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * java.lang.Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * java.lang.Math.fma(qX, qY, -_t1);
        dd[7] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * java.lang.Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * java.lang.Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW));
        dd[11] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f);
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
        float[] qData = ((FloatQuatImpl) q).data;
        return setOrientation(qData[0], qData[1], qData[2], qData[3], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return setOrientation_mulAdd(qX, qY, qZ, qW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * java.lang.Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * java.lang.Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * java.lang.Math.fma(qX, qY, -_t1);
        dd[7] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * java.lang.Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * java.lang.Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW));
        dd[11] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /** {@code setOrientation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB setOrientation_mulAdd(float qX, float qY, float qZ, float qW, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0))));
        dd[4] = 2.0f * ((qX) * (qY) + (_t1));
        dd[5] = 2.0f * ((qX) * (qZ) - (_t2));
        dd[6] = 2.0f * ((qX) * (qY) - (_t1));
        dd[7] = ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0))));
        dd[8] = 2.0f * ((qX) * (qW) + (qY * qZ));
        dd[9] = 2.0f * ((qX) * (qZ) + (_t2));
        dd[10] = 2.0f * ((qY) * (qZ) - (qX * qW));
        dd[11] = ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return setOrientation_mulAdd(qX, qY, qZ, qW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f);
        dd[4] = 2.0f * java.lang.Math.fma(qX, qY, _t1);
        dd[5] = 2.0f * java.lang.Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0f * java.lang.Math.fma(qX, qY, -_t1);
        dd[7] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f);
        dd[8] = 2.0f * java.lang.Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0f * java.lang.Math.fma(qX, qZ, _t2);
        dd[10] = 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW));
        dd[11] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /** {@code setOrientation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB setOrientation_mulAdd(float qX, float qY, float qZ, float qW, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0))));
        dd[4] = 2.0f * ((qX) * (qY) + (_t1));
        dd[5] = 2.0f * ((qX) * (qZ) - (_t2));
        dd[6] = 2.0f * ((qX) * (qY) - (_t1));
        dd[7] = ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0))));
        dd[8] = 2.0f * ((qX) * (qW) + (qY * qZ));
        dd[9] = 2.0f * ((qX) * (qZ) + (_t2));
        dd[10] = 2.0f * ((qY) * (qZ) - (qX * qW));
        dd[11] = ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY))));
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
     * Set this oriented bounding box to zero center and half-size and identity axes (a point box at
     * the origin).
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
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB transform(Float3x4R m, @Mutated FloatOBB dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
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
        return transform_s84fb42d0_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float3x4R m, @Mutated FloatOBB dest) {
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
        return transform_s84fb42d0_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 8 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float transform_s84fb42d0_1_fma(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t37 = java.lang.Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = java.lang.Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        return java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 8 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float transform_s84fb42d0_1_mulAdd(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t37 = ((_t21) * (_t19) - (_t18 * _t22));
        float _t38 = ((_t18) * (_t23) - (_t21 * _t20));
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        return ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_2_fma(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, mData[11])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (java.lang.Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (java.lang.Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (java.lang.Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_2_mulAdd(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        float _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        float _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (mData[11]))))));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (((_t46) * (_t53) - (_t45 * _t54)));
        dd[7] = (((_t44) * (_t54) - (_t46 * _t55)));
        dd[8] = (((_t45) * (_t55) - (_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_3_fma(float _ld0, float _ld2, float _ld4, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float[] dd) {
        float _buf0 = java.lang.Math.fma(_ld2, _ld18, java.lang.Math.fma(_ld4, _ld19, java.lang.Math.fma(_ld0, _ld20, _ld21)));
        float _buf1 = java.lang.Math.fma(_ld10, _ld18, java.lang.Math.fma(_ld11, _ld19, java.lang.Math.fma(_ld9, _ld20, _ld22)));
        dd[0] = _buf0;
        dd[1] = _buf1;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_4_fma(float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        float _t62 = java.lang.Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        float _buf2 = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
        float _buf3 = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        dd[12] = _buf2;
        dd[13] = _buf3;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s84fb42d0_4_mulAdd(float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        float _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        float _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        float _t62 = ((_t46) * (_t53) - (_t45 * _t54));
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        float _buf2 = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
        float _buf3 = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        dd[12] = _buf2;
        dd[13] = _buf3;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s84fb42d0_5_fma(Float3x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s84fb42d0_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s84fb42d0_5_mulAdd(Float3x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s84fb42d0_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        {
            float _buf0 = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (_ld21))))));
            float _buf1 = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        transform_s84fb42d0_4_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
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
        return transform_s9ad3639_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[7], mData[11], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float3x4R m, @Mutated DoubleOBB dest) {
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
        return transform_s9ad3639_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[7], mData[11], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s9ad3639_2_fma(float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, double[] dd) {
        dd[0] = java.lang.Math.fma(_ld2, _ld18, java.lang.Math.fma(_ld4, _ld19, java.lang.Math.fma(_ld0, _ld20, mData[3])));
        dd[1] = java.lang.Math.fma(_ld10, _ld18, java.lang.Math.fma(_ld11, _ld19, java.lang.Math.fma(_ld9, _ld20, _ld22)));
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, _ld23)));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s9ad3639_3_fma(float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (java.lang.Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (java.lang.Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (java.lang.Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[12] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s9ad3639_3_mulAdd(float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        float _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        float _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (((_t46) * (_t53) - (_t45 * _t54)));
        dd[7] = (((_t44) * (_t54) - (_t46 * _t55)));
        dd[8] = (((_t45) * (_t55) - (_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[12] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_s9ad3639_4_fma(DoubleOBB dest, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        float _t62 = java.lang.Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        dd[13] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        dd[14] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
        return dest;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_s9ad3639_4_mulAdd(DoubleOBB dest, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        float _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        float _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        float _t62 = ((_t46) * (_t53) - (_t45 * _t54));
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        dd[13] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        dd[14] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9ad3639_5_fma(Float3x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s9ad3639_2_fma(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9ad3639_5_mulAdd(Float3x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        dd[0] = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (mData[3]))))));
        dd[1] = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (_ld23))))));
        transform_s9ad3639_3_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float3x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s8ab82e23_1_fma(dest, sd, mData, ((FloatOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float3x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s8ab82e23_1_mulAdd(dest, sd, mData, ((FloatOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_1_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_s8ab82e23_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_1_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_s8ab82e23_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_2_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s8ab82e23_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_2_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s8ab82e23_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_3_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s8ab82e23_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s8ab82e23_3_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s8ab82e23_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_4_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        float _buf0 = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], java.lang.Math.fma(mData[2], sd[2], mData[3])));
        float _buf1 = java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[6], sd[2], mData[7])));
        dd[2] = java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[11])));
        dd[3] = _t70;
        return transform_degenerate_s8ab82e23_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_4_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        float _buf0 = ((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (((mData[2]) * (sd[2]) + (mData[3]))))));
        float _buf1 = ((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[6]) * (sd[2]) + (mData[7]))))));
        dd[2] = ((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[11]))))));
        dd[3] = _t70;
        return transform_degenerate_s8ab82e23_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_5_fma(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143, float _buf0, float _buf1) {
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s8ab82e23_6_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71))))), java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134))))));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_5_mulAdd(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143, float _buf0, float _buf1) {
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s8ab82e23_6_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71))))))))), ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134))))))))));
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_6_fma(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t141, float _t142, float _t143, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[14] = java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t142, java.lang.Math.fma(_t25, _t143, _t26 * _t141))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t142, java.lang.Math.fma(_t19, _t143, _t20 * _t141))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t142, java.lang.Math.fma(_t22, _t143, _t23 * _t141)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
        dd[13] = _buf3;
        return dest;
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_6_mulAdd(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t141, float _t142, float _t143, float _buf0, float _buf1, float _buf2, float _buf3) {
        dd[14] = ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t142) + (((_t25) * (_t143) + (_t26 * _t141)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t142) + (((_t19) * (_t143) + (_t20 * _t141)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t142) + (((_t22) * (_t143) + (_t23 * _t141)))))))));
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
    private DoubleOBB transform_degenerate_fma(Float3x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s374590_1_fma(dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float3x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s374590_1_mulAdd(dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_1_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_s374590_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_1_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_s374590_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_2_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s374590_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_2_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s374590_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_3_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s374590_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s374590_3_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s374590_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_4_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        dd[0] = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], java.lang.Math.fma(mData[2], sd[2], mData[3])));
        dd[1] = java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[6], sd[2], mData[7])));
        dd[2] = java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[11])));
        return transform_degenerate_s374590_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_4_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        dd[0] = ((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (((mData[2]) * (sd[2]) + (mData[3]))))));
        dd[1] = ((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[6]) * (sd[2]) + (mData[7]))))));
        dd[2] = ((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[11]))))));
        return transform_degenerate_s374590_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_5_fma(DoubleOBB dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        dd[12] = java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71)))));
        dd[13] = java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134)))));
        return transform_degenerate_s374590_6_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_5_mulAdd(DoubleOBB dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        dd[12] = ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71)))))))));
        dd[13] = ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134)))))))));
        return transform_degenerate_s374590_6_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143);
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_6_fma(DoubleOBB dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t141, float _t142, float _t143) {
        dd[14] = java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t142, java.lang.Math.fma(_t25, _t143, _t26 * _t141))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t142, java.lang.Math.fma(_t19, _t143, _t20 * _t141))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t142, java.lang.Math.fma(_t22, _t143, _t23 * _t141)))));
        return dest;
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_6_mulAdd(DoubleOBB dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t141, float _t142, float _t143) {
        dd[14] = ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t142) + (((_t25) * (_t143) + (_t26 * _t141)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t142) + (((_t19) * (_t143) + (_t20 * _t141)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t142) + (((_t22) * (_t143) + (_t23 * _t141)))))))));
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
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
        return transform_s4e1499f_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float4x4R m, @Mutated FloatOBB dest) {
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
        return transform_s4e1499f_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s4e1499f_2_fma(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        float _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        float _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, mData[14])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (java.lang.Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (java.lang.Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (java.lang.Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s4e1499f_2_mulAdd(float[] mData, float _ld6, float _ld7, float _ld8, float _ld18, float _ld19, float _ld20, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t40, float _ct0) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        float _t44 = _t18 * _t42;
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        float _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        float _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (mData[14]))))));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (((_t46) * (_t53) - (_t45 * _t54)));
        dd[7] = (((_t44) * (_t54) - (_t46 * _t55)));
        dd[8] = (((_t45) * (_t55) - (_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4e1499f_5_fma(Float4x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s4e1499f_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4e1499f_5_mulAdd(Float4x4R m, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s4e1499f_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        {
            float _buf0 = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (_ld21))))));
            float _buf1 = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        transform_s84fb42d0_4_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
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
        return transform_s49881fc_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[13], mData[14], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float4x4R m, @Mutated DoubleOBB dest) {
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
        return transform_s49881fc_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[13], mData[14], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s49881fc_2_fma(float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, double[] dd) {
        dd[0] = java.lang.Math.fma(_ld2, _ld18, java.lang.Math.fma(_ld4, _ld19, java.lang.Math.fma(_ld0, _ld20, mData[12])));
        dd[1] = java.lang.Math.fma(_ld10, _ld18, java.lang.Math.fma(_ld11, _ld19, java.lang.Math.fma(_ld9, _ld20, _ld22)));
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, _ld23)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s49881fc_5_fma(Float4x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s49881fc_2_fma(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s49881fc_5_mulAdd(Float4x4R m, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        dd[0] = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (mData[12]))))));
        dd[1] = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (_ld23))))));
        transform_s9ad3639_3_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float4x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_s5c5eb0bc_1_fma(dest, sd, mData, ((FloatOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float4x4R m, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_s5c5eb0bc_1_mulAdd(dest, sd, mData, ((FloatOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_1_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_s5c5eb0bc_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_1_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_s5c5eb0bc_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_2_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s5c5eb0bc_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_2_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s5c5eb0bc_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_3_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s5c5eb0bc_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s5c5eb0bc_3_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s5c5eb0bc_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s5c5eb0bc_4_fma(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        float _buf0 = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], java.lang.Math.fma(mData[8], sd[2], mData[12])));
        float _buf1 = java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[9], sd[2], mData[13])));
        dd[2] = java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[14])));
        return transform_degenerate_s5c5eb0bc_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s5c5eb0bc_4_mulAdd(FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        float _buf0 = ((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (((mData[8]) * (sd[2]) + (mData[12]))))));
        float _buf1 = ((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[9]) * (sd[2]) + (mData[13]))))));
        dd[2] = ((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[14]))))));
        return transform_degenerate_s5c5eb0bc_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s5c5eb0bc_5_fma(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143, float _buf0, float _buf1) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s8ab82e23_6_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71))))), java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134))))));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s5c5eb0bc_5_mulAdd(FloatOBB dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t132, float _t133, float _t134, float _t141, float _t142, float _t143, float _buf0, float _buf1) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s8ab82e23_6_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71))))))))), ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134))))))))));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Float4x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_sba294bd5_1_fma(dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float4x4R m, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_sba294bd5_1_mulAdd(dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_1_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_sba294bd5_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_1_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_sba294bd5_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_2_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_sba294bd5_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_2_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_sba294bd5_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_3_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sba294bd5_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sba294bd5_3_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sba294bd5_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 1.4551915E-11f);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sba294bd5_4_fma(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        dd[0] = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], java.lang.Math.fma(mData[8], sd[2], mData[12])));
        dd[1] = java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[9], sd[2], mData[13])));
        dd[2] = java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[14])));
        return transform_degenerate_s374590_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sba294bd5_4_mulAdd(DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115, float _t116, float _t117, float _t122, float _t123, float _t125) {
        float _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        dd[0] = ((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (((mData[8]) * (sd[2]) + (mData[12]))))));
        dd[1] = ((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[9]) * (sd[2]) + (mData[13]))))));
        dd[2] = ((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[14]))))));
        return transform_degenerate_s374590_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)));
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB transform(Float3x4R m, float epsilon, @Mutated FloatOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float3x4R m, float epsilon, @Mutated FloatOBB dest) {
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
        return transform_sa731d976_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float3x4R m, float epsilon, @Mutated FloatOBB dest) {
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
        return transform_sa731d976_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_sa731d976_5_fma(Float3x4R m, float epsilon, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_s84fb42d0_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_sa731d976_5_mulAdd(Float3x4R m, float epsilon, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        transform_s84fb42d0_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        {
            float _buf0 = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (_ld21))))));
            float _buf1 = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        transform_s84fb42d0_4_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Float3x4R m, float epsilon, @Mutated DoubleOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Float3x4R m, float epsilon, @Mutated DoubleOBB dest) {
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
        return transform_sab94c423_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[7], mData[11], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float3x4R m, float epsilon, @Mutated DoubleOBB dest) {
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
        return transform_sab94c423_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[7], mData[11], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_sab94c423_5_fma(Float3x4R m, float epsilon, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_s9ad3639_2_fma(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_sab94c423_5_mulAdd(Float3x4R m, float epsilon, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        dd[0] = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (mData[3]))))));
        dd[1] = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (_ld23))))));
        transform_s9ad3639_3_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float3x4R m, float epsilon, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_sf014f4cf_1_fma(epsilon, dest, sd, mData, ((FloatOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float3x4R m, float epsilon, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_sf014f4cf_1_mulAdd(epsilon, dest, sd, mData, ((FloatOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_1_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_sf014f4cf_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_1_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_sf014f4cf_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_2_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_sf014f4cf_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_2_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_sf014f4cf_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_3_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s8ab82e23_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sf014f4cf_3_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s8ab82e23_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Float3x4R m, float epsilon, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s94c0acc_1_fma(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float3x4R m, float epsilon, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_degenerate_s94c0acc_1_mulAdd(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_1_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_s94c0acc_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_1_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_s94c0acc_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_2_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s94c0acc_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_2_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s94c0acc_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_3_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s374590_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s94c0acc_3_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s374590_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB transform(Float4x4R m, float epsilon, @Mutated FloatOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float4x4R m, float epsilon, @Mutated FloatOBB dest) {
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
        return transform_s4a408b5b_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float4x4R m, float epsilon, @Mutated FloatOBB dest) {
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
        return transform_s4a408b5b_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((FloatOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4a408b5b_5_fma(Float4x4R m, float epsilon, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_s4e1499f_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_s84fb42d0_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, dd);
        transform_s84fb42d0_4_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4a408b5b_5_mulAdd(Float4x4R m, float epsilon, FloatOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld21, float _ld22, float _ld24, float _ld25, float _ld26, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        transform_s4e1499f_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        {
            float _buf0 = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (_ld21))))));
            float _buf1 = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
        }
        transform_s84fb42d0_4_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Float4x4R m, float epsilon, @Mutated DoubleOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Float4x4R m, float epsilon, @Mutated DoubleOBB dest) {
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
        return transform_s6c33d438_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[13], mData[14], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float4x4R m, float epsilon, @Mutated DoubleOBB dest) {
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
        return transform_s6c33d438_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[13], mData[14], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s6c33d438_5_fma(Float4x4R m, float epsilon, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        float _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        float _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        float _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _ct0 = transform_s84fb42d0_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_s49881fc_2_fma(mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld22, _ld23, dd);
        transform_s9ad3639_3_fma(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s6c33d438_5_mulAdd(Float4x4R m, float epsilon, DoubleOBB dest, float[] mData, float _ld0, float _ld2, float _ld4, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _ld15, float _ld16, float _ld17, float _ld18, float _ld19, float _ld20, float _ld22, float _ld23, float _ld24, float _ld25, float _ld26, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        float _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        float _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        float _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _ct0 = transform_s84fb42d0_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        dd[0] = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (mData[12]))))));
        dd[1] = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (_ld23))))));
        transform_s9ad3639_3_mulAdd(_ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        return transform_s9ad3639_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float4x4R m, float epsilon, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_sa105ac72_1_fma(epsilon, dest, sd, mData, ((FloatOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float4x4R m, float epsilon, @Mutated FloatOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_sa105ac72_1_mulAdd(epsilon, dest, sd, mData, ((FloatOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_1_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_sa105ac72_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_1_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_sa105ac72_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_2_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_sa105ac72_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_2_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_sa105ac72_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_3_fma(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s5c5eb0bc_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_sa105ac72_3_mulAdd(float epsilon, FloatOBB dest, float[] sd, float[] mData, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s5c5eb0bc_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Float4x4R m, float epsilon, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_s36de4c5f_1_fma(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float4x4R m, float epsilon, @Mutated DoubleOBB dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_degenerate_s36de4c5f_1_mulAdd(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_1_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
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
        return transform_degenerate_s36de4c5f_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_1_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
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
        return transform_degenerate_s36de4c5f_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_2_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
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
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s36de4c5f_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_2_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56) {
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        float _t69 = (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
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
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s36de4c5f_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_3_fma(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        float _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        float _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        float _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sba294bd5_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s36de4c5f_3_mulAdd(float epsilon, DoubleOBB dest, float[] sd, float[] mData, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55, float _t56, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t88, float _t89, float _t90, float _t92, float _t93) {
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        float _t98 = ((_t90) * (_t72) + (_t39));
        float _t99 = ((_t90) * (_t70) + (_t40));
        float _t100 = ((_t90) * (_t71) + (_t41));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sba294bd5_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0f / (float) java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
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
        float[] deltaData = ((Float3Impl) delta).data;
        float deltaY = deltaData[1];
        float deltaZ = deltaData[2];
        float[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = deltaData[0] + sd[0];
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
        float[] deltaData = ((Float3Impl) delta).data;
        float deltaY = deltaData[1];
        float deltaZ = deltaData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaData[0] + sd[0];
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
        float[] pData = ((Float3Impl) p).data;
        return closestPointToPoint(pData[0], pData[1], pData[2], dest);
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_fma(pX, pY, pZ, dest);
        return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = Math.max(-sd[12], Math.min(java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)), sd[12]));
        float _t19 = Math.max(-sd[13], Math.min(java.lang.Math.fma(sd[8], _t3, java.lang.Math.fma(sd[6], _t4, sd[7] * _t5)), sd[13]));
        float _t20 = Math.max(-sd[14], Math.min(java.lang.Math.fma(sd[11], _t3, java.lang.Math.fma(sd[9], _t4, sd[10] * _t5)), sd[14]));
        dd[0] = java.lang.Math.fma(sd[3], _t18, java.lang.Math.fma(sd[6], _t19, java.lang.Math.fma(sd[9], _t20, sd[0])));
        dd[1] = java.lang.Math.fma(sd[4], _t18, java.lang.Math.fma(sd[7], _t19, java.lang.Math.fma(sd[10], _t20, sd[1])));
        dd[2] = java.lang.Math.fma(sd[5], _t18, java.lang.Math.fma(sd[8], _t19, java.lang.Math.fma(sd[11], _t20, sd[2])));
        return dest;
    }

    /** {@code closestPointToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 closestPointToPoint_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = Math.max(-sd[12], Math.min(((sd[5]) * (_t3) + (((sd[3]) * (_t4) + (sd[4] * _t5)))), sd[12]));
        float _t19 = Math.max(-sd[13], Math.min(((sd[8]) * (_t3) + (((sd[6]) * (_t4) + (sd[7] * _t5)))), sd[13]));
        float _t20 = Math.max(-sd[14], Math.min(((sd[11]) * (_t3) + (((sd[9]) * (_t4) + (sd[10] * _t5)))), sd[14]));
        dd[0] = ((sd[3]) * (_t18) + (((sd[6]) * (_t19) + (((sd[9]) * (_t20) + (sd[0]))))));
        dd[1] = ((sd[4]) * (_t18) + (((sd[7]) * (_t19) + (((sd[10]) * (_t20) + (sd[1]))))));
        dd[2] = ((sd[5]) * (_t18) + (((sd[8]) * (_t19) + (((sd[11]) * (_t20) + (sd[2]))))));
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
        if (org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_fma(pX, pY, pZ, dest);
        return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code closestPointToPoint} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointToPoint_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = Math.max(-sd[12], Math.min(java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)), sd[12]));
        float _t19 = Math.max(-sd[13], Math.min(java.lang.Math.fma(sd[8], _t3, java.lang.Math.fma(sd[6], _t4, sd[7] * _t5)), sd[13]));
        float _t20 = Math.max(-sd[14], Math.min(java.lang.Math.fma(sd[11], _t3, java.lang.Math.fma(sd[9], _t4, sd[10] * _t5)), sd[14]));
        dd[0] = java.lang.Math.fma(sd[3], _t18, java.lang.Math.fma(sd[6], _t19, java.lang.Math.fma(sd[9], _t20, sd[0])));
        dd[1] = java.lang.Math.fma(sd[4], _t18, java.lang.Math.fma(sd[7], _t19, java.lang.Math.fma(sd[10], _t20, sd[1])));
        dd[2] = java.lang.Math.fma(sd[5], _t18, java.lang.Math.fma(sd[8], _t19, java.lang.Math.fma(sd[11], _t20, sd[2])));
        return dest;
    }

    /** {@code closestPointToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointToPoint_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t18 = Math.max(-sd[12], Math.min(((sd[5]) * (_t3) + (((sd[3]) * (_t4) + (sd[4] * _t5)))), sd[12]));
        float _t19 = Math.max(-sd[13], Math.min(((sd[8]) * (_t3) + (((sd[6]) * (_t4) + (sd[7] * _t5)))), sd[13]));
        float _t20 = Math.max(-sd[14], Math.min(((sd[11]) * (_t3) + (((sd[9]) * (_t4) + (sd[10] * _t5)))), sd[14]));
        dd[0] = ((sd[3]) * (_t18) + (((sd[6]) * (_t19) + (((sd[9]) * (_t20) + (sd[0]))))));
        dd[1] = ((sd[4]) * (_t18) + (((sd[7]) * (_t19) + (((sd[10]) * (_t20) + (sd[1]))))));
        dd[2] = ((sd[5]) * (_t18) + (((sd[8]) * (_t19) + (((sd[11]) * (_t20) + (sd[2]))))));
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return containsPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
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
        if (!org.joml2.internal.FmaSwitch.ON) return containsPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
    }

    /** {@code containsPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean containsPoint_mulAdd(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) <= sd[13])) return false;
        return java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) <= sd[14];
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return distanceSquaredToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
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
        if (!org.joml2.internal.FmaSwitch.ON) return distanceSquaredToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
    }

    /** {@code distanceSquaredToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float distanceSquaredToPoint_mulAdd(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) - sd[13]);
        return ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
    }

    /** {@code distanceToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float distanceToPoint_mulAdd(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t18 = Math.max(0.0f, java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) - sd[14]);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) - sd[12]);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) - sd[13]);
        return (float) java.lang.Math.sqrt(((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20)))));
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
        float[] oData = ((FloatOBBImpl) o).data;
        float oCX = oData[0];
        float oCY = oData[1];
        float oCZ = oData[2];
        float oUXx = oData[3];
        float oUXy = oData[4];
        float oUXz = oData[5];
        float oUYx = oData[6];
        float oUYy = oData[7];
        float oUYz = oData[8];
        float oUZx = oData[9];
        float oUZy = oData[10];
        float oUZz = oData[11];
        float oHsX = oData[12];
        float oHsY = oData[13];
        float oHsZ = oData[14];
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
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
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float[] sd = this.data;
        float _ld3 = sd[5];
        float _ld4 = sd[3];
        float _ld5 = sd[4];
        float _ld6 = sd[12];
        float _t0 = oCZ - sd[2];
        float _t1 = oCX - sd[0];
        float _t2 = oCY - sd[1];
        float _t54 = java.lang.Math.fma(_ld3, _t0, java.lang.Math.fma(_ld4, _t1, _ld5 * _t2));
        float _t24 = java.lang.Math.fma(oUXz, _ld3, java.lang.Math.fma(oUXx, _ld4, oUXy * _ld5));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = java.lang.Math.fma(oUYz, _ld3, java.lang.Math.fma(oUYx, _ld4, oUYy * _ld5));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = java.lang.Math.fma(oUZz, _ld3, java.lang.Math.fma(oUZx, _ld4, oUZy * _ld5));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, _ld6))))) return false;
        return intersectsOBB_s743cf6d4_2_fma(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _ld6, sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47);
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float[] sd = this.data;
        float _ld3 = sd[5];
        float _ld4 = sd[3];
        float _ld5 = sd[4];
        float _ld6 = sd[12];
        float _t0 = oCZ - sd[2];
        float _t1 = oCX - sd[0];
        float _t2 = oCY - sd[1];
        float _t54 = ((_ld3) * (_t0) + (((_ld4) * (_t1) + (_ld5 * _t2))));
        float _t24 = ((oUXz) * (_ld3) + (((oUXx) * (_ld4) + (oUXy * _ld5))));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = ((oUYz) * (_ld3) + (((oUYx) * (_ld4) + (oUYy * _ld5))));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = ((oUZz) * (_ld3) + (((oUZx) * (_ld4) + (oUZy * _ld5))));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (_ld6)))))))) return false;
        return intersectsOBB_s743cf6d4_2_mulAdd(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _ld6, sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_1_fma(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t25, float _t26, float _t55, float _t27, float _t28, float _t29, float _t56, float _t30, float _t31, float _t32) {
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(_ld6, _t53, _ld14 * _t47))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(_ld6, _t48, _ld10 * (java.lang.Math.abs(_t24) + 1.0E-5f)))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(_ld6, _t49, _ld10 * (java.lang.Math.abs(_t25) + 1.0E-5f)))) return false;
        return java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(_ld6, (java.lang.Math.abs(_t29) + 1.0E-5f), _ld10 * _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_1_mulAdd(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t25, float _t26, float _t55, float _t27, float _t28, float _t29, float _t56, float _t30, float _t31, float _t32) {
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        if (!(java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((_ld6) * (_t53) + (_ld14 * _t47)))) return false;
        if (!(java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((_ld6) * (_t48) + (_ld10 * (java.lang.Math.abs(_t24) + 1.0E-5f))))) return false;
        if (!(java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((_ld6) * (_t49) + (_ld10 * (java.lang.Math.abs(_t25) + 1.0E-5f))))) return false;
        return java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((_ld6) * ((java.lang.Math.abs(_t29) + 1.0E-5f)) + (_ld10 * _t47));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_2_fma(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47) {
        float _t55 = java.lang.Math.fma(_ld7, _t0, java.lang.Math.fma(_ld8, _t1, _ld9 * _t2));
        float _t27 = java.lang.Math.fma(oUXz, _ld7, java.lang.Math.fma(oUXx, _ld8, oUXy * _ld9));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = java.lang.Math.fma(oUYz, _ld7, java.lang.Math.fma(oUYx, _ld8, oUYy * _ld9));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = java.lang.Math.fma(oUZz, _ld7, java.lang.Math.fma(oUZx, _ld8, oUZy * _ld9));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, _ld10))))) return false;
        float _t30 = java.lang.Math.fma(oUXz, _ld11, java.lang.Math.fma(oUXx, _ld12, oUXy * _ld13));
        float _t31 = java.lang.Math.fma(oUYz, _ld11, java.lang.Math.fma(oUYx, _ld12, oUYy * _ld13));
        float _t32 = java.lang.Math.fma(oUZz, _ld11, java.lang.Math.fma(oUZx, _ld12, oUZy * _ld13));
        return intersectsOBB_s743cf6d4_3_fma(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, java.lang.Math.fma(_ld11, _t0, java.lang.Math.fma(_ld12, _t1, _ld13 * _t2)), _t30, java.lang.Math.abs(_t30) + 1.0E-5f, _t31, java.lang.Math.abs(_t31) + 1.0E-5f, _t32, java.lang.Math.abs(_t32) + 1.0E-5f);
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_2_mulAdd(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float _ld6, float _ld7, float _ld8, float _ld9, float _ld10, float _ld11, float _ld12, float _ld13, float _ld14, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47) {
        float _t55 = ((_ld7) * (_t0) + (((_ld8) * (_t1) + (_ld9 * _t2))));
        float _t27 = ((oUXz) * (_ld7) + (((oUXx) * (_ld8) + (oUXy * _ld9))));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = ((oUYz) * (_ld7) + (((oUYx) * (_ld8) + (oUYy * _ld9))));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = ((oUZz) * (_ld7) + (((oUZx) * (_ld8) + (oUZy * _ld9))));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (_ld10)))))))) return false;
        float _t30 = ((oUXz) * (_ld11) + (((oUXx) * (_ld12) + (oUXy * _ld13))));
        float _t31 = ((oUYz) * (_ld11) + (((oUYx) * (_ld12) + (oUYy * _ld13))));
        float _t32 = ((oUZz) * (_ld11) + (((oUZx) * (_ld12) + (oUZy * _ld13))));
        return intersectsOBB_s743cf6d4_3_mulAdd(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, ((_ld11) * (_t0) + (((_ld12) * (_t1) + (_ld13 * _t2)))), _t30, java.lang.Math.abs(_t30) + 1.0E-5f, _t31, java.lang.Math.abs(_t31) + 1.0E-5f, _t32, java.lang.Math.abs(_t32) + 1.0E-5f);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_3_fma(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, _ld14))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(_ld6, _t45, java.lang.Math.fma(_ld10, _t48, java.lang.Math.fma(_ld14, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(_ld6, _t46, java.lang.Math.fma(_ld10, _t49, java.lang.Math.fma(_ld14, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(_ld6, _t47, java.lang.Math.fma(_ld10, _t50, java.lang.Math.fma(_ld14, _t53, oHsZ))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(_ld10, _t51, _ld14 * _t48))) return false;
        return intersectsOBB_s743cf6d4_4_fma(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_3_mulAdd(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (_ld14)))))))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((_ld6) * (_t45) + (((_ld10) * (_t48) + (((_ld14) * (_t51) + (oHsX)))))))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((_ld6) * (_t46) + (((_ld10) * (_t49) + (((_ld14) * (_t52) + (oHsY)))))))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((_ld6) * (_t47) + (((_ld10) * (_t50) + (((_ld14) * (_t53) + (oHsZ)))))))) return false;
        if (!(java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((_ld10) * (_t51) + (_ld14 * _t48)))) return false;
        return intersectsOBB_s743cf6d4_4_mulAdd(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_4_fma(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(_ld10, _t52, _ld14 * _t49))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(_ld10, _t53, _ld14 * _t50))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(_ld6, _t51, _ld14 * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(_ld6, _t52, _ld14 * _t46))) return false;
        return intersectsOBB_s743cf6d4_1_fma(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_4_mulAdd(float oHsX, float oHsY, float oHsZ, float _ld6, float _ld10, float _ld14, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((_ld10) * (_t52) + (_ld14 * _t49)))) return false;
        if (!(java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((_ld10) * (_t53) + (_ld14 * _t50)))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((_ld6) * (_t51) + (_ld14 * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((_ld6) * (_t52) + (_ld14 * _t46)))) return false;
        return intersectsOBB_s743cf6d4_1_mulAdd(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /**
     * Determine whether this oriented bounding box intersects {@code o}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of {@code o} must
     * be orthonormal; each component of the half sizes of {@code o} must not be negative;
     * {@code epsilon} must not be negative.
     *
     * @param o the oriented bounding box to test for intersection
     * @param epsilon the amount added to each absolute term of the relative rotation, so that
     *        (near) parallel edges cannot degenerate the cross-product axes; a larger value makes
     *        the test more conservative; the overload without it uses {@code 1e-5}
     * @return {@code true} if this oriented bounding box intersects {@code o}, {@code false}
     *        otherwise
     */
    public boolean intersectsOBB(FloatOBBR o, float epsilon) {
        float[] oData = ((FloatOBBImpl) o).data;
        float oCX = oData[0];
        float oCY = oData[1];
        float oCZ = oData[2];
        float oUXx = oData[3];
        float oUXy = oData[4];
        float oUXz = oData[5];
        float oUYx = oData[6];
        float oUYy = oData[7];
        float oUYz = oData[8];
        float oUZx = oData[9];
        float oUZy = oData[10];
        float oUZz = oData[11];
        float oHsX = oData[12];
        float oHsY = oData[13];
        float oHsZ = oData[14];
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
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
     * must be orthonormal; each component of {@code (oHsX, oHsY, oHsZ)} must not be negative;
     * {@code epsilon} must not be negative.
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
     * @param epsilon the amount added to each absolute term of the relative rotation, so that
     *        (near) parallel edges cannot degenerate the cross-product axes; a larger value makes
     *        the test more conservative; the overload without it uses {@code 1e-5}
     * @return {@code true} if this oriented bounding box intersects ({@code oCX}, {@code oCY},
     *        {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     *        {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     *        {@code oHsZ}), {@code false} otherwise
     */
    public boolean intersectsOBB(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        float[] sd = this.data;
        float _t0 = oCZ - sd[2];
        float _t1 = oCX - sd[0];
        float _t2 = oCY - sd[1];
        float _t54 = java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2));
        float _t24 = java.lang.Math.fma(oUXz, sd[5], java.lang.Math.fma(oUXx, sd[3], oUXy * sd[4]));
        float _t45 = epsilon + java.lang.Math.abs(_t24);
        float _t25 = java.lang.Math.fma(oUYz, sd[5], java.lang.Math.fma(oUYx, sd[3], oUYy * sd[4]));
        float _t46 = epsilon + java.lang.Math.abs(_t25);
        float _t26 = java.lang.Math.fma(oUZz, sd[5], java.lang.Math.fma(oUZx, sd[3], oUZy * sd[4]));
        float _t47 = epsilon + java.lang.Math.abs(_t26);
        if (!(java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, sd[12]))))) return false;
        return intersectsOBB_sddf55c18_1_fma(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, sd, _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47, java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2)));
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        float[] sd = this.data;
        float _t0 = oCZ - sd[2];
        float _t1 = oCX - sd[0];
        float _t2 = oCY - sd[1];
        float _t54 = ((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))));
        float _t24 = ((oUXz) * (sd[5]) + (((oUXx) * (sd[3]) + (oUXy * sd[4]))));
        float _t45 = epsilon + java.lang.Math.abs(_t24);
        float _t25 = ((oUYz) * (sd[5]) + (((oUYx) * (sd[3]) + (oUYy * sd[4]))));
        float _t46 = epsilon + java.lang.Math.abs(_t25);
        float _t26 = ((oUZz) * (sd[5]) + (((oUZx) * (sd[3]) + (oUZy * sd[4]))));
        float _t47 = epsilon + java.lang.Math.abs(_t26);
        if (!(java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (sd[12])))))))) return false;
        return intersectsOBB_sddf55c18_1_mulAdd(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, sd, _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47, ((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2)))));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_1_fma(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon, float[] sd, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55) {
        float _t27 = java.lang.Math.fma(oUXz, sd[8], java.lang.Math.fma(oUXx, sd[6], oUXy * sd[7]));
        float _t48 = epsilon + java.lang.Math.abs(_t27);
        float _t28 = java.lang.Math.fma(oUYz, sd[8], java.lang.Math.fma(oUYx, sd[6], oUYy * sd[7]));
        float _t49 = epsilon + java.lang.Math.abs(_t28);
        float _t29 = java.lang.Math.fma(oUZz, sd[8], java.lang.Math.fma(oUZx, sd[6], oUZy * sd[7]));
        float _t50 = epsilon + java.lang.Math.abs(_t29);
        if (!(java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, sd[13]))))) return false;
        float _t30 = java.lang.Math.fma(oUXz, sd[11], java.lang.Math.fma(oUXx, sd[9], oUXy * sd[10]));
        float _t31 = java.lang.Math.fma(oUYz, sd[11], java.lang.Math.fma(oUYx, sd[9], oUYy * sd[10]));
        return intersectsOBB_sddf55c18_2_fma(oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2)), _t30, epsilon + java.lang.Math.abs(_t30), _t31, epsilon + java.lang.Math.abs(_t31));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_1_mulAdd(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon, float[] sd, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55) {
        float _t27 = ((oUXz) * (sd[8]) + (((oUXx) * (sd[6]) + (oUXy * sd[7]))));
        float _t48 = epsilon + java.lang.Math.abs(_t27);
        float _t28 = ((oUYz) * (sd[8]) + (((oUYx) * (sd[6]) + (oUYy * sd[7]))));
        float _t49 = epsilon + java.lang.Math.abs(_t28);
        float _t29 = ((oUZz) * (sd[8]) + (((oUZx) * (sd[6]) + (oUZy * sd[7]))));
        float _t50 = epsilon + java.lang.Math.abs(_t29);
        if (!(java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (sd[13])))))))) return false;
        float _t30 = ((oUXz) * (sd[11]) + (((oUXx) * (sd[9]) + (oUXy * sd[10]))));
        float _t31 = ((oUYz) * (sd[11]) + (((oUYx) * (sd[9]) + (oUYy * sd[10]))));
        return intersectsOBB_sddf55c18_2_mulAdd(oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, ((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2)))), _t30, epsilon + java.lang.Math.abs(_t30), _t31, epsilon + java.lang.Math.abs(_t31));
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_2_fma(float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52) {
        float _t32 = java.lang.Math.fma(oUZz, sd[11], java.lang.Math.fma(oUZx, sd[9], oUZy * sd[10]));
        float _t53 = epsilon + java.lang.Math.abs(_t32);
        if (!(java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, sd[14]))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(sd[12], _t45, java.lang.Math.fma(sd[13], _t48, java.lang.Math.fma(sd[14], _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(sd[12], _t46, java.lang.Math.fma(sd[13], _t49, java.lang.Math.fma(sd[14], _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(sd[12], _t47, java.lang.Math.fma(sd[13], _t50, java.lang.Math.fma(sd[14], _t53, oHsZ))))) return false;
        return intersectsOBB_sddf55c18_3_fma(oHsX, oHsY, oHsZ, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_2_mulAdd(float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52) {
        float _t32 = ((oUZz) * (sd[11]) + (((oUZx) * (sd[9]) + (oUZy * sd[10]))));
        float _t53 = epsilon + java.lang.Math.abs(_t32);
        if (!(java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (sd[14])))))))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((sd[12]) * (_t45) + (((sd[13]) * (_t48) + (((sd[14]) * (_t51) + (oHsX)))))))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((sd[12]) * (_t46) + (((sd[13]) * (_t49) + (((sd[14]) * (_t52) + (oHsY)))))))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((sd[12]) * (_t47) + (((sd[13]) * (_t50) + (((sd[14]) * (_t53) + (oHsZ)))))))) return false;
        return intersectsOBB_sddf55c18_3_mulAdd(oHsX, oHsY, oHsZ, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_3_fma(float oHsX, float oHsY, float oHsZ, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(sd[13], _t51, sd[14] * _t48))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(sd[13], _t52, sd[14] * _t49))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(sd[13], _t53, sd[14] * _t50))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(sd[12], _t51, sd[14] * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(sd[12], _t52, sd[14] * _t46))) return false;
        return intersectsOBB_sddf55c18_4_fma(oHsX, oHsY, oHsZ, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t51, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_3_mulAdd(float oHsX, float oHsY, float oHsZ, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((sd[13]) * (_t51) + (sd[14] * _t48)))) return false;
        if (!(java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((sd[13]) * (_t52) + (sd[14] * _t49)))) return false;
        if (!(java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((sd[13]) * (_t53) + (sd[14] * _t50)))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((sd[12]) * (_t51) + (sd[14] * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((sd[12]) * (_t52) + (sd[14] * _t46)))) return false;
        return intersectsOBB_sddf55c18_4_mulAdd(oHsX, oHsY, oHsZ, sd, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t51, _t52, _t32, _t53);
    }

    /** Piece 5 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_4_fma(float oHsX, float oHsY, float oHsZ, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t51, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(sd[12], _t53, sd[14] * _t47))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(sd[12], _t48, sd[13] * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(sd[12], _t49, sd[13] * _t46))) return false;
        return java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(sd[12], _t50, sd[13] * _t47);
    }

    /** Piece 5 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sddf55c18_4_mulAdd(float oHsX, float oHsY, float oHsZ, float[] sd, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t51, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((sd[12]) * (_t53) + (sd[14] * _t47)))) return false;
        if (!(java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((sd[12]) * (_t48) + (sd[13] * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((sd[12]) * (_t49) + (sd[13] * _t46)))) return false;
        return java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((sd[12]) * (_t50) + (sd[13] * _t47));
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
        return "FloatOBB(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ", " + this.data[6] + ", " + this.data[7] + ", " + this.data[8] + ", " + this.data[9] + ", " + this.data[10] + ", " + this.data[11] + ", " + this.data[12] + ", " + this.data[13] + ", " + this.data[14] + ")";
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
        float[] otherData = ((FloatOBBImpl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon
            && java.lang.Math.abs(sd[7] - otherData[7]) <= epsilon
            && java.lang.Math.abs(sd[8] - otherData[8]) <= epsilon
            && java.lang.Math.abs(sd[9] - otherData[9]) <= epsilon
            && java.lang.Math.abs(sd[10] - otherData[10]) <= epsilon
            && java.lang.Math.abs(sd[11] - otherData[11]) <= epsilon
            && java.lang.Math.abs(sd[12] - otherData[12]) <= epsilon
            && java.lang.Math.abs(sd[13] - otherData[13]) <= epsilon
            && java.lang.Math.abs(sd[14] - otherData[14]) <= epsilon;
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

    @Mutated public FloatOBB load(float[] src, int offset) {
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

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return buf;
    }

    @Mutated public FloatOBB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatOBB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatOBB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return this;
    }

    public FloatOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatOBB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public FloatOBB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public FloatOBB load(long offset, MemorySegment src) {
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

    @Mutated public FloatOBB load(double[] src, int offset) {
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

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return buf;
    }

    @Mutated public FloatOBB loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatOBB loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return this;
    }

    public FloatOBB storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public FloatOBB loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    public MemorySegment storeDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    @Mutated public FloatOBB loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }

    @Mutated public FloatOBB loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_0 = FloatVector.fromArray(COL_SPECIES, new float[] {1.0f, 0.0f, 0.0f, 0.0f}, 0);
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
