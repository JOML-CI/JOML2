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
 * Generated implementation of {@link FloatQuat} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatQuatImpl implements FloatQuat {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatQuatBbOpsUnsafe()
                        : new FloatQuatBbOpsApi();
        static final FloatQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatQuatRawOpsUnsafe()
                        : new FloatQuatRawOpsApi();
    }

    public FloatQuatImpl() {
        data = new float[4];
        data[3] = 1;
    }

    public FloatQuatImpl(float x, float y, float z, float w) {
        float[] dd = this.data = new float[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public FloatQuatImpl(FloatQuatR src) {
        float[] dd = this.data = new float[4];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
        dd[3] = src.w();
    }


    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat invert(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t3_inv = 1.0f / Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        dd[0] = -(sd[0] * _t3_inv);
        dd[1] = -(sd[1] * _t3_inv);
        dd[2] = -(sd[2] * _t3_inv);
        dd[3] = sd[3] * _t3_inv;
        return dest;
    }


    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invert(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t3_inv = 1.0f / Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        dd[0] = -(sd[0] * _t3_inv);
        dd[1] = -(sd[1] * _t3_inv);
        dd[2] = -(sd[2] * _t3_inv);
        dd[3] = sd[3] * _t3_inv;
        return dest;
    }


    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat invertProduct(FloatQuatR other, @Mutated FloatQuat dest) {
        return invertProduct(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invertProduct(FloatQuatR other, @Mutated DoubleQuat dest) {
        return invertProduct(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the inverse of the product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (otherX, otherY, otherZ, otherW)} must
     * be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat invertProduct(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t20 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _t21 = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        float _t22 = Math.fma(otherY, sd[0], otherZ * sd[3]) + Math.fma(otherW, sd[2], -(otherX * sd[1]));
        float _t23 = Math.fma(otherX, sd[2], otherW * sd[1]) + Math.fma(otherY, sd[3], -(otherZ * sd[0]));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dd[0] = -(_t20 * _t27_inv);
        dd[1] = -(_t23 * _t27_inv);
        dd[2] = -(_t22 * _t27_inv);
        dd[3] = _t21 * _t27_inv;
        return dest;
    }


    /**
     * Compute the inverse of the product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (otherX, otherY, otherZ, otherW)} must
     * be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invertProduct(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t20 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _t21 = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        float _t22 = Math.fma(otherY, sd[0], otherZ * sd[3]) + Math.fma(otherW, sd[2], -(otherX * sd[1]));
        float _t23 = Math.fma(otherX, sd[2], otherW * sd[1]) + Math.fma(otherY, sd[3], -(otherZ * sd[0]));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dd[0] = -(_t20 * _t27_inv);
        dd[1] = -(_t23 * _t27_inv);
        dd[2] = -(_t22 * _t27_inv);
        dd[3] = _t21 * _t27_inv;
        return dest;
    }


    /**
     * Add {@code other} to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat add(FloatQuatR other, @Mutated FloatQuat dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add {@code other} to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat add(FloatQuatR other, @Mutated DoubleQuat dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat add(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat add(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }


    /**
     * Multiply each component of this quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat mul(float scalar, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        return dest;
    }


    /**
     * Multiply each component of this quaternion by {@code scalar} and store the result in
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
    public DoubleQuat mul(float scalar, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        return dest;
    }


    /**
     * Negate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat negate(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
    }


    /**
     * Negate this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat negate(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
    }


    /**
     * Subtract {@code other} from this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat sub(FloatQuatR other, @Mutated FloatQuat dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract {@code other} from this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat sub(FloatQuatR other, @Mutated DoubleQuat dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this
     * quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat sub(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this
     * quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat sub(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }


    /**
     * Set this quaternion to the unit quaternion
     * {@code (sqrt(1 - u1) sin(2 PI u2), sqrt(1 - u1) cos(2 PI u2), sqrt(u1) sin(2 PI u3), sqrt(u1) cos(2 PI u3))},
     * Shoemake's construction: samples uniformly distributed in {@code [0, 1)} give a rotation
     * uniformly distributed over all rotations ({@code makeRandomRotation} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u1} must lie in {@code [0, 1]}.
     *
     * @param u1 the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed rotation
     * @param u2 the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @param u3 the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @return this
     */
    @Mutated public FloatQuat makeUniformRotation(float u1, float u2, float u3) {
        float[] dd = this.data;
        float _t0 = (float) Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sqrt(1.0f - u1);
        float _t6 = (float) Math.sin(_t3);
        dd[0] = _t4 * _t5;
        dd[1] = (float) Math.cosFromSin(_t4, _t1) * _t5;
        dd[2] = _t6 * _t0;
        dd[3] = (float) Math.cosFromSin(_t6, _t3) * _t0;
        return this;
    }


    /**
     * Set this quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the quaternion to copy
     * @return this
     */
    public @Mutated FloatQuat set(FloatQuatR v) {
        return set(v.x(), v.y(), v.z(), v.w());
    }


    /**
     * Set this quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @return this
     */
    @Mutated public FloatQuat set(float vX, float vY, float vZ, float vW) {
        float[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
        return this;
    }


    /**
     * Convert this quaternion to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat toDouble(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set this quaternion to the rotation (real) part of the unit dual quaternion {@code dq}.
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    public @Mutated FloatQuat makeFromDualQuat(FloatDualQuatR dq) {
        return makeFromDualQuat(dq.rX(), dq.rY(), dq.rZ(), dq.rW(), dq.dX(), dq.dY(), dq.dZ(), dq.dW());
    }


    /**
     * Set this quaternion to the rotation (real) part of the unit dual quaternion ({@code dqRX},
     * {@code dqRY}, {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ},
     * {@code dqDW}).
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
    @Mutated public FloatQuat makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float[] dd = this.data;
        dd[0] = dqRX;
        dd[1] = dqRY;
        dd[2] = dqRZ;
        dd[3] = dqRW;
        return this;
    }


    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatQuat makeFromMatrix(Float3x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        float _t0 = mData[0] + mData[4];
        float _t10 = mData[8] + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (mData[0] - (mData[4] + mData[8]));
        float _t16 = 1.0f + (mData[4] - (mData[0] + mData[8]));
        float _t17 = 1.0f + (mData[8] - _t0);
        return makeFromMatrix_s4398bb65_1(dd, mData, mData[5] - mData[7], mData[3] + mData[1], mData[6] + mData[2], mData[6] - mData[2], mData[7] + mData[5], mData[1] - mData[3], _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeFromMatrix_s4398bb65_1(float[] dd, float[] mData, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dd[0] = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
        } else {
            if (mData[0] > Math.max(mData[4], mData[8])) {
                dd[0] = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
            } else {
                if (mData[4] > mData[8]) {
                    dd[0] = _sp1 * _t4;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                } else {
                    dd[0] = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                }
            }
        }
        return this;
    }


    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatQuat makeFromMatrix(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _t0 = mData[0] + mData[5];
        float _t10 = mData[10] + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (mData[0] - (mData[5] + mData[10]));
        float _t16 = 1.0f + (mData[5] - (mData[0] + mData[10]));
        float _t17 = 1.0f + (mData[10] - _t0);
        return makeFromMatrix_s7b6de20e_1(dd, mData, mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatQuat makeFromMatrix_s7b6de20e_1(float[] dd, float[] mData, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dd[0] = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5f * (float) Math.sqrt(_t14);
        } else {
            if (mData[0] > Math.max(mData[5], mData[10])) {
                dd[0] = 0.5f * (float) Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
            } else {
                if (mData[5] > mData[10]) {
                    dd[0] = _sp1 * _t4;
                    dd[1] = 0.5f * (float) Math.sqrt(_t16);
                    dd[2] = _sp1 * _t8;
                    dd[3] = _sp1 * _t7;
                } else {
                    dd[0] = _sp2 * _t6;
                    dd[1] = _sp2 * _t8;
                    dd[2] = 0.5f * (float) Math.sqrt(_t17);
                    dd[3] = _sp2 * _t9;
                }
            }
        }
        return this;
    }


    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatQuat makeFromMatrix(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _t0 = mData[0] + mData[5];
        float _t10 = mData[10] + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (mData[0] - (mData[5] + mData[10]));
        float _t16 = 1.0f + (mData[5] - (mData[0] + mData[10]));
        float _t17 = 1.0f + (mData[10] - _t0);
        return makeFromMatrix_s7b6de20e_1(dd, mData, mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }


    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        return dest;
    }


    /**
     * Compute the matrix representation of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        dd[1] = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        dd[3] = 0.0f;
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        dd[5] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[7] = 0.0f;
        float _buf2 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        dd[9] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[10] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[4] = _buf1;
        dd[8] = _buf2;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the matrix representation of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        dd[1] = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        dd[3] = 0.0f;
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        dd[5] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[7] = 0.0f;
        float _buf2 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        dd[9] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[10] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = 0.0f;
        dd[13] = 0.0f;
        dd[14] = 0.0f;
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[4] = _buf1;
        dd[8] = _buf2;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the 3x3 rotation matrix representation of this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        float _buf2 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        dd[4] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _buf3 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        dd[7] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[8] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[6] = _buf3;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x3 rotation matrix representation of this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        float _buf2 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        dd[4] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _buf3 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        dd[7] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[8] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[6] = _buf3;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        float _buf2 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        float _buf3 = 0.0f;
        dd[4] = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[5] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[7] = 0.0f;
        dd[8] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        dd[9] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[10] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[11] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[2] * sd[2];
        float _t1 = sd[2] * sd[3];
        float _t2 = sd[1] * sd[3];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -_t1);
        float _buf2 = 2.0f * Math.fma(sd[0], sd[2], _t2);
        float _buf3 = 0.0f;
        dd[4] = 2.0f * Math.fma(sd[0], sd[1], _t1);
        dd[5] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[7] = 0.0f;
        dd[8] = 2.0f * Math.fma(sd[0], sd[2], -_t2);
        dd[9] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[10] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[11] = 0.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, storing them in {@code swing} and {@code twist} respectively,
     * such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    public FloatQuat decomposeSwingTwist(Float3R axis, @Mutated FloatQuat swing, @Mutated FloatQuat twist) {
        return decomposeSwingTwist(axis.x(), axis.y(), axis.z(), swing, twist);
    }


    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, storing them in {@code swing} and {@code twist} respectively,
     * such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    public FloatQuat decomposeSwingTwist(Float3R axis, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        return decomposeSwingTwist(axis.x(), axis.y(), axis.z(), swing, twist);
    }


    /**
     * Decompose this quaternion into a swing about an axis perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) followed by a twist about ({@code axisX}, {@code axisY},
     * {@code axisZ}), storing them in {@code swing} and {@code twist} respectively, such that
     * {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    public FloatQuat decomposeSwingTwist(float axisX, float axisY, float axisZ, @Mutated FloatQuat swing, @Mutated FloatQuat twist) {
        float[] sd = this.data;
        float[] swingData = ((FloatQuatImpl) swing).data;
        float[] twistData = ((FloatQuatImpl) twist).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        return decomposeSwingTwist_s224101aa_1(sd, swingData, twistData, _t11, _t12, _t13, _t14, Math.fma(sd[0], _t11, -(sd[3] * _t12)) + Math.fma(sd[2], _t13, -(sd[1] * _t14)), Math.fma(sd[0], _t14, -(sd[3] * _t13)) + Math.fma(sd[1], _t11, -(sd[2] * _t12)), Math.fma(sd[1], _t12, sd[2] * _t11) + Math.fma(-sd[0], _t13, -(sd[3] * _t14)));
    }

    /** Piece 2 of {@code decomposeSwingTwist}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeSwingTwist_s224101aa_1(float[] sd, float[] swingData, float[] twistData, float _t11, float _t12, float _t13, float _t14, float _d0buf0, float _d0buf1, float _d0buf2) {
        swingData[3] = Math.fma(sd[0], _t12, sd[3] * _t11) - Math.fma(-sd[2], _t14, -(sd[1] * _t13));
        swingData[0] = _d0buf0;
        swingData[1] = _d0buf1;
        swingData[2] = _d0buf2;
        twistData[0] = _t12;
        twistData[1] = _t13;
        twistData[2] = _t14;
        twistData[3] = _t11;
        return this;
    }


    /**
     * Decompose this quaternion into a swing about an axis perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) followed by a twist about ({@code axisX}, {@code axisY},
     * {@code axisZ}), storing them in {@code swing} and {@code twist} respectively, such that
     * {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    public FloatQuat decomposeSwingTwist(float axisX, float axisY, float axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        float[] sd = this.data;
        double[] swingData = ((DoubleQuatImpl) swing).data;
        double[] twistData = ((DoubleQuatImpl) twist).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        return decomposeSwingTwist_saf87bd42_1(sd, swingData, twistData, _t11, _t12, _t13, _t14, Math.fma(sd[0], _t11, -(sd[3] * _t12)) + Math.fma(sd[2], _t13, -(sd[1] * _t14)), Math.fma(sd[0], _t14, -(sd[3] * _t13)) + Math.fma(sd[1], _t11, -(sd[2] * _t12)), Math.fma(sd[1], _t12, sd[2] * _t11) + Math.fma(-sd[0], _t13, -(sd[3] * _t14)));
    }

    /** Piece 2 of {@code decomposeSwingTwist}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeSwingTwist_saf87bd42_1(float[] sd, double[] swingData, double[] twistData, float _t11, float _t12, float _t13, float _t14, float _d0buf0, float _d0buf1, float _d0buf2) {
        swingData[3] = Math.fma(sd[0], _t12, sd[3] * _t11) - Math.fma(-sd[2], _t14, -(sd[1] * _t13));
        swingData[0] = _d0buf0;
        swingData[1] = _d0buf1;
        swingData[2] = _d0buf2;
        twistData[0] = _t12;
        twistData[1] = _t13;
        twistData[2] = _t14;
        twistData[3] = _t11;
        return this;
    }


    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat getSwing(Float3R axis, @Mutated FloatQuat dest) {
        return getSwing(axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getSwing(Float3R axis, @Mutated DoubleQuat dest) {
        return getSwing(axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat getSwing(float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        float _buf0 = Math.fma(sd[0], _t11, -(sd[3] * _t12)) + Math.fma(sd[2], _t13, -(sd[1] * _t14));
        float _buf1 = Math.fma(sd[0], _t14, -(sd[3] * _t13)) + Math.fma(sd[1], _t11, -(sd[2] * _t12));
        float _buf2 = Math.fma(sd[1], _t12, sd[2] * _t11) + Math.fma(-sd[0], _t13, -(sd[3] * _t14));
        dd[3] = Math.fma(sd[0], _t12, sd[3] * _t11) - Math.fma(-sd[2], _t14, -(sd[1] * _t13));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getSwing(float axisX, float axisY, float axisZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        float _buf0 = Math.fma(sd[0], _t11, -(sd[3] * _t12)) + Math.fma(sd[2], _t13, -(sd[1] * _t14));
        float _buf1 = Math.fma(sd[0], _t14, -(sd[3] * _t13)) + Math.fma(sd[1], _t11, -(sd[2] * _t12));
        float _buf2 = Math.fma(sd[1], _t12, sd[2] * _t11) + Math.fma(-sd[0], _t13, -(sd[3] * _t14));
        dd[3] = Math.fma(sd[0], _t12, sd[3] * _t11) - Math.fma(-sd[2], _t14, -(sd[1] * _t13));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat getTwist(Float3R axis, @Mutated FloatQuat dest) {
        return getTwist(axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getTwist(Float3R axis, @Mutated DoubleQuat dest) {
        return getTwist(axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat getTwist(float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dd[0] = axisX * _t6;
            dd[1] = axisY * _t6;
            dd[2] = axisZ * _t6;
            dd[3] = sd[3] * _t5;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 1.0f;
        }
        return dest;
    }


    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getTwist(float axisX, float axisY, float axisZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _t4 = Math.fma(sd[3], sd[3], _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dd[0] = axisX * _t6;
            dd[1] = axisY * _t6;
            dd[2] = axisZ * _t6;
            dd[3] = sd[3] * _t5;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 1.0f;
        }
        return dest;
    }


    /**
     * Set this quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatQuat makeIdentity() {
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        return this;
    }


    /**
     * Set all components of this quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatQuat makeZero() {
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        return this;
    }


    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lerp(FloatQuatR other, float t, @Mutated FloatQuat dest) {
        return lerp(other.x(), other.y(), other.z(), other.w(), t, dest);
    }


    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
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
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lerp(FloatQuatR other, float t, @Mutated DoubleQuat dest) {
        return lerp(other.x(), other.y(), other.z(), other.w(), t, dest);
    }


    /**
     * Linearly interpolate between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t} and store the result
     * in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = Math.fma(t, otherX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherZ - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherW - sd[3], sd[3]);
        return dest;
    }


    /**
     * Linearly interpolate between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t} and store the result
     * in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = Math.fma(t, otherX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherZ - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherW - sd[3], sd[3]);
        return dest;
    }


    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat nlerp(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        return nlerp(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerp(FloatQuatR target, float alpha, @Mutated DoubleQuat dest) {
        return nlerp(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Interpolate between this quaternion and ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}) using the interpolation factor {@code alpha} and normalize the result and
     * store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat nlerp(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t4 = Math.fma(alpha, targetW - sd[3], sd[3]);
        float _t5 = Math.fma(alpha, targetZ - sd[2], sd[2]);
        float _t6 = Math.fma(alpha, targetX - sd[0], sd[0]);
        float _t7 = Math.fma(alpha, targetY - sd[1], sd[1]);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dd[0] = _t6 * _t12;
            dd[1] = _t7 * _t12;
            dd[2] = _t5 * _t12;
            dd[3] = _t4 * _t12;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Interpolate between this quaternion and ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}) using the interpolation factor {@code alpha} and normalize the result and
     * store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerp(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t4 = Math.fma(alpha, targetW - sd[3], sd[3]);
        float _t5 = Math.fma(alpha, targetZ - sd[2], sd[2]);
        float _t6 = Math.fma(alpha, targetX - sd[0], sd[0]);
        float _t7 = Math.fma(alpha, targetY - sd[1], sd[1]);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dd[0] = _t6 * _t12;
            dd[1] = _t7 * _t12;
            dd[2] = _t5 * _t12;
            dd[3] = _t4 * _t12;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat nlerpShortest(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        return nlerpShortest(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerpShortest(FloatQuatR target, float alpha, @Mutated DoubleQuat dest) {
        return nlerpShortest(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat nlerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY))) > 0.0f) {
            _t17 = Math.fma(alpha, -targetW - sd[3], sd[3]);
            _t18 = Math.fma(alpha, -targetZ - sd[2], sd[2]);
            _t19 = Math.fma(alpha, -targetX - sd[0], sd[0]);
            _t20 = Math.fma(alpha, -targetY - sd[1], sd[1]);
        } else {
            _t17 = Math.fma(alpha, targetW - sd[3], sd[3]);
            _t18 = Math.fma(alpha, targetZ - sd[2], sd[2]);
            _t19 = Math.fma(alpha, targetX - sd[0], sd[0]);
            _t20 = Math.fma(alpha, targetY - sd[1], sd[1]);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            dd[0] = _t19 * _t25;
            dd[1] = _t20 * _t25;
            dd[2] = _t18 * _t25;
            dd[3] = _t17 * _t25;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY))) > 0.0f) {
            _t17 = Math.fma(alpha, -targetW - sd[3], sd[3]);
            _t18 = Math.fma(alpha, -targetZ - sd[2], sd[2]);
            _t19 = Math.fma(alpha, -targetX - sd[0], sd[0]);
            _t20 = Math.fma(alpha, -targetY - sd[1], sd[1]);
        } else {
            _t17 = Math.fma(alpha, targetW - sd[3], sd[3]);
            _t18 = Math.fma(alpha, targetZ - sd[2], sd[2]);
            _t19 = Math.fma(alpha, targetX - sd[0], sd[0]);
            _t20 = Math.fma(alpha, targetY - sd[1], sd[1]);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            dd[0] = _t19 * _t25;
            dd[1] = _t20 * _t25;
            dd[2] = _t18 * _t25;
            dd[3] = _t17 * _t25;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat slerp(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        return slerp(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerp(FloatQuatR target, float alpha, @Mutated DoubleQuat dest) {
        return slerp(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Spherically interpolate between this quaternion and ({@code targetX}, {@code targetY},
     * {@code targetZ}, {@code targetW}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat slerp(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 1.0f - alpha;
        float _t1 = sd[3] + targetW;
        float _t2 = sd[2] + targetZ;
        float _t3 = sd[0] + targetX;
        float _t4 = sd[1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * (float) Math.sin(alpha * _t12);
            _t27 = _t21 * (float) Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        dd[0] = Math.fma(sd[0], _t27, targetX * _t26);
        dd[1] = Math.fma(sd[1], _t27, targetY * _t26);
        dd[2] = Math.fma(sd[2], _t27, targetZ * _t26);
        dd[3] = Math.fma(sd[3], _t27, targetW * _t26);
        return dest;
    }


    /**
     * Spherically interpolate between this quaternion and ({@code targetX}, {@code targetY},
     * {@code targetZ}, {@code targetW}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerp(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 1.0f - alpha;
        float _t1 = sd[3] + targetW;
        float _t2 = sd[2] + targetZ;
        float _t3 = sd[0] + targetX;
        float _t4 = sd[1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * (float) Math.sin(alpha * _t12);
            _t27 = _t21 * (float) Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        dd[0] = Math.fma(sd[0], _t27, targetX * _t26);
        dd[1] = Math.fma(sd[1], _t27, targetY * _t26);
        dd[2] = Math.fma(sd[2], _t27, targetZ * _t26);
        dd[3] = Math.fma(sd[3], _t27, targetW * _t26);
        return dest;
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat slerpShortest(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        return slerpShortest(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerpShortest(FloatQuatR target, float alpha, @Mutated DoubleQuat dest) {
        return slerpShortest(target.x(), target.y(), target.z(), target.w(), alpha, dest);
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat slerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY)));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        return slerpShortest_sde69b070_1(alpha, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest}, split to fit the inline budget; reached only through it. */
    private FloatQuat slerpShortest_sde69b070_1(float alpha, FloatQuat dest, float[] sd, float[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(sd[3], _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(sd[2], _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(sd[0], _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(sd[1], _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, sd[3] * _t0);
            _t43 = Math.fma(alpha, _t22, sd[2] * _t0);
            _t44 = Math.fma(alpha, _t23, sd[0] * _t0);
            _t45 = Math.fma(alpha, _t24, sd[1] * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dd[0] = _t50 * _t44;
            dd[1] = _t50 * _t45;
            dd[2] = _t50 * _t43;
            dd[3] = _t50 * _t42;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY)));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        return slerpShortest_s78a5e897_1(alpha, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest}, split to fit the inline budget; reached only through it. */
    private DoubleQuat slerpShortest_s78a5e897_1(float alpha, DoubleQuat dest, float[] sd, double[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(sd[3], _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(sd[2], _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(sd[0], _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(sd[1], _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, sd[3] * _t0);
            _t43 = Math.fma(alpha, _t22, sd[2] * _t0);
            _t44 = Math.fma(alpha, _t23, sd[0] * _t0);
            _t45 = Math.fma(alpha, _t24, sd[1] * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dd[0] = _t50 * _t44;
            dd[1] = _t50 * _t45;
            dd[2] = _t50 * _t43;
            dd[3] = _t50 * _t42;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat squad(FloatQuatR control0, FloatQuatR control1, FloatQuatR target, float t, @Mutated FloatQuat dest) {
        return squad(control0.x(), control0.y(), control0.z(), control0.w(), control1.x(), control1.y(), control1.z(), control1.w(), target.x(), target.y(), target.z(), target.w(), t, dest);
    }


    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat squad(FloatQuatR control0, FloatQuatR control1, FloatQuatR target, float t, @Mutated DoubleQuat dest) {
        return squad(control0.x(), control0.y(), control0.z(), control0.w(), control1.x(), control1.y(), control1.z(), control1.w(), target.x(), target.y(), target.z(), target.w(), t, dest);
    }


    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat squad(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = sd[3] + targetW;
        float _t9 = sd[2] + targetZ;
        float _t10 = sd[0] + targetX;
        float _t11 = sd[1] + targetY;
        float _t13 = _t0 * _t1;
        float _t25 = Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t26 = Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) Math.sqrt(_t29 * _t25);
        return squad_s6cc7e980_1(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest, sd, dd, _t0, _t7, 1.0f - _t7, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, _t25, _t26, quatArcAngle(_t25), quatArcAngle(_t26), _t29, _t30, _t41, (float) Math.sqrt(_t30 * _t26), 2.0f / _t41);
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private FloatQuat squad_s6cc7e980_1(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, FloatQuat dest, float[] sd, float[] dd, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t41, float _t43, float _t45) {
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * (float) Math.sin(t * _t27);
            _t57 = _t45 * (float) Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * (float) Math.sin(t * _t28);
            _t58 = _t46 * (float) Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        float _t67 = Math.fma(control0X, _t57, control1X * _t55);
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(sd[3], _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(sd[2], _t58, targetZ * _t56);
        float _t72 = Math.fma(sd[0], _t58, targetX * _t56);
        return squad_s6cc7e980_2(dest, dd, _t13, _t14, _t17, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(sd[1], _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private FloatQuat squad_s6cc7e980_2(FloatQuat dest, float[] dd, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77) {
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * (float) Math.sin(_t13 * _t84);
            _t99 = _t93 * (float) Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dd[0] = Math.fma(_t67, _t98, _t72 * _t99);
        dd[1] = Math.fma(_t73, _t98, _t74 * _t99);
        dd[2] = Math.fma(_t70, _t98, _t71 * _t99);
        dd[3] = Math.fma(_t68, _t98, _t69 * _t99);
        return dest;
    }


    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat squad(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = sd[3] + targetW;
        float _t9 = sd[2] + targetZ;
        float _t10 = sd[0] + targetX;
        float _t11 = sd[1] + targetY;
        float _t13 = _t0 * _t1;
        float _t25 = Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t26 = Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) Math.sqrt(_t29 * _t25);
        return squad_s74b465c7_1(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest, sd, dd, _t0, _t7, 1.0f - _t7, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, _t25, _t26, quatArcAngle(_t25), quatArcAngle(_t26), _t29, _t30, _t41, (float) Math.sqrt(_t30 * _t26), 2.0f / _t41);
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s74b465c7_1(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, DoubleQuat dest, float[] sd, double[] dd, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t41, float _t43, float _t45) {
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * (float) Math.sin(t * _t27);
            _t57 = _t45 * (float) Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * (float) Math.sin(t * _t28);
            _t58 = _t46 * (float) Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        float _t67 = Math.fma(control0X, _t57, control1X * _t55);
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(sd[3], _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(sd[2], _t58, targetZ * _t56);
        float _t72 = Math.fma(sd[0], _t58, targetX * _t56);
        return squad_s74b465c7_2(dest, dd, _t13, _t14, _t17, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(sd[1], _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s74b465c7_2(DoubleQuat dest, double[] dd, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77) {
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * (float) Math.sin(_t13 * _t84);
            _t99 = _t93 * (float) Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dd[0] = Math.fma(_t67, _t98, _t72 * _t99);
        dd[1] = Math.fma(_t73, _t98, _t74 * _t99);
        dd[2] = Math.fma(_t70, _t98, _t71 * _t99);
        dd[3] = Math.fma(_t68, _t98, _t69 * _t99);
        return dest;
    }


    /**
     * Multiply this quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat mul(FloatQuatR other, @Mutated FloatQuat dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
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
    public DoubleQuat mul(FloatQuatR other, @Mutated DoubleQuat dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this quaternion by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW})
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat mul(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _buf0 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _buf1 = Math.fma(otherX, sd[2], otherW * sd[1]) + Math.fma(otherY, sd[3], -(otherZ * sd[0]));
        float _buf2 = Math.fma(otherY, sd[0], otherZ * sd[3]) + Math.fma(otherW, sd[2], -(otherX * sd[1]));
        dd[3] = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Multiply this quaternion by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW})
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat mul(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _buf0 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _buf1 = Math.fma(otherX, sd[2], otherW * sd[1]) + Math.fma(otherY, sd[3], -(otherZ * sd[0]));
        float _buf2 = Math.fma(otherY, sd[0], otherZ * sd[3]) + Math.fma(otherW, sd[2], -(otherX * sd[1]));
        dd[3] = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Pre-multiply the transformation {@code other} onto this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat preMul(FloatQuatR other, @Mutated FloatQuat dest) {
        return preMul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Pre-multiply the transformation {@code other} onto this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
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
    public DoubleQuat preMul(FloatQuatR other, @Mutated DoubleQuat dest) {
        return preMul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Pre-multiply the transformation ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat preMul(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _buf0 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherY, sd[2], -(otherZ * sd[1]));
        float _buf1 = Math.fma(otherY, sd[3], otherZ * sd[0]) + Math.fma(otherW, sd[1], -(otherX * sd[2]));
        float _buf2 = Math.fma(otherX, sd[1], otherW * sd[2]) + Math.fma(otherZ, sd[3], -(otherY * sd[0]));
        dd[3] = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Pre-multiply the transformation ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat preMul(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _buf0 = Math.fma(otherX, sd[3], otherW * sd[0]) + Math.fma(otherY, sd[2], -(otherZ * sd[1]));
        float _buf1 = Math.fma(otherY, sd[3], otherZ * sd[0]) + Math.fma(otherW, sd[1], -(otherX * sd[2]));
        float _buf2 = Math.fma(otherX, sd[1], otherW * sd[2]) + Math.fma(otherZ, sd[3], -(otherY * sd[0]));
        dd[3] = Math.fma(otherW, sd[3], -(otherX * sd[0])) - Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Add {@code other} scaled by {@code weight} to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat addScaled(FloatQuatR other, float weight, @Mutated FloatQuat dest) {
        return addScaled(other.x(), other.y(), other.z(), other.w(), weight, dest);
    }


    /**
     * Add {@code other} scaled by {@code weight} to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat addScaled(FloatQuatR other, float weight, @Mutated DoubleQuat dest) {
        return addScaled(other.x(), other.y(), other.z(), other.w(), weight, dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) scaled by {@code weight}
     * to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param weight the factor to scale ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat addScaled(float otherX, float otherY, float otherZ, float otherW, float weight, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = Math.fma(weight, otherX, sd[0]);
        dd[1] = Math.fma(weight, otherY, sd[1]);
        dd[2] = Math.fma(weight, otherZ, sd[2]);
        dd[3] = Math.fma(weight, otherW, sd[3]);
        return dest;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) scaled by {@code weight}
     * to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param weight the factor to scale ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat addScaled(float otherX, float otherY, float otherZ, float otherW, float weight, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = Math.fma(weight, otherX, sd[0]);
        dd[1] = Math.fma(weight, otherY, sd[1]);
        dd[2] = Math.fma(weight, otherZ, sd[2]);
        dd[3] = Math.fma(weight, otherW, sd[3]);
        return dest;
    }


    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public float angle() {
        float[] sd = this.data;
        return 2.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]))), sd[3]);
    }


    /**
     * Compute the angle in radians between this quaternion and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians between this quaternion and {@code other}
     */
    public float angleTo(FloatQuatR other) {
        return angleTo(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code (otherX, otherY, otherZ, otherW)}
     * must have unit length.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public float angleTo(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1]))) > 0.0f) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        float _t13 = sd[3] - _t9;
        float _t14 = sd[2] - _t10;
        float _t15 = sd[0] - _t11;
        float _t16 = sd[1] - _t12;
        float _t17 = sd[3] + _t9;
        float _t18 = sd[2] + _t10;
        float _t19 = sd[0] + _t11;
        float _t20 = sd[1] + _t12;
        return 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }


    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 axis(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 axis(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat calculateW(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        dd[3] = (float) Math.sqrt(Math.max(0.0f, Math.fma(-sd[0], sd[0], Math.fma(-sd[1], sd[1], Math.fma(-sd[2], sd[2], 1.0f)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen) and store the result in
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
    public DoubleQuat calculateW(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _buf0 = sd[0];
        float _buf1 = sd[1];
        float _buf2 = sd[2];
        dd[3] = (float) Math.sqrt(Math.max(0.0f, Math.fma(-sd[0], sd[0], Math.fma(-sd[1], sd[1], Math.fma(-sd[2], sd[2], 1.0f)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat conjugate(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat conjugate(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat conjugateBy(FloatQuatR q, @Mutated FloatQuat dest) {
        return conjugateBy(q.x(), q.y(), q.z(), q.w(), dest);
    }


    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat conjugateBy(FloatQuatR q, @Mutated DoubleQuat dest) {
        return conjugateBy(q.x(), q.y(), q.z(), q.w(), dest);
    }


    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length) and store the result in {@code dest}.
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
    public FloatQuat conjugateBy(float qX, float qY, float qZ, float qW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t20 = Math.fma(qX, sd[1], qW * sd[2]) + Math.fma(qZ, sd[3], -(qY * sd[0]));
        float _t21 = Math.fma(qY, sd[3], qZ * sd[0]) + Math.fma(qW, sd[1], -(qX * sd[2]));
        float _t22 = Math.fma(qX, sd[3], qW * sd[0]) + Math.fma(qY, sd[2], -(qZ * sd[1]));
        float _t23 = Math.fma(qW, sd[3], -(qX * sd[0])) - Math.fma(qY, sd[1], qZ * sd[2]);
        dd[0] = Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23));
        dd[1] = Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20));
        dd[2] = Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23));
        dd[3] = Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21));
        return dest;
    }


    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length) and store the result in {@code dest}.
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
    public DoubleQuat conjugateBy(float qX, float qY, float qZ, float qW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t20 = Math.fma(qX, sd[1], qW * sd[2]) + Math.fma(qZ, sd[3], -(qY * sd[0]));
        float _t21 = Math.fma(qY, sd[3], qZ * sd[0]) + Math.fma(qW, sd[1], -(qX * sd[2]));
        float _t22 = Math.fma(qX, sd[3], qW * sd[0]) + Math.fma(qY, sd[2], -(qZ * sd[1]));
        float _t23 = Math.fma(qW, sd[3], -(qX * sd[0])) - Math.fma(qY, sd[1], qZ * sd[2]);
        dd[0] = Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23));
        dd[1] = Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20));
        dd[2] = Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23));
        dd[3] = Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21));
        return dest;
    }


    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat difference(FloatQuatR other, @Mutated FloatQuat dest) {
        return difference(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat difference(FloatQuatR other, @Mutated DoubleQuat dest) {
        return difference(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat difference(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t3_inv = 1.0f / Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        float _sp1 = _t3_inv * sd[2];
        float _sp0 = sd[1] * _t3_inv;
        float _buf0 = (Math.fma(otherX, sd[3], -(otherW * sd[0])) + Math.fma(otherY, sd[2], -(otherZ * sd[1]))) * _t3_inv;
        float _buf1 = Math.fma(Math.fma(otherY, sd[3], otherZ * sd[0]), _t3_inv, Math.fma(-otherX, _sp1, -(otherW * _sp0)));
        dd[2] = (Math.fma(otherX, sd[1], -(otherW * sd[2])) + Math.fma(otherZ, sd[3], -(otherY * sd[0]))) * _t3_inv;
        dd[3] = Math.fma(Math.fma(otherX, sd[0], otherW * sd[3]), _t3_inv, -Math.fma(-otherZ, _sp1, -(otherY * _sp0)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat difference(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t3_inv = 1.0f / Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        float _sp1 = _t3_inv * sd[2];
        float _sp0 = sd[1] * _t3_inv;
        float _buf0 = (Math.fma(otherX, sd[3], -(otherW * sd[0])) + Math.fma(otherY, sd[2], -(otherZ * sd[1]))) * _t3_inv;
        float _buf1 = Math.fma(Math.fma(otherY, sd[3], otherZ * sd[0]), _t3_inv, Math.fma(-otherX, _sp1, -(otherW * _sp0)));
        dd[2] = (Math.fma(otherX, sd[1], -(otherW * sd[2])) + Math.fma(otherZ, sd[3], -(otherY * sd[0]))) * _t3_inv;
        dd[3] = Math.fma(Math.fma(otherX, sd[0], otherW * sd[3]), _t3_inv, -Math.fma(-otherZ, _sp1, -(otherY * _sp0)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Compute the dot product of this quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this quaternion and {@code other}
     */
    public float dot(FloatQuatR other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the dot product of this quaternion and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public float dot(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        return Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1])));
    }


    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat exp(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.min((float) Math.exp(sd[3]), 3.4028235E38f);
        float _t4 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            dd[0] = sd[0] * _t10;
            dd[1] = sd[1] * _t10;
            dd[2] = sd[2] * _t10;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.cosFromSin(_t7, _t5) * _t2;
        return dest;
    }


    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat exp(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.min((float) Math.exp(sd[3]), 3.4028235E38f);
        float _t4 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            dd[0] = sd[0] * _t10;
            dd[1] = sd[1] * _t10;
            dd[2] = sd[2] * _t10;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.cosFromSin(_t7, _t5) * _t2;
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], _t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[2] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t9, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t12));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], _t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[2] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t9, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t12));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], -_t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[1] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t7, _t9);
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f));
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], -_t1), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[1] = 0.0f;
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t7, _t9);
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t0), 1.0f));
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[2] = 0.0f;
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t8, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(_t9, (float) Math.sqrt(_t12));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t3), 1.0f));
            dd[2] = 0.0f;
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(_t8, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t3), 1.0f));
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(_t9, (float) Math.sqrt(_t12));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = 0.0f;
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[1] = (float) Math.atan2(_t8, _t9);
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = 0.0f;
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], -(sd[1] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[1] = (float) Math.atan2(_t8, _t9);
            dd[0] = _buf0;
        }
        dd[2] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t1), 1.0f));
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = (float) Math.atan2(_t8, _t9);
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]), Math.fma(-2.0f, Math.fma(sd[1], sd[1], _t1), 1.0f));
            dd[1] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[2])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = (float) Math.atan2(_t8, _t9);
            dd[1] = _buf0;
        }
        dd[0] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = (float) Math.atan2(_t7, _t9);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: this quaternion must have unit length.
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
            float _buf0 = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[2], sd[3], -(sd[0] * sd[1])), Math.fma(-2.0f, Math.fma(sd[0], sd[0], _t0), 1.0f));
            dd[0] = _buf0;
        } else {
            float _buf0 = (float) Math.atan2(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]), Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f));
            dd[2] = (float) Math.atan2(_t7, _t9);
            dd[0] = _buf0;
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat integrate(Float3R angularVel, float dt, @Mutated FloatQuat dest) {
        return integrate(angularVel.x(), angularVel.y(), angularVel.z(), dt, dest);
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat integrate(Float3R angularVel, float dt, @Mutated DoubleQuat dest) {
        return integrate(angularVel.x(), angularVel.y(), angularVel.z(), dt, dest);
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVelX the {@code x} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelY the {@code y} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelZ the {@code z} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat integrate(float angularVelX, float angularVelY, float angularVelZ, float dt, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) Math.sqrt(_t6);
        float _t9 = (float) Math.sin(_t7);
        float _t10 = (float) Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _buf0 = Math.fma(sd[0], _t10, sd[3] * _t15) + Math.fma(sd[2], _t16, -(sd[1] * _t17));
        float _buf1 = Math.fma(sd[0], _t17, sd[3] * _t16) + Math.fma(sd[1], _t10, -(sd[2] * _t15));
        float _buf2 = Math.fma(sd[1], _t15, sd[2] * _t10) + Math.fma(sd[3], _t17, -(sd[0] * _t16));
        dd[3] = Math.fma(sd[3], _t10, -(sd[0] * _t15)) - Math.fma(sd[1], _t16, sd[2] * _t17);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVelX the {@code x} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelY the {@code y} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelZ the {@code z} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat integrate(float angularVelX, float angularVelY, float angularVelZ, float dt, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) Math.sqrt(_t6);
        float _t9 = (float) Math.sin(_t7);
        float _t10 = (float) Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _buf0 = Math.fma(sd[0], _t10, sd[3] * _t15) + Math.fma(sd[2], _t16, -(sd[1] * _t17));
        float _buf1 = Math.fma(sd[0], _t17, sd[3] * _t16) + Math.fma(sd[1], _t10, -(sd[2] * _t15));
        float _buf2 = Math.fma(sd[1], _t15, sd[2] * _t10) + Math.fma(sd[3], _t17, -(sd[0] * _t16));
        dd[3] = Math.fma(sd[3], _t10, -(sd[0] * _t15)) - Math.fma(sd[1], _t16, sd[2] * _t17);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNegativeX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNegativeY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNegativeZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedNegativeX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = Math.fma(2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0f);
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3])));
        dd[2] = -(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedNegativeX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = Math.fma(2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0f);
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3])));
        dd[2] = -(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedNegativeY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]));
        float _buf1 = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0f);
        dd[2] = -(2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedNegativeY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]));
        float _buf1 = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0f);
        dd[2] = -(2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedNegativeZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3])));
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]));
        dd[2] = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedNegativeZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3])));
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]));
        dd[2] = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedPositiveX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedPositiveX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedPositiveY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _buf1 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        dd[2] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedPositiveY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _buf1 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        dd[2] = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invNormalizedPositiveZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _buf1 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[2] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNormalizedPositiveZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _buf1 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[2] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invPositiveX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t12 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t12 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invPositiveY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t9 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t9 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 invPositiveZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t10 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t10 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this quaternion
     */
    public float length() {
        float[] sd = this.data;
        return (float) Math.sqrt(Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
    }


    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    public float lengthSquared() {
        float[] sd = this.data;
        return Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
    }


    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat log(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t6 = (float) Math.atan2((float) Math.sqrt(_t2), sd[3]) * (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dd[0] = sd[0] * _t6;
            dd[1] = sd[1] * _t6;
            dd[2] = sd[2] * _t6;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.log((float) Math.sqrt(Math.fma(sd[3], sd[3], _t2)));
        return dest;
    }


    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat log(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t6 = (float) Math.atan2((float) Math.sqrt(_t2), sd[3]) * (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dd[0] = sd[0] * _t6;
            dd[1] = sd[1] * _t6;
            dd[2] = sd[2] * _t6;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.log((float) Math.sqrt(Math.fma(sd[3], sd[3], _t2)));
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 negativeX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 negativeY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 negativeZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0f;
            dd[1] = -0.0f;
            dd[2] = -0.0f;
        }
        return dest;
    }


    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat normalize(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t3 = Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat normalize(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t3 = Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedNegativeX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = Math.fma(2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0f);
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]));
        dd[2] = -(2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedNegativeX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = Math.fma(2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0f);
        float _buf1 = -(2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]));
        dd[2] = -(2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedNegativeY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3])));
        float _buf1 = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0f);
        dd[2] = -(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedNegativeY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3])));
        float _buf1 = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0f);
        dd[2] = -(2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedNegativeZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]));
        float _buf1 = -(2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3])));
        dd[2] = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedNegativeZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = -(2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]));
        float _buf1 = -(2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3])));
        dd[2] = Math.fma(2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedPositiveX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedPositiveX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        dd[2] = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedPositiveY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _buf1 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        dd[2] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedPositiveY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _buf1 = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0f);
        dd[2] = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 normalizedPositiveZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _buf1 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[2] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 normalizedPositiveZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _buf0 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _buf1 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[2] = Math.fma(-2.0f, Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0f);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 positiveX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t12 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t12 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 positiveY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t10 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        float _t10 = 2.0f * Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        float _t12 = Math.fma(-sd[2], sd[2], Math.fma(sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t10 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 positiveZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t9 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        float _t10 = 2.0f * Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        float _t12 = Math.fma(sd[2], sd[2], Math.fma(-sd[1], sd[1], Math.fma(sd[3], sd[3], -(sd[0] * sd[0]))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dd[0] = _t9 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        return dest;
    }


    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat pow(float t, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t12 = Math.min((float) Math.exp(t == 0.0f ? 0.0f : t * (float) Math.log((float) Math.sqrt(Math.fma(sd[3], sd[3], _t2)))), 3.4028235E38f);
        float _t13 = (float) Math.atan2((float) Math.sqrt(_t2), sd[3]) * (1.0f / (float) Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * sd[2] * _t13;
            _t21 = t * sd[0] * _t13;
            _t22 = t * sd[1] * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) Math.sqrt(_t25);
        float _t28 = (float) Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            dd[0] = _t21 * _t31;
            dd[1] = _t22 * _t31;
            dd[2] = _t20 * _t31;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.cosFromSin(_t28, _t26) * _t12;
        return dest;
    }


    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat pow(float t, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t2 = Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        float _t12 = Math.min((float) Math.exp(t == 0.0f ? 0.0f : t * (float) Math.log((float) Math.sqrt(Math.fma(sd[3], sd[3], _t2)))), 3.4028235E38f);
        float _t13 = (float) Math.atan2((float) Math.sqrt(_t2), sd[3]) * (1.0f / (float) Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * sd[2] * _t13;
            _t21 = t * sd[0] * _t13;
            _t22 = t * sd[1] * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) Math.sqrt(_t25);
        float _t28 = (float) Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            dd[0] = _t21 * _t31;
            dd[1] = _t22 * _t31;
            dd[2] = _t20 * _t31;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
        }
        dd[3] = (float) Math.cosFromSin(_t28, _t26) * _t12;
        return dest;
    }


    /**
     * Pre-multiply {@code other} onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat premul(FloatQuatR other, @Mutated FloatQuat dest) {
        return preMul(other, dest);
    }


    /**
     * Pre-multiply {@code other} onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
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
    public DoubleQuat premul(FloatQuatR other, @Mutated DoubleQuat dest) {
        return preMul(other, dest);
    }


    /**
     * Pre-multiply ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) onto this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat premul(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        return preMul(otherX, otherY, otherZ, otherW, dest);
    }


    /**
     * Pre-multiply ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) onto this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat premul(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        return preMul(otherX, otherY, otherZ, otherW, dest);
    }


    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTowards(FloatQuatR target, float step, @Mutated FloatQuat dest) {
        return rotateTowards(target.x(), target.y(), target.z(), target.w(), step, dest);
    }


    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTowards(FloatQuatR target, float step, @Mutated DoubleQuat dest) {
        return rotateTowards(target.x(), target.y(), target.z(), target.w(), step, dest);
    }


    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTowards(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t7 = Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY)));
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        float _t17 = sd[3] - _t13;
        float _t18 = sd[2] - _t14;
        float _t19 = sd[0] - _t15;
        float _t20 = sd[1] - _t16;
        float _t21 = sd[3] + _t13;
        float _t22 = sd[2] + _t14;
        float _t23 = sd[0] + _t15;
        float _t24 = sd[1] + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        return rotateTowards_sa40886ef_1(dest, sd, dd, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f);
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateTowards_sa40886ef_1(FloatQuat dest, float[] sd, float[] dd, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39) {
        float _t40 = 1.0f - _t39;
        float _t42 = (float) Math.sin(_t11 * _t39);
        float _t44 = (float) Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(sd[3], _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(sd[2], _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(sd[0], _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(sd[1], _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(sd[3], _t40, _t13 * _t39);
            _t66 = Math.fma(sd[2], _t40, _t14 * _t39);
            _t67 = Math.fma(sd[0], _t40, _t15 * _t39);
            _t68 = Math.fma(sd[1], _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) Math.sqrt(_t72));
        if (_t72 != 0.0f) {
            dd[0] = _t73 * _t67;
            dd[1] = _t73 * _t68;
            dd[2] = _t73 * _t66;
            dd[3] = _t73 * _t65;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTowards(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t7 = Math.fma(sd[3], targetW, Math.fma(sd[2], targetZ, Math.fma(sd[0], targetX, sd[1] * targetY)));
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        float _t17 = sd[3] - _t13;
        float _t18 = sd[2] - _t14;
        float _t19 = sd[0] - _t15;
        float _t20 = sd[1] - _t16;
        float _t21 = sd[3] + _t13;
        float _t22 = sd[2] + _t14;
        float _t23 = sd[0] + _t15;
        float _t24 = sd[1] + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        return rotateTowards_se7198d92_1(dest, sd, dd, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f);
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_se7198d92_1(DoubleQuat dest, float[] sd, double[] dd, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39) {
        float _t40 = 1.0f - _t39;
        float _t42 = (float) Math.sin(_t11 * _t39);
        float _t44 = (float) Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(sd[3], _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(sd[2], _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(sd[0], _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(sd[1], _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(sd[3], _t40, _t13 * _t39);
            _t66 = Math.fma(sd[2], _t40, _t14 * _t39);
            _t67 = Math.fma(sd[0], _t40, _t15 * _t39);
            _t68 = Math.fma(sd[1], _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) Math.sqrt(_t72));
        if (_t72 != 0.0f) {
            dd[0] = _t73 * _t67;
            dd[1] = _t73 * _t68;
            dd[2] = _t73 * _t66;
            dd[3] = _t73 * _t65;
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
    public FloatQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatQuat dest) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
    public DoubleQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleQuat dest) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_scecfb61a_1(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_scecfb61a_1(float dirX, float dirY, float dirZ, FloatQuat dest, float[] sd, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = Math.fma(-_t37, _t45, 1.0f);
        float _t65 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        return lookAlong_scecfb61a_2(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t65), Math.fma(-dirY, _t16, _t65), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) Math.sqrt(_t78)), 0.5f * (1.0f / (float) Math.sqrt(_t80)), Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50))), 0.5f * (1.0f / (float) Math.sqrt(_t81)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_scecfb61a_2(float dirZ, FloatQuat dest, float[] sd, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t84));
        float _t126, _t127, _t128, _t129;
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) Math.sqrt(_t78);
        } else {
            if (_t46 > Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        return lookAlong_scecfb61a_3(dest, sd, dd, _t126, _t127, _t128, _t129, Math.fma(sd[0], _t129, sd[3] * _t126) + Math.fma(sd[1], _t127, -(sd[2] * _t128)), Math.fma(sd[1], _t129, sd[2] * _t126) + Math.fma(sd[3], _t128, -(sd[0] * _t127)), Math.fma(sd[0], _t128, sd[3] * _t127) + Math.fma(sd[2], _t129, -(sd[1] * _t126)));
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_scecfb61a_3(FloatQuat dest, float[] sd, float[] dd, float _t126, float _t127, float _t128, float _t129, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t129, -(sd[0] * _t126)) - Math.fma(sd[1], _t128, sd[2] * _t127);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
    public DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_sa9c2740d_1(dirX, dirY, dirZ, dest, sd, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_sa9c2740d_1(float dirX, float dirY, float dirZ, DoubleQuat dest, float[] sd, double[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t38, float _t39, float _t45) {
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t50 = Math.fma(-_t37, _t45, 1.0f);
        float _t65 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        return lookAlong_sa9c2740d_2(dirZ, dest, sd, dd, _t16, _t17, _t37, _t45, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t65), Math.fma(-dirY, _t16, _t65), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) Math.sqrt(_t78)), 0.5f * (1.0f / (float) Math.sqrt(_t80)), Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50))), 0.5f * (1.0f / (float) Math.sqrt(_t81)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_sa9c2740d_2(float dirZ, DoubleQuat dest, float[] sd, double[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t84));
        float _t126, _t127, _t128, _t129;
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) Math.sqrt(_t78);
        } else {
            if (_t46 > Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        return lookAlong_sa9c2740d_3(dest, sd, dd, _t126, _t127, _t128, _t129, Math.fma(sd[0], _t129, sd[3] * _t126) + Math.fma(sd[1], _t127, -(sd[2] * _t128)), Math.fma(sd[1], _t129, sd[2] * _t126) + Math.fma(sd[3], _t128, -(sd[0] * _t127)), Math.fma(sd[0], _t128, sd[3] * _t127) + Math.fma(sd[2], _t129, -(sd[1] * _t126)));
    }

    /**
     * Piece 4 of {@code lookAlong}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_sa9c2740d_3(DoubleQuat dest, float[] sd, double[] dd, float _t126, float _t127, float _t128, float _t129, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t129, -(sd[0] * _t126)) - Math.fma(sd[1], _t128, sd[2] * _t127);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }




    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_degenerate_sbe35b51b_1(dest, sd, dd, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, Math.fma(_t43, _t25, _t22), Math.fma(_t43, _t26, _t23), Math.fma(_t43, _t24, _t21));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_degenerate_sbe35b51b_1(FloatQuat dest, float[] sd, float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46) {
        float _t55 = Math.fma(_t44, _t26, -(_t45 * _t25));
        float _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        float _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_sbe35b51b_2(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, -_t68, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_degenerate_sbe35b51b_2(FloatQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96) {
        float _t98 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31)));
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        float _t102 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t103 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_sbe35b51b_3(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) Math.sqrt(_t98)), 0.5f * (1.0f / (float) Math.sqrt(_t102)), 0.5f * (1.0f / (float) Math.sqrt(_t103)), Math.fma(_t66, _t64, _t91), Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_degenerate_sbe35b51b_3(FloatQuat dest, float[] sd, float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) Math.sqrt(_t98);
        } else {
            if (_t69 > Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        return lookAlong_scecfb61a_3(dest, sd, dd, _t148, _t149, _t150, _t151, Math.fma(sd[0], _t151, sd[3] * _t148) + Math.fma(sd[1], _t149, -(sd[2] * _t150)), Math.fma(sd[1], _t151, sd[2] * _t148) + Math.fma(sd[3], _t150, -(sd[0] * _t149)), Math.fma(sd[0], _t150, sd[3] * _t149) + Math.fma(sd[2], _t151, -(sd[1] * _t148)));
    }



    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_degenerate_s6c7891ae_1(dest, sd, dd, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, Math.fma(_t43, _t25, _t22), Math.fma(_t43, _t26, _t23), Math.fma(_t43, _t24, _t21));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s6c7891ae_1(DoubleQuat dest, float[] sd, double[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46) {
        float _t55 = Math.fma(_t44, _t26, -(_t45 * _t25));
        float _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        float _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return lookAlong_degenerate_s6c7891ae_2(dest, sd, dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, -_t68, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s6c7891ae_2(DoubleQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96) {
        float _t98 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31)));
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        float _t102 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t103 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s6c7891ae_3(dest, sd, dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) Math.sqrt(_t98)), 0.5f * (1.0f / (float) Math.sqrt(_t102)), 0.5f * (1.0f / (float) Math.sqrt(_t103)), Math.fma(_t66, _t64, _t91), Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s6c7891ae_3(DoubleQuat dest, float[] sd, double[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp3, float _t102, float _t103, float _sp0, float _sp1, float _sp2, float _t114, float _t115) {
        float _t148, _t149, _t150, _t151;
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) Math.sqrt(_t98);
        } else {
            if (_t69 > Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        return lookAlong_sa9c2740d_3(dest, sd, dd, _t148, _t149, _t150, _t151, Math.fma(sd[0], _t151, sd[3] * _t148) + Math.fma(sd[1], _t149, -(sd[2] * _t150)), Math.fma(sd[1], _t151, sd[2] * _t148) + Math.fma(sd[3], _t150, -(sd[0] * _t149)), Math.fma(sd[0], _t150, sd[3] * _t149) + Math.fma(sd[2], _t151, -(sd[1] * _t148)));
    }


    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated FloatQuat makeRotationAxis(float angle, Float3R axis) {
        return makeRotationAxis(angle, axis.x(), axis.y(), axis.z());
    }


    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis ({@code axisX},
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
    @Mutated public FloatQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = (float) Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this quaternion to a rotation that makes {@code +z} point along {@code dir}.
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
    public @Mutated FloatQuat makeRotationLookAlong(Float3R dir, Float3R up) {
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Set this quaternion to a rotation that makes {@code +z} point along ({@code dirX},
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
    @Mutated public FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) Math.sqrt(_t8));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) Math.sqrt(_ct0));
        return makeRotationLookAlong_s25627a91_1(dirX, dirY, dirZ, dd, -dirZ, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37, _t39, _t45, _t37 * _t45, _t38 * _t45, _t39 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_s25627a91_1(float dirX, float dirY, float dirZ, float[] dd, float _t1, float _t16, float _t17, float _t18, float _t19, float _t20, float _t22, float _t37, float _t39, float _t45, float _t46, float _t47, float _t48) {
        float _t50 = Math.fma(-_t37, _t45, 1.0f);
        float _t64 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        float _t82 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s25627a91_2(dirZ, dd, _t16, _t17, _t37, _t45, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t64), Math.fma(-dirY, _t16, _t64), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, 0.5f * (1.0f / (float) Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) Math.sqrt(_t81)), 0.5f * (1.0f / (float) Math.sqrt(_t80)), 0.5f * (1.0f / (float) Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_s25627a91_2(float dirZ, float[] dd, float _t16, float _t17, float _t37, float _t45, float _t46, float _t51, float _t56, float _t63, float _t69, float _t70, float _t74, float _t75, float _t78, float _sp1, float _t80, float _t81, float _t82, float _sp3, float _sp4, float _sp2) {
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0f) {
            dd[0] = _sp1 * _t70;
            dd[1] = _sp1 * _t56;
            dd[2] = _sp1 * _t75;
            dd[3] = 0.5f * (float) Math.sqrt(_t78);
        } else {
            if (_t46 > Math.max(_t63, _t17)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t80);
                dd[1] = _sp4 * _t74;
                dd[2] = _sp4 * _t51;
                dd[3] = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    dd[0] = _sp2 * _t74;
                    dd[1] = 0.5f * (float) Math.sqrt(_t82);
                    dd[2] = _sp2 * _t69;
                    dd[3] = _sp2 * _t56;
                } else {
                    dd[0] = _sp3 * _t51;
                    dd[1] = _sp3 * _t69;
                    dd[2] = 0.5f * (float) Math.sqrt(_t81);
                    dd[3] = _sp3 * _t75;
                }
            }
        }
        return this;
    }



    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] dd = this.data;
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
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (Math.abs(_t25) > Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        return makeRotationLookAlong_degenerate_s1ae3d4da_1(dd, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, Math.fma(_t43, _t25, _t22), Math.fma(_t43, _t26, _t23), Math.fma(_t43, _t24, _t21));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_degenerate_s1ae3d4da_1(float[] dd, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t44, float _t45, float _t46) {
        float _t55 = Math.fma(_t44, _t26, -(_t45 * _t25));
        float _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        float _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        return makeRotationLookAlong_degenerate_s1ae3d4da_2(dd, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, -_t68, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_degenerate_s1ae3d4da_2(float[] dd, float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96) {
        float _t98 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31)));
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        float _t101 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t102 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_degenerate_s1ae3d4da_3(dd, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5f * (1.0f / (float) Math.sqrt(_t98)), _t101, _t102, 0.5f * (1.0f / (float) Math.sqrt(_t99)), 0.5f * (1.0f / (float) Math.sqrt(_t101)), 0.5f * (1.0f / (float) Math.sqrt(_t102)), Math.fma(_t66, _t64, _t91), Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_degenerate_s1ae3d4da_3(float[] dd, float _t24, float _t25, float _t63, float _t66, float _t69, float _t70, float _t73, float _t76, float _t87, float _t95, float _t96, float _t98, float _t99, float _sp0, float _t101, float _t102, float _sp3, float _sp1, float _sp2, float _t106, float _t107) {
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0f) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t76;
            dd[2] = _sp0 * _t107;
            dd[3] = 0.5f * (float) Math.sqrt(_t98);
        } else {
            if (_t69 > Math.max(_t87, _t24)) {
                dd[0] = 0.5f * (float) Math.sqrt(_t99);
                dd[1] = _sp3 * _t106;
                dd[2] = _sp3 * _t73;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    dd[0] = _sp1 * _t106;
                    dd[1] = 0.5f * (float) Math.sqrt(_t101);
                    dd[2] = _sp1 * _t95;
                    dd[3] = _sp1 * _t76;
                } else {
                    dd[0] = _sp2 * _t73;
                    dd[1] = _sp2 * _t95;
                    dd[2] = 0.5f * (float) Math.sqrt(_t102);
                    dd[3] = _sp2 * _t107;
                }
            }
        }
        return this;
    }


    /**
     * Set this quaternion to the rotation that rotates {@code fromDir} onto {@code toDir} (for
     * opposite vectors an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return this
     */
    public @Mutated FloatQuat makeRotationTo(Float3R fromDir, Float3R toDir) {
        return makeRotationTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z());
    }


    /**
     * Set this quaternion to the rotation that rotates ({@code fromDirX}, {@code fromDirY},
     * {@code fromDirZ}) onto ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors
     * an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @return this
     */
    @Mutated public FloatQuat makeRotationTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float[] dd = this.data;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t24 = 0.5f * _t23;
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t32 = (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 6.0E-8f) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0f) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0f;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
        }
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public FloatQuat makeRotationX(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = _t1;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = (float) Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
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
    @Mutated public FloatQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
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
    @Mutated public FloatQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public FloatQuat makeRotationY(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = _t1;
        dd[2] = 0.0f;
        dd[3] = (float) Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
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
    @Mutated public FloatQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[2] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
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
    @Mutated public FloatQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t5, _t2);
        float _t7 = (float) Math.cosFromSin(_t3, _t0);
        float _t8 = (float) Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        dd[0] = Math.fma(_t9, _t6, _t12 * _t5);
        dd[1] = Math.fma(_t10, _t6, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dd[3] = Math.fma(_t12, _t6, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public FloatQuat makeRotationZ(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = _t1;
        dd[3] = (float) Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
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
    @Mutated public FloatQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dd[0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[1] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[2] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
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
    @Mutated public FloatQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        float[] dd = this.data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        dd[0] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[1] = Math.fma(_t10, _t8, _t11 * _t5);
        dd[2] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[3] = Math.fma(_t9, _t5, _t12 * _t8);
        return this;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat preRotateX(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, -(sd[2] * _t1));
        dd[2] = Math.fma(sd[1], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    public DoubleQuat preRotateX(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, -(sd[2] * _t1));
        dd[2] = Math.fma(sd[1], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat preRotateY(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[2] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    public DoubleQuat preRotateY(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[2] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat preRotateZ(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t1));
        dd[1] = Math.fma(sd[0], _t1, sd[1] * _t2);
        float _buf1 = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        return dest;
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    public DoubleQuat preRotateZ(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t1));
        dd[1] = Math.fma(sd[0], _t1, sd[1] * _t2);
        float _buf1 = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateAxis(float angle, Float3R axis, @Mutated FloatQuat dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateAxis(float angle, Float3R axis, @Mutated DoubleQuat dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public FloatQuat rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t5, sd[3] * _t2) + Math.fma(sd[1], _t3, -(sd[2] * _t4));
        float _buf1 = Math.fma(sd[1], _t5, sd[2] * _t2) + Math.fma(sd[3], _t4, -(sd[0] * _t3));
        float _buf2 = Math.fma(sd[0], _t4, sd[3] * _t3) + Math.fma(sd[2], _t5, -(sd[1] * _t2));
        dd[3] = Math.fma(sd[3], _t5, -(sd[0] * _t2)) - Math.fma(sd[1], _t4, sd[2] * _t3);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleQuat dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t5, sd[3] * _t2) + Math.fma(sd[1], _t3, -(sd[2] * _t4));
        float _buf1 = Math.fma(sd[1], _t5, sd[2] * _t2) + Math.fma(sd[3], _t4, -(sd[0] * _t3));
        float _buf2 = Math.fma(sd[0], _t4, sd[3] * _t3) + Math.fma(sd[2], _t5, -(sd[1] * _t2));
        dd[3] = Math.fma(sd[3], _t5, -(sd[0] * _t2)) - Math.fma(sd[1], _t4, sd[2] * _t3);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTo(Float3R fromDir, Float3R toDir, @Mutated FloatQuat dest) {
        return rotateTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z(), dest);
    }


    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(Float3R fromDir, Float3R toDir, @Mutated DoubleQuat dest) {
        return rotateTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z(), dest);
    }


    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s793ef4ad_1(dest, sd, dd, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) Math.sqrt(_t29)), (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateTo_s793ef4ad_1(FloatQuat dest, float[] sd, float[] dd, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        float _buf0 = Math.fma(sd[0], _t44, sd[3] * _t45) + Math.fma(sd[1], _t46, -(sd[2] * _t47));
        float _buf1 = Math.fma(sd[1], _t44, sd[2] * _t45) + Math.fma(sd[3], _t47, -(sd[0] * _t46));
        float _buf2 = Math.fma(sd[0], _t47, sd[3] * _t46) + Math.fma(sd[2], _t44, -(sd[1] * _t45));
        dd[3] = Math.fma(sd[3], _t44, -(sd[0] * _t45)) - Math.fma(sd[1], _t47, sd[2] * _t46);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s785516c_1(dest, sd, dd, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) Math.sqrt(_t29)), (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTo_s785516c_1(DoubleQuat dest, float[] sd, double[] dd, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        float _buf0 = Math.fma(sd[0], _t44, sd[3] * _t45) + Math.fma(sd[1], _t46, -(sd[2] * _t47));
        float _buf1 = Math.fma(sd[1], _t44, sd[2] * _t45) + Math.fma(sd[3], _t47, -(sd[0] * _t46));
        float _buf2 = Math.fma(sd[0], _t47, sd[3] * _t46) + Math.fma(sd[2], _t44, -(sd[1] * _t45));
        dd[3] = Math.fma(sd[3], _t44, -(sd[0] * _t45)) - Math.fma(sd[1], _t47, sd[2] * _t46);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateX(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, sd[2] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[1] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis and store the result
     * in {@code dest}.
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
    public DoubleQuat rotateX(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[3] * _t1);
        float _buf1 = Math.fma(sd[1], _t2, sd[2] * _t1);
        dd[2] = Math.fma(sd[2], _t2, -(sd[1] * _t1));
        dd[3] = Math.fma(sd[3], _t2, -(sd[0] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateXYZ_s80b2582a_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateXYZ_s80b2582a_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateXYZ_sd0b3e83d_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateXYZ_sd0b3e83d_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        return rotateXZY_s53a376c0_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateXZY_s53a376c0_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        return rotateXZY_sb2ef2107_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t20, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t22, -(sd[0] * _t20)), Math.fma(sd[0], _t22, sd[3] * _t20) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateXZY_sb2ef2107_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateY(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, -(sd[2] * _t1));
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[0], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis and store the result
     * in {@code dest}.
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
    public DoubleQuat rotateY(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, -(sd[2] * _t1));
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t1);
        dd[2] = Math.fma(sd[0], _t1, sd[2] * _t2);
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateYXZ_s846e5c04_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t20) + Math.fma(sd[1], _t21, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t20) + Math.fma(sd[3], _t22, -(sd[0] * _t21)), Math.fma(sd[0], _t22, sd[3] * _t21) + Math.fma(sd[2], _t19, -(sd[1] * _t20)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateYXZ_s846e5c04_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t20)) - Math.fma(sd[1], _t22, sd[2] * _t21);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return rotateYXZ_s6880c12b_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t20) + Math.fma(sd[1], _t21, -(sd[2] * _t22)), Math.fma(sd[1], _t19, sd[2] * _t20) + Math.fma(sd[3], _t22, -(sd[0] * _t21)), Math.fma(sd[0], _t22, sd[3] * _t21) + Math.fma(sd[2], _t19, -(sd[1] * _t20)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateYXZ_s6880c12b_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t20)) - Math.fma(sd[1], _t22, sd[2] * _t21);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateYZX_s4c73b52c_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateYZX_s4c73b52c_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateYZX_sdbe4fec3_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t19) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t19) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateYZX_sdbe4fec3_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t19)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateZ(float angle, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[1] * _t1);
        dd[1] = Math.fma(sd[1], _t2, -(sd[0] * _t1));
        float _buf1 = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        return dest;
    }


    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis and store the result
     * in {@code dest}.
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
    public DoubleQuat rotateZ(float angle, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        float _buf0 = Math.fma(sd[0], _t2, sd[1] * _t1);
        dd[1] = Math.fma(sd[1], _t2, -(sd[0] * _t1));
        float _buf1 = Math.fma(sd[2], _t2, sd[3] * _t1);
        dd[3] = Math.fma(sd[3], _t2, -(sd[2] * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZXY_sfadc833c_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t22) + Math.fma(sd[1], _t19, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t22) + Math.fma(sd[3], _t20, -(sd[0] * _t19)), Math.fma(sd[0], _t20, sd[3] * _t19) + Math.fma(sd[2], _t21, -(sd[1] * _t22)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateZXY_sfadc833c_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t22)) - Math.fma(sd[1], _t20, sd[2] * _t19);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZXY_s492143b3_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t21, sd[3] * _t22) + Math.fma(sd[1], _t19, -(sd[2] * _t20)), Math.fma(sd[1], _t21, sd[2] * _t22) + Math.fma(sd[3], _t20, -(sd[0] * _t19)), Math.fma(sd[0], _t20, sd[3] * _t19) + Math.fma(sd[2], _t21, -(sd[1] * _t22)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateZXY_s492143b3_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t21, -(sd[0] * _t22)) - Math.fma(sd[1], _t20, sd[2] * _t19);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZYX_s9ba4b1ee_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateZYX_s9ba4b1ee_1(FloatQuat dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    public DoubleQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return rotateZYX_sc0e454b1_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[0], _t19, sd[3] * _t21) + Math.fma(sd[1], _t22, -(sd[2] * _t20)), Math.fma(sd[1], _t19, sd[2] * _t21) + Math.fma(sd[3], _t20, -(sd[0] * _t22)), Math.fma(sd[0], _t20, sd[3] * _t22) + Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateZYX_sc0e454b1_1(DoubleQuat dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1, float _buf2) {
        dd[3] = Math.fma(sd[3], _t19, -(sd[0] * _t21)) - Math.fma(sd[1], _t20, sd[2] * _t22);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Transform {@code v} by this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transform(Float3R v, @Mutated Float3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform {@code v} by this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Float3R v, @Mutated Double3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        float _buf0 = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        float _buf1 = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vY, -(sd[1] * vX));
        float _t10 = 2.0f * Math.fma(sd[2], vX, -(sd[0] * vZ));
        float _t11 = 2.0f * Math.fma(sd[1], vZ, -(sd[2] * vY));
        float _buf0 = Math.fma(sd[1], _t9, Math.fma(-sd[2], _t10, Math.fma(sd[3], _t11, vX)));
        float _buf1 = Math.fma(sd[2], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vY)));
        dd[2] = Math.fma(sd[0], _t10, Math.fma(-sd[1], _t11, Math.fma(sd[3], _t9, vZ)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Transform {@code v} by the inverse of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformInverse(Float3R v, @Mutated Float3 dest) {
        return transformInverse(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform {@code v} by the inverse of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Float3R v, @Mutated Double3 dest) {
        return transformInverse(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by the inverse of this quaternion and store
     * the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
        float _buf0 = Math.fma(sd[2], _t9, Math.fma(-sd[1], _t10, Math.fma(sd[3], _t11, vX)));
        float _buf1 = Math.fma(sd[0], _t10, Math.fma(-sd[2], _t11, Math.fma(sd[3], _t9, vY)));
        dd[2] = Math.fma(sd[1], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vZ)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by the inverse of this quaternion and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[0], vZ, -(sd[2] * vX));
        float _t10 = 2.0f * Math.fma(sd[1], vX, -(sd[0] * vY));
        float _t11 = 2.0f * Math.fma(sd[2], vY, -(sd[1] * vZ));
        float _buf0 = Math.fma(sd[2], _t9, Math.fma(-sd[1], _t10, Math.fma(sd[3], _t11, vX)));
        float _buf1 = Math.fma(sd[0], _t10, Math.fma(-sd[2], _t11, Math.fma(sd[3], _t9, vY)));
        dd[2] = Math.fma(sd[1], _t11, Math.fma(-sd[0], _t9, Math.fma(sd[3], _t10, vZ)));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }

    public float x() { return data[0]; }
    public float y() { return data[1]; }
    public float z() { return data[2]; }
    public float w() { return data[3]; }

    @Override public String toString() {
        return "FloatQuat(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatQuatImpl)) return false;
        FloatQuatImpl o = (FloatQuatImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(FloatQuatR other, float epsilon) {
        return Math.abs(data[0] - other.x()) <= epsilon
            && Math.abs(data[1] - other.y()) <= epsilon
            && Math.abs(data[2] - other.z()) <= epsilon
            && Math.abs(data[3] - other.w()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatQuat load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatQuat load(double[] src, int offset) {
        this.data[0] = (float) src[offset + 0];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatQuat loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatQuat storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatQuat loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(d))
                : (float) Math.PI - 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(s));
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
