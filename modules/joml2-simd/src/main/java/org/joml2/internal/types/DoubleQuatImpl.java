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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleQuat} backed by a {@code double[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleQuatImpl implements DoubleQuat {
    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleQuatSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatSegOpsUnsafe()
            : new DoubleQuatSegOpsMS();
        static final DoubleQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatBbOpsUnsafe()
            : new DoubleQuatBbOpsApi();
        static final DoubleQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatRawOpsUnsafe()
            : new DoubleQuatRawOpsApi();
    }

    public DoubleQuatImpl() {
        data = new double[4];
        data[3] = 1;
    }

    public DoubleQuatImpl(double x, double y, double z, double w) {
        double[] dd = this.data = new double[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public DoubleQuatImpl(DoubleQuatR src) {
        double[] srcData = ((DoubleQuatImpl) src).data;
        double[] dd = this.data = new double[4];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
    }

    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invert(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return invert_narrow(dest);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3_inv = 1.0 / java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
            _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, _t3_inv)).blend(_sv0.mul(DoubleVector.broadcast(_COL_SPECIES, -_t3_inv)), MASK_0).intoArray(dd, 0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3_inv = 1.0 / ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
            _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, _t3_inv)).blend(_sv0.mul(DoubleVector.broadcast(_COL_SPECIES, -_t3_inv)), MASK_0).intoArray(dd, 0);
            return dest;
        }
    }

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1}, and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invertProduct(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = java.lang.Math.fma(otherData[0], sd[3], otherData[3] * sd[0]) + java.lang.Math.fma(otherData[2], sd[1], -(otherData[1] * sd[2]));
        double _t21 = java.lang.Math.fma(otherData[3], sd[3], -otherData[0] * sd[0]) - java.lang.Math.fma(otherData[1], sd[1], otherData[2] * sd[2]);
        double _t22 = java.lang.Math.fma(otherData[1], sd[0], otherData[2] * sd[3]) + java.lang.Math.fma(otherData[3], sd[2], -otherData[0] * sd[1]);
        double _t23 = java.lang.Math.fma(otherData[0], sd[2], otherData[3] * sd[1]) + java.lang.Math.fma(otherData[1], sd[3], -(otherData[2] * sd[0]));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dd[0] = -(_t20 * _t27_inv);
        dd[1] = -(_t23 * _t27_inv);
        dd[2] = -(_t22 * _t27_inv);
        dd[3] = _t21 * _t27_inv;
        return dest;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat invertProduct_mulAdd(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = ((otherData[0]) * (sd[3]) + (otherData[3] * sd[0])) + ((otherData[2]) * (sd[1]) - (otherData[1] * sd[2]));
        double _t21 = ((otherData[3]) * (sd[3]) - (otherData[0] * sd[0])) - ((otherData[1]) * (sd[1]) + (otherData[2] * sd[2]));
        double _t22 = ((otherData[1]) * (sd[0]) + (otherData[2] * sd[3])) + ((otherData[3]) * (sd[2]) - (otherData[0] * sd[1]));
        double _t23 = ((otherData[0]) * (sd[2]) + (otherData[3] * sd[1])) + ((otherData[1]) * (sd[3]) - (otherData[2] * sd[0]));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
    public DoubleQuat invertProduct(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = java.lang.Math.fma(otherX, sd[3], otherW * sd[0]) + java.lang.Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        double _t21 = java.lang.Math.fma(otherW, sd[3], -otherX * sd[0]) - java.lang.Math.fma(otherY, sd[1], otherZ * sd[2]);
        double _t22 = java.lang.Math.fma(otherY, sd[0], otherZ * sd[3]) + java.lang.Math.fma(otherW, sd[2], -otherX * sd[1]);
        double _t23 = java.lang.Math.fma(otherX, sd[2], otherW * sd[1]) + java.lang.Math.fma(otherY, sd[3], -(otherZ * sd[0]));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dd[0] = -(_t20 * _t27_inv);
        dd[1] = -(_t23 * _t27_inv);
        dd[2] = -(_t22 * _t27_inv);
        dd[3] = _t21 * _t27_inv;
        return dest;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat invertProduct_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = ((otherX) * (sd[3]) + (otherW * sd[0])) + ((otherZ) * (sd[1]) - (otherY * sd[2]));
        double _t21 = ((otherW) * (sd[3]) - (otherX * sd[0])) - ((otherY) * (sd[1]) + (otherZ * sd[2]));
        double _t22 = ((otherY) * (sd[0]) + (otherZ * sd[3])) + ((otherW) * (sd[2]) - (otherX * sd[1]));
        double _t23 = ((otherX) * (sd[2]) + (otherW * sd[1])) + ((otherY) * (sd[3]) - (otherZ * sd[0]));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
    public DoubleQuat add(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return add_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, otherData, 0).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
    public DoubleQuat add(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
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
    public DoubleQuat mul(double scalar, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return mul_narrow(scalar, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, scalar).mul(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
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
    public DoubleQuat negate(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return negate_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).neg().intoArray(dd, 0);
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
    public DoubleQuat sub(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return sub_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).sub(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
    public DoubleQuat sub(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
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
     * Valid input: {@code u1} must lie in {@code [0, 1]}; {@code u2} must lie in {@code [0, 1]};
     * {@code u3} must lie in {@code [0, 1]}.
     *
     * @param u1 the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed rotation
     * @param u2 the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @param u3 the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @return this
     */
    @Mutated public DoubleQuat makeUniformRotation(double u1, double u2, double u3) {
        double[] dd = this.data;
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = java.lang.Math.sqrt(1.0 - u1);
        dd[0] = Math.sinWithCos(_t1) * _t4;
        dd[1] = Math.cosWithSin(_t1) * _t4;
        dd[2] = Math.sinWithCos(_t3) * _t0;
        dd[3] = Math.cosWithSin(_t3) * _t0;
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
    @Mutated public DoubleQuat set(DoubleQuatR v) {
        if (COL_NARROW) return set_narrow(v);
        double[] dd = this.data;
        double[] vData = ((DoubleQuatImpl) v).data;
        DoubleVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
        return this;
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
    @Mutated public DoubleQuat set(double vX, double vY, double vZ, double vW) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
        return this;
    }

    /**
     * Convert this quaternion to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat toFloat(@Mutated FloatQuat dest) {
        double[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
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
    @Mutated public DoubleQuat makeFromDualQuat(DoubleDualQuatR dq) {
        if (COL_NARROW) return makeFromDualQuat_narrow(dq);
        double[] dd = this.data;
        double[] dqData = ((DoubleDualQuatImpl) dq).data;
        DoubleVector.fromArray(COL_SPECIES, dqData, 0).intoArray(dd, 0);
        return this;
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
    @Mutated public DoubleQuat makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double[] dd = this.data;
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
    @Mutated public DoubleQuat makeFromMatrix(Double3x3R m) {
        double[] mData = ((Double3x3Impl) m).data;
        double _t0 = mData[0] + mData[4];
        double _t10 = mData[8] + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (mData[0] - (mData[4] + mData[8]));
        double _t16 = 1.0 + (mData[4] - (mData[0] + mData[8]));
        double _t17 = 1.0 + (mData[8] - _t0);
        return makeFromMatrix_s81574d1e_1(this.data, mData, mData[5] - mData[7], mData[3] + mData[1], mData[6] + mData[2], mData[6] - mData[2], mData[7] + mData[5], mData[1] - mData[3], _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeFromMatrix_s81574d1e_1(double[] dd, double[] mData, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dd[0] = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (mData[0] > Math.max(mData[4], mData[8])) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
            } else {
                if (mData[4] > mData[8]) {
                    dd[0] = _sp1 * _t4;
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
        return this;
    }

    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleQuat makeFromMatrix(Double3x4R m) {
        double[] mData = ((Double3x4Impl) m).data;
        double _t0 = mData[0] + mData[5];
        double _t10 = mData[10] + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (mData[0] - (mData[5] + mData[10]));
        double _t16 = 1.0 + (mData[5] - (mData[0] + mData[10]));
        double _t17 = 1.0 + (mData[10] - _t0);
        return makeFromMatrix_s89828b35_1(this.data, mData, mData[9] - mData[6], mData[1] + mData[4], mData[2] + mData[8], mData[2] - mData[8], mData[6] + mData[9], mData[4] - mData[1], _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleQuat makeFromMatrix_s89828b35_1(double[] dd, double[] mData, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dd[0] = _sp0 * _t1;
            dd[1] = _sp0 * _t7;
            dd[2] = _sp0 * _t9;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (mData[0] > Math.max(mData[5], mData[10])) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t15);
                dd[1] = _sp3 * _t4;
                dd[2] = _sp3 * _t6;
                dd[3] = _sp3 * _t1;
            } else {
                if (mData[5] > mData[10]) {
                    dd[0] = _sp1 * _t4;
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
        return this;
    }

    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleQuat makeFromMatrix(Double4x4R m) {
        double[] mData = ((Double4x4Impl) m).data;
        double _t0 = mData[0] + mData[5];
        double _t10 = mData[10] + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (mData[0] - (mData[5] + mData[10]));
        double _t16 = 1.0 + (mData[5] - (mData[0] + mData[10]));
        double _t17 = 1.0 + (mData[10] - _t0);
        return makeFromMatrix_s89828b35_1(this.data, mData, mData[6] - mData[9], mData[4] + mData[1], mData[8] + mData[2], mData[8] - mData[2], mData[9] + mData[6], mData[1] - mData[4], _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
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
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        if (COL_NARROW) return toDualQuat_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        VEC_1.intoArray(dd, 4);
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
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        if (COL_NARROW) return toMatrix_narrow(dest);
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0);
        dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2);
        dd[3] = 0.0;
        dd[4] = 2.0 * java.lang.Math.fma(sd[0], sd[1], -_t1);
        dd[5] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0);
        dd[6] = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[7] = 0.0;
        dd[8] = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        dd[9] = 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[10] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        VEC_1.intoArray(dd, 11);
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t0))));
        dd[1] = 2.0 * ((sd[0]) * (sd[1]) + (_t1));
        dd[2] = 2.0 * ((sd[0]) * (sd[2]) - (_t2));
        dd[3] = 0.0;
        dd[4] = 2.0 * ((sd[0]) * (sd[1]) - (_t1));
        dd[5] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t0))));
        dd[6] = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        dd[7] = 0.0;
        dd[8] = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        dd[9] = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        dd[10] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        VEC_1.intoArray(dd, 11);
        dd[15] = 1.0;
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
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0);
        dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t1);
        dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2);
        dd[3] = 2.0 * java.lang.Math.fma(sd[0], sd[1], -_t1);
        dd[4] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0);
        dd[5] = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[6] = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        dd[7] = 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[8] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t0))));
        dd[1] = 2.0 * ((sd[0]) * (sd[1]) + (_t1));
        dd[2] = 2.0 * ((sd[0]) * (sd[2]) - (_t2));
        dd[3] = 2.0 * ((sd[0]) * (sd[1]) - (_t1));
        dd[4] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t0))));
        dd[5] = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        dd[6] = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        dd[7] = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        dd[8] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
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
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0);
        dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[1], -_t1);
        dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        dd[3] = 0.0;
        dd[4] = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t1);
        dd[5] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0);
        dd[6] = 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
        dd[7] = 0.0;
        dd[8] = 2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2);
        dd[9] = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        dd[10] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        dd[11] = 0.0;
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        dd[0] = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t0))));
        dd[1] = 2.0 * ((sd[0]) * (sd[1]) - (_t1));
        dd[2] = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        dd[3] = 0.0;
        dd[4] = 2.0 * ((sd[0]) * (sd[1]) + (_t1));
        dd[5] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t0))));
        dd[6] = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        dd[7] = 0.0;
        dd[8] = 2.0 * ((sd[0]) * (sd[2]) - (_t2));
        dd[9] = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        dd[10] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        dd[11] = 0.0;
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Decompose this quaternion into a twist about {@code axis} followed by a swing about an axis
     * perpendicular to it, storing them in {@code twist} and {@code swing} respectively, such that
     * {@code swing * twist} is this rotation (the twist acts on a vector first).
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity rotation ({@code IDENTITY} or its negation) when the rotation is a
     * pure swing; in the 180-degree perpendicular case, where it is undefined, it is
     * {@code IDENTITY}.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    public DoubleQuat decomposeSwingTwist(Double3R axis, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return decomposeSwingTwist_mulAdd(axisX, axisY, axisZ, swing, twist);
        double[] sd = this.data;
        double _t2 = java.lang.Math.fma(axisZ, sd[2], java.lang.Math.fma(axisX, sd[0], axisY * sd[1]));
        double _t4 = java.lang.Math.fma(sd[3], sd[3], _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return decomposeSwingTwist_se8845677_1_fma(sd, ((DoubleQuatImpl) swing).data, ((DoubleQuatImpl) twist).data, _t11, _t12, _t13, _t14, java.lang.Math.fma(sd[0], _t11, -(sd[3] * _t12)) + java.lang.Math.fma(sd[2], _t13, -(sd[1] * _t14)), java.lang.Math.fma(sd[0], _t14, -(sd[3] * _t13)) + java.lang.Math.fma(sd[1], _t11, -(sd[2] * _t12)), java.lang.Math.fma(sd[1], _t12, sd[2] * _t11) + java.lang.Math.fma(-sd[0], _t13, -(sd[3] * _t14)));
    }

    /**
     * Decompose this quaternion into a twist about ({@code axisX}, {@code axisY}, {@code axisZ})
     * followed by a swing about an axis perpendicular to it, storing them in {@code twist} and
     * {@code swing} respectively, such that {@code swing * twist} is this rotation (the twist acts
     * on a vector first).
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity rotation ({@code IDENTITY} or its negation) when the rotation is a
     * pure swing; in the 180-degree perpendicular case, where it is undefined, it is
     * {@code IDENTITY}.
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
    public DoubleQuat decomposeSwingTwist(double axisX, double axisY, double axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        if (!org.joml2.internal.FmaSwitch.ON) return decomposeSwingTwist_mulAdd(axisX, axisY, axisZ, swing, twist);
        double[] sd = this.data;
        double _t2 = java.lang.Math.fma(axisZ, sd[2], java.lang.Math.fma(axisX, sd[0], axisY * sd[1]));
        double _t4 = java.lang.Math.fma(sd[3], sd[3], _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return decomposeSwingTwist_se8845677_1_fma(sd, ((DoubleQuatImpl) swing).data, ((DoubleQuatImpl) twist).data, _t11, _t12, _t13, _t14, java.lang.Math.fma(sd[0], _t11, -(sd[3] * _t12)) + java.lang.Math.fma(sd[2], _t13, -(sd[1] * _t14)), java.lang.Math.fma(sd[0], _t14, -(sd[3] * _t13)) + java.lang.Math.fma(sd[1], _t11, -(sd[2] * _t12)), java.lang.Math.fma(sd[1], _t12, sd[2] * _t11) + java.lang.Math.fma(-sd[0], _t13, -(sd[3] * _t14)));
    }

    /** {@code decomposeSwingTwist} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat decomposeSwingTwist_mulAdd(double axisX, double axisY, double axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        double[] sd = this.data;
        double _t2 = ((axisZ) * (sd[2]) + (((axisX) * (sd[0]) + (axisY * sd[1]))));
        double _t4 = ((sd[3]) * (sd[3]) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double[] swingData = ((DoubleQuatImpl) swing).data;
        double[] twistData = ((DoubleQuatImpl) twist).data;
        double _d0buf0 = ((sd[0]) * (_t11) - (sd[3] * _t12)) + ((sd[2]) * (_t13) - (sd[1] * _t14));
        double _d0buf1 = ((sd[0]) * (_t14) - (sd[3] * _t13)) + ((sd[1]) * (_t11) - (sd[2] * _t12));
        double _d0buf2 = ((sd[1]) * (_t12) + (sd[2] * _t11)) + ((-sd[0]) * (_t13) - (sd[3] * _t14));
        swingData[3] = ((sd[0]) * (_t12) + (sd[3] * _t11)) - ((-sd[2]) * (_t14) - (sd[1] * _t13));
        twistData[0] = _t12;
        twistData[1] = _t13;
        twistData[2] = _t14;
        twistData[3] = _t11;
        swingData[0] = _d0buf0;
        swingData[1] = _d0buf1;
        swingData[2] = _d0buf2;
        return this;
    }

    /** Piece 2 of {@code decomposeSwingTwist}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeSwingTwist_se8845677_1_fma(double[] sd, double[] swingData, double[] twistData, double _t11, double _t12, double _t13, double _t14, double _d0buf0, double _d0buf1, double _d0buf2) {
        swingData[3] = java.lang.Math.fma(sd[0], _t12, sd[3] * _t11) - java.lang.Math.fma(-sd[2], _t14, -(sd[1] * _t13));
        twistData[0] = _t12;
        twistData[1] = _t13;
        twistData[2] = _t14;
        twistData[3] = _t11;
        swingData[0] = _d0buf0;
        swingData[1] = _d0buf1;
        swingData[2] = _d0buf2;
        return this;
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition, and store the result in {@code dest}.
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
    public DoubleQuat getSwing(Double3R axis, @Mutated DoubleQuat dest) {
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (org.joml2.internal.FmaSwitch.ON) return getSwing_fma(axisX, axisY, axisZ, dest);
        return getSwing_mulAdd(axisX, axisY, axisZ, dest);
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, and store the result in
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
    public DoubleQuat getSwing(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getSwing_fma(axisX, axisY, axisZ, dest);
        return getSwing_mulAdd(axisX, axisY, axisZ, dest);
    }

    /** {@code getSwing} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat getSwing_fma(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = java.lang.Math.fma(axisZ, sd[2], java.lang.Math.fma(axisX, sd[0], axisY * sd[1]));
        double _t4 = java.lang.Math.fma(sd[3], sd[3], _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _buf0 = java.lang.Math.fma(sd[0], _t11, -sd[3] * _t12) + java.lang.Math.fma(sd[2], _t13, -sd[1] * _t14);
        double _buf1 = java.lang.Math.fma(sd[0], _t14, -sd[3] * _t13) + java.lang.Math.fma(sd[1], _t11, -sd[2] * _t12);
        double _buf2 = java.lang.Math.fma(sd[1], _t12, sd[2] * _t11) + java.lang.Math.fma(-sd[0], _t13, -sd[3] * _t14);
        dd[3] = java.lang.Math.fma(sd[0], _t12, sd[3] * _t11) - java.lang.Math.fma(-sd[2], _t14, -sd[1] * _t13);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code getSwing} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat getSwing_mulAdd(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = ((axisZ) * (sd[2]) + (((axisX) * (sd[0]) + (axisY * sd[1]))));
        double _t4 = ((sd[3]) * (sd[3]) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = sd[3] * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _buf0 = ((sd[0]) * (_t11) - (sd[3] * _t12)) + ((sd[2]) * (_t13) - (sd[1] * _t14));
        double _buf1 = ((sd[0]) * (_t14) - (sd[3] * _t13)) + ((sd[1]) * (_t11) - (sd[2] * _t12));
        double _buf2 = ((sd[1]) * (_t12) + (sd[2] * _t11)) + ((-sd[0]) * (_t13) - (sd[3] * _t14));
        dd[3] = ((sd[0]) * (_t12) + (sd[3] * _t11)) - ((-sd[2]) * (_t14) - (sd[1] * _t13));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition, and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity rotation ({@code IDENTITY}
     * or its negation) when the rotation is a pure swing, and {@code IDENTITY} in the 180-degree
     * perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getTwist(Double3R axis, @Mutated DoubleQuat dest) {
        double[] axisData = ((Double3Impl) axis).data;
        return getTwist(axisData[0], axisData[1], axisData[2], dest);
    }

    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, and store the result in
     * {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity rotation ({@code IDENTITY}
     * or its negation) when the rotation is a pure swing, and {@code IDENTITY} in the 180-degree
     * perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getTwist(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = java.lang.Math.fma(axisZ, sd[2], java.lang.Math.fma(axisX, sd[0], axisY * sd[1]));
            double _t4 = java.lang.Math.fma(sd[3], sd[3], _t2 * _t2);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                dd[0] = axisX * _t6;
                dd[1] = axisY * _t6;
                dd[2] = axisZ * _t6;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = ((axisZ) * (sd[2]) + (((axisX) * (sd[0]) + (axisY * sd[1]))));
            double _t4 = ((sd[3]) * (sd[3]) + (_t2 * _t2));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                dd[0] = axisX * _t6;
                dd[1] = axisY * _t6;
                dd[2] = axisZ * _t6;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
            }
            return dest;
        }
    }

    /**
     * Set this quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleQuat makeIdentity() {
        if (COL_NARROW) return makeIdentity_narrow();
        double[] dd = this.data;
        VEC_2.intoArray(dd, 0);
        return this;
    }

    /**
     * Set all components of this quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleQuat makeZero() {
        if (COL_NARROW) return makeZero_narrow();
        double[] dd = this.data;
        DoubleVector.broadcast(COL_SPECIES, 0.0).intoArray(dd, 0);
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lerp(DoubleQuatR other, double t, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return lerp_narrow(other, t, dest);
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private DoubleQuat lerp_fma(DoubleQuatR other, double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat lerp_mulAdd(DoubleQuatR other, double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0)).add(_sv0).intoArray(dd, 0);
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
     * magnitude than the other one (1e17 towards 1 ends at 0).
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
    public DoubleQuat lerp(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return lerp_narrow(otherX, otherY, otherZ, otherW, t, dest);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        if (SimdMath.USE_FMA) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
            DoubleVector.broadcast(_COL_SPECIES, t).fma(DoubleVector.zero(_COL_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dd, 0);
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        DoubleVector.broadcast(_COL_SPECIES, t).mul(DoubleVector.zero(_COL_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dd, 0);
        return dest;
    }

    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code alpha} must lie in {@code [0, 1]}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerp(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return nlerp_narrow(target, alpha, dest);
        if (SimdMath.USE_FMA) return nlerp_fma(target, alpha, dest);
        return nlerp_mulAdd(target, alpha, dest);
    }

    private DoubleQuat nlerp_fma(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.broadcast(_COL_SPECIES, alpha).fma(DoubleVector.fromArray(_COL_SPECIES, targetData, 0), DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, 1.0 - alpha)));
        double _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0 ? _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t12)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat nlerp_mulAdd(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.broadcast(_COL_SPECIES, alpha).mul(DoubleVector.fromArray(_COL_SPECIES, targetData, 0)).add(DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, 1.0 - alpha)));
        double _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0 ? _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t12)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
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
     * Valid input: {@code alpha} must lie in {@code [0, 1]}.
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
    public DoubleQuat nlerp(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return nlerp_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        if (!org.joml2.internal.FmaSwitch.ON) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, sd[3] * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, sd[2] * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, sd[0] * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, sd[1] * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dd[0] = _t7 * _t13;
            dd[1] = _t8 * _t13;
            dd[2] = _t6 * _t13;
            dd[3] = _t5 * _t13;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * {@code nlerp} with plain multiply-adds ({@code joml.useFma}). Shared by 2 identical private
     * paths of {@code nlerp}; reached only through it.
     */
    private DoubleQuat nlerp_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (targetW) + (sd[3] * _t0));
        double _t6 = ((alpha) * (targetZ) + (sd[2] * _t0));
        double _t7 = ((alpha) * (targetX) + (sd[0] * _t0));
        double _t8 = ((alpha) * (targetY) + (sd[1] * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dd[0] = _t7 * _t13;
            dd[1] = _t8 * _t13;
            dd[2] = _t6 * _t13;
            dd[3] = _t5 * _t13;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}), or at its negation - the same rotation -
     * when the two quaternions' dot product is negative.
     * <p>
     * Valid input: {@code alpha} must lie in {@code [0, 1]}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerpShortest(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return nlerpShortest_narrow(target, alpha, dest);
        if (SimdMath.USE_FMA) return nlerpShortest_fma(target, alpha, dest);
        return nlerpShortest_mulAdd(target, alpha, dest);
    }

    private DoubleQuat nlerpShortest_fma(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _r0 = sd[3];
        double _r1 = targetData[3];
        double _r2 = sd[2];
        double _r3 = targetData[2];
        double _r4 = sd[0];
        double _r5 = targetData[0];
        double _r6 = sd[1];
        double _r7 = targetData[1];
        nlerpShortest_s2dc9352d_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, alpha, sd, targetData);
        return dest;
    }

    private DoubleQuat nlerpShortest_mulAdd(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _r0 = sd[3];
        double _r1 = targetData[3];
        double _r2 = sd[2];
        double _r3 = targetData[2];
        double _r4 = sd[0];
        double _r5 = targetData[0];
        double _r6 = sd[1];
        double _r7 = targetData[1];
        nlerpShortest_s362bc8aa_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, alpha, sd, targetData);
        return dest;
    }

    /** Private vector tail of {@code nlerpShortest_s2dc9352d}: loads, computes and stores every column; reached only through it. */
    private static void nlerpShortest_s2dc9352d_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double alpha, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = DoubleVector.broadcast(_COL_SPECIES, alpha).fma((-java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6 * _r7))) > 0.0 ? _sv0.neg() : _sv0), _sv1.mul(DoubleVector.broadcast(_COL_SPECIES, 1.0 - alpha)));
        double _t25 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0 ? _sv2.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t25)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /** Private vector tail of {@code nlerpShortest_s362bc8aa}: loads, computes and stores every column; reached only through it. */
    private static void nlerpShortest_s362bc8aa_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double alpha, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = DoubleVector.broadcast(_COL_SPECIES, alpha).mul((-((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6 * _r7)))))) > 0.0 ? _sv0.neg() : _sv0)).add(_sv1.mul(DoubleVector.broadcast(_COL_SPECIES, 1.0 - alpha)));
        double _t25 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0 ? _sv2.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t25)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}), or at its negation - the same rotation - when the two quaternions' dot product is
     * negative.
     * <p>
     * Valid input: {@code alpha} must lie in {@code [0, 1]}.
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
    public DoubleQuat nlerpShortest(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return nlerpShortest_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        if (org.joml2.internal.FmaSwitch.ON) return nlerpShortest_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    /**
     * {@code nlerpShortest} with fused multiply-adds ({@code joml.useFma}). Shared by 2 identical
     * private paths of {@code nlerpShortest}; reached only through it.
     */
    private DoubleQuat nlerpShortest_fma(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY))) > 0.0) {
            _t18 = java.lang.Math.fma(alpha, -targetW, sd[3] * _t1);
            _t19 = java.lang.Math.fma(alpha, -targetZ, sd[2] * _t1);
            _t20 = java.lang.Math.fma(alpha, -targetX, sd[0] * _t1);
            _t21 = java.lang.Math.fma(alpha, -targetY, sd[1] * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, targetW, sd[3] * _t1);
            _t19 = java.lang.Math.fma(alpha, targetZ, sd[2] * _t1);
            _t20 = java.lang.Math.fma(alpha, targetX, sd[0] * _t1);
            _t21 = java.lang.Math.fma(alpha, targetY, sd[1] * _t1);
        }
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dd[0] = _t20 * _t26;
            dd[1] = _t21 * _t26;
            dd[2] = _t19 * _t26;
            dd[3] = _t18 * _t26;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * {@code nlerpShortest} with plain multiply-adds ({@code joml.useFma}). Shared by 2 identical
     * private paths of {@code nlerpShortest}; reached only through it.
     */
    private DoubleQuat nlerpShortest_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((sd[3]) * (targetW) + (((sd[2]) * (targetZ) + (((sd[0]) * (targetX) + (sd[1] * targetY)))))) > 0.0) {
            _t18 = ((sd[3] * _t1) - (alpha) * (targetW));
            _t19 = ((sd[2] * _t1) - (alpha) * (targetZ));
            _t20 = ((sd[0] * _t1) - (alpha) * (targetX));
            _t21 = ((sd[1] * _t1) - (alpha) * (targetY));
        } else {
            _t18 = ((alpha) * (targetW) + (sd[3] * _t1));
            _t19 = ((alpha) * (targetZ) + (sd[2] * _t1));
            _t20 = ((alpha) * (targetX) + (sd[0] * _t1));
            _t21 = ((alpha) * (targetY) + (sd[1] * _t1));
        }
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dd[0] = _t20 * _t26;
            dd[1] = _t21 * _t26;
            dd[2] = _t19 * _t26;
            dd[3] = _t18 * _t26;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc. For nearly opposite quaternions -
     * nearly the same rotation, taken the long way round - both interpolation weights grow like 1 /
     * sin of the angle between them and largely cancel, so the error grows like an ulp over that
     * sine, up to a few percent at the smallest one this method still interpolates along (1e-6 in
     * {@code float}, 1e-14 in {@code double}); the shorter arc has no such loss.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length;
     * {@code alpha} must lie in {@code [0, 1]}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerp(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return slerp_narrow(target, alpha, dest);
        if (SimdMath.USE_FMA) return slerp_fma(target, alpha, dest);
        return slerp_mulAdd(target, alpha, dest);
    }

    private DoubleQuat slerp_fma(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetData[3];
        double _t3 = sd[2] + targetData[2];
        double _t4 = sd[0] + targetData[0];
        double _t5 = sd[1] + targetData[1];
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (SimdMath.USE_FMA) return slerp_degenerate_fma(target, alpha, dest);
            return slerp_degenerate_mulAdd(target, alpha, dest);
        }
        double _t20_inv = 1.0 / _ct0;
        double _t22 = _t17 * _t20_inv;
        double _t24 = java.lang.Math.fma(-(java.lang.Math.fma(0.5, _t10, -1.0) * _t17), _t20_inv, Math.cosWithSin(_t14));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t24 : _t22), DoubleVector.fromArray(COL_SPECIES, targetData, 0).mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t22 : _t24))).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat slerp_mulAdd(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetData[3];
        double _t3 = sd[2] + targetData[2];
        double _t4 = sd[0] + targetData[0];
        double _t5 = sd[1] + targetData[1];
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct1 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct1 > 2.0E-14 && _ct1 < Double.POSITIVE_INFINITY)) {
            if (SimdMath.USE_FMA) return slerp_degenerate_fma(target, alpha, dest);
            return slerp_degenerate_mulAdd(target, alpha, dest);
        }
        double _t20_inv = 1.0 / _ct1;
        double _t22 = _t17 * _t20_inv;
        double _t24 = ((Math.cosWithSin(_t14)) - (((0.5) * (_t10) - (1.0)) * _t17) * (_t20_inv));
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t24 : _t22)).add(DoubleVector.fromArray(COL_SPECIES, targetData, 0).mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t22 : _t24))).intoArray(dd, 0);
        return dest;
    }

    /**
     * Spherically interpolate between this quaternion and ({@code targetX}, {@code targetY},
     * {@code targetZ}, {@code targetW}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc. For nearly opposite quaternions -
     * nearly the same rotation, taken the long way round - both interpolation weights grow like 1 /
     * sin of the angle between them and largely cancel, so the error grows like an ulp over that
     * sine, up to a few percent at the smallest one this method still interpolates along (1e-6 in
     * {@code float}, 1e-14 in {@code double}); the shorter arc has no such loss.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length; {@code alpha} must lie in {@code [0, 1]}.
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
    public DoubleQuat slerp(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return slerp_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        if (SimdMath.USE_FMA) return slerp_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    private DoubleQuat slerp_fma(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetW;
        double _t3 = sd[2] + targetZ;
        double _t4 = sd[0] + targetX;
        double _t5 = sd[1] + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return slerp_degenerate(targetX, targetY, targetZ, targetW, alpha, dest);
        double _t20_inv = 1.0 / _ct0;
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        {
            double _t22 = _t17 * _t20_inv;
            double _t24 = java.lang.Math.fma(-(java.lang.Math.fma(0.5, _t10, -1.0) * _t17), _t20_inv, Math.cosWithSin(_t14));
            var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
            _sv0.fma(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t24 : _t22), DoubleVector.zero(COL_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t22 : _t24))).intoArray(dd, 0);
        }
        return dest;
    }

    private DoubleQuat slerp_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetW;
        double _t3 = sd[2] + targetZ;
        double _t4 = sd[0] + targetX;
        double _t5 = sd[1] + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _ct1 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct1 > 2.0E-14 && _ct1 < Double.POSITIVE_INFINITY)) return slerp_degenerate(targetX, targetY, targetZ, targetW, alpha, dest);
        double _t20_inv = 1.0 / _ct1;
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        {
            double _t22 = _t17 * _t20_inv;
            double _t24 = ((Math.cosWithSin(_t14)) - (((0.5) * (_t10) - (1.0)) * _t17) * (_t20_inv));
            var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
            _sv0.mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t24 : _t22)).add(DoubleVector.zero(COL_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(COL_SPECIES, alpha < 0.5 ? _t22 : _t24))).intoArray(dd, 0);
        }
        return dest;
    }

    private DoubleQuat slerp_degenerate_fma(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetData[3];
        double _t2 = sd[2] + targetData[2];
        double _t3 = sd[0] + targetData[0];
        double _t4 = sd[1] + targetData[1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = 4.0 - _t11;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? 1.0 - alpha : _t5), DoubleVector.fromArray(COL_SPECIES, targetData, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? alpha : 1.0 - _t5))).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat slerp_degenerate_mulAdd(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetData[3];
        double _t2 = sd[2] + targetData[2];
        double _t3 = sd[0] + targetData[0];
        double _t4 = sd[1] + targetData[1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t12 = 4.0 - _t11;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? 1.0 - alpha : _t5)).add(DoubleVector.fromArray(COL_SPECIES, targetData, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? alpha : 1.0 - _t5))).intoArray(dd, 0);
        return dest;
    }

    /**
     * Degenerate-arc path of {@code slerp}: its methods leave here when the two quaternions are
     * equal or opposite to within the precision (the sine of the angle between them is too small to
     * divide by) or NaN; reached only through them.
     */
    private DoubleQuat slerp_degenerate(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (SimdMath.USE_FMA) return slerp_degenerate_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return slerp_degenerate_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    private DoubleQuat slerp_degenerate_fma(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetW;
        double _t2 = sd[2] + targetZ;
        double _t3 = sd[0] + targetX;
        double _t4 = sd[1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = 4.0 - _t11;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? 1.0 - alpha : _t5), DoubleVector.zero(COL_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? alpha : 1.0 - _t5))).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat slerp_degenerate_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetW;
        double _t2 = sd[2] + targetZ;
        double _t3 = sd[0] + targetX;
        double _t4 = sd[1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t12 = 4.0 - _t11;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? 1.0 - alpha : _t5)).add(DoubleVector.zero(COL_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(COL_SPECIES, _t11 > _t12 ? alpha : 1.0 - _t5))).intoArray(dd, 0);
        return dest;
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}), or at its negation - the same rotation -
     * when the two quaternions' dot product is negative.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length;
     * {@code alpha} must lie in {@code [0, 1]}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat slerpShortest(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return slerpShortest_narrow(target, alpha, dest);
        if (SimdMath.USE_FMA) return slerpShortest_fma(target, alpha, dest);
        return slerpShortest_mulAdd(target, alpha, dest);
    }

    private DoubleQuat slerpShortest_fma(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t12 = java.lang.Math.fma(sd[3], targetData[3], java.lang.Math.fma(sd[2], targetData[2], java.lang.Math.fma(sd[0], targetData[0], sd[1] * targetData[1])));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t30 = java.lang.Math.fma(-_t15, _t15, 1.0);
        slerpShortest_s2dc9352d_tail(dd, 1.0 - alpha, _t12, Math.acos(_t15), _t30, (1.0 / java.lang.Math.sqrt(_t30)), alpha, sd, targetData);
        return dest;
    }

    private DoubleQuat slerpShortest_mulAdd(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t12 = ((sd[3]) * (targetData[3]) + (((sd[2]) * (targetData[2]) + (((sd[0]) * (targetData[0]) + (sd[1] * targetData[1]))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t30 = ((1.0) - (_t15) * (_t15));
        slerpShortest_s362bc8aa_tail(dd, 1.0 - alpha, _t12, Math.acos(_t15), _t30, (1.0 / java.lang.Math.sqrt(_t30)), alpha, sd, targetData);
        return dest;
    }

    /** Private vector tail of {@code slerpShortest_s2dc9352d}: loads, computes and stores every column; reached only through it. */
    private static void slerpShortest_s2dc9352d_tail(double[] dd, double _t0, double _t12, double _t17, double _t30, double _t32, double alpha, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv2 = (-_t12 > 0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (java.lang.Math.sqrt(_t30) > 0.0 ? _sv0.fma(DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t0 * _t17) * _t32), DoubleVector.broadcast(_COL_SPECIES, Math.sin(alpha * _t17) * _t32).mul(_sv2)) : DoubleVector.broadcast(_COL_SPECIES, alpha).fma(_sv2, _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, _t0))));
        double _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0 ? _sv3.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t50)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /** Private vector tail of {@code slerpShortest_s362bc8aa}: loads, computes and stores every column; reached only through it. */
    private static void slerpShortest_s362bc8aa_tail(double[] dd, double _t0, double _t12, double _t17, double _t30, double _t32, double alpha, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv2 = (-_t12 > 0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (java.lang.Math.sqrt(_t30) > 0.0 ? _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t0 * _t17) * _t32)).add(DoubleVector.broadcast(_COL_SPECIES, Math.sin(alpha * _t17) * _t32).mul(_sv2)) : DoubleVector.broadcast(_COL_SPECIES, alpha).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(_COL_SPECIES, _t0))));
        double _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0 ? _sv3.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t50)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}), or at its negation - the same rotation - when the two quaternions' dot product is
     * negative.
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length; {@code alpha} must lie in {@code [0, 1]}.
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
    public DoubleQuat slerpShortest(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return slerpShortest_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        if (!org.joml2.internal.FmaSwitch.ON) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_s5e9c190a_1_fma(alpha, dest, sd, ((DoubleQuatImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /**
     * {@code slerpShortest} with plain multiply-adds ({@code joml.useFma}). Shared by 2 identical
     * private paths of {@code slerpShortest}; reached only through it.
     */
    private DoubleQuat slerpShortest_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 1.0 - alpha;
        double _t12 = ((sd[3]) * (targetW) + (((sd[2]) * (targetZ) + (((sd[0]) * (targetX) + (sd[1] * targetY))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        return slerpShortest_s5e9c190a_1_mulAdd(alpha, dest, sd, ((DoubleQuatImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /**
     * Piece 2 of {@code slerpShortest}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code slerpShortest}; reached only through it.
     */
    private DoubleQuat slerpShortest_s5e9c190a_1_fma(double alpha, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(sd[3], _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(sd[2], _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(sd[0], _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(sd[1], _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, sd[3] * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, sd[2] * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, sd[0] * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, sd[1] * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dd[0] = _t51 * _t45;
            dd[1] = _t51 * _t46;
            dd[2] = _t51 * _t44;
            dd[3] = _t51 * _t43;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * Piece 2 of {@code slerpShortest}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code slerpShortest}; reached only through it.
     */
    private DoubleQuat slerpShortest_s5e9c190a_1_mulAdd(double alpha, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((sd[3]) * (_t34) + (_t33 * _t21));
            _t44 = ((sd[2]) * (_t34) + (_t33 * _t22));
            _t45 = ((sd[0]) * (_t34) + (_t33 * _t23));
            _t46 = ((sd[1]) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (sd[3] * _t0));
            _t44 = ((alpha) * (_t22) + (sd[2] * _t0));
            _t45 = ((alpha) * (_t23) + (sd[0] * _t0));
            _t46 = ((alpha) * (_t24) + (sd[1] * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dd[0] = _t51 * _t45;
            dd[1] = _t51 * _t46;
            dd[2] = _t51 * _t44;
            dd[3] = _t51 * _t43;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length; {@code t} must
     * lie in {@code [0, 1]}.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat squad(DoubleQuatR control0, DoubleQuatR control1, DoubleQuatR target, double t, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return squad_mulAdd(control0, control1, target, t, dest);
        double[] sd = this.data;
        double[] control0Data = ((DoubleQuatImpl) control0).data;
        double[] control1Data = ((DoubleQuatImpl) control1).data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t3 = control0Data[3] + control1Data[3];
        double _t4 = control0Data[2] + control1Data[2];
        double _t5 = control0Data[0] + control1Data[0];
        double _t6 = control0Data[1] + control1Data[1];
        double _t8 = sd[3] + targetData[3];
        double _t9 = sd[2] + targetData[2];
        double _t10 = sd[0] + targetData[0];
        double _t11 = sd[1] + targetData[1];
        double _t12 = _t0 * _t1;
        double _t23 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t24 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        return squad_sc011060c_1_fma(t, dest, sd, control0Data, control1Data, targetData, ((DoubleQuatImpl) dest).data, Math.min(t, _t0), _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)), _t23, _t24, 4.0 - _t23, 4.0 - _t24);
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(DoubleQuatR control0, DoubleQuatR control1, DoubleQuatR target, double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] control0Data = ((DoubleQuatImpl) control0).data;
        double[] control1Data = ((DoubleQuatImpl) control1).data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t3 = control0Data[3] + control1Data[3];
        double _t4 = control0Data[2] + control1Data[2];
        double _t5 = control0Data[0] + control1Data[0];
        double _t6 = control0Data[1] + control1Data[1];
        double _t8 = sd[3] + targetData[3];
        double _t9 = sd[2] + targetData[2];
        double _t10 = sd[0] + targetData[0];
        double _t11 = sd[1] + targetData[1];
        double _t12 = _t0 * _t1;
        double _t23 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t24 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        return squad_sc011060c_1_mulAdd(t, dest, sd, control0Data, control1Data, targetData, ((DoubleQuatImpl) dest).data, Math.min(t, _t0), _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))), _t23, _t24, 4.0 - _t23, 4.0 - _t24);
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_1_fma(double t, DoubleQuat dest, double[] sd, double[] control0Data, double[] control1Data, double[] targetData, double[] dd, double _t7, double _t12, double _t16, double _t23, double _t24, double _t27, double _t28) {
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_sc011060c_2_fma(dest, sd, control0Data, control1Data, targetData, dd, _t12, _t16, _t59, _t60, _t61, _t62, java.lang.Math.fma(control0Data[0], _t59, control1Data[0] * _t60), java.lang.Math.fma(control0Data[3], _t59, control1Data[3] * _t60));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_1_mulAdd(double t, DoubleQuat dest, double[] sd, double[] control0Data, double[] control1Data, double[] targetData, double[] dd, double _t7, double _t12, double _t16, double _t23, double _t24, double _t27, double _t28) {
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_sc011060c_2_mulAdd(dest, sd, control0Data, control1Data, targetData, dd, _t12, _t16, _t59, _t60, _t61, _t62, ((control0Data[0]) * (_t59) + (control1Data[0] * _t60)), ((control0Data[3]) * (_t59) + (control1Data[3] * _t60)));
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_2_fma(DoubleQuat dest, double[] sd, double[] control0Data, double[] control1Data, double[] targetData, double[] dd, double _t12, double _t16, double _t59, double _t60, double _t61, double _t62, double _t71, double _t72) {
        double _t73 = java.lang.Math.fma(sd[3], _t61, targetData[3] * _t62);
        double _t74 = java.lang.Math.fma(control0Data[2], _t59, control1Data[2] * _t60);
        double _t75 = java.lang.Math.fma(sd[2], _t61, targetData[2] * _t62);
        double _t76 = java.lang.Math.fma(sd[0], _t61, targetData[0] * _t62);
        double _t77 = java.lang.Math.fma(control0Data[1], _t59, control1Data[1] * _t60);
        double _t78 = java.lang.Math.fma(sd[1], _t61, targetData[1] * _t62);
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        return squad_sc011060c_3_fma(dest, dd, _t12, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t103, _t104);
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_2_mulAdd(DoubleQuat dest, double[] sd, double[] control0Data, double[] control1Data, double[] targetData, double[] dd, double _t12, double _t16, double _t59, double _t60, double _t61, double _t62, double _t71, double _t72) {
        double _t73 = ((sd[3]) * (_t61) + (targetData[3] * _t62));
        double _t74 = ((control0Data[2]) * (_t59) + (control1Data[2] * _t60));
        double _t75 = ((sd[2]) * (_t61) + (targetData[2] * _t62));
        double _t76 = ((sd[0]) * (_t61) + (targetData[0] * _t62));
        double _t77 = ((control0Data[1]) * (_t59) + (control1Data[1] * _t60));
        double _t78 = ((sd[1]) * (_t61) + (targetData[1] * _t62));
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        return squad_sc011060c_3_mulAdd(dest, dd, _t12, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t103, _t104);
    }

    /** Piece 4 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_3_fma(DoubleQuat dest, double[] dd, double _t12, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t103, double _t104) {
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dd[0] = java.lang.Math.fma(_t71, _t105, _t76 * _t106);
        dd[1] = java.lang.Math.fma(_t77, _t105, _t78 * _t106);
        dd[2] = java.lang.Math.fma(_t74, _t105, _t75 * _t106);
        dd[3] = java.lang.Math.fma(_t72, _t105, _t73 * _t106);
        return dest;
    }

    /** Piece 4 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_sc011060c_3_mulAdd(DoubleQuat dest, double[] dd, double _t12, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t103, double _t104) {
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dd[0] = ((_t71) * (_t105) + (_t76 * _t106));
        dd[1] = ((_t77) * (_t105) + (_t78 * _t106));
        dd[2] = ((_t74) * (_t105) + (_t75 * _t106));
        dd[3] = ((_t72) * (_t105) + (_t73 * _t106));
        return dest;
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length; {@code t} must lie in
     * {@code [0, 1]}.
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
    public DoubleQuat squad(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return squad_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
        return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
    }

    /** {@code squad} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _ld0 = sd[3];
        double _ld1 = sd[2];
        double _ld2 = sd[0];
        double _ld3 = sd[1];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t7 = Math.min(t, _t0);
        double _t23 = squad_s2d5a8506_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W);
        double _t24 = squad_s2d5a8506_2_fma(targetX, targetY, targetZ, targetW, _ld0, _ld1, _ld2, _ld3);
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        return squad_s2d5a8506_4_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest, _ld0, _ld1, _ld2, _ld3, dd, _t0, _t1, _t0 * _t1, _t23, _t24, _t33, _t34, _t41, _t43, _t23 > _t27 ? _t7 : 0.0, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t41 * Math.sinWithCos(_t33), 2.0 / _t43 * Math.sinWithCos(_t34));
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _ld0 = sd[3];
        double _ld1 = sd[2];
        double _ld2 = sd[0];
        double _ld3 = sd[1];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t7 = Math.min(t, _t0);
        double _t23 = squad_s2d5a8506_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W);
        double _t24 = squad_s2d5a8506_2_mulAdd(targetX, targetY, targetZ, targetW, _ld0, _ld1, _ld2, _ld3);
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        return squad_s2d5a8506_4_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest, _ld0, _ld1, _ld2, _ld3, dd, _t0, _t1, _t0 * _t1, _t23, _t24, _t33, _t34, _t41, _t43, _t23 > _t27 ? _t7 : 0.0, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t41 * Math.sinWithCos(_t33), 2.0 / _t43 * Math.sinWithCos(_t34));
    }

    /** Part 1 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double squad_s2d5a8506_1_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W) {
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        return Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
    }

    /** Part 1 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double squad_s2d5a8506_1_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W) {
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        return Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
    }

    /** Part 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double squad_s2d5a8506_2_fma(double targetX, double targetY, double targetZ, double targetW, double _ld0, double _ld1, double _ld2, double _ld3) {
        double _t8 = _ld0 + targetW;
        double _t9 = _ld1 + targetZ;
        double _t10 = _ld2 + targetX;
        double _t11 = _ld3 + targetY;
        return Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
    }

    /** Part 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double squad_s2d5a8506_2_mulAdd(double targetX, double targetY, double targetZ, double targetW, double _ld0, double _ld1, double _ld2, double _ld3) {
        double _t8 = _ld0 + targetW;
        double _t9 = _ld1 + targetZ;
        double _t10 = _ld2 + targetX;
        double _t11 = _ld3 + targetY;
        return Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_3_fma(double control0Y, double control1Y, double targetX, double targetY, DoubleQuat dest, double _ld2, double _ld3, double[] dd, double _t12, double _t16, double _t61, double _t62, double _t59, double _t60, double _t71, double _t72, double _t73, double _t74, double _t75) {
        double _t76 = java.lang.Math.fma(_ld2, _t61, targetX * _t62);
        double _t77 = java.lang.Math.fma(control0Y, _t59, control1Y * _t60);
        double _t78 = java.lang.Math.fma(_ld3, _t61, targetY * _t62);
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dd[0] = java.lang.Math.fma(_t71, _t105, _t76 * _t106);
        dd[1] = java.lang.Math.fma(_t77, _t105, _t78 * _t106);
        dd[2] = java.lang.Math.fma(_t74, _t105, _t75 * _t106);
        dd[3] = java.lang.Math.fma(_t72, _t105, _t73 * _t106);
        return dest;
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_3_mulAdd(double control0Y, double control1Y, double targetX, double targetY, DoubleQuat dest, double _ld2, double _ld3, double[] dd, double _t12, double _t16, double _t61, double _t62, double _t59, double _t60, double _t71, double _t72, double _t73, double _t74, double _t75) {
        double _t76 = ((_ld2) * (_t61) + (targetX * _t62));
        double _t77 = ((control0Y) * (_t59) + (control1Y * _t60));
        double _t78 = ((_ld3) * (_t61) + (targetY * _t62));
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dd[0] = ((_t71) * (_t105) + (_t76 * _t106));
        dd[1] = ((_t77) * (_t105) + (_t78 * _t106));
        dd[2] = ((_t74) * (_t105) + (_t75 * _t106));
        dd[3] = ((_t72) * (_t105) + (_t73 * _t106));
        return dest;
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_4_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuat dest, double _ld0, double _ld1, double _ld2, double _ld3, double[] dd, double _t0, double _t1, double _t12, double _t23, double _t24, double _t33, double _t34, double _t41, double _t43, double _t47, double _t48, double _t51, double _t52) {
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_s2d5a8506_3_fma(control0Y, control1Y, targetX, targetY, dest, _ld2, _ld3, dd, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)), _t61, _t62, _t59, _t60, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_ld0, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_ld1, _t61, targetZ * _t62));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_4_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuat dest, double _ld0, double _ld1, double _ld2, double _ld3, double[] dd, double _t0, double _t1, double _t12, double _t23, double _t24, double _t33, double _t34, double _t41, double _t43, double _t47, double _t48, double _t51, double _t52) {
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_s2d5a8506_3_mulAdd(control0Y, control1Y, targetX, targetY, dest, _ld2, _ld3, dd, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))), _t61, _t62, _t59, _t60, ((control0X) * (_t59) + (control1X * _t60)), ((control0W) * (_t59) + (control1W * _t60)), ((_ld0) * (_t61) + (targetW * _t62)), ((control0Z) * (_t59) + (control1Z * _t60)), ((_ld1) * (_t61) + (targetZ * _t62)));
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
    public DoubleQuat mul(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherData[0], sd[3], otherData[3] * sd[0]) + java.lang.Math.fma(otherData[2], sd[1], -(otherData[1] * sd[2]));
        double _buf1 = java.lang.Math.fma(otherData[0], sd[2], otherData[3] * sd[1]) + java.lang.Math.fma(otherData[1], sd[3], otherData[2] * -sd[0]);
        double _buf2 = java.lang.Math.fma(otherData[1], sd[0], otherData[2] * sd[3]) + java.lang.Math.fma(otherData[3], sd[2], -(otherData[0] * sd[1]));
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], otherData[0] * -sd[0]) - java.lang.Math.fma(otherData[1], sd[1], otherData[2] * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat mul_mulAdd(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((otherData[0]) * (sd[3]) + (otherData[3] * sd[0])) + ((otherData[2]) * (sd[1]) - (otherData[1] * sd[2]));
        double _buf1 = ((otherData[0]) * (sd[2]) + (otherData[3] * sd[1])) + ((otherData[1]) * (sd[3]) - (otherData[2] * sd[0]));
        double _buf2 = ((otherData[1]) * (sd[0]) + (otherData[2] * sd[3])) + ((otherData[3]) * (sd[2]) - (otherData[0] * sd[1]));
        dd[3] = ((otherData[3]) * (sd[3]) - (otherData[0] * sd[0])) - ((otherData[1]) * (sd[1]) + (otherData[2] * sd[2]));
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
    public DoubleQuat mul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherX, sd[3], otherW * sd[0]) + java.lang.Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        double _buf1 = java.lang.Math.fma(otherX, sd[2], otherW * sd[1]) + java.lang.Math.fma(otherY, sd[3], otherZ * -sd[0]);
        double _buf2 = java.lang.Math.fma(otherY, sd[0], otherZ * sd[3]) + java.lang.Math.fma(otherW, sd[2], -(otherX * sd[1]));
        dd[3] = java.lang.Math.fma(otherW, sd[3], otherX * -sd[0]) - java.lang.Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat mul_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((otherX) * (sd[3]) + (otherW * sd[0])) + ((otherZ) * (sd[1]) - (otherY * sd[2]));
        double _buf1 = ((otherX) * (sd[2]) + (otherW * sd[1])) + ((otherY) * (sd[3]) - (otherZ * sd[0]));
        double _buf2 = ((otherY) * (sd[0]) + (otherZ * sd[3])) + ((otherW) * (sd[2]) - (otherX * sd[1]));
        dd[3] = ((otherW) * (sd[3]) - (otherX * sd[0])) - ((otherY) * (sd[1]) + (otherZ * sd[2]));
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
    public DoubleQuat preMul(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherData[0], sd[3], otherData[3] * sd[0]) + java.lang.Math.fma(otherData[1], sd[2], -(otherData[2] * sd[1]));
        double _buf1 = java.lang.Math.fma(otherData[1], sd[3], otherData[2] * sd[0]) + java.lang.Math.fma(otherData[3], sd[1], -otherData[0] * sd[2]);
        double _buf2 = java.lang.Math.fma(otherData[0], sd[1], otherData[3] * sd[2]) + java.lang.Math.fma(otherData[2], sd[3], -(otherData[1] * sd[0]));
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], -otherData[0] * sd[0]) - java.lang.Math.fma(otherData[1], sd[1], otherData[2] * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat preMul_mulAdd(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((otherData[0]) * (sd[3]) + (otherData[3] * sd[0])) + ((otherData[1]) * (sd[2]) - (otherData[2] * sd[1]));
        double _buf1 = ((otherData[1]) * (sd[3]) + (otherData[2] * sd[0])) + ((otherData[3]) * (sd[1]) - (otherData[0] * sd[2]));
        double _buf2 = ((otherData[0]) * (sd[1]) + (otherData[3] * sd[2])) + ((otherData[2]) * (sd[3]) - (otherData[1] * sd[0]));
        dd[3] = ((otherData[3]) * (sd[3]) - (otherData[0] * sd[0])) - ((otherData[1]) * (sd[1]) + (otherData[2] * sd[2]));
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
    public DoubleQuat preMul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherX, sd[3], otherW * sd[0]) + java.lang.Math.fma(otherY, sd[2], -(otherZ * sd[1]));
        double _buf1 = java.lang.Math.fma(otherY, sd[3], otherZ * sd[0]) + java.lang.Math.fma(otherW, sd[1], -otherX * sd[2]);
        double _buf2 = java.lang.Math.fma(otherX, sd[1], otherW * sd[2]) + java.lang.Math.fma(otherZ, sd[3], -(otherY * sd[0]));
        dd[3] = java.lang.Math.fma(otherW, sd[3], -otherX * sd[0]) - java.lang.Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat preMul_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((otherX) * (sd[3]) + (otherW * sd[0])) + ((otherY) * (sd[2]) - (otherZ * sd[1]));
        double _buf1 = ((otherY) * (sd[3]) + (otherZ * sd[0])) + ((otherW) * (sd[1]) - (otherX * sd[2]));
        double _buf2 = ((otherX) * (sd[1]) + (otherW * sd[2])) + ((otherZ) * (sd[3]) - (otherY * sd[0]));
        dd[3] = ((otherW) * (sd[3]) - (otherX * sd[0])) - ((otherY) * (sd[1]) + (otherZ * sd[2]));
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
    public DoubleQuat addScaled(DoubleQuatR other, double weight, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return addScaled_narrow(other, weight, dest);
        if (SimdMath.USE_FMA) return addScaled_fma(other, weight, dest);
        return addScaled_mulAdd(other, weight, dest);
    }

    private DoubleQuat addScaled_fma(DoubleQuatR other, double weight, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, weight).fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0), DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat addScaled_mulAdd(DoubleQuatR other, double weight, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, weight).mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
    public DoubleQuat addScaled(double otherX, double otherY, double otherZ, double otherW, double weight, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherX, sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherY, sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherZ, sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherW, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = ((weight) * (otherX) + (sd[0]));
            dd[1] = ((weight) * (otherY) + (sd[1]));
            dd[2] = ((weight) * (otherZ) + (sd[2]));
            dd[3] = ((weight) * (otherW) + (sd[3]));
            return dest;
        }
    }

    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public double angle() {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]))), sd[3]);
        } else {
            double[] sd = this.data;
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))), sd[3]);
        }
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to {@code other},
     * within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians of the rotation that takes this quaternion to {@code other},
     *        within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0)
     */
    public double angleTo(DoubleQuatR other) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1]))) > 0.0) {
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
        double _t13 = sd[3] - _t9;
        double _t14 = sd[2] - _t10;
        double _t15 = sd[0] - _t11;
        double _t16 = sd[1] - _t12;
        double _t17 = sd[3] + _t9;
        double _t18 = sd[2] + _t10;
        double _t19 = sd[0] + _t11;
        double _t20 = sd[1] + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion and its
     * negation, the same rotation, give 0).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
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
     * @return the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     *        {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion
     *        and its negation, the same rotation, give 0)
     */
    public double angleTo(double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.FmaSwitch.ON) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1]))) > 0.0) {
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
        double _t13 = sd[3] - _t9;
        double _t14 = sd[2] - _t10;
        double _t15 = sd[0] - _t11;
        double _t16 = sd[1] - _t12;
        double _t17 = sd[3] + _t9;
        double _t18 = sd[2] + _t10;
        double _t19 = sd[0] + _t11;
        double _t20 = sd[1] + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double angleTo_mulAdd(double otherX, double otherY, double otherZ, double otherW) {
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-((otherW) * (sd[3]) + (((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1])))))) > 0.0) {
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
        double _t13 = sd[3] - _t9;
        double _t14 = sd[2] - _t10;
        double _t15 = sd[0] - _t11;
        double _t16 = sd[1] - _t12;
        double _t17 = sd[3] + _t9;
        double _t18 = sd[2] + _t10;
        double _t19 = sd[0] + _t11;
        double _t20 = sd[1] + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
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
    public Double3 axis(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                dd[0] = sd[0] * _t3;
                dd[1] = sd[1] * _t3;
                dd[2] = sd[2] * _t3;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                dd[0] = sd[0] * _t3;
                dd[1] = sd[1] * _t3;
                dd[2] = sd[2] * _t3;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
        }
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen), and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat calculateW(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return calculateW_narrow(dest);
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, sd, 0).withLane(3, java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-sd[0], sd[0], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(-sd[2], sd[2], 1.0)))))).intoArray(dd, 0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, sd, 0).withLane(3, java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (sd[2]) * (sd[2]))) - (sd[1]) * (sd[1]))) - (sd[0]) * (sd[0]))))).intoArray(dd, 0);
            return dest;
        }
    }

    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat conjugate(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return conjugate_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).lanewise(VectorOperators.NEG, MASK_0).intoArray(dd, 0);
        return dest;
    }

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length),
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat conjugateBy(DoubleQuatR q, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return conjugateBy_fma(q, dest);
        return conjugateBy_mulAdd(q, dest);
    }

    /** {@code conjugateBy} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_fma(DoubleQuatR q, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] qData = ((DoubleQuatImpl) q).data;
        double _t20 = java.lang.Math.fma(qData[0], sd[1], qData[3] * sd[2]) + java.lang.Math.fma(qData[2], sd[3], qData[1] * -sd[0]);
        double _t21 = java.lang.Math.fma(qData[1], sd[3], qData[2] * sd[0]) + java.lang.Math.fma(qData[3], sd[1], -(qData[0] * sd[2]));
        double _t22 = java.lang.Math.fma(qData[0], sd[3], qData[3] * sd[0]) + java.lang.Math.fma(qData[1], sd[2], -(qData[2] * sd[1]));
        double _t23 = java.lang.Math.fma(qData[3], sd[3], qData[0] * -sd[0]) - java.lang.Math.fma(qData[1], sd[1], qData[2] * sd[2]);
        return conjugateBy_s7e54daef_1_fma(dest, qData, ((DoubleQuatImpl) dest).data, _t20, _t21, _t22, _t23, java.lang.Math.fma(qData[1], _t20, -qData[2] * _t21) + java.lang.Math.fma(qData[3], _t22, -qData[0] * _t23), java.lang.Math.fma(qData[2], _t22, -qData[1] * _t23) + java.lang.Math.fma(qData[3], _t21, -qData[0] * _t20), java.lang.Math.fma(qData[0], _t21, qData[3] * _t20) + java.lang.Math.fma(-qData[1], _t22, -qData[2] * _t23));
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_mulAdd(DoubleQuatR q, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] qData = ((DoubleQuatImpl) q).data;
        double _t20 = ((qData[0]) * (sd[1]) + (qData[3] * sd[2])) + ((qData[2]) * (sd[3]) - (qData[1] * sd[0]));
        double _t21 = ((qData[1]) * (sd[3]) + (qData[2] * sd[0])) + ((qData[3]) * (sd[1]) - (qData[0] * sd[2]));
        double _t22 = ((qData[0]) * (sd[3]) + (qData[3] * sd[0])) + ((qData[1]) * (sd[2]) - (qData[2] * sd[1]));
        double _t23 = ((qData[3]) * (sd[3]) - (qData[0] * sd[0])) - ((qData[1]) * (sd[1]) + (qData[2] * sd[2]));
        return conjugateBy_s7e54daef_1_mulAdd(dest, qData, ((DoubleQuatImpl) dest).data, _t20, _t21, _t22, _t23, ((qData[1]) * (_t20) - (qData[2] * _t21)) + ((qData[3]) * (_t22) - (qData[0] * _t23)), ((qData[2]) * (_t22) - (qData[1] * _t23)) + ((qData[3]) * (_t21) - (qData[0] * _t20)), ((qData[0]) * (_t21) + (qData[3] * _t20)) + ((-qData[1]) * (_t22) - (qData[2] * _t23)));
    }

    /** Piece 2 of {@code conjugateBy}, split to fit the inline budget; reached only through it. */
    private DoubleQuat conjugateBy_s7e54daef_1_fma(DoubleQuat dest, double[] qData, double[] dd, double _t20, double _t21, double _t22, double _t23, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(qData[0], _t22, qData[3] * _t23) - java.lang.Math.fma(-qData[2], _t20, -qData[1] * _t21);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code conjugateBy}, split to fit the inline budget; reached only through it. */
    private DoubleQuat conjugateBy_s7e54daef_1_mulAdd(DoubleQuat dest, double[] qData, double[] dd, double _t20, double _t21, double _t22, double _t23, double _buf0, double _buf1, double _buf2) {
        dd[3] = ((qData[0]) * (_t22) + (qData[3] * _t23)) - ((-qData[2]) * (_t20) - (qData[1] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length), and store the result in {@code dest}.
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
    public DoubleQuat conjugateBy(double qX, double qY, double qZ, double qW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return conjugateBy_mulAdd(qX, qY, qZ, qW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = java.lang.Math.fma(qX, sd[1], qW * sd[2]) + java.lang.Math.fma(qZ, sd[3], qY * -sd[0]);
        double _t21 = java.lang.Math.fma(qY, sd[3], qZ * sd[0]) + java.lang.Math.fma(qW, sd[1], -(qX * sd[2]));
        double _t22 = java.lang.Math.fma(qX, sd[3], qW * sd[0]) + java.lang.Math.fma(qY, sd[2], -(qZ * sd[1]));
        double _t23 = java.lang.Math.fma(qW, sd[3], qX * -sd[0]) - java.lang.Math.fma(qY, sd[1], qZ * sd[2]);
        dd[0] = java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23);
        dd[1] = java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20);
        dd[2] = java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23);
        dd[3] = java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21);
        return dest;
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_mulAdd(double qX, double qY, double qZ, double qW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t20 = ((qX) * (sd[1]) + (qW * sd[2])) + ((qZ) * (sd[3]) - (qY * sd[0]));
        double _t21 = ((qY) * (sd[3]) + (qZ * sd[0])) + ((qW) * (sd[1]) - (qX * sd[2]));
        double _t22 = ((qX) * (sd[3]) + (qW * sd[0])) + ((qY) * (sd[2]) - (qZ * sd[1]));
        double _t23 = ((qW) * (sd[3]) - (qX * sd[0])) - ((qY) * (sd[1]) + (qZ * sd[2]));
        dd[0] = ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23));
        dd[1] = ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20));
        dd[2] = ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23));
        dd[3] = ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21));
        return dest;
    }

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other}, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat difference(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t3_inv = 1.0 / java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        double _sp0 = sd[1] * _t3_inv;
        double _buf0 = (java.lang.Math.fma(otherData[0], sd[3], -otherData[3] * sd[0]) + java.lang.Math.fma(otherData[1], sd[2], -otherData[2] * sd[1])) * _t3_inv;
        double _buf1 = java.lang.Math.fma(java.lang.Math.fma(otherData[1], sd[3], otherData[2] * sd[0]), _t3_inv, java.lang.Math.fma(-(otherData[0] * sd[2]), _t3_inv, -otherData[3] * _sp0));
        double _buf2 = (java.lang.Math.fma(otherData[0], sd[1], -otherData[3] * sd[2]) + java.lang.Math.fma(otherData[2], sd[3], -otherData[1] * sd[0])) * _t3_inv;
        dd[3] = java.lang.Math.fma(java.lang.Math.fma(otherData[0], sd[0], otherData[3] * sd[3]), _t3_inv, java.lang.Math.fma(otherData[2] * sd[2], _t3_inv, otherData[1] * _sp0));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat difference_mulAdd(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t3_inv = 1.0 / ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        double _sp0 = sd[1] * _t3_inv;
        double _buf0 = (((otherData[0]) * (sd[3]) - (otherData[3] * sd[0])) + ((otherData[1]) * (sd[2]) - (otherData[2] * sd[1]))) * _t3_inv;
        double _buf1 = ((((otherData[1]) * (sd[3]) + (otherData[2] * sd[0]))) * (_t3_inv) + (((-(otherData[0] * sd[2])) * (_t3_inv) - (otherData[3] * _sp0))));
        double _buf2 = (((otherData[0]) * (sd[1]) - (otherData[3] * sd[2])) + ((otherData[2]) * (sd[3]) - (otherData[1] * sd[0]))) * _t3_inv;
        dd[3] = ((((otherData[0]) * (sd[0]) + (otherData[3] * sd[3]))) * (_t3_inv) + (((otherData[2] * sd[2]) * (_t3_inv) + (otherData[1] * _sp0))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)}, and store the result in {@code dest}.
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
    public DoubleQuat difference(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t3_inv = 1.0 / java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        double _sp0 = sd[1] * _t3_inv;
        double _buf0 = (java.lang.Math.fma(otherX, sd[3], -otherW * sd[0]) + java.lang.Math.fma(otherY, sd[2], -otherZ * sd[1])) * _t3_inv;
        double _buf1 = (java.lang.Math.fma(otherX, sd[1], -otherW * sd[2]) + java.lang.Math.fma(otherZ, sd[3], -otherY * sd[0])) * _t3_inv;
        dd[1] = java.lang.Math.fma(java.lang.Math.fma(otherY, sd[3], otherZ * sd[0]), _t3_inv, java.lang.Math.fma(-(otherX * sd[2]), _t3_inv, -otherW * _sp0));
        dd[3] = java.lang.Math.fma(java.lang.Math.fma(otherX, sd[0], otherW * sd[3]), _t3_inv, java.lang.Math.fma(otherZ * sd[2], _t3_inv, otherY * _sp0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        return dest;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat difference_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t3_inv = 1.0 / ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        double _sp0 = sd[1] * _t3_inv;
        double _buf0 = (((otherX) * (sd[3]) - (otherW * sd[0])) + ((otherY) * (sd[2]) - (otherZ * sd[1]))) * _t3_inv;
        double _buf1 = (((otherX) * (sd[1]) - (otherW * sd[2])) + ((otherZ) * (sd[3]) - (otherY * sd[0]))) * _t3_inv;
        dd[1] = ((((otherY) * (sd[3]) + (otherZ * sd[0]))) * (_t3_inv) + (((-(otherX * sd[2])) * (_t3_inv) - (otherW * _sp0))));
        dd[3] = ((((otherX) * (sd[0]) + (otherW * sd[3]))) * (_t3_inv) + (((otherZ * sd[2]) * (_t3_inv) + (otherY * _sp0))));
        dd[0] = _buf0;
        dd[2] = _buf1;
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
    public double dot(DoubleQuatR other) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
        } else {
            double[] sd = this.data;
            return ((otherW) * (sd[3]) + (((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))))));
        }
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
    public double dot(double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
        } else {
            double[] sd = this.data;
            return ((otherW) * (sd[3]) + (((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))))));
        }
    }

    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat exp(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return exp_narrow(dest);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = Math.min(Math.exp(sd[3]), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t5 = java.lang.Math.sqrt(_t4);
            (_t4 > 0.0 ? DoubleVector.fromArray(_COL_SPECIES, sd, 0).withLane(3, Math.cosWithSin(_t5)).mul(DoubleVector.broadcast(_COL_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dd, 0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = Math.min(Math.exp(sd[3]), 1.7976931348623157E308);
            double _t4 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t5 = java.lang.Math.sqrt(_t4);
            (_t4 > 0.0 ? DoubleVector.fromArray(_COL_SPECIES, sd, 0).withLane(3, Math.cosWithSin(_t5)).mul(DoubleVector.broadcast(_COL_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dd, 0);
            return dest;
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Y and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[3], _t1);
        double _t12 = 2.0 * java.lang.Math.fma(sd[0], sd[3], -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        double _t11 = 2.0 * ((sd[0]) * (sd[3]) + (_t1));
        double _t12 = 2.0 * ((sd[0]) * (sd[3]) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t14 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0 * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Y and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_fma(epsilon, dest);
        return getEulerAnglesXYZ_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[3], _t1);
        double _t12 = 2.0 * java.lang.Math.fma(sd[0], sd[3], -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), 2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        double _t11 = 2.0 * ((sd[0]) * (sd[3]) + (_t1));
        double _t12 = 2.0 * ((sd[0]) * (sd[3]) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t14 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) + (2.0 * ((sd[0]) * (sd[2]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Z and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], _t1);
        double _t10 = 2.0 * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(2.0 * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (_t1));
        double _t10 = 2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        double _t12 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(2.0 * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Z and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_fma(epsilon, dest);
        return getEulerAnglesXZY_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], _t1);
        double _t10 = 2.0 * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0 * java.lang.Math.fma(sd[0], sd[3], -_t1), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]), _t9, _t11 * -2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * -sd[3])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(sd[1], sd[2], sd[0] * -sd[3])));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[1] * sd[2];
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (_t1));
        double _t10 = 2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
        double _t12 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0 * ((sd[0]) * (sd[3]) - (_t1)), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]))) * (_t9) - (_t11 * 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3])))), ((_t12) * (_t11) - (_t9 * 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, X and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[3], -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dd[1] = Math.atan2(2.0 * java.lang.Math.fma(sd[1], sd[3], sd[0] * -sd[2]), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0 * java.lang.Math.fma(sd[0], sd[1], -sd[2] * sd[3])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        double _t10 = 2.0 * ((sd[0]) * (sd[3]) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t12 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dd[1] = Math.atan2(2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0 * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0 * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, X and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_fma(epsilon, dest);
        return getEulerAnglesYXZ_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], _t2);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[3], -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0 * java.lang.Math.fma(sd[1], sd[3], sd[0] * -sd[2]), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(sd[0], sd[3], _t0), _t11 * -2.0 * java.lang.Math.fma(sd[0], sd[1], -sd[2] * sd[3])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(sd[0], sd[2], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] * sd[2];
        double _t2 = sd[1] * sd[3];
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (_t2));
        double _t10 = 2.0 * ((sd[0]) * (sd[3]) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t12 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2])), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0 * ((sd[0]) * (sd[3]) + (_t0))) - (_t11 * 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3])))), ((_t11) * (_t12) - (_t9 * 2.0 * ((sd[0]) * (sd[2]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, Z and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[2] * sd[3];
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t2);
        double _t12 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t13 = 2.0 * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0 * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[2] * sd[3];
        double _t11 = 2.0 * ((sd[0]) * (sd[1]) + (_t2));
        double _t12 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t13 = 2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        double _t14 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        double _t15 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0 * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, Z and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_fma(epsilon, dest);
        return getEulerAnglesYZX_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[2] * sd[3];
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t2);
        double _t12 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t13 = 2.0 * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[2]));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]), 2.0 * java.lang.Math.fma(sd[0], sd[1], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[2] * sd[3];
        double _t11 = 2.0 * ((sd[0]) * (sd[1]) + (_t2));
        double _t12 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t13 = 2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        double _t14 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
        double _t15 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]))) + (2.0 * ((sd[0]) * (sd[1]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, X and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[2] * sd[2];
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t12 = 2.0 * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[2] * sd[2];
        double _t10 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t11 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t12 = 2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        double _t13 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, X and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_fma(epsilon, dest);
        return getEulerAnglesZXY_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[2] * sd[2];
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t11 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t12 = 2.0 * java.lang.Math.fma(sd[2], sd[3], -(sd[0] * sd[1]));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[2] * sd[2];
        double _t10 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t11 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t12 = 2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1]));
        double _t13 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) + (2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, Y and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t8 = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t1);
        double _t9 = 2.0 * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t8 = 2.0 * ((sd[0]) * (sd[1]) + (_t1));
        double _t9 = 2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        double _t10 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0 * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, Y and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_fma(epsilon, dest);
        return getEulerAnglesZYX_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t8 = 2.0 * java.lang.Math.fma(sd[0], sd[1], _t1);
        double _t9 = 2.0 * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[2]);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * java.lang.Math.fma(sd[2], sd[3], -sd[0] * sd[1]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]), _t10 * -2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(sd[0], sd[1], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t8 = 2.0 * ((sd[0]) * (sd[1]) + (_t1));
        double _t9 = 2.0 * ((sd[1]) * (sd[3]) - (sd[0] * sd[2]));
        double _t10 = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * ((sd[2]) * (sd[3]) - (sd[0] * sd[1])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]))) - (_t10 * 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3])))), ((_t11) * (_t10) - (_t8 * 2.0 * ((sd[0]) * (sd[1]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat integrate(Double3R angularVel, double dt, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return integrate_fma(angularVel, dt, dest);
        return integrate_mulAdd(angularVel, dt, dest);
    }

    /** {@code integrate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_fma(Double3R angularVel, double dt, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] angularVelData = ((Double3Impl) angularVel).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * dt;
        double _t1 = angularVelData[2] * _t0;
        double _t2 = angularVelData[0] * _t0;
        double _t3 = angularVelData[1] * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _buf0 = java.lang.Math.fma(sd[0], _t9, sd[3] * _t15) + java.lang.Math.fma(sd[2], _t16, -(sd[1] * _t17));
        double _buf1 = java.lang.Math.fma(sd[0], _t17, sd[3] * _t16) + java.lang.Math.fma(sd[1], _t9, -(sd[2] * _t15));
        double _buf2 = java.lang.Math.fma(sd[1], _t15, sd[2] * _t9) + java.lang.Math.fma(sd[3], _t17, -sd[0] * _t16);
        dd[3] = java.lang.Math.fma(sd[3], _t9, -sd[0] * _t15) - java.lang.Math.fma(sd[1], _t16, sd[2] * _t17);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_mulAdd(Double3R angularVel, double dt, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] angularVelData = ((Double3Impl) angularVel).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * dt;
        double _t1 = angularVelData[2] * _t0;
        double _t2 = angularVelData[0] * _t0;
        double _t3 = angularVelData[1] * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _buf0 = ((sd[0]) * (_t9) + (sd[3] * _t15)) + ((sd[2]) * (_t16) - (sd[1] * _t17));
        double _buf1 = ((sd[0]) * (_t17) + (sd[3] * _t16)) + ((sd[1]) * (_t9) - (sd[2] * _t15));
        double _buf2 = ((sd[1]) * (_t15) + (sd[2] * _t9)) + ((sd[3]) * (_t17) - (sd[0] * _t16));
        dd[3] = ((sd[3]) * (_t9) - (sd[0] * _t15)) - ((sd[1]) * (_t16) + (sd[2] * _t17));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one) and store the result in {@code dest}.
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
    public DoubleQuat integrate(double angularVelX, double angularVelY, double angularVelZ, double dt, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return integrate_fma(angularVelX, angularVelY, angularVelZ, dt, dest);
        return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt, dest);
    }

    /** {@code integrate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_fma(double angularVelX, double angularVelY, double angularVelZ, double dt, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _buf0 = java.lang.Math.fma(sd[0], _t9, sd[3] * _t15) + java.lang.Math.fma(sd[2], _t16, -(sd[1] * _t17));
        double _buf1 = java.lang.Math.fma(sd[0], _t17, sd[3] * _t16) + java.lang.Math.fma(sd[1], _t9, -(sd[2] * _t15));
        double _buf2 = java.lang.Math.fma(sd[1], _t15, sd[2] * _t9) + java.lang.Math.fma(sd[3], _t17, -sd[0] * _t16);
        dd[3] = java.lang.Math.fma(sd[3], _t9, -sd[0] * _t15) - java.lang.Math.fma(sd[1], _t16, sd[2] * _t17);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_mulAdd(double angularVelX, double angularVelY, double angularVelZ, double dt, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _buf0 = ((sd[0]) * (_t9) + (sd[3] * _t15)) + ((sd[2]) * (_t16) - (sd[1] * _t17));
        double _buf1 = ((sd[0]) * (_t17) + (sd[3] * _t16)) + ((sd[1]) * (_t9) - (sd[2] * _t15));
        double _buf2 = ((sd[1]) * (_t15) + (sd[2] * _t9)) + ((sd[3]) * (_t17) - (sd[0] * _t16));
        dd[3] = ((sd[3]) * (_t9) - (sd[0] * _t15)) - ((sd[1]) * (_t16) + (sd[2] * _t17));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invNegativeX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code invNegativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t10 = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
        double _t12 = ((((((sd[0]) * (sd[0]) + (sd[3] * sd[3]))) - (sd[1]) * (sd[1]))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invNegativeY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], sd[2], -sd[0] * sd[3]);
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code invNegativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t10 = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        double _t12 = ((((sd[1]) * (sd[1]) + (((sd[3]) * (sd[3]) - (sd[0] * sd[0]))))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invNegativeZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        double _t12 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code invNegativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
        double _t12 = ((sd[2]) * (sd[2]) + (((((sd[3]) * (sd[3]) - (sd[0] * sd[0]))) - (sd[1]) * (sd[1]))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
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
    public Double3 invNormalizedNegativeX(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0);
            dd[1] = -2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
            dd[2] = -2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))) - (1.0));
            dd[1] = -2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
            dd[2] = -2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
            return dest;
        }
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
    public Double3 invNormalizedNegativeY(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
            dd[1] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0);
            dd[2] = -2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
            dd[1] = ((2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))) - (1.0));
            dd[2] = -2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
            return dest;
        }
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
    public Double3 invNormalizedNegativeZ(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
            dd[1] = -2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
            dd[2] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
            dd[1] = -2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
            dd[2] = ((2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))) - (1.0));
            return dest;
        }
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
    public Double3 invNormalizedPositiveX(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
            dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
            dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
            dd[1] = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
            dd[2] = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
            return dest;
        }
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
    public Double3 invNormalizedPositiveY(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
            dd[1] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
            dd[2] = 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
            dd[1] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
            dd[2] = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
            return dest;
        }
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
    public Double3 invNormalizedPositiveZ(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
            dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
            dd[2] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
            dd[1] = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
            dd[2] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            return dest;
        }
    }

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invPositiveX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t12 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code invPositiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t10 = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
        double _t12 = ((((((sd[0]) * (sd[0]) + (sd[3] * sd[3]))) - (sd[1]) * (sd[1]))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t12 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invPositiveY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], sd[2], -sd[0] * sd[3]);
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t9 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code invPositiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t10 = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        double _t12 = ((((sd[1]) * (sd[1]) + (((sd[3]) * (sd[3]) - (sd[0] * sd[0]))))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t9 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invPositiveZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
        double _t12 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t10 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code invPositiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
        double _t12 = ((sd[2]) * (sd[2]) + (((((sd[3]) * (sd[3]) - (sd[0] * sd[0]))) - (sd[1]) * (sd[1]))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t10 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this quaternion
     */
    public double length() {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1])))))));
        }
    }

    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    public double lengthSquared() {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        } else {
            double[] sd = this.data;
            return ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        }
    }

    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat log(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return log_narrow(dest);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            (_t2 > 0.0 ? DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], _t2)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], _t2))))).intoArray(dd, 0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            (_t2 > 0.0 ? DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (_t2))))) : DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (_t2)))))).intoArray(dd, 0);
            return dest;
        }
    }

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return negativeX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], -sd[1] * sd[3]);
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code negativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
        double _t12 = ((((((sd[0]) * (sd[0]) + (sd[3] * sd[3]))) - (sd[1]) * (sd[1]))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t12 * _t16);
            dd[1] = -(_t9 * _t16);
            dd[2] = -(_t10 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return negativeY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code negativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t10 = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
        double _t12 = ((((sd[1]) * (sd[1]) + (((sd[3]) * (sd[3]) - (sd[0] * sd[0]))))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t10 * _t16);
            dd[1] = -(_t12 * _t16);
            dd[2] = -(_t9 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return negativeZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], sd[2], -sd[0] * sd[3]);
        double _t12 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /** {@code negativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t10 = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        double _t12 = ((sd[2]) * (sd[2]) + (((((sd[3]) * (sd[3]) - (sd[0] * sd[0]))) - (sd[1]) * (sd[1]))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = -(_t9 * _t16);
            dd[1] = -(_t10 * _t16);
            dd[2] = -(_t12 * _t16);
        } else {
            dd[0] = -0.0;
            dd[1] = -0.0;
            dd[2] = -0.0;
        }
        return dest;
    }

    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat normalize(@Mutated DoubleQuat dest) {
        if (COL_NARROW) return normalize_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0 ? _sv0.mul(DoubleVector.broadcast(COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
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
    public Double3 normalizedNegativeX(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), -1.0);
            dd[1] = -2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
            dd[2] = -2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))) - (1.0));
            dd[1] = -2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
            dd[2] = -2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
            return dest;
        }
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
    public Double3 normalizedNegativeY(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
            dd[1] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), -1.0);
            dd[2] = -2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
            dd[1] = ((2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))) - (1.0));
            dd[2] = -2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
            return dest;
        }
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
    public Double3 normalizedNegativeZ(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
            dd[1] = -2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
            dd[2] = java.lang.Math.fma(2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), -1.0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = -2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
            dd[1] = -2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
            dd[2] = ((2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))) - (1.0));
            return dest;
        }
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
    public Double3 normalizedPositiveX(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[1], sd[1], sd[2] * sd[2]), 1.0);
            dd[1] = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
            dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[2], -(sd[1] * sd[3]));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((1.0) - (2.0) * (((sd[1]) * (sd[1]) + (sd[2] * sd[2]))));
            dd[1] = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
            dd[2] = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
            return dest;
        }
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
    public Double3 normalizedPositiveY(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
            dd[1] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]), 1.0);
            dd[2] = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
            dd[1] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[2] * sd[2]))));
            dd[2] = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
            return dest;
        }
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
    public Double3 normalizedPositiveZ(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
            dd[1] = 2.0 * java.lang.Math.fma(sd[1], sd[2], -(sd[0] * sd[3]));
            dd[2] = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]), 1.0);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
            dd[1] = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
            dd[2] = ((1.0) - (2.0) * (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            return dest;
        }
    }

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return positiveX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[1], sd[2] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[2], -sd[1] * sd[3]);
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[3] * sd[3])));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t12 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code positiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[1]) + (sd[2] * sd[3]));
        double _t10 = 2.0 * ((sd[0]) * (sd[2]) - (sd[1] * sd[3]));
        double _t12 = ((((((sd[0]) * (sd[0]) + (sd[3] * sd[3]))) - (sd[1]) * (sd[1]))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t12 * _t16;
            dd[1] = _t9 * _t16;
            dd[2] = _t10 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return positiveY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[3], sd[1] * sd[2]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[0], sd[1], -(sd[2] * sd[3]));
        double _t12 = java.lang.Math.fma(-sd[2], sd[2], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t10 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code positiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[3]) + (sd[1] * sd[2]));
        double _t10 = 2.0 * ((sd[0]) * (sd[1]) - (sd[2] * sd[3]));
        double _t12 = ((((sd[1]) * (sd[1]) + (((sd[3]) * (sd[3]) - (sd[0] * sd[0]))))) - (sd[2]) * (sd[2]));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t10 * _t16;
            dd[1] = _t12 * _t16;
            dd[2] = _t9 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return positiveZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], sd[2], sd[1] * sd[3]);
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], sd[2], -sd[0] * sd[3]);
        double _t12 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(-sd[1], sd[1], java.lang.Math.fma(sd[3], sd[3], -sd[0] * sd[0])));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t9 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code positiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (sd[2]) + (sd[1] * sd[3]));
        double _t10 = 2.0 * ((sd[1]) * (sd[2]) - (sd[0] * sd[3]));
        double _t12 = ((sd[2]) * (sd[2]) + (((((sd[3]) * (sd[3]) - (sd[0] * sd[0]))) - (sd[1]) * (sd[1]))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dd[0] = _t9 * _t16;
            dd[1] = _t10 * _t16;
            dd[2] = _t12 * _t16;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))}, and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: {@code t} must lie in {@code [-67108864, 67108864]}.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat pow(double t, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return pow_mulAdd(t, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], _t2)))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * sd[2] * _t13;
            _t21 = t * sd[0] * _t13;
            _t22 = t * sd[1] * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0) {
            dd[0] = _t21 * _t31;
            dd[1] = _t22 * _t31;
            dd[2] = _t20 * _t31;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        dd[3] = Math.cosWithSin(_t26) * _t12;
        return dest;
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat pow_mulAdd(double t, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (_t2))))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * sd[2] * _t13;
            _t21 = t * sd[0] * _t13;
            _t22 = t * sd[1] * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0) {
            dd[0] = _t21 * _t31;
            dd[1] = _t22 * _t31;
            dd[2] = _t20 * _t31;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        dd[3] = Math.cosWithSin(_t26) * _t12;
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
    public DoubleQuat premul(DoubleQuatR other, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        double[] sd = this.data;
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherData[0], sd[3], otherData[3] * sd[0]) + java.lang.Math.fma(otherData[1], sd[2], -(otherData[2] * sd[1]));
        double _buf1 = java.lang.Math.fma(otherData[1], sd[3], otherData[2] * sd[0]) + java.lang.Math.fma(otherData[3], sd[1], -otherData[0] * sd[2]);
        double _buf2 = java.lang.Math.fma(otherData[0], sd[1], otherData[3] * sd[2]) + java.lang.Math.fma(otherData[2], sd[3], -(otherData[1] * sd[0]));
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], -otherData[0] * sd[0]) - java.lang.Math.fma(otherData[1], sd[1], otherData[2] * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
    public DoubleQuat premul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = java.lang.Math.fma(otherX, sd[3], otherW * sd[0]) + java.lang.Math.fma(otherY, sd[2], -(otherZ * sd[1]));
        double _buf1 = java.lang.Math.fma(otherY, sd[3], otherZ * sd[0]) + java.lang.Math.fma(otherW, sd[1], -otherX * sd[2]);
        double _buf2 = java.lang.Math.fma(otherX, sd[1], otherW * sd[2]) + java.lang.Math.fma(otherZ, sd[3], -(otherY * sd[0]));
        dd[3] = java.lang.Math.fma(otherW, sd[3], -otherX * sd[0]) - java.lang.Math.fma(otherY, sd[1], otherZ * sd[2]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle, and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTowards(DoubleQuatR target, double step, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return rotateTowards_narrow(target, step, dest);
        if (SimdMath.USE_FMA) return rotateTowards_fma(target, step, dest);
        return rotateTowards_mulAdd(target, step, dest);
    }

    private DoubleQuat rotateTowards_fma(DoubleQuatR target, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _r0 = sd[3];
        double _r1 = targetData[3];
        double _r2 = sd[2];
        double _r3 = targetData[2];
        double _r4 = sd[0];
        double _r5 = targetData[0];
        double _r6 = sd[1];
        double _r7 = targetData[1];
        double _t8 = -java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6 * _r7)));
        double _t9 = _t8 > 0.0 ? -_r1 : _r1;
        double _t10 = _t8 > 0.0 ? -_r3 : _r3;
        double _t11 = _t8 > 0.0 ? -_r5 : _r5;
        double _t12 = _t8 > 0.0 ? -_r7 : _r7;
        rotateTowards_s27727243_tail(dd, _t8, _r0 + _t9, _r2 + _t10, _r4 + _t11, _r6 + _t12, _r0 - _t9, _r2 - _t10, _r4 - _t11, _r6 - _t12, step, sd, targetData);
        return dest;
    }

    private DoubleQuat rotateTowards_mulAdd(DoubleQuatR target, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] targetData = ((DoubleQuatImpl) target).data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _r0 = sd[3];
        double _r1 = targetData[3];
        double _r2 = sd[2];
        double _r3 = targetData[2];
        double _r4 = sd[0];
        double _r5 = targetData[0];
        double _r6 = sd[1];
        double _r7 = targetData[1];
        double _t8 = -((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6 * _r7))))));
        double _t9 = _t8 > 0.0 ? -_r1 : _r1;
        double _t10 = _t8 > 0.0 ? -_r3 : _r3;
        double _t11 = _t8 > 0.0 ? -_r5 : _r5;
        double _t12 = _t8 > 0.0 ? -_r7 : _r7;
        rotateTowards_sba76bd4_tail(dd, _t8, _r0 + _t9, _r2 + _t10, _r4 + _t11, _r6 + _t12, _r0 - _t9, _r2 - _t10, _r4 - _t11, _r6 - _t12, step, sd, targetData);
        return dest;
    }

    /** Private vector tail of {@code rotateTowards_s27727243}: loads, computes and stores every column; reached only through it. */
    private static void rotateTowards_s27727243_tail2(double[] dd, double _t8, double _t31, double _t33, double _t37, double _t40, double _t41, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv2 = (_t8 > 0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (0.5 * java.lang.Math.sqrt(_t31) > 0.0 ? _sv0.fma(DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t41 * _t37) * (_t33 + _t33)), DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t37 * _t40) * (_t33 + _t33)).mul(_sv2)) : _sv0.fma(DoubleVector.broadcast(_COL_SPECIES, _t41), _sv2.mul(DoubleVector.broadcast(_COL_SPECIES, _t40))));
        double _t73 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t73 != 0.0 ? _sv3.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t73)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /** Private tail of {@code rotateTowards_s27727243}; reached only through it. */
    private static void rotateTowards_s27727243_tail(double[] dd, double _t8, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double step, double[] sd, double[] targetData) {
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_s27727243_tail2(dd, _t8, _t31, (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40, sd, targetData);
    }

    /** Private vector tail of {@code rotateTowards_sba76bd4}: loads, computes and stores every column; reached only through it. */
    private static void rotateTowards_sba76bd4_tail2(double[] dd, double _t8, double _t31, double _t33, double _t37, double _t40, double _t41, double[] sd, double[] targetData) {
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        var _sv0 = DoubleVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(_COL_SPECIES, targetData, 0);
        var _sv2 = (_t8 > 0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (0.5 * java.lang.Math.sqrt(_t31) > 0.0 ? _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t41 * _t37) * (_t33 + _t33))).add(DoubleVector.broadcast(_COL_SPECIES, Math.sin(_t37 * _t40) * (_t33 + _t33)).mul(_sv2)) : _sv0.mul(DoubleVector.broadcast(_COL_SPECIES, _t41)).add(_sv2.mul(DoubleVector.broadcast(_COL_SPECIES, _t40))));
        double _t73 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t73 != 0.0 ? _sv3.mul(DoubleVector.broadcast(_COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t73)))) : DoubleVector.broadcast(_COL_SPECIES, 0.0)).intoArray(dd, 0);
    }

    /** Private tail of {@code rotateTowards_sba76bd4}; reached only through it. */
    private static void rotateTowards_sba76bd4_tail(double[] dd, double _t8, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double step, double[] sd, double[] targetData) {
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_sba76bd4_tail2(dd, _t8, _t31, (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40, sd, targetData);
    }

    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle, and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
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
    public DoubleQuat rotateTowards(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return rotateTowards_narrow(targetX, targetY, targetZ, targetW, step, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_fma(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY))) > 0.0) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        double _t13 = sd[3] + _t9;
        double _t14 = sd[2] + _t10;
        double _t15 = sd[0] + _t11;
        double _t16 = sd[1] + _t12;
        double _t17 = sd[3] - _t9;
        double _t18 = sd[2] - _t10;
        double _t19 = sd[0] - _t11;
        double _t20 = sd[1] - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t9, _t10, _t11, _t12, _t31, (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_mulAdd(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-((sd[3]) * (targetW) + (((sd[2]) * (targetZ) + (((sd[0]) * (targetX) + (sd[1] * targetY)))))) > 0.0) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        double _t13 = sd[3] + _t9;
        double _t14 = sd[2] + _t10;
        double _t15 = sd[0] + _t11;
        double _t16 = sd[1] + _t12;
        double _t17 = sd[3] - _t9;
        double _t18 = sd[2] - _t10;
        double _t19 = sd[0] - _t11;
        double _t20 = sd[1] - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_mulAdd(dest, sd, ((DoubleQuatImpl) dest).data, _t9, _t10, _t11, _t12, _t31, (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t31, double _t33, double _t37, double _t40, double _t41) {
        double _t52 = Math.sin(_t37 * _t40) * (_t33 + _t33);
        double _t53 = Math.sin(_t41 * _t37) * (_t33 + _t33);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(sd[3], _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(sd[2], _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(sd[0], _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(sd[1], _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(sd[3], _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(sd[2], _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(sd[0], _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(sd[1], _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        if (_t73 != 0.0) {
            dd[0] = _t74 * _t68;
            dd[1] = _t74 * _t69;
            dd[2] = _t74 * _t67;
            dd[3] = _t74 * _t66;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t31, double _t33, double _t37, double _t40, double _t41) {
        double _t52 = Math.sin(_t37 * _t40) * (_t33 + _t33);
        double _t53 = Math.sin(_t41 * _t37) * (_t33 + _t33);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((sd[3]) * (_t53) + (_t52 * _t9));
            _t67 = ((sd[2]) * (_t53) + (_t52 * _t10));
            _t68 = ((sd[0]) * (_t53) + (_t52 * _t11));
            _t69 = ((sd[1]) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((sd[3]) * (_t41) + (_t9 * _t40));
            _t67 = ((sd[2]) * (_t41) + (_t10 * _t40));
            _t68 = ((sd[0]) * (_t41) + (_t11 * _t40));
            _t69 = ((sd[1]) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        if (_t73 != 0.0) {
            dd[0] = _t74 * _t68;
            dd[1] = _t74 * _t69;
            dd[2] = _t74 * _t67;
            dd[3] = _t74 * _t66;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dir, up, dest);
        double _t8 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        double _ct0 = lookAlong_lh_sda02a0f4_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dir, up, dest);
        lookAlong_lh_sda02a0f4_519_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t7, _t8, _ct0);
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dir, up, dest);
        double _t8 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        double _ct0 = lookAlong_lh_sda02a0f4_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > ((_t7) * (((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dir, up, dest);
        lookAlong_lh_sda02a0f4_519_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t7, _t8, _ct0);
        return dest;
    }

    /**
     * Part 1 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private double lookAlong_lh_sda02a0f4_1_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8) {
        double _t7_inv = 1.0 / _t7;
        double _t29 = java.lang.Math.fma(_ld1 * -_t8, _t7_inv, _ld4);
        double _t27 = java.lang.Math.fma(_ld2 * -_t8, _t7_inv, _ld5);
        double _t37 = java.lang.Math.fma(_ld2, _t29, -(_ld1 * _t27));
        double _t28 = java.lang.Math.fma(_ld0 * -_t8, _t7_inv, _ld3);
        double _t38 = java.lang.Math.fma(_ld1, _t28, -(_ld0 * _t29));
        double _t36 = java.lang.Math.fma(_ld0, _t27, -(_ld2 * _t28));
        return java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
    }

    /**
     * Part 1 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private double lookAlong_lh_sda02a0f4_1_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8) {
        double _t7_inv = 1.0 / _t7;
        double _t29 = ((_ld1 * -_t8) * (_t7_inv) + (_ld4));
        double _t27 = ((_ld2 * -_t8) * (_t7_inv) + (_ld5));
        double _t37 = ((_ld2) * (_t29) - (_ld1 * _t27));
        double _t28 = ((_ld0 * -_t8) * (_t7_inv) + (_ld3));
        double _t38 = ((_ld1) * (_t28) - (_ld0 * _t29));
        double _t36 = ((_ld0) * (_t27) - (_ld2 * _t28));
        return ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
    }

    /**
     * Part of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t7, double _t8, double _ct0) {
        double _t7_inv = 1.0 / _t7;
        double _t29 = java.lang.Math.fma(_ld1 * -_t8, _t7_inv, _ld4);
        double _t27 = java.lang.Math.fma(_ld2 * -_t8, _t7_inv, _ld5);
        double _t37 = java.lang.Math.fma(_ld2, _t29, -(_ld1 * _t27));
        double _t28 = java.lang.Math.fma(_ld0 * -_t8, _t7_inv, _ld3);
        double _t38 = java.lang.Math.fma(_ld1, _t28, -(_ld0 * _t29));
        double _t36 = java.lang.Math.fma(_ld0, _t27, -(_ld2 * _t28));
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = _ld0 * _t15;
        double _t46 = _t36 * _t45;
        double _t17 = _ld1 * _t15;
        double _t47 = _t37 * _t45;
        double _t19 = -_t17;
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_ld0, _t15, 1.0))));
        double _t48 = _t38 * _t45;
        double _t18 = _ld2 * _t15;
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        lookAlong_lh_sda02a0f4_519_se762ce16_1_fma(_ld0, _ld1, _ld2, _ld6, _ld7, _ld8, _ld9, dd, _t37, _t38, _t36, _t15, _t45, _t16, _t46, _t17, _t47, java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), _t19, _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t64, java.lang.Math.fma(_ld2, _t15, -_t64), _t65, java.lang.Math.fma(-_t38, _t45, _t65));
    }

    /**
     * Part of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t7, double _t8, double _ct0) {
        double _t7_inv = 1.0 / _t7;
        double _t29 = ((_ld1 * -_t8) * (_t7_inv) + (_ld4));
        double _t27 = ((_ld2 * -_t8) * (_t7_inv) + (_ld5));
        double _t37 = ((_ld2) * (_t29) - (_ld1 * _t27));
        double _t28 = ((_ld0 * -_t8) * (_t7_inv) + (_ld3));
        double _t38 = ((_ld1) * (_t28) - (_ld0 * _t29));
        double _t36 = ((_ld0) * (_t27) - (_ld2 * _t28));
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = _ld0 * _t15;
        double _t46 = _t36 * _t45;
        double _t17 = _ld1 * _t15;
        double _t47 = _t37 * _t45;
        double _t19 = -_t17;
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((_ld0) * (_t15) + (1.0))))))));
        double _t48 = _t38 * _t45;
        double _t18 = _ld2 * _t15;
        double _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        lookAlong_lh_sda02a0f4_519_se762ce16_1_mulAdd(_ld0, _ld1, _ld2, _ld6, _ld7, _ld8, _ld9, dd, _t37, _t38, _t36, _t15, _t45, _t16, _t46, _t17, _t47, ((_t16) * (_t46) - (_t17 * _t47)), _t19, _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t64, ((_ld2) * (_t15) - (_t64)), _t65, ((_t65) - (_t38) * (_t45)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh_sda02a0f4_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_se762ce16_1_fma(double _ld0, double _ld1, double _ld2, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t37, double _t38, double _t36, double _t15, double _t45, double _t16, double _t46, double _t17, double _t47, double _t62, double _t19, double _t77, double _sp0, double _t64, double _t70, double _t65, double _t74) {
        double _t21 = -_t16;
        double _t0 = -_ld0;
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t83 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        double _t80 = java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        lookAlong_lh_sda02a0f4_519_se762ce16_2_fma(_ld0, _ld6, _ld7, _ld8, _ld9, dd, _t36, _t15, _t45, _t16, _t46, _t62, _t77, _sp0, _t70, _t74, java.lang.Math.fma(_t37, _t45, _t19), _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_ld1, _t15, _t47), java.lang.Math.fma(_t38, _t45, _t65), _t83, 0.5 * (1.0 / java.lang.Math.sqrt(_t83)), java.lang.Math.fma(_ld2, _t15, _t64), _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh_sda02a0f4_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_se762ce16_1_mulAdd(double _ld0, double _ld1, double _ld2, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t37, double _t38, double _t36, double _t15, double _t45, double _t16, double _t46, double _t17, double _t47, double _t62, double _t19, double _t77, double _sp0, double _t64, double _t70, double _t65, double _t74) {
        double _t21 = -_t16;
        double _t0 = -_ld0;
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t83 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        double _t80 = ((_ld0) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        lookAlong_lh_sda02a0f4_519_se762ce16_2_mulAdd(_ld0, _ld6, _ld7, _ld8, _ld9, dd, _t36, _t15, _t45, _t16, _t46, _t62, _t77, _sp0, _t70, _t74, ((_t37) * (_t45) + (_t19)), _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), ((_ld1) * (_t15) + (_t47)), ((_t38) * (_t45) + (_t65)), _t83, 0.5 * (1.0 / java.lang.Math.sqrt(_t83)), ((_ld2) * (_t15) + (_t64)), _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_sda02a0f4_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_se762ce16_2_fma(double _ld0, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t36, double _t15, double _t45, double _t16, double _t46, double _t62, double _t77, double _sp0, double _t70, double _t74, double _t54, double _t79, double _sp3, double _t50, double _t73, double _t83, double _sp1, double _t68, double _t80, double _sp2) {
        double _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            _t125 = _sp0 * _t70;
            _t126 = _sp0 * _t74;
            _t127 = _sp0 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp3 * _t50;
                _t127 = _sp3 * _t73;
                _t128 = _sp3 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp1 * _t73;
                    _t126 = _sp1 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp1 * _t54;
                } else {
                    _t125 = _sp2 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp2 * _t68;
                    _t128 = _sp2 * _t74;
                }
            }
        }
        dd[3] = java.lang.Math.fma(_ld7, _t128, -_ld6 * _t125) - java.lang.Math.fma(_ld8, _t127, _ld9 * _t126);
        double _buf0 = java.lang.Math.fma(_ld6, _t128, _ld7 * _t125) + java.lang.Math.fma(_ld8, _t126, -(_ld9 * _t127));
        dd[0] = _buf0;
        double _buf1 = java.lang.Math.fma(_ld8, _t128, _ld9 * _t125) + java.lang.Math.fma(_ld7, _t127, -_ld6 * _t126);
        dd[1] = _buf1;
        double _buf2 = java.lang.Math.fma(_ld6, _t127, _ld7 * _t126) + java.lang.Math.fma(_ld9, _t128, -(_ld8 * _t125));
        dd[2] = _buf2;
    }

    /**
     * Piece 3 of {@code lookAlong_lh_sda02a0f4_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_lh_sda02a0f4_519_se762ce16_2_mulAdd(double _ld0, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t36, double _t15, double _t45, double _t16, double _t46, double _t62, double _t77, double _sp0, double _t70, double _t74, double _t54, double _t79, double _sp3, double _t50, double _t73, double _t83, double _sp1, double _t68, double _t80, double _sp2) {
        double _t125, _t126, _t127, _t128;
        if (((_ld0) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            _t125 = _sp0 * _t70;
            _t126 = _sp0 * _t74;
            _t127 = _sp0 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp3 * _t50;
                _t127 = _sp3 * _t73;
                _t128 = _sp3 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp1 * _t73;
                    _t126 = _sp1 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp1 * _t54;
                } else {
                    _t125 = _sp2 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp2 * _t68;
                    _t128 = _sp2 * _t74;
                }
            }
        }
        dd[3] = ((_ld7) * (_t128) - (_ld6 * _t125)) - ((_ld8) * (_t127) + (_ld9 * _t126));
        double _buf0 = ((_ld6) * (_t128) + (_ld7 * _t125)) + ((_ld8) * (_t126) - (_ld9 * _t127));
        dd[0] = _buf0;
        double _buf1 = ((_ld8) * (_t128) + (_ld9 * _t125)) + ((_ld7) * (_t127) - (_ld6 * _t126));
        dd[1] = _buf1;
        double _buf2 = ((_ld6) * (_t127) + (_ld7 * _t126)) + ((_ld9) * (_t128) - (_ld8 * _t125));
        dd[2] = _buf2;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up) {
        return lookAlong_lh_fma(dir, up, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up) {
        return lookAlong_lh_mulAdd(dir, up, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t7_inv = 1.0 / _t7;
        double _t8 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = java.lang.Math.fma(dirY * -_t8, _t7_inv, upY);
        double _t28 = java.lang.Math.fma(dirZ * -_t8, _t7_inv, upZ);
        double _t29 = java.lang.Math.fma(dirX * -_t8, _t7_inv, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -(dirY * _t28));
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        return lookAlong_lh_sd504c7d6_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t7_inv = 1.0 / _t7;
        double _t8 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = ((dirY * -_t8) * (_t7_inv) + (upY));
        double _t28 = ((dirZ * -_t8) * (_t7_inv) + (upZ));
        double _t29 = ((dirX * -_t8) * (_t7_inv) + (upX));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        return lookAlong_lh_sd504c7d6_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36)))));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_sd504c7d6_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        return lookAlong_lh_sd504c7d6_2_fma(dirZ, dest, sd, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, java.lang.Math.fma(-_t36, _t45, 1.0), java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0)))), java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0)))));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_sd504c7d6_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        return lookAlong_lh_sd504c7d6_2_mulAdd(dirZ, dest, sd, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, ((1.0) - (_t36) * (_t45)), ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0)))))))), ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0)))))))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_2_fma(double dirZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79) {
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t77));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _t83 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t83));
        double _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            _t125 = _sp0 * _t70;
            _t126 = _sp0 * _t74;
            _t127 = _sp0 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp3 * _t50;
                _t127 = _sp3 * _t73;
                _t128 = _sp3 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp1 * _t73;
                    _t126 = _sp1 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp1 * _t54;
                } else {
                    _t125 = _sp2 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp2 * _t68;
                    _t128 = _sp2 * _t74;
                }
            }
        }
        return lookAlong_lh_sd504c7d6_3_fma(dest, sd, dd, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_2_mulAdd(double dirZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79) {
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t77));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _t83 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t83));
        double _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            _t125 = _sp0 * _t70;
            _t126 = _sp0 * _t74;
            _t127 = _sp0 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp3 * _t50;
                _t127 = _sp3 * _t73;
                _t128 = _sp3 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp1 * _t73;
                    _t126 = _sp1 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp1 * _t54;
                } else {
                    _t125 = _sp2 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp2 * _t68;
                    _t128 = _sp2 * _t74;
                }
            }
        }
        return lookAlong_lh_sd504c7d6_3_mulAdd(dest, sd, dd, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_3_fma(DoubleQuat dest, double[] sd, double[] dd, double _t125, double _t126, double _t127, double _t128) {
        double _buf0 = java.lang.Math.fma(sd[0], _t128, sd[3] * _t125) + java.lang.Math.fma(sd[1], _t126, -(sd[2] * _t127));
        double _buf1 = java.lang.Math.fma(sd[1], _t128, sd[2] * _t125) + java.lang.Math.fma(sd[3], _t127, -sd[0] * _t126);
        double _buf2 = java.lang.Math.fma(sd[0], _t127, sd[3] * _t126) + java.lang.Math.fma(sd[2], _t128, -(sd[1] * _t125));
        dd[3] = java.lang.Math.fma(sd[3], _t128, -sd[0] * _t125) - java.lang.Math.fma(sd[1], _t127, sd[2] * _t126);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_3_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t125, double _t126, double _t127, double _t128) {
        double _buf0 = ((sd[0]) * (_t128) + (sd[3] * _t125)) + ((sd[1]) * (_t126) - (sd[2] * _t127));
        double _buf1 = ((sd[1]) * (_t128) + (sd[2] * _t125)) + ((sd[3]) * (_t127) - (sd[0] * _t126));
        double _buf2 = ((sd[0]) * (_t127) + (sd[3] * _t126)) + ((sd[2]) * (_t128) - (sd[1] * _t125));
        dd[3] = ((sd[3]) * (_t128) - (sd[0] * _t125)) - ((sd[1]) * (_t127) + (sd[2] * _t126));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_lh_degenerate_s118f679_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, java.lang.Math.fma(_t44, _t25, _t22));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        return lookAlong_lh_degenerate_s118f679_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, ((_t44) * (_t25) + (_t22)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s118f679_2_fma(dest, sd, dd, _t24, _t25, _t26, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t26) - (_t46 * _t25));
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s118f679_2_mulAdd(dest, sd, dd, _t24, _t25, _t26, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t26, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t78, double _t89, double _t93, double _t96) {
        double _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_s118f679_3_fma(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t26, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t78, double _t89, double _t93, double _t96) {
        double _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_s118f679_3_mulAdd(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_3_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp3, double _t103, double _t104, double _sp0, double _sp1, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5 * java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5 * java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_degenerate_s118f679_4_fma(dest, sd, dd, _t149, _t150, _t151, _t152, java.lang.Math.fma(sd[0], _t152, sd[3] * _t149) + java.lang.Math.fma(sd[1], _t150, -(sd[2] * _t151)), java.lang.Math.fma(sd[1], _t152, sd[2] * _t149) + java.lang.Math.fma(sd[3], _t151, -sd[0] * _t150), java.lang.Math.fma(sd[0], _t151, sd[3] * _t150) + java.lang.Math.fma(sd[2], _t152, -(sd[1] * _t149)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_3_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp3, double _t103, double _t104, double _sp0, double _sp1, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5 * java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5 * java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        double _buf0 = ((sd[0]) * (_t152) + (sd[3] * _t149)) + ((sd[1]) * (_t150) - (sd[2] * _t151));
        double _buf1 = ((sd[1]) * (_t152) + (sd[2] * _t149)) + ((sd[3]) * (_t151) - (sd[0] * _t150));
        double _buf2 = ((sd[0]) * (_t151) + (sd[3] * _t150)) + ((sd[2]) * (_t152) - (sd[1] * _t149));
        dd[3] = ((sd[3]) * (_t152) - (sd[0] * _t149)) - ((sd[1]) * (_t151) + (sd[2] * _t150));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s118f679_4_fma(DoubleQuat dest, double[] sd, double[] dd, double _t149, double _t150, double _t151, double _t152, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t152, -sd[0] * _t149) - java.lang.Math.fma(sd[1], _t151, sd[2] * _t150);
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
    private DoubleQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        return lookAlong_lh_degenerate_s8cf8056b_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        return lookAlong_lh_degenerate_s8cf8056b_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s8cf8056b_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_s118f679_3_fma(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_s118f679_3_mulAdd(dest, sd, dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dir, up, dest);
        double _t9 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        double _ct0 = lookAlong_rh_s6114deda_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t8, _t9);
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dir, up, dest);
        lookAlong_rh_s6114deda_519_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t8, _t9, _ct0);
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dir, up, dest);
        double _t9 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        double _ct0 = lookAlong_rh_s6114deda_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t8, _t9);
        if (!(_ct0 > ((_t8) * (((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dir, up, dest);
        lookAlong_rh_s6114deda_519_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t8, _t9, _ct0);
        return dest;
    }

    /**
     * Part 1 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private double lookAlong_rh_s6114deda_1_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t8, double _t9) {
        double _t8_inv = 1.0 / _t8;
        double _t30 = java.lang.Math.fma(_ld2 * -_t9, _t8_inv, _ld5);
        double _t31 = java.lang.Math.fma(_ld1 * -_t9, _t8_inv, _ld4);
        double _t39 = java.lang.Math.fma(_ld1, _t30, -(_ld2 * _t31));
        double _t29 = java.lang.Math.fma(_ld0 * -_t9, _t8_inv, _ld3);
        double _t38 = java.lang.Math.fma(_ld2, _t29, -(_ld0 * _t30));
        double _t40 = java.lang.Math.fma(_ld0, _t31, -(_ld1 * _t29));
        return java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
    }

    /**
     * Part 1 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private double lookAlong_rh_s6114deda_1_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t8, double _t9) {
        double _t8_inv = 1.0 / _t8;
        double _t30 = ((_ld2 * -_t9) * (_t8_inv) + (_ld5));
        double _t31 = ((_ld1 * -_t9) * (_t8_inv) + (_ld4));
        double _t39 = ((_ld1) * (_t30) - (_ld2 * _t31));
        double _t29 = ((_ld0 * -_t9) * (_t8_inv) + (_ld3));
        double _t38 = ((_ld2) * (_t29) - (_ld0 * _t30));
        double _t40 = ((_ld0) * (_t31) - (_ld1 * _t29));
        return ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
    }

    /**
     * Part of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t8, double _t9, double _ct0) {
        double _t8_inv = 1.0 / _t8;
        double _t30 = java.lang.Math.fma(_ld2 * -_t9, _t8_inv, _ld5);
        double _t31 = java.lang.Math.fma(_ld1 * -_t9, _t8_inv, _ld4);
        double _t39 = java.lang.Math.fma(_ld1, _t30, -(_ld2 * _t31));
        double _t29 = java.lang.Math.fma(_ld0 * -_t9, _t8_inv, _ld3);
        double _t38 = java.lang.Math.fma(_ld2, _t29, -(_ld0 * _t30));
        double _t40 = java.lang.Math.fma(_ld0, _t31, -(_ld1 * _t29));
        double _t0 = -_ld0;
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = _ld1 * _t16;
        double _t48 = _t39 * _t47;
        double _t18 = _ld0 * _t16;
        double _t49 = _t38 * _t47;
        double _t20 = -_t18;
        double _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0))));
        double _t19 = _ld2 * _t16;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        lookAlong_rh_s6114deda_519_s1a951498_1_fma(_ld0, _ld2, _ld6, _ld7, _ld8, _ld9, dd, _t39, _t38, _t40, _t0, _t16, _t47, _t17, _t48, _t18, _t49, java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), _t20, _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_ld1, _t16, _t48), _t65, -_t19 - _t65, java.lang.Math.fma(_t18, _t50, -_t19 * _t48));
    }

    /**
     * Part of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t8, double _t9, double _ct0) {
        double _t8_inv = 1.0 / _t8;
        double _t30 = ((_ld2 * -_t9) * (_t8_inv) + (_ld5));
        double _t31 = ((_ld1 * -_t9) * (_t8_inv) + (_ld4));
        double _t39 = ((_ld1) * (_t30) - (_ld2 * _t31));
        double _t29 = ((_ld0 * -_t9) * (_t8_inv) + (_ld3));
        double _t38 = ((_ld2) * (_t29) - (_ld0 * _t30));
        double _t40 = ((_ld0) * (_t31) - (_ld1 * _t29));
        double _t0 = -_ld0;
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = _ld1 * _t16;
        double _t48 = _t39 * _t47;
        double _t18 = _ld0 * _t16;
        double _t49 = _t38 * _t47;
        double _t20 = -_t18;
        double _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0))))))));
        double _t19 = _ld2 * _t16;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        lookAlong_rh_s6114deda_519_s1a951498_1_mulAdd(_ld0, _ld2, _ld6, _ld7, _ld8, _ld9, dd, _t39, _t38, _t40, _t0, _t16, _t47, _t17, _t48, _t18, _t49, ((_t17) * (_t48) - (_t18 * _t49)), _t20, _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), ((_ld1) * (_t16) + (_t48)), _t65, -_t19 - _t65, ((_t18) * (_t50) - (_t19 * _t48)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh_s6114deda_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_s1a951498_1_fma(double _ld0, double _ld2, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t39, double _t38, double _t40, double _t0, double _t16, double _t47, double _t17, double _t48, double _t18, double _t49, double _t64, double _t20, double _t79, double _sp0, double _t52, double _t65, double _t72, double _t66) {
        double _t22 = -_t17;
        double _t23 = java.lang.Math.fma(_ld0, _t16, 1.0);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t41 = -_t38;
        double _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        double _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        lookAlong_rh_s6114deda_519_s1a951498_2_fma(_ld6, _ld7, _ld8, _ld9, dd, _t38, _t0, _t16, _t47, _t49, _t64, _t20, _t79, _sp0, _t52, _t72, java.lang.Math.fma(-_t40, _t47, _t66), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(_t39, _t47, _t22), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), java.lang.Math.fma(-_ld2, _t16, _t65), _t85, 0.5 * (1.0 / java.lang.Math.sqrt(_t85)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh_s6114deda_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_s1a951498_1_mulAdd(double _ld0, double _ld2, double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t39, double _t38, double _t40, double _t0, double _t16, double _t47, double _t17, double _t48, double _t18, double _t49, double _t64, double _t20, double _t79, double _sp0, double _t52, double _t65, double _t72, double _t66) {
        double _t22 = -_t17;
        double _t23 = ((_ld0) * (_t16) + (1.0));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t41 = -_t38;
        double _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        double _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        lookAlong_rh_s6114deda_519_s1a951498_2_mulAdd(_ld6, _ld7, _ld8, _ld9, dd, _t38, _t0, _t16, _t47, _t49, _t64, _t20, _t79, _sp0, _t52, _t72, ((_t66) - (_t40) * (_t47)), _t78, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), ((_t40) * (_t47) + (_t66)), ((_t39) * (_t47) + (_t22)), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), ((_t65) - (_ld2) * (_t16)), _t85, 0.5 * (1.0 / java.lang.Math.sqrt(_t85)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_s6114deda_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_s1a951498_2_fma(double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t38, double _t0, double _t16, double _t47, double _t49, double _t64, double _t20, double _t79, double _sp0, double _t52, double _t72, double _t75, double _t78, double _sp1, double _t74, double _t56, double _t81, double _sp3, double _t70, double _t85, double _sp2) {
        double _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            _t126 = _sp0 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp0 * _t72;
            _t129 = _sp0 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp1 * _t74;
                _t127 = _sp1 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp1 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp3 * _t52;
                    _t128 = _sp3 * _t74;
                    _t129 = _sp3 * _t70;
                } else {
                    _t126 = _sp2 * _t70;
                    _t127 = _sp2 * _t75;
                    _t128 = _sp2 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        dd[3] = java.lang.Math.fma(_ld7, _t127, -_ld6 * _t128) - java.lang.Math.fma(_ld8, _t126, _ld9 * _t129);
        double _buf0 = java.lang.Math.fma(_ld6, _t127, _ld7 * _t128) + java.lang.Math.fma(_ld8, _t129, -(_ld9 * _t126));
        dd[0] = _buf0;
        double _buf1 = java.lang.Math.fma(_ld8, _t127, _ld9 * _t128) + java.lang.Math.fma(_ld7, _t126, -_ld6 * _t129);
        dd[1] = _buf1;
        double _buf2 = java.lang.Math.fma(_ld6, _t126, _ld7 * _t129) + java.lang.Math.fma(_ld9, _t127, -(_ld8 * _t128));
        dd[2] = _buf2;
    }

    /**
     * Piece 3 of {@code lookAlong_rh_s6114deda_519}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private void lookAlong_rh_s6114deda_519_s1a951498_2_mulAdd(double _ld6, double _ld7, double _ld8, double _ld9, double[] dd, double _t38, double _t0, double _t16, double _t47, double _t49, double _t64, double _t20, double _t79, double _sp0, double _t52, double _t72, double _t75, double _t78, double _sp1, double _t74, double _t56, double _t81, double _sp3, double _t70, double _t85, double _sp2) {
        double _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            _t126 = _sp0 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp0 * _t72;
            _t129 = _sp0 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp1 * _t74;
                _t127 = _sp1 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp1 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp3 * _t52;
                    _t128 = _sp3 * _t74;
                    _t129 = _sp3 * _t70;
                } else {
                    _t126 = _sp2 * _t70;
                    _t127 = _sp2 * _t75;
                    _t128 = _sp2 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        dd[3] = ((_ld7) * (_t127) - (_ld6 * _t128)) - ((_ld8) * (_t126) + (_ld9 * _t129));
        double _buf0 = ((_ld6) * (_t127) + (_ld7 * _t128)) + ((_ld8) * (_t129) - (_ld9 * _t126));
        dd[0] = _buf0;
        double _buf1 = ((_ld8) * (_t127) + (_ld9 * _t128)) + ((_ld7) * (_t126) - (_ld6 * _t129));
        dd[1] = _buf1;
        double _buf2 = ((_ld6) * (_t126) + (_ld7 * _t129)) + ((_ld9) * (_t127) - (_ld8 * _t128));
        dd[2] = _buf2;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up) {
        return lookAlong_rh_fma(dir, up, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up) {
        return lookAlong_rh_mulAdd(dir, up, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t8_inv = 1.0 / _t8;
        double _t9 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = java.lang.Math.fma(dirZ * -_t9, _t8_inv, upZ);
        double _t30 = java.lang.Math.fma(dirY * -_t9, _t8_inv, upY);
        double _t31 = java.lang.Math.fma(dirX * -_t9, _t8_inv, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -(dirX * _t29));
        return lookAlong_rh_s183ca1c4_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t8_inv = 1.0 / _t8;
        double _t9 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = ((dirZ * -_t9) * (_t8_inv) + (upZ));
        double _t30 = ((dirY * -_t9) * (_t8_inv) + (upY));
        double _t31 = ((dirX * -_t9) * (_t8_inv) + (upX));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_s183ca1c4_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_s183ca1c4_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_s183ca1c4_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_s183ca1c4_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_s183ca1c4_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t16, double _t18, double _t20, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp1, double _t81) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        double _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t85));
        double _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            _t126 = _sp0 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp0 * _t72;
            _t129 = _sp0 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp1 * _t74;
                _t127 = _sp1 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp1 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp3 * _t52;
                    _t128 = _sp3 * _t74;
                    _t129 = _sp3 * _t70;
                } else {
                    _t126 = _sp2 * _t70;
                    _t127 = _sp2 * _t75;
                    _t128 = _sp2 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_s183ca1c4_3_fma(dest, sd, dd, _t126, _t127, _t128, _t129, java.lang.Math.fma(sd[0], _t127, sd[3] * _t128) + java.lang.Math.fma(sd[1], _t129, -(sd[2] * _t126)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t16, double _t18, double _t20, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp1, double _t81) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        double _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t85));
        double _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            _t126 = _sp0 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp0 * _t72;
            _t129 = _sp0 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp1 * _t74;
                _t127 = _sp1 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp1 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp3 * _t52;
                    _t128 = _sp3 * _t74;
                    _t129 = _sp3 * _t70;
                } else {
                    _t126 = _sp2 * _t70;
                    _t127 = _sp2 * _t75;
                    _t128 = _sp2 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_s183ca1c4_3_mulAdd(dest, sd, dd, _t126, _t127, _t128, _t129, ((sd[0]) * (_t127) + (sd[3] * _t128)) + ((sd[1]) * (_t129) - (sd[2] * _t126)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_3_fma(DoubleQuat dest, double[] sd, double[] dd, double _t126, double _t127, double _t128, double _t129, double _buf0) {
        double _buf1 = java.lang.Math.fma(sd[1], _t127, sd[2] * _t128) + java.lang.Math.fma(sd[3], _t126, -sd[0] * _t129);
        double _buf2 = java.lang.Math.fma(sd[0], _t126, sd[3] * _t129) + java.lang.Math.fma(sd[2], _t127, -(sd[1] * _t128));
        dd[3] = java.lang.Math.fma(sd[3], _t127, -sd[0] * _t128) - java.lang.Math.fma(sd[1], _t126, sd[2] * _t129);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_3_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t126, double _t127, double _t128, double _t129, double _buf0) {
        double _buf1 = ((sd[1]) * (_t127) + (sd[2] * _t128)) + ((sd[3]) * (_t126) - (sd[0] * _t129));
        double _buf2 = ((sd[0]) * (_t126) + (sd[3] * _t129)) + ((sd[2]) * (_t127) - (sd[1] * _t128));
        dd[3] = ((sd[3]) * (_t127) - (sd[0] * _t128)) - ((sd[1]) * (_t126) + (sd[2] * _t129));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        return lookAlong_rh_degenerate_s28373b3_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        return lookAlong_rh_degenerate_s28373b3_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23)))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44) {
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s28373b3_2_fma(dest, sd, dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44) {
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t25) - (_t46 * _t26));
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s28373b3_2_mulAdd(dest, sd, dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t76, double _t88, double _t93) {
        double _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s28373b3_3_fma(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t76, double _t88, double _t93) {
        double _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s28373b3_3_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_3_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5 * java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5 * java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5 * java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_degenerate_s28373b3_4_fma(dest, sd, dd, _t149, _t150, _t151, _t152, java.lang.Math.fma(sd[0], _t150, sd[3] * _t151) + java.lang.Math.fma(sd[1], _t152, -(sd[2] * _t149)), java.lang.Math.fma(sd[1], _t150, sd[2] * _t151) + java.lang.Math.fma(sd[3], _t149, -sd[0] * _t152), java.lang.Math.fma(sd[0], _t149, sd[3] * _t152) + java.lang.Math.fma(sd[2], _t150, -(sd[1] * _t151)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_3_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5 * java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5 * java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5 * java.lang.Math.sqrt(_t104);
                }
            }
        }
        double _buf0 = ((sd[0]) * (_t150) + (sd[3] * _t151)) + ((sd[1]) * (_t152) - (sd[2] * _t149));
        double _buf1 = ((sd[1]) * (_t150) + (sd[2] * _t151)) + ((sd[3]) * (_t149) - (sd[0] * _t152));
        double _buf2 = ((sd[0]) * (_t149) + (sd[3] * _t152)) + ((sd[2]) * (_t150) - (sd[1] * _t151));
        dd[3] = ((sd[3]) * (_t150) - (sd[0] * _t151)) - ((sd[1]) * (_t149) + (sd[2] * _t152));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Piece 5 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s28373b3_4_fma(DoubleQuat dest, double[] sd, double[] dd, double _t149, double _t150, double _t151, double _t152, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t150, -sd[0] * _t151) - java.lang.Math.fma(sd[1], _t149, sd[2] * _t152);
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
    private DoubleQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_sf2324b45_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_sf2324b45_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sf2324b45_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sf2324b45_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s28373b3_3_fma(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s28373b3_3_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    public DoubleQuat lookAlong(Double3R dir, Double3R up, Handedness handedness, @Mutated DoubleQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, dest) : lookAlong_lh_mulAdd(dir, up, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up) : lookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness, @Mutated DoubleQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    public DoubleQuat lookAlong(Double3R dir, Double3R up, @Mutated DoubleQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, dest) : lookAlong_rh_mulAdd(dir, up, dest)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dir, up, epsilon, dest);
        double _t8 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        double _ct0 = lookAlong_lh_sda02a0f4_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dir, up, epsilon, dest);
        lookAlong_lh_sda02a0f4_519_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t7, _t8, _ct0);
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dir, up, epsilon, dest);
        double _t8 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        double _ct0 = lookAlong_lh_sda02a0f4_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > ((_t7) * (epsilon * ((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dir, up, epsilon, dest);
        lookAlong_lh_sda02a0f4_519_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t7, _t8, _ct0);
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, double epsilon) {
        return lookAlong_lh_fma(dir, up, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        return lookAlong_lh_mulAdd(dir, up, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t7_inv = 1.0 / _t7;
        double _t8 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = java.lang.Math.fma(dirY * -_t8, _t7_inv, upY);
        double _t28 = java.lang.Math.fma(dirZ * -_t8, _t7_inv, upZ);
        double _t29 = java.lang.Math.fma(dirX * -_t8, _t7_inv, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -(dirY * _t28));
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        return lookAlong_lh_s7e43cb75_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t7_inv = 1.0 / _t7;
        double _t8 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = ((dirY * -_t8) * (_t7_inv) + (upY));
        double _t28 = ((dirZ * -_t8) * (_t7_inv) + (upZ));
        double _t29 = ((dirX * -_t8) * (_t7_inv) + (upX));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        return lookAlong_lh_s7e43cb75_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36)))));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_s7e43cb75_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        return lookAlong_lh_sd504c7d6_2_fma(dirZ, dest, sd, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, java.lang.Math.fma(-_t36, _t45, 1.0), java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0)))), java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0)))));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_s7e43cb75_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        return lookAlong_lh_sd504c7d6_2_mulAdd(dirZ, dest, sd, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, ((1.0) - (_t36) * (_t45)), ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0)))))))), ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0)))))))));
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_lh_degenerate_sbbaf9832_1_fma(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, java.lang.Math.fma(_t44, _t25, _t22));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        return lookAlong_lh_degenerate_sbbaf9832_1_mulAdd(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, ((_t44) * (_t25) + (_t22)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sbbaf9832_1_fma(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s118f679_2_fma(dest, sd, dd, _t24, _t25, _t26, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sbbaf9832_1_mulAdd(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t26) - (_t46 * _t25));
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s118f679_2_mulAdd(dest, sd, dd, _t24, _t25, _t26, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        return lookAlong_lh_degenerate_s84179920_1_fma(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        return lookAlong_lh_degenerate_s84179920_1_mulAdd(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s84179920_1_fma(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s8cf8056b_2_fma(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s84179920_1_mulAdd(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(dest, sd, dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dir, up, epsilon, dest);
        double _t9 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        double _ct0 = lookAlong_rh_s6114deda_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t8, _t9);
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dir, up, epsilon, dest);
        lookAlong_rh_s6114deda_519_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t8, _t9, _ct0);
        return dest;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _ld6 = sd[0];
        double _ld7 = sd[3];
        double _ld8 = sd[1];
        double _ld9 = sd[2];
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dir, up, epsilon, dest);
        double _t9 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        double _ct0 = lookAlong_rh_s6114deda_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t8, _t9);
        if (!(_ct0 > ((_t8) * (epsilon * ((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dir, up, epsilon, dest);
        lookAlong_rh_s6114deda_519_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld9, dd, _t8, _t9, _ct0);
        return dest;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, double epsilon) {
        return lookAlong_rh_fma(dir, up, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        return lookAlong_rh_mulAdd(dir, up, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t8_inv = 1.0 / _t8;
        double _t9 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = java.lang.Math.fma(dirZ * -_t9, _t8_inv, upZ);
        double _t30 = java.lang.Math.fma(dirY * -_t9, _t8_inv, upY);
        double _t31 = java.lang.Math.fma(dirX * -_t9, _t8_inv, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -(dirX * _t29));
        return lookAlong_rh_scccad9f_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t8_inv = 1.0 / _t8;
        double _t9 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = ((dirZ * -_t9) * (_t8_inv) + (upZ));
        double _t30 = ((dirY * -_t9) * (_t8_inv) + (upY));
        double _t31 = ((dirX * -_t9) * (_t8_inv) + (upX));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return lookAlong_rh_scccad9f_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest, sd, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_scccad9f_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return lookAlong_rh_s183ca1c4_2_fma(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_scccad9f_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return lookAlong_rh_s183ca1c4_2_mulAdd(dest, sd, dd, _t0, _t16, _t18, _t20, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.doubleQuat() : this);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        return lookAlong_rh_degenerate_s239ab3f8_1_fma(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        return lookAlong_rh_degenerate_s239ab3f8_1_mulAdd(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23)))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s239ab3f8_1_fma(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44) {
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s28373b3_2_fma(dest, sd, dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s239ab3f8_1_mulAdd(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44) {
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t25) - (_t46 * _t26));
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s28373b3_2_mulAdd(dest, sd, dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, -_t70, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return lookAlong_rh_degenerate_sdc8a7b3e_1_fma(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        return lookAlong_rh_degenerate_sdc8a7b3e_1_mulAdd(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_1_fma(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
        double _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sdc8a7b3e_2_fma(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_1_mulAdd(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
        double _t56 = ((_t45) * (_t25) - (_t46 * _t26));
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sdc8a7b3e_2_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_2_fma(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97) {
        double _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s28373b3_3_fma(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_2_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97) {
        double _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s28373b3_3_mulAdd(dest, sd, dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon, Handedness handedness, @Mutated DoubleQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon, dest) : lookAlong_lh_mulAdd(dir, up, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon) : lookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness, @Mutated DoubleQuat dest) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon, dest) : lookAlong_rh_mulAdd(dir, up, epsilon, dest)); }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated public DoubleQuat makeRotationAxis(double angle, Double3R axis) {
        double[] axisData = ((Double3Impl) axis).data;
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axis);
        if (axisData[1] == 0 && axisData[2] == 0 && java.lang.Math.abs(axisData[0]) == 1) return makeRotationX(axisData[0] * angle);
        if (axisData[0] == 0 && axisData[2] == 0 && java.lang.Math.abs(axisData[1]) == 1) return makeRotationY(axisData[1] * angle);
        if (axisData[0] == 0 && axisData[1] == 0 && java.lang.Math.abs(axisData[2]) == 1) return makeRotationZ(axisData[2] * angle);
        if (SimdMath.USE_FMA) return makeRotationAxis_fma(angle, axis);
        return makeRotationAxis_mulAdd(angle, axis);
    }

    private DoubleQuat makeRotationAxis_fma(double angle, Double3R axis) {
        double[] dd = this.data;
        double[] axisData = ((Double3Impl) axis).data;
        double _t0 = 0.5 * angle;
        DoubleVector.zero(COL_SPECIES).withLane(0, axisData[0]).withLane(1, axisData[1]).withLane(2, axisData[2]).fma(DoubleVector.broadcast(COL_SPECIES, Math.sinWithCos(_t0)), DoubleVector.broadcast(COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dd, 0);
        return this;
    }

    private DoubleQuat makeRotationAxis_mulAdd(double angle, Double3R axis) {
        double[] dd = this.data;
        double[] axisData = ((Double3Impl) axis).data;
        double _t0 = 0.5 * angle;
        DoubleVector.zero(COL_SPECIES).withLane(0, axisData[0]).withLane(1, axisData[1]).withLane(2, axisData[2]).mul(DoubleVector.broadcast(COL_SPECIES, Math.sinWithCos(_t0))).add(DoubleVector.broadcast(COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dd, 0);
        return this;
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
    @Mutated public DoubleQuat makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axisX, axisY, axisZ);
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        if (SimdMath.USE_FMA) {
            double[] dd = this.data;
            double _t0 = 0.5 * angle;
            DoubleVector.zero(_COL_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(DoubleVector.broadcast(_COL_SPECIES, Math.sinWithCos(_t0)), DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dd, 0);
            return this;
        }
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        DoubleVector.zero(_COL_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(DoubleVector.broadcast(_COL_SPECIES, Math.sinWithCos(_t0))).add(DoubleVector.broadcast(_COL_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dd, 0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _t7 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        double _t8 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dir, up);
        double _ct0 = makeRotationLookAlong_lh_sa85678f4_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dir, up);
        makeRotationLookAlong_lh_sa85678f4_513_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8, _ct0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _t7 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        double _t8 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dir, up);
        double _ct0 = makeRotationLookAlong_lh_sa85678f4_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > ((_t7) * (((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dir, up);
        makeRotationLookAlong_lh_sa85678f4_513_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8, _ct0);
        return this;
    }

    /**
     * Part 1 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private double makeRotationLookAlong_lh_sa85678f4_1_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8) {
        double _t7_inv = 1.0 / _t7;
        double _t27 = java.lang.Math.fma(_ld2 * -_t8, _t7_inv, _ld5);
        double _t28 = java.lang.Math.fma(_ld0 * -_t8, _t7_inv, _ld3);
        double _t29 = java.lang.Math.fma(_ld1 * -_t8, _t7_inv, _ld4);
        double _t36 = java.lang.Math.fma(_ld0, _t27, -(_ld2 * _t28));
        double _t37 = java.lang.Math.fma(_ld2, _t29, -(_ld1 * _t27));
        double _t38 = java.lang.Math.fma(_ld1, _t28, -(_ld0 * _t29));
        return java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
    }

    /**
     * Part 1 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private double makeRotationLookAlong_lh_sa85678f4_1_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8) {
        double _t7_inv = 1.0 / _t7;
        double _t27 = ((_ld2 * -_t8) * (_t7_inv) + (_ld5));
        double _t28 = ((_ld0 * -_t8) * (_t7_inv) + (_ld3));
        double _t29 = ((_ld1 * -_t8) * (_t7_inv) + (_ld4));
        double _t36 = ((_ld0) * (_t27) - (_ld2 * _t28));
        double _t37 = ((_ld2) * (_t29) - (_ld1 * _t27));
        double _t38 = ((_ld1) * (_t28) - (_ld0 * _t29));
        return ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
    }

    /**
     * Part of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8, double _ct0) {
        double _t7_inv = 1.0 / _t7;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = _ld0 * _t15;
        double _t17 = _ld1 * _t15;
        double _t18 = _ld2 * _t15;
        double _t19 = -_t17;
        double _t27 = java.lang.Math.fma(_ld2 * -_t8, _t7_inv, _ld5);
        double _t28 = java.lang.Math.fma(_ld0 * -_t8, _t7_inv, _ld3);
        double _t29 = java.lang.Math.fma(_ld1 * -_t8, _t7_inv, _ld4);
        double _t36 = java.lang.Math.fma(_ld0, _t27, -(_ld2 * _t28));
        double _t37 = java.lang.Math.fma(_ld2, _t29, -(_ld1 * _t27));
        double _t38 = java.lang.Math.fma(_ld1, _t28, -(_ld0 * _t29));
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_1_fma(_ld0, this.data, -_ld0, _t15, _t16, _t17, _t19, -_t16, _t36, _t45, _t46, _t47, java.lang.Math.fma(-_t36, _t45, 1.0), java.lang.Math.fma(_ld1, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(_ld2, _t15, _t63), java.lang.Math.fma(_ld2, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65));
    }

    /**
     * Part of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t7, double _t8, double _ct0) {
        double _t7_inv = 1.0 / _t7;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = _ld0 * _t15;
        double _t17 = _ld1 * _t15;
        double _t18 = _ld2 * _t15;
        double _t19 = -_t17;
        double _t27 = ((_ld2 * -_t8) * (_t7_inv) + (_ld5));
        double _t28 = ((_ld0 * -_t8) * (_t7_inv) + (_ld3));
        double _t29 = ((_ld1 * -_t8) * (_t7_inv) + (_ld4));
        double _t36 = ((_ld0) * (_t27) - (_ld2 * _t28));
        double _t37 = ((_ld2) * (_t29) - (_ld1 * _t27));
        double _t38 = ((_ld1) * (_t28) - (_ld0 * _t29));
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_1_mulAdd(_ld0, this.data, -_ld0, _t15, _t16, _t17, _t19, -_t16, _t36, _t45, _t46, _t47, ((1.0) - (_t36) * (_t45)), ((_ld1) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((_ld2) * (_t15) + (_t63)), ((_ld2) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh_sa85678f4_513}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_1_fma(double _ld0, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74) {
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_ld0, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_2_fma(_ld0, dd, _t15, _t16, _t36, _t45, _t46, _t50, _t55, _t62, _t68, _t69, _t73, _t74, _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh_sa85678f4_513}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_1_mulAdd(double _ld0, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74) {
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((_ld0) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((_ld0) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_2_mulAdd(_ld0, dd, _t15, _t16, _t36, _t45, _t46, _t50, _t55, _t62, _t68, _t69, _t73, _t74, _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_sa85678f4_513}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_2_fma(double _ld0, double[] dd, double _t15, double _t16, double _t36, double _t45, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79, double _t80, double _t81, double _sp2, double _sp3, double _sp1) {
        if (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
                }
            }
        }
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_sa85678f4_513}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private void makeRotationLookAlong_lh_sa85678f4_513_s336fe20e_2_mulAdd(double _ld0, double[] dd, double _t15, double _t16, double _t36, double _t45, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79, double _t80, double _t81, double _sp2, double _sp3, double _sp1) {
        if (((_ld0) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
                }
            }
        }
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t7_inv = 1.0 / _t7;
        double _t8 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = java.lang.Math.fma(dirY * -_t8, _t7_inv, upY);
        double _t28 = java.lang.Math.fma(dirZ * -_t8, _t7_inv, upZ);
        double _t29 = java.lang.Math.fma(dirX * -_t8, _t7_inv, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -(dirY * _t28));
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_s5c9915aa_1_fma(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t7_inv = 1.0 / _t7;
        double _t8 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = ((dirY * -_t8) * (_t7_inv) + (upY));
        double _t28 = ((dirZ * -_t8) * (_t7_inv) + (upZ));
        double _t29 = ((dirX * -_t8) * (_t7_inv) + (upX));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(dirX, dirY, dirZ, dd, -dirZ, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, _ct0);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_fma(double dirX, double dirY, double dirZ, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return makeRotationLookAlong_lh_s5c9915aa_2_fma(dirZ, dd, _t15, _t16, _t36, _t45, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(double dirX, double dirY, double dirZ, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(dirZ, dd, _t15, _t16, _t36, _t45, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_2_fma(double dirZ, double[] dd, double _t15, double _t16, double _t36, double _t45, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79, double _t80, double _t81, double _sp2, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
                }
            }
        }
        return this;
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(double dirZ, double[] dd, double _t15, double _t16, double _t36, double _t45, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79, double _t80, double _t81, double _sp2, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
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
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_fma(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t44) * (_t25) + (_t22)), ((_t44) * (_t26) + (_t23)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_1_fma(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
        double _t56 = java.lang.Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_1_mulAdd(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
        double _t56 = ((_t45) * (_t26) - (_t46 * _t25));
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_fma(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97) {
        double _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_fma(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_mulAdd(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97) {
        double _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_mulAdd(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 4 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_fma(double[] dd, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t78;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t74;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t78;
                } else {
                    dd[0] = _sp2 * _t74;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        return this;
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 4 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_mulAdd(double[] dd, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t78;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t74;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t78;
                } else {
                    dd[0] = _sp2 * _t74;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
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
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100) {
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_fma(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100) {
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_mulAdd(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t8 = java.lang.Math.fma(dirData[2], dirData[2], java.lang.Math.fma(dirData[0], dirData[0], dirData[1] * dirData[1]));
        return makeRotationLookAlong_rh_saaa58f9e_1_fma(dir, up, this.data, dirData, upData, -dirData[2], _t8, 1.0 / _t8, java.lang.Math.fma(dirData[2], upData[2], java.lang.Math.fma(dirData[0], upData[0], dirData[1] * upData[1])));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t8 = ((dirData[2]) * (dirData[2]) + (((dirData[0]) * (dirData[0]) + (dirData[1] * dirData[1]))));
        return makeRotationLookAlong_rh_saaa58f9e_1_mulAdd(dir, up, this.data, dirData, upData, -dirData[2], _t8, 1.0 / _t8, ((dirData[2]) * (upData[2]) + (((dirData[0]) * (upData[0]) + (dirData[1] * upData[1])))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_1_fma(Double3R dir, Double3R up, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t8_inv, double _t9) {
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dir, up);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirData[0] * _t16;
        double _t18 = dirData[2] * _t16;
        double _t29 = java.lang.Math.fma(dirData[2] * -_t9, _t8_inv, upData[2]);
        double _t30 = java.lang.Math.fma(dirData[1] * -_t9, _t8_inv, upData[1]);
        double _t31 = java.lang.Math.fma(dirData[0] * -_t9, _t8_inv, upData[0]);
        double _t38 = java.lang.Math.fma(dirData[1], _t29, -(dirData[2] * _t30));
        double _t39 = java.lang.Math.fma(dirData[0], _t30, -(dirData[1] * _t31));
        double _t40 = java.lang.Math.fma(dirData[2], _t31, -(dirData[0] * _t29));
        return makeRotationLookAlong_rh_saaa58f9e_2_fma(dir, up, dd, dirData, upData, _t0, _t8, _t16, _t17, _t18, dirData[1] * _t16, -_t18, -_t17, java.lang.Math.fma(dirData[2], _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_1_mulAdd(Double3R dir, Double3R up, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t8_inv, double _t9) {
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dir, up);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirData[0] * _t16;
        double _t18 = dirData[2] * _t16;
        double _t29 = ((dirData[2] * -_t9) * (_t8_inv) + (upData[2]));
        double _t30 = ((dirData[1] * -_t9) * (_t8_inv) + (upData[1]));
        double _t31 = ((dirData[0] * -_t9) * (_t8_inv) + (upData[0]));
        double _t38 = ((dirData[1]) * (_t29) - (dirData[2] * _t30));
        double _t39 = ((dirData[0]) * (_t30) - (dirData[1] * _t31));
        double _t40 = ((dirData[2]) * (_t31) - (dirData[0] * _t29));
        return makeRotationLookAlong_rh_saaa58f9e_2_mulAdd(dir, up, dd, dirData, upData, _t0, _t8, _t16, _t17, _t18, dirData[1] * _t16, -_t18, -_t17, ((dirData[2]) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_2_fma(Double3R dir, Double3R up, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upData[2], upData[2], java.lang.Math.fma(upData[0], upData[0], upData[1] * upData[1])) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dir, up);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        return makeRotationLookAlong_rh_saaa58f9e_3_fma(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirData[0], _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirData[1], _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_2_mulAdd(Double3R dir, Double3R up, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (((upData[2]) * (upData[2]) + (((upData[0]) * (upData[0]) + (upData[1] * upData[1])))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dir, up);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        return makeRotationLookAlong_rh_saaa58f9e_3_mulAdd(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, ((dirData[0]) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirData[1]) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23)))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_3_fma(double[] dd, double _t0, double _t16, double _t18, double _t21, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t78));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            dd[0] = _sp0 * _t71;
            dd[1] = _sp0 * _t52;
            dd[2] = _sp0 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t78);
                dd[1] = _sp3 * _t74;
                dd[2] = _sp3 * _t56;
                dd[3] = _sp3 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp1 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[2] = _sp1 * _t70;
                    dd[3] = _sp1 * _t52;
                } else {
                    dd[0] = _sp2 * _t56;
                    dd[1] = _sp2 * _t70;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t84);
                    dd[3] = _sp2 * _t75;
                }
            }
        }
        return this;
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_saaa58f9e_3_mulAdd(double[] dd, double _t0, double _t16, double _t18, double _t21, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t78));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            dd[0] = _sp0 * _t71;
            dd[1] = _sp0 * _t52;
            dd[2] = _sp0 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t78);
                dd[1] = _sp3 * _t74;
                dd[2] = _sp3 * _t56;
                dd[3] = _sp3 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp1 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[2] = _sp1 * _t70;
                    dd[3] = _sp1 * _t52;
                } else {
                    dd[0] = _sp2 * _t56;
                    dd[1] = _sp2 * _t70;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t84);
                    dd[3] = _sp2 * _t75;
                }
            }
        }
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t8_inv = 1.0 / _t8;
        double _t9 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = java.lang.Math.fma(dirZ * -_t9, _t8_inv, upZ);
        double _t30 = java.lang.Math.fma(dirY * -_t9, _t8_inv, upY);
        double _t31 = java.lang.Math.fma(dirX * -_t9, _t8_inv, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -(dirX * _t29));
        return makeRotationLookAlong_rh_sf0b2d43c_1_fma(dirX, dirY, dirZ, upX, upY, upZ, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] dd = this.data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t8_inv = 1.0 / _t8;
        double _t9 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = ((dirZ * -_t9) * (_t8_inv) + (upZ));
        double _t30 = ((dirY * -_t9) * (_t8_inv) + (upY));
        double _t31 = ((dirX * -_t9) * (_t8_inv) + (upX));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return makeRotationLookAlong_rh_sf0b2d43c_2_fma(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_2_fma(double[] dd, double _t0, double _t16, double _t18, double _t21, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp3) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            dd[0] = _sp0 * _t71;
            dd[1] = _sp0 * _t52;
            dd[2] = _sp0 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t78);
                dd[1] = _sp3 * _t74;
                dd[2] = _sp3 * _t56;
                dd[3] = _sp3 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp1 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[2] = _sp1 * _t70;
                    dd[3] = _sp1 * _t52;
                } else {
                    dd[0] = _sp2 * _t56;
                    dd[1] = _sp2 * _t70;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t84);
                    dd[3] = _sp2 * _t75;
                }
            }
        }
        return this;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(double[] dd, double _t0, double _t16, double _t18, double _t21, double _t22, double _t38, double _t41, double _t47, double _t48, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp3) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            dd[0] = _sp0 * _t71;
            dd[1] = _sp0 * _t52;
            dd[2] = _sp0 * _t75;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t78);
                dd[1] = _sp3 * _t74;
                dd[2] = _sp3 * _t56;
                dd[3] = _sp3 * _t71;
            } else {
                if (_t64 > _t21) {
                    dd[0] = _sp1 * _t74;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[2] = _sp1 * _t70;
                    dd[3] = _sp1 * _t52;
                } else {
                    dd[0] = _sp2 * _t56;
                    dd[1] = _sp2 * _t70;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t84);
                    dd[3] = _sp2 * _t75;
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
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_fma(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t44, java.lang.Math.fma(_t44, _t26, _t23));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(Double3R dir, Double3R up) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t44, ((_t44) * (_t26) + (_t23)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_1_fma(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_2_fma(dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_1_mulAdd(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = ((_t44) * (_t25) + (_t22));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t25) - (_t46 * _t26));
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_2_mulAdd(dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_2_fma(double[] dd, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t76, double _t88, double _t93, double _t96) {
        double _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_3_fma(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_2_mulAdd(double[] dd, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t76, double _t88, double _t93, double _t96) {
        double _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_3_mulAdd(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 4 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_3_fma(double[] dd, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t74;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t76;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t74;
                } else {
                    dd[0] = _sp2 * _t76;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
                }
            }
        }
        return this;
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 4 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_s5c062b5b_3_mulAdd(double[] dd, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0) {
            dd[0] = _sp0 * _t96;
            dd[1] = _sp0 * _t74;
            dd[2] = _sp0 * _t108;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t100);
                dd[1] = _sp3 * _t107;
                dd[2] = _sp3 * _t76;
                dd[3] = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    dd[0] = _sp1 * _t107;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t102);
                    dd[2] = _sp1 * _t97;
                    dd[3] = _sp1 * _t74;
                } else {
                    dd[0] = _sp2 * _t76;
                    dd[1] = _sp2 * _t97;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t103);
                    dd[3] = _sp2 * _t108;
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
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_3_fma(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(double[] dd, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_3_mulAdd(dd, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along {@code dir} (the rotation
     * of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up) : makeRotationLookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along {@code dir} (the rotation
     * of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }

    /**
     * Set this quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _t7 = java.lang.Math.fma(_ld0, _ld0, java.lang.Math.fma(_ld1, _ld1, _ld2 * _ld2));
        double _t8 = java.lang.Math.fma(_ld0, _ld3, java.lang.Math.fma(_ld1, _ld4, _ld2 * _ld5));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dir, up, epsilon);
        double _ct0 = makeRotationLookAlong_lh_sa85678f4_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(_ld3, _ld3, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dir, up, epsilon);
        makeRotationLookAlong_lh_sa85678f4_513_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8, _ct0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _ld0 = dirData[2];
        double _ld1 = dirData[0];
        double _ld2 = dirData[1];
        double _ld3 = upData[2];
        double _ld4 = upData[0];
        double _ld5 = upData[1];
        double _t7 = ((_ld0) * (_ld0) + (((_ld1) * (_ld1) + (_ld2 * _ld2))));
        double _t8 = ((_ld0) * (_ld3) + (((_ld1) * (_ld4) + (_ld2 * _ld5))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dir, up, epsilon);
        double _ct0 = makeRotationLookAlong_lh_sa85678f4_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8);
        if (!(_ct0 > ((_t7) * (epsilon * ((_ld3) * (_ld3) + (((_ld4) * (_ld4) + (_ld5 * _ld5))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dir, up, epsilon);
        makeRotationLookAlong_lh_sa85678f4_513_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t7, _t8, _ct0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double[] dd = this.data;
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t7_inv = 1.0 / _t7;
        double _t8 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = java.lang.Math.fma(dirY * -_t8, _t7_inv, upY);
        double _t28 = java.lang.Math.fma(dirZ * -_t8, _t7_inv, upZ);
        double _t29 = java.lang.Math.fma(dirX * -_t8, _t7_inv, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -(dirY * _t28));
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        return makeRotationLookAlong_lh_s4c0dc561_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double[] dd = this.data;
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t7_inv = 1.0 / _t7;
        double _t8 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        double _t27 = ((dirY * -_t8) * (_t7_inv) + (upY));
        double _t28 = ((dirZ * -_t8) * (_t7_inv) + (upZ));
        double _t29 = ((dirX * -_t8) * (_t7_inv) + (upX));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        return makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dd, -dirZ, _t7, _t15, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36, _t37, _t38, ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        return makeRotationLookAlong_lh_s4c0dc561_2_fma(dirZ, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, java.lang.Math.fma(-_t36, _t45, 1.0), java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, double[] dd, double _t0, double _t7, double _t15, double _t16, double _t17, double _t18, double _t19, double _t21, double _t36, double _t37, double _t38, double _ct0) {
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        return makeRotationLookAlong_lh_s4c0dc561_2_mulAdd(dirZ, dd, _t0, _t15, _t16, _t17, _t19, _t21, _t36, _t45, _t46, _t47, ((1.0) - (_t36) * (_t45)), ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0)))))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_2_fma(double dirZ, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79) {
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
                }
            }
        }
        return this;
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_2_mulAdd(double dirZ, double[] dd, double _t0, double _t15, double _t16, double _t17, double _t19, double _t21, double _t36, double _t45, double _t46, double _t47, double _t49, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp0, double _t79) {
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            dd[0] = _sp0 * _t69;
            dd[1] = _sp0 * _t55;
            dd[2] = _sp0 * _t74;
            dd[3] = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                dd[0] = 0.5 * java.lang.Math.sqrt(_t79);
                dd[1] = _sp3 * _t73;
                dd[2] = _sp3 * _t50;
                dd[3] = _sp3 * _t69;
            } else {
                if (_t62 > _t16) {
                    dd[0] = _sp1 * _t73;
                    dd[1] = 0.5 * java.lang.Math.sqrt(_t81);
                    dd[2] = _sp1 * _t68;
                    dd[3] = _sp1 * _t55;
                } else {
                    dd[0] = _sp2 * _t50;
                    dd[1] = _sp2 * _t68;
                    dd[2] = 0.5 * java.lang.Math.sqrt(_t80);
                    dd[3] = _sp2 * _t74;
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
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_fma(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return makeRotationLookAlong_lh_degenerate_sbd5e19ee_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t26, _t23));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
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
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        return makeRotationLookAlong_lh_degenerate_sbd5e19ee_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t44, ((_t44) * (_t25) + (_t22)), ((_t44) * (_t26) + (_t23)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_sbd5e19ee_1_fma(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45, double _t46) {
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_sbd5e19ee_1_mulAdd(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45, double _t46) {
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t26) - (_t46 * _t25));
        double _t57 = ((_t47) * (_t25) - (_t45 * _t24));
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t46, _t47, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t46, _t47, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t46, double _t47, double _t56, double _t57) {
        double _t58 = java.lang.Math.fma(_t46, _t24, _t47 * -_t26);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s76e68778_2_fma(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t46, double _t47, double _t56, double _t57) {
        double _t58 = ((_t46) * (_t24) - (_t47 * _t26));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_lh_degenerate_s76e68778_2_mulAdd(dd, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_2_fma(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_fma(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_2_mulAdd(double[] dd, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s5ae7f2cd_3_mulAdd(dd, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t8 = java.lang.Math.fma(dirData[2], dirData[2], java.lang.Math.fma(dirData[0], dirData[0], dirData[1] * dirData[1]));
        return makeRotationLookAlong_rh_sac012a5_1_fma(dir, up, epsilon, this.data, dirData, upData, -dirData[2], _t8, 1.0 / _t8, java.lang.Math.fma(dirData[2], upData[2], java.lang.Math.fma(dirData[0], upData[0], dirData[1] * upData[1])));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t8 = ((dirData[2]) * (dirData[2]) + (((dirData[0]) * (dirData[0]) + (dirData[1] * dirData[1]))));
        return makeRotationLookAlong_rh_sac012a5_1_mulAdd(dir, up, epsilon, this.data, dirData, upData, -dirData[2], _t8, 1.0 / _t8, ((dirData[2]) * (upData[2]) + (((dirData[0]) * (upData[0]) + (dirData[1] * upData[1])))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sac012a5_1_fma(Double3R dir, Double3R up, double epsilon, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t8_inv, double _t9) {
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dir, up, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirData[0] * _t16;
        double _t18 = dirData[2] * _t16;
        double _t29 = java.lang.Math.fma(dirData[2] * -_t9, _t8_inv, upData[2]);
        double _t30 = java.lang.Math.fma(dirData[1] * -_t9, _t8_inv, upData[1]);
        double _t31 = java.lang.Math.fma(dirData[0] * -_t9, _t8_inv, upData[0]);
        double _t38 = java.lang.Math.fma(dirData[1], _t29, -(dirData[2] * _t30));
        double _t39 = java.lang.Math.fma(dirData[0], _t30, -(dirData[1] * _t31));
        double _t40 = java.lang.Math.fma(dirData[2], _t31, -(dirData[0] * _t29));
        return makeRotationLookAlong_rh_sac012a5_2_fma(dir, up, epsilon, dd, dirData, upData, _t0, _t8, _t16, _t17, _t18, dirData[1] * _t16, -_t18, -_t17, java.lang.Math.fma(dirData[2], _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sac012a5_1_mulAdd(Double3R dir, Double3R up, double epsilon, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t8_inv, double _t9) {
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dir, up, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirData[0] * _t16;
        double _t18 = dirData[2] * _t16;
        double _t29 = ((dirData[2] * -_t9) * (_t8_inv) + (upData[2]));
        double _t30 = ((dirData[1] * -_t9) * (_t8_inv) + (upData[1]));
        double _t31 = ((dirData[0] * -_t9) * (_t8_inv) + (upData[0]));
        double _t38 = ((dirData[1]) * (_t29) - (dirData[2] * _t30));
        double _t39 = ((dirData[0]) * (_t30) - (dirData[1] * _t31));
        double _t40 = ((dirData[2]) * (_t31) - (dirData[0] * _t29));
        return makeRotationLookAlong_rh_sac012a5_2_mulAdd(dir, up, epsilon, dd, dirData, upData, _t0, _t8, _t16, _t17, _t18, dirData[1] * _t16, -_t18, -_t17, ((dirData[2]) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sac012a5_2_fma(Double3R dir, Double3R up, double epsilon, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upData[2], upData[2], java.lang.Math.fma(upData[0], upData[0], upData[1] * upData[1])), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dir, up, epsilon);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        return makeRotationLookAlong_rh_saaa58f9e_3_fma(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirData[0], _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirData[1], _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sac012a5_2_mulAdd(Double3R dir, Double3R up, double epsilon, double[] dd, double[] dirData, double[] upData, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (epsilon * ((upData[2]) * (upData[2]) + (((upData[0]) * (upData[0]) + (upData[1] * upData[1]))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dir, up, epsilon);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        return makeRotationLookAlong_rh_saaa58f9e_3_mulAdd(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, ((dirData[0]) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirData[1]) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23)))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23)))))));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double[] dd = this.data;
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t8_inv = 1.0 / _t8;
        double _t9 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = java.lang.Math.fma(dirZ * -_t9, _t8_inv, upZ);
        double _t30 = java.lang.Math.fma(dirY * -_t9, _t8_inv, upY);
        double _t31 = java.lang.Math.fma(dirX * -_t9, _t8_inv, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -(dirX * _t29));
        return makeRotationLookAlong_rh_sacb5e2cf_1_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), _t38, _t39, _t40, -_t38, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double[] dd = this.data;
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t8_inv = 1.0 / _t8;
        double _t9 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        double _t29 = ((dirZ * -_t9) * (_t8_inv) + (upZ));
        double _t30 = ((dirY * -_t9) * (_t8_inv) + (upY));
        double _t31 = ((dirX * -_t9) * (_t8_inv) + (upX));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        return makeRotationLookAlong_rh_sacb5e2cf_1_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dd, -dirZ, _t8, _t16, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), _t38, _t39, _t40, -_t38, ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sacb5e2cf_1_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        return makeRotationLookAlong_rh_sf0b2d43c_2_fma(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_sacb5e2cf_1_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, double[] dd, double _t0, double _t8, double _t16, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t38, double _t39, double _t40, double _t41, double _ct0) {
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(dd, _t0, _t16, _t18, _t21, _t22, _t38, _t41, _t47, _t48, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_fma(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        return makeRotationLookAlong_rh_degenerate_sfdcd6078_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t44, java.lang.Math.fma(_t44, _t26, _t23));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(Double3R dir, Double3R up, double epsilon) {
        double[] dirData = ((Double3Impl) dir).data;
        double[] upData = ((Double3Impl) up).data;
        double _t0 = unitScale(dirData[0], dirData[1], dirData[2]);
        double _t1 = unitScale(upData[0], upData[1], upData[2]);
        double _t8 = dirData[2] * _t0;
        double _t9 = dirData[0] * _t0;
        double _t10 = dirData[1] * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upData[2] * _t1;
            _t22 = upData[0] * _t1;
            _t23 = upData[1] * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        return makeRotationLookAlong_rh_degenerate_sfdcd6078_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t44, ((_t44) * (_t26) + (_t23)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sfdcd6078_1_fma(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        double _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_2_fma(dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, _t73, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sfdcd6078_1_mulAdd(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t44, double _t45) {
        double _t46 = ((_t44) * (_t25) + (_t22));
        double _t47 = ((_t44) * (_t24) + (_t21));
        double _t56 = ((_t45) * (_t25) - (_t46 * _t26));
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s5c062b5b_2_mulAdd(dd, _t24, _t25, _t26, _t29, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, _t73, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
            _t24 = -1.0;
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
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(epsilon, this.data, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(double epsilon, double[] dd, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(dd, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along {@code dir} (the rotation
     * of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up, epsilon) : makeRotationLookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Set this quaternion to a "look along" view rotation looking along {@code dir} (the rotation
     * of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return this
     */
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up, double epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Set this quaternion to a "look along" view rotation looking along ({@code dirX},
     * {@code dirY}, {@code dirZ}) (the rotation of a "look at" view transformation with the eye at
     * the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return this
     */
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Set this quaternion to the rotation that rotates {@code fromDir} onto {@code toDir} (for
     * opposite vectors an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return this
     */
    @Mutated public DoubleQuat makeRotationTo(Double3R fromDir, Double3R toDir) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDir, toDir);
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = java.lang.Math.fma(fromDirData[1], toDirData[2], -(fromDirData[2] * toDirData[1]));
        double _t16 = java.lang.Math.fma(fromDirData[0], toDirData[1], -fromDirData[1] * toDirData[0]);
        double _t17 = java.lang.Math.fma(fromDirData[2], toDirData[0], -fromDirData[0] * toDirData[2]);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_s7e149d43_1(this.data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(Double3R fromDir, Double3R toDir) {
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = ((fromDirData[1]) * (toDirData[2]) - (fromDirData[2] * toDirData[1]));
        double _t16 = ((fromDirData[0]) * (toDirData[1]) - (fromDirData[1] * toDirData[0]));
        double _t17 = ((fromDirData[2]) * (toDirData[0]) - (fromDirData[0] * toDirData[2]));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_s7e149d43_1(this.data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationTo_s7e149d43_1(double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
        }
        return this;
    }

    /**
     * Set this quaternion to the rotation that rotates ({@code fromDirX}, {@code fromDirY},
     * {@code fromDirZ}) onto ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors
     * an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
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
    @Mutated public DoubleQuat makeRotationTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (org.joml2.internal.FmaSwitch.ON) return makeRotationTo_fma(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_fma(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double[] dd = this.data;
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t24 = 0.5 * _t23;
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 1.0E-13) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
        }
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double[] dd = this.data;
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t24 = 0.5 * _t23;
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17))))))));
        if (_t24 > 1.0E-13) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
        }
        return this;
    }

    /**
     * Set this quaternion to the rotation that rotates {@code fromDir} onto {@code toDir} (for
     * opposite vectors an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length;
     * {@code epsilon} must not be negative.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return this
     */
    @Mutated public DoubleQuat makeRotationTo(Double3R fromDir, Double3R toDir, double epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDir, toDir, epsilon);
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = java.lang.Math.fma(fromDirData[1], toDirData[2], -(fromDirData[2] * toDirData[1]));
        double _t16 = java.lang.Math.fma(fromDirData[0], toDirData[1], -fromDirData[1] * toDirData[0]);
        double _t17 = java.lang.Math.fma(fromDirData[2], toDirData[0], -fromDirData[0] * toDirData[2]);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_s92cd7010_1(epsilon, this.data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(Double3R fromDir, Double3R toDir, double epsilon) {
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = ((fromDirData[1]) * (toDirData[2]) - (fromDirData[2] * toDirData[1]));
        double _t16 = ((fromDirData[0]) * (toDirData[1]) - (fromDirData[1] * toDirData[0]));
        double _t17 = ((fromDirData[2]) * (toDirData[0]) - (fromDirData[0] * toDirData[2]));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_s92cd7010_1(epsilon, this.data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationTo_s92cd7010_1(double epsilon, double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
        }
        return this;
    }

    /**
     * Set this quaternion to the rotation that rotates ({@code fromDirX}, {@code fromDirY},
     * {@code fromDirZ}) onto ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors
     * an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length; {@code epsilon} must not be negative.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return this
     */
    @Mutated public DoubleQuat makeRotationTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) return makeRotationTo_fma(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_fma(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double[] dd = this.data;
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t24 = 0.5 * _t23;
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > epsilon) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
        }
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double[] dd = this.data;
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t24 = 0.5 * _t23;
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17))))))));
        if (_t24 > epsilon) {
            dd[0] = _t15 * _t32;
            dd[1] = _t17 * _t32;
            dd[2] = _t16 * _t32;
            dd[3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0) {
                dd[0] = _t30 * _t13;
                dd[1] = _t30 * _t19;
                dd[2] = _t30 * _t18;
                dd[3] = 0.0;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
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
    @Mutated public DoubleQuat makeRotationX(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        dd[0] = Math.sinWithCos(_t0);
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
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
    @Mutated public DoubleQuat makeRotationXYZ(double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        dd[2] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[3] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[2] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[3] = ((_t12) * (_t5) - (_t11 * _t8));
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
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
    @Mutated public DoubleQuat makeRotationXZY(double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[1] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[2] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[3] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[2] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[3] = ((_t12) * (_t8) + (_t11 * _t5));
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
    @Mutated public DoubleQuat makeRotationY(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        dd[0] = 0.0;
        dd[1] = Math.sinWithCos(_t0);
        dd[2] = 0.0;
        dd[3] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
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
    @Mutated public DoubleQuat makeRotationYXZ(double angleY, double angleX, double angleZ) {
        if (COL_NARROW) return makeRotationYXZ_narrow(angleY, angleX, angleZ);
        if (SimdMath.USE_FMA) return makeRotationYXZ_fma(angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
    }

    private DoubleQuat makeRotationYXZ_fma(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        DoubleVector.zero(COL_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).fma(DoubleVector.broadcast(COL_SPECIES, _t5), DoubleVector.broadcast(COL_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(COL_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dd, 0);
        return this;
    }

    private DoubleQuat makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        DoubleVector.zero(COL_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).mul(DoubleVector.broadcast(COL_SPECIES, _t5)).add(DoubleVector.broadcast(COL_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(COL_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dd, 0);
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
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
    @Mutated public DoubleQuat makeRotationYZX(double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[2] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[3] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[2] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[3] = ((_t10) * (_t5) - (_t9 * _t8));
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
    @Mutated public DoubleQuat makeRotationZ(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = Math.sinWithCos(_t0);
        dd[3] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
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
    @Mutated public DoubleQuat makeRotationZXY(double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        dd[1] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[2] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[3] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[2] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[3] = ((_t12) * (_t5) - (_t11 * _t8));
        return this;
    }

    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
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
    @Mutated public DoubleQuat makeRotationZYX(double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dd[0] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[1] = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        dd[2] = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[3] = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dd[0] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[1] = ((_t11) * (_t8) + (_t12 * _t5));
        dd[2] = ((_t12) * (_t8) - (_t11 * _t5));
        dd[3] = ((_t10) * (_t5) + (_t9 * _t8));
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
    public DoubleQuat preRotateX(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return preRotateX_narrow(angle, dest);
        if (SimdMath.USE_FMA) return preRotateX_fma(angle, dest);
        return preRotateX_mulAdd(angle, dest);
    }

    private DoubleQuat preRotateX_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(0, sd[3]).withLane(2, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[2]).withLane(3, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_3)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat preRotateX_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[3]).withLane(2, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[2]).withLane(3, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_3)).intoArray(dd, 0);
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
    public DoubleQuat preRotateY(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return preRotateY_narrow(angle, dest);
        if (SimdMath.USE_FMA) return preRotateY_fma(angle, dest);
        return preRotateY_mulAdd(angle, dest);
    }

    private DoubleQuat preRotateY_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(0, sd[2]).withLane(1, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(2, sd[0]).withLane(3, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_4)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat preRotateY_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[2]).withLane(1, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(2, sd[0]).withLane(3, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_4)).intoArray(dd, 0);
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
    public DoubleQuat preRotateZ(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return preRotateZ_narrow(angle, dest);
        if (SimdMath.USE_FMA) return preRotateZ_fma(angle, dest);
        return preRotateZ_mulAdd(angle, dest);
    }

    private DoubleQuat preRotateZ_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(1, sd[0]).withLane(2, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[1]).withLane(3, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_5)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat preRotateZ_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[0]).withLane(2, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[1]).withLane(3, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_5)).intoArray(dd, 0);
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
    public DoubleQuat rotateAxis(double angle, Double3R axis, @Mutated DoubleQuat dest) {
        double[] axisData = ((Double3Impl) axis).data;
        if (axisData[1] == 0 && axisData[2] == 0 && java.lang.Math.abs(axisData[0]) == 1) return rotateX(axisData[0] * angle, dest);
        if (axisData[0] == 0 && axisData[2] == 0 && java.lang.Math.abs(axisData[1]) == 1) return rotateY(axisData[1] * angle, dest);
        if (axisData[0] == 0 && axisData[1] == 0 && java.lang.Math.abs(axisData[2]) == 1) return rotateZ(axisData[2] * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axis, dest);
        return rotateAxis_mulAdd(angle, axis, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_fma(double angle, Double3R axis, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] axisData = ((Double3Impl) axis).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisData[0] * _t2;
        double _t4 = axisData[2] * _t2;
        double _t5 = axisData[1] * _t2;
        return rotateAxis_s81238614_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t1, _t3, _t4, _t5, java.lang.Math.fma(sd[0], _t1, sd[3] * _t3) + java.lang.Math.fma(sd[1], _t4, -(sd[2] * _t5)), java.lang.Math.fma(sd[1], _t1, sd[2] * _t3) + java.lang.Math.fma(sd[3], _t5, -sd[0] * _t4));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_mulAdd(double angle, Double3R axis, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] axisData = ((Double3Impl) axis).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisData[0] * _t2;
        double _t4 = axisData[2] * _t2;
        double _t5 = axisData[1] * _t2;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t1) + (sd[3] * _t3)) + ((sd[1]) * (_t4) - (sd[2] * _t5));
        double _buf1 = ((sd[1]) * (_t1) + (sd[2] * _t3)) + ((sd[3]) * (_t5) - (sd[0] * _t4));
        double _buf2 = ((sd[0]) * (_t5) + (sd[3] * _t4)) + ((sd[2]) * (_t1) - (sd[1] * _t3));
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t3)) - ((sd[1]) * (_t5) + (sd[2] * _t4));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateAxis_s81238614_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t1, double _t3, double _t4, double _t5, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[0], _t5, sd[3] * _t4) + java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t3));
        dd[3] = java.lang.Math.fma(sd[3], _t1, -sd[0] * _t3) - java.lang.Math.fma(sd[1], _t5, sd[2] * _t4);
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
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        double _buf0 = java.lang.Math.fma(sd[0], _t1, sd[3] * _t3) + java.lang.Math.fma(sd[1], _t4, -(sd[2] * _t5));
        double _buf1 = java.lang.Math.fma(sd[1], _t1, sd[2] * _t3) + java.lang.Math.fma(sd[3], _t5, -sd[0] * _t4);
        double _buf2 = java.lang.Math.fma(sd[0], _t5, sd[3] * _t4) + java.lang.Math.fma(sd[2], _t1, -(sd[1] * _t3));
        dd[3] = java.lang.Math.fma(sd[3], _t1, -sd[0] * _t3) - java.lang.Math.fma(sd[1], _t5, sd[2] * _t4);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        double _buf0 = ((sd[0]) * (_t1) + (sd[3] * _t3)) + ((sd[1]) * (_t4) - (sd[2] * _t5));
        double _buf1 = ((sd[1]) * (_t1) + (sd[2] * _t3)) + ((sd[3]) * (_t5) - (sd[0] * _t4));
        double _buf2 = ((sd[0]) * (_t5) + (sd[3] * _t4)) + ((sd[2]) * (_t1) - (sd[1] * _t3));
        dd[3] = ((sd[3]) * (_t1) - (sd[0] * _t3)) - ((sd[1]) * (_t5) + (sd[2] * _t4));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(Double3R fromDir, Double3R toDir, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateTo_mulAdd(fromDir, toDir, dest);
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = java.lang.Math.fma(fromDirData[0], toDirData[1], -fromDirData[1] * toDirData[0]);
        double _t16 = java.lang.Math.fma(fromDirData[1], toDirData[2], -(fromDirData[2] * toDirData[1]));
        double _t17 = java.lang.Math.fma(fromDirData[2], toDirData[0], -fromDirData[0] * toDirData[2]);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_sddf19302_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(Double3R fromDir, Double3R toDir, @Mutated DoubleQuat dest) {
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = ((fromDirData[0]) * (toDirData[1]) - (fromDirData[1] * toDirData[0]));
        double _t16 = ((fromDirData[1]) * (toDirData[2]) - (fromDirData[2] * toDirData[1]));
        double _t17 = ((fromDirData[2]) * (toDirData[0]) - (fromDirData[0] * toDirData[2]));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_sddf19302_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTo_sddf19302_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        double _buf0 = java.lang.Math.fma(sd[0], _t44, sd[3] * _t45) + java.lang.Math.fma(sd[1], _t46, -(sd[2] * _t47));
        double _buf1 = java.lang.Math.fma(sd[1], _t44, sd[2] * _t45) + java.lang.Math.fma(sd[3], _t47, -sd[0] * _t46);
        double _buf2 = java.lang.Math.fma(sd[0], _t47, sd[3] * _t46) + java.lang.Math.fma(sd[2], _t44, -(sd[1] * _t45));
        dd[3] = java.lang.Math.fma(sd[3], _t44, -sd[0] * _t45) - java.lang.Math.fma(sd[1], _t47, sd[2] * _t46);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTo_sddf19302_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        double _buf0 = ((sd[0]) * (_t44) + (sd[3] * _t45)) + ((sd[1]) * (_t46) - (sd[2] * _t47));
        double _buf1 = ((sd[1]) * (_t44) + (sd[2] * _t45)) + ((sd[3]) * (_t47) - (sd[0] * _t46));
        double _buf2 = ((sd[0]) * (_t47) + (sd[3] * _t46)) + ((sd[2]) * (_t44) - (sd[1] * _t45));
        dd[3] = ((sd[3]) * (_t44) - (sd[0] * _t45)) - ((sd[1]) * (_t47) + (sd[2] * _t46));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
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
    public DoubleQuat rotateTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return rotateTo_fma(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
    }

    /** {@code rotateTo} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_fma(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, @Mutated DoubleQuat dest) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t24 = 0.5 * _t23;
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return rotateTo_s5e3358c8_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t44, _t45, _t46, _t47);
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, @Mutated DoubleQuat dest) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t24 = 0.5 * _t23;
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return rotateTo_s5e3358c8_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t44, _t45, _t46, _t47);
    }

    /**
     * Piece 2 of {@code rotateTo}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code rotateTo}; reached only through it.
     */
    private DoubleQuat rotateTo_s5e3358c8_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t44, double _t45, double _t46, double _t47) {
        double _buf0 = java.lang.Math.fma(sd[0], _t44, sd[3] * _t45) + java.lang.Math.fma(sd[1], _t46, -(sd[2] * _t47));
        double _buf1 = java.lang.Math.fma(sd[1], _t44, sd[2] * _t45) + java.lang.Math.fma(sd[3], _t47, -sd[0] * _t46);
        double _buf2 = java.lang.Math.fma(sd[0], _t47, sd[3] * _t46) + java.lang.Math.fma(sd[2], _t44, -(sd[1] * _t45));
        dd[3] = java.lang.Math.fma(sd[3], _t44, -sd[0] * _t45) - java.lang.Math.fma(sd[1], _t47, sd[2] * _t46);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Piece 2 of {@code rotateTo}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code rotateTo}; reached only through it.
     */
    private DoubleQuat rotateTo_s5e3358c8_1_mulAdd(DoubleQuat dest, double[] sd, double[] dd, double _t44, double _t45, double _t46, double _t47) {
        double _buf0 = ((sd[0]) * (_t44) + (sd[3] * _t45)) + ((sd[1]) * (_t46) - (sd[2] * _t47));
        double _buf1 = ((sd[1]) * (_t44) + (sd[2] * _t45)) + ((sd[3]) * (_t47) - (sd[0] * _t46));
        double _buf2 = ((sd[0]) * (_t47) + (sd[3] * _t46)) + ((sd[2]) * (_t44) - (sd[1] * _t45));
        dd[3] = ((sd[3]) * (_t44) - (sd[0] * _t45)) - ((sd[1]) * (_t47) + (sd[2] * _t46));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length;
     * {@code epsilon} must not be negative.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(Double3R fromDir, Double3R toDir, double epsilon, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return rotateTo_fma(fromDir, toDir, epsilon, dest);
        return rotateTo_mulAdd(fromDir, toDir, epsilon, dest);
    }

    /** {@code rotateTo} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_fma(Double3R fromDir, Double3R toDir, double epsilon, @Mutated DoubleQuat dest) {
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = java.lang.Math.fma(fromDirData[0], toDirData[1], -fromDirData[1] * toDirData[0]);
        double _t16 = java.lang.Math.fma(fromDirData[1], toDirData[2], -(fromDirData[2] * toDirData[1]));
        double _t17 = java.lang.Math.fma(fromDirData[2], toDirData[0], -fromDirData[0] * toDirData[2]);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_sac8cd0f1_1_fma(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(Double3R fromDir, Double3R toDir, double epsilon, @Mutated DoubleQuat dest) {
        double[] fromDirData = ((Double3Impl) fromDir).data;
        double[] toDirData = ((Double3Impl) toDir).data;
        double _t4 = fromDirData[2] + toDirData[2];
        double _t5 = fromDirData[0] + toDirData[0];
        double _t6 = fromDirData[1] + toDirData[1];
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirData[2]) < java.lang.Math.abs(fromDirData[0])) {
            _t13 = fromDirData[1];
            _t18 = 0.0;
            _t19 = -fromDirData[0];
        } else {
            _t13 = 0.0;
            _t18 = -fromDirData[1];
            _t19 = fromDirData[2];
        }
        double _t15 = ((fromDirData[0]) * (toDirData[1]) - (fromDirData[1] * toDirData[0]));
        double _t16 = ((fromDirData[1]) * (toDirData[2]) - (fromDirData[2] * toDirData[1]));
        double _t17 = ((fromDirData[2]) * (toDirData[0]) - (fromDirData[0] * toDirData[2]));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_sac8cd0f1_1_mulAdd(epsilon, dest, this.data, ((DoubleQuatImpl) dest).data, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTo_sac8cd0f1_1_fma(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        double _buf0 = java.lang.Math.fma(sd[0], _t44, sd[3] * _t45) + java.lang.Math.fma(sd[1], _t46, -(sd[2] * _t47));
        double _buf1 = java.lang.Math.fma(sd[1], _t44, sd[2] * _t45) + java.lang.Math.fma(sd[3], _t47, -sd[0] * _t46);
        double _buf2 = java.lang.Math.fma(sd[0], _t47, sd[3] * _t46) + java.lang.Math.fma(sd[2], _t44, -(sd[1] * _t45));
        dd[3] = java.lang.Math.fma(sd[3], _t44, -sd[0] * _t45) - java.lang.Math.fma(sd[1], _t47, sd[2] * _t46);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTo_sac8cd0f1_1_mulAdd(double epsilon, DoubleQuat dest, double[] sd, double[] dd, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        double _buf0 = ((sd[0]) * (_t44) + (sd[3] * _t45)) + ((sd[1]) * (_t46) - (sd[2] * _t47));
        double _buf1 = ((sd[1]) * (_t44) + (sd[2] * _t45)) + ((sd[3]) * (_t47) - (sd[0] * _t46));
        double _buf2 = ((sd[0]) * (_t47) + (sd[3] * _t46)) + ((sd[2]) * (_t44) - (sd[1] * _t45));
        dd[3] = ((sd[3]) * (_t44) - (sd[0] * _t45)) - ((sd[1]) * (_t47) + (sd[2] * _t46));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length; {@code epsilon} must not be negative.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return rotateTo_fma(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
    }

    /** {@code rotateTo} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_fma(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t24 = 0.5 * _t23;
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return rotateTo_s5e3358c8_1_fma(dest, this.data, ((DoubleQuatImpl) dest).data, _t44, _t45, _t46, _t47);
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t24 = 0.5 * _t23;
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return rotateTo_s5e3358c8_1_mulAdd(dest, this.data, ((DoubleQuatImpl) dest).data, _t44, _t45, _t46, _t47);
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
    public DoubleQuat rotateX(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return rotateX_narrow(angle, dest);
        if (SimdMath.USE_FMA) return rotateX_fma(angle, dest);
        return rotateX_mulAdd(angle, dest);
    }

    private DoubleQuat rotateX_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(0, sd[3]).withLane(1, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(2, sd[1]).withLane(3, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_4)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat rotateX_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[3]).withLane(1, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(2, sd[1]).withLane(3, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_4)).intoArray(dd, 0);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateXYZ(double angleX, double angleY, double angleZ, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return rotateXYZ_s36c5d624_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20), java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXYZ_mulAdd(double angleX, double angleY, double angleZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        double _buf1 = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        double _buf2 = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateXYZ_s36c5d624_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateXZY(double angleX, double angleZ, double angleY, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        return rotateXZY_s851d2078_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t20, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t20), java.lang.Math.fma(sd[0], _t22, sd[3] * _t20) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXZY_mulAdd(double angleX, double angleZ, double angleY, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        double _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t20) - (sd[2] * _t22));
        double _buf1 = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t22) - (sd[0] * _t20));
        double _buf2 = ((sd[0]) * (_t22) + (sd[3] * _t20)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t22) + (sd[2] * _t20));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateXZY_s851d2078_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t20);
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
    public DoubleQuat rotateY(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return rotateY_narrow(angle, dest);
        if (SimdMath.USE_FMA) return rotateY_fma(angle, dest);
        return rotateY_mulAdd(angle, dest);
    }

    private DoubleQuat rotateY_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(1, sd[3]).withLane(2, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[2]).withLane(3, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_5)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat rotateY_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[3]).withLane(2, sd[0]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[2]).withLane(3, sd[1]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_5)).intoArray(dd, 0);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateYXZ(double angleY, double angleX, double angleZ, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        return rotateYXZ_s906d3080_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t20) + java.lang.Math.fma(sd[1], _t21, -(sd[2] * _t22)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t20) + java.lang.Math.fma(sd[3], _t22, -sd[0] * _t21), java.lang.Math.fma(sd[0], _t22, sd[3] * _t21) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t20)));
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYXZ_mulAdd(double angleY, double angleX, double angleZ, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t19) + (sd[3] * _t20)) + ((sd[1]) * (_t21) - (sd[2] * _t22));
        double _buf1 = ((sd[1]) * (_t19) + (sd[2] * _t20)) + ((sd[3]) * (_t22) - (sd[0] * _t21));
        double _buf2 = ((sd[0]) * (_t22) + (sd[3] * _t21)) + ((sd[2]) * (_t19) - (sd[1] * _t20));
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t20)) - ((sd[1]) * (_t22) + (sd[2] * _t21));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateYXZ_s906d3080_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t20) - java.lang.Math.fma(sd[1], _t22, sd[2] * _t21);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateYZX(double angleY, double angleZ, double angleX, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return rotateYZX_s99c0e7b0_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t19) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t19) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22), java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t19)));
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYZX_mulAdd(double angleY, double angleZ, double angleX, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t21) + (sd[3] * _t19)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        double _buf1 = ((sd[1]) * (_t21) + (sd[2] * _t19)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        double _buf2 = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t21) - (sd[1] * _t19));
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t19)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateYZX_s99c0e7b0_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t19) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
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
    public DoubleQuat rotateZ(double angle, @Mutated DoubleQuat dest) {
        if (COL_NARROW) return rotateZ_narrow(angle, dest);
        if (SimdMath.USE_FMA) return rotateZ_fma(angle, dest);
        return rotateZ_mulAdd(angle, dest);
    }

    private DoubleQuat rotateZ_fma(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0)), DoubleVector.zero(_COL_SPECIES).withLane(0, sd[1]).withLane(2, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[0]).withLane(3, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_3)).intoArray(dd, 0);
        return dest;
    }

    private DoubleQuat rotateZ_mulAdd(double angle, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t2 = Math.sinWithCos(_t0);
        VectorSpecies<Double> _COL_SPECIES = COL_SPECIES;
        DoubleVector.fromArray(_COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(_COL_SPECIES, Math.cosWithSin(_t0))).add(DoubleVector.zero(_COL_SPECIES).withLane(0, sd[1]).withLane(2, sd[3]).mul(DoubleVector.broadcast(_COL_SPECIES, _t2)).blend(DoubleVector.zero(_COL_SPECIES).withLane(1, sd[0]).withLane(3, sd[2]).mul(DoubleVector.broadcast(_COL_SPECIES, -_t2)), MASK_3)).intoArray(dd, 0);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateZXY(double angleZ, double angleX, double angleY, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return rotateZXY_s4277c910_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t21, sd[3] * _t22) + java.lang.Math.fma(sd[1], _t19, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t21, sd[2] * _t22) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t19), java.lang.Math.fma(sd[0], _t20, sd[3] * _t19) + java.lang.Math.fma(sd[2], _t21, -(sd[1] * _t22)));
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZXY_mulAdd(double angleZ, double angleX, double angleY, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t21) + (sd[3] * _t22)) + ((sd[1]) * (_t19) - (sd[2] * _t20));
        double _buf1 = ((sd[1]) * (_t21) + (sd[2] * _t22)) + ((sd[3]) * (_t20) - (sd[0] * _t19));
        double _buf2 = ((sd[0]) * (_t20) + (sd[3] * _t19)) + ((sd[2]) * (_t21) - (sd[1] * _t22));
        dd[3] = ((sd[3]) * (_t21) - (sd[0] * _t22)) - ((sd[1]) * (_t20) + (sd[2] * _t19));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateZXY_s4277c910_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t21, -sd[0] * _t22) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t19);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this quaternion and store the result in {@code dest}.
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
    public DoubleQuat rotateZYX(double angleZ, double angleY, double angleX, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        return rotateZYX_sbc89e804_1_fma(dest, sd, ((DoubleQuatImpl) dest).data, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[0], _t19, sd[3] * _t21) + java.lang.Math.fma(sd[1], _t22, -(sd[2] * _t20)), java.lang.Math.fma(sd[1], _t19, sd[2] * _t21) + java.lang.Math.fma(sd[3], _t20, -sd[0] * _t22), java.lang.Math.fma(sd[0], _t20, sd[3] * _t22) + java.lang.Math.fma(sd[2], _t19, -(sd[1] * _t21)));
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZYX_mulAdd(double angleZ, double angleY, double angleX, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _buf0 = ((sd[0]) * (_t19) + (sd[3] * _t21)) + ((sd[1]) * (_t22) - (sd[2] * _t20));
        double _buf1 = ((sd[1]) * (_t19) + (sd[2] * _t21)) + ((sd[3]) * (_t20) - (sd[0] * _t22));
        double _buf2 = ((sd[0]) * (_t20) + (sd[3] * _t22)) + ((sd[2]) * (_t19) - (sd[1] * _t21));
        dd[3] = ((sd[3]) * (_t19) - (sd[0] * _t21)) - ((sd[1]) * (_t20) + (sd[2] * _t22));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateZYX_sbc89e804_1_fma(DoubleQuat dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1, double _buf2) {
        dd[3] = java.lang.Math.fma(sd[3], _t19, -sd[0] * _t21) - java.lang.Math.fma(sd[1], _t20, sd[2] * _t22);
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
    public Double3 transform(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
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
    public Double3 transform(double vX, double vY, double vZ, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], vY, -(sd[1] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[2], vX, -(sd[0] * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(sd[1], vZ, -(sd[2] * vY));
        dd[0] = java.lang.Math.fma(sd[1], _t9, java.lang.Math.fma(-sd[2], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[2], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[1], _t11, java.lang.Math.fma(sd[3], _t9, vZ)));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (vY) - (sd[1] * vX));
        double _t10 = 2.0 * ((sd[2]) * (vX) - (sd[0] * vZ));
        double _t11 = 2.0 * ((sd[1]) * (vZ) - (sd[2] * vY));
        dd[0] = ((sd[1]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[2]) * (_t10))));
        dd[1] = ((sd[2]) * (_t11) + (((((sd[3]) * (_t10) + (vY))) - (sd[0]) * (_t9))));
        dd[2] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vZ))) - (sd[1]) * (_t11))));
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
    public Double3 transformInverse(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
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
    public Double3 transformInverse(double vX, double vY, double vZ, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[0], vZ, -(sd[2] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[1], vX, -(sd[0] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[2], vY, -(sd[1] * vZ));
        dd[0] = java.lang.Math.fma(sd[2], _t9, java.lang.Math.fma(-sd[1], _t10, java.lang.Math.fma(sd[3], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(-sd[2], _t11, java.lang.Math.fma(sd[3], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[1], _t11, java.lang.Math.fma(-sd[0], _t9, java.lang.Math.fma(sd[3], _t10, vZ)));
        return dest;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[0]) * (vZ) - (sd[2] * vX));
        double _t10 = 2.0 * ((sd[1]) * (vX) - (sd[0] * vY));
        double _t11 = 2.0 * ((sd[2]) * (vY) - (sd[1] * vZ));
        dd[0] = ((sd[2]) * (_t9) + (((((sd[3]) * (_t11) + (vX))) - (sd[1]) * (_t10))));
        dd[1] = ((sd[0]) * (_t10) + (((((sd[3]) * (_t9) + (vY))) - (sd[2]) * (_t11))));
        dd[2] = ((sd[1]) * (_t11) + (((((sd[3]) * (_t10) + (vZ))) - (sd[0]) * (_t9))));
        return dest;
    }

    public double x() { return data[0]; }
    public double y() { return data[1]; }
    public double z() { return data[2]; }
    public double w() { return data[3]; }

    @Override public String toString() {
        return "DoubleQuat(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleQuatImpl)) return false;
        DoubleQuatImpl o = (DoubleQuatImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(DoubleQuatR other, double epsilon) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        if (COL_NARROW) return store_narrow(dest, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }

    @Mutated public DoubleQuat load(double[] src, int offset) {
        if (COL_NARROW) return load_narrow(src, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }

    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        if (COL_NARROW) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        double[] d = this.data;
        double[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        return buf;
    }

    @Mutated public DoubleQuat loadAbsolute(int index, DoubleBuffer buf) {
        if (COL_NARROW) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        double[] d = this.data;
        double[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
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

    @Mutated public DoubleQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public DoubleQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public DoubleQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    private boolean storeLanes(MemorySegment dest, long offset) {
        byte[] b = HeapSegments.laneBytes(dest, true);
        if (b == null) return false;
        int at = HeapSegments.index(dest, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).reinterpretAsBytes().intoArray(b, at);
        return true;
    }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        if (COL_NARROW) return StoreLoad.SEG_OPS.store(this, offset, dest);
        if (!dest.isNative() && storeLanes(dest, offset)) return dest;
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }

    private boolean loadLanes(MemorySegment src, long offset) {
        byte[] b = HeapSegments.laneBytes(src, false);
        if (b == null) return false;
        int at = HeapSegments.index(src, offset);
        double[] d = this.data;
        ByteVector.fromArray(ByteVector.SPECIES_256, b, at).reinterpretAsDoubles().intoArray(d, 0);
        return true;
    }

    @Mutated public DoubleQuat load(long offset, MemorySegment src) {
        if (COL_NARROW) return StoreLoad.SEG_OPS.load(this, offset, src);
        if (!src.isNative() && loadLanes(src, offset)) return this;
        double[] d = this.data;
        DoubleVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }

    @Mutated public DoubleQuat load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }

    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public DoubleQuat load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public DoubleQuat loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }

    @Mutated public DoubleQuat loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public DoubleQuat storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    @Mutated public DoubleQuat loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    public MemorySegment storeFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    @Mutated public DoubleQuat loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }

    @Mutated public DoubleQuat loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final VectorMask<Double> MASK_0 = VectorMask.fromValues(COL_SPECIES, true, true, true, false);
    private static final VectorMask<Double> MASK_3 = VectorMask.fromValues(COL_SPECIES, false, true, false, true);
    private static final VectorMask<Double> MASK_4 = VectorMask.fromValues(COL_SPECIES, false, false, true, true);
    private static final VectorMask<Double> MASK_5 = VectorMask.fromValues(COL_SPECIES, true, false, false, true);
    private static final DoubleVector VEC_1 = DoubleVector.fromArray(COL_SPECIES, new double[] {0.0, 0.0, 0.0, 0.0}, 0);
    private static final DoubleVector VEC_2 = DoubleVector.fromArray(COL_SPECIES, new double[] {0.0, 0.0, 0.0, 1.0}, 0);

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]: 2
     * asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an argument of
     * at most sqrt(2)/2 and the angle stays accurate at both ends. Shared by 2 identical private
     * paths of {@code quatArcAngle}; reached only through it.
     */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
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

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private DoubleQuat invert_narrow(@Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3_inv = 1.0 / java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            dd[0] = -(sd[0] * _t3_inv);
            dd[1] = -(sd[1] * _t3_inv);
            dd[2] = -(sd[2] * _t3_inv);
            dd[3] = sd[3] * _t3_inv;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3_inv = 1.0 / ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            dd[0] = -(sd[0] * _t3_inv);
            dd[1] = -(sd[1] * _t3_inv);
            dd[2] = -(sd[2] * _t3_inv);
            dd[3] = sd[3] * _t3_inv;
            return dest;
        }
    }

    private DoubleQuat add_narrow(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }

    private DoubleQuat mul_narrow(double scalar, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        return dest;
    }

    private DoubleQuat negate_narrow(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
    }

    private DoubleQuat sub_narrow(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }

    private DoubleQuat set_narrow(DoubleQuatR v) {
        double[] vData = ((DoubleQuatImpl) v).data;
        double vY = vData[1];
        double vZ = vData[2];
        double vW = vData[3];
        double[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
        return this;
    }

    private DoubleQuat makeFromDualQuat_narrow(DoubleDualQuatR dq) {
        double[] dqData = ((DoubleDualQuatImpl) dq).data;
        double dqRY = dqData[1];
        double dqRZ = dqData[2];
        double dqRW = dqData[3];
        double dqDX = dqData[4];
        double dqDY = dqData[5];
        double dqDZ = dqData[6];
        double dqDW = dqData[7];
        double[] dd = this.data;
        dd[0] = dqData[0];
        dd[1] = dqRY;
        dd[2] = dqRZ;
        dd[3] = dqRW;
        return this;
    }

    private DoubleDualQuat toDualQuat_narrow(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        return dest;
    }

    private Double4x4 toMatrix_narrow(@Mutated Double4x4 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = java.lang.Math.fma(-2.0, java.lang.Math.fma(_rd1, _rd1, _t0), 1.0);
        dd[1] = 2.0 * java.lang.Math.fma(_rd0, _rd1, _t1);
        dd[2] = 2.0 * java.lang.Math.fma(_rd0, _rd2, -_t2);
        dd[3] = 0.0;
        dd[4] = 2.0 * java.lang.Math.fma(_rd0, _rd1, -_t1);
        dd[5] = java.lang.Math.fma(-2.0, java.lang.Math.fma(_rd0, _rd0, _t0), 1.0);
        dd[6] = 2.0 * java.lang.Math.fma(_rd0, _rd3, _rd1 * _rd2);
        dd[7] = 0.0;
        dd[8] = 2.0 * java.lang.Math.fma(_rd0, _rd2, _t2);
        dd[9] = 2.0 * java.lang.Math.fma(_rd1, _rd2, -(_rd0 * _rd3));
        dd[10] = java.lang.Math.fma(-2.0, java.lang.Math.fma(_rd0, _rd0, _rd1 * _rd1), 1.0);
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    private DoubleQuat makeIdentity_narrow() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        return this;
    }

    private DoubleQuat makeZero_narrow() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        return this;
    }

    private DoubleQuat calculateW_narrow(@Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            dd[0] = _rd0;
            dd[1] = _rd1;
            dd[2] = _rd2;
            dd[3] = java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-_rd0, _rd0, java.lang.Math.fma(-_rd1, _rd1, java.lang.Math.fma(-_rd2, _rd2, 1.0)))));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            dd[0] = _rd0;
            dd[1] = _rd1;
            dd[2] = _rd2;
            dd[3] = java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (_rd2) * (_rd2))) - (_rd1) * (_rd1))) - (_rd0) * (_rd0))));
            return dest;
        }
    }

    private DoubleQuat conjugate_narrow(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = sd[3];
        return dest;
    }

    private DoubleQuat exp_narrow(@Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = Math.min(Math.exp(sd[3]), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
            if (_t4 > 0.0) {
                dd[0] = sd[0] * _t10;
                dd[1] = sd[1] * _t10;
                dd[2] = sd[2] * _t10;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            dd[3] = Math.cosWithSin(_t5) * _t2;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = Math.min(Math.exp(sd[3]), 1.7976931348623157E308);
            double _t4 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
            if (_t4 > 0.0) {
                dd[0] = sd[0] * _t10;
                dd[1] = sd[1] * _t10;
                dd[2] = sd[2] * _t10;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            dd[3] = Math.cosWithSin(_t5) * _t2;
            return dest;
        }
    }

    private DoubleQuat log_narrow(@Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                dd[0] = sd[0] * _t6;
                dd[1] = sd[1] * _t6;
                dd[2] = sd[2] * _t6;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            dd[3] = Math.log(java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], _t2)));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), sd[3]) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                dd[0] = sd[0] * _t6;
                dd[1] = sd[1] * _t6;
                dd[2] = sd[2] * _t6;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            dd[3] = Math.log(java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (_t2))));
            return dest;
        }
    }

    private DoubleQuat normalize_narrow(@Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        }
    }

    private double[] store_narrow(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    private DoubleQuat load_narrow(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }

    private DoubleQuat lerp_narrow(DoubleQuatR other, double t, @Mutated DoubleQuat dest) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }

    private DoubleQuat lerp_narrow(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }

    private DoubleQuat nlerp_narrow(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] targetData = ((DoubleQuatImpl) target).data;
        double targetX = targetData[0];
        double targetY = targetData[1];
        double targetZ = targetData[2];
        double targetW = targetData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, sd[3] * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, sd[2] * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, sd[0] * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, sd[1] * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dd[0] = _t7 * _t13;
            dd[1] = _t8 * _t13;
            dd[2] = _t6 * _t13;
            dd[3] = _t5 * _t13;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    private DoubleQuat nlerp_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, sd[3] * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, sd[2] * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, sd[0] * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, sd[1] * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dd[0] = _t7 * _t13;
            dd[1] = _t8 * _t13;
            dd[2] = _t6 * _t13;
            dd[3] = _t5 * _t13;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    private DoubleQuat nlerpShortest_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return nlerpShortest_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    private DoubleQuat slerp_narrow(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] targetData = ((DoubleQuatImpl) target).data;
        return slerp(targetData[0], targetData[1], targetData[2], targetData[3], alpha, dest);
    }

    private DoubleQuat slerp_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return slerp_mulAdd_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetW;
        double _t3 = sd[2] + targetZ;
        double _t4 = sd[0] + targetX;
        double _t5 = sd[1] + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t10, 1.0), _t22, Math.cosWithSin(_t14));
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dd[0] = java.lang.Math.fma(sd[0], _t25, targetX * _t26);
        dd[1] = java.lang.Math.fma(sd[1], _t25, targetY * _t26);
        dd[2] = java.lang.Math.fma(sd[2], _t25, targetZ * _t26);
        dd[3] = java.lang.Math.fma(sd[3], _t25, targetW * _t26);
        return dest;
    }

    private DoubleQuat slerpShortest_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_s5e9c190a_1_fma(alpha, dest, sd, ((DoubleQuatImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    private DoubleQuat addScaled_narrow(DoubleQuatR other, double weight, @Mutated DoubleQuat dest) {
        double[] otherData = ((DoubleQuatImpl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double otherW = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherX, sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherY, sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherZ, sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherW, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            dd[0] = ((weight) * (otherX) + (sd[0]));
            dd[1] = ((weight) * (otherY) + (sd[1]));
            dd[2] = ((weight) * (otherZ) + (sd[2]));
            dd[3] = ((weight) * (otherW) + (sd[3]));
            return dest;
        }
    }

    private DoubleQuat makeRotationAxis_narrow(double angle, Double3R axis) {
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosWithSin(_t0);
        return this;
    }

    private DoubleQuat makeRotationAxis_narrow(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = axisX * _t1;
        dd[1] = axisY * _t1;
        dd[2] = axisZ * _t1;
        dd[3] = Math.cosWithSin(_t0);
        return this;
    }

    private DoubleQuat makeRotationYXZ_narrow(double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYXZ_mulAdd_narrow(angleY, angleX, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[1] = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[2] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[3] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    private DoubleQuat preRotateX_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, _rd3 * _t2);
            dd[1] = java.lang.Math.fma(_rd1, _t1, -(_rd2 * _t2));
            dd[2] = java.lang.Math.fma(_rd1, _t2, _rd2 * _t1);
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd0 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) + (_rd3 * _t2));
            dd[1] = ((_rd1) * (_t1) - (_rd2 * _t2));
            dd[2] = ((_rd1) * (_t2) + (_rd2 * _t1));
            dd[3] = ((_rd3) * (_t1) - (_rd0 * _t2));
            return dest;
        }
    }

    private DoubleQuat preRotateY_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, _rd2 * _t2);
            dd[1] = java.lang.Math.fma(_rd1, _t1, _rd3 * _t2);
            dd[2] = java.lang.Math.fma(_rd2, _t1, -(_rd0 * _t2));
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd1 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) + (_rd2 * _t2));
            dd[1] = ((_rd1) * (_t1) + (_rd3 * _t2));
            dd[2] = ((_rd2) * (_t1) - (_rd0 * _t2));
            dd[3] = ((_rd3) * (_t1) - (_rd1 * _t2));
            return dest;
        }
    }

    private DoubleQuat preRotateZ_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, -(_rd1 * _t2));
            dd[1] = java.lang.Math.fma(_rd0, _t2, _rd1 * _t1);
            dd[2] = java.lang.Math.fma(_rd2, _t1, _rd3 * _t2);
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd2 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) - (_rd1 * _t2));
            dd[1] = ((_rd0) * (_t2) + (_rd1 * _t1));
            dd[2] = ((_rd2) * (_t1) + (_rd3 * _t2));
            dd[3] = ((_rd3) * (_t1) - (_rd2 * _t2));
            return dest;
        }
    }

    private DoubleQuat rotateX_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, _rd3 * _t2);
            dd[1] = java.lang.Math.fma(_rd1, _t1, _rd2 * _t2);
            dd[2] = java.lang.Math.fma(_rd2, _t1, -(_rd1 * _t2));
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd0 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) + (_rd3 * _t2));
            dd[1] = ((_rd1) * (_t1) + (_rd2 * _t2));
            dd[2] = ((_rd2) * (_t1) - (_rd1 * _t2));
            dd[3] = ((_rd3) * (_t1) - (_rd0 * _t2));
            return dest;
        }
    }

    private DoubleQuat rotateY_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, -(_rd2 * _t2));
            dd[1] = java.lang.Math.fma(_rd1, _t1, _rd3 * _t2);
            dd[2] = java.lang.Math.fma(_rd0, _t2, _rd2 * _t1);
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd1 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) - (_rd2 * _t2));
            dd[1] = ((_rd1) * (_t1) + (_rd3 * _t2));
            dd[2] = ((_rd0) * (_t2) + (_rd2 * _t1));
            dd[3] = ((_rd3) * (_t1) - (_rd1 * _t2));
            return dest;
        }
    }

    private DoubleQuat rotateZ_narrow(double angle, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = java.lang.Math.fma(_rd0, _t1, _rd1 * _t2);
            dd[1] = java.lang.Math.fma(_rd1, _t1, -(_rd0 * _t2));
            dd[2] = java.lang.Math.fma(_rd2, _t1, _rd3 * _t2);
            dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd2 * _t2));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoubleQuatImpl) dest).data;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = sd[0];
            double _rd1 = sd[1];
            double _rd2 = sd[2];
            double _rd3 = sd[3];
            dd[0] = ((_rd0) * (_t1) + (_rd1 * _t2));
            dd[1] = ((_rd1) * (_t1) - (_rd0 * _t2));
            dd[2] = ((_rd2) * (_t1) + (_rd3 * _t2));
            dd[3] = ((_rd3) * (_t1) - (_rd2 * _t2));
            return dest;
        }
    }

    private DoubleQuat nlerpShortest_narrow(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] targetData = ((DoubleQuatImpl) target).data;
        double targetX = targetData[0];
        double targetY = targetData[1];
        double targetZ = targetData[2];
        double targetW = targetData[3];
        if (org.joml2.internal.FmaSwitch.ON) return nlerpShortest_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    private DoubleQuat slerpShortest_narrow(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double[] targetData = ((DoubleQuatImpl) target).data;
        double targetX = targetData[0];
        double targetY = targetData[1];
        double targetZ = targetData[2];
        double targetW = targetData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        double[] sd = this.data;
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_s5e9c190a_1_fma(alpha, dest, sd, ((DoubleQuatImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    private DoubleQuat rotateTowards_narrow(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) return rotateTowards_fma_narrow(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd_narrow(targetX, targetY, targetZ, targetW, step, dest);
    }

    private DoubleQuat rotateTowards_narrow(DoubleQuatR target, double step, @Mutated DoubleQuat dest) {
        double[] targetData = ((DoubleQuatImpl) target).data;
        double targetX = targetData[0];
        double targetY = targetData[1];
        double targetZ = targetData[2];
        double targetW = targetData[3];
        if (org.joml2.internal.FmaSwitch.ON) return rotateTowards_fma_narrow(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd_narrow(targetX, targetY, targetZ, targetW, step, dest);
    }

    private Double4x4 toMatrix_mulAdd_narrow(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[2] * sd[2];
        double _t1 = sd[2] * sd[3];
        double _t2 = sd[1] * sd[3];
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = ((1.0) - (2.0) * (((_rd1) * (_rd1) + (_t0))));
        dd[1] = 2.0 * ((_rd0) * (_rd1) + (_t1));
        dd[2] = 2.0 * ((_rd0) * (_rd2) - (_t2));
        dd[3] = 0.0;
        dd[4] = 2.0 * ((_rd0) * (_rd1) - (_t1));
        dd[5] = ((1.0) - (2.0) * (((_rd0) * (_rd0) + (_t0))));
        dd[6] = 2.0 * ((_rd0) * (_rd3) + (_rd1 * _rd2));
        dd[7] = 0.0;
        dd[8] = 2.0 * ((_rd0) * (_rd2) + (_t2));
        dd[9] = 2.0 * ((_rd1) * (_rd2) - (_rd0 * _rd3));
        dd[10] = ((1.0) - (2.0) * (((_rd0) * (_rd0) + (_rd1 * _rd1))));
        dd[11] = 0.0;
        dd[12] = 0.0;
        dd[13] = 0.0;
        dd[14] = 0.0;
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    private DoubleQuat slerp_mulAdd_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t2 = sd[3] + targetW;
        double _t3 = sd[2] + targetZ;
        double _t4 = sd[0] + targetX;
        double _t5 = sd[1] + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd_narrow(targetX, targetY, targetZ, targetW, alpha, dest);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = ((((1.0) - (0.5) * (_t10))) * (_t22) + (Math.cosWithSin(_t14)));
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dd[0] = ((sd[0]) * (_t25) + (targetX * _t26));
        dd[1] = ((sd[1]) * (_t25) + (targetY * _t26));
        dd[2] = ((sd[2]) * (_t25) + (targetZ * _t26));
        dd[3] = ((sd[3]) * (_t25) + (targetW * _t26));
        return dest;
    }

    private DoubleQuat slerp_degenerate_fma_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetW;
        double _t2 = sd[2] + targetZ;
        double _t3 = sd[0] + targetX;
        double _t4 = sd[1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dd[0] = java.lang.Math.fma(sd[0], _t13, targetX * _t14);
        dd[1] = java.lang.Math.fma(sd[1], _t13, targetY * _t14);
        dd[2] = java.lang.Math.fma(sd[2], _t13, targetZ * _t14);
        dd[3] = java.lang.Math.fma(sd[3], _t13, targetW * _t14);
        return dest;
    }

    private DoubleQuat makeRotationYXZ_mulAdd_narrow(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[1] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[2] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[3] = ((_t12) * (_t8) + (_t11 * _t5));
        return this;
    }

    private DoubleQuat rotateTowards_fma_narrow(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(sd[3], targetW, java.lang.Math.fma(sd[2], targetZ, java.lang.Math.fma(sd[0], targetX, sd[1] * targetY))) > 0.0) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        double _t13 = sd[3] + _t9;
        double _t14 = sd[2] + _t10;
        double _t15 = sd[0] + _t11;
        double _t16 = sd[1] + _t12;
        double _t17 = sd[3] - _t9;
        double _t18 = sd[2] - _t10;
        double _t19 = sd[0] - _t11;
        double _t20 = sd[1] - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_fma_narrow(dest, sd, ((DoubleQuatImpl) dest).data, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    private DoubleQuat rotateTowards_mulAdd_narrow(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double _t9, _t10, _t11, _t12;
        if (-((sd[3]) * (targetW) + (((sd[2]) * (targetZ) + (((sd[0]) * (targetX) + (sd[1] * targetY)))))) > 0.0) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        double _t13 = sd[3] + _t9;
        double _t14 = sd[2] + _t10;
        double _t15 = sd[0] + _t11;
        double _t16 = sd[1] + _t12;
        double _t17 = sd[3] - _t9;
        double _t18 = sd[2] - _t10;
        double _t19 = sd[0] - _t11;
        double _t20 = sd[1] - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_mulAdd_narrow(dest, sd, ((DoubleQuatImpl) dest).data, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    private DoubleQuat slerp_degenerate_mulAdd_narrow(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        double _t1 = sd[3] + targetW;
        double _t2 = sd[2] + targetZ;
        double _t3 = sd[0] + targetX;
        double _t4 = sd[1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dd[0] = ((sd[0]) * (_t13) + (targetX * _t14));
        dd[1] = ((sd[1]) * (_t13) + (targetY * _t14));
        dd[2] = ((sd[2]) * (_t13) + (targetZ * _t14));
        dd[3] = ((sd[3]) * (_t13) + (targetW * _t14));
        return dest;
    }

    private DoubleQuat rotateTowards_sbfa68797_1_fma_narrow(DoubleQuat dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t37, double _t40, double _t41) {
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(sd[3], _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(sd[2], _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(sd[0], _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(sd[1], _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(sd[3], _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(sd[2], _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(sd[0], _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(sd[1], _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        if (_t73 != 0.0) {
            dd[0] = _t74 * _t68;
            dd[1] = _t74 * _t69;
            dd[2] = _t74 * _t67;
            dd[3] = _t74 * _t66;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    private DoubleQuat rotateTowards_sbfa68797_1_mulAdd_narrow(DoubleQuat dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t37, double _t40, double _t41) {
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((sd[3]) * (_t53) + (_t52 * _t9));
            _t67 = ((sd[2]) * (_t53) + (_t52 * _t10));
            _t68 = ((sd[0]) * (_t53) + (_t52 * _t11));
            _t69 = ((sd[1]) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((sd[3]) * (_t41) + (_t9 * _t40));
            _t67 = ((sd[2]) * (_t41) + (_t10 * _t40));
            _t68 = ((sd[0]) * (_t41) + (_t11 * _t40));
            _t69 = ((sd[1]) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        if (_t73 != 0.0) {
            dd[0] = _t74 * _t68;
            dd[1] = _t74 * _t69;
            dd[2] = _t74 * _t67;
            dd[3] = _t74 * _t66;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }
}
