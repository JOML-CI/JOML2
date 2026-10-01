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
 * Generated implementation of {@link FloatDualQuat} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatDualQuatImpl implements FloatDualQuat {

    public float[] data;

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
        data = new float[8];
        data[3] = 1;
    }

    public FloatDualQuatImpl(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) {
        float[] dd = this.data = new float[8];
        dd[0] = rX;
        dd[1] = rY;
        dd[2] = rZ;
        dd[3] = rW;
        dd[4] = dX;
        dd[5] = dY;
        dd[6] = dZ;
        dd[7] = dW;
    }

    public FloatDualQuatImpl(FloatDualQuatR src) {
        float[] dd = this.data = new float[8];
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
    public FloatDualQuat add(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat add(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat mul(float scalar, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat negate(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat sub(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat sub(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public @Mutated FloatDualQuat set(FloatDualQuatR v) {
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
        float vDX = v.dX();
        float vDY = v.dY();
        float vDZ = v.dZ();
        float vDW = v.dW();
        float[] dd = this.data;
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
    @Mutated public FloatDualQuat set(float vRX, float vRY, float vRZ, float vRW, float vDX, float vDY, float vDZ, float vDW) {
        float[] dd = this.data;
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
     * Convert this dual quaternion to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDouble(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
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
            float[] dd = this.data;
            float _t0 = -rTZ;
            dd[0] = rRX;
            dd[1] = rRY;
            dd[2] = rRZ;
            dd[3] = rRW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ));
            dd[5] = 0.5f * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -(rTX * rRZ)));
            dd[6] = 0.5f * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -(rTY * rRX)));
            dd[7] = 0.5f * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -(rTX * rRX)));
            return this;
        } else {
            float[] dd = this.data;
            float _t0 = -rTZ;
            dd[0] = rRX;
            dd[1] = rRY;
            dd[2] = rRZ;
            dd[3] = rRW;
            dd[4] = 0.5f * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ))));
            dd[5] = 0.5f * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ))));
            dd[6] = 0.5f * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX))));
            dd[7] = 0.5f * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX))));
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
            float[] dd = this.data;
            float _t0 = -tTZ;
            dd[0] = tRX;
            dd[1] = tRY;
            dd[2] = tRZ;
            dd[3] = tRW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ));
            dd[5] = 0.5f * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -(tTX * tRZ)));
            dd[6] = 0.5f * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -(tTY * tRX)));
            dd[7] = 0.5f * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -(tTX * tRX)));
            return this;
        } else {
            float[] dd = this.data;
            float _t0 = -tTZ;
            dd[0] = tRX;
            dd[1] = tRY;
            dd[2] = tRZ;
            dd[3] = tRW;
            dd[4] = 0.5f * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ))));
            dd[5] = 0.5f * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ))));
            dd[6] = 0.5f * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX))));
            dd[7] = 0.5f * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX))));
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
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = 2.0f * (Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[1] = 2.0f * (Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[2] = 2.0f * (Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = 2.0f * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[1] = 2.0f * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[2] = 2.0f * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[3];
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
    public FloatTransform toTransform(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = 2.0f * (Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[1] = 2.0f * (Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[2] = 2.0f * (Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = 2.0f * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[1] = 2.0f * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[2] = 2.0f * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[3];
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public boolean isUnit(float epsilon) {
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.abs(java.lang.Math.fma(sd[0], sd[0], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[3], sd[3], -1.0f))))) <= epsilon;
        } else {
            float[] sd = this.data;
            return java.lang.Math.abs(((sd[0]) * (sd[0]) + (((sd[1]) * (sd[1]) + (((sd[2]) * (sd[2]) + (((sd[3]) * (sd[3]) - (1.0f))))))))) <= epsilon;
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
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axis.x() * _t2;
        float _t4 = axis.y() * _t2;
        float _t5 = axis.z() * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        dd[0] = _t3;
        dd[1] = _t4;
        dd[2] = _t5;
        dd[3] = _t6;
        dd[4] = 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        dd[5] = 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        dd[6] = 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        dd[7] = 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
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
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisY * _t2;
        float _t5 = axisZ * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        dd[0] = _t3;
        dd[1] = _t4;
        dd[2] = _t5;
        dd[3] = _t6;
        dd[4] = 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5));
        dd[5] = 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5)));
        dd[6] = 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3)));
        dd[7] = 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3)));
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
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            dd[6] = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            dd[5] = 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            dd[6] = 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            dd[7] = 0.5f * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        dd[0] = rotation.x();
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -(rotationZ * translationX)));
            dd[6] = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -(rotationX * translationY)));
            dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -(rotationX * translationX)));
            return this;
        } else {
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX))));
            dd[5] = 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX))));
            dd[6] = 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY))));
            dd[7] = 0.5f * ((-rotationZ) * (translationZ) + (((_t0) * (translationY) - (rotationX * translationX))));
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
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.5f * translationX;
        dd[5] = 0.5f * translationY;
        dd[6] = 0.5f * translationZ;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        dd[4] = 0.5f * translationX;
        dd[5] = 0.5f * translationY;
        dd[6] = 0.5f * translationZ;
        dd[7] = 0.0f;
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
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
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
    public FloatDualQuat dlb(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code dlb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat dlb_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t9 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t14 = java.lang.Math.fma(t, java.lang.Math.fma(otherRX, _t9, -sd[0]), sd[0]);
        float _t15 = java.lang.Math.fma(t, java.lang.Math.fma(otherRY, _t9, -sd[1]), sd[1]);
        float _t16 = java.lang.Math.fma(t, java.lang.Math.fma(otherRZ, _t9, -sd[2]), sd[2]);
        float _t17 = java.lang.Math.fma(t, java.lang.Math.fma(otherRW, _t9, -sd[3]), sd[3]);
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, _t15 * _t15) + java.lang.Math.fma(_t16, _t16, _t17 * _t17)));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -sd[4]), sd[4]) * _t23;
        dd[5] = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -sd[5]), sd[5]) * _t23;
        return dlb_sdd8c0be2_1_fma(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat dlb_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t9 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t14 = ((t) * (((otherRX) * (_t9) - (sd[0]))) + (sd[0]));
        float _t15 = ((t) * (((otherRY) * (_t9) - (sd[1]))) + (sd[1]));
        float _t16 = ((t) * (((otherRZ) * (_t9) - (sd[2]))) + (sd[2]));
        float _t17 = ((t) * (((otherRW) * (_t9) - (sd[3]))) + (sd[3]));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(((_t14) * (_t14) + (_t15 * _t15)) + ((_t16) * (_t16) + (_t17 * _t17))));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = ((t) * (((otherDX) * (_t9) - (sd[4]))) + (sd[4])) * _t23;
        dd[5] = ((t) * (((otherDY) * (_t9) - (sd[5]))) + (sd[5])) * _t23;
        return dlb_sdd8c0be2_1_mulAdd(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat dlb_sdd8c0be2_1_fma(float otherDZ, float otherDW, float t, FloatDualQuat dest, float[] sd, float[] dd, float _t9, float _t23) {
        dd[6] = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -sd[6]), sd[6]) * _t23;
        dd[7] = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -sd[7]), sd[7]) * _t23;
        return dest;
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat dlb_sdd8c0be2_1_mulAdd(float otherDZ, float otherDW, float t, FloatDualQuat dest, float[] sd, float[] dd, float _t9, float _t23) {
        dd[6] = ((t) * (((otherDZ) * (_t9) - (sd[6]))) + (sd[6])) * _t23;
        dd[7] = ((t) * (((otherDW) * (_t9) - (sd[7]))) + (sd[7])) * _t23;
        return dest;
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
        if (Math.useFma()) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code dlb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t9 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t14 = java.lang.Math.fma(t, java.lang.Math.fma(otherRX, _t9, -sd[0]), sd[0]);
        float _t15 = java.lang.Math.fma(t, java.lang.Math.fma(otherRY, _t9, -sd[1]), sd[1]);
        float _t16 = java.lang.Math.fma(t, java.lang.Math.fma(otherRZ, _t9, -sd[2]), sd[2]);
        float _t17 = java.lang.Math.fma(t, java.lang.Math.fma(otherRW, _t9, -sd[3]), sd[3]);
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, _t15 * _t15) + java.lang.Math.fma(_t16, _t16, _t17 * _t17)));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -sd[4]), sd[4]) * _t23;
        dd[5] = java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -sd[5]), sd[5]) * _t23;
        return dlb_s6b8190ed_1_fma(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t9 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t14 = ((t) * (((otherRX) * (_t9) - (sd[0]))) + (sd[0]));
        float _t15 = ((t) * (((otherRY) * (_t9) - (sd[1]))) + (sd[1]));
        float _t16 = ((t) * (((otherRZ) * (_t9) - (sd[2]))) + (sd[2]));
        float _t17 = ((t) * (((otherRW) * (_t9) - (sd[3]))) + (sd[3]));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(((_t14) * (_t14) + (_t15 * _t15)) + ((_t16) * (_t16) + (_t17 * _t17))));
        dd[0] = _t14 * _t23;
        dd[1] = _t15 * _t23;
        dd[2] = _t16 * _t23;
        dd[3] = _t17 * _t23;
        dd[4] = ((t) * (((otherDX) * (_t9) - (sd[4]))) + (sd[4])) * _t23;
        dd[5] = ((t) * (((otherDY) * (_t9) - (sd[5]))) + (sd[5])) * _t23;
        return dlb_s6b8190ed_1_mulAdd(otherDZ, otherDW, t, dest, sd, dd, _t9, _t23);
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat dlb_s6b8190ed_1_fma(float otherDZ, float otherDW, float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t9, float _t23) {
        dd[6] = java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -sd[6]), sd[6]) * _t23;
        dd[7] = java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -sd[7]), sd[7]) * _t23;
        return dest;
    }

    /** Piece 2 of {@code dlb}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat dlb_s6b8190ed_1_mulAdd(float otherDZ, float otherDW, float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t9, float _t23) {
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
    public FloatDualQuat lerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat lerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
        float[] sd = this.data;
        float _t0 = -sd[2];
        float _t8 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = java.lang.Math.fma(sd[0], _t9, sd[3] * _t10);
        float _t77 = java.lang.Math.fma(_t0, _t11, -(sd[1] * _t12));
        return sclerp_sd6508ecf_1_fma(t, dest, sd, ((FloatDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(sd[1], _t9, -(sd[2] * _t10)) + java.lang.Math.fma(sd[3], _t11, -(sd[0] * _t12)), java.lang.Math.fma(sd[0], _t11, sd[3] * _t12) + java.lang.Math.fma(_t0, _t9, -(sd[1] * _t10)), java.lang.Math.fma(sd[2], _t12, -(sd[1] * _t11)) + java.lang.Math.fma(sd[3], _t9, -(sd[0] * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = -sd[2];
        float _t8 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = ((sd[0]) * (_t9) + (sd[3] * _t10));
        float _t77 = ((_t0) * (_t11) - (sd[1] * _t12));
        return sclerp_sd6508ecf_1_mulAdd(t, dest, sd, ((FloatDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((sd[1]) * (_t9) - (sd[2] * _t10)) + ((sd[3]) * (_t11) - (sd[0] * _t12)), ((sd[0]) * (_t11) + (sd[3] * _t12)) + ((_t0) * (_t9) - (sd[1] * _t10)), ((sd[2]) * (_t12) - (sd[1] * _t11)) + ((sd[3]) * (_t9) - (sd[0] * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_fma(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_sd6508ecf_2_fma(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(sd[0], _t13, sd[3] * _t14) + java.lang.Math.fma(sd[1], _t15, sd[2] * _t16) + (java.lang.Math.fma(sd[4], _t9, sd[7] * _t10) + java.lang.Math.fma(sd[5], _t12, sd[6] * _t11)), java.lang.Math.fma(sd[2], _t15, -(sd[1] * _t16)) + java.lang.Math.fma(sd[3], _t13, -(sd[0] * _t14)) + (java.lang.Math.fma(sd[6], _t12, -(sd[5] * _t11)) + java.lang.Math.fma(sd[7], _t9, -(sd[4] * _t10))), java.lang.Math.fma(sd[1], _t13, -(sd[2] * _t14)) + java.lang.Math.fma(sd[3], _t16, -(sd[0] * _t15)) + (java.lang.Math.fma(sd[5], _t9, -(sd[6] * _t10)) + java.lang.Math.fma(sd[7], _t11, -(sd[4] * _t12))), java.lang.Math.fma(sd[0], _t16, sd[3] * _t15) + java.lang.Math.fma(_t0, _t13, -(sd[1] * _t14)) + (java.lang.Math.fma(sd[4], _t11, sd[7] * _t12) + java.lang.Math.fma(_t2, _t9, -(sd[5] * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_mulAdd(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_sd6508ecf_2_mulAdd(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((sd[0]) * (_t13) + (sd[3] * _t14)) + ((sd[1]) * (_t15) + (sd[2] * _t16)) + (((sd[4]) * (_t9) + (sd[7] * _t10)) + ((sd[5]) * (_t12) + (sd[6] * _t11))), ((sd[2]) * (_t15) - (sd[1] * _t16)) + ((sd[3]) * (_t13) - (sd[0] * _t14)) + (((sd[6]) * (_t12) - (sd[5] * _t11)) + ((sd[7]) * (_t9) - (sd[4] * _t10))), ((sd[1]) * (_t13) - (sd[2] * _t14)) + ((sd[3]) * (_t16) - (sd[0] * _t15)) + (((sd[5]) * (_t9) - (sd[6] * _t10)) + ((sd[7]) * (_t11) - (sd[4] * _t12))), ((sd[0]) * (_t16) + (sd[3] * _t15)) + ((_t0) * (_t13) - (sd[1] * _t14)) + (((sd[4]) * (_t11) + (sd[7] * _t12)) + ((_t2) * (_t9) - (sd[5] * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_fma(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
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
        float _t126 = _t124 * _t108;
        float _t127 = _t124 * _t106;
        float _t128 = _t124 * _t107;
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        float _t132 = t * _t131;
        float _t137 = Math.cosFromSin(_t130, _t129);
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
        return sclerp_sd6508ecf_3_fma(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_mulAdd(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
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
        float _t126 = _t124 * _t108;
        float _t127 = _t124 * _t106;
        float _t128 = _t124 * _t107;
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        float _t132 = t * _t131;
        float _t137 = Math.cosFromSin(_t130, _t129);
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
        return sclerp_sd6508ecf_3_mulAdd(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_fma(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t133, float _t142, float _t143, float _t144, float _t145, float _t147, float _t146) {
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t142, _rd3 * _t143) + java.lang.Math.fma(_rd1, _t144, -(_rd2 * _t145));
        dd[1] = java.lang.Math.fma(_rd1, _t142, _rd2 * _t143) + java.lang.Math.fma(_rd3, _t145, -(_rd0 * _t144));
        dd[2] = java.lang.Math.fma(_rd0, _t145, _rd3 * _t144) + java.lang.Math.fma(_rd2, _t142, -(_rd1 * _t143));
        return sclerp_sd6508ecf_4_fma(dest, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_mulAdd(float t, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t2, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t133, float _t142, float _t143, float _t144, float _t145, float _t147, float _t146) {
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t142) + (_rd3 * _t143)) + ((_rd1) * (_t144) - (_rd2 * _t145));
        dd[1] = ((_rd1) * (_t142) + (_rd2 * _t143)) + ((_rd3) * (_t145) - (_rd0 * _t144));
        dd[2] = ((_rd0) * (_t145) + (_rd3 * _t144)) + ((_rd2) * (_t142) - (_rd1 * _t143));
        return sclerp_sd6508ecf_4_mulAdd(dest, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_fma(FloatDualQuat dest, float[] dd, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[3] = java.lang.Math.fma(_rd3, _t142, -(_rd0 * _t143)) - java.lang.Math.fma(_rd1, _t145, _rd2 * _t144);
        dd[4] = java.lang.Math.fma(_rd0, _t147, _rd3 * _t160) + java.lang.Math.fma(_rd1, _t161, -(_rd2 * _t162)) + (java.lang.Math.fma(_rd4, _t142, _rd7 * _t143) + java.lang.Math.fma(_rd5, _t144, -(_rd6 * _t145)));
        dd[5] = java.lang.Math.fma(_rd1, _t147, _rd2 * _t160) + java.lang.Math.fma(_rd3, _t162, -(_rd0 * _t161)) + (java.lang.Math.fma(_rd5, _t142, _rd6 * _t143) + java.lang.Math.fma(_rd7, _t145, -(_rd4 * _t144)));
        dd[6] = java.lang.Math.fma(_rd0, _t162, _rd3 * _t161) + java.lang.Math.fma(_rd2, _t147, -(_rd1 * _t160)) + (java.lang.Math.fma(_rd4, _t145, _rd7 * _t144) + java.lang.Math.fma(_rd6, _t142, -(_rd5 * _t143)));
        dd[7] = java.lang.Math.fma(_rd3, _t147, -(_rd0 * _t160)) + java.lang.Math.fma(_t0, _t161, -(_rd1 * _t162)) + (java.lang.Math.fma(_rd7, _t142, -(_rd4 * _t143)) + java.lang.Math.fma(_t2, _t144, -(_rd5 * _t145)));
        return dest;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_mulAdd(FloatDualQuat dest, float[] dd, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[3] = ((_rd3) * (_t142) - (_rd0 * _t143)) - ((_rd1) * (_t145) + (_rd2 * _t144));
        dd[4] = ((_rd0) * (_t147) + (_rd3 * _t160)) + ((_rd1) * (_t161) - (_rd2 * _t162)) + (((_rd4) * (_t142) + (_rd7 * _t143)) + ((_rd5) * (_t144) - (_rd6 * _t145)));
        dd[5] = ((_rd1) * (_t147) + (_rd2 * _t160)) + ((_rd3) * (_t162) - (_rd0 * _t161)) + (((_rd5) * (_t142) + (_rd6 * _t143)) + ((_rd7) * (_t145) - (_rd4 * _t144)));
        dd[6] = ((_rd0) * (_t162) + (_rd3 * _t161)) + ((_rd2) * (_t147) - (_rd1 * _t160)) + (((_rd4) * (_t145) + (_rd7 * _t144)) + ((_rd6) * (_t142) - (_rd5 * _t143)));
        dd[7] = ((_rd3) * (_t147) - (_rd0 * _t160)) + ((_t0) * (_t161) - (_rd1 * _t162)) + (((_rd7) * (_t142) - (_rd4 * _t143)) + ((_t2) * (_t144) - (_rd5 * _t145)));
        return dest;
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
        float[] sd = this.data;
        float _t0 = -sd[2];
        float _t8 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = java.lang.Math.fma(sd[0], _t9, sd[3] * _t10);
        float _t77 = java.lang.Math.fma(_t0, _t11, -(sd[1] * _t12));
        return sclerp_se724e73a_1_fma(t, dest, sd, ((DoubleDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(sd[1], _t9, -(sd[2] * _t10)) + java.lang.Math.fma(sd[3], _t11, -(sd[0] * _t12)), java.lang.Math.fma(sd[0], _t11, sd[3] * _t12) + java.lang.Math.fma(_t0, _t9, -(sd[1] * _t10)), java.lang.Math.fma(sd[2], _t12, -(sd[1] * _t11)) + java.lang.Math.fma(sd[3], _t9, -(sd[0] * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        float _t0 = -sd[2];
        float _t8 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = ((sd[0]) * (_t9) + (sd[3] * _t10));
        float _t77 = ((_t0) * (_t11) - (sd[1] * _t12));
        return sclerp_se724e73a_1_mulAdd(t, dest, sd, ((DoubleDualQuatImpl) dest).data, _t0, -sd[6], _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((sd[1]) * (_t9) - (sd[2] * _t10)) + ((sd[3]) * (_t11) - (sd[0] * _t12)), ((sd[0]) * (_t11) + (sd[3] * _t12)) + ((_t0) * (_t9) - (sd[1] * _t10)), ((sd[2]) * (_t12) - (sd[1] * _t11)) + ((sd[3]) * (_t9) - (sd[0] * _t10)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_fma(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_se724e73a_2_fma(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(sd[0], _t13, sd[3] * _t14) + java.lang.Math.fma(sd[1], _t15, sd[2] * _t16) + (java.lang.Math.fma(sd[4], _t9, sd[7] * _t10) + java.lang.Math.fma(sd[5], _t12, sd[6] * _t11)), java.lang.Math.fma(sd[2], _t15, -(sd[1] * _t16)) + java.lang.Math.fma(sd[3], _t13, -(sd[0] * _t14)) + (java.lang.Math.fma(sd[6], _t12, -(sd[5] * _t11)) + java.lang.Math.fma(sd[7], _t9, -(sd[4] * _t10))), java.lang.Math.fma(sd[1], _t13, -(sd[2] * _t14)) + java.lang.Math.fma(sd[3], _t16, -(sd[0] * _t15)) + (java.lang.Math.fma(sd[5], _t9, -(sd[6] * _t10)) + java.lang.Math.fma(sd[7], _t11, -(sd[4] * _t12))), java.lang.Math.fma(sd[0], _t16, sd[3] * _t15) + java.lang.Math.fma(_t0, _t13, -(sd[1] * _t14)) + (java.lang.Math.fma(sd[4], _t11, sd[7] * _t12) + java.lang.Math.fma(_t2, _t9, -(sd[5] * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_mulAdd(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_se724e73a_2_mulAdd(t, dest, sd, dd, _t0, _t2, _t57, _t77, _t83, _t84, _t85, _t86, ((sd[0]) * (_t13) + (sd[3] * _t14)) + ((sd[1]) * (_t15) + (sd[2] * _t16)) + (((sd[4]) * (_t9) + (sd[7] * _t10)) + ((sd[5]) * (_t12) + (sd[6] * _t11))), ((sd[2]) * (_t15) - (sd[1] * _t16)) + ((sd[3]) * (_t13) - (sd[0] * _t14)) + (((sd[6]) * (_t12) - (sd[5] * _t11)) + ((sd[7]) * (_t9) - (sd[4] * _t10))), ((sd[1]) * (_t13) - (sd[2] * _t14)) + ((sd[3]) * (_t16) - (sd[0] * _t15)) + (((sd[5]) * (_t9) - (sd[6] * _t10)) + ((sd[7]) * (_t11) - (sd[4] * _t12))), ((sd[0]) * (_t16) + (sd[3] * _t15)) + ((_t0) * (_t13) - (sd[1] * _t14)) + (((sd[4]) * (_t11) + (sd[7] * _t12)) + ((_t2) * (_t9) - (sd[5] * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_fma(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
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
        float _t126 = _t124 * _t108;
        float _t127 = _t124 * _t106;
        float _t128 = _t124 * _t107;
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        float _t132 = t * _t131;
        float _t137 = Math.cosFromSin(_t130, _t129);
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
        return sclerp_se724e73a_3_fma(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_mulAdd(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
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
        float _t126 = _t124 * _t108;
        float _t127 = _t124 * _t106;
        float _t128 = _t124 * _t107;
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        float _t132 = t * _t131;
        float _t137 = Math.cosFromSin(_t130, _t129);
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
        return sclerp_se724e73a_3_mulAdd(t, dest, sd, dd, _t0, _t2, _t114, _t115, _t116, _t122, _t124, _t126, _t127, _t128, _t130, _t131 * _t105, _t142, _t143, _t144, _t145, _t147, _t132 * _t137);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_fma(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t133, float _t142, float _t143, float _t144, float _t145, float _t147, float _t146) {
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
        dd[0] = java.lang.Math.fma(sd[0], _t142, sd[3] * _t143) + java.lang.Math.fma(sd[1], _t144, -(sd[2] * _t145));
        dd[1] = java.lang.Math.fma(sd[1], _t142, sd[2] * _t143) + java.lang.Math.fma(sd[3], _t145, -(sd[0] * _t144));
        dd[2] = java.lang.Math.fma(sd[0], _t145, sd[3] * _t144) + java.lang.Math.fma(sd[2], _t142, -(sd[1] * _t143));
        dd[3] = java.lang.Math.fma(sd[3], _t142, -(sd[0] * _t143)) - java.lang.Math.fma(sd[1], _t145, sd[2] * _t144);
        return sclerp_se724e73a_4_fma(dest, sd, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_mulAdd(float t, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t114, float _t115, float _t116, float _t122, float _t124, float _t126, float _t127, float _t128, float _t130, float _t133, float _t142, float _t143, float _t144, float _t145, float _t147, float _t146) {
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
        dd[0] = ((sd[0]) * (_t142) + (sd[3] * _t143)) + ((sd[1]) * (_t144) - (sd[2] * _t145));
        dd[1] = ((sd[1]) * (_t142) + (sd[2] * _t143)) + ((sd[3]) * (_t145) - (sd[0] * _t144));
        dd[2] = ((sd[0]) * (_t145) + (sd[3] * _t144)) + ((sd[2]) * (_t142) - (sd[1] * _t143));
        dd[3] = ((sd[3]) * (_t142) - (sd[0] * _t143)) - ((sd[1]) * (_t145) + (sd[2] * _t144));
        return sclerp_se724e73a_4_mulAdd(dest, sd, dd, _t0, _t2, _t142, _t143, _t144, _t145, _t147, _t160, _t161, _t162);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
        dd[4] = java.lang.Math.fma(sd[0], _t147, sd[3] * _t160) + java.lang.Math.fma(sd[1], _t161, -(sd[2] * _t162)) + (java.lang.Math.fma(sd[4], _t142, sd[7] * _t143) + java.lang.Math.fma(sd[5], _t144, -(sd[6] * _t145)));
        dd[5] = java.lang.Math.fma(sd[1], _t147, sd[2] * _t160) + java.lang.Math.fma(sd[3], _t162, -(sd[0] * _t161)) + (java.lang.Math.fma(sd[5], _t142, sd[6] * _t143) + java.lang.Math.fma(sd[7], _t145, -(sd[4] * _t144)));
        dd[6] = java.lang.Math.fma(sd[0], _t162, sd[3] * _t161) + java.lang.Math.fma(sd[2], _t147, -(sd[1] * _t160)) + (java.lang.Math.fma(sd[4], _t145, sd[7] * _t144) + java.lang.Math.fma(sd[6], _t142, -(sd[5] * _t143)));
        dd[7] = java.lang.Math.fma(sd[3], _t147, -(sd[0] * _t160)) + java.lang.Math.fma(_t0, _t161, -(sd[1] * _t162)) + (java.lang.Math.fma(sd[7], _t142, -(sd[4] * _t143)) + java.lang.Math.fma(_t2, _t144, -(sd[5] * _t145)));
        return dest;
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t2, float _t142, float _t143, float _t144, float _t145, float _t147, float _t160, float _t161, float _t162) {
        dd[4] = ((sd[0]) * (_t147) + (sd[3] * _t160)) + ((sd[1]) * (_t161) - (sd[2] * _t162)) + (((sd[4]) * (_t142) + (sd[7] * _t143)) + ((sd[5]) * (_t144) - (sd[6] * _t145)));
        dd[5] = ((sd[1]) * (_t147) + (sd[2] * _t160)) + ((sd[3]) * (_t162) - (sd[0] * _t161)) + (((sd[5]) * (_t142) + (sd[6] * _t143)) + ((sd[7]) * (_t145) - (sd[4] * _t144)));
        dd[6] = ((sd[0]) * (_t162) + (sd[3] * _t161)) + ((sd[2]) * (_t147) - (sd[1] * _t160)) + (((sd[4]) * (_t145) + (sd[7] * _t144)) + ((sd[6]) * (_t142) - (sd[5] * _t143)));
        dd[7] = ((sd[3]) * (_t147) - (sd[0] * _t160)) + ((_t0) * (_t161) - (sd[1] * _t162)) + (((sd[7]) * (_t142) - (sd[4] * _t143)) + ((_t2) * (_t144) - (sd[5] * _t145)));
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
    public FloatDualQuat mul(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) return mul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
    public FloatDualQuat mul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return mul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat mul_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRZ, _rd1, -(otherRY * _rd2));
        dd[1] = java.lang.Math.fma(otherRX, _rd2, otherRW * _rd1) + java.lang.Math.fma(otherRY, _rd3, -(otherRZ * _rd0));
        dd[2] = java.lang.Math.fma(otherRY, _rd0, otherRZ * _rd3) + java.lang.Math.fma(otherRW, _rd2, -(otherRX * _rd1));
        dd[3] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[4] = java.lang.Math.fma(otherRX, _rd7, otherRW * _rd4) + java.lang.Math.fma(otherRZ, _rd5, -(otherRY * _rd6)) + (java.lang.Math.fma(otherDX, _rd3, otherDW * _rd0) + java.lang.Math.fma(otherDZ, _rd1, -(otherDY * _rd2)));
        return mul_s9f969cca_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat mul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRZ) * (_rd1) - (otherRY * _rd2));
        dd[1] = ((otherRX) * (_rd2) + (otherRW * _rd1)) + ((otherRY) * (_rd3) - (otherRZ * _rd0));
        dd[2] = ((otherRY) * (_rd0) + (otherRZ * _rd3)) + ((otherRW) * (_rd2) - (otherRX * _rd1));
        dd[3] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[4] = ((otherRX) * (_rd7) + (otherRW * _rd4)) + ((otherRZ) * (_rd5) - (otherRY * _rd6)) + (((otherDX) * (_rd3) + (otherDW * _rd0)) + ((otherDZ) * (_rd1) - (otherDY * _rd2)));
        return mul_s9f969cca_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat mul_s9f969cca_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = java.lang.Math.fma(otherRX, _rd6, otherRW * _rd5) + java.lang.Math.fma(otherRY, _rd7, -(otherRZ * _rd4)) + (java.lang.Math.fma(otherDX, _rd2, otherDW * _rd1) + java.lang.Math.fma(otherDY, _rd3, -(otherDZ * _rd0)));
        dd[6] = java.lang.Math.fma(otherRY, _rd4, otherRZ * _rd7) + java.lang.Math.fma(otherRW, _rd6, -(otherRX * _rd5)) + (java.lang.Math.fma(otherDY, _rd0, otherDZ * _rd3) + java.lang.Math.fma(otherDW, _rd2, -(otherDX * _rd1)));
        dd[7] = java.lang.Math.fma(otherRW, _rd7, -(otherRX * _rd4)) + java.lang.Math.fma(-otherRZ, _rd6, -(otherRY * _rd5)) + (java.lang.Math.fma(otherDW, _rd3, -(otherDX * _rd0)) + java.lang.Math.fma(-otherDZ, _rd2, -(otherDY * _rd1)));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat mul_s9f969cca_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = ((otherRX) * (_rd6) + (otherRW * _rd5)) + ((otherRY) * (_rd7) - (otherRZ * _rd4)) + (((otherDX) * (_rd2) + (otherDW * _rd1)) + ((otherDY) * (_rd3) - (otherDZ * _rd0)));
        dd[6] = ((otherRY) * (_rd4) + (otherRZ * _rd7)) + ((otherRW) * (_rd6) - (otherRX * _rd5)) + (((otherDY) * (_rd0) + (otherDZ * _rd3)) + ((otherDW) * (_rd2) - (otherDX * _rd1)));
        dd[7] = ((otherRW) * (_rd7) - (otherRX * _rd4)) + ((-otherRZ) * (_rd6) - (otherRY * _rd5)) + (((otherDW) * (_rd3) - (otherDX * _rd0)) + ((-otherDZ) * (_rd2) - (otherDY * _rd1)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(otherRX, sd[3], otherRW * sd[0]) + Math.fma(otherRZ, sd[1], -(otherRY * sd[2]));
        dd[1] = Math.fma(otherRX, sd[2], otherRW * sd[1]) + Math.fma(otherRY, sd[3], -(otherRZ * sd[0]));
        dd[2] = Math.fma(otherRY, sd[0], otherRZ * sd[3]) + Math.fma(otherRW, sd[2], -(otherRX * sd[1]));
        dd[3] = Math.fma(otherRW, sd[3], -(otherRX * sd[0])) - Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[4] = Math.fma(otherRX, sd[7], otherRW * sd[4]) + Math.fma(otherRZ, sd[5], -(otherRY * sd[6])) + (Math.fma(otherDX, sd[3], otherDW * sd[0]) + Math.fma(otherDZ, sd[1], -(otherDY * sd[2])));
        return (Math.useFma() ? mul_sdf825025_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd) : mul_sdf825025_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sdf825025_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRX, sd[6], otherRW * sd[5]) + java.lang.Math.fma(otherRY, sd[7], -(otherRZ * sd[4])) + (java.lang.Math.fma(otherDX, sd[2], otherDW * sd[1]) + java.lang.Math.fma(otherDY, sd[3], -(otherDZ * sd[0])));
        dd[6] = java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[7]) + java.lang.Math.fma(otherRW, sd[6], -(otherRX * sd[5])) + (java.lang.Math.fma(otherDY, sd[0], otherDZ * sd[3]) + java.lang.Math.fma(otherDW, sd[2], -(otherDX * sd[1])));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -(otherRX * sd[4])) + java.lang.Math.fma(-otherRZ, sd[6], -(otherRY * sd[5])) + (java.lang.Math.fma(otherDW, sd[3], -(otherDX * sd[0])) + java.lang.Math.fma(-otherDZ, sd[2], -(otherDY * sd[1])));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sdf825025_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = ((otherRX) * (sd[6]) + (otherRW * sd[5])) + ((otherRY) * (sd[7]) - (otherRZ * sd[4])) + (((otherDX) * (sd[2]) + (otherDW * sd[1])) + ((otherDY) * (sd[3]) - (otherDZ * sd[0])));
        dd[6] = ((otherRY) * (sd[4]) + (otherRZ * sd[7])) + ((otherRW) * (sd[6]) - (otherRX * sd[5])) + (((otherDY) * (sd[0]) + (otherDZ * sd[3])) + ((otherDW) * (sd[2]) - (otherDX * sd[1])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((-otherDZ) * (sd[2]) - (otherDY * sd[1])));
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
    public FloatDualQuat preMul(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) return preMul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
    public FloatDualQuat preMul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return preMul_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat preMul_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        dd[1] = java.lang.Math.fma(otherRY, _rd3, otherRZ * _rd0) + java.lang.Math.fma(otherRW, _rd1, -(otherRX * _rd2));
        dd[2] = java.lang.Math.fma(otherRX, _rd1, otherRW * _rd2) + java.lang.Math.fma(otherRZ, _rd3, -(otherRY * _rd0));
        dd[3] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[4] = java.lang.Math.fma(otherRX, _rd7, otherRW * _rd4) + java.lang.Math.fma(otherRY, _rd6, -(otherRZ * _rd5)) + (java.lang.Math.fma(otherDX, _rd3, otherDW * _rd0) + java.lang.Math.fma(otherDY, _rd2, -(otherDZ * _rd1)));
        return preMul_sf00631ab_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat preMul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        dd[1] = ((otherRY) * (_rd3) + (otherRZ * _rd0)) + ((otherRW) * (_rd1) - (otherRX * _rd2));
        dd[2] = ((otherRX) * (_rd1) + (otherRW * _rd2)) + ((otherRZ) * (_rd3) - (otherRY * _rd0));
        dd[3] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[4] = ((otherRX) * (_rd7) + (otherRW * _rd4)) + ((otherRY) * (_rd6) - (otherRZ * _rd5)) + (((otherDX) * (_rd3) + (otherDW * _rd0)) + ((otherDY) * (_rd2) - (otherDZ * _rd1)));
        return preMul_sf00631ab_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat preMul_sf00631ab_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = java.lang.Math.fma(otherRY, _rd7, otherRZ * _rd4) + java.lang.Math.fma(otherRW, _rd5, -(otherRX * _rd6)) + (java.lang.Math.fma(otherDY, _rd3, otherDZ * _rd0) + java.lang.Math.fma(otherDW, _rd1, -(otherDX * _rd2)));
        dd[6] = java.lang.Math.fma(otherRX, _rd5, otherRW * _rd6) + java.lang.Math.fma(otherRZ, _rd7, -(otherRY * _rd4)) + (java.lang.Math.fma(otherDX, _rd1, otherDW * _rd2) + java.lang.Math.fma(otherDZ, _rd3, -(otherDY * _rd0)));
        dd[7] = java.lang.Math.fma(otherRW, _rd7, -(otherRX * _rd4)) + java.lang.Math.fma(-otherRZ, _rd6, -(otherRY * _rd5)) + (java.lang.Math.fma(otherDW, _rd3, -(otherDX * _rd0)) + java.lang.Math.fma(-otherDZ, _rd2, -(otherDY * _rd1)));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat preMul_sf00631ab_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = ((otherRY) * (_rd7) + (otherRZ * _rd4)) + ((otherRW) * (_rd5) - (otherRX * _rd6)) + (((otherDY) * (_rd3) + (otherDZ * _rd0)) + ((otherDW) * (_rd1) - (otherDX * _rd2)));
        dd[6] = ((otherRX) * (_rd5) + (otherRW * _rd6)) + ((otherRZ) * (_rd7) - (otherRY * _rd4)) + (((otherDX) * (_rd1) + (otherDW * _rd2)) + ((otherDZ) * (_rd3) - (otherDY * _rd0)));
        dd[7] = ((otherRW) * (_rd7) - (otherRX * _rd4)) + ((-otherRZ) * (_rd6) - (otherRY * _rd5)) + (((otherDW) * (_rd3) - (otherDX * _rd0)) + ((-otherDZ) * (_rd2) - (otherDY * _rd1)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(otherRX, sd[3], otherRW * sd[0]) + Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[1] = Math.fma(otherRY, sd[3], otherRZ * sd[0]) + Math.fma(otherRW, sd[1], -(otherRX * sd[2]));
        dd[2] = Math.fma(otherRX, sd[1], otherRW * sd[2]) + Math.fma(otherRZ, sd[3], -(otherRY * sd[0]));
        dd[3] = Math.fma(otherRW, sd[3], -(otherRX * sd[0])) - Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[4] = Math.fma(otherRX, sd[7], otherRW * sd[4]) + Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (Math.fma(otherDX, sd[3], otherDW * sd[0]) + Math.fma(otherDY, sd[2], -(otherDZ * sd[1])));
        return (Math.useFma() ? preMul_s8fae4586_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd) : preMul_s8fae4586_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8fae4586_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(otherRW, sd[5], -(otherRX * sd[6])) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(otherDW, sd[1], -(otherDX * sd[2])));
        dd[6] = java.lang.Math.fma(otherRX, sd[5], otherRW * sd[6]) + java.lang.Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (java.lang.Math.fma(otherDX, sd[1], otherDW * sd[2]) + java.lang.Math.fma(otherDZ, sd[3], -(otherDY * sd[0])));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -(otherRX * sd[4])) + java.lang.Math.fma(-otherRZ, sd[6], -(otherRY * sd[5])) + (java.lang.Math.fma(otherDW, sd[3], -(otherDX * sd[0])) + java.lang.Math.fma(-otherDZ, sd[2], -(otherDY * sd[1])));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8fae4586_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((otherRW) * (sd[5]) - (otherRX * sd[6])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((otherDW) * (sd[1]) - (otherDX * sd[2])));
        dd[6] = ((otherRX) * (sd[5]) + (otherRW * sd[6])) + ((otherRZ) * (sd[7]) - (otherRY * sd[4])) + (((otherDX) * (sd[1]) + (otherDW * sd[2])) + ((otherDZ) * (sd[3]) - (otherDY * sd[0])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((-otherDZ) * (sd[2]) - (otherDY * sd[1])));
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
            float[] sd = this.data;
            float[] dd = ((FloatDualQuatImpl) dest).data;
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
            float[] sd = this.data;
            float[] dd = ((FloatDualQuatImpl) dest).data;
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
            float[] sd = this.data;
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
            float[] sd = this.data;
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
    public FloatDualQuat conjugate(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat difference(FloatDualQuatR other, @Mutated FloatDualQuat dest) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        if (Math.useFma()) return difference_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
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
        if (Math.useFma()) return difference_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat difference_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -otherRX;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(otherRX, _rd3, -(otherRW * _rd0)) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        dd[1] = java.lang.Math.fma(otherRY, _rd3, otherRZ * _rd0) + java.lang.Math.fma(_t0, _rd2, -(otherRW * _rd1));
        dd[2] = java.lang.Math.fma(otherRX, _rd1, -(otherRW * _rd2)) + java.lang.Math.fma(otherRZ, _rd3, -(otherRY * _rd0));
        dd[3] = java.lang.Math.fma(otherRX, _rd0, otherRW * _rd3) - java.lang.Math.fma(-otherRZ, _rd2, -(otherRY * _rd1));
        dd[4] = java.lang.Math.fma(otherRX, _rd7, -(otherRW * _rd4)) + java.lang.Math.fma(otherRY, _rd6, -(otherRZ * _rd5)) + (java.lang.Math.fma(otherDX, _rd3, -(otherDW * _rd0)) + java.lang.Math.fma(otherDY, _rd2, -(otherDZ * _rd1)));
        return difference_s52291601_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _t0, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat difference_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -otherRX;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((otherRX) * (_rd3) - (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        dd[1] = ((otherRY) * (_rd3) + (otherRZ * _rd0)) + ((_t0) * (_rd2) - (otherRW * _rd1));
        dd[2] = ((otherRX) * (_rd1) - (otherRW * _rd2)) + ((otherRZ) * (_rd3) - (otherRY * _rd0));
        dd[3] = ((otherRX) * (_rd0) + (otherRW * _rd3)) - ((-otherRZ) * (_rd2) - (otherRY * _rd1));
        dd[4] = ((otherRX) * (_rd7) - (otherRW * _rd4)) + ((otherRY) * (_rd6) - (otherRZ * _rd5)) + (((otherDX) * (_rd3) - (otherDW * _rd0)) + ((otherDY) * (_rd2) - (otherDZ * _rd1)));
        return difference_s52291601_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, dd, _t0, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat difference_s52291601_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _t0, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = java.lang.Math.fma(otherRY, _rd7, otherRZ * _rd4) + java.lang.Math.fma(_t0, _rd6, -(otherRW * _rd5)) + (java.lang.Math.fma(otherDY, _rd3, otherDZ * _rd0) + java.lang.Math.fma(-otherDX, _rd2, -(otherDW * _rd1)));
        dd[6] = java.lang.Math.fma(otherRX, _rd5, -(otherRW * _rd6)) + java.lang.Math.fma(otherRZ, _rd7, -(otherRY * _rd4)) + (java.lang.Math.fma(otherDX, _rd1, -(otherDW * _rd2)) + java.lang.Math.fma(otherDZ, _rd3, -(otherDY * _rd0)));
        dd[7] = java.lang.Math.fma(otherRX, _rd4, otherRW * _rd7) + java.lang.Math.fma(otherRY, _rd5, otherRZ * _rd6) + (java.lang.Math.fma(otherDX, _rd0, otherDW * _rd3) + java.lang.Math.fma(otherDY, _rd1, otherDZ * _rd2));
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat difference_s52291601_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] dd, float _t0, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[5] = ((otherRY) * (_rd7) + (otherRZ * _rd4)) + ((_t0) * (_rd6) - (otherRW * _rd5)) + (((otherDY) * (_rd3) + (otherDZ * _rd0)) + ((-otherDX) * (_rd2) - (otherDW * _rd1)));
        dd[6] = ((otherRX) * (_rd5) - (otherRW * _rd6)) + ((otherRZ) * (_rd7) - (otherRY * _rd4)) + (((otherDX) * (_rd1) - (otherDW * _rd2)) + ((otherDZ) * (_rd3) - (otherDY * _rd0)));
        dd[7] = ((otherRX) * (_rd4) + (otherRW * _rd7)) + ((otherRY) * (_rd5) + (otherRZ * _rd6)) + (((otherDX) * (_rd0) + (otherDW * _rd3)) + ((otherDY) * (_rd1) + (otherDZ * _rd2)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -otherRX;
        dd[0] = Math.fma(otherRX, sd[3], -(otherRW * sd[0])) + Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[1] = Math.fma(otherRY, sd[3], otherRZ * sd[0]) + Math.fma(_t0, sd[2], -(otherRW * sd[1]));
        dd[2] = Math.fma(otherRX, sd[1], -(otherRW * sd[2])) + Math.fma(otherRZ, sd[3], -(otherRY * sd[0]));
        dd[3] = Math.fma(otherRX, sd[0], otherRW * sd[3]) - Math.fma(-otherRZ, sd[2], -(otherRY * sd[1]));
        dd[4] = Math.fma(otherRX, sd[7], -(otherRW * sd[4])) + Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (Math.fma(otherDX, sd[3], -(otherDW * sd[0])) + Math.fma(otherDY, sd[2], -(otherDZ * sd[1])));
        return (Math.useFma() ? difference_s86fe7700_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd, _t0) : difference_s86fe7700_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd, _t0));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_s86fe7700_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd, float _t0) {
        dd[5] = java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(_t0, sd[6], -(otherRW * sd[5])) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(-otherDX, sd[2], -(otherDW * sd[1])));
        dd[6] = java.lang.Math.fma(otherRX, sd[5], -(otherRW * sd[6])) + java.lang.Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (java.lang.Math.fma(otherDX, sd[1], -(otherDW * sd[2])) + java.lang.Math.fma(otherDZ, sd[3], -(otherDY * sd[0])));
        dd[7] = java.lang.Math.fma(otherRX, sd[4], otherRW * sd[7]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * sd[6]) + (java.lang.Math.fma(otherDX, sd[0], otherDW * sd[3]) + java.lang.Math.fma(otherDY, sd[1], otherDZ * sd[2]));
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_s86fe7700_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd, float _t0) {
        dd[5] = ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((_t0) * (sd[6]) - (otherRW * sd[5])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((-otherDX) * (sd[2]) - (otherDW * sd[1])));
        dd[6] = ((otherRX) * (sd[5]) - (otherRW * sd[6])) + ((otherRZ) * (sd[7]) - (otherRY * sd[4])) + (((otherDX) * (sd[1]) - (otherDW * sd[2])) + ((otherDZ) * (sd[3]) - (otherDY * sd[0])));
        dd[7] = ((otherRX) * (sd[4]) + (otherRW * sd[7])) + ((otherRY) * (sd[5]) + (otherRZ * sd[6])) + (((otherDX) * (sd[0]) + (otherDW * sd[3])) + ((otherDY) * (sd[1]) + (otherDZ * sd[2])));
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
            float[] sd = this.data;
            return java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) + (java.lang.Math.fma(otherDX, sd[4], otherDY * sd[5]) + java.lang.Math.fma(otherDZ, sd[6], otherDW * sd[7]));
        } else {
            float[] sd = this.data;
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
    public float dot(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) + (java.lang.Math.fma(otherDX, sd[4], otherDY * sd[5]) + java.lang.Math.fma(otherDZ, sd[6], otherDW * sd[7]));
        } else {
            float[] sd = this.data;
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
    public FloatDualQuat dualConjugate(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat exp(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t5 = Math.fma(sd[2], sd[6], Math.fma(sd[0], sd[4], sd[1] * sd[5]));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sin(_t7);
        float _sp0 = _t6 * _t8;
        float _t9 = sd[0] * _t6;
        float _t10 = sd[1] * _t6;
        float _t11 = sd[2] * _t6;
        float _t12 = _t5 * _t6;
        float _t13 = Math.cosFromSin(_t8, _t7);
        float _t14 = _t12 * _t13;
        if (_t4 < 1.0E-12f) {
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = 1.0f;
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
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t4 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            float _t5 = java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], sd[1] * sd[5]));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sin(_t7);
            float _t12 = _t5 * _t6;
            float _t13 = Math.cosFromSin(_t8, _t7);
            return exp_sa11f286a_1_fma(dest, sd, ((DoubleDualQuatImpl) dest).data, _t4, _t5, _t8, _t6 * _t8, sd[0] * _t6, sd[1] * _t6, sd[2] * _t6, _t12, _t13, _t12 * _t13);
        } else {
            float[] sd = this.data;
            float _t4 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            float _t5 = ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) + (sd[1] * sd[5]))));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sin(_t7);
            float _t12 = _t5 * _t6;
            float _t13 = Math.cosFromSin(_t8, _t7);
            return exp_sa11f286a_1_mulAdd(dest, sd, ((DoubleDualQuatImpl) dest).data, _t4, _t5, _t8, _t6 * _t8, sd[0] * _t6, sd[1] * _t6, sd[2] * _t6, _t12, _t13, _t12 * _t13);
        }
    }

    /** Piece 2 of {@code exp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat exp_sa11f286a_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t4, float _t5, float _t8, float _sp0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        if (_t4 < 1.0E-12f) {
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = 1.0f;
            dd[4] = sd[4];
            dd[5] = sd[5];
            dd[6] = sd[6];
            dd[7] = -_t5;
        } else {
            dd[0] = _t9 * _t8;
            dd[1] = _t10 * _t8;
            dd[2] = _t11 * _t8;
            dd[3] = _t13;
            dd[4] = java.lang.Math.fma(_t9, _t14, java.lang.Math.fma(-_t9, _t12, sd[4]) * _sp0);
            dd[5] = java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(-_t10, _t12, sd[5]) * _sp0);
            dd[6] = java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(-_t11, _t12, sd[6]) * _sp0);
            dd[7] = -(_t12 * _t8);
        }
        return dest;
    }

    /** Piece 2 of {@code exp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat exp_sa11f286a_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t4, float _t5, float _t8, float _sp0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        if (_t4 < 1.0E-12f) {
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = 1.0f;
            dd[4] = sd[4];
            dd[5] = sd[5];
            dd[6] = sd[6];
            dd[7] = -_t5;
        } else {
            dd[0] = _t9 * _t8;
            dd[1] = _t10 * _t8;
            dd[2] = _t11 * _t8;
            dd[3] = _t13;
            dd[4] = ((_t9) * (_t14) + (((-_t9) * (_t12) + (sd[4])) * _sp0));
            dd[5] = ((_t10) * (_t14) + (((-_t10) * (_t12) + (sd[5])) * _sp0));
            dd[6] = ((_t11) * (_t14) + (((-_t11) * (_t12) + (sd[6])) * _sp0));
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
    public FloatQuat getDual(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = sd[4];
        dd[1] = sd[5];
        dd[2] = sd[6];
        dd[3] = sd[7];
        return dest;
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
        float[] sd = this.data;
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t3 = sd[2] * sd[2];
        float _t8 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], _t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t10);
            dd[2] = Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
        }
        dd[1] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t3 = sd[2] * sd[2];
        float _t8 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], _t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t10);
            dd[2] = Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
        }
        dd[1] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[1] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[3], _t1);
        float _t8 = 2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], -_t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t7, _t9);
            dd[1] = Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f));
        }
        dd[2] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[1] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[3], _t1);
        float _t8 = 2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], -_t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t7, _t9);
            dd[1] = Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f));
        }
        dd[2] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t3 = sd[2] * sd[2];
        float _t8 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2]));
        float _t10 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        dd[0] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dd[1] = Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t8, _t10);
            dd[2] = Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
        }
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t3 = sd[2] * sd[2];
        float _t8 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2]));
        float _t10 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        dd[0] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dd[1] = Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t8, _t10);
            dd[2] = Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t8 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
        } else {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[1] = Math.atan2(_t8, _t9);
        }
        dd[2] = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t8 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
        } else {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[1] = Math.atan2(_t8, _t9);
        }
        dd[2] = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t8 = 2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        dd[0] = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t1), 1.0f));
        } else {
            dd[1] = Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = Math.atan2(_t8, _t9);
        }
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t8 = 2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        dd[0] = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t1), 1.0f));
        } else {
            dd[1] = Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t8 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
        } else {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = Math.atan2(_t7, _t9);
        }
        dd[1] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t7 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t8 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
        } else {
            dd[0] = Math.atan2(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = Math.atan2(_t7, _t9);
        }
        dd[1] = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
    public FloatQuat getRotation(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
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
    public Float3 getTranslation(@Mutated Float3 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _rd0 = sd[0];
            float _rd1 = sd[1];
            float _rd2 = sd[2];
            float _rd3 = sd[3];
            float _rd4 = sd[4];
            float _rd5 = sd[5];
            float _rd6 = sd[6];
            float _rd7 = sd[7];
            dd[0] = 2.0f * (java.lang.Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + java.lang.Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
            dd[1] = 2.0f * (java.lang.Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
            dd[2] = 2.0f * (java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + java.lang.Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _rd0 = sd[0];
            float _rd1 = sd[1];
            float _rd2 = sd[2];
            float _rd3 = sd[3];
            float _rd4 = sd[4];
            float _rd5 = sd[5];
            float _rd6 = sd[6];
            float _rd7 = sd[7];
            dd[0] = 2.0f * (((_rd1) * (_rd6) - (_rd2 * _rd5)) + ((_rd3) * (_rd4) - (_rd0 * _rd7)));
            dd[1] = 2.0f * (((_rd2) * (_rd4) - (_rd0 * _rd6)) + ((_rd3) * (_rd5) - (_rd1 * _rd7)));
            dd[2] = 2.0f * (((_rd0) * (_rd5) - (_rd1 * _rd4)) + ((_rd3) * (_rd6) - (_rd2 * _rd7)));
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
            dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
            dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
            dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
            dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
            return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat invert(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (Math.fma(sd[0], sd[4], sd[1] * sd[5]) + Math.fma(sd[2], sd[6], sd[3] * sd[7])) / (_t8 * _t8);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (Math.fma(sd[0], sd[4], sd[1] * sd[5]) + Math.fma(sd[2], sd[6], sd[3] * sd[7])) / (_t8 * _t8);
        dd[0] = -(sd[0] * _t8_inv);
        dd[1] = -(sd[1] * _t8_inv);
        dd[2] = -(sd[2] * _t8_inv);
        dd[3] = sd[3] * _t8_inv;
        dd[4] = Math.fma(sd[0], _sp0, -(sd[4] * _t8_inv));
        dd[5] = Math.fma(sd[1], _sp0, -(sd[5] * _t8_inv));
        dd[6] = Math.fma(sd[2], _sp0, -(sd[6] * _t8_inv));
        dd[7] = Math.fma(sd[7], _t8_inv, -(sd[3] * _sp0));
        return dest;
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
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3])));
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
            float[] sd = this.data;
            return java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        } else {
            float[] sd = this.data;
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
    public FloatDualQuat log(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return (Math.useFma() ? log_sfb80bbd6_1_fma(dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11) : log_sfb80bbd6_1_mulAdd(dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11));
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat log_sfb80bbd6_1_fma(FloatDualQuat dest, float[] dd, float _t8, float _t9, float _t10, float _t12, float _t14, float _t15, float _t18, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        if (_t18 < 1.0E-12f) {
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
        dd[3] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat log_sfb80bbd6_1_mulAdd(FloatDualQuat dest, float[] dd, float _t8, float _t9, float _t10, float _t12, float _t14, float _t15, float _t18, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        if (_t18 < 1.0E-12f) {
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
        dd[3] = 0.0f;
        dd[7] = 0.0f;
        return dest;
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
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return (Math.useFma() ? log_sfb9ef421_1_fma(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11) : log_sfb9ef421_1_mulAdd(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11));
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat log_sfb9ef421_1_fma(DoubleDualQuat dest, double[] dd, float _t8, float _t9, float _t10, float _t12, float _t14, float _t15, float _t18, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        if (_t18 < 1.0E-12f) {
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
        dd[3] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code log}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat log_sfb9ef421_1_mulAdd(DoubleDualQuat dest, double[] dd, float _t8, float _t9, float _t10, float _t12, float _t14, float _t15, float _t18, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        if (_t18 < 1.0E-12f) {
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
        dd[3] = 0.0f;
        dd[7] = 0.0f;
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
    @Mutated public FloatDualQuat makeFromMatrix(Float4x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeFromMatrix_fma(Float4x4R m) {
        float[] mData = ((Float4x4Impl) m).data;
        float _t2 = 1.0f - mData[0];
        float _t14 = mData[10] + (mData[0] + mData[5]);
        float _t15 = 1.0f + _t14;
        float _t16 = mData[0] + (1.0f - mData[5] - mData[10]);
        float _t17 = mData[5] + (_t2 - mData[10]);
        float _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s40927f3d_1_fma(this.data, mData, -mData[14], mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t14, _t15, _t16, _t17, _t18, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeFromMatrix_mulAdd(Float4x4R m) {
        float[] mData = ((Float4x4Impl) m).data;
        float _t2 = 1.0f - mData[0];
        float _t14 = mData[10] + (mData[0] + mData[5]);
        float _t15 = 1.0f + _t14;
        float _t16 = mData[0] + (1.0f - mData[5] - mData[10]);
        float _t17 = mData[5] + (_t2 - mData[10]);
        float _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s40927f3d_1_mulAdd(this.data, mData, -mData[14], mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t14, _t15, _t16, _t17, _t18, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s40927f3d_1_fma(float[] dd, float[] mData, float _t0, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _t18, float _sp0, float _sp1, float _sp2, float _sp3) {
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
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
        float _rd0 = mData[12];
        float _rd1 = mData[13];
        float _rd2 = mData[14];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_rd0, _t66, _rd1 * _t65));
        dd[5] = 0.5f * java.lang.Math.fma(_rd2, _t63, java.lang.Math.fma(_rd1, _t66, -(_rd0 * _t65)));
        return makeFromMatrix_s40927f3d_2_fma(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s40927f3d_1_mulAdd(float[] dd, float[] mData, float _t0, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _t18, float _sp0, float _sp1, float _sp2, float _sp3) {
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
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
        float _rd0 = mData[12];
        float _rd1 = mData[13];
        float _rd2 = mData[14];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * ((_t0) * (_t64) + (((_rd0) * (_t66) + (_rd1 * _t65))));
        dd[5] = 0.5f * ((_rd2) * (_t63) + (((_rd1) * (_t66) - (_rd0 * _t65))));
        return makeFromMatrix_s40927f3d_2_mulAdd(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatDualQuat makeFromMatrix_s40927f3d_2_fma(float[] dd, float _t0, float _t63, float _t64, float _t65, float _t66, float _rd0, float _rd1, float _rd2) {
        dd[6] = 0.5f * java.lang.Math.fma(_rd2, _t66, java.lang.Math.fma(_rd0, _t64, -(_rd1 * _t63)));
        dd[7] = 0.5f * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_rd1, _t64, -(_rd0 * _t63)));
        return this;
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatDualQuat makeFromMatrix_s40927f3d_2_mulAdd(float[] dd, float _t0, float _t63, float _t64, float _t65, float _t66, float _rd0, float _rd1, float _rd2) {
        dd[6] = 0.5f * ((_rd2) * (_t66) + (((_rd0) * (_t64) - (_rd1 * _t63))));
        dd[7] = 0.5f * ((_t0) * (_t65) + (((-_rd1) * (_t64) - (_rd0 * _t63))));
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
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeFromMatrix_fma(Float3x4R m) {
        float[] mData = ((Float3x4Impl) m).data;
        float _t2 = 1.0f - mData[0];
        float _t14 = mData[10] + (mData[0] + mData[5]);
        float _t15 = 1.0f + _t14;
        float _t16 = mData[0] + (1.0f - mData[5] - mData[10]);
        float _t17 = mData[5] + (_t2 - mData[10]);
        float _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s7b6de20e_1_fma(this.data, mData, -mData[11], mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t14, _t15, _t16, _t17, _t18, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeFromMatrix_mulAdd(Float3x4R m) {
        float[] mData = ((Float3x4Impl) m).data;
        float _t2 = 1.0f - mData[0];
        float _t14 = mData[10] + (mData[0] + mData[5]);
        float _t15 = 1.0f + _t14;
        float _t16 = mData[0] + (1.0f - mData[5] - mData[10]);
        float _t17 = mData[5] + (_t2 - mData[10]);
        float _t18 = mData[10] + (_t2 - mData[5]);
        return makeFromMatrix_s7b6de20e_1_mulAdd(this.data, mData, -mData[11], mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t14, _t15, _t16, _t17, _t18, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s7b6de20e_1_fma(float[] dd, float[] mData, float _t0, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _t18, float _sp0, float _sp1, float _sp2, float _sp3) {
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
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
        float _rd0 = mData[3];
        float _rd1 = mData[7];
        float _rd2 = mData[11];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_rd0, _t66, _rd1 * _t65));
        dd[5] = 0.5f * java.lang.Math.fma(_rd2, _t63, java.lang.Math.fma(_rd1, _t66, -(_rd0 * _t65)));
        return makeFromMatrix_s40927f3d_2_fma(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s7b6de20e_1_mulAdd(float[] dd, float[] mData, float _t0, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _t18, float _sp0, float _sp1, float _sp2, float _sp3) {
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (mData[0] > java.lang.Math.max(mData[5], mData[10])) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (mData[5] > mData[10]) {
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
        float _rd0 = mData[3];
        float _rd1 = mData[7];
        float _rd2 = mData[11];
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * ((_t0) * (_t64) + (((_rd0) * (_t66) + (_rd1 * _t65))));
        dd[5] = 0.5f * ((_rd2) * (_t63) + (((_rd1) * (_t66) - (_rd0 * _t65))));
        return makeFromMatrix_s40927f3d_2_mulAdd(dd, _t0, _t63, _t64, _t65, _t66, _rd0, _rd1, _rd2);
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
        float[] mData = ((Float3x3Impl) m).data;
        float _t1 = 1.0f - mData[0];
        float _t13 = mData[8] + (mData[0] + mData[4]);
        float _t14 = 1.0f + _t13;
        float _t15 = mData[0] + (1.0f - mData[4] - mData[8]);
        float _t16 = mData[4] + (_t1 - mData[8]);
        float _t17 = mData[8] + (_t1 - mData[4]);
        return makeFromMatrix_s4398bb65_1(this.data, mData, mData[5] - mData[7], mData[3] + mData[1], mData[6] + mData[2], mData[6] - mData[2], mData[7] + mData[5], mData[1] - mData[3], _t13, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s4398bb65_1(float[] dd, float[] mData, float _t3, float _t5, float _t6, float _t7, float _t8, float _t9, float _t13, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t13 > 0.0f) {
            dd[0] = _sp0 * _t3;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t14);
        } else {
            if (mData[0] > java.lang.Math.max(mData[4], mData[8])) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t15);
                dd[1] = _sp3 * _t5;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t3;
            } else {
                if (mData[4] > mData[8]) {
                    dd[0] = _sp1 * _t5;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                } else {
                    dd[0] = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0f) {
            dd[0] = sd[0] * _t5;
            dd[1] = sd[1] * _t5;
            dd[2] = sd[2] * _t5;
            dd[3] = sd[3] * _t5;
            dd[4] = sd[4] * _t5;
            dd[5] = sd[5] * _t5;
            dd[6] = sd[6] * _t5;
            dd[7] = sd[7] * _t5;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
            dd[7] = 0.0f;
        }
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t4 = Math.fma(sd[0], sd[0], sd[1] * sd[1]) + Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0f) {
            dd[0] = sd[0] * _t5;
            dd[1] = sd[1] * _t5;
            dd[2] = sd[2] * _t5;
            dd[3] = sd[3] * _t5;
            dd[4] = sd[4] * _t5;
            dd[5] = sd[5] * _t5;
            dd[6] = sd[6] * _t5;
            dd[7] = sd[7] * _t5;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
            dd[7] = 0.0f;
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
    public FloatDualQuat pow(float t, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return pow_fma(t, dest);
        return pow_mulAdd(t, dest);
    }

    /** {@code pow} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat pow_fma(float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        return pow_see3b1b7e_1_fma(t, dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat pow_mulAdd(float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        return pow_see3b1b7e_1_mulAdd(t, dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** Piece 2 of {@code pow}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat pow_see3b1b7e_1_fma(float t, FloatDualQuat dest, float[] dd, float _t8, float _t9, float _t10, float _t12, float _t13, float _t14, float _t15, float _t18, float _t19, float _t21, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31) {
        if (_t18 < 1.0E-12f) {
            dd[0] = t * _t9;
            dd[1] = t * _t10;
            dd[2] = t * _t8;
            dd[3] = 1.0f;
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
    private FloatDualQuat pow_see3b1b7e_1_mulAdd(float t, FloatDualQuat dest, float[] dd, float _t8, float _t9, float _t10, float _t12, float _t13, float _t14, float _t15, float _t18, float _t19, float _t21, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31) {
        if (_t18 < 1.0E-12f) {
            dd[0] = t * _t9;
            dd[1] = t * _t10;
            dd[2] = t * _t8;
            dd[3] = 1.0f;
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
        if (Math.useFma()) return pow_fma(t, dest);
        return pow_mulAdd(t, dest);
    }

    /** {@code pow} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat pow_fma(float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s250262f9_1_fma(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat pow_mulAdd(float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (sd[3] < 0.0f) {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s250262f9_1_mulAdd(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, _t28, _t25 * _t11, _t30, _t27 * _t30);
    }

    /** Piece 2 of {@code pow}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat pow_s250262f9_1_fma(float t, DoubleDualQuat dest, double[] dd, float _t8, float _t9, float _t10, float _t12, float _t13, float _t14, float _t15, float _t18, float _t19, float _t21, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31) {
        if (_t18 < 1.0E-12f) {
            dd[0] = t * _t9;
            dd[1] = t * _t10;
            dd[2] = t * _t8;
            dd[3] = 1.0f;
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
    private DoubleDualQuat pow_s250262f9_1_mulAdd(float t, DoubleDualQuat dest, double[] dd, float _t8, float _t9, float _t10, float _t12, float _t13, float _t14, float _t15, float _t18, float _t19, float _t21, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31) {
        if (_t18 < 1.0E-12f) {
            dd[0] = t * _t9;
            dd[1] = t * _t10;
            dd[2] = t * _t8;
            dd[3] = 1.0f;
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
    public FloatDualQuat setDual(FloatQuatR dual, @Mutated FloatDualQuat dest) {
        float dualX = dual.x();
        float dualY = dual.y();
        float dualZ = dual.z();
        float dualW = dual.w();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat setDual(float dualX, float dualY, float dualZ, float dualW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat setReal(FloatQuatR real, @Mutated FloatDualQuat dest) {
        float realY = real.y();
        float realZ = real.z();
        float realW = real.w();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
    public FloatDualQuat setReal(float realX, float realY, float realZ, float realW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float[] sd = this.data;
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        float _t23 = 2.0f * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        float _t24 = 2.0f * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        dd[5] = 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        dd[6] = 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        dd[7] = 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        float _t23 = 2.0f * (Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        float _t24 = 2.0f * (Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24));
        dd[5] = 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24)));
        dd[6] = 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23)));
        dd[7] = 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24)));
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
    public FloatDualQuat setTranslation(Float3R translation, @Mutated FloatDualQuat dest) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = 0.5f * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        dd[5] = 0.5f * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        dd[6] = 0.5f * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        dd[7] = 0.5f * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.5f * Math.fma(_t0, translationZ, Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * Math.fma(sd[0], translationZ, Math.fma(sd[3], translationY, -(sd[2] * translationX)));
        dd[6] = 0.5f * Math.fma(sd[3], translationZ, Math.fma(sd[1], translationX, -(sd[0] * translationY)));
        dd[7] = 0.5f * Math.fma(-sd[2], translationZ, Math.fma(_t0, translationY, -(sd[0] * translationX)));
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
    public FloatDualQuat setTranslation(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = 0.5f * Math.fma(_t0, translationZ, Math.fma(_rd2, translationY, _rd3 * translationX));
        dd[5] = 0.5f * Math.fma(_rd0, translationZ, Math.fma(_rd3, translationY, -(_rd2 * translationX)));
        dd[6] = 0.5f * Math.fma(_rd3, translationZ, Math.fma(_rd1, translationX, -(_rd0 * translationY)));
        dd[7] = 0.5f * Math.fma(-_rd2, translationZ, Math.fma(_t0, translationY, -(_rd0 * translationX)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.5f * Math.fma(_t0, translationZ, Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * Math.fma(sd[0], translationZ, Math.fma(sd[3], translationY, -(sd[2] * translationX)));
        dd[6] = 0.5f * Math.fma(sd[3], translationZ, Math.fma(sd[1], translationX, -(sd[0] * translationY)));
        dd[7] = 0.5f * Math.fma(-sd[2], translationZ, Math.fma(_t0, translationY, -(sd[0] * translationX)));
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
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        if (Math.useFma()) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_fma(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * java.lang.Math.fma(_rd0, _rd1, _t2);
        dd[2] = java.lang.Math.fma(-2.0f, _t3, _sp0 * _rd2);
        dd[3] = 0.0f;
        dd[4] = java.lang.Math.fma(-2.0f, _t2, _sp0 * _rd1);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = 2.0f * java.lang.Math.fma(_rd0, _rd3, _t5);
        dd[7] = 0.0f;
        dd[8] = 2.0f * java.lang.Math.fma(_rd0, _rd2, _t3);
        dd[9] = java.lang.Math.fma(-2.0f, _rd0 * _rd3, _t5 + _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 0.0f;
        return toMatrix_s6357fbbf_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((-2.0f) * (sd[2] * sd[2]) + (1.0f));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((-2.0f) * (_t0) + (_t6));
        dd[1] = 2.0f * ((_rd0) * (_rd1) + (_t2));
        dd[2] = ((-2.0f) * (_t3) + (_sp0 * _rd2));
        dd[3] = 0.0f;
        dd[4] = ((-2.0f) * (_t2) + (_sp0 * _rd1));
        dd[5] = ((-2.0f) * (_t4) + (_t6));
        dd[6] = 2.0f * ((_rd0) * (_rd3) + (_t5));
        dd[7] = 0.0f;
        dd[8] = 2.0f * ((_rd0) * (_rd2) + (_t3));
        dd[9] = ((-2.0f) * (_rd0 * _rd3) + (_t5 + _t5));
        dd[10] = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
        dd[11] = 0.0f;
        return toMatrix_s6357fbbf_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1_fma(Float4x4 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[12] = 2.0f * (java.lang.Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + java.lang.Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[13] = 2.0f * (java.lang.Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[14] = 2.0f * (java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + java.lang.Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        dd[15] = 1.0f;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1_mulAdd(Float4x4 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[12] = 2.0f * (((_rd1) * (_rd6) - (_rd2 * _rd5)) + ((_rd3) * (_rd4) - (_rd0 * _rd7)));
        dd[13] = 2.0f * (((_rd2) * (_rd4) - (_rd0 * _rd6)) + ((_rd3) * (_rd5) - (_rd1 * _rd7)));
        dd[14] = 2.0f * (((_rd0) * (_rd5) - (_rd1 * _rd4)) + ((_rd3) * (_rd6) - (_rd2 * _rd7)));
        dd[15] = 1.0f;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * Math.fma(sd[0], sd[1], _t2);
        dd[2] = Math.fma(-2.0f, _t3, _sp0 * sd[2]);
        dd[3] = 0.0f;
        dd[4] = Math.fma(-2.0f, _t2, _sp0 * sd[1]);
        dd[5] = Math.fma(-2.0f, _t4, _t6);
        dd[6] = 2.0f * Math.fma(sd[0], sd[3], _t5);
        dd[7] = 0.0f;
        dd[8] = 2.0f * Math.fma(sd[0], sd[2], _t3);
        dd[9] = Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[10] = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 0.0f;
        return (Math.useFma() ? toMatrix_s9c292502_1_fma(dest, sd, dd) : toMatrix_s9c292502_1_mulAdd(dest, sd, dd));
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1_fma(Double4x4 dest, float[] sd, double[] dd) {
        dd[12] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[13] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[14] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        dd[15] = 1.0f;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1_mulAdd(Double4x4 dest, float[] sd, double[] dd) {
        dd[12] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[13] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[14] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
        dd[15] = 1.0f;
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * Math.fma(_rd0, _rd1, _t2);
        dd[2] = Math.fma(-2.0f, _t3, _sp0 * _rd2);
        dd[3] = Math.fma(-2.0f, _t2, _sp0 * _rd1);
        dd[4] = Math.fma(-2.0f, _t4, _t6);
        dd[5] = 2.0f * Math.fma(_rd0, _rd3, _t5);
        dd[6] = 2.0f * Math.fma(_rd0, _rd2, _t3);
        dd[7] = Math.fma(-2.0f, _rd0 * _rd3, _t5 + _t5);
        dd[8] = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
        ((Float3x3Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * Math.fma(sd[0], sd[1], _t2);
        dd[2] = Math.fma(-2.0f, _t3, _sp0 * sd[2]);
        dd[3] = Math.fma(-2.0f, _t2, _sp0 * sd[1]);
        dd[4] = Math.fma(-2.0f, _t4, _t6);
        dd[5] = 2.0f * Math.fma(sd[0], sd[3], _t5);
        dd[6] = 2.0f * Math.fma(sd[0], sd[2], _t3);
        dd[7] = Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[8] = Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f));
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        if (Math.useFma()) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_fma(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = java.lang.Math.fma(-2.0f, _t2, _sp0 * _rd1);
        dd[2] = 2.0f * java.lang.Math.fma(_rd0, _rd2, _t3);
        dd[3] = 2.0f * (java.lang.Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + java.lang.Math.fma(_rd3, _rd4, -(_rd0 * _rd7)));
        dd[4] = 2.0f * java.lang.Math.fma(_rd0, _rd1, _t2);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = java.lang.Math.fma(-2.0f, _rd0 * _rd3, _t5 + _t5);
        return toMatrix3x4_scac7d5c1_1_fma(dest, dd, _sp0, _t0, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((-2.0f) * (sd[2] * sd[2]) + (1.0f));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((-2.0f) * (_t0) + (_t6));
        dd[1] = ((-2.0f) * (_t2) + (_sp0 * _rd1));
        dd[2] = 2.0f * ((_rd0) * (_rd2) + (_t3));
        dd[3] = 2.0f * (((_rd1) * (_rd6) - (_rd2 * _rd5)) + ((_rd3) * (_rd4) - (_rd0 * _rd7)));
        dd[4] = 2.0f * ((_rd0) * (_rd1) + (_t2));
        dd[5] = ((-2.0f) * (_t4) + (_t6));
        dd[6] = ((-2.0f) * (_rd0 * _rd3) + (_t5 + _t5));
        return toMatrix3x4_scac7d5c1_1_mulAdd(dest, dd, _sp0, _t0, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Float3x4 toMatrix3x4_scac7d5c1_1_fma(Float3x4 dest, float[] dd, float _sp0, float _t0, float _t3, float _t4, float _t5, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[7] = 2.0f * (java.lang.Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd7)));
        dd[8] = java.lang.Math.fma(-2.0f, _t3, _sp0 * _rd2);
        dd[9] = 2.0f * java.lang.Math.fma(_rd0, _rd3, _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 2.0f * (java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + java.lang.Math.fma(_rd3, _rd6, -(_rd2 * _rd7)));
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Float3x4 toMatrix3x4_scac7d5c1_1_mulAdd(Float3x4 dest, float[] dd, float _sp0, float _t0, float _t3, float _t4, float _t5, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[7] = 2.0f * (((_rd2) * (_rd4) - (_rd0 * _rd6)) + ((_rd3) * (_rd5) - (_rd1 * _rd7)));
        dd[8] = ((-2.0f) * (_t3) + (_sp0 * _rd2));
        dd[9] = 2.0f * ((_rd0) * (_rd3) + (_t5));
        dd[10] = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
        dd[11] = 2.0f * (((_rd0) * (_rd5) - (_rd1 * _rd4)) + ((_rd3) * (_rd6) - (_rd2 * _rd7)));
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = java.lang.Math.fma(-2.0f, _t2, _sp0 * sd[1]);
        dd[2] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[3] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[4] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[7] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[8] = java.lang.Math.fma(-2.0f, _t3, _sp0 * sd[2]);
        return toMatrix3x4_sec42bb4e_1_fma(dest, sd, dd, _t0, _t4, _t5);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((-2.0f) * (sd[2] * sd[2]) + (1.0f));
        dd[0] = ((-2.0f) * (_t0) + (_t6));
        dd[1] = ((-2.0f) * (_t2) + (_sp0 * sd[1]));
        dd[2] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[3] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[4] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[5] = ((-2.0f) * (_t4) + (_t6));
        dd[6] = ((-2.0f) * (sd[0] * sd[3]) + (_t5 + _t5));
        dd[7] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[8] = ((-2.0f) * (_t3) + (_sp0 * sd[2]));
        return toMatrix3x4_sec42bb4e_1_mulAdd(dest, sd, dd, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1_fma(Double3x4 dest, float[] sd, double[] dd, float _t0, float _t4, float _t5) {
        dd[9] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1_mulAdd(Double3x4 dest, float[] sd, double[] dd, float _t0, float _t4, float _t5) {
        dd[9] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[10] = ((-2.0f) * (_t4) + (((-2.0f) * (_t0) + (1.0f))));
        dd[11] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        return lookAlong_sf6a57214_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat lookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        return lookAlong_sf6a57214_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_1_fma(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = java.lang.Math.fma(-_t37, _t45, 1.0f);
        float _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        return lookAlong_sf6a57214_2_fma(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_1_mulAdd(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = ((-_t37) * (_t45) + (1.0f));
        float _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        return lookAlong_sf6a57214_2_mulAdd(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_2_fma(float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84));
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t129, _rd3 * _t126) + java.lang.Math.fma(_rd1, _t127, -(_rd2 * _t128));
        return lookAlong_sf6a57214_3_fma(dest, dd, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sf6a57214_2_mulAdd(float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84));
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t129) + (_rd3 * _t126)) + ((_rd1) * (_t127) - (_rd2 * _t128));
        return lookAlong_sf6a57214_3_mulAdd(dest, dd, _t126, _t127, _t128, _t129, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_sf6a57214_3_fma(FloatDualQuat dest, float[] dd, float _t126, float _t127, float _t128, float _t129, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[1] = java.lang.Math.fma(_rd1, _t129, _rd2 * _t126) + java.lang.Math.fma(_rd3, _t128, -(_rd0 * _t127));
        dd[2] = java.lang.Math.fma(_rd0, _t128, _rd3 * _t127) + java.lang.Math.fma(_rd2, _t129, -(_rd1 * _t126));
        dd[3] = java.lang.Math.fma(_rd3, _t129, -(_rd0 * _t126)) - java.lang.Math.fma(_rd1, _t128, _rd2 * _t127);
        dd[4] = java.lang.Math.fma(_rd4, _t129, _rd7 * _t126) + java.lang.Math.fma(_rd5, _t127, -(_rd6 * _t128));
        dd[5] = java.lang.Math.fma(_rd5, _t129, _rd6 * _t126) + java.lang.Math.fma(_rd7, _t128, -(_rd4 * _t127));
        dd[6] = java.lang.Math.fma(_rd4, _t128, _rd7 * _t127) + java.lang.Math.fma(_rd6, _t129, -(_rd5 * _t126));
        dd[7] = java.lang.Math.fma(_rd7, _t129, -(_rd4 * _t126)) - java.lang.Math.fma(_rd5, _t128, _rd6 * _t127);
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_sf6a57214_3_mulAdd(FloatDualQuat dest, float[] dd, float _t126, float _t127, float _t128, float _t129, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        return lookAlong_s1985c6f3_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        return lookAlong_s1985c6f3_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_1_fma(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = java.lang.Math.fma(-_t37, _t45, 1.0f);
        float _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        return lookAlong_s1985c6f3_2_fma(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_1_mulAdd(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = ((-_t37) * (_t45) + (1.0f));
        float _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        return lookAlong_s1985c6f3_2_mulAdd(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_2_fma(float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84));
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
        dd[0] = java.lang.Math.fma(sd[0], _t129, sd[3] * _t126) + java.lang.Math.fma(sd[1], _t127, -(sd[2] * _t128));
        dd[1] = java.lang.Math.fma(sd[1], _t129, sd[2] * _t126) + java.lang.Math.fma(sd[3], _t128, -(sd[0] * _t127));
        return lookAlong_s1985c6f3_3_fma(dest, sd, dd, _t126, _t127, _t128, _t129);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_2_mulAdd(float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84));
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
        dd[0] = ((sd[0]) * (_t129) + (sd[3] * _t126)) + ((sd[1]) * (_t127) - (sd[2] * _t128));
        dd[1] = ((sd[1]) * (_t129) + (sd[2] * _t126)) + ((sd[3]) * (_t128) - (sd[0] * _t127));
        return lookAlong_s1985c6f3_3_mulAdd(dest, sd, dd, _t126, _t127, _t128, _t129);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t126, float _t127, float _t128, float _t129) {
        dd[2] = java.lang.Math.fma(sd[0], _t128, sd[3] * _t127) + java.lang.Math.fma(sd[2], _t129, -(sd[1] * _t126));
        dd[3] = java.lang.Math.fma(sd[3], _t129, -(sd[0] * _t126)) - java.lang.Math.fma(sd[1], _t128, sd[2] * _t127);
        dd[4] = java.lang.Math.fma(sd[4], _t129, sd[7] * _t126) + java.lang.Math.fma(sd[5], _t127, -(sd[6] * _t128));
        dd[5] = java.lang.Math.fma(sd[5], _t129, sd[6] * _t126) + java.lang.Math.fma(sd[7], _t128, -(sd[4] * _t127));
        dd[6] = java.lang.Math.fma(sd[4], _t128, sd[7] * _t127) + java.lang.Math.fma(sd[6], _t129, -(sd[5] * _t126));
        dd[7] = java.lang.Math.fma(sd[7], _t129, -(sd[4] * _t126)) - java.lang.Math.fma(sd[5], _t128, sd[6] * _t127);
        return dest;
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_s1985c6f3_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t126, float _t127, float _t128, float _t129) {
        dd[2] = ((sd[0]) * (_t128) + (sd[3] * _t127)) + ((sd[2]) * (_t129) - (sd[1] * _t126));
        dd[3] = ((sd[3]) * (_t129) - (sd[0] * _t126)) - ((sd[1]) * (_t128) + (sd[2] * _t127));
        dd[4] = ((sd[4]) * (_t129) + (sd[7] * _t126)) + ((sd[5]) * (_t127) - (sd[6] * _t128));
        dd[5] = ((sd[5]) * (_t129) + (sd[6] * _t126)) + ((sd[7]) * (_t128) - (sd[4] * _t127));
        dd[6] = ((sd[4]) * (_t128) + (sd[7] * _t127)) + ((sd[6]) * (_t129) - (sd[5] * _t126));
        dd[7] = ((sd[7]) * (_t129) - (sd[4] * _t126)) - ((sd[5]) * (_t128) + (sd[6] * _t127));
        return dest;
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
        return lookAlong_degenerate_s82df72c1_1_fma(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t44, _t45, java.lang.Math.fma(_t43, _t24, _t21), java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)));
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
        return lookAlong_degenerate_s82df72c1_1_mulAdd(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t44, _t45, ((_t43) * (_t24) + (_t21)), ((_t44) * (_t26) - (_t45 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46, float _t55) {
        float _t56 = java.lang.Math.fma(_t46, _t25, -(_t44 * _t24));
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
        return lookAlong_degenerate_s82df72c1_2_fma(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46, float _t55) {
        float _t56 = ((_t46) * (_t25) - (_t44 * _t24));
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
        return lookAlong_degenerate_s82df72c1_2_mulAdd(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_2_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s82df72c1_3_fma(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_2_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s82df72c1_3_mulAdd(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_3_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t151, _rd3 * _t148) + java.lang.Math.fma(_rd1, _t149, -(_rd2 * _t150));
        return lookAlong_sf6a57214_3_fma(dest, dd, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_s82df72c1_3_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
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
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t151) + (_rd3 * _t148)) + ((_rd1) * (_t149) - (_rd2 * _t150));
        return lookAlong_sf6a57214_3_mulAdd(dest, dd, _t148, _t149, _t150, _t151, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
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
        return lookAlong_degenerate_s34d9c4c0_1_fma(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t44, _t45, java.lang.Math.fma(_t43, _t24, _t21), java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)));
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
        return lookAlong_degenerate_s34d9c4c0_1_mulAdd(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t44, _t45, ((_t43) * (_t24) + (_t21)), ((_t44) * (_t26) - (_t45 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46, float _t55) {
        float _t56 = java.lang.Math.fma(_t46, _t25, -(_t44 * _t24));
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
        return lookAlong_degenerate_s34d9c4c0_2_fma(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46, float _t55) {
        float _t56 = ((_t46) * (_t25) - (_t44 * _t24));
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
        return lookAlong_degenerate_s34d9c4c0_2_mulAdd(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_2_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s34d9c4c0_3_fma(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_2_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s34d9c4c0_3_mulAdd(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
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
        dd[0] = java.lang.Math.fma(sd[0], _t151, sd[3] * _t148) + java.lang.Math.fma(sd[1], _t149, -(sd[2] * _t150));
        dd[1] = java.lang.Math.fma(sd[1], _t151, sd[2] * _t148) + java.lang.Math.fma(sd[3], _t150, -(sd[0] * _t149));
        dd[2] = java.lang.Math.fma(sd[0], _t150, sd[3] * _t149) + java.lang.Math.fma(sd[2], _t151, -(sd[1] * _t148));
        return lookAlong_degenerate_s34d9c4c0_4_fma(dest, sd, dd, _t148, _t149, _t150, _t151);
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
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
        dd[0] = ((sd[0]) * (_t151) + (sd[3] * _t148)) + ((sd[1]) * (_t149) - (sd[2] * _t150));
        dd[1] = ((sd[1]) * (_t151) + (sd[2] * _t148)) + ((sd[3]) * (_t150) - (sd[0] * _t149));
        dd[2] = ((sd[0]) * (_t150) + (sd[3] * _t149)) + ((sd[2]) * (_t151) - (sd[1] * _t148));
        return lookAlong_degenerate_s34d9c4c0_4_mulAdd(dest, sd, dd, _t148, _t149, _t150, _t151);
    }

    /**
     * Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget. Shared by the
     * identical private paths of {@code lookAlong} and {@code rotateAxis}; reached only through
     * them.
     */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_4_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t148, float _t149, float _t150, float _t151) {
        dd[3] = java.lang.Math.fma(sd[3], _t151, -(sd[0] * _t148)) - java.lang.Math.fma(sd[1], _t150, sd[2] * _t149);
        dd[4] = java.lang.Math.fma(sd[4], _t151, sd[7] * _t148) + java.lang.Math.fma(sd[5], _t149, -(sd[6] * _t150));
        dd[5] = java.lang.Math.fma(sd[5], _t151, sd[6] * _t148) + java.lang.Math.fma(sd[7], _t150, -(sd[4] * _t149));
        dd[6] = java.lang.Math.fma(sd[4], _t150, sd[7] * _t149) + java.lang.Math.fma(sd[6], _t151, -(sd[5] * _t148));
        dd[7] = java.lang.Math.fma(sd[7], _t151, -(sd[4] * _t148)) - java.lang.Math.fma(sd[5], _t150, sd[6] * _t149);
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_degenerate}, split to fit the inline budget. Shared by the
     * identical private paths of {@code lookAlong} and {@code rotateAxis}; reached only through
     * them.
     */
    private DoubleDualQuat lookAlong_degenerate_s34d9c4c0_4_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t148, float _t149, float _t150, float _t151) {
        dd[3] = ((sd[3]) * (_t151) - (sd[0] * _t148)) - ((sd[1]) * (_t150) + (sd[2] * _t149));
        dd[4] = ((sd[4]) * (_t151) + (sd[7] * _t148)) + ((sd[5]) * (_t149) - (sd[6] * _t150));
        dd[5] = ((sd[5]) * (_t151) + (sd[6] * _t148)) + ((sd[7]) * (_t150) - (sd[4] * _t149));
        dd[6] = ((sd[4]) * (_t150) + (sd[7] * _t149)) + ((sd[6]) * (_t151) - (sd[5] * _t148));
        dd[7] = ((sd[7]) * (_t151) - (sd[4] * _t148)) - ((sd[5]) * (_t150) + (sd[6] * _t149));
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
    public @Mutated FloatDualQuat makeRotationAxis(float angle, Float3R axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
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
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s25627a91_1_fma(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** {@code makeRotationLookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationLookAlong_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
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
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_s25627a91_1_mulAdd(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_1_fma(float dirX, float dirY, float dirZ, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = java.lang.Math.fma(-_t37, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50)));
        float _t82 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s25627a91_2_fma(dirZ, dd, _t16, _t17, _t37, _t45, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t64), java.lang.Math.fma(-dirY, _t16, _t64), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_1_mulAdd(float dirX, float dirY, float dirZ, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = ((-_t37) * (_t45) + (1.0f));
        float _t64 = ((_t18) * (_t48) - (_t19 * _t46));
        float _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        float _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0f))))))));
        float _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0f))))))));
        float _t81 = ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50))))));
        float _t82 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50))))));
        return makeRotationLookAlong_s25627a91_2_mulAdd(dirZ, dd, _t16, _t17, _t37, _t45, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t64)), ((-dirY) * (_t16) + (_t64)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_2_fma(float dirZ, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t56, float _t63, float _t69, float _t70, float _t74, float _t75, float _t78, float _sp1, float _t80, float _t81, float _t82, float _sp3, float _sp4, float _sp2) {
        if (java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t37, _t45, _t63)) > 0.0f) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return this;
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_s25627a91_2_mulAdd(float dirZ, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t56, float _t63, float _t69, float _t70, float _t74, float _t75, float _t78, float _sp1, float _t80, float _t81, float _t82, float _sp3, float _sp4, float _sp2) {
        if (((dirZ) * (_t16) + (((_t37) * (_t45) + (_t63)))) > 0.0f) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return this;
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
        return makeRotationLookAlong_degenerate_s1ae3d4da_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)), java.lang.Math.fma(_t46, _t25, -(_t44 * _t24)));
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
        return makeRotationLookAlong_degenerate_s1ae3d4da_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, _t45, _t46, ((_t44) * (_t26) - (_t45 * _t25)), ((_t46) * (_t25) - (_t44 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_1_fma(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
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
        return makeRotationLookAlong_degenerate_s1ae3d4da_2_fma(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)), java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_1_mulAdd(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t45, float _t46, float _t55, float _t56) {
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
        return makeRotationLookAlong_degenerate_s1ae3d4da_2_mulAdd(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))), ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_2_fma(float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        float _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        float _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_degenerate_s1ae3d4da_3_fma(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_2_mulAdd(float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98) {
        float _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        float _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        float _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return makeRotationLookAlong_degenerate_s1ae3d4da_3_mulAdd(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), _t101, _t102, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_3_fma(float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp0, float _t101, float _t102, float _sp3, float _sp1, float _sp2, float _t106, float _t107) {
        if (java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t24))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return this;
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_3_mulAdd(float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp0, float _t101, float _t102, float _sp3, float _sp1, float _sp2, float _t106, float _t107) {
        if (((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t24)))))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
    @Mutated public FloatDualQuat makeRotationX(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dd[0] = _t1;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = _t1;
        dd[2] = 0.0f;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t9, _t6, _t12 * _t5);
        dd[1] = Math.fma(_t10, _t6, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dd[3] = Math.fma(_t12, _t6, -(_t9 * _t5));
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = _t1;
        dd[3] = Math.cosFromSin(_t1, _t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] dd = this.data;
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
        dd[0] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[1] = Math.fma(_t10, _t8, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t8);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(rotationX, sd[3], rotationW * sd[0]) + Math.fma(rotationY, sd[2], -(rotationZ * sd[1]));
        dd[1] = Math.fma(rotationY, sd[3], rotationZ * sd[0]) + Math.fma(rotationW, sd[1], -(rotationX * sd[2]));
        dd[2] = Math.fma(rotationX, sd[1], rotationW * sd[2]) + Math.fma(rotationZ, sd[3], -(rotationY * sd[0]));
        dd[3] = Math.fma(rotationW, sd[3], -(rotationX * sd[0])) - Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        dd[4] = Math.fma(rotationX, sd[7], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[5]));
        dd[5] = Math.fma(rotationY, sd[7], rotationZ * sd[4]) + Math.fma(rotationW, sd[5], -(rotationX * sd[6]));
        dd[6] = Math.fma(rotationX, sd[5], rotationW * sd[6]) + Math.fma(rotationZ, sd[7], -(rotationY * sd[4]));
        dd[7] = Math.fma(rotationW, sd[7], -(rotationX * sd[4])) - Math.fma(rotationY, sd[5], rotationZ * sd[6]);
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = Math.fma(rotationX, sd[3], rotationW * sd[0]) + Math.fma(rotationZ, sd[1], -(rotationY * sd[2]));
        dd[1] = Math.fma(rotationX, sd[2], rotationW * sd[1]) + Math.fma(rotationY, sd[3], -(rotationZ * sd[0]));
        dd[2] = Math.fma(rotationY, sd[0], rotationZ * sd[3]) + Math.fma(rotationW, sd[2], -(rotationX * sd[1]));
        dd[3] = Math.fma(rotationW, sd[3], -(rotationX * sd[0])) - Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        dd[4] = Math.fma(rotationX, sd[7], rotationW * sd[4]) + Math.fma(rotationZ, sd[5], -(rotationY * sd[6]));
        dd[5] = Math.fma(rotationX, sd[6], rotationW * sd[5]) + Math.fma(rotationY, sd[7], -(rotationZ * sd[4]));
        dd[6] = Math.fma(rotationY, sd[4], rotationZ * sd[7]) + Math.fma(rotationW, sd[6], -(rotationX * sd[5]));
        dd[7] = Math.fma(rotationW, sd[7], -(rotationX * sd[4])) - Math.fma(rotationY, sd[5], rotationZ * sd[6]);
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
    public FloatDualQuat rotateAxis(float angle, Float3R axis, @Mutated FloatDualQuat dest) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
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
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
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
    public FloatDualQuat rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatDualQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t5, _rd3 * _t2) + java.lang.Math.fma(_rd1, _t3, -(_rd2 * _t4));
        dd[1] = java.lang.Math.fma(_rd1, _t5, _rd2 * _t2) + java.lang.Math.fma(_rd3, _t4, -(_rd0 * _t3));
        return rotateAxis_s52b79d91_1_fma(dest, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t5) + (_rd3 * _t2)) + ((_rd1) * (_t3) - (_rd2 * _t4));
        dd[1] = ((_rd1) * (_t5) + (_rd2 * _t2)) + ((_rd3) * (_t4) - (_rd0 * _t3));
        return rotateAxis_s52b79d91_1_mulAdd(dest, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateAxis_s52b79d91_1_fma(FloatDualQuat dest, float[] dd, float _t2, float _t3, float _t4, float _t5, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[2] = java.lang.Math.fma(_rd0, _t4, _rd3 * _t3) + java.lang.Math.fma(_rd2, _t5, -(_rd1 * _t2));
        dd[3] = java.lang.Math.fma(_rd3, _t5, -(_rd0 * _t2)) - java.lang.Math.fma(_rd1, _t4, _rd2 * _t3);
        dd[4] = java.lang.Math.fma(_rd4, _t5, _rd7 * _t2) + java.lang.Math.fma(_rd5, _t3, -(_rd6 * _t4));
        dd[5] = java.lang.Math.fma(_rd5, _t5, _rd6 * _t2) + java.lang.Math.fma(_rd7, _t4, -(_rd4 * _t3));
        dd[6] = java.lang.Math.fma(_rd4, _t4, _rd7 * _t3) + java.lang.Math.fma(_rd6, _t5, -(_rd5 * _t2));
        dd[7] = java.lang.Math.fma(_rd7, _t5, -(_rd4 * _t2)) - java.lang.Math.fma(_rd5, _t4, _rd6 * _t3);
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateAxis_s52b79d91_1_mulAdd(FloatDualQuat dest, float[] dd, float _t2, float _t3, float _t4, float _t5, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
        dd[2] = ((_rd0) * (_t4) + (_rd3 * _t3)) + ((_rd2) * (_t5) - (_rd1 * _t2));
        dd[3] = ((_rd3) * (_t5) - (_rd0 * _t2)) - ((_rd1) * (_t4) + (_rd2 * _t3));
        dd[4] = ((_rd4) * (_t5) + (_rd7 * _t2)) + ((_rd5) * (_t3) - (_rd6 * _t4));
        dd[5] = ((_rd5) * (_t5) + (_rd6 * _t2)) + ((_rd7) * (_t4) - (_rd4 * _t3));
        dd[6] = ((_rd4) * (_t4) + (_rd7 * _t3)) + ((_rd6) * (_t5) - (_rd5 * _t2));
        dd[7] = ((_rd7) * (_t5) - (_rd4 * _t2)) - ((_rd5) * (_t4) + (_rd6 * _t3));
        return dest;
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
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dd[0] = java.lang.Math.fma(sd[0], _t5, sd[3] * _t2) + java.lang.Math.fma(sd[1], _t3, -(sd[2] * _t4));
        dd[1] = java.lang.Math.fma(sd[1], _t5, sd[2] * _t2) + java.lang.Math.fma(sd[3], _t4, -(sd[0] * _t3));
        dd[2] = java.lang.Math.fma(sd[0], _t4, sd[3] * _t3) + java.lang.Math.fma(sd[2], _t5, -(sd[1] * _t2));
        return lookAlong_degenerate_s34d9c4c0_4_fma(dest, sd, dd, _t2, _t3, _t4, _t5);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dd[0] = ((sd[0]) * (_t5) + (sd[3] * _t2)) + ((sd[1]) * (_t3) - (sd[2] * _t4));
        dd[1] = ((sd[1]) * (_t5) + (sd[2] * _t2)) + ((sd[3]) * (_t4) - (sd[0] * _t3));
        dd[2] = ((sd[0]) * (_t4) + (sd[3] * _t3)) + ((sd[2]) * (_t5) - (sd[1] * _t2));
        return lookAlong_degenerate_s34d9c4c0_4_mulAdd(dest, sd, dd, _t2, _t3, _t4, _t5);
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
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = Math.fma(sd[0], _t2, sd[3] * _t1);
        dd[1] = Math.fma(sd[1], _t2, sd[2] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[1] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        dd[4] = Math.fma(sd[4], _t2, sd[7] * _t1);
        dd[5] = Math.fma(sd[5], _t2, sd[6] * _t1);
        dd[6] = Math.fma(sd[6], _t2, -(sd[5] * _t1));
        dd[7] = Math.fma(sd[7], _t2, -(sd[4] * _t1));
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
    public FloatDualQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXYZ_fma(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        return rotateXYZ_sca472e04_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t10) * (_t8) + (_t11 * _t5));
        float _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        return rotateXYZ_sca472e04_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXYZ_sca472e04_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateXYZ_sca472e04_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_fma(float angleX, float angleY, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t22, -(sd[0] * _t20));
        dd[2] = java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19));
        return rotateXYZ_s56376703_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t10) * (_t8) + (_t11 * _t5));
        float _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        return rotateXYZ_s56376703_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_s56376703_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -(sd[0] * _t19)) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t22, -(sd[4] * _t20));
        dd[6] = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -(sd[4] * _t19)) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_s56376703_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        dd[4] = ((sd[4]) * (_t21) + (sd[7] * _t19)) + ((sd[5]) * (_t20) - (sd[6] * _t22));
        dd[5] = ((sd[5]) * (_t21) + (sd[6] * _t19)) + ((sd[7]) * (_t22) - (sd[4] * _t20));
        dd[6] = ((sd[4]) * (_t22) + (sd[7] * _t20)) + ((sd[6]) * (_t21) - (sd[5] * _t19));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t19)) - ((sd[5]) * (_t22) + (sd[6] * _t20));
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
    public FloatDualQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXZY_fma(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        return rotateXZY_s5a2d259a_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t10) * (_t8) - (_t11 * _t5));
        float _t22 = ((_t12) * (_t5) - (_t9 * _t8));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        return rotateXZY_s5a2d259a_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXZY_s5a2d259a_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateXZY_s5a2d259a_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_fma(float angleX, float angleZ, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t22, -(sd[0] * _t20));
        dd[2] = java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21));
        return rotateXZY_s115e7ad5_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t10) * (_t8) - (_t11 * _t5));
        float _t22 = ((_t12) * (_t5) - (_t9 * _t8));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        return rotateXZY_s115e7ad5_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s115e7ad5_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -(sd[0] * _t21)) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        dd[5] = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t22, -(sd[4] * _t20));
        dd[6] = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -(sd[4] * _t21)) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s115e7ad5_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        dd[4] = ((sd[4]) * (_t19) + (sd[7] * _t21)) + ((sd[5]) * (_t20) - (sd[6] * _t22));
        dd[5] = ((sd[5]) * (_t19) + (sd[6] * _t21)) + ((sd[7]) * (_t22) - (sd[4] * _t20));
        dd[6] = ((sd[4]) * (_t22) + (sd[7] * _t20)) + ((sd[6]) * (_t19) - (sd[5] * _t21));
        dd[7] = ((sd[7]) * (_t19) - (sd[4] * _t21)) - ((sd[5]) * (_t22) + (sd[6] * _t20));
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
    public FloatDualQuat rotateY(float angle, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = Math.fma(sd[0], _t2, -(sd[2] * _t1));
        dd[1] = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[0], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        dd[4] = Math.fma(sd[4], _t2, -(sd[6] * _t1));
        dd[5] = Math.fma(sd[5], _t2, sd[7] * _t1);
        dd[6] = Math.fma(sd[4], _t1, sd[6] * _t2);
        dd[7] = Math.fma(sd[7], _t2, -(sd[5] * _t1));
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
    public FloatDualQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYXZ_fma(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t20) + java.lang.Math.fma(_rd1, _t21, -(_rd2 * _t22));
        return rotateYXZ_se653a286_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t20)) + ((_rd1) * (_t21) - (_rd2 * _t22));
        return rotateYXZ_se653a286_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateYXZ_se653a286_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateYXZ_se653a286_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_fma(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t20) + java.lang.Math.fma(sd[1], _t21, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t20) + java.lang.Math.fma(sd[3], _t22, -(sd[0] * _t21));
        dd[2] = java.lang.Math.fma(sd[0], _t22, sd[3] * _t21) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t20));
        return rotateYXZ_sb3e6431_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t20)) + ((sd[1]) * (_t21) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t20)) + ((sd[3]) * (_t22) - (sd[0] * _t21));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t21)) + ((sd[2]) * (_t19) - (sd[1] * _t20));
        return rotateYXZ_sb3e6431_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYXZ_sb3e6431_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -(sd[0] * _t20)) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t21);
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[7] * _t20) + java.lang.Math.fma(sd[5], _t21, -(sd[6] * _t22));
        dd[5] = java.lang.Math.fma(sd[5], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[7], _t22, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[4], _t22, sd[7] * _t21) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t20));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -(sd[4] * _t20)) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t21);
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYXZ_sb3e6431_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t20)) - ((sd[1]) * (_t22) + (sd[2] * _t21));
        dd[4] = ((sd[4]) * (_t19) + (sd[7] * _t20)) + ((sd[5]) * (_t21) - (sd[6] * _t22));
        dd[5] = ((sd[5]) * (_t19) + (sd[6] * _t20)) + ((sd[7]) * (_t22) - (sd[4] * _t21));
        dd[6] = ((sd[4]) * (_t22) + (sd[7] * _t21)) + ((sd[6]) * (_t19) - (sd[5] * _t20));
        dd[7] = ((sd[7]) * (_t19) - (sd[4] * _t20)) - ((sd[5]) * (_t22) + (sd[6] * _t21));
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
    public FloatDualQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYZX_fma(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        return rotateYZX_s3069681e_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        return rotateYZX_s3069681e_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateYZX_s3069681e_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateYZX_s3069681e_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_fma(float angleY, float angleZ, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t20, -(sd[0] * _t22));
        dd[2] = java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19));
        return rotateYZX_s5ddd7519_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        return rotateYZX_s5ddd7519_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s5ddd7519_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -(sd[0] * _t19)) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t20, -(sd[4] * _t22));
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -(sd[4] * _t19)) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s5ddd7519_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        dd[4] = ((sd[4]) * (_t21) + (sd[7] * _t19)) + ((sd[5]) * (_t22) - (sd[6] * _t20));
        dd[5] = ((sd[5]) * (_t21) + (sd[6] * _t19)) + ((sd[7]) * (_t20) - (sd[4] * _t22));
        dd[6] = ((sd[4]) * (_t20) + (sd[7] * _t22)) + ((sd[6]) * (_t21) - (sd[5] * _t19));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t19)) - ((sd[5]) * (_t20) + (sd[6] * _t22));
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
    public FloatDualQuat rotateZ(float angle, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = Math.fma(sd[0], _t2, sd[1] * _t1);
        dd[1] = Math.fma(sd[1], _t2, -(sd[0] * _t1));
        dd[2] = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        dd[4] = Math.fma(sd[4], _t2, sd[5] * _t1);
        dd[5] = Math.fma(sd[5], _t2, -(sd[4] * _t1));
        dd[6] = Math.fma(sd[6], _t2, sd[7] * _t1);
        dd[7] = Math.fma(sd[7], _t2, -(sd[6] * _t1));
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
    public FloatDualQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZXY_fma(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t22) + java.lang.Math.fma(_rd1, _t19, -(_rd2 * _t20));
        return rotateZXY_s2cab48e_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t10) * (_t5) + (_t11 * _t8));
        float _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t21) + (_rd3 * _t22)) + ((_rd1) * (_t19) - (_rd2 * _t20));
        return rotateZXY_s2cab48e_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZXY_s2cab48e_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateZXY_s2cab48e_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_fma(float angleZ, float angleX, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t22) + java.lang.Math.fma(sd[1], _t19, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t22) + java.lang.Math.fma(sd[3], _t20, -(sd[0] * _t19));
        dd[2] = java.lang.Math.fma(sd[0], _t20, sd[3] * _t19) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t22));
        return rotateZXY_s3268b4e9_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t10) * (_t5) + (_t11 * _t8));
        float _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        float _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t22)) + ((sd[1]) * (_t19) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t22)) + ((sd[3]) * (_t20) - (sd[0] * _t19));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t19)) + ((sd[2]) * (_t21) - (sd[1] * _t22));
        return rotateZXY_s3268b4e9_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3268b4e9_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -(sd[0] * _t22)) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t19);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t22) + java.lang.Math.fma(sd[5], _t19, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[7], _t20, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t19) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t22));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -(sd[4] * _t22)) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t19);
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3268b4e9_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t22)) - ((sd[1]) * (_t20) + (sd[2] * _t19));
        dd[4] = ((sd[4]) * (_t21) + (sd[7] * _t22)) + ((sd[5]) * (_t19) - (sd[6] * _t20));
        dd[5] = ((sd[5]) * (_t21) + (sd[6] * _t22)) + ((sd[7]) * (_t20) - (sd[4] * _t19));
        dd[6] = ((sd[4]) * (_t20) + (sd[7] * _t19)) + ((sd[6]) * (_t21) - (sd[5] * _t22));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t22)) - ((sd[5]) * (_t20) + (sd[6] * _t19));
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
    public FloatDualQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest) {
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZYX_fma(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        return rotateZYX_se9d00b00_1_fma(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        float _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        return rotateZYX_se9d00b00_1_mulAdd(dest, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZYX_se9d00b00_1_fma(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
    private FloatDualQuat rotateZYX_se9d00b00_1_mulAdd(FloatDualQuat dest, float[] dd, float _t19, float _t20, float _t21, float _t22, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7) {
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
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_fma(float angleZ, float angleY, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t20, -(sd[0] * _t22));
        dd[2] = java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21));
        return rotateZYX_s3d4ff58f_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
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
        float _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        float _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        return rotateZYX_s3d4ff58f_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s3d4ff58f_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -(sd[0] * _t21)) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t20, -(sd[4] * _t22));
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -(sd[4] * _t21)) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s3d4ff58f_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        dd[4] = ((sd[4]) * (_t19) + (sd[7] * _t21)) + ((sd[5]) * (_t22) - (sd[6] * _t20));
        dd[5] = ((sd[5]) * (_t19) + (sd[6] * _t21)) + ((sd[7]) * (_t20) - (sd[4] * _t22));
        dd[6] = ((sd[4]) * (_t20) + (sd[7] * _t22)) + ((sd[6]) * (_t19) - (sd[5] * _t21));
        dd[7] = ((sd[7]) * (_t19) - (sd[4] * _t21)) - ((sd[5]) * (_t20) + (sd[6] * _t22));
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
    public FloatDualQuat translate(Float3R translation, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translation.z();
        float _t4 = 0.5f * translation.y();
        float _t5 = 0.5f * translation.x();
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translation.z();
        float _t4 = 0.5f * translation.y();
        float _t5 = 0.5f * translation.x();
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = Math.fma(sd[1], _t3, Math.fma(_t0, _t4, Math.fma(sd[3], _t5, sd[4])));
        dd[5] = Math.fma(sd[3], _t4, Math.fma(_t1, _t3, Math.fma(sd[2], _t5, sd[5])));
        dd[6] = Math.fma(sd[0], _t4, Math.fma(sd[3], _t3, Math.fma(_t2, _t5, sd[6])));
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
    public FloatDualQuat translate(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
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
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = Math.fma(sd[1], _t3, Math.fma(_t0, _t4, Math.fma(sd[3], _t5, sd[4])));
        dd[5] = Math.fma(sd[3], _t4, Math.fma(_t1, _t3, Math.fma(sd[2], _t5, sd[5])));
        dd[6] = Math.fma(sd[0], _t4, Math.fma(sd[3], _t3, Math.fma(_t2, _t5, sd[6])));
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
    public Float3 transform(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(pY, sd[0], -(pX * sd[1]));
        float _t10 = 2.0f * Math.fma(pX, sd[2], -(pZ * sd[0]));
        float _t11 = 2.0f * Math.fma(pZ, sd[1], -(pY * sd[2]));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, Math.fma(2.0f, Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)), pX))));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, Math.fma(2.0f, Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)), pY))));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, Math.fma(2.0f, Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)), pZ))));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(pY, sd[0], -(pX * sd[1]));
        float _t10 = 2.0f * Math.fma(pX, sd[2], -(pZ * sd[0]));
        float _t11 = 2.0f * Math.fma(pZ, sd[1], -(pY * sd[2]));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, Math.fma(2.0f, Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX))));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, Math.fma(2.0f, Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY))));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, Math.fma(2.0f, Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ))));
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
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
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
    public Float3 transformDirection(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
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
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
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
    public Float3 transformInverse(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
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
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transformInverse} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float _t21 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ);
        float _t22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX);
        float _t23 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY);
        return transformInverse_s6bbb37d7_1_fma(dest, ((Float3Impl) dest).data, _t21, _t22, _t23, 2.0f * java.lang.Math.fma(sd[0], _t21, -(sd[2] * _t22)), 2.0f * java.lang.Math.fma(sd[1], _t22, -(sd[0] * _t23)), 2.0f * java.lang.Math.fma(sd[2], _t23, -(sd[1] * _t21)), sd[0], sd[1], sd[2], sd[3]);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float _t21 = ((-2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))) + (pZ));
        float _t22 = ((-2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))) + (pX));
        float _t23 = ((-2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))) + (pY));
        return transformInverse_s6bbb37d7_1_mulAdd(dest, ((Float3Impl) dest).data, _t21, _t22, _t23, 2.0f * ((sd[0]) * (_t21) - (sd[2] * _t22)), 2.0f * ((sd[1]) * (_t22) - (sd[0] * _t23)), 2.0f * ((sd[2]) * (_t23) - (sd[1] * _t21)), sd[0], sd[1], sd[2], sd[3]);
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Float3 transformInverse_s6bbb37d7_1_fma(Float3 dest, float[] dd, float _t21, float _t22, float _t23, float _t33, float _t34, float _t35, float _rd0, float _rd1, float _rd2, float _rd3) {
        dd[0] = java.lang.Math.fma(_rd2, _t33, java.lang.Math.fma(-_rd1, _t34, java.lang.Math.fma(_rd3, _t35, _t22)));
        dd[1] = java.lang.Math.fma(_rd0, _t34, java.lang.Math.fma(-_rd2, _t35, java.lang.Math.fma(_rd3, _t33, _t23)));
        dd[2] = java.lang.Math.fma(_rd1, _t35, java.lang.Math.fma(-_rd0, _t33, java.lang.Math.fma(_rd3, _t34, _t21)));
        return dest;
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Float3 transformInverse_s6bbb37d7_1_mulAdd(Float3 dest, float[] dd, float _t21, float _t22, float _t23, float _t33, float _t34, float _t35, float _rd0, float _rd1, float _rd2, float _rd3) {
        dd[0] = ((_rd2) * (_t33) + (((-_rd1) * (_t34) + (((_rd3) * (_t35) + (_t22))))));
        dd[1] = ((_rd0) * (_t34) + (((-_rd2) * (_t35) + (((_rd3) * (_t33) + (_t23))))));
        dd[2] = ((_rd1) * (_t35) + (((-_rd0) * (_t33) + (((_rd3) * (_t34) + (_t21))))));
        return dest;
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
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transformInverse} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t21 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])), pZ);
        float _t22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])), pX);
        float _t23 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])), pY);
        float _t33 = 2.0f * java.lang.Math.fma(sd[0], _t21, -(sd[2] * _t22));
        float _t34 = 2.0f * java.lang.Math.fma(sd[1], _t22, -(sd[0] * _t23));
        float _t35 = 2.0f * java.lang.Math.fma(sd[2], _t23, -(sd[1] * _t21));
        dd[0] = java.lang.Math.fma(sd[2], _t33, java.lang.Math.fma(-sd[1], _t34, java.lang.Math.fma(sd[3], _t35, _t22)));
        return transformInverse_s57511e60_1_fma(dest, sd, dd, _t21, _t23, _t33, _t34, _t35);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t21 = ((-2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))) + (pZ));
        float _t22 = ((-2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))) + (pX));
        float _t23 = ((-2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))) + (pY));
        float _t33 = 2.0f * ((sd[0]) * (_t21) - (sd[2] * _t22));
        float _t34 = 2.0f * ((sd[1]) * (_t22) - (sd[0] * _t23));
        float _t35 = 2.0f * ((sd[2]) * (_t23) - (sd[1] * _t21));
        dd[0] = ((sd[2]) * (_t33) + (((-sd[1]) * (_t34) + (((sd[3]) * (_t35) + (_t22))))));
        return transformInverse_s57511e60_1_mulAdd(dest, sd, dd, _t21, _t23, _t33, _t34, _t35);
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_s57511e60_1_fma(Double3 dest, float[] sd, double[] dd, float _t21, float _t23, float _t33, float _t34, float _t35) {
        dd[1] = java.lang.Math.fma(sd[0], _t34, java.lang.Math.fma(-sd[2], _t35, java.lang.Math.fma(sd[3], _t33, _t23)));
        dd[2] = java.lang.Math.fma(sd[1], _t35, java.lang.Math.fma(-sd[0], _t33, java.lang.Math.fma(sd[3], _t34, _t21)));
        return dest;
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_s57511e60_1_mulAdd(Double3 dest, float[] sd, double[] dd, float _t21, float _t23, float _t33, float _t34, float _t35) {
        dd[1] = ((sd[0]) * (_t34) + (((-sd[2]) * (_t35) + (((sd[3]) * (_t33) + (_t23))))));
        dd[2] = ((sd[1]) * (_t35) + (((-sd[0]) * (_t33) + (((sd[3]) * (_t34) + (_t21))))));
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
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(pY, sd[0], -(pX * sd[1]));
        float _t10 = 2.0f * Math.fma(pX, sd[2], -(pZ * sd[0]));
        float _t11 = 2.0f * Math.fma(pZ, sd[1], -(pY * sd[2]));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        float _rd4 = sd[4];
        float _rd5 = sd[5];
        float _rd6 = sd[6];
        float _rd7 = sd[7];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, Math.fma(2.0f, Math.fma(_rd1, _rd6, -(_rd2 * _rd5)) + Math.fma(_rd3, _rd4, -(_rd0 * _rd7)), pX))));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, Math.fma(2.0f, Math.fma(_rd2, _rd4, -(_rd0 * _rd6)) + Math.fma(_rd3, _rd5, -(_rd1 * _rd7)), pY))));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, Math.fma(2.0f, Math.fma(_rd0, _rd5, -(_rd1 * _rd4)) + Math.fma(_rd3, _rd6, -(_rd2 * _rd7)), pZ))));
        return dest;
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
        if (Math.useFma()) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
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
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        dd[1] = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    public float rX() { return data[0]; }
    public float rY() { return data[1]; }
    public float rZ() { return data[2]; }
    public float rW() { return data[3]; }
    public float dX() { return data[4]; }
    public float dY() { return data[5]; }
    public float dZ() { return data[6]; }
    public float dW() { return data[7]; }

    @Override public String toString() {
        return "FloatDualQuat(" + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + dX() + ", " + dY() + ", " + dZ() + ", " + dW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatDualQuatImpl)) return false;
        FloatDualQuatImpl o = (FloatDualQuatImpl) obj;
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
            && Float.isFinite(data[7]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7]);
    }

    @Override public boolean equalsEpsilon(FloatDualQuatR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.rX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.rY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.rZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.rW()) <= epsilon
            && java.lang.Math.abs(data[4] - other.dX()) <= epsilon
            && java.lang.Math.abs(data[5] - other.dY()) <= epsilon
            && java.lang.Math.abs(data[6] - other.dZ()) <= epsilon
            && java.lang.Math.abs(data[7] - other.dW()) <= epsilon;
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
        return dest;
    }
    public @Mutated FloatDualQuat load(float[] src, int offset) {
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
    public @Mutated FloatDualQuat load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
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
