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
 * Generated implementation of {@link DoubleQuat} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleQuatImpl implements DoubleQuat {
    public double x;
    public double y;
    public double z;
    public double w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatBbOpsUnsafe()
            : new DoubleQuatBbOpsApi();
        static final DoubleQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatRawOpsUnsafe()
            : new DoubleQuatRawOpsApi();
    }

    public DoubleQuatImpl() {
        w = 1;
    }

    public DoubleQuatImpl(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public DoubleQuatImpl(DoubleQuatR src) {
        DoubleQuatImpl srcImpl = (DoubleQuatImpl) src;
        this.x = srcImpl.x;
        this.y = srcImpl.y;
        this.z = srcImpl.z;
        this.w = srcImpl.w;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            d.x = -(this.x * _t3_inv);
            d.y = -(this.y * _t3_inv);
            d.z = -(this.z * _t3_inv);
            d.w = this.w * _t3_inv;
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t3_inv = 1.0 / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            d.x = -(this.x * _t3_inv);
            d.y = -(this.y * _t3_inv);
            d.z = -(this.z * _t3_inv);
            d.w = this.w * _t3_inv;
            return d;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        double _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        double _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat invertProduct_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = ((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z));
        double _t21 = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        double _t22 = ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y));
        double _t23 = ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
        return d;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = java.lang.Math.sqrt(1.0 - u1);
        this.x = Math.sinWithCos(_t1) * _t4;
        this.y = Math.cosWithSin(_t1) * _t4;
        this.z = Math.sinWithCos(_t3) * _t0;
        this.w = Math.cosWithSin(_t3) * _t0;
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
        DoubleQuatImpl vImpl = (DoubleQuatImpl) v;
        double vX = vImpl.x;
        double vY = vImpl.y;
        double vZ = vImpl.z;
        double vW = vImpl.w;
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = (float) (this.x);
        d.y = (float) (this.y);
        d.z = (float) (this.z);
        d.w = (float) (this.w);
        return d;
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
        DoubleDualQuatImpl dqImpl = (DoubleDualQuatImpl) dq;
        double dqRX = dqImpl.rX;
        double dqRY = dqImpl.rY;
        double dqRZ = dqImpl.rZ;
        double dqRW = dqImpl.rW;
        double dqDX = dqImpl.dX;
        double dqDY = dqImpl.dY;
        double dqDZ = dqImpl.dZ;
        double dqDW = dqImpl.dW;
        this.x = dqRX;
        this.y = dqRY;
        this.z = dqRZ;
        this.w = dqRW;
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
        this.x = dqRX;
        this.y = dqRY;
        this.z = dqRZ;
        this.w = dqRW;
        return this;
    }

    /**
     * Private store group 0 of {@code makeFromMatrix}: computes and stores it. Shared by 3
     * identical private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s11ab8262_c0(DoubleQuatImpl _dst, double _t10, double _sp0, double _t1, double _r0, double _t2, double _t15, double _r1, double _r4, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        _dst.x = _t10 > 0.0 ? _sp0 * _t1 : _r0 > _t2 ? 0.5 * java.lang.Math.sqrt(_t15) : _r1 > _r4 ? _sp1 * _t4 : _sp2 * _t6;
        _dst.y = _t10 > 0.0 ? _sp0 * _t7 : _r0 > _t2 ? _sp3 * _t4 : _r1 > _r4 ? 0.5 * java.lang.Math.sqrt(_t16) : _sp2 * _t8;
        _dst.z = _t10 > 0.0 ? _sp0 * _t9 : _r0 > _t2 ? _sp3 * _t6 : _r1 > _r4 ? _sp1 * _t8 : 0.5 * java.lang.Math.sqrt(_t17);
        _dst.w = _t10 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : _r0 > _t2 ? _sp3 * _t1 : _r1 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /**
     * Private tail of {@code makeFromMatrix}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s11ab8262_tail(DoubleQuatImpl _dst, double _t15, double _t10, double _sp0, double _t1, double _r0, double _t2, double _r1, double _r4, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _t16, double _t8, double _t9, double _t17, double _t14) {
        makeFromMatrix_s11ab8262_c0(_dst, _t10, _sp0, _t1, _r0, _t2, _t15, _r1, _r4, _sp1, _t4, _sp2, _t6, _t7, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
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
        Double3x3Impl mImpl = (Double3x3Impl) m;
        double _r0 = mImpl.m00;
        double _r1 = mImpl.m11;
        double _r2 = mImpl.m21;
        double _r3 = mImpl.m12;
        double _r4 = mImpl.m22;
        double _r5 = mImpl.m01;
        double _r6 = mImpl.m10;
        double _r7 = mImpl.m02;
        double _r8 = mImpl.m20;
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
        Double3x4Impl mImpl = (Double3x4Impl) m;
        double _r0 = mImpl.m00;
        double _r1 = mImpl.m11;
        double _r2 = mImpl.m21;
        double _r3 = mImpl.m12;
        double _r4 = mImpl.m22;
        double _r5 = mImpl.m01;
        double _r6 = mImpl.m10;
        double _r7 = mImpl.m02;
        double _r8 = mImpl.m20;
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
        Double4x4Impl mImpl = (Double4x4Impl) m;
        double _r0 = mImpl.m00;
        double _r1 = mImpl.m11;
        double _r2 = mImpl.m21;
        double _r3 = mImpl.m12;
        double _r4 = mImpl.m22;
        double _r5 = mImpl.m01;
        double _r6 = mImpl.m10;
        double _r7 = mImpl.m02;
        double _r8 = mImpl.m20;
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
        return this;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.x;
        d.rY = this.y;
        d.rZ = this.z;
        d.rW = this.w;
        d.dX = 0.0;
        d.dY = 0.0;
        d.dZ = 0.0;
        d.dW = 0.0;
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_fma(@Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        d.m10 = 2.0 * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0 * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m30 = 0.0;
        d.m01 = 2.0 * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        d.m21 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m31 = 0.0;
        d.m02 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        d.m32 = 0.0;
        d.m03 = 0.0;
        d.m13 = 0.0;
        d.m23 = 0.0;
        d.m33 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0 * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0 * ((this.x) * (this.z) - (_t2));
        d.m30 = 0.0;
        d.m01 = 2.0 * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        d.m31 = 0.0;
        d.m02 = 2.0 * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m32 = 0.0;
        d.m03 = 0.0;
        d.m13 = 0.0;
        d.m23 = 0.0;
        d.m33 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        d.m10 = 2.0 * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0 * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m01 = 2.0 * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        d.m21 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m02 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        d.properties = 0;
        return d;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0 * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0 * ((this.x) * (this.z) - (_t2));
        d.m01 = 2.0 * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        d.m02 = 2.0 * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        d.properties = 0;
        return d;
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
        Double3x4Impl d = (Double3x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        d.m01 = 2.0 * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m02 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        d.m03 = 0.0;
        d.m10 = 2.0 * java.lang.Math.fma(this.x, this.y, _t1);
        d.m11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        d.m12 = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m13 = 0.0;
        d.m20 = 2.0 * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m21 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m22 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        d.m23 = 0.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        Double3x4Impl d = (Double3x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        d.m01 = 2.0 * ((this.x) * (this.y) - (_t1));
        d.m02 = 2.0 * ((this.x) * (this.z) + (_t2));
        d.m03 = 0.0;
        d.m10 = 2.0 * ((this.x) * (this.y) + (_t1));
        d.m11 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        d.m12 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        d.m13 = 0.0;
        d.m20 = 2.0 * ((this.x) * (this.z) - (_t2));
        d.m21 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        d.m22 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m23 = 0.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        Double3Impl axisImpl = (Double3Impl) axis;
        double axisX = axisImpl.x;
        double axisY = axisImpl.y;
        double axisZ = axisImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) return decomposeSwingTwist_fma(axisX, axisY, axisZ, swing, twist);
        return decomposeSwingTwist_mulAdd(axisX, axisY, axisZ, swing, twist);
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
        if (org.joml2.internal.FmaSwitch.ON) return decomposeSwingTwist_fma(axisX, axisY, axisZ, swing, twist);
        return decomposeSwingTwist_mulAdd(axisX, axisY, axisZ, swing, twist);
    }

    /** {@code decomposeSwingTwist} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat decomposeSwingTwist_fma(double axisX, double axisY, double axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        DoubleQuatImpl d0 = (DoubleQuatImpl) swing;
        DoubleQuatImpl d1 = (DoubleQuatImpl) twist;
        double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d0.x = java.lang.Math.fma(_rd0, _t11, -this.w * _t12) + java.lang.Math.fma(_rd2, _t13, -_rd1 * _t14);
        d0.y = java.lang.Math.fma(_rd0, _t14, -this.w * _t13) + java.lang.Math.fma(_rd1, _t11, -_rd2 * _t12);
        d0.z = java.lang.Math.fma(_rd1, _t12, _rd2 * _t11) + java.lang.Math.fma(-_rd0, _t13, -this.w * _t14);
        d0.w = java.lang.Math.fma(_rd0, _t12, this.w * _t11) - java.lang.Math.fma(-_rd2, _t14, -_rd1 * _t13);
        d1.x = _t12;
        d1.y = _t13;
        d1.z = _t14;
        d1.w = _t11;
        return this;
    }

    /** {@code decomposeSwingTwist} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat decomposeSwingTwist_mulAdd(double axisX, double axisY, double axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        DoubleQuatImpl d0 = (DoubleQuatImpl) swing;
        DoubleQuatImpl d1 = (DoubleQuatImpl) twist;
        double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d0.x = ((_rd0) * (_t11) - (this.w * _t12)) + ((_rd2) * (_t13) - (_rd1 * _t14));
        d0.y = ((_rd0) * (_t14) - (this.w * _t13)) + ((_rd1) * (_t11) - (_rd2 * _t12));
        d0.z = ((_rd1) * (_t12) + (_rd2 * _t11)) + ((-_rd0) * (_t13) - (this.w * _t14));
        d0.w = ((_rd0) * (_t12) + (this.w * _t11)) - ((-_rd2) * (_t14) - (_rd1 * _t13));
        d1.x = _t12;
        d1.y = _t13;
        d1.z = _t14;
        d1.w = _t11;
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
        Double3Impl axisImpl = (Double3Impl) axis;
        return getSwing(axisImpl.x, axisImpl.y, axisImpl.z, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return getSwing_mulAdd(axisX, axisY, axisZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t11, -this.w * _t12) + java.lang.Math.fma(_rd2, _t13, -_rd1 * _t14);
        d.y = java.lang.Math.fma(_rd0, _t14, -this.w * _t13) + java.lang.Math.fma(_rd1, _t11, -_rd2 * _t12);
        d.z = java.lang.Math.fma(_rd1, _t12, _rd2 * _t11) + java.lang.Math.fma(-_rd0, _t13, -this.w * _t14);
        d.w = java.lang.Math.fma(_rd0, _t12, this.w * _t11) - java.lang.Math.fma(-_rd2, _t14, -_rd1 * _t13);
        return d;
    }

    /** {@code getSwing} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat getSwing_mulAdd(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t11) - (this.w * _t12)) + ((_rd2) * (_t13) - (_rd1 * _t14));
        d.y = ((_rd0) * (_t14) - (this.w * _t13)) + ((_rd1) * (_t11) - (_rd2 * _t12));
        d.z = ((_rd1) * (_t12) + (_rd2 * _t11)) + ((-_rd0) * (_t13) - (this.w * _t14));
        d.w = ((_rd0) * (_t12) + (this.w * _t11)) - ((-_rd2) * (_t14) - (_rd1 * _t13));
        return d;
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
        Double3Impl axisImpl = (Double3Impl) axis;
        return getTwist(axisImpl.x, axisImpl.y, axisImpl.z, dest);
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
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
            double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
                d.w = 1.0;
            }
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
            double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
                d.w = 1.0;
            }
            return d;
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
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 1.0;
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
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
        }
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
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        double targetX = targetImpl.x;
        double targetY = targetImpl.y;
        double targetZ = targetImpl.z;
        double targetW = targetImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, this.w * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, this.z * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, this.x * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, this.y * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            d.x = _t7 * _t13;
            d.y = _t8 * _t13;
            d.z = _t6 * _t13;
            d.w = _t5 * _t13;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, this.w * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, this.z * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, this.x * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, this.y * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            d.x = _t7 * _t13;
            d.y = _t8 * _t13;
            d.z = _t6 * _t13;
            d.w = _t5 * _t13;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
    }

    /** {@code nlerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat nlerp_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (targetW) + (this.w * _t0));
        double _t6 = ((alpha) * (targetZ) + (this.z * _t0));
        double _t7 = ((alpha) * (targetX) + (this.x * _t0));
        double _t8 = ((alpha) * (targetY) + (this.y * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            d.x = _t7 * _t13;
            d.y = _t8 * _t13;
            d.z = _t6 * _t13;
            d.w = _t5 * _t13;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
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
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        double targetX = targetImpl.x;
        double targetY = targetImpl.y;
        double targetZ = targetImpl.z;
        double targetW = targetImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return nlerpShortest_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return nlerpShortest_fma(targetX, targetY, targetZ, targetW, alpha, dest);
        return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
    }

    /** {@code nlerpShortest} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat nlerpShortest_fma(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(this.w, targetW, java.lang.Math.fma(this.z, targetZ, java.lang.Math.fma(this.x, targetX, this.y * targetY))) > 0.0) {
            _t18 = java.lang.Math.fma(alpha, -targetW, this.w * _t1);
            _t19 = java.lang.Math.fma(alpha, -targetZ, this.z * _t1);
            _t20 = java.lang.Math.fma(alpha, -targetX, this.x * _t1);
            _t21 = java.lang.Math.fma(alpha, -targetY, this.y * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, targetW, this.w * _t1);
            _t19 = java.lang.Math.fma(alpha, targetZ, this.z * _t1);
            _t20 = java.lang.Math.fma(alpha, targetX, this.x * _t1);
            _t21 = java.lang.Math.fma(alpha, targetY, this.y * _t1);
        }
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            d.x = _t20 * _t26;
            d.y = _t21 * _t26;
            d.z = _t19 * _t26;
            d.w = _t18 * _t26;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
    }

    /** {@code nlerpShortest} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat nlerpShortest_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((this.w) * (targetW) + (((this.z) * (targetZ) + (((this.x) * (targetX) + (this.y * targetY)))))) > 0.0) {
            _t18 = ((this.w * _t1) - (alpha) * (targetW));
            _t19 = ((this.z * _t1) - (alpha) * (targetZ));
            _t20 = ((this.x * _t1) - (alpha) * (targetX));
            _t21 = ((this.y * _t1) - (alpha) * (targetY));
        } else {
            _t18 = ((alpha) * (targetW) + (this.w * _t1));
            _t19 = ((alpha) * (targetZ) + (this.z * _t1));
            _t20 = ((alpha) * (targetX) + (this.x * _t1));
            _t21 = ((alpha) * (targetY) + (this.y * _t1));
        }
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            d.x = _t20 * _t26;
            d.y = _t21 * _t26;
            d.z = _t19 * _t26;
            d.w = _t18 * _t26;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
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
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        return slerp(targetImpl.x, targetImpl.y, targetImpl.z, targetImpl.w, alpha, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = this.w + targetW;
        double _t3 = this.z + targetZ;
        double _t4 = this.x + targetX;
        double _t5 = this.y + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(targetX, targetY, targetZ, targetW, alpha, dest);
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
        d.x = java.lang.Math.fma(this.x, _t25, targetX * _t26);
        d.y = java.lang.Math.fma(this.y, _t25, targetY * _t26);
        d.z = java.lang.Math.fma(this.z, _t25, targetZ * _t26);
        d.w = java.lang.Math.fma(this.w, _t25, targetW * _t26);
        return d;
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat slerp_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = this.w + targetW;
        double _t3 = this.z + targetZ;
        double _t4 = this.x + targetX;
        double _t5 = this.y + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
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
        d.x = ((this.x) * (_t25) + (targetX * _t26));
        d.y = ((this.y) * (_t25) + (targetY * _t26));
        d.z = ((this.z) * (_t25) + (targetZ * _t26));
        d.w = ((this.w) * (_t25) + (targetW * _t26));
        return d;
    }

    /**
     * Degenerate-arc path of {@code slerp}: its methods leave here when the two quaternions are
     * equal or opposite to within the precision (the sine of the angle between them is too small to
     * divide by) or NaN; reached only through them.
     */
    private DoubleQuat slerp_degenerate_fma(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
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
        d.x = java.lang.Math.fma(this.x, _t13, targetX * _t14);
        d.y = java.lang.Math.fma(this.y, _t13, targetY * _t14);
        d.z = java.lang.Math.fma(this.z, _t13, targetZ * _t14);
        d.w = java.lang.Math.fma(this.w, _t13, targetW * _t14);
        return d;
    }

    /**
     * Degenerate-arc path of {@code slerp}: its methods leave here when the two quaternions are
     * equal or opposite to within the precision (the sine of the angle between them is too small to
     * divide by) or NaN; reached only through them.
     */
    private DoubleQuat slerp_degenerate_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
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
        d.x = ((this.x) * (_t13) + (targetX * _t14));
        d.y = ((this.y) * (_t13) + (targetY * _t14));
        d.z = ((this.z) * (_t13) + (targetZ * _t14));
        d.w = ((this.w) * (_t13) + (targetW * _t14));
        return d;
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
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        double targetX = targetImpl.x;
        double targetY = targetImpl.y;
        double targetZ = targetImpl.z;
        double targetW = targetImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
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
        double _t30 = java.lang.Math.fma(-_t15, _t15, 1.0);
        double _t32 = (1.0 / java.lang.Math.sqrt(_t30));
        slerpShortest_s323d976d_tail_fma(d, java.lang.Math.sqrt(_t30), _r0, Math.sin(_t0 * _t17) * _t32, Math.sin(alpha * _t17) * _t32, _t21, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s323d976d_tail_fma(DoubleQuatImpl _dst, double _t31, double _r0, double _t34, double _t33, double _t21, double alpha, double _t0, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(_r0, _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(_r1, _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(_r2, _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(_r3, _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _r0 * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, _r1 * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, _r2 * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, _r3 * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        {
            double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
            _dst.x = _t50 != 0.0 ? _t51 * _t45 : 0.0;
            _dst.y = _t50 != 0.0 ? _t51 * _t46 : 0.0;
            _dst.z = _t50 != 0.0 ? _t51 * _t44 : 0.0;
            _dst.w = _t50 != 0.0 ? _t51 * _t43 : 0.0;
        }
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s323d976d_tail_mulAdd(DoubleQuatImpl _dst, double _t31, double _r0, double _t34, double _t33, double _t21, double alpha, double _t0, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((_r0) * (_t34) + (_t33 * _t21));
            _t44 = ((_r1) * (_t34) + (_t33 * _t22));
            _t45 = ((_r2) * (_t34) + (_t33 * _t23));
            _t46 = ((_r3) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_r0 * _t0));
            _t44 = ((alpha) * (_t22) + (_r1 * _t0));
            _t45 = ((alpha) * (_t23) + (_r2 * _t0));
            _t46 = ((alpha) * (_t24) + (_r3 * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        {
            double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
            _dst.x = _t50 != 0.0 ? _t51 * _t45 : 0.0;
            _dst.y = _t50 != 0.0 ? _t51 * _t46 : 0.0;
            _dst.z = _t50 != 0.0 ? _t51 * _t44 : 0.0;
            _dst.w = _t50 != 0.0 ? _t51 * _t43 : 0.0;
        }
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
        if (!org.joml2.internal.FmaSwitch.ON) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
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
        double _t30 = java.lang.Math.fma(-_t15, _t15, 1.0);
        double _t32 = (1.0 / java.lang.Math.sqrt(_t30));
        slerpShortest_s323d976d_tail_fma(d, java.lang.Math.sqrt(_t30), _r0, Math.sin(_t0 * _t17) * _t32, Math.sin(alpha * _t17) * _t32, _t21, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** {@code slerpShortest} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat slerpShortest_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - alpha;
        double _t12 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
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
        double _t30 = ((1.0) - (_t15) * (_t15));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t30));
        slerpShortest_s323d976d_tail_mulAdd(d, java.lang.Math.sqrt(_t30), _r0, Math.sin(_t0 * _t17) * _t32, Math.sin(alpha * _t17) * _t32, _t21, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
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
        DoubleQuatImpl control0Impl = (DoubleQuatImpl) control0;
        DoubleQuatImpl control1Impl = (DoubleQuatImpl) control1;
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        double control0X = control0Impl.x;
        double control0Y = control0Impl.y;
        double control0Z = control0Impl.z;
        double control0W = control0Impl.w;
        double control1X = control1Impl.x;
        double control1Y = control1Impl.y;
        double control1Z = control1Impl.z;
        double control1W = control1Impl.w;
        double targetX = targetImpl.x;
        double targetY = targetImpl.y;
        double targetZ = targetImpl.z;
        double targetW = targetImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return squad_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
        return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
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
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t7 = Math.min(t, _t0);
        double _t23 = squad_s2d5a8506_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W);
        double _t24 = squad_s2d5a8506_2_fma(targetX, targetY, targetZ, targetW, _r0, _r1, _r2, _r3);
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_s2d5a8506_4_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t1, _t0 * _t1, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t7 = Math.min(t, _t0);
        double _t23 = squad_s2d5a8506_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W);
        double _t24 = squad_s2d5a8506_2_mulAdd(targetX, targetY, targetZ, targetW, _r0, _r1, _r2, _r3);
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_s2d5a8506_4_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t1, _t0 * _t1, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
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
    private double squad_s2d5a8506_2_fma(double targetX, double targetY, double targetZ, double targetW, double _r0, double _r1, double _r2, double _r3) {
        double _t8 = _r0 + targetW;
        double _t9 = _r1 + targetZ;
        double _t10 = _r2 + targetX;
        double _t11 = _r3 + targetY;
        return Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
    }

    /** Part 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double squad_s2d5a8506_2_mulAdd(double targetX, double targetY, double targetZ, double targetW, double _r0, double _r1, double _r2, double _r3) {
        double _t8 = _r0 + targetW;
        double _t9 = _r1 + targetZ;
        double _t10 = _r2 + targetX;
        double _t11 = _r3 + targetY;
        return Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_3_fma(double control0Y, double control1Y, double targetX, double targetY, DoubleQuatImpl d, double _r2, double _r3, double _t12, double _t16, double _t61, double _t62, double _t59, double _t60, double _t71, double _t72, double _t73, double _t74, double _t75) {
        double _t76 = java.lang.Math.fma(_r2, _t61, targetX * _t62);
        double _t77 = java.lang.Math.fma(control0Y, _t59, control1Y * _t60);
        double _t78 = java.lang.Math.fma(_r3, _t61, targetY * _t62);
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
        d.x = java.lang.Math.fma(_t71, _t105, _t76 * _t106);
        d.y = java.lang.Math.fma(_t77, _t105, _t78 * _t106);
        d.z = java.lang.Math.fma(_t74, _t105, _t75 * _t106);
        d.w = java.lang.Math.fma(_t72, _t105, _t73 * _t106);
        return d;
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_3_mulAdd(double control0Y, double control1Y, double targetX, double targetY, DoubleQuatImpl d, double _r2, double _r3, double _t12, double _t16, double _t61, double _t62, double _t59, double _t60, double _t71, double _t72, double _t73, double _t74, double _t75) {
        double _t76 = ((_r2) * (_t61) + (targetX * _t62));
        double _t77 = ((control0Y) * (_t59) + (control1Y * _t60));
        double _t78 = ((_r3) * (_t61) + (targetY * _t62));
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
        d.x = ((_t71) * (_t105) + (_t76 * _t106));
        d.y = ((_t77) * (_t105) + (_t78 * _t106));
        d.z = ((_t74) * (_t105) + (_t75 * _t106));
        d.w = ((_t72) * (_t105) + (_t73 * _t106));
        return d;
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_4_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t0, double _t1, double _t12, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
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
        return squad_s2d5a8506_3_fma(control0Y, control1Y, targetX, targetY, d, _r2, _r3, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)), _t61, _t62, _t59, _t60, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_r0, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_r1, _t61, targetZ * _t62));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_4_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t0, double _t1, double _t12, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
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
        return squad_s2d5a8506_3_mulAdd(control0Y, control1Y, targetX, targetY, d, _r2, _r3, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))), _t61, _t62, _t59, _t60, ((control0X) * (_t59) + (control1X * _t60)), ((control0W) * (_t59) + (control1W * _t60)), ((_r0) * (_t61) + (targetW * _t62)), ((control0Z) * (_t59) + (control1Z * _t60)), ((_r1) * (_t61) + (targetZ * _t62)));
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        return mul(otherImpl.x, otherImpl.y, otherImpl.z, otherImpl.w, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherZ, _rd1, -(otherY * _rd2));
            d.y = java.lang.Math.fma(otherX, _rd2, otherW * _rd1) + java.lang.Math.fma(otherY, this.w, otherZ * -_rd0);
            d.z = java.lang.Math.fma(otherY, _rd0, otherZ * this.w) + java.lang.Math.fma(otherW, _rd2, -(otherX * _rd1));
            d.w = java.lang.Math.fma(otherW, this.w, otherX * -_rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherZ) * (_rd1) - (otherY * _rd2));
            d.y = ((otherX) * (_rd2) + (otherW * _rd1)) + ((otherY) * (this.w) - (otherZ * _rd0));
            d.z = ((otherY) * (_rd0) + (otherZ * this.w)) + ((otherW) * (_rd2) - (otherX * _rd1));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        return preMul(otherImpl.x, otherImpl.y, otherImpl.z, otherImpl.w, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -otherX * _rd2);
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -otherX * _rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherY) * (_rd2) - (otherZ * _rd1));
            d.y = ((otherY) * (this.w) + (otherZ * _rd0)) + ((otherW) * (_rd1) - (otherX * _rd2));
            d.z = ((otherX) * (_rd1) + (otherW * _rd2)) + ((otherZ) * (this.w) - (otherY * _rd0));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = java.lang.Math.fma(weight, otherX, this.x);
            d.y = java.lang.Math.fma(weight, otherY, this.y);
            d.z = java.lang.Math.fma(weight, otherZ, this.z);
            d.w = java.lang.Math.fma(weight, otherW, this.w);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = ((weight) * (otherX) + (this.x));
            d.y = ((weight) * (otherY) + (this.y));
            d.z = ((weight) * (otherZ) + (this.z));
            d.w = ((weight) * (otherW) + (this.w));
            return d;
        }
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
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = java.lang.Math.fma(weight, otherX, this.x);
            d.y = java.lang.Math.fma(weight, otherY, this.y);
            d.z = java.lang.Math.fma(weight, otherZ, this.z);
            d.w = java.lang.Math.fma(weight, otherW, this.w);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = ((weight) * (otherX) + (this.x));
            d.y = ((weight) * (otherY) + (this.y));
            d.z = ((weight) * (otherZ) + (this.z));
            d.w = ((weight) * (otherW) + (this.w));
            return d;
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
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))), this.w);
        } else {
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))), this.w);
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
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
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
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
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
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
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double angleTo_mulAdd(double otherX, double otherY, double otherZ, double otherW) {
        double _t9, _t10, _t11, _t12;
        if (-((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))))) > 0.0) {
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
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
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
            Double3Impl d = (Double3Impl) dest;
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-_rd0, _rd0, java.lang.Math.fma(-_rd1, _rd1, java.lang.Math.fma(-_rd2, _rd2, 1.0)))));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (_rd2) * (_rd2))) - (_rd1) * (_rd1))) - (_rd0) * (_rd0))));
            return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = this.w;
        return d;
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
        DoubleQuatImpl qImpl = (DoubleQuatImpl) q;
        return conjugateBy(qImpl.x, qImpl.y, qImpl.z, qImpl.w, dest);
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = java.lang.Math.fma(qX, this.y, qW * this.z) + java.lang.Math.fma(qZ, this.w, qY * -this.x);
        double _t21 = java.lang.Math.fma(qY, this.w, qZ * this.x) + java.lang.Math.fma(qW, this.y, -(qX * this.z));
        double _t22 = java.lang.Math.fma(qX, this.w, qW * this.x) + java.lang.Math.fma(qY, this.z, -(qZ * this.y));
        double _t23 = java.lang.Math.fma(qW, this.w, qX * -this.x) - java.lang.Math.fma(qY, this.y, qZ * this.z);
        d.x = java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23);
        d.y = java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20);
        d.z = java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23);
        d.w = java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21);
        return d;
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_mulAdd(double qX, double qY, double qZ, double qW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = ((qX) * (this.y) + (qW * this.z)) + ((qZ) * (this.w) - (qY * this.x));
        double _t21 = ((qY) * (this.w) + (qZ * this.x)) + ((qW) * (this.y) - (qX * this.z));
        double _t22 = ((qX) * (this.w) + (qW * this.x)) + ((qY) * (this.z) - (qZ * this.y));
        double _t23 = ((qW) * (this.w) - (qX * this.x)) - ((qY) * (this.y) + (qZ * this.z));
        d.x = ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23));
        d.y = ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20));
        d.z = ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23));
        d.w = ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21));
        return d;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * _rd0) + java.lang.Math.fma(otherY, this.z, -otherZ * _rd1)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, _rd1, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * _rd0)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * _rd0) + java.lang.Math.fma(otherY, this.z, -otherZ * _rd1)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, _rd1, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * _rd0)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat difference_mulAdd(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t3_inv = 1.0 / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = (((otherX) * (this.w) - (otherW * _rd0)) + ((otherY) * (this.z) - (otherZ * _rd1))) * _t3_inv;
        d.y = ((((otherY) * (this.w) + (otherZ * _rd0))) * (_t3_inv) + (((-otherX) * (_sp1) - (otherW * _sp0))));
        d.z = (((otherX) * (_rd1) - (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * _rd0))) * _t3_inv;
        d.w = ((((otherX) * (_rd0) + (otherW * this.w))) * (_t3_inv) + (((otherZ) * (_sp1) + (otherY * _sp0))));
        return d;
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
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        double otherX = otherImpl.x;
        double otherY = otherImpl.y;
        double otherZ = otherImpl.z;
        double otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
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
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
            if (_t4 > 0.0) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.cosWithSin(_t5) * _t2;
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
            if (_t4 > 0.0) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.cosWithSin(_t5) * _t2;
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.w, _t1);
        double _t12 = 2.0 * java.lang.Math.fma(this.x, this.w, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            d.x = Math.atan2(_t11, _t14);
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t12, _t13);
            d.z = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), 2.0 * java.lang.Math.fma(this.x, this.z, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        d.y = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t10 = 2.0 * ((this.x) * (this.z) + (_t2));
        double _t11 = 2.0 * ((this.x) * (this.w) + (_t1));
        double _t12 = 2.0 * ((this.x) * (this.w) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t14 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            d.x = Math.atan2(_t11, _t14);
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t12, _t13);
            d.z = Math.atan2(((_t13) * (2.0 * ((this.x) * (this.y) + (this.z * this.w))) + (2.0 * ((this.x) * (this.z) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        d.y = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.w, _t1);
        double _t12 = 2.0 * java.lang.Math.fma(this.x, this.w, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            d.x = Math.atan2(_t11, _t14);
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t12, _t13);
            d.z = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), 2.0 * java.lang.Math.fma(this.x, this.z, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        d.y = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t10 = 2.0 * ((this.x) * (this.z) + (_t2));
        double _t11 = 2.0 * ((this.x) * (this.w) + (_t1));
        double _t12 = 2.0 * ((this.x) * (this.w) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t14 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            d.x = Math.atan2(_t11, _t14);
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t12, _t13);
            d.z = Math.atan2(((_t13) * (2.0 * ((this.x) * (this.y) + (this.z * this.w))) + (2.0 * ((this.x) * (this.z) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        d.y = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return d;
    }

    /**
     * Private store group 0 of {@code getEulerAnglesXZY}: computes and stores it. Shared by 2
     * identical private paths of {@code getEulerAnglesXZY}; reached only through it.
     */
    private void getEulerAnglesXZY_s34d82902_c0_fma(Double3Impl _dst, double _t14, double _t16, double _r2, double _r3, double _t1, double _t12, double _t9, double _t11, double _r0, double _r1, double _t10) {
        _dst.x = _t14 <= _t16 ? Math.atan2(2.0 * java.lang.Math.fma(_r2, _r3, -_t1), _t12) : Math.atan2(_t9, _t11);
        _dst.y = _t14 <= _t16 ? 0.0 : Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(_r2, _r0, _r1 * _r3), _t9, _t11 * -2.0 * java.lang.Math.fma(_r2, _r1, _r0 * -_r3)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(_r0, _r1, _r2 * -_r3)));
        _dst.z = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
    }

    /**
     * Private store group 0 of {@code getEulerAnglesXZY}: computes and stores it. Shared by 2
     * identical private paths of {@code getEulerAnglesXZY}; reached only through it.
     */
    private void getEulerAnglesXZY_s34d82902_c0_mulAdd(Double3Impl _dst, double _t14, double _t16, double _r2, double _r3, double _t1, double _t12, double _t9, double _t11, double _r0, double _r1, double _t10) {
        _dst.x = _t14 <= _t16 ? Math.atan2(2.0 * ((_r2) * (_r3) - (_t1)), _t12) : Math.atan2(_t9, _t11);
        _dst.y = _t14 <= _t16 ? 0.0 : Math.atan2(((2.0 * ((_r2) * (_r0) + (_r1 * _r3))) * (_t9) - (_t11 * 2.0 * ((_r2) * (_r1) - (_r0 * _r3)))), ((_t12) * (_t11) - (_t9 * 2.0 * ((_r0) * (_r1) - (_r2 * _r3)))));
        _dst.z = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
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
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.w;
        double _t1 = _r0 * _r1;
        double _t9 = 2.0 * java.lang.Math.fma(_r2, _r3, _t1);
        double _t10 = 2.0 * java.lang.Math.fma(_r1, _r3, -(_r2 * _r0));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_r2, _r2, _r1 * _r1), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        getEulerAnglesXZY_s34d82902_c0_fma(d, _t14, java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31, _r2, _r3, _t1, java.lang.Math.fma(-2.0, java.lang.Math.fma(_r2, _r2, _r0 * _r0), 1.0), _t9, _t11, _r0, _r1, _t10);
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.w;
        double _t1 = _r0 * _r1;
        double _t9 = 2.0 * ((_r2) * (_r3) + (_t1));
        double _t10 = 2.0 * ((_r1) * (_r3) - (_r2 * _r0));
        double _t11 = ((1.0) - (2.0) * (((_r2) * (_r2) + (_r1 * _r1))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        getEulerAnglesXZY_s34d82902_c0_mulAdd(d, _t14, ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31, _r2, _r3, _t1, ((1.0) - (2.0) * (((_r2) * (_r2) + (_r0 * _r0)))), _t9, _t11, _r0, _r1, _t10);
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.w;
        double _t1 = _r0 * _r1;
        double _t9 = 2.0 * java.lang.Math.fma(_r2, _r3, _t1);
        double _t10 = 2.0 * java.lang.Math.fma(_r1, _r3, -(_r2 * _r0));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_r2, _r2, _r1 * _r1), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        getEulerAnglesXZY_s34d82902_c0_fma(d, _t14, epsilon * java.lang.Math.fma(_t10, _t10, _t14), _r2, _r3, _t1, java.lang.Math.fma(-2.0, java.lang.Math.fma(_r2, _r2, _r0 * _r0), 1.0), _t9, _t11, _r0, _r1, _t10);
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.w;
        double _t1 = _r0 * _r1;
        double _t9 = 2.0 * ((_r2) * (_r3) + (_t1));
        double _t10 = 2.0 * ((_r1) * (_r3) - (_r2 * _r0));
        double _t11 = ((1.0) - (2.0) * (((_r2) * (_r2) + (_r1 * _r1))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        getEulerAnglesXZY_s34d82902_c0_mulAdd(d, _t14, epsilon * ((_t10) * (_t10) + (_t14)), _r2, _r3, _t1, ((1.0) - (2.0) * (((_r2) * (_r2) + (_r0 * _r0)))), _t9, _t11, _r0, _r1, _t10);
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_fma(dest);
        return getEulerAnglesYXZ_mulAdd(dest);
    }

    /** {@code getEulerAnglesYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_fma(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, _t2);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.w, -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            d.y = Math.atan2(2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), _t12);
            d.z = 0.0;
        } else {
            d.y = Math.atan2(_t9, _t11);
            d.z = Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(this.x, this.w, _t0), _t11 * -2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(this.x, this.z, -_t2)));
        }
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.y * this.z;
        double _t2 = this.y * this.w;
        double _t9 = 2.0 * ((this.x) * (this.z) + (_t2));
        double _t10 = 2.0 * ((this.x) * (this.w) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = ((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            d.y = Math.atan2(2.0 * ((this.y) * (this.w) - (this.x * this.z)), _t12);
            d.z = 0.0;
        } else {
            d.y = Math.atan2(_t9, _t11);
            d.z = Math.atan2(((_t9) * (2.0 * ((this.x) * (this.w) + (_t0))) - (_t11 * 2.0 * ((this.x) * (this.y) - (this.z * this.w)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((this.x) * (this.z) - (_t2)))));
        }
        return d;
    }

    /** Private store group 0 of {@code getEulerAnglesYXZ}: computes and stores it; reached only through it. */
    private void getEulerAnglesYXZ_s19f1db93_c0_fma(Double3Impl _dst, double _t10, double _t14, double _t16, double _r0, double _r2, double _r3, double _r1, double _t12, double _t9, double _t11, double _t0, double _t2) {
        _dst.x = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        _dst.y = _t14 <= _t16 ? Math.atan2(2.0 * java.lang.Math.fma(_r0, _r2, -(_r3 * _r1)), _t12) : Math.atan2(_t9, _t11);
        _dst.z = _t14 <= _t16 ? 0.0 : Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(_r3, _r2, _t0), _t11 * -2.0 * java.lang.Math.fma(_r3, _r0, -(_r1 * _r2))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(_r3, _r1, -_t2)));
    }

    /** Private store group 0 of {@code getEulerAnglesYXZ}: computes and stores it; reached only through it. */
    private void getEulerAnglesYXZ_s19f1db93_c0_mulAdd(Double3Impl _dst, double _t10, double _t14, double _t16, double _r0, double _r2, double _r3, double _r1, double _t12, double _t9, double _t11, double _t0, double _t2) {
        _dst.x = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        _dst.y = _t14 <= _t16 ? Math.atan2(2.0 * ((_r0) * (_r2) - (_r3 * _r1)), _t12) : Math.atan2(_t9, _t11);
        _dst.z = _t14 <= _t16 ? 0.0 : Math.atan2(((_t9) * (2.0 * ((_r3) * (_r2) + (_t0))) - (_t11 * 2.0 * ((_r3) * (_r0) - (_r1 * _r2)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((_r3) * (_r1) - (_t2)))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.w;
        double _r3 = this.x;
        double _t0 = _r0 * _r1;
        double _t2 = _r0 * _r2;
        double _t9 = 2.0 * java.lang.Math.fma(_r3, _r1, _t2);
        double _t10 = 2.0 * java.lang.Math.fma(_r3, _r2, -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_r3, _r3, _r0 * _r0), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        getEulerAnglesYXZ_s19f1db93_c0_fma(d, _t10, _t14, epsilon * java.lang.Math.fma(_t10, _t10, _t14), _r0, _r2, _r3, _r1, java.lang.Math.fma(-2.0, java.lang.Math.fma(_r0, _r0, _r1 * _r1), 1.0), _t9, _t11, _t0, _t2);
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.y;
        double _r1 = this.z;
        double _r2 = this.w;
        double _r3 = this.x;
        double _t0 = _r0 * _r1;
        double _t2 = _r0 * _r2;
        double _t9 = 2.0 * ((_r3) * (_r1) + (_t2));
        double _t10 = 2.0 * ((_r3) * (_r2) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((_r3) * (_r3) + (_r0 * _r0))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        getEulerAnglesYXZ_s19f1db93_c0_mulAdd(d, _t10, _t14, epsilon * ((_t10) * (_t10) + (_t14)), _r0, _r2, _r3, _r1, ((1.0) - (2.0) * (((_r0) * (_r0) + (_r1 * _r1)))), _t9, _t11, _t0, _t2);
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t2 = this.z * this.w;
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.y, _t2);
        double _t12 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t13 = 2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 7.888609052210118E-31) {
            d.x = 0.0;
            d.y = Math.atan2(_t12, _t15);
        } else {
            d.x = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), 2.0 * java.lang.Math.fma(this.x, this.y, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            d.y = Math.atan2(_t13, _t14);
        }
        d.z = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t2 = this.z * this.w;
        double _t11 = 2.0 * ((this.x) * (this.y) + (_t2));
        double _t12 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t13 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t14 = ((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z))));
        double _t15 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 7.888609052210118E-31) {
            d.x = 0.0;
            d.y = Math.atan2(_t12, _t15);
        } else {
            d.x = Math.atan2(((_t14) * (2.0 * ((this.x) * (this.w) + (this.y * this.z))) + (2.0 * ((this.x) * (this.y) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            d.y = Math.atan2(_t13, _t14);
        }
        d.z = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t2 = this.z * this.w;
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.y, _t2);
        double _t12 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t13 = 2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            d.x = 0.0;
            d.y = Math.atan2(_t12, _t15);
        } else {
            d.x = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), 2.0 * java.lang.Math.fma(this.x, this.y, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            d.y = Math.atan2(_t13, _t14);
        }
        d.z = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t2 = this.z * this.w;
        double _t11 = 2.0 * ((this.x) * (this.y) + (_t2));
        double _t12 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t13 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t14 = ((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z))));
        double _t15 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            d.x = 0.0;
            d.y = Math.atan2(_t12, _t15);
        } else {
            d.x = Math.atan2(((_t14) * (2.0 * ((this.x) * (this.w) + (this.y * this.z))) + (2.0 * ((this.x) * (this.y) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            d.y = Math.atan2(_t13, _t14);
        }
        d.z = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.z * this.z;
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t12 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            d.y = 0.0;
            d.z = Math.atan2(_t11, _t14);
        } else {
            d.y = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            d.z = Math.atan2(_t12, _t13);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.z * this.z;
        double _t10 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t11 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t12 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t13 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            d.y = 0.0;
            d.z = Math.atan2(_t11, _t14);
        } else {
            d.y = Math.atan2(((_t13) * (2.0 * ((this.x) * (this.z) + (this.y * this.w))) + (2.0 * ((this.y) * (this.z) - (this.x * this.w)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            d.z = Math.atan2(_t12, _t13);
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.z * this.z;
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t11 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t12 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            d.y = 0.0;
            d.z = Math.atan2(_t11, _t14);
        } else {
            d.y = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            d.z = Math.atan2(_t12, _t13);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.z * this.z;
        double _t10 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t11 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t12 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t13 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        d.x = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            d.y = 0.0;
            d.z = Math.atan2(_t11, _t14);
        } else {
            d.y = Math.atan2(((_t13) * (2.0 * ((this.x) * (this.z) + (this.y * this.w))) + (2.0 * ((this.y) * (this.z) - (this.x * this.w)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            d.z = Math.atan2(_t12, _t13);
        }
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_fma(dest);
        return getEulerAnglesZYX_mulAdd(dest);
    }

    /** {@code getEulerAnglesZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_fma(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t8 = 2.0 * java.lang.Math.fma(this.x, this.y, _t1);
        double _t9 = 2.0 * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 7.888609052210118E-31) {
            d.x = 0.0;
            d.z = Math.atan2(2.0 * java.lang.Math.fma(this.z, this.w, -this.x * this.y), _t11);
        } else {
            d.x = Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), _t10 * -2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(this.x, this.y, -_t1)));
            d.z = Math.atan2(_t8, _t10);
        }
        d.y = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t8 = 2.0 * ((this.x) * (this.y) + (_t1));
        double _t9 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t10 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 7.888609052210118E-31) {
            d.x = 0.0;
            d.z = Math.atan2(2.0 * ((this.z) * (this.w) - (this.x * this.y)), _t11);
        } else {
            d.x = Math.atan2(((_t8) * (2.0 * ((this.x) * (this.z) + (this.y * this.w))) - (_t10 * 2.0 * ((this.y) * (this.z) - (this.x * this.w)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((this.x) * (this.y) - (_t1)))));
            d.z = Math.atan2(_t8, _t10);
        }
        d.y = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return d;
    }

    /** Private store group 0 of {@code getEulerAnglesZYX}: computes and stores it; reached only through it. */
    private void getEulerAnglesZYX_s19f1db93_c0_fma(Double3Impl _dst, double _t13, double _t15, double _t8, double _r2, double _r0, double _r3, double _r1, double _t10, double _t11, double _t1, double _t9) {
        _dst.x = _t13 <= _t15 ? 0.0 : Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(_r2, _r0, _r3 * _r1), _t10 * -2.0 * java.lang.Math.fma(_r3, _r0, -(_r2 * _r1))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(_r2, _r3, -_t1)));
        _dst.y = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        _dst.z = _t13 <= _t15 ? Math.atan2(2.0 * java.lang.Math.fma(_r0, _r1, -_r2 * _r3), _t11) : Math.atan2(_t8, _t10);
    }

    /** Private store group 0 of {@code getEulerAnglesZYX}: computes and stores it; reached only through it. */
    private void getEulerAnglesZYX_s19f1db93_c0_mulAdd(Double3Impl _dst, double _t13, double _t15, double _t8, double _r2, double _r0, double _r3, double _r1, double _t10, double _t11, double _t1, double _t9) {
        _dst.x = _t13 <= _t15 ? 0.0 : Math.atan2(((_t8) * (2.0 * ((_r2) * (_r0) + (_r3 * _r1))) - (_t10 * 2.0 * ((_r3) * (_r0) - (_r2 * _r1)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((_r2) * (_r3) - (_t1)))));
        _dst.y = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        _dst.z = _t13 <= _t15 ? Math.atan2(2.0 * ((_r0) * (_r1) - (_r2 * _r3)), _t11) : Math.atan2(_t8, _t10);
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
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.z;
        double _r1 = this.w;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = _r0 * _r0;
        double _t1 = _r0 * _r1;
        double _t8 = 2.0 * java.lang.Math.fma(_r2, _r3, _t1);
        double _t9 = 2.0 * java.lang.Math.fma(_r3, _r1, -_r2 * _r0);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_r3, _r3, _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        getEulerAnglesZYX_s19f1db93_c0_fma(d, _t13, epsilon * java.lang.Math.fma(_t9, _t9, _t13), _t8, _r2, _r0, _r3, _r1, _t10, java.lang.Math.fma(-2.0, java.lang.Math.fma(_r2, _r2, _t0), 1.0), _t1, _t9);
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.z;
        double _r1 = this.w;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = _r0 * _r0;
        double _t1 = _r0 * _r1;
        double _t8 = 2.0 * ((_r2) * (_r3) + (_t1));
        double _t9 = 2.0 * ((_r3) * (_r1) - (_r2 * _r0));
        double _t10 = ((1.0) - (2.0) * (((_r3) * (_r3) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        getEulerAnglesZYX_s19f1db93_c0_mulAdd(d, _t13, epsilon * ((_t9) * (_t9) + (_t13)), _t8, _r2, _r0, _r3, _r1, _t10, ((1.0) - (2.0) * (((_r2) * (_r2) + (_t0)))), _t1, _t9);
        return d;
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
        Double3Impl angularVelImpl = (Double3Impl) angularVel;
        return integrate(angularVelImpl.x, angularVelImpl.y, angularVelImpl.z, dt, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t9, this.w * _t15) + java.lang.Math.fma(_rd2, _t16, -(_rd1 * _t17));
        d.y = java.lang.Math.fma(_rd0, _t17, this.w * _t16) + java.lang.Math.fma(_rd1, _t9, -(_rd2 * _t15));
        d.z = java.lang.Math.fma(_rd1, _t15, _rd2 * _t9) + java.lang.Math.fma(this.w, _t17, -_rd0 * _t16);
        d.w = java.lang.Math.fma(this.w, _t9, -_rd0 * _t15) - java.lang.Math.fma(_rd1, _t16, _rd2 * _t17);
        return d;
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_mulAdd(double angularVelX, double angularVelY, double angularVelZ, double dt, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t9) + (this.w * _t15)) + ((_rd2) * (_t16) - (_rd1 * _t17));
        d.y = ((_rd0) * (_t17) + (this.w * _t16)) + ((_rd1) * (_t9) - (_rd2 * _t15));
        d.z = ((_rd1) * (_t15) + (_rd2 * _t9)) + ((this.w) * (_t17) - (_rd0 * _t16));
        d.w = ((this.w) * (_t9) - (_rd0 * _t15)) - ((_rd1) * (_t16) + (_rd2 * _t17));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code invNegativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code invNegativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code invNegativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0);
            d.y = -2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = -2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0));
            d.y = -2.0 * ((this.x) * (this.y) - (this.z * this.w));
            d.z = -2.0 * ((this.x) * (this.z) + (this.y * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0);
            d.z = -2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0));
            d.z = -2.0 * ((this.y) * (this.z) - (this.x * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = -2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * ((this.x) * (this.z) - (this.y * this.w));
            d.y = -2.0 * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((2.0) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
            d.y = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
            d.z = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
            d.z = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
            d.y = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code invPositiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code invPositiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code invPositiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
            return java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))));
        } else {
            return java.lang.Math.sqrt(((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y)))))));
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
            return java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        } else {
            return ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.log(java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.log(java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))));
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code negativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code negativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
    }

    /** {@code negativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0;
            d.y = -0.0;
            d.z = -0.0;
        }
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
                d.w = 0.0;
            }
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
                d.w = 0.0;
            }
            return d;
        }
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
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0);
            d.y = -2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = -2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0));
            d.y = -2.0 * ((this.x) * (this.y) + (this.z * this.w));
            d.z = -2.0 * ((this.x) * (this.z) - (this.y * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0);
            d.z = -2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0));
            d.z = -2.0 * ((this.x) * (this.w) + (this.y * this.z));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = -2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0 * ((this.x) * (this.z) + (this.y * this.w));
            d.y = -2.0 * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((2.0) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
            d.y = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
            d.z = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
            d.z = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
            return d;
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
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
            d.y = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code positiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code positiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
    }

    /** {@code positiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        d.w = Math.cosWithSin(_t26) * _t12;
        return d;
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat pow_mulAdd(double t, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        d.w = Math.cosWithSin(_t26) * _t12;
        return d;
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
    public DoubleQuat premul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -otherX * _rd2);
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -otherX * _rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherY) * (_rd2) - (otherZ * _rd1));
            d.y = ((otherY) * (this.w) + (otherZ * _rd0)) + ((otherW) * (_rd1) - (otherX * _rd2));
            d.z = ((otherX) * (_rd1) + (otherW * _rd2)) + ((otherZ) * (this.w) - (otherY * _rd0));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        DoubleQuatImpl targetImpl = (DoubleQuatImpl) target;
        double targetX = targetImpl.x;
        double targetY = targetImpl.y;
        double targetZ = targetImpl.z;
        double targetW = targetImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_fma(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY))) > 0.0) {
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
        double _t13 = _r0 + _t9;
        double _t14 = _r1 + _t10;
        double _t15 = _r2 + _t11;
        double _t16 = _r3 + _t12;
        double _t17 = _r0 - _t9;
        double _t18 = _r1 - _t10;
        double _t19 = _r2 - _t11;
        double _t20 = _r3 - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_fma((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_mulAdd(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t9, _t10, _t11, _t12;
        if (-((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY)))))) > 0.0) {
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
        double _t13 = _r0 + _t9;
        double _t14 = _r1 + _t10;
        double _t15 = _r2 + _t11;
        double _t16 = _r3 + _t12;
        double _t17 = _r0 - _t9;
        double _t18 = _r1 - _t10;
        double _t19 = _r2 - _t11;
        double _t20 = _r3 - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_mulAdd((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t40, 1.0 - _t40);
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t37, double _t40, double _t41) {
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(_r0, _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(_r1, _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(_r2, _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(_r3, _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(_r0, _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(_r1, _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(_r2, _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(_r3, _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        d.x = _t73 != 0.0 ? _t74 * _t68 : 0.0;
        d.y = _t73 != 0.0 ? _t74 * _t69 : 0.0;
        d.z = _t73 != 0.0 ? _t74 * _t67 : 0.0;
        d.w = _t73 != 0.0 ? _t74 * _t66 : 0.0;
        return d;
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t37, double _t40, double _t41) {
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((_r0) * (_t53) + (_t52 * _t9));
            _t67 = ((_r1) * (_t53) + (_t52 * _t10));
            _t68 = ((_r2) * (_t53) + (_t52 * _t11));
            _t69 = ((_r3) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((_r0) * (_t41) + (_t9 * _t40));
            _t67 = ((_r1) * (_t41) + (_t10 * _t40));
            _t68 = ((_r2) * (_t41) + (_t11 * _t40));
            _t69 = ((_r3) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        d.x = _t73 != 0.0 ? _t74 * _t68 : 0.0;
        d.y = _t73 != 0.0 ? _t74 * _t69 : 0.0;
        d.z = _t73 != 0.0 ? _t74 * _t67 : 0.0;
        d.w = _t73 != 0.0 ? _t74 * _t66 : 0.0;
        return d;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_sd504c7d6_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t15, dirX * _t15, dirY * _t15);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_lh_sd504c7d6_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t15, dirX * _t15, dirY * _t15);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _r0, double _r1, double _r2, double _r3, double _t0, double _t16, double _t17, double _t18) {
        double _t19 = -_t17;
        double _t21 = -_t16;
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        return lookAlong_lh_sd504c7d6_2_fma(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49))), 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))));
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _r0, double _r1, double _r2, double _r3, double _t0, double _t16, double _t17, double _t18) {
        double _t19 = -_t17;
        double _t21 = -_t16;
        double _t46 = _t36 * _t45;
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        return lookAlong_lh_sd504c7d6_2_mulAdd(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_2_fma(double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t45, double _r0, double _r1, double _r2, double _r3, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t83));
        double _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        d.x = java.lang.Math.fma(_r0, _t128, _r1 * _t125) + java.lang.Math.fma(_r2, _t126, -(_r3 * _t127));
        d.y = java.lang.Math.fma(_r2, _t128, _r3 * _t125) + java.lang.Math.fma(_r1, _t127, -_r0 * _t126);
        return lookAlong_lh_sd504c7d6_3_fma(d, _r0, _r1, _r2, _r3, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_2_mulAdd(double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t45, double _r0, double _r1, double _r2, double _r3, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t80));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t83));
        double _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        d.x = ((_r0) * (_t128) + (_r1 * _t125)) + ((_r2) * (_t126) - (_r3 * _t127));
        d.y = ((_r2) * (_t128) + (_r3 * _t125)) + ((_r1) * (_t127) - (_r0 * _t126));
        d.z = ((_r0) * (_t127) + (_r1 * _t126)) + ((_r3) * (_t128) - (_r2 * _t125));
        d.w = ((_r1) * (_t128) - (_r0 * _t125)) - ((_r2) * (_t127) + (_r3 * _t126));
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd504c7d6_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t125, double _t126, double _t127, double _t128) {
        d.z = java.lang.Math.fma(_r0, _t127, _r1 * _t126) + java.lang.Math.fma(_r3, _t128, -(_r2 * _t125));
        d.w = java.lang.Math.fma(_r1, _t128, -_r0 * _t125) - java.lang.Math.fma(_r2, _t127, _r3 * _t126);
        return d;
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
        return lookAlong_lh_degenerate_s8cf8056b_1_fma((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
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
        return lookAlong_lh_degenerate_s8cf8056b_1_mulAdd((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_1_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        return lookAlong_lh_degenerate_s8cf8056b_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_1_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        return lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_2_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97) {
        double _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_s8cf8056b_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97) {
        double _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_s8cf8056b_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp3, double _t103, double _t104, double _sp0, double _sp1, double _sp2, double _t115, double _t116) {
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
        d.x = java.lang.Math.fma(_r0, _t152, _r1 * _t149) + java.lang.Math.fma(_r2, _t150, -(_r3 * _t151));
        d.y = java.lang.Math.fma(_r2, _t152, _r3 * _t149) + java.lang.Math.fma(_r1, _t151, -_r0 * _t150);
        d.z = java.lang.Math.fma(_r0, _t151, _r1 * _t150) + java.lang.Math.fma(_r3, _t152, -(_r2 * _t149));
        d.w = java.lang.Math.fma(_r1, _t152, -_r0 * _t149) - java.lang.Math.fma(_r2, _t151, _r3 * _t150);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s8cf8056b_3_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp3, double _t103, double _t104, double _sp0, double _sp1, double _sp2, double _t115, double _t116) {
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
        d.x = ((_r0) * (_t152) + (_r1 * _t149)) + ((_r2) * (_t150) - (_r3 * _t151));
        d.y = ((_r2) * (_t152) + (_r3 * _t149)) + ((_r1) * (_t151) - (_r0 * _t150));
        d.z = ((_r0) * (_t151) + (_r1 * _t150)) + ((_r3) * (_t152) - (_r2 * _t149));
        d.w = ((_r1) * (_t152) - (_r0 * _t149)) - ((_r2) * (_t151) + (_r3 * _t150));
        return d;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_rh_s183ca1c4_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirX * _t16, dirZ * _t16, dirY * _t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_rh_s183ca1c4_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirX * _t16, dirZ * _t16, dirY * _t16);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t38, double _t39, double _t40, double _t47, double _r0, double _r1, double _r2, double _r3, double _t0, double _t17, double _t18, double _t19) {
        double _t20 = -_t18;
        double _t22 = -_t17;
        double _t23 = java.lang.Math.fma(dirZ, _t16, 1.0);
        double _t41 = -_t38;
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        return lookAlong_rh_s183ca1c4_2_fma(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0)))));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t38, double _t39, double _t40, double _t47, double _r0, double _r1, double _r2, double _r3, double _t0, double _t17, double _t18, double _t19) {
        double _t20 = -_t18;
        double _t22 = -_t17;
        double _t23 = ((dirZ) * (_t16) + (1.0));
        double _t41 = -_t38;
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        return lookAlong_rh_s183ca1c4_2_mulAdd(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0)))))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0)))))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_2_fma(DoubleQuatImpl d, double _t16, double _t38, double _t47, double _r0, double _r1, double _r2, double _r3, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t85));
        double _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        d.x = java.lang.Math.fma(_r0, _t127, _r1 * _t128) + java.lang.Math.fma(_r2, _t129, -(_r3 * _t126));
        d.y = java.lang.Math.fma(_r2, _t127, _r3 * _t128) + java.lang.Math.fma(_r1, _t126, -_r0 * _t129);
        return lookAlong_rh_s183ca1c4_3_fma(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_2_mulAdd(DoubleQuatImpl d, double _t16, double _t38, double _t47, double _r0, double _r1, double _r2, double _r3, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t79));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t85));
        double _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        d.x = ((_r0) * (_t127) + (_r1 * _t128)) + ((_r2) * (_t129) - (_r3 * _t126));
        d.y = ((_r2) * (_t127) + (_r3 * _t128)) + ((_r1) * (_t126) - (_r0 * _t129));
        d.z = ((_r0) * (_t126) + (_r1 * _t129)) + ((_r3) * (_t127) - (_r2 * _t128));
        d.w = ((_r1) * (_t127) - (_r0 * _t128)) - ((_r2) * (_t126) + (_r3 * _t129));
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s183ca1c4_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t126, double _t127, double _t128, double _t129) {
        d.z = java.lang.Math.fma(_r0, _t126, _r1 * _t129) + java.lang.Math.fma(_r3, _t127, -(_r2 * _t128));
        d.w = java.lang.Math.fma(_r1, _t127, -_r0 * _t128) - java.lang.Math.fma(_r2, _t126, _r3 * _t129);
        return d;
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
        return lookAlong_rh_degenerate_sf2324b45_1_fma((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
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
        return lookAlong_rh_degenerate_sf2324b45_1_mulAdd((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_1_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
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
        return lookAlong_rh_degenerate_sf2324b45_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_1_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
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
        return lookAlong_rh_degenerate_sf2324b45_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_2_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97) {
        double _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_sf2324b45_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_2_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97) {
        double _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_sf2324b45_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
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
        d.x = java.lang.Math.fma(_r0, _t150, _r1 * _t151) + java.lang.Math.fma(_r2, _t152, -(_r3 * _t149));
        d.y = java.lang.Math.fma(_r2, _t150, _r3 * _t151) + java.lang.Math.fma(_r1, _t149, -_r0 * _t152);
        d.z = java.lang.Math.fma(_r0, _t149, _r1 * _t152) + java.lang.Math.fma(_r3, _t150, -(_r2 * _t151));
        d.w = java.lang.Math.fma(_r1, _t150, -_r0 * _t151) - java.lang.Math.fma(_r2, _t149, _r3 * _t152);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_sf2324b45_3_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
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
        d.x = ((_r0) * (_t150) + (_r1 * _t151)) + ((_r2) * (_t152) - (_r3 * _t149));
        d.y = ((_r2) * (_t150) + (_r3 * _t151)) + ((_r1) * (_t149) - (_r0 * _t152));
        d.z = ((_r0) * (_t149) + (_r1 * _t152)) + ((_r3) * (_t150) - (_r2 * _t151));
        d.w = ((_r1) * (_t150) - (_r0 * _t151)) - ((_r2) * (_t149) + (_r3 * _t152));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dir, up, dest);
                default: return lookAlong_rh_fma(dir, up, dest);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dir, up, dest);
                default: return lookAlong_rh_mulAdd(dir, up, dest);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dir, up);
                default: return lookAlong_rh_fma(dir, up);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dir, up);
                default: return lookAlong_rh_mulAdd(dir, up);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
                default: return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
                default: return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ);
                default: return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
                default: return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
            }
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
    public DoubleQuat lookAlong(Double3R dir, Double3R up, @Mutated DoubleQuat dest) { return lookAlong(dir, up, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up) { return lookAlong(dir, up, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_fma(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_sd504c7d6_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t15, dirX * _t15, dirY * _t15);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_lh_sd504c7d6_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t15, dirX * _t15, dirY * _t15);
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
        return lookAlong_lh_degenerate_s84179920_1_fma(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
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
        return lookAlong_lh_degenerate_s84179920_1_mulAdd(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s84179920_1_fma(double epsilon, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        return lookAlong_lh_degenerate_s8cf8056b_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s84179920_1_mulAdd(double epsilon, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        return lookAlong_lh_degenerate_s8cf8056b_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_fma(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private DoubleQuat lookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return lookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_rh_s183ca1c4_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirX * _t16, dirZ * _t16, dirY * _t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        return lookAlong_rh_s183ca1c4_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirX * _t16, dirZ * _t16, dirY * _t16);
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
        return lookAlong_rh_degenerate_sdc8a7b3e_1_fma(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
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
        return lookAlong_rh_degenerate_sdc8a7b3e_1_mulAdd(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_1_fma(double epsilon, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
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
        return lookAlong_rh_degenerate_sf2324b45_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_sdc8a7b3e_1_mulAdd(double epsilon, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47) {
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
        return lookAlong_rh_degenerate_sf2324b45_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dir, up, epsilon, dest);
                default: return lookAlong_rh_fma(dir, up, epsilon, dest);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dir, up, epsilon, dest);
                default: return lookAlong_rh_mulAdd(dir, up, epsilon, dest);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dir, up, epsilon);
                default: return lookAlong_rh_fma(dir, up, epsilon);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dir, up, epsilon);
                default: return lookAlong_rh_mulAdd(dir, up, epsilon);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
                default: return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
                default: return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
                default: return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
                default: return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            }
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
    public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon, @Mutated DoubleQuat dest) { return lookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public DoubleQuat lookAlong(Double3R dir, Double3R up, double epsilon) { return lookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, @Mutated DoubleQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED); }

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
        Double3Impl axisImpl = (Double3Impl) axis;
        double axisX = axisImpl.x;
        double axisY = axisImpl.y;
        double axisZ = axisImpl.z;
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        this.x = axisX * _t1;
        this.y = axisY * _t1;
        this.z = axisZ * _t1;
        this.w = Math.cosWithSin(_t0);
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        this.x = axisX * _t1;
        this.y = axisY * _t1;
        this.z = axisZ * _t1;
        this.w = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s5c9915aa_1_fma(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t62 = java.lang.Math.fma(_t16, _t46, -(_t17 * _t47));
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return makeRotationLookAlong_lh_s5c9915aa_2(d, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), _t62, java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), Math.max(_t62, _t16), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t62 = ((_t16) * (_t46) - (_t17 * _t47));
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return makeRotationLookAlong_lh_s5c9915aa_2(d, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), _t62, ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), Math.max(_t62, _t16), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), ((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_lh_s5c9915aa_2(DoubleQuatImpl d, double _t16, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t70, double _t73, double _t74, double _t76, double _t77, double _sp1, double _t79, double _t80, double _t81, double _sp3, double _sp4) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t81));
        d.x = _t76 > 0.0 ? _sp1 * _t69 : _t46 > _t70 ? 0.5 * java.lang.Math.sqrt(_t79) : _t62 > _t16 ? _sp2 * _t73 : _sp3 * _t50;
        d.y = _t76 > 0.0 ? _sp1 * _t55 : _t46 > _t70 ? _sp4 * _t73 : _t62 > _t16 ? 0.5 * java.lang.Math.sqrt(_t81) : _sp3 * _t68;
        d.z = _t76 > 0.0 ? _sp1 * _t74 : _t46 > _t70 ? _sp4 * _t50 : _t62 > _t16 ? _sp2 * _t68 : 0.5 * java.lang.Math.sqrt(_t80);
        d.w = _t76 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t77) : _t46 > _t70 ? _sp4 * _t69 : _t62 > _t16 ? _sp2 * _t55 : _sp3 * _t74;
        return d;
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
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
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
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        double _t89 = java.lang.Math.fma(_t70, _t24, -(_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t89, java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), Math.max(_t89, _t24), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        double _t89 = ((_t70) * (_t24) - (_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t89, ((_t69) * (_t26) - (_t73 * _t24)), Math.max(_t89, _t24), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(DoubleQuatImpl d, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t94, double _t96, double _t97, double _t98, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t100));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        d.x = _t98 > 0.0 ? _sp0 * _t96 : _t70 > _t94 ? 0.5 * java.lang.Math.sqrt(_t100) : _t89 > _t24 ? _sp1 * _t107 : _sp2 * _t74;
        d.y = _t98 > 0.0 ? _sp0 * _t78 : _t70 > _t94 ? _sp3 * _t107 : _t89 > _t24 ? 0.5 * java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        return makeRotationLookAlong_lh_degenerate_s4577925b_3(d, _t24, _t70, _t74, _t78, _t89, _t94, _t96, _t97, _t98, _t99, _sp0, _t103, _sp3, _sp1, _sp2, java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(DoubleQuatImpl d, double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t94, double _t96, double _t97, double _t98, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t100));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = ((_t67) * (_t65) + (_t93));
        d.x = _t98 > 0.0 ? _sp0 * _t96 : _t70 > _t94 ? 0.5 * java.lang.Math.sqrt(_t100) : _t89 > _t24 ? _sp1 * _t107 : _sp2 * _t74;
        d.y = _t98 > 0.0 ? _sp0 * _t78 : _t70 > _t94 ? _sp3 * _t107 : _t89 > _t24 ? 0.5 * java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        return makeRotationLookAlong_lh_degenerate_s4577925b_3(d, _t24, _t70, _t74, _t78, _t89, _t94, _t96, _t97, _t98, _t99, _sp0, _t103, _sp3, _sp1, _sp2, ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_3(DoubleQuatImpl d, double _t24, double _t70, double _t74, double _t78, double _t89, double _t94, double _t96, double _t97, double _t98, double _t99, double _sp0, double _t103, double _sp3, double _sp1, double _sp2, double _t108) {
        d.z = _t98 > 0.0 ? _sp0 * _t108 : _t70 > _t94 ? _sp3 * _t74 : _t89 > _t24 ? _sp1 * _t97 : 0.5 * java.lang.Math.sqrt(_t103);
        d.w = _t98 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t99) : _t70 > _t94 ? _sp3 * _t96 : _t89 > _t24 ? _sp1 * _t78 : _sp2 * _t108;
        return d;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3R dir, Double3R up) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_fma(dirX, dirY, this, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(dirX, dirY, this, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_fma(double dirX, double dirY, DoubleQuatImpl d, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t49));
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        return makeRotationLookAlong_rh_sf0b2d43c_2(d, _t21, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), _t64, java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, Math.max(_t64, _t21), java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0)))));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(double dirX, double dirY, DoubleQuatImpl d, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t64 = ((_t17) * (_t48) - (_t18 * _t49));
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0))))))));
        double _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        return makeRotationLookAlong_rh_sf0b2d43c_2(d, _t21, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), _t64, ((_t65) - (dirY) * (_t16)), -_t19 - _t65, Math.max(_t64, _t21), ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), ((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0)))))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_2(DoubleQuatImpl d, double _t21, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t72, double _t74, double _t75, double _t77, double _t78, double _t79, double _t80, double _sp4, double _sp1, double _sp2, double _t84) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t84));
        d.x = _t77 > 0.0 ? _sp1 * _t71 : _t49 > _t72 ? 0.5 * java.lang.Math.sqrt(_t78) : _t64 > _t21 ? _sp2 * _t74 : _sp3 * _t56;
        d.y = _t77 > 0.0 ? _sp1 * _t52 : _t49 > _t72 ? _sp4 * _t74 : _t64 > _t21 ? 0.5 * java.lang.Math.sqrt(_t80) : _sp3 * _t70;
        d.z = _t77 > 0.0 ? _sp1 * _t75 : _t49 > _t72 ? _sp4 * _t56 : _t64 > _t21 ? _sp2 * _t70 : 0.5 * java.lang.Math.sqrt(_t84);
        d.w = _t77 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t79) : _t49 > _t72 ? _sp4 * _t71 : _t64 > _t21 ? _sp2 * _t52 : _sp3 * _t75;
        return d;
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
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
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
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        double _t88 = java.lang.Math.fma(_t69, _t25, -(_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t88, java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), Math.max(_t88, _t30), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        double _t88 = ((_t69) * (_t25) - (_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t88, ((_t73) * (_t24) - (_t69 * _t26)), Math.max(_t88, _t30), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(DoubleQuatImpl d, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t94, double _t96, double _t97) {
        double _t98 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30)));
        double _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        d.x = _t98 > 0.0 ? _sp0 * _t96 : _t70 > _t94 ? 0.5 * java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(DoubleQuatImpl d, double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t94, double _t96, double _t97) {
        double _t98 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30))))));
        double _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = ((_t67) * (_t65) + (_t93));
        d.x = _t98 > 0.0 ? _sp0 * _t96 : _t70 > _t94 ? 0.5 * java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_3(DoubleQuatImpl d, double _t30, double _t70, double _t74, double _t76, double _t88, double _t94, double _t96, double _t97, double _t98, double _t99, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        d.y = _t98 > 0.0 ? _sp0 * _t74 : _t70 > _t94 ? _sp3 * _t107 : _t88 > _t30 ? 0.5 * java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        d.z = _t98 > 0.0 ? _sp0 * _t108 : _t70 > _t94 ? _sp3 * _t76 : _t88 > _t30 ? _sp1 * _t97 : 0.5 * java.lang.Math.sqrt(_t103);
        d.w = _t98 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t99) : _t70 > _t94 ? _sp3 * _t96 : _t88 > _t30 ? _sp1 * _t74 : _sp2 * _t108;
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_fma(dir, up);
                default: return makeRotationLookAlong_rh_fma(dir, up);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_mulAdd(dir, up);
                default: return makeRotationLookAlong_rh_mulAdd(dir, up);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ);
                default: return makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
                default: return makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
            }
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
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up) { return makeRotationLookAlong(dir, up, Handedness.RIGHT_HANDED); }

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
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return makeRotationLookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_lh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_lh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s4c0dc561_1_fma(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46) {
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t62 = java.lang.Math.fma(_t16, _t46, -(_t17 * _t47));
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return makeRotationLookAlong_lh_s5c9915aa_2(d, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), _t62, java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), Math.max(_t62, _t16), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46) {
        double _t47 = _t37 * _t45;
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t62 = ((_t16) * (_t46) - (_t17 * _t47));
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return makeRotationLookAlong_lh_s5c9915aa_2(d, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), _t62, ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), Math.max(_t62, _t16), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), ((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)));
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
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
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
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(double epsilon, DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        double _t89 = java.lang.Math.fma(_t70, _t24, -(_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t89, java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), Math.max(_t89, _t24), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(double epsilon, DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        double _t89 = ((_t70) * (_t24) - (_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t89, ((_t69) * (_t26) - (_t73 * _t24)), Math.max(_t89, _t24), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_rh_fma(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3R dir, Double3R up, double epsilon) {
        Double3Impl dirImpl = (Double3Impl) dir;
        Double3Impl upImpl = (Double3Impl) up;
        return makeRotationLookAlong_rh_mulAdd(dirImpl.x, dirImpl.y, dirImpl.z, upImpl.x, upImpl.y, upImpl.z, epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_fma(dirX, dirY, this, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(dirX, dirY, this, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38);
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
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
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
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(double epsilon, DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        double _t88 = java.lang.Math.fma(_t69, _t25, -(_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t88, java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), Math.max(_t88, _t30), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(double epsilon, DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
        double _t88 = ((_t69) * (_t25) - (_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t88, ((_t73) * (_t24) - (_t69 * _t26)), Math.max(_t88, _t30), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_fma(dir, up, epsilon);
                default: return makeRotationLookAlong_rh_fma(dir, up, epsilon);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_mulAdd(dir, up, epsilon);
                default: return makeRotationLookAlong_rh_mulAdd(dir, up, epsilon);
            }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
                default: return makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            }
        } else {
            switch (handedness) {
                case LEFT_HANDED: return makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
                default: return makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            }
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
    @Mutated public DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up, double epsilon) { return makeRotationLookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED); }

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
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return makeRotationLookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED); }

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
        Double3Impl fromDirImpl = (Double3Impl) fromDir;
        Double3Impl toDirImpl = (Double3Impl) toDir;
        double fromDirX = fromDirImpl.x;
        double fromDirY = fromDirImpl.y;
        double fromDirZ = fromDirImpl.z;
        double toDirX = toDirImpl.x;
        double toDirY = toDirImpl.y;
        double toDirZ = toDirImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
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
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s6ca4b61d_c0(this, 0.5 * _t23, _t15, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** Private store group 0 of {@code makeRotationTo}: computes and stores it; reached only through it. */
    private void makeRotationTo_s6ca4b61d_c0(DoubleQuatImpl _dst, double _t24, double _t15, double _t32, double _t29, double _t30, double _t13, double _t17, double _t19, double _t16, double _t18) {
        _dst.x = _t24 > 1.0E-13 ? _t15 * _t32 : _t29 != 0.0 ? _t30 * _t13 : 0.0;
        _dst.y = _t24 > 1.0E-13 ? _t17 * _t32 : _t29 != 0.0 ? _t30 * _t19 : 0.0;
        _dst.z = _t24 > 1.0E-13 ? _t16 * _t32 : _t29 != 0.0 ? _t30 * _t18 : 0.0;
        _dst.w = _t24 > 1.0E-13 ? _t24 * _t32 : 0.0;
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
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
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
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s6ca4b61d_c0(this, 0.5 * _t23, _t15, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
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
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        makeRotationTo_s6ca4b61d_c0(this, 0.5 * _t23, _t15, (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
        Double3Impl fromDirImpl = (Double3Impl) fromDir;
        Double3Impl toDirImpl = (Double3Impl) toDir;
        double fromDirX = fromDirImpl.x;
        double fromDirY = fromDirImpl.y;
        double fromDirZ = fromDirImpl.z;
        double toDirX = toDirImpl.x;
        double toDirY = toDirImpl.y;
        double toDirZ = toDirImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
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
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s640110ac_c0(this, 0.5 * _t23, epsilon, _t15, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** Private store group 0 of {@code makeRotationTo}: computes and stores it; reached only through it. */
    private void makeRotationTo_s640110ac_c0(DoubleQuatImpl _dst, double _t24, double epsilon, double _t15, double _t32, double _t29, double _t30, double _t13, double _t17, double _t19, double _t16, double _t18) {
        _dst.x = _t24 > epsilon ? _t15 * _t32 : _t29 != 0.0 ? _t30 * _t13 : 0.0;
        _dst.y = _t24 > epsilon ? _t17 * _t32 : _t29 != 0.0 ? _t30 * _t19 : 0.0;
        _dst.z = _t24 > epsilon ? _t16 * _t32 : _t29 != 0.0 ? _t30 * _t18 : 0.0;
        _dst.w = _t24 > epsilon ? _t24 * _t32 : 0.0;
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
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
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
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s640110ac_c0(this, 0.5 * _t23, epsilon, _t15, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
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
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        makeRotationTo_s640110ac_c0(this, 0.5 * _t23, epsilon, _t15, (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
        double _t0 = 0.5 * angle;
        this.x = Math.sinWithCos(_t0);
        this.y = 0.0;
        this.z = 0.0;
        this.w = Math.cosWithSin(_t0);
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
        this.x = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        this.y = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        this.z = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        this.w = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
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
        this.x = ((_t9) * (_t5) + (_t10 * _t8));
        this.y = ((_t10) * (_t5) - (_t9 * _t8));
        this.z = ((_t11) * (_t5) + (_t12 * _t8));
        this.w = ((_t12) * (_t5) - (_t11 * _t8));
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
        this.x = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        this.y = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        this.z = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        this.w = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
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
        this.x = ((_t9) * (_t5) - (_t10 * _t8));
        this.y = ((_t11) * (_t8) - (_t12 * _t5));
        this.z = ((_t9) * (_t8) + (_t10 * _t5));
        this.w = ((_t12) * (_t8) + (_t11 * _t5));
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
        double _t0 = 0.5 * angle;
        this.x = 0.0;
        this.y = Math.sinWithCos(_t0);
        this.z = 0.0;
        this.w = Math.cosWithSin(_t0);
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
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
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
        this.x = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        this.y = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        this.z = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        this.w = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
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
        this.x = ((_t9) * (_t5) + (_t10 * _t8));
        this.y = ((_t10) * (_t5) - (_t9 * _t8));
        this.z = ((_t11) * (_t8) - (_t12 * _t5));
        this.w = ((_t12) * (_t8) + (_t11 * _t5));
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
        this.x = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        this.y = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        this.z = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        this.w = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
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
        this.x = ((_t9) * (_t5) + (_t10 * _t8));
        this.y = ((_t11) * (_t5) + (_t12 * _t8));
        this.z = ((_t12) * (_t5) - (_t11 * _t8));
        this.w = ((_t10) * (_t5) - (_t9 * _t8));
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
        double _t0 = 0.5 * angle;
        this.x = 0.0;
        this.y = 0.0;
        this.z = Math.sinWithCos(_t0);
        this.w = Math.cosWithSin(_t0);
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
        this.x = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        this.y = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        this.z = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        this.w = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
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
        this.x = ((_t9) * (_t5) - (_t10 * _t8));
        this.y = ((_t11) * (_t5) + (_t12 * _t8));
        this.z = ((_t9) * (_t8) + (_t10 * _t5));
        this.w = ((_t12) * (_t5) - (_t11 * _t8));
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
        this.x = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        this.y = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        this.z = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        this.w = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
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
        this.x = ((_t9) * (_t5) - (_t10 * _t8));
        this.y = ((_t11) * (_t8) + (_t12 * _t5));
        this.z = ((_t12) * (_t8) - (_t11 * _t5));
        this.w = ((_t10) * (_t5) + (_t9 * _t8));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t1, this.w * _t2);
            d.y = java.lang.Math.fma(_rd1, _t1, -(this.z * _t2));
            d.z = java.lang.Math.fma(_rd1, _t2, this.z * _t1);
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd0 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t1) + (this.w * _t2));
            d.y = ((_rd1) * (_t1) - (this.z * _t2));
            d.z = ((_rd1) * (_t2) + (this.z * _t1));
            d.w = ((this.w) * (_t1) - (_rd0 * _t2));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t1, this.z * _t2);
            d.y = java.lang.Math.fma(_rd1, _t1, this.w * _t2);
            d.z = java.lang.Math.fma(this.z, _t1, -(_rd0 * _t2));
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd1 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t1) + (this.z * _t2));
            d.y = ((_rd1) * (_t1) + (this.w * _t2));
            d.z = ((this.z) * (_t1) - (_rd0 * _t2));
            d.w = ((this.w) * (_t1) - (_rd1 * _t2));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t1, -(this.y * _t2));
            d.y = java.lang.Math.fma(_rd0, _t2, this.y * _t1);
            d.z = java.lang.Math.fma(_rd1, _t1, this.w * _t2);
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd1 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = ((_rd0) * (_t1) - (this.y * _t2));
            d.y = ((_rd0) * (_t2) + (this.y * _t1));
            d.z = ((_rd1) * (_t1) + (this.w * _t2));
            d.w = ((this.w) * (_t1) - (_rd1 * _t2));
            return d;
        }
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
        Double3Impl axisImpl = (Double3Impl) axis;
        double axisX = axisImpl.x;
        double axisY = axisImpl.y;
        double axisZ = axisImpl.z;
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t1, this.w * _t3) + java.lang.Math.fma(_rd1, _t4, -(_rd2 * _t5));
        d.y = java.lang.Math.fma(_rd1, _t1, _rd2 * _t3) + java.lang.Math.fma(this.w, _t5, -_rd0 * _t4);
        d.z = java.lang.Math.fma(_rd0, _t5, this.w * _t4) + java.lang.Math.fma(_rd2, _t1, -(_rd1 * _t3));
        d.w = java.lang.Math.fma(this.w, _t1, -_rd0 * _t3) - java.lang.Math.fma(_rd1, _t5, _rd2 * _t4);
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t1) + (this.w * _t3)) + ((_rd1) * (_t4) - (_rd2 * _t5));
        d.y = ((_rd1) * (_t1) + (_rd2 * _t3)) + ((this.w) * (_t5) - (_rd0 * _t4));
        d.z = ((_rd0) * (_t5) + (this.w * _t4)) + ((_rd2) * (_t1) - (_rd1 * _t3));
        d.w = ((this.w) * (_t1) - (_rd0 * _t3)) - ((_rd1) * (_t5) + (_rd2 * _t4));
        return d;
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
        Double3Impl fromDirImpl = (Double3Impl) fromDir;
        Double3Impl toDirImpl = (Double3Impl) toDir;
        double fromDirX = fromDirImpl.x;
        double fromDirY = fromDirImpl.y;
        double fromDirZ = fromDirImpl.z;
        double toDirX = toDirImpl.x;
        double toDirY = toDirImpl.y;
        double toDirZ = toDirImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s203b189_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by 2 identical
     * private paths of {@code rotateTo}; reached only through it.
     */
    private void rotateTo_s203b189_c0_fma(DoubleQuatImpl _dst, double _r0, double _t44, double _r1, double _t45, double _r2, double _t46, double _r3, double _t47) {
        _dst.x = java.lang.Math.fma(_r0, _t44, _r1 * _t45) + java.lang.Math.fma(_r2, _t46, -(_r3 * _t47));
        _dst.y = java.lang.Math.fma(_r2, _t44, _r3 * _t45) + java.lang.Math.fma(_r1, _t47, -_r0 * _t46);
        _dst.z = java.lang.Math.fma(_r0, _t47, _r1 * _t46) + java.lang.Math.fma(_r3, _t44, -(_r2 * _t45));
        _dst.w = java.lang.Math.fma(_r1, _t44, -_r0 * _t45) - java.lang.Math.fma(_r2, _t47, _r3 * _t46);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s203b189_tail_fma(DoubleQuatImpl _dst, double _t23, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t13, double _t18, double _t19, double _r0, double _r1, double _r2, double _r3) {
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
        rotateTo_s203b189_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s203b189_tail_mulAdd(DoubleQuatImpl _dst, double _t23, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t13, double _t18, double _t19, double _r0, double _r1, double _r2, double _r3) {
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
        _dst.x = ((_r0) * (_t44) + (_r1 * _t45)) + ((_r2) * (_t46) - (_r3 * _t47));
        _dst.y = ((_r2) * (_t44) + (_r3 * _t45)) + ((_r1) * (_t47) - (_r0 * _t46));
        _dst.z = ((_r0) * (_t47) + (_r1 * _t46)) + ((_r3) * (_t44) - (_r2 * _t45));
        _dst.w = ((_r1) * (_t44) - (_r0 * _t45)) - ((_r2) * (_t47) + (_r3 * _t46));
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s203b189_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s203b189_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
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
        Double3Impl fromDirImpl = (Double3Impl) fromDir;
        Double3Impl toDirImpl = (Double3Impl) toDir;
        double fromDirX = fromDirImpl.x;
        double fromDirY = fromDirImpl.y;
        double fromDirZ = fromDirImpl.z;
        double toDirX = toDirImpl.x;
        double toDirY = toDirImpl.y;
        double toDirZ = toDirImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s428be758_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, epsilon, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s428be758_tail_fma(DoubleQuatImpl _dst, double _t23, double _t15, double _t16, double _t17, double _t24, double epsilon, double _t29, double _t30, double _t13, double _t18, double _t19, double _r0, double _r1, double _r2, double _r3) {
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
        rotateTo_s203b189_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s428be758_tail_mulAdd(DoubleQuatImpl _dst, double _t23, double _t15, double _t16, double _t17, double _t24, double epsilon, double _t29, double _t30, double _t13, double _t18, double _t19, double _r0, double _r1, double _r2, double _r3) {
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
        _dst.x = ((_r0) * (_t44) + (_r1 * _t45)) + ((_r2) * (_t46) - (_r3 * _t47));
        _dst.y = ((_r2) * (_t44) + (_r3 * _t45)) + ((_r1) * (_t47) - (_r0 * _t46));
        _dst.z = ((_r0) * (_t47) + (_r1 * _t46)) + ((_r3) * (_t44) - (_r2 * _t45));
        _dst.w = ((_r1) * (_t44) - (_r0 * _t45)) - ((_r2) * (_t47) + (_r3 * _t46));
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s428be758_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, epsilon, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s428be758_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5 * _t23, epsilon, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t1, this.w * _t2);
            d.y = java.lang.Math.fma(_rd1, _t1, this.z * _t2);
            d.z = java.lang.Math.fma(this.z, _t1, -(_rd1 * _t2));
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd0 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t1) + (this.w * _t2));
            d.y = ((_rd1) * (_t1) + (this.z * _t2));
            d.z = ((this.z) * (_t1) - (_rd1 * _t2));
            d.w = ((this.w) * (_t1) - (_rd0 * _t2));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXYZ_fma(double angleX, double angleY, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t21, this.w * _t19) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        d.y = java.lang.Math.fma(_rd1, _t21, _rd2 * _t19) + java.lang.Math.fma(this.w, _t22, -_rd0 * _t20);
        d.z = java.lang.Math.fma(_rd0, _t22, this.w * _t20) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t19));
        d.w = java.lang.Math.fma(this.w, _t21, -_rd0 * _t19) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        return d;
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXYZ_mulAdd(double angleX, double angleY, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t21) + (this.w * _t19)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        d.y = ((_rd1) * (_t21) + (_rd2 * _t19)) + ((this.w) * (_t22) - (_rd0 * _t20));
        d.z = ((_rd0) * (_t22) + (this.w * _t20)) + ((_rd2) * (_t21) - (_rd1 * _t19));
        d.w = ((this.w) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXZY_fma(double angleX, double angleZ, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t19, this.w * _t21) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        d.y = java.lang.Math.fma(_rd1, _t19, _rd2 * _t21) + java.lang.Math.fma(this.w, _t22, -_rd0 * _t20);
        d.z = java.lang.Math.fma(_rd0, _t22, this.w * _t20) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t21));
        d.w = java.lang.Math.fma(this.w, _t19, -_rd0 * _t21) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        return d;
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXZY_mulAdd(double angleX, double angleZ, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t19) + (this.w * _t21)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        d.y = ((_rd1) * (_t19) + (_rd2 * _t21)) + ((this.w) * (_t22) - (_rd0 * _t20));
        d.z = ((_rd0) * (_t22) + (this.w * _t20)) + ((_rd2) * (_t19) - (_rd1 * _t21));
        d.w = ((this.w) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t1, -(this.z * _t2));
            d.y = java.lang.Math.fma(_rd1, _t1, this.w * _t2);
            d.z = java.lang.Math.fma(_rd0, _t2, this.z * _t1);
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd1 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t1) - (this.z * _t2));
            d.y = ((_rd1) * (_t1) + (this.w * _t2));
            d.z = ((_rd0) * (_t2) + (this.z * _t1));
            d.w = ((this.w) * (_t1) - (_rd1 * _t2));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYXZ_fma(double angleY, double angleX, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t19, this.w * _t20) + java.lang.Math.fma(_rd1, _t21, -(_rd2 * _t22));
        d.y = java.lang.Math.fma(_rd1, _t19, _rd2 * _t20) + java.lang.Math.fma(this.w, _t22, -_rd0 * _t21);
        d.z = java.lang.Math.fma(_rd0, _t22, this.w * _t21) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t20));
        d.w = java.lang.Math.fma(this.w, _t19, -_rd0 * _t20) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t21);
        return d;
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYXZ_mulAdd(double angleY, double angleX, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t19) + (this.w * _t20)) + ((_rd1) * (_t21) - (_rd2 * _t22));
        d.y = ((_rd1) * (_t19) + (_rd2 * _t20)) + ((this.w) * (_t22) - (_rd0 * _t21));
        d.z = ((_rd0) * (_t22) + (this.w * _t21)) + ((_rd2) * (_t19) - (_rd1 * _t20));
        d.w = ((this.w) * (_t19) - (_rd0 * _t20)) - ((_rd1) * (_t22) + (_rd2 * _t21));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYZX_fma(double angleY, double angleZ, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t21, this.w * _t19) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        d.y = java.lang.Math.fma(_rd1, _t21, _rd2 * _t19) + java.lang.Math.fma(this.w, _t20, -_rd0 * _t22);
        d.z = java.lang.Math.fma(_rd0, _t20, this.w * _t22) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t19));
        d.w = java.lang.Math.fma(this.w, _t21, -_rd0 * _t19) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        return d;
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYZX_mulAdd(double angleY, double angleZ, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t21) + (this.w * _t19)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        d.y = ((_rd1) * (_t21) + (_rd2 * _t19)) + ((this.w) * (_t20) - (_rd0 * _t22));
        d.z = ((_rd0) * (_t20) + (this.w * _t22)) + ((_rd2) * (_t21) - (_rd1 * _t19));
        d.w = ((this.w) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t1, this.y * _t2);
            d.y = java.lang.Math.fma(this.y, _t1, -(_rd0 * _t2));
            d.z = java.lang.Math.fma(_rd1, _t1, this.w * _t2);
            d.w = java.lang.Math.fma(this.w, _t1, -(_rd1 * _t2));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = ((_rd0) * (_t1) + (this.y * _t2));
            d.y = ((this.y) * (_t1) - (_rd0 * _t2));
            d.z = ((_rd1) * (_t1) + (this.w * _t2));
            d.w = ((this.w) * (_t1) - (_rd1 * _t2));
            return d;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZXY_fma(double angleZ, double angleX, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t21, this.w * _t22) + java.lang.Math.fma(_rd1, _t19, -(_rd2 * _t20));
        d.y = java.lang.Math.fma(_rd1, _t21, _rd2 * _t22) + java.lang.Math.fma(this.w, _t20, -_rd0 * _t19);
        d.z = java.lang.Math.fma(_rd0, _t20, this.w * _t19) + java.lang.Math.fma(_rd2, _t21, -(_rd1 * _t22));
        d.w = java.lang.Math.fma(this.w, _t21, -_rd0 * _t22) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t19);
        return d;
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZXY_mulAdd(double angleZ, double angleX, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t21) + (this.w * _t22)) + ((_rd1) * (_t19) - (_rd2 * _t20));
        d.y = ((_rd1) * (_t21) + (_rd2 * _t22)) + ((this.w) * (_t20) - (_rd0 * _t19));
        d.z = ((_rd0) * (_t20) + (this.w * _t19)) + ((_rd2) * (_t21) - (_rd1 * _t22));
        d.w = ((this.w) * (_t21) - (_rd0 * _t22)) - ((_rd1) * (_t20) + (_rd2 * _t19));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZYX_fma(double angleZ, double angleY, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t19, this.w * _t21) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        d.y = java.lang.Math.fma(_rd1, _t19, _rd2 * _t21) + java.lang.Math.fma(this.w, _t20, -_rd0 * _t22);
        d.z = java.lang.Math.fma(_rd0, _t20, this.w * _t22) + java.lang.Math.fma(_rd2, _t19, -(_rd1 * _t21));
        d.w = java.lang.Math.fma(this.w, _t19, -_rd0 * _t21) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        return d;
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZYX_mulAdd(double angleZ, double angleY, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
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
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = ((_rd0) * (_t19) + (this.w * _t21)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        d.y = ((_rd1) * (_t19) + (_rd2 * _t21)) + ((this.w) * (_t20) - (_rd0 * _t22));
        d.z = ((_rd0) * (_t20) + (this.w * _t22)) + ((_rd2) * (_t19) - (_rd1 * _t21));
        d.w = ((this.w) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        return d;
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
        Double3Impl vImpl = (Double3Impl) v;
        double vX = vImpl.x;
        double vY = vImpl.y;
        double vZ = vImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        d.x = java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY)));
        d.z = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        d.x = java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY)));
        d.z = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ)));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (vY) - (this.y * vX));
        double _t10 = 2.0 * ((this.z) * (vX) - (this.x * vZ));
        double _t11 = 2.0 * ((this.y) * (vZ) - (this.z * vY));
        d.x = ((this.y) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.z) * (_t10))));
        d.y = ((this.z) * (_t11) + (((((this.w) * (_t10) + (vY))) - (this.x) * (_t9))));
        d.z = ((this.x) * (_t10) + (((((this.w) * (_t9) + (vZ))) - (this.y) * (_t11))));
        return d;
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
        Double3Impl vImpl = (Double3Impl) v;
        double vX = vImpl.x;
        double vY = vImpl.y;
        double vZ = vImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        d.x = java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY)));
        d.z = java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        d.x = java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY)));
        d.z = java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ)));
        return d;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * ((this.x) * (vZ) - (this.z * vX));
        double _t10 = 2.0 * ((this.y) * (vX) - (this.x * vY));
        double _t11 = 2.0 * ((this.z) * (vY) - (this.y * vZ));
        d.x = ((this.z) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.y) * (_t10))));
        d.y = ((this.x) * (_t10) + (((((this.w) * (_t9) + (vY))) - (this.z) * (_t11))));
        d.z = ((this.y) * (_t11) + (((((this.w) * (_t10) + (vZ))) - (this.x) * (_t9))));
        return d;
    }

    public double x() { return this.x; }
    public double y() { return this.y; }
    public double z() { return this.z; }
    public double w() { return this.w; }

    @Override public String toString() {
        return "DoubleQuat(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleQuatImpl)) return false;
        DoubleQuatImpl o = (DoubleQuatImpl) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z)
            && Double.doubleToLongBits(w) == Double.doubleToLongBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(w) ^ (Double.doubleToLongBits(w) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z)
            && Double.isFinite(w);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z)
            || Double.isNaN(w);
    }

    @Override public boolean equalsEpsilon(DoubleQuatR other, double epsilon) {
        DoubleQuatImpl otherImpl = (DoubleQuatImpl) other;
        return java.lang.Math.abs(x - otherImpl.x) <= epsilon
            && java.lang.Math.abs(y - otherImpl.y) <= epsilon
            && java.lang.Math.abs(z - otherImpl.z) <= epsilon
            && java.lang.Math.abs(w - otherImpl.w) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    @Mutated public DoubleQuat load(double[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }

    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public DoubleQuat load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleQuat loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
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

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        dest[offset + 2] = (float) this.z;
        dest[offset + 3] = (float) this.w;
        return dest;
    }

    @Mutated public DoubleQuat load(float[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
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

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
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
