// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleDualQuat} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleDualQuatImpl implements DoubleDualQuat {

    public double rX;
    public double rY;
    public double rZ;
    public double rW;
    public double dX;
    public double dY;
    public double dZ;
    public double dW;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
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
        rW = 1;
    }

    public DoubleDualQuatImpl(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) {
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
        this.dW = dW;
    }

    public DoubleDualQuatImpl(DoubleDualQuatR src) {
        this.rX = src.rX();
        this.rY = src.rY();
        this.rZ = src.rZ();
        this.rW = src.rW();
        this.dX = src.dX();
        this.dY = src.dY();
        this.dZ = src.dZ();
        this.dW = src.dW();
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = other.rX() + this.rX;
        d.rY = otherRY + this.rY;
        d.rZ = otherRZ + this.rZ;
        d.rW = otherRW + this.rW;
        d.dX = otherDX + this.dX;
        d.dY = otherDY + this.dY;
        d.dZ = otherDZ + this.dZ;
        d.dW = otherDW + this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = otherRX + this.rX;
        d.rY = otherRY + this.rY;
        d.rZ = otherRZ + this.rZ;
        d.rW = otherRW + this.rW;
        d.dX = otherDX + this.dX;
        d.dY = otherDY + this.dY;
        d.dZ = otherDZ + this.dZ;
        d.dW = otherDW + this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = scalar * this.rX;
        d.rY = scalar * this.rY;
        d.rZ = scalar * this.rZ;
        d.rW = scalar * this.rW;
        d.dX = scalar * this.dX;
        d.dY = scalar * this.dY;
        d.dZ = scalar * this.dZ;
        d.dW = scalar * this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = -this.rX;
        d.rY = -this.rY;
        d.rZ = -this.rZ;
        d.rW = -this.rW;
        d.dX = -this.dX;
        d.dY = -this.dY;
        d.dZ = -this.dZ;
        d.dW = -this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX - other.rX();
        d.rY = this.rY - otherRY;
        d.rZ = this.rZ - otherRZ;
        d.rW = this.rW - otherRW;
        d.dX = this.dX - otherDX;
        d.dY = this.dY - otherDY;
        d.dZ = this.dZ - otherDZ;
        d.dW = this.dW - otherDW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX - otherRX;
        d.rY = this.rY - otherRY;
        d.rZ = this.rZ - otherRZ;
        d.rW = this.rW - otherRW;
        d.dX = this.dX - otherDX;
        d.dY = this.dY - otherDY;
        d.dZ = this.dZ - otherDZ;
        d.dW = this.dW - otherDW;
        return d;
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
        this.rX = v.rX();
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        this.dX = vDX;
        this.dY = vDY;
        this.dZ = vDZ;
        this.dW = vDW;
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
        this.rX = vRX;
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        this.dX = vDX;
        this.dY = vDY;
        this.dZ = vDZ;
        this.dW = vDW;
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
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        d.rX = (float) (this.rX);
        d.rY = (float) (this.rY);
        d.rZ = (float) (this.rZ);
        d.rW = (float) (this.rW);
        d.dX = (float) (this.dX);
        d.dY = (float) (this.dY);
        d.dZ = (float) (this.dZ);
        d.dW = (float) (this.dW);
        return d;
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
            double _t0 = -rTZ;
            this.rX = rRX;
            this.rY = rRY;
            this.rZ = rRZ;
            this.rW = rRW;
            this.dX = 0.5 * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ));
            this.dY = 0.5 * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -(rTX * rRZ)));
            this.dZ = 0.5 * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -(rTY * rRX)));
            this.dW = 0.5 * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -(rTX * rRX)));
            return this;
        } else {
            double _t0 = -rTZ;
            this.rX = rRX;
            this.rY = rRY;
            this.rZ = rRZ;
            this.rW = rRW;
            this.dX = 0.5 * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ))));
            this.dY = 0.5 * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ))));
            this.dZ = 0.5 * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX))));
            this.dW = 0.5 * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX))));
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
            double _t0 = -tTZ;
            this.rX = tRX;
            this.rY = tRY;
            this.rZ = tRZ;
            this.rW = tRW;
            this.dX = 0.5 * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ));
            this.dY = 0.5 * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -(tTX * tRZ)));
            this.dZ = 0.5 * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -(tTY * tRX)));
            this.dW = 0.5 * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -(tTX * tRX)));
            return this;
        } else {
            double _t0 = -tTZ;
            this.rX = tRX;
            this.rY = tRY;
            this.rZ = tRZ;
            this.rW = tRW;
            this.dX = 0.5 * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ))));
            this.dY = 0.5 * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ))));
            this.dZ = 0.5 * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX))));
            this.dW = 0.5 * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX))));
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = 2.0 * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0 * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0 * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = 2.0 * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0 * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0 * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0;
        d.sY = 1.0;
        d.sZ = 1.0;
        return d;
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
            return java.lang.Math.abs(java.lang.Math.fma(this.rX, this.rX, java.lang.Math.fma(this.rY, this.rY, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rW, this.rW, -1.0))))) <= epsilon;
        } else {
            return java.lang.Math.abs(((this.rX) * (this.rX) + (((this.rY) * (this.rY) + (((this.rZ) * (this.rZ) + (((this.rW) * (this.rW) - (1.0))))))))) <= epsilon;
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
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axis.x() * _t2;
        double _t4 = axis.y() * _t2;
        double _t5 = axis.z() * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        this.rX = _t3;
        this.rY = _t4;
        this.rZ = _t5;
        this.rW = _t6;
        this.dX = 0.5 * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        this.dY = 0.5 * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        this.dZ = 0.5 * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        this.dW = 0.5 * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
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
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisY * _t2;
        double _t5 = axisZ * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        this.rX = _t3;
        this.rY = _t4;
        this.rZ = _t5;
        this.rW = _t6;
        this.dX = 0.5 * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        this.dY = 0.5 * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        this.dZ = 0.5 * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        this.dW = 0.5 * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
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
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
            double _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            this.dY = 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            this.dZ = 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            this.dW = 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            double _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            this.dY = 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            this.dZ = 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            this.dW = 0.5 * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 0.0;
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = rotation.x();
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
            double _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            this.dY = 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            this.dZ = 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            this.dW = 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            double _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            this.dY = 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            this.dZ = 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            this.dW = 0.5 * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        this.dX = 0.5 * translationX;
        this.dY = 0.5 * translationY;
        this.dZ = 0.5 * translationZ;
        this.dW = 0.0;
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
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        this.dX = 0.5 * translationX;
        this.dY = 0.5 * translationY;
        this.dZ = 0.5 * translationZ;
        this.dW = 0.0;
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

    /** Private store group 0 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s341e3a49_c0(DoubleDualQuatImpl _dst, double _t14, double _t23, double _t15, double _t16, double _t17) {
        _dst.rX = _t14 * _t23;
        _dst.rY = _t15 * _t23;
        _dst.rZ = _t16 * _t23;
        _dst.rW = _t17 * _t23;
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s341e3a49_c1_fma(DoubleDualQuatImpl _dst, double t, double otherDX, double _t9, double _r4, double _t23, double otherDY, double _r5, double otherDZ, double _r6, double otherDW, double _r7) {
        _dst.dX = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -_r4), _r4) * _t23;
        _dst.dY = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -_r5), _r5) * _t23;
        _dst.dZ = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -_r6), _r6) * _t23;
        _dst.dW = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -_r7), _r7) * _t23;
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s341e3a49_c1_mulAdd(DoubleDualQuatImpl _dst, double t, double otherDX, double _t9, double _r4, double _t23, double otherDY, double _r5, double otherDZ, double _r6, double otherDW, double _r7) {
        _dst.dX = ((t) * (((otherDX) * (_t9) - (_r4))) + (_r4)) * _t23;
        _dst.dY = ((t) * (((otherDY) * (_t9) - (_r5))) + (_r5)) * _t23;
        _dst.dZ = ((t) * (((otherDZ) * (_t9) - (_r6))) + (_r6)) * _t23;
        _dst.dW = ((t) * (((otherDW) * (_t9) - (_r7))) + (_r7)) * _t23;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.dX;
        double _r5 = this.dY;
        double _r6 = this.dZ;
        double _r7 = this.dW;
        double _t9 = Math.fma(otherRX, _r0, otherRY * _r1) + Math.fma(otherRZ, _r2, otherRW * _r3) < 0.0 ? -1.0 : 1.0;
        double _t14 = Math.fma(t, Math.fma(otherRX, _t9, -_r0), _r0);
        double _t15 = Math.fma(t, Math.fma(otherRY, _t9, -_r1), _r1);
        double _t16 = Math.fma(t, Math.fma(otherRZ, _t9, -_r2), _r2);
        double _t17 = Math.fma(t, Math.fma(otherRW, _t9, -_r3), _r3);
        double _t23 = (1.0 / java.lang.Math.sqrt(Math.fma(_t14, _t14, _t15 * _t15) + Math.fma(_t16, _t16, _t17 * _t17)));
        dlb_s341e3a49_c0(d, _t14, _t23, _t15, _t16, _t17);
        if (Math.useFma()) dlb_s341e3a49_c1_fma(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7); else dlb_s341e3a49_c1_mulAdd(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = Math.fma(t, other.rX() - this.rX, this.rX);
        d.rY = Math.fma(t, otherRY - this.rY, this.rY);
        d.rZ = Math.fma(t, otherRZ - this.rZ, this.rZ);
        d.rW = Math.fma(t, otherRW - this.rW, this.rW);
        d.dX = Math.fma(t, otherDX - this.dX, this.dX);
        d.dY = Math.fma(t, otherDY - this.dY, this.dY);
        d.dZ = Math.fma(t, otherDZ - this.dZ, this.dZ);
        d.dW = Math.fma(t, otherDW - this.dW, this.dW);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = Math.fma(t, otherRX - this.rX, this.rX);
        d.rY = Math.fma(t, otherRY - this.rY, this.rY);
        d.rZ = Math.fma(t, otherRZ - this.rZ, this.rZ);
        d.rW = Math.fma(t, otherRW - this.rW, this.rW);
        d.dX = Math.fma(t, otherDX - this.dX, this.dX);
        d.dY = Math.fma(t, otherDY - this.dY, this.dY);
        d.dZ = Math.fma(t, otherDZ - this.dZ, this.dZ);
        d.dW = Math.fma(t, otherDW - this.dW, this.dW);
        return d;
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
        double _r0 = this.rZ;
        double _r1 = this.dZ;
        double _r2 = this.rX;
        double _r3 = this.rY;
        double _r4 = this.rW;
        double _t0 = -_r0;
        double _t8 = java.lang.Math.fma(otherRX, _r2, otherRY * _r3) + java.lang.Math.fma(otherRZ, _r0, otherRW * _r4) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = java.lang.Math.fma(_r2, _t9, _r4 * _t10);
        double _t77 = java.lang.Math.fma(_t0, _t11, -(_r3 * _t12));
        return sclerp_sea9d2379_1_fma(t, (DoubleDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(_r3, _t9, -(_r0 * _t10)) + java.lang.Math.fma(_r4, _t11, -(_r2 * _t12)), java.lang.Math.fma(_r2, _t11, _r4 * _t12) + java.lang.Math.fma(_t0, _t9, -(_r3 * _t10)), java.lang.Math.fma(_r0, _t12, -(_r3 * _t11)) + java.lang.Math.fma(_r4, _t9, -(_r2 * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t, @Mutated DoubleDualQuat dest) {
        double _r0 = this.rZ;
        double _r1 = this.dZ;
        double _r2 = this.rX;
        double _r3 = this.rY;
        double _r4 = this.rW;
        double _t0 = -_r0;
        double _t8 = ((otherRX) * (_r2) + (otherRY * _r3)) + ((otherRZ) * (_r0) + (otherRW * _r4)) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = ((_r2) * (_t9) + (_r4 * _t10));
        double _t77 = ((_t0) * (_t11) - (_r3 * _t12));
        return sclerp_sea9d2379_1_mulAdd(t, (DoubleDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((_r3) * (_t9) - (_r0 * _t10)) + ((_r4) * (_t11) - (_r2 * _t12)), ((_r2) * (_t11) + (_r4 * _t12)) + ((_t0) * (_t9) - (_r3 * _t10)), ((_r0) * (_t12) - (_r3 * _t11)) + ((_r4) * (_t9) - (_r2 * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_1_fma(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_sea9d2379_2_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(_r2, _t13, _r4 * _t14) + java.lang.Math.fma(_r3, _t15, _r0 * _t16) + (java.lang.Math.fma(_r5, _t9, _r6 * _t10) + java.lang.Math.fma(_r7, _t12, _r1 * _t11)), java.lang.Math.fma(_r0, _t15, -(_r3 * _t16)) + java.lang.Math.fma(_r4, _t13, -(_r2 * _t14)) + (java.lang.Math.fma(_r1, _t12, -(_r7 * _t11)) + java.lang.Math.fma(_r6, _t9, -(_r5 * _t10))), java.lang.Math.fma(_r3, _t13, -(_r0 * _t14)) + java.lang.Math.fma(_r4, _t16, -(_r2 * _t15)) + (java.lang.Math.fma(_r7, _t9, -(_r1 * _t10)) + java.lang.Math.fma(_r6, _t11, -(_r5 * _t12))), java.lang.Math.fma(_r2, _t16, _r4 * _t15) + java.lang.Math.fma(_t0, _t13, -(_r3 * _t14)) + (java.lang.Math.fma(_r5, _t11, _r6 * _t12) + java.lang.Math.fma(_t2, _t9, -(_r7 * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_1_mulAdd(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_sea9d2379_2_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((_r2) * (_t13) + (_r4 * _t14)) + ((_r3) * (_t15) + (_r0 * _t16)) + (((_r5) * (_t9) + (_r6 * _t10)) + ((_r7) * (_t12) + (_r1 * _t11))), ((_r0) * (_t15) - (_r3 * _t16)) + ((_r4) * (_t13) - (_r2 * _t14)) + (((_r1) * (_t12) - (_r7 * _t11)) + ((_r6) * (_t9) - (_r5 * _t10))), ((_r3) * (_t13) - (_r0 * _t14)) + ((_r4) * (_t16) - (_r2 * _t15)) + (((_r7) * (_t9) - (_r1 * _t10)) + ((_r6) * (_t11) - (_r5 * _t12))), ((_r2) * (_t16) + (_r4 * _t15)) + ((_t0) * (_t13) - (_r3 * _t14)) + (((_r5) * (_t11) + (_r6 * _t12)) + ((_t2) * (_t9) - (_r7 * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_2_fma(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
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
        double _t129 = t * Math.atan2(java.lang.Math.sqrt(_t122), _t105);
        double _t130 = Math.sin(_t129);
        double _t131 = _t124 * _t112;
        return sclerp_sea9d2379_3_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_2_mulAdd(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
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
        double _t129 = t * Math.atan2(java.lang.Math.sqrt(_t122), _t105);
        double _t130 = Math.sin(_t129);
        double _t131 = _t124 * _t112;
        return sclerp_sea9d2379_3_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_3_fma(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t106, double _t107, double _t108, double _t112, double _t114, double _t115, double _t116, double _t122, double _t124, double _t126, double _t127, double _t128, double _t130, double _t132, double _t133, double _t137) {
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
        double _t146 = _t132 * _t137;
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
        d.rX = java.lang.Math.fma(_r2, _t142, _r4 * _t143) + java.lang.Math.fma(_r3, _t144, -(_r0 * _t145));
        d.rY = java.lang.Math.fma(_r3, _t142, _r0 * _t143) + java.lang.Math.fma(_r4, _t145, -(_r2 * _t144));
        return sclerp_sea9d2379_4_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_3_mulAdd(double t, DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t106, double _t107, double _t108, double _t112, double _t114, double _t115, double _t116, double _t122, double _t124, double _t126, double _t127, double _t128, double _t130, double _t132, double _t133, double _t137) {
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
        double _t146 = _t132 * _t137;
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
        d.rX = ((_r2) * (_t142) + (_r4 * _t143)) + ((_r3) * (_t144) - (_r0 * _t145));
        d.rY = ((_r3) * (_t142) + (_r0 * _t143)) + ((_r4) * (_t145) - (_r2 * _t144));
        return sclerp_sea9d2379_4_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_4_fma(DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t142, double _t143, double _t144, double _t145, double _t147, double _t160, double _t161, double _t162) {
        d.rZ = java.lang.Math.fma(_r2, _t145, _r4 * _t144) + java.lang.Math.fma(_r0, _t142, -(_r3 * _t143));
        d.rW = java.lang.Math.fma(_r4, _t142, -(_r2 * _t143)) - java.lang.Math.fma(_r3, _t145, _r0 * _t144);
        d.dX = java.lang.Math.fma(_r2, _t147, _r4 * _t160) + java.lang.Math.fma(_r3, _t161, -(_r0 * _t162)) + (java.lang.Math.fma(_r5, _t142, _r6 * _t143) + java.lang.Math.fma(_r7, _t144, -(_r1 * _t145)));
        d.dY = java.lang.Math.fma(_r3, _t147, _r0 * _t160) + java.lang.Math.fma(_r4, _t162, -(_r2 * _t161)) + (java.lang.Math.fma(_r7, _t142, _r1 * _t143) + java.lang.Math.fma(_r6, _t145, -(_r5 * _t144)));
        d.dZ = java.lang.Math.fma(_r2, _t162, _r4 * _t161) + java.lang.Math.fma(_r0, _t147, -(_r3 * _t160)) + (java.lang.Math.fma(_r5, _t145, _r6 * _t144) + java.lang.Math.fma(_r1, _t142, -(_r7 * _t143)));
        d.dW = java.lang.Math.fma(_r4, _t147, -(_r2 * _t160)) + java.lang.Math.fma(_t0, _t161, -(_r3 * _t162)) + (java.lang.Math.fma(_r6, _t142, -(_r5 * _t143)) + java.lang.Math.fma(_t2, _t144, -(_r7 * _t145)));
        return d;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_sea9d2379_4_mulAdd(DoubleDualQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _t0, double _t2, double _t142, double _t143, double _t144, double _t145, double _t147, double _t160, double _t161, double _t162) {
        d.rZ = ((_r2) * (_t145) + (_r4 * _t144)) + ((_r0) * (_t142) - (_r3 * _t143));
        d.rW = ((_r4) * (_t142) - (_r2 * _t143)) - ((_r3) * (_t145) + (_r0 * _t144));
        d.dX = ((_r2) * (_t147) + (_r4 * _t160)) + ((_r3) * (_t161) - (_r0 * _t162)) + (((_r5) * (_t142) + (_r6 * _t143)) + ((_r7) * (_t144) - (_r1 * _t145)));
        d.dY = ((_r3) * (_t147) + (_r0 * _t160)) + ((_r4) * (_t162) - (_r2 * _t161)) + (((_r7) * (_t142) + (_r1 * _t143)) + ((_r6) * (_t145) - (_r5 * _t144)));
        d.dZ = ((_r2) * (_t162) + (_r4 * _t161)) + ((_r0) * (_t147) - (_r3 * _t160)) + (((_r5) * (_t145) + (_r6 * _t144)) + ((_r1) * (_t142) - (_r7 * _t143)));
        d.dW = ((_r4) * (_t147) - (_r2 * _t160)) + ((_t0) * (_t161) - (_r3 * _t162)) + (((_r6) * (_t142) - (_r5 * _t143)) + ((_t2) * (_t144) - (_r7 * _t145)));
        return d;
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
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rY;
            double _r3 = this.rZ;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dY;
            double _r7 = this.dZ;
            mul_s15ca02b0_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s15ca02b0_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rY;
            double _r3 = this.rZ;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dY;
            double _r7 = this.dZ;
            mul_s15ca02b0_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s15ca02b0_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        }
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s15ca02b0_c0_fma(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRZ, double _r2, double otherRY, double _r3) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, otherRW * _r1) + java.lang.Math.fma(otherRZ, _r2, -(otherRY * _r3));
        _dst.rY = java.lang.Math.fma(otherRX, _r3, otherRW * _r2) + java.lang.Math.fma(otherRY, _r0, -(otherRZ * _r1));
        _dst.rZ = java.lang.Math.fma(otherRY, _r1, otherRZ * _r0) + java.lang.Math.fma(otherRW, _r3, -(otherRX * _r2));
        _dst.rW = java.lang.Math.fma(otherRW, _r0, -(otherRX * _r1)) - java.lang.Math.fma(otherRY, _r2, otherRZ * _r3);
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s15ca02b0_c0_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRZ, double _r2, double otherRY, double _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRZ) * (_r2) - (otherRY * _r3));
        _dst.rY = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRY) * (_r0) - (otherRZ * _r1));
        _dst.rZ = ((otherRY) * (_r1) + (otherRZ * _r0)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r2) + (otherRZ * _r3));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s15ca02b0_c1_fma(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRZ, double _r6, double otherRY, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDZ, double _r2, double otherDY, double _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDZ, _r2, -(otherDY * _r3)));
        _dst.dY = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRY, _r4, -(otherRZ * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDY, _r0, -(otherDZ * _r1)));
        _dst.dZ = java.lang.Math.fma(otherRY, _r5, otherRZ * _r4) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r1, otherDZ * _r0) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r7, -(otherRY * _r6)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r3, -(otherDY * _r2)));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s15ca02b0_c1_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRZ, double _r6, double otherRY, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDZ, double _r2, double otherDY, double _r3) {
        _dst.dX = ((otherRX) * (_r4) + (otherRW * _r5)) + ((otherRZ) * (_r6) - (otherRY * _r7)) + (((otherDX) * (_r0) + (otherDW * _r1)) + ((otherDZ) * (_r2) - (otherDY * _r3)));
        _dst.dY = ((otherRX) * (_r7) + (otherRW * _r6)) + ((otherRY) * (_r4) - (otherRZ * _r5)) + (((otherDX) * (_r3) + (otherDW * _r2)) + ((otherDY) * (_r0) - (otherDZ * _r1)));
        _dst.dZ = ((otherRY) * (_r5) + (otherRZ * _r4)) + ((otherRW) * (_r7) - (otherRX * _r6)) + (((otherDY) * (_r1) + (otherDZ * _r0)) + ((otherDW) * (_r3) - (otherDX * _r2)));
        _dst.dW = ((otherRW) * (_r4) - (otherRX * _r5)) + ((-otherRZ) * (_r7) - (otherRY * _r6)) + (((otherDW) * (_r0) - (otherDX * _r1)) + ((-otherDZ) * (_r3) - (otherDY * _r2)));
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
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rY;
            double _r3 = this.rZ;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dY;
            double _r7 = this.dZ;
            mul_s15ca02b0_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s15ca02b0_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rY;
            double _r3 = this.rZ;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dY;
            double _r7 = this.dZ;
            mul_s15ca02b0_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s15ca02b0_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        }
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
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            preMul_s15ca02b0_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s15ca02b0_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            preMul_s15ca02b0_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s15ca02b0_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        }
    }

    /**
     * Private store group 0 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s15ca02b0_c0_fma(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRY, double _r2, double otherRZ, double _r3) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, otherRW * _r1) + java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r3));
        _dst.rY = java.lang.Math.fma(otherRY, _r0, otherRZ * _r1) + java.lang.Math.fma(otherRW, _r3, -(otherRX * _r2));
        _dst.rZ = java.lang.Math.fma(otherRX, _r3, otherRW * _r2) + java.lang.Math.fma(otherRZ, _r0, -(otherRY * _r1));
        _dst.rW = java.lang.Math.fma(otherRW, _r0, -(otherRX * _r1)) - java.lang.Math.fma(otherRY, _r3, otherRZ * _r2);
    }

    /**
     * Private store group 0 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s15ca02b0_c0_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRY, double _r2, double otherRZ, double _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rZ = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r3) + (otherRZ * _r2));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s15ca02b0_c1_fma(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRY, double _r6, double otherRZ, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDY, double _r2, double otherDZ, double _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r2, -(otherDY * _r3)));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s15ca02b0_c1_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRY, double _r6, double otherRZ, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDY, double _r2, double otherDZ, double _r3) {
        _dst.dX = ((otherRX) * (_r4) + (otherRW * _r5)) + ((otherRY) * (_r6) - (otherRZ * _r7)) + (((otherDX) * (_r0) + (otherDW * _r1)) + ((otherDY) * (_r2) - (otherDZ * _r3)));
        _dst.dY = ((otherRY) * (_r4) + (otherRZ * _r5)) + ((otherRW) * (_r7) - (otherRX * _r6)) + (((otherDY) * (_r0) + (otherDZ * _r1)) + ((otherDW) * (_r3) - (otherDX * _r2)));
        _dst.dZ = ((otherRX) * (_r7) + (otherRW * _r6)) + ((otherRZ) * (_r4) - (otherRY * _r5)) + (((otherDX) * (_r3) + (otherDW * _r2)) + ((otherDZ) * (_r0) - (otherDY * _r1)));
        _dst.dW = ((otherRW) * (_r4) - (otherRX * _r5)) + ((-otherRZ) * (_r6) - (otherRY * _r7)) + (((otherDW) * (_r0) - (otherDX * _r1)) + ((-otherDZ) * (_r2) - (otherDY * _r3)));
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
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            preMul_s15ca02b0_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s15ca02b0_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            preMul_s15ca02b0_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s15ca02b0_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        }
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
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            d.rX = java.lang.Math.fma(weight, otherRX, this.rX);
            d.rY = java.lang.Math.fma(weight, otherRY, this.rY);
            d.rZ = java.lang.Math.fma(weight, otherRZ, this.rZ);
            d.rW = java.lang.Math.fma(weight, otherRW, this.rW);
            d.dX = java.lang.Math.fma(weight, otherDX, this.dX);
            d.dY = java.lang.Math.fma(weight, otherDY, this.dY);
            d.dZ = java.lang.Math.fma(weight, otherDZ, this.dZ);
            d.dW = java.lang.Math.fma(weight, otherDW, this.dW);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            d.rX = ((weight) * (otherRX) + (this.rX));
            d.rY = ((weight) * (otherRY) + (this.rY));
            d.rZ = ((weight) * (otherRZ) + (this.rZ));
            d.rW = ((weight) * (otherRW) + (this.rW));
            d.dX = ((weight) * (otherDX) + (this.dX));
            d.dY = ((weight) * (otherDY) + (this.dY));
            d.dZ = ((weight) * (otherDZ) + (this.dZ));
            d.dW = ((weight) * (otherDW) + (this.dW));
            return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = -this.rX;
        d.rY = -this.rY;
        d.rZ = -this.rZ;
        d.rW = this.rW;
        d.dX = -this.dX;
        d.dY = -this.dY;
        d.dZ = -this.dZ;
        d.dW = this.dW;
        return d;
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

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s15ca02b0_c0_fma(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRY, double _r2, double otherRZ, double _r3, double _t0) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, -(otherRW * _r1)) + java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r3));
        _dst.rY = java.lang.Math.fma(otherRY, _r0, otherRZ * _r1) + java.lang.Math.fma(_t0, _r2, -(otherRW * _r3));
        _dst.rZ = java.lang.Math.fma(otherRX, _r3, -(otherRW * _r2)) + java.lang.Math.fma(otherRZ, _r0, -(otherRY * _r1));
        _dst.rW = java.lang.Math.fma(otherRX, _r1, otherRW * _r0) - java.lang.Math.fma(-otherRZ, _r2, -(otherRY * _r3));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s15ca02b0_c0_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r0, double otherRW, double _r1, double otherRY, double _r2, double otherRZ, double _r3, double _t0) {
        _dst.rX = ((otherRX) * (_r0) - (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((_t0) * (_r2) - (otherRW * _r3));
        _dst.rZ = ((otherRX) * (_r3) - (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRX) * (_r1) + (otherRW * _r0)) - ((-otherRZ) * (_r2) - (otherRY * _r3));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s15ca02b0_c1_fma(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRY, double _r6, double otherRZ, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDY, double _r2, double otherDZ, double _r3, double _t0) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, -(otherRW * _r5)) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, -(otherDW * _r1)) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(_t0, _r6, -(otherRW * _r7)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(-otherDX, _r2, -(otherDW * _r3)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, -(otherRW * _r6)) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, -(otherDW * _r2)) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRX, _r5, otherRW * _r4) + java.lang.Math.fma(otherRY, _r7, otherRZ * _r6) + (java.lang.Math.fma(otherDX, _r1, otherDW * _r0) + java.lang.Math.fma(otherDY, _r3, otherDZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s15ca02b0_c1_mulAdd(DoubleDualQuatImpl _dst, double otherRX, double _r4, double otherRW, double _r5, double otherRY, double _r6, double otherRZ, double _r7, double otherDX, double _r0, double otherDW, double _r1, double otherDY, double _r2, double otherDZ, double _r3, double _t0) {
        _dst.dX = ((otherRX) * (_r4) - (otherRW * _r5)) + ((otherRY) * (_r6) - (otherRZ * _r7)) + (((otherDX) * (_r0) - (otherDW * _r1)) + ((otherDY) * (_r2) - (otherDZ * _r3)));
        _dst.dY = ((otherRY) * (_r4) + (otherRZ * _r5)) + ((_t0) * (_r6) - (otherRW * _r7)) + (((otherDY) * (_r0) + (otherDZ * _r1)) + ((-otherDX) * (_r2) - (otherDW * _r3)));
        _dst.dZ = ((otherRX) * (_r7) - (otherRW * _r6)) + ((otherRZ) * (_r4) - (otherRY * _r5)) + (((otherDX) * (_r3) - (otherDW * _r2)) + ((otherDZ) * (_r0) - (otherDY * _r1)));
        _dst.dW = ((otherRX) * (_r5) + (otherRW * _r4)) + ((otherRY) * (_r7) + (otherRZ * _r6)) + (((otherDX) * (_r1) + (otherDW * _r0)) + ((otherDY) * (_r3) + (otherDZ * _r2)));
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
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            double _t0 = -otherRX;
            difference_s15ca02b0_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s15ca02b0_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            double _r0 = this.rW;
            double _r1 = this.rX;
            double _r2 = this.rZ;
            double _r3 = this.rY;
            double _r4 = this.dW;
            double _r5 = this.dX;
            double _r6 = this.dZ;
            double _r7 = this.dY;
            double _t0 = -otherRX;
            difference_s15ca02b0_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s15ca02b0_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
            return d;
        }
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
            return java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) + (java.lang.Math.fma(otherDX, this.dX, otherDY * this.dY) + java.lang.Math.fma(otherDZ, this.dZ, otherDW * this.dW));
        } else {
            return ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) + (((otherDX) * (this.dX) + (otherDY * this.dY)) + ((otherDZ) * (this.dZ) + (otherDW * this.dW)));
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
            return java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) + (java.lang.Math.fma(otherDX, this.dX, otherDY * this.dY) + java.lang.Math.fma(otherDZ, this.dZ, otherDW * this.dW));
        } else {
            return ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) + (((otherDX) * (this.dX) + (otherDY * this.dY)) + ((otherDZ) * (this.dZ) + (otherDW * this.dW)));
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = -this.dX;
        d.dY = -this.dY;
        d.dZ = -this.dZ;
        d.dW = -this.dW;
        return d;
    }

    /** Private store group 0 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c0(DoubleDualQuatImpl _dst, double _t4, double _r1, double _t9, double _t8, double _r2, double _t10, double _r0, double _t11, double _t13) {
        _dst.rX = _t4 < 1.0E-28 ? _r1 : _t9 * _t8;
        _dst.rY = _t4 < 1.0E-28 ? _r2 : _t10 * _t8;
        _dst.rZ = _t4 < 1.0E-28 ? _r0 : _t11 * _t8;
        _dst.rW = _t4 < 1.0E-28 ? 1.0 : _t13;
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c1_fma(DoubleDualQuatImpl _dst, double _t4, double _r4, double _t9, double _t14, double _t12, double _sp0, double _r5, double _t10, double _r3, double _t11, double _t5, double _t8) {
        _dst.dX = _t4 < 1.0E-28 ? _r4 : java.lang.Math.fma(_t9, _t14, java.lang.Math.fma(-_t9, _t12, _r4) * _sp0);
        _dst.dY = _t4 < 1.0E-28 ? _r5 : java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(-_t10, _t12, _r5) * _sp0);
        _dst.dZ = _t4 < 1.0E-28 ? _r3 : java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(-_t11, _t12, _r3) * _sp0);
        _dst.dW = _t4 < 1.0E-28 ? -_t5 : -(_t12 * _t8);
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c1_mulAdd(DoubleDualQuatImpl _dst, double _t4, double _r4, double _t9, double _t14, double _t12, double _sp0, double _r5, double _t10, double _r3, double _t11, double _t5, double _t8) {
        _dst.dX = _t4 < 1.0E-28 ? _r4 : ((_t9) * (_t14) + (((-_t9) * (_t12) + (_r4)) * _sp0));
        _dst.dY = _t4 < 1.0E-28 ? _r5 : ((_t10) * (_t14) + (((-_t10) * (_t12) + (_r5)) * _sp0));
        _dst.dZ = _t4 < 1.0E-28 ? _r3 : ((_t11) * (_t14) + (((-_t11) * (_t12) + (_r3)) * _sp0));
        _dst.dW = _t4 < 1.0E-28 ? -_t5 : -(_t12 * _t8);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rZ;
        double _r1 = this.rX;
        double _r2 = this.rY;
        double _r3 = this.dZ;
        double _r4 = this.dX;
        double _r5 = this.dY;
        double _t4 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        double _t5 = Math.fma(_r0, _r3, Math.fma(_r1, _r4, _r2 * _r5));
        double _t7 = java.lang.Math.sqrt(_t4);
        double _t6 = 1.0 / _t7;
        double _t8 = Math.sin(_t7);
        double _t9 = _r1 * _t6;
        double _t10 = _r2 * _t6;
        double _t11 = _r0 * _t6;
        double _t12 = _t5 * _t6;
        double _t13 = Math.cosFromSin(_t8, _t7);
        exp_s6608609c_c0(d, _t4, _r1, _t9, _t8, _r2, _t10, _r0, _t11, _t13);
        if (Math.useFma()) exp_s6608609c_c1_fma(d, _t4, _r4, _t9, _t12 * _t13, _t12, _t6 * _t8, _r5, _t10, _r3, _t11, _t5, _t8); else exp_s6608609c_c1_mulAdd(d, _t4, _r4, _t9, _t12 * _t13, _t12, _t6 * _t8, _r5, _t10, _r3, _t11, _t5, _t8);
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.dX;
        d.y = this.dY;
        d.z = this.dZ;
        d.w = this.dW;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0));
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t12));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.y = 0.0;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0));
        }
        d.z = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            d.y = Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0));
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.y = Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.y = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t1), 1.0));
        } else {
            d.y = Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.rX;
        d.y = this.rY;
        d.z = this.rZ;
        d.w = this.rW;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = 2.0 * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.y = 2.0 * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.z = 2.0 * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = -this.rX;
        d.rY = -this.rY;
        d.rZ = -this.rZ;
        d.rW = this.rW;
        d.dX = -this.dX;
        d.dY = -this.dY;
        d.dZ = -this.dZ;
        d.dW = this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t8 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 * (Math.fma(this.rX, this.dX, this.rY * this.dY) + Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.rW;
        d.rX = -(_rd0 * _t8_inv);
        d.rY = -(_rd1 * _t8_inv);
        d.rZ = -(_rd2 * _t8_inv);
        d.rW = _rd3 * _t8_inv;
        d.dX = Math.fma(_rd0, _sp0, -(this.dX * _t8_inv));
        d.dY = Math.fma(_rd1, _sp0, -(this.dY * _t8_inv));
        d.dZ = Math.fma(_rd2, _sp0, -(this.dZ * _t8_inv));
        d.dW = Math.fma(this.dW, _t8_inv, -(_rd3 * _sp0));
        return d;
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
            return java.lang.Math.sqrt(java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW));
        } else {
            return java.lang.Math.sqrt(((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW)));
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
            return java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        } else {
            return ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        }
    }

    /** Private store group 0 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c0(DoubleDualQuatImpl _dst, double _t18, double _t9, double _t21, double _t22, double _t10, double _t23, double _t8, double _t24) {
        _dst.rX = _t18 < 1.0E-28 ? _t9 : _t21 * _t22;
        _dst.rY = _t18 < 1.0E-28 ? _t10 : _t23 * _t22;
        _dst.rZ = _t18 < 1.0E-28 ? _t8 : _t24 * _t22;
        _dst.rW = 0.0;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c1_fma(DoubleDualQuatImpl _dst, double _t18, double _t12, double _t26, double _t21, double _t19, double _t22, double _t25, double _t14, double _t23, double _t15, double _t24) {
        _dst.dX = _t18 < 1.0E-28 ? _t12 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25));
        _dst.dY = _t18 < 1.0E-28 ? _t14 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-28 ? _t15 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25));
        _dst.dW = 0.0;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c1_mulAdd(DoubleDualQuatImpl _dst, double _t18, double _t12, double _t26, double _t21, double _t19, double _t22, double _t25, double _t14, double _t23, double _t15, double _t24) {
        _dst.dX = _t18 < 1.0E-28 ? _t12 : ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25));
        _dst.dY = _t18 < 1.0E-28 ? _t14 : ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-28 ? _t15 : ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25));
        _dst.dW = 0.0;
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s6608609c_tail_fma(DoubleDualQuatImpl _dst, double _t19, double _t9, double _t18, double _t11, double _t10, double _t8, double _r0, double _r7, double _t12, double _t14, double _t15) {
        double _t21 = _t19 * _t9;
        double _t22 = Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * (_r0 < 0.0 ? -_r7 : _r7);
        log_s6608609c_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s6608609c_c1_fma(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s6608609c_tail_mulAdd(DoubleDualQuatImpl _dst, double _t19, double _t9, double _t18, double _t11, double _t10, double _t8, double _r0, double _r7, double _t12, double _t14, double _t15) {
        double _t21 = _t19 * _t9;
        double _t22 = Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * (_r0 < 0.0 ? -_r7 : _r7);
        log_s6608609c_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s6608609c_c1_mulAdd(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rW;
        double _r1 = this.rZ;
        double _r2 = this.rX;
        double _r3 = this.rY;
        double _r4 = this.dX;
        double _r5 = this.dY;
        double _r6 = this.dZ;
        double _r7 = this.dW;
        double _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (_r0 < 0.0) {
            _t8 = -_r1;
            _t9 = -_r2;
            _t10 = -_r3;
            _t11 = -_r0;
            _t12 = -_r4;
            _t14 = -_r5;
            _t15 = -_r6;
        } else {
            _t8 = _r1;
            _t9 = _r2;
            _t10 = _r3;
            _t11 = _r0;
            _t12 = _r4;
            _t14 = _r5;
            _t15 = _r6;
        }
        double _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        if (Math.useFma()) log_s6608609c_tail_fma(d, (1.0 / java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15); else log_s6608609c_tail_mulAdd(d, (1.0 / java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15);
        return d;
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
        double _t0 = -m.m23();
        double _t2 = 1.0 - m.m00();
        double _t4 = m.m21() - m.m12();
        double _t6 = m.m01() + m.m10();
        double _t7 = m.m02() + m.m20();
        double _t8 = m.m02() - m.m20();
        double _t9 = m.m12() + m.m21();
        double _t10 = m.m10() - m.m01();
        double _t14 = m.m22() + (m.m00() + m.m11());
        double _t15 = 1.0 + _t14;
        double _t16 = m.m00() + (1.0 - m.m11() - m.m22());
        double _t17 = m.m11() + (_t2 - m.m22());
        double _t18 = m.m22() + (_t2 - m.m11());
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t15));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t17));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t18));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t16));
        double _t63, _t64, _t65, _t66;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5 * java.lang.Math.sqrt(_t15);
        } else {
            if (m.m00() > java.lang.Math.max(m.m11(), m.m22())) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (m.m11() > m.m22()) {
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
        this.rX = _t63;
        this.rY = _t64;
        this.rZ = _t65;
        this.rW = _t66;
        this.dX = 0.5 * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        this.dY = 0.5 * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        this.dZ = 0.5 * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        this.dW = 0.5 * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
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
        double _t0 = -m.m23();
        double _t2 = 1.0 - m.m00();
        double _t4 = m.m21() - m.m12();
        double _t6 = m.m01() + m.m10();
        double _t7 = m.m02() + m.m20();
        double _t8 = m.m02() - m.m20();
        double _t9 = m.m12() + m.m21();
        double _t10 = m.m10() - m.m01();
        double _t14 = m.m22() + (m.m00() + m.m11());
        double _t15 = 1.0 + _t14;
        double _t16 = m.m00() + (1.0 - m.m11() - m.m22());
        double _t17 = m.m11() + (_t2 - m.m22());
        double _t18 = m.m22() + (_t2 - m.m11());
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t15));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t17));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t18));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t16));
        double _t63, _t64, _t65, _t66;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5 * java.lang.Math.sqrt(_t15);
        } else {
            if (m.m00() > java.lang.Math.max(m.m11(), m.m22())) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (m.m11() > m.m22()) {
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
        this.rX = _t63;
        this.rY = _t64;
        this.rZ = _t65;
        this.rW = _t66;
        this.dX = 0.5 * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        this.dY = 0.5 * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        this.dZ = 0.5 * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        this.dW = 0.5 * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
        return this;
    }

    /** Private store group 0 of {@code makeFromMatrix}: computes and stores it; reached only through it. */
    private void makeFromMatrix_s11ab8262_c0(DoubleDualQuatImpl _dst, double _t13, double _sp0, double _t3, double _r0, double _t4, double _t15, double _r3, double _r4, double _sp1, double _t5, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        _dst.rX = _t13 > 0.0 ? _sp0 * _t3 : _r0 > _t4 ? 0.5 * java.lang.Math.sqrt(_t15) : _r3 > _r4 ? _sp1 * _t5 : _sp2 * _t6;
        _dst.rY = _t13 > 0.0 ? _sp0 * _t7 : _r0 > _t4 ? _sp3 * _t5 : _r3 > _r4 ? 0.5 * java.lang.Math.sqrt(_t16) : _sp2 * _t8;
        _dst.rZ = _t13 > 0.0 ? _sp0 * _t9 : _r0 > _t4 ? _sp3 * _t6 : _r3 > _r4 ? _sp1 * _t8 : 0.5 * java.lang.Math.sqrt(_t17);
        _dst.rW = _t13 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : _r0 > _t4 ? _sp3 * _t3 : _r3 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private void makeFromMatrix_s11ab8262_tail(DoubleDualQuatImpl _dst, double _t15, double _t13, double _sp0, double _t3, double _r0, double _t4, double _r3, double _r4, double _sp1, double _t5, double _sp2, double _t6, double _t7, double _t16, double _t8, double _t9, double _t17, double _t14) {
        makeFromMatrix_s11ab8262_c0(_dst, _t13, _sp0, _t3, _r0, _t4, _t15, _r3, _r4, _sp1, _t5, _sp2, _t6, _t7, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
        _dst.dX = 0.0;
        _dst.dY = 0.0;
        _dst.dZ = 0.0;
        _dst.dW = 0.0;
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
        double _r0 = m.m00();
        double _r1 = m.m21();
        double _r2 = m.m12();
        double _r3 = m.m11();
        double _r4 = m.m22();
        double _r5 = m.m01();
        double _r6 = m.m10();
        double _r7 = m.m02();
        double _r8 = m.m20();
        double _t1 = 1.0 - _r0;
        double _t13 = _r4 + (_r0 + _r3);
        double _t14 = 1.0 + _t13;
        double _t16 = _r3 + (_t1 - _r4);
        double _t17 = _r4 + (_t1 - _r3);
        makeFromMatrix_s11ab8262_tail(this, _r0 + (1.0 - _r3 - _r4), _t13, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r1 - _r2, _r0, java.lang.Math.max(_r3, _r4), _r3, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r2 + _r1, _r6 - _r5, _t17, _t14);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t4 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0) {
            d.rX = this.rX * _t5;
            d.rY = this.rY * _t5;
            d.rZ = this.rZ * _t5;
            d.rW = this.rW * _t5;
            d.dX = this.dX * _t5;
            d.dY = this.dY * _t5;
            d.dZ = this.dZ * _t5;
            d.dW = this.dW * _t5;
        } else {
            d.rX = 0.0;
            d.rY = 0.0;
            d.rZ = 0.0;
            d.rW = 0.0;
            d.dX = 0.0;
            d.dY = 0.0;
            d.dZ = 0.0;
            d.dW = 0.0;
        }
        return d;
    }

    /** Private store group 0 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s26a12135_c0(DoubleDualQuatImpl _dst, double _t18, double t, double _t9, double _t21, double _t28, double _t10, double _t23, double _t8, double _t24, double _t30) {
        _dst.rX = _t18 < 1.0E-28 ? t * _t9 : _t21 * _t28;
        _dst.rY = _t18 < 1.0E-28 ? t * _t10 : _t23 * _t28;
        _dst.rZ = _t18 < 1.0E-28 ? t * _t8 : _t24 * _t28;
        _dst.rW = _t18 < 1.0E-28 ? 1.0 : _t30;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s26a12135_c1_fma(DoubleDualQuatImpl _dst, double _t18, double t, double _t12, double _t29, double _t21, double _t19, double _t28, double _t31, double _t14, double _t23, double _t15, double _t24, double _t13, double _t27) {
        _dst.dX = _t18 < 1.0E-28 ? t * _t12 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
        _dst.dY = _t18 < 1.0E-28 ? t * _t14 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-28 ? t * _t15 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
        _dst.dW = _t18 < 1.0E-28 ? t * t * _t13 : _t27 * _t28;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s26a12135_c1_mulAdd(DoubleDualQuatImpl _dst, double _t18, double t, double _t12, double _t29, double _t21, double _t19, double _t28, double _t31, double _t14, double _t23, double _t15, double _t24, double _t13, double _t27) {
        _dst.dX = _t18 < 1.0E-28 ? t * _t12 : ((((_t29) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
        _dst.dY = _t18 < 1.0E-28 ? t * _t14 : ((((_t29) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-28 ? t * _t15 : ((((_t29) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
        _dst.dW = _t18 < 1.0E-28 ? t * t * _t13 : _t27 * _t28;
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s26a12135_tail_fma(DoubleDualQuatImpl _dst, double _t8, double _t9, double _t10, double _t13, double t, double _t11, double _t12, double _t14, double _t15) {
        double _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t21 = _t19 * _t9;
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        pow_s26a12135_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s26a12135_c1_fma(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s26a12135_tail_mulAdd(DoubleDualQuatImpl _dst, double _t8, double _t9, double _t10, double _t13, double t, double _t11, double _t12, double _t14, double _t15) {
        double _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t21 = _t19 * _t9;
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        pow_s26a12135_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s26a12135_c1_mulAdd(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rW;
        double _r1 = this.rZ;
        double _r2 = this.rX;
        double _r3 = this.rY;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
        double _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (_r0 < 0.0) {
            _t8 = -_r1;
            _t9 = -_r2;
            _t10 = -_r3;
            _t11 = -_r0;
            _t12 = -_r4;
            _t13 = -_r5;
            _t14 = -_r6;
            _t15 = -_r7;
        } else {
            _t8 = _r1;
            _t9 = _r2;
            _t10 = _r3;
            _t11 = _r0;
            _t12 = _r4;
            _t13 = _r5;
            _t14 = _r6;
            _t15 = _r7;
        }
        if (Math.useFma()) pow_s26a12135_tail_fma(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15); else pow_s26a12135_tail_mulAdd(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = dualX;
        d.dY = dualY;
        d.dZ = dualZ;
        d.dW = dualW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = dualX;
        d.dY = dualY;
        d.dZ = dualZ;
        d.dW = dualW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = real.x();
        d.rY = realY;
        d.rZ = realZ;
        d.rW = realW;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        d.dW = this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = realX;
        d.rY = realY;
        d.rZ = realZ;
        d.rW = realW;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        d.dW = this.dW;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -rotationY;
        double _t22 = 2.0 * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        double _t23 = 2.0 * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        double _t24 = 2.0 * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.rX = rotationX;
        d.rY = rotationY;
        d.rZ = rotationZ;
        d.rW = rotationW;
        d.dX = 0.5 * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        d.dY = 0.5 * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        d.dZ = 0.5 * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        d.dW = 0.5 * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -this.rY;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = 0.5 * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        d.dY = 0.5 * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        d.dZ = 0.5 * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        d.dW = 0.5 * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -this.rY;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = 0.5 * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        d.dY = 0.5 * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        d.dZ = 0.5 * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        d.dW = 0.5 * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return d;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_fma(Double4x4Impl _dst, double _t0, double _t6, double _r0, double _r1, double _t2, double _t3, double _sp0, double _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0, _t0, _t6);
        _dst.m10 = 2.0 * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0, _t3, _sp0 * _r2);
        _dst.m30 = 0.0;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_mulAdd(Double4x4Impl _dst, double _t0, double _t6, double _r0, double _r1, double _t2, double _t3, double _sp0, double _r2) {
        _dst.m00 = ((-2.0) * (_t0) + (_t6));
        _dst.m10 = 2.0 * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0) * (_t3) + (_sp0 * _r2));
        _dst.m30 = 0.0;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_fma(Double4x4Impl _dst, double _t2, double _sp0, double _r1, double _t4, double _t6, double _r0, double _r3, double _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0, _t4, _t6);
        _dst.m21 = 2.0 * java.lang.Math.fma(_r0, _r3, _t5);
        _dst.m31 = 0.0;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_mulAdd(Double4x4Impl _dst, double _t2, double _sp0, double _r1, double _t4, double _t6, double _r0, double _r3, double _t5) {
        _dst.m01 = ((-2.0) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0) * (_t4) + (_t6));
        _dst.m21 = 2.0 * ((_r0) * (_r3) + (_t5));
        _dst.m31 = 0.0;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_fma(Double4x4Impl _dst, double _r0, double _r2, double _t3, double _r3, double _t5, double _t4, double _t0) {
        _dst.m02 = 2.0 * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
        _dst.m32 = 0.0;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_mulAdd(Double4x4Impl _dst, double _r0, double _r2, double _t3, double _r3, double _t5, double _t4, double _t0) {
        _dst.m02 = 2.0 * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0) * (_t4) + (((-2.0) * (_t0) + (1.0))));
        _dst.m32 = 0.0;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c3_fma(Double4x4Impl _dst, double _r1, double _r4, double _r2, double _r5, double _r3, double _r6, double _r0, double _r7) {
        _dst.m03 = 2.0 * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0 * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0 * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
        _dst.m33 = 1.0;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c3_mulAdd(Double4x4Impl _dst, double _r1, double _r4, double _r2, double _r5, double _r3, double _r6, double _r0, double _r7) {
        _dst.m03 = 2.0 * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0 * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0 * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
        _dst.m33 = 1.0;
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
        Double4x4Impl d = (Double4x4Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.dZ;
        double _r5 = this.dY;
        double _r6 = this.dX;
        double _r7 = this.dW;
        double _sp0 = _r0 + _r0;
        double _t0 = _r1 * _r1;
        double _t2 = _r2 * _r3;
        double _t3 = _r1 * _r3;
        double _t4 = _r0 * _r0;
        double _t5 = _r1 * _r2;
        double _t6 = java.lang.Math.fma(-2.0, _r2 * _r2, 1.0);
        toMatrix_s20bb8ca5_c0_fma(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix_s20bb8ca5_c1_fma(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix_s20bb8ca5_c2_fma(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix_s20bb8ca5_c3_fma(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.dZ;
        double _r5 = this.dY;
        double _r6 = this.dX;
        double _r7 = this.dW;
        double _sp0 = _r0 + _r0;
        double _t0 = _r1 * _r1;
        double _t2 = _r2 * _r3;
        double _t3 = _r1 * _r3;
        double _t4 = _r0 * _r0;
        double _t5 = _r1 * _r2;
        double _t6 = ((-2.0) * (_r2 * _r2) + (1.0));
        toMatrix_s20bb8ca5_c0_mulAdd(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix_s20bb8ca5_c1_mulAdd(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix_s20bb8ca5_c2_mulAdd(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix_s20bb8ca5_c3_mulAdd(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = Math.fma(-2.0, this.rZ * this.rZ, 1.0);
        d.m00 = Math.fma(-2.0, _t0, _t6);
        d.m10 = 2.0 * Math.fma(this.rX, this.rY, _t2);
        d.m20 = Math.fma(-2.0, _t3, _sp0 * this.rZ);
        d.m01 = Math.fma(-2.0, _t2, _sp0 * this.rY);
        d.m11 = Math.fma(-2.0, _t4, _t6);
        d.m21 = 2.0 * Math.fma(this.rX, this.rW, _t5);
        d.m02 = 2.0 * Math.fma(this.rX, this.rZ, _t3);
        d.m12 = Math.fma(-2.0, this.rX * this.rW, _t5 + _t5);
        d.m22 = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        d.properties = 0;
        return d;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_fma(Double3x4Impl _dst, double _t0, double _t6, double _r0, double _r1, double _t2, double _t3, double _sp0, double _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0, _t0, _t6);
        _dst.m10 = 2.0 * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0, _t3, _sp0 * _r2);
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_mulAdd(Double3x4Impl _dst, double _t0, double _t6, double _r0, double _r1, double _t2, double _t3, double _sp0, double _r2) {
        _dst.m00 = ((-2.0) * (_t0) + (_t6));
        _dst.m10 = 2.0 * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0) * (_t3) + (_sp0 * _r2));
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_fma(Double3x4Impl _dst, double _t2, double _sp0, double _r1, double _t4, double _t6, double _r0, double _r3, double _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0, _t4, _t6);
        _dst.m21 = 2.0 * java.lang.Math.fma(_r0, _r3, _t5);
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_mulAdd(Double3x4Impl _dst, double _t2, double _sp0, double _r1, double _t4, double _t6, double _r0, double _r3, double _t5) {
        _dst.m01 = ((-2.0) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0) * (_t4) + (_t6));
        _dst.m21 = 2.0 * ((_r0) * (_r3) + (_t5));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_fma(Double3x4Impl _dst, double _r0, double _r2, double _t3, double _r3, double _t5, double _t4, double _t0) {
        _dst.m02 = 2.0 * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_mulAdd(Double3x4Impl _dst, double _r0, double _r2, double _t3, double _r3, double _t5, double _t4, double _t0) {
        _dst.m02 = 2.0 * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0) * (_t4) + (((-2.0) * (_t0) + (1.0))));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c3_fma(Double3x4Impl _dst, double _r1, double _r4, double _r2, double _r5, double _r3, double _r6, double _r0, double _r7) {
        _dst.m03 = 2.0 * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0 * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0 * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c3_mulAdd(Double3x4Impl _dst, double _r1, double _r4, double _r2, double _r5, double _r3, double _r6, double _r0, double _r7) {
        _dst.m03 = 2.0 * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0 * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0 * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
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
        Double3x4Impl d = (Double3x4Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.dZ;
        double _r5 = this.dY;
        double _r6 = this.dX;
        double _r7 = this.dW;
        double _sp0 = _r0 + _r0;
        double _t0 = _r1 * _r1;
        double _t2 = _r2 * _r3;
        double _t3 = _r1 * _r3;
        double _t4 = _r0 * _r0;
        double _t5 = _r1 * _r2;
        double _t6 = java.lang.Math.fma(-2.0, _r2 * _r2, 1.0);
        toMatrix3x4_s38da5fc6_c0_fma(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix3x4_s38da5fc6_c1_fma(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix3x4_s38da5fc6_c2_fma(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix3x4_s38da5fc6_c3_fma(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        Double3x4Impl d = (Double3x4Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.dZ;
        double _r5 = this.dY;
        double _r6 = this.dX;
        double _r7 = this.dW;
        double _sp0 = _r0 + _r0;
        double _t0 = _r1 * _r1;
        double _t2 = _r2 * _r3;
        double _t3 = _r1 * _r3;
        double _t4 = _r0 * _r0;
        double _t5 = _r1 * _r2;
        double _t6 = ((-2.0) * (_r2 * _r2) + (1.0));
        toMatrix3x4_s38da5fc6_c0_mulAdd(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix3x4_s38da5fc6_c1_mulAdd(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix3x4_s38da5fc6_c2_mulAdd(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix3x4_s38da5fc6_c3_mulAdd(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        double _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_s51104727_1_fma(dirX, dirY, dirZ, (DoubleDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(_ct0)));
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleDualQuat dest) {
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
        double _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_s51104727_1_mulAdd(dirX, dirY, dirZ, (DoubleDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_1_fma(double dirX, double dirY, double dirZ, DoubleDualQuatImpl d, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45) {
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = java.lang.Math.fma(-_t37, _t45, 1.0);
        double _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0))));
        double _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        return lookAlong_s51104727_2_fma(dirZ, d, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50))), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_1_mulAdd(double dirX, double dirY, double dirZ, DoubleDualQuatImpl d, double _t1, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t37, double _t38, double _t39, double _t45) {
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = ((-_t37) * (_t45) + (1.0));
        double _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        double _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0))))))));
        double _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0))))))));
        double _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        return lookAlong_s51104727_2_mulAdd(dirZ, d, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_2_fma(double dirZ, DoubleDualQuatImpl d, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80, double _t81, double _sp1, double _sp4, double _t84, double _sp3) {
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
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = java.lang.Math.fma(_rd0, _t129, this.rW * _t126) + java.lang.Math.fma(_rd1, _t127, -(_rd2 * _t128));
        return lookAlong_s51104727_3_fma(d, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_2_mulAdd(double dirZ, DoubleDualQuatImpl d, double _t16, double _t17, double _t37, double _t45, double _t46, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80, double _t81, double _sp1, double _sp4, double _t84, double _sp3) {
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
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = ((_rd0) * (_t129) + (this.rW * _t126)) + ((_rd1) * (_t127) - (_rd2 * _t128));
        return lookAlong_s51104727_3_mulAdd(d, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_3_fma(DoubleDualQuatImpl d, double _t126, double _t127, double _t128, double _t129, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5) {
        d.rY = java.lang.Math.fma(_rd1, _t129, _rd2 * _t126) + java.lang.Math.fma(this.rW, _t128, -(_rd0 * _t127));
        d.rZ = java.lang.Math.fma(_rd0, _t128, this.rW * _t127) + java.lang.Math.fma(_rd2, _t129, -(_rd1 * _t126));
        d.rW = java.lang.Math.fma(this.rW, _t129, -(_rd0 * _t126)) - java.lang.Math.fma(_rd1, _t128, _rd2 * _t127);
        d.dX = java.lang.Math.fma(_rd3, _t129, this.dW * _t126) + java.lang.Math.fma(_rd4, _t127, -(_rd5 * _t128));
        d.dY = java.lang.Math.fma(_rd4, _t129, _rd5 * _t126) + java.lang.Math.fma(this.dW, _t128, -(_rd3 * _t127));
        d.dZ = java.lang.Math.fma(_rd3, _t128, this.dW * _t127) + java.lang.Math.fma(_rd5, _t129, -(_rd4 * _t126));
        d.dW = java.lang.Math.fma(this.dW, _t129, -(_rd3 * _t126)) - java.lang.Math.fma(_rd4, _t128, _rd5 * _t127);
        return d;
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s51104727_3_mulAdd(DoubleDualQuatImpl d, double _t126, double _t127, double _t128, double _t129, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5) {
        d.rY = ((_rd1) * (_t129) + (_rd2 * _t126)) + ((this.rW) * (_t128) - (_rd0 * _t127));
        d.rZ = ((_rd0) * (_t128) + (this.rW * _t127)) + ((_rd2) * (_t129) - (_rd1 * _t126));
        d.rW = ((this.rW) * (_t129) - (_rd0 * _t126)) - ((_rd1) * (_t128) + (_rd2 * _t127));
        d.dX = ((_rd3) * (_t129) + (this.dW * _t126)) + ((_rd4) * (_t127) - (_rd5 * _t128));
        d.dY = ((_rd4) * (_t129) + (_rd5 * _t126)) + ((this.dW) * (_t128) - (_rd3 * _t127));
        d.dZ = ((_rd3) * (_t128) + (this.dW * _t127)) + ((_rd5) * (_t129) - (_rd4 * _t126));
        d.dW = ((this.dW) * (_t129) - (_rd3 * _t126)) - ((_rd4) * (_t128) + (_rd5 * _t127));
        return d;
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
        double _t46 = java.lang.Math.fma(_t43, _t24, _t21);
        return lookAlong_degenerate_s41d16670_1_fma((DoubleDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
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
        double _t46 = ((_t43) * (_t24) + (_t21));
        return lookAlong_degenerate_s41d16670_1_mulAdd((DoubleDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_1_fma(DoubleDualQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        return lookAlong_degenerate_s41d16670_2_fma(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_1_mulAdd(DoubleDualQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        return lookAlong_degenerate_s41d16670_2_mulAdd(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_2_fma(DoubleDualQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s41d16670_3_fma(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_2_mulAdd(DoubleDualQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96, double _t98) {
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s41d16670_3_mulAdd(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_3_fma(DoubleDualQuatImpl d, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
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
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = java.lang.Math.fma(_rd0, _t151, this.rW * _t148) + java.lang.Math.fma(_rd1, _t149, -(_rd2 * _t150));
        d.rY = java.lang.Math.fma(_rd1, _t151, _rd2 * _t148) + java.lang.Math.fma(this.rW, _t150, -(_rd0 * _t149));
        return lookAlong_degenerate_s41d16670_4_fma(d, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_3_mulAdd(DoubleDualQuatImpl d, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
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
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = ((_rd0) * (_t151) + (this.rW * _t148)) + ((_rd1) * (_t149) - (_rd2 * _t150));
        d.rY = ((_rd1) * (_t151) + (_rd2 * _t148)) + ((this.rW) * (_t150) - (_rd0 * _t149));
        return lookAlong_degenerate_s41d16670_4_mulAdd(d, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_4_fma(DoubleDualQuatImpl d, double _t148, double _t149, double _t150, double _t151, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5) {
        d.rZ = java.lang.Math.fma(_rd0, _t150, this.rW * _t149) + java.lang.Math.fma(_rd2, _t151, -(_rd1 * _t148));
        d.rW = java.lang.Math.fma(this.rW, _t151, -(_rd0 * _t148)) - java.lang.Math.fma(_rd1, _t150, _rd2 * _t149);
        d.dX = java.lang.Math.fma(_rd3, _t151, this.dW * _t148) + java.lang.Math.fma(_rd4, _t149, -(_rd5 * _t150));
        d.dY = java.lang.Math.fma(_rd4, _t151, _rd5 * _t148) + java.lang.Math.fma(this.dW, _t150, -(_rd3 * _t149));
        d.dZ = java.lang.Math.fma(_rd3, _t150, this.dW * _t149) + java.lang.Math.fma(_rd5, _t151, -(_rd4 * _t148));
        d.dW = java.lang.Math.fma(this.dW, _t151, -(_rd3 * _t148)) - java.lang.Math.fma(_rd4, _t150, _rd5 * _t149);
        return d;
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s41d16670_4_mulAdd(DoubleDualQuatImpl d, double _t148, double _t149, double _t150, double _t151, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5) {
        d.rZ = ((_rd0) * (_t150) + (this.rW * _t149)) + ((_rd2) * (_t151) - (_rd1 * _t148));
        d.rW = ((this.rW) * (_t151) - (_rd0 * _t148)) - ((_rd1) * (_t150) + (_rd2 * _t149));
        d.dX = ((_rd3) * (_t151) + (this.dW * _t148)) + ((_rd4) * (_t149) - (_rd5 * _t150));
        d.dY = ((_rd4) * (_t151) + (_rd5 * _t148)) + ((this.dW) * (_t150) - (_rd3 * _t149));
        d.dZ = ((_rd3) * (_t150) + (this.dW * _t149)) + ((_rd5) * (_t151) - (_rd4 * _t148));
        d.dW = ((this.dW) * (_t151) - (_rd3 * _t148)) - ((_rd4) * (_t150) + (_rd5 * _t149));
        return d;
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        double _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        return makeRotationLookAlong_s309f29f5_1_fma(dirX, dirY, dirZ, this, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45);
    }

    /** {@code makeRotationLookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t28 = ((-dirY) * (_sp0) + (upY));
        double _t29 = ((-dirZ) * (_sp0) + (upZ));
        double _t30 = ((-dirX) * (_sp0) + (upX));
        double _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        double _t38 = ((dirY) * (_t30) - (dirX * _t28));
        double _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        double _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = dirZ * _t16;
        double _t18 = dirX * _t16;
        return makeRotationLookAlong_s309f29f5_1_mulAdd(dirX, dirY, dirZ, this, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_fma(double dirX, double dirY, double dirZ, DoubleDualQuatImpl d, double _t16, double _t37, double _t39, double _t45, double _t1, double _t17, double _t18, double _t19, double _t20, double _t22, double _t46, double _t47) {
        double _t48 = _t39 * _t45;
        double _t50 = java.lang.Math.fma(-_t37, _t45, 1.0);
        double _t63 = java.lang.Math.fma(_t17, _t46, -(_t18 * _t47));
        double _t64 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0))));
        double _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        return makeRotationLookAlong_s309f29f5_2(d, _t17, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), _t63, java.lang.Math.fma(dirY, _t16, _t64), java.lang.Math.fma(-dirY, _t16, _t64), java.lang.Math.max(_t63, _t17), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50))), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_1_mulAdd(double dirX, double dirY, double dirZ, DoubleDualQuatImpl d, double _t16, double _t37, double _t39, double _t45, double _t1, double _t17, double _t18, double _t19, double _t20, double _t22, double _t46, double _t47) {
        double _t48 = _t39 * _t45;
        double _t50 = ((-_t37) * (_t45) + (1.0));
        double _t63 = ((_t17) * (_t46) - (_t18 * _t47));
        double _t64 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        double _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0))))))));
        double _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0))))))));
        double _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        return makeRotationLookAlong_s309f29f5_2(d, _t17, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), _t63, ((dirY) * (_t16) + (_t64)), ((-dirY) * (_t16) + (_t64)), java.lang.Math.max(_t63, _t17), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), ((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t80, _t81, ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_s309f29f5_2(DoubleDualQuatImpl d, double _t17, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t71, double _t74, double _t75, double _t77, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t82));
        d.rX = _t77 > 0.0 ? _sp1 * _t70 : _t46 > _t71 ? 0.5 * java.lang.Math.sqrt(_t80) : _t63 > _t17 ? _sp2 * _t74 : _sp3 * _t51;
        d.rY = _t77 > 0.0 ? _sp1 * _t56 : _t46 > _t71 ? _sp4 * _t74 : _t63 > _t17 ? 0.5 * java.lang.Math.sqrt(_t82) : _sp3 * _t69;
        d.rZ = _t77 > 0.0 ? _sp1 * _t75 : _t46 > _t71 ? _sp4 * _t51 : _t63 > _t17 ? _sp2 * _t69 : 0.5 * java.lang.Math.sqrt(_t81);
        d.rW = _t77 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t78) : _t46 > _t71 ? _sp4 * _t70 : _t63 > _t17 ? _sp2 * _t56 : _sp3 * _t75;
        d.dX = 0.0;
        d.dY = 0.0;
        d.dZ = 0.0;
        d.dW = 0.0;
        return d;
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
        return makeRotationLookAlong_degenerate_se3802ea_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
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
        return makeRotationLookAlong_degenerate_se3802ea_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_fma(DoubleDualQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        double _t87 = java.lang.Math.fma(_t69, _t24, -(_t68 * _t25));
        return makeRotationLookAlong_degenerate_se3802ea_2_fma(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), _t87, java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.max(_t87, _t24), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_1_mulAdd(DoubleDualQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
        double _t87 = ((_t69) * (_t24) - (_t68 * _t25));
        return makeRotationLookAlong_degenerate_se3802ea_2_mulAdd(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), _t87, ((_t68) * (_t26) - (_t72 * _t24)), java.lang.Math.max(_t87, _t24), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_fma(DoubleDualQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t93, double _t95, double _t96, double _t97) {
        double _t98 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31)));
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t98));
        double _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t101));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _t106 = java.lang.Math.fma(_t66, _t64, _t91);
        d.rX = _t97 > 0.0 ? _sp0 * _t96 : _t69 > _t93 ? 0.5 * java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_se3802ea_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_2_mulAdd(DoubleDualQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t93, double _t95, double _t96, double _t97) {
        double _t98 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31))))));
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t98));
        double _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t101));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _t106 = ((_t66) * (_t64) + (_t91));
        d.rX = _t97 > 0.0 ? _sp0 * _t96 : _t69 > _t93 ? 0.5 * java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_se3802ea_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat makeRotationLookAlong_degenerate_se3802ea_3(DoubleDualQuatImpl d, double _t24, double _t69, double _t73, double _t76, double _t87, double _t93, double _t95, double _t96, double _t97, double _t98, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
        d.rY = _t97 > 0.0 ? _sp0 * _t76 : _t69 > _t93 ? _sp3 * _t106 : _t87 > _t24 ? 0.5 * java.lang.Math.sqrt(_t101) : _sp2 * _t95;
        d.rZ = _t97 > 0.0 ? _sp0 * _t107 : _t69 > _t93 ? _sp3 * _t73 : _t87 > _t24 ? _sp1 * _t95 : 0.5 * java.lang.Math.sqrt(_t102);
        d.rW = _t97 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t98) : _t69 > _t93 ? _sp3 * _t96 : _t87 > _t24 ? _sp1 * _t76 : _sp2 * _t107;
        d.dX = 0.0;
        d.dY = 0.0;
        d.dZ = 0.0;
        d.dW = 0.0;
        return d;
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.rX = _t1;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t9, _t7, _t12 * _t5);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.rX = 0.0;
        this.rY = _t1;
        this.rZ = 0.0;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t9, _t6, _t12 * _t5);
        this.rY = Math.fma(_t10, _t6, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t6, -(_t10 * _t5));
        this.rW = Math.fma(_t12, _t6, -(_t9 * _t5));
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t9, _t7, _t12 * _t5);
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        this.rX = Math.fma(_t12, _t5, -(_t9 * _t8));
        this.rY = Math.fma(_t10, _t8, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t8, -(_t10 * _t5));
        this.rW = Math.fma(_t9, _t5, _t12 * _t8);
        this.dX = 0.0;
        this.dY = 0.0;
        this.dZ = 0.0;
        this.dW = 0.0;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationY, _rd2, -(rotationZ * _rd1));
        d.rY = Math.fma(rotationY, this.rW, rotationZ * _rd0) + Math.fma(rotationW, _rd1, -(rotationX * _rd2));
        d.rZ = Math.fma(rotationX, _rd1, rotationW * _rd2) + Math.fma(rotationZ, this.rW, -(rotationY * _rd0));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        d.dX = Math.fma(rotationX, this.dW, rotationW * _rd3) + Math.fma(rotationY, _rd5, -(rotationZ * _rd4));
        d.dY = Math.fma(rotationY, this.dW, rotationZ * _rd3) + Math.fma(rotationW, _rd4, -(rotationX * _rd5));
        d.dZ = Math.fma(rotationX, _rd4, rotationW * _rd5) + Math.fma(rotationZ, this.dW, -(rotationY * _rd3));
        d.dW = Math.fma(rotationW, this.dW, -(rotationX * _rd3)) - Math.fma(rotationY, _rd4, rotationZ * _rd5);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.dX;
        double _rd4 = this.dY;
        double _rd5 = this.dZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        d.rY = Math.fma(rotationX, _rd2, rotationW * _rd1) + Math.fma(rotationY, this.rW, -(rotationZ * _rd0));
        d.rZ = Math.fma(rotationY, _rd0, rotationZ * this.rW) + Math.fma(rotationW, _rd2, -(rotationX * _rd1));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        d.dX = Math.fma(rotationX, this.dW, rotationW * _rd3) + Math.fma(rotationZ, _rd4, -(rotationY * _rd5));
        d.dY = Math.fma(rotationX, _rd5, rotationW * _rd4) + Math.fma(rotationY, this.dW, -(rotationZ * _rd3));
        d.dZ = Math.fma(rotationY, _rd3, rotationZ * this.dW) + Math.fma(rotationW, _rd5, -(rotationX * _rd4));
        d.dW = Math.fma(rotationW, this.dW, -(rotationX * _rd3)) - Math.fma(rotationY, _rd4, rotationZ * _rd5);
        return d;
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
     * Private store group 1 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s42f2628b_c1_fma(DoubleDualQuatImpl _dst, double _r4, double _t5, double _r5, double _t2, double _r6, double _t3, double _r7, double _t4) {
        _dst.dX = java.lang.Math.fma(_r4, _t5, _r5 * _t2) + java.lang.Math.fma(_r6, _t3, -(_r7 * _t4));
        _dst.dY = java.lang.Math.fma(_r6, _t5, _r7 * _t2) + java.lang.Math.fma(_r5, _t4, -(_r4 * _t3));
        _dst.dZ = java.lang.Math.fma(_r4, _t4, _r5 * _t3) + java.lang.Math.fma(_r7, _t5, -(_r6 * _t2));
        _dst.dW = java.lang.Math.fma(_r5, _t5, -(_r4 * _t2)) - java.lang.Math.fma(_r6, _t4, _r7 * _t3);
    }

    /**
     * Private store group 1 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s42f2628b_c1_mulAdd(DoubleDualQuatImpl _dst, double _r4, double _t5, double _r5, double _t2, double _r6, double _t3, double _r7, double _t4) {
        _dst.dX = ((_r4) * (_t5) + (_r5 * _t2)) + ((_r6) * (_t3) - (_r7 * _t4));
        _dst.dY = ((_r6) * (_t5) + (_r7 * _t2)) + ((_r5) * (_t4) - (_r4 * _t3));
        _dst.dZ = ((_r4) * (_t4) + (_r5 * _t3)) + ((_r7) * (_t5) - (_r6 * _t2));
        _dst.dW = ((_r5) * (_t5) - (_r4 * _t2)) - ((_r6) * (_t4) + (_r7 * _t3));
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        preMul_s15ca02b0_c0_fma(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4);
        rotateAxis_s42f2628b_c1_fma(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4);
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        preMul_s15ca02b0_c0_mulAdd(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4);
        rotateAxis_s42f2628b_c1_mulAdd(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.dX;
        double _rd3 = this.dY;
        d.rX = Math.fma(_rd0, _t2, this.rW * _t1);
        d.rY = Math.fma(_rd1, _t2, this.rZ * _t1);
        d.rZ = Math.fma(this.rZ, _t2, -(_rd1 * _t1));
        d.rW = Math.fma(this.rW, _t2, -(_rd0 * _t1));
        d.dX = Math.fma(_rd2, _t2, this.dW * _t1);
        d.dY = Math.fma(_rd3, _t2, this.dZ * _t1);
        d.dZ = Math.fma(this.dZ, _t2, -(_rd3 * _t1));
        d.dW = Math.fma(this.dW, _t2, -(_rd2 * _t1));
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s12e02545_tail_fma(DoubleDualQuatImpl _dst, double _t11, double _t8, double _t10, double _t5, double _r0, double _t21, double _r1, double _t19, double _r2, double _t20, double _r3, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        preMul_s15ca02b0_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s42f2628b_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s12e02545_tail_mulAdd(DoubleDualQuatImpl _dst, double _t11, double _t8, double _t10, double _t5, double _r0, double _t21, double _r1, double _t19, double _r2, double _t20, double _r3, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        preMul_s15ca02b0_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s42f2628b_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateXYZ_s12e02545_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s12e02545_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, _r4, _r5, _r6, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateXYZ_s12e02545_tail_fma(d, _t12, _t5, _t9, _t8, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s12e02545_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, _r4, _r5, _r6, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.dX;
        double _rd3 = this.dY;
        d.rX = Math.fma(_rd0, _t2, -(this.rZ * _t1));
        d.rY = Math.fma(_rd1, _t2, this.rW * _t1);
        d.rZ = Math.fma(_rd0, _t1, this.rZ * _t2);
        d.rW = Math.fma(this.rW, _t2, -(_rd1 * _t1));
        d.dX = Math.fma(_rd2, _t2, -(this.dZ * _t1));
        d.dY = Math.fma(_rd3, _t2, this.dW * _t1);
        d.dZ = Math.fma(_rd2, _t1, this.dZ * _t2);
        d.dW = Math.fma(this.dW, _t2, -(_rd3 * _t1));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateXYZ_s12e02545_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s12e02545_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, _r4, _r5, _r6, _r7);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s5cfbc2e3_tail_fma(DoubleDualQuatImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _t21, double _r1, double _t19, double _r2, double _r3, double _t20, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s15ca02b0_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s42f2628b_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s5cfbc2e3_tail_mulAdd(DoubleDualQuatImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _t21, double _r1, double _t19, double _r2, double _r3, double _t20, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s15ca02b0_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s42f2628b_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateYZX_s5cfbc2e3_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s5cfbc2e3_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = this.rX;
        double _rd1 = this.rZ;
        double _rd2 = this.dX;
        double _rd3 = this.dZ;
        d.rX = Math.fma(_rd0, _t2, this.rY * _t1);
        d.rY = Math.fma(this.rY, _t2, -(_rd0 * _t1));
        d.rZ = Math.fma(_rd1, _t2, this.rW * _t1);
        d.rW = Math.fma(this.rW, _t2, -(_rd1 * _t1));
        d.dX = Math.fma(_rd2, _t2, this.dY * _t1);
        d.dY = Math.fma(this.dY, _t2, -(_rd2 * _t1));
        d.dZ = Math.fma(_rd3, _t2, this.dW * _t1);
        d.dW = Math.fma(this.dW, _t2, -(_rd3 * _t1));
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s396b1067_tail_fma(DoubleDualQuatImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _t21, double _r1, double _r2, double _t19, double _r3, double _t20, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s15ca02b0_c0_fma(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s42f2628b_c1_fma(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s396b1067_tail_mulAdd(DoubleDualQuatImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _t21, double _r1, double _r2, double _t19, double _r3, double _t20, double _r4, double _r5, double _r6, double _r7) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s15ca02b0_c0_mulAdd(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s42f2628b_c1_mulAdd(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateZXY_s396b1067_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r4, _r5, _r6, _r7); else rotateZXY_s396b1067_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)), _r4, _r5, _r6, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rW;
        double _r2 = this.rY;
        double _r3 = this.rZ;
        double _r4 = this.dX;
        double _r5 = this.dW;
        double _r6 = this.dY;
        double _r7 = this.dZ;
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
        if (Math.useFma()) rotateYZX_s5cfbc2e3_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s5cfbc2e3_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -this.rZ;
        double _t1 = -this.rX;
        double _t2 = -this.rY;
        double _t3 = 0.5 * translation.z();
        double _t4 = 0.5 * translation.y();
        double _t5 = 0.5 * translation.x();
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = Math.fma(_rd1, _t3, Math.fma(_t0, _t4, Math.fma(_rd3, _t5, this.dX)));
        d.dY = Math.fma(_rd3, _t4, Math.fma(_t1, _t3, Math.fma(_rd2, _t5, this.dY)));
        d.dZ = Math.fma(_rd0, _t4, Math.fma(_rd3, _t3, Math.fma(_t2, _t5, this.dZ)));
        d.dW = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, this.dW)));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -this.rZ;
        double _t1 = -this.rX;
        double _t2 = -this.rY;
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        double _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = Math.fma(_rd1, _t3, Math.fma(_t0, _t4, Math.fma(_rd3, _t5, this.dX)));
        d.dY = Math.fma(_rd3, _t4, Math.fma(_t1, _t3, Math.fma(_rd2, _t5, this.dY)));
        d.dZ = Math.fma(_rd0, _t4, Math.fma(_rd3, _t3, Math.fma(_t2, _t5, this.dZ)));
        d.dW = Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, this.dW)));
        return d;
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_fma(d, _r1, 2.0 * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0 * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0 * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_mulAdd(d, _r1, 2.0 * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0 * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0 * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        }
    }

    /** Private store group 0 of {@code transform}: computes and stores it; reached only through it. */
    private void transform_s3d3f3e36_c0_fma(Double3Impl _dst, double _r1, double _t9, double _r2, double _t10, double _r3, double _t11, double _r4, double _r5, double _r6, double _r0, double _r7, double pX, double pY, double pZ) {
        _dst.x = java.lang.Math.fma(_r1, _t9, java.lang.Math.fma(-_r2, _t10, java.lang.Math.fma(_r3, _t11, java.lang.Math.fma(2.0, java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)), pX))));
        _dst.y = java.lang.Math.fma(_r2, _t11, java.lang.Math.fma(-_r0, _t9, java.lang.Math.fma(_r3, _t10, java.lang.Math.fma(2.0, java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)), pY))));
        _dst.z = java.lang.Math.fma(_r0, _t10, java.lang.Math.fma(-_r1, _t11, java.lang.Math.fma(_r3, _t9, java.lang.Math.fma(2.0, java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)), pZ))));
    }

    /** Private store group 0 of {@code transform}: computes and stores it; reached only through it. */
    private void transform_s3d3f3e36_c0_mulAdd(Double3Impl _dst, double _r1, double _t9, double _r2, double _t10, double _r3, double _t11, double _r4, double _r5, double _r6, double _r0, double _r7, double pX, double pY, double pZ) {
        _dst.x = ((_r1) * (_t9) + (((-_r2) * (_t10) + (((_r3) * (_t11) + (((2.0) * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7))) + (pX))))))));
        _dst.y = ((_r2) * (_t11) + (((-_r0) * (_t9) + (((_r3) * (_t10) + (((2.0) * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7))) + (pY))))))));
        _dst.z = ((_r0) * (_t10) + (((-_r1) * (_t11) + (((_r3) * (_t9) + (((2.0) * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7))) + (pZ))))))));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_fma(d, _r1, 2.0 * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0 * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0 * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_mulAdd(d, _r1, 2.0 * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0 * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0 * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        }
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY)));
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY)));
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.dY;
        double _r2 = this.rY;
        double _r3 = this.dX;
        double _r4 = this.rW;
        double _r5 = this.dZ;
        double _r6 = this.rZ;
        double _r7 = this.dW;
        double _t21 = Math.fma(-2.0, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), p.z());
        double _t22 = Math.fma(-2.0, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), p.x());
        double _t23 = Math.fma(-2.0, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), p.y());
        if (Math.useFma()) transformInverse_s3d3f3e36_tail_fma(d, _r6, _t23, _r2, _t21, 2.0 * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), 2.0 * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, _t22, _r0); else transformInverse_s3d3f3e36_tail_mulAdd(d, _r6, _t23, _r2, _t21, 2.0 * ((_r0) * (_t21) - (_r6 * _t22)), 2.0 * ((_r2) * (_t22) - (_r0 * _t23)), _r4, _t22, _r0);
        return d;
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s3d3f3e36_c0_fma(Double3Impl _dst, double _r6, double _t33, double _r2, double _t34, double _r4, double _t35, double _t22, double _r0, double _t23, double _t21) {
        _dst.x = java.lang.Math.fma(_r6, _t33, java.lang.Math.fma(-_r2, _t34, java.lang.Math.fma(_r4, _t35, _t22)));
        _dst.y = java.lang.Math.fma(_r0, _t34, java.lang.Math.fma(-_r6, _t35, java.lang.Math.fma(_r4, _t33, _t23)));
        _dst.z = java.lang.Math.fma(_r2, _t35, java.lang.Math.fma(-_r0, _t33, java.lang.Math.fma(_r4, _t34, _t21)));
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s3d3f3e36_c0_mulAdd(Double3Impl _dst, double _r6, double _t33, double _r2, double _t34, double _r4, double _t35, double _t22, double _r0, double _t23, double _t21) {
        _dst.x = ((_r6) * (_t33) + (((-_r2) * (_t34) + (((_r4) * (_t35) + (_t22))))));
        _dst.y = ((_r0) * (_t34) + (((-_r6) * (_t35) + (((_r4) * (_t33) + (_t23))))));
        _dst.z = ((_r2) * (_t35) + (((-_r0) * (_t33) + (((_r4) * (_t34) + (_t21))))));
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private void transformInverse_s3d3f3e36_tail_fma(Double3Impl _dst, double _r6, double _t23, double _r2, double _t21, double _t33, double _t34, double _r4, double _t22, double _r0) {
        transformInverse_s3d3f3e36_c0_fma(_dst, _r6, _t33, _r2, _t34, _r4, 2.0 * java.lang.Math.fma(_r6, _t23, -(_r2 * _t21)), _t22, _r0, _t23, _t21);
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private void transformInverse_s3d3f3e36_tail_mulAdd(Double3Impl _dst, double _r6, double _t23, double _r2, double _t21, double _t33, double _t34, double _r4, double _t22, double _r0) {
        transformInverse_s3d3f3e36_c0_mulAdd(_dst, _r6, _t33, _r2, _t34, _r4, 2.0 * ((_r6) * (_t23) - (_r2 * _t21)), _t22, _r0, _t23, _t21);
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
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.dY;
        double _r2 = this.rY;
        double _r3 = this.dX;
        double _r4 = this.rW;
        double _r5 = this.dZ;
        double _r6 = this.rZ;
        double _r7 = this.dW;
        double _t21 = Math.fma(-2.0, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        double _t22 = Math.fma(-2.0, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        double _t23 = Math.fma(-2.0, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s3d3f3e36_tail_fma(d, _r6, _t23, _r2, _t21, 2.0 * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), 2.0 * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, _t22, _r0); else transformInverse_s3d3f3e36_tail_mulAdd(d, _r6, _t23, _r2, _t21, 2.0 * ((_r0) * (_t21) - (_r6 * _t22)), 2.0 * ((_r2) * (_t22) - (_r0 * _t23)), _r4, _t22, _r0);
        return d;
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_fma(d, _r1, 2.0 * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0 * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0 * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _r0 = this.rX;
            double _r1 = this.rY;
            double _r2 = this.rZ;
            double _r3 = this.rW;
            double _r4 = this.dZ;
            double _r5 = this.dY;
            double _r6 = this.dX;
            double _r7 = this.dW;
            transform_s3d3f3e36_c0_mulAdd(d, _r1, 2.0 * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0 * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0 * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        }
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
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.rX;
        double _r1 = this.dY;
        double _r2 = this.rY;
        double _r3 = this.dX;
        double _r4 = this.rW;
        double _r5 = this.dZ;
        double _r6 = this.rZ;
        double _r7 = this.dW;
        double _t21 = Math.fma(-2.0, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        double _t22 = Math.fma(-2.0, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        double _t23 = Math.fma(-2.0, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s3d3f3e36_tail_fma(d, _r6, _t23, _r2, _t21, 2.0 * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), 2.0 * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, _t22, _r0); else transformInverse_s3d3f3e36_tail_mulAdd(d, _r6, _t23, _r2, _t21, 2.0 * ((_r0) * (_t21) - (_r6 * _t22)), 2.0 * ((_r2) * (_t22) - (_r0 * _t23)), _r4, _t22, _r0);
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }

    public double rX() { return this.rX; }
    public double rY() { return this.rY; }
    public double rZ() { return this.rZ; }
    public double rW() { return this.rW; }
    public double dX() { return this.dX; }
    public double dY() { return this.dY; }
    public double dZ() { return this.dZ; }
    public double dW() { return this.dW; }

    @Override public String toString() {
        return "DoubleDualQuat(" + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + dX() + ", " + dY() + ", " + dZ() + ", " + dW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleDualQuatImpl)) return false;
        DoubleDualQuatImpl o = (DoubleDualQuatImpl) obj;
        return Double.doubleToLongBits(rX) == Double.doubleToLongBits(o.rX)
            && Double.doubleToLongBits(rY) == Double.doubleToLongBits(o.rY)
            && Double.doubleToLongBits(rZ) == Double.doubleToLongBits(o.rZ)
            && Double.doubleToLongBits(rW) == Double.doubleToLongBits(o.rW)
            && Double.doubleToLongBits(dX) == Double.doubleToLongBits(o.dX)
            && Double.doubleToLongBits(dY) == Double.doubleToLongBits(o.dY)
            && Double.doubleToLongBits(dZ) == Double.doubleToLongBits(o.dZ)
            && Double.doubleToLongBits(dW) == Double.doubleToLongBits(o.dW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(rX) ^ (Double.doubleToLongBits(rX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rY) ^ (Double.doubleToLongBits(rY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rZ) ^ (Double.doubleToLongBits(rZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rW) ^ (Double.doubleToLongBits(rW) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(dX) ^ (Double.doubleToLongBits(dX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(dY) ^ (Double.doubleToLongBits(dY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(dZ) ^ (Double.doubleToLongBits(dZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(dW) ^ (Double.doubleToLongBits(dW) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(rX)
            && Double.isFinite(rY)
            && Double.isFinite(rZ)
            && Double.isFinite(rW)
            && Double.isFinite(dX)
            && Double.isFinite(dY)
            && Double.isFinite(dZ)
            && Double.isFinite(dW);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(rX)
            || Double.isNaN(rY)
            || Double.isNaN(rZ)
            || Double.isNaN(rW)
            || Double.isNaN(dX)
            || Double.isNaN(dY)
            || Double.isNaN(dZ)
            || Double.isNaN(dW);
    }

    @Override public boolean equalsEpsilon(DoubleDualQuatR other, double epsilon) {
        return java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon
            && java.lang.Math.abs(dX - other.dX()) <= epsilon
            && java.lang.Math.abs(dY - other.dY()) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ()) <= epsilon
            && java.lang.Math.abs(dW - other.dW()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.rX;
        dest[offset + 1] = this.rY;
        dest[offset + 2] = this.rZ;
        dest[offset + 3] = this.rW;
        dest[offset + 4] = this.dX;
        dest[offset + 5] = this.dY;
        dest[offset + 6] = this.dZ;
        dest[offset + 7] = this.dW;
        return dest;
    }
    public @Mutated DoubleDualQuat load(double[] src, int offset) {
        this.rX = src[offset];
        this.rY = src[offset + 1];
        this.rZ = src[offset + 2];
        this.rW = src[offset + 3];
        this.dX = src[offset + 4];
        this.dY = src[offset + 5];
        this.dZ = src[offset + 6];
        this.dW = src[offset + 7];
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

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.rX;
        dest[offset + 1] = (float) this.rY;
        dest[offset + 2] = (float) this.rZ;
        dest[offset + 3] = (float) this.rW;
        dest[offset + 4] = (float) this.dX;
        dest[offset + 5] = (float) this.dY;
        dest[offset + 6] = (float) this.dZ;
        dest[offset + 7] = (float) this.dW;
        return dest;
    }
    public @Mutated DoubleDualQuat load(float[] src, int offset) {
        this.rX = src[offset];
        this.rY = src[offset + 1];
        this.rZ = src[offset + 2];
        this.rW = src[offset + 3];
        this.dX = src[offset + 4];
        this.dY = src[offset + 5];
        this.dZ = src[offset + 6];
        this.dW = src[offset + 7];
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
