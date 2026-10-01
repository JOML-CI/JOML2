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
 * Generated implementation of {@link FloatDualQuat} backed by a {@code float[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatDualQuatImpl implements FloatDualQuat {
    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatDualQuatSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatDualQuatSegOpsUnsafe()
            : new FloatDualQuatSegOpsMS();
        static final FloatDualQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatDualQuatBbOpsUnsafe()
            : new FloatDualQuatBbOpsApi();
        static final FloatDualQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        float[] srcData = ((FloatDualQuatImpl) src).data;
        float[] dd = this.data = new float[8];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
        dd[6] = srcData[6];
        dd[7] = srcData[7];
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = otherData[0] + sd[0];
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = otherData[0] + sd[0];
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = sd[0] - otherData[0];
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0] - otherData[0];
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
    @Mutated public FloatDualQuat set(FloatDualQuatR v) {
        float[] vData = ((FloatDualQuatImpl) v).data;
        float vRY = vData[1];
        float vRZ = vData[2];
        float vRW = vData[3];
        float vDX = vData[4];
        float vDY = vData[5];
        float vDZ = vData[6];
        float vDW = vData[7];
        float[] dd = this.data;
        dd[0] = vData[0];
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
    @Mutated public FloatDualQuat makeFromRigid(FloatRigidR r) {
        float[] rData = ((FloatRigidImpl) r).data;
        return makeFromRigid(rData[0], rData[1], rData[2], rData[3], rData[4], rData[5], rData[6]);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            float _t0 = -rTZ;
            dd[0] = rRX;
            dd[1] = rRY;
            dd[2] = rRZ;
            dd[3] = rRW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ));
            dd[5] = 0.5f * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -rTX * rRZ));
            dd[6] = 0.5f * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -rTY * rRX));
            dd[7] = 0.5f * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -rTX * rRX));
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
    @Mutated public FloatDualQuat makeFromTransform(FloatTransformR t) {
        float[] tData = ((FloatTransformImpl) t).data;
        return makeFromTransform(tData[0], tData[1], tData[2], tData[3], tData[4], tData[5], tData[6], tData[7], tData[8], tData[9]);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            float _t0 = -tTZ;
            dd[0] = tRX;
            dd[1] = tRY;
            dd[2] = tRZ;
            dd[3] = tRW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ));
            dd[5] = 0.5f * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -tTX * tRZ));
            dd[6] = 0.5f * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -tTY * tRX));
            dd[7] = 0.5f * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -tTX * tRX));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
            dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
            dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
            dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
            dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
            return dest;
        }
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
        if (!org.joml2.internal.FmaSwitch.ON) return toRigid_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[3];
        return dest;
    }

    /** {@code toRigid} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid toRigid_mulAdd(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toTransform_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
    }

    /** {@code toTransform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform toTransform_mulAdd(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 3);
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
        if (!org.joml2.internal.FmaSwitch.ON) return toTransform_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        dd[3] = sd[0];
        dd[4] = sd[1];
        dd[5] = sd[2];
        dd[6] = sd[3];
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
    }

    /** {@code toTransform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform toTransform_mulAdd(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
    @Mutated public FloatDualQuat makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        float[] axisData = ((Float3Impl) axis).data;
        float[] translationData = ((Float3Impl) translation).data;
        float axisX = axisData[0];
        float axisY = axisData[1];
        float axisZ = axisData[2];
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromAxisAngle_mulAdd(axisX, axisY, axisZ, angle, translationX, translationY, translationZ);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = axisX * _t2;
        float _t5 = axisY * _t2;
        float _t6 = axisZ * _t2;
        dd[0] = _t4;
        dd[1] = _t5;
        dd[2] = _t6;
        dd[3] = _t3;
        dd[4] = 0.5f * java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(translationX, _t3, translationY * _t6));
        dd[5] = 0.5f * java.lang.Math.fma(translationZ, _t4, java.lang.Math.fma(translationY, _t3, -translationX * _t6));
        dd[6] = 0.5f * java.lang.Math.fma(translationZ, _t3, java.lang.Math.fma(translationX, _t5, -translationY * _t4));
        dd[7] = 0.5f * java.lang.Math.fma(_t1, _t6, java.lang.Math.fma(-translationY, _t5, -translationX * _t4));
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
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromAxisAngle_mulAdd(axisX, axisY, axisZ, angle, translationX, translationY, translationZ);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = axisX * _t2;
        float _t5 = axisY * _t2;
        float _t6 = axisZ * _t2;
        dd[0] = _t4;
        dd[1] = _t5;
        dd[2] = _t6;
        dd[3] = _t3;
        dd[4] = 0.5f * java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(translationX, _t3, translationY * _t6));
        dd[5] = 0.5f * java.lang.Math.fma(translationZ, _t4, java.lang.Math.fma(translationY, _t3, -translationX * _t6));
        dd[6] = 0.5f * java.lang.Math.fma(translationZ, _t3, java.lang.Math.fma(translationX, _t5, -translationY * _t4));
        dd[7] = 0.5f * java.lang.Math.fma(_t1, _t6, java.lang.Math.fma(-translationY, _t5, -translationX * _t4));
        return this;
    }

    /** {@code makeFromAxisAngle} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeFromAxisAngle_mulAdd(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = axisX * _t2;
        float _t5 = axisY * _t2;
        float _t6 = axisZ * _t2;
        dd[0] = _t4;
        dd[1] = _t5;
        dd[2] = _t6;
        dd[3] = _t3;
        dd[4] = 0.5f * ((_t1) * (_t5) + (((translationX) * (_t3) + (translationY * _t6))));
        dd[5] = 0.5f * ((translationZ) * (_t4) + (((translationY) * (_t3) - (translationX * _t6))));
        dd[6] = 0.5f * ((translationZ) * (_t3) + (((translationX) * (_t5) - (translationY * _t4))));
        dd[7] = 0.5f * ((_t1) * (_t6) + (((-translationY) * (_t5) - (translationX * _t4))));
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
        float[] _DATA_0 = DATA_0;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_0, 0).intoArray(dd, 0);
        } else {
            FloatVector.fromArray(COL_SPECIES, _DATA_0, 0).intoArray(dd, 0);
            FloatVector.fromArray(COL_SPECIES, _DATA_0, 4).intoArray(dd, 4);
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
    @Mutated public FloatDualQuat makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
        float[] translationData = ((Float3Impl) translation).data;
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        return makeTranslationRotation(translationData[0], translationData[1], translationData[2], rotationData[0], rotationData[1], rotationData[2], rotationData[3]);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX));
            dd[6] = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY));
            dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX));
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
            dd[7] = 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ));
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
        float[] _DATA_1 = DATA_1;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_1, 0).intoArray(dd, 0);
        } else {
            FloatVector.fromArray(COL_SPECIES, _DATA_1, 0).intoArray(dd, 0);
            FloatVector.fromArray(COL_SPECIES, _DATA_1, 4).intoArray(dd, 4);
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
    @Mutated public FloatDualQuat set(FloatQuatR rotation) {
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        float[] dd = this.data;
        dd[0] = rotationData[0];
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
    @Mutated public FloatDualQuat set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float[] dd = this.data;
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
    @Mutated public FloatDualQuat set(FloatQuatR rotation, Float3R translation) {
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float[] translationData = ((Float3Impl) translation).data;
        return set(rotationData[0], rotationData[1], rotationData[2], rotationData[3], translationData[0], translationData[1], translationData[2]);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            float _t0 = -rotationY;
            dd[0] = rotationX;
            dd[1] = rotationY;
            dd[2] = rotationZ;
            dd[3] = rotationW;
            dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX));
            dd[5] = 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX));
            dd[6] = 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY));
            dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX));
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
            dd[7] = 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ));
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
    @Mutated public FloatDualQuat set(Float3R translation) {
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        float[] dd = this.data;
        VEC_3.intoArray(dd, 0);
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
        VEC_3.intoArray(dd, 0);
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (org.joml2.internal.FmaSwitch.ON) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (org.joml2.internal.FmaSwitch.ON) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code dlb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat dlb_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t11 = t * (java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f);
        float _t12 = java.lang.Math.fma(otherRX, _t11, sd[0] * _t0);
        float _t13 = java.lang.Math.fma(otherRY, _t11, sd[1] * _t0);
        float _t14 = java.lang.Math.fma(otherRZ, _t11, sd[2] * _t0);
        float _t15 = java.lang.Math.fma(otherRW, _t11, sd[3] * _t0);
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, _t13 * _t13) + java.lang.Math.fma(_t14, _t14, _t15 * _t15)));
        dd[0] = _t12 * _t21;
        dd[1] = _t13 * _t21;
        dd[2] = _t14 * _t21;
        dd[3] = _t15 * _t21;
        dd[4] = java.lang.Math.fma(otherDX, _t11, sd[4] * _t0) * _t21;
        dd[5] = java.lang.Math.fma(otherDY, _t11, sd[5] * _t0) * _t21;
        dd[6] = java.lang.Math.fma(otherDZ, _t11, sd[6] * _t0) * _t21;
        dd[7] = java.lang.Math.fma(otherDW, _t11, sd[7] * _t0) * _t21;
        return dest;
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat dlb_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t11 = t * (((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f);
        float _t12 = ((otherRX) * (_t11) + (sd[0] * _t0));
        float _t13 = ((otherRY) * (_t11) + (sd[1] * _t0));
        float _t14 = ((otherRZ) * (_t11) + (sd[2] * _t0));
        float _t15 = ((otherRW) * (_t11) + (sd[3] * _t0));
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(((_t12) * (_t12) + (_t13 * _t13)) + ((_t14) * (_t14) + (_t15 * _t15))));
        dd[0] = _t12 * _t21;
        dd[1] = _t13 * _t21;
        dd[2] = _t14 * _t21;
        dd[3] = _t15 * _t21;
        dd[4] = ((otherDX) * (_t11) + (sd[4] * _t0)) * _t21;
        dd[5] = ((otherDY) * (_t11) + (sd[5] * _t0)) * _t21;
        dd[6] = ((otherDZ) * (_t11) + (sd[6] * _t0)) * _t21;
        dd[7] = ((otherDW) * (_t11) + (sd[7] * _t0)) * _t21;
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
        if (org.joml2.internal.FmaSwitch.ON) return dlb_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code dlb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t11 = t * (java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f);
        float _t12 = java.lang.Math.fma(otherRX, _t11, sd[0] * _t0);
        float _t13 = java.lang.Math.fma(otherRY, _t11, sd[1] * _t0);
        float _t14 = java.lang.Math.fma(otherRZ, _t11, sd[2] * _t0);
        float _t15 = java.lang.Math.fma(otherRW, _t11, sd[3] * _t0);
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, _t13 * _t13) + java.lang.Math.fma(_t14, _t14, _t15 * _t15)));
        dd[0] = _t12 * _t21;
        dd[1] = _t13 * _t21;
        dd[2] = _t14 * _t21;
        dd[3] = _t15 * _t21;
        dd[4] = java.lang.Math.fma(otherDX, _t11, sd[4] * _t0) * _t21;
        dd[5] = java.lang.Math.fma(otherDY, _t11, sd[5] * _t0) * _t21;
        dd[6] = java.lang.Math.fma(otherDZ, _t11, sd[6] * _t0) * _t21;
        dd[7] = java.lang.Math.fma(otherDW, _t11, sd[7] * _t0) * _t21;
        return dest;
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat dlb_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t11 = t * (((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f);
        float _t12 = ((otherRX) * (_t11) + (sd[0] * _t0));
        float _t13 = ((otherRY) * (_t11) + (sd[1] * _t0));
        float _t14 = ((otherRZ) * (_t11) + (sd[2] * _t0));
        float _t15 = ((otherRW) * (_t11) + (sd[3] * _t0));
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(((_t12) * (_t12) + (_t13 * _t13)) + ((_t14) * (_t14) + (_t15 * _t15))));
        dd[0] = _t12 * _t21;
        dd[1] = _t13 * _t21;
        dd[2] = _t14 * _t21;
        dd[3] = _t15 * _t21;
        dd[4] = ((otherDX) * (_t11) + (sd[4] * _t0)) * _t21;
        dd[5] = ((otherDY) * (_t11) + (sd[5] * _t0)) * _t21;
        dd[6] = ((otherDZ) * (_t11) + (sd[6] * _t0)) * _t21;
        dd[7] = ((otherDW) * (_t11) + (sd[7] * _t0)) * _t21;
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(t, otherRX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = java.lang.Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = java.lang.Math.fma(t, otherDW - sd[7], sd[7]);
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(t, otherRX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = java.lang.Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = java.lang.Math.fma(t, otherDW - sd[7], sd[7]);
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
     * than the other one (1e8 towards 1 ends at 0).
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(t, otherRX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = java.lang.Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = java.lang.Math.fma(t, otherDW - sd[7], sd[7]);
        return dest;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat lerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = ((t) * (otherRX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherRY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherRZ - sd[2]) + (sd[2]));
        dd[3] = ((t) * (otherRW - sd[3]) + (sd[3]));
        dd[4] = ((t) * (otherDX - sd[4]) + (sd[4]));
        dd[5] = ((t) * (otherDY - sd[5]) + (sd[5]));
        dd[6] = ((t) * (otherDZ - sd[6]) + (sd[6]));
        dd[7] = ((t) * (otherDW - sd[7]) + (sd[7]));
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
     * than the other one (1e8 towards 1 ends at 0).
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(t, otherRX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherRY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherRZ - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, otherRW - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, otherDX - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, otherDY - sd[5], sd[5]);
        dd[6] = java.lang.Math.fma(t, otherDZ - sd[6], sd[6]);
        dd[7] = java.lang.Math.fma(t, otherDW - sd[7], sd[7]);
        return dest;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat lerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = ((t) * (otherRX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherRY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherRZ - sd[2]) + (sd[2]));
        dd[3] = ((t) * (otherRW - sd[3]) + (sd[3]));
        dd[4] = ((t) * (otherDX - sd[4]) + (sd[4]));
        dd[5] = ((t) * (otherDY - sd[5]) + (sd[5]));
        dd[6] = ((t) * (otherDZ - sd[6]) + (sd[6]));
        dd[7] = ((t) * (otherDW - sd[7]) + (sd[7]));
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
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat sclerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest) {
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (org.joml2.internal.FmaSwitch.ON) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat sclerp(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest) {
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (org.joml2.internal.FmaSwitch.ON) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
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
     * must be a unit dual quaternion; this dual quaternion must be a unit dual quaternion;
     * {@code t} must lie in {@code [0, 1]}.
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
        if (org.joml2.internal.FmaSwitch.ON) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code sclerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t11 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t12 = otherRX * _t11;
        float _t13 = otherRW * _t11;
        float _t14 = otherRZ * _t11;
        float _t15 = otherRY * _t11;
        float _t16 = otherDX * _t11;
        float _t17 = otherDW * _t11;
        float _t18 = otherDY * _t11;
        float _t19 = otherDZ * _t11;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = sd[0];
            _t61 = sd[3];
            _t62 = sd[1];
            _t63 = sd[2];
            _t64 = sd[4];
            _t65 = sd[7];
            _t66 = sd[5];
            _t67 = sd[6];
        } else {
            _t60 = _t12;
            _t61 = _t13;
            _t62 = _t15;
            _t63 = _t14;
            _t64 = _t16;
            _t65 = _t17;
            _t66 = _t18;
            _t67 = _t19;
        }
        return sclerp_sd6508ecf_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, -sd[2], t < 0.5f ? 1.0f : -1.0f, Math.min(t, 1.0f - t), _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, java.lang.Math.fma(sd[0], _t12, sd[3] * _t13));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t11 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t12 = otherRX * _t11;
        float _t13 = otherRW * _t11;
        float _t14 = otherRZ * _t11;
        float _t15 = otherRY * _t11;
        float _t16 = otherDX * _t11;
        float _t17 = otherDW * _t11;
        float _t18 = otherDY * _t11;
        float _t19 = otherDZ * _t11;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = sd[0];
            _t61 = sd[3];
            _t62 = sd[1];
            _t63 = sd[2];
            _t64 = sd[4];
            _t65 = sd[7];
            _t66 = sd[5];
            _t67 = sd[6];
        } else {
            _t60 = _t12;
            _t61 = _t13;
            _t62 = _t15;
            _t63 = _t14;
            _t64 = _t16;
            _t65 = _t17;
            _t66 = _t18;
            _t67 = _t19;
        }
        return sclerp_sd6508ecf_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, -sd[2], t < 0.5f ? 1.0f : -1.0f, Math.min(t, 1.0f - t), _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, ((sd[0]) * (_t12) + (sd[3] * _t13)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68) {
        float _t88 = java.lang.Math.fma(_t0, _t14, -sd[1] * _t15);
        return sclerp_sd6508ecf_2_fma(dest, sd, dd, _t0, _t5, _t6, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t68, _t88, _t68 - _t88, (java.lang.Math.fma(sd[1], _t12, -sd[2] * _t13) + java.lang.Math.fma(sd[3], _t14, -(sd[0] * _t15))) * _t5, (java.lang.Math.fma(sd[0], _t14, sd[3] * _t15) + java.lang.Math.fma(_t0, _t12, -sd[1] * _t13)) * _t5, (java.lang.Math.fma(sd[2], _t15, -sd[1] * _t14) + java.lang.Math.fma(sd[3], _t12, -(sd[0] * _t13))) * _t5, java.lang.Math.fma(sd[0], _t16, sd[3] * _t17) + java.lang.Math.fma(sd[1], _t18, sd[2] * _t19) + (java.lang.Math.fma(sd[4], _t12, sd[7] * _t13) + java.lang.Math.fma(sd[5], _t15, sd[6] * _t14)), (java.lang.Math.fma(sd[2], _t18, -(sd[1] * _t19)) + java.lang.Math.fma(sd[3], _t16, -sd[0] * _t17) + (java.lang.Math.fma(sd[6], _t15, -sd[5] * _t14) + java.lang.Math.fma(sd[7], _t12, -sd[4] * _t13))) * _t5);
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68) {
        float _t88 = ((_t0) * (_t14) - (sd[1] * _t15));
        return sclerp_sd6508ecf_2_mulAdd(dest, sd, dd, _t0, _t5, _t6, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t68, _t88, _t68 - _t88, (((sd[1]) * (_t12) - (sd[2] * _t13)) + ((sd[3]) * (_t14) - (sd[0] * _t15))) * _t5, (((sd[0]) * (_t14) + (sd[3] * _t15)) + ((_t0) * (_t12) - (sd[1] * _t13))) * _t5, (((sd[2]) * (_t15) - (sd[1] * _t14)) + ((sd[3]) * (_t12) - (sd[0] * _t13))) * _t5, ((sd[0]) * (_t16) + (sd[3] * _t17)) + ((sd[1]) * (_t18) + (sd[2] * _t19)) + (((sd[4]) * (_t12) + (sd[7] * _t13)) + ((sd[5]) * (_t15) + (sd[6] * _t14))), (((sd[2]) * (_t18) - (sd[1] * _t19)) + ((sd[3]) * (_t16) - (sd[0] * _t17)) + (((sd[6]) * (_t15) - (sd[5] * _t14)) + ((sd[7]) * (_t12) - (sd[4] * _t13)))) * _t5);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116) {
        float _t117 = (java.lang.Math.fma(sd[1], _t16, -(sd[2] * _t17)) + java.lang.Math.fma(sd[3], _t19, -sd[0] * _t18) + (java.lang.Math.fma(sd[5], _t12, -sd[6] * _t13) + java.lang.Math.fma(sd[7], _t14, -sd[4] * _t15))) * _t5;
        float _t118 = (java.lang.Math.fma(sd[0], _t19, sd[3] * _t18) + java.lang.Math.fma(_t0, _t16, -(sd[1] * _t17)) + (java.lang.Math.fma(sd[4], _t14, sd[7] * _t15) + java.lang.Math.fma(-sd[6], _t12, -sd[5] * _t13))) * _t5;
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float _t139 = java.lang.Math.fma(_t123, _t123, java.lang.Math.fma(_t124, _t124, _t125 * _t125));
        return sclerp_sd6508ecf_3_fma(dest, dd, _t6, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133, _t139, (1.0f / (float) java.lang.Math.sqrt(_t139)), Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_2_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116) {
        float _t117 = (((sd[1]) * (_t16) - (sd[2] * _t17)) + ((sd[3]) * (_t19) - (sd[0] * _t18)) + (((sd[5]) * (_t12) - (sd[6] * _t13)) + ((sd[7]) * (_t14) - (sd[4] * _t15)))) * _t5;
        float _t118 = (((sd[0]) * (_t19) + (sd[3] * _t18)) + ((_t0) * (_t16) - (sd[1] * _t17)) + (((sd[4]) * (_t14) + (sd[7] * _t15)) + ((-sd[6]) * (_t12) - (sd[5] * _t13)))) * _t5;
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float _t139 = ((_t123) * (_t123) + (((_t124) * (_t124) + (_t125 * _t125))));
        return sclerp_sd6508ecf_3_mulAdd(dest, dd, _t6, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133, _t139, (1.0f / (float) java.lang.Math.sqrt(_t139)), Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_fma(FloatDualQuat dest, float[] dd, float _t6, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t122, float _t123, float _t124, float _t125, float _t129, float _t131, float _t132, float _t133, float _t139, float _t141, float _t143) {
        float _t144 = Math.cosWithSin(_t143);
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t151 = _t149 * _t122;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = _t144;
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        float _t157 = _t150 * _t144;
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t146, _t131) * _t141, _t145, -(_t157 * _t146));
            _t178 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t147, _t132) * _t141, _t145, -(_t157 * _t147));
            _t179 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t148, _t133) * _t141, _t145, -(_t157 * _t148));
        }
        dd[0] = java.lang.Math.fma(_t60, _t152, _t61 * _t161) + java.lang.Math.fma(_t62, _t162, -_t63 * _t163);
        return sclerp_sd6508ecf_4_fma(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_3_mulAdd(FloatDualQuat dest, float[] dd, float _t6, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t122, float _t123, float _t124, float _t125, float _t129, float _t131, float _t132, float _t133, float _t139, float _t141, float _t143) {
        float _t144 = Math.cosWithSin(_t143);
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t151 = _t149 * _t122;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = _t144;
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        float _t157 = _t150 * _t144;
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = ((((_t151) * (_t146) + (_t131)) * _t141) * (_t145) - (_t157 * _t146));
            _t178 = ((((_t151) * (_t147) + (_t132)) * _t141) * (_t145) - (_t157 * _t147));
            _t179 = ((((_t151) * (_t148) + (_t133)) * _t141) * (_t145) - (_t157 * _t148));
        }
        dd[0] = ((_t60) * (_t152) + (_t61 * _t161)) + ((_t62) * (_t162) - (_t63 * _t163));
        return sclerp_sd6508ecf_4_mulAdd(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_fma(FloatDualQuat dest, float[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[1] = java.lang.Math.fma(_t62, _t152, _t63 * _t161) + java.lang.Math.fma(_t61, _t163, -_t60 * _t162);
        dd[2] = java.lang.Math.fma(_t60, _t163, _t61 * _t162) + java.lang.Math.fma(_t63, _t152, -_t62 * _t161);
        dd[3] = java.lang.Math.fma(_t61, _t152, -_t60 * _t161) - java.lang.Math.fma(_t62, _t163, _t63 * _t162);
        dd[4] = java.lang.Math.fma(_t60, _t170, _t61 * _t177) + java.lang.Math.fma(_t62, _t178, -_t63 * _t179) + (java.lang.Math.fma(_t64, _t152, _t65 * _t161) + java.lang.Math.fma(_t66, _t162, -_t67 * _t163));
        dd[5] = java.lang.Math.fma(_t62, _t170, _t63 * _t177) + java.lang.Math.fma(_t61, _t179, -_t60 * _t178) + (java.lang.Math.fma(_t66, _t152, _t67 * _t161) + java.lang.Math.fma(_t65, _t163, -_t64 * _t162));
        dd[6] = java.lang.Math.fma(_t60, _t179, _t61 * _t178) + java.lang.Math.fma(_t63, _t170, -_t62 * _t177) + (java.lang.Math.fma(_t64, _t163, _t65 * _t162) + java.lang.Math.fma(_t67, _t152, -_t66 * _t161));
        return sclerp_sd6508ecf_5_fma(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_4_mulAdd(FloatDualQuat dest, float[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[1] = ((_t62) * (_t152) + (_t63 * _t161)) + ((_t61) * (_t163) - (_t60 * _t162));
        dd[2] = ((_t60) * (_t163) + (_t61 * _t162)) + ((_t63) * (_t152) - (_t62 * _t161));
        dd[3] = ((_t61) * (_t152) - (_t60 * _t161)) - ((_t62) * (_t163) + (_t63 * _t162));
        dd[4] = ((_t60) * (_t170) + (_t61 * _t177)) + ((_t62) * (_t178) - (_t63 * _t179)) + (((_t64) * (_t152) + (_t65 * _t161)) + ((_t66) * (_t162) - (_t67 * _t163)));
        dd[5] = ((_t62) * (_t170) + (_t63 * _t177)) + ((_t61) * (_t179) - (_t60 * _t178)) + (((_t66) * (_t152) + (_t67 * _t161)) + ((_t65) * (_t163) - (_t64 * _t162)));
        dd[6] = ((_t60) * (_t179) + (_t61 * _t178)) + ((_t63) * (_t170) - (_t62 * _t177)) + (((_t64) * (_t163) + (_t65 * _t162)) + ((_t67) * (_t152) - (_t66 * _t161)));
        dd[7] = ((_t61) * (_t170) - (_t60 * _t177)) + ((-_t63) * (_t178) - (_t62 * _t179)) + (((_t65) * (_t152) - (_t64 * _t161)) + ((-_t67) * (_t162) - (_t66 * _t163)));
        return dest;
    }

    /** Piece 6 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_sd6508ecf_5_fma(FloatDualQuat dest, float[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[7] = java.lang.Math.fma(_t61, _t170, -_t60 * _t177) + java.lang.Math.fma(-_t63, _t178, -_t62 * _t179) + (java.lang.Math.fma(_t65, _t152, -_t64 * _t161) + java.lang.Math.fma(-_t67, _t162, -_t66 * _t163));
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
     * must be a unit dual quaternion; this dual quaternion must be a unit dual quaternion;
     * {@code t} must lie in {@code [0, 1]}.
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
        if (org.joml2.internal.FmaSwitch.ON) return sclerp_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
        return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t, dest);
    }

    /** {@code sclerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        float _t11 = java.lang.Math.fma(otherRX, sd[0], otherRY * sd[1]) + java.lang.Math.fma(otherRZ, sd[2], otherRW * sd[3]) < 0.0f ? -1.0f : 1.0f;
        float _t12 = otherRX * _t11;
        float _t13 = otherRW * _t11;
        float _t14 = otherRZ * _t11;
        float _t15 = otherRY * _t11;
        float _t16 = otherDX * _t11;
        float _t17 = otherDW * _t11;
        float _t18 = otherDY * _t11;
        float _t19 = otherDZ * _t11;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = sd[0];
            _t61 = sd[3];
            _t62 = sd[1];
            _t63 = sd[2];
            _t64 = sd[4];
            _t65 = sd[7];
            _t66 = sd[5];
            _t67 = sd[6];
        } else {
            _t60 = _t12;
            _t61 = _t13;
            _t62 = _t15;
            _t63 = _t14;
            _t64 = _t16;
            _t65 = _t17;
            _t66 = _t18;
            _t67 = _t19;
        }
        return sclerp_se724e73a_1_fma(dest, sd, ((DoubleDualQuatImpl) dest).data, -sd[2], t < 0.5f ? 1.0f : -1.0f, Math.min(t, 1.0f - t), _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, java.lang.Math.fma(sd[0], _t12, sd[3] * _t13));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        float _t11 = ((otherRX) * (sd[0]) + (otherRY * sd[1])) + ((otherRZ) * (sd[2]) + (otherRW * sd[3])) < 0.0f ? -1.0f : 1.0f;
        float _t12 = otherRX * _t11;
        float _t13 = otherRW * _t11;
        float _t14 = otherRZ * _t11;
        float _t15 = otherRY * _t11;
        float _t16 = otherDX * _t11;
        float _t17 = otherDW * _t11;
        float _t18 = otherDY * _t11;
        float _t19 = otherDZ * _t11;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = sd[0];
            _t61 = sd[3];
            _t62 = sd[1];
            _t63 = sd[2];
            _t64 = sd[4];
            _t65 = sd[7];
            _t66 = sd[5];
            _t67 = sd[6];
        } else {
            _t60 = _t12;
            _t61 = _t13;
            _t62 = _t15;
            _t63 = _t14;
            _t64 = _t16;
            _t65 = _t17;
            _t66 = _t18;
            _t67 = _t19;
        }
        return sclerp_se724e73a_1_mulAdd(dest, sd, ((DoubleDualQuatImpl) dest).data, -sd[2], t < 0.5f ? 1.0f : -1.0f, Math.min(t, 1.0f - t), _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, ((sd[0]) * (_t12) + (sd[3] * _t13)));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68) {
        float _t88 = java.lang.Math.fma(_t0, _t14, -sd[1] * _t15);
        return sclerp_se724e73a_2_fma(dest, sd, dd, _t0, _t5, _t6, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t68, _t88, _t68 - _t88, (java.lang.Math.fma(sd[1], _t12, -sd[2] * _t13) + java.lang.Math.fma(sd[3], _t14, -(sd[0] * _t15))) * _t5, (java.lang.Math.fma(sd[0], _t14, sd[3] * _t15) + java.lang.Math.fma(_t0, _t12, -sd[1] * _t13)) * _t5, (java.lang.Math.fma(sd[2], _t15, -sd[1] * _t14) + java.lang.Math.fma(sd[3], _t12, -(sd[0] * _t13))) * _t5, java.lang.Math.fma(sd[0], _t16, sd[3] * _t17) + java.lang.Math.fma(sd[1], _t18, sd[2] * _t19) + (java.lang.Math.fma(sd[4], _t12, sd[7] * _t13) + java.lang.Math.fma(sd[5], _t15, sd[6] * _t14)), (java.lang.Math.fma(sd[2], _t18, -(sd[1] * _t19)) + java.lang.Math.fma(sd[3], _t16, -sd[0] * _t17) + (java.lang.Math.fma(sd[6], _t15, -sd[5] * _t14) + java.lang.Math.fma(sd[7], _t12, -sd[4] * _t13))) * _t5);
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68) {
        float _t88 = ((_t0) * (_t14) - (sd[1] * _t15));
        return sclerp_se724e73a_2_mulAdd(dest, sd, dd, _t0, _t5, _t6, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t68, _t88, _t68 - _t88, (((sd[1]) * (_t12) - (sd[2] * _t13)) + ((sd[3]) * (_t14) - (sd[0] * _t15))) * _t5, (((sd[0]) * (_t14) + (sd[3] * _t15)) + ((_t0) * (_t12) - (sd[1] * _t13))) * _t5, (((sd[2]) * (_t15) - (sd[1] * _t14)) + ((sd[3]) * (_t12) - (sd[0] * _t13))) * _t5, ((sd[0]) * (_t16) + (sd[3] * _t17)) + ((sd[1]) * (_t18) + (sd[2] * _t19)) + (((sd[4]) * (_t12) + (sd[7] * _t13)) + ((sd[5]) * (_t15) + (sd[6] * _t14))), (((sd[2]) * (_t18) - (sd[1] * _t19)) + ((sd[3]) * (_t16) - (sd[0] * _t17)) + (((sd[6]) * (_t15) - (sd[5] * _t14)) + ((sd[7]) * (_t12) - (sd[4] * _t13)))) * _t5);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116) {
        float _t117 = (java.lang.Math.fma(sd[1], _t16, -(sd[2] * _t17)) + java.lang.Math.fma(sd[3], _t19, -sd[0] * _t18) + (java.lang.Math.fma(sd[5], _t12, -sd[6] * _t13) + java.lang.Math.fma(sd[7], _t14, -sd[4] * _t15))) * _t5;
        float _t118 = (java.lang.Math.fma(sd[0], _t19, sd[3] * _t18) + java.lang.Math.fma(_t0, _t16, -(sd[1] * _t17)) + (java.lang.Math.fma(sd[4], _t14, sd[7] * _t15) + java.lang.Math.fma(-sd[6], _t12, -sd[5] * _t13))) * _t5;
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float _t139 = java.lang.Math.fma(_t123, _t123, java.lang.Math.fma(_t124, _t124, _t125 * _t125));
        return sclerp_se724e73a_3_fma(dest, dd, _t6, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133, _t139, (1.0f / (float) java.lang.Math.sqrt(_t139)), Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_2_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t5, float _t6, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116) {
        float _t117 = (((sd[1]) * (_t16) - (sd[2] * _t17)) + ((sd[3]) * (_t19) - (sd[0] * _t18)) + (((sd[5]) * (_t12) - (sd[6] * _t13)) + ((sd[7]) * (_t14) - (sd[4] * _t15)))) * _t5;
        float _t118 = (((sd[0]) * (_t19) + (sd[3] * _t18)) + ((_t0) * (_t16) - (sd[1] * _t17)) + (((sd[4]) * (_t14) + (sd[7] * _t15)) + ((-sd[6]) * (_t12) - (sd[5] * _t13)))) * _t5;
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float _t139 = ((_t123) * (_t123) + (((_t124) * (_t124) + (_t125 * _t125))));
        return sclerp_se724e73a_3_mulAdd(dest, dd, _t6, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133, _t139, (1.0f / (float) java.lang.Math.sqrt(_t139)), Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_fma(DoubleDualQuat dest, double[] dd, float _t6, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t122, float _t123, float _t124, float _t125, float _t129, float _t131, float _t132, float _t133, float _t139, float _t141, float _t143) {
        float _t144 = Math.cosWithSin(_t143);
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t151 = _t149 * _t122;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = _t144;
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        float _t157 = _t150 * _t144;
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t146, _t131) * _t141, _t145, -(_t157 * _t146));
            _t178 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t147, _t132) * _t141, _t145, -(_t157 * _t147));
            _t179 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t148, _t133) * _t141, _t145, -(_t157 * _t148));
        }
        dd[0] = java.lang.Math.fma(_t60, _t152, _t61 * _t161) + java.lang.Math.fma(_t62, _t162, -_t63 * _t163);
        return sclerp_se724e73a_4_fma(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_3_mulAdd(DoubleDualQuat dest, double[] dd, float _t6, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t122, float _t123, float _t124, float _t125, float _t129, float _t131, float _t132, float _t133, float _t139, float _t141, float _t143) {
        float _t144 = Math.cosWithSin(_t143);
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t151 = _t149 * _t122;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = _t144;
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        float _t157 = _t150 * _t144;
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = ((((_t151) * (_t146) + (_t131)) * _t141) * (_t145) - (_t157 * _t146));
            _t178 = ((((_t151) * (_t147) + (_t132)) * _t141) * (_t145) - (_t157 * _t147));
            _t179 = ((((_t151) * (_t148) + (_t133)) * _t141) * (_t145) - (_t157 * _t148));
        }
        dd[0] = ((_t60) * (_t152) + (_t61 * _t161)) + ((_t62) * (_t162) - (_t63 * _t163));
        return sclerp_se724e73a_4_mulAdd(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_fma(DoubleDualQuat dest, double[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[1] = java.lang.Math.fma(_t62, _t152, _t63 * _t161) + java.lang.Math.fma(_t61, _t163, -_t60 * _t162);
        dd[2] = java.lang.Math.fma(_t60, _t163, _t61 * _t162) + java.lang.Math.fma(_t63, _t152, -_t62 * _t161);
        dd[3] = java.lang.Math.fma(_t61, _t152, -_t60 * _t161) - java.lang.Math.fma(_t62, _t163, _t63 * _t162);
        dd[4] = java.lang.Math.fma(_t60, _t170, _t61 * _t177) + java.lang.Math.fma(_t62, _t178, -_t63 * _t179) + (java.lang.Math.fma(_t64, _t152, _t65 * _t161) + java.lang.Math.fma(_t66, _t162, -_t67 * _t163));
        dd[5] = java.lang.Math.fma(_t62, _t170, _t63 * _t177) + java.lang.Math.fma(_t61, _t179, -_t60 * _t178) + (java.lang.Math.fma(_t66, _t152, _t67 * _t161) + java.lang.Math.fma(_t65, _t163, -_t64 * _t162));
        dd[6] = java.lang.Math.fma(_t60, _t179, _t61 * _t178) + java.lang.Math.fma(_t63, _t170, -_t62 * _t177) + (java.lang.Math.fma(_t64, _t163, _t65 * _t162) + java.lang.Math.fma(_t67, _t152, -_t66 * _t161));
        return sclerp_se724e73a_5_fma(dest, dd, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t152, _t161, _t162, _t163, _t170, _t177, _t178, _t179);
    }

    /** Piece 5 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_4_mulAdd(DoubleDualQuat dest, double[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[1] = ((_t62) * (_t152) + (_t63 * _t161)) + ((_t61) * (_t163) - (_t60 * _t162));
        dd[2] = ((_t60) * (_t163) + (_t61 * _t162)) + ((_t63) * (_t152) - (_t62 * _t161));
        dd[3] = ((_t61) * (_t152) - (_t60 * _t161)) - ((_t62) * (_t163) + (_t63 * _t162));
        dd[4] = ((_t60) * (_t170) + (_t61 * _t177)) + ((_t62) * (_t178) - (_t63 * _t179)) + (((_t64) * (_t152) + (_t65 * _t161)) + ((_t66) * (_t162) - (_t67 * _t163)));
        dd[5] = ((_t62) * (_t170) + (_t63 * _t177)) + ((_t61) * (_t179) - (_t60 * _t178)) + (((_t66) * (_t152) + (_t67 * _t161)) + ((_t65) * (_t163) - (_t64 * _t162)));
        dd[6] = ((_t60) * (_t179) + (_t61 * _t178)) + ((_t63) * (_t170) - (_t62 * _t177)) + (((_t64) * (_t163) + (_t65 * _t162)) + ((_t67) * (_t152) - (_t66 * _t161)));
        dd[7] = ((_t61) * (_t170) - (_t60 * _t177)) + ((-_t63) * (_t178) - (_t62 * _t179)) + (((_t65) * (_t152) - (_t64 * _t161)) + ((-_t67) * (_t162) - (_t66 * _t163)));
        return dest;
    }

    /** Piece 6 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_se724e73a_5_fma(DoubleDualQuat dest, double[] dd, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t162, float _t163, float _t170, float _t177, float _t178, float _t179) {
        dd[7] = java.lang.Math.fma(_t61, _t170, -_t60 * _t177) + java.lang.Math.fma(-_t63, _t178, -_t62 * _t179) + (java.lang.Math.fma(_t65, _t152, -_t64 * _t161) + java.lang.Math.fma(-_t67, _t162, -_t66 * _t163));
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return mul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return mul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        return mul_s9f969cca_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, java.lang.Math.fma(otherRX, sd[3], otherRW * sd[0]) + java.lang.Math.fma(otherRZ, sd[1], -otherRY * sd[2]), java.lang.Math.fma(otherRX, sd[2], otherRW * sd[1]) + java.lang.Math.fma(otherRY, sd[3], otherRZ * -sd[0]), java.lang.Math.fma(otherRY, sd[0], otherRZ * sd[3]) + java.lang.Math.fma(otherRW, sd[2], -otherRX * sd[1]), java.lang.Math.fma(otherRX, sd[7], otherRW * sd[4]) + java.lang.Math.fma(otherRZ, sd[5], -otherRY * sd[6]) + (java.lang.Math.fma(otherDX, sd[3], otherDW * sd[0]) + java.lang.Math.fma(otherDZ, sd[1], otherDY * -sd[2])), java.lang.Math.fma(otherRX, sd[6], otherRW * sd[5]) + java.lang.Math.fma(otherRY, sd[7], -otherRZ * sd[4]) + (java.lang.Math.fma(otherDX, sd[2], otherDW * sd[1]) + java.lang.Math.fma(otherDY, sd[3], otherDZ * -sd[0])));
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat mul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        return mul_s9f969cca_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, ((otherRX) * (sd[3]) + (otherRW * sd[0])) + ((otherRZ) * (sd[1]) - (otherRY * sd[2])), ((otherRX) * (sd[2]) + (otherRW * sd[1])) + ((otherRY) * (sd[3]) - (otherRZ * sd[0])), ((otherRY) * (sd[0]) + (otherRZ * sd[3])) + ((otherRW) * (sd[2]) - (otherRX * sd[1])), ((otherRX) * (sd[7]) + (otherRW * sd[4])) + ((otherRZ) * (sd[5]) - (otherRY * sd[6])) + (((otherDX) * (sd[3]) + (otherDW * sd[0])) + ((otherDZ) * (sd[1]) - (otherDY * sd[2]))), ((otherRX) * (sd[6]) + (otherRW * sd[5])) + ((otherRY) * (sd[7]) - (otherRZ * sd[4])) + (((otherDX) * (sd[2]) + (otherDW * sd[1])) + ((otherDY) * (sd[3]) - (otherDZ * sd[0]))));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat mul_s9f969cca_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[7]) + java.lang.Math.fma(otherRW, sd[6], -otherRX * sd[5]) + (java.lang.Math.fma(otherDY, sd[0], otherDZ * sd[3]) + java.lang.Math.fma(otherDW, sd[2], otherDX * -sd[1]));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -otherRX * sd[4]) + java.lang.Math.fma(-otherRZ, sd[6], -otherRY * sd[5]) + (java.lang.Math.fma(otherDW, sd[3], otherDX * -sd[0]) + java.lang.Math.fma(otherDZ, -sd[2], otherDY * -sd[1]));
        dd[3] = java.lang.Math.fma(otherRW, sd[3], otherRX * -sd[0]) - java.lang.Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat mul_s9f969cca_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = ((otherRY) * (sd[4]) + (otherRZ * sd[7])) + ((otherRW) * (sd[6]) - (otherRX * sd[5])) + (((otherDY) * (sd[0]) + (otherDZ * sd[3])) + ((otherDW) * (sd[2]) - (otherDX * sd[1])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((otherDZ) * (-sd[2]) - (otherDY * sd[1])));
        dd[3] = ((otherRW) * (sd[3]) - (otherRX * sd[0])) - ((otherRY) * (sd[1]) + (otherRZ * sd[2]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(otherRX, sd[3], otherRW * sd[0]) + java.lang.Math.fma(otherRZ, sd[1], -otherRY * sd[2]);
        dd[1] = java.lang.Math.fma(otherRX, sd[2], otherRW * sd[1]) + java.lang.Math.fma(otherRY, sd[3], otherRZ * -sd[0]);
        dd[2] = java.lang.Math.fma(otherRY, sd[0], otherRZ * sd[3]) + java.lang.Math.fma(otherRW, sd[2], -otherRX * sd[1]);
        dd[3] = java.lang.Math.fma(otherRW, sd[3], otherRX * -sd[0]) - java.lang.Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[4] = java.lang.Math.fma(otherRX, sd[7], otherRW * sd[4]) + java.lang.Math.fma(otherRZ, sd[5], -otherRY * sd[6]) + (java.lang.Math.fma(otherDX, sd[3], otherDW * sd[0]) + java.lang.Math.fma(otherDZ, sd[1], otherDY * -sd[2]));
        return mul_sdf825025_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat mul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = ((otherRX) * (sd[3]) + (otherRW * sd[0])) + ((otherRZ) * (sd[1]) - (otherRY * sd[2]));
        dd[1] = ((otherRX) * (sd[2]) + (otherRW * sd[1])) + ((otherRY) * (sd[3]) - (otherRZ * sd[0]));
        dd[2] = ((otherRY) * (sd[0]) + (otherRZ * sd[3])) + ((otherRW) * (sd[2]) - (otherRX * sd[1]));
        dd[3] = ((otherRW) * (sd[3]) - (otherRX * sd[0])) - ((otherRY) * (sd[1]) + (otherRZ * sd[2]));
        dd[4] = ((otherRX) * (sd[7]) + (otherRW * sd[4])) + ((otherRZ) * (sd[5]) - (otherRY * sd[6])) + (((otherDX) * (sd[3]) + (otherDW * sd[0])) + ((otherDZ) * (sd[1]) - (otherDY * sd[2])));
        return mul_sdf825025_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sdf825025_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRX, sd[6], otherRW * sd[5]) + java.lang.Math.fma(otherRY, sd[7], -otherRZ * sd[4]) + (java.lang.Math.fma(otherDX, sd[2], otherDW * sd[1]) + java.lang.Math.fma(otherDY, sd[3], otherDZ * -sd[0]));
        dd[6] = java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[7]) + java.lang.Math.fma(otherRW, sd[6], -otherRX * sd[5]) + (java.lang.Math.fma(otherDY, sd[0], otherDZ * sd[3]) + java.lang.Math.fma(otherDW, sd[2], otherDX * -sd[1]));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -otherRX * sd[4]) + java.lang.Math.fma(-otherRZ, sd[6], -otherRY * sd[5]) + (java.lang.Math.fma(otherDW, sd[3], otherDX * -sd[0]) + java.lang.Math.fma(otherDZ, -sd[2], otherDY * -sd[1]));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat mul_sdf825025_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = ((otherRX) * (sd[6]) + (otherRW * sd[5])) + ((otherRY) * (sd[7]) - (otherRZ * sd[4])) + (((otherDX) * (sd[2]) + (otherDW * sd[1])) + ((otherDY) * (sd[3]) - (otherDZ * sd[0])));
        dd[6] = ((otherRY) * (sd[4]) + (otherRZ * sd[7])) + ((otherRW) * (sd[6]) - (otherRX * sd[5])) + (((otherDY) * (sd[0]) + (otherDZ * sd[3])) + ((otherDW) * (sd[2]) - (otherDX * sd[1])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((otherDZ) * (-sd[2]) - (otherDY * sd[1])));
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return preMul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return preMul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        return preMul_sf00631ab_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, java.lang.Math.fma(otherRX, sd[3], otherRW * sd[0]) + java.lang.Math.fma(otherRY, sd[2], -otherRZ * sd[1]), java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[0]) + java.lang.Math.fma(otherRW, sd[1], -otherRX * sd[2]), java.lang.Math.fma(otherRX, sd[1], otherRW * sd[2]) + java.lang.Math.fma(otherRZ, sd[3], -otherRY * sd[0]), java.lang.Math.fma(otherRX, sd[7], otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], -otherRZ * sd[5]) + (java.lang.Math.fma(otherDX, sd[3], otherDW * sd[0]) + java.lang.Math.fma(otherDY, sd[2], otherDZ * -sd[1])), java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(otherRW, sd[5], -otherRX * sd[6]) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(otherDW, sd[1], otherDX * -sd[2])));
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat preMul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        return preMul_sf00631ab_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, ((otherRX) * (sd[3]) + (otherRW * sd[0])) + ((otherRY) * (sd[2]) - (otherRZ * sd[1])), ((otherRY) * (sd[3]) + (otherRZ * sd[0])) + ((otherRW) * (sd[1]) - (otherRX * sd[2])), ((otherRX) * (sd[1]) + (otherRW * sd[2])) + ((otherRZ) * (sd[3]) - (otherRY * sd[0])), ((otherRX) * (sd[7]) + (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[5])) + (((otherDX) * (sd[3]) + (otherDW * sd[0])) + ((otherDY) * (sd[2]) - (otherDZ * sd[1]))), ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((otherRW) * (sd[5]) - (otherRX * sd[6])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((otherDW) * (sd[1]) - (otherDX * sd[2]))));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat preMul_sf00631ab_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = java.lang.Math.fma(otherRX, sd[5], otherRW * sd[6]) + java.lang.Math.fma(otherRZ, sd[7], -otherRY * sd[4]) + (java.lang.Math.fma(otherDX, sd[1], otherDW * sd[2]) + java.lang.Math.fma(otherDZ, sd[3], otherDY * -sd[0]));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -otherRX * sd[4]) + java.lang.Math.fma(-otherRZ, sd[6], -otherRY * sd[5]) + (java.lang.Math.fma(otherDW, sd[3], otherDX * -sd[0]) + java.lang.Math.fma(otherDZ, -sd[2], otherDY * -sd[1]));
        dd[3] = java.lang.Math.fma(otherRW, sd[3], -otherRX * sd[0]) - java.lang.Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat preMul_sf00631ab_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = ((otherRX) * (sd[5]) + (otherRW * sd[6])) + ((otherRZ) * (sd[7]) - (otherRY * sd[4])) + (((otherDX) * (sd[1]) + (otherDW * sd[2])) + ((otherDZ) * (sd[3]) - (otherDY * sd[0])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((otherDZ) * (-sd[2]) - (otherDY * sd[1])));
        dd[3] = ((otherRW) * (sd[3]) - (otherRX * sd[0])) - ((otherRY) * (sd[1]) + (otherRZ * sd[2]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(otherRX, sd[3], otherRW * sd[0]) + java.lang.Math.fma(otherRY, sd[2], -otherRZ * sd[1]);
        dd[1] = java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[0]) + java.lang.Math.fma(otherRW, sd[1], -otherRX * sd[2]);
        dd[2] = java.lang.Math.fma(otherRX, sd[1], otherRW * sd[2]) + java.lang.Math.fma(otherRZ, sd[3], -otherRY * sd[0]);
        dd[3] = java.lang.Math.fma(otherRW, sd[3], -otherRX * sd[0]) - java.lang.Math.fma(otherRY, sd[1], otherRZ * sd[2]);
        dd[4] = java.lang.Math.fma(otherRX, sd[7], otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], -otherRZ * sd[5]) + (java.lang.Math.fma(otherDX, sd[3], otherDW * sd[0]) + java.lang.Math.fma(otherDY, sd[2], otherDZ * -sd[1]));
        return preMul_s8fae4586_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preMul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = ((otherRX) * (sd[3]) + (otherRW * sd[0])) + ((otherRY) * (sd[2]) - (otherRZ * sd[1]));
        dd[1] = ((otherRY) * (sd[3]) + (otherRZ * sd[0])) + ((otherRW) * (sd[1]) - (otherRX * sd[2]));
        dd[2] = ((otherRX) * (sd[1]) + (otherRW * sd[2])) + ((otherRZ) * (sd[3]) - (otherRY * sd[0]));
        dd[3] = ((otherRW) * (sd[3]) - (otherRX * sd[0])) - ((otherRY) * (sd[1]) + (otherRZ * sd[2]));
        dd[4] = ((otherRX) * (sd[7]) + (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[5])) + (((otherDX) * (sd[3]) + (otherDW * sd[0])) + ((otherDY) * (sd[2]) - (otherDZ * sd[1])));
        return preMul_s8fae4586_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8fae4586_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(otherRW, sd[5], -otherRX * sd[6]) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(otherDW, sd[1], otherDX * -sd[2]));
        dd[6] = java.lang.Math.fma(otherRX, sd[5], otherRW * sd[6]) + java.lang.Math.fma(otherRZ, sd[7], -otherRY * sd[4]) + (java.lang.Math.fma(otherDX, sd[1], otherDW * sd[2]) + java.lang.Math.fma(otherDZ, sd[3], otherDY * -sd[0]));
        dd[7] = java.lang.Math.fma(otherRW, sd[7], -otherRX * sd[4]) + java.lang.Math.fma(-otherRZ, sd[6], -otherRY * sd[5]) + (java.lang.Math.fma(otherDW, sd[3], otherDX * -sd[0]) + java.lang.Math.fma(otherDZ, -sd[2], otherDY * -sd[1]));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat preMul_s8fae4586_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd) {
        dd[5] = ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((otherRW) * (sd[5]) - (otherRX * sd[6])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((otherDW) * (sd[1]) - (otherDX * sd[2])));
        dd[6] = ((otherRX) * (sd[5]) + (otherRW * sd[6])) + ((otherRZ) * (sd[7]) - (otherRY * sd[4])) + (((otherDX) * (sd[1]) + (otherDW * sd[2])) + ((otherDZ) * (sd[3]) - (otherDY * sd[0])));
        dd[7] = ((otherRW) * (sd[7]) - (otherRX * sd[4])) + ((-otherRZ) * (sd[6]) - (otherRY * sd[5])) + (((otherDW) * (sd[3]) - (otherDX * sd[0])) + ((otherDZ) * (-sd[2]) - (otherDY * sd[1])));
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return addScaled(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], weight, dest);
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return addScaled(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], weight, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
     * dual part (for a unit dual quaternion this is its inverse), and store the result in
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
     * dual part (for a unit dual quaternion this is its inverse), and store the result in
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
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return difference(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
    }

    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        return difference(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], dest);
    }

    /**
     * Compute the difference between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}), i.e. the rigid transformation {@code D} with
     * {@code this * D = (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
     * that is
     * {@code D = this^-1 * (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        float _t0 = -otherRX;
        return difference_s52291601_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, java.lang.Math.fma(otherRX, sd[3], -otherRW * sd[0]) + java.lang.Math.fma(otherRY, sd[2], otherRZ * -sd[1]), java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[0]) + java.lang.Math.fma(_t0, sd[2], -otherRW * sd[1]), java.lang.Math.fma(otherRX, sd[1], -otherRW * sd[2]) + java.lang.Math.fma(otherRZ, sd[3], otherRY * -sd[0]), java.lang.Math.fma(otherRX, sd[7], -otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (java.lang.Math.fma(otherDX, sd[3], otherDW * -sd[0]) + java.lang.Math.fma(otherDY, sd[2], otherDZ * -sd[1])), java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(_t0, sd[6], -otherRW * sd[5]) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(otherDX, -sd[2], otherDW * -sd[1])));
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat difference_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = -otherRX;
        return difference_s52291601_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, ((FloatDualQuatImpl) dest).data, ((otherRX) * (sd[3]) - (otherRW * sd[0])) + ((otherRY) * (sd[2]) - (otherRZ * sd[1])), ((otherRY) * (sd[3]) + (otherRZ * sd[0])) + ((_t0) * (sd[2]) - (otherRW * sd[1])), ((otherRX) * (sd[1]) - (otherRW * sd[2])) + ((otherRZ) * (sd[3]) - (otherRY * sd[0])), ((otherRX) * (sd[7]) - (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[5])) + (((otherDX) * (sd[3]) - (otherDW * sd[0])) + ((otherDY) * (sd[2]) - (otherDZ * sd[1]))), ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((_t0) * (sd[6]) - (otherRW * sd[5])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((otherDX) * (-sd[2]) - (otherDW * sd[1]))));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat difference_s52291601_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = java.lang.Math.fma(otherRX, sd[5], -otherRW * sd[6]) + java.lang.Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (java.lang.Math.fma(otherDX, sd[1], otherDW * -sd[2]) + java.lang.Math.fma(otherDZ, sd[3], otherDY * -sd[0]));
        dd[7] = java.lang.Math.fma(otherRX, sd[4], otherRW * sd[7]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * sd[6]) + (java.lang.Math.fma(otherDX, sd[0], otherDW * sd[3]) + java.lang.Math.fma(otherDY, sd[1], otherDZ * sd[2]));
        dd[3] = java.lang.Math.fma(otherRX, sd[0], otherRW * sd[3]) - java.lang.Math.fma(otherRZ, -sd[2], otherRY * -sd[1]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat difference_s52291601_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        float _buf5 = ((otherRX) * (sd[5]) - (otherRW * sd[6])) + ((otherRZ) * (sd[7]) - (otherRY * sd[4])) + (((otherDX) * (sd[1]) - (otherDW * sd[2])) + ((otherDZ) * (sd[3]) - (otherDY * sd[0])));
        dd[7] = ((otherRX) * (sd[4]) + (otherRW * sd[7])) + ((otherRY) * (sd[5]) + (otherRZ * sd[6])) + (((otherDX) * (sd[0]) + (otherDW * sd[3])) + ((otherDY) * (sd[1]) + (otherDZ * sd[2])));
        dd[3] = ((otherRX) * (sd[0]) + (otherRW * sd[3])) - ((otherRZ) * (-sd[2]) - (otherRY * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Compute the difference between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}), i.e. the rigid transformation {@code D} with
     * {@code this * D = (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
     * that is
     * {@code D = this^-1 * (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -otherRX;
        dd[0] = java.lang.Math.fma(otherRX, sd[3], -otherRW * sd[0]) + java.lang.Math.fma(otherRY, sd[2], otherRZ * -sd[1]);
        dd[1] = java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[0]) + java.lang.Math.fma(_t0, sd[2], -otherRW * sd[1]);
        dd[2] = java.lang.Math.fma(otherRX, sd[1], -otherRW * sd[2]) + java.lang.Math.fma(otherRZ, sd[3], otherRY * -sd[0]);
        dd[3] = java.lang.Math.fma(otherRX, sd[0], otherRW * sd[3]) - java.lang.Math.fma(otherRZ, -sd[2], otherRY * -sd[1]);
        dd[4] = java.lang.Math.fma(otherRX, sd[7], -otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], -(otherRZ * sd[5])) + (java.lang.Math.fma(otherDX, sd[3], otherDW * -sd[0]) + java.lang.Math.fma(otherDY, sd[2], otherDZ * -sd[1]));
        return difference_s86fe7700_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd, _t0);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat difference_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -otherRX;
        dd[0] = ((otherRX) * (sd[3]) - (otherRW * sd[0])) + ((otherRY) * (sd[2]) - (otherRZ * sd[1]));
        dd[1] = ((otherRY) * (sd[3]) + (otherRZ * sd[0])) + ((_t0) * (sd[2]) - (otherRW * sd[1]));
        dd[2] = ((otherRX) * (sd[1]) - (otherRW * sd[2])) + ((otherRZ) * (sd[3]) - (otherRY * sd[0]));
        dd[3] = ((otherRX) * (sd[0]) + (otherRW * sd[3])) - ((otherRZ) * (-sd[2]) - (otherRY * sd[1]));
        dd[4] = ((otherRX) * (sd[7]) - (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[5])) + (((otherDX) * (sd[3]) - (otherDW * sd[0])) + ((otherDY) * (sd[2]) - (otherDZ * sd[1])));
        return difference_s86fe7700_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, dest, sd, dd, _t0);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_s86fe7700_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd, float _t0) {
        dd[5] = java.lang.Math.fma(otherRY, sd[7], otherRZ * sd[4]) + java.lang.Math.fma(_t0, sd[6], -otherRW * sd[5]) + (java.lang.Math.fma(otherDY, sd[3], otherDZ * sd[0]) + java.lang.Math.fma(otherDX, -sd[2], otherDW * -sd[1]));
        dd[6] = java.lang.Math.fma(otherRX, sd[5], -otherRW * sd[6]) + java.lang.Math.fma(otherRZ, sd[7], -(otherRY * sd[4])) + (java.lang.Math.fma(otherDX, sd[1], otherDW * -sd[2]) + java.lang.Math.fma(otherDZ, sd[3], otherDY * -sd[0]));
        dd[7] = java.lang.Math.fma(otherRX, sd[4], otherRW * sd[7]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * sd[6]) + (java.lang.Math.fma(otherDX, sd[0], otherDW * sd[3]) + java.lang.Math.fma(otherDY, sd[1], otherDZ * sd[2]));
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat difference_s86fe7700_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, DoubleDualQuat dest, float[] sd, double[] dd, float _t0) {
        dd[5] = ((otherRY) * (sd[7]) + (otherRZ * sd[4])) + ((_t0) * (sd[6]) - (otherRW * sd[5])) + (((otherDY) * (sd[3]) + (otherDZ * sd[0])) + ((otherDX) * (-sd[2]) - (otherDW * sd[1])));
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float otherRX = otherData[0];
        float otherRY = otherData[1];
        float otherRZ = otherData[2];
        float otherRW = otherData[3];
        float otherDX = otherData[4];
        float otherDY = otherData[5];
        float otherDZ = otherData[6];
        float otherDW = otherData[7];
        if (org.joml2.internal.FmaSwitch.ON) {
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (org.joml2.internal.FmaSwitch.ON) return exp_fma(dest);
        return exp_mulAdd(dest);
    }

    /** {@code exp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat exp_fma(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t5 = java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], sd[1] * sd[5]));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sinWithCos(_t7);
        float _sp0 = _t6 * _t8;
        float _t9 = Math.cosWithSin(_t7);
        float _t10 = sd[0] * _t6;
        float _t11 = sd[1] * _t6;
        float _t12 = sd[2] * _t6;
        float _t13 = _t5 * _t6;
        float _t14 = _t13 * _t9;
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
            dd[0] = _t10 * _t8;
            dd[1] = _t11 * _t8;
            dd[2] = _t12 * _t8;
            dd[3] = _t9;
            dd[4] = java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(_t10, -_t13, sd[4]) * _sp0);
            dd[5] = java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(_t11, -_t13, sd[5]) * _sp0);
            dd[6] = java.lang.Math.fma(_t12, _t14, java.lang.Math.fma(_t12, -_t13, sd[6]) * _sp0);
            dd[7] = -_t13 * _t8;
        }
        return dest;
    }

    /** {@code exp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat exp_mulAdd(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t5 = ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) + (sd[1] * sd[5]))));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sinWithCos(_t7);
        float _sp0 = _t6 * _t8;
        float _t9 = Math.cosWithSin(_t7);
        float _t10 = sd[0] * _t6;
        float _t11 = sd[1] * _t6;
        float _t12 = sd[2] * _t6;
        float _t13 = _t5 * _t6;
        float _t14 = _t13 * _t9;
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
            dd[0] = _t10 * _t8;
            dd[1] = _t11 * _t8;
            dd[2] = _t12 * _t8;
            dd[3] = _t9;
            dd[4] = ((_t10) * (_t14) + (((sd[4]) - (_t10) * (_t13)) * _sp0));
            dd[5] = ((_t11) * (_t14) + (((sd[5]) - (_t11) * (_t13)) * _sp0));
            dd[6] = ((_t12) * (_t14) + (((sd[6]) - (_t12) * (_t13)) * _sp0));
            dd[7] = -_t13 * _t8;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float _t4 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            float _t5 = java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], sd[1] * sd[5]));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sinWithCos(_t7);
            float _t9 = Math.cosWithSin(_t7);
            float _t13 = _t5 * _t6;
            return exp_sa11f286a_1_fma(dest, sd, ((DoubleDualQuatImpl) dest).data, _t4, _t5, _t8, _t6 * _t8, _t9, sd[0] * _t6, sd[1] * _t6, sd[2] * _t6, _t13, _t13 * _t9);
        } else {
            float[] sd = this.data;
            float _t4 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            float _t5 = ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) + (sd[1] * sd[5]))));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sinWithCos(_t7);
            float _t9 = Math.cosWithSin(_t7);
            float _t13 = _t5 * _t6;
            return exp_sa11f286a_1_mulAdd(dest, sd, ((DoubleDualQuatImpl) dest).data, _t4, _t5, _t8, _t6 * _t8, _t9, sd[0] * _t6, sd[1] * _t6, sd[2] * _t6, _t13, _t13 * _t9);
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
            dd[0] = _t10 * _t8;
            dd[1] = _t11 * _t8;
            dd[2] = _t12 * _t8;
            dd[3] = _t9;
            dd[4] = java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(_t10, -_t13, sd[4]) * _sp0);
            dd[5] = java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(_t11, -_t13, sd[5]) * _sp0);
            dd[6] = java.lang.Math.fma(_t12, _t14, java.lang.Math.fma(_t12, -_t13, sd[6]) * _sp0);
            dd[7] = -_t13 * _t8;
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
            dd[0] = _t10 * _t8;
            dd[1] = _t11 * _t8;
            dd[2] = _t12 * _t8;
            dd[3] = _t9;
            dd[4] = ((_t10) * (_t14) + (((sd[4]) - (_t10) * (_t13)) * _sp0));
            dd[5] = ((_t11) * (_t14) + (((sd[5]) - (_t11) * (_t13)) * _sp0));
            dd[6] = ((_t12) * (_t14) + (((sd[6]) - (_t12) * (_t13)) * _sp0));
            dd[7] = -_t13 * _t8;
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
        FloatVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 0);
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
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t11 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t12 = 2.0f * ((sd[0]) * (sd[3]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0f * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t11 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t12 = 2.0f * ((sd[0]) * (sd[3]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0f * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t11 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t12 = 2.0f * ((sd[0]) * (sd[3]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0f * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t10 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t11 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t12 = 2.0f * ((sd[0]) * (sd[3]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0f * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t10 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0f * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t10 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0f * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t10 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0f * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0f * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[1] * sd[2];
        float _t9 = 2.0f * ((sd[0]) * (sd[3]) + (_t1));
        float _t10 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0f * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0f * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[1], sd[3], sd[0] * -sd[2]), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -sd[2] * sd[3])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0f * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0f * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[1], sd[3], sd[0] * -sd[2]), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -sd[2] * sd[3])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0f * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[1] * sd[2];
        float _t2 = sd[1] * sd[3];
        float _t9 = 2.0f * ((sd[0]) * (sd[2]) + (_t2));
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0f * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0f * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        float _t12 = 2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0f * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0f * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        float _t12 = 2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0f * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0f * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        float _t12 = 2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0f * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0f * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[2] * sd[3];
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        float _t12 = 2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0f * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        float _t12 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        float _t12 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        float _t12 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[2] * sd[2];
        float _t10 = 2.0f * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        float _t11 = 2.0f * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        float _t12 = 2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * ((sd[0]) * (sd[1]) + (_t1));
        float _t9 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * ((sd[0]) * (sd[1]) + (_t1));
        float _t9 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * ((sd[0]) * (sd[1]) + (_t1));
        float _t9 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0f * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t8 = 2.0f * ((sd[0]) * (sd[1]) + (_t1));
        float _t9 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[1]) * (sd[1]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[0]) * (sd[0]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0f * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
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
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
            dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
            dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
            dd[1] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
            dd[2] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
            dd[1] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
            dd[2] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (java.lang.Math.fma(sd[0], sd[4], sd[1] * sd[5]) + java.lang.Math.fma(sd[2], sd[6], sd[3] * sd[7])) / (_t8 * _t8);
        dd[4] = java.lang.Math.fma(sd[0], _sp0, -(sd[4] * _t8_inv));
        dd[0] = -(sd[0] * _t8_inv);
        dd[5] = java.lang.Math.fma(sd[1], _sp0, -(sd[5] * _t8_inv));
        dd[1] = -(sd[1] * _t8_inv);
        dd[6] = java.lang.Math.fma(sd[2], _sp0, -(sd[6] * _t8_inv));
        dd[2] = -(sd[2] * _t8_inv);
        dd[7] = java.lang.Math.fma(sd[7], _t8_inv, -(sd[3] * _sp0));
        dd[3] = sd[3] * _t8_inv;
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat invert_mulAdd(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = ((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3]));
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (((sd[0]) * (sd[4]) + (sd[1] * sd[5])) + ((sd[2]) * (sd[6]) + (sd[3] * sd[7]))) / (_t8 * _t8);
        dd[4] = ((sd[0]) * (_sp0) - (sd[4] * _t8_inv));
        dd[0] = -(sd[0] * _t8_inv);
        dd[5] = ((sd[1]) * (_sp0) - (sd[5] * _t8_inv));
        dd[1] = -(sd[1] * _t8_inv);
        dd[6] = ((sd[2]) * (_sp0) - (sd[6] * _t8_inv));
        dd[2] = -(sd[2] * _t8_inv);
        dd[7] = ((sd[7]) * (_t8_inv) - (sd[3] * _sp0));
        dd[3] = sd[3] * _t8_inv;
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (java.lang.Math.fma(sd[0], sd[4], sd[1] * sd[5]) + java.lang.Math.fma(sd[2], sd[6], sd[3] * sd[7])) / (_t8 * _t8);
        dd[0] = -(sd[0] * _t8_inv);
        dd[1] = -(sd[1] * _t8_inv);
        dd[2] = -(sd[2] * _t8_inv);
        dd[3] = sd[3] * _t8_inv;
        dd[4] = java.lang.Math.fma(sd[0], _sp0, -(sd[4] * _t8_inv));
        dd[5] = java.lang.Math.fma(sd[1], _sp0, -(sd[5] * _t8_inv));
        dd[6] = java.lang.Math.fma(sd[2], _sp0, -(sd[6] * _t8_inv));
        dd[7] = java.lang.Math.fma(sd[7], _t8_inv, -(sd[3] * _sp0));
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat invert_mulAdd(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = ((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3]));
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (((sd[0]) * (sd[4]) + (sd[1] * sd[5])) + ((sd[2]) * (sd[6]) + (sd[3] * sd[7]))) / (_t8 * _t8);
        dd[0] = -(sd[0] * _t8_inv);
        dd[1] = -(sd[1] * _t8_inv);
        dd[2] = -(sd[2] * _t8_inv);
        dd[3] = sd[3] * _t8_inv;
        dd[4] = ((sd[0]) * (_sp0) - (sd[4] * _t8_inv));
        dd[5] = ((sd[1]) * (_sp0) - (sd[5] * _t8_inv));
        dd[6] = ((sd[2]) * (_sp0) - (sd[6] * _t8_inv));
        dd[7] = ((sd[7]) * (_t8_inv) - (sd[3] * _sp0));
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        if (!org.joml2.internal.FmaSwitch.ON) return log_mulAdd(dest);
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return log_sfb80bbd6_1_fma(dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11);
    }

    /** {@code log} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat log_mulAdd(@Mutated FloatDualQuat dest) {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return log_sfb80bbd6_1_mulAdd(dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11);
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
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, _t21 * -_t25);
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, _t23 * -_t25);
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, _t24 * -_t25);
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
        if (!org.joml2.internal.FmaSwitch.ON) return log_mulAdd(dest);
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return log_sfb9ef421_1_fma(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11);
    }

    /** {@code log} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat log_mulAdd(@Mutated DoubleDualQuat dest) {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t25 = _t19 * (sd[3] < 0.0f ? -sd[7] : sd[7]);
        return log_sfb9ef421_1_mulAdd(dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t14, _t15, _t18, _t19, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t19 * _t10, _t19 * _t8, _t25, _t25 * _t11);
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
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, _t21 * -_t25);
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, _t23 * -_t25);
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, _t24 * -_t25);
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
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatDualQuat makeFromMatrix(Float4x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
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
            if (mData[0] > Math.max(mData[5], mData[10])) {
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
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(mData[12], _t66, mData[13] * _t65));
        dd[5] = 0.5f * java.lang.Math.fma(mData[14], _t63, java.lang.Math.fma(mData[13], _t66, -mData[12] * _t65));
        return makeFromMatrix_s40927f3d_2_fma(dd, mData, _t0, _t63, _t64, _t65, _t66);
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
            if (mData[0] > Math.max(mData[5], mData[10])) {
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
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * ((_t0) * (_t64) + (((mData[12]) * (_t66) + (mData[13] * _t65))));
        dd[5] = 0.5f * ((mData[14]) * (_t63) + (((mData[13]) * (_t66) - (mData[12] * _t65))));
        dd[6] = 0.5f * ((mData[14]) * (_t66) + (((mData[12]) * (_t64) - (mData[13] * _t63))));
        dd[7] = 0.5f * ((_t0) * (_t65) + (((-mData[13]) * (_t64) - (mData[12] * _t63))));
        return this;
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s40927f3d_2_fma(float[] dd, float[] mData, float _t0, float _t63, float _t64, float _t65, float _t66) {
        dd[6] = 0.5f * java.lang.Math.fma(mData[14], _t66, java.lang.Math.fma(mData[12], _t64, -mData[13] * _t63));
        dd[7] = 0.5f * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-mData[13], _t64, -mData[12] * _t63));
        return this;
    }

    /**
     * Set this dual quaternion to the rigid motion of the given matrix: rotation from its
     * upper-left 3x3 block and translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatDualQuat makeFromMatrix(Float3x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
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
            if (mData[0] > Math.max(mData[5], mData[10])) {
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
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(mData[3], _t66, mData[7] * _t65));
        dd[5] = 0.5f * java.lang.Math.fma(mData[11], _t63, java.lang.Math.fma(mData[7], _t66, -mData[3] * _t65));
        return makeFromMatrix_s7b6de20e_2_fma(dd, mData, _t0, _t63, _t64, _t65, _t66);
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
            if (mData[0] > Math.max(mData[5], mData[10])) {
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
        dd[0] = _t63;
        dd[1] = _t64;
        dd[2] = _t65;
        dd[3] = _t66;
        dd[4] = 0.5f * ((_t0) * (_t64) + (((mData[3]) * (_t66) + (mData[7] * _t65))));
        dd[5] = 0.5f * ((mData[11]) * (_t63) + (((mData[7]) * (_t66) - (mData[3] * _t65))));
        dd[6] = 0.5f * ((mData[11]) * (_t66) + (((mData[3]) * (_t64) - (mData[7] * _t63))));
        dd[7] = 0.5f * ((_t0) * (_t65) + (((-mData[7]) * (_t64) - (mData[3] * _t63))));
        return this;
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeFromMatrix_s7b6de20e_2_fma(float[] dd, float[] mData, float _t0, float _t63, float _t64, float _t65, float _t66) {
        dd[6] = 0.5f * java.lang.Math.fma(mData[11], _t66, java.lang.Math.fma(mData[3], _t64, -mData[7] * _t63));
        dd[7] = 0.5f * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-mData[7], _t64, -mData[3] * _t63));
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
            if (mData[0] > Math.max(mData[4], mData[8])) {
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
        VEC_2.intoArray(dd, 4);
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
        if (!org.joml2.internal.FmaSwitch.ON) return normalize_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
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

    /** {@code normalize} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat normalize_mulAdd(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t4 = ((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return normalize_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t4 = java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]);
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

    /** {@code normalize} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat normalize_mulAdd(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t4 = ((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3]));
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
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code t} must lie in
     * {@code [-4096, 4096]}.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat pow(float t, @Mutated FloatDualQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return pow_mulAdd(t, dest);
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
        float _t29 = Math.cosWithSin(_t26);
        return pow_see3b1b7e_1_fma(t, dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, Math.sinWithCos(_t26), _t29, _t25 * _t11, _t27 * _t29);
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
        float _t29 = Math.cosWithSin(_t26);
        return pow_see3b1b7e_1_mulAdd(t, dest, ((FloatDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, Math.sinWithCos(_t26), _t29, _t25 * _t11, _t27 * _t29);
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
            dd[3] = _t29;
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
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
            dd[3] = _t29;
            dd[4] = ((((_t30) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
            dd[5] = ((((_t30) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
            dd[6] = ((((_t30) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
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
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code t} must lie in
     * {@code [-4096, 4096]}.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat pow(float t, @Mutated DoubleDualQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return pow_mulAdd(t, dest);
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
        float _t29 = Math.cosWithSin(_t26);
        return pow_s250262f9_1_fma(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, Math.sinWithCos(_t26), _t29, _t25 * _t11, _t27 * _t29);
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
        float _t29 = Math.cosWithSin(_t26);
        return pow_s250262f9_1_mulAdd(t, dest, ((DoubleDualQuatImpl) dest).data, _t8, _t9, _t10, _t12, _t13, _t14, _t15, _t18, _t19, _t19 * _t9, _t19 * _t10, _t19 * _t8, _t27, Math.sinWithCos(_t26), _t29, _t25 * _t11, _t27 * _t29);
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
            dd[3] = _t29;
            dd[4] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            dd[5] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            dd[6] = java.lang.Math.fma(java.lang.Math.fma(_t30, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
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
            dd[3] = _t29;
            dd[4] = ((((_t30) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
            dd[5] = ((((_t30) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
            dd[6] = ((((_t30) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
            dd[7] = _t27 * _t28;
        }
        return dest;
    }

    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is, and store the result in {@code dest}.
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
        float[] dualData = ((FloatQuatImpl) dual).data;
        float dualX = dualData[0];
        float dualY = dualData[1];
        float dualZ = dualData[2];
        float dualW = dualData[3];
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }

    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is, and store the result in {@code dest}.
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
        float[] dualData = ((FloatQuatImpl) dual).data;
        float dualX = dualData[0];
        float dualY = dualData[1];
        float dualZ = dualData[2];
        float dualW = dualData[3];
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
     * {@code dualW}), keeping the real (rotation) half as it is, and store the result in
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
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = dualX;
        dd[5] = dualY;
        dd[6] = dualZ;
        dd[7] = dualW;
        return dest;
    }

    /**
     * Set the dual half of this dual quaternion to ({@code dualX}, {@code dualY}, {@code dualZ},
     * {@code dualW}), keeping the real (rotation) half as it is, and store the result in
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
     * as it is, and store the result in {@code dest}.
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
        float[] realData = ((FloatQuatImpl) real).data;
        float realY = realData[1];
        float realZ = realData[2];
        float realW = realData[3];
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = realData[0];
        dd[1] = realY;
        dd[2] = realZ;
        dd[3] = realW;
        FloatVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        return dest;
    }

    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is, and store the result in {@code dest}.
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
        float[] realData = ((FloatQuatImpl) real).data;
        float realY = realData[1];
        float realZ = realData[2];
        float realW = realData[3];
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = realData[0];
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
     * {@code realZ}, {@code realW}), keeping the dual half as it is, and store the result in
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
        FloatVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        return dest;
    }

    /**
     * Set the real (rotation) half of this dual quaternion to ({@code realX}, {@code realY},
     * {@code realZ}, {@code realW}), keeping the dual half as it is, and store the result in
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (org.joml2.internal.FmaSwitch.ON) return setRotation_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (org.joml2.internal.FmaSwitch.ON) return setRotation_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return setRotation_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
    }

    /** {@code setRotation} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat setRotation_fma(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        float _t23 = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        float _t24 = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24));
        dd[5] = 0.5f * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24));
        dd[6] = 0.5f * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23));
        dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24));
        return dest;
    }

    /** {@code setRotation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat setRotation_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
        float _t23 = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        float _t24 = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * ((_t0) * (_t22) + (((rotationZ) * (_t23) + (rotationW * _t24))));
        dd[5] = 0.5f * ((rotationX) * (_t22) + (((rotationW) * (_t23) - (rotationZ * _t24))));
        dd[6] = 0.5f * ((rotationW) * (_t22) + (((rotationY) * (_t24) - (rotationX * _t23))));
        dd[7] = 0.5f * ((((_t0) * (_t23) - (rotationX * _t24))) - (rotationZ) * (_t22));
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
        if (org.joml2.internal.FmaSwitch.ON) return setRotation_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
    }

    /** {@code setRotation} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat setRotation_fma(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], -(sd[2] * sd[7])));
        float _t23 = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        float _t24 = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24));
        dd[5] = 0.5f * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24));
        dd[6] = 0.5f * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23));
        dd[7] = 0.5f * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24));
        return dest;
    }

    /** {@code setRotation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat setRotation_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -rotationY;
        float _t22 = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
        float _t23 = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        float _t24 = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[0] = rotationX;
        dd[1] = rotationY;
        dd[2] = rotationZ;
        dd[3] = rotationW;
        dd[4] = 0.5f * ((_t0) * (_t22) + (((rotationZ) * (_t23) + (rotationW * _t24))));
        dd[5] = 0.5f * ((rotationX) * (_t22) + (((rotationW) * (_t23) - (rotationZ * _t24))));
        dd[6] = 0.5f * ((rotationW) * (_t22) + (((rotationY) * (_t24) - (rotationX * _t23))));
        dd[7] = 0.5f * ((((_t0) * (_t23) - (rotationX * _t24))) - (rotationZ) * (_t22));
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return setTranslation_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * java.lang.Math.fma(sd[0], translationZ, java.lang.Math.fma(sd[3], translationY, -sd[2] * translationX));
        dd[6] = 0.5f * java.lang.Math.fma(sd[3], translationZ, java.lang.Math.fma(sd[1], translationX, -sd[0] * translationY));
        dd[7] = 0.5f * java.lang.Math.fma(-sd[2], translationZ, java.lang.Math.fma(_t0, translationY, -sd[0] * translationX));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return setTranslation_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * java.lang.Math.fma(sd[0], translationZ, java.lang.Math.fma(sd[3], translationY, -sd[2] * translationX));
        dd[6] = 0.5f * java.lang.Math.fma(sd[3], translationZ, java.lang.Math.fma(sd[1], translationX, -sd[0] * translationY));
        dd[7] = 0.5f * java.lang.Math.fma(-sd[2], translationZ, java.lang.Math.fma(_t0, translationY, -sd[0] * translationX));
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
        if (!org.joml2.internal.FmaSwitch.ON) return setTranslation_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * java.lang.Math.fma(sd[0], translationZ, java.lang.Math.fma(sd[3], translationY, -sd[2] * translationX));
        dd[6] = 0.5f * java.lang.Math.fma(sd[3], translationZ, java.lang.Math.fma(sd[1], translationX, -sd[0] * translationY));
        dd[7] = 0.5f * java.lang.Math.fma(-sd[2], translationZ, java.lang.Math.fma(_t0, translationY, -sd[0] * translationX));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }

    /** {@code setTranslation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat setTranslation_mulAdd(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[4] = 0.5f * ((_t0) * (translationZ) + (((sd[2]) * (translationY) + (sd[3] * translationX))));
        dd[5] = 0.5f * ((sd[0]) * (translationZ) + (((sd[3]) * (translationY) - (sd[2] * translationX))));
        dd[6] = 0.5f * ((sd[3]) * (translationZ) + (((sd[1]) * (translationX) - (sd[0] * translationY))));
        dd[7] = 0.5f * ((((_t0) * (translationY) - (sd[0] * translationX))) - (sd[2]) * (translationZ));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (!org.joml2.internal.FmaSwitch.ON) return setTranslation_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(sd[2], translationY, sd[3] * translationX));
        dd[5] = 0.5f * java.lang.Math.fma(sd[0], translationZ, java.lang.Math.fma(sd[3], translationY, -sd[2] * translationX));
        dd[6] = 0.5f * java.lang.Math.fma(sd[3], translationZ, java.lang.Math.fma(sd[1], translationX, -sd[0] * translationY));
        dd[7] = 0.5f * java.lang.Math.fma(-sd[2], translationZ, java.lang.Math.fma(_t0, translationY, -sd[0] * translationX));
        return dest;
    }

    /** {@code setTranslation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat setTranslation_mulAdd(float translationX, float translationY, float translationZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.5f * ((_t0) * (translationZ) + (((sd[2]) * (translationY) + (sd[3] * translationX))));
        dd[5] = 0.5f * ((sd[0]) * (translationZ) + (((sd[3]) * (translationY) - (sd[2] * translationX))));
        dd[6] = 0.5f * ((sd[3]) * (translationZ) + (((sd[1]) * (translationX) - (sd[0] * translationY))));
        dd[7] = 0.5f * ((((_t0) * (translationY) - (sd[0] * translationX))) - (sd[2]) * (translationZ));
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_fma(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[2] = java.lang.Math.fma(-2.0f, _t3, (sd[0] + sd[0]) * sd[2]);
        dd[3] = 0.0f;
        dd[4] = java.lang.Math.fma(-2.0f, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[7] = 0.0f;
        dd[8] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[9] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 0.0f;
        dd[12] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        return toMatrix_s6357fbbf_1_fma(dest, sd, dd);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[2] = (((sd[0] + sd[0]) * sd[2]) - (2.0f) * (_t3));
        dd[3] = 0.0f;
        dd[4] = (((sd[0] + sd[0]) * sd[1]) - (2.0f) * (_t2));
        dd[5] = ((_t6) - (2.0f) * (_t4));
        dd[6] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[7] = 0.0f;
        dd[8] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[9] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        dd[11] = 0.0f;
        dd[12] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        return toMatrix_s6357fbbf_1_mulAdd(dest, sd, dd);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1_fma(Float4x4 dest, float[] sd, float[] dd) {
        dd[13] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        dd[14] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        dd[15] = 1.0f;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1_mulAdd(Float4x4 dest, float[] sd, float[] dd) {
        dd[13] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[14] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[2] = java.lang.Math.fma(-2.0f, _t3, (sd[0] + sd[0]) * sd[2]);
        dd[3] = 0.0f;
        dd[4] = java.lang.Math.fma(-2.0f, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[7] = 0.0f;
        dd[8] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[9] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 0.0f;
        return toMatrix_s9c292502_1_fma(dest, sd, dd);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[2] = (((sd[0] + sd[0]) * sd[2]) - (2.0f) * (_t3));
        dd[3] = 0.0f;
        dd[4] = (((sd[0] + sd[0]) * sd[1]) - (2.0f) * (_t2));
        dd[5] = ((_t6) - (2.0f) * (_t4));
        dd[6] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[7] = 0.0f;
        dd[8] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[9] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        dd[11] = 0.0f;
        return toMatrix_s9c292502_1_mulAdd(dest, sd, dd);
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[2] = java.lang.Math.fma(-2.0f, _t3, _sp0 * sd[2]);
        dd[3] = java.lang.Math.fma(-2.0f, _t2, _sp0 * sd[1]);
        dd[4] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[5] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[6] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[7] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[8] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 toMatrix3x3_mulAdd(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[2] = ((_sp0 * sd[2]) - (2.0f) * (_t3));
        dd[3] = ((_sp0 * sd[1]) - (2.0f) * (_t2));
        dd[4] = ((_t6) - (2.0f) * (_t4));
        dd[5] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[6] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[7] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[8] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[2] = java.lang.Math.fma(-2.0f, _t3, _sp0 * sd[2]);
        dd[3] = java.lang.Math.fma(-2.0f, _t2, _sp0 * sd[1]);
        dd[4] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[5] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[6] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[7] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[8] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _sp0 = sd[0] + sd[0];
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[2] = ((_sp0 * sd[2]) - (2.0f) * (_t3));
        dd[3] = ((_sp0 * sd[1]) - (2.0f) * (_t2));
        dd[4] = ((_t6) - (2.0f) * (_t4));
        dd[5] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[6] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[7] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[8] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_fma(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = java.lang.Math.fma(-2.0f, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[2] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[3] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]));
        dd[4] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[7] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]));
        dd[8] = java.lang.Math.fma(-2.0f, _t3, (sd[0] + sd[0]) * sd[2]);
        return toMatrix3x4_scac7d5c1_1_fma(dest, sd, dd, _t0, _t4, _t5);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = (((sd[0] + sd[0]) * sd[1]) - (2.0f) * (_t2));
        dd[2] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[3] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[4] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[5] = ((_t6) - (2.0f) * (_t4));
        dd[6] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[7] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[8] = (((sd[0] + sd[0]) * sd[2]) - (2.0f) * (_t3));
        return toMatrix3x4_scac7d5c1_1_mulAdd(dest, sd, dd, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Float3x4 toMatrix3x4_scac7d5c1_1_fma(Float3x4 dest, float[] sd, float[] dd, float _t0, float _t4, float _t5) {
        dd[9] = 2.0f * java.lang.Math.fma(sd[0], sd[3], _t5);
        dd[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        dd[11] = 2.0f * (java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]));
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Float3x4 toMatrix3x4_scac7d5c1_1_mulAdd(Float3x4 dest, float[] sd, float[] dd, float _t0, float _t4, float _t5) {
        dd[9] = 2.0f * ((sd[0]) * (sd[3]) + (_t5));
        dd[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        dd[11] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_fma(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = java.lang.Math.fma(-2.0f, sd[2] * sd[2], 1.0f);
        dd[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        dd[1] = java.lang.Math.fma(-2.0f, _t2, (sd[0] + sd[0]) * sd[1]);
        dd[2] = 2.0f * java.lang.Math.fma(sd[0], sd[2], _t3);
        dd[3] = 2.0f * (java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], -(sd[0] * sd[7])));
        dd[4] = 2.0f * java.lang.Math.fma(sd[0], sd[1], _t2);
        dd[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        dd[6] = java.lang.Math.fma(-2.0f, sd[0] * sd[3], _t5 + _t5);
        dd[7] = 2.0f * (java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], -(sd[1] * sd[7])));
        dd[8] = java.lang.Math.fma(-2.0f, _t3, (sd[0] + sd[0]) * sd[2]);
        return toMatrix3x4_sec42bb4e_1_fma(dest, sd, dd, _t0, _t4, _t5);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[1] * sd[1];
        float _t2 = sd[2] * sd[3];
        float _t3 = sd[1] * sd[3];
        float _t4 = sd[0] * sd[0];
        float _t5 = sd[1] * sd[2];
        float _t6 = ((1.0f) - (2.0f) * (sd[2] * sd[2]));
        dd[0] = ((_t6) - (2.0f) * (_t0));
        dd[1] = (((sd[0] + sd[0]) * sd[1]) - (2.0f) * (_t2));
        dd[2] = 2.0f * ((sd[0]) * (sd[2]) + (_t3));
        dd[3] = 2.0f * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7])));
        dd[4] = 2.0f * ((sd[0]) * (sd[1]) + (_t2));
        dd[5] = ((_t6) - (2.0f) * (_t4));
        dd[6] = ((_t5 + _t5) - (2.0f) * (sd[0] * sd[3]));
        dd[7] = 2.0f * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7])));
        dd[8] = (((sd[0] + sd[0]) * sd[2]) - (2.0f) * (_t3));
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
        dd[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        dd[11] = 2.0f * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7])));
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_fma(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_s188be2b9_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_s188be2b9_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s188be2b9_1_fma(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_s188be2b9_2_fma(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s188be2b9_1_mulAdd(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_s188be2b9_2_mulAdd(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s188be2b9_2_fma(float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83));
        float _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_s188be2b9_3_fma(dest, sd, dd, _t125, _t126, _t127, _t128, java.lang.Math.fma(sd[0], _t128, sd[3] * _t125) + java.lang.Math.fma(sd[1], _t126, -(sd[2] * _t127)), java.lang.Math.fma(sd[1], _t128, sd[2] * _t125) + java.lang.Math.fma(sd[3], _t127, -sd[0] * _t126), java.lang.Math.fma(sd[0], _t127, sd[3] * _t126) + java.lang.Math.fma(sd[2], _t128, -(sd[1] * _t125)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s188be2b9_2_mulAdd(float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83));
        float _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_s188be2b9_3_mulAdd(dest, sd, dd, _t125, _t126, _t127, _t128, ((sd[0]) * (_t128) + (sd[3] * _t125)) + ((sd[1]) * (_t126) - (sd[2] * _t127)), ((sd[1]) * (_t128) + (sd[2] * _t125)) + ((sd[3]) * (_t127) - (sd[0] * _t126)), ((sd[0]) * (_t127) + (sd[3] * _t126)) + ((sd[2]) * (_t128) - (sd[1] * _t125)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s188be2b9_3_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t125, float _t126, float _t127, float _t128, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t128, -sd[0] * _t125) - java.lang.Math.fma(sd[1], _t127, sd[2] * _t126);
        float _buf3 = java.lang.Math.fma(sd[4], _t128, sd[7] * _t125) + java.lang.Math.fma(sd[5], _t126, -(sd[6] * _t127));
        float _buf4 = java.lang.Math.fma(sd[5], _t128, sd[6] * _t125) + java.lang.Math.fma(sd[7], _t127, -sd[4] * _t126);
        float _buf5 = java.lang.Math.fma(sd[4], _t127, sd[7] * _t126) + java.lang.Math.fma(sd[6], _t128, -(sd[5] * _t125));
        dd[7] = java.lang.Math.fma(sd[7], _t128, -sd[4] * _t125) - java.lang.Math.fma(sd[5], _t127, sd[6] * _t126);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s188be2b9_3_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t125, float _t126, float _t127, float _t128, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t128) - (sd[0] * _t125)) - ((sd[1]) * (_t127) + (sd[2] * _t126));
        float _buf3 = ((sd[4]) * (_t128) + (sd[7] * _t125)) + ((sd[5]) * (_t126) - (sd[6] * _t127));
        float _buf4 = ((sd[5]) * (_t128) + (sd[6] * _t125)) + ((sd[7]) * (_t127) - (sd[4] * _t126));
        float _buf5 = ((sd[4]) * (_t127) + (sd[7] * _t126)) + ((sd[6]) * (_t128) - (sd[5] * _t125));
        dd[7] = ((sd[7]) * (_t128) - (sd[4] * _t125)) - ((sd[5]) * (_t127) + (sd[6] * _t126));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_sb91d4a88_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_sb91d4a88_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_1_fma(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_sb91d4a88_2_fma(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_1_mulAdd(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_sb91d4a88_2_mulAdd(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_2_fma(float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83));
        float _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        dd[0] = java.lang.Math.fma(sd[0], _t128, sd[3] * _t125) + java.lang.Math.fma(sd[1], _t126, -(sd[2] * _t127));
        dd[1] = java.lang.Math.fma(sd[1], _t128, sd[2] * _t125) + java.lang.Math.fma(sd[3], _t127, -sd[0] * _t126);
        return lookAlong_lh_sb91d4a88_3_fma(dest, sd, dd, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_2_mulAdd(float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83));
        float _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        dd[0] = ((sd[0]) * (_t128) + (sd[3] * _t125)) + ((sd[1]) * (_t126) - (sd[2] * _t127));
        dd[1] = ((sd[1]) * (_t128) + (sd[2] * _t125)) + ((sd[3]) * (_t127) - (sd[0] * _t126));
        return lookAlong_lh_sb91d4a88_3_mulAdd(dest, sd, dd, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t125, float _t126, float _t127, float _t128) {
        dd[2] = java.lang.Math.fma(sd[0], _t127, sd[3] * _t126) + java.lang.Math.fma(sd[2], _t128, -(sd[1] * _t125));
        dd[3] = java.lang.Math.fma(sd[3], _t128, -sd[0] * _t125) - java.lang.Math.fma(sd[1], _t127, sd[2] * _t126);
        dd[4] = java.lang.Math.fma(sd[4], _t128, sd[7] * _t125) + java.lang.Math.fma(sd[5], _t126, -(sd[6] * _t127));
        dd[5] = java.lang.Math.fma(sd[5], _t128, sd[6] * _t125) + java.lang.Math.fma(sd[7], _t127, -sd[4] * _t126);
        dd[6] = java.lang.Math.fma(sd[4], _t127, sd[7] * _t126) + java.lang.Math.fma(sd[6], _t128, -(sd[5] * _t125));
        dd[7] = java.lang.Math.fma(sd[7], _t128, -sd[4] * _t125) - java.lang.Math.fma(sd[5], _t127, sd[6] * _t126);
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sb91d4a88_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t125, float _t126, float _t127, float _t128) {
        dd[2] = ((sd[0]) * (_t127) + (sd[3] * _t126)) + ((sd[2]) * (_t128) - (sd[1] * _t125));
        dd[3] = ((sd[3]) * (_t128) - (sd[0] * _t125)) - ((sd[1]) * (_t127) + (sd[2] * _t126));
        dd[4] = ((sd[4]) * (_t128) + (sd[7] * _t125)) + ((sd[5]) * (_t126) - (sd[6] * _t127));
        dd[5] = ((sd[5]) * (_t128) + (sd[6] * _t125)) + ((sd[7]) * (_t127) - (sd[4] * _t126));
        dd[6] = ((sd[4]) * (_t127) + (sd[7] * _t126)) + ((sd[6]) * (_t128) - (sd[5] * _t125));
        dd[7] = ((sd[7]) * (_t128) - (sd[4] * _t125)) - ((sd[5]) * (_t127) + (sd[6] * _t126));
        return dest;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_se766d87a_1_fma(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t46, _t47, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_se766d87a_1_mulAdd(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t46, _t47, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t46, float _t47, float _t56, float _t57) {
        float _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_se766d87a_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t46, float _t47, float _t56, float _t57) {
        float _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_se766d87a_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_2_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        float _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_se766d87a_3_fma(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_2_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        float _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_se766d87a_3_mulAdd(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_3_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_s188be2b9_3_fma(dest, sd, dd, _t149, _t150, _t151, _t152, java.lang.Math.fma(sd[0], _t152, sd[3] * _t149) + java.lang.Math.fma(sd[1], _t150, -(sd[2] * _t151)), java.lang.Math.fma(sd[1], _t152, sd[2] * _t149) + java.lang.Math.fma(sd[3], _t151, -sd[0] * _t150), java.lang.Math.fma(sd[0], _t151, sd[3] * _t150) + java.lang.Math.fma(sd[2], _t152, -(sd[1] * _t149)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_se766d87a_3_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_s188be2b9_3_mulAdd(dest, sd, dd, _t149, _t150, _t151, _t152, ((sd[0]) * (_t152) + (sd[3] * _t149)) + ((sd[1]) * (_t150) - (sd[2] * _t151)), ((sd[1]) * (_t152) + (sd[2] * _t149)) + ((sd[3]) * (_t151) - (sd[0] * _t150)), ((sd[0]) * (_t151) + (sd[3] * _t150)) + ((sd[2]) * (_t152) - (sd[1] * _t149)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_sf72dad35_1_fma(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t46, _t47, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_sf72dad35_1_mulAdd(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t46, _t47, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t46, float _t47, float _t56, float _t57) {
        float _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sf72dad35_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t46, float _t47, float _t56, float _t57) {
        float _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sf72dad35_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_2_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        float _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_sf72dad35_3_fma(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_2_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        float _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_sf72dad35_3_mulAdd(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        dd[0] = java.lang.Math.fma(sd[0], _t152, sd[3] * _t149) + java.lang.Math.fma(sd[1], _t150, -(sd[2] * _t151));
        dd[1] = java.lang.Math.fma(sd[1], _t152, sd[2] * _t149) + java.lang.Math.fma(sd[3], _t151, -sd[0] * _t150);
        dd[2] = java.lang.Math.fma(sd[0], _t151, sd[3] * _t150) + java.lang.Math.fma(sd[2], _t152, -(sd[1] * _t149));
        return lookAlong_lh_degenerate_sf72dad35_4_fma(dest, sd, dd, _t149, _t150, _t151, _t152);
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        dd[0] = ((sd[0]) * (_t152) + (sd[3] * _t149)) + ((sd[1]) * (_t150) - (sd[2] * _t151));
        dd[1] = ((sd[1]) * (_t152) + (sd[2] * _t149)) + ((sd[3]) * (_t151) - (sd[0] * _t150));
        dd[2] = ((sd[0]) * (_t151) + (sd[3] * _t150)) + ((sd[2]) * (_t152) - (sd[1] * _t149));
        return lookAlong_lh_degenerate_sf72dad35_4_mulAdd(dest, sd, dd, _t149, _t150, _t151, _t152);
    }

    /**
     * Piece 5 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_4_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t149, float _t150, float _t151, float _t152) {
        dd[3] = java.lang.Math.fma(sd[3], _t152, -sd[0] * _t149) - java.lang.Math.fma(sd[1], _t151, sd[2] * _t150);
        dd[4] = java.lang.Math.fma(sd[4], _t152, sd[7] * _t149) + java.lang.Math.fma(sd[5], _t150, -(sd[6] * _t151));
        dd[5] = java.lang.Math.fma(sd[5], _t152, sd[6] * _t149) + java.lang.Math.fma(sd[7], _t151, -sd[4] * _t150);
        dd[6] = java.lang.Math.fma(sd[4], _t151, sd[7] * _t150) + java.lang.Math.fma(sd[6], _t152, -(sd[5] * _t149));
        dd[7] = java.lang.Math.fma(sd[7], _t152, -sd[4] * _t149) - java.lang.Math.fma(sd[5], _t151, sd[6] * _t150);
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sf72dad35_4_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t149, float _t150, float _t151, float _t152) {
        dd[3] = ((sd[3]) * (_t152) - (sd[0] * _t149)) - ((sd[1]) * (_t151) + (sd[2] * _t150));
        dd[4] = ((sd[4]) * (_t152) + (sd[7] * _t149)) + ((sd[5]) * (_t150) - (sd[6] * _t151));
        dd[5] = ((sd[5]) * (_t152) + (sd[6] * _t149)) + ((sd[7]) * (_t151) - (sd[4] * _t150));
        dd[6] = ((sd[4]) * (_t151) + (sd[7] * _t150)) + ((sd[6]) * (_t152) - (sd[5] * _t149));
        dd[7] = ((sd[7]) * (_t152) - (sd[4] * _t149)) - ((sd[5]) * (_t151) + (sd[6] * _t150));
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        float dirX = dirData[0];
        float dirY = dirData[1];
        float dirZ = dirData[2];
        float upX = upData[0];
        float upY = upData[1];
        float upZ = upData[2];
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_s2397163b_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        float dirX = dirData[0];
        float dirY = dirData[1];
        float dirZ = dirData[2];
        float upX = upData[0];
        float upY = upData[1];
        float upZ = upData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_sf41606f6_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_fma(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        return lookAlong_rh_s2397163b_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_s2397163b_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_s2397163b_1_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_s2397163b_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_s2397163b_1_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_s2397163b_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_s2397163b_2_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t16, float _t18, float _t20, float _t22, float _t38, float _t41, float _t47, float _t48, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81) {
        float _sp4 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81));
        float _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85));
        float _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_s2397163b_3_fma(dest, sd, dd, _t126, _t127, _t128, _t129, java.lang.Math.fma(sd[0], _t127, sd[3] * _t128) + java.lang.Math.fma(sd[1], _t129, -(sd[2] * _t126)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_s2397163b_2_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t16, float _t18, float _t20, float _t22, float _t38, float _t41, float _t47, float _t48, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81) {
        float _sp4 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81));
        float _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85));
        float _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_s2397163b_3_mulAdd(dest, sd, dd, _t126, _t127, _t128, _t129, ((sd[0]) * (_t127) + (sd[3] * _t128)) + ((sd[1]) * (_t129) - (sd[2] * _t126)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_s2397163b_3_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t126, float _t127, float _t128, float _t129, float _buf0) {
        float _buf1 = java.lang.Math.fma(sd[1], _t127, sd[2] * _t128) + java.lang.Math.fma(sd[3], _t126, -sd[0] * _t129);
        float _buf2 = java.lang.Math.fma(sd[0], _t126, sd[3] * _t129) + java.lang.Math.fma(sd[2], _t127, -(sd[1] * _t128));
        dd[3] = java.lang.Math.fma(sd[3], _t127, -sd[0] * _t128) - java.lang.Math.fma(sd[1], _t126, sd[2] * _t129);
        float _buf3 = java.lang.Math.fma(sd[4], _t127, sd[7] * _t128) + java.lang.Math.fma(sd[5], _t129, -(sd[6] * _t126));
        float _buf4 = java.lang.Math.fma(sd[5], _t127, sd[6] * _t128) + java.lang.Math.fma(sd[7], _t126, -sd[4] * _t129);
        float _buf5 = java.lang.Math.fma(sd[4], _t126, sd[7] * _t129) + java.lang.Math.fma(sd[6], _t127, -(sd[5] * _t128));
        dd[7] = java.lang.Math.fma(sd[7], _t127, -sd[4] * _t128) - java.lang.Math.fma(sd[5], _t126, sd[6] * _t129);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_s2397163b_3_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t126, float _t127, float _t128, float _t129, float _buf0) {
        float _buf1 = ((sd[1]) * (_t127) + (sd[2] * _t128)) + ((sd[3]) * (_t126) - (sd[0] * _t129));
        float _buf2 = ((sd[0]) * (_t126) + (sd[3] * _t129)) + ((sd[2]) * (_t127) - (sd[1] * _t128));
        dd[3] = ((sd[3]) * (_t127) - (sd[0] * _t128)) - ((sd[1]) * (_t126) + (sd[2] * _t129));
        float _buf3 = ((sd[4]) * (_t127) + (sd[7] * _t128)) + ((sd[5]) * (_t129) - (sd[6] * _t126));
        float _buf4 = ((sd[5]) * (_t127) + (sd[6] * _t128)) + ((sd[7]) * (_t126) - (sd[4] * _t129));
        float _buf5 = ((sd[4]) * (_t126) + (sd[7] * _t129)) + ((sd[6]) * (_t127) - (sd[5] * _t128));
        dd[7] = ((sd[7]) * (_t127) - (sd[4] * _t128)) - ((sd[5]) * (_t126) + (sd[6] * _t129));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        return lookAlong_rh_sf41606f6_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_sf41606f6_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_sf41606f6_1_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_sf41606f6_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_sf41606f6_1_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_sf41606f6_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sf41606f6_2_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t16, float _t18, float _t20, float _t22, float _t38, float _t41, float _t47, float _t48, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81) {
        float _sp4 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81));
        float _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85));
        float _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        dd[0] = java.lang.Math.fma(sd[0], _t127, sd[3] * _t128) + java.lang.Math.fma(sd[1], _t129, -(sd[2] * _t126));
        return lookAlong_rh_sf41606f6_3_fma(dest, sd, dd, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sf41606f6_2_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t16, float _t18, float _t20, float _t22, float _t38, float _t41, float _t47, float _t48, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81) {
        float _sp4 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81));
        float _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85));
        float _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        dd[0] = ((sd[0]) * (_t127) + (sd[3] * _t128)) + ((sd[1]) * (_t129) - (sd[2] * _t126));
        return lookAlong_rh_sf41606f6_3_mulAdd(dest, sd, dd, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sf41606f6_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t126, float _t127, float _t128, float _t129) {
        dd[1] = java.lang.Math.fma(sd[1], _t127, sd[2] * _t128) + java.lang.Math.fma(sd[3], _t126, -sd[0] * _t129);
        dd[2] = java.lang.Math.fma(sd[0], _t126, sd[3] * _t129) + java.lang.Math.fma(sd[2], _t127, -(sd[1] * _t128));
        dd[3] = java.lang.Math.fma(sd[3], _t127, -sd[0] * _t128) - java.lang.Math.fma(sd[1], _t126, sd[2] * _t129);
        dd[4] = java.lang.Math.fma(sd[4], _t127, sd[7] * _t128) + java.lang.Math.fma(sd[5], _t129, -(sd[6] * _t126));
        dd[5] = java.lang.Math.fma(sd[5], _t127, sd[6] * _t128) + java.lang.Math.fma(sd[7], _t126, -sd[4] * _t129);
        dd[6] = java.lang.Math.fma(sd[4], _t126, sd[7] * _t129) + java.lang.Math.fma(sd[6], _t127, -(sd[5] * _t128));
        dd[7] = java.lang.Math.fma(sd[7], _t127, -sd[4] * _t128) - java.lang.Math.fma(sd[5], _t126, sd[6] * _t129);
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sf41606f6_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t126, float _t127, float _t128, float _t129) {
        dd[1] = ((sd[1]) * (_t127) + (sd[2] * _t128)) + ((sd[3]) * (_t126) - (sd[0] * _t129));
        dd[2] = ((sd[0]) * (_t126) + (sd[3] * _t129)) + ((sd[2]) * (_t127) - (sd[1] * _t128));
        dd[3] = ((sd[3]) * (_t127) - (sd[0] * _t128)) - ((sd[1]) * (_t126) + (sd[2] * _t129));
        dd[4] = ((sd[4]) * (_t127) + (sd[7] * _t128)) + ((sd[5]) * (_t129) - (sd[6] * _t126));
        dd[5] = ((sd[5]) * (_t127) + (sd[6] * _t128)) + ((sd[7]) * (_t126) - (sd[4] * _t129));
        dd[6] = ((sd[4]) * (_t126) + (sd[7] * _t129)) + ((sd[6]) * (_t127) - (sd[5] * _t128));
        dd[7] = ((sd[7]) * (_t127) - (sd[4] * _t128)) - ((sd[5]) * (_t126) + (sd[6] * _t129));
        return dest;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_safe21078_1_fma(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_safe21078_1_mulAdd(dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_safe21078_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_safe21078_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_2_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_safe21078_3_fma(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_2_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_safe21078_3_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_3_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_degenerate_safe21078_4_fma(dest, sd, dd, _t149, _t150, _t151, _t152, java.lang.Math.fma(sd[0], _t150, sd[3] * _t151) + java.lang.Math.fma(sd[1], _t152, -(sd[2] * _t149)), java.lang.Math.fma(sd[1], _t150, sd[2] * _t151) + java.lang.Math.fma(sd[3], _t149, -sd[0] * _t152), java.lang.Math.fma(sd[0], _t149, sd[3] * _t152) + java.lang.Math.fma(sd[2], _t150, -(sd[1] * _t151)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_3_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_degenerate_safe21078_4_mulAdd(dest, sd, dd, _t149, _t150, _t151, _t152, ((sd[0]) * (_t150) + (sd[3] * _t151)) + ((sd[1]) * (_t152) - (sd[2] * _t149)), ((sd[1]) * (_t150) + (sd[2] * _t151)) + ((sd[3]) * (_t149) - (sd[0] * _t152)), ((sd[0]) * (_t149) + (sd[3] * _t152)) + ((sd[2]) * (_t150) - (sd[1] * _t151)));
    }

    /**
     * Piece 5 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_4_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t149, float _t150, float _t151, float _t152, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t150, -sd[0] * _t151) - java.lang.Math.fma(sd[1], _t149, sd[2] * _t152);
        float _buf3 = java.lang.Math.fma(sd[4], _t150, sd[7] * _t151) + java.lang.Math.fma(sd[5], _t152, -(sd[6] * _t149));
        float _buf4 = java.lang.Math.fma(sd[5], _t150, sd[6] * _t151) + java.lang.Math.fma(sd[7], _t149, -sd[4] * _t152);
        float _buf5 = java.lang.Math.fma(sd[4], _t149, sd[7] * _t152) + java.lang.Math.fma(sd[6], _t150, -(sd[5] * _t151));
        dd[7] = java.lang.Math.fma(sd[7], _t150, -sd[4] * _t151) - java.lang.Math.fma(sd[5], _t149, sd[6] * _t152);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_safe21078_4_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t149, float _t150, float _t151, float _t152, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t150) - (sd[0] * _t151)) - ((sd[1]) * (_t149) + (sd[2] * _t152));
        float _buf3 = ((sd[4]) * (_t150) + (sd[7] * _t151)) + ((sd[5]) * (_t152) - (sd[6] * _t149));
        float _buf4 = ((sd[5]) * (_t150) + (sd[6] * _t151)) + ((sd[7]) * (_t149) - (sd[4] * _t152));
        float _buf5 = ((sd[4]) * (_t149) + (sd[7] * _t152)) + ((sd[6]) * (_t150) - (sd[5] * _t151));
        dd[7] = ((sd[7]) * (_t150) - (sd[4] * _t151)) - ((sd[5]) * (_t149) + (sd[6] * _t152));
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
    private DoubleDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_s94f46f77_1_fma(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_s94f46f77_1_mulAdd(dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s94f46f77_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s94f46f77_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_2_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s94f46f77_3_fma(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_2_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s94f46f77_3_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_3_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        dd[0] = java.lang.Math.fma(sd[0], _t150, sd[3] * _t151) + java.lang.Math.fma(sd[1], _t152, -(sd[2] * _t149));
        dd[1] = java.lang.Math.fma(sd[1], _t150, sd[2] * _t151) + java.lang.Math.fma(sd[3], _t149, -sd[0] * _t152);
        dd[2] = java.lang.Math.fma(sd[0], _t149, sd[3] * _t152) + java.lang.Math.fma(sd[2], _t150, -(sd[1] * _t151));
        return lookAlong_rh_degenerate_s94f46f77_4_fma(dest, sd, dd, _t149, _t150, _t151, _t152);
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_3_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        dd[0] = ((sd[0]) * (_t150) + (sd[3] * _t151)) + ((sd[1]) * (_t152) - (sd[2] * _t149));
        dd[1] = ((sd[1]) * (_t150) + (sd[2] * _t151)) + ((sd[3]) * (_t149) - (sd[0] * _t152));
        dd[2] = ((sd[0]) * (_t149) + (sd[3] * _t152)) + ((sd[2]) * (_t150) - (sd[1] * _t151));
        return lookAlong_rh_degenerate_s94f46f77_4_mulAdd(dest, sd, dd, _t149, _t150, _t151, _t152);
    }

    /**
     * Piece 5 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_4_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t149, float _t150, float _t151, float _t152) {
        dd[3] = java.lang.Math.fma(sd[3], _t150, -sd[0] * _t151) - java.lang.Math.fma(sd[1], _t149, sd[2] * _t152);
        dd[4] = java.lang.Math.fma(sd[4], _t150, sd[7] * _t151) + java.lang.Math.fma(sd[5], _t152, -(sd[6] * _t149));
        dd[5] = java.lang.Math.fma(sd[5], _t150, sd[6] * _t151) + java.lang.Math.fma(sd[7], _t149, -sd[4] * _t152);
        dd[6] = java.lang.Math.fma(sd[4], _t149, sd[7] * _t152) + java.lang.Math.fma(sd[6], _t150, -(sd[5] * _t151));
        dd[7] = java.lang.Math.fma(sd[7], _t150, -sd[4] * _t151) - java.lang.Math.fma(sd[5], _t149, sd[6] * _t152);
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s94f46f77_4_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t149, float _t150, float _t151, float _t152) {
        dd[3] = ((sd[3]) * (_t150) - (sd[0] * _t151)) - ((sd[1]) * (_t149) + (sd[2] * _t152));
        dd[4] = ((sd[4]) * (_t150) + (sd[7] * _t151)) + ((sd[5]) * (_t152) - (sd[6] * _t149));
        dd[5] = ((sd[5]) * (_t150) + (sd[6] * _t151)) + ((sd[7]) * (_t149) - (sd[4] * _t152));
        dd[6] = ((sd[4]) * (_t149) + (sd[7] * _t152)) + ((sd[6]) * (_t150) - (sd[5] * _t151));
        dd[7] = ((sd[7]) * (_t150) - (sd[4] * _t151)) - ((sd[5]) * (_t149) + (sd[6] * _t152));
        return dest;
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(Float3R dir, Float3R up, Handedness handedness, @Mutated FloatDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, dest) : lookAlong_lh_mulAdd(dir, up, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(Float3R dir, Float3R up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up) : lookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(Float3R dir, Float3R up, Handedness handedness, @Mutated DoubleDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, dest) : lookAlong_lh_mulAdd(dir, up, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness, @Mutated FloatDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
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
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness, @Mutated DoubleDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(Float3R dir, Float3R up) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    @Mutated public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    public DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_s86c3d625_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_s86c3d625_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s86c3d625_1_fma(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _ct0) {
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_s188be2b9_2_fma(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s86c3d625_1_mulAdd(float dirX, float dirY, float dirZ, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _ct0) {
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_s188be2b9_2_mulAdd(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_s14af5ee4_1_fma(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_s14af5ee4_1_mulAdd(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_s14af5ee4_1_fma(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _ct0) {
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_sb91d4a88_2_fma(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_s14af5ee4_1_mulAdd(float dirX, float dirY, float dirZ, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _ct0) {
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_sb91d4a88_2_mulAdd(dirZ, dest, sd, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        return lookAlong_lh_degenerate_sfbedf00_1_fma(epsilon, dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        return lookAlong_lh_degenerate_sfbedf00_1_mulAdd(epsilon, dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_sfbedf00_1_fma(float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        float _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_se766d87a_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_sfbedf00_1_mulAdd(float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        float _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_se766d87a_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        return lookAlong_lh_degenerate_sf447b18f_1_fma(epsilon, dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        return lookAlong_lh_degenerate_sf447b18f_1_mulAdd(epsilon, dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sf447b18f_1_fma(float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        float _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sf72dad35_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sf447b18f_1_mulAdd(float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        float _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sf72dad35_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated FloatDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return lookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        return lookAlong_rh_s448873f_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_s448873f_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_s448873f_1_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_s2397163b_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_s448873f_1_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_s2397163b_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatDualQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        return lookAlong_rh_sca70a58a_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_sca70a58a_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_sca70a58a_1_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_sf41606f6_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_sca70a58a_1_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t0, float _t8, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _ct0) {
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_sf41606f6_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_s81656f2e_1_fma(epsilon, dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_s81656f2e_1_mulAdd(epsilon, dest, this.data, ((FloatDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s81656f2e_1_fma(float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_safe21078_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s81656f2e_1_mulAdd(float epsilon, FloatDualQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_safe21078_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_s5b83f209_1_fma(epsilon, dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_s5b83f209_1_mulAdd(epsilon, dest, this.data, ((DoubleDualQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s5b83f209_1_fma(float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s94f46f77_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s5b83f209_1_mulAdd(float epsilon, DoubleDualQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s94f46f77_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness, @Mutated FloatDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon, dest) : lookAlong_lh_mulAdd(dir, up, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon) : lookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness, @Mutated DoubleDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon, dest) : lookAlong_lh_mulAdd(dir, up, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness, @Mutated FloatDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness, @Mutated DoubleDualQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(Float3R dir, Float3R up, float epsilon, @Mutated FloatDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(Float3R dir, Float3R up, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(Float3R dir, Float3R up, float epsilon, @Mutated DoubleDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleDualQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationAxis(float angle, Float3R axis) {
        float[] axisData = ((Float3Impl) axis).data;
        float axisX = axisData[0];
        float axisY = axisData[1];
        float axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosWithSin(_t0);
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
    @Mutated public FloatDualQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosWithSin(_t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_fma(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_mulAdd(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_lh_s96c6b39a_1_fma(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _t45, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _t45, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_1_fma(float dirX, float dirY, float dirZ, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45, float _t46, float _t47, float _t48) {
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s96c6b39a_2_fma(dirZ, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(float dirX, float dirY, float dirZ, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45, float _t46, float _t47, float _t48) {
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s96c6b39a_2_mulAdd(dirZ, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_2_fma(float dirZ, float[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t55, float _t62, float _t68, float _t69, float _t73, float _t74, float _t77, float _sp1, float _t79, float _t80, float _t81, float _sp3, float _sp4, float _sp2) {
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0f) {
            dd[0] = _sp1 * _t69;
            dd[1] = _sp1 * _t55;
            dd[2] = _sp1 * _t74;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t79);
                dd[1] = _sp4 * _t73;
                dd[2] = _sp4 * _t50;
                dd[3] = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp2 * _t73;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    dd[2] = _sp2 * _t68;
                    dd[3] = _sp2 * _t55;
                } else {
                    dd[0] = _sp3 * _t50;
                    dd[1] = _sp3 * _t68;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    dd[3] = _sp3 * _t74;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_2_mulAdd(float dirZ, float[] dd, float _t15, float _t16, float _t36, float _t45, float _t46, float _t50, float _t55, float _t62, float _t68, float _t69, float _t73, float _t74, float _t77, float _sp1, float _t79, float _t80, float _t81, float _sp3, float _sp4, float _sp2) {
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0f) {
            dd[0] = _sp1 * _t69;
            dd[1] = _sp1 * _t55;
            dd[2] = _sp1 * _t74;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t79);
                dd[1] = _sp4 * _t73;
                dd[2] = _sp4 * _t50;
                dd[3] = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp2 * _t73;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    dd[2] = _sp2 * _t68;
                    dd[3] = _sp2 * _t55;
                } else {
                    dd[0] = _sp3 * _t50;
                    dd[1] = _sp3 * _t68;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    dd[3] = _sp3 * _t74;
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
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(float[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp0) {
        float _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3_fma(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(float[] dd, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp0) {
        float _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3_mulAdd(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3_fma(float[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t78;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t74;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t78;
                } else {
                    dd[0] = _sp2 * _t74;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3_mulAdd(float[] dd, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t78;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t74;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t78;
                } else {
                    dd[0] = _sp2 * _t74;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_fma(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_mulAdd(Float3R dir, Float3R up) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2]);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_rh_s1465dddc_1_fma(dirX, dirY, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, _t47, _t39 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_rh_s1465dddc_1_mulAdd(dirX, dirY, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, _t47, _t39 * _t47);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_s1465dddc_1_fma(float dirX, float dirY, float[] dd, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _t47, float _t48) {
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return makeRotationLookAlong_rh_s1465dddc_2_fma(dd, _t0, _t16, _t21, _t38, _t47, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_s1465dddc_1_mulAdd(float dirX, float dirY, float[] dd, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _t47, float _t48) {
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return makeRotationLookAlong_rh_s1465dddc_2_mulAdd(dd, _t0, _t16, _t21, _t38, _t47, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_s1465dddc_2_fma(float[] dd, float _t0, float _t16, float _t21, float _t38, float _t47, float _t49, float _t52, float _t56, float _t64, float _t70, float _t71, float _t74, float _t75, float _t78, float _t79, float _t80, float _sp4, float _sp1, float _sp2, float _t84, float _sp3) {
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0f) {
            dd[0] = _sp1 * _t71;
            dd[1] = _sp1 * _t52;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t78);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t56;
                dd[3] = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    dd[2] = _sp2 * _t70;
                    dd[3] = _sp2 * _t52;
                } else {
                    dd[0] = _sp3 * _t56;
                    dd[1] = _sp3 * _t70;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_s1465dddc_2_mulAdd(float[] dd, float _t0, float _t16, float _t21, float _t38, float _t47, float _t49, float _t52, float _t56, float _t64, float _t70, float _t71, float _t74, float _t75, float _t78, float _t79, float _t80, float _sp4, float _sp1, float _sp2, float _t84, float _sp3) {
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0f) {
            dd[0] = _sp1 * _t71;
            dd[1] = _sp1 * _t52;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t78);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t56;
                dd[3] = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    dd[2] = _sp2 * _t70;
                    dd[3] = _sp2 * _t52;
                } else {
                    dd[0] = _sp3 * _t56;
                    dd[1] = _sp3 * _t70;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t84);
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
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(float[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_rh_degenerate_s57efa25_3_fma(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(float[] dd, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_rh_degenerate_s57efa25_3_mulAdd(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_3_fma(float[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t74;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t76;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t74;
                } else {
                    dd[0] = _sp2 * _t76;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_3_mulAdd(float[] dd, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t74;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                dd[0] = 0.5f * (float) java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t76;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t74;
                } else {
                    dd[0] = _sp2 * _t76;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along {@code dir} (the
     * rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(Float3R dir, Float3R up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up) : makeRotationLookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along {@code dir} (the
     * rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(Float3R dir, Float3R up) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    @Mutated public FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_fma(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_lh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_lh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float[] dd = this.data;
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_lh_s6268dd16_1_fma(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _t45, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float[] dd = this.data;
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return makeRotationLookAlong_lh_s6268dd16_1_mulAdd(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _t45, _t36 * _t45, _t37 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_s6268dd16_1_fma(float dirX, float dirY, float dirZ, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45, float _t46, float _t47) {
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s96c6b39a_2_fma(dirZ, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_s6268dd16_1_mulAdd(float dirX, float dirY, float dirZ, float[] dd, float _t0, float _t15, float _t16, float _t17, float _t18, float _t19, float _t21, float _t36, float _t37, float _t38, float _t45, float _t46, float _t47) {
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s96c6b39a_2_mulAdd(dirZ, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(float epsilon, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(float epsilon, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_fma(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_rh_fma(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        float[] dirData = ((Float3Impl) dir).data;
        float[] upData = ((Float3Impl) up).data;
        return makeRotationLookAlong_rh_mulAdd(dirData[0], dirData[1], dirData[2], upData[0], upData[1], upData[2], epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float[] dd = this.data;
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_s2009b560_1_fma(dirX, dirY, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), _t38, _t39, _t40, -_t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float[] dd = this.data;
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_s2009b560_1_mulAdd(dirX, dirY, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), _t38, _t39, _t40, -_t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_s2009b560_1_fma(float dirX, float dirY, float[] dd, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _t47) {
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return makeRotationLookAlong_rh_s1465dddc_2_fma(dd, _t0, _t16, _t21, _t38, _t47, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_s2009b560_1_mulAdd(float dirX, float dirY, float[] dd, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t38, float _t39, float _t40, float _t41, float _t47) {
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return makeRotationLookAlong_rh_s1465dddc_2_mulAdd(dd, _t0, _t16, _t21, _t38, _t47, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(float epsilon, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(float epsilon, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along {@code dir} (the
     * rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up, epsilon) : makeRotationLookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along {@code dir} (the
     * rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(Float3R dir, Float3R up, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Set this dual quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

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
        dd[0] = Math.sinWithCos(_t0);
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = Math.cosWithSin(_t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
     * radians about the X, Y and Z axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the fixed Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        dd[2] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[3] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationXYZ_mulAdd(float angleX, float angleY, float angleZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[2] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[3] = ((_t12) * (_t5) - (_t11 * _t8));
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
     * radians about the X, Z and Y axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the fixed Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[1] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[2] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[3] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationXZY_mulAdd(float angleX, float angleZ, float angleY) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[2] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[3] = ((_t12) * (_t8) + (_t11 * _t5));
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
    @Mutated public FloatDualQuat makeRotationY(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        dd[0] = 0.0f;
        dd[1] = Math.sinWithCos(_t0);
        dd[2] = 0.0f;
        dd[3] = Math.cosWithSin(_t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
     * radians about the Y, X and Z axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the fixed Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[2] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[3] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationYXZ_mulAdd(float angleY, float angleX, float angleZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[2] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[3] = ((_t12) * (_t8) + (_t11 * _t5));
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
     * radians about the Y, Z and X axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the fixed X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[2] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[3] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationYZX_mulAdd(float angleY, float angleZ, float angleX) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[2] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[3] = ((_t10) * (_t5) - (_t9 * _t8));
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
    @Mutated public FloatDualQuat makeRotationZ(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = Math.sinWithCos(_t0);
        dd[3] = Math.cosWithSin(_t0);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
     * radians about the Z, X and Y axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the fixed Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        dd[1] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[2] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[3] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationZXY_mulAdd(float angleZ, float angleX, float angleY) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[2] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[3] = ((_t12) * (_t5) - (_t11 * _t8));
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
     * radians about the Z, Y and X axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the fixed X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public FloatDualQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        dd[0] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[1] = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        dd[2] = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[3] = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        VEC_2.intoArray(dd, 4);
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat makeRotationZYX_mulAdd(float angleZ, float angleY, float angleX) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t8) + (_t12 * _t5));
        dd[2] = ((_t12) * (_t8) - (_t11 * _t5));
        dd[3] = ((_t10) * (_t5) + (_t9 * _t8));
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
    public FloatDualQuat preRotate(FloatQuatR rotation, @Mutated FloatDualQuat dest) {
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        return preRotate(rotationData[0], rotationData[1], rotationData[2], rotationData[3], dest);
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (org.joml2.internal.FmaSwitch.ON) return preRotate_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return preRotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return preRotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _buf0 = java.lang.Math.fma(rotationX, sd[3], rotationW * sd[0]) + java.lang.Math.fma(rotationY, sd[2], -rotationZ * sd[1]);
        float _buf1 = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[0]) + java.lang.Math.fma(rotationW, sd[1], -rotationX * sd[2]);
        float _buf2 = java.lang.Math.fma(rotationX, sd[1], rotationW * sd[2]) + java.lang.Math.fma(rotationZ, sd[3], -rotationY * sd[0]);
        dd[3] = java.lang.Math.fma(rotationW, sd[3], -rotationX * sd[0]) - java.lang.Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        return preRotate_s1c063002_1_fma(rotationX, rotationY, rotationZ, rotationW, dest, sd, dd, _buf0, _buf1, _buf2, java.lang.Math.fma(rotationX, sd[7], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], -rotationZ * sd[5]), java.lang.Math.fma(rotationY, sd[7], rotationZ * sd[4]) + java.lang.Math.fma(rotationW, sd[5], -rotationX * sd[6]), java.lang.Math.fma(rotationX, sd[5], rotationW * sd[6]) + java.lang.Math.fma(rotationZ, sd[7], -rotationY * sd[4]));
    }

    /** {@code preRotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat preRotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _buf0 = ((rotationX) * (sd[3]) + (rotationW * sd[0])) + ((rotationY) * (sd[2]) - (rotationZ * sd[1]));
        float _buf1 = ((rotationY) * (sd[3]) + (rotationZ * sd[0])) + ((rotationW) * (sd[1]) - (rotationX * sd[2]));
        float _buf2 = ((rotationX) * (sd[1]) + (rotationW * sd[2])) + ((rotationZ) * (sd[3]) - (rotationY * sd[0]));
        dd[3] = ((rotationW) * (sd[3]) - (rotationX * sd[0])) - ((rotationY) * (sd[1]) + (rotationZ * sd[2]));
        float _buf3 = ((rotationX) * (sd[7]) + (rotationW * sd[4])) + ((rotationY) * (sd[6]) - (rotationZ * sd[5]));
        float _buf4 = ((rotationY) * (sd[7]) + (rotationZ * sd[4])) + ((rotationW) * (sd[5]) - (rotationX * sd[6]));
        float _buf5 = ((rotationX) * (sd[5]) + (rotationW * sd[6])) + ((rotationZ) * (sd[7]) - (rotationY * sd[4]));
        dd[7] = ((rotationW) * (sd[7]) - (rotationX * sd[4])) - ((rotationY) * (sd[5]) + (rotationZ * sd[6]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Piece 2 of {@code preRotate}, split to fit the inline budget. Shared by the identical private
     * paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private FloatDualQuat preRotate_s1c063002_1_fma(float rotationX, float rotationY, float rotationZ, float rotationW, FloatDualQuat dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5) {
        dd[7] = java.lang.Math.fma(rotationW, sd[7], -rotationX * sd[4]) - java.lang.Math.fma(rotationY, sd[5], rotationZ * sd[6]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (org.joml2.internal.FmaSwitch.ON) return preRotate_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return preRotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
    }

    /** {@code preRotate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preRotate_fma(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(rotationX, sd[3], rotationW * sd[0]) + java.lang.Math.fma(rotationY, sd[2], -rotationZ * sd[1]);
        dd[1] = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[0]) + java.lang.Math.fma(rotationW, sd[1], -rotationX * sd[2]);
        dd[2] = java.lang.Math.fma(rotationX, sd[1], rotationW * sd[2]) + java.lang.Math.fma(rotationZ, sd[3], -rotationY * sd[0]);
        dd[3] = java.lang.Math.fma(rotationW, sd[3], -rotationX * sd[0]) - java.lang.Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        dd[4] = java.lang.Math.fma(rotationX, sd[7], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], -rotationZ * sd[5]);
        dd[5] = java.lang.Math.fma(rotationY, sd[7], rotationZ * sd[4]) + java.lang.Math.fma(rotationW, sd[5], -rotationX * sd[6]);
        dd[6] = java.lang.Math.fma(rotationX, sd[5], rotationW * sd[6]) + java.lang.Math.fma(rotationZ, sd[7], -rotationY * sd[4]);
        dd[7] = java.lang.Math.fma(rotationW, sd[7], -rotationX * sd[4]) - java.lang.Math.fma(rotationY, sd[5], rotationZ * sd[6]);
        return dest;
    }

    /** {@code preRotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preRotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = ((rotationX) * (sd[3]) + (rotationW * sd[0])) + ((rotationY) * (sd[2]) - (rotationZ * sd[1]));
        dd[1] = ((rotationY) * (sd[3]) + (rotationZ * sd[0])) + ((rotationW) * (sd[1]) - (rotationX * sd[2]));
        dd[2] = ((rotationX) * (sd[1]) + (rotationW * sd[2])) + ((rotationZ) * (sd[3]) - (rotationY * sd[0]));
        dd[3] = ((rotationW) * (sd[3]) - (rotationX * sd[0])) - ((rotationY) * (sd[1]) + (rotationZ * sd[2]));
        dd[4] = ((rotationX) * (sd[7]) + (rotationW * sd[4])) + ((rotationY) * (sd[6]) - (rotationZ * sd[5]));
        dd[5] = ((rotationY) * (sd[7]) + (rotationZ * sd[4])) + ((rotationW) * (sd[5]) - (rotationX * sd[6]));
        dd[6] = ((rotationX) * (sd[5]) + (rotationW * sd[6])) + ((rotationZ) * (sd[7]) - (rotationY * sd[4]));
        dd[7] = ((rotationW) * (sd[7]) - (rotationX * sd[4])) - ((rotationY) * (sd[5]) + (rotationZ * sd[6]));
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        return rotate(rotationData[0], rotationData[1], rotationData[2], rotationData[3], dest);
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (org.joml2.internal.FmaSwitch.ON) return rotate_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _buf0 = java.lang.Math.fma(rotationX, sd[3], rotationW * sd[0]) + java.lang.Math.fma(rotationZ, sd[1], -rotationY * sd[2]);
        float _buf1 = java.lang.Math.fma(rotationX, sd[2], rotationW * sd[1]) + java.lang.Math.fma(rotationY, sd[3], -rotationZ * sd[0]);
        float _buf2 = java.lang.Math.fma(rotationY, sd[0], rotationZ * sd[3]) + java.lang.Math.fma(rotationW, sd[2], -rotationX * sd[1]);
        dd[3] = java.lang.Math.fma(rotationW, sd[3], -rotationX * sd[0]) - java.lang.Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        return preRotate_s1c063002_1_fma(rotationX, rotationY, rotationZ, rotationW, dest, sd, dd, _buf0, _buf1, _buf2, java.lang.Math.fma(rotationX, sd[7], rotationW * sd[4]) + java.lang.Math.fma(rotationZ, sd[5], -rotationY * sd[6]), java.lang.Math.fma(rotationX, sd[6], rotationW * sd[5]) + java.lang.Math.fma(rotationY, sd[7], -rotationZ * sd[4]), java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[7]) + java.lang.Math.fma(rotationW, sd[6], -rotationX * sd[5]));
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _buf0 = ((rotationX) * (sd[3]) + (rotationW * sd[0])) + ((rotationZ) * (sd[1]) - (rotationY * sd[2]));
        float _buf1 = ((rotationX) * (sd[2]) + (rotationW * sd[1])) + ((rotationY) * (sd[3]) - (rotationZ * sd[0]));
        float _buf2 = ((rotationY) * (sd[0]) + (rotationZ * sd[3])) + ((rotationW) * (sd[2]) - (rotationX * sd[1]));
        dd[3] = ((rotationW) * (sd[3]) - (rotationX * sd[0])) - ((rotationY) * (sd[1]) + (rotationZ * sd[2]));
        float _buf3 = ((rotationX) * (sd[7]) + (rotationW * sd[4])) + ((rotationZ) * (sd[5]) - (rotationY * sd[6]));
        float _buf4 = ((rotationX) * (sd[6]) + (rotationW * sd[5])) + ((rotationY) * (sd[7]) - (rotationZ * sd[4]));
        float _buf5 = ((rotationY) * (sd[4]) + (rotationZ * sd[7])) + ((rotationW) * (sd[6]) - (rotationX * sd[5]));
        dd[7] = ((rotationW) * (sd[7]) - (rotationX * sd[4])) - ((rotationY) * (sd[5]) + (rotationZ * sd[6]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (org.joml2.internal.FmaSwitch.ON) return rotate_fma(rotationX, rotationY, rotationZ, rotationW, dest);
        return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
    }

    /** {@code rotate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotate_fma(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = java.lang.Math.fma(rotationX, sd[3], rotationW * sd[0]) + java.lang.Math.fma(rotationZ, sd[1], -rotationY * sd[2]);
        dd[1] = java.lang.Math.fma(rotationX, sd[2], rotationW * sd[1]) + java.lang.Math.fma(rotationY, sd[3], -rotationZ * sd[0]);
        dd[2] = java.lang.Math.fma(rotationY, sd[0], rotationZ * sd[3]) + java.lang.Math.fma(rotationW, sd[2], -rotationX * sd[1]);
        dd[3] = java.lang.Math.fma(rotationW, sd[3], -rotationX * sd[0]) - java.lang.Math.fma(rotationY, sd[1], rotationZ * sd[2]);
        dd[4] = java.lang.Math.fma(rotationX, sd[7], rotationW * sd[4]) + java.lang.Math.fma(rotationZ, sd[5], -rotationY * sd[6]);
        dd[5] = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[5]) + java.lang.Math.fma(rotationY, sd[7], -rotationZ * sd[4]);
        dd[6] = java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[7]) + java.lang.Math.fma(rotationW, sd[6], -rotationX * sd[5]);
        dd[7] = java.lang.Math.fma(rotationW, sd[7], -rotationX * sd[4]) - java.lang.Math.fma(rotationY, sd[5], rotationZ * sd[6]);
        return dest;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = ((rotationX) * (sd[3]) + (rotationW * sd[0])) + ((rotationZ) * (sd[1]) - (rotationY * sd[2]));
        dd[1] = ((rotationX) * (sd[2]) + (rotationW * sd[1])) + ((rotationY) * (sd[3]) - (rotationZ * sd[0]));
        dd[2] = ((rotationY) * (sd[0]) + (rotationZ * sd[3])) + ((rotationW) * (sd[2]) - (rotationX * sd[1]));
        dd[3] = ((rotationW) * (sd[3]) - (rotationX * sd[0])) - ((rotationY) * (sd[1]) + (rotationZ * sd[2]));
        dd[4] = ((rotationX) * (sd[7]) + (rotationW * sd[4])) + ((rotationZ) * (sd[5]) - (rotationY * sd[6]));
        dd[5] = ((rotationX) * (sd[6]) + (rotationW * sd[5])) + ((rotationY) * (sd[7]) - (rotationZ * sd[4]));
        dd[6] = ((rotationY) * (sd[4]) + (rotationZ * sd[7])) + ((rotationW) * (sd[6]) - (rotationX * sd[5]));
        dd[7] = ((rotationW) * (sd[7]) - (rotationX * sd[4])) - ((rotationY) * (sd[5]) + (rotationZ * sd[6]));
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
        float[] axisData = ((Float3Impl) axis).data;
        return rotateAxis(angle, axisData[0], axisData[1], axisData[2], dest);
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
        float[] axisData = ((Float3Impl) axis).data;
        return rotateAxis(angle, axisData[0], axisData[1], axisData[2], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        return rotateAxis_s52b79d91_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t1, _t3, _t4, _t5, java.lang.Math.fma(sd[0], _t1, sd[3] * _t3) + java.lang.Math.fma(sd[1], _t4, -(sd[2] * _t5)), java.lang.Math.fma(sd[1], _t1, sd[2] * _t3) + java.lang.Math.fma(sd[3], _t5, -sd[0] * _t4), java.lang.Math.fma(sd[0], _t5, sd[3] * _t4) + java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t3)));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        return rotateAxis_s52b79d91_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t1, _t3, _t4, _t5, ((sd[0]) * (_t1) + (sd[3] * _t3)) + ((sd[1]) * (_t4) - (sd[2] * _t5)), ((sd[1]) * (_t1) + (sd[2] * _t3)) + ((sd[3]) * (_t5) - (sd[0] * _t4)), ((sd[0]) * (_t5) + (sd[3] * _t4)) + ((sd[2]) * (_t1) - (sd[1] * _t3)));
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private FloatDualQuat rotateAxis_s52b79d91_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t1, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t1, -sd[0] * _t3) - java.lang.Math.fma(sd[1], _t5, sd[2] * _t4);
        float _buf3 = java.lang.Math.fma(sd[4], _t1, sd[7] * _t3) + java.lang.Math.fma(sd[5], _t4, -(sd[6] * _t5));
        float _buf4 = java.lang.Math.fma(sd[5], _t1, sd[6] * _t3) + java.lang.Math.fma(sd[7], _t5, -sd[4] * _t4);
        float _buf5 = java.lang.Math.fma(sd[4], _t5, sd[7] * _t4) + java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t3));
        dd[7] = java.lang.Math.fma(sd[7], _t1, -sd[4] * _t3) - java.lang.Math.fma(sd[5], _t5, sd[6] * _t4);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private FloatDualQuat rotateAxis_s52b79d91_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t1, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t3)) - ((sd[1]) * (_t5) + (sd[2] * _t4));
        float _buf3 = ((sd[4]) * (_t1) + (sd[7] * _t3)) + ((sd[5]) * (_t4) - (sd[6] * _t5));
        float _buf4 = ((sd[5]) * (_t1) + (sd[6] * _t3)) + ((sd[7]) * (_t5) - (sd[4] * _t4));
        float _buf5 = ((sd[4]) * (_t5) + (sd[7] * _t4)) + ((sd[6]) * (_t1) - (sd[5] * _t3));
        dd[7] = ((sd[7]) * (_t1) - (sd[4] * _t3)) - ((sd[5]) * (_t5) + (sd[6] * _t4));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = java.lang.Math.fma(sd[0], _t1, sd[3] * _t3) + java.lang.Math.fma(sd[1], _t4, -(sd[2] * _t5));
        dd[1] = java.lang.Math.fma(sd[1], _t1, sd[2] * _t3) + java.lang.Math.fma(sd[3], _t5, -sd[0] * _t4);
        dd[2] = java.lang.Math.fma(sd[0], _t5, sd[3] * _t4) + java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t3));
        return rotateAxis_sffd823f0_1_fma(dest, sd, dd, _t1, _t3, _t4, _t5);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = ((sd[0]) * (_t1) + (sd[3] * _t3)) + ((sd[1]) * (_t4) - (sd[2] * _t5));
        dd[1] = ((sd[1]) * (_t1) + (sd[2] * _t3)) + ((sd[3]) * (_t5) - (sd[0] * _t4));
        dd[2] = ((sd[0]) * (_t5) + (sd[3] * _t4)) + ((sd[2]) * (_t1) - (sd[1] * _t3));
        return rotateAxis_sffd823f0_1_mulAdd(dest, sd, dd, _t1, _t3, _t4, _t5);
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleDualQuat rotateAxis_sffd823f0_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t1, float _t3, float _t4, float _t5) {
        dd[3] = java.lang.Math.fma(sd[3], _t1, -sd[0] * _t3) - java.lang.Math.fma(sd[1], _t5, sd[2] * _t4);
        dd[4] = java.lang.Math.fma(sd[4], _t1, sd[7] * _t3) + java.lang.Math.fma(sd[5], _t4, -(sd[6] * _t5));
        dd[5] = java.lang.Math.fma(sd[5], _t1, sd[6] * _t3) + java.lang.Math.fma(sd[7], _t5, -sd[4] * _t4);
        dd[6] = java.lang.Math.fma(sd[4], _t5, sd[7] * _t4) + java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t3));
        dd[7] = java.lang.Math.fma(sd[7], _t1, -sd[4] * _t3) - java.lang.Math.fma(sd[5], _t5, sd[6] * _t4);
        return dest;
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleDualQuat rotateAxis_sffd823f0_1_mulAdd(DoubleDualQuat dest, float[] sd, double[] dd, float _t1, float _t3, float _t4, float _t5) {
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t3)) - ((sd[1]) * (_t5) + (sd[2] * _t4));
        dd[4] = ((sd[4]) * (_t1) + (sd[7] * _t3)) + ((sd[5]) * (_t4) - (sd[6] * _t5));
        dd[5] = ((sd[5]) * (_t1) + (sd[6] * _t3)) + ((sd[7]) * (_t5) - (sd[4] * _t4));
        dd[6] = ((sd[4]) * (_t5) + (sd[7] * _t4)) + ((sd[6]) * (_t1) - (sd[5] * _t3));
        dd[7] = ((sd[7]) * (_t1) - (sd[4] * _t3)) - ((sd[5]) * (_t5) + (sd[6] * _t4));
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
    public FloatDualQuat rotateX(float angle, @Mutated FloatDualQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateX_mulAdd(angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = java.lang.Math.fma(sd[0], _t1, sd[3] * _t2);
        float _buf1 = java.lang.Math.fma(sd[1], _t1, sd[2] * _t2);
        dd[2] = java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t2));
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[0] * _t2));
        float _buf2 = java.lang.Math.fma(sd[4], _t1, sd[7] * _t2);
        float _buf3 = java.lang.Math.fma(sd[5], _t1, sd[6] * _t2);
        dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t2));
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[4] * _t2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        return dest;
    }

    /** {@code rotateX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateX_mulAdd(float angle, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = ((sd[0]) * (_t1) + (sd[3] * _t2));
        float _buf1 = ((sd[1]) * (_t1) + (sd[2] * _t2));
        dd[2] = ((sd[2]) * (_t1) - (sd[1] * _t2));
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t2));
        float _buf2 = ((sd[4]) * (_t1) + (sd[7] * _t2));
        float _buf3 = ((sd[5]) * (_t1) + (sd[6] * _t2));
        dd[6] = ((sd[6]) * (_t1) - (sd[5] * _t2));
        dd[7] = ((sd[7]) * (_t1) - (sd[4] * _t2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateX_mulAdd(angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = java.lang.Math.fma(sd[0], _t1, sd[3] * _t2);
        dd[1] = java.lang.Math.fma(sd[1], _t1, sd[2] * _t2);
        dd[2] = java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t2));
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[0] * _t2));
        dd[4] = java.lang.Math.fma(sd[4], _t1, sd[7] * _t2);
        dd[5] = java.lang.Math.fma(sd[5], _t1, sd[6] * _t2);
        dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t2));
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[4] * _t2));
        return dest;
    }

    /** {@code rotateX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateX_mulAdd(float angle, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = ((sd[0]) * (_t1) + (sd[3] * _t2));
        dd[1] = ((sd[1]) * (_t1) + (sd[2] * _t2));
        dd[2] = ((sd[2]) * (_t1) - (sd[1] * _t2));
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t2));
        dd[4] = ((sd[4]) * (_t1) + (sd[7] * _t2));
        dd[5] = ((sd[5]) * (_t1) + (sd[6] * _t2));
        dd[6] = ((sd[6]) * (_t1) - (sd[5] * _t2));
        dd[7] = ((sd[7]) * (_t1) - (sd[4] * _t2));
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return rotateXYZ_sca472e04_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20), java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        return rotateXYZ_sca472e04_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t20) - (sd[2] * _t22)), ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t22) - (sd[0] * _t20)), ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t21) - (sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXYZ_sca472e04_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        float _buf3 = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        float _buf4 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t22, -sd[4] * _t20);
        float _buf5 = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t19) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXYZ_sca472e04_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        float _buf3 = ((sd[4]) * (_t21) + (sd[7] * _t19)) + ((sd[5]) * (_t20) - (sd[6] * _t22));
        float _buf4 = ((sd[5]) * (_t21) + (sd[6] * _t19)) + ((sd[7]) * (_t22) - (sd[4] * _t20));
        float _buf5 = ((sd[4]) * (_t22) + (sd[7] * _t20)) + ((sd[6]) * (_t21) - (sd[5] * _t19));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t19)) - ((sd[5]) * (_t22) + (sd[6] * _t20));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_fma(float angleX, float angleY, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20);
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
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        return rotateXYZ_s56376703_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXYZ_s56376703_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t22, -sd[4] * _t20);
        dd[6] = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t19) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
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
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        return rotateXZY_s5a2d259a_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20), java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        return rotateXZY_s5a2d259a_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t20) - (sd[2] * _t22)), ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t22) - (sd[0] * _t20)), ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t19) - (sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXZY_s5a2d259a_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        float _buf3 = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        float _buf4 = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t22, -sd[4] * _t20);
        float _buf5 = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -sd[4] * _t21) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateXZY_s5a2d259a_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        float _buf3 = ((sd[4]) * (_t19) + (sd[7] * _t21)) + ((sd[5]) * (_t20) - (sd[6] * _t22));
        float _buf4 = ((sd[5]) * (_t19) + (sd[6] * _t21)) + ((sd[7]) * (_t22) - (sd[4] * _t20));
        float _buf5 = ((sd[4]) * (_t22) + (sd[7] * _t20)) + ((sd[6]) * (_t19) - (sd[5] * _t21));
        dd[7] = ((sd[7]) * (_t19) - (sd[4] * _t21)) - ((sd[5]) * (_t22) + (sd[6] * _t20));
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
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_fma(float angleX, float angleZ, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20);
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
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        return rotateXZY_s115e7ad5_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateXZY_s115e7ad5_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t20, -(sd[6] * _t22));
        dd[5] = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t22, -sd[4] * _t20);
        dd[6] = java.lang.Math.fma(sd[4], _t22, sd[7] * _t20) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -sd[4] * _t21) - java.lang.Math.fma(sd[5], _t22, sd[6] * _t20);
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateY_mulAdd(angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = java.lang.Math.fma(sd[0], _t1, -(sd[2] * _t2));
        float _buf1 = java.lang.Math.fma(sd[1], _t1, sd[3] * _t2);
        dd[2] = java.lang.Math.fma(sd[0], _t2, sd[2] * _t1);
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[1] * _t2));
        float _buf2 = java.lang.Math.fma(sd[4], _t1, -(sd[6] * _t2));
        float _buf3 = java.lang.Math.fma(sd[5], _t1, sd[7] * _t2);
        dd[6] = java.lang.Math.fma(sd[4], _t2, sd[6] * _t1);
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[5] * _t2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        return dest;
    }

    /** {@code rotateY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateY_mulAdd(float angle, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = ((sd[0]) * (_t1) - (sd[2] * _t2));
        float _buf1 = ((sd[1]) * (_t1) + (sd[3] * _t2));
        dd[2] = ((sd[0]) * (_t2) + (sd[2] * _t1));
        dd[3] = ((sd[3]) * (_t1) - (sd[1] * _t2));
        float _buf2 = ((sd[4]) * (_t1) - (sd[6] * _t2));
        float _buf3 = ((sd[5]) * (_t1) + (sd[7] * _t2));
        dd[6] = ((sd[4]) * (_t2) + (sd[6] * _t1));
        dd[7] = ((sd[7]) * (_t1) - (sd[5] * _t2));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateY_mulAdd(angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = java.lang.Math.fma(sd[0], _t1, -(sd[2] * _t2));
        dd[1] = java.lang.Math.fma(sd[1], _t1, sd[3] * _t2);
        dd[2] = java.lang.Math.fma(sd[0], _t2, sd[2] * _t1);
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[1] * _t2));
        dd[4] = java.lang.Math.fma(sd[4], _t1, -(sd[6] * _t2));
        dd[5] = java.lang.Math.fma(sd[5], _t1, sd[7] * _t2);
        dd[6] = java.lang.Math.fma(sd[4], _t2, sd[6] * _t1);
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[5] * _t2));
        return dest;
    }

    /** {@code rotateY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateY_mulAdd(float angle, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = ((sd[0]) * (_t1) - (sd[2] * _t2));
        dd[1] = ((sd[1]) * (_t1) + (sd[3] * _t2));
        dd[2] = ((sd[0]) * (_t2) + (sd[2] * _t1));
        dd[3] = ((sd[3]) * (_t1) - (sd[1] * _t2));
        dd[4] = ((sd[4]) * (_t1) - (sd[6] * _t2));
        dd[5] = ((sd[5]) * (_t1) + (sd[7] * _t2));
        dd[6] = ((sd[4]) * (_t2) + (sd[6] * _t1));
        dd[7] = ((sd[7]) * (_t1) - (sd[5] * _t2));
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        return rotateAxis_s52b79d91_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t20) + java.lang.Math.fma(sd[1], _t21, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t20) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t21), java.lang.Math.fma(sd[0], _t22, sd[3] * _t21) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t20)));
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        return rotateAxis_s52b79d91_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t19) + (sd[3] * _t20)) + ((sd[1]) * (_t21) - (sd[2] * _t22)), ((sd[1]) * (_t19) + (sd[2] * _t20)) + ((sd[3]) * (_t22) - (sd[0] * _t21)), ((sd[0]) * (_t22) + (sd[3] * _t21)) + ((sd[2]) * (_t19) - (sd[1] * _t20)));
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_fma(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t20) + java.lang.Math.fma(sd[1], _t21, -(sd[2] * _t22));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t20) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t21);
        dd[2] = java.lang.Math.fma(sd[0], _t22, sd[3] * _t21) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t20));
        return rotateAxis_sffd823f0_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t20)) + ((sd[1]) * (_t21) - (sd[2] * _t22));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t20)) + ((sd[3]) * (_t22) - (sd[0] * _t21));
        dd[2] = ((sd[0]) * (_t22) + (sd[3] * _t21)) + ((sd[2]) * (_t19) - (sd[1] * _t20));
        return rotateAxis_sffd823f0_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return rotateYZX_s3069681e_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22), java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        return rotateYZX_s3069681e_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t22) - (sd[2] * _t20)), ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t20) - (sd[0] * _t22)), ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t21) - (sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateYZX_s3069681e_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        float _buf3 = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        float _buf4 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t22);
        float _buf5 = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t19) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateYZX_s3069681e_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        float _buf3 = ((sd[4]) * (_t21) + (sd[7] * _t19)) + ((sd[5]) * (_t22) - (sd[6] * _t20));
        float _buf4 = ((sd[5]) * (_t21) + (sd[6] * _t19)) + ((sd[7]) * (_t20) - (sd[4] * _t22));
        float _buf5 = ((sd[4]) * (_t20) + (sd[7] * _t22)) + ((sd[6]) * (_t21) - (sd[5] * _t19));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t19)) - ((sd[5]) * (_t20) + (sd[6] * _t22));
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
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_fma(float angleY, float angleZ, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22);
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
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        return rotateYZX_s5ddd7519_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateYZX_s5ddd7519_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t19) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t22);
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t19));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t19) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZ_mulAdd(angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = java.lang.Math.fma(sd[0], _t1, sd[1] * _t2);
        dd[1] = java.lang.Math.fma(sd[1], _t1, -(sd[0] * _t2));
        float _buf1 = java.lang.Math.fma(sd[2], _t1, sd[3] * _t2);
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[2] * _t2));
        float _buf2 = java.lang.Math.fma(sd[4], _t1, sd[5] * _t2);
        dd[5] = java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t2));
        float _buf3 = java.lang.Math.fma(sd[6], _t1, sd[7] * _t2);
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[6] * _t2));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        dd[6] = _buf3;
        return dest;
    }

    /** {@code rotateZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZ_mulAdd(float angle, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _buf0 = ((sd[0]) * (_t1) + (sd[1] * _t2));
        dd[1] = ((sd[1]) * (_t1) - (sd[0] * _t2));
        float _buf1 = ((sd[2]) * (_t1) + (sd[3] * _t2));
        dd[3] = ((sd[3]) * (_t1) - (sd[2] * _t2));
        float _buf2 = ((sd[4]) * (_t1) + (sd[5] * _t2));
        dd[5] = ((sd[5]) * (_t1) - (sd[4] * _t2));
        float _buf3 = ((sd[6]) * (_t1) + (sd[7] * _t2));
        dd[7] = ((sd[7]) * (_t1) - (sd[6] * _t2));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        dd[6] = _buf3;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZ_mulAdd(angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t2);
        dd[1] = java.lang.Math.fma(sd[1], _t1, -(sd[0] * _t2));
        dd[2] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t2);
        dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[2] * _t2));
        dd[4] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t2);
        dd[5] = java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t2));
        dd[6] = java.lang.Math.fma(sd[6], _t1, sd[7] * _t2);
        dd[7] = java.lang.Math.fma(sd[7], _t1, -(sd[6] * _t2));
        return dest;
    }

    /** {@code rotateZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZ_mulAdd(float angle, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dd[0] = ((sd[0]) * (_t1) + (sd[1] * _t2));
        dd[1] = ((sd[1]) * (_t1) - (sd[0] * _t2));
        dd[2] = ((sd[2]) * (_t1) + (sd[3] * _t2));
        dd[3] = ((sd[3]) * (_t1) - (sd[2] * _t2));
        dd[4] = ((sd[4]) * (_t1) + (sd[5] * _t2));
        dd[5] = ((sd[5]) * (_t1) - (sd[4] * _t2));
        dd[6] = ((sd[6]) * (_t1) + (sd[7] * _t2));
        dd[7] = ((sd[7]) * (_t1) - (sd[6] * _t2));
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return rotateZXY_s2cab48e_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t22) + java.lang.Math.fma(sd[1], _t19, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t22) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t19), java.lang.Math.fma(sd[0], _t20, sd[3] * _t19) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t22)));
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        return rotateZXY_s2cab48e_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t21) + (sd[3] * _t22)) + ((sd[1]) * (_t19) - (sd[2] * _t20)), ((sd[1]) * (_t21) + (sd[2] * _t22)) + ((sd[3]) * (_t20) - (sd[0] * _t19)), ((sd[0]) * (_t20) + (sd[3] * _t19)) + ((sd[2]) * (_t21) - (sd[1] * _t22)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZXY_s2cab48e_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t22) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t19);
        float _buf3 = java.lang.Math.fma(sd[4], _t21, sd[7] * _t22) + java.lang.Math.fma(sd[5], _t19, -(sd[6] * _t20));
        float _buf4 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t19);
        float _buf5 = java.lang.Math.fma(sd[4], _t20, sd[7] * _t19) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t22));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t22) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t19);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZXY_s2cab48e_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t22)) - ((sd[1]) * (_t20) + (sd[2] * _t19));
        float _buf3 = ((sd[4]) * (_t21) + (sd[7] * _t22)) + ((sd[5]) * (_t19) - (sd[6] * _t20));
        float _buf4 = ((sd[5]) * (_t21) + (sd[6] * _t22)) + ((sd[7]) * (_t20) - (sd[4] * _t19));
        float _buf5 = ((sd[4]) * (_t20) + (sd[7] * _t19)) + ((sd[6]) * (_t21) - (sd[5] * _t22));
        dd[7] = ((sd[7]) * (_t21) - (sd[4] * _t22)) - ((sd[5]) * (_t20) + (sd[6] * _t19));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_fma(float angleZ, float angleX, float angleY, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dd[0] = java.lang.Math.fma(sd[0], _t21, sd[3] * _t22) + java.lang.Math.fma(sd[1], _t19, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t21, sd[2] * _t22) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t19);
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
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dd[0] = ((sd[0]) * (_t21) + (sd[3] * _t22)) + ((sd[1]) * (_t19) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t21) + (sd[2] * _t22)) + ((sd[3]) * (_t20) - (sd[0] * _t19));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t19)) + ((sd[2]) * (_t21) - (sd[1] * _t22));
        return rotateZXY_s3268b4e9_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZXY_s3268b4e9_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t22) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t19);
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[7] * _t22) + java.lang.Math.fma(sd[5], _t19, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t19);
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t19) + java.lang.Math.fma(sd[6], _t21, -(sd[5] * _t22));
        dd[7] = java.lang.Math.fma(sd[7], _t21, -sd[4] * _t22) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t19);
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
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this dual quaternion and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        float[] sd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        return rotateZYX_se9d00b00_1_fma(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22), java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        return rotateZYX_se9d00b00_1_mulAdd(dest, sd, ((FloatDualQuatImpl) dest).data, _t19, _t20, _t21, _t22, ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t22) - (sd[2] * _t20)), ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t20) - (sd[0] * _t22)), ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t19) - (sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZYX_se9d00b00_1_fma(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        float _buf3 = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        float _buf4 = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t22);
        float _buf5 = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -sd[4] * _t21) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat rotateZYX_se9d00b00_1_mulAdd(FloatDualQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        float _buf3 = ((sd[4]) * (_t19) + (sd[7] * _t21)) + ((sd[5]) * (_t22) - (sd[6] * _t20));
        float _buf4 = ((sd[5]) * (_t19) + (sd[6] * _t21)) + ((sd[7]) * (_t20) - (sd[4] * _t22));
        float _buf5 = ((sd[4]) * (_t20) + (sd[7] * _t22)) + ((sd[6]) * (_t19) - (sd[5] * _t21));
        dd[7] = ((sd[7]) * (_t19) - (sd[4] * _t21)) - ((sd[5]) * (_t20) + (sd[6] * _t22));
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
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this dual quaternion and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_fma(float angleZ, float angleY, float angleX, @Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[0] = java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20));
        dd[1] = java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22);
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
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dd[0] = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        dd[1] = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        dd[2] = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        return rotateZYX_s3d4ff58f_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat rotateZYX_s3d4ff58f_1_fma(DoubleDualQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[7] * _t21) + java.lang.Math.fma(sd[5], _t22, -(sd[6] * _t20));
        dd[5] = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[7], _t20, -sd[4] * _t22);
        dd[6] = java.lang.Math.fma(sd[4], _t20, sd[7] * _t22) + java.lang.Math.fma(sd[6], _t19, -(sd[5] * _t21));
        dd[7] = java.lang.Math.fma(sd[7], _t19, -sd[4] * _t21) - java.lang.Math.fma(sd[5], _t20, sd[6] * _t22);
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        dd[4] = java.lang.Math.fma(sd[1], _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(sd[3], _t5, sd[4])));
        dd[5] = java.lang.Math.fma(sd[3], _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(sd[2], _t5, sd[5])));
        dd[6] = java.lang.Math.fma(sd[0], _t4, java.lang.Math.fma(sd[3], _t3, java.lang.Math.fma(_t2, _t5, sd[6])));
        dd[7] = java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, sd[7])));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
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
        dd[4] = java.lang.Math.fma(sd[1], _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(sd[3], _t5, sd[4])));
        dd[5] = java.lang.Math.fma(sd[3], _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(sd[2], _t5, sd[5])));
        dd[6] = java.lang.Math.fma(sd[0], _t4, java.lang.Math.fma(sd[3], _t3, java.lang.Math.fma(_t2, _t5, sd[6])));
        dd[7] = java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, sd[7])));
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
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        dd[4] = java.lang.Math.fma(sd[1], _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(sd[3], _t5, sd[4])));
        dd[5] = java.lang.Math.fma(sd[3], _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(sd[2], _t5, sd[5])));
        dd[6] = java.lang.Math.fma(sd[0], _t4, java.lang.Math.fma(sd[3], _t3, java.lang.Math.fma(_t2, _t5, sd[6])));
        dd[7] = java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, sd[7])));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat translate_mulAdd(float translationX, float translationY, float translationZ, @Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _t1 = -sd[0];
        float _t2 = -sd[1];
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        dd[4] = ((sd[1]) * (_t3) + (((_t0) * (_t4) + (((sd[3]) * (_t5) + (sd[4]))))));
        dd[5] = ((sd[3]) * (_t4) + (((_t1) * (_t3) + (((sd[2]) * (_t5) + (sd[5]))))));
        dd[6] = ((sd[0]) * (_t4) + (((sd[3]) * (_t3) + (((_t2) * (_t5) + (sd[6]))))));
        dd[7] = ((_t1) * (_t5) + (((_t2) * (_t4) + (((_t0) * (_t3) + (sd[7]))))));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
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
        dd[4] = java.lang.Math.fma(sd[1], _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(sd[3], _t5, sd[4])));
        dd[5] = java.lang.Math.fma(sd[3], _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(sd[2], _t5, sd[5])));
        dd[6] = java.lang.Math.fma(sd[0], _t4, java.lang.Math.fma(sd[3], _t3, java.lang.Math.fma(_t2, _t5, sd[6])));
        dd[7] = java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, sd[7])));
        return dest;
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat translate_mulAdd(float translationX, float translationY, float translationZ, @Mutated DoubleDualQuat dest) {
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
        dd[4] = ((sd[1]) * (_t3) + (((_t0) * (_t4) + (((sd[3]) * (_t5) + (sd[4]))))));
        dd[5] = ((sd[3]) * (_t4) + (((_t1) * (_t3) + (((sd[2]) * (_t5) + (sd[5]))))));
        dd[6] = ((sd[0]) * (_t4) + (((sd[3]) * (_t3) + (((_t2) * (_t5) + (sd[6]))))));
        dd[7] = ((_t1) * (_t5) + (((_t2) * (_t4) + (((_t0) * (_t3) + (sd[7]))))));
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transform_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(pY, sd[0], pX * -sd[1]);
        float _t10 = 2.0f * java.lang.Math.fma(pX, sd[2], pZ * -sd[0]);
        float _t11 = 2.0f * java.lang.Math.fma(pZ, sd[1], pY * -sd[2]);
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[1], sd[6], -sd[2] * sd[5]) + java.lang.Math.fma(sd[3], sd[4], -sd[0] * sd[7]), pX))));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[2], sd[4], -sd[0] * sd[6]) + java.lang.Math.fma(sd[3], sd[5], -sd[1] * sd[7]), pY))));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[0], sd[5], -sd[1] * sd[4]) + java.lang.Math.fma(sd[3], sd[6], -sd[2] * sd[7]), pZ))));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transform_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((pY) * (sd[0]) - (pX * sd[1]));
        float _t10 = 2.0f * ((pX) * (sd[2]) - (pZ * sd[0]));
        float _t11 = 2.0f * ((pZ) * (sd[1]) - (pY * sd[2]));
        dd[0] = ((sd[1]) * (_t9) + (((((sd[3]) * (_t11) + (((2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))) + (pX))))) - (sd[2]) * (_t10))));
        dd[1] = ((sd[2]) * (_t11) + (((((sd[3]) * (_t10) + (((2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))) + (pY))))) - (sd[0]) * (_t9))));
        dd[2] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (((2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))) + (pZ))))) - (sd[1]) * (_t11))));
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
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(pY, sd[0], pX * -sd[1]);
        float _t10 = 2.0f * java.lang.Math.fma(pX, sd[2], pZ * -sd[0]);
        float _t11 = 2.0f * java.lang.Math.fma(pZ, sd[1], pY * -sd[2]);
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[1], sd[6], -sd[2] * sd[5]) + java.lang.Math.fma(sd[3], sd[4], -sd[0] * sd[7]), pX))));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[2], sd[4], -sd[0] * sd[6]) + java.lang.Math.fma(sd[3], sd[5], -sd[1] * sd[7]), pY))));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, java.lang.Math.fma(2.0f, java.lang.Math.fma(sd[0], sd[5], -sd[1] * sd[4]) + java.lang.Math.fma(sd[3], sd[6], -sd[2] * sd[7]), pZ))));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((pY) * (sd[0]) - (pX * sd[1]));
        float _t10 = 2.0f * ((pX) * (sd[2]) - (pZ * sd[0]));
        float _t11 = 2.0f * ((pZ) * (sd[1]) - (pY * sd[2]));
        dd[0] = ((sd[1]) * (_t9) + (((((sd[3]) * (_t11) + (((2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))) + (pX))))) - (sd[2]) * (_t10))));
        dd[1] = ((sd[2]) * (_t11) + (((((sd[3]) * (_t10) + (((2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))) + (pY))))) - (sd[0]) * (_t9))));
        dd[2] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (((2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))) + (pZ))))) - (sd[1]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, and store
     * the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, and store
     * the result in {@code dest}.
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, and store
     * the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformDirection_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((sd[0]) * (vY) - (sd[1] * vX));
        float _t10 = 2.0f * ((sd[2]) * (vX) - (sd[0] * vZ));
        float _t11 = 2.0f * ((sd[1]) * (vZ) - (sd[2] * vY));
        dd[0] = ((sd[1]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[2]) * (_t10))));
        dd[1] = ((sd[2]) * (_t11) + (((((sd[3]) * (_t10) + (vY))) - (sd[0]) * (_t9))));
        dd[2] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vZ))) - (sd[1]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, and store
     * the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirection_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((sd[0]) * (vY) - (sd[1] * vX));
        float _t10 = 2.0f * ((sd[2]) * (vX) - (sd[0] * vZ));
        float _t11 = 2.0f * ((sd[1]) * (vZ) - (sd[2] * vY));
        dd[0] = ((sd[1]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[2]) * (_t10))));
        dd[1] = ((sd[2]) * (_t11) + (((((sd[3]) * (_t10) + (vY))) - (sd[0]) * (_t9))));
        dd[2] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vZ))) - (sd[1]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
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
    public Double3 transformDirectionInverse(Float3R v, @Mutated Double3 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformDirectionInverse_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((sd[0]) * (vZ) - (sd[2] * vX));
        float _t10 = 2.0f * ((sd[1]) * (vX) - (sd[0] * vY));
        float _t11 = 2.0f * ((sd[2]) * (vY) - (sd[1] * vZ));
        dd[0] = ((sd[2]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[1]) * (_t10))));
        dd[1] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vY))) - (sd[2]) * (_t11))));
        dd[2] = ((sd[1]) * (_t11) + (((((sd[3]) * (_t10) + (vZ))) - (sd[0]) * (_t9))));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
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
    public Double3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirectionInverse_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((sd[0]) * (vZ) - (sd[2] * vX));
        float _t10 = 2.0f * ((sd[1]) * (vX) - (sd[0] * vY));
        float _t11 = 2.0f * ((sd[2]) * (vY) - (sd[1] * vZ));
        dd[0] = ((sd[2]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[1]) * (_t10))));
        dd[1] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vY))) - (sd[2]) * (_t11))));
        dd[2] = ((sd[1]) * (_t11) + (((((sd[3]) * (_t10) + (vZ))) - (sd[0]) * (_t9))));
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transformInverse} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t21 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]), pZ);
        float _t22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]), pX);
        float _t23 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]), pY);
        float _t33 = 2.0f * java.lang.Math.fma(sd[0], _t21, -(sd[2] * _t22));
        float _t34 = 2.0f * java.lang.Math.fma(sd[1], _t22, -(sd[0] * _t23));
        float _t35 = 2.0f * java.lang.Math.fma(sd[2], _t23, -(sd[1] * _t21));
        dd[0] = java.lang.Math.fma(sd[2], _t33, java.lang.Math.fma(-sd[1], _t34, java.lang.Math.fma(sd[3], _t35, _t22)));
        return transformInverse_s6bbb37d7_1_fma(dest, sd, dd, _t21, _t23, _t33, _t34, _t35);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t21 = ((pZ) - (2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))));
        float _t22 = ((pX) - (2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))));
        float _t23 = ((pY) - (2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))));
        float _t33 = 2.0f * ((sd[0]) * (_t21) - (sd[2] * _t22));
        float _t34 = 2.0f * ((sd[1]) * (_t22) - (sd[0] * _t23));
        float _t35 = 2.0f * ((sd[2]) * (_t23) - (sd[1] * _t21));
        dd[0] = ((sd[2]) * (_t33) + (((((sd[3]) * (_t35) + (_t22))) - (sd[1]) * (_t34))));
        dd[1] = ((sd[0]) * (_t34) + (((((sd[3]) * (_t33) + (_t23))) - (sd[2]) * (_t35))));
        dd[2] = ((sd[1]) * (_t35) + (((((sd[3]) * (_t34) + (_t21))) - (sd[0]) * (_t33))));
        return dest;
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Float3 transformInverse_s6bbb37d7_1_fma(Float3 dest, float[] sd, float[] dd, float _t21, float _t23, float _t33, float _t34, float _t35) {
        dd[1] = java.lang.Math.fma(sd[0], _t34, java.lang.Math.fma(-sd[2], _t35, java.lang.Math.fma(sd[3], _t33, _t23)));
        dd[2] = java.lang.Math.fma(sd[1], _t35, java.lang.Math.fma(-sd[0], _t33, java.lang.Math.fma(sd[3], _t34, _t21)));
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
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code transformInverse} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t21 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[0], sd[5], -(sd[1] * sd[4])) + java.lang.Math.fma(sd[3], sd[6], sd[2] * -sd[7]), pZ);
        float _t22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[1], sd[6], -(sd[2] * sd[5])) + java.lang.Math.fma(sd[3], sd[4], sd[0] * -sd[7]), pX);
        float _t23 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[2], sd[4], -(sd[0] * sd[6])) + java.lang.Math.fma(sd[3], sd[5], sd[1] * -sd[7]), pY);
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
        float _t21 = ((pZ) - (2.0f) * (((sd[0]) * (sd[5]) - (sd[1] * sd[4])) + ((sd[3]) * (sd[6]) - (sd[2] * sd[7]))));
        float _t22 = ((pX) - (2.0f) * (((sd[1]) * (sd[6]) - (sd[2] * sd[5])) + ((sd[3]) * (sd[4]) - (sd[0] * sd[7]))));
        float _t23 = ((pY) - (2.0f) * (((sd[2]) * (sd[4]) - (sd[0] * sd[6])) + ((sd[3]) * (sd[5]) - (sd[1] * sd[7]))));
        float _t33 = 2.0f * ((sd[0]) * (_t21) - (sd[2] * _t22));
        float _t34 = 2.0f * ((sd[1]) * (_t22) - (sd[0] * _t23));
        float _t35 = 2.0f * ((sd[2]) * (_t23) - (sd[1] * _t21));
        dd[0] = ((sd[2]) * (_t33) + (((((sd[3]) * (_t35) + (_t22))) - (sd[1]) * (_t34))));
        dd[1] = ((sd[0]) * (_t34) + (((((sd[3]) * (_t33) + (_t23))) - (sd[2]) * (_t35))));
        dd[2] = ((sd[1]) * (_t35) + (((((sd[3]) * (_t34) + (_t21))) - (sd[0]) * (_t33))));
        return dest;
    }

    /** Piece 2 of {@code transformInverse}, split to fit the inline budget; reached only through it. */
    private Double3 transformInverse_s57511e60_1_fma(Double3 dest, float[] sd, double[] dd, float _t21, float _t23, float _t33, float _t34, float _t35) {
        dd[1] = java.lang.Math.fma(sd[0], _t34, java.lang.Math.fma(-sd[2], _t35, java.lang.Math.fma(sd[3], _t33, _t23)));
        dd[2] = java.lang.Math.fma(sd[1], _t35, java.lang.Math.fma(-sd[0], _t33, java.lang.Math.fma(sd[3], _t34, _t21)));
        return dest;
    }

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
     * {@code w = 1}, and store the result in {@code dest}.
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
     * {@code w = 1}, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
    }

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(pX, pY, pZ, dest);
        return transform_mulAdd(pX, pY, pZ, dest);
    }

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()}, and store the result in {@code dest}.
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
     * materializing {@code invert()}, and store the result in {@code dest}.
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
     * materializing {@code invert()}, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()}, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return transformInverse_fma(pX, pY, pZ, dest);
        return transformInverse_mulAdd(pX, pY, pZ, dest);
    }

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation, and store the result in {@code dest}.
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
     * translation, and store the result in {@code dest}.
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
     * translation, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
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
        return "FloatDualQuat(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ", " + this.data[6] + ", " + this.data[7] + ")";
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
        float[] otherData = ((FloatDualQuatImpl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon
            && java.lang.Math.abs(sd[7] - otherData[7]) <= epsilon;
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

    @Mutated public FloatDualQuat load(float[] src, int offset) {
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

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public FloatDualQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatDualQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public FloatDualQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatDualQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public FloatDualQuat load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public FloatDualQuat load(long offset, MemorySegment src) {
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
        return dest;
    }

    @Mutated public FloatDualQuat load(double[] src, int offset) {
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

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    @Mutated public FloatDualQuat loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatDualQuat loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return this;
    }

    public FloatDualQuat storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public FloatDualQuat loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    public MemorySegment storeDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    @Mutated public FloatDualQuat loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }

    @Mutated public FloatDualQuat loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_2 = FloatVector.fromArray(COL_SPECIES, new float[] {0.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final FloatVector VEC_3 = FloatVector.fromArray(COL_SPECIES, new float[] {0.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final float[] DATA_0 = new float[] {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private static final float[] DATA_1 = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

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
