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
 * Generated implementation of {@link FloatOBB} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatOBBImpl implements FloatOBB {

    public float cX;
    public float cY;
    public float cZ;
    public float uXx;
    public float uXy;
    public float uXz;
    public float uYx;
    public float uYy;
    public float uYz;
    public float uZx;
    public float uZy;
    public float uZz;
    public float hsX;
    public float hsY;
    public float hsZ;

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
        uXx = 1;
        uYy = 1;
        uZz = 1;
    }

    public FloatOBBImpl(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) {
        this.cX = cX;
        this.cY = cY;
        this.cZ = cZ;
        this.uXx = uXx;
        this.uXy = uXy;
        this.uXz = uXz;
        this.uYx = uYx;
        this.uYy = uYy;
        this.uYz = uYz;
        this.uZx = uZx;
        this.uZy = uZy;
        this.uZz = uZz;
        this.hsX = hsX;
        this.hsY = hsY;
        this.hsZ = hsZ;
    }

    public FloatOBBImpl(FloatOBBR src) {
        this.cX = src.cX();
        this.cY = src.cY();
        this.cZ = src.cZ();
        this.uXx = src.uXx();
        this.uXy = src.uXy();
        this.uXz = src.uXz();
        this.uYx = src.uYx();
        this.uYy = src.uYy();
        this.uYz = src.uYz();
        this.uZx = src.uZx();
        this.uZy = src.uZy();
        this.uZz = src.uZz();
        this.hsX = src.hsX();
        this.hsY = src.hsY();
        this.hsZ = src.hsZ();
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
        this.cX = v.cX();
        this.cY = vCY;
        this.cZ = vCZ;
        this.uXx = vUXx;
        this.uXy = vUXy;
        this.uXz = vUXz;
        this.uYx = vUYx;
        this.uYy = vUYy;
        this.uYz = vUYz;
        this.uZx = vUZx;
        this.uZy = vUZy;
        this.uZz = vUZz;
        this.hsX = vHsX;
        this.hsY = vHsY;
        this.hsZ = vHsZ;
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
        this.cX = vCX;
        this.cY = vCY;
        this.cZ = vCZ;
        this.uXx = vUXx;
        this.uXy = vUXy;
        this.uXz = vUXz;
        this.uYx = vUYx;
        this.uYy = vUYy;
        this.uYz = vUYz;
        this.uZx = vUZx;
        this.uZy = vUZy;
        this.uZz = vUZz;
        this.hsX = vHsX;
        this.hsY = vHsY;
        this.hsZ = vHsZ;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = axisXX;
        d.uXy = axisXY;
        d.uXz = axisXZ;
        d.uYx = axisYX;
        d.uYy = axisYY;
        d.uYz = axisYZ;
        d.uZx = axisZX;
        d.uZy = axisZY;
        d.uZz = axisZZ;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = axisXX;
        d.uXy = axisXY;
        d.uXz = axisXZ;
        d.uYx = axisYX;
        d.uYy = axisYY;
        d.uYz = axisYZ;
        d.uZx = axisZX;
        d.uZy = axisZY;
        d.uZz = axisZZ;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = axisXX;
        d.uXy = axisXY;
        d.uXz = axisXZ;
        d.uYx = axisYX;
        d.uYy = axisYY;
        d.uYz = axisYZ;
        d.uZx = axisZX;
        d.uZy = axisZY;
        d.uZz = axisZZ;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = axisXX;
        d.uXy = axisXY;
        d.uXz = axisXZ;
        d.uYx = axisYX;
        d.uYy = axisYY;
        d.uYz = axisYZ;
        d.uZx = axisZX;
        d.uZy = axisZY;
        d.uZz = axisZZ;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = c.x();
        d.cY = cY;
        d.cZ = cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = c.x();
        d.cY = cY;
        d.cZ = cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = cX;
        d.cY = cY;
        d.cZ = cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = cX;
        d.cY = cY;
        d.cZ = cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = hX;
        d.hsY = hY;
        d.hsZ = hZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = hX;
        d.hsY = hY;
        d.hsZ = hZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = hX;
        d.hsY = hY;
        d.hsZ = hZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = hX;
        d.hsY = hY;
        d.hsZ = hZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = 1.0f;
        d.uXy = 0.0f;
        d.uXz = 0.0f;
        d.uYx = 0.0f;
        d.uYy = 1.0f;
        d.uYz = 0.0f;
        d.uZx = 0.0f;
        d.uZy = 0.0f;
        d.uZz = 1.0f;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = 1.0f;
        d.uXy = 0.0f;
        d.uXz = 0.0f;
        d.uYx = 0.0f;
        d.uYy = 1.0f;
        d.uYz = 0.0f;
        d.uZx = 0.0f;
        d.uZy = 0.0f;
        d.uZz = 1.0f;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        d.uXy = 2.0f * Math.fma(qX, qY, _t1);
        d.uXz = 2.0f * Math.fma(qX, qZ, -_t2);
        d.uYx = 2.0f * Math.fma(qX, qY, -_t1);
        d.uYy = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        d.uYz = 2.0f * Math.fma(qX, qW, qY * qZ);
        d.uZx = 2.0f * Math.fma(qX, qZ, _t2);
        d.uZy = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        d.uZz = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        d.uXy = 2.0f * Math.fma(qX, qY, _t1);
        d.uXz = 2.0f * Math.fma(qX, qZ, -_t2);
        d.uYx = 2.0f * Math.fma(qX, qY, -_t1);
        d.uYy = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        d.uYz = 2.0f * Math.fma(qX, qW, qY * qZ);
        d.uZx = 2.0f * Math.fma(qX, qZ, _t2);
        d.uZy = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        d.uZz = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        d.uXy = 2.0f * Math.fma(qX, qY, _t1);
        d.uXz = 2.0f * Math.fma(qX, qZ, -_t2);
        d.uYx = 2.0f * Math.fma(qX, qY, -_t1);
        d.uYy = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        d.uYz = 2.0f * Math.fma(qX, qW, qY * qZ);
        d.uZx = 2.0f * Math.fma(qX, qZ, _t2);
        d.uZy = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        d.uZz = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f);
        d.uXy = 2.0f * Math.fma(qX, qY, _t1);
        d.uXz = 2.0f * Math.fma(qX, qZ, -_t2);
        d.uYx = 2.0f * Math.fma(qX, qY, -_t1);
        d.uYy = Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f);
        d.uYz = 2.0f * Math.fma(qX, qW, qY * qZ);
        d.uZx = 2.0f * Math.fma(qX, qZ, _t2);
        d.uZy = 2.0f * Math.fma(qY, qZ, -(qX * qW));
        d.uZz = Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f);
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
    }


    /**
     * Set this oriented bounding box to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatOBB makeIdentity() {
        this.cX = 0.0f;
        this.cY = 0.0f;
        this.cZ = 0.0f;
        this.uXx = 1.0f;
        this.uXy = 0.0f;
        this.uXz = 0.0f;
        this.uYx = 0.0f;
        this.uYy = 1.0f;
        this.uYz = 0.0f;
        this.uZx = 0.0f;
        this.uZy = 0.0f;
        this.uZz = 1.0f;
        this.hsX = 0.0f;
        this.hsY = 0.0f;
        this.hsZ = 0.0f;
        return this;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s2c7715ad_c0_fma(FloatOBBImpl _dst, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _t44) {
        _dst.cX = java.lang.Math.fma(_r2, _r18, java.lang.Math.fma(_r4, _r19, java.lang.Math.fma(_r0, _r20, _r21)));
        _dst.cY = java.lang.Math.fma(_r10, _r18, java.lang.Math.fma(_r11, _r19, java.lang.Math.fma(_r9, _r20, _r22)));
        _dst.cZ = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r6, _r20, _r23)));
        _dst.uXx = _t44;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s2c7715ad_c0_mulAdd(FloatOBBImpl _dst, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _t44) {
        _dst.cX = ((_r2) * (_r18) + (((_r4) * (_r19) + (((_r0) * (_r20) + (_r21))))));
        _dst.cY = ((_r10) * (_r18) + (((_r11) * (_r19) + (((_r9) * (_r20) + (_r22))))));
        _dst.cZ = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r6) * (_r20) + (_r23))))));
        _dst.uXx = _t44;
    }

    /**
     * Private store group 3 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s2c7715ad_c3_fma(FloatOBBImpl _dst, float _r24, float _t24, float _t46, float _t25, float _t44, float _t26, float _t45, float _r25, float _t19, float _t18, float _t20, float _r26, float _t22, float _t21, float _t23, float _t64, float _t62, float _t63, float _t54, float _t55, float _t53) {
        _dst.hsX = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
        _dst.hsY = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        _dst.hsZ = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Private store group 3 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s2c7715ad_c3_mulAdd(FloatOBBImpl _dst, float _r24, float _t24, float _t46, float _t25, float _t44, float _t26, float _t45, float _r25, float _t19, float _t18, float _t20, float _r26, float _t22, float _t21, float _t23, float _t64, float _t62, float _t63, float _t54, float _t55, float _t53) {
        _dst.hsX = ((_r24) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
        _dst.hsY = ((_r24) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        _dst.hsZ = ((_r24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s2c7715ad_tail_fma(FloatOBBImpl _dst, Float3x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s2c7715ad_tail2_fma(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17)), java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17)), java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s2c7715ad_tail_mulAdd(FloatOBBImpl _dst, Float3x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s2c7715ad_tail2_mulAdd(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17)))), ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17)))), ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17)))), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s2c7715ad_tail2_fma(FloatOBBImpl _dst, float _t20, float _t42, float _t19, float _t37, float _t52, float _t38, float _t39, float _t44, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _r24, float _t24, float _t25, float _t26, float _r25, float _t18, float _r26, float _t22, float _t21, float _t23) {
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t53 = _t37 * _t52;
        float _t54 = _t38 * _t52;
        float _t55 = _t39 * _t52;
        float _t62 = java.lang.Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        transform_s2c7715ad_c0_fma(_dst, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _t44);
        _dst.uXy = _t45;
        _dst.uXz = _t46;
        _dst.uYx = _t62;
        _dst.uYy = _t63;
        _dst.uYz = _t64;
        _dst.uZx = _t55;
        _dst.uZy = _t53;
        _dst.uZz = _t54;
        transform_s2c7715ad_c3_fma(_dst, _r24, _t24, _t46, _t25, _t44, _t26, _t45, _r25, _t19, _t18, _t20, _r26, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s2c7715ad_tail2_mulAdd(FloatOBBImpl _dst, float _t20, float _t42, float _t19, float _t37, float _t52, float _t38, float _t39, float _t44, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _r24, float _t24, float _t25, float _t26, float _r25, float _t18, float _r26, float _t22, float _t21, float _t23) {
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t53 = _t37 * _t52;
        float _t54 = _t38 * _t52;
        float _t55 = _t39 * _t52;
        float _t62 = ((_t46) * (_t53) - (_t45 * _t54));
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        transform_s2c7715ad_c0_mulAdd(_dst, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _t44);
        _dst.uXy = _t45;
        _dst.uXz = _t46;
        _dst.uYx = _t62;
        _dst.uYy = _t63;
        _dst.uYz = _t64;
        _dst.uZx = _t55;
        _dst.uZy = _t53;
        _dst.uZz = _t54;
        transform_s2c7715ad_c3_mulAdd(_dst, _r24, _t24, _t46, _t25, _t44, _t26, _t45, _r25, _t19, _t18, _t20, _r26, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53);
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
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float3x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        float _t19 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        float _t20 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        float _t21 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        return transform_s84fb42d0_1_fma(m, dest, (FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t21, _t19, -(_t18 * _t22)), java.lang.Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float3x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        float _t19 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        float _t20 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        float _t21 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        return transform_s84fb42d0_1_mulAdd(m, dest, (FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, ((_t21) * (_t19) - (_t18 * _t22)), ((_t18) * (_t23) - (_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s84fb42d0_1_fma(Float3x4R m, FloatOBB dest, FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _t52 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s2c7715ad_tail_fma(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s84fb42d0_1_mulAdd(Float3x4R m, FloatOBB dest, FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _t52 = ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
        if (!(_t52 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s2c7715ad_tail_mulAdd(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s4592d9e6_c0_fma(DoubleOBBImpl _dst, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _t44) {
        _dst.cX = java.lang.Math.fma(_r2, _r18, java.lang.Math.fma(_r4, _r19, java.lang.Math.fma(_r0, _r20, _r21)));
        _dst.cY = java.lang.Math.fma(_r10, _r18, java.lang.Math.fma(_r11, _r19, java.lang.Math.fma(_r9, _r20, _r22)));
        _dst.cZ = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r6, _r20, _r23)));
        _dst.uXx = _t44;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s4592d9e6_c0_mulAdd(DoubleOBBImpl _dst, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _t44) {
        _dst.cX = ((_r2) * (_r18) + (((_r4) * (_r19) + (((_r0) * (_r20) + (_r21))))));
        _dst.cY = ((_r10) * (_r18) + (((_r11) * (_r19) + (((_r9) * (_r20) + (_r22))))));
        _dst.cZ = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r6) * (_r20) + (_r23))))));
        _dst.uXx = _t44;
    }

    /**
     * Private store group 3 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s4592d9e6_c3_fma(DoubleOBBImpl _dst, float _r24, float _t24, float _t46, float _t25, float _t44, float _t26, float _t45, float _r25, float _t19, float _t18, float _t20, float _r26, float _t22, float _t21, float _t23, float _t64, float _t62, float _t63, float _t54, float _t55, float _t53) {
        _dst.hsX = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
        _dst.hsY = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        _dst.hsZ = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Private store group 3 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s4592d9e6_c3_mulAdd(DoubleOBBImpl _dst, float _r24, float _t24, float _t46, float _t25, float _t44, float _t26, float _t45, float _r25, float _t19, float _t18, float _t20, float _r26, float _t22, float _t21, float _t23, float _t64, float _t62, float _t63, float _t54, float _t55, float _t53) {
        _dst.hsX = ((_r24) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
        _dst.hsY = ((_r24) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        _dst.hsZ = ((_r24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_r25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_r26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s4592d9e6_tail_fma(DoubleOBBImpl _dst, Float3x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s4592d9e6_tail2_fma(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17)), java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17)), java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s4592d9e6_tail_mulAdd(DoubleOBBImpl _dst, Float3x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s4592d9e6_tail2_mulAdd(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17)))), ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17)))), ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17)))), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s4592d9e6_tail2_fma(DoubleOBBImpl _dst, float _t20, float _t42, float _t19, float _t37, float _t52, float _t38, float _t39, float _t44, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _r24, float _t24, float _t25, float _t26, float _r25, float _t18, float _r26, float _t22, float _t21, float _t23) {
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t53 = _t37 * _t52;
        float _t54 = _t38 * _t52;
        float _t55 = _t39 * _t52;
        float _t62 = java.lang.Math.fma(_t46, _t53, -(_t45 * _t54));
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        transform_s4592d9e6_c0_fma(_dst, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _t44);
        _dst.uXy = _t45;
        _dst.uXz = _t46;
        _dst.uYx = _t62;
        _dst.uYy = _t63;
        _dst.uYz = _t64;
        _dst.uZx = _t55;
        _dst.uZy = _t53;
        _dst.uZz = _t54;
        transform_s4592d9e6_c3_fma(_dst, _r24, _t24, _t46, _t25, _t44, _t26, _t45, _r25, _t19, _t18, _t20, _r26, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s4592d9e6_tail2_mulAdd(DoubleOBBImpl _dst, float _t20, float _t42, float _t19, float _t37, float _t52, float _t38, float _t39, float _t44, float _r2, float _r18, float _r4, float _r19, float _r0, float _r20, float _r21, float _r10, float _r11, float _r9, float _r22, float _r7, float _r8, float _r6, float _r23, float _r24, float _t24, float _t25, float _t26, float _r25, float _t18, float _r26, float _t22, float _t21, float _t23) {
        float _t45 = _t20 * _t42;
        float _t46 = _t19 * _t42;
        float _t53 = _t37 * _t52;
        float _t54 = _t38 * _t52;
        float _t55 = _t39 * _t52;
        float _t62 = ((_t46) * (_t53) - (_t45 * _t54));
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        transform_s4592d9e6_c0_mulAdd(_dst, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _t44);
        _dst.uXy = _t45;
        _dst.uXz = _t46;
        _dst.uYx = _t62;
        _dst.uYy = _t63;
        _dst.uYz = _t64;
        _dst.uZx = _t55;
        _dst.uZy = _t53;
        _dst.uZz = _t54;
        transform_s4592d9e6_c3_mulAdd(_dst, _r24, _t24, _t46, _t25, _t44, _t26, _t45, _r25, _t19, _t18, _t20, _r26, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53);
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
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Float3x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        float _t19 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        float _t20 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        float _t21 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        return transform_s9ad3639_1_fma(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t21, _t19, -(_t18 * _t22)), java.lang.Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float3x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        float _t19 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        float _t20 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        float _t21 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        return transform_s9ad3639_1_mulAdd(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, ((_t21) * (_t19) - (_t18 * _t22)), ((_t18) * (_t23) - (_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9ad3639_1_fma(Float3x4R m, DoubleOBB dest, DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _t52 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s4592d9e6_tail_fma(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9ad3639_1_mulAdd(Float3x4R m, DoubleOBB dest, DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _t52 = ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
        if (!(_t52 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s4592d9e6_tail_mulAdd(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float3x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s8ab82e23_1_fma((FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5)), java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5)), java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5)), java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14)));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float3x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s8ab82e23_1_mulAdd((FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5)))), ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5)))), ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5)))), ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14)))));
    }

    /**
     * Piece 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_1_fma(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21) {
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        float _t24 = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17));
        float _t25 = java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17));
        float _t26 = java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        return transform_degenerate_s8ab82e23_2_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28, _t24 * _t29, _t25 * _t29, _t26 * _t29);
    }

    /**
     * Piece 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_1_mulAdd(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21) {
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        float _t24 = ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17))));
        float _t25 = ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17))));
        float _t26 = ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17))));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        return transform_degenerate_s8ab82e23_2_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28, _t24 * _t29, _t25 * _t29, _t26 * _t29);
    }

    /**
     * Piece 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_2_fma(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t63, _t64, _t65;
        if (java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
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
        return transform_degenerate_s8ab82e23_3_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t70, _t71, _t72, _t83, _t84, _t91);
    }

    /**
     * Piece 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_2_mulAdd(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t63, _t64, _t65;
        if (((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))) > 0.0f) {
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
        return transform_degenerate_s8ab82e23_3_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t70, _t71, _t72, _t83, _t84, _t91);
    }

    /**
     * Piece 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_3_fma(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91) {
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41));
        float _t92 = java.lang.Math.fma(_t88, _t72, _t42);
        float _t93 = java.lang.Math.fma(_t88, _t70, _t43);
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        return transform_degenerate_s8ab82e23_4_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, java.lang.Math.fma(_t90, _t72, _t39), java.lang.Math.fma(_t90, _t70, _t40), java.lang.Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94)), java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97)));
    }

    /**
     * Piece 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_3_mulAdd(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91) {
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        float _t89 = -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47))));
        float _t90 = -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41))));
        float _t92 = ((_t88) * (_t72) + (_t42));
        float _t93 = ((_t88) * (_t70) + (_t43));
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        return transform_degenerate_s8ab82e23_4_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, ((_t90) * (_t72) + (_t39)), ((_t90) * (_t70) + (_t40)), ((_t90) * (_t71) + (_t41)), (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94)))), ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97)))));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_4_fma(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115) {
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
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
        d.cX = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r6, _r20, _r21)));
        return transform_degenerate_s8ab82e23_5_fma(d, _r0, _r2, _r4, _r9, _r10, _r11, _r18, _r19, _r20, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, java.lang.Math.fma(_t72, _t129, -(_t70 * _t130)), java.lang.Math.fma(_t70, _t131, -(_t71 * _t129)), java.lang.Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_4_mulAdd(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115) {
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
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
        d.cX = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r6) * (_r20) + (_r21))))));
        return transform_degenerate_s8ab82e23_5_mulAdd(d, _r0, _r2, _r4, _r9, _r10, _r11, _r18, _r19, _r20, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, ((_t72) * (_t129) - (_t70 * _t130)), ((_t70) * (_t131) - (_t71 * _t129)), ((_t71) * (_t130) - (_t72 * _t131)));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_5_fma(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.cY = java.lang.Math.fma(_r10, _r18, java.lang.Math.fma(_r11, _r19, java.lang.Math.fma(_r9, _r20, _r22)));
        d.cZ = java.lang.Math.fma(_r2, _r18, java.lang.Math.fma(_r4, _r19, java.lang.Math.fma(_r0, _r20, _r23)));
        d.uXx = _t70;
        d.uXy = _t71;
        d.uXz = _t72;
        d.uYx = _t129;
        d.uYy = _t131;
        d.uYz = _t130;
        d.uZx = _t140;
        d.uZy = _t138;
        d.uZz = _t139;
        d.hsX = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71)))));
        d.hsY = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t130, java.lang.Math.fma(_t25, _t129, _t26 * _t131))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t130, java.lang.Math.fma(_t19, _t129, _t20 * _t131))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t130, java.lang.Math.fma(_t22, _t129, _t23 * _t131)))));
        return transform_degenerate_s8ab82e23_6_fma(d, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t138, _t139, _t140);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_5_mulAdd(FloatOBBImpl d, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.cY = ((_r10) * (_r18) + (((_r11) * (_r19) + (((_r9) * (_r20) + (_r22))))));
        d.cZ = ((_r2) * (_r18) + (((_r4) * (_r19) + (((_r0) * (_r20) + (_r23))))));
        d.uXx = _t70;
        d.uXy = _t71;
        d.uXz = _t72;
        d.uYx = _t129;
        d.uYy = _t131;
        d.uYz = _t130;
        d.uZx = _t140;
        d.uZy = _t138;
        d.uZz = _t139;
        d.hsX = ((_r24) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71)))))))));
        d.hsY = ((_r24) * (java.lang.Math.abs(((_t24) * (_t130) + (((_t25) * (_t129) + (_t26 * _t131)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t130) + (((_t19) * (_t129) + (_t20 * _t131)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t130) + (((_t22) * (_t129) + (_t23 * _t131)))))))));
        return transform_degenerate_s8ab82e23_6_mulAdd(d, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t138, _t139, _t140);
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_6_fma(FloatOBBImpl d, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t138, float _t139, float _t140) {
        d.hsZ = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t139, java.lang.Math.fma(_t25, _t140, _t26 * _t138))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t139, java.lang.Math.fma(_t19, _t140, _t20 * _t138))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t139, java.lang.Math.fma(_t22, _t140, _t23 * _t138)))));
        return d;
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s8ab82e23_6_mulAdd(FloatOBBImpl d, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t138, float _t139, float _t140) {
        d.hsZ = ((_r24) * (java.lang.Math.abs(((_t24) * (_t139) + (((_t25) * (_t140) + (_t26 * _t138)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t139) + (((_t19) * (_t140) + (_t20 * _t138)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t139) + (((_t22) * (_t140) + (_t23 * _t138)))))))));
        return d;
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Float3x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s374590_1_fma((DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5)), java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5)), java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5)), java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14)));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float3x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s374590_1_mulAdd((DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5)))), ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5)))), ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5)))), ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14)))));
    }

    /**
     * Piece 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_1_fma(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21) {
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        float _t24 = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17));
        float _t25 = java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17));
        float _t26 = java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        return transform_degenerate_s374590_2_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28, _t24 * _t29, _t25 * _t29, _t26 * _t29);
    }

    /**
     * Piece 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_1_mulAdd(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21) {
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        float _t24 = ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17))));
        float _t25 = ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17))));
        float _t26 = ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17))));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        return transform_degenerate_s374590_2_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28, _t24 * _t29, _t25 * _t29, _t26 * _t29);
    }

    /**
     * Piece 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_2_fma(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t63, _t64, _t65;
        if (java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
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
        return transform_degenerate_s374590_3_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t70, _t71, _t72, _t83, _t84, _t91);
    }

    /**
     * Piece 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_2_mulAdd(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t63, _t64, _t65;
        if (((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))) > 0.0f) {
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
        return transform_degenerate_s374590_3_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t70, _t71, _t72, _t83, _t84, _t91);
    }

    /**
     * Piece 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_3_fma(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91) {
        float _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        float _t89 = -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47));
        float _t90 = -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41));
        float _t92 = java.lang.Math.fma(_t88, _t72, _t42);
        float _t93 = java.lang.Math.fma(_t88, _t70, _t43);
        float _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        float _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        float _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        float _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        return transform_degenerate_s374590_4_fma(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, java.lang.Math.fma(_t90, _t72, _t39), java.lang.Math.fma(_t90, _t70, _t40), java.lang.Math.fma(_t90, _t71, _t41), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94)), java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97)));
    }

    /**
     * Piece 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_3_mulAdd(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91) {
        float _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        float _t89 = -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47))));
        float _t90 = -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41))));
        float _t92 = ((_t88) * (_t72) + (_t42));
        float _t93 = ((_t88) * (_t70) + (_t43));
        float _t94 = ((_t88) * (_t71) + (_t44));
        float _t95 = ((_t89) * (_t72) + (_t45));
        float _t96 = ((_t89) * (_t70) + (_t46));
        float _t97 = ((_t89) * (_t71) + (_t47));
        return transform_degenerate_s374590_4_mulAdd(d, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, ((_t90) * (_t72) + (_t39)), ((_t90) * (_t70) + (_t40)), ((_t90) * (_t71) + (_t41)), (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94)))), ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97)))));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_4_fma(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115) {
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
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
        d.cX = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r6, _r20, _r21)));
        return transform_degenerate_s374590_5_fma(d, _r0, _r2, _r4, _r9, _r10, _r11, _r18, _r19, _r20, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, java.lang.Math.fma(_t72, _t129, -(_t70 * _t130)), java.lang.Math.fma(_t70, _t131, -(_t71 * _t129)), java.lang.Math.fma(_t71, _t130, -(_t72 * _t131)));
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_4_mulAdd(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t110, float _t114, float _t115) {
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
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
        d.cX = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r6) * (_r20) + (_r21))))));
        return transform_degenerate_s374590_5_mulAdd(d, _r0, _r2, _r4, _r9, _r10, _r11, _r18, _r19, _r20, _r22, _r23, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t129, _t130, _t131, ((_t72) * (_t129) - (_t70 * _t130)), ((_t70) * (_t131) - (_t71 * _t129)), ((_t71) * (_t130) - (_t72 * _t131)));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_5_fma(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.cY = java.lang.Math.fma(_r10, _r18, java.lang.Math.fma(_r11, _r19, java.lang.Math.fma(_r9, _r20, _r22)));
        d.cZ = java.lang.Math.fma(_r2, _r18, java.lang.Math.fma(_r4, _r19, java.lang.Math.fma(_r0, _r20, _r23)));
        d.uXx = _t70;
        d.uXy = _t71;
        d.uXz = _t72;
        d.uYx = _t129;
        d.uYy = _t131;
        d.uYz = _t130;
        d.uZx = _t140;
        d.uZy = _t138;
        d.uZz = _t139;
        d.hsX = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71)))));
        return transform_degenerate_s374590_6_fma(d, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t129, _t130, _t131, _t138, _t139, _t140);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_5_mulAdd(DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _r18, float _r19, float _r20, float _r22, float _r23, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t70, float _t71, float _t72, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.cY = ((_r10) * (_r18) + (((_r11) * (_r19) + (((_r9) * (_r20) + (_r22))))));
        d.cZ = ((_r2) * (_r18) + (((_r4) * (_r19) + (((_r0) * (_r20) + (_r23))))));
        d.uXx = _t70;
        d.uXy = _t71;
        d.uXz = _t72;
        d.uYx = _t129;
        d.uYy = _t131;
        d.uYz = _t130;
        d.uZx = _t140;
        d.uZy = _t138;
        d.uZz = _t139;
        d.hsX = ((_r24) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71)))))))));
        return transform_degenerate_s374590_6_mulAdd(d, _r24, _r25, _r26, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t129, _t130, _t131, _t138, _t139, _t140);
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_6_fma(DoubleOBBImpl d, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.hsY = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t130, java.lang.Math.fma(_t25, _t129, _t26 * _t131))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t130, java.lang.Math.fma(_t19, _t129, _t20 * _t131))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t130, java.lang.Math.fma(_t22, _t129, _t23 * _t131)))));
        d.hsZ = java.lang.Math.fma(_r24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t139, java.lang.Math.fma(_t25, _t140, _t26 * _t138))), java.lang.Math.fma(_r25, java.lang.Math.abs(java.lang.Math.fma(_t18, _t139, java.lang.Math.fma(_t19, _t140, _t20 * _t138))), _r26 * java.lang.Math.abs(java.lang.Math.fma(_t21, _t139, java.lang.Math.fma(_t22, _t140, _t23 * _t138)))));
        return d;
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s374590_6_mulAdd(DoubleOBBImpl d, float _r24, float _r25, float _r26, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t129, float _t130, float _t131, float _t138, float _t139, float _t140) {
        d.hsY = ((_r24) * (java.lang.Math.abs(((_t24) * (_t130) + (((_t25) * (_t129) + (_t26 * _t131)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t130) + (((_t19) * (_t129) + (_t20 * _t131)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t130) + (((_t22) * (_t129) + (_t23 * _t131)))))))));
        d.hsZ = ((_r24) * (java.lang.Math.abs(((_t24) * (_t139) + (((_t25) * (_t140) + (_t26 * _t138)))))) + (((_r25) * (java.lang.Math.abs(((_t18) * (_t139) + (((_t19) * (_t140) + (_t20 * _t138)))))) + (_r26 * java.lang.Math.abs(((_t21) * (_t139) + (((_t22) * (_t140) + (_t23 * _t138)))))))));
        return d;
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s2d23c14c_tail_fma(FloatOBBImpl _dst, Float4x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s2c7715ad_tail2_fma(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17)), java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17)), java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s2d23c14c_tail_mulAdd(FloatOBBImpl _dst, Float4x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s2c7715ad_tail2_mulAdd(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17)))), ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17)))), ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17)))), _r25, _t18, _r26, _t22, _t21, _t23);
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
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float4x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        float _t19 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        float _t20 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        float _t21 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        return transform_s4e1499f_1_fma(m, dest, (FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t21, _t19, -(_t18 * _t22)), java.lang.Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float4x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        float _t19 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        float _t20 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        float _t21 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        return transform_s4e1499f_1_mulAdd(m, dest, (FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, ((_t21) * (_t19) - (_t18 * _t22)), ((_t18) * (_t23) - (_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4e1499f_1_fma(Float4x4R m, FloatOBB dest, FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _t52 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s2d23c14c_tail_fma(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_s4e1499f_1_mulAdd(Float4x4R m, FloatOBB dest, FloatOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _t52 = ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
        if (!(_t52 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s2d23c14c_tail_mulAdd(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s5a7ba227_tail_fma(DoubleOBBImpl _dst, Float4x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s4592d9e6_tail2_fma(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17)), java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17)), java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s5a7ba227_tail_mulAdd(DoubleOBBImpl _dst, Float4x4R m, float _r6, float _r7, float _r8, float _r0, float _r2, float _r4, float _r9, float _r10, float _r11, float _t40, float _t18, float _t20, float _t19, float _t37, float _t52, float _t38, float _t39, float _t22, float _t21, float _t23) {
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        float _r23 = m.m23();
        float _r24 = this.hsZ;
        float _r25 = this.hsX;
        float _r26 = this.hsY;
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t40));
        transform_s4592d9e6_tail2_mulAdd(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17)))), ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17)))), ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17)))), _r25, _t18, _r26, _t22, _t21, _t23);
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
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Float4x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        float _t19 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        float _t20 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        float _t21 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        float _t22 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        float _t23 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        return transform_s49881fc_1_fma(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t21, _t19, -(_t18 * _t22)), java.lang.Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Float4x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m02();
        float _r1 = this.uXz;
        float _r2 = m.m00();
        float _r3 = this.uXx;
        float _r4 = m.m01();
        float _r5 = this.uXy;
        float _r6 = m.m22();
        float _r7 = m.m20();
        float _r8 = m.m21();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _t18 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        float _t19 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        float _t20 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        float _t21 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        float _t22 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        float _t23 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        return transform_s49881fc_1_mulAdd(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, ((_t21) * (_t19) - (_t18 * _t22)), ((_t18) * (_t23) - (_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s49881fc_1_fma(Float4x4R m, DoubleOBB dest, DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        float _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        float _t52 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 1.4551915E-11f, 1.1754944E-38f) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s5a7ba227_tail_fma(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s49881fc_1_mulAdd(Float4x4R m, DoubleOBB dest, DoubleOBBImpl d, float _r0, float _r2, float _r4, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t37, float _t38) {
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        float _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        float _t52 = ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
        if (!(_t52 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 1.4551915E-11f) + (1.1754944E-38f)) && _t52 < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s5a7ba227_tail_mulAdd(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0f / (float) java.lang.Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float4x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s8ab82e23_1_fma((FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5)), java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5)), java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5)), java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14)));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float4x4R m, @Mutated FloatOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s8ab82e23_1_mulAdd((FloatOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5)))), ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5)))), ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5)))), ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14)))));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Float4x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s374590_1_fma((DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5)), java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5)), java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5)), java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14)));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Float4x4R m, @Mutated DoubleOBB dest) {
        float _r0 = m.m22();
        float _r1 = this.uXz;
        float _r2 = m.m20();
        float _r3 = this.uXx;
        float _r4 = m.m21();
        float _r5 = this.uXy;
        float _r6 = m.m02();
        float _r7 = m.m00();
        float _r8 = m.m01();
        float _r9 = m.m12();
        float _r10 = m.m10();
        float _r11 = m.m11();
        float _r12 = this.uYz;
        float _r13 = this.uYx;
        float _r14 = this.uYy;
        float _r15 = this.uZz;
        float _r16 = this.uZx;
        float _r17 = this.uZy;
        float _r18 = this.cX;
        float _r19 = this.cY;
        float _r20 = this.cZ;
        float _r21 = m.m03();
        float _r22 = m.m13();
        return transform_degenerate_s374590_1_mulAdd((DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, m.m23(), this.hsZ, this.hsX, this.hsY, ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5)))), ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5)))), ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5)))), ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14)))));
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = delta.x() + this.cX;
        d.cY = deltaY + this.cY;
        d.cZ = deltaZ + this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = delta.x() + this.cX;
        d.cY = deltaY + this.cY;
        d.cZ = deltaZ + this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = deltaX + this.cX;
        d.cY = deltaY + this.cY;
        d.cZ = deltaZ + this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = deltaX + this.cX;
        d.cY = deltaY + this.cY;
        d.cZ = deltaZ + this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t3 = p.z() - this.cZ;
        float _t4 = p.x() - this.cX;
        float _t5 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        d.x = Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX)));
        d.y = Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY)));
        d.z = Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t3 = p.z() - this.cZ;
        float _t4 = p.x() - this.cX;
        float _t5 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        d.x = Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX)));
        d.y = Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY)));
        d.z = Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ)));
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t3 = pZ - this.cZ;
        float _t4 = pX - this.cX;
        float _t5 = pY - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        d.x = Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX)));
        d.y = Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY)));
        d.z = Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t3 = pZ - this.cZ;
        float _t4 = pX - this.cX;
        float _t5 = pY - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        d.x = Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX)));
        d.y = Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY)));
        d.z = Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ)));
        return d;
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
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
            return java.lang.Math.abs(java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
        } else {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))))) <= this.hsY)) return false;
            return java.lang.Math.abs(((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))))) <= this.hsZ;
        }
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
        if (Math.useFma()) {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
            return java.lang.Math.abs(java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
        } else {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))))) <= this.hsY)) return false;
            return java.lang.Math.abs(((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))))) <= this.hsZ;
        }
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
        float _t0 = p.z() - this.cZ;
        float _t1 = p.x() - this.cX;
        float _t2 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t0 = pZ - this.cZ;
        float _t1 = pX - this.cX;
        float _t2 = pY - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t0 = p.z() - this.cZ;
        float _t1 = p.x() - this.cX;
        float _t2 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t0 = pZ - this.cZ;
        float _t1 = pX - this.cX;
        float _t2 = pY - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.uXx;
        d.y = this.uXy;
        d.z = this.uXz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uXx;
        d.y = this.uXy;
        d.z = this.uXz;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.uYx;
        d.y = this.uYy;
        d.z = this.uYz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uYx;
        d.y = this.uYy;
        d.z = this.uYz;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.uZx;
        d.y = this.uZy;
        d.z = this.uZz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uZx;
        d.y = this.uZy;
        d.z = this.uZz;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.cX;
        d.y = this.cY;
        d.z = this.cZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.cX;
        d.y = this.cY;
        d.z = this.cZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.hsX;
        d.y = this.hsY;
        d.z = this.hsZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.hsX;
        d.y = this.hsY;
        d.z = this.hsZ;
        return d;
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
        float oCX = o.cX();
        float oCY = o.cY();
        float oCZ = o.cZ();
        float oUXx = o.uXx();
        float oUXy = o.uXy();
        float oUXz = o.uXz();
        float oUYx = o.uYx();
        float oUYy = o.uYy();
        float oUYz = o.uYz();
        float oUZx = o.uZx();
        float oUZy = o.uZy();
        float oUZz = o.uZz();
        float oHsX = o.hsX();
        float oHsY = o.hsY();
        float oHsZ = o.hsZ();
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
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
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t54 = java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2));
        float _t24 = java.lang.Math.fma(oUXz, this.uXz, java.lang.Math.fma(oUXx, this.uXx, oUXy * this.uXy));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = java.lang.Math.fma(oUYz, this.uXz, java.lang.Math.fma(oUYx, this.uXx, oUYy * this.uXy));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = java.lang.Math.fma(oUZz, this.uXz, java.lang.Math.fma(oUZx, this.uXx, oUZy * this.uXy));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, this.hsX))))) return false;
        return intersectsOBB_s743cf6d4_1_fma(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47, java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2)), java.lang.Math.fma(oUXz, this.uYz, java.lang.Math.fma(oUXx, this.uYx, oUXy * this.uYy)));
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t54 = ((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))));
        float _t24 = ((oUXz) * (this.uXz) + (((oUXx) * (this.uXx) + (oUXy * this.uXy))));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = ((oUYz) * (this.uXz) + (((oUYx) * (this.uXx) + (oUYy * this.uXy))));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = ((oUZz) * (this.uXz) + (((oUZx) * (this.uXx) + (oUZy * this.uXy))));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (this.hsX)))))))) return false;
        return intersectsOBB_s743cf6d4_1_mulAdd(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47, ((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2)))), ((oUXz) * (this.uYz) + (((oUXx) * (this.uYx) + (oUXy * this.uYy)))));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_1_fma(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27) {
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = java.lang.Math.fma(oUYz, this.uYz, java.lang.Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = java.lang.Math.fma(oUZz, this.uYz, java.lang.Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, this.hsY))))) return false;
        float _t30 = java.lang.Math.fma(oUXz, this.uZz, java.lang.Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        float _t31 = java.lang.Math.fma(oUYz, this.uZz, java.lang.Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        float _t32 = java.lang.Math.fma(oUZz, this.uZz, java.lang.Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        return intersectsOBB_s743cf6d4_2_fma(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2)), _t30, java.lang.Math.abs(_t30) + 1.0E-5f, _t31, java.lang.Math.abs(_t31) + 1.0E-5f, _t32, java.lang.Math.abs(_t32) + 1.0E-5f);
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_1_mulAdd(float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float _t0, float _t1, float _t2, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27) {
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = ((oUYz) * (this.uYz) + (((oUYx) * (this.uYx) + (oUYy * this.uYy))));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = ((oUZz) * (this.uYz) + (((oUZx) * (this.uYx) + (oUZy * this.uYy))));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (this.hsY)))))))) return false;
        float _t30 = ((oUXz) * (this.uZz) + (((oUXx) * (this.uZx) + (oUXy * this.uZy))));
        float _t31 = ((oUYz) * (this.uZz) + (((oUYx) * (this.uZx) + (oUYy * this.uZy))));
        float _t32 = ((oUZz) * (this.uZz) + (((oUZx) * (this.uZx) + (oUZy * this.uZy))));
        return intersectsOBB_s743cf6d4_2_mulAdd(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, ((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2)))), _t30, java.lang.Math.abs(_t30) + 1.0E-5f, _t31, java.lang.Math.abs(_t31) + 1.0E-5f, _t32, java.lang.Math.abs(_t32) + 1.0E-5f);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_2_fma(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, this.hsZ))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(this.hsX, _t45, java.lang.Math.fma(this.hsY, _t48, java.lang.Math.fma(this.hsZ, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(this.hsX, _t46, java.lang.Math.fma(this.hsY, _t49, java.lang.Math.fma(this.hsZ, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(this.hsX, _t47, java.lang.Math.fma(this.hsY, _t50, java.lang.Math.fma(this.hsZ, _t53, oHsZ))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(this.hsY, _t51, this.hsZ * _t48))) return false;
        return intersectsOBB_s743cf6d4_3_fma(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_2_mulAdd(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (this.hsZ)))))))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((this.hsX) * (_t45) + (((this.hsY) * (_t48) + (((this.hsZ) * (_t51) + (oHsX)))))))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((this.hsX) * (_t46) + (((this.hsY) * (_t49) + (((this.hsZ) * (_t52) + (oHsY)))))))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((this.hsX) * (_t47) + (((this.hsY) * (_t50) + (((this.hsZ) * (_t53) + (oHsZ)))))))) return false;
        if (!(java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((this.hsY) * (_t51) + (this.hsZ * _t48)))) return false;
        return intersectsOBB_s743cf6d4_3_mulAdd(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_3_fma(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(this.hsY, _t52, this.hsZ * _t49))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(this.hsY, _t53, this.hsZ * _t50))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(this.hsX, _t51, this.hsZ * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(this.hsX, _t52, this.hsZ * _t46))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(this.hsX, _t53, this.hsZ * _t47))) return false;
        return intersectsOBB_s743cf6d4_4_fma(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t51, _t52, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_3_mulAdd(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t56, float _t30, float _t51, float _t31, float _t52, float _t32, float _t53) {
        if (!(java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((this.hsY) * (_t52) + (this.hsZ * _t49)))) return false;
        if (!(java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((this.hsY) * (_t53) + (this.hsZ * _t50)))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((this.hsX) * (_t51) + (this.hsZ * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((this.hsX) * (_t52) + (this.hsZ * _t46)))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((this.hsX) * (_t53) + (this.hsZ * _t47)))) return false;
        return intersectsOBB_s743cf6d4_4_mulAdd(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t51, _t52, _t53);
    }

    /** Piece 5 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_4_fma(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t51, float _t52, float _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(this.hsX, _t48, this.hsY * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(this.hsX, _t49, this.hsY * _t46))) return false;
        return java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(this.hsX, _t50, this.hsY * _t47);
    }

    /** Piece 5 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s743cf6d4_4_mulAdd(float oHsX, float oHsY, float oHsZ, float _t54, float _t24, float _t45, float _t25, float _t46, float _t26, float _t47, float _t55, float _t27, float _t48, float _t28, float _t49, float _t29, float _t50, float _t51, float _t52, float _t53) {
        if (!(java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((this.hsX) * (_t48) + (this.hsY * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((this.hsX) * (_t49) + (this.hsY * _t46)))) return false;
        return java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((this.hsX) * (_t50) + (this.hsY * _t47));
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
        if (!(this.hsX >= 0.0f)) return false;
        if (!(this.hsY >= 0.0f)) return false;
        return this.hsZ >= 0.0f;
    }

    public float cX() { return this.cX; }
    public float cY() { return this.cY; }
    public float cZ() { return this.cZ; }
    public float uXx() { return this.uXx; }
    public float uXy() { return this.uXy; }
    public float uXz() { return this.uXz; }
    public float uYx() { return this.uYx; }
    public float uYy() { return this.uYy; }
    public float uYz() { return this.uYz; }
    public float uZx() { return this.uZx; }
    public float uZy() { return this.uZy; }
    public float uZz() { return this.uZz; }
    public float hsX() { return this.hsX; }
    public float hsY() { return this.hsY; }
    public float hsZ() { return this.hsZ; }

    @Override public String toString() {
        return "FloatOBB(" + cX() + ", " + cY() + ", " + cZ() + ", " + uXx() + ", " + uXy() + ", " + uXz() + ", " + uYx() + ", " + uYy() + ", " + uYz() + ", " + uZx() + ", " + uZy() + ", " + uZz() + ", " + hsX() + ", " + hsY() + ", " + hsZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatOBBImpl)) return false;
        FloatOBBImpl o = (FloatOBBImpl) obj;
        return Float.floatToIntBits(cX) == Float.floatToIntBits(o.cX)
            && Float.floatToIntBits(cY) == Float.floatToIntBits(o.cY)
            && Float.floatToIntBits(cZ) == Float.floatToIntBits(o.cZ)
            && Float.floatToIntBits(uXx) == Float.floatToIntBits(o.uXx)
            && Float.floatToIntBits(uXy) == Float.floatToIntBits(o.uXy)
            && Float.floatToIntBits(uXz) == Float.floatToIntBits(o.uXz)
            && Float.floatToIntBits(uYx) == Float.floatToIntBits(o.uYx)
            && Float.floatToIntBits(uYy) == Float.floatToIntBits(o.uYy)
            && Float.floatToIntBits(uYz) == Float.floatToIntBits(o.uYz)
            && Float.floatToIntBits(uZx) == Float.floatToIntBits(o.uZx)
            && Float.floatToIntBits(uZy) == Float.floatToIntBits(o.uZy)
            && Float.floatToIntBits(uZz) == Float.floatToIntBits(o.uZz)
            && Float.floatToIntBits(hsX) == Float.floatToIntBits(o.hsX)
            && Float.floatToIntBits(hsY) == Float.floatToIntBits(o.hsY)
            && Float.floatToIntBits(hsZ) == Float.floatToIntBits(o.hsZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(cX);
        h = 31 * h + Float.floatToIntBits(cY);
        h = 31 * h + Float.floatToIntBits(cZ);
        h = 31 * h + Float.floatToIntBits(uXx);
        h = 31 * h + Float.floatToIntBits(uXy);
        h = 31 * h + Float.floatToIntBits(uXz);
        h = 31 * h + Float.floatToIntBits(uYx);
        h = 31 * h + Float.floatToIntBits(uYy);
        h = 31 * h + Float.floatToIntBits(uYz);
        h = 31 * h + Float.floatToIntBits(uZx);
        h = 31 * h + Float.floatToIntBits(uZy);
        h = 31 * h + Float.floatToIntBits(uZz);
        h = 31 * h + Float.floatToIntBits(hsX);
        h = 31 * h + Float.floatToIntBits(hsY);
        h = 31 * h + Float.floatToIntBits(hsZ);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(cX)
            && Float.isFinite(cY)
            && Float.isFinite(cZ)
            && Float.isFinite(uXx)
            && Float.isFinite(uXy)
            && Float.isFinite(uXz)
            && Float.isFinite(uYx)
            && Float.isFinite(uYy)
            && Float.isFinite(uYz)
            && Float.isFinite(uZx)
            && Float.isFinite(uZy)
            && Float.isFinite(uZz)
            && Float.isFinite(hsX)
            && Float.isFinite(hsY)
            && Float.isFinite(hsZ);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(cX)
            || Float.isNaN(cY)
            || Float.isNaN(cZ)
            || Float.isNaN(uXx)
            || Float.isNaN(uXy)
            || Float.isNaN(uXz)
            || Float.isNaN(uYx)
            || Float.isNaN(uYy)
            || Float.isNaN(uYz)
            || Float.isNaN(uZx)
            || Float.isNaN(uZy)
            || Float.isNaN(uZz)
            || Float.isNaN(hsX)
            || Float.isNaN(hsY)
            || Float.isNaN(hsZ);
    }

    @Override public boolean equalsEpsilon(FloatOBBR other, float epsilon) {
        return java.lang.Math.abs(cX - other.cX()) <= epsilon
            && java.lang.Math.abs(cY - other.cY()) <= epsilon
            && java.lang.Math.abs(cZ - other.cZ()) <= epsilon
            && java.lang.Math.abs(uXx - other.uXx()) <= epsilon
            && java.lang.Math.abs(uXy - other.uXy()) <= epsilon
            && java.lang.Math.abs(uXz - other.uXz()) <= epsilon
            && java.lang.Math.abs(uYx - other.uYx()) <= epsilon
            && java.lang.Math.abs(uYy - other.uYy()) <= epsilon
            && java.lang.Math.abs(uYz - other.uYz()) <= epsilon
            && java.lang.Math.abs(uZx - other.uZx()) <= epsilon
            && java.lang.Math.abs(uZy - other.uZy()) <= epsilon
            && java.lang.Math.abs(uZz - other.uZz()) <= epsilon
            && java.lang.Math.abs(hsX - other.hsX()) <= epsilon
            && java.lang.Math.abs(hsY - other.hsY()) <= epsilon
            && java.lang.Math.abs(hsZ - other.hsZ()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.cX;
        dest[offset + 1] = this.cY;
        dest[offset + 2] = this.cZ;
        dest[offset + 3] = this.uXx;
        dest[offset + 4] = this.uXy;
        dest[offset + 5] = this.uXz;
        dest[offset + 6] = this.uYx;
        dest[offset + 7] = this.uYy;
        dest[offset + 8] = this.uYz;
        dest[offset + 9] = this.uZx;
        dest[offset + 10] = this.uZy;
        dest[offset + 11] = this.uZz;
        dest[offset + 12] = this.hsX;
        dest[offset + 13] = this.hsY;
        dest[offset + 14] = this.hsZ;
        return dest;
    }
    public @Mutated FloatOBB load(float[] src, int offset) {
        this.cX = src[offset];
        this.cY = src[offset + 1];
        this.cZ = src[offset + 2];
        this.uXx = src[offset + 3];
        this.uXy = src[offset + 4];
        this.uXz = src[offset + 5];
        this.uYx = src[offset + 6];
        this.uYy = src[offset + 7];
        this.uYz = src[offset + 8];
        this.uZx = src[offset + 9];
        this.uZy = src[offset + 10];
        this.uZz = src[offset + 11];
        this.hsX = src[offset + 12];
        this.hsY = src[offset + 13];
        this.hsZ = src[offset + 14];
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
        dest[offset] = this.cX;
        dest[offset + 1] = this.cY;
        dest[offset + 2] = this.cZ;
        dest[offset + 3] = this.uXx;
        dest[offset + 4] = this.uXy;
        dest[offset + 5] = this.uXz;
        dest[offset + 6] = this.uYx;
        dest[offset + 7] = this.uYy;
        dest[offset + 8] = this.uYz;
        dest[offset + 9] = this.uZx;
        dest[offset + 10] = this.uZy;
        dest[offset + 11] = this.uZz;
        dest[offset + 12] = this.hsX;
        dest[offset + 13] = this.hsY;
        dest[offset + 14] = this.hsZ;
        return dest;
    }
    public @Mutated FloatOBB load(double[] src, int offset) {
        this.cX = (float) src[offset];
        this.cY = (float) src[offset + 1];
        this.cZ = (float) src[offset + 2];
        this.uXx = (float) src[offset + 3];
        this.uXy = (float) src[offset + 4];
        this.uXz = (float) src[offset + 5];
        this.uYx = (float) src[offset + 6];
        this.uYy = (float) src[offset + 7];
        this.uYz = (float) src[offset + 8];
        this.uZx = (float) src[offset + 9];
        this.uZy = (float) src[offset + 10];
        this.uZz = (float) src[offset + 11];
        this.hsX = (float) src[offset + 12];
        this.hsY = (float) src[offset + 13];
        this.hsZ = (float) src[offset + 14];
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
