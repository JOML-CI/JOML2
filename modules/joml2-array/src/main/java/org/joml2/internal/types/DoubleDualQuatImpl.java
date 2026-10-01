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
 * Generated implementation of {@link DoubleDualQuat} backed by a {@code double[]} array.
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
        return makeFromRigid(r.tX(), r.tY(), r.tZ(), r.rX(), r.rY(), r.rZ(), r.rW());
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
        if (Math.useFma()) {
            double[] dd = this.data;
            double _t0 = -rTZ;
            dd[0] = rRX;
            dd[1] = rRY;
            dd[2] = rRZ;
            dd[3] = rRW;
            dd[4] = 0.5 * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ));
            dd[5] = 0.5 * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -(rTX * rRZ)));
            dd[6] = 0.5 * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -(rTY * rRX)));
            dd[7] = 0.5 * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -(rTX * rRX)));
            return this;
        } else {
            double[] dd = this.data;
            double _t0 = -rTZ;
            dd[0] = rRX;
            dd[1] = rRY;
            dd[2] = rRZ;
            dd[3] = rRW;
            dd[4] = 0.5 * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ))));
            dd[5] = 0.5 * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ))));
            dd[6] = 0.5 * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX))));
            dd[7] = 0.5 * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX))));
            return this;
        }
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
        return makeFromTransform(t.tX(), t.tY(), t.tZ(), t.rX(), t.rY(), t.rZ(), t.rW(), t.sX(), t.sY(), t.sZ());
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
        if (Math.useFma()) {
            double[] dd = this.data;
            double _t0 = -tTZ;
            dd[0] = tRX;
            dd[1] = tRY;
            dd[2] = tRZ;
            dd[3] = tRW;
            dd[4] = 0.5 * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ));
            dd[5] = 0.5 * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -(tTX * tRZ)));
            dd[6] = 0.5 * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -(tTY * tRX)));
            dd[7] = 0.5 * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -(tTX * tRX)));
            return this;
        } else {
            double[] dd = this.data;
            double _t0 = -tTZ;
            dd[0] = tRX;
            dd[1] = tRY;
            dd[2] = tRZ;
            dd[3] = tRW;
            dd[4] = 0.5 * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ))));
            dd[5] = 0.5 * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ))));
            dd[6] = 0.5 * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX))));
            dd[7] = 0.5 * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX))));
            return this;
        }
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
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.abs(java.lang.Math.fma(sd[0], sd[0], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[3], sd[3], -1.0))))) <= epsilon;
        } else {
            double[] sd = this.data;
            return java.lang.Math.abs(((sd[0]) * (sd[0]) + (((sd[1]) * (sd[1]) + (((sd[2]) * (sd[2]) + (((sd[3]) * (sd[3]) - (1.0))))))))) <= epsilon;
        }
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
        return makeTranslationRotation(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w());
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
        if (Math.useFma()) {
            double[] dd = this.data;
            double _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            dd[6] = 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            dd[7] = 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            double[] dd = this.data;
            double _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            dd[5] = 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            dd[6] = 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            dd[7] = 0.5 * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
            return this;
        }
    }


    /**
     * Set all components of this dual quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleDualQuat makeZero() {
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


    /**
     * Set this dual quaternion to a pure rotation by {@code rotation} (zero translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleDualQuat set(DoubleQuatR rotation) {
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
        return set(rotation.x(), rotation.y(), rotation.z(), rotation.w(), translation.x(), translation.y(), translation.z());
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
        if (Math.useFma()) {
            double[] dd = this.data;
            double _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            dd[6] = 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            dd[7] = 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            double[] dd = this.data;
            double _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            dd[5] = 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            dd[6] = 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            dd[7] = 0.5 * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
            return this;
        }
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
        if (Math.useFma()) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code dlb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t9 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0 ? -1.0 : 1.0;
        double _t14 = java.lang.Math.fma(t, java.lang.Math.fma(otherRX, _t9, -sd[0]), sd[0]);
        double _t15 = java.lang.Math.fma(t, java.lang.Math.fma(otherRY, _t9, -sd[1]), sd[1]);
        double _t16 = java.lang.Math.fma(t, java.lang.Math.fma(otherRZ, _t9, -sd[2]), sd[2]);
        double _t17 = java.lang.Math.fma(t, java.lang.Math.fma(otherRW, _t9, -sd[3]), sd[3]);
        double _t23 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, _t15 * _t15) + java.lang.Math.fma(_t16, _t16, _t17 * _t17)));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -sd[4]), sd[4]) * _t23;
        dd[5] = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -sd[5]), sd[5]) * _t23;
        return dlb_s39e58938_1_fma(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t9 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0 ? -1.0 : 1.0;
        double _t14 = ((t) * (((otherRX) * (_t9) - (sd[0]))) + (sd[0]));
        double _t15 = ((t) * (((otherRY) * (_t9) - (sd[1]))) + (sd[1]));
        double _t16 = ((t) * (((otherRZ) * (_t9) - (sd[2]))) + (sd[2]));
        double _t17 = ((t) * (((otherRW) * (_t9) - (sd[3]))) + (sd[3]));
        double _t23 = (1.0 / java.lang.Math.sqrt(((_t14) * (_t14) + (_t15 * _t15)) + ((_t16) * (_t16) + (_t17 * _t17))));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = ((t) * (((otherDX) * (_t9) - (sd[4]))) + (sd[4])) * _t23;
        dd[5] = ((t) * (((otherDY) * (_t9) - (sd[5]))) + (sd[5])) * _t23;
        return dlb_s39e58938_1_mulAdd(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat dlb_s39e58938_1_fma(double otherDZ, double otherDW, double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t9, double _t23) {
        dd[6] = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -sd[6]), sd[6]) * _t23;
        dd[7] = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -sd[7]), sd[7]) * _t23;
        return dest;
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat dlb_s39e58938_1_mulAdd(double otherDZ, double otherDW, double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t9, double _t23) {
        dd[6] = ((t) * (((otherDZ) * (_t9) - (sd[6]))) + (sd[6])) * _t23;
        dd[7] = ((t) * (((otherDW) * (_t9) - (sd[7]))) + (sd[7])) * _t23;
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
        if (Math.useFma()) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code sclerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = -sd[2];
        double _t8 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = java.lang.Math.fma(sd[0], _t9, sd[3] * _t10);
        double _t77 = java.lang.Math.fma(_t0, _t11, -(sd[1] * _t12));
        return sclerp_sea9d2379_1_fma(t, dest, sd, ((DoubleDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(sd[1], _t9, -(sd[2] * _t10)) + java.lang.Math.fma(sd[3], _t11, -(sd[0] * _t12)), java.lang.Math.fma(sd[0], _t11, sd[3] * _t12) + java.lang.Math.fma(_t0, _t9, -(sd[1] * _t10)), java.lang.Math.fma(sd[2], _t12, -(sd[1] * _t11)) + java.lang.Math.fma(sd[3], _t9, -(sd[0] * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double _t0 = -sd[2];
        double _t8 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = ((sd[0]) * (_t9) + (sd[3] * _t10));
        double _t77 = ((_t0) * (_t11) - (sd[1] * _t12));
        return sclerp_sea9d2379_1_mulAdd(t, dest, sd, ((DoubleDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((sd[1]) * (_t9) - (sd[2] * _t10)) + ((sd[3]) * (_t11) - (sd[0] * _t12)), ((sd[0]) * (_t11) + (sd[3] * _t12)) + ((_t0) * (_t9) - (sd[1] * _t10)), ((sd[2]) * (_t12) - (sd[1] * _t11)) + ((sd[3]) * (_t9) - (sd[0] * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_1_fma(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_sea9d2379_2_fma(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(sd[0], _t13, sd[3] * _t14) + java.lang.Math.fma(sd[1], _t15, sd[2] * _t16) + (java.lang.Math.fma(sd[4], _t9, sd[7] * _t10) + java.lang.Math.fma(sd[5], _t12, sd[6] * _t11)), java.lang.Math.fma(sd[2], _t15, -(sd[1] * _t16)) + java.lang.Math.fma(sd[3], _t13, -(sd[0] * _t14)) + (java.lang.Math.fma(sd[6], _t12, -(sd[5] * _t11)) + java.lang.Math.fma(sd[7], _t9, -(sd[4] * _t10))), java.lang.Math.fma(sd[1], _t13, -(sd[2] * _t14)) + java.lang.Math.fma(sd[3], _t16, -(sd[0] * _t15)) + (java.lang.Math.fma(sd[5], _t9, -(sd[6] * _t10)) + java.lang.Math.fma(sd[7], _t11, -(sd[4] * _t12))), java.lang.Math.fma(sd[0], _t16, sd[3] * _t15) + java.lang.Math.fma(_t0, _t13, -(sd[1] * _t14)) + (java.lang.Math.fma(sd[4], _t11, sd[7] * _t12) + java.lang.Math.fma(_t2, _t9, -(sd[5] * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_1_mulAdd(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_sea9d2379_2_mulAdd(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((sd[0]) * (_t13) + (sd[3] * _t14)) + ((sd[1]) * (_t15) + (sd[2] * _t16)) + (((sd[4]) * (_t9) + (sd[7] * _t10)) + ((sd[5]) * (_t12) + (sd[6] * _t11))), ((sd[2]) * (_t15) - (sd[1] * _t16)) + ((sd[3]) * (_t13) - (sd[0] * _t14)) + (((sd[6]) * (_t12) - (sd[5] * _t11)) + ((sd[7]) * (_t9) - (sd[4] * _t10))), ((sd[1]) * (_t13) - (sd[2] * _t14)) + ((sd[3]) * (_t16) - (sd[0] * _t15)) + (((sd[5]) * (_t9) - (sd[6] * _t10)) + ((sd[7]) * (_t11) - (sd[4] * _t12))), ((sd[0]) * (_t16) + (sd[3] * _t15)) + ((_t0) * (_t13) - (sd[1] * _t14)) + (((sd[4]) * (_t11) + (sd[7] * _t12)) + ((_t2) * (_t9) - (sd[5] * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_2_fma(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
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
        double _t122 = java.lang.Math.fma(_t106, _t106, java.lang.Math.fma(_t107, _t107, _t108 * _t108));
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
        return sclerp_sea9d2379_3_fma(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_2_mulAdd(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
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
        double _t122 = ((_t106) * (_t106) + (((_t107) * (_t107) + (_t108 * _t108))));
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
        return sclerp_sea9d2379_3_mulAdd(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_3_fma(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t114, double _t115, double _t116, double _t122, double _t124, double _t126, double _t127, double _t128, double _t130, double _t133, double _t142, double _t143, double _t144, double _t145, double _t147, double _t146) {
        double _t160, _t161, _t162;
        if (_t122 < 1.0E-28) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t126, _t114) * _t124, _t130, -(_t146 * _t126));
            _t161 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t127, _t115) * _t124, _t130, -(_t146 * _t127));
            _t162 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t128, _t116) * _t124, _t130, -(_t146 * _t128));
        }
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t142, _rd3 * _t143) + java.lang.Math.fma(_rd1, _t144, -(_rd2 * _t145));
        dd[1] = java.lang.Math.fma(_rd1, _t142, _rd2 * _t143) + java.lang.Math.fma(_rd3, _t145, -(_rd0 * _t144));
        dd[2] = java.lang.Math.fma(_rd0, _t145, _rd3 * _t144) + java.lang.Math.fma(_rd2, _t142, -(_rd1 * _t143));
        return sclerp_sea9d2379_4_fma(dest, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_3_mulAdd(double t, DoubleDualQuat dest, double[] sd, double[] dd, double _t0, double _t2, double _t114, double _t115, double _t116, double _t122, double _t124, double _t126, double _t127, double _t128, double _t130, double _t133, double _t142, double _t143, double _t144, double _t145, double _t147, double _t146) {
        double _t160, _t161, _t162;
        if (_t122 < 1.0E-28) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = ((((_t133) * (_t126) + (_t114)) * _t124) * (_t130) - (_t146 * _t126));
            _t161 = ((((_t133) * (_t127) + (_t115)) * _t124) * (_t130) - (_t146 * _t127));
            _t162 = ((((_t133) * (_t128) + (_t116)) * _t124) * (_t130) - (_t146 * _t128));
        }
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t142) + (_rd3 * _t143)) + ((_rd1) * (_t144) - (_rd2 * _t145));
        dd[1] = ((_rd1) * (_t142) + (_rd2 * _t143)) + ((_rd3) * (_t145) - (_rd0 * _t144));
        dd[2] = ((_rd0) * (_t145) + (_rd3 * _t144)) + ((_rd2) * (_t142) - (_rd1 * _t143));
        return sclerp_sea9d2379_4_mulAdd(dest, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_4_fma(DoubleDualQuat dest, double[] dd, double _t0, double _t2, double _t142, double _t143, double _t144, double _t145, double _t147, double _t160, double _t161, double _t162, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[3] = java.lang.Math.fma(_rd3, _t142, -(_rd0 * _t143)) - java.lang.Math.fma(_rd1, _t145, _rd2 * _t144);
        dd[4] = java.lang.Math.fma(_rd0, _t147, _rd3 * _t160) + java.lang.Math.fma(_rd1, _t161, -(_rd2 * _t162)) + (java.lang.Math.fma(_rd4, _t142, _rd7 * _t143) + java.lang.Math.fma(_rd5, _t144, -(_rd6 * _t145)));
        dd[5] = java.lang.Math.fma(_rd1, _t147, _rd2 * _t160) + java.lang.Math.fma(_rd3, _t162, -(_rd0 * _t161)) + (java.lang.Math.fma(_rd5, _t142, _rd6 * _t143) + java.lang.Math.fma(_rd7, _t145, -(_rd4 * _t144)));
        dd[6] = java.lang.Math.fma(_rd0, _t162, _rd3 * _t161) + java.lang.Math.fma(_rd2, _t147, -(_rd1 * _t160)) + (java.lang.Math.fma(_rd4, _t145, _rd7 * _t144) + java.lang.Math.fma(_rd6, _t142, -(_rd5 * _t143)));
        dd[7] = java.lang.Math.fma(_rd3, _t147, -(_rd0 * _t160)) + java.lang.Math.fma(_t0, _t161, -(_rd1 * _t162)) + (java.lang.Math.fma(_rd7, _t142, -(_rd4 * _t143)) + java.lang.Math.fma(_t2, _t144, -(_rd5 * _t145)));
        return dest;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_4_mulAdd(DoubleDualQuat dest, double[] dd, double _t0, double _t2, double _t142, double _t143, double _t144, double _t145, double _t147, double _t160, double _t161, double _t162, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[3] = ((_rd3) * (_t142) - (_rd0 * _t143)) - ((_rd1) * (_t145) + (_rd2 * _t144));
        dd[4] = ((_rd0) * (_t147) + (_rd3 * _t160)) + ((_rd1) * (_t161) - (_rd2 * _t162)) + (((_rd4) * (_t142) + (_rd7 * _t143)) + ((_rd5) * (_t144) - (_rd6 * _t145)));
        dd[5] = ((_rd1) * (_t147) + (_rd2 * _t160)) + ((_rd3) * (_t162) - (_rd0 * _t161)) + (((_rd5) * (_t142) + (_rd6 * _t143)) + ((_rd7) * (_t145) - (_rd4 * _t144)));
        dd[6] = ((_rd0) * (_t162) + (_rd3 * _t161)) + ((_rd2) * (_t147) - (_rd1 * _t160)) + (((_rd4) * (_t145) + (_rd7 * _t144)) + ((_rd6) * (_t142) - (_rd5 * _t143)));
        dd[7] = ((_rd3) * (_t147) - (_rd0 * _t160)) + ((_t0) * (_t161) - (_rd1 * _t162)) + (((_rd7) * (_t142) - (_rd4 * _t143)) + ((_t2) * (_t144) - (_rd5 * _t145)));
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) return mul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
        if (Math.useFma()) return mul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat mul_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRZ, _rd1, -(otherRY * _rd2));
        dd[1] = java.lang.Math.fma(otherRX, _rd2, otherRW * _rd1) + java.lang.Math.fma(otherRY, _rd3, -(otherRZ * _rd0));
        dd[2] = java.lang.Math.fma(otherRY, _rd0, otherRZ * _rd3) + java.lang.Math.fma(otherRW, _rd2, -(otherRX * _rd1));
        dd[3] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[4] = java.lang.Math.fma(otherRX, _rd7, otherRW * _rd4) + java.lang.Math.fma(otherRZ, _rd5, -(otherRY * _rd6)) + (java.lang.Math.fma(otherDX, _rd3, otherDW * _rd0) + java.lang.Math.fma(otherDZ, _rd1, -(otherDY * _rd2)));
        return mul_sc0028309_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat mul_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRZ) * (_rd1) - (otherRY * _rd2));
        dd[1] = ((otherRX) * (_rd2) + (otherRW * _rd1)) + ((otherRY) * (_rd3) - (otherRZ * _rd0));
        dd[2] = ((otherRY) * (_rd0) + (otherRZ * _rd3)) + ((otherRW) * (_rd2) - (otherRX * _rd1));
        dd[3] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[4] = ((otherRX) * (_rd7) + (otherRW * _rd4)) + ((otherRZ) * (_rd5) - (otherRY * _rd6)) + (((otherDX) * (_rd3) + (otherDW * _rd0)) + ((otherDZ) * (_rd1) - (otherDY * _rd2)));
        return mul_sc0028309_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sc0028309_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = java.lang.Math.fma(otherRX, _rd6, otherRW * _rd5) + java.lang.Math.fma(otherRY, _rd7, -(otherRZ * _rd4)) + (java.lang.Math.fma(otherDX, _rd2, otherDW * _rd1) + java.lang.Math.fma(otherDY, _rd3, -(otherDZ * _rd0)));
        dd[6] = java.lang.Math.fma(otherRY, _rd4, otherRZ * _rd7) + java.lang.Math.fma(otherRW, _rd6, -(otherRX * _rd5)) + (java.lang.Math.fma(otherDY, _rd0, otherDZ * _rd3) + java.lang.Math.fma(otherDW, _rd2, -(otherDX * _rd1)));
        dd[7] = java.lang.Math.fma(otherRW, _rd7, -(otherRX * _rd4)) + java.lang.Math.fma(-otherRZ, _rd6, -(otherRY * _rd5)) + (java.lang.Math.fma(otherDW, _rd3, -(otherDX * _rd0)) + java.lang.Math.fma(-otherDZ, _rd2, -(otherDY * _rd1)));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sc0028309_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = ((otherRX) * (_rd6) + (otherRW * _rd5)) + ((otherRY) * (_rd7) - (otherRZ * _rd4)) + (((otherDX) * (_rd2) + (otherDW * _rd1)) + ((otherDY) * (_rd3) - (otherDZ * _rd0)));
        dd[6] = ((otherRY) * (_rd4) + (otherRZ * _rd7)) + ((otherRW) * (_rd6) - (otherRX * _rd5)) + (((otherDY) * (_rd0) + (otherDZ * _rd3)) + ((otherDW) * (_rd2) - (otherDX * _rd1)));
        dd[7] = ((otherRW) * (_rd7) - (otherRX * _rd4)) + ((-otherRZ) * (_rd6) - (otherRY * _rd5)) + (((otherDW) * (_rd3) - (otherDX * _rd0)) + ((-otherDZ) * (_rd2) - (otherDY * _rd1)));
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) return preMul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
        if (Math.useFma()) return preMul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preMul_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        dd[1] = java.lang.Math.fma(otherRY, _rd3, otherRZ * _rd0) + java.lang.Math.fma(otherRW, _rd1, -(otherRX * _rd2));
        dd[2] = java.lang.Math.fma(otherRX, _rd1, otherRW * _rd2) + java.lang.Math.fma(otherRZ, _rd3, -(otherRY * _rd0));
        dd[3] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[4] = java.lang.Math.fma(otherRX, _rd7, otherRW * _rd4) + java.lang.Math.fma(otherRY, _rd6, -(otherRZ * _rd5)) + (java.lang.Math.fma(otherDX, _rd3, otherDW * _rd0) + java.lang.Math.fma(otherDY, _rd2, -(otherDZ * _rd1)));
        return preMul_s8abe26fa_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preMul_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        dd[1] = ((otherRY) * (_rd3) + (otherRZ * _rd0)) + ((otherRW) * (_rd1) - (otherRX * _rd2));
        dd[2] = ((otherRX) * (_rd1) + (otherRW * _rd2)) + ((otherRZ) * (_rd3) - (otherRY * _rd0));
        dd[3] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[4] = ((otherRX) * (_rd7) + (otherRW * _rd4)) + ((otherRY) * (_rd6) - (otherRZ * _rd5)) + (((otherDX) * (_rd3) + (otherDW * _rd0)) + ((otherDY) * (_rd2) - (otherDZ * _rd1)));
        return preMul_s8abe26fa_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8abe26fa_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = java.lang.Math.fma(otherRY, _rd7, otherRZ * _rd4) + java.lang.Math.fma(otherRW, _rd5, -(otherRX * _rd6)) + (java.lang.Math.fma(otherDY, _rd3, otherDZ * _rd0) + java.lang.Math.fma(otherDW, _rd1, -(otherDX * _rd2)));
        dd[6] = java.lang.Math.fma(otherRX, _rd5, otherRW * _rd6) + java.lang.Math.fma(otherRZ, _rd7, -(otherRY * _rd4)) + (java.lang.Math.fma(otherDX, _rd1, otherDW * _rd2) + java.lang.Math.fma(otherDZ, _rd3, -(otherDY * _rd0)));
        dd[7] = java.lang.Math.fma(otherRW, _rd7, -(otherRX * _rd4)) + java.lang.Math.fma(-otherRZ, _rd6, -(otherRY * _rd5)) + (java.lang.Math.fma(otherDW, _rd3, -(otherDX * _rd0)) + java.lang.Math.fma(-otherDZ, _rd2, -(otherDY * _rd1)));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8abe26fa_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = ((otherRY) * (_rd7) + (otherRZ * _rd4)) + ((otherRW) * (_rd5) - (otherRX * _rd6)) + (((otherDY) * (_rd3) + (otherDZ * _rd0)) + ((otherDW) * (_rd1) - (otherDX * _rd2)));
        dd[6] = ((otherRX) * (_rd5) + (otherRW * _rd6)) + ((otherRZ) * (_rd7) - (otherRY * _rd4)) + (((otherDX) * (_rd1) + (otherDW * _rd2)) + ((otherDZ) * (_rd3) - (otherDY * _rd0)));
        dd[7] = ((otherRW) * (_rd7) - (otherRX * _rd4)) + ((-otherRZ) * (_rd6) - (otherRY * _rd5)) + (((otherDW) * (_rd3) - (otherDX * _rd0)) + ((-otherDZ) * (_rd2) - (otherDY * _rd1)));
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
        return addScaled(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), weight, dest);
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
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((DoubleDualQuatImpl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherRX, sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherRY, sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherRZ, sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherRW, sd[3]);
            dd[4] = java.lang.Math.fma(weight, otherDX, sd[4]);
            dd[5] = java.lang.Math.fma(weight, otherDY, sd[5]);
            dd[6] = java.lang.Math.fma(weight, otherDZ, sd[6]);
            dd[7] = java.lang.Math.fma(weight, otherDW, sd[7]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleDualQuatImpl) dest).data;
            dd[0] = ((weight) * (otherRX) + (sd[0]));
            dd[1] = ((weight) * (otherRY) + (sd[1]));
            dd[2] = ((weight) * (otherRZ) + (sd[2]));
            dd[3] = ((weight) * (otherRW) + (sd[3]));
            dd[4] = ((weight) * (otherDX) + (sd[4]));
            dd[5] = ((weight) * (otherDY) + (sd[5]));
            dd[6] = ((weight) * (otherDZ) + (sd[6]));
            dd[7] = ((weight) * (otherDW) + (sd[7]));
            return dest;
        }
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) return difference_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
        if (Math.useFma()) return difference_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat difference_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -otherRX;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, -(otherRW * _rd0)) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        dd[1] = java.lang.Math.fma(otherRY, _rd3, otherRZ * _rd0) + java.lang.Math.fma(_t0, _rd2, -(otherRW * _rd1));
        dd[2] = java.lang.Math.fma(otherRX, _rd1, -(otherRW * _rd2)) + java.lang.Math.fma(otherRZ, _rd3, -(otherRY * _rd0));
        dd[3] = java.lang.Math.fma(otherRX, _rd0, otherRW * _rd3) - java.lang.Math.fma(-otherRZ, _rd2, -(otherRY * _rd1));
        dd[4] = java.lang.Math.fma(otherRX, _rd7, -(otherRW * _rd4)) + java.lang.Math.fma(otherRY, _rd6, -(otherRZ * _rd5)) + (java.lang.Math.fma(otherDX, _rd3, -(otherDW * _rd0)) + java.lang.Math.fma(otherDY, _rd2, -(otherDZ * _rd1)));
        return difference_sb481444_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _t0, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat difference_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -otherRX;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) - (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        dd[1] = ((otherRY) * (_rd3) + (otherRZ * _rd0)) + ((_t0) * (_rd2) - (otherRW * _rd1));
        dd[2] = ((otherRX) * (_rd1) - (otherRW * _rd2)) + ((otherRZ) * (_rd3) - (otherRY * _rd0));
        dd[3] = ((otherRX) * (_rd0) + (otherRW * _rd3)) - ((-otherRZ) * (_rd2) - (otherRY * _rd1));
        dd[4] = ((otherRX) * (_rd7) - (otherRW * _rd4)) + ((otherRY) * (_rd6) - (otherRZ * _rd5)) + (((otherDX) * (_rd3) - (otherDW * _rd0)) + ((otherDY) * (_rd2) - (otherDZ * _rd1)));
        return difference_sb481444_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _t0, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_sb481444_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _t0, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = java.lang.Math.fma(otherRY, _rd7, otherRZ * _rd4) + java.lang.Math.fma(_t0, _rd6, -(otherRW * _rd5)) + (java.lang.Math.fma(otherDY, _rd3, otherDZ * _rd0) + java.lang.Math.fma(-otherDX, _rd2, -(otherDW * _rd1)));
        dd[6] = java.lang.Math.fma(otherRX, _rd5, -(otherRW * _rd6)) + java.lang.Math.fma(otherRZ, _rd7, -(otherRY * _rd4)) + (java.lang.Math.fma(otherDX, _rd1, -(otherDW * _rd2)) + java.lang.Math.fma(otherDZ, _rd3, -(otherDY * _rd0)));
        dd[7] = java.lang.Math.fma(otherRX, _rd4, otherRW * _rd7) + java.lang.Math.fma(otherRY, _rd5, otherRZ * _rd6) + (java.lang.Math.fma(otherDX, _rd0, otherDW * _rd3) + java.lang.Math.fma(otherDY, _rd1, otherDZ * _rd2));
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_sb481444_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, DoubleDualQuat dest, double[] dd, double _t0, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[5] = ((otherRY) * (_rd7) + (otherRZ * _rd4)) + ((_t0) * (_rd6) - (otherRW * _rd5)) + (((otherDY) * (_rd3) + (otherDZ * _rd0)) + ((-otherDX) * (_rd2) - (otherDW * _rd1)));
        dd[6] = ((otherRX) * (_rd5) - (otherRW * _rd6)) + ((otherRZ) * (_rd7) - (otherRY * _rd4)) + (((otherDX) * (_rd1) - (otherDW * _rd2)) + ((otherDZ) * (_rd3) - (otherDY * _rd0)));
        dd[7] = ((otherRX) * (_rd4) + (otherRW * _rd7)) + ((otherRY) * (_rd5) + (otherRZ * _rd6)) + (((otherDX) * (_rd0) + (otherDW * _rd3)) + ((otherDY) * (_rd1) + (otherDZ * _rd2)));
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
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherDX = other.dX();
        double otherDY = other.dY();
        double otherDZ = other.dZ();
        double otherDW = other.dW();
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) + (java.lang.Math.fma(otherDX, sd[4], otherDY * sd[5]) + java.lang.Math.fma(otherDZ, sd[6], otherDW * sd[7]));
        } else {
            double[] sd = this.data;
            return ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) + (((otherDX) * (sd[4]) + (otherDY * sd[5])) + ((otherDZ) * (sd[6]) + (otherDW * sd[7])));
        }
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
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) + (java.lang.Math.fma(otherDX, sd[4], otherDY * sd[5]) + java.lang.Math.fma(otherDZ, sd[6], otherDW * sd[7]));
        } else {
            double[] sd = this.data;
            return ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) + (((otherDX) * (sd[4]) + (otherDY * sd[5])) + ((otherDZ) * (sd[6]) + (otherDW * sd[7])));
        }
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
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[4];
        dd[1] = sd[5];
        dd[2] = sd[6];
        dd[3] = sd[7];
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
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = -(_rd0 * _t8_inv);
        dd[1] = -(_rd1 * _t8_inv);
        dd[2] = -(_rd2 * _t8_inv);
        dd[3] = _rd3 * _t8_inv;
        dd[4] = Math.fma(_rd0, _sp0, -(sd[4] * _t8_inv));
        dd[5] = Math.fma(_rd1, _sp0, -(sd[5] * _t8_inv));
        dd[6] = Math.fma(_rd2, _sp0, -(sd[6] * _t8_inv));
        dd[7] = Math.fma(sd[7], _t8_inv, -(_rd3 * _sp0));
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
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3])));
        }
    }


    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public double lengthSquared() {
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        } else {
            double[] sd = this.data;
            return ((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3]));
        }
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
        return (Math.useFma() ? log_sfb9ef421_1_fma(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2(java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11) : log_sfb9ef421_1_mulAdd(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2(java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11));
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat log_sfb9ef421_1_fma(DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t14, double _t15, double _t18, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
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
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25));
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25));
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25));
        }
        dd[3] = 0.0;
        dd[7] = 0.0;
        return dest;
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat log_sfb9ef421_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t14, double _t15, double _t18, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
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
            dd[4] = ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25));
            dd[5] = ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25));
            dd[6] = ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25));
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
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeFromMatrix_fma(Double4x4R m) {
        double[] mData = ((Double4x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_sf64e4286_1_fma(this.data, mData, -mData[14], mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeFromMatrix_mulAdd(Double4x4R m) {
        double[] mData = ((Double4x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_sf64e4286_1_mulAdd(this.data, mData, -mData[14], mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_sf64e4286_1_fma(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        double _rd0 = mData[12];
        double _rd1 = mData[13];
        double _rd2 = mData[14];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_rd0, _t66, _rd1 * _t65));
        dd[5] = 0.5 * java.lang.Math.fma(_rd2, _t63, java.lang.Math.fma(_rd1, _t66, -(_rd0 * _t65)));
        return makeFromMatrix_sf64e4286_2_fma(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_sf64e4286_1_mulAdd(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        double _rd0 = mData[12];
        double _rd1 = mData[13];
        double _rd2 = mData[14];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * ((_t0) * (_t64) + (((_rd0) * (_t66) + (_rd1 * _t65))));
        dd[5] = 0.5 * ((_rd2) * (_t63) + (((_rd1) * (_t66) - (_rd0 * _t65))));
        return makeFromMatrix_sf64e4286_2_mulAdd(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleDualQuat makeFromMatrix_sf64e4286_2_fma(double[] dd, double _t0, double _t63, double _t64, double _t65, double _t66, double _rd0, double _rd1, double _rd2) {
        dd[6] = 0.5 * java.lang.Math.fma(_rd2, _t66, java.lang.Math.fma(_rd0, _t64, -(_rd1 * _t63)));
        dd[7] = 0.5 * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_rd1, _t64, -(_rd0 * _t63)));
        return this;
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleDualQuat makeFromMatrix_sf64e4286_2_mulAdd(double[] dd, double _t0, double _t63, double _t64, double _t65, double _t66, double _rd0, double _rd1, double _rd2) {
        dd[6] = 0.5 * ((_rd2) * (_t66) + (((_rd0) * (_t64) - (_rd1 * _t63))));
        dd[7] = 0.5 * ((_t0) * (_t65) + (((-_rd1) * (_t64) - (_rd0 * _t63))));
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
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeFromMatrix_fma(Double3x4R m) {
        double[] mData = ((Double3x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s89828b35_1_fma(this.data, mData, -mData[11], mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeFromMatrix_mulAdd(Double3x4R m) {
        double[] mData = ((Double3x4Impl) m).data;
        double _t2 = 1.0 - mData[0];
        double _t14 = mData[10] + (mData[0] + mData[5]);
        double _t15 = 1.0 + _t14;
        double _t16 = mData[0] + (1.0 - mData[5] - mData[10]);
        double _t17 = mData[5] + (_t2 - mData[10]);
        double _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s89828b35_1_mulAdd(this.data, mData, -mData[11], mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_s89828b35_1_fma(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        double _rd0 = mData[3];
        double _rd1 = mData[7];
        double _rd2 = mData[11];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_rd0, _t66, _rd1 * _t65));
        dd[5] = 0.5 * java.lang.Math.fma(_rd2, _t63, java.lang.Math.fma(_rd1, _t66, -(_rd0 * _t65)));
        return makeFromMatrix_sf64e4286_2_fma(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeFromMatrix_s89828b35_1_mulAdd(double[] dd, double[] mData, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        double _rd0 = mData[3];
        double _rd1 = mData[7];
        double _rd2 = mData[11];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5 * ((_t0) * (_t64) + (((_rd0) * (_t66) + (_rd1 * _t65))));
        dd[5] = 0.5 * ((_rd2) * (_t63) + (((_rd1) * (_t66) - (_rd0 * _t65))));
        return makeFromMatrix_sf64e4286_2_mulAdd(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
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
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
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
        if (Math.useFma()) return pow_fma(t, dest);
        return pow_mulAdd(t, dest);
    }

    /** {@code pow} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat pow_fma(double t, @Mutated DoubleDualQuat dest) {
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
        double _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s4d980ef8_1_fma(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat pow_mulAdd(double t, @Mutated DoubleDualQuat dest) {
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
        double _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s4d980ef8_1_mulAdd(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** Piece 2 of {@code pow}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat pow_s4d980ef8_1_fma(double t, DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t13, double _t14, double _t15, double _t18, double _t19, double _t21, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31) {
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
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
            dd[7] = _t27 * _t28;
        }
        return dest;
    }

    /** Piece 2 of {@code pow}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat pow_s4d980ef8_1_mulAdd(double t, DoubleDualQuat dest, double[] dd, double _t8, double _t9, double _t10, double _t12, double _t13, double _t14, double _t15, double _t18, double _t19, double _t21, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31) {
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
            dd[4] = ((((_t29) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
            dd[5] = ((((_t29) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
            dd[6] = ((((_t29) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
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
        if (Math.useFma()) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_fma(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _sp0 = sd[0] + sd[0];
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = java.lang.Math.fma(-2.0, sd[2] * sd[2], 1.0);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(-2.0, _t0, _t6);
        dd[1] = 2.0 * java.lang.Math.fma(_rd0, _rd1, _t2);
        dd[2] = java.lang.Math.fma(-2.0, _t3, _sp0 * _rd2);
        dd[3] = 0.0;
        dd[4] = java.lang.Math.fma(-2.0, _t2, _sp0 * _rd1);
        dd[5] = java.lang.Math.fma(-2.0, _t4, _t6);
        dd[6] = 2.0 * java.lang.Math.fma(_rd0, _rd3, _t5);
        dd[7] = 0.0;
        dd[8] = 2.0 * java.lang.Math.fma(_rd0, _rd2, _t3);
        dd[9] = java.lang.Math.fma(-2.0, _rd0 * _rd3, _t5 + _t5);
        dd[10] = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
        return toMatrix_s9c292502_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _sp0 = sd[0] + sd[0];
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = ((-2.0) * (sd[2] * sd[2]) + (1.0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((-2.0) * (_t0) + (_t6));
        dd[1] = 2.0 * ((_rd0) * (_rd1) + (_t2));
        dd[2] = ((-2.0) * (_t3) + (_sp0 * _rd2));
        dd[3] = 0.0;
        dd[4] = ((-2.0) * (_t2) + (_sp0 * _rd1));
        dd[5] = ((-2.0) * (_t4) + (_t6));
        dd[6] = 2.0 * ((_rd0) * (_rd3) + (_t5));
        dd[7] = 0.0;
        dd[8] = 2.0 * ((_rd0) * (_rd2) + (_t3));
        dd[9] = ((-2.0) * (_rd0 * _rd3) + (_t5 + _t5));
        dd[10] = ((-2.0) * (_t4) + (((-2.0) * (_t0) + (1.0))));
        return toMatrix_s9c292502_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1_fma(Double4x4 dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[11] = 0.0;
        dd[12] = 2.0 * (java.lang.Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + java.lang.Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[13] = 2.0 * (java.lang.Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[14] = 2.0 * (java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + java.lang.Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1_mulAdd(Double4x4 dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[11] = 0.0;
        dd[12] = 2.0 * (((_rd1) * (_rd6) - (_rd2 * _rd5)) + ((_rd3) * (_rd4) - (_rd0 * _rd7)));
        dd[13] = 2.0 * (((_rd2) * (_rd4) - (_rd0 * _rd6)) + ((_rd3) * (_rd5) - (_rd1 * _rd7)));
        dd[14] = 2.0 * (((_rd0) * (_rd5) - (_rd1 * _rd4)) + ((_rd3) * (_rd6) - (_rd2 * _rd7)));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(-2.0, _t0, _t6);
        dd[1] = 2.0 * Math.fma(_rd0, _rd1, _t2);
        dd[2] = Math.fma(-2.0, _t3, _sp0 * _rd2);
        dd[3] = Math.fma(-2.0, _t2, _sp0 * _rd1);
        dd[4] = Math.fma(-2.0, _t4, _t6);
        dd[5] = 2.0 * Math.fma(_rd0, _rd3, _t5);
        dd[6] = 2.0 * Math.fma(_rd0, _rd2, _t3);
        dd[7] = Math.fma(-2.0, _rd0 * _rd3, _t5 + _t5);
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
        if (Math.useFma()) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_fma(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _sp0 = sd[0] + sd[0];
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = java.lang.Math.fma(-2.0, sd[2] * sd[2], 1.0);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(-2.0, _t0, _t6);
        dd[1] = java.lang.Math.fma(-2.0, _t2, _sp0 * _rd1);
        dd[2] = 2.0 * java.lang.Math.fma(_rd0, _rd2, _t3);
        dd[3] = 2.0 * (java.lang.Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + java.lang.Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[4] = 2.0 * java.lang.Math.fma(_rd0, _rd1, _t2);
        dd[5] = java.lang.Math.fma(-2.0, _t4, _t6);
        dd[6] = java.lang.Math.fma(-2.0, _rd0 * _rd3, _t5 + _t5);
        return toMatrix3x4_sec42bb4e_1_fma(dest, dd, _sp0, _t0, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _sp0 = sd[0] + sd[0];
        double _t0 = sd[1] * sd[1];
        double _t2 = sd[2] * sd[3];
        double _t3 = sd[1] * sd[3];
        double _t4 = sd[0] * sd[0];
        double _t5 = sd[1] * sd[2];
        double _t6 = ((-2.0) * (sd[2] * sd[2]) + (1.0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((-2.0) * (_t0) + (_t6));
        dd[1] = ((-2.0) * (_t2) + (_sp0 * _rd1));
        dd[2] = 2.0 * ((_rd0) * (_rd2) + (_t3));
        dd[3] = 2.0 * (((_rd1) * (_rd6) - (_rd2 * _rd5)) + ((_rd3) * (_rd4) - (_rd0 * _rd7)));
        dd[4] = 2.0 * ((_rd0) * (_rd1) + (_t2));
        dd[5] = ((-2.0) * (_t4) + (_t6));
        dd[6] = ((-2.0) * (_rd0 * _rd3) + (_t5 + _t5));
        return toMatrix3x4_sec42bb4e_1_mulAdd(dest, dd, _sp0, _t0, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1_fma(Double3x4 dest, double[] dd, double _sp0, double _t0, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[7] = 2.0 * (java.lang.Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[8] = java.lang.Math.fma(-2.0, _t3, _sp0 * _rd2);
        dd[9] = 2.0 * java.lang.Math.fma(_rd0, _rd3, _t5);
        dd[10] = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
        dd[11] = 2.0 * (java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + java.lang.Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1_mulAdd(Double3x4 dest, double[] dd, double _sp0, double _t0, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[7] = 2.0 * (((_rd2) * (_rd4) - (_rd0 * _rd6)) + ((_rd3) * (_rd5) - (_rd1 * _rd7)));
        dd[8] = ((-2.0) * (_t3) + (_sp0 * _rd2));
        dd[9] = 2.0 * ((_rd0) * (_rd3) + (_t5));
        dd[10] = ((-2.0) * (_t4) + (((-2.0) * (_t0) + (1.0))));
        dd[11] = 2.0 * (((_rd0) * (_rd5) - (_rd1 * _rd4)) + ((_rd3) * (_rd6) - (_rd2 * _rd7)));
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
        double dirX = dir.x();
        double dirY = dir.y();
        double dirZ = dir.z();
        double upX = up.x();
        double upY = up.y();
        double upZ = up.z();
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
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
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
    }

    /** {@code lookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        return lookAlong_s51104727_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37)));
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        double _t28 = ((-dirY) * (_sp0) + (upY));
        double _t29 = ((-dirZ) * (_sp0) + (upZ));
        double _t30 = ((-dirX) * (_sp0) + (upX));
        double _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        double _t38 = ((dirY) * (_t30) - (dirX * _t28));
        double _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        return lookAlong_s51104727_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37)))));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        return lookAlong_s51104727_2_fma(dirZ, dest, sd, dd, _t1, _t16, _t17, _t18, _t20, _t22, _t37, _t45, _t46, _t47, java.lang.Math.fma(-_t37, _t45, 1.0), java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0)))), java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0)))));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _ct0) {
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        return lookAlong_s51104727_2_mulAdd(dirZ, dest, sd, dd, _t1, _t16, _t17, _t18, _t20, _t22, _t37, _t45, _t46, _t47, ((-_t37) * (_t45) + (1.0)), ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0)))))))), ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0)))))))));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_2_fma(double dirZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t20, double _t22, double _t37, double _t45, double _t46, double _t47, double _t50, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80) {
        double _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t78));
        double _sp4 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        double _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)) > 0.0) {
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
        return lookAlong_s51104727_3_fma(dest, sd, dd, _t126, _t127, _t128, _t129, sd[0], sd[1], sd[2]);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_2_mulAdd(double dirZ, DoubleDualQuat dest, double[] sd, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t20, double _t22, double _t37, double _t45, double _t46, double _t47, double _t50, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80) {
        double _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t78));
        double _sp4 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        double _t126, _t127, _t128, _t129;
        if (((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))) > 0.0) {
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
        return lookAlong_s51104727_3_mulAdd(dest, sd, dd, _t126, _t127, _t128, _t129, sd[0], sd[1], sd[2]);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_3_fma(DoubleDualQuat dest, double[] sd, double[] dd, double _t126, double _t127, double _t128, double _t129, double _rd0, double _rd1, double _rd2) {
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t129, _rd3 * _t126) + java.lang.Math.fma(_rd1, _t127, -(_rd2 * _t128));
        dd[1] = java.lang.Math.fma(_rd1, _t129, _rd2 * _t126) + java.lang.Math.fma(_rd3, _t128, -(_rd0 * _t127));
        dd[2] = java.lang.Math.fma(_rd0, _t128, _rd3 * _t127) + java.lang.Math.fma(_rd2, _t129, -(_rd1 * _t126));
        dd[3] = java.lang.Math.fma(_rd3, _t129, -(_rd0 * _t126)) - java.lang.Math.fma(_rd1, _t128, _rd2 * _t127);
        dd[4] = java.lang.Math.fma(_rd4, _t129, _rd7 * _t126) + java.lang.Math.fma(_rd5, _t127, -(_rd6 * _t128));
        dd[5] = java.lang.Math.fma(_rd5, _t129, _rd6 * _t126) + java.lang.Math.fma(_rd7, _t128, -(_rd4 * _t127));
        dd[6] = java.lang.Math.fma(_rd4, _t128, _rd7 * _t127) + java.lang.Math.fma(_rd6, _t129, -(_rd5 * _t126));
        dd[7] = java.lang.Math.fma(_rd7, _t129, -(_rd4 * _t126)) - java.lang.Math.fma(_rd5, _t128, _rd6 * _t127);
        return dest;
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_3_mulAdd(DoubleDualQuat dest, double[] sd, double[] dd, double _t126, double _t127, double _t128, double _t129, double _rd0, double _rd1, double _rd2) {
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t129) + (_rd3 * _t126)) + ((_rd1) * (_t127) - (_rd2 * _t128));
        dd[1] = ((_rd1) * (_t129) + (_rd2 * _t126)) + ((_rd3) * (_t128) - (_rd0 * _t127));
        dd[2] = ((_rd0) * (_t128) + (_rd3 * _t127)) + ((_rd2) * (_t129) - (_rd1 * _t126));
        dd[3] = ((_rd3) * (_t129) - (_rd0 * _t126)) - ((_rd1) * (_t128) + (_rd2 * _t127));
        dd[4] = ((_rd4) * (_t129) + (_rd7 * _t126)) + ((_rd5) * (_t127) - (_rd6 * _t128));
        dd[5] = ((_rd5) * (_t129) + (_rd6 * _t126)) + ((_rd7) * (_t128) - (_rd4 * _t127));
        dd[6] = ((_rd4) * (_t128) + (_rd7 * _t127)) + ((_rd6) * (_t129) - (_rd5 * _t126));
        dd[7] = ((_rd7) * (_t129) - (_rd4 * _t126)) - ((_rd5) * (_t128) + (_rd6 * _t127));
        return dest;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
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
        return lookAlong_degenerate_s41d16670_1_fma(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t44, _t45, java.lang.Math.fma(_t43, _t24, _t21), java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
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
        return lookAlong_degenerate_s41d16670_1_mulAdd(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t44, _t45, ((_t43) * (_t24) + (_t21)), ((_t44) * (_t26) - (_t45 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_1_fma(DoubleDualQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t44, double _t45, double _t46, double _t55) {
        double _t56 = java.lang.Math.fma(_t46, _t25, -(_t44 * _t24));
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
        return lookAlong_degenerate_s41d16670_2_fma(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_1_mulAdd(DoubleDualQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t44, double _t45, double _t46, double _t55) {
        double _t56 = ((_t46) * (_t25) - (_t44 * _t24));
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
        return lookAlong_degenerate_s41d16670_2_mulAdd(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_2_fma(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s41d16670_3_fma(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_2_mulAdd(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s41d16670_3_mulAdd(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_3_fma(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
        double _t148, _t149, _t150, _t151;
        if (java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))) > 0.0) {
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t151, _rd3 * _t148) + java.lang.Math.fma(_rd1, _t149, -(_rd2 * _t150));
        return lookAlong_degenerate_s41d16670_4_fma(dest, dd, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_3_mulAdd(DoubleDualQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
        double _t148, _t149, _t150, _t151;
        if (((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))) > 0.0) {
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t151) + (_rd3 * _t148)) + ((_rd1) * (_t149) - (_rd2 * _t150));
        return lookAlong_degenerate_s41d16670_4_mulAdd(dest, dd, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_4_fma(DoubleDualQuat dest, double[] dd, double _t148, double _t149, double _t150, double _t151, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t151, _rd2 * _t148) + java.lang.Math.fma(_rd3, _t150, -(_rd0 * _t149));
        dd[2] = java.lang.Math.fma(_rd0, _t150, _rd3 * _t149) + java.lang.Math.fma(_rd2, _t151, -(_rd1 * _t148));
        dd[3] = java.lang.Math.fma(_rd3, _t151, -(_rd0 * _t148)) - java.lang.Math.fma(_rd1, _t150, _rd2 * _t149);
        dd[4] = java.lang.Math.fma(_rd4, _t151, _rd7 * _t148) + java.lang.Math.fma(_rd5, _t149, -(_rd6 * _t150));
        dd[5] = java.lang.Math.fma(_rd5, _t151, _rd6 * _t148) + java.lang.Math.fma(_rd7, _t150, -(_rd4 * _t149));
        dd[6] = java.lang.Math.fma(_rd4, _t150, _rd7 * _t149) + java.lang.Math.fma(_rd6, _t151, -(_rd5 * _t148));
        dd[7] = java.lang.Math.fma(_rd7, _t151, -(_rd4 * _t148)) - java.lang.Math.fma(_rd5, _t150, _rd6 * _t149);
        return dest;
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_4_mulAdd(DoubleDualQuat dest, double[] dd, double _t148, double _t149, double _t150, double _t151, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t151) + (_rd2 * _t148)) + ((_rd3) * (_t150) - (_rd0 * _t149));
        dd[2] = ((_rd0) * (_t150) + (_rd3 * _t149)) + ((_rd2) * (_t151) - (_rd1 * _t148));
        dd[3] = ((_rd3) * (_t151) - (_rd0 * _t148)) - ((_rd1) * (_t150) + (_rd2 * _t149));
        dd[4] = ((_rd4) * (_t151) + (_rd7 * _t148)) + ((_rd5) * (_t149) - (_rd6 * _t150));
        dd[5] = ((_rd5) * (_t151) + (_rd6 * _t148)) + ((_rd7) * (_t150) - (_rd4 * _t149));
        dd[6] = ((_rd4) * (_t150) + (_rd7 * _t149)) + ((_rd6) * (_t151) - (_rd5 * _t148));
        dd[7] = ((_rd7) * (_t151) - (_rd4 * _t148)) - ((_rd5) * (_t150) + (_rd6 * _t149));
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
        double dirX = dir.x();
        double dirY = dir.y();
        double dirZ = dir.z();
        double upX = up.x();
        double upY = up.y();
        double upZ = up.z();
        if (Math.useFma()) return makeRotationLookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
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
        if (Math.useFma()) return makeRotationLookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
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
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s309f29f5_1_fma(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, _t45, _t37 * _t45);
    }

    /** {@code makeRotationLookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
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
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s309f29f5_1_mulAdd(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, _t45, _t37 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_fma(double dirX, double dirY, double dirZ, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45, double _t46) {
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = java.lang.Math.fma(-_t37, _t45, 1.0);
        double _t64 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0))));
        double _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        double _t82 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s309f29f5_2_fma(dirZ, dd, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t64), java.lang.Math.fma(-dirY, _t16, _t64), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t82)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_mulAdd(double dirX, double dirY, double dirZ, double[] dd, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45, double _t46) {
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = ((-_t37) * (_t45) + (1.0));
        double _t64 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        double _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0))))))));
        double _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0))))))));
        double _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        double _t82 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return makeRotationLookAlong_s309f29f5_2_mulAdd(dirZ, dd, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t64)), ((-dirY) * (_t16) + (_t64)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2_fma(double dirZ, double[] dd, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t74, double _t75, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4, double _sp2) {
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

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2_mulAdd(double dirZ, double[] dd, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t74, double _t75, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4, double _sp2) {
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

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleDualQuat makeRotationLookAlong_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return makeRotationLookAlong_degenerate_se3802ea_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleDualQuat makeRotationLookAlong_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return makeRotationLookAlong_degenerate_se3802ea_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_fma(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        return makeRotationLookAlong_degenerate_se3802ea_2_fma(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_mulAdd(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        return makeRotationLookAlong_degenerate_se3802ea_2_mulAdd(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_fma(double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_degenerate_se3802ea_3_fma(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_mulAdd(double[] dd, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return makeRotationLookAlong_degenerate_se3802ea_3_mulAdd(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3_fma(double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
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

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3_mulAdd(double[] dd, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
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


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationX(double angle) {
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


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationY(double angle) {
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


    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleDualQuat makeRotationZ(double angle) {
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(rotationX, _rd3, rotationW * _rd0) + Math.fma(rotationY, _rd2, -(rotationZ * _rd1));
        dd[1] = Math.fma(rotationY, _rd3, rotationZ * _rd0) + Math.fma(rotationW, _rd1, -(rotationX * _rd2));
        dd[2] = Math.fma(rotationX, _rd1, rotationW * _rd2) + Math.fma(rotationZ, _rd3, -(rotationY * _rd0));
        dd[3] = Math.fma(rotationW, _rd3, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        dd[4] = Math.fma(rotationX, _rd7, rotationW * _rd4) + Math.fma(rotationY, _rd6, -(rotationZ * _rd5));
        dd[5] = Math.fma(rotationY, _rd7, rotationZ * _rd4) + Math.fma(rotationW, _rd5, -(rotationX * _rd6));
        dd[6] = Math.fma(rotationX, _rd5, rotationW * _rd6) + Math.fma(rotationZ, _rd7, -(rotationY * _rd4));
        dd[7] = Math.fma(rotationW, _rd7, -(rotationX * _rd4)) - Math.fma(rotationY, _rd5, rotationZ * _rd6);
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(rotationX, _rd3, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        dd[1] = Math.fma(rotationX, _rd2, rotationW * _rd1) + Math.fma(rotationY, _rd3, -(rotationZ * _rd0));
        dd[2] = Math.fma(rotationY, _rd0, rotationZ * _rd3) + Math.fma(rotationW, _rd2, -(rotationX * _rd1));
        dd[3] = Math.fma(rotationW, _rd3, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        dd[4] = Math.fma(rotationX, _rd7, rotationW * _rd4) + Math.fma(rotationZ, _rd5, -(rotationY * _rd6));
        dd[5] = Math.fma(rotationX, _rd6, rotationW * _rd5) + Math.fma(rotationY, _rd7, -(rotationZ * _rd4));
        dd[6] = Math.fma(rotationY, _rd4, rotationZ * _rd7) + Math.fma(rotationW, _rd6, -(rotationX * _rd5));
        dd[7] = Math.fma(rotationW, _rd7, -(rotationX * _rd4)) - Math.fma(rotationY, _rd5, rotationZ * _rd6);
        return dest;
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
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
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
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t5, _rd3 * _t2) + java.lang.Math.fma(_rd1, _t3, -(_rd2 * _t4));
        dd[1] = java.lang.Math.fma(_rd1, _t5, _rd2 * _t2) + java.lang.Math.fma(_rd3, _t4, -(_rd0 * _t3));
        return rotateAxis_s52c225a2_1_fma(dest, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t5) + (_rd3 * _t2)) + ((_rd1) * (_t3) - (_rd2 * _t4));
        dd[1] = ((_rd1) * (_t5) + (_rd2 * _t2)) + ((_rd3) * (_t4) - (_rd0 * _t3));
        return rotateAxis_s52c225a2_1_mulAdd(dest, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateAxis_s52c225a2_1_fma(DoubleDualQuat dest, double[] dd, double _t2, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[2] = java.lang.Math.fma(_rd0, _t4, _rd3 * _t3) + java.lang.Math.fma(_rd2, _t5, -(_rd1 * _t2));
        dd[3] = java.lang.Math.fma(_rd3, _t5, -(_rd0 * _t2)) - java.lang.Math.fma(_rd1, _t4, _rd2 * _t3);
        dd[4] = java.lang.Math.fma(_rd4, _t5, _rd7 * _t2) + java.lang.Math.fma(_rd5, _t3, -(_rd6 * _t4));
        dd[5] = java.lang.Math.fma(_rd5, _t5, _rd6 * _t2) + java.lang.Math.fma(_rd7, _t4, -(_rd4 * _t3));
        dd[6] = java.lang.Math.fma(_rd4, _t4, _rd7 * _t3) + java.lang.Math.fma(_rd6, _t5, -(_rd5 * _t2));
        dd[7] = java.lang.Math.fma(_rd7, _t5, -(_rd4 * _t2)) - java.lang.Math.fma(_rd5, _t4, _rd6 * _t3);
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateAxis_s52c225a2_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t2, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[2] = ((_rd0) * (_t4) + (_rd3 * _t3)) + ((_rd2) * (_t5) - (_rd1 * _t2));
        dd[3] = ((_rd3) * (_t5) - (_rd0 * _t2)) - ((_rd1) * (_t4) + (_rd2 * _t3));
        dd[4] = ((_rd4) * (_t5) + (_rd7 * _t2)) + ((_rd5) * (_t3) - (_rd6 * _t4));
        dd[5] = ((_rd5) * (_t5) + (_rd6 * _t2)) + ((_rd7) * (_t4) - (_rd4 * _t3));
        dd[6] = ((_rd4) * (_t4) + (_rd7 * _t3)) + ((_rd6) * (_t5) - (_rd5 * _t2));
        dd[7] = ((_rd7) * (_t5) - (_rd4 * _t2)) - ((_rd5) * (_t4) + (_rd6 * _t3));
        return dest;
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(_rd0, _t2, _rd3 * _t1);
        dd[1] = Math.fma(_rd1, _t2, _rd2 * _t1);
        dd[2] = Math.fma(_rd2, _t2, -(_rd1 * _t1));
        dd[3] = Math.fma(_rd3, _t2, -(_rd0 * _t1));
        dd[4] = Math.fma(_rd4, _t2, _rd7 * _t1);
        dd[5] = Math.fma(_rd5, _t2, _rd6 * _t1);
        dd[6] = Math.fma(_rd6, _t2, -(_rd5 * _t1));
        dd[7] = Math.fma(_rd7, _t2, -(_rd4 * _t1));
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
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_fma(double angleX, double angleY, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        double _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        return rotateXYZ_sf95923e6_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_mulAdd(double angleX, double angleY, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t10) * (_t8) + (_t11 * _t5));
        double _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        return rotateXYZ_sf95923e6_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_sf95923e6_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t21, _rd2 * _t19) + java.lang.Math.fma(_rd3, _t22, -(_rd0 * _t20));
        dd[2] = java.lang.Math.fma(_rd0, _t22, _rd3 * _t20) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t19));
        dd[3] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t19)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        dd[4] = java.lang.Math.fma(_rd4, _t21, _rd7 * _t19) + java.lang.Math.fma(_rd5, _t20, -(_rd6 * _t22));
        dd[5] = java.lang.Math.fma(_rd5, _t21, _rd6 * _t19) + java.lang.Math.fma(_rd7, _t22, -(_rd4 * _t20));
        dd[6] = java.lang.Math.fma(_rd4, _t22, _rd7 * _t20) + java.lang.Math.fma(_rd6, _t21, -(_rd5 * _t19));
        dd[7] = java.lang.Math.fma(_rd7, _t21, -(_rd4 * _t19)) - java.lang.Math.fma(_rd5, _t22, _rd6 * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_sf95923e6_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t21) + (_rd2 * _t19)) + ((_rd3) * (_t22) - (_rd0 * _t20));
        dd[2] = ((_rd0) * (_t22) + (_rd3 * _t20)) + ((_rd2) * (_t21) - (_rd1 * _t19));
        dd[3] = ((_rd3) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        dd[4] = ((_rd4) * (_t21) + (_rd7 * _t19)) + ((_rd5) * (_t20) - (_rd6 * _t22));
        dd[5] = ((_rd5) * (_t21) + (_rd6 * _t19)) + ((_rd7) * (_t22) - (_rd4 * _t20));
        dd[6] = ((_rd4) * (_t22) + (_rd7 * _t20)) + ((_rd6) * (_t21) - (_rd5 * _t19));
        dd[7] = ((_rd7) * (_t21) - (_rd4 * _t19)) - ((_rd5) * (_t22) + (_rd6 * _t20));
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
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_fma(double angleX, double angleZ, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        return rotateXZY_s82778362_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_mulAdd(double angleX, double angleZ, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t10) * (_t8) - (_t11 * _t5));
        double _t22 = ((_t12) * (_t5) - (_t9 * _t8));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        return rotateXZY_s82778362_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s82778362_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t19, _rd2 * _t21) + java.lang.Math.fma(_rd3, _t22, -(_rd0 * _t20));
        dd[2] = java.lang.Math.fma(_rd0, _t22, _rd3 * _t20) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t21));
        dd[3] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t21)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        dd[4] = java.lang.Math.fma(_rd4, _t19, _rd7 * _t21) + java.lang.Math.fma(_rd5, _t20, -(_rd6 * _t22));
        dd[5] = java.lang.Math.fma(_rd5, _t19, _rd6 * _t21) + java.lang.Math.fma(_rd7, _t22, -(_rd4 * _t20));
        dd[6] = java.lang.Math.fma(_rd4, _t22, _rd7 * _t20) + java.lang.Math.fma(_rd6, _t19, -(_rd5 * _t21));
        dd[7] = java.lang.Math.fma(_rd7, _t19, -(_rd4 * _t21)) - java.lang.Math.fma(_rd5, _t22, _rd6 * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s82778362_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t19) + (_rd2 * _t21)) + ((_rd3) * (_t22) - (_rd0 * _t20));
        dd[2] = ((_rd0) * (_t22) + (_rd3 * _t20)) + ((_rd2) * (_t19) - (_rd1 * _t21));
        dd[3] = ((_rd3) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        dd[4] = ((_rd4) * (_t19) + (_rd7 * _t21)) + ((_rd5) * (_t20) - (_rd6 * _t22));
        dd[5] = ((_rd5) * (_t19) + (_rd6 * _t21)) + ((_rd7) * (_t22) - (_rd4 * _t20));
        dd[6] = ((_rd4) * (_t22) + (_rd7 * _t20)) + ((_rd6) * (_t19) - (_rd5 * _t21));
        dd[7] = ((_rd7) * (_t19) - (_rd4 * _t21)) - ((_rd5) * (_t22) + (_rd6 * _t20));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(_rd0, _t2, -(_rd2 * _t1));
        dd[1] = Math.fma(_rd1, _t2, _rd3 * _t1);
        dd[2] = Math.fma(_rd0, _t1, _rd2 * _t2);
        dd[3] = Math.fma(_rd3, _t2, -(_rd1 * _t1));
        dd[4] = Math.fma(_rd4, _t2, -(_rd6 * _t1));
        dd[5] = Math.fma(_rd5, _t2, _rd7 * _t1);
        dd[6] = Math.fma(_rd4, _t1, _rd6 * _t2);
        dd[7] = Math.fma(_rd7, _t2, -(_rd5 * _t1));
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
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_fma(double angleY, double angleX, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t20) + java.lang.Math.fma(_rd1, _t21, -(_rd2 * _t22));
        return rotateYXZ_se2acb55a_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_mulAdd(double angleY, double angleX, double angleZ, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t20)) + ((_rd1) * (_t21) - (_rd2 * _t22));
        return rotateYXZ_se2acb55a_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYXZ_se2acb55a_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t19, _rd2 * _t20) + java.lang.Math.fma(_rd3, _t22, -(_rd0 * _t21));
        dd[2] = java.lang.Math.fma(_rd0, _t22, _rd3 * _t21) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t20));
        dd[3] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t20)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t21);
        dd[4] = java.lang.Math.fma(_rd4, _t19, _rd7 * _t20) + java.lang.Math.fma(_rd5, _t21, -(_rd6 * _t22));
        dd[5] = java.lang.Math.fma(_rd5, _t19, _rd6 * _t20) + java.lang.Math.fma(_rd7, _t22, -(_rd4 * _t21));
        dd[6] = java.lang.Math.fma(_rd4, _t22, _rd7 * _t21) + java.lang.Math.fma(_rd6, _t19, -(_rd5 * _t20));
        dd[7] = java.lang.Math.fma(_rd7, _t19, -(_rd4 * _t20)) - java.lang.Math.fma(_rd5, _t22, _rd6 * _t21);
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYXZ_se2acb55a_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t19) + (_rd2 * _t20)) + ((_rd3) * (_t22) - (_rd0 * _t21));
        dd[2] = ((_rd0) * (_t22) + (_rd3 * _t21)) + ((_rd2) * (_t19) - (_rd1 * _t20));
        dd[3] = ((_rd3) * (_t19) - (_rd0 * _t20)) - ((_rd1) * (_t22) + (_rd2 * _t21));
        dd[4] = ((_rd4) * (_t19) + (_rd7 * _t20)) + ((_rd5) * (_t21) - (_rd6 * _t22));
        dd[5] = ((_rd5) * (_t19) + (_rd6 * _t20)) + ((_rd7) * (_t22) - (_rd4 * _t21));
        dd[6] = ((_rd4) * (_t22) + (_rd7 * _t21)) + ((_rd6) * (_t19) - (_rd5 * _t20));
        dd[7] = ((_rd7) * (_t19) - (_rd4 * _t20)) - ((_rd5) * (_t22) + (_rd6 * _t21));
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
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_fma(double angleY, double angleZ, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        return rotateYZX_s483bd26a_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_mulAdd(double angleY, double angleZ, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        return rotateYZX_s483bd26a_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s483bd26a_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t21, _rd2 * _t19) + java.lang.Math.fma(_rd3, _t20, -(_rd0 * _t22));
        dd[2] = java.lang.Math.fma(_rd0, _t20, _rd3 * _t22) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t19));
        dd[3] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t19)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        dd[4] = java.lang.Math.fma(_rd4, _t21, _rd7 * _t19) + java.lang.Math.fma(_rd5, _t22, -(_rd6 * _t20));
        dd[5] = java.lang.Math.fma(_rd5, _t21, _rd6 * _t19) + java.lang.Math.fma(_rd7, _t20, -(_rd4 * _t22));
        dd[6] = java.lang.Math.fma(_rd4, _t20, _rd7 * _t22) + java.lang.Math.fma(_rd6, _t21, -(_rd5 * _t19));
        dd[7] = java.lang.Math.fma(_rd7, _t21, -(_rd4 * _t19)) - java.lang.Math.fma(_rd5, _t20, _rd6 * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s483bd26a_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t21) + (_rd2 * _t19)) + ((_rd3) * (_t20) - (_rd0 * _t22));
        dd[2] = ((_rd0) * (_t20) + (_rd3 * _t22)) + ((_rd2) * (_t21) - (_rd1 * _t19));
        dd[3] = ((_rd3) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        dd[4] = ((_rd4) * (_t21) + (_rd7 * _t19)) + ((_rd5) * (_t22) - (_rd6 * _t20));
        dd[5] = ((_rd5) * (_t21) + (_rd6 * _t19)) + ((_rd7) * (_t20) - (_rd4 * _t22));
        dd[6] = ((_rd4) * (_t20) + (_rd7 * _t22)) + ((_rd6) * (_t21) - (_rd5 * _t19));
        dd[7] = ((_rd7) * (_t21) - (_rd4 * _t19)) - ((_rd5) * (_t20) + (_rd6 * _t22));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(_rd0, _t2, _rd1 * _t1);
        dd[1] = Math.fma(_rd1, _t2, -(_rd0 * _t1));
        dd[2] = Math.fma(_rd2, _t2, _rd3 * _t1);
        dd[3] = Math.fma(_rd3, _t2, -(_rd2 * _t1));
        dd[4] = Math.fma(_rd4, _t2, _rd5 * _t1);
        dd[5] = Math.fma(_rd5, _t2, -(_rd4 * _t1));
        dd[6] = Math.fma(_rd6, _t2, _rd7 * _t1);
        dd[7] = Math.fma(_rd7, _t2, -(_rd6 * _t1));
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
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_fma(double angleZ, double angleX, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        double _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t22) + java.lang.Math.fma(_rd1, _t19, -(_rd2 * _t20));
        return rotateZXY_s3ffbc50a_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_mulAdd(double angleZ, double angleX, double angleY, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t10) * (_t5) + (_t11 * _t8));
        double _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t22)) + ((_rd1) * (_t19) - (_rd2 * _t20));
        return rotateZXY_s3ffbc50a_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3ffbc50a_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t21, _rd2 * _t22) + java.lang.Math.fma(_rd3, _t20, -(_rd0 * _t19));
        dd[2] = java.lang.Math.fma(_rd0, _t20, _rd3 * _t19) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t22));
        dd[3] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t22)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t19);
        dd[4] = java.lang.Math.fma(_rd4, _t21, _rd7 * _t22) + java.lang.Math.fma(_rd5, _t19, -(_rd6 * _t20));
        dd[5] = java.lang.Math.fma(_rd5, _t21, _rd6 * _t22) + java.lang.Math.fma(_rd7, _t20, -(_rd4 * _t19));
        dd[6] = java.lang.Math.fma(_rd4, _t20, _rd7 * _t19) + java.lang.Math.fma(_rd6, _t21, -(_rd5 * _t22));
        dd[7] = java.lang.Math.fma(_rd7, _t21, -(_rd4 * _t22)) - java.lang.Math.fma(_rd5, _t20, _rd6 * _t19);
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3ffbc50a_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t21) + (_rd2 * _t22)) + ((_rd3) * (_t20) - (_rd0 * _t19));
        dd[2] = ((_rd0) * (_t20) + (_rd3 * _t19)) + ((_rd2) * (_t21) - (_rd1 * _t22));
        dd[3] = ((_rd3) * (_t21) - (_rd0 * _t22)) - ((_rd1) * (_t20) + (_rd2 * _t19));
        dd[4] = ((_rd4) * (_t21) + (_rd7 * _t22)) + ((_rd5) * (_t19) - (_rd6 * _t20));
        dd[5] = ((_rd5) * (_t21) + (_rd6 * _t22)) + ((_rd7) * (_t20) - (_rd4 * _t19));
        dd[6] = ((_rd4) * (_t20) + (_rd7 * _t19)) + ((_rd6) * (_t21) - (_rd5 * _t22));
        dd[7] = ((_rd7) * (_t21) - (_rd4 * _t22)) - ((_rd5) * (_t20) + (_rd6 * _t19));
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
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_fma(double angleZ, double angleY, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        return rotateZYX_s779bee86_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_mulAdd(double angleZ, double angleY, double angleX, @Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        double _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        return rotateZYX_s779bee86_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s779bee86_1_fma(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t19, _rd2 * _t21) + java.lang.Math.fma(_rd3, _t20, -(_rd0 * _t22));
        dd[2] = java.lang.Math.fma(_rd0, _t20, _rd3 * _t22) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t21));
        dd[3] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t21)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        dd[4] = java.lang.Math.fma(_rd4, _t19, _rd7 * _t21) + java.lang.Math.fma(_rd5, _t22, -(_rd6 * _t20));
        dd[5] = java.lang.Math.fma(_rd5, _t19, _rd6 * _t21) + java.lang.Math.fma(_rd7, _t20, -(_rd4 * _t22));
        dd[6] = java.lang.Math.fma(_rd4, _t20, _rd7 * _t22) + java.lang.Math.fma(_rd6, _t19, -(_rd5 * _t21));
        dd[7] = java.lang.Math.fma(_rd7, _t19, -(_rd4 * _t21)) - java.lang.Math.fma(_rd5, _t20, _rd6 * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s779bee86_1_mulAdd(DoubleDualQuat dest, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7) {
        dd[1] = ((_rd1) * (_t19) + (_rd2 * _t21)) + ((_rd3) * (_t20) - (_rd0 * _t22));
        dd[2] = ((_rd0) * (_t20) + (_rd3 * _t22)) + ((_rd2) * (_t19) - (_rd1 * _t21));
        dd[3] = ((_rd3) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        dd[4] = ((_rd4) * (_t19) + (_rd7 * _t21)) + ((_rd5) * (_t22) - (_rd6 * _t20));
        dd[5] = ((_rd5) * (_t19) + (_rd6 * _t21)) + ((_rd7) * (_t20) - (_rd4 * _t22));
        dd[6] = ((_rd4) * (_t20) + (_rd7 * _t22)) + ((_rd6) * (_t19) - (_rd5 * _t21));
        dd[7] = ((_rd7) * (_t19) - (_rd4 * _t21)) - ((_rd5) * (_t20) + (_rd6 * _t22));
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
        return transform(p.x(), p.y(), p.z(), dest);
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, Math.fma(2.0, Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)), pX))));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, Math.fma(2.0, Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)), pY))));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, Math.fma(2.0, Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)), pZ))));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY)));
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ)));
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY)));
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ)));
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
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
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
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transformInverse} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_fma(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t21 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ);
        double _t22 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX);
        double _t23 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY);
        return transformInverse_scbf64a19_1_fma(dest, ((Double3Impl) dest).data, _t21, _t22, _t23, 2.0 * java.lang.Math.fma(sd[0], _t21, -(sd[2] * _t22)), 2.0 * java.lang.Math.fma(sd[1], _t22, -(sd[0] * _t23)), 2.0 * java.lang.Math.fma(sd[2], _t23, -(sd[1] * _t21)), sd[0], sd[1], sd[2], sd[3]);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t21 = ((-2.0) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))) + (pZ));
        double _t22 = ((-2.0) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))) + (pX));
        double _t23 = ((-2.0) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))) + (pY));
        return transformInverse_scbf64a19_1_mulAdd(dest, ((Double3Impl) dest).data, _t21, _t22, _t23, 2.0 * ((sd[0]) * (_t21) - (sd[2] * _t22)), 2.0 * ((sd[1]) * (_t22) - (sd[0] * _t23)), 2.0 * ((sd[2]) * (_t23) - (sd[1] * _t21)), sd[0], sd[1], sd[2], sd[3]);
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_scbf64a19_1_fma(Double3 dest, double[] dd, double _t21, double _t22, double _t23, double _t33, double _t34, double _t35, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[0] = java.lang.Math.fma(_rd2, _t33, java.lang.Math.fma(-_rd1, _t34, java.lang.Math.fma(_rd3, _t35, _t22)));
        dd[1] = java.lang.Math.fma(_rd0, _t34, java.lang.Math.fma(-_rd2, _t35, java.lang.Math.fma(_rd3, _t33, _t23)));
        dd[2] = java.lang.Math.fma(_rd1, _t35, java.lang.Math.fma(-_rd0, _t33, java.lang.Math.fma(_rd3, _t34, _t21)));
        return dest;
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_scbf64a19_1_mulAdd(Double3 dest, double[] dd, double _t21, double _t22, double _t23, double _t33, double _t34, double _t35, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[0] = ((_rd2) * (_t33) + (((-_rd1) * (_t34) + (((_rd3) * (_t35) + (_t22))))));
        dd[1] = ((_rd0) * (_t34) + (((-_rd2) * (_t35) + (((_rd3) * (_t33) + (_t23))))));
        dd[2] = ((_rd1) * (_t35) + (((-_rd0) * (_t33) + (((_rd3) * (_t34) + (_t21))))));
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
        return transform(pX, pY, pZ, dest);
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
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
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
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
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
