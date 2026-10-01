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
        static final DoubleQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleQuatBbOpsUnsafe()
                        : new DoubleQuatBbOpsApi();
        static final DoubleQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        this.x = src.x();
        this.y = src.y();
        this.z = src.z();
        this.w = src.w();
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
        if (Math.useFma()) {
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
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat invertProduct(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = Math.fma(otherX, this.w, otherW * this.x) + Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = Math.fma(otherW, this.w, -(otherX * this.x)) - Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = Math.fma(otherY, this.x, otherZ * this.w) + Math.fma(otherW, this.z, -(otherX * this.y));
        double _t23 = Math.fma(otherX, this.z, otherW * this.y) + Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = Math.fma(otherX, this.w, otherW * this.x) + Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = Math.fma(otherW, this.w, -(otherX * this.x)) - Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = Math.fma(otherY, this.x, otherZ * this.w) + Math.fma(otherW, this.z, -(otherX * this.y));
        double _t23 = Math.fma(otherX, this.z, otherW * this.y) + Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
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
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = other.x() + this.x;
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
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.x - other.x();
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
    @Mutated public DoubleQuat makeUniformRotation(double u1, double u2, double u3) {
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u1);
        double _t6 = Math.sin(_t3);
        this.x = _t4 * _t5;
        this.y = Math.cosFromSin(_t4, _t1) * _t5;
        this.z = _t6 * _t0;
        this.w = Math.cosFromSin(_t6, _t3) * _t0;
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
    public @Mutated DoubleQuat set(DoubleQuatR v) {
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        this.x = v.x();
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
    public @Mutated DoubleQuat makeFromDualQuat(DoubleDualQuatR dq) {
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        this.x = dq.rX();
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
        double _r0 = m.m00();
        double _r1 = m.m11();
        double _r2 = m.m21();
        double _r3 = m.m12();
        double _r4 = m.m22();
        double _r5 = m.m01();
        double _r6 = m.m10();
        double _r7 = m.m02();
        double _r8 = m.m20();
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, java.lang.Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
    @Mutated public DoubleQuat makeFromMatrix(Double3x4R m) {
        double _r0 = m.m00();
        double _r1 = m.m11();
        double _r2 = m.m21();
        double _r3 = m.m12();
        double _r4 = m.m22();
        double _r5 = m.m01();
        double _r6 = m.m10();
        double _r7 = m.m02();
        double _r8 = m.m20();
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, java.lang.Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
    @Mutated public DoubleQuat makeFromMatrix(Double4x4R m) {
        double _r0 = m.m00();
        double _r1 = m.m11();
        double _r2 = m.m21();
        double _r3 = m.m12();
        double _r4 = m.m22();
        double _r5 = m.m01();
        double _r6 = m.m10();
        double _r7 = m.m02();
        double _r8 = m.m20();
        double _t0 = _r0 + _r1;
        double _t10 = _r4 + _t0;
        double _t14 = 1.0 + _t10;
        double _t16 = 1.0 + (_r1 - (_r0 + _r4));
        double _t17 = 1.0 + (_r4 - _t0);
        makeFromMatrix_s11ab8262_tail(this, 1.0 + (_r0 - (_r1 + _r4)), _t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, java.lang.Math.max(_r1, _r4), _r1, _r4, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
        Double4x4Impl d = (Double4x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0);
        d.m10 = 2.0 * Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0 * Math.fma(this.x, this.z, -_t2);
        d.m30 = 0.0;
        d.m01 = 2.0 * Math.fma(this.x, this.y, -_t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0);
        d.m21 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        d.m31 = 0.0;
        d.m02 = 2.0 * Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0);
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
        Double3x3Impl d = (Double3x3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0);
        d.m10 = 2.0 * Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0 * Math.fma(this.x, this.z, -_t2);
        d.m01 = 2.0 * Math.fma(this.x, this.y, -_t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0);
        d.m21 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        d.m02 = 2.0 * Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0);
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
        Double3x4Impl d = (Double3x4Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        d.m00 = Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0);
        d.m01 = 2.0 * Math.fma(this.x, this.y, -_t1);
        d.m02 = 2.0 * Math.fma(this.x, this.z, _t2);
        d.m03 = 0.0;
        d.m10 = 2.0 * Math.fma(this.x, this.y, _t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0);
        d.m12 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        d.m13 = 0.0;
        d.m20 = 2.0 * Math.fma(this.x, this.z, -_t2);
        d.m21 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        d.m22 = Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0);
        d.m23 = 0.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
    public DoubleQuat decomposeSwingTwist(Double3R axis, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
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
    public DoubleQuat decomposeSwingTwist(double axisX, double axisY, double axisZ, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist) {
        DoubleQuatImpl d0 = (DoubleQuatImpl) swing;
        DoubleQuatImpl d1 = (DoubleQuatImpl) twist;
        double _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = Math.fma(this.w, this.w, _t2 * _t2);
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
        d0.x = Math.fma(_rd0, _t11, -(this.w * _t12)) + Math.fma(_rd2, _t13, -(_rd1 * _t14));
        d0.y = Math.fma(_rd0, _t14, -(this.w * _t13)) + Math.fma(_rd1, _t11, -(_rd2 * _t12));
        d0.z = Math.fma(_rd1, _t12, _rd2 * _t11) + Math.fma(-_rd0, _t13, -(this.w * _t14));
        d0.w = Math.fma(_rd0, _t12, this.w * _t11) - Math.fma(-_rd2, _t14, -(_rd1 * _t13));
        d1.x = _t12;
        d1.y = _t13;
        d1.z = _t14;
        d1.w = _t11;
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
    public DoubleQuat getSwing(Double3R axis, @Mutated DoubleQuat dest) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = Math.fma(this.w, this.w, _t2 * _t2);
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
        d.x = Math.fma(_rd0, _t11, -(this.w * _t12)) + Math.fma(_rd2, _t13, -(_rd1 * _t14));
        d.y = Math.fma(_rd0, _t14, -(this.w * _t13)) + Math.fma(_rd1, _t11, -(_rd2 * _t12));
        d.z = Math.fma(_rd1, _t12, _rd2 * _t11) + Math.fma(-_rd0, _t13, -(this.w * _t14));
        d.w = Math.fma(_rd0, _t12, this.w * _t11) - Math.fma(-_rd2, _t14, -(_rd1 * _t13));
        return d;
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
    public DoubleQuat getSwing(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = Math.fma(this.w, this.w, _t2 * _t2);
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
        d.x = Math.fma(_rd0, _t11, -(this.w * _t12)) + Math.fma(_rd2, _t13, -(_rd1 * _t14));
        d.y = Math.fma(_rd0, _t14, -(this.w * _t13)) + Math.fma(_rd1, _t11, -(_rd2 * _t12));
        d.z = Math.fma(_rd1, _t12, _rd2 * _t11) + Math.fma(-_rd0, _t13, -(this.w * _t14));
        d.w = Math.fma(_rd0, _t12, this.w * _t11) - Math.fma(-_rd2, _t14, -(_rd1 * _t13));
        return d;
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
    public DoubleQuat getTwist(Double3R axis, @Mutated DoubleQuat dest) {
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
    public DoubleQuat getTwist(double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
    public DoubleQuat lerp(DoubleQuatR other, double t, @Mutated DoubleQuat dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        if (Math.useFma()) {
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
    public DoubleQuat lerp(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerp(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t4 = Math.fma(alpha, target.w() - this.w, this.w);
        double _t5 = Math.fma(alpha, target.z() - this.z, this.z);
        double _t6 = Math.fma(alpha, target.x() - this.x, this.x);
        double _t7 = Math.fma(alpha, target.y() - this.y, this.y);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t4 = Math.fma(alpha, targetW - this.w, this.w);
        double _t5 = Math.fma(alpha, targetZ - this.z, this.z);
        double _t6 = Math.fma(alpha, targetX - this.x, this.x);
        double _t7 = Math.fma(alpha, targetY - this.y, this.y);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
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
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat nlerpShortest(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t17, _t18, _t19, _t20;
        if (-Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY))) > 0.0) {
            _t17 = Math.fma(alpha, -targetW - this.w, this.w);
            _t18 = Math.fma(alpha, -targetZ - this.z, this.z);
            _t19 = Math.fma(alpha, -targetX - this.x, this.x);
            _t20 = Math.fma(alpha, -targetY - this.y, this.y);
        } else {
            _t17 = Math.fma(alpha, targetW - this.w, this.w);
            _t18 = Math.fma(alpha, targetZ - this.z, this.z);
            _t19 = Math.fma(alpha, targetX - this.x, this.x);
            _t20 = Math.fma(alpha, targetY - this.y, this.y);
        }
        double _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t25 = (1.0 / java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0) {
            d.x = _t19 * _t25;
            d.y = _t20 * _t25;
            d.z = _t18 * _t25;
            d.w = _t17 * _t25;
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
    public DoubleQuat slerp(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double targetX = target.x();
        double targetY = target.y();
        double targetZ = target.z();
        double targetW = target.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 1.0 - alpha;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        d.x = Math.fma(this.x, _t27, targetX * _t26);
        d.y = Math.fma(this.y, _t27, targetY * _t26);
        d.z = Math.fma(this.z, _t27, targetZ * _t26);
        d.w = Math.fma(this.w, _t27, targetW * _t26);
        return d;
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
    public DoubleQuat slerp(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 1.0 - alpha;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        d.x = Math.fma(this.x, _t27, targetX * _t26);
        d.y = Math.fma(this.y, _t27, targetY * _t26);
        d.z = Math.fma(this.z, _t27, targetZ * _t26);
        d.w = Math.fma(this.w, _t27, targetW * _t26);
        return d;
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
    public DoubleQuat slerpShortest(DoubleQuatR target, double alpha, @Mutated DoubleQuat dest) {
        double targetX = target.x();
        double targetY = target.y();
        double targetZ = target.z();
        double targetW = target.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(_r0, targetW, Math.fma(_r1, targetZ, Math.fma(_r2, targetX, _r3 * targetY)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
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
        if (Math.useFma()) slerpShortest_s323d976d_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24); else slerpShortest_s323d976d_tail_mulAdd(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** Private store group 0 of {@code slerpShortest}: computes and stores it; reached only through it. */
    private void slerpShortest_s323d976d_c0(DoubleQuatImpl _dst, double _t49, double _t50, double _t44, double _t45, double _t43, double _t42) {
        _dst.x = _t49 != 0.0 ? _t50 * _t44 : 0.0;
        _dst.y = _t49 != 0.0 ? _t50 * _t45 : 0.0;
        _dst.z = _t49 != 0.0 ? _t50 * _t43 : 0.0;
        _dst.w = _t49 != 0.0 ? _t50 * _t42 : 0.0;
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s323d976d_tail_fma(DoubleQuatImpl _dst, double _t17, double _r0, double _t25, double _t19, double _t21, double _t17_inv, double alpha, double _t0, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = java.lang.Math.fma(_r0, _t25, _t19 * _t21) * _t17_inv;
            _t43 = java.lang.Math.fma(_r1, _t25, _t19 * _t22) * _t17_inv;
            _t44 = java.lang.Math.fma(_r2, _t25, _t19 * _t23) * _t17_inv;
            _t45 = java.lang.Math.fma(_r3, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = java.lang.Math.fma(alpha, _t21, _r0 * _t0);
            _t43 = java.lang.Math.fma(alpha, _t22, _r1 * _t0);
            _t44 = java.lang.Math.fma(alpha, _t23, _r2 * _t0);
            _t45 = java.lang.Math.fma(alpha, _t24, _r3 * _t0);
        }
        double _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        slerpShortest_s323d976d_c0(_dst, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s323d976d_tail_mulAdd(DoubleQuatImpl _dst, double _t17, double _r0, double _t25, double _t19, double _t21, double _t17_inv, double alpha, double _t0, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = ((_r0) * (_t25) + (_t19 * _t21)) * _t17_inv;
            _t43 = ((_r1) * (_t25) + (_t19 * _t22)) * _t17_inv;
            _t44 = ((_r2) * (_t25) + (_t19 * _t23)) * _t17_inv;
            _t45 = ((_r3) * (_t25) + (_t19 * _t24)) * _t17_inv;
        } else {
            _t42 = ((alpha) * (_t21) + (_r0 * _t0));
            _t43 = ((alpha) * (_t22) + (_r1 * _t0));
            _t44 = ((alpha) * (_t23) + (_r2 * _t0));
            _t45 = ((alpha) * (_t24) + (_r3 * _t0));
        }
        double _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        slerpShortest_s323d976d_c0(_dst, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
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
    public DoubleQuat slerpShortest(double targetX, double targetY, double targetZ, double targetW, double alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(_r0, targetW, Math.fma(_r1, targetZ, Math.fma(_r2, targetX, _r3 * targetY)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
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
        if (Math.useFma()) slerpShortest_s323d976d_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24); else slerpShortest_s323d976d_tail_mulAdd(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
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
    public DoubleQuat squad(DoubleQuatR control0, DoubleQuatR control1, DoubleQuatR target, double t, @Mutated DoubleQuat dest) {
        double control0X = control0.x();
        double control0Y = control0.y();
        double control0Z = control0.z();
        double control0W = control0.w();
        double control1X = control1.x();
        double control1Y = control1.y();
        double control1Z = control1.z();
        double control1W = control1.w();
        double targetX = target.x();
        double targetY = target.y();
        double targetZ = target.z();
        double targetW = target.w();
        if (Math.useFma()) return squad_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
        return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
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
    public DoubleQuat squad(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        if (Math.useFma()) return squad_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
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
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        double _t8 = _r0 + targetW;
        double _t9 = _r1 + targetZ;
        double _t10 = _r2 + targetX;
        double _t11 = _r3 + targetY;
        double _t13 = _t0 * _t1;
        return squad_s2d5a8506_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0 - _t7, _t13, java.lang.Math.fma(-_t0, _t1, 1.0), _t13 < 0.5 ? 1.0 : 0.0, java.lang.Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        double _t8 = _r0 + targetW;
        double _t9 = _r1 + targetZ;
        double _t10 = _r2 + targetX;
        double _t11 = _r3 + targetY;
        double _t13 = _t0 * _t1;
        return squad_s2d5a8506_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0 - _t7, _t13, ((-_t0) * (_t1) + (1.0)), _t13 < 0.5 ? 1.0 : 0.0, java.lang.Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))))))), java.lang.Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))))))));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_1_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t0, double _t7, double _t12, double _t13, double _t14, double _t17, double _t25, double _t26) {
        double _t27 = quatArcAngle(_t25);
        double _t28 = quatArcAngle(_t26);
        double _t29 = 4.0 - _t25;
        double _t30 = 4.0 - _t26;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t45 = 2.0 / _t41;
        double _t46 = 2.0 / _t43;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return squad_s2d5a8506_2_fma(d, _t13, _t14, _t17, java.lang.Math.fma(control0X, _t57, control1X * _t55), java.lang.Math.fma(control0W, _t57, control1W * _t55), java.lang.Math.fma(_r0, _t58, targetW * _t56), java.lang.Math.fma(control0Z, _t57, control1Z * _t55), java.lang.Math.fma(_r1, _t58, targetZ * _t56), java.lang.Math.fma(_r2, _t58, targetX * _t56), java.lang.Math.fma(control0Y, _t57, control1Y * _t55), java.lang.Math.fma(_r3, _t58, targetY * _t56));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_1_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t0, double _t7, double _t12, double _t13, double _t14, double _t17, double _t25, double _t26) {
        double _t27 = quatArcAngle(_t25);
        double _t28 = quatArcAngle(_t26);
        double _t29 = 4.0 - _t25;
        double _t30 = 4.0 - _t26;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t45 = 2.0 / _t41;
        double _t46 = 2.0 / _t43;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return squad_s2d5a8506_2_mulAdd(d, _t13, _t14, _t17, ((control0X) * (_t57) + (control1X * _t55)), ((control0W) * (_t57) + (control1W * _t55)), ((_r0) * (_t58) + (targetW * _t56)), ((control0Z) * (_t57) + (control1Z * _t55)), ((_r1) * (_t58) + (targetZ * _t56)), ((_r2) * (_t58) + (targetX * _t56)), ((control0Y) * (_t57) + (control1Y * _t55)), ((_r3) * (_t58) + (targetY * _t56)));
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_2_fma(DoubleQuatImpl d, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
        double _t75 = _t68 + _t69;
        double _t76 = _t70 + _t71;
        double _t77 = _t67 + _t72;
        double _t78 = _t73 + _t74;
        double _t83 = java.lang.Math.min(4.0, java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t77, _t77, _t78 * _t78))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        d.x = java.lang.Math.fma(_t67, _t98, _t72 * _t99);
        d.y = java.lang.Math.fma(_t73, _t98, _t74 * _t99);
        d.z = java.lang.Math.fma(_t70, _t98, _t71 * _t99);
        d.w = java.lang.Math.fma(_t68, _t98, _t69 * _t99);
        return d;
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s2d5a8506_2_mulAdd(DoubleQuatImpl d, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
        double _t75 = _t68 + _t69;
        double _t76 = _t70 + _t71;
        double _t77 = _t67 + _t72;
        double _t78 = _t73 + _t74;
        double _t83 = java.lang.Math.min(4.0, ((_t75) * (_t75) + (((_t76) * (_t76) + (((_t77) * (_t77) + (_t78 * _t78)))))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        d.x = ((_t67) * (_t98) + (_t72 * _t99));
        d.y = ((_t73) * (_t98) + (_t74 * _t99));
        d.z = ((_t70) * (_t98) + (_t71 * _t99));
        d.w = ((_t68) * (_t98) + (_t69 * _t99));
        return d;
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
    public DoubleQuat mul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherZ, _rd1, -(otherY * _rd2));
            d.y = java.lang.Math.fma(otherX, _rd2, otherW * _rd1) + java.lang.Math.fma(otherY, this.w, -(otherZ * _rd0));
            d.z = java.lang.Math.fma(otherY, _rd0, otherZ * this.w) + java.lang.Math.fma(otherW, _rd2, -(otherX * _rd1));
            d.w = java.lang.Math.fma(otherW, this.w, -(otherX * _rd0)) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
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
    public DoubleQuat preMul(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -(otherX * _rd2));
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -(otherX * _rd0)) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
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
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        if (Math.useFma()) {
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
        if (Math.useFma()) {
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
        if (Math.useFma()) {
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))), this.w);
        } else {
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))), this.w);
        }
    }


    /**
     * Compute the angle in radians between this quaternion and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians between this quaternion and {@code other}
     */
    public double angleTo(DoubleQuatR other) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }


    /**
     * Compute the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
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
     * @return the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public double angleTo(double otherX, double otherY, double otherZ, double otherW) {
        double _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
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
        if (Math.useFma()) {
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
     * assuming unit length (the positive square root is chosen) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat calculateW(@Mutated DoubleQuat dest) {
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = java.lang.Math.sqrt(java.lang.Math.max(0.0, java.lang.Math.fma(-_rd0, _rd0, java.lang.Math.fma(-_rd1, _rd1, java.lang.Math.fma(-_rd2, _rd2, 1.0)))));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = java.lang.Math.sqrt(java.lang.Math.max(0.0, ((-_rd0) * (_rd0) + (((-_rd1) * (_rd1) + (((-_rd2) * (_rd2) + (1.0))))))));
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
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat conjugateBy(DoubleQuatR q, @Mutated DoubleQuat dest) {
        double qX = q.x();
        double qY = q.y();
        double qZ = q.z();
        double qW = q.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = Math.fma(qX, this.y, qW * this.z) + Math.fma(qZ, this.w, -(qY * this.x));
        double _t21 = Math.fma(qY, this.w, qZ * this.x) + Math.fma(qW, this.y, -(qX * this.z));
        double _t22 = Math.fma(qX, this.w, qW * this.x) + Math.fma(qY, this.z, -(qZ * this.y));
        double _t23 = Math.fma(qW, this.w, -(qX * this.x)) - Math.fma(qY, this.y, qZ * this.z);
        d.x = Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23));
        d.y = Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20));
        d.z = Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23));
        d.w = Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21));
        return d;
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
    public DoubleQuat conjugateBy(double qX, double qY, double qZ, double qW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t20 = Math.fma(qX, this.y, qW * this.z) + Math.fma(qZ, this.w, -(qY * this.x));
        double _t21 = Math.fma(qY, this.w, qZ * this.x) + Math.fma(qW, this.y, -(qX * this.z));
        double _t22 = Math.fma(qX, this.w, qW * this.x) + Math.fma(qY, this.z, -(qZ * this.y));
        double _t23 = Math.fma(qW, this.w, -(qX * this.x)) - Math.fma(qY, this.y, qZ * this.z);
        d.x = Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23));
        d.y = Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20));
        d.z = Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23));
        d.w = Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21));
        return d;
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
    public DoubleQuat difference(DoubleQuatR other, @Mutated DoubleQuat dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t3_inv = 1.0 / Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = (Math.fma(otherX, this.w, -(otherW * _rd0)) + Math.fma(otherY, this.z, -(otherZ * _rd1))) * _t3_inv;
        d.y = Math.fma(Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, Math.fma(-otherX, _sp1, -(otherW * _sp0)));
        d.z = (Math.fma(otherX, _rd1, -(otherW * this.z)) + Math.fma(otherZ, this.w, -(otherY * _rd0))) * _t3_inv;
        d.w = Math.fma(Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, -Math.fma(-otherZ, _sp1, -(otherY * _sp0)));
        return d;
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
    public DoubleQuat difference(double otherX, double otherY, double otherZ, double otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t3_inv = 1.0 / Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = (Math.fma(otherX, this.w, -(otherW * _rd0)) + Math.fma(otherY, this.z, -(otherZ * _rd1))) * _t3_inv;
        d.y = Math.fma(Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, Math.fma(-otherX, _sp1, -(otherW * _sp0)));
        d.z = (Math.fma(otherX, _rd1, -(otherW * this.z)) + Math.fma(otherZ, this.w, -(otherY * _rd0))) * _t3_inv;
        d.w = Math.fma(Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, -Math.fma(-otherZ, _sp1, -(otherY * _sp0)));
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
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        if (Math.useFma()) {
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
        if (Math.useFma()) {
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = java.lang.Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t7 = Math.sin(_t5);
            double _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t2 = java.lang.Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t7 = Math.sin(_t5);
            double _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
            return d;
        }
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.y * this.z;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * Math.fma(this.x, this.w, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.x, this.w, _t1), Math.fma(-2.0, Math.fma(this.x, this.x, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.z, this.w, -(this.x * this.y)), Math.fma(-2.0, Math.fma(this.y, this.y, _t3), 1.0));
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t12));
        return d;
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.z * this.z;
        double _t1 = this.y * this.z;
        double _t7 = 2.0 * Math.fma(this.x, this.w, _t1);
        double _t8 = 2.0 * Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.x, this.w, -_t1), Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0));
            d.y = 0.0;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0 * Math.fma(this.x, this.z, this.y * this.w), Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0));
        }
        d.z = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * Math.fma(this.x, this.w, -(this.y * this.z));
        double _t10 = Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            d.y = Math.atan2(2.0 * Math.fma(this.y, this.w, -(this.x * this.z)), Math.fma(-2.0, Math.fma(this.y, this.y, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.x, this.y, this.z * this.w), Math.fma(-2.0, Math.fma(this.x, this.x, _t3), 1.0));
        }
        return d;
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * Math.fma(this.y, this.w, -(this.x * this.z));
        double _t9 = Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.y = Math.atan2(2.0 * Math.fma(this.x, this.z, this.y * this.w), Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.x, this.w, -(this.y * this.z)), Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        return d;
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.z * this.z;
        double _t7 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        double _t8 = 2.0 * Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = Math.fma(-2.0, Math.fma(this.x, this.x, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.y = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.x, this.y, this.z * this.w), Math.fma(-2.0, Math.fma(this.y, this.y, _t1), 1.0));
        } else {
            d.y = Math.atan2(2.0 * Math.fma(this.y, this.w, -(this.x * this.z)), Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * Math.fma(this.y, this.w, -(this.x * this.z));
        double _t9 = Math.fma(-2.0, Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.z, this.w, -(this.x * this.y)), Math.fma(-2.0, Math.fma(this.x, this.x, _t0), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0, Math.fma(this.x, this.x, this.y * this.y), 1.0));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
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
    public DoubleQuat integrate(Double3R angularVel, double dt, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 0.5 * dt;
        double _t1 = angularVel.z() * _t0;
        double _t2 = angularVel.x() * _t0;
        double _t3 = angularVel.y() * _t0;
        double _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
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
        d.x = Math.fma(_rd0, _t10, this.w * _t15) + Math.fma(_rd2, _t16, -(_rd1 * _t17));
        d.y = Math.fma(_rd0, _t17, this.w * _t16) + Math.fma(_rd1, _t10, -(_rd2 * _t15));
        d.z = Math.fma(_rd1, _t15, _rd2 * _t10) + Math.fma(this.w, _t17, -(_rd0 * _t16));
        d.w = Math.fma(this.w, _t10, -(_rd0 * _t15)) - Math.fma(_rd1, _t16, _rd2 * _t17);
        return d;
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
    public DoubleQuat integrate(double angularVelX, double angularVelY, double angularVelZ, double dt, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
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
        d.x = Math.fma(_rd0, _t10, this.w * _t15) + Math.fma(_rd2, _t16, -(_rd1 * _t17));
        d.y = Math.fma(_rd0, _t17, this.w * _t16) + Math.fma(_rd1, _t10, -(_rd2 * _t15));
        d.z = Math.fma(_rd1, _t15, _rd2 * _t10) + Math.fma(this.w, _t17, -(_rd0 * _t16));
        d.w = Math.fma(this.w, _t10, -(_rd0 * _t15)) - Math.fma(_rd1, _t16, _rd2 * _t17);
        return d;
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invNegativeZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0);
            d.y = -(2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)));
            d.z = -(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0));
            d.y = -(2.0 * ((this.x) * (this.y) - (this.z * this.w)));
            d.z = -(2.0 * ((this.x) * (this.z) + (this.y * this.w)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w));
            d.y = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0);
            d.z = -(2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * ((this.x) * (this.y) + (this.z * this.w)));
            d.y = ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0));
            d.z = -(2.0 * ((this.y) * (this.z) - (this.x * this.w)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)));
            d.y = -(2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z));
            d.z = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * ((this.x) * (this.z) - (this.y * this.w)));
            d.y = -(2.0 * ((this.x) * (this.w) + (this.y * this.z)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
            d.y = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((-2.0) * (((this.y) * (this.y) + (this.z * this.z))) + (1.0));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
            d.z = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((-2.0) * (((this.x) * (this.x) + (this.z * this.z))) + (1.0));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
            d.y = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((-2.0) * (((this.x) * (this.x) + (this.y * this.y))) + (1.0));
            return d;
        }
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 invPositiveZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        if (Math.useFma()) {
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
        if (Math.useFma()) {
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
        if (Math.useFma()) {
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negativeZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        double _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        if (Math.useFma()) {
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0);
            d.y = -(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w));
            d.z = -(2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0));
            d.y = -(2.0 * ((this.x) * (this.y) + (this.z * this.w)));
            d.z = -(2.0 * ((this.x) * (this.z) - (this.y * this.w)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)));
            d.y = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0);
            d.z = -(2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * ((this.x) * (this.y) - (this.z * this.w)));
            d.y = ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0));
            d.z = -(2.0 * ((this.x) * (this.w) + (this.y * this.z)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w));
            d.y = -(2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)));
            d.z = java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -(2.0 * ((this.x) * (this.z) + (this.y * this.w)));
            d.y = -(2.0 * ((this.y) * (this.z) - (this.x * this.w)));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0);
            d.y = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((-2.0) * (((this.y) * (this.y) + (this.z * this.z))) + (1.0));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0);
            d.z = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((-2.0) * (((this.x) * (this.x) + (this.z * this.z))) + (1.0));
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
            d.y = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((-2.0) * (((this.x) * (this.x) + (this.y * this.y))) + (1.0));
            return d;
        }
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 positiveZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * Math.fma(this.y, this.z, -(this.x * this.w));
        double _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat pow(double t, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        double _t12 = java.lang.Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(Math.fma(this.w, this.w, _t2)))), 1.7976931348623157E308);
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
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t28 = Math.sin(_t26);
        double _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
        }
        d.w = Math.cosFromSin(_t28, _t26) * _t12;
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            double _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -(otherX * _rd2));
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -(otherX * _rd0)) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
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
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
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
        double targetX = target.x();
        double targetY = target.y();
        double targetZ = target.z();
        double targetW = target.w();
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }


    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle and store the result in {@code dest}.
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
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_fma(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t7 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        double _t11 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
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
        double _t17 = _r0 - _t13;
        double _t18 = _r1 - _t14;
        double _t19 = _r2 - _t15;
        double _t20 = _r3 - _t16;
        double _t21 = _r0 + _t13;
        double _t22 = _r1 + _t14;
        double _t23 = _r2 + _t15;
        double _t24 = _r3 + _t16;
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_fma((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t39, 1.0 - _t39, Math.sin(_t11 * _t39));
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_mulAdd(double targetX, double targetY, double targetZ, double targetW, double step, @Mutated DoubleQuat dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t7 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
        double _t11 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
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
        double _t17 = _r0 - _t13;
        double _t18 = _r1 - _t14;
        double _t19 = _r2 - _t15;
        double _t20 = _r3 - _t16;
        double _t21 = _r0 + _t13;
        double _t22 = _r1 + _t14;
        double _t23 = _r2 + _t15;
        double _t24 = _r3 + _t16;
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))), java.lang.Math.sqrt(((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        return rotateTowards_sbfa68797_1_mulAdd((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t39, 1.0 - _t39, Math.sin(_t11 * _t39));
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39, double _t40, double _t42) {
        double _t44 = Math.sin(_t40 * _t11);
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = java.lang.Math.fma(_r0, _t44, _t42 * _t13) * _t12_inv;
            _t66 = java.lang.Math.fma(_r1, _t44, _t42 * _t14) * _t12_inv;
            _t67 = java.lang.Math.fma(_r2, _t44, _t42 * _t15) * _t12_inv;
            _t68 = java.lang.Math.fma(_r3, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = java.lang.Math.fma(_r0, _t40, _t13 * _t39);
            _t66 = java.lang.Math.fma(_r1, _t40, _t14 * _t39);
            _t67 = java.lang.Math.fma(_r2, _t40, _t15 * _t39);
            _t68 = java.lang.Math.fma(_r3, _t40, _t16 * _t39);
        }
        double _t72 = java.lang.Math.fma(_t65, _t65, java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, _t68 * _t68)));
        double _t73 = (1.0 / java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0 ? _t73 * _t67 : 0.0;
        d.y = _t72 != 0.0 ? _t73 * _t68 : 0.0;
        d.z = _t72 != 0.0 ? _t73 * _t66 : 0.0;
        d.w = _t72 != 0.0 ? _t73 * _t65 : 0.0;
        return d;
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbfa68797_1_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39, double _t40, double _t42) {
        double _t44 = Math.sin(_t40 * _t11);
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = ((_r0) * (_t44) + (_t42 * _t13)) * _t12_inv;
            _t66 = ((_r1) * (_t44) + (_t42 * _t14)) * _t12_inv;
            _t67 = ((_r2) * (_t44) + (_t42 * _t15)) * _t12_inv;
            _t68 = ((_r3) * (_t44) + (_t42 * _t16)) * _t12_inv;
        } else {
            _t65 = ((_r0) * (_t40) + (_t13 * _t39));
            _t66 = ((_r1) * (_t40) + (_t14 * _t39));
            _t67 = ((_r2) * (_t40) + (_t15 * _t39));
            _t68 = ((_r3) * (_t40) + (_t16 * _t39));
        }
        double _t72 = ((_t65) * (_t65) + (((_t66) * (_t66) + (((_t67) * (_t67) + (_t68 * _t68))))));
        double _t73 = (1.0 / java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0 ? _t73 * _t67 : 0.0;
        d.y = _t72 != 0.0 ? _t73 * _t68 : 0.0;
        d.z = _t72 != 0.0 ? _t73 * _t66 : 0.0;
        d.w = _t72 != 0.0 ? _t73 * _t65 : 0.0;
        return d;
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
    public DoubleQuat lookAlong(Double3R dir, Double3R up, @Mutated DoubleQuat dest) {
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
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        if (Math.useFma()) return lookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
    }

    /** {@code lookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat lookAlong_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t28 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t37 = java.lang.Math.fma(dirZ, _t28, -(dirY * _t29));
        double _t38 = java.lang.Math.fma(dirY, _t30, -(dirX * _t28));
        double _t39 = java.lang.Math.fma(dirX, _t29, -(dirZ * _t30));
        double _ct0 = java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_se6cf3819_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t16, dirX * _t16, dirY * _t16);
    }

    /** {@code lookAlong} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat lookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t28 = ((-dirY) * (_sp0) + (upY));
        double _t29 = ((-dirZ) * (_sp0) + (upZ));
        double _t30 = ((-dirX) * (_sp0) + (upX));
        double _t37 = ((dirZ) * (_t28) - (dirY * _t29));
        double _t38 = ((dirY) * (_t30) - (dirX * _t28));
        double _t39 = ((dirX) * (_t29) - (dirZ * _t30));
        double _ct0 = ((_t38) * (_t38) + (((_t39) * (_t39) + (_t37 * _t37))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        return lookAlong_se6cf3819_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, dirZ * _t16, dirX * _t16, dirY * _t16);
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t38, double _t39, double _t45, double _r0, double _r1, double _r2, double _r3, double _t1, double _t17, double _t18, double _t19) {
        double _t20 = -_t18;
        double _t22 = -_t17;
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = java.lang.Math.fma(-_t37, _t45, 1.0);
        double _t65 = java.lang.Math.fma(_t18, _t48, -(_t19 * _t46));
        double _t66 = java.lang.Math.fma(_t19, _t47, -(_t17 * _t48));
        double _t78 = java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(dirZ, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t37, _t45, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, java.lang.Math.fma(_t1, _t16, 1.0))));
        return lookAlong_se6cf3819_2_fma(dirZ, d, _t16, _t37, _t45, _r0, _r1, _r2, _r3, _t17, _t46, java.lang.Math.fma(dirX, _t16, _t47), java.lang.Math.fma(dirX, _t16, -_t47), java.lang.Math.fma(_t17, _t46, -(_t18 * _t47)), java.lang.Math.fma(dirY, _t16, _t65), java.lang.Math.fma(-dirY, _t16, _t65), java.lang.Math.fma(_t39, _t45, _t66), java.lang.Math.fma(_t39, _t45, -_t66), _t78, _t80, java.lang.Math.fma(dirZ, _t16, java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t18, _t47, _t50))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), java.lang.Math.fma(_t17, _t46, java.lang.Math.fma(_t20, _t47, java.lang.Math.fma(_t1, _t16, _t50))));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t38, double _t39, double _t45, double _r0, double _r1, double _r2, double _r3, double _t1, double _t17, double _t18, double _t19) {
        double _t20 = -_t18;
        double _t22 = -_t17;
        double _t46 = _t37 * _t45;
        double _t47 = _t38 * _t45;
        double _t48 = _t39 * _t45;
        double _t50 = ((-_t37) * (_t45) + (1.0));
        double _t65 = ((_t18) * (_t48) - (_t19 * _t46));
        double _t66 = ((_t19) * (_t47) - (_t17 * _t48));
        double _t78 = ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t37) * (_t45) + (((dirZ) * (_t16) + (1.0))))))));
        double _t80 = ((_t37) * (_t45) + (((_t22) * (_t46) + (((_t18) * (_t47) + (((_t1) * (_t16) + (1.0))))))));
        return lookAlong_se6cf3819_2_mulAdd(dirZ, d, _t16, _t37, _t45, _r0, _r1, _r2, _r3, _t17, _t46, ((dirX) * (_t16) + (_t47)), ((dirX) * (_t16) - (_t47)), ((_t17) * (_t46) - (_t18 * _t47)), ((dirY) * (_t16) + (_t65)), ((-dirY) * (_t16) + (_t65)), ((_t39) * (_t45) + (_t66)), ((_t39) * (_t45) - (_t66)), _t78, _t80, ((dirZ) * (_t16) + (((_t22) * (_t46) + (((_t18) * (_t47) + (_t50)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), ((_t17) * (_t46) + (((_t20) * (_t47) + (((_t1) * (_t16) + (_t50)))))));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_2_fma(double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t45, double _r0, double _r1, double _r2, double _r3, double _t17, double _t46, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80, double _t81, double _sp1, double _sp4, double _t84) {
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
        d.x = java.lang.Math.fma(_r0, _t129, _r1 * _t126) + java.lang.Math.fma(_r2, _t127, -(_r3 * _t128));
        d.y = java.lang.Math.fma(_r2, _t129, _r3 * _t126) + java.lang.Math.fma(_r1, _t128, -(_r0 * _t127));
        return lookAlong_se6cf3819_3_fma(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_2_mulAdd(double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t45, double _r0, double _r1, double _r2, double _r3, double _t17, double _t46, double _t51, double _t55, double _t63, double _t69, double _t71, double _t74, double _t75, double _t78, double _t80, double _t81, double _sp1, double _sp4, double _t84) {
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
        d.x = ((_r0) * (_t129) + (_r1 * _t126)) + ((_r2) * (_t127) - (_r3 * _t128));
        d.y = ((_r2) * (_t129) + (_r3 * _t126)) + ((_r1) * (_t128) - (_r0 * _t127));
        return lookAlong_se6cf3819_3_mulAdd(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t126, double _t127, double _t128, double _t129) {
        d.z = java.lang.Math.fma(_r0, _t128, _r1 * _t127) + java.lang.Math.fma(_r3, _t129, -(_r2 * _t126));
        d.w = java.lang.Math.fma(_r1, _t129, -(_r0 * _t126)) - java.lang.Math.fma(_r2, _t128, _r3 * _t127);
        return d;
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_se6cf3819_3_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t126, double _t127, double _t128, double _t129) {
        d.z = ((_r0) * (_t128) + (_r1 * _t127)) + ((_r3) * (_t129) - (_r2 * _t126));
        d.w = ((_r1) * (_t129) - (_r0 * _t126)) - ((_r2) * (_t128) + (_r3 * _t127));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_degenerate_s1f2692fe_1_fma((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t44, _t45, java.lang.Math.fma(_t43, _t24, _t21), java.lang.Math.fma(_t44, _t26, -(_t45 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_degenerate_s1f2692fe_1_mulAdd((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0 + _t24, 1.0 - _t24, _t36, _t37, _t41, _t44, _t45, ((_t43) * (_t24) + (_t21)), ((_t44) * (_t26) - (_t45 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_1_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t44, double _t45, double _t46, double _t55) {
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
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return lookAlong_degenerate_s1f2692fe_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, -_t68, _t71, java.lang.Math.fma(_t66, _t62, _t25), java.lang.Math.fma(_t67, _t62, _t25), java.lang.Math.fma(_t69, _t24, -(_t68 * _t25)), java.lang.Math.fma(_t68, _t26, -(_t72 * _t24)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t26)), java.lang.Math.fma(_t72, _t25, java.lang.Math.fma(_t71, _t26, _t29)));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_1_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t44, double _t45, double _t46, double _t55) {
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
        double _t71 = -_t69;
        double _t72 = _t66 * _t64;
        return lookAlong_degenerate_s1f2692fe_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, -_t68, _t71, ((_t66) * (_t62) + (_t25)), ((_t67) * (_t62) + (_t25)), ((_t69) * (_t24) - (_t68 * _t25)), ((_t68) * (_t26) - (_t72 * _t24)), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t26)))), ((_t72) * (_t25) + (((_t71) * (_t26) + (_t29)))));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_2_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96) {
        double _t98 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31)));
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _t102 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t103 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        return lookAlong_degenerate_s1f2692fe_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t66, _t64, _t91), java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_2_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t95, double _t96) {
        double _t98 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31))))));
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _t102 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t103 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        return lookAlong_degenerate_s1f2692fe_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t63, _t66, _t69, _t70, _t73, _t76, _t87, _t95, _t96, _t98, _t99, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t66) * (_t64) + (_t91)), ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_3_fma(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
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
        d.x = java.lang.Math.fma(_r0, _t151, _r1 * _t148) + java.lang.Math.fma(_r2, _t149, -(_r3 * _t150));
        d.y = java.lang.Math.fma(_r2, _t151, _r3 * _t148) + java.lang.Math.fma(_r1, _t150, -(_r0 * _t149));
        d.z = java.lang.Math.fma(_r0, _t150, _r1 * _t149) + java.lang.Math.fma(_r3, _t151, -(_r2 * _t148));
        d.w = java.lang.Math.fma(_r1, _t151, -(_r0 * _t148)) - java.lang.Math.fma(_r2, _t150, _r3 * _t149);
        return d;
    }

    /** Piece 4 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_degenerate_s1f2692fe_3_mulAdd(DoubleQuatImpl d, double _r0, double _r1, double _r2, double _r3, double _t24, double _t25, double _t63, double _t66, double _t69, double _t70, double _t73, double _t76, double _t87, double _t95, double _t96, double _t98, double _t99, double _sp3, double _t102, double _t103, double _sp0, double _sp1, double _sp2, double _t114, double _t115) {
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
        d.x = ((_r0) * (_t151) + (_r1 * _t148)) + ((_r2) * (_t149) - (_r3 * _t150));
        d.y = ((_r2) * (_t151) + (_r3 * _t148)) + ((_r1) * (_t150) - (_r0 * _t149));
        d.z = ((_r0) * (_t150) + (_r1 * _t149)) + ((_r3) * (_t151) - (_r2 * _t148));
        d.w = ((_r1) * (_t151) - (_r0 * _t148)) - ((_r2) * (_t150) + (_r3 * _t149));
        return d;
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
    public @Mutated DoubleQuat makeRotationAxis(double angle, Double3R axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.x = axisX * _t1;
        this.y = axisY * _t1;
        this.z = axisZ * _t1;
        this.w = Math.cosFromSin(_t1, _t0);
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
        double _t1 = Math.sin(_t0);
        this.x = axisX * _t1;
        this.y = axisY * _t1;
        this.z = axisZ * _t1;
        this.w = Math.cosFromSin(_t1, _t0);
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
    public @Mutated DoubleQuat makeRotationLookAlong(Double3R dir, Double3R up) {
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
    @Mutated public DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Math.useFma()) return makeRotationLookAlong_fma(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat makeRotationLookAlong_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat makeRotationLookAlong_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat makeRotationLookAlong_s309f29f5_1_fma(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t39, double _t45, double _t1, double _t17, double _t18, double _t19, double _t20, double _t22, double _t46, double _t47) {
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
    private DoubleQuat makeRotationLookAlong_s309f29f5_1_mulAdd(double dirX, double dirY, double dirZ, DoubleQuatImpl d, double _t16, double _t37, double _t39, double _t45, double _t1, double _t17, double _t18, double _t19, double _t20, double _t22, double _t46, double _t47) {
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
    private DoubleQuat makeRotationLookAlong_s309f29f5_2(DoubleQuatImpl d, double _t17, double _t46, double _t51, double _t56, double _t63, double _t69, double _t70, double _t71, double _t74, double _t75, double _t77, double _t78, double _sp1, double _t80, double _t81, double _t82, double _sp3, double _sp4) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t82));
        d.x = _t77 > 0.0 ? _sp1 * _t70 : _t46 > _t71 ? 0.5 * java.lang.Math.sqrt(_t80) : _t63 > _t17 ? _sp2 * _t74 : _sp3 * _t51;
        d.y = _t77 > 0.0 ? _sp1 * _t56 : _t46 > _t71 ? _sp4 * _t74 : _t63 > _t17 ? 0.5 * java.lang.Math.sqrt(_t82) : _sp3 * _t69;
        d.z = _t77 > 0.0 ? _sp1 * _t75 : _t46 > _t71 ? _sp4 * _t51 : _t63 > _t17 ? _sp2 * _t69 : 0.5 * java.lang.Math.sqrt(_t81);
        d.w = _t77 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t78) : _t46 > _t71 ? _sp4 * _t70 : _t63 > _t17 ? _sp2 * _t56 : _sp3 * _t75;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private DoubleQuat makeRotationLookAlong_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    @Mutated private DoubleQuat makeRotationLookAlong_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat makeRotationLookAlong_degenerate_se3802ea_1_fma(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
    private DoubleQuat makeRotationLookAlong_degenerate_se3802ea_1_mulAdd(DoubleQuatImpl d, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t31, double _t32, double _t36, double _t37, double _t41, double _t45, double _t46, double _t55, double _t56) {
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
    private DoubleQuat makeRotationLookAlong_degenerate_se3802ea_2_fma(DoubleQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t93, double _t95, double _t96, double _t97) {
        double _t98 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t66, _t63, _t31)));
        double _t99 = java.lang.Math.fma(_t66, _t63, java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, _t32)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t98));
        double _t101 = java.lang.Math.fma(_t69, _t24, java.lang.Math.fma(_t70, _t25, java.lang.Math.fma(_t67, _t63, _t32)));
        double _t102 = java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t68, _t25, java.lang.Math.fma(_t67, _t63, _t31)));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t101));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _t106 = java.lang.Math.fma(_t66, _t64, _t91);
        d.x = _t97 > 0.0 ? _sp0 * _t96 : _t69 > _t93 ? 0.5 * java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_se3802ea_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, java.lang.Math.fma(_t66, _t64, -_t91));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_degenerate_se3802ea_2_mulAdd(DoubleQuatImpl d, double _t24, double _t25, double _t31, double _t32, double _t63, double _t64, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t73, double _t76, double _t87, double _t91, double _t93, double _t95, double _t96, double _t97) {
        double _t98 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t66) * (_t63) + (_t31))))));
        double _t99 = ((_t66) * (_t63) + (((_t71) * (_t24) + (((_t68) * (_t25) + (_t32))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t98));
        double _t101 = ((_t69) * (_t24) + (((_t70) * (_t25) + (((_t67) * (_t63) + (_t32))))));
        double _t102 = ((_t71) * (_t24) + (((_t68) * (_t25) + (((_t67) * (_t63) + (_t31))))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t101));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _t106 = ((_t66) * (_t64) + (_t91));
        d.x = _t97 > 0.0 ? _sp0 * _t96 : _t69 > _t93 ? 0.5 * java.lang.Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t106 : _sp2 * _t73;
        return makeRotationLookAlong_degenerate_se3802ea_3(d, _t24, _t69, _t73, _t76, _t87, _t93, _t95, _t96, _t97, _t98, _sp0, _t101, _t102, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _sp1, _sp2, _t106, ((_t66) * (_t64) - (_t91)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat makeRotationLookAlong_degenerate_se3802ea_3(DoubleQuatImpl d, double _t24, double _t69, double _t73, double _t76, double _t87, double _t93, double _t95, double _t96, double _t97, double _t98, double _sp0, double _t101, double _t102, double _sp3, double _sp1, double _sp2, double _t106, double _t107) {
        d.y = _t97 > 0.0 ? _sp0 * _t76 : _t69 > _t93 ? _sp3 * _t106 : _t87 > _t24 ? 0.5 * java.lang.Math.sqrt(_t101) : _sp2 * _t95;
        d.z = _t97 > 0.0 ? _sp0 * _t107 : _t69 > _t93 ? _sp3 * _t73 : _t87 > _t24 ? _sp1 * _t95 : 0.5 * java.lang.Math.sqrt(_t102);
        d.w = _t97 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t98) : _t69 > _t93 ? _sp3 * _t96 : _t87 > _t24 ? _sp1 * _t76 : _sp2 * _t107;
        return d;
    }


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
    public @Mutated DoubleQuat makeRotationTo(Double3R fromDir, Double3R toDir) {
        double fromDirX = fromDir.x();
        double fromDirY = fromDir.y();
        double fromDirZ = fromDir.z();
        double toDirX = toDir.x();
        double toDirY = toDir.y();
        double toDirZ = toDir.z();
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
        double _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        double _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s6ca4b61d_c0(this, 0.5 * _t23, _t15, (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
        double _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        double _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s6ca4b61d_c0(this, 0.5 * _t23, _t15, (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
        double _t1 = Math.sin(_t0);
        this.x = _t1;
        this.y = 0.0;
        this.z = 0.0;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public DoubleQuat makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        this.x = Math.fma(_t10, _t7, _t11 * _t5);
        this.y = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.z = Math.fma(_t9, _t7, _t12 * _t5);
        this.w = Math.fma(_t12, _t7, -(_t9 * _t5));
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
    @Mutated public DoubleQuat makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        this.x = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.y = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.z = Math.fma(_t10, _t5, _t11 * _t7);
        this.w = Math.fma(_t9, _t5, _t12 * _t7);
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
        double _t1 = Math.sin(_t0);
        this.x = 0.0;
        this.y = _t1;
        this.z = 0.0;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public DoubleQuat makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        this.x = Math.fma(_t10, _t7, _t11 * _t5);
        this.y = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.z = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.w = Math.fma(_t9, _t5, _t12 * _t7);
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
    @Mutated public DoubleQuat makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        this.x = Math.fma(_t9, _t6, _t12 * _t5);
        this.y = Math.fma(_t10, _t6, _t11 * _t5);
        this.z = Math.fma(_t11, _t6, -(_t10 * _t5));
        this.w = Math.fma(_t12, _t6, -(_t9 * _t5));
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
        double _t1 = Math.sin(_t0);
        this.x = 0.0;
        this.y = 0.0;
        this.z = _t1;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public DoubleQuat makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        this.x = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.y = Math.fma(_t9, _t7, _t12 * _t5);
        this.z = Math.fma(_t10, _t5, _t11 * _t7);
        this.w = Math.fma(_t12, _t7, -(_t9 * _t5));
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
    @Mutated public DoubleQuat makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        this.x = Math.fma(_t12, _t5, -(_t9 * _t8));
        this.y = Math.fma(_t10, _t8, _t11 * _t5);
        this.z = Math.fma(_t11, _t8, -(_t10 * _t5));
        this.w = Math.fma(_t9, _t5, _t12 * _t8);
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, -(this.z * _t1));
            d.z = java.lang.Math.fma(_rd1, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd0 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.w * _t1));
            d.y = ((_rd1) * (_t2) - (this.z * _t1));
            d.z = ((_rd1) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (_rd0 * _t1));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.z * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(_rd0 * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.z * _t1));
            d.y = ((_rd1) * (_t2) + (this.w * _t1));
            d.z = ((this.z) * (_t2) - (_rd0 * _t1));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t2, -(this.y * _t1));
            d.y = java.lang.Math.fma(_rd0, _t1, this.y * _t2);
            d.z = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = ((_rd0) * (_t2) - (this.y * _t1));
            d.y = ((_rd0) * (_t1) + (this.y * _t2));
            d.z = ((_rd1) * (_t2) + (this.w * _t1));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
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
    public DoubleQuat rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleQuat dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _rd0 = this.x;
        double _rd1 = this.y;
        double _rd2 = this.z;
        d.x = Math.fma(_rd0, _t5, this.w * _t2) + Math.fma(_rd1, _t3, -(_rd2 * _t4));
        d.y = Math.fma(_rd1, _t5, _rd2 * _t2) + Math.fma(this.w, _t4, -(_rd0 * _t3));
        d.z = Math.fma(_rd0, _t4, this.w * _t3) + Math.fma(_rd2, _t5, -(_rd1 * _t2));
        d.w = Math.fma(this.w, _t5, -(_rd0 * _t2)) - Math.fma(_rd1, _t4, _rd2 * _t3);
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
        return rotateTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z(), dest);
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s203b189_c0_fma(DoubleQuatImpl _dst, double _r0, double _t44, double _r1, double _t45, double _r2, double _t46, double _r3, double _t47) {
        _dst.x = java.lang.Math.fma(_r0, _t44, _r1 * _t45) + java.lang.Math.fma(_r2, _t46, -(_r3 * _t47));
        _dst.y = java.lang.Math.fma(_r2, _t44, _r3 * _t45) + java.lang.Math.fma(_r1, _t47, -(_r0 * _t46));
        _dst.z = java.lang.Math.fma(_r0, _t47, _r1 * _t46) + java.lang.Math.fma(_r3, _t44, -(_r2 * _t45));
        _dst.w = java.lang.Math.fma(_r1, _t44, -(_r0 * _t45)) - java.lang.Math.fma(_r2, _t47, _r3 * _t46);
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s203b189_c0_mulAdd(DoubleQuatImpl _dst, double _r0, double _t44, double _r1, double _t45, double _r2, double _t46, double _r3, double _t47) {
        _dst.x = ((_r0) * (_t44) + (_r1 * _t45)) + ((_r2) * (_t46) - (_r3 * _t47));
        _dst.y = ((_r2) * (_t44) + (_r3 * _t45)) + ((_r1) * (_t47) - (_r0 * _t46));
        _dst.z = ((_r0) * (_t47) + (_r1 * _t46)) + ((_r3) * (_t44) - (_r2 * _t45));
        _dst.w = ((_r1) * (_t44) - (_r0 * _t45)) - ((_r2) * (_t47) + (_r3 * _t46));
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
        rotateTo_s203b189_c0_mulAdd(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
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
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        if (Math.useFma()) rotateTo_s203b189_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -(fromDirY * toDirX)), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ)), 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3); else rotateTo_s203b189_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, this.z * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(_rd1 * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd0 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.w * _t1));
            d.y = ((_rd1) * (_t2) + (this.z * _t1));
            d.z = ((this.z) * (_t2) - (_rd1 * _t1));
            d.w = ((this.w) * (_t2) - (_rd0 * _t1));
            return d;
        }
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
    public DoubleQuat rotateXYZ(double angleX, double angleY, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, java.lang.Math.fma(_t11, _t8, -(_t10 * _t5))); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
        return d;
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
    public DoubleQuat rotateXZY(double angleX, double angleZ, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8))); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t12) * (_t5) - (_t9 * _t8)));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, -(this.z * _t1));
            d.y = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(_rd0, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((_rd0) * (_t2) - (this.z * _t1));
            d.y = ((_rd1) * (_t2) + (this.w * _t1));
            d.z = ((_rd0) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
            return d;
        }
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
    public DoubleQuat rotateYXZ(double angleY, double angleX, double angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, java.lang.Math.fma(_t11, _t8, -(_t10 * _t5))); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
        return d;
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
    public DoubleQuat rotateYZX(double angleY, double angleZ, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t2, this.y * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, -(_rd0 * _t1));
            d.z = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.x;
            double _rd1 = this.z;
            d.x = ((_rd0) * (_t2) + (this.y * _t1));
            d.y = ((this.y) * (_t2) - (_rd0 * _t1));
            d.z = ((_rd1) * (_t2) + (this.w * _t1));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
            return d;
        }
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
    public DoubleQuat rotateZXY(double angleZ, double angleX, double angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5)); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)));
        return d;
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
    public DoubleQuat rotateZYX(double angleZ, double angleY, double angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        double _r0 = this.x;
        double _r1 = this.w;
        double _r2 = this.y;
        double _r3 = this.z;
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
        if (Math.useFma()) rotateTo_s203b189_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateTo_s203b189_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * Math.fma(this.y, vZ, -(this.z * vY));
        d.x = Math.fma(this.y, _t9, Math.fma(-this.z, _t10, Math.fma(this.w, _t11, vX)));
        d.y = Math.fma(this.z, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vY)));
        d.z = Math.fma(this.x, _t10, Math.fma(-this.y, _t11, Math.fma(this.w, _t9, vZ)));
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * Math.fma(this.y, vZ, -(this.z * vY));
        d.x = Math.fma(this.y, _t9, Math.fma(-this.z, _t10, Math.fma(this.w, _t11, vX)));
        d.y = Math.fma(this.z, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vY)));
        d.z = Math.fma(this.x, _t10, Math.fma(-this.y, _t11, Math.fma(this.w, _t9, vZ)));
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
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * Math.fma(this.z, vY, -(this.y * vZ));
        d.x = Math.fma(this.z, _t9, Math.fma(-this.y, _t10, Math.fma(this.w, _t11, vX)));
        d.y = Math.fma(this.x, _t10, Math.fma(-this.z, _t11, Math.fma(this.w, _t9, vY)));
        d.z = Math.fma(this.y, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vZ)));
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
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * Math.fma(this.z, vY, -(this.y * vZ));
        d.x = Math.fma(this.z, _t9, Math.fma(-this.y, _t10, Math.fma(this.w, _t11, vX)));
        d.y = Math.fma(this.x, _t10, Math.fma(-this.z, _t11, Math.fma(this.w, _t9, vY)));
        d.z = Math.fma(this.y, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vZ)));
        return d;
    }

    public double x() { return this.x; }
    public double y() { return this.y; }
    public double z() { return this.z; }
    public double w() { return this.w; }

    @Override public String toString() {
        return "DoubleQuat(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
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
        h = 31 * h + (int)(Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(w) ^ (Double.doubleToLongBits(w) >>> 32));
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
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon
            && java.lang.Math.abs(w - other.w()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated DoubleQuat load(double[] src, int offset) {
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
    public DoubleQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
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
    public @Mutated DoubleQuat load(float[] src, int offset) {
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
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public DoubleQuat loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleQuat loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
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
