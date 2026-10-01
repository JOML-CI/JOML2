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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleDualQuat} backed by a {@code double[]} array, with
 * Vector API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleDualQuatImpl implements DoubleDualQuat {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleDualQuatSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleDualQuatSegOpsUnsafe()
                        : new DoubleDualQuatSegOpsMS();
        static final DoubleDualQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleDualQuatBbOpsUnsafe()
                        : new DoubleDualQuatBbOpsApi();
        static final DoubleDualQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleDualQuatRawOpsUnsafe()
                        : new DoubleDualQuatRawOpsApi();
    }

    public DoubleDualQuatImpl() {
        data = new double[8];
        data[3] = 1;
    }

    public DoubleDualQuatImpl(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) {
        double[] dd = this.data = new double[8];
        dd[0] = rX;
        dd[1] = rY;
        dd[2] = rZ;
        dd[3] = rW;
        dd[4] = dX;
        dd[5] = dY;
        dd[6] = dZ;
        dd[7] = dW;
    }

    public DoubleDualQuatImpl(DoubleDualQuatR src) {
        double[] dd = this.data = new double[8];
        dd[0] = src.rX();
        dd[1] = src.rY();
        dd[2] = src.rZ();
        dd[3] = src.rW();
        dd[4] = src.dX();
        dd[5] = src.dY();
        dd[6] = src.dZ();
        dd[7] = src.dW();
    }


    /**
     * Add {@code other} to this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat add(DoubleDualQuatR other, @Mutated DoubleDualQuat dest) {
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = other.rX() + sd[0];
        dd[1] = otherRY + sd[1];
        dd[2] = otherRZ + sd[2];
        dd[3] = otherRW + sd[3];
        dd[4] = otherDX + sd[4];
        dd[5] = otherDY + sd[5];
        dd[6] = otherDZ + sd[6];
        dd[7] = otherDW + sd[7];
        return dest;
    }


    /**
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) to this dual quaternion and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat add(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = otherRX + sd[0];
        dd[1] = otherRY + sd[1];
        dd[2] = otherRZ + sd[2];
        dd[3] = otherRW + sd[3];
        dd[4] = otherDX + sd[4];
        dd[5] = otherDY + sd[5];
        dd[6] = otherDZ + sd[6];
        dd[7] = otherDW + sd[7];
        return dest;
    }


    /**
     * Multiply each component of this dual quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat mul(double scalar, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        return dest;
    }


    /**
     * Negate this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat negate(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        return dest;
    }


    /**
     * Subtract {@code other} from this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sub(DoubleDualQuatR other, @Mutated DoubleDualQuat dest) {
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0] - other.rX();
        dd[1] = sd[1] - otherRY;
        dd[2] = sd[2] - otherRZ;
        dd[3] = sd[3] - otherRW;
        dd[4] = sd[4] - otherDX;
        dd[5] = sd[5] - otherDY;
        dd[6] = sd[6] - otherDZ;
        dd[7] = sd[7] - otherDW;
        return dest;
    }


    /**
     * Subtract ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) from this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sub(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0] - otherRX;
        dd[1] = sd[1] - otherRY;
        dd[2] = sd[2] - otherRZ;
        dd[3] = sd[3] - otherRW;
        dd[4] = sd[4] - otherDX;
        dd[5] = sd[5] - otherDY;
        dd[6] = sd[6] - otherDZ;
        dd[7] = sd[7] - otherDW;
        return dest;
    }


    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the dual quaternion to copy
     * @return this
     */
    public @Mutated DoubleDualQuat set(DoubleDualQuatR v) {
        double vRY = v.rY();
        double vRZ = v.rZ();
        double vRW = v.rW();
        double vDX = v.dX();
        double vDY = v.dY();
        double vDZ = v.dZ();
        double vDW = v.dW();
        double[] dd = this.data;
        dd[0] = v.rX();
        dd[1] = vRY;
        dd[2] = vRZ;
        dd[3] = vRW;
        dd[4] = vDX;
        dd[5] = vDY;
        dd[6] = vDZ;
        dd[7] = vDW;
        return this;
    }


    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vRX the {@code rX} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRY the {@code rY} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRZ the {@code rZ} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRW the {@code rW} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDX the {@code dX} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDY the {@code dY} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDZ the {@code dZ} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDW the {@code dW} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @return this
     */
    @Mutated public DoubleDualQuat set(double vRX, double vRY, double vRZ, double vRW, double vDX, double vDY, double vDZ, double vDW) {
        double[] dd = this.data;
        dd[0] = vRX;
        dd[1] = vRY;
        dd[2] = vRZ;
        dd[3] = vRW;
        dd[4] = vDX;
        dd[5] = vDY;
        dd[6] = vDZ;
        dd[7] = vDW;
        return this;
    }


    /**
     * Convert this dual quaternion to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat toFloat(@Mutated FloatDualQuat dest) {
        double[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        return dest;
    }


    /**
     * Set this dual quaternion to the rigid motion of the given rigid transform (an exact
     * conversion - both represent rotation plus translation).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    public @Mutated DoubleDualQuat makeFromRigid(DoubleRigidR r) {
        double rTX = r.tX();
        double rTY = r.tY();
        double rTZ = r.tZ();
        double rRX = r.rX();
        double rRY = r.rY();
        double rRZ = r.rZ();
        double rRW = r.rW();
        double[] dd = this.data;
        double _t0 = -rTZ;
        dd[0] = rRX;
        dd[1] = rRY;
        dd[2] = rRZ;
        dd[3] = rRW;
        dd[4] = 0.5 * Math.fma(_t0, rRY, Math.fma(rTX, rRW, rTY * rRZ));
        dd[5] = 0.5 * Math.fma(rTZ, rRX, Math.fma(rTY, rRW, -(rTX * rRZ)));
        dd[6] = 0.5 * Math.fma(rTZ, rRW, Math.fma(rTX, rRY, -(rTY * rRX)));
        dd[7] = 0.5 * Math.fma(_t0, rRZ, Math.fma(-rTY, rRY, -(rTX * rRX)));
        return this;
    }


    /**
     * Set this dual quaternion to the rigid motion of the given rigid transform (an exact
     * conversion - both represent rotation plus translation).
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
    @Mutated public DoubleDualQuat makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double[] dd = this.data;
        double _t0 = -rTZ;
        dd[0] = rRX;
        dd[1] = rRY;
        dd[2] = rRZ;
        dd[3] = rRW;
        dd[4] = 0.5 * Math.fma(_t0, rRY, Math.fma(rTX, rRW, rTY * rRZ));
        dd[5] = 0.5 * Math.fma(rTZ, rRX, Math.fma(rTY, rRW, -(rTX * rRZ)));
        dd[6] = 0.5 * Math.fma(rTZ, rRW, Math.fma(rTX, rRY, -(rTY * rRX)));
        dd[7] = 0.5 * Math.fma(_t0, rRZ, Math.fma(-rTY, rRY, -(rTX * rRX)));
        return this;
    }


    /**
     * Set this dual quaternion to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (dual quaternions cannot represent it).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return this
     */
    public @Mutated DoubleDualQuat makeFromTransform(DoubleTransformR t) {
        double tTX = t.tX();
        double tTY = t.tY();
        double tTZ = t.tZ();
        double tRX = t.rX();
        double tRY = t.rY();
        double tRZ = t.rZ();
        double tRW = t.rW();
        double[] dd = this.data;
        double _t0 = -tTZ;
        dd[0] = tRX;
        dd[1] = tRY;
        dd[2] = tRZ;
        dd[3] = tRW;
        dd[4] = 0.5 * Math.fma(_t0, tRY, Math.fma(tTX, tRW, tTY * tRZ));
        dd[5] = 0.5 * Math.fma(tTZ, tRX, Math.fma(tTY, tRW, -(tTX * tRZ)));
        dd[6] = 0.5 * Math.fma(tTZ, tRW, Math.fma(tTX, tRY, -(tTY * tRX)));
        dd[7] = 0.5 * Math.fma(_t0, tRZ, Math.fma(-tTY, tRY, -(tTX * tRX)));
        return this;
    }


    /**
     * Set this dual quaternion to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (dual quaternions cannot represent it).
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
    @Mutated public DoubleDualQuat makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double[] dd = this.data;
        double _t0 = -tTZ;
        dd[0] = tRX;
        dd[1] = tRY;
        dd[2] = tRZ;
        dd[3] = tRW;
        dd[4] = 0.5 * Math.fma(_t0, tRY, Math.fma(tTX, tRW, tTY * tRZ));
        dd[5] = 0.5 * Math.fma(tTZ, tRX, Math.fma(tTY, tRW, -(tTX * tRZ)));
        dd[6] = 0.5 * Math.fma(tTZ, tRW, Math.fma(tTX, tRY, -(tTY * tRX)));
        dd[7] = 0.5 * Math.fma(_t0, tRZ, Math.fma(-tTY, tRY, -(tTX * tRX)));
        return this;
    }


    /**
     * Convert this unit dual quaternion to a rigid transform (an exact conversion - both represent
     * rotation plus translation) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
        if (COL_NARROW) return toRigid_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[1] = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[2] = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
        return dest;
    }


    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        if (COL_NARROW) return toTransform_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[1] = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[2] = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return dest;
    }


    /**
     * Determine whether the real (rotation) part of this dual quaternion has unit length.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param epsilon the maximum allowed deviation of the squared real-part length from {@code 1}
     * @return {@code true} if the real (rotation) part of this dual quaternion has unit length,
     *        {@code false} otherwise
     */
    public boolean isUnit(double epsilon) {
        double[] sd = this.data;
        return java.lang.Math.abs(Math.fma(sd[0], sd[0], Math.fma(sd[1], sd[1], Math.fma(sd[2], sd[2], Math.fma(sd[3], sd[3], -1.0))))) <= epsilon;
    }


    /**
     * Set this dual quaternion to the rotation of {@code angle} radians about the axis
     * {@code axis}, combined with a translation by {@code translation}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param angle the angle in radians
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleDualQuat makeFromAxisAngle(Double3R axis, double angle, Double3R translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axis.x() * _t2;
        double _t4 = axis.y() * _t2;
        double _t5 = axis.z() * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        dd[0] = _t3;
        dd[1] = _t4;
        dd[2] = _t5;
        dd[3] = _t6;
        dd[4] = 0.5 * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        dd[5] = 0.5 * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        dd[6] = 0.5 * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        dd[7] = 0.5 * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
        return this;
    }


    /**
     * Set this dual quaternion to the rotation of {@code angle} radians about the axis
     * ({@code axisX}, {@code axisY}, {@code axisZ}), combined with a translation by
     * ({@code translationX}, {@code translationY}, {@code translationZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param angle the angle in radians
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return this
     */
    @Mutated public DoubleDualQuat makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisY * _t2;
        double _t5 = axisZ * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        dd[0] = _t3;
        dd[1] = _t4;
        dd[2] = _t5;
        dd[3] = _t6;
        dd[4] = 0.5 * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        dd[5] = 0.5 * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        dd[6] = 0.5 * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        dd[7] = 0.5 * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
        return this;
    }


    /**
     * Set this dual quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleDualQuat makeIdentity() {
        if (COL_NARROW) return makeIdentity_narrow();
        double[] dd = this.data;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, DATA_0, 0).intoArray(dd, 0);
        } else {
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 4).intoArray(dd, 4);
        }
        return this;
    }


    /**
     * Set this dual quaternion to a rigid transformation that first rotates by {@code rotation} and
     * then translates by {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleDualQuat makeTranslationRotation(Double3R translation, DoubleQuatR rotation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double[] dd = this.data;
        double _t0 = -rotationY;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX));
        dd[5] = 0.5 * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX)));
        dd[6] = 0.5 * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY)));
        dd[7] = 0.5 * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX)));
        return this;
    }


    /**
     * Set this dual quaternion to a rigid transformation that first rotates by ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) and then translates by
     * ({@code translationX}, {@code translationY}, {@code translationZ}) ({@code T * R}).
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
     * @return this
     */
    @Mutated public DoubleDualQuat makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        double[] dd = this.data;
        double _t0 = -rotationY;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX));
        dd[5] = 0.5 * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX)));
        dd[6] = 0.5 * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY)));
        dd[7] = 0.5 * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX)));
        return this;
    }


    /**
     * Set all components of this dual quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleDualQuat makeZero() {
        if (COL_NARROW) return makeZero_narrow();
        double[] dd = this.data;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, DATA_1, 0).intoArray(dd, 0);
        } else {
            DoubleVector.fromArray(COL_SPECIES, DATA_1, 0).intoArray(dd, 0);
            DoubleVector.fromArray(COL_SPECIES, DATA_1, 4).intoArray(dd, 4);
        }
        return this;
    }


    /**
     * Set this dual quaternion to a pure rotation by {@code rotation} (zero translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleDualQuat set(DoubleQuatR rotation) {
        if (COL_NARROW) return set_narrow(rotation);
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double[] dd = this.data;
        dd[0] = rotation.x();
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a pure rotation by ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) (zero translation).
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return this
     */
    @Mutated public DoubleDualQuat set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (COL_NARROW) return set_narrow(rotationX, rotationY, rotationZ, rotationW);
        double[] dd = this.data;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleDualQuat set(DoubleQuatR rotation, Double3R translation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        double _t0 = -rotationY;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX));
        dd[5] = 0.5 * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX)));
        dd[6] = 0.5 * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY)));
        dd[7] = 0.5 * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX)));
        return this;
    }


    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return this
     */
    @Mutated public DoubleDualQuat set(double rotationX, double rotationY, double rotationZ, double rotationW, double translationX, double translationY, double translationZ) {
        double[] dd = this.data;
        double _t0 = -rotationY;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX));
        dd[5] = 0.5 * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX)));
        dd[6] = 0.5 * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY)));
        dd[7] = 0.5 * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX)));
        return this;
    }


    /**
     * Set this dual quaternion to a pure translation by {@code translation} (identity rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleDualQuat set(Double3R translation) {
        if (COL_NARROW) return set_narrow(translation);
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        VEC_3.intoArray(dd, 0);
        dd[4] = 0.5 * translationX;
        dd[5] = 0.5 * translationY;
        dd[6] = 0.5 * translationZ;
        dd[7] = 0.0;
        return this;
    }


    /**
     * Set this dual quaternion to a pure translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}) (identity rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return this
     */
    @Mutated public DoubleDualQuat set(double translationX, double translationY, double translationZ) {
        if (COL_NARROW) return set_narrow(translationX, translationY, translationZ);
        double[] dd = this.data;
        VEC_3.intoArray(dd, 0);
        dd[4] = 0.5 * translationX;
        dd[5] = 0.5 * translationY;
        dd[6] = 0.5 * translationZ;
        dd[7] = 0.0;
        return this;
    }


    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat dlb(DoubleDualQuatR other, double t, @Mutated DoubleDualQuat dest) {
        return dlb(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), t, dest);
    }


    /**
     * Blend this dual quaternion with ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     * {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) using
     * dual-quaternion linear blending with the weight {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion;
     * {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat dlb(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t9 = Math.fma(otherRX, sd[0], otherRY * sd[1]) + Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0 ? -1.0 : 1.0;
        double _t14 = Math.fma(t, Math.fma(otherRX, _t9, -sd[0]), sd[0]);
        double _t15 = Math.fma(t, Math.fma(otherRY, _t9, -sd[1]), sd[1]);
        double _t16 = Math.fma(t, Math.fma(otherRZ, _t9, -sd[2]), sd[2]);
        double _t17 = Math.fma(t, Math.fma(otherRW, _t9, -sd[3]), sd[3]);
        double _t23 = (1.0 / java.lang.Math.sqrt(Math.fma(_t14, _t14, _t15 * _t15) + Math.fma(_t16, _t16, _t17 * _t17)));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = Math.fma(t, Math.fma(otherDX, _t9, -sd[4]), sd[4]) * _t23;
        dd[5] = Math.fma(t, Math.fma(otherDY, _t9, -sd[5]), sd[5]) * _t23;
        return dlb_s39e58938_1(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat dlb_s39e58938_1(double otherDZ, double otherDW, double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t9, double _t23) {
        dd[6] = Math.fma(t, Math.fma(otherDZ, _t9, -sd[6]), sd[6]) * _t23;
        dd[7] = Math.fma(t, Math.fma(otherDW, _t9, -sd[7]), sd[7]) * _t23;
        return dest;
    }


    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lerp(DoubleDualQuatR other, double t, @Mutated DoubleDualQuat dest) {
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(t, other.rX() - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherDW - sd[7], sd[7]);
        return dest;
    }


    /**
     * Linearly interpolate between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}) using the interpolation factor {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lerp(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(t, otherRX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherDW - sd[7], sd[7]);
        return dest;
    }


    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sclerp(DoubleDualQuatR other, double t, @Mutated DoubleDualQuat dest) {
        return sclerp(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), t, dest);
    }


    /**
     * Screw-linearly interpolate between this dual quaternion and ({@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY},
     * {@code otherDZ}, {@code otherDW}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * must be a unit dual quaternion; this dual quaternion must be a unit dual quaternion.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sclerp(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = -sd[2];
        double _t8 = Math.fma(otherRX, sd[0], otherRY * sd[1]) + Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = Math.fma(sd[0], _t9, sd[3] * _t10);
        double _t77 = Math.fma(_t0, _t11, -(sd[1] * _t12));
        return sclerp_sea9d2379_1(t, dest, sd, ((DoubleDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, Math.fma(sd[1], _t9, -(sd[2] * _t10)) + Math.fma(sd[3], _t11, -(sd[0] * _t12)), Math.fma(sd[0], _t11, sd[3] * _t12) + Math.fma(_t0, _t9, -(sd[1] * _t10)), Math.fma(sd[2], _t12, -(sd[1] * _t11)) + Math.fma(sd[3], _t9, -(sd[0] * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_1(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_sea9d2379_2(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, Math.fma(sd[0], _t13, sd[3] * _t14) + Math.fma(sd[1], _t15, sd[2] * _t16) + (Math.fma(sd[4], _t9, sd[7] * _t10) + Math.fma(sd[5], _t12, sd[6] * _t11)), Math.fma(sd[2], _t15, -(sd[1] * _t16)) + Math.fma(sd[3], _t13, -(sd[0] * _t14)) + (Math.fma(sd[6], _t12, -(sd[5] * _t11)) + Math.fma(sd[7], _t9, -(sd[4] * _t10))), Math.fma(sd[1], _t13, -(sd[2] * _t14)) + Math.fma(sd[3], _t16, -(sd[0] * _t15)) + (Math.fma(sd[5], _t9, -(sd[6] * _t10)) + Math.fma(sd[7], _t11, -(sd[4] * _t12))), Math.fma(sd[0], _t16, sd[3] * _t15) + Math.fma(_t0, _t13, -(sd[1] * _t14)) + (Math.fma(sd[4], _t11, sd[7] * _t12) + Math.fma(_t2, _t9, -(sd[5] * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_2(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
        double _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0) {
            _t105 = _t77 - _t57;
            _t106 = -_t84;
            _t107 = -_t85;
            _t108 = -_t86;
            _t112 = -_t97;
            _t114 = -_t99;
            _t115 = -_t100;
            _t116 = -_t101;
        } else {
            _t105 = _t83;
            _t106 = _t84;
            _t107 = _t85;
            _t108 = _t86;
            _t112 = _t97;
            _t114 = _t99;
            _t115 = _t100;
            _t116 = _t101;
        }
        double _t122 = Math.fma(_t106, _t106, Math.fma(_t107, _t107, _t108 * _t108));
        double _t124 = (1.0 / java.lang.Math.sqrt(_t122));
        double _t126 = _t124 * _t108;
        double _t127 = _t124 * _t106;
        double _t128 = _t124 * _t107;
        double _t129 = t * Math.atan2(java.lang.Math.sqrt(_t122), _t105);
        double _t130 = Math.sin(_t129);
        double _t131 = _t124 * _t112;
        double _t132 = t * _t131;
        double _t137 = Math.cosFromSin(_t130, _t129);
        double _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-28) {
            _t142 = 1.0;
            _t143 = t * _t108;
            _t144 = t * _t106;
            _t145 = t * _t107;
            _t147 = t * t * _t112;
        } else {
            _t142 = _t137;
            _t143 = _t126 * _t130;
            _t144 = _t127 * _t130;
            _t145 = _t128 * _t130;
            _t147 = _t132 * _t130;
        }
        return sclerp_sea9d2379_3(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_3(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t114, double _t115, double _t116, double _t122, double _t124, double _t126, double _t127, double _t128, double _t130, double _t133, double _t142, double _t143, double _t144, double _t145, double _t147, double _t146) {
        double _t160, _t161, _t162;
        if (_t122 < 1.0E-28) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = Math.fma(Math.fma(_t133, _t126, _t114) * _t124, _t130, -(_t146 * _t126));
            _t161 = Math.fma(Math.fma(_t133, _t127, _t115) * _t124, _t130, -(_t146 * _t127));
            _t162 = Math.fma(Math.fma(_t133, _t128, _t116) * _t124, _t130, -(_t146 * _t128));
        }
        return sclerp_sea9d2379_4(dest, sd, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162, Math.fma(sd[0], _t142, sd[3] * _t143) + Math.fma(sd[1], _t144, -(sd[2] * _t145)), Math.fma(sd[1], _t142, sd[2] * _t143) + Math.fma(sd[3], _t145, -(sd[0] * _t144)), Math.fma(sd[0], _t145, sd[3] * _t144) + Math.fma(sd[2], _t142, -(sd[1] * _t143)), Math.fma(sd[0], _t147, sd[3] * _t160) + Math.fma(sd[1], _t161, -(sd[2] * _t162)) + (Math.fma(sd[4], _t142, sd[7] * _t143) + Math.fma(sd[5], _t144, -(sd[6] * _t145))));
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_4(DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t142, double _t143, double _t144, double _t145, double _t147, double _t160, double _t161, double _t162, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(sd[1], _t147, sd[2] * _t160) + Math.fma(sd[3], _t162, -(sd[0] * _t161)) + (Math.fma(sd[5], _t142, sd[6] * _t143) + Math.fma(sd[7], _t145, -(sd[4] * _t144)));
        dd[6] = Math.fma(sd[0], _t162, sd[3] * _t161) + Math.fma(sd[2], _t147, -(sd[1] * _t160)) + (Math.fma(sd[4], _t145, sd[7] * _t144) + Math.fma(sd[6], _t142, -(sd[5] * _t143)));
        dd[7] = Math.fma(sd[3], _t147, -(sd[0] * _t160)) + Math.fma(_t0, _t161, -(sd[1] * _t162)) + (Math.fma(sd[7], _t142, -(sd[4] * _t143)) + Math.fma(_t2, _t144, -(sd[5] * _t145)));
        dd[3] = Math.fma(sd[3], _t142, -(sd[0] * _t143)) - Math.fma(sd[1], _t145, sd[2] * _t144);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        return dest;
    }


    /**
     * Multiply this dual quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat mul(DoubleDualQuatR other, @Mutated DoubleDualQuat dest) {
        return mul(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), dest);
    }


    /**
     * Multiply this dual quaternion by ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     * {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat mul(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        return mul_sc0028309_1(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((DoubleDualQuatImpl) dest).data, Math.fma(otherRX, sd[3], otherRW * sd[0]) + Math.fma(otherRZ, sd[1], -(otherRY * sd[2])), Math.fma(otherRX, sd[2], otherRW * sd[1]) + Math.fma(otherRY, sd[3], -(otherRZ * sd[0])), Math.fma(otherRY, sd[0], otherRZ * sd[3]) + Math.fma(otherRW, sd[2], -(otherRX * sd[1])), Math.fma(otherRX, sd[7], otherRW * sd[4]) + Math.fma(otherRZ, sd[5], -(otherRY * sd[6])) + (Math.fma(otherDX, sd[3], otherDW * sd[0]) + Math.fma(otherDZ, sd[1], -(otherDY * sd[2]))), Math.fma(otherRX, sd[6], otherRW * sd[5]) + Math.fma(otherRY, sd[7], -(otherRZ * sd[4])) + (Math.fma(otherDX, sd[2], otherDW * sd[1]) + Math.fma(otherDY, sd[3], -(otherDZ * sd[0]))));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sc0028309_1(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(otherRY, sd[4], otherRZ * sd[7]) + Math.fma(otherRW, sd[6], -(otherRX * sd[5])) + (Math.fma(otherDY, sd[0], otherDZ * sd[3]) + Math.fma(otherDW, sd[2], -(otherDX * sd[1])));
        dd[7] = Math.fma(otherRW, sd[7], -(otherRX * sd[4])) + Math.fma(-otherRZ, sd[6], -(otherRY * sd[5])) + (Math.fma(otherDW, sd[3], -(otherDX * sd[0])) + Math.fma(-otherDZ, sd[2], -(otherDY * sd[1])));
        dd[3] = Math.fma(otherRW, sd[3], -(otherRX * sd[0])) - Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Pre-multiply {@code other} onto this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preMul(DoubleDualQuatR other, @Mutated DoubleDualQuat dest) {
        return preMul(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), dest);
    }


    /**
     * Pre-multiply ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) onto this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preMul(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        return preMul_s8abe26fa_1(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((DoubleDualQuatImpl) dest).data, Math.fma(otherRX, sd[3], otherRW * sd[0]) + Math.fma(otherRY, sd[2], -(otherRZ * sd[1])), Math.fma(otherRY, sd[3], otherRZ * sd[0]) + Math.fma(otherRW, sd[1], -(otherRX * sd[2])), Math.fma(otherRX, sd[1], otherRW * sd[2]) + Math.fma(otherRZ, sd[3], -(otherRY * sd[0])), Math.fma(otherRX, sd[7], otherRW * sd[4]) + Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (Math.fma(otherDX, sd[3], otherDW * sd[0]) + Math.fma(otherDY, sd[2], -(otherDZ * sd[1]))), Math.fma(otherRY, sd[7], otherRZ * sd[4]) + Math.fma(otherRW, sd[5], -(otherRX * sd[6])) + (Math.fma(otherDY, sd[3], otherDZ * sd[0]) + Math.fma(otherDW, sd[1], -(otherDX * sd[2]))));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8abe26fa_1(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(otherRX, sd[5], otherRW * sd[6]) + Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (Math.fma(otherDX, sd[1], otherDW * sd[2]) + Math.fma(otherDZ, sd[3], -(otherDY * sd[0])));
        dd[7] = Math.fma(otherRW, sd[7], -(otherRX * sd[4])) + Math.fma(-otherRZ, sd[6], -(otherRY * sd[5])) + (Math.fma(otherDW, sd[3], -(otherDX * sd[0])) + Math.fma(-otherDZ, sd[2], -(otherDY * sd[1])));
        dd[3] = Math.fma(otherRW, sd[3], -(otherRX * sd[0])) - Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat addScaled(DoubleDualQuatR other, double weight, @Mutated DoubleDualQuat dest) {
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(weight, other.rX(), sd[0]);
        dd[1] = Math.fma(weight, otherRY, sd[1]);
        dd[2] = Math.fma(weight, otherRZ, sd[2]);
        dd[3] = Math.fma(weight, otherRW, sd[3]);
        dd[4] = Math.fma(weight, otherDX, sd[4]);
        dd[5] = Math.fma(weight, otherDY, sd[5]);
        dd[6] = Math.fma(weight, otherDZ, sd[6]);
        dd[7] = Math.fma(weight, otherDW, sd[7]);
        return dest;
    }


    /**
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) scaled by {@code weight} to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param weight the factor to scale ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     *        {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW})
     *        by before adding
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat addScaled(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double weight, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(weight, otherRX, sd[0]);
        dd[1] = Math.fma(weight, otherRY, sd[1]);
        dd[2] = Math.fma(weight, otherRZ, sd[2]);
        dd[3] = Math.fma(weight, otherRW, sd[3]);
        dd[4] = Math.fma(weight, otherDX, sd[4]);
        dd[5] = Math.fma(weight, otherDY, sd[5]);
        dd[6] = Math.fma(weight, otherDZ, sd[6]);
        dd[7] = Math.fma(weight, otherDW, sd[7]);
        return dest;
    }


    /**
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat conjugate(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = sd[7];
        return dest;
    }


    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat difference(DoubleDualQuatR other, @Mutated DoubleDualQuat dest) {
        return difference(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), dest);
    }


    /**
     * Compute the difference between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}), i.e. the rigid transformation {@code D} with
     * {@code this * D = (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
     * that is
     * {@code D = this^-1 * (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat difference(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = -otherRX;
        return difference_sb481444_1(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((DoubleDualQuatImpl) dest).data, Math.fma(otherRX, sd[3], -(otherRW * sd[0])) + Math.fma(otherRY, sd[2], -(otherRZ * sd[1])), Math.fma(otherRY, sd[3], otherRZ * sd[0]) + Math.fma(_t0, sd[2], -(otherRW * sd[1])), Math.fma(otherRX, sd[1], -(otherRW * sd[2])) + Math.fma(otherRZ, sd[3], -(otherRY * sd[0])), Math.fma(otherRX, sd[7], -(otherRW * sd[4])) + Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (Math.fma(otherDX, sd[3], -(otherDW * sd[0])) + Math.fma(otherDY, sd[2], -(otherDZ * sd[1]))), Math.fma(otherRY, sd[7], otherRZ * sd[4]) + Math.fma(_t0, sd[6], -(otherRW * sd[5])) + (Math.fma(otherDY, sd[3], otherDZ * sd[0]) + Math.fma(-otherDX, sd[2], -(otherDW * sd[1]))));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_sb481444_1(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4) {
        double _buf5 = Math.fma(otherRX, sd[5], -(otherRW * sd[6])) + Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (Math.fma(otherDX, sd[1], -(otherDW * sd[2])) + Math.fma(otherDZ, sd[3], -(otherDY * sd[0])));
        dd[7] = Math.fma(otherRX, sd[4], otherRW * sd[7]) + Math.fma(otherRY, sd[5], otherRZ * sd[6]) + (Math.fma(otherDX, sd[0], otherDW * sd[3]) + Math.fma(otherDY, sd[1], otherDZ * sd[2]));
        dd[3] = Math.fma(otherRX, sd[0], otherRW * sd[3]) - Math.fma(-otherRZ, sd[2], -(otherRY * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Compute the dot product of this dual quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this dual quaternion and {@code other}
     */
    public double dot(DoubleDualQuatR other) {
        double[] sd = this.data;
        return Math.fma(other.rX(), sd[0], other.rY() * sd[1]) + Math.fma(other.rZ(), sd[2], other.rW() * sd[3]) + (Math.fma(other.dX(), sd[4], other.dY() * sd[5]) + Math.fma(other.dZ(), sd[6], other.dW() * sd[7]));
    }


    /**
     * Compute the dot product of this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the dot product of this dual quaternion and ({@code otherRX}, {@code otherRY},
     *        {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     *        {@code otherDW})
     */
    public double dot(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        double[] sd = this.data;
        return Math.fma(otherRX, sd[0], otherRY * sd[1]) + Math.fma(otherRZ, sd[2], otherRW * sd[3]) + (Math.fma(otherDX, sd[4], otherDY * sd[5]) + Math.fma(otherDZ, sd[6], otherDW * sd[7]));
    }


    /**
     * Compute the dual-number conjugate of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat dualConjugate(@Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return dualConjugate_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        return dest;
    }


    /**
     * Compute the exponential of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat exp(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t4 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t5 = Math.fma(sd[2], sd[6], Math.fma(sd[0], sd[4], sd[1] * sd[5]));
        double _t7 = java.lang.Math.sqrt(_t4);
        double _t6 = 1.0 / _t7;
        double _t8 = Math.sin(_t7);
        double _sp0 = _t6 * _t8;
        double _t9 = sd[0] * _t6;
        double _t10 = sd[1] * _t6;
        double _t11 = sd[2] * _t6;
        double _t12 = _t5 * _t6;
        double _t13 = Math.cosFromSin(_t8, _t7);
        double _t14 = _t12 * _t13;
        if (_t4 < 1.0E-28) {
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = 1.0;
            dd[4] = sd[4];
            dd[5] = sd[5];
            dd[6] = sd[6];
            dd[7] = -_t5;
        } else {
            dd[0] = _t9 * _t8;
            dd[1] = _t10 * _t8;
            dd[2] = _t11 * _t8;
            dd[3] = _t13;
            dd[4] = Math.fma(_t9, _t14, Math.fma(-_t9, _t12, sd[4]) * _sp0);
            dd[5] = Math.fma(_t10, _t14, Math.fma(-_t10, _t12, sd[5]) * _sp0);
            dd[6] = Math.fma(_t11, _t14, Math.fma(-_t11, _t12, sd[6]) * _sp0);
            dd[7] = -(_t12 * _t8);
        }
        return dest;
    }


    /**
     * Get the dual part of this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getDual(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return getDual_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 0);
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Y and Z
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t3 = sd[2] * sd[2];
        double _t8 = 2.0 * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t9 = 2.0 * Math.fma(sd[0], sd[3], -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[0], sd[3], _t1), Math.fma(-2.0, Math.fma(sd[0], sd[0], _t3), 1.0));
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t10);
            dd[2] = Math.atan2(2.0 * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0, Math.fma(sd[1], sd[1], _t3), 1.0));
        }
        dd[1] = Math.atan2(_t8, java.lang.Math.sqrt(_t12));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Z and Y
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[1] * sd[2];
        double _t7 = 2.0 * Math.fma(sd[0], sd[3], _t1);
        double _t8 = 2.0 * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[0], sd[0], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[0], sd[3], -_t1), Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0));
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t7, _t9);
            dd[1] = Math.atan2(2.0 * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0, Math.fma(sd[1], sd[1], _t0), 1.0));
        }
        dd[2] = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, X and Z
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = sd[2] * sd[2];
        double _t8 = 2.0 * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t9 = 2.0 * Math.fma(sd[0], sd[3], -(sd[1] * sd[2]));
        double _t10 = Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        dd[0] = Math.atan2(_t9, java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            dd[1] = Math.atan2(2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0, Math.fma(sd[1], sd[1], _t3), 1.0));
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t8, _t10);
            dd[2] = Math.atan2(2.0 * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0, Math.fma(sd[0], sd[0], _t3), 1.0));
        }
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, Z and X
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t7 = 2.0 * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t8 = 2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[1], sd[1], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(2.0 * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0));
        } else {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[0], sd[3], -(sd[1] * sd[2])), Math.fma(-2.0, Math.fma(sd[0], sd[0], _t0), 1.0));
            dd[1] = Math.atan2(_t8, _t9);
        }
        dd[2] = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, X and Y
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[2] * sd[2];
        double _t7 = 2.0 * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t8 = 2.0 * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[0], sd[0], _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        dd[0] = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(2.0 * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0, Math.fma(sd[1], sd[1], _t1), 1.0));
        } else {
            dd[1] = Math.atan2(2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0));
            dd[2] = Math.atan2(_t8, _t9);
        }
        return dest;
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, Y and X
     * axes, in that order and store the result in {@code dest}.
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t7 = 2.0 * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t8 = 2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[1], sd[1], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0, Math.fma(sd[0], sd[0], _t0), 1.0));
        } else {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[0], sd[3], sd[1] * sd[2]), Math.fma(-2.0, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0));
            dd[2] = Math.atan2(_t7, _t9);
        }
        dd[1] = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the rotation of this dual quaternion, i.e. its raw real part (a unit quaternion only when
     * this dual quaternion has unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getRotation(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return getRotation_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }


    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getTranslation(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[1] = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[2] = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        return dest;
    }


    /**
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat inverseUnit(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = sd[7];
        return dest;
    }


    /**
     * Invert this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat invert(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t8 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 * (Math.fma(sd[0], sd[4], sd[1] * sd[5]) + Math.fma(sd[2], sd[6], sd[3] * sd[7])) / (_t8 * _t8);
        dd[4] = Math.fma(sd[0], _sp0, -(sd[4] * _t8_inv));
        dd[0] = -(sd[0] * _t8_inv);
        dd[5] = Math.fma(sd[1], _sp0, -(sd[5] * _t8_inv));
        dd[1] = -(sd[1] * _t8_inv);
        dd[6] = Math.fma(sd[2], _sp0, -(sd[6] * _t8_inv));
        dd[2] = -(sd[2] * _t8_inv);
        dd[7] = Math.fma(sd[7], _t8_inv, -(sd[3] * _sp0));
        dd[3] = sd[3] * _t8_inv;
        return dest;
    }


    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    public double length() {
        double[] sd = this.data;
        return java.lang.Math.sqrt(Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]));
    }


    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public double lengthSquared() {
        double[] sd = this.data;
        return Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
    }


    /**
     * Compute the natural logarithm of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat log(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (sd[3] < 0.0) {
            _t8 = -sd[2];
            _t9 = -sd[0];
            _t10 = -sd[1];
            _t11 = -sd[3];
            _t12 = -sd[4];
            _t14 = -sd[5];
            _t15 = -sd[6];
        } else {
            _t8 = sd[2];
            _t9 = sd[0];
            _t10 = sd[1];
            _t11 = sd[3];
            _t12 = sd[4];
            _t14 = sd[5];
            _t15 = sd[6];
        }
        double _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t25 = _t19 * (sd[3] < 0.0 ? -sd[7] : sd[7]);
        return log_sfb9ef421_1(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2(java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11);
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat log_sfb9ef421_1(DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t14, double _t15, double _t18, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        if (_t18 < 1.0E-28) {
            dd[0] = _t9;
            dd[1] = _t10;
            dd[2] = _t8;
            dd[4] = _t12;
            dd[5] = _t14;
            dd[6] = _t15;
        } else {
            dd[0] = _t21 * _t22;
            dd[1] = _t23 * _t22;
            dd[2] = _t24 * _t22;
            dd[4] = Math.fma(Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25));
            dd[5] = Math.fma(Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25));
            dd[6] = Math.fma(Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25));
        }
        dd[3] = 0.0;
        dd[7] = 0.0;
        return dest;
    }


    /**
     * Set this dual quaternion to the rigid motion of the given matrix: rotation from its
     * upper-left 3x3 block and translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleDualQuat makeFromMatrix(Double4x4R m) {
        double[] mData = ((Double4x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_sf64e4286_1(this.data, mData, -mData[14], mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_sf64e4286_1(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
        double _t63, _t64, _t65, _t66;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5 * java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * Math.fma(_t0, _t64, Math.fma(mData[12], _t66, mData[13] * _t65));
        dd[5] = 0.5 * Math.fma(mData[14], _t63, Math.fma(mData[13], _t66, -(mData[12] * _t65)));
        return makeFromMatrix_sf64e4286_2(dd, mData, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_sf64e4286_2(double[] dd, double[] mData, double _t0, double _t63, double _t64, double _t65, double _t66) {
        dd[6] = 0.5 * Math.fma(mData[14], _t66, Math.fma(mData[12], _t64, -(mData[13] * _t63)));
        dd[7] = 0.5 * Math.fma(_t0, _t65, Math.fma(-mData[13], _t64, -(mData[12] * _t63)));
        return this;
    }


    /**
     * Set this dual quaternion to the rigid motion of the given matrix: rotation from its
     * upper-left 3x3 block and translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleDualQuat makeFromMatrix(Double3x4R m) {
        double[] mData = ((Double3x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s89828b35_1(this.data, mData, -mData[11], mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_s89828b35_1(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
        double _t63, _t64, _t65, _t66;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5 * java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * Math.fma(_t0, _t64, Math.fma(mData[3], _t66, mData[7] * _t65));
        dd[5] = 0.5 * Math.fma(mData[11], _t63, Math.fma(mData[7], _t66, -(mData[3] * _t65)));
        return makeFromMatrix_s89828b35_2(dd, mData, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_s89828b35_2(double[] dd, double[] mData, double _t0, double _t63, double _t64, double _t65, double _t66) {
        dd[6] = 0.5 * Math.fma(mData[11], _t66, Math.fma(mData[3], _t64, -(mData[7] * _t63)));
        dd[7] = 0.5 * Math.fma(_t0, _t65, Math.fma(-mData[7], _t64, -(mData[3] * _t63)));
        return this;
    }


    /**
     * Set this dual quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first), with zero
     * translation.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleDualQuat makeFromMatrix(Double3x3R m) {
        if (COL_NARROW) return makeFromMatrix_narrow(m);
        double[] mData = ((Double3x3Impl) m).data;
        double _t1 = 1.0 - mData[0];
        double _t13 = mData[8] + (mData[0] + mData[4]);
        double _t14 = 1.0 + _t13;
        double _t15 = mData[0] + (1.0 - mData[4] - mData[8]);
        double _t16 = mData[4] + (_t1 - mData[8]);
        double _t17 = mData[8] + (_t1 - mData[4]);
        return makeFromMatrix_s81574d1e_1(this.data, mData, mData[5] - mData[7], mData[3] + mData[1], mData[6] + mData[2], mData[6] - mData[2], mData[7] + mData[5], mData[1] - mData[3], _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_s81574d1e_1(double[] dd, double[] mData, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            dd[0] = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (mData[0] > java.lang.Math.max(mData[4], mData[8])) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
            } else {
                if (mData[4] > mData[8]) {
                    dd[0] = _sp1 * _t5;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                } else {
                    dd[0] = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat normalize(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t4 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0) {
            dd[0] = sd[0] * _t5;
            dd[1] = sd[1] * _t5;
            dd[2] = sd[2] * _t5;
            dd[3] = sd[3] * _t5;
            dd[4] = sd[4] * _t5;
            dd[5] = sd[5] * _t5;
            dd[6] = sd[6] * _t5;
            dd[7] = sd[7] * _t5;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
            dd[4] = 0.0;
            dd[5] = 0.0;
            dd[6] = 0.0;
            dd[7] = 0.0;
        }
        return dest;
    }


    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat pow(double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (sd[3] < 0.0) {
            _t8 = -sd[2];
            _t9 = -sd[0];
            _t10 = -sd[1];
            _t11 = -sd[3];
            _t12 = -sd[4];
            _t13 = -sd[7];
            _t14 = -sd[5];
            _t15 = -sd[6];
        } else {
            _t8 = sd[2];
            _t9 = sd[0];
            _t10 = sd[1];
            _t11 = sd[3];
            _t12 = sd[4];
            _t13 = sd[7];
            _t14 = sd[5];
            _t15 = sd[6];
        }
        double _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s4d980ef8_1(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** Piece 2 of {@code pow}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat pow_s4d980ef8_1(double t, DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t13, double _t14, double _t15, double _t18, double _t19, double _t21, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31) {
        if (_t18 < 1.0E-28) {
            dd[0] = t * _t9;
            dd[1] = t * _t10;
            dd[2] = t * _t8;
            dd[3] = 1.0;
            dd[4] = t * _t12;
            dd[5] = t * _t14;
            dd[6] = t * _t15;
            dd[7] = t * t * _t13;
        } else {
            dd[0] = _t21 * _t28;
            dd[1] = _t23 * _t28;
            dd[2] = _t24 * _t28;
            dd[3] = _t30;
            dd[4] = Math.fma(Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            dd[5] = Math.fma(Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            dd[6] = Math.fma(Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
            dd[7] = _t27 * _t28;
        }
        return dest;
    }


    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setDual(DoubleQuatR dual, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setDual_narrow(dual, dest);
        double dualX = dual.x();
        double dualY = dual.y();
        double dualZ = dual.z();
        double dualW = dual.w();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }


    /**
     * Set the dual half of this dual quaternion to ({@code dualX}, {@code dualY}, {@code dualZ},
     * {@code dualW}), keeping the real (rotation) half as it is and store the result in
     * {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dualX the {@code x} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualY the {@code y} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualZ the {@code z} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualW the {@code w} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setDual(double dualX, double dualY, double dualZ, double dualW, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setDual_narrow(dualX, dualY, dualZ, dualW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }


    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setReal(DoubleQuatR real, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setReal_narrow(real, dest);
        double realY = real.y();
        double realZ = real.z();
        double realW = real.w();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = real.x();
        dd[1] = realY;
        dd[2] = realZ;
        dd[3] = realW;
        DoubleVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        return dest;
    }


    /**
     * Set the real (rotation) half of this dual quaternion to ({@code realX}, {@code realY},
     * {@code realZ}, {@code realW}), keeping the dual half as it is and store the result in
     * {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param realX the {@code x} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realY the {@code y} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realZ the {@code z} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realW the {@code w} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setReal(double realX, double realY, double realZ, double realW, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setReal_narrow(realX, realY, realZ, realW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = realX;
        dd[1] = realY;
        dd[2] = realZ;
        dd[3] = realW;
        DoubleVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        return dest;
    }


    /**
     * Set the rotation of this dual quaternion to {@code rotation} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setRotation(DoubleQuatR rotation, @Mutated DoubleDualQuat dest) {
        return setRotation(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Set the rotation of this dual quaternion to ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setRotation(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -rotationY;
        double _t22 = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        double _t23 = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        double _t24 = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5 * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        dd[5] = 0.5 * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        dd[6] = 0.5 * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        dd[7] = 0.5 * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
        return dest;
    }


    /**
     * Set the translation of this dual quaternion to {@code translation} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setTranslation(Double3R translation, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setTranslation_narrow(translation, dest);
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[1];
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5 * Math.fma(sd[0], translationZ, Math.fma(sd[3], translationY, -(sd[2] * translationX)));
        dd[6] = 0.5 * Math.fma(sd[3], translationZ, Math.fma(sd[1], translationX, -(sd[0] * translationY)));
        dd[7] = 0.5 * Math.fma(-sd[2], translationZ, Math.fma(_t0, translationY, -(sd[0] * translationX)));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }


    /**
     * Set the translation of this dual quaternion to ({@code translationX}, {@code translationY},
     * {@code translationZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setTranslation(double translationX, double translationY, double translationZ, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return setTranslation_narrow(translationX, translationY, translationZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[1];
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5 * Math.fma(sd[0], translationZ, Math.fma(sd[3], translationY, -(sd[2] * translationX)));
        dd[6] = 0.5 * Math.fma(sd[3], translationZ, Math.fma(sd[1], translationX, -(sd[0] * translationY)));
        dd[7] = 0.5 * Math.fma(-sd[2], translationZ, Math.fma(_t0, translationY, -(sd[0] * translationX)));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }


    /**
     * Compute the matrix representation of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = Math.fma(-2.0, sd[2] * sd[2], 1.0);
        dd[0] = Math.fma(-2.0, _t0, _t6);
        dd[1] = 2.0 * Math.fma(sd[0], sd[1], _t2);
        dd[2] = Math.fma(-2.0, _t3, (sd[0] + sd[0]) * sd[2]);
        dd[3] = 0.0;
        dd[4] = Math.fma(-2.0, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[5] = Math.fma(-2.0, _t4, _t6);
        dd[6] = 2.0 * Math.fma(sd[0], sd[3], _t5);
        dd[7] = 0.0;
        dd[8] = 2.0 * Math.fma(sd[0], sd[2], _t3);
        dd[9] = Math.fma(-2.0, sd[0] * sd[3], _t5 + _t5);
        dd[10] = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        dd[11] = 0.0;
        return toMatrix_s9c292502_1(dest, sd, dd);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1(Double4x4 dest, double[] sd, double[] dd) {
        dd[12] = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[13] = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[14] = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _sp0 = sd[0] + sd[0];
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = Math.fma(-2.0, sd[2] * sd[2], 1.0);
        dd[0] = Math.fma(-2.0, _t0, _t6);
        dd[1] = 2.0 * Math.fma(sd[0], sd[1], _t2);
        dd[2] = Math.fma(-2.0, _t3, _sp0 * sd[2]);
        dd[3] = Math.fma(-2.0, _t2, _sp0 * sd[1]);
        dd[4] = Math.fma(-2.0, _t4, _t6);
        dd[5] = 2.0 * Math.fma(sd[0], sd[3], _t5);
        dd[6] = 2.0 * Math.fma(sd[0], sd[2], _t3);
        dd[7] = Math.fma(-2.0, sd[0] * sd[3], _t5 + _t5);
        dd[8] = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = Math.fma(-2.0, sd[2] * sd[2], 1.0);
        dd[0] = Math.fma(-2.0, _t0, _t6);
        dd[1] = Math.fma(-2.0, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[2] = 2.0 * Math.fma(sd[0], sd[2], _t3);
        dd[3] = 2.0 * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[4] = 2.0 * Math.fma(sd[0], sd[1], _t2);
        dd[5] = Math.fma(-2.0, _t4, _t6);
        dd[6] = Math.fma(-2.0, sd[0] * sd[3], _t5 + _t5);
        dd[7] = 2.0 * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[8] = Math.fma(-2.0, _t3, (sd[0] + sd[0]) * sd[2]);
        return toMatrix3x4_sec42bb4e_1(dest, sd, dd, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1(Double3x4 dest, double[] sd, double[] dd, double _t0, double _t4, double _t5) {
        dd[9] = 2.0 * Math.fma(sd[0], sd[3], _t5);
        dd[10] = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        dd[11] = 2.0 * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(Double3R dir, Double3R up, @Mutated DoubleDualQuat dest) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
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
    public DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = Math.fma(-dirY, _sp0, upY);
        double _t29 = Math.fma(-dirZ, _sp0, upZ);
        double _t30 = Math.fma(-dirX, _sp0, upX);
        double _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        return lookAlong_s51104727_1(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_1(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _ct0) {
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t65 = Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        return lookAlong_s51104727_2(dirZ, dest, sd, dd, _t1, _t16, _t17, _t18, _t20, _t22, _t37, _t45, _t46, _t47, Math.fma(-_t37, _t45, 1.0), Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t65), Math.fma(-dirY, _t16, _t65), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0)))), Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0)))));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_2(double dirZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t20, double _t22, double _t37, double _t45, double _t46, double _t47, double _t50, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80) {
        double _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t78));
        double _sp4 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        double _t126, _t127, _t128, _t129;
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5 * java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5 * java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        return lookAlong_s51104727_3(dest, sd, dd, _t126, _t127, _t128, _t129);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_3(DoubleDualQuat dest, double[] sd, double[] dd, double _t126, double _t127, double _t128, double _t129) {
        double _buf0 = Math.fma(sd[0], _t129, sd[3] * _t126) + Math.fma(sd[1], _t127, -(sd[2] * _t128));
        double _buf1 = Math.fma(sd[1], _t129, sd[2] * _t126) + Math.fma(sd[3], _t128, -(sd[0] * _t127));
        double _buf2 = Math.fma(sd[0], _t128, sd[3] * _t127) + Math.fma(sd[2], _t129, -(sd[1] * _t126));
        dd[3] = Math.fma(sd[3], _t129, -(sd[0] * _t126)) - Math.fma(sd[1], _t128, sd[2] * _t127);
        double _buf3 = Math.fma(sd[4], _t129, sd[7] * _t126) + Math.fma(sd[5], _t127, -(sd[6] * _t128));
        double _buf4 = Math.fma(sd[5], _t129, sd[6] * _t126) + Math.fma(sd[7], _t128, -(sd[4] * _t127));
        double _buf5 = Math.fma(sd[4], _t128, sd[7] * _t127) + Math.fma(sd[6], _t129, -(sd[5] * _t126));
        dd[7] = Math.fma(sd[7], _t129, -(sd[4] * _t126)) - Math.fma(sd[5], _t128, sd[6] * _t127);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
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
        double _t29 = -_t26;
        double _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0;
            _t41 = -_t24;
        }
        double _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        double _t44 = Math.fma(_t43, _t25, _t22);
        double _t45 = Math.fma(_t43, _t26, _t23);
        return lookAlong_degenerate_s41d16670_1(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t44, _t45, Math.fma(_t43, _t24, _t21), Math.fma(_t44, _t26, -(_t45 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t44, double _t45, double _t46, double _t55) {
        double _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        double _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        double _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        double _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0 / java.lang.Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0 / java.lang.Math.sqrt(_t61));
        }
        double _t67 = -_t66;
        double _t68 = _t66 * _t62;
        double _t69 = _t66 * _t63;
        double _t70 = -_t68;
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return lookAlong_degenerate_s41d16670_2(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_2(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        double _t102 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        double _t103 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s41d16670_3(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), Math.fma(_t66, _t64, _t91), Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_3(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
        double _t148, _t149, _t150, _t151;
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5 * java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5 * java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5 * java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        return lookAlong_degenerate_s41d16670_4(dest, sd, dd, _t148, _t149, _t150, _t151, Math.fma(sd[0], _t151, sd[3] * _t148) + Math.fma(sd[1], _t149, -(sd[2] * _t150)), Math.fma(sd[1], _t151, sd[2] * _t148) + Math.fma(sd[3], _t150, -(sd[0] * _t149)), Math.fma(sd[0], _t150, sd[3] * _t149) + Math.fma(sd[2], _t151, -(sd[1] * _t148)));
    }

    /**
     * Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget. Shared by the
     * identical private paths of {@code lookAlong} and {@code rotateAxis}; reached only through
     * them.
     */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_4(DoubleDualQuat dest, double[] sd, double[] dd, double _t148, double _t149, double _t150, double _t151, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t151, -(sd[0] * _t148)) - Math.fma(sd[1], _t150, sd[2] * _t149);
        double _buf3 = Math.fma(sd[4], _t151, sd[7] * _t148) + Math.fma(sd[5], _t149, -(sd[6] * _t150));
        double _buf4 = Math.fma(sd[5], _t151, sd[6] * _t148) + Math.fma(sd[7], _t150, -(sd[4] * _t149));
        double _buf5 = Math.fma(sd[4], _t150, sd[7] * _t149) + Math.fma(sd[6], _t151, -(sd[5] * _t148));
        dd[7] = Math.fma(sd[7], _t151, -(sd[4] * _t148)) - Math.fma(sd[5], _t150, sd[6] * _t149);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated DoubleDualQuat makeRotationAxis(double angle, Double3R axis) {
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axis);
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the axis
     * ({@code axisX}, {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axisX, axisY, axisZ);
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return this
     */
    public @Mutated DoubleDualQuat makeRotationLookAlong(Double3R dir, Double3R up) {
        if (COL_NARROW) return makeRotationLookAlong_narrow(dir, up);
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Set this dual quaternion to a rotation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        return COL_NARROW ? makeRotationLookAlong_narrow(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_wide(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1(double dirX, double dirY, double dirZ, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45, double _t46) {
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = Math.fma(-_t37, _t45, 1.0);
        double _t64 = Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0))));
        double _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0))));
        double _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        double _t82 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s309f29f5_2(dirZ, dd, _t16, _t17, _t37, _t45, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t64), Math.fma(-dirY, _t16, _t64), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2(double dirZ, double[] dd, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t74, double _t75, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4, double _sp2) {
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleDualQuat makeRotationLookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        double _t29 = -_t26;
        double _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0;
            _t41 = -_t24;
        }
        double _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        double _t44 = Math.fma(_t43, _t25, _t22);
        double _t45 = Math.fma(_t43, _t26, _t23);
        double _t46 = Math.fma(_t43, _t24, _t21);
        return makeRotationLookAlong_degenerate_se3802ea_1(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, Math.fma(_t44, _t26, -(_t45 * _t25)), Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
        double _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        double _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        double _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0 / java.lang.Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0 / java.lang.Math.sqrt(_t61));
        }
        double _t67 = -_t66;
        double _t68 = _t66 * _t62;
        double _t69 = _t66 * _t63;
        double _t70 = -_t68;
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return makeRotationLookAlong_degenerate_se3802ea_2(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2(double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        double _t101 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        double _t102 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_degenerate_se3802ea_3(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), Math.fma(_t66, _t64, _t91), Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3(double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationX(double angle) {
        if (COL_NARROW) return makeRotationX_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = _t1;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.cosFromSin(_t1, _t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
     * radians about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so
     * a vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationXYZ(double angleX, double angleY, double angleZ) {
        if (COL_NARROW) return makeRotationXYZ_narrow(angleX, angleY, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
     * radians about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so
     * a vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationXZY(double angleX, double angleZ, double angleY) {
        if (COL_NARROW) return makeRotationXZY_narrow(angleX, angleZ, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationY(double angle) {
        if (COL_NARROW) return makeRotationY_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = _t1;
        dd[2] = 0.0;
        dd[3] = Math.cosFromSin(_t1, _t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
     * radians about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so
     * a vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationYXZ(double angleY, double angleX, double angleZ) {
        if (COL_NARROW) return makeRotationYXZ_narrow(angleY, angleX, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
     * radians about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so
     * a vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationYZX(double angleY, double angleZ, double angleX) {
        if (COL_NARROW) return makeRotationYZX_narrow(angleY, angleZ, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t5, _t2);
        double _t7 = Math.cosFromSin(_t3, _t0);
        double _t8 = Math.cosFromSin(_t4, _t1);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t8;
        double _t11 = _t4 * _t7;
        double _t12 = _t7 * _t8;
        dd[0] = Math.fma(_t9, _t6, _t12 * _t5);
        dd[1] = Math.fma(_t10, _t6, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dd[3] = Math.fma(_t12, _t6, -(_t9 * _t5));
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationZ(double angle) {
        if (COL_NARROW) return makeRotationZ_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
     * radians about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so
     * a vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationZXY(double angleZ, double angleX, double angleY) {
        if (COL_NARROW) return makeRotationZXY_narrow(angleZ, angleX, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
     * radians about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so
     * a vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationZYX(double angleZ, double angleY, double angleX) {
        if (COL_NARROW) return makeRotationZYX_narrow(angleZ, angleY, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        dd[0] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[1] = Math.fma(_t10, _t8, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t8);
        VEC_2.intoArray(dd, 4);
        return this;
    }


    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preRotate(DoubleQuatR rotation, @Mutated DoubleDualQuat dest) {
        return preRotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Pre-multiply the rotation represented by the quaternion ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) onto this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preRotate(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _buf0 = Math.fma(rotationX, sd[3], rotationW * sd[0]) + Math.fma(rotationY, sd[2], -(rotationZ * sd[1]));
        double _buf1 = Math.fma(rotationY, sd[3], rotationZ * sd[0]) + Math.fma(rotationW, sd[1], -(rotationX * sd[2]));
        double _buf2 = Math.fma(rotationX, sd[1], rotationW * sd[2]) + Math.fma(rotationZ, sd[3], -(rotationY * sd[0]));
        dd[3] = Math.fma(rotationW, sd[3], -(rotationX * sd[0])) - Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        return preRotate_sb3c1d689_1(rotationX, rotationY, rotationZ, rotationW, dest, sd, dd, _buf0, _buf1, _buf2, Math.fma(rotationX, sd[7], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[5])), Math.fma(rotationY, sd[7], rotationZ * sd[4]) + Math.fma(rotationW, sd[5], -(rotationX * sd[6])), Math.fma(rotationX, sd[5], rotationW * sd[6]) + Math.fma(rotationZ, sd[7], -(rotationY * sd[4])));
    }

    /**
     * Piece 2 of {@code preRotate}, split to fit the inline budget. Shared by the identical private
     * paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private DoubleDualQuat preRotate_sb3c1d689_1(double rotationX, double rotationY, double rotationZ, double rotationW, DoubleDualQuat dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3, double _buf4, double _buf5) {
        dd[7] = Math.fma(rotationW, sd[7], -(rotationX * sd[4])) - Math.fma(rotationY, sd[5], rotationZ * sd[6]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotate(DoubleQuatR rotation, @Mutated DoubleDualQuat dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotate(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _buf0 = Math.fma(rotationX, sd[3], rotationW * sd[0]) + Math.fma(rotationZ, sd[1], -(rotationY * sd[2]));
        double _buf1 = Math.fma(rotationX, sd[2], rotationW * sd[1]) + Math.fma(rotationY, sd[3], -(rotationZ * sd[0]));
        double _buf2 = Math.fma(rotationY, sd[0], rotationZ * sd[3]) + Math.fma(rotationW, sd[2], -(rotationX * sd[1]));
        dd[3] = Math.fma(rotationW, sd[3], -(rotationX * sd[0])) - Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        return preRotate_sb3c1d689_1(rotationX, rotationY, rotationZ, rotationW, dest, sd, dd, _buf0, _buf1, _buf2, Math.fma(rotationX, sd[7], rotationW * sd[4]) + Math.fma(rotationZ, sd[5], -(rotationY * sd[6])), Math.fma(rotationX, sd[6], rotationW * sd[5]) + Math.fma(rotationY, sd[7], -(rotationZ * sd[4])), Math.fma(rotationY, sd[4], rotationZ * sd[7]) + Math.fma(rotationW, sd[6], -(rotationX * sd[5])));
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateAxis(double angle, Double3R axis, @Mutated DoubleDualQuat dest) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return lookAlong_degenerate_s41d16670_4(dest, sd, ((DoubleDualQuatImpl) dest).data, _t2, _t3, _t4, _t5, Math.fma(sd[0], _t5, sd[3] * _t2) + Math.fma(sd[1], _t3, -(sd[2] * _t4)), Math.fma(sd[1], _t5, sd[2] * _t2) + Math.fma(sd[3], _t4, -(sd[0] * _t3)), Math.fma(sd[0], _t4, sd[3] * _t3) + Math.fma(sd[2], _t5, -(sd[1] * _t2)));
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleDualQuat rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleDualQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return lookAlong_degenerate_s41d16670_4(dest, sd, ((DoubleDualQuatImpl) dest).data, _t2, _t3, _t4, _t5, Math.fma(sd[0], _t5, sd[3] * _t2) + Math.fma(sd[1], _t3, -(sd[2] * _t4)), Math.fma(sd[1], _t5, sd[2] * _t2) + Math.fma(sd[3], _t4, -(sd[0] * _t3)), Math.fma(sd[0], _t4, sd[3] * _t3) + Math.fma(sd[2], _t5, -(sd[1] * _t2)));
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateX(double angle, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _buf0 = Math.fma(sd[0], _t2, sd[3] * _t1);
        double _buf1 = Math.fma(sd[1], _t2, sd[2] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[1] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        double _buf2 = Math.fma(sd[4], _t2, sd[7] * _t1);
        double _buf3 = Math.fma(sd[5], _t2, sd[6] * _t1);
        dd[6] = Math.fma(sd[6], _t2, -(sd[5] * _t1));
        dd[7] = Math.fma(sd[7], _t2, -(sd[4] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateXYZ(double angleX, double angleY, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateXYZ_sf95923e6_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_sf95923e6_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        double _buf3 = Math.fma(sd[4], _t21, sd[7] * _t19) + Math.fma(sd[5], _t20, -(sd[6] * _t22));
        double _buf4 = Math.fma(sd[5], _t21, sd[6] * _t19) + Math.fma(sd[7], _t22, -(sd[4] * _t20));
        double _buf5 = Math.fma(sd[4], _t22, sd[7] * _t20) + Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = Math.fma(sd[7], _t21, -(sd[4] * _t19)) - Math.fma(sd[5], _t22, sd[6] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateXZY(double angleX, double angleZ, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        double _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        return rotateXZY_s82778362_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s82778362_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        double _buf3 = Math.fma(sd[4], _t19, sd[7] * _t21) + Math.fma(sd[5], _t20, -(sd[6] * _t22));
        double _buf4 = Math.fma(sd[5], _t19, sd[6] * _t21) + Math.fma(sd[7], _t22, -(sd[4] * _t20));
        double _buf5 = Math.fma(sd[4], _t22, sd[7] * _t20) + Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = Math.fma(sd[7], _t19, -(sd[4] * _t21)) - Math.fma(sd[5], _t22, sd[6] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateY(double angle, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _buf0 = Math.fma(sd[0], _t2, -(sd[2] * _t1));
        double _buf1 = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[0], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        double _buf2 = Math.fma(sd[4], _t2, -(sd[6] * _t1));
        double _buf3 = Math.fma(sd[5], _t2, sd[7] * _t1);
        dd[6] = Math.fma(sd[4], _t1, sd[6] * _t2);
        dd[7] = Math.fma(sd[7], _t2, -(sd[5] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateYXZ(double angleY, double angleX, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateYXZ_se2acb55a_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t20) + Math.fma(sd[1], _t21, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t20) + Math.fma(sd[3], _t22, -(sd[0] * _t21)), Math.fma(sd[0], _t22, sd[3] * _t21) + Math.fma(sd[2], _t19, -(sd[1] * _t20)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYXZ_se2acb55a_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t20)) - Math.fma(sd[1], _t22, sd[2] * _t21);
        double _buf3 = Math.fma(sd[4], _t19, sd[7] * _t20) + Math.fma(sd[5], _t21, -(sd[6] * _t22));
        double _buf4 = Math.fma(sd[5], _t19, sd[6] * _t20) + Math.fma(sd[7], _t22, -(sd[4] * _t21));
        double _buf5 = Math.fma(sd[4], _t22, sd[7] * _t21) + Math.fma(sd[6], _t19, -(sd[5] * _t20));
        dd[7] = Math.fma(sd[7], _t19, -(sd[4] * _t20)) - Math.fma(sd[5], _t22, sd[6] * _t21);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateYZX(double angleY, double angleZ, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateYZX_s483bd26a_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s483bd26a_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        double _buf3 = Math.fma(sd[4], _t21, sd[7] * _t19) + Math.fma(sd[5], _t22, -(sd[6] * _t20));
        double _buf4 = Math.fma(sd[5], _t21, sd[6] * _t19) + Math.fma(sd[7], _t20, -(sd[4] * _t22));
        double _buf5 = Math.fma(sd[4], _t20, sd[7] * _t22) + Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = Math.fma(sd[7], _t21, -(sd[4] * _t19)) - Math.fma(sd[5], _t20, sd[6] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateZ(double angle, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _buf0 = Math.fma(sd[0], _t2, sd[1] * _t1);
        dd[1] = Math.fma(sd[1], _t2, -(sd[0] * _t1));
        double _buf1 = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        double _buf2 = Math.fma(sd[4], _t2, sd[5] * _t1);
        dd[5] = Math.fma(sd[5], _t2, -(sd[4] * _t1));
        double _buf3 = Math.fma(sd[6], _t2, sd[7] * _t1);
        dd[7] = Math.fma(sd[7], _t2, -(sd[6] * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        dd[6] = _buf3;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateZXY(double angleZ, double angleX, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZXY_s3ffbc50a_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t22) + Math.fma(sd[1], _t19, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t22) + Math.fma(sd[3], _t20, -(sd[0] * _t19)), Math.fma(sd[0], _t20, sd[3] * _t19) + Math.fma(sd[2], _t21, -(sd[1] * _t22)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3ffbc50a_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t22)) - Math.fma(sd[1], _t20, sd[2] * _t19);
        double _buf3 = Math.fma(sd[4], _t21, sd[7] * _t22) + Math.fma(sd[5], _t19, -(sd[6] * _t20));
        double _buf4 = Math.fma(sd[5], _t21, sd[6] * _t22) + Math.fma(sd[7], _t20, -(sd[4] * _t19));
        double _buf5 = Math.fma(sd[4], _t20, sd[7] * _t19) + Math.fma(sd[6], _t21, -(sd[5] * _t22));
        dd[7] = Math.fma(sd[7], _t21, -(sd[4] * _t22)) - Math.fma(sd[5], _t20, sd[6] * _t19);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotateZYX(double angleZ, double angleY, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZYX_s779bee86_1(dest, sd, ((DoubleDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s779bee86_1(DoubleDualQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        double _buf3 = Math.fma(sd[4], _t19, sd[7] * _t21) + Math.fma(sd[5], _t22, -(sd[6] * _t20));
        double _buf4 = Math.fma(sd[5], _t19, sd[6] * _t21) + Math.fma(sd[7], _t20, -(sd[4] * _t22));
        double _buf5 = Math.fma(sd[4], _t20, sd[7] * _t22) + Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = Math.fma(sd[7], _t19, -(sd[4] * _t21)) - Math.fma(sd[5], _t20, sd[6] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }


    /**
     * Apply a translation by {@code translation} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat translate(Double3R translation, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return translate_narrow(translation, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        double _t1 = -sd[0];
        double _t2 = -sd[1];
        double _t3 = 0.5 * translation.z();
        double _t4 = 0.5 * translation.y();
        double _t5 = 0.5 * translation.x();
        dd[4] = Math.fma(sd[1], _t3, Math.fma(_t0, _t4, Math.fma(sd[3], _t5, sd[4])));
        dd[5] = Math.fma(sd[3], _t4, Math.fma(_t1, _t3, Math.fma(sd[2], _t5, sd[5])));
        dd[6] = Math.fma(sd[0], _t4, Math.fma(sd[3], _t3, Math.fma(_t2, _t5, sd[6])));
        dd[7] = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, sd[7])));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat translate(double translationX, double translationY, double translationZ, @Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return translate_narrow(translationX, translationY, translationZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        double _t1 = -sd[0];
        double _t2 = -sd[1];
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        dd[4] = Math.fma(sd[1], _t3, Math.fma(_t0, _t4, Math.fma(sd[3], _t5, sd[4])));
        dd[5] = Math.fma(sd[3], _t4, Math.fma(_t1, _t3, Math.fma(sd[2], _t5, sd[5])));
        dd[6] = Math.fma(sd[0], _t4, Math.fma(sd[3], _t3, Math.fma(_t2, _t5, sd[6])));
        dd[7] = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, sd[7])));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }


    /**
     * Transform {@code p} by this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Double3R p, @Mutated Double3 dest) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(pY, sd[0], -(pX * sd[1]));
        double _t10 = 2.0 * Math.fma(pX, sd[2], -(pZ * sd[0]));
        double _t11 = 2.0 * Math.fma(pZ, sd[1], -(pY * sd[2]));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, Math.fma(2.0, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX))));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, Math.fma(2.0, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY))));
        return transform_s70692057_1(pZ, dest, sd, dd, _t9, _t10, _t11);
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(pY, sd[0], -(pX * sd[1]));
        double _t10 = 2.0 * Math.fma(pX, sd[2], -(pZ * sd[0]));
        double _t11 = 2.0 * Math.fma(pZ, sd[1], -(pY * sd[2]));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, Math.fma(2.0, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX))));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, Math.fma(2.0, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY))));
        return transform_s70692057_1(pZ, dest, sd, dd, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private Double3 transform_s70692057_1(double pZ, Double3 dest, double[] sd, double[] dd, double _t9, double _t10, double _t11) {
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, Math.fma(2.0, Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ))));
        return dest;
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[0], vY, -(sd[1] * vX));
        double _t10 = 2.0 * Math.fma(sd[2], vX, -(sd[0] * vZ));
        double _t11 = 2.0 * Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[0], vY, -(sd[1] * vX));
        double _t10 = 2.0 * Math.fma(sd[2], vX, -(sd[0] * vZ));
        double _t11 = 2.0 * Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[0], vZ, -(sd[2] * vX));
        double _t10 = 2.0 * Math.fma(sd[1], vX, -(sd[0] * vY));
        double _t11 = 2.0 * Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = Math.fma(sd[2], _t9, Math.fma(-sd[1], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[0], _t10, Math.fma(-sd[2], _t11, Math.fma(sd[3], _t9, vY)));
        dd[2] = Math.fma(sd[1], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[0], vZ, -(sd[2] * vX));
        double _t10 = 2.0 * Math.fma(sd[1], vX, -(sd[0] * vY));
        double _t11 = 2.0 * Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = Math.fma(sd[2], _t9, Math.fma(-sd[1], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[0], _t10, Math.fma(-sd[2], _t11, Math.fma(sd[3], _t9, vY)));
        dd[2] = Math.fma(sd[1], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vZ)));
        return dest;
    }


    /**
     * Transform {@code p} by the inverse of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Double3R p, @Mutated Double3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t21 = Math.fma(-2.0, Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ);
        double _t22 = Math.fma(-2.0, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX);
        double _t23 = Math.fma(-2.0, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY);
        double _t33 = 2.0 * Math.fma(sd[0], _t21, -(sd[2] * _t22));
        double _t34 = 2.0 * Math.fma(sd[1], _t22, -(sd[0] * _t23));
        double _t35 = 2.0 * Math.fma(sd[2], _t23, -(sd[1] * _t21));
        dd[0] = Math.fma(sd[2], _t33, Math.fma(-sd[1], _t34, Math.fma(sd[3], _t35, _t22)));
        return transformInverse_scbf64a19_1(dest, sd, dd, _t21, _t23, _t33, _t34, _t35);
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_scbf64a19_1(Double3 dest, double[] sd, double[] dd, double _t21, double _t23, double _t33, double _t34, double _t35) {
        dd[1] = Math.fma(sd[0], _t34, Math.fma(-sd[2], _t35, Math.fma(sd[3], _t33, _t23)));
        dd[2] = Math.fma(sd[1], _t35, Math.fma(-sd[0], _t33, Math.fma(sd[3], _t34, _t21)));
        return dest;
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Double3R p, @Mutated Double3 dest) {
        return transform(p, dest);
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(pY, sd[0], -(pX * sd[1]));
        double _t10 = 2.0 * Math.fma(pX, sd[2], -(pZ * sd[0]));
        double _t11 = 2.0 * Math.fma(pZ, sd[1], -(pY * sd[2]));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, Math.fma(2.0, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX))));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, Math.fma(2.0, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY))));
        return transform_s70692057_1(pZ, dest, sd, dd, _t9, _t10, _t11);
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Double3R p, @Mutated Double3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t21 = Math.fma(-2.0, Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ);
        double _t22 = Math.fma(-2.0, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX);
        double _t23 = Math.fma(-2.0, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY);
        double _t33 = 2.0 * Math.fma(sd[0], _t21, -(sd[2] * _t22));
        double _t34 = 2.0 * Math.fma(sd[1], _t22, -(sd[0] * _t23));
        double _t35 = 2.0 * Math.fma(sd[2], _t23, -(sd[1] * _t21));
        dd[0] = Math.fma(sd[2], _t33, Math.fma(-sd[1], _t34, Math.fma(sd[3], _t35, _t22)));
        return transformInverse_scbf64a19_1(dest, sd, dd, _t21, _t23, _t33, _t34, _t35);
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(Double3R v, @Mutated Double3 dest) {
        return transformDirection(v, dest);
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[0], vY, -(sd[1] * vX));
        double _t10 = 2.0 * Math.fma(sd[2], vX, -(sd[0] * vZ));
        double _t11 = 2.0 * Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    public double rX() { return data[0]; }
    public double rY() { return data[1]; }
    public double rZ() { return data[2]; }
    public double rW() { return data[3]; }
    public double dX() { return data[4]; }
    public double dY() { return data[5]; }
    public double dZ() { return data[6]; }
    public double dW() { return data[7]; }

    @Override public String toString() {
        return "DoubleDualQuat(" + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + dX() + ", " + dY() + ", " + dZ() + ", " + dW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleDualQuatImpl)) return false;
        DoubleDualQuatImpl o = (DoubleDualQuatImpl) obj;
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
            && Double.isFinite(data[7]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7]);
    }

    @Override public boolean equalsEpsilon(DoubleDualQuatR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.rX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.rY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.rZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.rW()) <= epsilon
            && java.lang.Math.abs(data[4] - other.dX()) <= epsilon
            && java.lang.Math.abs(data[5] - other.dY()) <= epsilon
            && java.lang.Math.abs(data[6] - other.dZ()) <= epsilon
            && java.lang.Math.abs(data[7] - other.dW()) <= epsilon;
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
        return dest;
    }
    public @Mutated DoubleDualQuat load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public DoubleDualQuat load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleDualQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public DoubleDualQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleDualQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleDualQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public DoubleDualQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleDualQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public DoubleDualQuat load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public DoubleDualQuat load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        return dest;
    }
    public @Mutated DoubleDualQuat load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public DoubleDualQuat load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleDualQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleDualQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public DoubleDualQuat loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleDualQuat loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public DoubleDualQuat storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleDualQuat loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public DoubleDualQuat loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public DoubleDualQuat loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final DoubleVector VEC_2 = DoubleVector.fromArray(COL_SPECIES, new double[]{0.0, 0.0, 0.0, 0.0}, 0);
    private static final DoubleVector VEC_3 = DoubleVector.fromArray(COL_SPECIES, new double[]{0.0, 0.0, 0.0, 1.0}, 0);
    private static final double[] DATA_0 = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
    private static final double[] DATA_1 = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent field:
     * multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal values scale
     * up without overflow and the largest doubles land in [2, 4). Shared by 2 identical private
     * paths of {@code unitScale}; reached only through it.
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private DoubleDualQuat makeRotationLookAlong_wide(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = Math.fma(-dirY, _sp0, upY);
        double _t29 = Math.fma(-dirZ, _sp0, upZ);
        double _t30 = Math.fma(-dirX, _sp0, upX);
        double _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        double _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s309f29f5_1(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, _t45, _t37 * _t45);
    }

    private DoubleRigid toRigid_narrow(DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = 2.0 * (Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[1] = 2.0 * (Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[2] = 2.0 * (Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        return dest;
    }

    private DoubleTransform toTransform_narrow(DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = 2.0 * (Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[1] = 2.0 * (Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[2] = 2.0 * (Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return dest;
    }

    private DoubleDualQuat makeIdentity_narrow() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeZero_narrow() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat set_narrow(DoubleQuatR rotation) {
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double[] dd = this.data;
        dd[0] = rotation.x();
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat set_narrow(double rotationX, double rotationY, double rotationZ, double rotationW) {
        double[] dd = this.data;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat set_narrow(Double3R translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.5 * translationX;
        dd[5] = 0.5 * translationY;
        dd[6] = 0.5 * translationZ;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat set_narrow(double translationX, double translationY, double translationZ) {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.5 * translationX;
        dd[5] = 0.5 * translationY;
        dd[6] = 0.5 * translationZ;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat dualConjugate_narrow(DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        return dest;
    }

    private DoubleQuat getDual_narrow(DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[4];
        dd[1] = sd[5];
        dd[2] = sd[6];
        dd[3] = sd[7];
        return dest;
    }

    private DoubleQuat getRotation_narrow(DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
    }

    private DoubleDualQuat setDual_narrow(DoubleQuatR dual, DoubleDualQuat dest) {
        double dualX = dual.x();
        double dualY = dual.y();
        double dualZ = dual.z();
        double dualW = dual.w();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }

    private DoubleDualQuat setDual_narrow(double dualX, double dualY, double dualZ, double dualW, DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }

    private DoubleDualQuat setReal_narrow(DoubleQuatR real, DoubleDualQuat dest) {
        double realY = real.y();
        double realZ = real.z();
        double realW = real.w();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = real.x();
        dd[1] = realY;
        dd[2] = realZ;
        dd[3] = realW;
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        return dest;
    }

    private DoubleDualQuat setReal_narrow(double realX, double realY, double realZ, double realW, DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = realX;
        dd[1] = realY;
        dd[2] = realZ;
        dd[3] = realW;
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        return dest;
    }

    private DoubleDualQuat setTranslation_narrow(Double3R translation, DoubleDualQuat dest) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[1];
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        dd[5] = 0.5 * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        dd[6] = 0.5 * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        dd[7] = 0.5 * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return dest;
    }

    private DoubleDualQuat setTranslation_narrow(double translationX, double translationY, double translationZ, DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[1];
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = 0.5 * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        dd[5] = 0.5 * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        dd[6] = 0.5 * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        dd[7] = 0.5 * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return dest;
    }

    private DoubleDualQuat makeRotationAxis_narrow(double angle, Double3R axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationAxis_narrow(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationX_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = _t1;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationXYZ_narrow(double angleX, double angleY, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationXZY_narrow(double angleX, double angleZ, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationY_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = _t1;
        dd[2] = 0.0;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationYXZ_narrow(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationYZX_narrow(double angleY, double angleZ, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t5, _t2);
        double _t7 = Math.cosFromSin(_t3, _t0);
        double _t8 = Math.cosFromSin(_t4, _t1);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t8;
        double _t11 = _t4 * _t7;
        double _t12 = _t7 * _t8;
        dd[0] = Math.fma(_t9, _t6, _t12 * _t5);
        dd[1] = Math.fma(_t10, _t6, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dd[3] = Math.fma(_t12, _t6, -(_t9 * _t5));
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationZ_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationZXY_narrow(double angleZ, double angleX, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationZYX_narrow(double angleZ, double angleY, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        dd[0] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[1] = Math.fma(_t10, _t8, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t8);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat translate_narrow(Double3R translation, DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        double _t1 = -sd[0];
        double _t2 = -sd[1];
        double _t3 = 0.5 * translation.z();
        double _t4 = 0.5 * translation.y();
        double _t5 = 0.5 * translation.x();
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = Math.fma(_rd1, _t3, Math.fma(_t0, _t4, Math.fma(_rd3, _t5, sd[4])));
        dd[5] = Math.fma(_rd3, _t4, Math.fma(_t1, _t3, Math.fma(_rd2, _t5, sd[5])));
        dd[6] = Math.fma(_rd0, _t4, Math.fma(_rd3, _t3, Math.fma(_t2, _t5, sd[6])));
        dd[7] = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, sd[7])));
        return dest;
    }

    private DoubleDualQuat translate_narrow(double translationX, double translationY, double translationZ, DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        double _t1 = -sd[0];
        double _t2 = -sd[1];
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = Math.fma(_rd1, _t3, Math.fma(_t0, _t4, Math.fma(_rd3, _t5, sd[4])));
        dd[5] = Math.fma(_rd3, _t4, Math.fma(_t1, _t3, Math.fma(_rd2, _t5, sd[5])));
        dd[6] = Math.fma(_rd0, _t4, Math.fma(_rd3, _t3, Math.fma(_t2, _t5, sd[6])));
        dd[7] = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, sd[7])));
        return dest;
    }

    private DoubleDualQuat makeFromMatrix_narrow(Double3x3R m) {
        double[] mData = ((Double3x3Impl) m).data;
        double _t1 = 1.0 - mData[0];
        double _t13 = mData[8] + (mData[0] + mData[4]);
        double _t14 = 1.0 + _t13;
        double _t15 = mData[0] + (1.0 - mData[4] - mData[8]);
        double _t16 = mData[4] + (_t1 - mData[8]);
        double _t17 = mData[8] + (_t1 - mData[4]);
        return makeFromMatrix_s81574d1e_1_narrow(this.data, mData, mData[5] - mData[7], mData[3] + mData[1], mData[6] + mData[2], mData[6] - mData[2], mData[7] + mData[5], mData[1] - mData[3], _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    private DoubleDualQuat makeRotationLookAlong_narrow(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Math.useFma()) return makeRotationLookAlong_fma_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd_narrow(dirX, dirY, dirZ, upX, upY, upZ);
    }

    private DoubleDualQuat makeRotationLookAlong_narrow(Double3R dir, Double3R up) {
        double dirX = dir.x();
        double dirY = dir.y();
        double dirZ = dir.z();
        double upX = up.x();
        double upY = up.y();
        double upZ = up.z();
        if (Math.useFma()) return makeRotationLookAlong_fma_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd_narrow(dirX, dirY, dirZ, upX, upY, upZ);
    }

    private DoubleDualQuat makeFromMatrix_s81574d1e_1_narrow(double[] dd, double[] mData, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            dd[0] = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (mData[0] > java.lang.Math.max(mData[4], mData[8])) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
            } else {
                if (mData[4] > mData[8]) {
                    dd[0] = _sp1 * _t5;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                } else {
                    dd[0] = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationLookAlong_fma_narrow(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        double _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s309f29f5_1_fma_narrow(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, _t45, _t37 * _t45);
    }

    private DoubleDualQuat makeRotationLookAlong_mulAdd_narrow(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = ((-dirY) * (_sp0) + (upY));
        double _t29 = ((-dirZ) * (_sp0) + (upZ));
        double _t30 = ((-dirX) * (_sp0) + (upX));
        double _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        double _t38 = ((dirY) * (_t30) - (dirX * _t28));
        double _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        double _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd_narrow(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s309f29f5_1_mulAdd_narrow(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, _t45, _t37 * _t45);
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_fma_narrow(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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
        double _t29 = -_t26;
        double _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0;
            _t41 = -_t24;
        }
        double _t43 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t44 = java.lang.Math.fma(_t43, _t25, _t22);
        double _t45 = java.lang.Math.fma(_t43, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t43, _t24, _t21);
        return makeRotationLookAlong_degenerate_se3802ea_1_fma_narrow(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_fma_narrow(double dirX, double dirY, double dirZ, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45, double _t46) {
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = java.lang.Math.fma(-_t37, _t45, 1.0);
        double _t64 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0))));
        double _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        double _t82 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s309f29f5_2_fma_narrow(dirZ, dd, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t64), java.lang.Math.fma(-dirY, _t16, _t64), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t82)));
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_mulAdd_narrow(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        double _t29 = -_t26;
        double _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0;
            _t41 = -_t24;
        }
        double _t43 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t44 = ((_t43) * (_t25) + (_t22));
        double _t45 = ((_t43) * (_t26) + (_t23));
        double _t46 = ((_t43) * (_t24) + (_t21));
        return makeRotationLookAlong_degenerate_se3802ea_1_mulAdd_narrow(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_mulAdd_narrow(double dirX, double dirY, double dirZ, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45, double _t46) {
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = ((-_t37) * (_t45) + (1.0));
        double _t64 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        double _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0))))))));
        double _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0))))))));
        double _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        double _t82 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return makeRotationLookAlong_s309f29f5_2_mulAdd_narrow(dirZ, dd, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t64)), ((-dirY) * (_t16) + (_t64)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t82)));
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_fma_narrow(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
        double _t57 = java.lang.Math.fma(_t45, _t24, -(_t46 * _t26));
        double _t61 = java.lang.Math.fma(_t55, _t55, java.lang.Math.fma(_t56, _t56, _t57 * _t57));
        double _t62, _t63, _t64, _t66;
        if (_t61 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0 / java.lang.Math.sqrt(_t61));
        }
        double _t67 = -_t66;
        double _t68 = _t66 * _t62;
        double _t69 = _t66 * _t63;
        double _t70 = -_t68;
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return makeRotationLookAlong_degenerate_se3802ea_2_fma_narrow(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2_fma_narrow(double dirZ, double[] dd, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t74, double _t75, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4, double _sp2) {
        if (java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)) > 0.0) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_mulAdd_narrow(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
        double _t57 = ((_t45) * (_t24) - (_t46 * _t26));
        double _t61 = ((_t55) * (_t55) + (((_t56) * (_t56) + (_t57 * _t57))));
        double _t62, _t63, _t64, _t66;
        if (_t61 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0 / java.lang.Math.sqrt(((_t36) * (_t36) + (((_t37) * (_t37) + (_t41 * _t41))))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0 / java.lang.Math.sqrt(_t61));
        }
        double _t67 = -_t66;
        double _t68 = _t66 * _t62;
        double _t69 = _t66 * _t63;
        double _t70 = -_t68;
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return makeRotationLookAlong_degenerate_se3802ea_2_mulAdd_narrow(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2_mulAdd_narrow(double dirZ, double[] dd, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t74, double _t75, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4, double _sp2) {
        if (((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))) > 0.0) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_fma_narrow(double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_degenerate_se3802ea_3_fma_narrow(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_mulAdd_narrow(double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return makeRotationLookAlong_degenerate_se3802ea_3_mulAdd_narrow(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3_fma_narrow(double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
        if (java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }

    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3_mulAdd_narrow(double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
        if (((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return this;
    }
}
