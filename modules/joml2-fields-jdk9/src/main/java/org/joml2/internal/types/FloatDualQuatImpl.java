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
 * Generated implementation of {@link FloatDualQuat} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatDualQuatImpl implements FloatDualQuat {

    public float rX;
    public float rY;
    public float rZ;
    public float rW;
    public float dX;
    public float dY;
    public float dZ;
    public float dW;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatDualQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatDualQuatBbOpsUnsafe()
                        : new FloatDualQuatBbOpsApi();
        static final FloatDualQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatDualQuatRawOpsUnsafe()
                        : new FloatDualQuatRawOpsApi();
    }

    public FloatDualQuatImpl() {
        rW = 1;
    }

    public FloatDualQuatImpl(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) {
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
        this.dW = dW;
    }

    public FloatDualQuatImpl(FloatDualQuatR src) {
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
    public FloatDualQuat add(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Add {@code other} to this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat add(FloatDualQuatR other, @Mutated DoubleDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public FloatDualQuat add(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) to this dual quaternion and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat add(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat mul(float scalar, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Multiply each component of this dual quaternion by {@code scalar} and store the result in
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
    public DoubleDualQuat mul(float scalar, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat negate(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Negate this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public FloatDualQuat sub(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Subtract {@code other} from this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sub(FloatDualQuatR other, @Mutated DoubleDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public FloatDualQuat sub(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Subtract ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) from this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat sub(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
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
    public @Mutated FloatDualQuat set(FloatDualQuatR v) {
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
        float vDX = v.dX();
        float vDY = v.dY();
        float vDZ = v.dZ();
        float vDW = v.dW();
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
    @Mutated public FloatDualQuat set(float vRX, float vRY, float vRZ, float vRW, float vDX, float vDY, float vDZ, float vDW) {
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
     * Convert this dual quaternion to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDouble(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        d.dW = this.dW;
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
    public @Mutated FloatDualQuat makeFromRigid(FloatRigidR r) {
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
    @Mutated public FloatDualQuat makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        if (Math.useFma()) {
            float _t0 = -rTZ;
            this.rX = rRX;
            this.rY = rRY;
            this.rZ = rRZ;
            this.rW = rRW;
            this.dX = 0.5f * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ));
            this.dY = 0.5f * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -(rTX * rRZ)));
            this.dZ = 0.5f * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -(rTY * rRX)));
            this.dW = 0.5f * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -(rTX * rRX)));
            return this;
        } else {
            float _t0 = -rTZ;
            this.rX = rRX;
            this.rY = rRY;
            this.rZ = rRZ;
            this.rW = rRW;
            this.dX = 0.5f * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ))));
            this.dY = 0.5f * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ))));
            this.dZ = 0.5f * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX))));
            this.dW = 0.5f * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX))));
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
    public @Mutated FloatDualQuat makeFromTransform(FloatTransformR t) {
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
    @Mutated public FloatDualQuat makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (Math.useFma()) {
            float _t0 = -tTZ;
            this.rX = tRX;
            this.rY = tRY;
            this.rZ = tRZ;
            this.rW = tRW;
            this.dX = 0.5f * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ));
            this.dY = 0.5f * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -(tTX * tRZ)));
            this.dZ = 0.5f * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -(tTY * tRX)));
            this.dW = 0.5f * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -(tTX * tRX)));
            return this;
        } else {
            float _t0 = -tTZ;
            this.rX = tRX;
            this.rY = tRY;
            this.rZ = tRZ;
            this.rW = tRW;
            this.dX = 0.5f * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ))));
            this.dY = 0.5f * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ))));
            this.dZ = 0.5f * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX))));
            this.dW = 0.5f * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX))));
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
    public FloatRigid toRigid(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        d.tX = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
    }


    /**
     * Convert this unit dual quaternion to a rigid transform (an exact conversion - both represent
     * rotation plus translation) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
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
    public FloatTransform toTransform(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0f;
        d.sY = 1.0f;
        d.sZ = 1.0f;
        return d;
    }


    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.tY = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        d.tZ = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0f;
        d.sY = 1.0f;
        d.sZ = 1.0f;
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
    public boolean isUnit(float epsilon) {
        if (Math.useFma()) {
            return java.lang.Math.abs(java.lang.Math.fma(this.rX, this.rX, java.lang.Math.fma(this.rY, this.rY, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rW, this.rW, -1.0f))))) <= epsilon;
        } else {
            return java.lang.Math.abs(((this.rX) * (this.rX) + (((this.rY) * (this.rY) + (((this.rZ) * (this.rZ) + (((this.rW) * (this.rW) - (1.0f))))))))) <= epsilon;
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
    public @Mutated FloatDualQuat makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axis.x() * _t2;
        float _t4 = axis.y() * _t2;
        float _t5 = axis.z() * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        this.rX = _t3;
        this.rY = _t4;
        this.rZ = _t5;
        this.rW = _t6;
        this.dX = 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        this.dY = 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        this.dZ = 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        this.dW = 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
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
    @Mutated public FloatDualQuat makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisY * _t2;
        float _t5 = axisZ * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        this.rX = _t3;
        this.rY = _t4;
        this.rZ = _t5;
        this.rW = _t6;
        this.dX = 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        this.dY = 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        this.dZ = 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        this.dW = 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
        return this;
    }


    /**
     * Set this dual quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatDualQuat makeIdentity() {
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    public @Mutated FloatDualQuat makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
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
    @Mutated public FloatDualQuat makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        if (Math.useFma()) {
            float _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            this.dY = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            this.dZ = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            this.dW = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            float _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            this.dY = 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            this.dZ = 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            this.dW = 0.5f * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
    @Mutated public FloatDualQuat makeZero() {
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 0.0f;
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    public @Mutated FloatDualQuat set(FloatQuatR rotation) {
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        this.rX = rotation.x();
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    public @Mutated FloatDualQuat set(FloatQuatR rotation, Float3R translation) {
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
    @Mutated public FloatDualQuat set(float rotationX, float rotationY, float rotationZ, float rotationW, float translationX, float translationY, float translationZ) {
        if (Math.useFma()) {
            float _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            this.dY = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            this.dZ = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            this.dW = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            float _t0 = -rotationY;
            this.rX = rotationX;
            this.rY = rotationY;
            this.rZ = rotationZ;
            this.rW = rotationW;
            this.dX = 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            this.dY = 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            this.dZ = 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            this.dW = 0.5f * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
    public @Mutated FloatDualQuat set(Float3R translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.dX = 0.5f * translationX;
        this.dY = 0.5f * translationY;
        this.dZ = 0.5f * translationZ;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat set(float translationX, float translationY, float translationZ) {
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.dX = 0.5f * translationX;
        this.dY = 0.5f * translationY;
        this.dZ = 0.5f * translationZ;
        this.dW = 0.0f;
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
    public FloatDualQuat dlb(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        return dlb(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), t, dest);
    }


    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat dlb(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest) {
        return dlb(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), t, dest);
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s4b3cdb8d_c1_fma(FloatDualQuatImpl _dst, float t, float otherDX, float _t9, float _r4, float _t23, float otherDY, float _r5, float otherDZ, float _r6, float otherDW, float _r7) {
        _dst.dX = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -_r4), _r4) * _t23;
        _dst.dY = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -_r5), _r5) * _t23;
        _dst.dZ = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -_r6), _r6) * _t23;
        _dst.dW = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -_r7), _r7) * _t23;
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s4b3cdb8d_c1_mulAdd(FloatDualQuatImpl _dst, float t, float otherDX, float _t9, float _r4, float _t23, float otherDY, float _r5, float otherDZ, float _r6, float otherDW, float _r7) {
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
    public FloatDualQuat dlb(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _r6 = this.dZ;
        float _r7 = this.dW;
        float _t9 = Math.fma(otherRX, _r0, otherRY * _r1) + Math.fma(otherRZ, _r2, otherRW * _r3) < 0.0f ? -1.0f : 1.0f;
        float _t14 = Math.fma(t, Math.fma(otherRX, _t9, -_r0), _r0);
        float _t15 = Math.fma(t, Math.fma(otherRY, _t9, -_r1), _r1);
        float _t16 = Math.fma(t, Math.fma(otherRZ, _t9, -_r2), _r2);
        float _t17 = Math.fma(t, Math.fma(otherRW, _t9, -_r3), _r3);
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t14, _t14, _t15 * _t15) + Math.fma(_t16, _t16, _t17 * _t17)));
        d.rX = _t14 * _t23;
        d.rY = _t15 * _t23;
        d.rZ = _t16 * _t23;
        d.rW = _t17 * _t23;
        if (Math.useFma()) dlb_s4b3cdb8d_c1_fma(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7); else dlb_s4b3cdb8d_c1_mulAdd(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7);
        return d;
    }

    /** Private store group 0 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s1639cfc4_c0(DoubleDualQuatImpl _dst, float _t14, float _t23, float _t15, float _t16, float _t17) {
        _dst.rX = _t14 * _t23;
        _dst.rY = _t15 * _t23;
        _dst.rZ = _t16 * _t23;
        _dst.rW = _t17 * _t23;
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s1639cfc4_c1_fma(DoubleDualQuatImpl _dst, float t, float otherDX, float _t9, float _r4, float _t23, float otherDY, float _r5, float otherDZ, float _r6, float otherDW, float _r7) {
        _dst.dX = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -_r4), _r4) * _t23;
        _dst.dY = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -_r5), _r5) * _t23;
        _dst.dZ = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -_r6), _r6) * _t23;
        _dst.dW = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -_r7), _r7) * _t23;
    }

    /** Private store group 1 of {@code dlb}: computes and stores it; reached only through it. */
    private void dlb_s1639cfc4_c1_mulAdd(DoubleDualQuatImpl _dst, float t, float otherDX, float _t9, float _r4, float _t23, float otherDY, float _r5, float otherDZ, float _r6, float otherDW, float _r7) {
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat dlb(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _r6 = this.dZ;
        float _r7 = this.dW;
        float _t9 = Math.fma(otherRX, _r0, otherRY * _r1) + Math.fma(otherRZ, _r2, otherRW * _r3) < 0.0f ? -1.0f : 1.0f;
        float _t14 = Math.fma(t, Math.fma(otherRX, _t9, -_r0), _r0);
        float _t15 = Math.fma(t, Math.fma(otherRY, _t9, -_r1), _r1);
        float _t16 = Math.fma(t, Math.fma(otherRZ, _t9, -_r2), _r2);
        float _t17 = Math.fma(t, Math.fma(otherRW, _t9, -_r3), _r3);
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t14, _t14, _t15 * _t15) + Math.fma(_t16, _t16, _t17 * _t17)));
        dlb_s1639cfc4_c0(d, _t14, _t23, _t15, _t16, _t17);
        if (Math.useFma()) dlb_s1639cfc4_c1_fma(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7); else dlb_s1639cfc4_c1_mulAdd(d, t, otherDX, _t9, _r4, _t23, otherDY, _r5, otherDZ, _r6, otherDW, _r7);
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
    public FloatDualQuat lerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lerp(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public FloatDualQuat lerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat lerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat sclerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }


    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sclerp(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public FloatDualQuat sclerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code sclerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float _r0 = this.rZ;
        float _r1 = this.dZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.rW;
        float _t0 = -_r0;
        float _t8 = java.lang.Math.fma(otherRX, _r2, otherRY * _r3) + java.lang.Math.fma(otherRZ, _r0, otherRW * _r4) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = java.lang.Math.fma(_r2, _t9, _r4 * _t10);
        float _t77 = java.lang.Math.fma(_t0, _t11, -(_r3 * _t12));
        return sclerp_sd6508ecf_1_fma(t, (FloatDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(_r3, _t9, -(_r0 * _t10)) + java.lang.Math.fma(_r4, _t11, -(_r2 * _t12)), java.lang.Math.fma(_r2, _t11, _r4 * _t12) + java.lang.Math.fma(_t0, _t9, -(_r3 * _t10)), java.lang.Math.fma(_r0, _t12, -(_r3 * _t11)) + java.lang.Math.fma(_r4, _t9, -(_r2 * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float _r0 = this.rZ;
        float _r1 = this.dZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.rW;
        float _t0 = -_r0;
        float _t8 = ((otherRX) * (_r2) + (otherRY * _r3)) + ((otherRZ) * (_r0) + (otherRW * _r4)) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = ((_r2) * (_t9) + (_r4 * _t10));
        float _t77 = ((_t0) * (_t11) - (_r3 * _t12));
        return sclerp_sd6508ecf_1_mulAdd(t, (FloatDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((_r3) * (_t9) - (_r0 * _t10)) + ((_r4) * (_t11) - (_r2 * _t12)), ((_r2) * (_t11) + (_r4 * _t12)) + ((_t0) * (_t9) - (_r3 * _t10)), ((_r0) * (_t12) - (_r3 * _t11)) + ((_r4) * (_t9) - (_r2 * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_fma(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_sd6508ecf_2_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(_r2, _t13, _r4 * _t14) + java.lang.Math.fma(_r3, _t15, _r0 * _t16) + (java.lang.Math.fma(_r5, _t9, _r6 * _t10) + java.lang.Math.fma(_r7, _t12, _r1 * _t11)), java.lang.Math.fma(_r0, _t15, -(_r3 * _t16)) + java.lang.Math.fma(_r4, _t13, -(_r2 * _t14)) + (java.lang.Math.fma(_r1, _t12, -(_r7 * _t11)) + java.lang.Math.fma(_r6, _t9, -(_r5 * _t10))), java.lang.Math.fma(_r3, _t13, -(_r0 * _t14)) + java.lang.Math.fma(_r4, _t16, -(_r2 * _t15)) + (java.lang.Math.fma(_r7, _t9, -(_r1 * _t10)) + java.lang.Math.fma(_r6, _t11, -(_r5 * _t12))), java.lang.Math.fma(_r2, _t16, _r4 * _t15) + java.lang.Math.fma(_t0, _t13, -(_r3 * _t14)) + (java.lang.Math.fma(_r5, _t11, _r6 * _t12) + java.lang.Math.fma(_t2, _t9, -(_r7 * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_mulAdd(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_sd6508ecf_2_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((_r2) * (_t13) + (_r4 * _t14)) + ((_r3) * (_t15) + (_r0 * _t16)) + (((_r5) * (_t9) + (_r6 * _t10)) + ((_r7) * (_t12) + (_r1 * _t11))), ((_r0) * (_t15) - (_r3 * _t16)) + ((_r4) * (_t13) - (_r2 * _t14)) + (((_r1) * (_t12) - (_r7 * _t11)) + ((_r6) * (_t9) - (_r5 * _t10))), ((_r3) * (_t13) - (_r0 * _t14)) + ((_r4) * (_t16) - (_r2 * _t15)) + (((_r7) * (_t9) - (_r1 * _t10)) + ((_r6) * (_t11) - (_r5 * _t12))), ((_r2) * (_t16) + (_r4 * _t15)) + ((_t0) * (_t13) - (_r3 * _t14)) + (((_r5) * (_t11) + (_r6 * _t12)) + ((_t2) * (_t9) - (_r7 * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_fma(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
        float _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0f) {
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
        float _t122 = java.lang.Math.fma(_t106, _t106, java.lang.Math.fma(_t107, _t107, _t108 * _t108));
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        return sclerp_sd6508ecf_3_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_mulAdd(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
        float _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0f) {
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
        float _t122 = ((_t106) * (_t106) + (((_t107) * (_t107) + (_t108 * _t108))));
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        return sclerp_sd6508ecf_3_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_fma(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t106, float _t107, float _t108, float _t112, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t132, float _t133, float _t137) {
        float _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-12f) {
            _t142 = 1.0f;
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
        float _t146 = _t132 * _t137;
        float _t160, _t161, _t162;
        if (_t122 < 1.0E-12f) {
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
        return sclerp_sd6508ecf_4_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_mulAdd(float t, FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t106, float _t107, float _t108, float _t112, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t132, float _t133, float _t137) {
        float _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-12f) {
            _t142 = 1.0f;
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
        float _t146 = _t132 * _t137;
        float _t160, _t161, _t162;
        if (_t122 < 1.0E-12f) {
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
        return sclerp_sd6508ecf_4_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_fma(FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
        d.rZ = java.lang.Math.fma(_r2, _t145, _r4 * _t144) + java.lang.Math.fma(_r0, _t142, -(_r3 * _t143));
        d.rW = java.lang.Math.fma(_r4, _t142, -(_r2 * _t143)) - java.lang.Math.fma(_r3, _t145, _r0 * _t144);
        d.dX = java.lang.Math.fma(_r2, _t147, _r4 * _t160) + java.lang.Math.fma(_r3, _t161, -(_r0 * _t162)) + (java.lang.Math.fma(_r5, _t142, _r6 * _t143) + java.lang.Math.fma(_r7, _t144, -(_r1 * _t145)));
        d.dY = java.lang.Math.fma(_r3, _t147, _r0 * _t160) + java.lang.Math.fma(_r4, _t162, -(_r2 * _t161)) + (java.lang.Math.fma(_r7, _t142, _r1 * _t143) + java.lang.Math.fma(_r6, _t145, -(_r5 * _t144)));
        d.dZ = java.lang.Math.fma(_r2, _t162, _r4 * _t161) + java.lang.Math.fma(_r0, _t147, -(_r3 * _t160)) + (java.lang.Math.fma(_r5, _t145, _r6 * _t144) + java.lang.Math.fma(_r1, _t142, -(_r7 * _t143)));
        d.dW = java.lang.Math.fma(_r4, _t147, -(_r2 * _t160)) + java.lang.Math.fma(_t0, _t161, -(_r3 * _t162)) + (java.lang.Math.fma(_r6, _t142, -(_r5 * _t143)) + java.lang.Math.fma(_t2, _t144, -(_r7 * _t145)));
        return d;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_mulAdd(FloatDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
        d.rZ = ((_r2) * (_t145) + (_r4 * _t144)) + ((_r0) * (_t142) - (_r3 * _t143));
        d.rW = ((_r4) * (_t142) - (_r2 * _t143)) - ((_r3) * (_t145) + (_r0 * _t144));
        d.dX = ((_r2) * (_t147) + (_r4 * _t160)) + ((_r3) * (_t161) - (_r0 * _t162)) + (((_r5) * (_t142) + (_r6 * _t143)) + ((_r7) * (_t144) - (_r1 * _t145)));
        d.dY = ((_r3) * (_t147) + (_r0 * _t160)) + ((_r4) * (_t162) - (_r2 * _t161)) + (((_r7) * (_t142) + (_r1 * _t143)) + ((_r6) * (_t145) - (_r5 * _t144)));
        d.dZ = ((_r2) * (_t162) + (_r4 * _t161)) + ((_r0) * (_t147) - (_r3 * _t160)) + (((_r5) * (_t145) + (_r6 * _t144)) + ((_r1) * (_t142) - (_r7 * _t143)));
        d.dW = ((_r4) * (_t147) - (_r2 * _t160)) + ((_t0) * (_t161) - (_r3 * _t162)) + (((_r6) * (_t142) - (_r5 * _t143)) + ((_t2) * (_t144) - (_r7 * _t145)));
        return d;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat sclerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        if (Math.useFma()) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code sclerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float _r0 = this.rZ;
        float _r1 = this.dZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.rW;
        float _t0 = -_r0;
        float _t8 = java.lang.Math.fma(otherRX, _r2, otherRY * _r3) + java.lang.Math.fma(otherRZ, _r0, otherRW * _r4) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = java.lang.Math.fma(_r2, _t9, _r4 * _t10);
        float _t77 = java.lang.Math.fma(_t0, _t11, -(_r3 * _t12));
        return sclerp_se724e73a_1_fma(t, (DoubleDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(_r3, _t9, -(_r0 * _t10)) + java.lang.Math.fma(_r4, _t11, -(_r2 * _t12)), java.lang.Math.fma(_r2, _t11, _r4 * _t12) + java.lang.Math.fma(_t0, _t9, -(_r3 * _t10)), java.lang.Math.fma(_r0, _t12, -(_r3 * _t11)) + java.lang.Math.fma(_r4, _t9, -(_r2 * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float _r0 = this.rZ;
        float _r1 = this.dZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.rW;
        float _t0 = -_r0;
        float _t8 = ((otherRX) * (_r2) + (otherRY * _r3)) + ((otherRZ) * (_r0) + (otherRW * _r4)) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = ((_r2) * (_t9) + (_r4 * _t10));
        float _t77 = ((_t0) * (_t11) - (_r3 * _t12));
        return sclerp_se724e73a_1_mulAdd(t, (DoubleDualQuatImpl) dest, _r0, _r1, _r2, _r3, _r4, this.dX, this.dW, this.dY, _t0, -_r1, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((_r3) * (_t9) - (_r0 * _t10)) + ((_r4) * (_t11) - (_r2 * _t12)), ((_r2) * (_t11) + (_r4 * _t12)) + ((_t0) * (_t9) - (_r3 * _t10)), ((_r0) * (_t12) - (_r3 * _t11)) + ((_r4) * (_t9) - (_r2 * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_fma(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_se724e73a_2_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(_r2, _t13, _r4 * _t14) + java.lang.Math.fma(_r3, _t15, _r0 * _t16) + (java.lang.Math.fma(_r5, _t9, _r6 * _t10) + java.lang.Math.fma(_r7, _t12, _r1 * _t11)), java.lang.Math.fma(_r0, _t15, -(_r3 * _t16)) + java.lang.Math.fma(_r4, _t13, -(_r2 * _t14)) + (java.lang.Math.fma(_r1, _t12, -(_r7 * _t11)) + java.lang.Math.fma(_r6, _t9, -(_r5 * _t10))), java.lang.Math.fma(_r3, _t13, -(_r0 * _t14)) + java.lang.Math.fma(_r4, _t16, -(_r2 * _t15)) + (java.lang.Math.fma(_r7, _t9, -(_r1 * _t10)) + java.lang.Math.fma(_r6, _t11, -(_r5 * _t12))), java.lang.Math.fma(_r2, _t16, _r4 * _t15) + java.lang.Math.fma(_t0, _t13, -(_r3 * _t14)) + (java.lang.Math.fma(_r5, _t11, _r6 * _t12) + java.lang.Math.fma(_t2, _t9, -(_r7 * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_mulAdd(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_se724e73a_2_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((_r2) * (_t13) + (_r4 * _t14)) + ((_r3) * (_t15) + (_r0 * _t16)) + (((_r5) * (_t9) + (_r6 * _t10)) + ((_r7) * (_t12) + (_r1 * _t11))), ((_r0) * (_t15) - (_r3 * _t16)) + ((_r4) * (_t13) - (_r2 * _t14)) + (((_r1) * (_t12) - (_r7 * _t11)) + ((_r6) * (_t9) - (_r5 * _t10))), ((_r3) * (_t13) - (_r0 * _t14)) + ((_r4) * (_t16) - (_r2 * _t15)) + (((_r7) * (_t9) - (_r1 * _t10)) + ((_r6) * (_t11) - (_r5 * _t12))), ((_r2) * (_t16) + (_r4 * _t15)) + ((_t0) * (_t13) - (_r3 * _t14)) + (((_r5) * (_t11) + (_r6 * _t12)) + ((_t2) * (_t9) - (_r7 * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_fma(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
        float _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0f) {
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
        float _t122 = java.lang.Math.fma(_t106, _t106, java.lang.Math.fma(_t107, _t107, _t108 * _t108));
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        return sclerp_se724e73a_3_fma(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_mulAdd(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
        float _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0f) {
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
        float _t122 = ((_t106) * (_t106) + (((_t107) * (_t107) + (_t108 * _t108))));
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        return sclerp_se724e73a_3_mulAdd(t, d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t106, _t107, _t108, _t112, _t114, _t115, _t116, _t122, _t124, _t124 * _t108, _t124 * _t106, _t124 * _t107, _t130, t * _t131, _t131 * _t105, Math.cosFromSin(_t130, _t129));
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_fma(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t106, float _t107, float _t108, float _t112, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t132, float _t133, float _t137) {
        float _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-12f) {
            _t142 = 1.0f;
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
        float _t146 = _t132 * _t137;
        float _t160, _t161, _t162;
        if (_t122 < 1.0E-12f) {
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
        return sclerp_se724e73a_4_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_mulAdd(float t, DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t106, float _t107, float _t108, float _t112, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t132, float _t133, float _t137) {
        float _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-12f) {
            _t142 = 1.0f;
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
        float _t146 = _t132 * _t137;
        float _t160, _t161, _t162;
        if (_t122 < 1.0E-12f) {
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
        return sclerp_se724e73a_4_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_fma(DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
        d.rZ = java.lang.Math.fma(_r2, _t145, _r4 * _t144) + java.lang.Math.fma(_r0, _t142, -(_r3 * _t143));
        d.rW = java.lang.Math.fma(_r4, _t142, -(_r2 * _t143)) - java.lang.Math.fma(_r3, _t145, _r0 * _t144);
        d.dX = java.lang.Math.fma(_r2, _t147, _r4 * _t160) + java.lang.Math.fma(_r3, _t161, -(_r0 * _t162)) + (java.lang.Math.fma(_r5, _t142, _r6 * _t143) + java.lang.Math.fma(_r7, _t144, -(_r1 * _t145)));
        d.dY = java.lang.Math.fma(_r3, _t147, _r0 * _t160) + java.lang.Math.fma(_r4, _t162, -(_r2 * _t161)) + (java.lang.Math.fma(_r7, _t142, _r1 * _t143) + java.lang.Math.fma(_r6, _t145, -(_r5 * _t144)));
        d.dZ = java.lang.Math.fma(_r2, _t162, _r4 * _t161) + java.lang.Math.fma(_r0, _t147, -(_r3 * _t160)) + (java.lang.Math.fma(_r5, _t145, _r6 * _t144) + java.lang.Math.fma(_r1, _t142, -(_r7 * _t143)));
        d.dW = java.lang.Math.fma(_r4, _t147, -(_r2 * _t160)) + java.lang.Math.fma(_t0, _t161, -(_r3 * _t162)) + (java.lang.Math.fma(_r6, _t142, -(_r5 * _t143)) + java.lang.Math.fma(_t2, _t144, -(_r7 * _t145)));
        return d;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_mulAdd(DoubleDualQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
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
    public FloatDualQuat mul(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s447e38b9_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s447e38b9_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s447e38b9_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s447e38b9_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        }
    }


    /**
     * Multiply this dual quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat mul(FloatDualQuatR other, @Mutated DoubleDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s45241818_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s45241818_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s45241818_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s45241818_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        }
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s447e38b9_c0_fma(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRZ, float _r2, float otherRY, float _r3) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, otherRW * _r1) + java.lang.Math.fma(otherRZ, _r2, -(otherRY * _r3));
        _dst.rY = java.lang.Math.fma(otherRX, _r3, otherRW * _r2) + java.lang.Math.fma(otherRY, _r0, -(otherRZ * _r1));
        _dst.rZ = java.lang.Math.fma(otherRY, _r1, otherRZ * _r0) + java.lang.Math.fma(otherRW, _r3, -(otherRX * _r2));
        _dst.rW = java.lang.Math.fma(otherRW, _r0, -(otherRX * _r1)) - java.lang.Math.fma(otherRY, _r2, otherRZ * _r3);
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s447e38b9_c0_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRZ, float _r2, float otherRY, float _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRZ) * (_r2) - (otherRY * _r3));
        _dst.rY = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRY) * (_r0) - (otherRZ * _r1));
        _dst.rZ = ((otherRY) * (_r1) + (otherRZ * _r0)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r2) + (otherRZ * _r3));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s447e38b9_c1_fma(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRZ, float _r6, float otherRY, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDZ, float _r2, float otherDY, float _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDZ, _r2, -(otherDY * _r3)));
        _dst.dY = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRY, _r4, -(otherRZ * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDY, _r0, -(otherDZ * _r1)));
        _dst.dZ = java.lang.Math.fma(otherRY, _r5, otherRZ * _r4) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r1, otherDZ * _r0) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r7, -(otherRY * _r6)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r3, -(otherDY * _r2)));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s447e38b9_c1_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRZ, float _r6, float otherRY, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDZ, float _r2, float otherDY, float _r3) {
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
    public FloatDualQuat mul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s447e38b9_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s447e38b9_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s447e38b9_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s447e38b9_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        }
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s45241818_c0_fma(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRZ, float _r2, float otherRY, float _r3) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, otherRW * _r1) + java.lang.Math.fma(otherRZ, _r2, -(otherRY * _r3));
        _dst.rY = java.lang.Math.fma(otherRX, _r3, otherRW * _r2) + java.lang.Math.fma(otherRY, _r0, -(otherRZ * _r1));
        _dst.rZ = java.lang.Math.fma(otherRY, _r1, otherRZ * _r0) + java.lang.Math.fma(otherRW, _r3, -(otherRX * _r2));
        _dst.rW = java.lang.Math.fma(otherRW, _r0, -(otherRX * _r1)) - java.lang.Math.fma(otherRY, _r2, otherRZ * _r3);
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s45241818_c0_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRZ, float _r2, float otherRY, float _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRZ) * (_r2) - (otherRY * _r3));
        _dst.rY = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRY) * (_r0) - (otherRZ * _r1));
        _dst.rZ = ((otherRY) * (_r1) + (otherRZ * _r0)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r2) + (otherRZ * _r3));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s45241818_c1_fma(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRZ, float _r6, float otherRY, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDZ, float _r2, float otherDY, float _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDZ, _r2, -(otherDY * _r3)));
        _dst.dY = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRY, _r4, -(otherRZ * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDY, _r0, -(otherDZ * _r1)));
        _dst.dZ = java.lang.Math.fma(otherRY, _r5, otherRZ * _r4) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r1, otherDZ * _r0) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r7, -(otherRY * _r6)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r3, -(otherDY * _r2)));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s45241818_c1_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRZ, float _r6, float otherRY, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDZ, float _r2, float otherDY, float _r3) {
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat mul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s45241818_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s45241818_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rY;
            float _r3 = this.rZ;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dY;
            float _r7 = this.dZ;
            mul_s45241818_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRZ, _r2, otherRY, _r3);
            mul_s45241818_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRZ, _r6, otherRY, _r7, otherDX, _r0, otherDW, _r1, otherDZ, _r2, otherDY, _r3);
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
    public FloatDualQuat preMul(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s447e38b9_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s447e38b9_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s447e38b9_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s447e38b9_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preMul(FloatDualQuatR other, @Mutated DoubleDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s45241818_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s45241818_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s45241818_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s45241818_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        }
    }

    /**
     * Private store group 0 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s447e38b9_c0_fma(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3) {
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
    private void preMul_s447e38b9_c0_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rZ = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r3) + (otherRZ * _r2));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s447e38b9_c1_fma(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r2, -(otherDY * _r3)));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s447e38b9_c1_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3) {
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
    public FloatDualQuat preMul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s447e38b9_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s447e38b9_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s447e38b9_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s447e38b9_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        }
    }

    /**
     * Private store group 0 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s45241818_c0_fma(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3) {
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
    private void preMul_s45241818_c0_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3) {
        _dst.rX = ((otherRX) * (_r0) + (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((otherRW) * (_r3) - (otherRX * _r2));
        _dst.rZ = ((otherRX) * (_r3) + (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRW) * (_r0) - (otherRX * _r1)) - ((otherRY) * (_r3) + (otherRZ * _r2));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s45241818_c1_fma(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, otherRW * _r5) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, otherDW * _r1) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(otherRW, _r7, -(otherRX * _r6)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(otherDW, _r3, -(otherDX * _r2)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, otherRW * _r6) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, otherDW * _r2) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRW, _r4, -(otherRX * _r5)) + java.lang.Math.fma(-otherRZ, _r6, -(otherRY * _r7)) + (java.lang.Math.fma(otherDW, _r0, -(otherDX * _r1)) + java.lang.Math.fma(-otherDZ, _r2, -(otherDY * _r3)));
    }

    /** Private store group 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s45241818_c1_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3) {
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat preMul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s45241818_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s45241818_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            preMul_s45241818_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3);
            preMul_s45241818_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3);
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
    public FloatDualQuat addScaled(FloatDualQuatR other, float weight, @Mutated FloatDualQuat dest) {
        return addScaled(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), weight, dest);
    }


    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat addScaled(FloatDualQuatR other, float weight, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat addScaled(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float weight, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) scaled by {@code weight} to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat addScaled(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float weight, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat conjugate(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse) and store the result in
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
    public FloatDualQuat difference(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        return difference(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), dest);
    }


    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat difference(FloatDualQuatR other, @Mutated DoubleDualQuat dest) {
        return difference(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), dest);
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s447e38b9_c0_fma(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3, float _t0) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, -(otherRW * _r1)) + java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r3));
        _dst.rY = java.lang.Math.fma(otherRY, _r0, otherRZ * _r1) + java.lang.Math.fma(_t0, _r2, -(otherRW * _r3));
        _dst.rZ = java.lang.Math.fma(otherRX, _r3, -(otherRW * _r2)) + java.lang.Math.fma(otherRZ, _r0, -(otherRY * _r1));
        _dst.rW = java.lang.Math.fma(otherRX, _r1, otherRW * _r0) - java.lang.Math.fma(-otherRZ, _r2, -(otherRY * _r3));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s447e38b9_c0_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3, float _t0) {
        _dst.rX = ((otherRX) * (_r0) - (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((_t0) * (_r2) - (otherRW * _r3));
        _dst.rZ = ((otherRX) * (_r3) - (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRX) * (_r1) + (otherRW * _r0)) - ((-otherRZ) * (_r2) - (otherRY * _r3));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s447e38b9_c1_fma(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3, float _t0) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, -(otherRW * _r5)) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, -(otherDW * _r1)) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(_t0, _r6, -(otherRW * _r7)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(-otherDX, _r2, -(otherDW * _r3)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, -(otherRW * _r6)) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, -(otherDW * _r2)) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRX, _r5, otherRW * _r4) + java.lang.Math.fma(otherRY, _r7, otherRZ * _r6) + (java.lang.Math.fma(otherDX, _r1, otherDW * _r0) + java.lang.Math.fma(otherDY, _r3, otherDZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s447e38b9_c1_mulAdd(FloatDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3, float _t0) {
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
    public FloatDualQuat difference(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            float _t0 = -otherRX;
            difference_s447e38b9_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s447e38b9_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
            return d;
        } else {
            FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            float _t0 = -otherRX;
            difference_s447e38b9_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s447e38b9_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
            return d;
        }
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s45241818_c0_fma(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3, float _t0) {
        _dst.rX = java.lang.Math.fma(otherRX, _r0, -(otherRW * _r1)) + java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r3));
        _dst.rY = java.lang.Math.fma(otherRY, _r0, otherRZ * _r1) + java.lang.Math.fma(_t0, _r2, -(otherRW * _r3));
        _dst.rZ = java.lang.Math.fma(otherRX, _r3, -(otherRW * _r2)) + java.lang.Math.fma(otherRZ, _r0, -(otherRY * _r1));
        _dst.rW = java.lang.Math.fma(otherRX, _r1, otherRW * _r0) - java.lang.Math.fma(-otherRZ, _r2, -(otherRY * _r3));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s45241818_c0_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r0, float otherRW, float _r1, float otherRY, float _r2, float otherRZ, float _r3, float _t0) {
        _dst.rX = ((otherRX) * (_r0) - (otherRW * _r1)) + ((otherRY) * (_r2) - (otherRZ * _r3));
        _dst.rY = ((otherRY) * (_r0) + (otherRZ * _r1)) + ((_t0) * (_r2) - (otherRW * _r3));
        _dst.rZ = ((otherRX) * (_r3) - (otherRW * _r2)) + ((otherRZ) * (_r0) - (otherRY * _r1));
        _dst.rW = ((otherRX) * (_r1) + (otherRW * _r0)) - ((-otherRZ) * (_r2) - (otherRY * _r3));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s45241818_c1_fma(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3, float _t0) {
        _dst.dX = java.lang.Math.fma(otherRX, _r4, -(otherRW * _r5)) + java.lang.Math.fma(otherRY, _r6, -(otherRZ * _r7)) + (java.lang.Math.fma(otherDX, _r0, -(otherDW * _r1)) + java.lang.Math.fma(otherDY, _r2, -(otherDZ * _r3)));
        _dst.dY = java.lang.Math.fma(otherRY, _r4, otherRZ * _r5) + java.lang.Math.fma(_t0, _r6, -(otherRW * _r7)) + (java.lang.Math.fma(otherDY, _r0, otherDZ * _r1) + java.lang.Math.fma(-otherDX, _r2, -(otherDW * _r3)));
        _dst.dZ = java.lang.Math.fma(otherRX, _r7, -(otherRW * _r6)) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5)) + (java.lang.Math.fma(otherDX, _r3, -(otherDW * _r2)) + java.lang.Math.fma(otherDZ, _r0, -(otherDY * _r1)));
        _dst.dW = java.lang.Math.fma(otherRX, _r5, otherRW * _r4) + java.lang.Math.fma(otherRY, _r7, otherRZ * _r6) + (java.lang.Math.fma(otherDX, _r1, otherDW * _r0) + java.lang.Math.fma(otherDY, _r3, otherDZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s45241818_c1_mulAdd(DoubleDualQuatImpl _dst, float otherRX, float _r4, float otherRW, float _r5, float otherRY, float _r6, float otherRZ, float _r7, float otherDX, float _r0, float otherDW, float _r1, float otherDY, float _r2, float otherDZ, float _r3, float _t0) {
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat difference(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        if (Math.useFma()) {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            float _t0 = -otherRX;
            difference_s45241818_c0_fma(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s45241818_c1_fma(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
            return d;
        } else {
            DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
            float _r0 = this.rW;
            float _r1 = this.rX;
            float _r2 = this.rZ;
            float _r3 = this.rY;
            float _r4 = this.dW;
            float _r5 = this.dX;
            float _r6 = this.dZ;
            float _r7 = this.dY;
            float _t0 = -otherRX;
            difference_s45241818_c0_mulAdd(d, otherRX, _r0, otherRW, _r1, otherRY, _r2, otherRZ, _r3, _t0);
            difference_s45241818_c1_mulAdd(d, otherRX, _r4, otherRW, _r5, otherRY, _r6, otherRZ, _r7, otherDX, _r0, otherDW, _r1, otherDY, _r2, otherDZ, _r3, _t0);
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
    public float dot(FloatDualQuatR other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public float dot(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
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
    public FloatDualQuat dualConjugate(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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


    /**
     * Compute the dual-number conjugate of this dual quaternion and store the result in
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
    private void exp_s416ccfb5_c0(FloatDualQuatImpl _dst, float _t4, float _r1, float _t9, float _t8, float _r2, float _t10, float _r0, float _t11, float _t13) {
        _dst.rX = _t4 < 1.0E-12f ? _r1 : _t9 * _t8;
        _dst.rY = _t4 < 1.0E-12f ? _r2 : _t10 * _t8;
        _dst.rZ = _t4 < 1.0E-12f ? _r0 : _t11 * _t8;
        _dst.rW = _t4 < 1.0E-12f ? 1.0f : _t13;
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s416ccfb5_c1_fma(FloatDualQuatImpl _dst, float _t4, float _r4, float _t9, float _t14, float _t12, float _sp0, float _r5, float _t10, float _r3, float _t11, float _t5, float _t8) {
        _dst.dX = _t4 < 1.0E-12f ? _r4 : java.lang.Math.fma(_t9, _t14, java.lang.Math.fma(-_t9, _t12, _r4) * _sp0);
        _dst.dY = _t4 < 1.0E-12f ? _r5 : java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(-_t10, _t12, _r5) * _sp0);
        _dst.dZ = _t4 < 1.0E-12f ? _r3 : java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(-_t11, _t12, _r3) * _sp0);
        _dst.dW = _t4 < 1.0E-12f ? -_t5 : -(_t12 * _t8);
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s416ccfb5_c1_mulAdd(FloatDualQuatImpl _dst, float _t4, float _r4, float _t9, float _t14, float _t12, float _sp0, float _r5, float _t10, float _r3, float _t11, float _t5, float _t8) {
        _dst.dX = _t4 < 1.0E-12f ? _r4 : ((_t9) * (_t14) + (((-_t9) * (_t12) + (_r4)) * _sp0));
        _dst.dY = _t4 < 1.0E-12f ? _r5 : ((_t10) * (_t14) + (((-_t10) * (_t12) + (_r5)) * _sp0));
        _dst.dZ = _t4 < 1.0E-12f ? _r3 : ((_t11) * (_t14) + (((-_t11) * (_t12) + (_r3)) * _sp0));
        _dst.dW = _t4 < 1.0E-12f ? -_t5 : -(_t12 * _t8);
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
    public FloatDualQuat exp(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rZ;
        float _r1 = this.rX;
        float _r2 = this.rY;
        float _r3 = this.dZ;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _t4 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t5 = Math.fma(_r0, _r3, Math.fma(_r1, _r4, _r2 * _r5));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sin(_t7);
        float _t9 = _r1 * _t6;
        float _t10 = _r2 * _t6;
        float _t11 = _r0 * _t6;
        float _t12 = _t5 * _t6;
        float _t13 = Math.cosFromSin(_t8, _t7);
        exp_s416ccfb5_c0(d, _t4, _r1, _t9, _t8, _r2, _t10, _r0, _t11, _t13);
        if (Math.useFma()) exp_s416ccfb5_c1_fma(d, _t4, _r4, _t9, _t12 * _t13, _t12, _t6 * _t8, _r5, _t10, _r3, _t11, _t5, _t8); else exp_s416ccfb5_c1_mulAdd(d, _t4, _r4, _t9, _t12 * _t13, _t12, _t6 * _t8, _r5, _t10, _r3, _t11, _t5, _t8);
        return d;
    }

    /** Private store group 0 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c0(DoubleDualQuatImpl _dst, float _t4, float _r1, float _t9, float _t8, float _r2, float _t10, float _r0, float _t11, float _t13) {
        _dst.rX = _t4 < 1.0E-12f ? _r1 : _t9 * _t8;
        _dst.rY = _t4 < 1.0E-12f ? _r2 : _t10 * _t8;
        _dst.rZ = _t4 < 1.0E-12f ? _r0 : _t11 * _t8;
        _dst.rW = _t4 < 1.0E-12f ? 1.0f : _t13;
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c1_fma(DoubleDualQuatImpl _dst, float _t4, float _r4, float _t9, float _t14, float _t12, float _sp0, float _r5, float _t10, float _r3, float _t11, float _t5, float _t8) {
        _dst.dX = _t4 < 1.0E-12f ? _r4 : java.lang.Math.fma(_t9, _t14, java.lang.Math.fma(-_t9, _t12, _r4) * _sp0);
        _dst.dY = _t4 < 1.0E-12f ? _r5 : java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(-_t10, _t12, _r5) * _sp0);
        _dst.dZ = _t4 < 1.0E-12f ? _r3 : java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(-_t11, _t12, _r3) * _sp0);
        _dst.dW = _t4 < 1.0E-12f ? -_t5 : -(_t12 * _t8);
    }

    /** Private store group 1 of {@code exp}: computes and stores it; reached only through it. */
    private void exp_s6608609c_c1_mulAdd(DoubleDualQuatImpl _dst, float _t4, float _r4, float _t9, float _t14, float _t12, float _sp0, float _r5, float _t10, float _r3, float _t11, float _t5, float _t8) {
        _dst.dX = _t4 < 1.0E-12f ? _r4 : ((_t9) * (_t14) + (((-_t9) * (_t12) + (_r4)) * _sp0));
        _dst.dY = _t4 < 1.0E-12f ? _r5 : ((_t10) * (_t14) + (((-_t10) * (_t12) + (_r5)) * _sp0));
        _dst.dZ = _t4 < 1.0E-12f ? _r3 : ((_t11) * (_t14) + (((-_t11) * (_t12) + (_r3)) * _sp0));
        _dst.dW = _t4 < 1.0E-12f ? -_t5 : -(_t12 * _t8);
    }


    /**
     * Compute the exponential of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat exp(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rZ;
        float _r1 = this.rX;
        float _r2 = this.rY;
        float _r3 = this.dZ;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _t4 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, _r2 * _r2));
        float _t5 = Math.fma(_r0, _r3, Math.fma(_r1, _r4, _r2 * _r5));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sin(_t7);
        float _t9 = _r1 * _t6;
        float _t10 = _r2 * _t6;
        float _t11 = _r0 * _t6;
        float _t12 = _t5 * _t6;
        float _t13 = Math.cosFromSin(_t8, _t7);
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
    public FloatQuat getDual(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = this.dX;
        d.y = this.dY;
        d.z = this.dZ;
        d.w = this.dW;
        return d;
    }


    /**
     * Get the dual part of this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.rY * this.rZ;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.rY * this.rZ;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rY * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, _t1);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rY * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, _t1);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
        }
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
    public FloatQuat getRotation(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = this.rX;
        d.y = this.rY;
        d.z = this.rZ;
        d.w = this.rW;
        return d;
    }


    /**
     * Get the rotation of this dual quaternion, i.e. its raw real part (a unit quaternion only when
     * this dual quaternion has unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Float3 getTranslation(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
            d.y = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
            d.z = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
            d.y = 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
            d.z = 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
            return d;
        }
    }


    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length) and store the result in
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
    public Double3 getTranslation(@Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
            d.y = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
            d.z = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
            d.y = 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
            d.z = 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
            return d;
        }
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
    public FloatDualQuat inverseUnit(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public FloatDualQuat invert(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t8 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (Math.fma(this.rX, this.dX, this.rY * this.dY) + Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.rW;
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
     * Invert this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat invert(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t8 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (Math.fma(this.rX, this.dX, this.rY * this.dY) + Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        d.rX = -(this.rX * _t8_inv);
        d.rY = -(this.rY * _t8_inv);
        d.rZ = -(this.rZ * _t8_inv);
        d.rW = this.rW * _t8_inv;
        d.dX = Math.fma(this.rX, _sp0, -(this.dX * _t8_inv));
        d.dY = Math.fma(this.rY, _sp0, -(this.dY * _t8_inv));
        d.dZ = Math.fma(this.rZ, _sp0, -(this.dZ * _t8_inv));
        d.dW = Math.fma(this.dW, _t8_inv, -(this.rW * _sp0));
        return d;
    }


    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    public float length() {
        if (Math.useFma()) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW));
        } else {
            return (float) java.lang.Math.sqrt(((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW)));
        }
    }


    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public float lengthSquared() {
        if (Math.useFma()) {
            return java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        } else {
            return ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        }
    }

    /** Private store group 0 of {@code log}: computes and stores it; reached only through it. */
    private void log_s416ccfb5_c0(FloatDualQuatImpl _dst, float _t18, float _t9, float _t21, float _t22, float _t10, float _t23, float _t8, float _t24) {
        _dst.rX = _t18 < 1.0E-12f ? _t9 : _t21 * _t22;
        _dst.rY = _t18 < 1.0E-12f ? _t10 : _t23 * _t22;
        _dst.rZ = _t18 < 1.0E-12f ? _t8 : _t24 * _t22;
        _dst.rW = 0.0f;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s416ccfb5_c1_fma(FloatDualQuatImpl _dst, float _t18, float _t12, float _t26, float _t21, float _t19, float _t22, float _t25, float _t14, float _t23, float _t15, float _t24) {
        _dst.dX = _t18 < 1.0E-12f ? _t12 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25));
        _dst.dY = _t18 < 1.0E-12f ? _t14 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-12f ? _t15 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25));
        _dst.dW = 0.0f;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s416ccfb5_c1_mulAdd(FloatDualQuatImpl _dst, float _t18, float _t12, float _t26, float _t21, float _t19, float _t22, float _t25, float _t14, float _t23, float _t15, float _t24) {
        _dst.dX = _t18 < 1.0E-12f ? _t12 : ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25));
        _dst.dY = _t18 < 1.0E-12f ? _t14 : ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-12f ? _t15 : ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25));
        _dst.dW = 0.0f;
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s416ccfb5_tail_fma(FloatDualQuatImpl _dst, float _t19, float _t9, float _t18, float _t11, float _t10, float _t8, float _r0, float _r7, float _t12, float _t14, float _t15) {
        float _t21 = _t19 * _t9;
        float _t22 = Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (_r0 < 0.0f ? -_r7 : _r7);
        log_s416ccfb5_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s416ccfb5_c1_fma(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s416ccfb5_tail_mulAdd(FloatDualQuatImpl _dst, float _t19, float _t9, float _t18, float _t11, float _t10, float _t8, float _r0, float _r7, float _t12, float _t14, float _t15) {
        float _t21 = _t19 * _t9;
        float _t22 = Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (_r0 < 0.0f ? -_r7 : _r7);
        log_s416ccfb5_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s416ccfb5_c1_mulAdd(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
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
    public FloatDualQuat log(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _r6 = this.dZ;
        float _r7 = this.dW;
        float _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (_r0 < 0.0f) {
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
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        if (Math.useFma()) log_s416ccfb5_tail_fma(d, (1.0f / (float) java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15); else log_s416ccfb5_tail_mulAdd(d, (1.0f / (float) java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15);
        return d;
    }

    /** Private store group 0 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c0(DoubleDualQuatImpl _dst, float _t18, float _t9, float _t21, float _t22, float _t10, float _t23, float _t8, float _t24) {
        _dst.rX = _t18 < 1.0E-12f ? _t9 : _t21 * _t22;
        _dst.rY = _t18 < 1.0E-12f ? _t10 : _t23 * _t22;
        _dst.rZ = _t18 < 1.0E-12f ? _t8 : _t24 * _t22;
        _dst.rW = 0.0f;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c1_fma(DoubleDualQuatImpl _dst, float _t18, float _t12, float _t26, float _t21, float _t19, float _t22, float _t25, float _t14, float _t23, float _t15, float _t24) {
        _dst.dX = _t18 < 1.0E-12f ? _t12 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25));
        _dst.dY = _t18 < 1.0E-12f ? _t14 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-12f ? _t15 : java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25));
        _dst.dW = 0.0f;
    }

    /** Private store group 1 of {@code log}: computes and stores it; reached only through it. */
    private void log_s6608609c_c1_mulAdd(DoubleDualQuatImpl _dst, float _t18, float _t12, float _t26, float _t21, float _t19, float _t22, float _t25, float _t14, float _t23, float _t15, float _t24) {
        _dst.dX = _t18 < 1.0E-12f ? _t12 : ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25));
        _dst.dY = _t18 < 1.0E-12f ? _t14 : ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25));
        _dst.dZ = _t18 < 1.0E-12f ? _t15 : ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25));
        _dst.dW = 0.0f;
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s6608609c_tail_fma(DoubleDualQuatImpl _dst, float _t19, float _t9, float _t18, float _t11, float _t10, float _t8, float _r0, float _r7, float _t12, float _t14, float _t15) {
        float _t21 = _t19 * _t9;
        float _t22 = Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (_r0 < 0.0f ? -_r7 : _r7);
        log_s6608609c_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s6608609c_c1_fma(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
    }

    /** Private tail of {@code log}; reached only through it. */
    private void log_s6608609c_tail_mulAdd(DoubleDualQuatImpl _dst, float _t19, float _t9, float _t18, float _t11, float _t10, float _t8, float _r0, float _r7, float _t12, float _t14, float _t15) {
        float _t21 = _t19 * _t9;
        float _t22 = Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (_r0 < 0.0f ? -_r7 : _r7);
        log_s6608609c_c0(_dst, _t18, _t9, _t21, _t22, _t10, _t23, _t8, _t24);
        log_s6608609c_c1_mulAdd(_dst, _t18, _t12, _t25 * _t11, _t21, _t19, _t22, _t25, _t14, _t23, _t15, _t24);
    }


    /**
     * Compute the natural logarithm of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat log(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.dX;
        float _r5 = this.dY;
        float _r6 = this.dZ;
        float _r7 = this.dW;
        float _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (_r0 < 0.0f) {
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
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        if (Math.useFma()) log_s6608609c_tail_fma(d, (1.0f / (float) java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15); else log_s6608609c_tail_mulAdd(d, (1.0f / (float) java.lang.Math.sqrt(_t18)), _t9, _t18, _t11, _t10, _t8, _r0, _r7, _t12, _t14, _t15);
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
    @Mutated public FloatDualQuat makeFromMatrix(Float4x4R m) {
        float _t0 = -m.m23();
        float _t2 = 1.0f - m.m00();
        float _t4 = m.m21() - m.m12();
        float _t6 = m.m01() + m.m10();
        float _t7 = m.m02() + m.m20();
        float _t8 = m.m02() - m.m20();
        float _t9 = m.m12() + m.m21();
        float _t10 = m.m10() - m.m01();
        float _t14 = m.m22() + (m.m00() + m.m11());
        float _t15 = 1.0f + _t14;
        float _t16 = m.m00() + (1.0f - m.m11() - m.m22());
        float _t17 = m.m11() + (_t2 - m.m22());
        float _t18 = m.m22() + (_t2 - m.m11());
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (m.m00() > java.lang.Math.max(m.m11(), m.m22())) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        this.rX = _t63;
        this.rY = _t64;
        this.rZ = _t65;
        this.rW = _t66;
        this.dX = 0.5f * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        this.dY = 0.5f * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        this.dZ = 0.5f * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        this.dW = 0.5f * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
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
    @Mutated public FloatDualQuat makeFromMatrix(Float3x4R m) {
        float _t0 = -m.m23();
        float _t2 = 1.0f - m.m00();
        float _t4 = m.m21() - m.m12();
        float _t6 = m.m01() + m.m10();
        float _t7 = m.m02() + m.m20();
        float _t8 = m.m02() - m.m20();
        float _t9 = m.m12() + m.m21();
        float _t10 = m.m10() - m.m01();
        float _t14 = m.m22() + (m.m00() + m.m11());
        float _t15 = 1.0f + _t14;
        float _t16 = m.m00() + (1.0f - m.m11() - m.m22());
        float _t17 = m.m11() + (_t2 - m.m22());
        float _t18 = m.m22() + (_t2 - m.m11());
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (m.m00() > java.lang.Math.max(m.m11(), m.m22())) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        this.rX = _t63;
        this.rY = _t64;
        this.rZ = _t65;
        this.rW = _t66;
        this.dX = 0.5f * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        this.dY = 0.5f * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        this.dZ = 0.5f * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        this.dW = 0.5f * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
        return this;
    }

    /** Private store group 0 of {@code makeFromMatrix}: computes and stores it; reached only through it. */
    private void makeFromMatrix_s4190286d_c0(FloatDualQuatImpl _dst, float _t13, float _sp0, float _t3, float _r0, float _t4, float _t15, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        _dst.rX = _t13 > 0.0f ? _sp0 * _t3 : _r0 > _t4 ? 0.5f * (float) java.lang.Math.sqrt(_t15) : _r3 > _r4 ? _sp1 * _t5 : _sp2 * _t6;
        _dst.rY = _t13 > 0.0f ? _sp0 * _t7 : _r0 > _t4 ? _sp3 * _t5 : _r3 > _r4 ? 0.5f * (float) java.lang.Math.sqrt(_t16) : _sp2 * _t8;
        _dst.rZ = _t13 > 0.0f ? _sp0 * _t9 : _r0 > _t4 ? _sp3 * _t6 : _r3 > _r4 ? _sp1 * _t8 : 0.5f * (float) java.lang.Math.sqrt(_t17);
        _dst.rW = _t13 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t14) : _r0 > _t4 ? _sp3 * _t3 : _r3 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private void makeFromMatrix_s4190286d_tail(FloatDualQuatImpl _dst, float _t15, float _t13, float _sp0, float _t3, float _r0, float _t4, float _r3, float _r4, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        makeFromMatrix_s4190286d_c0(_dst, _t13, _sp0, _t3, _r0, _t4, _t15, _r3, _r4, _sp1, _t5, _sp2, _t6, _t7, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
        _dst.dX = 0.0f;
        _dst.dY = 0.0f;
        _dst.dZ = 0.0f;
        _dst.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeFromMatrix(Float3x3R m) {
        float _r0 = m.m00();
        float _r1 = m.m21();
        float _r2 = m.m12();
        float _r3 = m.m11();
        float _r4 = m.m22();
        float _r5 = m.m01();
        float _r6 = m.m10();
        float _r7 = m.m02();
        float _r8 = m.m20();
        float _t1 = 1.0f - _r0;
        float _t13 = _r4 + (_r0 + _r3);
        float _t14 = 1.0f + _t13;
        float _t16 = _r3 + (_t1 - _r4);
        float _t17 = _r4 + (_t1 - _r3);
        makeFromMatrix_s4190286d_tail(this, _r0 + (1.0f - _r3 - _r4), _t13, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), _r1 - _r2, _r0, java.lang.Math.max(_r3, _r4), _r3, _r4, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r2 + _r1, _r6 - _r5, _t17, _t14);
        return this;
    }


    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat normalize(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t4 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0f) {
            d.rX = this.rX * _t5;
            d.rY = this.rY * _t5;
            d.rZ = this.rZ * _t5;
            d.rW = this.rW * _t5;
            d.dX = this.dX * _t5;
            d.dY = this.dY * _t5;
            d.dZ = this.dZ * _t5;
            d.dW = this.dW * _t5;
        } else {
            d.rX = 0.0f;
            d.rY = 0.0f;
            d.rZ = 0.0f;
            d.rW = 0.0f;
            d.dX = 0.0f;
            d.dY = 0.0f;
            d.dZ = 0.0f;
            d.dW = 0.0f;
        }
        return d;
    }


    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat normalize(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t4 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0f) {
            d.rX = this.rX * _t5;
            d.rY = this.rY * _t5;
            d.rZ = this.rZ * _t5;
            d.rW = this.rW * _t5;
            d.dX = this.dX * _t5;
            d.dY = this.dY * _t5;
            d.dZ = this.dZ * _t5;
            d.dW = this.dW * _t5;
        } else {
            d.rX = 0.0f;
            d.rY = 0.0f;
            d.rZ = 0.0f;
            d.rW = 0.0f;
            d.dX = 0.0f;
            d.dY = 0.0f;
            d.dZ = 0.0f;
            d.dW = 0.0f;
        }
        return d;
    }

    /** Private store group 0 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s7aa26011_c0(FloatDualQuatImpl _dst, float _t18, float t, float _t9, float _t21, float _t28, float _t10, float _t23, float _t8, float _t24, float _t30) {
        _dst.rX = _t18 < 1.0E-12f ? t * _t9 : _t21 * _t28;
        _dst.rY = _t18 < 1.0E-12f ? t * _t10 : _t23 * _t28;
        _dst.rZ = _t18 < 1.0E-12f ? t * _t8 : _t24 * _t28;
        _dst.rW = _t18 < 1.0E-12f ? 1.0f : _t30;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s7aa26011_c1_fma(FloatDualQuatImpl _dst, float _t18, float t, float _t12, float _t29, float _t21, float _t19, float _t28, float _t31, float _t14, float _t23, float _t15, float _t24, float _t13, float _t27) {
        _dst.dX = _t18 < 1.0E-12f ? t * _t12 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
        _dst.dY = _t18 < 1.0E-12f ? t * _t14 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-12f ? t * _t15 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
        _dst.dW = _t18 < 1.0E-12f ? t * t * _t13 : _t27 * _t28;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s7aa26011_c1_mulAdd(FloatDualQuatImpl _dst, float _t18, float t, float _t12, float _t29, float _t21, float _t19, float _t28, float _t31, float _t14, float _t23, float _t15, float _t24, float _t13, float _t27) {
        _dst.dX = _t18 < 1.0E-12f ? t * _t12 : ((((_t29) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
        _dst.dY = _t18 < 1.0E-12f ? t * _t14 : ((((_t29) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-12f ? t * _t15 : ((((_t29) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
        _dst.dW = _t18 < 1.0E-12f ? t * t * _t13 : _t27 * _t28;
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s7aa26011_tail_fma(FloatDualQuatImpl _dst, float _t8, float _t9, float _t10, float _t13, float t, float _t11, float _t12, float _t14, float _t15) {
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t21 = _t19 * _t9;
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        pow_s7aa26011_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s7aa26011_c1_fma(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s7aa26011_tail_mulAdd(FloatDualQuatImpl _dst, float _t8, float _t9, float _t10, float _t13, float t, float _t11, float _t12, float _t14, float _t15) {
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t21 = _t19 * _t9;
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        pow_s7aa26011_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s7aa26011_c1_mulAdd(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
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
    public FloatDualQuat pow(float t, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (_r0 < 0.0f) {
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
        if (Math.useFma()) pow_s7aa26011_tail_fma(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15); else pow_s7aa26011_tail_mulAdd(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15);
        return d;
    }

    /** Private store group 0 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s5384dbc0_c0(DoubleDualQuatImpl _dst, float _t18, float t, float _t9, float _t21, float _t28, float _t10, float _t23, float _t8, float _t24, float _t30) {
        _dst.rX = _t18 < 1.0E-12f ? t * _t9 : _t21 * _t28;
        _dst.rY = _t18 < 1.0E-12f ? t * _t10 : _t23 * _t28;
        _dst.rZ = _t18 < 1.0E-12f ? t * _t8 : _t24 * _t28;
        _dst.rW = _t18 < 1.0E-12f ? 1.0f : _t30;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s5384dbc0_c1_fma(DoubleDualQuatImpl _dst, float _t18, float t, float _t12, float _t29, float _t21, float _t19, float _t28, float _t31, float _t14, float _t23, float _t15, float _t24, float _t13, float _t27) {
        _dst.dX = _t18 < 1.0E-12f ? t * _t12 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
        _dst.dY = _t18 < 1.0E-12f ? t * _t14 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-12f ? t * _t15 : java.lang.Math.fma(java.lang.Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
        _dst.dW = _t18 < 1.0E-12f ? t * t * _t13 : _t27 * _t28;
    }

    /** Private store group 1 of {@code pow}: computes and stores it; reached only through it. */
    private void pow_s5384dbc0_c1_mulAdd(DoubleDualQuatImpl _dst, float _t18, float t, float _t12, float _t29, float _t21, float _t19, float _t28, float _t31, float _t14, float _t23, float _t15, float _t24, float _t13, float _t27) {
        _dst.dX = _t18 < 1.0E-12f ? t * _t12 : ((((_t29) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
        _dst.dY = _t18 < 1.0E-12f ? t * _t14 : ((((_t29) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
        _dst.dZ = _t18 < 1.0E-12f ? t * _t15 : ((((_t29) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
        _dst.dW = _t18 < 1.0E-12f ? t * t * _t13 : _t27 * _t28;
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s5384dbc0_tail_fma(DoubleDualQuatImpl _dst, float _t8, float _t9, float _t10, float _t13, float t, float _t11, float _t12, float _t14, float _t15) {
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t21 = _t19 * _t9;
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        pow_s5384dbc0_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s5384dbc0_c1_fma(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private void pow_s5384dbc0_tail_mulAdd(DoubleDualQuatImpl _dst, float _t8, float _t9, float _t10, float _t13, float t, float _t11, float _t12, float _t14, float _t15) {
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t21 = _t19 * _t9;
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        pow_s5384dbc0_c0(_dst, _t18, t, _t9, _t21, _t28, _t10, _t23, _t8, _t24, _t30);
        pow_s5384dbc0_c1_mulAdd(_dst, _t18, t, _t12, _t25 * _t11, _t21, _t19, _t28, _t27 * _t30, _t14, _t23, _t15, _t24, _t13, _t27);
    }


    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat pow(float t, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (_r0 < 0.0f) {
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
        if (Math.useFma()) pow_s5384dbc0_tail_fma(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15); else pow_s5384dbc0_tail_mulAdd(d, _t8, _t9, _t10, _t13, t, _t11, _t12, _t14, _t15);
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
    public FloatDualQuat setDual(FloatQuatR dual, @Mutated FloatDualQuat dest) {
        float dualX = dual.x();
        float dualY = dual.y();
        float dualZ = dual.z();
        float dualW = dual.w();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setDual(FloatQuatR dual, @Mutated DoubleDualQuat dest) {
        float dualX = dual.x();
        float dualY = dual.y();
        float dualZ = dual.z();
        float dualW = dual.w();
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
    public FloatDualQuat setDual(float dualX, float dualY, float dualZ, float dualW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat setDual(float dualX, float dualY, float dualZ, float dualW, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat setReal(FloatQuatR real, @Mutated FloatDualQuat dest) {
        float realY = real.y();
        float realZ = real.z();
        float realW = real.w();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setReal(FloatQuatR real, @Mutated DoubleDualQuat dest) {
        float realY = real.y();
        float realZ = real.z();
        float realW = real.w();
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
    public FloatDualQuat setReal(float realX, float realY, float realZ, float realW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
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
     * Set the real (rotation) half of this dual quaternion to ({@code realX}, {@code realY},
     * {@code realZ}, {@code realW}), keeping the dual half as it is and store the result in
     * {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat setReal(float realX, float realY, float realZ, float realW, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat setRotation(FloatQuatR rotation, @Mutated FloatDualQuat dest) {
        return setRotation(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Set the rotation of this dual quaternion to {@code rotation} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setRotation(FloatQuatR rotation, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat setRotation(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        float _t24 = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.rX = rotationX;
        d.rY = rotationY;
        d.rZ = rotationZ;
        d.rW = rotationW;
        d.dX = 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        d.dY = 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        d.dZ = 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        d.dW = 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
        return d;
    }


    /**
     * Set the rotation of this dual quaternion to ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat setRotation(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        float _t24 = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        d.rX = rotationX;
        d.rY = rotationY;
        d.rZ = rotationZ;
        d.rW = rotationW;
        d.dX = 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        d.dY = 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        d.dZ = 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        d.dW = 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
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
    public FloatDualQuat setTranslation(Float3R translation, @Mutated FloatDualQuat dest) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -this.rY;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = 0.5f * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        d.dY = 0.5f * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        d.dZ = 0.5f * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        d.dW = 0.5f * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return d;
    }


    /**
     * Set the translation of this dual quaternion to {@code translation} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat setTranslation(Float3R translation, @Mutated DoubleDualQuat dest) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -this.rY;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = 0.5f * Math.fma(_t0, translationZ, Math.fma(this.rZ, translationY, this.rW * translationX));
        d.dY = 0.5f * Math.fma(this.rX, translationZ, Math.fma(this.rW, translationY, -(this.rZ * translationX)));
        d.dZ = 0.5f * Math.fma(this.rW, translationZ, Math.fma(this.rY, translationX, -(this.rX * translationY)));
        d.dW = 0.5f * Math.fma(-this.rZ, translationZ, Math.fma(_t0, translationY, -(this.rX * translationX)));
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
    public FloatDualQuat setTranslation(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -this.rY;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.rW;
        d.rX = _rd0;
        d.rY = _rd1;
        d.rZ = _rd2;
        d.rW = _rd3;
        d.dX = 0.5f * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        d.dY = 0.5f * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        d.dZ = 0.5f * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        d.dW = 0.5f * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return d;
    }


    /**
     * Set the translation of this dual quaternion to ({@code translationX}, {@code translationY},
     * {@code translationZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat setTranslation(float translationX, float translationY, float translationZ, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -this.rY;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = 0.5f * Math.fma(_t0, translationZ, Math.fma(this.rZ, translationY, this.rW * translationX));
        d.dY = 0.5f * Math.fma(this.rX, translationZ, Math.fma(this.rW, translationY, -(this.rZ * translationX)));
        d.dZ = 0.5f * Math.fma(this.rW, translationZ, Math.fma(this.rY, translationX, -(this.rX * translationY)));
        d.dW = 0.5f * Math.fma(-this.rZ, translationZ, Math.fma(_t0, translationY, -(this.rX * translationX)));
        return d;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c0_fma(Float4x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0f, _t0, _t6);
        _dst.m10 = 2.0f * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0f, _t3, _sp0 * _r2);
        _dst.m30 = 0.0f;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c0_mulAdd(Float4x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = ((-2.0f) * (_t0) + (_t6));
        _dst.m10 = 2.0f * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0f) * (_t3) + (_sp0 * _r2));
        _dst.m30 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c1_fma(Float4x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0f, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0f, _t4, _t6);
        _dst.m21 = 2.0f * java.lang.Math.fma(_r0, _r3, _t5);
        _dst.m31 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c1_mulAdd(Float4x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = ((-2.0f) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0f) * (_t4) + (_t6));
        _dst.m21 = 2.0f * ((_r0) * (_r3) + (_t5));
        _dst.m31 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c2_fma(Float4x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0f, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        _dst.m32 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c2_mulAdd(Float4x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0f) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
        _dst.m32 = 0.0f;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c3_fma(Float4x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0f * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0f * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
        _dst.m33 = 1.0f;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c3_mulAdd(Float4x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0f * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0f * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
        _dst.m33 = 1.0f;
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
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        if (Math.useFma()) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_fma(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = java.lang.Math.fma(-2.0f, _r2 * _r2, 1.0f);
        toMatrix_s5af251ec_c0_fma(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix_s5af251ec_c1_fma(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix_s5af251ec_c2_fma(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix_s5af251ec_c3_fma(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = ((-2.0f) * (_r2 * _r2) + (1.0f));
        toMatrix_s5af251ec_c0_mulAdd(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix_s5af251ec_c1_mulAdd(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix_s5af251ec_c2_mulAdd(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix_s5af251ec_c3_mulAdd(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_fma(Double4x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0f, _t0, _t6);
        _dst.m10 = 2.0f * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0f, _t3, _sp0 * _r2);
        _dst.m30 = 0.0f;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_mulAdd(Double4x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = ((-2.0f) * (_t0) + (_t6));
        _dst.m10 = 2.0f * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0f) * (_t3) + (_sp0 * _r2));
        _dst.m30 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_fma(Double4x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0f, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0f, _t4, _t6);
        _dst.m21 = 2.0f * java.lang.Math.fma(_r0, _r3, _t5);
        _dst.m31 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_mulAdd(Double4x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = ((-2.0f) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0f) * (_t4) + (_t6));
        _dst.m21 = 2.0f * ((_r0) * (_r3) + (_t5));
        _dst.m31 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_fma(Double4x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0f, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        _dst.m32 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_mulAdd(Double4x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0f) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
        _dst.m32 = 0.0f;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c3_fma(Double4x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0f * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0f * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
        _dst.m33 = 1.0f;
    }

    /** Private column 3 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c3_mulAdd(Double4x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0f * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0f * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
        _dst.m33 = 1.0f;
    }


    /**
     * Compute the matrix representation of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = java.lang.Math.fma(-2.0f, _r2 * _r2, 1.0f);
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
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = ((-2.0f) * (_r2 * _r2) + (1.0f));
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        d.m00 = Math.fma(-2.0f, _t0, _t6);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t2);
        d.m20 = Math.fma(-2.0f, _t3, _sp0 * this.rZ);
        d.m01 = Math.fma(-2.0f, _t2, _sp0 * this.rY);
        d.m11 = Math.fma(-2.0f, _t4, _t6);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, _t5);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t3);
        d.m12 = Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5);
        d.m22 = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        d.properties = 0;
        return d;
    }


    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        d.m00 = Math.fma(-2.0f, _t0, _t6);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t2);
        d.m20 = Math.fma(-2.0f, _t3, _sp0 * this.rZ);
        d.m01 = Math.fma(-2.0f, _t2, _sp0 * this.rY);
        d.m11 = Math.fma(-2.0f, _t4, _t6);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, _t5);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t3);
        d.m12 = Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5);
        d.m22 = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        d.properties = 0;
        return d;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c0_fma(Float3x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0f, _t0, _t6);
        _dst.m10 = 2.0f * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0f, _t3, _sp0 * _r2);
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c0_mulAdd(Float3x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = ((-2.0f) * (_t0) + (_t6));
        _dst.m10 = 2.0f * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0f) * (_t3) + (_sp0 * _r2));
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c1_fma(Float3x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0f, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0f, _t4, _t6);
        _dst.m21 = 2.0f * java.lang.Math.fma(_r0, _r3, _t5);
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c1_mulAdd(Float3x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = ((-2.0f) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0f) * (_t4) + (_t6));
        _dst.m21 = 2.0f * ((_r0) * (_r3) + (_t5));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c2_fma(Float3x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0f, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c2_mulAdd(Float3x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0f) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c3_fma(Float3x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0f * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0f * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c3_mulAdd(Float3x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0f * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0f * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        if (Math.useFma()) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_fma(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = java.lang.Math.fma(-2.0f, _r2 * _r2, 1.0f);
        toMatrix3x4_s7311250d_c0_fma(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix3x4_s7311250d_c1_fma(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix3x4_s7311250d_c2_fma(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix3x4_s7311250d_c3_fma(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = ((-2.0f) * (_r2 * _r2) + (1.0f));
        toMatrix3x4_s7311250d_c0_mulAdd(d, _t0, _t6, _r0, _r1, _t2, _t3, _sp0, _r2);
        toMatrix3x4_s7311250d_c1_mulAdd(d, _t2, _sp0, _r1, _t4, _t6, _r0, _r3, _t5);
        toMatrix3x4_s7311250d_c2_mulAdd(d, _r0, _r2, _t3, _r3, _t5, _t4, _t0);
        toMatrix3x4_s7311250d_c3_mulAdd(d, _r1, _r4, _r2, _r5, _r3, _r6, _r0, _r7);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_fma(Double3x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = java.lang.Math.fma(-2.0f, _t0, _t6);
        _dst.m10 = 2.0f * java.lang.Math.fma(_r0, _r1, _t2);
        _dst.m20 = java.lang.Math.fma(-2.0f, _t3, _sp0 * _r2);
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_mulAdd(Double3x4Impl _dst, float _t0, float _t6, float _r0, float _r1, float _t2, float _t3, float _sp0, float _r2) {
        _dst.m00 = ((-2.0f) * (_t0) + (_t6));
        _dst.m10 = 2.0f * ((_r0) * (_r1) + (_t2));
        _dst.m20 = ((-2.0f) * (_t3) + (_sp0 * _r2));
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_fma(Double3x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = java.lang.Math.fma(-2.0f, _t2, _sp0 * _r1);
        _dst.m11 = java.lang.Math.fma(-2.0f, _t4, _t6);
        _dst.m21 = 2.0f * java.lang.Math.fma(_r0, _r3, _t5);
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_mulAdd(Double3x4Impl _dst, float _t2, float _sp0, float _r1, float _t4, float _t6, float _r0, float _r3, float _t5) {
        _dst.m01 = ((-2.0f) * (_t2) + (_sp0 * _r1));
        _dst.m11 = ((-2.0f) * (_t4) + (_t6));
        _dst.m21 = 2.0f * ((_r0) * (_r3) + (_t5));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_fma(Double3x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * java.lang.Math.fma(_r0, _r2, _t3);
        _dst.m12 = java.lang.Math.fma(-2.0f, _r0 * _r3, _t5 + _t5);
        _dst.m22 = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_mulAdd(Double3x4Impl _dst, float _r0, float _r2, float _t3, float _r3, float _t5, float _t4, float _t0) {
        _dst.m02 = 2.0f * ((_r0) * (_r2) + (_t3));
        _dst.m12 = ((-2.0f) * (_r0 * _r3) + (_t5 + _t5));
        _dst.m22 = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c3_fma(Double3x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)));
        _dst.m13 = 2.0f * (java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)));
        _dst.m23 = 2.0f * (java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)));
    }

    /** Private column 3 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c3_mulAdd(Double3x4Impl _dst, float _r1, float _r4, float _r2, float _r5, float _r3, float _r6, float _r0, float _r7) {
        _dst.m03 = 2.0f * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7)));
        _dst.m13 = 2.0f * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7)));
        _dst.m23 = 2.0f * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7)));
    }


    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = java.lang.Math.fma(-2.0f, _r2 * _r2, 1.0f);
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
        float _r0 = this.rX;
        float _r1 = this.rY;
        float _r2 = this.rZ;
        float _r3 = this.rW;
        float _r4 = this.dZ;
        float _r5 = this.dY;
        float _r6 = this.dX;
        float _r7 = this.dW;
        float _sp0 = _r0 + _r0;
        float _t0 = _r1 * _r1;
        float _t2 = _r2 * _r3;
        float _t3 = _r1 * _r3;
        float _t4 = _r0 * _r0;
        float _t5 = _r1 * _r2;
        float _t6 = ((-2.0f) * (_r2 * _r2) + (1.0f));
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
    public FloatDualQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) {
        float dirX = dir.x();
        float dirY = dir.y();
        float dirZ = dir.z();
        float upX = up.x();
        float upY = up.y();
        float upZ = up.z();
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
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
    public DoubleDualQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) {
        float dirX = dir.x();
        float dirY = dir.y();
        float dirZ = dir.z();
        float upX = up.x();
        float upY = up.y();
        float upZ = up.z();
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
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
    }

    /** {@code lookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat lookAlong_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return lookAlong_sf6a57214_1_fma(dirX, dirY, dirZ, (FloatDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat lookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = ((-dirY) * (_sp0) + (upY));
        float _t29 = ((-dirZ) * (_sp0) + (upZ));
        float _t30 = ((-dirX) * (_sp0) + (upX));
        float _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        float _t38 = ((dirY) * (_t30) - (dirX * _t28));
        float _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        float _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return lookAlong_sf6a57214_1_mulAdd(dirX, dirY, dirZ, (FloatDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_1_fma(float dirX, float dirY, float dirZ, FloatDualQuatImpl d, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = java.lang.Math.fma(-_t37, _t45, 1.0f);
        float _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        float _t84 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return lookAlong_sf6a57214_2_fma(dirZ, d, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_1_mulAdd(float dirX, float dirY, float dirZ, FloatDualQuatImpl d, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = ((-_t37) * (_t45) + (1.0f));
        float _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        float _t84 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return lookAlong_sf6a57214_2_mulAdd(dirZ, d, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_2_fma(float dirZ, FloatDualQuatImpl d, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3, float _sp2) {
        float _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
        d.rX = java.lang.Math.fma(_rd0, _t129, this.rW * _t126) + java.lang.Math.fma(_rd1, _t127, -(_rd2 * _t128));
        d.rY = java.lang.Math.fma(_rd1, _t129, _rd2 * _t126) + java.lang.Math.fma(this.rW, _t128, -(_rd0 * _t127));
        return lookAlong_sf6a57214_3_fma(d, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_2_mulAdd(float dirZ, FloatDualQuatImpl d, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3, float _sp2) {
        float _t126, _t127, _t128, _t129;
        if (((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
        d.rX = ((_rd0) * (_t129) + (this.rW * _t126)) + ((_rd1) * (_t127) - (_rd2 * _t128));
        d.rY = ((_rd1) * (_t129) + (_rd2 * _t126)) + ((this.rW) * (_t128) - (_rd0 * _t127));
        return lookAlong_sf6a57214_3_mulAdd(d, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_sf6a57214_3_fma(FloatDualQuatImpl d, float _t126, float _t127, float _t128, float _t129, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5) {
        d.rZ = java.lang.Math.fma(_rd0, _t128, this.rW * _t127) + java.lang.Math.fma(_rd2, _t129, -(_rd1 * _t126));
        d.rW = java.lang.Math.fma(this.rW, _t129, -(_rd0 * _t126)) - java.lang.Math.fma(_rd1, _t128, _rd2 * _t127);
        d.dX = java.lang.Math.fma(_rd3, _t129, this.dW * _t126) + java.lang.Math.fma(_rd4, _t127, -(_rd5 * _t128));
        d.dY = java.lang.Math.fma(_rd4, _t129, _rd5 * _t126) + java.lang.Math.fma(this.dW, _t128, -(_rd3 * _t127));
        d.dZ = java.lang.Math.fma(_rd3, _t128, this.dW * _t127) + java.lang.Math.fma(_rd5, _t129, -(_rd4 * _t126));
        d.dW = java.lang.Math.fma(this.dW, _t129, -(_rd3 * _t126)) - java.lang.Math.fma(_rd4, _t128, _rd5 * _t127);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_sf6a57214_3_mulAdd(FloatDualQuatImpl d, float _t126, float _t127, float _t128, float _t129, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5) {
        d.rZ = ((_rd0) * (_t128) + (this.rW * _t127)) + ((_rd2) * (_t129) - (_rd1 * _t126));
        d.rW = ((this.rW) * (_t129) - (_rd0 * _t126)) - ((_rd1) * (_t128) + (_rd2 * _t127));
        d.dX = ((_rd3) * (_t129) + (this.dW * _t126)) + ((_rd4) * (_t127) - (_rd5 * _t128));
        d.dY = ((_rd4) * (_t129) + (_rd5 * _t126)) + ((this.dW) * (_t128) - (_rd3 * _t127));
        d.dZ = ((_rd3) * (_t128) + (this.dW * _t127)) + ((_rd5) * (_t129) - (_rd4 * _t126));
        d.dW = ((this.dW) * (_t129) - (_rd3 * _t126)) - ((_rd4) * (_t128) + (_rd5 * _t127));
        return d;
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
    public DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
    }

    /** {@code lookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return lookAlong_s1985c6f3_1_fma(dirX, dirY, dirZ, (DoubleDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = ((-dirY) * (_sp0) + (upY));
        float _t29 = ((-dirZ) * (_sp0) + (upZ));
        float _t30 = ((-dirX) * (_sp0) + (upX));
        float _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        float _t38 = ((dirY) * (_t30) - (dirX * _t28));
        float _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        float _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return lookAlong_s1985c6f3_1_mulAdd(dirX, dirY, dirZ, (DoubleDualQuatImpl) dest, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_1_fma(float dirX, float dirY, float dirZ, DoubleDualQuatImpl d, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = java.lang.Math.fma(-_t37, _t45, 1.0f);
        float _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        float _t84 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return lookAlong_s1985c6f3_2_fma(dirZ, d, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_1_mulAdd(float dirX, float dirY, float dirZ, DoubleDualQuatImpl d, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = ((-_t37) * (_t45) + (1.0f));
        float _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        float _t84 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return lookAlong_s1985c6f3_2_mulAdd(dirZ, d, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_2_fma(float dirZ, DoubleDualQuatImpl d, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3, float _sp2) {
        float _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        d.rX = java.lang.Math.fma(this.rX, _t129, this.rW * _t126) + java.lang.Math.fma(this.rY, _t127, -(this.rZ * _t128));
        d.rY = java.lang.Math.fma(this.rY, _t129, this.rZ * _t126) + java.lang.Math.fma(this.rW, _t128, -(this.rX * _t127));
        d.rZ = java.lang.Math.fma(this.rX, _t128, this.rW * _t127) + java.lang.Math.fma(this.rZ, _t129, -(this.rY * _t126));
        return lookAlong_s1985c6f3_3_fma(d, _t126, _t127, _t128, _t129);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_2_mulAdd(float dirZ, DoubleDualQuatImpl d, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3, float _sp2) {
        float _t126, _t127, _t128, _t129;
        if (((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        d.rX = ((this.rX) * (_t129) + (this.rW * _t126)) + ((this.rY) * (_t127) - (this.rZ * _t128));
        d.rY = ((this.rY) * (_t129) + (this.rZ * _t126)) + ((this.rW) * (_t128) - (this.rX * _t127));
        d.rZ = ((this.rX) * (_t128) + (this.rW * _t127)) + ((this.rZ) * (_t129) - (this.rY * _t126));
        return lookAlong_s1985c6f3_3_mulAdd(d, _t126, _t127, _t128, _t129);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_3_fma(DoubleDualQuatImpl d, float _t126, float _t127, float _t128, float _t129) {
        d.rW = java.lang.Math.fma(this.rW, _t129, -(this.rX * _t126)) - java.lang.Math.fma(this.rY, _t128, this.rZ * _t127);
        d.dX = java.lang.Math.fma(this.dX, _t129, this.dW * _t126) + java.lang.Math.fma(this.dY, _t127, -(this.dZ * _t128));
        d.dY = java.lang.Math.fma(this.dY, _t129, this.dZ * _t126) + java.lang.Math.fma(this.dW, _t128, -(this.dX * _t127));
        d.dZ = java.lang.Math.fma(this.dX, _t128, this.dW * _t127) + java.lang.Math.fma(this.dZ, _t129, -(this.dY * _t126));
        d.dW = java.lang.Math.fma(this.dW, _t129, -(this.dX * _t126)) - java.lang.Math.fma(this.dY, _t128, this.dZ * _t127);
        return d;
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_3_mulAdd(DoubleDualQuatImpl d, float _t126, float _t127, float _t128, float _t129) {
        d.rW = ((this.rW) * (_t129) - (this.rX * _t126)) - ((this.rY) * (_t128) + (this.rZ * _t127));
        d.dX = ((this.dX) * (_t129) + (this.dW * _t126)) + ((this.dY) * (_t127) - (this.dZ * _t128));
        d.dY = ((this.dY) * (_t129) + (this.dZ * _t126)) + ((this.dW) * (_t128) - (this.dX * _t127));
        d.dZ = ((this.dX) * (_t128) + (this.dW * _t127)) + ((this.dZ) * (_t129) - (this.dY * _t126));
        d.dW = ((this.dW) * (_t129) - (this.dX * _t126)) - ((this.dY) * (_t128) + (this.dZ * _t127));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = java.lang.Math.fma(_t43, _t25, _t22);
        float _t45 = java.lang.Math.fma(_t43, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t43, _t24, _t21);
        return lookAlong_degenerate_s82df72c1_1_fma((FloatDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t44 = ((_t43) * (_t25) + (_t22));
        float _t45 = ((_t43) * (_t26) + (_t23));
        float _t46 = ((_t43) * (_t24) + (_t21));
        return lookAlong_degenerate_s82df72c1_1_mulAdd((FloatDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_1_fma(FloatDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = java.lang.Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = java.lang.Math.fma(_t55, _t55, java.lang.Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_s82df72c1_2_fma(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_1_mulAdd(FloatDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = ((_t45) * (_t24) - (_t46 * _t26));
        float _t61 = ((_t55) * (_t55) + (((_t56) * (_t56) + (_t57 * _t57))));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(((_t36) * (_t36) + (((_t37) * (_t37) + (_t41 * _t41))))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_s82df72c1_2_mulAdd(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_2_fma(FloatDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s82df72c1_3_fma(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_2_mulAdd(FloatDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s82df72c1_3_mulAdd(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_3_fma(FloatDualQuatImpl d, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
        d.rX = java.lang.Math.fma(_rd0, _t151, this.rW * _t148) + java.lang.Math.fma(_rd1, _t149, -(_rd2 * _t150));
        d.rY = java.lang.Math.fma(_rd1, _t151, _rd2 * _t148) + java.lang.Math.fma(this.rW, _t150, -(_rd0 * _t149));
        return lookAlong_sf6a57214_3_fma(d, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_3_mulAdd(FloatDualQuatImpl d, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
        d.rX = ((_rd0) * (_t151) + (this.rW * _t148)) + ((_rd1) * (_t149) - (_rd2 * _t150));
        d.rY = ((_rd1) * (_t151) + (_rd2 * _t148)) + ((this.rW) * (_t150) - (_rd0 * _t149));
        return lookAlong_sf6a57214_3_mulAdd(d, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = java.lang.Math.fma(_t43, _t25, _t22);
        float _t45 = java.lang.Math.fma(_t43, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t43, _t24, _t21);
        return lookAlong_degenerate_s34d9c4c0_1_fma((DoubleDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t44 = ((_t43) * (_t25) + (_t22));
        float _t45 = ((_t43) * (_t26) + (_t23));
        float _t46 = ((_t43) * (_t24) + (_t21));
        return lookAlong_degenerate_s34d9c4c0_1_mulAdd((DoubleDualQuatImpl) dest, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_1_fma(DoubleDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = java.lang.Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = java.lang.Math.fma(_t55, _t55, java.lang.Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_s34d9c4c0_2_fma(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_1_mulAdd(DoubleDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = ((_t45) * (_t24) - (_t46 * _t26));
        float _t61 = ((_t55) * (_t55) + (((_t56) * (_t56) + (_t57 * _t57))));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(((_t36) * (_t36) + (((_t37) * (_t37) + (_t41 * _t41))))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_s34d9c4c0_2_mulAdd(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_2_fma(DoubleDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s34d9c4c0_3_fma(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_2_mulAdd(DoubleDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s34d9c4c0_3_mulAdd(d, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_3_fma(DoubleDualQuatImpl d, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        d.rX = java.lang.Math.fma(this.rX, _t151, this.rW * _t148) + java.lang.Math.fma(this.rY, _t149, -(this.rZ * _t150));
        d.rY = java.lang.Math.fma(this.rY, _t151, this.rZ * _t148) + java.lang.Math.fma(this.rW, _t150, -(this.rX * _t149));
        return lookAlong_degenerate_s34d9c4c0_4_fma(d, _t148, _t149, _t150, _t151);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_3_mulAdd(DoubleDualQuatImpl d, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        d.rX = ((this.rX) * (_t151) + (this.rW * _t148)) + ((this.rY) * (_t149) - (this.rZ * _t150));
        d.rY = ((this.rY) * (_t151) + (this.rZ * _t148)) + ((this.rW) * (_t150) - (this.rX * _t149));
        return lookAlong_degenerate_s34d9c4c0_4_mulAdd(d, _t148, _t149, _t150, _t151);
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_4_fma(DoubleDualQuatImpl d, float _t148, float _t149, float _t150, float _t151) {
        d.rZ = java.lang.Math.fma(this.rX, _t150, this.rW * _t149) + java.lang.Math.fma(this.rZ, _t151, -(this.rY * _t148));
        d.rW = java.lang.Math.fma(this.rW, _t151, -(this.rX * _t148)) - java.lang.Math.fma(this.rY, _t150, this.rZ * _t149);
        d.dX = java.lang.Math.fma(this.dX, _t151, this.dW * _t148) + java.lang.Math.fma(this.dY, _t149, -(this.dZ * _t150));
        d.dY = java.lang.Math.fma(this.dY, _t151, this.dZ * _t148) + java.lang.Math.fma(this.dW, _t150, -(this.dX * _t149));
        d.dZ = java.lang.Math.fma(this.dX, _t150, this.dW * _t149) + java.lang.Math.fma(this.dZ, _t151, -(this.dY * _t148));
        d.dW = java.lang.Math.fma(this.dW, _t151, -(this.dX * _t148)) - java.lang.Math.fma(this.dY, _t150, this.dZ * _t149);
        return d;
    }

    /** Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_4_mulAdd(DoubleDualQuatImpl d, float _t148, float _t149, float _t150, float _t151) {
        d.rZ = ((this.rX) * (_t150) + (this.rW * _t149)) + ((this.rZ) * (_t151) - (this.rY * _t148));
        d.rW = ((this.rW) * (_t151) - (this.rX * _t148)) - ((this.rY) * (_t150) + (this.rZ * _t149));
        d.dX = ((this.dX) * (_t151) + (this.dW * _t148)) + ((this.dY) * (_t149) - (this.dZ * _t150));
        d.dY = ((this.dY) * (_t151) + (this.dZ * _t148)) + ((this.dW) * (_t150) - (this.dX * _t149));
        d.dZ = ((this.dX) * (_t150) + (this.dW * _t149)) + ((this.dZ) * (_t151) - (this.dY * _t148));
        d.dW = ((this.dW) * (_t151) - (this.dX * _t148)) - ((this.dY) * (_t150) + (this.dZ * _t149));
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
    public @Mutated FloatDualQuat makeRotationAxis(float angle, Float3R axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    public @Mutated FloatDualQuat makeRotationLookAlong(Float3R dir, Float3R up) {
        float dirX = dir.x();
        float dirY = dir.y();
        float dirZ = dir.z();
        float upX = up.x();
        float upY = up.y();
        float upZ = up.z();
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
    @Mutated public FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Math.useFma()) return makeRotationLookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationLookAlong_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        return makeRotationLookAlong_s25627a91_1_fma(dirX, dirY, dirZ, this, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45, _t39 * _t45, java.lang.Math.fma(-_t37, _t45, 1.0f));
    }

    /** {@code makeRotationLookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationLookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t28 = ((-dirY) * (_sp0) + (upY));
        float _t29 = ((-dirZ) * (_sp0) + (upZ));
        float _t30 = ((-dirX) * (_sp0) + (upX));
        float _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        float _t38 = ((dirY) * (_t30) - (dirX * _t28));
        float _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        float _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        return makeRotationLookAlong_s25627a91_1_mulAdd(dirX, dirY, dirZ, this, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45, _t39 * _t45, ((-_t37) * (_t45) + (1.0f)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_1_fma(float dirX, float dirY, float dirZ, FloatDualQuatImpl d, float _t16, float _t37, float _t39, float _t45, float _t1, float _t17, float _t18, float _t19, float _t20, float _t22, float _t46, float _t47, float _t48, float _t50) {
        float _t63 = java.lang.Math.fma(_t17, _t46, -(_t18 * _t47));
        float _t64 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        float _t82 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s25627a91_2(d, _t17, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), _t63, java.lang.Math.fma(dirY, _t16, _t64), java.lang.Math.fma(-dirY, _t16, _t64), java.lang.Math.max(_t63, _t17), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)), _t78, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_1_mulAdd(float dirX, float dirY, float dirZ, FloatDualQuatImpl d, float _t16, float _t37, float _t39, float _t45, float _t1, float _t17, float _t18, float _t19, float _t20, float _t22, float _t46, float _t47, float _t48, float _t50) {
        float _t63 = ((_t17) * (_t46) - (_t18 * _t47));
        float _t64 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        float _t82 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return makeRotationLookAlong_s25627a91_2(d, _t17, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), _t63, ((dirY) * (_t16) + (_t64)), ((-dirY) * (_t16) + (_t64)), java.lang.Math.max(_t63, _t17), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), ((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))), _t78, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_2(FloatDualQuatImpl d, float _t17, float _t46, float _t51, float _t56, float _t63, float _t69, float _t70, float _t71, float _t74, float _t75, float _t77, float _t78, float _sp1, float _t80, float _t81, float _t82, float _sp3, float _sp4, float _sp2) {
        d.rX = _t77 > 0.0f ? _sp1 * _t70 : _t46 > _t71 ? 0.5f * (float) java.lang.Math.sqrt(_t80) : _t63 > _t17 ? _sp2 * _t74 : _sp3 * _t51;
        d.rY = _t77 > 0.0f ? _sp1 * _t56 : _t46 > _t71 ? _sp4 * _t74 : _t63 > _t17 ? 0.5f * (float) java.lang.Math.sqrt(_t82) : _sp3 * _t69;
        d.rZ = _t77 > 0.0f ? _sp1 * _t75 : _t46 > _t71 ? _sp4 * _t51 : _t63 > _t17 ? _sp2 * _t69 : 0.5f * (float) java.lang.Math.sqrt(_t81);
        d.rW = _t77 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t78) : _t46 > _t71 ? _sp4 * _t70 : _t63 > _t17 ? _sp2 * _t56 : _sp3 * _t75;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = java.lang.Math.fma(_t43, _t25, _t22);
        float _t45 = java.lang.Math.fma(_t43, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t43, _t24, _t21);
        return makeRotationLookAlong_degenerate_s1ae3d4da_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t44 = ((_t43) * (_t25) + (_t22));
        float _t45 = ((_t43) * (_t26) + (_t23));
        float _t46 = ((_t43) * (_t24) + (_t21));
        return makeRotationLookAlong_degenerate_s1ae3d4da_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_1_fma(FloatDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = java.lang.Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = java.lang.Math.fma(_t55, _t55, java.lang.Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t87 = java.lang.Math.fma(_t69, _t24, -(_t68 * _t25));
        return makeRotationLookAlong_degenerate_s1ae3d4da_2_fma(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), _t87, java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.max(_t87, _t24), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_1_mulAdd(FloatDualQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
        float _t57 = ((_t45) * (_t24) - (_t46 * _t26));
        float _t61 = ((_t55) * (_t55) + (((_t56) * (_t56) + (_t57 * _t57))));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(((_t36) * (_t36) + (((_t37) * (_t37) + (_t41 * _t41))))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t87 = ((_t69) * (_t24) - (_t68 * _t25));
        return makeRotationLookAlong_degenerate_s1ae3d4da_2_mulAdd(d, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), _t87, ((_t68) * (_t26) - (_t72 * _t24)), java.lang.Math.max(_t87, _t24), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_2_fma(FloatDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t93, float _t95, float _t96, float _t97) {
        float _t98 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31)));
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98));
        float _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _t106 = java.lang.Math.fma(_t66, _t64, _t91);
        d.rX = _t97 > 0.0f ? _sp0 * _t96 : _t69 > _t93 ? 0.5f * (float) java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_s1ae3d4da_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_2_mulAdd(FloatDualQuatImpl d, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t93, float _t95, float _t96, float _t97) {
        float _t98 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31))))));
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98));
        float _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _t106 = ((_t66) * (_t64) + (_t91));
        d.rX = _t97 > 0.0f ? _sp0 * _t96 : _t69 > _t93 ? 0.5f * (float) java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_s1ae3d4da_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_3(FloatDualQuatImpl d, float _t24, float _t69, float _t73, float _t76, float _t87, float _t93, float _t95, float _t96, float _t97, float _t98, float _sp0, float _t101, float _t102, float _sp3, float _sp1, float _sp2, float _t106, float _t107) {
        d.rY = _t97 > 0.0f ? _sp0 * _t76 : _t69 > _t93 ? _sp3 * _t106 : _t87 > _t24 ? 0.5f * (float) java.lang.Math.sqrt(_t101) : _sp2 * _t95;
        d.rZ = _t97 > 0.0f ? _sp0 * _t107 : _t69 > _t93 ? _sp3 * _t73 : _t87 > _t24 ? _sp1 * _t95 : 0.5f * (float) java.lang.Math.sqrt(_t102);
        d.rW = _t97 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t98) : _t69 > _t93 ? _sp3 * _t96 : _t87 > _t24 ? _sp1 * _t76 : _sp2 * _t107;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.rX = _t1;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t9, _t7, _t12 * _t5);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.rX = 0.0f;
        this.rY = _t1;
        this.rZ = 0.0f;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t5, _t2);
        float _t7 = Math.cosFromSin(_t3, _t0);
        float _t8 = Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        this.rX = Math.fma(_t9, _t6, _t12 * _t5);
        this.rY = Math.fma(_t10, _t6, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t6, -(_t10 * _t5));
        this.rW = Math.fma(_t12, _t6, -(_t9 * _t5));
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t9, _t7, _t12 * _t5);
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        this.rX = Math.fma(_t12, _t5, -(_t9 * _t8));
        this.rY = Math.fma(_t10, _t8, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t8, -(_t10 * _t5));
        this.rW = Math.fma(_t9, _t5, _t12 * _t8);
        this.dX = 0.0f;
        this.dY = 0.0f;
        this.dZ = 0.0f;
        this.dW = 0.0f;
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
    public FloatDualQuat preRotate(FloatQuatR rotation, @Mutated FloatDualQuat dest) {
        return preRotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat preRotate(FloatQuatR rotation, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat preRotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
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
     * Pre-multiply the rotation represented by the quaternion ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) onto this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat preRotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationY, this.rZ, -(rotationZ * this.rY));
        d.rY = Math.fma(rotationY, this.rW, rotationZ * this.rX) + Math.fma(rotationW, this.rY, -(rotationX * this.rZ));
        d.rZ = Math.fma(rotationX, this.rY, rotationW * this.rZ) + Math.fma(rotationZ, this.rW, -(rotationY * this.rX));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        d.dX = Math.fma(rotationX, this.dW, rotationW * this.dX) + Math.fma(rotationY, this.dZ, -(rotationZ * this.dY));
        d.dY = Math.fma(rotationY, this.dW, rotationZ * this.dX) + Math.fma(rotationW, this.dY, -(rotationX * this.dZ));
        d.dZ = Math.fma(rotationX, this.dY, rotationW * this.dZ) + Math.fma(rotationZ, this.dW, -(rotationY * this.dX));
        d.dW = Math.fma(rotationW, this.dW, -(rotationX * this.dX)) - Math.fma(rotationY, this.dY, rotationZ * this.dZ);
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
    public FloatDualQuat rotate(FloatQuatR rotation, @Mutated FloatDualQuat dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat rotate(FloatQuatR rotation, @Mutated DoubleDualQuat dest) {
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
    public FloatDualQuat rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.dX;
        float _rd4 = this.dY;
        float _rd5 = this.dZ;
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
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        d.rY = Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX));
        d.rZ = Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        d.dX = Math.fma(rotationX, this.dW, rotationW * this.dX) + Math.fma(rotationZ, this.dY, -(rotationY * this.dZ));
        d.dY = Math.fma(rotationX, this.dZ, rotationW * this.dY) + Math.fma(rotationY, this.dW, -(rotationZ * this.dX));
        d.dZ = Math.fma(rotationY, this.dX, rotationZ * this.dW) + Math.fma(rotationW, this.dZ, -(rotationX * this.dY));
        d.dW = Math.fma(rotationW, this.dW, -(rotationX * this.dX)) - Math.fma(rotationY, this.dY, rotationZ * this.dZ);
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
    public FloatDualQuat rotateAxis(float angle, Float3R axis, @Mutated FloatDualQuat dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleDualQuat rotateAxis(float angle, Float3R axis, @Mutated DoubleDualQuat dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }

    /**
     * Private store group 1 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s61270cf0_c1_fma(FloatDualQuatImpl _dst, float _r4, float _t5, float _r5, float _t2, float _r6, float _t3, float _r7, float _t4) {
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
    private void rotateAxis_s61270cf0_c1_mulAdd(FloatDualQuatImpl _dst, float _r4, float _t5, float _r5, float _t2, float _r6, float _t3, float _r7, float _t4) {
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
    public FloatDualQuat rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatDualQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        if (Math.useFma()) preMul_s447e38b9_c0_fma(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4); else preMul_s447e38b9_c0_mulAdd(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4);
        if (Math.useFma()) rotateAxis_s61270cf0_c1_fma(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4); else rotateAxis_s61270cf0_c1_mulAdd(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4);
        return d;
    }

    /**
     * Private store group 1 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s3d95cac1_c1_fma(DoubleDualQuatImpl _dst, float _r4, float _t5, float _r5, float _t2, float _r6, float _t3, float _r7, float _t4) {
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
    private void rotateAxis_s3d95cac1_c1_mulAdd(DoubleDualQuatImpl _dst, float _r4, float _t5, float _r5, float _t2, float _r6, float _t3, float _r7, float _t4) {
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
    public DoubleDualQuat rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleDualQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        if (Math.useFma()) preMul_s45241818_c0_fma(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4); else preMul_s45241818_c0_mulAdd(d, _r0, _t5, _r1, _t2, _r2, _t3, _r3, _t4);
        if (Math.useFma()) rotateAxis_s3d95cac1_c1_fma(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4); else rotateAxis_s3d95cac1_c1_mulAdd(d, _r4, _t5, _r5, _t2, _r6, _t3, _r7, _t4);
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
    public FloatDualQuat rotateX(float angle, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.dX;
        float _rd3 = this.dY;
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
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleDualQuat rotateX(float angle, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.rX = Math.fma(this.rX, _t2, this.rW * _t1);
        d.rY = Math.fma(this.rY, _t2, this.rZ * _t1);
        d.rZ = Math.fma(this.rZ, _t2, -(this.rY * _t1));
        d.rW = Math.fma(this.rW, _t2, -(this.rX * _t1));
        d.dX = Math.fma(this.dX, _t2, this.dW * _t1);
        d.dY = Math.fma(this.dY, _t2, this.dZ * _t1);
        d.dZ = Math.fma(this.dZ, _t2, -(this.dY * _t1));
        d.dW = Math.fma(this.dW, _t2, -(this.dX * _t1));
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s106d101b_tail_fma(FloatDualQuatImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _t20, float _r3, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        preMul_s447e38b9_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s61270cf0_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s106d101b_tail_mulAdd(FloatDualQuatImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _t20, float _r3, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        preMul_s447e38b9_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s61270cf0_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
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
    public FloatDualQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s106d101b_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s106d101b_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, _r4, _r5, _r6, _r7);
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s77102cf6_tail_fma(DoubleDualQuatImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _t20, float _r3, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        preMul_s45241818_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s3d95cac1_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s77102cf6_tail_mulAdd(DoubleDualQuatImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _t20, float _r3, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        preMul_s45241818_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t20, _r3, _t22);
        rotateAxis_s3d95cac1_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t20, _r7, _t22);
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
    public DoubleDualQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s77102cf6_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s77102cf6_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, _r4, _r5, _r6, _r7);
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
    public FloatDualQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s106d101b_tail_fma(d, _t12, _t5, _t9, _t8, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s106d101b_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, _r4, _r5, _r6, _r7);
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
    public DoubleDualQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s77102cf6_tail_fma(d, _t12, _t5, _t9, _t8, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s77102cf6_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, _r4, _r5, _r6, _r7);
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
    public FloatDualQuat rotateY(float angle, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.dX;
        float _rd3 = this.dY;
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
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleDualQuat rotateY(float angle, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.rX = Math.fma(this.rX, _t2, -(this.rZ * _t1));
        d.rY = Math.fma(this.rY, _t2, this.rW * _t1);
        d.rZ = Math.fma(this.rX, _t1, this.rZ * _t2);
        d.rW = Math.fma(this.rW, _t2, -(this.rY * _t1));
        d.dX = Math.fma(this.dX, _t2, -(this.dZ * _t1));
        d.dY = Math.fma(this.dY, _t2, this.dW * _t1);
        d.dZ = Math.fma(this.dX, _t1, this.dZ * _t2);
        d.dW = Math.fma(this.dW, _t2, -(this.dY * _t1));
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
    public FloatDualQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s106d101b_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s106d101b_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, _r4, _r5, _r6, _r7);
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
    public DoubleDualQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateXYZ_s77102cf6_tail_fma(d, _t11, _t8, _t10, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, _r4, _r5, _r6, _r7); else rotateXYZ_s77102cf6_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, _r4, _r5, _r6, _r7);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s5eed395b_tail_fma(FloatDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s447e38b9_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s61270cf0_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s5eed395b_tail_mulAdd(FloatDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s447e38b9_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s61270cf0_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
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
    public FloatDualQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateYZX_s5eed395b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s5eed395b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s78952bb6_tail_fma(DoubleDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s45241818_c0_fma(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s3d95cac1_c1_fma(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s78952bb6_tail_mulAdd(DoubleDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _t19, float _r2, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s45241818_c0_mulAdd(_dst, _r0, _t21, _r1, _t19, _r2, _t22, _r3, _t20);
        rotateAxis_s3d95cac1_c1_mulAdd(_dst, _r4, _t21, _r5, _t19, _r6, _t22, _r7, _t20);
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
    public DoubleDualQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateYZX_s78952bb6_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s78952bb6_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
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
    public FloatDualQuat rotateZ(float angle, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rZ;
        float _rd2 = this.dX;
        float _rd3 = this.dZ;
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


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleDualQuat rotateZ(float angle, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.rX = Math.fma(this.rX, _t2, this.rY * _t1);
        d.rY = Math.fma(this.rY, _t2, -(this.rX * _t1));
        d.rZ = Math.fma(this.rZ, _t2, this.rW * _t1);
        d.rW = Math.fma(this.rW, _t2, -(this.rZ * _t1));
        d.dX = Math.fma(this.dX, _t2, this.dY * _t1);
        d.dY = Math.fma(this.dY, _t2, -(this.dX * _t1));
        d.dZ = Math.fma(this.dZ, _t2, this.dW * _t1);
        d.dW = Math.fma(this.dW, _t2, -(this.dZ * _t1));
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s66ae295b_tail_fma(FloatDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _r2, float _t19, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s447e38b9_c0_fma(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s61270cf0_c1_fma(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s66ae295b_tail_mulAdd(FloatDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _r2, float _t19, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s447e38b9_c0_mulAdd(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s61270cf0_c1_mulAdd(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
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
    public FloatDualQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateZXY_s66ae295b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r4, _r5, _r6, _r7); else rotateZXY_s66ae295b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)), _r4, _r5, _r6, _r7);
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s68f23bb6_tail_fma(DoubleDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _r2, float _t19, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        preMul_s45241818_c0_fma(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s3d95cac1_c1_fma(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s68f23bb6_tail_mulAdd(DoubleDualQuatImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _t21, float _r1, float _r2, float _t19, float _r3, float _t20, float _r4, float _r5, float _r6, float _r7) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        preMul_s45241818_c0_mulAdd(_dst, _r0, _t21, _r1, _t22, _r2, _t19, _r3, _t20);
        rotateAxis_s3d95cac1_c1_mulAdd(_dst, _r4, _t21, _r5, _t22, _r6, _t19, _r7, _t20);
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
    public DoubleDualQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        if (Math.useFma()) rotateZXY_s68f23bb6_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r4, _r5, _r6, _r7); else rotateZXY_s68f23bb6_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)), _r4, _r5, _r6, _r7);
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
    public FloatDualQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateYZX_s5eed395b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s5eed395b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
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
    public DoubleDualQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rW;
        float _r2 = this.rY;
        float _r3 = this.rZ;
        float _r4 = this.dX;
        float _r5 = this.dW;
        float _r6 = this.dY;
        float _r7 = this.dZ;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        if (Math.useFma()) rotateYZX_s78952bb6_tail_fma(d, _t10, _t8, _t11, _t5, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r4, _r5, _r6, _r7); else rotateYZX_s78952bb6_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, _r3, ((_t11) * (_t8) + (_t10 * _t5)), _r4, _r5, _r6, _r7);
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
    public FloatDualQuat translate(Float3R translation, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translation.z();
        float _t4 = 0.5f * translation.y();
        float _t5 = 0.5f * translation.x();
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.rW;
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
     * Apply a translation by {@code translation} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat translate(Float3R translation, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translation.z();
        float _t4 = 0.5f * translation.y();
        float _t5 = 0.5f * translation.x();
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = Math.fma(this.rY, _t3, Math.fma(_t0, _t4, Math.fma(this.rW, _t5, this.dX)));
        d.dY = Math.fma(this.rW, _t4, Math.fma(_t1, _t3, Math.fma(this.rZ, _t5, this.dY)));
        d.dZ = Math.fma(this.rX, _t4, Math.fma(this.rW, _t3, Math.fma(_t2, _t5, this.dZ)));
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
    public FloatDualQuat translate(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        float _rd3 = this.rW;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleDualQuat translate(float translationX, float translationY, float translationZ, @Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = Math.fma(this.rY, _t3, Math.fma(_t0, _t4, Math.fma(this.rW, _t5, this.dX)));
        d.dY = Math.fma(this.rW, _t4, Math.fma(_t1, _t3, Math.fma(this.rZ, _t5, this.dY)));
        d.dZ = Math.fma(this.rX, _t4, Math.fma(this.rW, _t3, Math.fma(_t2, _t5, this.dZ)));
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
    public Float3 transform(Float3R p, @Mutated Float3 dest) {
        return transform(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Transform {@code p} by this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Float3R p, @Mutated Double3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0f * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0f * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_mulAdd(d, _r1, 2.0f * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0f * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0f * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        }
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
    public Float3 transform(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(pY, this.rX, -(pX * this.rY));
        float _t10 = 2.0f * Math.fma(pX, this.rZ, -(pZ * this.rX));
        float _t11 = 2.0f * Math.fma(pZ, this.rY, -(pY * this.rZ));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, Math.fma(2.0f, Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)), pX))));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, Math.fma(2.0f, Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)), pY))));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, Math.fma(2.0f, Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)), pZ))));
        return d;
    }

    /** Private store group 0 of {@code transform}: computes and stores it; reached only through it. */
    private void transform_s496e6cff_c0_fma(Double3Impl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float _r5, float _r6, float _r0, float _r7, float pX, float pY, float pZ) {
        _dst.x = java.lang.Math.fma(_r1, _t9, java.lang.Math.fma(-_r2, _t10, java.lang.Math.fma(_r3, _t11, java.lang.Math.fma(2.0f, java.lang.Math.fma(_r1, _r4, -(_r2 * _r5)) + java.lang.Math.fma(_r3, _r6, -(_r0 * _r7)), pX))));
        _dst.y = java.lang.Math.fma(_r2, _t11, java.lang.Math.fma(-_r0, _t9, java.lang.Math.fma(_r3, _t10, java.lang.Math.fma(2.0f, java.lang.Math.fma(_r2, _r6, -(_r0 * _r4)) + java.lang.Math.fma(_r3, _r5, -(_r1 * _r7)), pY))));
        _dst.z = java.lang.Math.fma(_r0, _t10, java.lang.Math.fma(-_r1, _t11, java.lang.Math.fma(_r3, _t9, java.lang.Math.fma(2.0f, java.lang.Math.fma(_r0, _r5, -(_r1 * _r6)) + java.lang.Math.fma(_r3, _r4, -(_r2 * _r7)), pZ))));
    }

    /** Private store group 0 of {@code transform}: computes and stores it; reached only through it. */
    private void transform_s496e6cff_c0_mulAdd(Double3Impl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float _r5, float _r6, float _r0, float _r7, float pX, float pY, float pZ) {
        _dst.x = ((_r1) * (_t9) + (((-_r2) * (_t10) + (((_r3) * (_t11) + (((2.0f) * (((_r1) * (_r4) - (_r2 * _r5)) + ((_r3) * (_r6) - (_r0 * _r7))) + (pX))))))));
        _dst.y = ((_r2) * (_t11) + (((-_r0) * (_t9) + (((_r3) * (_t10) + (((2.0f) * (((_r2) * (_r6) - (_r0 * _r4)) + ((_r3) * (_r5) - (_r1 * _r7))) + (pY))))))));
        _dst.z = ((_r0) * (_t10) + (((-_r1) * (_t11) + (((_r3) * (_t9) + (((2.0f) * (((_r0) * (_r5) - (_r1 * _r6)) + ((_r3) * (_r4) - (_r2 * _r7))) + (pZ))))))));
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(float pX, float pY, float pZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0f * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0f * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_mulAdd(d, _r1, 2.0f * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0f * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0f * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
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
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Float3R v, @Mutated Double3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transformDirection(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(float vX, float vY, float vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Float3R v, @Mutated Double3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
    public Float3 transformInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Transform {@code p} by the inverse of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Float3R p, @Mutated Double3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s36805c6c_c0_fma(Float3Impl _dst, float _r6, float _t33, float _r2, float _t34, float _r4, float _t35, float _t22, float _r0, float _t23, float _t21) {
        _dst.x = java.lang.Math.fma(_r6, _t33, java.lang.Math.fma(-_r2, _t34, java.lang.Math.fma(_r4, _t35, _t22)));
        _dst.y = java.lang.Math.fma(_r0, _t34, java.lang.Math.fma(-_r6, _t35, java.lang.Math.fma(_r4, _t33, _t23)));
        _dst.z = java.lang.Math.fma(_r2, _t35, java.lang.Math.fma(-_r0, _t33, java.lang.Math.fma(_r4, _t34, _t21)));
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s36805c6c_c0_mulAdd(Float3Impl _dst, float _r6, float _t33, float _r2, float _t34, float _r4, float _t35, float _t22, float _r0, float _t23, float _t21) {
        _dst.x = ((_r6) * (_t33) + (((-_r2) * (_t34) + (((_r4) * (_t35) + (_t22))))));
        _dst.y = ((_r0) * (_t34) + (((-_r6) * (_t35) + (((_r4) * (_t33) + (_t23))))));
        _dst.z = ((_r2) * (_t35) + (((-_r0) * (_t33) + (((_r4) * (_t34) + (_t21))))));
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.dY;
        float _r2 = this.rY;
        float _r3 = this.dX;
        float _r4 = this.rW;
        float _r5 = this.dZ;
        float _r6 = this.rZ;
        float _r7 = this.dW;
        float _t21 = Math.fma(-2.0f, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        float _t22 = Math.fma(-2.0f, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        float _t23 = Math.fma(-2.0f, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s36805c6c_c0_fma(d, _r6, 2.0f * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), _r2, 2.0f * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, 2.0f * java.lang.Math.fma(_r6, _t23, -(_r2 * _t21)), _t22, _r0, _t23, _t21); else transformInverse_s36805c6c_c0_mulAdd(d, _r6, 2.0f * ((_r0) * (_t21) - (_r6 * _t22)), _r2, 2.0f * ((_r2) * (_t22) - (_r0 * _t23)), _r4, 2.0f * ((_r6) * (_t23) - (_r2 * _t21)), _t22, _r0, _t23, _t21);
        return d;
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s496e6cff_c0_fma(Double3Impl _dst, float _r6, float _t33, float _r2, float _t34, float _r4, float _t35, float _t22, float _r0, float _t23, float _t21) {
        _dst.x = java.lang.Math.fma(_r6, _t33, java.lang.Math.fma(-_r2, _t34, java.lang.Math.fma(_r4, _t35, _t22)));
        _dst.y = java.lang.Math.fma(_r0, _t34, java.lang.Math.fma(-_r6, _t35, java.lang.Math.fma(_r4, _t33, _t23)));
        _dst.z = java.lang.Math.fma(_r2, _t35, java.lang.Math.fma(-_r0, _t33, java.lang.Math.fma(_r4, _t34, _t21)));
    }

    /** Private store group 0 of {@code transformInverse}: computes and stores it; reached only through it. */
    private void transformInverse_s496e6cff_c0_mulAdd(Double3Impl _dst, float _r6, float _t33, float _r2, float _t34, float _r4, float _t35, float _t22, float _r0, float _t23, float _t21) {
        _dst.x = ((_r6) * (_t33) + (((-_r2) * (_t34) + (((_r4) * (_t35) + (_t22))))));
        _dst.y = ((_r0) * (_t34) + (((-_r6) * (_t35) + (((_r4) * (_t33) + (_t23))))));
        _dst.z = ((_r2) * (_t35) + (((-_r0) * (_t33) + (((_r4) * (_t34) + (_t21))))));
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.dY;
        float _r2 = this.rY;
        float _r3 = this.dX;
        float _r4 = this.rW;
        float _r5 = this.dZ;
        float _r6 = this.rZ;
        float _r7 = this.dW;
        float _t21 = Math.fma(-2.0f, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        float _t22 = Math.fma(-2.0f, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        float _t23 = Math.fma(-2.0f, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s496e6cff_c0_fma(d, _r6, 2.0f * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), _r2, 2.0f * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, 2.0f * java.lang.Math.fma(_r6, _t23, -(_r2 * _t21)), _t22, _r0, _t23, _t21); else transformInverse_s496e6cff_c0_mulAdd(d, _r6, 2.0f * ((_r0) * (_t21) - (_r6 * _t22)), _r2, 2.0f * ((_r2) * (_t22) - (_r0 * _t23)), _r4, 2.0f * ((_r6) * (_t23) - (_r2 * _t21)), _t22, _r0, _t23, _t21);
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
    public Float3 transformPosition(Float3R p, @Mutated Float3 dest) {
        return transform(p, dest);
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Float3R p, @Mutated Double3 dest) {
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
    public Float3 transformPosition(float pX, float pY, float pZ, @Mutated Float3 dest) {
        return transform(pX, pY, pZ, dest);
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(float pX, float pY, float pZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(pY, _r0, -(pX * _r1)), _r2, 2.0f * java.lang.Math.fma(pX, _r2, -(pZ * _r0)), _r3, 2.0f * java.lang.Math.fma(pZ, _r1, -(pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.dZ;
            float _r5 = this.dY;
            float _r6 = this.dX;
            float _r7 = this.dW;
            transform_s496e6cff_c0_mulAdd(d, _r1, 2.0f * ((pY) * (_r0) - (pX * _r1)), _r2, 2.0f * ((pX) * (_r2) - (pZ * _r0)), _r3, 2.0f * ((pZ) * (_r1) - (pY * _r2)), _r4, _r5, _r6, _r0, _r7, pX, pY, pZ);
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
    public Float3 transformPositionInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Float3R p, @Mutated Double3 dest) {
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
    public Float3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.dY;
        float _r2 = this.rY;
        float _r3 = this.dX;
        float _r4 = this.rW;
        float _r5 = this.dZ;
        float _r6 = this.rZ;
        float _r7 = this.dW;
        float _t21 = Math.fma(-2.0f, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        float _t22 = Math.fma(-2.0f, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        float _t23 = Math.fma(-2.0f, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s36805c6c_c0_fma(d, _r6, 2.0f * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), _r2, 2.0f * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, 2.0f * java.lang.Math.fma(_r6, _t23, -(_r2 * _t21)), _t22, _r0, _t23, _t21); else transformInverse_s36805c6c_c0_mulAdd(d, _r6, 2.0f * ((_r0) * (_t21) - (_r6 * _t22)), _r2, 2.0f * ((_r2) * (_t22) - (_r0 * _t23)), _r4, 2.0f * ((_r6) * (_t23) - (_r2 * _t21)), _t22, _r0, _t23, _t21);
        return d;
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _r0 = this.rX;
        float _r1 = this.dY;
        float _r2 = this.rY;
        float _r3 = this.dX;
        float _r4 = this.rW;
        float _r5 = this.dZ;
        float _r6 = this.rZ;
        float _r7 = this.dW;
        float _t21 = Math.fma(-2.0f, Math.fma(_r0, _r1, -(_r2 * _r3)) + Math.fma(_r4, _r5, -(_r6 * _r7)), pZ);
        float _t22 = Math.fma(-2.0f, Math.fma(_r2, _r5, -(_r6 * _r1)) + Math.fma(_r4, _r3, -(_r0 * _r7)), pX);
        float _t23 = Math.fma(-2.0f, Math.fma(_r6, _r3, -(_r0 * _r5)) + Math.fma(_r4, _r1, -(_r2 * _r7)), pY);
        if (Math.useFma()) transformInverse_s496e6cff_c0_fma(d, _r6, 2.0f * java.lang.Math.fma(_r0, _t21, -(_r6 * _t22)), _r2, 2.0f * java.lang.Math.fma(_r2, _t22, -(_r0 * _t23)), _r4, 2.0f * java.lang.Math.fma(_r6, _t23, -(_r2 * _t21)), _t22, _r0, _t23, _t21); else transformInverse_s496e6cff_c0_mulAdd(d, _r6, 2.0f * ((_r0) * (_t21) - (_r6 * _t22)), _r2, 2.0f * ((_r2) * (_t22) - (_r0 * _t23)), _r4, 2.0f * ((_r6) * (_t23) - (_r2 * _t21)), _t22, _r0, _t23, _t21);
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
    public Float3 transformVector(Float3R v, @Mutated Float3 dest) {
        return transformDirection(v, dest);
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(Float3R v, @Mutated Double3 dest) {
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
    public Float3 transformVector(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(float vX, float vY, float vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }

    public float rX() { return this.rX; }
    public float rY() { return this.rY; }
    public float rZ() { return this.rZ; }
    public float rW() { return this.rW; }
    public float dX() { return this.dX; }
    public float dY() { return this.dY; }
    public float dZ() { return this.dZ; }
    public float dW() { return this.dW; }

    @Override public String toString() {
        return "FloatDualQuat(" + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + dX() + ", " + dY() + ", " + dZ() + ", " + dW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatDualQuatImpl)) return false;
        FloatDualQuatImpl o = (FloatDualQuatImpl) obj;
        return Float.floatToIntBits(rX) == Float.floatToIntBits(o.rX)
            && Float.floatToIntBits(rY) == Float.floatToIntBits(o.rY)
            && Float.floatToIntBits(rZ) == Float.floatToIntBits(o.rZ)
            && Float.floatToIntBits(rW) == Float.floatToIntBits(o.rW)
            && Float.floatToIntBits(dX) == Float.floatToIntBits(o.dX)
            && Float.floatToIntBits(dY) == Float.floatToIntBits(o.dY)
            && Float.floatToIntBits(dZ) == Float.floatToIntBits(o.dZ)
            && Float.floatToIntBits(dW) == Float.floatToIntBits(o.dW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(rX);
        h = 31 * h + Float.floatToIntBits(rY);
        h = 31 * h + Float.floatToIntBits(rZ);
        h = 31 * h + Float.floatToIntBits(rW);
        h = 31 * h + Float.floatToIntBits(dX);
        h = 31 * h + Float.floatToIntBits(dY);
        h = 31 * h + Float.floatToIntBits(dZ);
        h = 31 * h + Float.floatToIntBits(dW);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(rX)
            && Float.isFinite(rY)
            && Float.isFinite(rZ)
            && Float.isFinite(rW)
            && Float.isFinite(dX)
            && Float.isFinite(dY)
            && Float.isFinite(dZ)
            && Float.isFinite(dW);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(rX)
            || Float.isNaN(rY)
            || Float.isNaN(rZ)
            || Float.isNaN(rW)
            || Float.isNaN(dX)
            || Float.isNaN(dY)
            || Float.isNaN(dZ)
            || Float.isNaN(dW);
    }

    @Override public boolean equalsEpsilon(FloatDualQuatR other, float epsilon) {
        return java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon
            && java.lang.Math.abs(dX - other.dX()) <= epsilon
            && java.lang.Math.abs(dY - other.dY()) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ()) <= epsilon
            && java.lang.Math.abs(dW - other.dW()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
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
    public @Mutated FloatDualQuat load(float[] src, int offset) {
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
    @Mutated public FloatDualQuat load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatDualQuat loadRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public FloatDualQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatDualQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public FloatDualQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatDualQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
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
    public @Mutated FloatDualQuat load(double[] src, int offset) {
        this.rX = (float) src[offset];
        this.rY = (float) src[offset + 1];
        this.rZ = (float) src[offset + 2];
        this.rW = (float) src[offset + 3];
        this.dX = (float) src[offset + 4];
        this.dY = (float) src[offset + 5];
        this.dZ = (float) src[offset + 6];
        this.dW = (float) src[offset + 7];
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
    @Mutated public FloatDualQuat load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatDualQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public FloatDualQuat loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatDualQuat loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public FloatDualQuat storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatDualQuat loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
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
